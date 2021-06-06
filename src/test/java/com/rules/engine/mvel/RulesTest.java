package com.rules.engine.mvel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RulesTest {

    @Test
    public void fireRule() throws Exception {
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        URL resource = RulesTest.class.getClassLoader().getResource("mvel-courier-rules.yml");
        Rules rules = ruleFactory.createRules(new FileReader(new File(resource.toURI())));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        FactInput input = new FactInput(101, "10:30 AM", 3, 'Y');
        Facts facts = new Facts();
        facts.put("input", input);
        rulesEngine.fire(rules, facts);
        System.out.println(convertAnyObjectToJsonString(input));
    }

    @Test
    public void fireRuleWeather() throws Exception {
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        URL resource = RulesTest.class.getClassLoader().getResource("weather-rule.yml");
        Rules rules = ruleFactory.createRules(new FileReader(new File(resource.toURI())));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Facts facts = new Facts();
        facts.put("rain", true);
        rulesEngine.fire(rules, facts);
    }

    private String convertAnyObjectToJsonString(Object obj) {
        String json = "";
        try {
            json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
