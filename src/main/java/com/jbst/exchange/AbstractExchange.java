package com.jbst.exchange;


import com.google.common.base.Optional;
import com.jbst.common.HttpRequest;
import com.jbst.common.Util;

public abstract class AbstractExchange {

	private String accessKey;
	private String secureKey;
	private Optional<String> clientId;
	
	public AbstractExchange(String accessKey, String secureKey, Optional<String> clientId) {
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

        public Depth getDepth(Currency inCurrency, Currency outCurrency, int bidLen, int askLen) {
            String res = HttpRequest.sendGetRequest(
            	getDepthUrl(inCurrency, outCurrency, bidLen, askLen), 10000);

            System.out.println(res);
            return Util.gson.fromJson(res, Depth.class);
        }
        
        public Depth getDepth(Currency inCurrency, Currency outCurrency) {
        	return getDepth(inCurrency, outCurrency, 50, 50);
        }

        public abstract String getDepthUrl(Currency inCurrency, Currency outCurrency, int bidLen, int askLen);
        
}
