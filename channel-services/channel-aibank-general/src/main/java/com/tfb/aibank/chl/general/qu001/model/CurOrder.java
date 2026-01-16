package com.tfb.aibank.chl.general.qu001.model;

import java.io.Serializable;
import java.util.List;

import com.tfb.aibank.chl.component.ratecurrency.RateCurrency;


// @formatter:off
/**
 * @(#)CurOrder.java
 *
 * <p>Description: CurOrder</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[幣別與順序]</li>
 * </ol>
 */
//@formatter:on
public class CurOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    public CurOrder(RateCurrency rateCurrency) {
        this.curCode = rateCurrency.getCurrencyEname1();
        this.seq = rateCurrency.getCurrencySort();
    }

    public CurOrder() {
    }


    /**
     * */
    private String curCode;
    /**
     * */
    private int seq;

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
