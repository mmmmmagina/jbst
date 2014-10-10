package com.jbst.exchange;

import com.jbst.com.HttpRequest;
import com.google.gson.Gson;

public abstract class AbstractExchange {

	private String accessKey;
	private String secureKey;
	private String clientId;

	public String getAccessKey() {
		return accessKey;
	}

	public String getSecureKey() {
		return secureKey;
	}

        public Depth getDepth(Currency inCurrency, Currency outCurrency, int bidLen, int askLen) {
            String depthUrl = getDepthUrl(inCurrency, outCurrency, bidLen, askLen); 
            String res = HttpRequest.sendRequest(depthUrl, "", "GET");


        }

        public abstract String getDepthUrl(Currency inCurrency, Currency outCurrency, int bidLen, int askLen);
        
}
