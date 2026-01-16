package com.tfb.aibank.chl.creditcard.qu009.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU009010Rs.java
 * 
 * <p>Description:簽帳金融卡消費明細 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/18, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU009010Rs implements RsData {

    /** 帳號資訊 */
    private List<NCCQU009AccModel> accountDatas;

    /** 明細 tab 顯示資料 (最新&月份) */
    private List<NCCQU009TabModel> tabDatas;

    /** 簽帳卡資料 */
    private List<NCCQU009DebitCardModel> debitCardModel;

    /**
     * @return the accountDatas
     */
    public List<NCCQU009AccModel> getAccountDatas() {
        return accountDatas;
    }

    /**
     * @param accountDatas
     *            the accountDatas to set
     */
    public void setAccountDatas(List<NCCQU009AccModel> accountDatas) {
        this.accountDatas = accountDatas;
    }

    /**
     * @return the tabDatas
     */
    public List<NCCQU009TabModel> getTabDatas() {
        return tabDatas;
    }

    /**
     * @param tabDatas
     *            the tabDatas to set
     */
    public void setTabDatas(List<NCCQU009TabModel> tabDatas) {
        this.tabDatas = tabDatas;
    }

    /**
     * @return the debitCardModel
     */
    public List<NCCQU009DebitCardModel> getDebitCardModel() {
        return debitCardModel;
    }

    /**
     * @param debitCardModel
     *            the debitCardModel to set
     */
    public void setDebitCardModel(List<NCCQU009DebitCardModel> debitCardModel) {
        this.debitCardModel = debitCardModel;
    }

}
