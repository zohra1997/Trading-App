package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.Domain.Position;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class PositionDao {
    private JdbcTemplate jdbcTemplate;

    public PositionDao(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public List<Position> findById(Integer id){
        String sql = "select * from position  where account_id = ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Position.class),id);

    }

}
