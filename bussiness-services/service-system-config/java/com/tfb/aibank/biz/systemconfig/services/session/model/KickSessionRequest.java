package com.tfb.aibank.biz.systemconfig.services.session.model;

public class KickSessionRequest {
    /** 統一編號 */
    private String corpIdNum;
    /** 授權碼 */
    private String authorityCode;
    /** 系統代號 */
    private String systemId;
    /** sessionID */
    private String sessionID;

    /**
     * @return the corpIdNum
     */
    public String getCorpIdNum() {
        return corpIdNum;
    }

    /**
     * @param corpIdNum
     *            the corpIdNum to set
     */
    public void setCorpIdNum(String corpIdNum) {
        this.corpIdNum = corpIdNum;
    }

    /**
     * @return the authorityCode
     */
    public String getAuthorityCode() {
        return authorityCode;
    }

    /**
     * @param authorityCode
     *            the authorityCode to set
     */
    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    /**
     * @return the systemId
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId
     *            the systemId to set
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * @return the sessionID
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * @param sessionID
     *            the sessionID to set
     */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

}
