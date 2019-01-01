package com.anurag.darkarmy.common.util;

import com.anurag.darkarmy.common.util.script.ExpressionFactory;
import org.mvel2.templates.TemplateRuntime;

import javax.script.ScriptException;
import java.util.*;

public final class ParameterUtil {
    private static final ExpressionFactory expressionFactory = ExpressionFactory.getInstance();

    private ParameterUtil() {
        throw new UnsupportedOperationException();
    }


    public static Optional<String> resolveTemplate(String template, Map<String, ?> bindings) {
        return Optional.ofNullable(template).map(tmpl -> (String) TemplateRuntime.eval(tmpl, bindings));
    }

    public static Object resolve(String template, Map<String, ?> context) {
        if (template == null) {
            return null;
        }
        try {
            return expressionFactory.getInstance(template).eval(context);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map resolve(Map template, Map<String, ?> context) {
        Map result = new LinkedHashMap();
        if (template != null) {
            for (Object o : template.entrySet()) {
                Map.Entry e = (Map.Entry) o;
                if (e.getValue() instanceof String) {
                    result.put(e.getKey(), resolve((String) e.getValue(), context));
                } else if (e.getValue() instanceof Map) {
                    result.put(e.getKey(), resolve((Map) e.getValue(), context));
                } else if (e.getValue() instanceof Collection) {
                    result.put(e.getKey(), resolve((Collection) e.getValue(), context));
                } else {
                    result.put(e.getKey(), e.getValue());
                }
            }
        }
        return result;
    }

    public static List resolve(Collection template, Map<String, ?> context) {
        List list = new ArrayList();
        if (template != null) {
            for (Object o : template) {
                if (o instanceof String) {
                    list.add(resolve((String) o, context));
                } else if (o instanceof Map) {
                    list.add(resolve((Map) o, context));
                } else if (o instanceof Collection) {
                    list.add(resolve((Collection) o, context));
                } else {
                    list.add(o);
                }
            }
        }
        return list;
    }
}
