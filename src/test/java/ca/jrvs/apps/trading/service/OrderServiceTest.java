package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QouteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.Domain.*;
import ca.jrvs.apps.trading.model.dto.MarketOrderDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    // Mocking the Dependencies
    @Mock
    private AccountDao accountDao;
    @Mock
    private SecurityOrderDao securityOrderDao;
    @Mock
    private QouteDao quoteDao;
    @Mock
    private PositionDao positionDao;
    //injecting Mocks
    @InjectMocks
    OrderService orderService;

     private MarketOrderDto orderDto;
     private SecurityOrder actual ;


    @Captor
    ArgumentCaptor<SecurityOrder> captorSecurityOrder;

    @Before
    public void initialize (){
        orderDto = new MarketOrderDto();
        orderDto.setAccountId(3);
        orderDto.setSize(1);
        orderDto.setTicker("TSLA");

        when(quoteDao.existById(orderDto.getTicker())).thenReturn(true);
        when(accountDao.existById(orderDto.getAccountId())).thenReturn(true);


    }

    @Test
    public void executeHappyPath(){
        Qoute qoute = new Qoute();
        Account account = new Account ();
        qoute.setAskSize(10);
        qoute.setAskPrice(100.00);
        when(quoteDao.findById(orderDto.getTicker())).thenReturn(qoute);

        account.setAmount(100.00);
        account.setId(orderDto.getAccountId());
        when(accountDao.findById(orderDto.getAccountId())).thenReturn(account);

        orderService.executeMarketOrder(orderDto);
        verify(securityOrderDao).save(captorSecurityOrder.capture());
        SecurityOrder captorOrder = captorSecurityOrder.getValue();
        assertEquals(orderStatus.FILLED, captorOrder.getStatus());

    }
    @Test

    public void executeSadPath(){
        when(quoteDao.existById(orderDto.getTicker())).thenReturn(true);
        when(accountDao.existById(orderDto.getAccountId())).thenReturn(true);
        Qoute qoute = new Qoute ();
        qoute.setAskPrice(300.00);
        qoute.setAskSize(12);
        when(quoteDao.findById(orderDto.getTicker())).thenReturn(qoute);

        Account account = new Account();
        account.setAmount(200.00);
        account.setId(orderDto.getAccountId());
        when(accountDao.findById(orderDto.getAccountId())).thenReturn(account);
        orderService.executeMarketOrder(orderDto);
        verify(securityOrderDao).save(captorSecurityOrder.capture());
        SecurityOrder captor = captorSecurityOrder.getValue();
        assertEquals(orderStatus.CANCELLED, captor.getStatus());

    }


    @Test
    public void executeSellHappyPath(){
        Qoute qoute = new Qoute();
        Account account = new Account ();
        qoute.setBidPrice(100.00);
        qoute.setBidSize(10);
        when(quoteDao.findById(orderDto.getTicker())).thenReturn(qoute);

        account.setId(orderDto.getAccountId());
        account.setAmount(200.00);
        when(accountDao.findById(orderDto.getAccountId())).thenReturn(account);

        long p = 10;
        when(positionDao.findByTicker(orderDto.getAccountId(),orderDto.getTicker())).thenReturn(p);

        orderService.executeMarketOrder(orderDto);
        verify(securityOrderDao).save(captorSecurityOrder.capture());
        SecurityOrder captorOrder = captorSecurityOrder.getValue();
        assertEquals(orderStatus.FILLED, captorOrder.getStatus());

    }

    @Test
    public void executeSellSadPath(){
        Qoute qoute = new Qoute();
        Account account = new Account ();
        qoute.setBidPrice(100.00);
        qoute.setBidSize(10);
        when(quoteDao.findById(orderDto.getTicker())).thenReturn(qoute);

        account.setId(orderDto.getAccountId());
        account.setAmount(200.00);
        when(accountDao.findById(orderDto.getAccountId())).thenReturn(account);

        long p = 1;
        when(positionDao.findByTicker(orderDto.getAccountId(),orderDto.getTicker())).thenReturn(p);

        orderService.executeMarketOrder(orderDto);
        verify(securityOrderDao).save(captorSecurityOrder.capture());
        SecurityOrder captorOrder = captorSecurityOrder.getValue();
        assertEquals(orderStatus.CANCELLED, captorOrder.getStatus());

    }

}