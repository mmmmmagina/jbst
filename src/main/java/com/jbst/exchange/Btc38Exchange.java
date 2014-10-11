package com.jbst.exchange;

import com.google.common.base.Optional;

public class Btc38Exchange extends AbstractExchange {

	public Btc38Exchange(String accessKey, String secureKey, Optional<String> clientId) {
		super(accessKey, secureKey, clientId);
	}

	@Override
	public String getDepthUrl(Currency inCurrency, Currency outCurrency,
		int bidLen, int askLen) {
		return new StringBuilder()
		  .append("http://api.btc38.com/v1/depth.php?c=")
		  .append(inCurrency.toString().toLowerCase())
		  .append("&mk_type=")
		  .append(outCurrency.toString().toLowerCase()).toString();
	}
	
	public static void main(String[] args) {
		Btc38Exchange e = new Btc38Exchange("", "", Optional.of(""));
		System.out.println(e.getDepth(Currency.Doge, Currency.Cny));
	}
}
