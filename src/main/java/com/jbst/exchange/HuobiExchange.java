package com.jbst.exchange;

import java.util.HashMap;

import com.google.common.base.Optional;
import com.jbst.common.Util;

public class HuobiExchange extends AbstractExchange {

    public HuobiExchange(String accessKey, String secureKey,
        Optional<String> clientId) {
        super(accessKey, secureKey, clientId);
    }

    @Override
    public String getDepthUrl(CurrencyEnum in, CurrencyEnum out, int bidLen,
        int askLen) {
        String dUrl;
        switch (in) {
        case Btc:
            dUrl = "http://market.huobi.com/staticmarket/depth_btc_json.js";
            break;
        case Ltc:
            dUrl = "http://market.huobi.com/staticmarket/depth_ltc_json.js";
            break;
        default:
            dUrl = "";
            break;
        }
        return dUrl;
    }

    @Override
    public Account getAccountInfo() {
        HashMap<String, String> postParams = new HashMap<String, String>();
        String created = String.valueOf(System.currentTimeMillis()/1000);
        postParams.put("method", "get_account_info");
        postParams.put("access_key", getAccessKey());
        postParams.put("created", created);
        return apiE2EFlow("https://api.huobi.com/apiv2.php",
            postParams, Account.class);
    }

    @Override
    public SubmitOrderResult submitOrder(CurrencyEnum in, CurrencyEnum out,
        Optional<Double> price, Double amount) {
        String type;
        String created = String.valueOf(System.currentTimeMillis()/1000);
        if (CurrencyEnum.Cny == in && price.isPresent()) {
            type = "buy";
        } else if (CurrencyEnum.Cny == out && price.isPresent()) {
            type = "sell";
        } else if (CurrencyEnum.Cny == in && !price.isPresent()) {
            type = "buy_market";
        } else if (CurrencyEnum.Cny == out && !price.isPresent()) {
            type = "sell_market";
        } else {
            return new SubmitOrderResult("failed", 0L);
        }

        String coinType = "1";
        if (CurrencyEnum.Btc == in || CurrencyEnum.Btc == out) {
            coinType = "1";
        } else if (CurrencyEnum.Btc == in || CurrencyEnum.Btc == out) {
            coinType = "2";
        }
        
        HashMap<String, String> postParams = new HashMap<String, String>();
        postParams.put("method", type);
        postParams.put("access_key", getAccessKey());
        postParams.put("coin_type", coinType);
        postParams.put("created", created);
        postParams.put("amount", amount.toString());
        if (price.isPresent()) {
            postParams.put("price", price.get().toString());
        }
        return apiE2EFlow("https://api.huobi.com/apiv2.php",
            postParams, SubmitOrderResult.class);
    }

    @Override
    public boolean cancelOrder(String id, Optional<CurrencyEnum> in,
        Optional<CurrencyEnum> out) {
        String created = String.valueOf(System.currentTimeMillis()/1000);
        HashMap<String, String> postParams = new HashMap<String, String>();
        postParams.put("method", "cancel_order");
        postParams.put("access_key", getAccessKey());
        postParams.put("id", id);
        if (in.get() == CurrencyEnum.Btc || out.get() == CurrencyEnum.Btc) {
            postParams.put("coin_type", "1");
        } else if (in.get() == CurrencyEnum.Ltc || out.get() == CurrencyEnum.Ltc) {
            postParams.put("coin_type", "2");
        }
        postParams.put("created", created);
        return apiE2EFlow("https://api.huobi.com/apiv2.php",
            postParams, Boolean.class);
    }

    @Override
    public boolean queryOrder(String id, Optional<CurrencyEnum> in,
        Optional<CurrencyEnum> out) {
        return false;
    }

    @Override
    public HashMap<String, String> addSignInfo(HashMap<String, String> src) {
        src.put("secret_key", getSecureKey());
        System.out.println(Util.combineToEncryptParams(src));
        String sign = Util.md5(Util.combineToEncryptParams(src)).get().toLowerCase();
        src.put("sign", sign);
        return src;
    }
}
