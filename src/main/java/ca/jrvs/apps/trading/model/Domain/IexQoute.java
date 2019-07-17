package ca.jrvs.apps.trading.model.Domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "companyName",
        "primaryExchange",
        "calculationPrice",
        "open",
        "openTime",
        "close",
        "closeTime",
        "high",
        "low",
        "latestPrice",
        "latestSource",
        "latestTime",
        "latestUpdate",
        "latestVolume",
        "iexRealtimePrice",
        "iexRealtimeSize",
        "iexLastUpdated",
        "delayedPrice",
        "delayedPriceTime",
        "extendedPrice",
        "extendedChange",
        "extendedChangePercent",
        "extendedPriceTime",
        "previousClose",
        "previousVolume",
        "change",
        "changePercent",
        "volume",
        "iexMarketPercent",
        "iexVolume",
        "avgTotalVolume",
        "iexBidPrice",
        "iexBidSize",
        "iexAskPrice",
        "iexAskSize",
        "marketCap",
        "peRatio",
        "week52High",
        "week52Low",
        "ytdChange",
        "lastTradeTime"
})
public class IexQoute {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("primaryExchange")
    private String primaryExchange;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("open")
    private Object open;
    @JsonProperty("openTime")
    private Object openTime;
    @JsonProperty("close")
    private Object close;
    @JsonProperty("closeTime")
    private Object closeTime;
    @JsonProperty("high")
    private Object high;
    @JsonProperty("low")
    private Object low;
    @JsonProperty("latestPrice")
    private Double latestPrice;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestTime")
    private String latestTime;
    @JsonProperty("latestUpdate")
    private Long latestUpdate;
    @JsonProperty("latestVolume")
    private Object latestVolume;
    @JsonProperty("iexRealtimePrice")
    private Double iexRealtimePrice;
    @JsonProperty("iexRealtimeSize")
    private Long iexRealtimeSize;
    @JsonProperty("iexLastUpdated")
    private Long iexLastUpdated;
    @JsonProperty("delayedPrice")
    private Object delayedPrice;
    @JsonProperty("delayedPriceTime")
    private Object delayedPriceTime;
    @JsonProperty("extendedPrice")
    private Object extendedPrice;
    @JsonProperty("extendedChange")
    private Object extendedChange;
    @JsonProperty("extendedChangePercent")
    private Object extendedChangePercent;
    @JsonProperty("extendedPriceTime")
    private Object extendedPriceTime;
    @JsonProperty("previousClose")
    private Double previousClose;
    @JsonProperty("previousVolume")
    private Object previousVolume;
    @JsonProperty("change")
    private Double change;
    @JsonProperty("changePercent")
    private Double changePercent;
    @JsonProperty("volume")
    private Object volume;
    @JsonProperty("iexMarketPercent")
    private Double iexMarketPercent;
    @JsonProperty("iexVolume")
    private Long iexVolume;
    @JsonProperty("avgTotalVolume")
    private Long avgTotalVolume;
    @JsonProperty("iexBidPrice")
    private Double iexBidPrice;
    @JsonProperty("iexBidSize")
    private Long iexBidSize;
    @JsonProperty("iexAskPrice")
    private Double iexAskPrice;
    @JsonProperty("iexAskSize")
    private Long iexAskSize;
    @JsonProperty("marketCap")
    private Long marketCap;
    @JsonProperty("peRatio")
    private Double peRatio;
    @JsonProperty("week52High")
    private Double week52High;
    @JsonProperty("week52Low")
    private Long week52Low;
    @JsonProperty("ytdChange")
    private Double ytdChange;
    @JsonProperty("lastTradeTime")
    private Long lastTradeTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("primaryExchange")
    public String getPrimaryExchange() {
        return primaryExchange;
    }

    @JsonProperty("primaryExchange")
    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    @JsonProperty("calculationPrice")
    public String getCalculationPrice() {
        return calculationPrice;
    }

    @JsonProperty("calculationPrice")
    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    @JsonProperty("open")
    public Object getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(Object open) {
        this.open = open;
    }

    @JsonProperty("openTime")
    public Object getOpenTime() {
        return openTime;
    }

    @JsonProperty("openTime")
    public void setOpenTime(Object openTime) {
        this.openTime = openTime;
    }

