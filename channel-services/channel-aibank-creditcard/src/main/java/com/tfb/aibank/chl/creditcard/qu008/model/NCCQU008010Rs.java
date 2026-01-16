package com.tfb.aibank.chl.creditcard.qu008.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU008010Rs.java
 * 
 * <p>Description:信用卡分期管理 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008010Rs implements RsData {

    /**
     * 已分期剩餘分期總額
     */
    private String remainInsTotal;

    /**
     * 已分期查詢狀態 NCCQU008StatusType.memo e.g. 查詢失敗,查詢無資料,查詢成功
     */
    private String queryStatus;

    /**
     * 已分期單期分期明細
     */
    private List<NCCQU008InstallmentDetailVo> singleIntallments;

    /**
     * 已分期帳單分期明細
     */
    private List<NCCQU008InstallmentDetailVo> billIntallments;

    /**
     * 已分期其他分期明細
     */
    private List<NCCQU008InstallmentDetailVo> otherIntallments;

    /**
     * 目標page代號 e.g. 010
     */
    private String targetPagePath;

    /** 明細區塊 */
    private NCCQU008BilledDetailVo billedDetail;

    /** 取得簽訂約定書註記 */
    private Boolean isAgreement;

    /** 分期類型 BILL or UNBILL */
    private String type;

    /**
     * 單筆分期尚未入帳消費區塊 CEW222R_Rs.NCCDAY= 0。 單筆分期已入帳消費區塊 CE4150R~CE4153R_Rs. NCGROP=空值且CE4150R~CE4153R_Rs. NCSEQN = 0或無值。
     */
    private List<NCCQU008BilledDetailVo> singleIntallmentsNoGetMoney;

    /**
     * 單筆分期申請中已請款：CEW222R_Rs.Pchday取月日(MMDD)後四碼，為消費月日。
     */
    private List<NCCQU008BilledDetailVo> singleIntallmentsGetMoney;

    /**
     * 未分期單筆查詢未請款狀態 e.g. 查詢失敗,查詢無資料,查詢成功
     */
    private String singleIntallmentsQueryNotPayStatus;

    /**
     * 未分期單筆查詢已請款狀態 e.g. 查詢失敗,查詢無資料,查詢成功
     */
    private String singleIntallmentsQueryHasPayStatus;

    /**
     * 帳單分期未分期
     */
    private List<NCCQU008BillNotIntallmentsDisplay> billNotIntallments;

    /**
     * 未分期帳單查詢狀態 e.g. 查詢失敗,查詢無資料,查詢成功
     */
    private String billIntallmentsQueryStatus;

    /**
     * 預設 可分期交易Tab 0:已分期 , 1:可分期
     */
    private String txnTabDefault = "0";

    /**
     * @return the remainInsTotal
     */
    public String getRemainInsTotal() {
        return remainInsTotal;
    }

    /**
     * @param remainInsTotal
     *            the remainInsTotal to set
     */
    public void setRemainInsTotal(String remainInsTotal) {
        this.remainInsTotal = remainInsTotal;
    }

    /**
     * @return the singleQueryStatus
     */
    public String getQueryStatus() {
        return queryStatus;
    }

    /**
     * @param singleQueryStatus
     *            the singleQueryStatus to set
     */
    public void setQueryStatus(String queryStatus) {
        this.queryStatus = queryStatus;
    }

    /**
     * @return the singleIntallments
     */
    public List<NCCQU008InstallmentDetailVo> getSingleIntallments() {
        return singleIntallments;
    }

    /**
     * @param singleIntallments
     *            the singleIntallments to set
     */
    public void setSingleIntallments(List<NCCQU008InstallmentDetailVo> singleIntallments) {
        this.singleIntallments = singleIntallments;
    }

    /**
     * @return the billIntallments
     */
    public List<NCCQU008InstallmentDetailVo> getBillIntallments() {
        return billIntallments;
    }

    /**
     * @param billIntallments
     *            the billIntallments to set
     */
    public void setBillIntallments(List<NCCQU008InstallmentDetailVo> billIntallments) {
        this.billIntallments = billIntallments;
    }

    /**
     * @return the otherIntallments
     */
    public List<NCCQU008InstallmentDetailVo> getOtherIntallments() {
        return otherIntallments;
    }

    /**
     * @param otherIntallments
     *            the otherIntallments to set
     */
    public void setOtherIntallments(List<NCCQU008InstallmentDetailVo> otherIntallments) {
        this.otherIntallments = otherIntallments;
    }

    /**
     * @return the targetPagePath
     */
    public String getTargetPagePath() {
        return targetPagePath;
    }

    /**
     * @param targetPagePath
     *            the targetPagePath to set
     */
    public void setTargetPagePath(String targetPagePath) {
        this.targetPagePath = targetPagePath;
    }

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
     * @return the isAgreement
     */
    public Boolean getIsAgreement() {
        return isAgreement;
    }

    /**
     * @param isAgreement
     *            the isAgreement to set
     */
    public void setIsAgreement(Boolean isAgreement) {
        this.isAgreement = isAgreement;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the singleIntallmentsNoGetMoney
     */
    public List<NCCQU008BilledDetailVo> getSingleIntallmentsNoGetMoney() {
        return singleIntallmentsNoGetMoney;
    }

    /**
     * @param singleIntallmentsNoGetMoney
     *            the singleIntallmentsNoGetMoney to set
     */
    public void setSingleIntallmentsNoGetMoney(List<NCCQU008BilledDetailVo> singleIntallmentsNoGetMoney) {
        this.singleIntallmentsNoGetMoney = singleIntallmentsNoGetMoney;
    }

    /**
     * @return the billNotIntallments
     */
    public List<NCCQU008BillNotIntallmentsDisplay> getBillNotIntallments() {
        return billNotIntallments;
    }

    /**
     * @param billNotIntallments
     *            the billNotIntallments to set
     */
    public void setBillNotIntallments(List<NCCQU008BillNotIntallmentsDisplay> billNotIntallments) {
        this.billNotIntallments = billNotIntallments;
    }

    /**
     * @return the singleIntallmentsGetMoney
     */
    public List<NCCQU008BilledDetailVo> getSingleIntallmentsGetMoney() {
        return singleIntallmentsGetMoney;
    }

    /**
     * @param singleIntallmentsGetMoney
     *            the singleIntallmentsGetMoney to set
     */
    public void setSingleIntallmentsGetMoney(List<NCCQU008BilledDetailVo> singleIntallmentsGetMoney) {
        this.singleIntallmentsGetMoney = singleIntallmentsGetMoney;
    }

    /**
     * @return the singleIntallmentsQueryNotPayStatus
     */
    public String getSingleIntallmentsQueryNotPayStatus() {
        return singleIntallmentsQueryNotPayStatus;
    }

    /**
     * @param singleIntallmentsQueryNotPayStatus
     *            the singleIntallmentsQueryNotPayStatus to set
     */
    public void setSingleIntallmentsQueryNotPayStatus(String singleIntallmentsQueryNotPayStatus) {
        this.singleIntallmentsQueryNotPayStatus = singleIntallmentsQueryNotPayStatus;
    }

    /**
     * @return the singleIntallmentsQueryHasPayStatus
     */
    public String getSingleIntallmentsQueryHasPayStatus() {
        return singleIntallmentsQueryHasPayStatus;
    }

    /**
     * @param singleIntallmentsQueryHasPayStatus
     *            the singleIntallmentsQueryHasPayStatus to set
     */
    public void setSingleIntallmentsQueryHasPayStatus(String singleIntallmentsQueryHasPayStatus) {
        this.singleIntallmentsQueryHasPayStatus = singleIntallmentsQueryHasPayStatus;
    }

    /**
     * @return the billIntallmentsQueryStatus
     */
    public String getBillIntallmentsQueryStatus() {
        return billIntallmentsQueryStatus;
    }

    /**
     * @param billIntallmentsQueryStatus
     *            the billIntallmentsQueryStatus to set
     */
    public void setBillIntallmentsQueryStatus(String billIntallmentsQueryStatus) {
        this.billIntallmentsQueryStatus = billIntallmentsQueryStatus;
    }

    /**
     * @return the txnTabDefault
     */
    public String getTxnTabDefault() {
        return txnTabDefault;
    }

    /**
     * @param txnTabDefault
     *            the txnTabDefault to set
     */
    public void setTxnTabDefault(String txnTabDefault) {
        this.txnTabDefault = txnTabDefault;
    }

}
