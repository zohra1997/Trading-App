package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.Domain.IexQoute;
import ca.jrvs.apps.trading.model.Domain.Qoute;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MarketDataDaoTest {
    public HttpClientConnectionManager connectionManager;
    public MarketDataConfig marketDataConfig = new MarketDataConfig();
    public MarketDataDao marketDataDao = new MarketDataDao(connectionManager, marketDataConfig);

    @Test
    public void findIexQouteByTicker() {
        String [] tickers = {"AAPL", "MSFT"};
        List<IexQoute> actual= marketDataDao.findIexQouteByTicker(Arrays.asList(tickers));
        System.out.println(actual);
    }
}