package com.tfb.aibank.chl.creditcard.qu008.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU008040Rs.java
 * 
 * <p>Description:信用卡分期管理 040 方案選項頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008041Rs implements RsData {

    /** 其他分期方案 */
    private List<NCCQU008InsInterestSection> otherInsInterestSection = new ArrayList<>();

    public List<NCCQU008InsInterestSection> getOtherInsInterestSection() {
        return otherInsInterestSection;
    }

    public void setOtherInsInterestSection(List<NCCQU008InsInterestSection> otherInsInterestSection) {
        this.otherInsInterestSection = otherInsInterestSection;
    }

}
