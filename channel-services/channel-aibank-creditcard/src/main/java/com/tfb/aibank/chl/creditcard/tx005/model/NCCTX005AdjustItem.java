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
package com.tfb.aibank.chl.creditcard.tx005.model;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)NCCTX005AdjustItem.java
 * 
 * <p>Description:調整項目</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCTX005AdjustItem {

    public NCCTX005AdjustItem() {
        // default constructor
    }

    public NCCTX005AdjustItem(AdjustItemType item) {
        this.item = item;
    }

    /** 調整項目 */
    private AdjustItemType item;

    /** 錯誤代碼 01~09，若有多個則會用”;”分隔. */
    private String emsgId;

    /** 錯誤代碼中文說明 */
    private String emsgTxt;

    /** 錯誤代碼中文說明(固定) */
    private String emsgDesc = I18NUtils.getMessage("ncctx005.adjust_item.disable_msg");

    /** 是否 Disabled */
    private boolean isDisabled;

    public AdjustItemType getItem() {
        return item;
    }

    public void setItem(AdjustItemType item) {
        this.item = item;
    }

    public String getEmsgId() {
        return emsgId;
    }

    public void setEmsgId(String emsgId) {
        this.emsgId = emsgId;
    }

    public String getEmsgTxt() {
        return emsgTxt;
    }

    public void setEmsgTxt(String emsgTxt) {
        this.emsgTxt = emsgTxt;
        this.isDisabled = StringUtils.isNotBlank(emsgTxt);
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public String getEmsgDesc() {
        return emsgDesc;
    }

    public void setEmsgDesc(String emsgDesc) {
        this.emsgDesc = emsgDesc;
    }

}
