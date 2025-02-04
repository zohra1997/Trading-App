package ca.jrvs.apps.trading.model.Domain;

public class Qoute implements Entity <String> {
        private String ticker;
        private Double askPrice;
        private long askSize;
        private Double bidPrice;
        private long bidSize;
        private String Id;
        private Double lastPrice;


        public Double getAskPrice() {
            return askPrice;
        }

        public void setAskPrice(Double askPrice) {
            this.askPrice = askPrice;
        }

        public long  getAskSize() {
            return askSize;
        }

        public void setAskSize(long askSize) {
            this.askSize = askSize;
        }

        public Double getBidPrice() {
            return bidPrice;
        }

        public void setBidPrice(Double bidPrice) {
            this.bidPrice = bidPrice;
        }

        public long getBidSize() {
            return bidSize;
        }

        public void setBidSize(long bidSize) {
            this.bidSize = bidSize;
        }

        public Double getLastPrice() {
            return lastPrice;
        }

        public void setLastPrice(Double lastPrice) {
            this.lastPrice = lastPrice;
        }

        public String getTicker() {
            return ticker;
        }

        public void setTicker(String ticker) {
            this.ticker = ticker;
        }



        @Override
        public String getId() {
            return Id;
        }

        @Override
        public void setId( String s) {
            this.Id = s;

        }

    @Override
    public String toString() {
        return "Qoute{" +
                "  ticker='" + ticker + '\'' +
                ", askPrice=" + askPrice +
                ", askSize=" + askSize +
                ", bidPrice=" + bidPrice +
                ", bidSize=" + bidSize +
                ", lastPrice=" + lastPrice +
                '}';
    }
}

