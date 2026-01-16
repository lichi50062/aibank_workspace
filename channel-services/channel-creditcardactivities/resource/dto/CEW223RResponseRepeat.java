package com.tfb.aibank.chl.creditcardactivities.resource.dto;

//@formatter:off
/**
 * @(#)CEW223RResponseRepeat.java
 * 
 * <p>Description:CEW223R 信用卡活動登錄/查詢 Response Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW223RResponseRepeat {

    /** 活動代碼 */
    private String uscde1;

    /** 活動名稱 */
    private String name;

    /** 登錄日期 */
    private String date;

    /** 登錄結果 */
    private String result;

    /**
     * @return the uscde1
     */
    public String getUscde1() {
        return uscde1;
    }

    /**
     * @param uscde1
     *            the uscde1 to set
     */
    public void setUscde1(String uscde1) {
        this.uscde1 = uscde1;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

}
