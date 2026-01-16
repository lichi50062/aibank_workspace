package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)CEW346RResponse.java
 * 
 * <p>Description:CEW346R 保費權益設定-變更 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/02/10, leiping	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW346RResponse {
    /** 卡號 */
    private String crdno;

    /** 五萬以上保費權益 */
    private String insuTypea;
    
    /** 未達五萬保費權益 */
    private String insuTypeb;

    /** 通路別 */
    private String channel;

    public String getCrdno() {
        return crdno;
    }

    public void setCrdno(String crdno) {
        this.crdno = crdno;
    }

    public String getInsuTypea() {
        return insuTypea;
    }

    public void setInsuTypea(String insuTypea) {
        this.insuTypea = insuTypea;
    }
    
    public String getInsuTypeb() {
        return insuTypeb;
    }

    public void setInsuTypeb(String insuTypeb) {
        this.insuTypeb = insuTypeb;
    }
    
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}
