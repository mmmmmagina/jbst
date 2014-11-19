package com.jbst.exchange;

import java.util.HashMap;

import com.google.common.base.Optional;
import com.jbst.common.Util;

public class OkcoinCnExchange extends AbstractExchange {

    public OkcoinCnExchange(String accessKey, String secureKey,
        Optional<String> clientId) {
        super(accessKey, secureKey, clientId);
    }

    @Override
    // in_out: Btc_Cny
    public String getDepthUrl(CurrencyEnum in, CurrencyEnum out, int bidLen,
        int askLen) {
        return new StringBuilder()
            .append("https://www.okcoin.cn/api/v1/depth.do?symbol=")
            .append(in.toString().toLowerCase()).append("_")
            .append(out.toString().toLowerCase()).toString();
    }

    @Override
    public Account getAccountInfo() {
        HashMap<String, String> postParams = new HashMap<String, String>();
        postParams.put("partner", getAccessKey());
        return apiE2EFlow("https://www.okcoin.cn/api/v1/userinfo.do",
            postParams, Account.class);
    }

    @Override
    //TODO(xiaolu) to support market order
    public SubmitOrderResult submitOrder(CurrencyEnum in, CurrencyEnum out,
        Optional<Double> price, Double amount) {
        String type;
        if (CurrencyEnum.Cny == in && price.isPresent()) {
            type = "buy";
        } else if (CurrencyEnum.Cny == out && price.isPresent()) {
            type = "sell";
        } else if (CurrencyEnum.Cny == out && !price.isPresent()) {
            return new SubmitOrderResult("failed", 0L);
            // type = "buy_market";
        } else if (CurrencyEnum.Cny == in && !price.isPresent()) {
            return new SubmitOrderResult("failed", 0L);
            // type = "sell_market";
        } else {
            return new SubmitOrderResult("failed", 0L);
        }
        HashMap<String, String> postParams = new HashMap<String, String>();
        postParams.put("partner", getAccessKey());
        postParams.put("symbol", in.toString().toLowerCase() + "_" + out.toString().toLowerCase());
        postParams.put("type", type);
        postParams.put("price", price.get().toString());
        postParams.put("amount", amount.toString());
        return apiE2EFlow("https://www.okcoin.cn/api/v1/trade.do",
            postParams, SubmitOrderResult.class);
    }

    @Override
    public boolean cancelOrder(String id, Optional<CurrencyEnum> in,
        Optional<CurrencyEnum> out) {
        HashMap<String, String> postParams = new HashMap<String, String>();
        postParams.put("partner", getAccessKey());
        postParams.put("symbol", in.toString().toLowerCase() + "_" + out.toString().toLowerCase());
        postParams.put("order_id", id);
        return apiE2EFlow("https://www.okcoin.cn/api/v1/cancel_order.do",
            postParams, Boolean.class);
    }

    @Override
    public boolean queryOrder(String id, Optional<CurrencyEnum> in,
        Optional<CurrencyEnum> out) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public HashMap<String, String> addSignInfo(HashMap<String, String> src) {
        String sign = Util.md5(Util.combineToEncryptParams(src) + getSecureKey()).get();
        src.put("sign", sign);
        return src;
    }
}
