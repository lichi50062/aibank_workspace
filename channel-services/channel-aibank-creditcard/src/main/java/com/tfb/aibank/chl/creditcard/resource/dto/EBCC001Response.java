package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
 * @(#)EBCC001Response.java
 * 
 * <p>Description:固定調額啟案檢核電文下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Edward Tien    
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class EBCC001Response {

    /**
     * 錯誤代碼 01~09，若有多個則會用”;”分隔.
     */
    private String emsgId;

    /**
     * 錯誤代碼中文說明.
     */
    private String emsgTxt;

    /**
     * Gets emsg id.
     *
     * @return the emsg id
     */
    public String getEmsgId() {
        return emsgId;
    }

    /**
     * Sets emsg id.
     *
     * @param emsgId
     *            the emsg id
     */
    public void setEmsgId(String emsgId) {
        this.emsgId = emsgId;
    }

    /**
     * Gets emsg txt.
     *
     * @return the emsg txt
     */
    public String getEmsgTxt() {
        return emsgTxt;
    }

    /**
     * Sets emsg txt.
     *
     * @param emsgTxt
     *            the emsg txt
     */
    public void setEmsgTxt(String emsgTxt) {
        this.emsgTxt = emsgTxt;
    }
}
