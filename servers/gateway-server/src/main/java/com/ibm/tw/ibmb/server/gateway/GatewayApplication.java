package com.ibm.tw.ibmb.server.gateway;

import java.util.Optional;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.cloud.gateway.webflux.config.ProxyExchangeArgumentResolver;
import org.springframework.cloud.gateway.webflux.config.ProxyProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.view.ViewResolver;

import com.ibm.tw.ibmb.server.gateway.config.AIBankProxyExchangeArgumentResolver;
import com.ibm.tw.ibmb.server.gateway.config.GlobalErrorWebExceptionHandler;
import com.ibm.tw.ibmb.server.gateway.config.RouteMappingConfig;

import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class GatewayApplication {

    public static final String[] GW_DEFAULT_SENSITIVE = { "cookie", "host" };

    @Autowired
    private RouteMappingConfig mappingConfig;

    @Value("${aibank.gateway.timeoutPreserved:5000}")
    private long timeoutPreserved = 5000;

    @Value("${aibank.gateway.defaultTimeout:70000}")
    private long defaultTimeout = 70000;

    @Value("${aibank.gateway.connectTimeout:10000}")
    private int connectTimeout;

    /**
     * Run in jar
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @GetMapping(path = "/{serviceId}/**")
    public Mono<ResponseEntity<byte[]>> proxyGet(ProxyExchange<byte[]> proxy, @PathVariable("serviceId") String serviceId, ServerHttpRequest request) {
        String targetUri = buildTargetUri(proxy, serviceId, request);
        // proxy.header("Connection", "close");
        return proxy.uri(targetUri).get();
    }

    private String buildTargetUri(ProxyExchange<byte[]> proxy, String serviceId, ServerHttpRequest request) {

        String query = request.getURI().getRawQuery();
        String mappedHost = mappingConfig.getMappedHost(serviceId);
        proxy.sensitive(GW_DEFAULT_SENSITIVE);
        return (StringUtils.startsWithIgnoreCase(mappedHost, "http") ? "" : "http://") + mappedHost + StringUtils.delete(proxy.path(), "/" + serviceId) + (StringUtils.hasText(query) ? ("?" + query) : "");
    }

    @DeleteMapping(path = "/{serviceId}/**")
    public Mono<ResponseEntity<byte[]>> proxyDelete(ProxyExchange<byte[]> proxy, @PathVariable("serviceId") String serviceId, ServerHttpRequest request) {
        String targetUri = buildTargetUri(proxy, serviceId, request);
        return proxy.uri(targetUri).delete();
    }

    @RequestMapping(path = "/{serviceId}/**", method = RequestMethod.HEAD)
    public Mono<ResponseEntity<byte[]>> proxyHead(ProxyExchange<byte[]> proxy, @PathVariable("serviceId") String serviceId, ServerHttpRequest request) {
        String targetUri = buildTargetUri(proxy, serviceId, request);
        return proxy.uri(targetUri).head();
    }

    @RequestMapping(path = "/{serviceId}/**", method = RequestMethod.OPTIONS)
    public Mono<ResponseEntity<byte[]>> proxyOptions(ProxyExchange<byte[]> proxy, @PathVariable("serviceId") String serviceId, ServerHttpRequest request) {
        String targetUri = buildTargetUri(proxy, serviceId, request);
        return proxy.uri(targetUri).options();
    }

    @PatchMapping("/{serviceId}/**")
    public Mono<ResponseEntity<byte[]>> proxyPatch(ProxyExchange<byte[]> proxy, @PathVariable("serviceId") String serviceId, ServerHttpRequest request) {
        String targetUri = buildTargetUri(proxy, serviceId, request);
        return proxy.uri(targetUri).patch();
    }

    @PostMapping(path = "/{serviceId}/**")
    public Mono<ResponseEntity<byte[]>> proxyPost(ProxyExchange<byte[]> proxy, @PathVariable("serviceId") String serviceId, ServerHttpRequest request) {
        String targetUri = buildTargetUri(proxy, serviceId, request);
        Mono<ResponseEntity<byte[]>> postResp = proxy.uri(targetUri).post();
        return postResp.map(res -> {
            if (res.getStatusCode().is5xxServerError()) {
                // change status code to 400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(res.getHeaders()).body(res.getBody());
            }
            return res;
        });
    }

    @PutMapping(path = "/{serviceId}/**")
    public Mono<ResponseEntity<byte[]>> proxyPut(ProxyExchange<byte[]> proxy, @PathVariable("serviceId") String serviceId, ServerHttpRequest request) {
        String targetUri = buildTargetUri(proxy, serviceId, request);
        return proxy.uri(targetUri).put();
    }

    @Bean
    ProxyExchangeArgumentResolver proxyExchangeArgumentResolver(Optional<WebClient.Builder> optional, ProxyProperties proxy) {
        WebClient.Builder builder = optional.orElse(WebClient.builder());
        AIBankProxyExchangeArgumentResolver resolver = new AIBankProxyExchangeArgumentResolver(builder.build(), this.timeoutPreserved, this.defaultTimeout, this.connectTimeout);
        resolver.setHeaders(proxy.convertHeaders());
        resolver.setAutoForwardedHeaders(proxy.getAutoForward());
        resolver.setSensitive(proxy.getSensitive()); // can be null
        return resolver;
    }

    @Bean
    ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties webProperties, ObjectProvider<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer, ApplicationContext applicationContext, ServerProperties serverProperties) {
        GlobalErrorWebExceptionHandler exceptionHandler = new GlobalErrorWebExceptionHandler(errorAttributes, webProperties.getResources(), serverProperties.getError(), applicationContext);
        exceptionHandler.setViewResolvers(viewResolvers.orderedStream().toList());
        exceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        exceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return exceptionHandler;
    }

    /**
     * 控制 Spring 數據綁定行為 - for Fortify - Mass Assignment: Insecure Binder Configuration
     * 
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields(new String[] {});
    }
}