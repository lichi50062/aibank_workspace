package com.tfb.aibank.biz.component.videoauthapi.model;

// @formatter:off
/**
 * @(#)VideoAuthApiCommonRsData.java
 * 
 * <p>Description:[視訊 api rs 共用物件]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VideoAuthApiRsBase {

    /**
     * 狀態代碼
     */
    private String returnCode;

    /**
     * 回覆訊息
     */
    private String returnMsg;

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode
     *            the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return the returnMsg
     */
    public String getReturnMsg() {
        return returnMsg;
    }

    /**
     * @param returnMsg
     *            the returnMsg to set
     */
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    /**
     * 視訊API 判斷是否成功回傳，為returnCode末三碼 = "000"代表成功
     * 
     * @return
     */
    public boolean isSuccessCallBack() {
        return this.returnCode.endsWith("000");
    }
}
