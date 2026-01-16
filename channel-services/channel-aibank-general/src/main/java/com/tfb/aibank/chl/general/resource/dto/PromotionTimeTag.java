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

// @formatter:off
/**
 * @(#)Promotion.java
 * 
 * <p>Description:[優惠活動]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PromotionTimeTag {
    
    private String tagLabel;
    
    private String tagClass;

    
    
    public PromotionTimeTag(String tagLabel, String tagClass) {
        super();
        this.tagLabel = tagLabel;
        this.tagClass = tagClass;
    }

    public String getTagLabel() {
        return tagLabel;
    }

    public void setTagLabel(String tagLabel) {
        this.tagLabel = tagLabel;
    }

    public String getTagClass() {
        return tagClass;
    }

    public void setTagClass(String tagClass) {
        this.tagClass = tagClass;
    }
    
    
    
}
