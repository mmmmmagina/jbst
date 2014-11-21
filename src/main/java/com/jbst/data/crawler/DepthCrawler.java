package com.jbst.data.crawler;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.jbst.data.container.Container;
import com.jbst.exchange.CurrencyEnum;
import com.jbst.exchange.Depth;
import com.jbst.exchange.DepthItem;
import com.jbst.exchange.ExchangeEnum;
import com.jbst.exchange.ExchangeFactory;

public class DepthCrawler extends AbstractScheduledService {

    private static Logger logger = LoggerFactory.getLogger(DepthCrawler.class);

    @Override
    protected void runOneIteration() throws Exception {
        Container container = Container.getInstance();
//        logger.info("================ before update ================");
//        logger.info(container.mergedDepth.toString());
        for (ExchangeEnum ex : container.supportedDepth) {
            Depth d = ExchangeFactory.getExchange(ex).getDepth(
                CurrencyEnum.Btc, CurrencyEnum.Cny);
            container.updateDepths(ex, d);
        }
//        logger.info("================ after update ================");
//        logger.info(container.mergedDepth.toString());
        
        // check the depth
        arbitrage(container.mergedDepth);

    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, 10, TimeUnit.SECONDS);
    }
    
    private void arbitrage(Depth d) {
        
        for (int i = 0; i < 5; i++) {
            System.out.println(
                d.getBids().get(i).getExchange().get() + " = " + d.getBids().get(i).getPrice()
                + " | "
                + d.getAsks().get(i).getExchange().get() + d.getAsks().get(i).getPrice());
        }
        
        if (d.getBids().get(0).getPrice() > d.getAsks().get(0).getPrice()) {
            System.out.println("could get money!");
            // buy
            DepthItem buyItem = d.getAsks().get(0);
            DepthItem sellItem = d.getBids().get(0);
            System.out.println(buyItem);
            System.out.println(sellItem);
//            Double amount = Math.min(buyItem.getQuantity(), sellItem.getQuantity());
//            ExchangeFactory.getExchange(buyItem.getExchange().get())
//              .submitOrder(CurrencyEnum.Btc, CurrencyEnum.Cny, Optional.of(buyItem.getPrice()),amount);
//            // sell
//            ExchangeFactory.getExchange(buyItem.getExchange().get())
//            .submitOrder(CurrencyEnum.Cny, CurrencyEnum.Btc, Optional.of(sellItem.getPrice()),amount);
        }
    }

    public static void main(String[] args) {
        DepthCrawler c = new DepthCrawler();
        System.out.println("start");
        c.startAsync();
        System.out.println("started");
    }

}
