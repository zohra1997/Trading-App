package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.Domain.Account;
import ca.jrvs.apps.trading.model.Domain.Qoute;
import ca.jrvs.apps.trading.model.Domain.SecurityOrder;
import ca.jrvs.apps.trading.model.Domain.orderStatus;
import ca.jrvs.apps.trading.model.dto.MarketOrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private AccountDao accountDao;
    private SecurityOrderDao securityOrderDao;
    private QouteDao quoteDao;
    private PositionDao positionDao;

    @Autowired
    public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao,
                        QouteDao quoteDao, PositionDao positionDao) {
        this.accountDao = accountDao;
        this.securityOrderDao = securityOrderDao;
        this.quoteDao = quoteDao;
        this.positionDao = positionDao;
    }

    /**
     * Execute a market order
     *
     * - validate the order (e.g. size, and ticker)
     * - Create a securityOrder (for security_order table)
     * - Handle buy or sell order
     *   - buy order : check account balance
     *   - sell order: check position for the ticker/symbol
     *   - (please don't forget to update securityOrder.status)
     * - Save and return securityOrder
     *
     * NOTE: you will need to some helper methods (protected or private)
     *
     * @param orderDto market order
     * @return SecurityOrder from security_order table
     * @throws org.springframework.dao.DataAccessException if unable to get data from DAO
     * @throws IllegalArgumentException for invalid input
     */
    public SecurityOrder executeMarketOrder(MarketOrderDto orderDto) {
        // validate the order
        validate(orderDto);
        // create security order
        SecurityOrder securityOrder = new SecurityOrder();
        securityOrder.setAccountId(orderDto.getAccountId());
        securityOrder.setSize(orderDto.getSize());
        securityOrder.setTicker(orderDto.getTicker());
        Qoute qoute = quoteDao.findById(orderDto.getTicker());
        Account account = accountDao.findById(orderDto.getAccountId());
        // buy or sell
        if(orderDto.getSize()>0){
            // buy
            securityOrder.setPrice(qoute.getAskPrice());
            StockBuy(orderDto,securityOrder,account);
        }
        else {
            // sell
            securityOrder.setPrice(qoute.getBidPrice());
            StockSell(orderDto,securityOrder,account);
        }
        return securityOrderDao.save(securityOrder);
    }

    protected void validate(MarketOrderDto order){

        if (order.getSize()==0 || order.getSize() == null){
            throw new IllegalArgumentException("seize can not be empty");
        }
        String ticker = order.getTicker();
        if (!quoteDao.existById(ticker)){
            throw new IllegalArgumentException("Ticker does not exist");
        }
        if (!accountDao.existById(order.getAccountId())){
            throw new IllegalArgumentException(order.getAccountId()+" Does not exist");

        }

    }

    protected  void StockBuy (MarketOrderDto orderDto, SecurityOrder securityOrder, Account account){
        Double amount = securityOrder.getPrice()*securityOrder.getSize();
        if (account.getAmount() >= amount){
        Double newMoney = account.getAmount()-amount;
        accountDao.updateAmountById(orderDto.getAccountId(),newMoney);
        securityOrder.setStatus(orderStatus.FILLED);
        }
        else {
            securityOrder.setStatus(orderStatus.CANCELLED);
            securityOrder.setNotes("Insufficient Funds "+amount);
        }

    }

    protected void StockSell (MarketOrderDto orderDto, SecurityOrder securityOrder, Account account){
        Long position = positionDao.findByTicker(orderDto.getAccountId(),orderDto.getTicker());
        logger.debug("AccountID :"+orderDto.getAccountId()+" Ticker: "+orderDto.getTicker());
        if (position+orderDto.getSize()>=0){
            Double amount = securityOrder.getSize()*securityOrder.getPrice();
            Double newMoney = account.getAmount()-amount;
            securityOrder.setStatus(orderStatus.FILLED);
            accountDao.updateAmountById(orderDto.getAccountId(),newMoney);
        }else
        {
            securityOrder.setStatus(orderStatus.CANCELLED);
            securityOrder.setNotes("Not enough money:(");
        }


    }
}
