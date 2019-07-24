package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.Domain.Account;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
@Repository
public class AccountDao extends JdbcCrudDao<Account, Integer> {
    Logger logger = LoggerFactory.getLogger(AccountDao.class);
    private final static String TABLE_NAME="account";
    private final static String ID_NAME = "id";
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    private DataSource dataSource;

    @Autowired
    public AccountDao(DataSource dataSource){
        jdbcTemplate= new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_NAME);
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
        return Account.class;
    }

    public Account findByTrader (Integer traderId){
       return super.findById("trader_id", traderId,getEntityClass());

    }
    public boolean existById (Integer traderId){
        return super.existById("trader_id", traderId);

    }

    public Account updateAmountById(Integer id, Double amount) {
        if (super.existById(id)) {
            String sql = "UPDATE " + TABLE_NAME + " SET amount=? WHERE id=?";
            int row = jdbcTemplate.update(sql, amount, id);
            logger.debug("Update amount row=" + row);
            if (row != 1) {
                throw new IncorrectResultSizeDataAccessException(1, row);
            }
            return findById(id);
        }
        return null;
    }
}


