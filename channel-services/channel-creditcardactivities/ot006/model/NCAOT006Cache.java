package com.tfb.aibank.chl.creditcardactivities.ot006.model;

import java.util.List;

//@formatter:off
/**
 * @(#)NCCOT006CacheModel.java
 * 
 * <p>Description:信用卡活動登錄 Cache Data</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class NCAOT006Cache {

    public NCAOT006Cache(List<NCAOT006Activity> activity) {
        this.activity = activity;
    }

    /** 所有活動 */
    private List<NCAOT006Activity> activity;

    /**
     * @return the activity
     */
    public List<NCAOT006Activity> getActivity() {
        return activity;
    }

    /**
     * @param activity
     *            the activity to set
     */
    public void setActivity(List<NCAOT006Activity> activity) {
        this.activity = activity;
    }

}
