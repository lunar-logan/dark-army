package com.anurag.darkarmy.common.util.script;

import javax.script.ScriptException;
import java.util.Collections;
import java.util.Map;

public interface Expression {
    default Object eval() throws ScriptException {
        return eval(Collections.emptyMap());
    }

    Object eval(Map bindings) throws ScriptException;
}
