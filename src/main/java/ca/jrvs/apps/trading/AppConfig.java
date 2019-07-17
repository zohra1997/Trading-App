package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.activation.DataSource;

@Configuration

public class AppConfig {
    private Logger logger = LoggerFactory.getLogger(AppConfig.class);
    private String Iex_Host ;
    private String Driver_class="org.postgresql.Driver";
    private String url = System.getenv("PSQL_URL");
    private String user = System.getenv("PSQL_USER");
    private String password = System.getenv("PSQL_PASSWORD");




    @Bean
    public MarketDataConfig marketDataConfig() {
        return  new MarketDataConfig();

    }

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(Driver_class);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return  basicDataSource;
    }

    @Bean
    public HttpClientConnectionManager httpClientConnectionManager(){
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        return cm;

    }

}