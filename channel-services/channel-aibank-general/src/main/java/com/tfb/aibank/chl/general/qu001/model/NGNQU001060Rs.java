package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.ibmb.base.model.RsData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @(#)NGNQU001060Rs.java
 *
 * <p>Description: 首頁-存款利率區塊</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyP
 *  <li>存款利率區塊</li>
 * </ol>
 */
@Component
public class NGNQU001060Rs implements RsData {

    /** 外幣優惠利率 */
    private List<FxInterestRateVo> fxInterestRates;

    public List<FxInterestRateVo> getFxInterestRates() {
        return fxInterestRates;
    }

    public void setFxInterestRates(List<FxInterestRateVo> fxInterestRates) {
        this.fxInterestRates = fxInterestRates;
    }
}
