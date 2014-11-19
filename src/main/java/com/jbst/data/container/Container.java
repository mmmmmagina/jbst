package com.jbst.data.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;
import com.jbst.exchange.CurrencyEnum;
import com.jbst.exchange.Depth;
import com.jbst.exchange.DepthItem;
import com.jbst.exchange.ExchangeEnum;
import com.jbst.exchange.ExchangeFactory;

public class Container {

    public List<ExchangeEnum> supportedDepth = new ArrayList<>();
    public HashMap<ExchangeEnum, Depth> depths = new HashMap<ExchangeEnum, Depth>();
    public Depth mergedDepth = new Depth();

    public static Container container = new Container();
    
    private Container() {
        System.out.println("fuck here");
        supportedDepth.add(ExchangeEnum.OkcoinCn);
        supportedDepth.add(ExchangeEnum.Huobi);

        System.out.println("fuck heare 2");
        System.out.println(supportedDepth);
        // init depth
        for (ExchangeEnum ex : supportedDepth) {
            System.out.println("fuck heare 3");
            Depth d = ExchangeFactory.getExchange(ex).getDepth(
                CurrencyEnum.Btc, CurrencyEnum.Cny);
            System.out.println("fuck heare 4");
            depths.put(ex, d);
        }
        updateMergedDepth();
    }
    
    public static Container getInstance() {
        return container;
    }
    
    public void updateDepths(ExchangeEnum ex, Depth d) {
        System.out.println("=========== before update depth ===========");
        System.out.println(depths);
        System.out.println(mergedDepth);
        depths.put(ex, d);
        updateMergedDepth();
        System.out.println("=========== after update depth ===========");
        System.out.println(depths);
        System.out.println(mergedDepth);
    }

    private void updateMergedDepth() {
        Set<ExchangeEnum> keys = depths.keySet();
        for (Iterator<ExchangeEnum> iterator = keys.iterator(); iterator
            .hasNext();) {
            ExchangeEnum ex = (ExchangeEnum) iterator.next();
            Depth d = depths.get(ex);
            for (DepthItem di : d.getBids()) {
                // TODO(xiaolu) update raw price according the exchange and
                // withdraw Fee
                DepthItem updated = new DepthItem(di.getPrice(),
                    di.getRawPrice(), di.getQuantity(), Optional.of(ex),
                    Optional.of(System.currentTimeMillis()));
                mergedDepth.getBids().add(updated);
            }
            for (DepthItem di : d.getAsks()) {
                // TODO(xiaolu) update raw price according the exchange and
                // withdraw Fee
                DepthItem updated = new DepthItem(di.getPrice(),
                    di.getRawPrice(), di.getQuantity(), Optional.of(ex),
                    Optional.of(System.currentTimeMillis()));
                mergedDepth.getAsks().add(updated);
            }

            // mergedDepth.getAsks().sort(arg0);
            Comparator<DepthItem> comparator = new Comparator<DepthItem>() {
                @Override
                public int compare(DepthItem d1, DepthItem d2) {
                    if (d1.getPrice() > d2.getPrice())
                        return 1;
                    else if (d1.getPrice() < d2.getPrice())
                        return -1;
                    else
                        return 0;
                }
            };
            Collections.sort(mergedDepth.getBids(), comparator);
            Collections.sort(mergedDepth.getAsks(), comparator);
        }
    }
}
