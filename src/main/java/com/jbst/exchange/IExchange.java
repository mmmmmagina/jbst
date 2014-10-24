package com.jbst.exchange;

import java.util.List;

import com.google.common.base.Optional;

public interface IExchange {

    public Depth getDepth(Currency inCurrency, Currency outCurrency,
        int bidLen, int askLen);

    public Account getAccountInfo();

    public boolean submitOrder(Currency in, Currency out,
        Optional<Double> price, Double amount);

    public boolean cancelOrder(String id, Optional<Currency> in,
        Optional<Currency> out);

    public boolean queryOrder(String id, Optional<Currency> in,
        Optional<Currency> out);

    default List<Order> queryAllOpeningOrder() {
        throw new UnsupportedOperationException(
            "unsupport operation in this exchange");
    }
}