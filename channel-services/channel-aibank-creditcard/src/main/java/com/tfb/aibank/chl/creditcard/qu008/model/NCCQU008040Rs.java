package com.tfb.aibank.chl.creditcard.qu008.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU008040Rs.java
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
public class NCCQU008040Rs implements RsData {
    /** 明細區塊 */
    private NCCQU008BilledDetailVo billedDetailFormPrev;
    /**
     * 是否帳單分期流程
     */
    private Boolean isBillProcessFromPrev;

    /** 來源交易 ex:030(單筆總攬頁) ,040(方案選擇頁) */
    private String sourcePageId;

    /** 分期資訊 */
    private String installmentDesc;

    /** 分期說明 */
    private String installmentDesc02;

    /** 分期方案 */
    private List<NCCQU008InsInterestSection> insInterestSection = new ArrayList<>();

    /** 其他分期方案 */
    private List<NCCQU008InsInterestSection> otherInsInterestSection = new ArrayList<>();

    /** 是否帳單流程 */
    private Boolean isBillProcess;

    /** 帳單分期為已申請處理中 */
    private Boolean isBillProcessAndApply;

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
     * @return the installmentDesc
     */
    public String getInstallmentDesc() {
        return installmentDesc;
    }

    /**
     * @param installmentDesc
     *            the installmentDesc to set
     */
    public void setInstallmentDesc(String installmentDesc) {
        this.installmentDesc = installmentDesc;
    }

    /**
     * @return the installmentDesc02
     */
    public String getInstallmentDesc02() {
        return installmentDesc02;
    }

    /**
     * @param installmentDesc02
     *            the installmentDesc02 to set
     */
    public void setInstallmentDesc02(String installmentDesc02) {
        this.installmentDesc02 = installmentDesc02;
    }

    /**
     * @return the insInterestSection
     */
    public List<NCCQU008InsInterestSection> getInsInterestSection() {
        return insInterestSection;
    }

    /**
     * @param insInterestSection
     *            the insInterestSection to set
     */
    public void setInsInterestSection(List<NCCQU008InsInterestSection> insInterestSection) {
        this.insInterestSection = insInterestSection;
    }

    /**
     * @return the otherInsInterestSection
     */
    public List<NCCQU008InsInterestSection> getOtherInsInterestSection() {
        return otherInsInterestSection;
    }

    /**
     * @param otherInsInterestSection
     *            the otherInsInterestSection to set
     */
    public void setOtherInsInterestSection(List<NCCQU008InsInterestSection> otherInsInterestSection) {
        this.otherInsInterestSection = otherInsInterestSection;
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
     * @return the isBillProcessAndApply
     */
    public Boolean getIsBillProcessAndApply() {
        return isBillProcessAndApply;
    }

    /**
     * @param isBillProcessAndApply
     *            the isBillProcessAndApply to set
     */
    public void setIsBillProcessAndApply(Boolean isBillProcessAndApply) {
        this.isBillProcessAndApply = isBillProcessAndApply;
    }

    public NCCQU008BilledDetailVo getBilledDetailFormPrev() {
        return billedDetailFormPrev;
    }

    public void setBilledDetailFormPrev(NCCQU008BilledDetailVo billedDetailFormPrev) {
        this.billedDetailFormPrev = billedDetailFormPrev;
    }

    public Boolean getIsBillProcessFromPrev() {
        return isBillProcessFromPrev;
    }

    public void setIsBillProcessFromPrev(Boolean isBillProcessFromPrev) {
        this.isBillProcessFromPrev = isBillProcessFromPrev;
    }

}
