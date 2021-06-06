package com.plainjava.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        RulesEngine ruleEngine = new RulesEngine();
        ruleEngine.registerRule(new BobbyRule());
        ruleEngine.registerRule(new MartinRule());
        ruleEngine.registerRule(new GeoffRule());
        List<InputOrder> orders = new ArrayList<>();
        InputOrder order1 = new InputOrder(101, "10:45 AM", 3, 'Y');
        InputOrder order2 = new InputOrder(102, "10:45 AM", 3, 'N');
        InputOrder order3 = new InputOrder(103, "10:45 PM", 3, 'N');
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        List<CourierDelivery>  outlist = null;
        Map<Integer, List<CourierDelivery>> matchedPerOrderMap = new HashMap<>();
        for(InputOrder order : orders) {
            outlist = ruleEngine.rule(order);
            matchedPerOrderMap.put(order.getOrderId(), outlist);
        }
        System.out.println("Output courierDelivery: " + CourierUtils.convertAnyObjectToJsonString(matchedPerOrderMap));
        matchedPerOrderMap.forEach((k, v) -> System.out.println("Economical Courier: " + "Order:"+k+"=>" + CourierUtils.economicalCourier(v)));
    }

}
