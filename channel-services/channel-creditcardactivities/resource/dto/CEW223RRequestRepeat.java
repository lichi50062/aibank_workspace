package com.tfb.aibank.chl.creditcardactivities.resource.dto;

//@formatter:off
/**
 * @(#)CEW223RRequestRepeat.java
 * 
 * <p>Description:CEW223R 信用卡活動登錄/查詢 Request Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW223RRequestRepeat {

    /** 活動代碼 */
    private String usced1;

    /** 勾選結果 */
    private String select;

    /**
     * @return the usced1
     */
    public String getUsced1() {
        return usced1;
    }

    /**
     * @param usced1
     *            the usced1 to set
     */
    public void setUsced1(String usced1) {
        this.usced1 = usced1;
    }

    /**
     * @return the select
     */
    public String getSelect() {
        return select;
    }

    /**
     * @param select
     *            the select to set
     */
    public void setSelect(String select) {
        this.select = select;
    }

}
