package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.Domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class PositionDao {
    private Logger  logger = LoggerFactory.getLogger(PositionDao.class);
    private JdbcTemplate jdbcTemplate;

    public PositionDao(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public List<Position> findById(Integer id){
        String sql = "select * from position  where account_id = ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Position.class),id);

    }

    public Long findByTicker(Integer accountId, String ticker ){
        String sql = "Select position from position where account_id =? and ticker =?";
       Long position = null;
        try {
            position = jdbcTemplate.queryForObject(sql,Long.class, accountId,ticker);
        } catch (EmptyResultDataAccessException e){
            logger.debug(String.format("select position from position where account_id=%s and ticker=%s",accountId,ticker));
        }
        return  position;
    }

}
