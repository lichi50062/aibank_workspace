package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.List;

//@formatter:off
/**
* @(#)CEW314RResponse.java
* 
* <p>Description:CEW314R 正卡:帳單明細查詢 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW314RResponse {

    /** 傳送序號 X(5) */
    private String spRefId;
    /** 格式代號 X(4) */
    private String formatId;
    /** 資料筆數 X(2) */
    private String occur;

    /** A010Repeats */
    private List<CEW314RA010Repeat> a010Repeats;
    /** A021Repeats */
    private List<CEW314RA021Repeat> a021Repeats;
    /** B060Repeats */
    private List<CEW314RB060Repeat> b060Repeats;
    /** B062Repeats */
    private List<CEW314RB062Repeat> b062Repeats;
    /** B100Repeats */
    private List<CEW314RB100Repeat> b100Repeats;
    /** B500Repeats */
    private List<CEW314RB500Repeat> b500Repeats;

    /**
     * @return the spRefId
     */
    public String getSpRefId() {
        return spRefId;
    }

    /**
     * @param spRefId
     *            the spRefId to set
     */
    public void setSpRefId(String spRefId) {
        this.spRefId = spRefId;
    }

    /**
     * @return the formatId
     */
    public String getFormatId() {
        return formatId;
    }

    /**
     * @param formatId
     *            the formatId to set
     */
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

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
     * @return the a010Repeats
     */
    public List<CEW314RA010Repeat> getA010Repeats() {
        return a010Repeats;
    }

    /**
     * @param a010Repeats
     *            the a010Repeats to set
     */
    public void setA010Repeats(List<CEW314RA010Repeat> a010Repeats) {
        this.a010Repeats = a010Repeats;
    }

    /**
     * @return the a021Repeats
     */
    public List<CEW314RA021Repeat> getA021Repeats() {
        return a021Repeats;
    }

    /**
     * @param a021Repeats
     *            the a021Repeats to set
     */
    public void setA021Repeats(List<CEW314RA021Repeat> a021Repeats) {
        this.a021Repeats = a021Repeats;
    }

    /**
     * @return the b060Repeats
     */
    public List<CEW314RB060Repeat> getB060Repeats() {
        return b060Repeats;
    }

    /**
     * @param b060Repeats
     *            the b060Repeats to set
     */
    public void setB060Repeats(List<CEW314RB060Repeat> b060Repeats) {
        this.b060Repeats = b060Repeats;
    }

    /**
     * @return the b062Repeats
     */
    public List<CEW314RB062Repeat> getB062Repeats() {
        return b062Repeats;
    }

    /**
     * @param b062Repeats
     *            the b062Repeats to set
     */
    public void setB062Repeats(List<CEW314RB062Repeat> b062Repeats) {
        this.b062Repeats = b062Repeats;
    }

    /**
     * @return the b100Repeats
     */
    public List<CEW314RB100Repeat> getB100Repeats() {
        return b100Repeats;
    }

    /**
     * @param b100Repeats
     *            the b100Repeats to set
     */
    public void setB100Repeats(List<CEW314RB100Repeat> b100Repeats) {
        this.b100Repeats = b100Repeats;
    }

    /**
     * @return the b500Repeats
     */
    public List<CEW314RB500Repeat> getB500Repeats() {
        return b500Repeats;
    }

    /**
     * @param b500Repeats
     *            the b500Repeats to set
     */
    public void setB500Repeats(List<CEW314RB500Repeat> b500Repeats) {
        this.b500Repeats = b500Repeats;
    }


}
