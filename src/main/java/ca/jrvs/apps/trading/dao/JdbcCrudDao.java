package ca.jrvs.apps.trading.dao;


import ca.jrvs.apps.trading.model.Domain.Entity;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


public abstract class JdbcCrudDao <E extends Entity,ID> implements CrudRepository<E,ID>{
    private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);
    abstract public JdbcTemplate getJdbcTemplate();
    abstract public SimpleJdbcInsert getSimpleJdbcInsert();
    abstract public String getTableName();
    abstract public String getIdName();
    abstract Class getEntityClass();

    @SuppressWarnings("unchecked")
    @Override
    public E save (E entity){
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);
        Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
        entity.setId(newId.intValue());
        return entity;
    }

    @Override
    public E findById (ID id){ return findById(getIdName() , id , getEntityClass()); }
    @SuppressWarnings("unchecked")
    public E findById(String idName, ID id, Class clazz){
      E t = null;
      String sql = "select * from " +getTableName() + " where " + idName + " =?";
    try {
        t = (E) getJdbcTemplate().queryForObject(sql, BeanPropertyRowMapper.newInstance(clazz),id);
    } catch (EmptyResultDataAccessException e){
        logger.debug("can't find the id:" + id, e);
    }
    if (t == null){
        throw new ResourceNotFoundException("Resource Not Found!");
    }
    return t;

    }

    @Override
    public boolean existById(ID id){
        return existById(getIdName(),id);
    }
    public boolean existById(String idName, ID id){
        if (id == null){
            throw new IllegalArgumentException("Id can't be Null!");
        }
        String sql = "select count (*) from "+getTableName()+" where "+idName+" = ?";
        logger.info(sql);
        Integer count = getJdbcTemplate().queryForObject(sql,Integer.class,id);
        return count !=0;
    }

    @Override
    public void deleteById(ID id){
        deleteById(getIdName(),id);
    }

    public void deleteById(String idName, ID id){

        if (id == null){
            throw new IllegalArgumentException("Id can not be NULL!");
        }
        String sql = "delete from "+ getTableName()+ " where " +idName+ " =?";
        logger.info(sql);
        getJdbcTemplate().update(sql,id);

    }

}

