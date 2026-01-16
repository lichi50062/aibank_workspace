package com.tfb.aibank.chl.general.resource.dto;

import java.util.ArrayList;
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

    /**
     * 傳送序號 X(5)
     */
    private String spRefId;
    /**
     * 格式代號 X(4)
     */
    private String formatId;
    /**
     * 資料筆數 X(2)
     */
    private String occur;

    /**
     * A010Repeats
     */
    private List<CEW314RA010Repeat> a010Repeats;

    private List<CEW314RA021Repeat> a021Repeats;

    /**
     * @return the spRefId
     */
    public String getSpRefId() {
        return spRefId;
    }

    /**
     * @param spRefId the spRefId to set
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
     * @param formatId the formatId to set
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
     * @param occur the occur to set
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
     * @param a010Repeats the a010Repeats to set
     */
    public void setA010Repeats(List<CEW314RA010Repeat> a010Repeats) {
        this.a010Repeats = a010Repeats;
    }

    public List<CEW314RA021Repeat> getA021Repeats() {
        return a021Repeats;
    }

    public void setA021Repeats(List<CEW314RA021Repeat> a021Repeats) {
        this.a021Repeats = a021Repeats;
    }
}
