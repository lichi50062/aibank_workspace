/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.session;

import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.session.vo.CardUserProfileVo;
import com.tfb.aibank.chl.session.vo.EB5556981Response;

// @formatter:off
/**
 * @(#)AIBankUserExtend.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AIBankUserExtend extends BaseAIBankUserExtend<EB5556981Response> {

    private static final IBLog logger = IBLog.getLog(AIBankUserExtend.class);

    private static final long serialVersionUID = 5410223393424947929L;

    private static final String[] NO_EBILL_CHECK_DATE_TYPES = { "N1", "N2", "N3", "N4", "N5", "E5" };

    public static final String[] PLATINUM_MASS_CHECK = { "A", "G", "V" };

    /**
     * for reference only
     */
    private AIBankUser aiBankUser = null;

    /**
     * 晶片金融卡卡號
     */
    private String loginICCardNo = "";

    /**
     * 暫存用的 nameCode, for 首登
     */
    private String tempNameCode;
    /**
     * 是否已簽訂契約
     */
    private Boolean signedETFContract;
    /** 黃金存摺電子契約註記 */
    private Boolean signedGoldContract;

    /** 客戶身分是否為弱勢客戶註記 */
    private Boolean vulFlag;

    private CardUserProfileVo cardUserProfile;

    public AIBankUserExtend(AIBankUser aiBankUser, EB5556981Response loginMsgRs, CardUserProfileVo cardUserProfile) {
        this.loginMsgRs = loginMsgRs;
        this.aiBankUser = aiBankUser;
        this.cardUserProfile = cardUserProfile;
    }

    /**
     * @return the pLATINUM_MASS_CHECK
     */
    public static String[] getPLATINUM_MASS_CHECK() {
        return PLATINUM_MASS_CHECK;
    }

    /**
     * @return the aiBankUser
     */
    public AIBankUser getAiBankUser() {
        return aiBankUser;
    }

    /**
     * @param aiBankUser
     *            the aiBankUser to set
     */
    public void setAiBankUser(AIBankUser aiBankUser) {
        this.aiBankUser = aiBankUser;
    }

    /**
     * @return the loginICCardNo
     */
    public String getLoginICCardNo() {
        return loginICCardNo;
    }

    /**
     * @param loginICCardNo
     *            the loginICCardNo to set
     */
    public void setLoginICCardNo(String loginICCardNo) {
        this.loginICCardNo = loginICCardNo;
    }

    /**
     * @return the tempNameCode
     */
    public String getTempNameCode() {
        return tempNameCode;
    }

    /**
     * @param tempNameCode
     *            the tempNameCode to set
     */
    public void setTempNameCode(String tempNameCode) {
        this.tempNameCode = tempNameCode;
    }

    /**
     * @return the signedETFContract
     */
    public Boolean getSignedETFContract() {
        return signedETFContract;
    }

    /**
     * @param signedETFContract
     *            the signedETFContract to set
     */
    public void setSignedETFContract(Boolean signedETFContract) {
        this.signedETFContract = signedETFContract;
    }

    /**
     * @return the signedGoldContract
     */
    public Boolean getSignedGoldContract() {
        return signedGoldContract;
    }

    /**
     * @param signedGoldContract
     *            the signedGoldContract to set
     */
    public void setSignedGoldContract(Boolean signedGoldContract) {
        this.signedGoldContract = signedGoldContract;
    }

    /**
     * @return the vulFlag
     */
    public Boolean getVulFlag() {
        return vulFlag;
    }

    /**
     * @param vulFlag
     *            the vulFlag to set
     */
    public void setVulFlag(Boolean vulFlag) {
        this.vulFlag = vulFlag;
    }

    /**
     * @return the noEbillCheckDateTypes
     */
    public static String[] getNoEbillCheckDateTypes() {
        return NO_EBILL_CHECK_DATE_TYPES;
    }

    /**
     * @return the cardUserProfile
     */
    public CardUserProfileVo getCardUserProfile() {
        return cardUserProfile;
    }

    /**
     * @param cardUserProfile
     *            the cardUserProfile to set
     */
    public void setCardUserProfile(CardUserProfileVo cardUserProfile) {
        this.cardUserProfile = cardUserProfile;
    }

}
