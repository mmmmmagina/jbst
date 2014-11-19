package com.jbst.exchange;

import java.util.List;

import com.google.common.base.Optional;

public interface IExchange {

    public Depth getDepth(CurrencyEnum inCurrency, CurrencyEnum outCurrency,
        int bidLen, int askLen);

    public Account getAccountInfo();

    public SubmitOrderResult submitOrder(CurrencyEnum in, CurrencyEnum out,
        Optional<Double> price, Double amount);

    public boolean cancelOrder(String id, Optional<CurrencyEnum> in,
        Optional<CurrencyEnum> out);

    public boolean queryOrder(String id, Optional<CurrencyEnum> in,
        Optional<CurrencyEnum> out);

    default List<Order> queryAllOpeningOrder() {
        throw new UnsupportedOperationException(
            "unsupport operation in this exchange");
    }
}