package com.tfb.aibank.chl.system.ot004.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT004030Rq.java
 * 
 * <p>Description: 030 結果頁截圖</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/17, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT004030Rq implements RqData {

    /** 類型，resultPage:結果頁 personalization:個人化版位 */
    private String type;

    /** 由前端傳入的「交易存取記錄追蹤編號」 */
    private String traceId;

    /** 結果頁HTML */
    private String resData;

    /** 版型代號 */
    private String templateId;

    /** 情境編號 */
    private String offerId;

    /** 版位代號 */
    private String actionPointId;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getResData() {
        return resData;
    }

    public void setResData(String resData) {
        this.resData = resData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getActionPointId() {
        return actionPointId;
    }

    public void setActionPointId(String actionPointId) {
        this.actionPointId = actionPointId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
