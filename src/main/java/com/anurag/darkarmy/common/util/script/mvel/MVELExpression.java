package com.anurag.darkarmy.common.util.script.mvel;


import com.anurag.darkarmy.common.util.script.Expression;
import org.mvel2.MVEL;

import java.util.Map;
import java.util.Objects;

public class MVELExpression implements Expression {
    private final String expression;

    public MVELExpression(String expression) {
        this.expression = Objects.requireNonNull(expression);
    }

    @Override
    public Object eval(Map bindings) {
        return MVEL.eval(expression, bindings);
    }
}
