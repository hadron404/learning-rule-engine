package org.example.business.call;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

public class RuleService {

    public void call(Customer fact) {
        RulesEngineParameters parameters = new RulesEngineParameters()
                .skipOnFirstAppliedRule(true);
        RulesEngine rulesEngine = new DefaultRulesEngine(parameters);
        Rules rules = new Rules();
        rules.register(
                new BlackListRule(),
                new RedListRule(),
                new WithOutOwnerRule(),
                new WithOwnerRule()
        );
        Facts facts = new Facts();
        facts.put("customer", fact);
        rulesEngine.fire(rules, facts);
    }
}
