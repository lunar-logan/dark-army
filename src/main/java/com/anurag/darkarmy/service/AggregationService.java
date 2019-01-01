package com.anurag.darkarmy.service;

import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.response.AggregationResponse;

public interface AggregationService {
    AggregationResponse aggregate(String method,
                                  String path,
                                  AggregationContext context);
}