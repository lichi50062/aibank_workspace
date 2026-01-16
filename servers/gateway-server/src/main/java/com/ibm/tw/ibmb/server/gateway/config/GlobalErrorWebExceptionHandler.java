package com.ibm.tw.ibmb.server.gateway.config;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.netty.handler.timeout.TimeoutException;
import reactor.core.publisher.Mono;

public class GlobalErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {

    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, Resources resources, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resources, errorProperties, applicationContext);
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {

        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    public Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Throwable throwable = getError(request);

        Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        if (throwable.getCause() instanceof TimeoutException || throwable.getCause() instanceof SocketTimeoutException) {
            errorPropertiesMap.put("status", HttpStatus.GATEWAY_TIMEOUT.value());
            errorPropertiesMap.put("error", HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase());
        }
        // keep status and error only
        Map<String, Object> simplifiedMap = new HashMap<>();
        Integer errorStatus = (Integer) errorPropertiesMap.getOrDefault("status", HttpStatus.BAD_REQUEST.value());
        if (errorStatus.intValue() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            simplifiedMap.put("status", HttpStatus.BAD_REQUEST.value());
            simplifiedMap.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());     
        }
        else {
            simplifiedMap.put("status", errorStatus);
            simplifiedMap.put("error", errorPropertiesMap.getOrDefault("error", HttpStatus.BAD_REQUEST.getReasonPhrase()));            
        }
        int httpStatus = getHttpStatus(errorPropertiesMap);
        int respHttpStatus = httpStatus == HttpStatus.INTERNAL_SERVER_ERROR.value() ? HttpStatus.BAD_REQUEST.value() : httpStatus;
        return ServerResponse.status(respHttpStatus ).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(simplifiedMap));
    }
}
