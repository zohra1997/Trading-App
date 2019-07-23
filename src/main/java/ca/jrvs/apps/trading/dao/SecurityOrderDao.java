package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.Domain.SecurityOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
@Repository
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder,Integer>{

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    private static final String TABLE_NAME = "security_order";
    private static final String ID_NAME="id";

    @Autowired
    public SecurityOrderDao(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
        jdbcInsert=new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_NAME);;
    }
    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return jdbcInsert;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getIdName() {
        return ID_NAME;
    }

    @Override
    Class getEntityClass() {
        return SecurityOrder.class  ;
    }
}
