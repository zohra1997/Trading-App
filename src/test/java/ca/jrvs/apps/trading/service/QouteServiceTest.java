package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QouteDao;
import ca.jrvs.apps.trading.model.Domain.IexQoute;
import ca.jrvs.apps.trading.model.Domain.Qoute;
import ca.jrvs.apps.trading.model.Domain.SecurityOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QouteServiceTest {

    @Mock
    private QouteDao qouteDao;
    @Mock
    private MarketDataDao marketDataDao;

    @InjectMocks
    QouteService qouteService;

    @Captor
    ArgumentCaptor<Qoute> qouteArgumentCaptor;

    @Before
    public void initialize (){

    }

    @Test
    public void initQuotes() {
        List<String> tickers = new ArrayList<>();
        tickers.add("AAPL");
        List<IexQoute> iexQoutes= new ArrayList<>();
        when(marketDataDao.findIexQouteByTicker(tickers)).thenReturn(iexQoutes);
      //  List<Qoute> qoutes =
    }

    @Test
    public void initQuote() {
    }

    @Test
    public void updateMarketData() {
    }
}