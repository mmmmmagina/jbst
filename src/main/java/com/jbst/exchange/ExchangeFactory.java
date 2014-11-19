package com.jbst.exchange;

import com.google.common.base.Optional;

//TODO(xiaolu) 改成单例模式工厂
public class ExchangeFactory {
    
    public static AbstractExchange getExchange(ExchangeEnum ex) {
        switch (ex) {
        case OkcoinCn:
            return new OkcoinCnExchange("", "", Optional.of(""));
        case Huobi:
            return new HuobiExchange("", "", Optional.of(""));
        default:
            return new OkcoinCnExchange("", "", Optional.of(""));
        }
    }

}
