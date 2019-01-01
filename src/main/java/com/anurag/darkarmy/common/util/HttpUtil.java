package com.anurag.darkarmy.common.util;

import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.EndpointDef;
import com.anurag.darkarmy.core.domain.common.InputDef;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

public final class HttpUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private HttpUtil() {
    }

    public static URI buildURI(EndpointDef endpoint, AggregationContext context) {
        return UriComponentsBuilder.fromUriString(endpoint.getUri())
                .build(resolveUriVariables(endpoint.getInput(), context));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> buildHeader(EndpointDef endpoint, AggregationContext context) {
        InputDef input = endpoint.getInput();
        if (input != null) {
            Map<String, String> header = input.getHeader();
            if (header != null) {
                return ParameterUtil.resolve(header, context.getContext());
            }
        }
        return Collections.emptyMap();
    }

    public static Optional<Map> buildBody(EndpointDef endpoint, AggregationContext context) {
        InputDef input = endpoint.getInput();
        if (input != null) {
            return Optional.ofNullable(ParameterUtil.resolve(input.getBody(), context.getContext()));
        }
        return Optional.empty();
    }

    private static Map<String, ?> resolveUriVariables(InputDef input, AggregationContext context) {
        Map<String, Object> uriVariables = new HashMap<>();
        MultiValueMap<String, String> queryParams = resolveQueryParams(input.getQuery(), context);
        if (queryParams != null) {
            queryParams.forEach(uriVariables::put);
        }

        Map<String, String> path = null;
        if ((path = input.getPath()) != null) {
            uriVariables.putAll(ParameterUtil.resolve(path, context.getContext()));
        }

        return uriVariables;
    }

    private static MultiValueMap<String, String> resolveQueryParams(Map<String, List<String>> queryParams, AggregationContext context) {
        LinkedMultiValueMap<String, String> query = new LinkedMultiValueMap<>();
        if (queryParams != null) {
            queryParams.forEach((name, values) -> query.put(name, ParameterUtil.resolve(values, context.getContext())));
        }
        return query;
    }

    public static Map buildOutput(HttpResponse<String> res) throws Exception {
        Map<String, Object> out = new HashMap<>();
        out.put("status", res.getStatus());
        out.put("header", CollectionUtil.asSingleValueMap(res.getHeaders()));
        out.put("body", getBody(res));
        return out;
    }

    private static Object getBody(HttpResponse<String> res) throws Exception {
        String contentType = res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        if (contentType != null) {
            switch (contentType.toLowerCase()) {
                case "application/json":
                    return MAPPER.readValue(res.getBody(), Map.class);
            }
        }
        return res.getBody();
    }
}
