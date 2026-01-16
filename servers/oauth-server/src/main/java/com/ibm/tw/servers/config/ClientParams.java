package com.ibm.tw.servers.config;

public class ClientParams {

    private String clientId;

    private String clientSecret;

    private String[] grantTypes;

    private String[] scopes;

    private int validSec;

    private String username;

    private String secret;

    public ClientParams() {

    }

    public ClientParams(String clientId, String clientSecret, String[] grantTypes, String[] scopes, int validSec) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantTypes = grantTypes;
        this.scopes = scopes;
        this.validSec = validSec;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    public String[] getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String[] grantTypes) {
        this.grantTypes = grantTypes;
    }

    public void setValidSec(int validSec) {
        this.validSec = validSec;
    }

    public int getValidSec() {
        return this.validSec;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("clientId=").append(clientId).append("grantTypes=");
        for (int i = 0; i < grantTypes.length; i++) {
            sb.append(grantTypes).append(i < grantTypes.length - 1 ? "," : "");
        }

        sb.append("  expire in (sec):").append(validSec);

        return sb.toString();
    }

    /**
     * @return {@link #username}
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            {@link #username}
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return {@link #secrxt}
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secrxt
     *            {@link #secrxt}
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

}
