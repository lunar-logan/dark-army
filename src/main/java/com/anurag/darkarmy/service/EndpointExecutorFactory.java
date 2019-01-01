package com.anurag.darkarmy.service;

import com.anurag.darkarmy.core.domain.EndpointDef;

import java.util.Optional;

public interface EndpointExecutorFactory {
    Optional<EndpointExecutorService> getInstance(EndpointDef endpoint);
}
