package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.Domain.Trader;
import ca.jrvs.apps.trading.model.View.TraderAccountView;
import ca.jrvs.apps.trading.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trader")
public class TraderController {
    private AccountDao accountDao;
    private TraderDao traderDao;
    private RegisterService registerService;
    @Autowired
    public TraderController(TraderDao traderDao, AccountDao accountDao,RegisterService registerService ){
        this.traderDao= traderDao;
        this.accountDao=accountDao;
        this.registerService=registerService;
    }
    @PostMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TraderAccountView createAccount (@RequestBody  Trader trader){

      try {
         return registerService.createTraderAndAccount(trader);
      } catch (Exception e) {
          throw ResponseExceptionUtil.getResponseStatusException(e);
      }
    }

//    @DeleteMapping(path="/traderId/{traderId}")
//    @


}
