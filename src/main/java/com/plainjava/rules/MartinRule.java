package com.plainjava.rules;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MartinRule implements IRule<InputOrder, CourierDelivery> {

    private static final int ruleId = 2;
    private static final String stime = "09:00 AM";
    private static final String etime = "05:00 PM";
    private static final int milesCapability = 3;
    private static char hasRefrigirator = 'N';
    private static String costPerMile = "$1.50";

    @Override
    public boolean matches(InputOrder input) {
        long deliveryTime = CourierUtils.getTimeInMillis(input.getDeliveryTime());
        boolean isAvailable = CourierUtils.getWorkingHoursRange(stime, etime).contains(deliveryTime);
        boolean distanceCapability = input.getDestinationDistance() <= milesCapability;
        boolean hasRefrigirator = this.hasRefrigirator == input.getRefrigeratorRequired();
        return isAvailable && distanceCapability && hasRefrigirator;
    }

    @Override
    public CourierDelivery process(InputOrder input) {
        CourierDelivery out = new CourierDelivery();
        out.setOrderId(input.getOrderId());
        out.setRuleApplied(ruleId+" - "+"Martin-Courier");
        CourierUtils.getDollarAmount(costPerMile);
        BigDecimal totalCost = CourierUtils.getDollarAmount(costPerMile)
                .multiply(new BigDecimal(input.getDestinationDistance()))
                .setScale(2, java.math.RoundingMode.HALF_EVEN);
        out.setTotalCost(totalCost);
        out.setPerMilePrice(costPerMile);
        out.setTotalCostInDollar(NumberFormat.getCurrencyInstance(Locale.US).format(totalCost));
        out.setOrderDeliveryTime(input.getDeliveryTime());
        out.setIsRefrigeratorRequired(input.getRefrigeratorRequired());
        return out;
    }
}
