package ca.jrvs.apps.trading.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceNotFoundException {
    private Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);
    public ResourceNotFoundException(){
        throw new RuntimeException("");
    }

}
