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
package com.tfb.aibank.chl.general.resource.vo.faq;

import java.util.List;

// @formatter:off
/**
 * @(#)GuideBizVo.java
 * 
 * <p>Description:操作教學業務類別 (Duplicate From Channel Preference)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan Wang (Duplicate From Channel Preference)
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GuideBizVo {
    /** 交易類別資料 */
    private List<GuideTypeVo> guideTypeDatas;

    /** 業務類別 */
    private String bizName;

    /**
     * @return the guideTypeDatas
     */
    public List<GuideTypeVo> getGuideTypeDatas() {
        return guideTypeDatas;
    }

    /**
     * @param guideTypeDatas
     *            the guideTypeDatas to set
     */
    public void setGuideTypeDatas(List<GuideTypeVo> guideTypeDatas) {
        this.guideTypeDatas = guideTypeDatas;
    }

    /**
     * @return the bizName
     */
    public String getBizName() {
        return bizName;
    }

    /**
     * @param bizName
     *            the bizName to set
     */
    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

}
