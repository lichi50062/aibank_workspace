package com.tfb.aibank.chl.creditcard.ag011.model;

import com.tfb.aibank.chl.creditcard.resource.dto.CEW466RRes;

// @formatter:off
/**
 * @(#)NCCAG011Cache.java
 * 
 * <p>Description:好市多會費代扣繳申請 交易暫存資料物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCAG011Cache {

    private String clientIp;

    private String traceId;

    private CEW466RRes res;

    /** 是否已申請會費代扣繳 */
    private boolean isApply;

    /**
     * @return the isApply
     */
    public boolean isApply() {
        return isApply;
    }

    /**
     * @param isApply
     *            the isApply to set
     */
    public void setApply(boolean isApply) {
        this.isApply = isApply;
    }

    /**
     * @return the res
     */
    public CEW466RRes getRes() {
        return res;
    }

    /**
     * @param res
     *            the res to set
     */
    public void setRes(CEW466RRes res) {
        this.res = res;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return the traceId
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId
     *            the traceId to set
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
