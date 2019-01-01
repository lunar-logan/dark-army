package com.anurag.darkarmy.common.util;

import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.common.OutputDef;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public final class AggregationUtil {
    private AggregationUtil() {
    }

    public static Map buildOutput(OutputDef output, Map context) {
        String decisionExpression = output.getOn();
        String key = (String) ParameterUtil.resolve(decisionExpression, context);
        Map<String, Object> outputTemplate = CollectionUtil.getOrEmpty(output.getCases()).get(key);
        if (outputTemplate == null) {
            throw new RuntimeException();
        }
        return ParameterUtil.resolve(outputTemplate, context);
    }

    public static AggregationContext createAggregationContext(String correlationId,
                                                              Map<String, String> header,
                                                              Object body) {
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        AggregationContext context = new AggregationContext(correlationId);
        LinkedHashMap<String, Object> input = new LinkedHashMap<>();
        input.put("header", header);
        input.put("body", body);
        context.putContext("root", input);
        return context;
    }
}
