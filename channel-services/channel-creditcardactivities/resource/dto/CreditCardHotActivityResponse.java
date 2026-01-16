package com.tfb.aibank.chl.creditcardactivities.resource.dto;

import java.util.List;

//@formatter:off
/**
 * @(#)CreditCardHotActivityResponse.java
 * 
 * <p>Description:Credit_Card_Hot_Activity 信用卡熱門活動查詢 Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/08/15, Lichi Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CreditCardHotActivityResponse {

    /** 活動清單 */
    private List<CreditCardHotActivityResponseRepeat> activity;

    /**
     * @return the activity
     */
    public List<CreditCardHotActivityResponseRepeat> getActivity() {
        return activity;
    }

    /**
     * @param activity
     *            the activity to set
     */
    public void setActivity(List<CreditCardHotActivityResponseRepeat> activity) {
        this.activity = activity;
    }

}