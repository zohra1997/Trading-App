package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.Domain.Qoute;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class  QouteDao  implements CrudRepository<Qoute,String>{

    Logger logger = LoggerFactory.getLogger(QouteDao.class);
    private final static String TABLE_NAME="quote";
    private final static String ID_NAME = "ticker";
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public QouteDao(DataSource dataSource){
        jdbcTemplate= new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);

    }

    @Override
    public Qoute save(Qoute entity) {
        String sql = "insert into quote (ticker, last_price, bid_price, bid_size, ask_price, ask_size) " +
                "values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql,entity.getTicker(), entity.getLastPrice(),entity.getBidPrice(),entity.getBidSize()
        ,entity.getAskPrice(),entity.getAskSize());
        Qoute qoute = findById(entity.getTicker());
        return qoute;

    }

    @Override
    public Qoute findById(String id) {
       Qoute qoute = null;
       qoute =jdbcTemplate.queryForObject("Select * from "+TABLE_NAME+" where "+ ID_NAME +" =?",
               BeanPropertyRowMapper.newInstance(Qoute.class),id);
       return qoute;
    }

    public List<Qoute> ListOfQoutes (){
        String Sql = "Select * from "+TABLE_NAME;
        List<Qoute> qoutes = jdbcTemplate.query(Sql,BeanPropertyRowMapper.newInstance(Qoute.class));
        return qoutes;

    }

    @Override
    public boolean existrById(String id) {

       return false ;


    }

    @Override
    public void deleteById(String id) {
        if (id == null){
            throw new IllegalArgumentException("ID can not be Null");
        }
        String sql = "Delete from"+ TABLE_NAME+"where"+ID_NAME+"="+id;
        jdbcTemplate.update(sql);

    }

    public void updateQoutes (List<Qoute> qoutes) throws IncorrectResultSizeDataAccessException {
        String sql = "updte "+ TABLE_NAME+ "set last_price = ?, bid_price = ?, bid_size = ?, ask_price =?, ask_size=? where"
                +ID_NAME+"=?";
       List< Object []> batch = new ArrayList<>();
       for (Qoute q: qoutes){
           if (!existrById(q.getTicker())){
               throw new ResourceNotFoundException("Could not find Ticker to Update");
           }
           Object[] values = new Object[]{ q.getLastPrice(), q.getBidPrice(), q.getBidSize(), q.getAskPrice()
                   ,q.getAskSize()};
           batch.add(values);

           }
       int [] rows = jdbcTemplate.batchUpdate(sql,batch);
       int totalRow = Arrays.stream(rows).sum();
       if (totalRow!=qoutes.size()){
           throw new IncorrectResultSizeDataAccessException("Number of rows:",totalRow,qoutes.size());
       }
       }




    }

