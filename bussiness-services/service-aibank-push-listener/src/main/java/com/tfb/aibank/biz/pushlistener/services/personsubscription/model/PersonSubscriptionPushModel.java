/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.pushlistener.services.personsubscription.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

// @formatter:off
/**
 * @(#)PersonSubscriptionPushModel.java
 * 
 * <p>Description:個人化訂閱檔案內容</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PersonSubscriptionPushModel {

    /** 回傳資料 */
    private List<Result> result = new ArrayList<>();

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result {
        /** id/身分證字號(含誤別碼) */
        private String id;
        /** userTag/使用者資訊 */
        private UserTag userTag;
        /** 情境資料 */
        private List<Offer> offerList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public UserTag getUserTag() {
            return userTag;
        }

        public void setUserTag(UserTag userTag) {
            this.userTag = userTag;
        }

        public List<Offer> getOfferList() {
            return offerList;
        }

        public void setOfferList(List<Offer> offerList) {
            this.offerList = offerList;
        }
    }

    // 2024/01/31 屬性名稱依照取得範例檔定義
    public static class UserTag {

        @SerializedName("t_p_01")
        private List<String> tp01;
        @SerializedName("t_f_01")
        private List<String> tf01;

        /**
         * @return the tp01
         */
        public List<String> getTp01() {
            return tp01;
        }

        /**
         * @param tp01
         *            the tp01 to set
         */
        public void setTp01(List<String> tp01) {
            this.tp01 = tp01;
        }

        /**
         * @return the tf01
         */
        public List<String> getTf01() {
            return tf01;
        }

        /**
         * @param tf01
         *            the tf01 to set
         */
        public void setTf01(List<String> tf01) {
            this.tf01 = tf01;
        }

    }

    public static class Offer {

        /** 必要發送註記 */
        private String offerPolicy;

        /** 情境排序 */
        private String offerRanking;

        /** 情境分數 */
        private String offerScore;

        /** 情境代碼 */
        private String offerId;

        /**
         * { "subofferId": "", "subofferRanking": 0, "subofferScore": 0, "MARATHON": "\u53f0\u7063\u7c73\u5009\u7530\u4e2d\u99ac\u62c9\u677e-3", "REGISTRATION_DATE": "2023/6/7", "MARATHON_DATE": "2023/11/12", "MARATHON_URL":
         * "https://www.facebook.com/520marathon" }
         */
        private JsonObject offerInfo;

        public String getOfferPolicy() {
            return offerPolicy;
        }

        public void setOfferPolicy(String offerPolicy) {
            this.offerPolicy = offerPolicy;
        }

        public String getOfferRanking() {
            return offerRanking;
        }

        public void setOfferRanking(String offerRanking) {
            this.offerRanking = offerRanking;
        }

        public String getOfferScore() {
            return offerScore;
        }

        public void setOfferScore(String offerScore) {
            this.offerScore = offerScore;
        }

        public String getOfferId() {
            return offerId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }

        public JsonObject getOfferInfo() {
            return offerInfo;
        }

        public void setOfferInfo(JsonObject offerInfo) {
            this.offerInfo = offerInfo;
        }

    }
}