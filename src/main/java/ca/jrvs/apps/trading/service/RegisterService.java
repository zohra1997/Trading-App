package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.Domain.Account;
import ca.jrvs.apps.trading.model.Domain.Trader;
import ca.jrvs.apps.trading.model.View.TraderAccountView;
import ca.jrvs.apps.trading.model.Domain.Account;
import ca.jrvs.apps.trading.model.Domain.Position;
import ca.jrvs.apps.trading.model.Domain.Trader;
import ca.jrvs.apps.trading.model.View.TraderAccountView;
import java.util.List;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private TraderDao traderDao;
    private AccountDao accountDao;
    private PositionDao positionDao;
    private SecurityOrderDao securityOrderDao;

    @Autowired
    public RegisterService(TraderDao traderDao, AccountDao accountDao,
                           PositionDao positionDao, SecurityOrderDao securityOrderDao) {
        this.traderDao = traderDao;
        this.accountDao = accountDao;
        this.positionDao = positionDao;
        this.securityOrderDao = securityOrderDao;
    }

    /**
     * Create a new trader and initialize a new account with 0 amount.
     * - validate user input (all fields must be non empty)
     * - create a trader
     * - create an account
     * - create, setup, and return a new traderAccountView
     *
     * @param trader trader info
     * @return traderAccountView
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public TraderAccountView createTraderAndAccount(Trader trader) {
        validate(trader);
        traderDao.save(trader);
        Account account = new Account();
        account.setAmount(0.0);
        account.setTraderId(trader.getId());
        accountDao.save(account);
        TraderAccountView traderAccountView = new TraderAccountView();
        traderAccountView.setTrader(trader);
        traderAccountView.setAccount(account);
        return traderAccountView;
    }

    /**
     * A trader can be deleted iff no open position and no cash balance.
     * - validate traderID
     * - get trader account by traderId and check account balance
     * - get positions by accountId and check positions
     * - delete all securityOrders, account, trader (in this order)
     *
     * @param traderId
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public void deleteTraderById(Integer traderId) {
        if(traderId==null){
            throw new IllegalArgumentException("Id Required:");
        }if (!traderDao.existById(traderId)){
            throw new ResourceNotFoundException("Id does not exist");
        }
        Account account =  accountDao.findByTrader(traderId);
        if (account.getAmount()!=0){
            throw new IllegalArgumentException("Account balance should be zero");
        }
        List<Position> position = positionDao.findById(account.getId());
        if (position.size()!=0){
            throw new IllegalArgumentException("Positions are not empty");
        }
        securityOrderDao.deleteById(account.getId());
        accountDao.deleteById(account.getId());
        traderDao.deleteById(traderId);

    }

    private void validate (Trader trader ){
        if ( trader.getCountry().isEmpty()){
            throw new IllegalArgumentException("Country can not be empty");
        }
        if (trader.getDob()==null)
        {
            throw new IllegalArgumentException("Date of birth can not be empty");
        }
        if (trader.getEmail()==null)
        {
            throw new IllegalArgumentException("Email is required");
        }
        if (trader.getFirstName()==null|| trader.getLastName()==null){
            throw  new IllegalArgumentException ("First name and Last name is required");
        }
    }
}
