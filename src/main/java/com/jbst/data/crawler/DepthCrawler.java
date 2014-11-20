package com.jbst.data.crawler;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.jbst.data.container.Container;
import com.jbst.exchange.CurrencyEnum;
import com.jbst.exchange.Depth;
import com.jbst.exchange.ExchangeEnum;
import com.jbst.exchange.ExchangeFactory;

public class DepthCrawler extends AbstractScheduledService {

    private static Logger logger = LoggerFactory.getLogger(DepthCrawler.class);

    @Override
    protected void runOneIteration() throws Exception {
        Container container = Container.getInstance();
        logger.info("================ before update ================");
        logger.info(container.mergedDepth.toString());
        for (ExchangeEnum ex : container.supportedDepth) {
            Depth d = ExchangeFactory.getExchange(ex).getDepth(
                CurrencyEnum.Btc, CurrencyEnum.Cny);
            container.updateDepths(ex, d);
        }
        logger.info("================ after update ================");
        logger.info(container.mergedDepth.toString());
        
        // check the depth
        arbitrage(container.mergedDepth);

    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, 10, TimeUnit.SECONDS);
    }
    
    private void arbitrage(Depth d) {
        if (d.getBids().get(0).getPrice() > d.getAsks().get(0).getPrice()) {
            //submit order to buy and sell
        }
    }

    public static void main(String[] args) {
        DepthCrawler c = new DepthCrawler();
        System.out.println("start");
        c.startAsync();
        System.out.println("started");
    }

}
