package com.tfb.aibank.chl.creditcard.ag002.model;

import java.util.Map;

import com.tfb.aibank.chl.creditcard.resource.dto.CEW302RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CardEmailBillModel;
import com.tfb.aibank.chl.creditcard.resource.dto.RecommendInfo;

public class NCCAG002DataOutput {

    /** 信用卡卡片總覽電文下行 */
    private CEW302RRes cew302RResponse;

    private boolean send213RSuccess;

    private Map<String, String> params;

    private CardEmailBillModel cancelResultModel;

    private RecommendInfo recommendInfo;

    public CEW302RRes getCew302RResponse() {
        return cew302RResponse;
    }

    public void setCew302RResponse(CEW302RRes cew302rResponse) {
        cew302RResponse = cew302rResponse;
    }

    public boolean isSend213RSuccess() {
        return send213RSuccess;
    }

    public void setSend213RSuccess(boolean send213rSuccess) {
        send213RSuccess = send213rSuccess;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public CardEmailBillModel getCancelResultModel() {
        return cancelResultModel;
    }

    public void setCancelResultModel(CardEmailBillModel cancelResultModel) {
        this.cancelResultModel = cancelResultModel;
    }

    public RecommendInfo getRecommendInfo() {
        return recommendInfo;
    }

    public void setRecommendInfo(RecommendInfo recommendInfo) {
        this.recommendInfo = recommendInfo;
    }
}
