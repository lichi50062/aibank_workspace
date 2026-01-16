package com.tfb.aibank.biz.component.etrans;

public class RSAResult_JSB {

    protected String errorCode;
    protected String errorMessage;
    protected String keyVersion;
    protected int RSAKeyType;
    protected int RSAKeyLength;
    protected int RSAKeyLengthAsByte;
    protected String txCode;
    protected String RN1;
    protected String clientEncRN1;
    protected String serverSignRN1;
    protected String encRn2;
    protected String encRn2Hash;
    protected String sessionKeyHash;
    protected String sessionKey;
    protected String encSessionKey;
    protected String cipherText;
    protected String plainText;
    protected int hsmEnvType;
    protected String mac;
    protected int isLocalEnv;
    protected String keyVersionClient;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(String keyVersion) {
        this.keyVersion = keyVersion;
    }

    public int getRSAKeyType() {
        return RSAKeyType;
    }

    public void setRSAKeyType(int rSAKeyType) {
        RSAKeyType = rSAKeyType;
    }

    public int getRSAKeyLength() {
        return RSAKeyLength;
    }

    public void setRSAKeyLength(int rSAKeyLength) {
        RSAKeyLength = rSAKeyLength;
    }

    public int getRSAKeyLengthAsByte() {
        return RSAKeyLengthAsByte;
    }

    public void setRSAKeyLengthAsByte(int rSAKeyLengthAsByte) {
        RSAKeyLengthAsByte = rSAKeyLengthAsByte;
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public String getRN1() {
        return RN1;
    }

    public void setRN1(String rN1) {
        RN1 = rN1;
    }

    public String getClientEncRN1() {
        return clientEncRN1;
    }

    public void setClientEncRN1(String clientEncRN1) {
        this.clientEncRN1 = clientEncRN1;
    }

    public String getServerSignRN1() {
        return serverSignRN1;
    }

    public void setServerSignRN1(String serverSignRN1) {
        this.serverSignRN1 = serverSignRN1;
    }

    public String getEncRn2() {
        return encRn2;
    }

    public void setEncRn2(String encRn2) {
        this.encRn2 = encRn2;
    }

    public String getEncRn2Hash() {
        return encRn2Hash;
    }

    public void setEncRn2Hash(String encRn2Hash) {
        this.encRn2Hash = encRn2Hash;
    }

    public String getSessionKeyHash() {
        return sessionKeyHash;
    }

    public void setSessionKeyHash(String sessionKeyHash) {
        this.sessionKeyHash = sessionKeyHash;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getEncSessionKey() {
        return encSessionKey;
    }

    public void setEncSessionKey(String encSessionKey) {
        this.encSessionKey = encSessionKey;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public int getHsmEnvType() {
        return hsmEnvType;
    }

    public void setHsmEnvType(int hsmEnvType) {
        this.hsmEnvType = hsmEnvType;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getIsLocalEnv() {
        return isLocalEnv;
    }

    public void setIsLocalEnv(int isLocalEnv) {
        this.isLocalEnv = isLocalEnv;
    }

    public String getKeyVersionClient() {
        return keyVersionClient;
    }

    public void setKeyVersionClient(String keyVersionClient) {
        this.keyVersionClient = keyVersionClient;
    }

    @Override
    public String toString() {
        return "RSAResult_JSB{" + "errorCode='" + errorCode + '\'' + ", errorMessage='" + errorMessage + '\'' + ", keyVersion='" + keyVersion + '\'' + ", RSAKeyType=" + RSAKeyType + ", RSAKeyLength=" + RSAKeyLength + ", RSAKeyLengthAsByte=" + RSAKeyLengthAsByte + ", txCode='" + txCode + '\'' + ", RN1='" + RN1 + '\'' + ", clientEncRN1='" + clientEncRN1 + '\'' + ", serverSignRN1='" + serverSignRN1 + '\'' + ", encRn2='" + encRn2 + '\'' + ", encRn2Hash='" + encRn2Hash + '\'' + ", sessionKeyHash='" + sessionKeyHash + '\'' + ", sessionKey='" + sessionKey + '\'' + ", encSessionKey='" + encSessionKey + '\'' + ", cipherText='" + cipherText + '\'' + ", plainText='" + plainText + '\'' + ", hsmEnvType=" + hsmEnvType + ", mac='" + mac + '\'' + ", isLocalEnv=" + isLocalEnv + ", keyVersionClient='" + keyVersionClient + '\'' + '}';
    }
}
