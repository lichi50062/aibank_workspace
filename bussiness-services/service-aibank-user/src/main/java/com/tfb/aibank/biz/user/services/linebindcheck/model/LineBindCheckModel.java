/**
 * 
 */
package com.tfb.aibank.biz.user.services.linebindcheck.model;

/**
 // @formatter:off
 * @(#)LineBindCheckModel.java
 *
 * <p>Description:[LineBindCheckModel]</p>
 *
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/1, Marty Pan
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class LineBindCheckModel {
    private String errorCode;
    private String errorMessage;
    private String isBind;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getIsBind() {
        return isBind;
    }

    public void setIsBind(String isBind) {
        this.isBind = isBind;
    }
}
