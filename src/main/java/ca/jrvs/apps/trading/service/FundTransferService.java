package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.Domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FundTransferService {

    private AccountDao accountDao;
    private TraderDao traderDao;
    @Autowired
    public FundTransferService(AccountDao accountDao,TraderDao traderDao) {
        this.accountDao = accountDao;
        this.traderDao= traderDao;
    }

    /**
     * Deposit a fund to the account which is associated with the traderId
     * - validate user input
     * - account = accountDao.findByTraderId
     * - accountDao.updateAmountById
     *
     * @param traderId trader id
     * @param fund found amount (can't be 0)
     * @return updated Account object
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public Account deposit(Integer traderId, Double fund) {
        validate(traderId,fund);
        Account account = accountDao.findByTrader(traderId);
        double balance = account.getAmount();
       Account updatedAccount =  accountDao.updateAmountById(account.getId(),(fund+balance));
        return updatedAccount;
    }

    /**
     * Withdraw a fund from the account which is associated with the traderId
     *
     * - validate user input
     * - account = accountDao.findByTraderId
     * - accountDao.updateAmountById
     *
     * @param traderId trader ID
     * @param fund amount can't be 0
     * @return updated Account object
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public Account withdraw(Integer traderId, Double fund) {
        validate(traderId,fund);
        Account account =accountDao.findByTrader(traderId);
        Double balance = account.getAmount();
        if (fund>balance){
            throw new IllegalArgumentException("there is not enough balance");
        }

        Account updatedAccount = accountDao.updateAmountById(traderId,(balance-fund));
        return updatedAccount;
    }
    private void validate (Integer id, Double fund ){
        if (!traderDao.existById(id)|| fund <=0){
            throw new IllegalArgumentException("Please Check Trader Id and deposite amount.(amount>0)");
        }
    }
}