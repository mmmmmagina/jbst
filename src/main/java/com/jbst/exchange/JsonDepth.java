package com.jbst.exchange;

import java.util.ArrayList;

import com.google.common.base.Optional;

public class JsonDepth {
    private ArrayList<ArrayList<Double>> bids;
    private ArrayList<ArrayList<Double>> asks;

    public ArrayList<ArrayList<Double>> getBids() {
        return bids;
    }

    public void setBids(ArrayList<ArrayList<Double>> bids) {
        this.bids = bids;
    }

    public ArrayList<ArrayList<Double>> getAsks() {
        return asks;
    }

    public void setAsks(ArrayList<ArrayList<Double>> asks) {
        this.asks = asks;
    }

    @Override
    public String toString() {
        return "JsonDepth [bids=" + bids + ", asks=" + asks + "]";
    }
    
    public Depth toDepth() {
        Depth d = new Depth();
        ArrayList<DepthItem> dBids = new ArrayList<DepthItem>();
        ArrayList<DepthItem> dAsks = new ArrayList<DepthItem>();
        for(ArrayList<Double> bid : bids) {
            dBids.add(new DepthItem(bid.get(0), bid.get(0), bid.get(1), Optional.absent(), Optional.absent()));
        }
        for(ArrayList<Double> ask : asks) {
            dAsks.add(new DepthItem(ask.get(0), ask.get(0), ask.get(1), Optional.absent(), Optional.absent()));
        }
        d.setBids(dBids);
        d.setAsks(dAsks);
        return d;
    }
}
