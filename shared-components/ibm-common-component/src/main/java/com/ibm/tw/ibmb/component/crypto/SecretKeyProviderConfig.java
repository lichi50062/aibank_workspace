package com.ibm.tw.ibmb.component.crypto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aibank.crypto.seckey")
public class SecretKeyProviderConfig {
    private Map<String, String> providers = new HashMap<>();

    public Map<String, String> getProviders() {
        return providers;
    }

    public void setProviders(Map<String, String> providers) {
        this.providers = providers;
    }

    public String getProperty(String key, String defaultValue) {
        return this.providers.getOrDefault(key, defaultValue);
    }
}
