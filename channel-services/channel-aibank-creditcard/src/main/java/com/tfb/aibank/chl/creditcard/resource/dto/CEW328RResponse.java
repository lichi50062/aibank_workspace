package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)CEW328RResponse.java
 * 
 * <p>Description:CEW328R 保費權益設定-變更 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW328RResponse {
    /** 卡號 */
    private String crdno;

    /** 保費權益 */
    private String insuType;

    /** 通路別 */
    private String channel;

    public String getCrdno() {
        return crdno;
    }

    public void setCrdno(String crdno) {
        this.crdno = crdno;
    }

    public String getInsuType() {
        return insuType;
    }

    public void setInsuType(String insuType) {
        this.insuType = insuType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}
