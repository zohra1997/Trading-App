package ca.jrvs.apps.trading.model.View;

import ca.jrvs.apps.trading.model.Domain.Position;
import ca.jrvs.apps.trading.model.Domain.Qoute;

import java.util.List;

public class PortfolioView {
    private String ticker ;
    private Position position;
    private Qoute qoute;


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Qoute getQoute() {
        return qoute;
    }

    public void setQoute(Qoute qoute) {
        this.qoute = qoute;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "PortfolioView{" +
                "position=" + position +
                ", quote=" + qoute +
                ", ticker='" + ticker + '\'' +
                '}';
    }
}
