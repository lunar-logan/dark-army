package com.anurag.darkarmy.common.util.script.groovy;

import com.anurag.darkarmy.common.util.script.Expression;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.Map;
import java.util.Objects;

public class GroovyExpression implements Expression {
    private final String script;

    public GroovyExpression(String expression) {
        this.script = Objects.requireNonNull(expression);
    }

    @Override
    public Object eval(Map bindings) {
        return new GroovyShell(new Binding(bindings)).evaluate(script);
    }
}
