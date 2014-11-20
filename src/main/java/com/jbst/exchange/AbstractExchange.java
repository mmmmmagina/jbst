package com.jbst.exchange;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Optional;
import com.jbst.common.HttpRequest;

public abstract class AbstractExchange implements IExchange {

    private String accessKey;
    private String secureKey;
    private Optional<String> clientId;

    public AbstractExchange(String accessKey, String secureKey,
        Optional<String> clientId) {
        this.accessKey = accessKey;
        this.secureKey = secureKey;
        this.clientId = clientId;
    }

    public AbstractExchange(String accessKey, String secureKey) {
        this.accessKey = accessKey;
        this.secureKey = secureKey;
        this.clientId = null;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecureKey() {
        return secureKey;
    }

    public Depth getDepth(CurrencyEnum inCurrency, CurrencyEnum outCurrency,
        int bidLen, int askLen) {
        String res = HttpRequest.sendGetRequest(
            getDepthUrl(inCurrency, outCurrency, bidLen, askLen), 10000);
        JsonDepth jsonDepth = JSON.parseObject(res, JsonDepth.class);
        return jsonDepth.toDepth();
    }

    public Depth getDepth(CurrencyEnum inCurrency, CurrencyEnum outCurrency) {
        return getDepth(inCurrency, outCurrency, 50, 50);
    }

    public abstract String getDepthUrl(CurrencyEnum inCurrency,
        CurrencyEnum outCurrency, int bidLen, int askLen);

    public abstract HashMap<String, String> addSignInfo(
        HashMap<String, String> src);

    protected <T> T apiE2EFlow(String url, HashMap<String, String> postParams,
        Class<T> clazz) {
        String res;
        postParams = addSignInfo(postParams);
        if (!postParams.isEmpty()) {
            res = HttpRequest.sendPostRequest(url, 10000, postParams);
        } else {
            res = HttpRequest.sendGetRequest(url, 10000);
        }
        return JSON.parseObject(res, clazz);
    }

}
