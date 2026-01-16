package com.tfb.aibank.chl.creditcard.tx004.model;

// @formatter:off
import com.tfb.aibank.chl.creditcard.resource.dto.CardCarnoApply;import java.util.List; /**
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
public class NCCTX004Output {

    private List<CreditCardData> creditCardDatas;

    /**
     * 有正卡身份
     * */
    private boolean primaryCardHolder;

    /**
     * 執行設定時於DB中紀錄的資料
     */
    private CardCarnoApply applyData;

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

    public CardCarnoApply getApplyData() {
        return applyData;
    }

    public void setApplyData(CardCarnoApply applyData) {
        this.applyData = applyData;
    }
}
