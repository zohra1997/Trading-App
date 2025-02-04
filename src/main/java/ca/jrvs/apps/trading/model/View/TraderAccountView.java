package ca.jrvs.apps.trading.model.View;

import ca.jrvs.apps.trading.model.Domain.Account;
import ca.jrvs.apps.trading.model.Domain.Trader;

public class TraderAccountView {
    private Trader trader;
    private Account account;

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "TraderAccountView{" +
                "trader=" + trader +
                ", account=" + account +
                '}';
    }
}
