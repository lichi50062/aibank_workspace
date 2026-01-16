package com.tfb.aibank.chl.creditcardactivities.resource.dto;

import java.util.List;

//@formatter:off
/**
 * @(#)CreditCardActivityResponse.java
 *
 * <p>Description:Credit_Card_Activity 信用卡活動查詢 Response</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/010/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CreditCardActivityResponse {

    /** 活動清單 */
    private List<CreditCardActivityResponseRepeat> activity;

    /**
     * @return the activity
     */
    public List<CreditCardActivityResponseRepeat> getActivity() {
        return activity;
    }

    /**
     * @param activity
     *            the activity to set
     */
    public void setActivity(List<CreditCardActivityResponseRepeat> activity) {
        this.activity = activity;
    }

}