    @JsonProperty("close")
    public Object getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(Object close) {
        this.close = close;
    }

    @JsonProperty("closeTime")
    public Object getCloseTime() {
        return closeTime;
    }

    @JsonProperty("closeTime")
    public void setCloseTime(Object closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("high")
    public Object getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(Object high) {
        this.high = high;
    }

    @JsonProperty("low")
    public Object getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(Object low) {
        this.low = low;
    }

    @JsonProperty("latestPrice")
    public Double getLatestPrice() {
        return latestPrice;
    }

    @JsonProperty("latestPrice")
    public void setLatestPrice(Double latestPrice) {
        this.latestPrice = latestPrice;
    }

    @JsonProperty("latestSource")
    public String getLatestSource() {
        return latestSource;
    }

    @JsonProperty("latestSource")
    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    @JsonProperty("latestTime")
    public String getLatestTime() {
        return latestTime;
    }

    @JsonProperty("latestTime")
    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    @JsonProperty("latestUpdate")
    public Long getLatestUpdate() {
        return latestUpdate;
    }

    @JsonProperty("latestUpdate")
    public void setLatestUpdate(Long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    @JsonProperty("latestVolume")
    public Object getLatestVolume() {
        return latestVolume;
    }

    @JsonProperty("latestVolume")
    public void setLatestVolume(Object latestVolume) {
        this.latestVolume = latestVolume;
    }

    @JsonProperty("iexRealtimePrice")
    public Double getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    @JsonProperty("iexRealtimePrice")
    public void setIexRealtimePrice(Double iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    @JsonProperty("iexRealtimeSize")
    public Long getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    @JsonProperty("iexRealtimeSize")
    public void setIexRealtimeSize(Long iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    @JsonProperty("iexLastUpdated")
    public Long getIexLastUpdated() {
        return iexLastUpdated;
    }

    @JsonProperty("iexLastUpdated")
    public void setIexLastUpdated(Long iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    @JsonProperty("delayedPrice")
    public Object getDelayedPrice() {
        return delayedPrice;
    }

    @JsonProperty("delayedPrice")
    public void setDelayedPrice(Object delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    @JsonProperty("delayedPriceTime")
    public Object getDelayedPriceTime() {
        return delayedPriceTime;
    }

    @JsonProperty("delayedPriceTime")
    public void setDelayedPriceTime(Object delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    @JsonProperty("extendedPrice")
    public Object getExtendedPrice() {
        return extendedPrice;
    }

    @JsonProperty("extendedPrice")
    public void setExtendedPrice(Object extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @JsonProperty("extendedChange")
    public Object getExtendedChange() {
        return extendedChange;
    }

    @JsonProperty("extendedChange")
    public void setExtendedChange(Object extendedChange) {
        this.extendedChange = extendedChange;
    }

    @JsonProperty("extendedChangePercent")
    public Object getExtendedChangePercent() {
        return extendedChangePercent;
    }

    @JsonProperty("extendedChangePercent")
    public void setExtendedChangePercent(Object extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    @JsonProperty("extendedPriceTime")
    public Object getExtendedPriceTime() {
        return extendedPriceTime;
    }

    @JsonProperty("extendedPriceTime")
    public void setExtendedPriceTime(Object extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    @JsonProperty("previousClose")
    public Double getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("previousClose")
    public void setPreviousClose(Double previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("previousVolume")
    public Object getPreviousVolume() {
        return previousVolume;
    }

    @JsonProperty("previousVolume")
    public void setPreviousVolume(Object previousVolume) {
        this.previousVolume = previousVolume;
    }

    @JsonProperty("change")
    public Double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(Double change) {
        this.change = change;
    }

    @JsonProperty("changePercent")
    public Double getChangePercent() {
        return changePercent;
    }

    @JsonProperty("changePercent")
    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    @JsonProperty("volume")
    public Object getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Object volume) {
        this.volume = volume;
    }

    @JsonProperty("iexMarketPercent")
    public Double getIexMarketPercent() {
        return iexMarketPercent;
    }

    @JsonProperty("iexMarketPercent")
    public void setIexMarketPercent(Double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    @JsonProperty("iexVolume")
    public Long getIexVolume() {
        return iexVolume;
    }

    @JsonProperty("iexVolume")
    public void setIexVolume(Long iexVolume) {
        this.iexVolume = iexVolume;
    }

    @JsonProperty("avgTotalVolume")
    public Long getAvgTotalVolume() {
        return avgTotalVolume;
    }

    @JsonProperty("avgTotalVolume")
    public void setAvgTotalVolume(Long avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    @JsonProperty("iexBidPrice")
    public Double getIexBidPrice() {
        return iexBidPrice;
    }

    @JsonProperty("iexBidPrice")
    public void setIexBidPrice(Double iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    @JsonProperty("iexBidSize")
    public Long getIexBidSize() {
        return iexBidSize;
    }

    @JsonProperty("iexBidSize")
    public void setIexBidSize(Long iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    @JsonProperty("iexAskPrice")
    public Double getIexAskPrice() {
        return iexAskPrice;
    }

    @JsonProperty("iexAskPrice")
    public void setIexAskPrice(Double iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    @JsonProperty("iexAskSize")
    public Long getIexAskSize() {
        return iexAskSize;
    }

    @JsonProperty("iexAskSize")
    public void setIexAskSize(Long iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    @JsonProperty("marketCap")
    public Long getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    @JsonProperty("peRatio")
    public Double getPeRatio() {
        return peRatio;
    }

    @JsonProperty("peRatio")
    public void setPeRatio(Double peRatio) {
        this.peRatio = peRatio;
    }

    @JsonProperty("week52High")
    public Double getWeek52High() {
        return week52High;
    }

    @JsonProperty("week52High")
    public void setWeek52High(Double week52High) {
        this.week52High = week52High;
    }

    @JsonProperty("week52Low")
    public Long getWeek52Low() {
        return week52Low;
    }

    @JsonProperty("week52Low")
    public void setWeek52Low(Long week52Low) {
        this.week52Low = week52Low;
    }

    @JsonProperty("ytdChange")
    public Double getYtdChange() {
        return ytdChange;
    }

    @JsonProperty("ytdChange")
    public void setYtdChange(Double ytdChange) {
        this.ytdChange = ytdChange;
    }

    @JsonProperty("lastTradeTime")
    public Long getLastTradeTime() {
        return lastTradeTime;
    }

    @JsonProperty("lastTradeTime")
    public void setLastTradeTime(Long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "IexQoute{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", primaryExchange='" + primaryExchange + '\'' +
                ", calculationPrice='" + calculationPrice + '\'' +
                ", open=" + open +
                ", openTime=" + openTime +
                ", close=" + close +
                ", closeTime=" + closeTime +
                ", high=" + high +
                ", low=" + low +
                ", latestPrice=" + latestPrice +
                ", latestSource='" + latestSource + '\'' +
                ", latestTime='" + latestTime + '\'' +
                ", latestUpdate=" + latestUpdate +
                ", latestVolume=" + latestVolume +
                ", iexRealtimePrice=" + iexRealtimePrice +
                ", iexRealtimeSize=" + iexRealtimeSize +
                ", iexLastUpdated=" + iexLastUpdated +
                ", delayedPrice=" + delayedPrice +
                ", delayedPriceTime=" + delayedPriceTime +
                ", extendedPrice=" + extendedPrice +
                ", extendedChange=" + extendedChange +
                ", extendedChangePercent=" + extendedChangePercent +
                ", extendedPriceTime=" + extendedPriceTime +
                ", previousClose=" + previousClose +
                ", previousVolume=" + previousVolume +
                ", change=" + change +
                ", changePercent=" + changePercent +
                ", volume=" + volume +
                ", iexMarketPercent=" + iexMarketPercent +
                ", iexVolume=" + iexVolume +
                ", avgTotalVolume=" + avgTotalVolume +
                ", iexBidPrice=" + iexBidPrice +
                ", iexBidSize=" + iexBidSize +
                ", iexAskPrice=" + iexAskPrice +
                ", iexAskSize=" + iexAskSize +
                ", marketCap=" + marketCap +
                ", peRatio=" + peRatio +
                ", week52High=" + week52High +
                ", week52Low=" + week52Low +
                ", ytdChange=" + ytdChange +
                ", lastTradeTime=" + lastTradeTime +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}