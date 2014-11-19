package com.jbst.exchange;

import com.google.common.base.Optional;

public class DepthItem {

    private double price;
    private double rawPrice;
    private double quantity;
    private Optional<ExchangeEnum> exchange;
    private Optional<Long> expire;

    public DepthItem(double price, double rawPrice, double quantity,
        Optional<ExchangeEnum> exchange, Optional<Long> expire) {
        this.price = price;
        this.rawPrice = rawPrice;
        this.quantity = quantity;
        this.exchange = exchange;
        this.expire = expire;
    }

    public DepthItem(double price, double rawPrice, double quantity) {
        new DepthItem(price, rawPrice, quantity, null, null);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRawPrice() {
        return rawPrice;
    }

    public void setRawPrice(double rawPrice) {
        this.rawPrice = rawPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Optional<ExchangeEnum> getExchange() {
        return exchange;
    }

    public void setExchange(Optional<ExchangeEnum> exchange) {
        this.exchange = exchange;
    }

    public Optional<Long> getExpire() {
        return expire;
    }

    public void setExpire(Optional<Long> expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "DepthItem [price=" + price + ", rawPrice=" + rawPrice
            + ", quantity=" + quantity + ", exchange=" + exchange + ", expire="
            + expire + "]";
    }
}
