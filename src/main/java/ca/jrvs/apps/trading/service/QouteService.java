package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QouteDao;
import ca.jrvs.apps.trading.model.Domain.IexQoute;
import ca.jrvs.apps.trading.model.Domain.Qoute;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class QouteService {

    private QouteDao quoteDao;
    private MarketDataDao marketDataDao;

    @Autowired
    public QouteService(QouteDao quoteDao, MarketDataDao marketDataDao) {
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    /**
     * Helper method. Map a IexQuote to a Quote entity.
     * Note: `iexQuote.getLatestPrice() == null` if the stock market is closed.
     * Make sure set a default value for number field(s).
     */
    public static Qoute buildQuoteFromIexQuote(IexQoute iexQuote) {
        Qoute qoute = new Qoute();
        qoute.setTicker(iexQuote.getSymbol());
        qoute.setId(iexQuote.getSymbol());
        qoute.setAskPrice(iexQuote.getIexAskPrice());
        qoute.setAskSize(iexQuote.getIexAskSize());
        qoute.setBidPrice(iexQuote.getIexBidPrice());
        qoute.setBidSize(iexQuote.getIexBidSize());
        qoute.setLastPrice(iexQuote.getLatestPrice());
        return qoute;
    }

    /**
     * Add a list of new tickers to the quote table. Skip existing ticker(s).
     *  - Get iexQuote(s)
     *  - convert each iexQuote to Quote entity
     *  - persist the quote to db
     *
     * @param tickers a list of tickers/symbols
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public void initQuotes(List<String> tickers) {
        List<IexQoute> iexQoutes = tickers.stream()
                .map(marketDataDao::findIexQouteByTicker).collect(Collectors.toList());
        List<Qoute> qoutes = iexQoutes.stream().map(QouteService::buildQuoteFromIexQuote).collect(Collectors.toList());
        for (Qoute q: qoutes){
            if (quoteDao.existById(q.getTicker())){
                throw new IllegalArgumentException("This ticker already exist.");

            }else
            quoteDao.save(q);
        }

    }

    /**
     * Add a new ticker to the quote table. Skip existing ticker.
     *
     * @param ticker ticker/symbol
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public void initQuote(String ticker) {

        initQuotes(Collections.singletonList(ticker));
    }


    /**
     * Update quote table against IEX source
     *  - get all quotes from the db
     *  - foreach ticker get iexQuote
     *  - convert iexQuote to quote entity
     *  - persist quote to db
     *
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public void updateMarketData() {
        List<String> tickers = (quoteDao.ListOfQoutes()).
                stream().map(Qoute::getTicker).collect(Collectors.toList());
        List<IexQoute>iexQoutes = tickers.stream().map(marketDataDao::findIexQouteByTicker).collect(Collectors.toList());
        List<Qoute> qoutes = iexQoutes.stream().map(QouteService::buildQuoteFromIexQuote).collect(Collectors.toList());
        quoteDao.updateQoutes(qoutes);
    }
}
