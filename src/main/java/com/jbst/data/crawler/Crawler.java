package com.jbst.data.crawler;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.jbst.data.container.Container;
import com.jbst.exchange.CurrencyEnum;
import com.jbst.exchange.Depth;
import com.jbst.exchange.ExchangeEnum;
import com.jbst.exchange.ExchangeFactory;

public class Crawler extends AbstractScheduledService {

    @Override
    protected void runOneIteration() throws Exception {
        Container container = Container.getInstance();
        System.out.println(container.supportedDepth);
        for (ExchangeEnum ex : container.supportedDepth) {
            Depth d = ExchangeFactory.getExchange(ex).getDepth(
                CurrencyEnum.Btc, CurrencyEnum.Cny);
            System.out.println(d);
            container.updateDepths(ex, d);
        }

    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, 2, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        Crawler c = new Crawler();
        System.out.println("start");
        c.startAsync();
        System.out.println("started");
    }

}
