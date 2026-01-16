package com.tfb.aibank.chl.creditcardactivities.resource.dto;

import java.util.List;

//@formatter:off
/**
 * @(#)CEW223RResponse.java
 * 
 * <p>Description:CEW223R 信用卡活動登錄/查詢 Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW223RResponse {

    /** 筆數 */
    private String occur;

    /** 活動代碼清單 */
    private List<CEW223RResponseRepeat> repeat;

    /**
     * @return the occur
     */
    public String getOccur() {
        return occur;
    }

    /**
     * @param occur
     *            the occur to set
     */
    public void setOccur(String occur) {
        this.occur = occur;
    }

    /**
     * @return the repeat
     */
    public List<CEW223RResponseRepeat> getRepeat() {
        return repeat;
    }

    /**
     * @param repeat
     *            the repeat to set
     */
    public void setRepeat(List<CEW223RResponseRepeat> repeat) {
        this.repeat = repeat;
    }

}
