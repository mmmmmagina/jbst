package com.jbst.services;

import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.jbst.exchange.AbstractExchange;
import com.jbst.exchange.CurrencyEnum;
import com.jbst.exchange.Depth;
import com.jbst.exchange.DepthItem;
import com.jbst.exchange.ExchangeEnum;
import com.jbst.exchange.HuobiExchange;
import com.jbst.exchange.OkcoinCnExchange;

public class ArbitrageService extends AbstractScheduledService {
    
    private EnumSet<ExchangeEnum> currencies = EnumSet.of(ExchangeEnum.OkcoinCn, ExchangeEnum.Huobi);

    @Override
    protected void runOneIteration() throws Exception {
        
        AbstractExchange huobi = new HuobiExchange("", "", Optional.of(""));
        AbstractExchange okcoinCn = new OkcoinCnExchange("", "", Optional.of(""));
        
        Depth huobiDepth = huobi.getDepth(CurrencyEnum.Btc, CurrencyEnum.Cny);
        Depth okcoinDepth = okcoinCn.getDepth(CurrencyEnum.Btc, CurrencyEnum.Cny);
        
        List<DepthItem> huobiBids = huobiDepth.getBids();
        List<DepthItem> okcoinBids = huobiDepth.getBids();
        
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, 30, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        ArbitrageService s = new ArbitrageService();
//        s.startAsync();
        System.out.println(s.currencies);
    }
}
