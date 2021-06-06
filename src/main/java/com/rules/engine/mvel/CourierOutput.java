package com.rules.engine.mvel;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Data
public class CourierOutput {
    private int orderId;
    public String ruleApplied = StringUtils.EMPTY;
    public String perMilePrice;
    public BigDecimal totalCost;
    public String orderDeliveryTime;
    public char isRefrigeratorRequired;
}
