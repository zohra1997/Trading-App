package ca.jrvs.apps.trading.model.config;

public class MarketDataConfig {
    private String Host = "https://cloud.iexapis.com/v1";
    private String token = System.getenv("IEX_PUB_TOKEN");

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
