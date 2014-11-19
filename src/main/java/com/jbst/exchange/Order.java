package com.jbst.exchange;

import com.google.common.base.Optional;

public class Order {
    
    private String id;
    private OperationEnum op;
    private Optional<Double> price;
    private double amount;
    private double dealedAmount;
    private OrderStatus status;
}
