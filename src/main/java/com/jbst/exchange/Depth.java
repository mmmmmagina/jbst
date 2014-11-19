package com.jbst.exchange;

import java.util.ArrayList;

public class Depth {
    private ArrayList<DepthItem> bids;
    private ArrayList<DepthItem> asks;
    public ArrayList<DepthItem> getBids() {
        return bids;
    }
    public void setBids(ArrayList<DepthItem> bids) {
        this.bids = bids;
    }
    public ArrayList<DepthItem> getAsks() {
        return asks;
    }
    public void setAsks(ArrayList<DepthItem> asks) {
        this.asks = asks;
    }
    @Override
    public String toString() {
        return "Depth [bids=" + bids + ", asks=" + asks + "]";
    }
}
