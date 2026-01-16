package com.tfb.aibank.chl.creditcard.qu014.model;

// @formatter:off
import java.util.ArrayList;import java.util.List; /**
 * @(#)NCCQU014Output.java
 * 
 * <p>Description:輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU014Output {

    private List<RateInfoGroup> infoGroups;

    private String infoCreateTime;

    public List<RateInfoGroup> getInfoGroups() {
        return infoGroups;
    }

    public void setInfoGroups(List<RateInfoGroup> infoGroups) {
        this.infoGroups = infoGroups;
    }

    public String getInfoCreateTime() {
        return infoCreateTime;
    }

    public void setInfoCreateTime(String infoCreateTime) {
        this.infoCreateTime = infoCreateTime;
    }
}
