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

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB5557891D005Rep.java
 * 
 * <p>Description:歸戶帳號電文(EB5557891)下行欄位 D005 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "歸戶帳號電文(EB5557891)下行欄位 D005 Repeat")
public class EB5557891D005Rep implements Serializable {

    private static final long serialVersionUID = 4028762075030827181L;

    /** 限約定轉入帳號註記 */
    @Schema(description = "限約定轉入帳號註記")
    private String acnoInFlag;

    /** 外幣功能註記 */
    @Schema(description = "外幣功能註記")
    private String exFlg;

    /** 數位存款視訊註記 */
    @Schema(description = "數位存款視訊註記")
    private String videoFlg;

    public String getAcnoInFlag() {
        return acnoInFlag;
    }

    public void setAcnoInFlag(String acnoInFlag) {
        this.acnoInFlag = acnoInFlag;
    }

    public String getExFlg() {
        return exFlg;
    }

    public void setExFlg(String exFlg) {
        this.exFlg = exFlg;
    }

    public String getVideoFlg() {
        return videoFlg;
    }

    public void setVideoFlg(String videoFlg) {
        this.videoFlg = videoFlg;
    }

}
