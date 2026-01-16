/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author john
 *
 */
public class FidoQueryVerifyResultResponseContentBean {

    /** 應用組合 */
    @SerializedName("Action")
    private String action;

    /** 選擇的驗證方式 */
    @SerializedName("SelectType")
    private String selectType;

    /** 驗證時間 */
    @SerializedName("VerifyTime")
    private String verifyTime;

    /** 簽章內文 */
    @SerializedName("Plaintext")
    private String plaintext;

    /** OutputParams 子項目 */
    @SerializedName("FidoParams")
    private FidoQueryVerifyResultResponseContentFidoParamsBean fidoParams;

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the selectType
     */
    public String getSelectType() {
        return selectType;
    }

    /**
     * @param selectType
     *            the selectType to set
     */
    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    /**
     * @return the verifyTime
     */
    public String getVerifyTime() {
        return verifyTime;
    }

    /**
     * @param verifyTime
     *            the verifyTime to set
     */
    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

    /**
     * @return the plaintext
     */
    public String getPlaintext() {
        return plaintext;
    }

    /**
     * @param plaintext
     *            the plaintext to set
     */
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    /**
     * @return the fidoParams
     */
    public FidoQueryVerifyResultResponseContentFidoParamsBean getFidoParams() {
        return fidoParams;
    }

    /**
     * @param fidoParams
     *            the fidoParams to set
     */
    public void setFidoParams(FidoQueryVerifyResultResponseContentFidoParamsBean fidoParams) {
        this.fidoParams = fidoParams;
    }

}
