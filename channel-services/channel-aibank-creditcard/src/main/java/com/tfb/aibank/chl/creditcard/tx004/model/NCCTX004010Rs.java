package com.tfb.aibank.chl.creditcard.tx004.model;

import com.ibm.tw.ibmb.base.model.RsData;
import org.springframework.stereotype.Component;

import java.util.List;

//@formatter:off
/**
 * @(#)NCCTX004010Rs.java
 *
 * <p>Description:NCCTX004_道路救援登錄 RS</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/24
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Component
public class NCCTX004010Rs implements RsData {

    private List<CreditCardData> creditCardDatas;

    /**
     * 有正卡身份
     * */
    private boolean primaryCardHolder;

    public List<CreditCardData> getCreditCardDatas() {
        return creditCardDatas;
    }

    public void setCreditCardDatas(List<CreditCardData> creditCardDatas) {
        this.creditCardDatas = creditCardDatas;
    }

    public boolean isPrimaryCardHolder() {
        return primaryCardHolder;
    }

    public void setPrimaryCardHolder(boolean primaryCardHolder) {
        this.primaryCardHolder = primaryCardHolder;
    }
}
