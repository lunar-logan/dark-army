package com.anurag.darkarmy.service;


import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.EndpointDef;

import java.util.Map;

public interface EndpointExecutorService {
    Map execute(EndpointDef endpoint, AggregationContext context);

    String getType();
}
