package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.Domain.IexQoute;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
@Repository
public class MarketDataDao {
    private HttpClientConnectionManager httpClientConnectionManager;
    private final String BATCH_URL;
    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
@Autowired
    public MarketDataDao(HttpClientConnectionManager connectionManager,  MarketDataConfig marketDataConfig){
        this.httpClientConnectionManager=connectionManager;
        BATCH_URL =  marketDataConfig.getHost() + "/stock/market/batch?symbols=%s&types=quote&token="
                + marketDataConfig.getToken();
    }
    public List<IexQoute> findIexQouteByTicker(List<String> tickers){
        String symbols = String.join(",", tickers);
        String uri = String.format(BATCH_URL,symbols);
        logger.info("GET URL:" +uri);
        String response = exeuteHttpRequest(uri);
        logger.info("response:" +response);
        List<IexQoute> qoutes = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(response);
        Iterator<String> keys = jsonObject.keys();
        ObjectMapper mapper = new ObjectMapper();
        while (keys.hasNext()){
            String value = keys.next();
            if (jsonObject.get(value)instanceof JSONObject){
                String qouteObject = ((JSONObject)jsonObject.get(value)).get("quote").toString();
                try {
                    IexQoute iexQoute = mapper.readValue(qouteObject,IexQoute.class);

                    qoutes.add(iexQoute);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        logger.info("Qoutes \n"+ qoutes);
        return qoutes;
    }
    public IexQoute findIexQouteByTicker(String ticker) {
        List<IexQoute> quotes = findIexQouteByTicker(Arrays.asList(ticker));
        if (quotes == null || quotes.size() != 1) {
            throw new DataRetrievalFailureException("Unable to get data");
        }
        return quotes.get(0);
    }

    private String exeuteHttpRequest(String url) {
        try (CloseableHttpClient httpclient = getHttpClient()) {
            HttpGet httpGet = new HttpGet(url);
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                switch (response.getStatusLine().getStatusCode()) {
                    case 200:
                        String Body = EntityUtils.toString(response.getEntity());
                        return Body;
                    case 400:
                        throw new ResourceNotFoundException("Not Found");

                    default:
                        throw new DataRetrievalFailureException("Unexpected Error:"+response.getStatusLine().getStatusCode());

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  return null;
    }


    private CloseableHttpClient getHttpClient(){
        return HttpClients.custom().setConnectionManager(httpClientConnectionManager)
                .setConnectionManagerShared(true)
               .build();
    }
}
