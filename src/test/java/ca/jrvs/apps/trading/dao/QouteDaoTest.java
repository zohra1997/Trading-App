package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.AppConfig;
import ca.jrvs.apps.trading.model.Domain.Qoute;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QouteDaoTest {
    private AppConfig appConfig = new AppConfig();
    private QouteDao qouteDao = new QouteDao(appConfig.dataSource());
    Qoute qoute = new Qoute();
    List <Qoute> QouteExpected = new ArrayList<>();

    @Before
    public void initialize (){
        Qoute Q1= new Qoute();
        Qoute Q2 = new Qoute();
        Qoute Q3 = new Qoute();
        Q3.setTicker("AB");
        Q2.setTicker("ABC");
        Q1.setTicker("A");
        QouteExpected.add(Q2);
        QouteExpected.add(Q1);
        QouteExpected.add(Q3);

    }

    @Test
    public void save() {
        qoute.setTicker("AB");
        qoute.setBidSize(100);
        qoute.setBidPrice(200.0);
        qoute.setAskSize(12);
        qoute.setAskPrice(121.0);
        qoute.setLastPrice(300.0);
        Qoute actualQoute = qouteDao.save(qoute);
        assertEquals(qoute.getTicker(),actualQoute.getTicker());
    }

    @Test
    public void listOfQoutes() {
        List<Qoute> QouteActual = qouteDao.ListOfQoutes();
        for (int i=0;i<QouteActual.size();i++){
            assertEquals(QouteActual.get(i).getTicker(),QouteExpected.get(i).getTicker());
        }
    }

    @Test
    public void updateQoutes() {
        List<Qoute> qoutes = new ArrayList<>();
        Qoute q = new Qoute();
        q.setTicker("AAPl");
        qoutes.add(q);
        try {
            qouteDao.updateQoutes(qoutes);
            fail ("should throw exception.");
        }catch(Exception e){
            try {
                throw new ResourceNotFoundException("Could not find Ticker to Update");
            } catch (ResourceNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }
}