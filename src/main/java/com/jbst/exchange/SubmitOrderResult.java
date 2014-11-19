package com.jbst.exchange;

public class SubmitOrderResult {
    
    public String isSuccess;
    public long id;

    public SubmitOrderResult(String isSuccess, long id) {
        this.isSuccess = isSuccess;
        this.id = id;
    }
}
