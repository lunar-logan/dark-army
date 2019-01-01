package com.anurag.darkarmy.controller;

import com.anurag.darkarmy.common.exception.SystemException;
import com.anurag.darkarmy.common.util.AggregationUtil;
import com.anurag.darkarmy.common.util.CollectionUtil;
import com.anurag.darkarmy.core.AggregationContext;
import com.anurag.darkarmy.core.response.AggregationResponse;
import com.anurag.darkarmy.core.response.OperationResponse;
import com.anurag.darkarmy.service.AggregationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(WebServiceController.BASE_PATH)
public class WebServiceController {
    static final String BASE_PATH = "/service/befe";
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final AggregationService service;

    public WebServiceController(AggregationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<OperationResponse> aggregate(HttpServletRequest request,
                                                       @RequestHeader HttpHeaders headers,
                                                       @RequestBody(required = false) Map body) {
        AggregationContext context = AggregationUtil.createAggregationContext(headers.getFirst("Correlation-ID"),
                headers.toSingleValueMap(),
                body);

        try {
            AggregationResponse response = service.aggregate(request.getMethod(),
                    URI.create(BASE_PATH).relativize(URI.create(request.getRequestURI())).toASCIIString(),
                    context);

            return ResponseEntity.status(response.getStatus())
                    .headers(new HttpHeaders(new LinkedMultiValueMap<>(CollectionUtil.asMultiValueMap(response.getHeader()))))
                    .body(new OperationResponse(response.getBody()));
        } catch (SystemException ex) {
            log.info("Exception during aggregation", ex);
            return ResponseEntity.status(ex.getStatus()).body(new OperationResponse(ex.getResource()));
        }
    }
}
