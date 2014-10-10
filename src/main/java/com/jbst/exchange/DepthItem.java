package com.jbst.exchange;

import com.google.common.base.Optional;

public class DepthItem {
	
	private double price;
	private double rawPrice;
	private double quantity;
	private Optional<Exchange> exchange;
	private Optional<Long> expire;
	
	public DepthItem(double price, double rawPrice, double quantity, Optional<Exchange> exchange, Optional<Long> expire) {
		this.price = price;
		this.rawPrice = rawPrice;
		this.quantity = quantity;
		this.exchange = exchange;
		this.expire = expire;
	}

	public DepthItem(double price, double rawPrice, double quantity) {
		new DepthItem(price, rawPrice, quantity, null, null);
	}
	
}
