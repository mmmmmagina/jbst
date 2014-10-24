package com.jbst.exchange;

import com.google.common.base.Optional;

//(TODO) xiaolu : unfinished
public class Btc38Exchange extends AbstractExchange {

    public Btc38Exchange(String accessKey, String secureKey,
        Optional<String> clientId) {
        super(accessKey, secureKey, clientId);
    }

    @Override
    public String getDepthUrl(Currency inCurrency, Currency outCurrency,
        int bidLen, int askLen) {
        return new StringBuilder()
            .append("http://api.btc38.com/v1/depth.php?c=")
            .append(inCurrency.toString().toLowerCase()).append("&mk_type=")
            .append(outCurrency.toString().toLowerCase()).toString();
    }

    @Override
    public Account getAccountInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean submitOrder(Currency in, Currency out,
        Optional<Double> price, Double amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean cancelOrder(String id, Optional<Currency> in,
        Optional<Currency> out) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean queryOrder(String id, Optional<Currency> in,
        Optional<Currency> out) {
        // TODO Auto-generated method stub
        return false;
    }
}
