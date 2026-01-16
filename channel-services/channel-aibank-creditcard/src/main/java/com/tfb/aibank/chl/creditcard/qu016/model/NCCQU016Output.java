package com.tfb.aibank.chl.creditcard.qu016.model;

import com.tfb.aibank.chl.creditcard.resource.dto.AdditionalBenefit;

import java.util.List;

public class NCCQU016Output {

    // 優惠期間_起
    private String effectStartDate;
    // 優惠期間_迄
    private String effectEndDate;
    private List<AdditionalBenefit> dataList;

    // 是否顯示私銀資料
    private boolean showPrivateBanking;

    public String getEffectStartDate() {
        return effectStartDate;
    }

    public void setEffectStartDate(String effectStartDate) {
        this.effectStartDate = effectStartDate;
    }

    public String getEffectEndDate() {
        return effectEndDate;
    }

    public void setEffectEndDate(String effectEndDate) {
        this.effectEndDate = effectEndDate;
    }

    public List<AdditionalBenefit> getDataList() {
        return dataList;
    }

    public void setDataList(List<AdditionalBenefit> dataList) {
        this.dataList = dataList;
    }


    public boolean isShowPrivateBanking() {
        return showPrivateBanking;
    }

    public void setShowPrivateBanking(boolean showPrivateBanking) {
        this.showPrivateBanking = showPrivateBanking;
    }
}
