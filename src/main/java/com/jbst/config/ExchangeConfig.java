/**
 * 
 * Author: xiaolu@coinport.com (Xiaolu Wu)
 */

package com.jbst.config;

import java.util.ArrayList;
import java.util.HashMap;

import com.jbst.exchange.CurrencyEnum;
import com.jbst.exchange.ExchangeEnum;
import com.jbst.exchange.TradePair;

public class ExchangeConfig {

  //TODO(xiaolu) add access limit and other configs....
  private ExchangeEnum exType;
  private ArrayList<TradePair> supportedMarket;
  private HashMap<TradePair, Double> minExchangeAmount;
  private HashMap<CurrencyEnum, Double> withdrawalFee;
  private String accessKey;
  private String secretKey;
  private HashMap<CurrencyEnum, String> depositAddress;

}
