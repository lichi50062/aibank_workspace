package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001031Rs.java 
* 
* <p>Description:裝置綁定條款頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230605, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001031Rs implements RsData {

    /** 設定項目 */
    private String nextPage;
    /** 條款標題 */
    private String contractTitle;
    /** 條款內文 */
    private String contractContent;

    /**
     * FIDO Portal SDK URL
     */
    private String fidoPortalUrl;

    /** 參加單位 */
    private String businessNo;

    /** 使用者 */
    private String memberNo;

    /**
     * 用戶旅程註記
     */
    private int onboardingType;

    /** 裝置綁定錯誤ErrorStatus */
    private NGNOT001100RsErrorStatus errorStatus;

    /**
     * @return the contractTitle
     */
    public String getContractTitle() {
        return contractTitle;
    }

    /**
     * @param contractTitle
     *            the contractTitle to set
     */
    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    /**
     * @return the contractContent
     */
    public String getContractContent() {
        return contractContent;
    }

    /**
     * @param contractContent
     *            the contractContent to set
     */
    public void setContractContent(String contractContent) {
        this.contractContent = contractContent;
    }

    /**
     * @return the nextPage
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * @param nextPage
     *            the nextPage to set
     */
    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * @return the fidoPortalUrl
     */
    public String getFidoPortalUrl() {
        return fidoPortalUrl;
    }

    /**
     * @param fidoPortalUrl
     *            the fidoPortalUrl to set
     */
    public void setFidoPortalUrl(String fidoPortalUrl) {
        this.fidoPortalUrl = fidoPortalUrl;
    }

    /**
     * @return the businessNo
     */
    public String getBusinessNo() {
        return businessNo;
    }

    /**
     * @param businessNo
     *            the businessNo to set
     */
    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    /**
     * @return the errorStatus
     */
    public NGNOT001100RsErrorStatus getErrorStatus() {
        return errorStatus;
    }

    /**
     * @param errorStatus
     *            the errorStatus to set
     */
    public void setErrorStatus(NGNOT001100RsErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

    /**
     * @return the onboardingType
     */
    public int getOnboardingType() {
        return onboardingType;
    }

    /**
     * @param onboardingType
     *            the onboardingType to set
     */
    public void setOnboardingType(int onboardingType) {
        this.onboardingType = onboardingType;
    }

    /**
     * @return the memberNo
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * @param memberNo
     *            the memberNo to set
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

}
