package com.anurag.darkarmy.common.util.script.noop;


import com.anurag.darkarmy.common.util.script.Expression;

import javax.script.ScriptException;
import java.util.Map;

public class NOOPExpression implements Expression {
    private final String exp;

    public NOOPExpression(String script) {
        this.exp = script;
    }

    @Override
    public Object eval(Map bindings) throws ScriptException {
        return exp;
    }
}
