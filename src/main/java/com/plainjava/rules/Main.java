package com.plainjava.rules;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        RulesEngine ruleEngine = new RulesEngine();
        ruleEngine.registerRule(new BobbyRule());
        ruleEngine.registerRule(new MartinRule());
        ruleEngine.registerRule(new GeoffRule());
        List<InputOrder> orders = new ArrayList<>();
        InputOrder order1 = new InputOrder(101, "10:45 AM", 3, 'Y');
        InputOrder order2 = new InputOrder(102, "10:45 AM", 3, 'Y');
        orders.add(order1);
        orders.add(order2);
        List<CourierDelivery> matchedoutput = new ArrayList<>();
        CourierDelivery courierDelivery = null;
        for(InputOrder order : orders) {
            matchedoutput = ruleEngine.rule(order);
            //matchedoutput.add(courierDelivery);
        }
        System.out.println("Output courierDelivery: " + CourierUtils.convertAnyObjectToJsonString(matchedoutput));
        System.out.println("Economical Courier: " + CourierUtils.economicalCourier(matchedoutput));
    }

}
