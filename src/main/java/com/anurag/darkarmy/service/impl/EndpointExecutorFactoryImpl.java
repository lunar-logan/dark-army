package com.anurag.darkarmy.service.impl;

import com.anurag.darkarmy.core.domain.EndpointDef;
import com.anurag.darkarmy.service.EndpointExecutorFactory;
import com.anurag.darkarmy.service.EndpointExecutorService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.text.html.Option;
import java.util.*;

@Service
public class EndpointExecutorFactoryImpl implements EndpointExecutorFactory {

    private final List<EndpointExecutorService> executorServices;
    private Map<String, EndpointExecutorService> serviceMap;

    public EndpointExecutorFactoryImpl(List<EndpointExecutorService> executorServices) {
        this.executorServices = executorServices;
    }

    @PostConstruct
    public void init() {
        Map<String, EndpointExecutorService> m = new HashMap<>();
        executorServices.forEach(service -> m.put(String.valueOf(service.getType()).trim().toLowerCase(), service));
        this.serviceMap = Collections.unmodifiableMap(m);
    }

    @Override
    public Optional<EndpointExecutorService> getInstance(EndpointDef endpoint) {
        return Optional.ofNullable(serviceMap.get(String.valueOf(endpoint.getType()).toLowerCase()));
    }
}
