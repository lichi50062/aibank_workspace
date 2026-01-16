package com.tfb.aibank.chl.general.ag004.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.general.ag004.model.vo.FxCurrencyVo;

// @formatter:off
/**
 * @(#)NGNAG004010Rs.java
 * 
 * <p>Description:匯率設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNAG004010Rs implements RsData {

    /** 優惠資訊 */
    private List<FxCurrencyVo> fxCurrencys;

    /**
     * @return the fxCurrencys
     */
    public List<FxCurrencyVo> getFxCurrencys() {
        return fxCurrencys;
    }

    /**
     * @param fxCurrencys
     *            the fxCurrencys to set
     */
    public void setFxCurrencys(List<FxCurrencyVo> fxCurrencys) {
        this.fxCurrencys = fxCurrencys;
    }

}
