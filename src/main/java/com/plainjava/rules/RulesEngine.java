package com.plainjava.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RulesEngine {

    List<IRule<InputOrder, CourierDelivery>> rules;

    public RulesEngine() {
        rules = new ArrayList<>();
    }

    public List<CourierDelivery> rule(InputOrder inputOrder) {
        /*return rules.stream()
                .filter(rule -> rule.matches(inputOrder))
                .map(rule -> rule.process(inputOrder))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No Matching rule found"));*/
        return rules.stream()
                .filter(rule -> rule.matches(inputOrder))
                .map(rule -> rule.process(inputOrder))
                .collect(Collectors.toList());
    }

    public RulesEngine registerRule(IRule<InputOrder, CourierDelivery> rule) {
        rules.add(rule);
        return this;
    }
}
