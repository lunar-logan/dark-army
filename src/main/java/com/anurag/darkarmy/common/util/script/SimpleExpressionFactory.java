package com.anurag.darkarmy.common.util.script;


import com.anurag.darkarmy.common.util.script.groovy.GroovyExpression;
import com.anurag.darkarmy.common.util.script.js.JavaScriptExpression;
import com.anurag.darkarmy.common.util.script.mvel.MVELExpression;
import com.anurag.darkarmy.common.util.script.noop.NOOPExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleExpressionFactory implements ExpressionFactory {
    private static SimpleExpressionFactory instance;

    public static ExpressionFactory getInstance() {
        if (instance == null) {
            synchronized (SimpleExpressionFactory.class) {
                if (instance == null) {
                    instance = new SimpleExpressionFactory();
                }
            }
        }
        return instance;
    }

    private static final Pattern LANG_PATTERN = Pattern.compile("<(mvel|groovy|js) +(.*) +%>", Pattern.CASE_INSENSITIVE);

    @Override
    public Expression getInstance(String expression) {
        Matcher matcher = LANG_PATTERN.matcher(expression);
        if (matcher.matches()) {
            String scriptType = matcher.group(1);
            String script = matcher.group(2);
            switch (scriptType.toLowerCase()) {
                case "mvel":
                    return new MVELExpression(script);
                case "groovy":
                    return new GroovyExpression(script);
                case "js":
                    return new JavaScriptExpression(script);
            }
        }

        return new NOOPExpression(expression);
    }
}
