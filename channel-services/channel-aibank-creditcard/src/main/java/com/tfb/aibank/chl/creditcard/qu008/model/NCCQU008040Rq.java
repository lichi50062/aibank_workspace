package com.tfb.aibank.chl.creditcard.qu008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCQU008040Rq.java
 * 
 * <p>Description:信用卡分期管理 040 方案選項頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008040Rq implements RqData {

    /** 明細區塊 */
    private NCCQU008BilledDetailVo billedDetail;

    /** 來源交易 ex:010(首頁) 021(條款頁) 030(單筆總攬頁) ,040(方案選擇頁) */
    private String sourcePageId;

    /**
     * 是否帳單分期流程
     */
    private Boolean isBillProcess;

    /**
     * 是否直達帳單分期已申請處理中
     */
    private Boolean isGotoBillProcessPage;

    /**
     * @return the billedDetail
     */
    public NCCQU008BilledDetailVo getBilledDetail() {
        return billedDetail;
    }

    /**
     * @param billedDetail
     *            the billedDetail to set
     */
    public void setBilledDetail(NCCQU008BilledDetailVo billedDetail) {
        this.billedDetail = billedDetail;
    }

    /**
     * @return the sourcePageId
     */
    public String getSourcePageId() {
        return sourcePageId;
    }

    /**
     * @param sourcePageId
     *            the sourcePageId to set
     */
    public void setSourcePageId(String sourcePageId) {
        this.sourcePageId = sourcePageId;
    }

    /**
     * @return the isBillProcess
     */
    public Boolean getIsBillProcess() {
        return isBillProcess;
    }

    /**
     * @param isBillProcess
     *            the isBillProcess to set
     */
    public void setIsBillProcess(Boolean isBillProcess) {
        this.isBillProcess = isBillProcess;
    }

    /**
     * @return the isGotoBillProcessPage
     */
    public Boolean getIsGotoBillProcessPage() {
        return isGotoBillProcessPage;
    }

    /**
     * @param isGotoBillProcessPage
     *            the isGotoBillProcessPage to set
     */
    public void setIsGotoBillProcessPage(Boolean isGotoBillProcessPage) {
        this.isGotoBillProcessPage = isGotoBillProcessPage;
    }

}
