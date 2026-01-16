package com.tfb.aibank.chl.general.ag004.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;
import com.tfb.aibank.chl.general.ag004.model.vo.FxCurrencyVo;

// @formatter:off
/**
 * @(#)NGNAG004011Rq.java
 * 
 * <p>Description:匯率設定 011 儲存action</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNAG004011Rq implements RqData {
    /** 欲儲存外幣資訊 */
    private List<FxCurrencyVo> currencyInHomeArea;

    /**
     * @return the currencyInHomeArea
     */
    public List<FxCurrencyVo> getCurrencyInHomeArea() {
        return currencyInHomeArea;
    }

    /**
     * @param currencyInHomeArea
     *            the currencyInHomeArea to set
     */
    public void setCurrencyInHomeArea(List<FxCurrencyVo> currencyInHomeArea) {
        this.currencyInHomeArea = currencyInHomeArea;
    }

}
