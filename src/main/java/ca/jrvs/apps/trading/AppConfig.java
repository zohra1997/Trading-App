package ca.jrvs.apps.trading;


import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class AppConfig {

    private Logger logger = LoggerFactory.getLogger(AppConfig.class);
    private String iex_host= "https://cloud.iexapis.com/v1";

    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public MarketDataConfig marketDataConfig() {
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));
        marketDataConfig.setHost(iex_host);
        return marketDataConfig;
    }

    @Bean
    public DataSource dataSource() {

        String jdbcUrl;
        String user;
        String password ;
        if (!System.getenv("RDS_HOSTNAME").isEmpty()){
            jdbcUrl="jdbc:postgresql://"+System.getenv("RDS_HOSTNAME")+":"+
                    System.getenv("RDS_PORT")+"jrvstrading";
            user = System.getenv("RDS_USER");
            password= System.getenv("RDS_PASSWORD");
        }
        else {
            jdbcUrl = System.getenv("PSQL_URL");
            user = System.getenv("PSQL_USER");
            password = System.getenv("PSQL_PASSWORD");
            logger.error("JDBC:" + jdbcUrl);

        }
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUrl(jdbcUrl);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }


    @Bean
    public HttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        return cm;
    }
}