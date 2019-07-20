package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QouteDao;
import ca.jrvs.apps.trading.model.Domain.IexQoute;
import ca.jrvs.apps.trading.model.Domain.Qoute;
import ca.jrvs.apps.trading.service.QouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/qoute")
public class QouteController {
    private MarketDataDao marketDataDao;
    private QouteService qouteService;
    private QouteDao qouteDao;

    @Autowired
    public QouteController(MarketDataDao marketDataDao, QouteService qouteService, QouteDao qouteDao) {
        this.marketDataDao = marketDataDao;
        this.qouteDao = qouteDao;
        this.qouteService = qouteService;
    }


    @PutMapping(path = "/iexMarketData")
    @ResponseStatus(HttpStatus.OK)
    public void updtaeMarketData() {
        try {
            qouteService.updateMarketData();
        } catch (Exception e) {
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }

    @PutMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public void putQuote(@RequestBody Qoute quote) {
        try {
            qouteDao.updateQoutes(Collections.singletonList(quote));

        } catch (Exception e) {

            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }

    @PostMapping(path = "/tickerId/{tickerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createQuote(@PathVariable String tickerId) {
        try {
            qouteService.initQuote(tickerId);
        } catch (Exception e) {

            throw ResponseExceptionUtil.getResponseStatusException(e);

        }

    }

    @GetMapping (path = "/dailyList")
    @ResponseStatus(HttpStatus.OK)
    public List<Qoute> getDailyList() {
      try {
          return qouteDao.ListOfQoutes();
      }
      catch (Exception e){
          throw ResponseExceptionUtil.getResponseStatusException(e);

      }
    }

    @GetMapping(path = "/iex/ticker/{ticker}")
      @ResponseStatus(HttpStatus.OK)
      @ResponseBody
    public IexQoute getQuote(@PathVariable String ticker) {
        try {
            return marketDataDao.findIexQouteByTicker(ticker);
        } catch (Exception e) {
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }
}
