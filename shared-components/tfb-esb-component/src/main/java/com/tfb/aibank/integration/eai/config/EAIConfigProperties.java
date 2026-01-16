/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "eai")
public class EAIConfigProperties {

    private Map<String, EAIChannelConfig> channels;

    private String postURL;

    private int maxConversactionTime = 1000;

    private boolean checkIntegrity = false;

    private int connectionTimeout = 30000;

    private int socketTimeout = 120000;

    private String clientSystemId;

    public int getMaxConversactionTime() {
        return maxConversactionTime;
    }

    public void setMaxConversactionTime(int maxConversactionTime) {
        this.maxConversactionTime = maxConversactionTime;
    }

    public boolean isCheckIntegrity() {
        return checkIntegrity;
    }

    public void setCheckIntegrity(boolean checkIntegrity) {
        this.checkIntegrity = checkIntegrity;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Map<String, EAIChannelConfig> getChannels() {
        return channels;
    }

    public void setChannels(Map<String, EAIChannelConfig> channels) {
        this.channels = channels;
    }

    public String getPostURL() {
        return postURL;
    }

    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    public String getClientSystemId() {
        return clientSystemId;
    }

    public void setClientSystemId(String clientSystemId) {
        this.clientSystemId = clientSystemId;
    }
}
