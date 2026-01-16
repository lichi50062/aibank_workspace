package com.ibm.tw.ibmb.server.gateway.config;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@ConfigurationProperties(prefix = "aibank.server.gateway.routes")
public class RouteMappingConfig {

    private int serviceDefaultPort = 80;

    private boolean enabled = false;

    private String serviceUrlPattern = "http://{0}:{1}";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private Map<String, String> mapping = new LinkedHashMap<>();

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }

    public String getMappedHost(String serviceId) {
        String defaultRoute = MessageFormat.format(serviceUrlPattern, serviceId, serviceDefaultPort);
        if (!enabled) {
            return defaultRoute;
        }
        String mappedRoute = mapping.get(serviceId);
        return !StringUtils.hasText(mappedRoute) ? defaultRoute : mappedRoute;
    }

    /**
     * @return the serviceDefaultPort
     */
    public int getServiceDefaultPort() {
        return serviceDefaultPort;
    }

    /**
     * @param serviceDefaultPort
     *            the serviceDefaultPort to set
     */
    public void setServiceDefaultPort(int serviceDefaultPort) {
        this.serviceDefaultPort = serviceDefaultPort;
    }

    /**
     * @return the seriviceUrlPattern
     */
    public String getServiceUrlPattern() {
        return serviceUrlPattern;
    }

    /**
     * @param seriviceUrlPattern
     *            the seriviceUrlPattern to set
     */
    public void setServiceUrlPattern(String seriviceUrlPattern) {
        this.serviceUrlPattern = seriviceUrlPattern;
    }

}
