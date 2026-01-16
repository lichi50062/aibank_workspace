/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.io.Serializable;
import java.util.Date;

//@formatter:off
/**
* @(#)PromotionClickRecord.java
* 
* <p>Description:[熱門優惠活動點選次數]</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/02,
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class PromotionClickRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 優惠鍵值 */
    private String promotionKey;

    /** 點擊次數 */
    private Integer click;

    /* 更新時間 */
    private Date updateTime;

    public String getPromotionKey() {
        return promotionKey;
    }

    public void setPromotionKey(String promotionKey) {
        this.promotionKey = promotionKey;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
