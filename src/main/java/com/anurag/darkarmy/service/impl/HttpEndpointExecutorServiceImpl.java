package com.anurag.darkarmy.service.impl;

import com.anurag.darkarmy.common.exception.SystemException;
import com.anurag.darkarmy.common.util.HttpUtil;
import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.domain.EndpointDef;
import com.anurag.darkarmy.core.domain.common.InputDef;
import com.anurag.darkarmy.service.EndpointExecutorService;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Service
public class HttpEndpointExecutorServiceImpl implements EndpointExecutorService {

    @Override
    public Map execute(EndpointDef endpoint, AggregationContext context) {
        URI uri = HttpUtil.buildURI(endpoint, context);

        Optional<HttpRequest> maybeRequest = getHttpRequest(uri, endpoint.getInput());
        if (!maybeRequest.isPresent()) {
            throw new SystemException();
        }

        HttpRequest request = maybeRequest.get();
        if (request instanceof HttpRequestWithBody) {
            ((HttpRequestWithBody) request).body(HttpUtil.buildBody(endpoint, context));
        }

        try {
            return HttpUtil.buildOutput(request.headers(HttpUtil.buildHeader(endpoint, context)).asString());
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    private Optional<HttpRequest> getHttpRequest(URI uri, InputDef input) {
        switch (input.getMethod()) {
            case GET:
                return Optional.of(Unirest.get(uri.toASCIIString()));
            case PUT:
                return Optional.of(Unirest.put(uri.toASCIIString()));
            case POST:
                return Optional.of(Unirest.post(uri.toASCIIString()));
            case DELETE:
                return Optional.of(Unirest.delete(uri.toASCIIString()));
        }
        return Optional.empty();
    }

    @Override
    public String getType() {
        return "http";
    }


    public static void main(String[] args) {
        HttpEndpointExecutorServiceImpl impl = new HttpEndpointExecutorServiceImpl();

        EndpointDef ep = new EndpointDef();
        ep.setUri("https://google.com");

        InputDef input = new InputDef();
        input.setMethod(InputDef.Method.GET);
        ep.setInput(input);

        Map execute = impl.execute(ep, new AggregationContext("1243"));
        System.out.println(execute);
    }

//    public static void main(String[] args) throws Exception {
//        HttpEndpointExecutorServiceImpl impl = new HttpEndpointExecutorServiceImpl(WebClient.builder().build());
//        EndpointDef def = new EndpointDef();
//        def.setId("1");
//
//        InputDef input = new InputDef();
//        input.setMethod(InputDef.Method.GET);
//        def.setInput(input);
//        def.setUri("https://reqres.in/api/users?page=2");
//
//        OutputDef output = new OutputDef();
//        output.setOn("200");
//        output.setCases(new MapBuilder<>(new HashMap<String, Map<String, Object>>())
//                .put("200", new MapBuilder<>(new HashMap<String, Object>())
//                        .put("status", "<mvel status %>")
//                        .put("properties", "<mvel header %>")
//                        .put("payload", "<groovy     String.join(\".\", Arrays.asList(body.data[0].last_name.toUpperCase().split(\"\")))  %>")
//                        .build())
//                .build());
////        def.setOutput(output);
//
//        AggregationContext ac = new AggregationContext("coid123");
//        impl.executeWorkflow(def, ac)
//                .log()
//                .subscribe(res -> {
//                    System.out.println(res);
//                });
////
//        Thread.sleep(4000); }

}
