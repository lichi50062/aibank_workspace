package com.tfb.aibank.chl.general.resource.dto;

/**
 * // @formatter:off
/**
 * @(#)TwoFactorAuthUserResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/13, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class TwoFactorAuthUserResponse {
    
    private Long seq;
    
    private Integer companyKey;
    
    private Integer userKey;
    
    private String status;

    
    public TwoFactorAuthUserResponse() {
        super();
    }
    
    public TwoFactorAuthUserResponse(Long seq, Integer companyKey, Integer userKey) {
        super();
        this.seq = seq;
        this.companyKey = companyKey;
        this.userKey = userKey;
    }

    public TwoFactorAuthUserResponse(TwoFactorAuthUserRequest request) {
        super();
        
        this.seq = request.getSeq();
        this.companyKey = request.getCompanyKey();
        this.userKey = request.getUserKey();

    }

    public Long getSeq() {
        return seq;
    }


    public void setSeq(Long seq) {
        this.seq = seq;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "TwoFactorAuthUserResponse [seq=" + seq + ", companyKey=" + companyKey + ", userKey=" + userKey + ", status=" + status + "]";
    }
    

}
