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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.util.List;

import com.tfb.aibank.common.model.TxHeadRq;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB552171Req.java
 * 
 * <p>Description:網路銀行轉出帳號建檔及維護 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "網路銀行轉出帳號建檔及維護 上行電文")
public class EB552171Req extends TxHeadRq {

    private static final long serialVersionUID = -6559106461004629575L;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** NAME_CODE */
    @Schema(description = "NAME_CODE")
    private String nameCode;

    /** 確認修改 */
    @Schema(description = "確認修改")
    private String intTyp;

    /** 轉入帳號約定方式 */
    @Schema(description = "轉入帳號約定方式")
    private String tdType;

    /** 確認選項 */
    @Schema(description = "確認選項")
    private String busEb5;

    /** 網銀線上開戶初始密碼 */
    @Schema(description = "網銀線上開戶初始密碼")
    private String newpassWord;

    /** 國內簡訊動態密碼手機號碼 */
    @Schema(description = "國內簡訊動態密碼手機號碼")
    private String newuserId;

    /** 國外簡訊動態密碼手機號碼 */
    @Schema(description = "國外簡訊動態密碼手機號碼")
    private String mrMemo;

    /** 證件類型 */
    @Schema(description = "證件類型")
    private String idtype;

    /** 細項 */
    @Schema(description = "細項")
    private List<EB552171ReqRep> txRepeats;

    /**
     * @return the uidDup
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            the uidDup to set
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * @return the intTyp
     */
    public String getIntTyp() {
        return intTyp;
    }

    /**
     * @param intTyp
     *            the intTyp to set
     */
    public void setIntTyp(String intTyp) {
        this.intTyp = intTyp;
    }

    /**
     * @return the tdType
     */
    public String getTdType() {
        return tdType;
    }

    /**
     * @param tdType
     *            the tdType to set
     */
    public void setTdType(String tdType) {
        this.tdType = tdType;
    }

    /**
     * @return the busEb5
     */
    public String getBusEb5() {
        return busEb5;
    }

    /**
     * @param busEb5
     *            the busEb5 to set
     */
    public void setBusEb5(String busEb5) {
        this.busEb5 = busEb5;
    }

    /**
     * @return the newpaxxWord (fortify fix)
     */
    public String getNewpassWord() {
        return newpassWord;
    }

    /**
     * @param newpaxxWord
     *            the newpaxxWord to set (fortify fix)
     */
    public void setNewpassWord(String newpassWord) {
        this.newpassWord = newpassWord;
    }

    /**
     * @return the newuserId
     */
    public String getNewuserId() {
        return newuserId;
    }

    /**
     * @param newuserId
     *            the newuserId to set
     */
    public void setNewuserId(String newuserId) {
        this.newuserId = newuserId;
    }

    /**
     * @return the mrMemo
     */
    public String getMrMemo() {
        return mrMemo;
    }

    /**
     * @param mrMemo
     *            the mrMemo to set
     */
    public void setMrMemo(String mrMemo) {
        this.mrMemo = mrMemo;
    }

    /**
     * @return the idtype
     */
    public String getIdtype() {
        return idtype;
    }

    /**
     * @param idtype
     *            the idtype to set
     */
    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    /**
     * @return the txRepeats
     */
    public List<EB552171ReqRep> getTxRepeats() {
        return txRepeats;
    }

    /**
     * @param txRepeats
     *            the txRepeats to set
     */
    public void setTxRepeats(List<EB552171ReqRep> txRepeats) {
        this.txRepeats = txRepeats;
    }

}
