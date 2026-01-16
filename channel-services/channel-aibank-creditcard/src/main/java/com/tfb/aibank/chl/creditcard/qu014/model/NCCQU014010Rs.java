package com.tfb.aibank.chl.creditcard.qu014.model;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001Bill;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001CardData;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW306RRepeat;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//@formatter:off
/**
 * @(#)NCCQU014010Rs.java
 *
 * <p>Description:信用卡循環利率查詢 RS</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/24
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Component
public class NCCQU014010Rs implements RsData {

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
