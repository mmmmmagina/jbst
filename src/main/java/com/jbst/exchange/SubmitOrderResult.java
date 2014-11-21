package com.jbst.exchange;

public class SubmitOrderResult {
    
    public String isSuccess;
    public long id;

    public SubmitOrderResult() {
        
    }
    
    public SubmitOrderResult(String isSuccess, long id) {
        this.isSuccess = isSuccess;
        this.id = id;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
