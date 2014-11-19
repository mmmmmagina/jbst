package com.jbst.exchange;

import java.util.HashMap;

import com.google.common.base.Optional;

//(TODO) xiaolu : unfinished
public class Btc38Exchange extends AbstractExchange {

    public Btc38Exchange(String accessKey, String secureKey,
        Optional<String> clientId) {
        super(accessKey, secureKey, clientId);
    }

    @Override
    public String getDepthUrl(CurrencyEnum inCurrency, CurrencyEnum outCurrency,
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
    public SubmitOrderResult submitOrder(CurrencyEnum in, CurrencyEnum out,
        Optional<Double> price, Double amount) {
        return new SubmitOrderResult("failed", 0L);
    }

    @Override
    public boolean cancelOrder(String id, Optional<CurrencyEnum> in,
        Optional<CurrencyEnum> out) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean queryOrder(String id, Optional<CurrencyEnum> in,
        Optional<CurrencyEnum> out) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public HashMap<String, String> addSignInfo(HashMap<String, String> src) {
        // TODO Auto-generated method stub
        return null;
    }
}
