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
package com.tfb.aibank.biz.component.cardpromote.model;

import java.util.List;
// @formatter:off
/**
 * @(#)DataBean.java
 *
 * <p>Description:信用卡優惠網</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DataBean {
    // 活動清單
    private List<ActivityGroupBean> activityGroup;

    public List<ActivityGroupBean> getActivityGroup() {
        return activityGroup;
    }

    public void setActivityGroup(List<ActivityGroupBean> activityGroup) {
        this.activityGroup = activityGroup;
    }

}
