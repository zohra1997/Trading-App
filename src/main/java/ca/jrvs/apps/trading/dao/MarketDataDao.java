package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.Domain.IexQoute;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
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
import org.springframework.dao.DataRetrievalFailureException;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MarketDataDao {
    private HttpClientConnectionManager httpClientConnectionManager;
    private final String BATCH_URL;
    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);

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

        System.out.println(response);
        JSONObject jsonObject = new JSONObject(response);
        jsonObject.get("AAPL");
        System.out.println("the json objects :*******\n"+jsonObject);


        return null;
    }
    public IexQoute findiexByTicker(String ticker){


        return null;
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


    public static void main(String[] args) {
        MarketDataConfig config = new MarketDataConfig();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        MarketDataDao dao = new MarketDataDao(cm, config);
        List<String> tickers  = Arrays.asList("aapl","msft");
        dao.findIexQouteByTicker(tickers);
    }
}
