package com.anurag.darkarmy.common.util.script;

public interface ExpressionFactory {
    Expression getInstance(String expression);

    static ExpressionFactory getInstance() {
        return SimpleExpressionFactory.getInstance();
    }
}
