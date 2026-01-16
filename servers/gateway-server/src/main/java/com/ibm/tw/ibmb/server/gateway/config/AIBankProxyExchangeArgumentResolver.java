package com.ibm.tw.ibmb.server.gateway.config;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.cloud.gateway.webflux.config.ProxyExchangeArgumentResolver;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.ibm.tw.ibmb.server.gateway.util.HeaderParamUtils;

import io.netty.channel.ChannelOption;
import io.netty.handler.codec.http.HttpHeaderValidationUtil;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public class AIBankProxyExchangeArgumentResolver extends ProxyExchangeArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(AIBankProxyExchangeArgumentResolver.class);

    private static final String REQ_TIMEOUT_HEADER = "x-request-timeout";

    private WebClient rest;

    private HttpHeaders headers;

    private Set<String> autoForwardedHeaders;

    private Set<String> sensitive;

    private long timeoutPreserved = 5000;

    private long defaultTimeout = 65000;

    private int connectTimeout = 10000;

    public AIBankProxyExchangeArgumentResolver(WebClient builder, long timeoutPreserved, long defaultTimeout, int connectTimeout) {
        super(builder);
        this.rest = builder;
        this.timeoutPreserved = timeoutPreserved;
        this.defaultTimeout = defaultTimeout;
        this.connectTimeout = connectTimeout;
    }

    @Override
    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    @Override
    public void setAutoForwardedHeaders(Set<String> autoForwardedHeaders) {
        this.autoForwardedHeaders = autoForwardedHeaders;
    }

    @Override
    public void setSensitive(Set<String> sensitive) {
        this.sensitive = sensitive;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ProxyExchange.class.isAssignableFrom(parameter.getParameterType());
    }

    private Type type(MethodParameter parameter) {
        Type type = parameter.getGenericParameterType();
        if (type instanceof ParameterizedType) {
            ParameterizedType param = (ParameterizedType) type;
            type = param.getActualTypeArguments()[0];
        }
        if (type instanceof TypeVariable || type instanceof WildcardType) {
            type = Object.class;
        }
        return type;
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        WebClient client = rest;
        List<String> timeouts = exchange.getRequest().getHeaders().get(REQ_TIMEOUT_HEADER);
        String timeout = "";
        if (timeouts != null && !timeouts.isEmpty()) {
            timeout = timeouts.get(0);
        }
        try {
            long timeoutMillis = (timeout == null || timeout.length() == 0) ? defaultTimeout : Long.parseLong(timeout);
            long reducedTimeout = timeoutMillis - timeoutPreserved;
            client = mutateWebClientWithTimeout(connectTimeout, (reducedTimeout > 0 ? reducedTimeout : timeoutPreserved));
        }
        catch (NumberFormatException e) {
            logger.info("invalid timeout value in header, ignored", e);
        }
        ProxyExchange<?> proxy = new ProxyExchange<>(client, exchange, bindingContext, type(parameter));
        proxy.headers(headers);
        if (this.autoForwardedHeaders.size() > 0) {
            proxy.headers(extractAutoForwardedHeaders(exchange));
        }
        if (sensitive != null) {
            proxy.sensitive(sensitive.toArray(new String[0]));
        }
        return Mono.just(proxy);
    }

    public WebClient mutateWebClientWithTimeout(int connectTimeout, long readTimeout) {
        // @formatter:off
        HttpClient tcpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
                .doOnConnected(connection ->
                        connection.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS)));
        if (logger.isDebugEnabled()) {
            logger.debug("config read timeout: {} ms", readTimeout);
        }
        return this.rest
                .mutate()
                .clientConnector(new ReactorClientHttpConnector(tcpClient))
                .build();
        // @formatter:on
    }

    private HttpHeaders extractAutoForwardedHeaders(ServerWebExchange exchange) {
        HttpHeaders headers = new HttpHeaders();
        exchange.getRequest().getHeaders().forEach((header, values) -> {
            if (this.autoForwardedHeaders.contains(header)) {

                List<String> validValues = new ArrayList<>();
                for (String s : values) {
                    // fortify Header Manipulation - 驗證 header 是否含有非法字符
                    if (HttpHeaderValidationUtil.validateValidHeaderValue(s) == -1) {
                        s = HeaderParamUtils.validParam(s);
                        s = Encode.forHtml(s);
                        validValues.add(s);
                    }
                }
                // fortify Header Manipulation - 驗證 header 是否含有非法字符
                if (HttpHeaderValidationUtil.validateValidHeaderValue(header) == -1) {
                    header = HeaderParamUtils.validParam(header);
                    header = Encode.forHtml(header);
                    headers.addAll(header, validValues);
                }
            }
        });

        return headers;
    }

}
