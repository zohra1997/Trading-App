package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.Domain.IexQoute;
import ca.jrvs.apps.trading.model.Domain.Qoute;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MarketDataDaoTest {
    public HttpClientConnectionManager connectionManager;
    public MarketDataConfig marketDataConfig = new MarketDataConfig();
    public MarketDataDao marketDataDao = new MarketDataDao(connectionManager, marketDataConfig);

     public String expected = "[IexQoute{symbol='AAPL', companyName='Apple, Inc.', primaryExchange='NASDAQ', calculationPrice='close', open=205.26, openTime=1564752600411, close=204.02, closeTime=1564776000782, high=206.43, low=201.63, latestPrice=204.02, latestSource='Close', latestTime='August 2, 2019', latestUpdate=1564776000782, latestVolume=40831686, iexRealtimePrice=null, iexRealtimeSize=null, iexLastUpdated=null, delayedPrice=204.2, delayedPriceTime=1564776931493, extendedPrice=204.5, extendedChange=0.48, extendedChangePercent=0.00235, extendedPriceTime=1564963125076, previousClose=208.43, previousVolume=54017922, change=-4.41, changePercent=-0.02116, volume=40831686, iexMarketPercent=null, iexVolume=null, avgTotalVolume=24466879, iexBidPrice=null, iexBidSize=null, iexAskPrice=null, iexAskSize=null, marketCap=922003103600, peRatio=17.25, week52High=233.47, week52Low=142, ytdChange=0.27076, lastTradeTime=1564776931493, additionalProperties={}}]";



    @Test
    public void findIexQouteByTicker() {
        String [] tickers = {"AAPL"};
        List<IexQoute> actual= marketDataDao.findIexQouteByTicker(Arrays.asList(tickers));
        assertEquals(actual.toString(), expected);

    }
}