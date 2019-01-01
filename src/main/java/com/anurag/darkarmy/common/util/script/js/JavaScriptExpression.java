package com.anurag.darkarmy.common.util.script.js;


import com.anurag.darkarmy.common.util.script.Expression;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.util.Map;
import java.util.Objects;

public class JavaScriptExpression implements Expression {
    private final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("nashorn");
    private final String script;

    public JavaScriptExpression(String script) {
        this.script = Objects.requireNonNull(script);
    }

    @Override
    public Object eval(Map bindings) throws ScriptException {
        return ENGINE.eval(script, new SimpleBindings(bindings));
    }
}
