package com.plainjava.rules;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class BobbyRule implements IRule<InputOrder, CourierDelivery> {

    private static final int ruleId = 1;
    private static final String stime = "09:00 AM";
    private static final String etime = "01:00 PM";
    private static final int milesCapability = 5;
    private static char hasRefrigirator = 'Y';
    private static String costPerMile = "$1.75";

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
        out.setRuleApplied(ruleId+" - "+"Bobby-Courier");
        CourierUtils.getDollarAmount(costPerMile);
        BigDecimal totalCost = CourierUtils.getDollarAmount(costPerMile)
                .multiply(new BigDecimal(input.getDestinationDistance()))
                .setScale(2, java.math.RoundingMode.HALF_EVEN);
        out.setTotalCost(totalCost);
        out.setPerMilePrice(costPerMile);
        out.setTotalCostInDollar(NumberFormat.getCurrencyInstance(Locale.US).format(totalCost));
        out.setOrderDeliveryTime(input.getDeliveryTime());
        out.setIsRefrigeratorRequired('Y');
        return out;
}
}
