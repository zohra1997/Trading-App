package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QouteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.Domain.Account;
import ca.jrvs.apps.trading.model.Domain.Position;
import ca.jrvs.apps.trading.model.View.PortfolioView;
import ca.jrvs.apps.trading.model.View.TraderAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class DashboardService {

    private TraderDao traderDao;
    private PositionDao positionDao;
    private AccountDao accountDao;
    private QouteDao quoteDao;

    @Autowired
    public DashboardService(TraderDao traderDao, PositionDao positionDao, AccountDao accountDao,
                            QouteDao quoteDao) {
        this.traderDao = traderDao;
        this.positionDao = positionDao;
        this.accountDao = accountDao;
        this.quoteDao = quoteDao;
    }

    /**
     * Create and return a traderAccountView by trader ID
     * - get trader account by id
     * - get trader info by id
     * - create and return a traderAccountView
     *
     * @param traderId trader ID
     * @return traderAccountView
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public TraderAccountView getTraderAccount(Integer traderId) {
        if (!traderDao.existById(traderId)){
            throw  new IllegalArgumentException("trader with id "+ traderId +" does not exist");

        }
        TraderAccountView accountView = new TraderAccountView();
        accountView.setTrader(traderDao.findById(traderId));
        accountView.setAccount(accountDao.findByTrader(traderId));
        return accountView;
    }

    /**
     * Create and return portfolioView by trader ID
     * - get account by trader id
     * - get positions by account id
     * - create and return a portfolioView
     *
     * @param traderId
     * @return portfolioView
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public List<PortfolioView> getProfileViewByTraderId(Integer traderId) {
        PortfolioView portfolioView ;
        List<PortfolioView> mylist= new ArrayList<>();
        Account account = accountDao.findByTrader(traderId);
        List<Position> position = positionDao.findById(account.getId());
        for (Position p :position){
            portfolioView = new PortfolioView();
            p.setId(account.getId());
            portfolioView.setTicker(p.getTicker());
            portfolioView.setQoute(quoteDao.findById(p.getTicker()));
            portfolioView.setPosition(p);
            mylist.add(portfolioView);
        }
      return mylist;
    }
}