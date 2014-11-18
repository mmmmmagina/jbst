/**
 * 
 * Author: xiaolu@coinport.com (Xiaolu Wu)
 */

package com.jbst.config;

import com.jbst.exchange.ExchangeEnum;
import com.jbst.exchange.TradePair;
import java.util.ArrayList;
import java.util.HashMap;

public class ExchangeConfig {

  //TODO(xiaolu) add access limit and other configs....
  private ExchangeEnum exType;
  private ArrayList<TradePair> supportedMarket;
  private HashMap<TradePair, Double> minExchangeAmount;
  private HashMap<Currency, Double> withdrawalFee;
  private String accessKey;
  private String secretKey;
  private HashMap<Currency, String> depositAddress;

}
