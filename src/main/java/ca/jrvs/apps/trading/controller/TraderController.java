package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.Domain.Account;
import ca.jrvs.apps.trading.model.Domain.IexQoute;
import ca.jrvs.apps.trading.model.Domain.Trader;
import ca.jrvs.apps.trading.model.View.TraderAccountView;
import ca.jrvs.apps.trading.service.FundTransferService;
import ca.jrvs.apps.trading.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("/trader")
public class TraderController {
    private AccountDao accountDao;
    private TraderDao traderDao;
    private RegisterService registerService;
    private FundTransferService fundTransferService;
    @Autowired
    public TraderController(TraderDao traderDao, AccountDao accountDao,RegisterService registerService,FundTransferService fundTransferService ){
        this.traderDao= traderDao;
        this.accountDao=accountDao;
        this.registerService=registerService;
        this.fundTransferService=fundTransferService;
    }
    @PostMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TraderAccountView createAccountWithDto (@RequestBody  Trader trader){

      try {
         return registerService.createTraderAndAccount(trader);
      } catch (Exception e) {
          throw ResponseExceptionUtil.getResponseStatusException(e);
      }
    }

    @PostMapping(path="/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TraderAccountView createAccount (@PathVariable  String firstname, @PathVariable String lastname
    , @PathVariable @DateTimeFormat(pattern ="yyyy-MM-dd") LocalDate dob, @PathVariable String country , @PathVariable String email){

        try {
            Trader trader = new Trader();
            trader.setCountry(country);
           trader.setDob(Date.valueOf(dob));
            trader.setEmail(email);
            trader.setFirstName(firstname);
            trader.setLastName(lastname);
            return registerService.createTraderAndAccount(trader);
        } catch (Exception e) {
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }

    @DeleteMapping (path = "/traderId/{traderId}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteTraderWithId(@PathVariable Integer traderId) {
        try {
           registerService.deleteTraderById(traderId);
        } catch (Exception e) {
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }

    @PutMapping(path = "/deposit/accountId/{accountId}/amount/{amount}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Account deposit (@PathVariable Integer accountId, @PathVariable Double amount){
        try {
            return fundTransferService.deposit(accountId,amount);
        }catch(Exception e){
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }

    @PutMapping(path = "/withdraw/accountId/{accountId}/amount/{amount}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Account withdraw (@PathVariable Integer accountId, @PathVariable Double amount){
        try {
            return fundTransferService.withdraw(accountId,amount);
        }catch(Exception e){
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }


}
