package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.Domain.IexQoute;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/qoute")
public class QouteController {
    private MarketDataDao marketDataDao;
    public QouteController(MarketDataDao marketDataDao){
        this.marketDataDao=marketDataDao;
    }
    @GetMapping(path="/iex/ticker/{ticker}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public IexQoute getQoute (@PathVariable String ticker){
        try {
            return marketDataDao.findIexQouteByTicker(ticker);
        }catch (Exception e){
            throw new RuntimeException("Error Binding URl");
        }

    }
}
