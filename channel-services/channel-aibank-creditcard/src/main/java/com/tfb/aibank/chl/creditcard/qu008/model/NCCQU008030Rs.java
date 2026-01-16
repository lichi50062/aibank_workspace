package com.tfb.aibank.chl.creditcard.qu008.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU008030Rs.java
 * 
 * <p>Description:信用卡分期管理 030 單筆總覽頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008030Rs implements RsData {

    /** 未請款明細區塊 資料 */
    private List<NCCQU008BilledDetailVo> unBilledDetails = new ArrayList<>();

    /** 未請款明細區塊 查詢結果 */
    private String unBilledDetailQueryStatus;

    /** 已請款明細區塊 資料 */
    private List<NCCQU008BilledDetailVo> billedDetails = new ArrayList<>();

    /** 已請款明細區塊 查詢結果 */
    private String billedDetailQueryStatus;

    /**
     * @return the unBilledDetails
     */
    public List<NCCQU008BilledDetailVo> getUnBilledDetails() {
        return unBilledDetails;
    }

    /**
     * @param unBilledDetails
     *            the unBilledDetails to set
     */
    public void setUnBilledDetails(List<NCCQU008BilledDetailVo> unBilledDetails) {
        this.unBilledDetails = unBilledDetails;
    }

    /**
     * @return the unBilledDetailQueryStatus
     */
    public String getUnBilledDetailQueryStatus() {
        return unBilledDetailQueryStatus;
    }

    /**
     * @param unBilledDetailQueryStatus
     *            the unBilledDetailQueryStatus to set
     */
    public void setUnBilledDetailQueryStatus(String unBilledDetailQueryStatus) {
        this.unBilledDetailQueryStatus = unBilledDetailQueryStatus;
    }

    /**
     * @return the billedDetails
     */
    public List<NCCQU008BilledDetailVo> getBilledDetails() {
        return billedDetails;
    }

    /**
     * @param billedDetails
     *            the billedDetails to set
     */
    public void setBilledDetails(List<NCCQU008BilledDetailVo> billedDetails) {
        this.billedDetails = billedDetails;
    }

    /**
     * @return the billedDetailQueryStatus
     */
    public String getBilledDetailQueryStatus() {
        return billedDetailQueryStatus;
    }

    /**
     * @param billedDetailQueryStatus
     *            the billedDetailQueryStatus to set
     */
    public void setBilledDetailQueryStatus(String billedDetailQueryStatus) {
        this.billedDetailQueryStatus = billedDetailQueryStatus;
    }

}
