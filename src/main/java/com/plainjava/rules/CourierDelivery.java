package com.plainjava.rules;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Data
public class CourierDelivery {
    private int orderId;
    public String ruleApplied = StringUtils.EMPTY;
    public String perMilePrice;
    public BigDecimal totalCost;
    public String totalCostInDollar;
    public String orderDeliveryTime;
    public char isRefrigeratorRequired;
}
