package com.tfb.aibank.chl.creditcard.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecommendInfoRes {

    private String status;          // "200"
    private String message;         // "OK"
    private DataBody data;          // 主要資料
    private Object errorData;       // 若錯誤時才會有內容，成功通常為 null
    private String time;            // "2025-06-12T10:55:02.064515"
    private boolean success;        // true / false


    public static class DataBody {

        private String urlCode;     // "Z9FBTK15"
        private String forwardUrl;  // "https://.../curl/Z9FBTK15"
        private String product;     // "bp"

        private Recommender recommender;  // 推薦人資訊

        public String getUrlCode() {
            return urlCode;
        }

        public void setUrlCode(String urlCode) {
            this.urlCode = urlCode;
        }

        public Recommender getRecommender() {
            return recommender;
        }

        public void setRecommender(Recommender recommender) {
            this.recommender = recommender;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getForwardUrl() {
            return forwardUrl;
        }

        public void setForwardUrl(String forwardUrl) {
            this.forwardUrl = forwardUrl;
        }
    }

    public static class Recommender {

        /** 推薦人身分別 */
        private String type;

        /** 所屬營業單位 */
        private String bu;

        /** 員工編號，可能為 null */
        @JsonProperty("empNo")
        private String empNo;

        /** 身分證字號 */
        private String idNo;

        /** userCode，可能為 null */
        private String userCode;

        /** scCode，可能為 null */
        private String scCode;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEmpNo() {
            return empNo;
        }

        public void setEmpNo(String empNo) {
            this.empNo = empNo;
        }

        public String getBu() {
            return bu;
        }

        public void setBu(String bu) {
            this.bu = bu;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getScCode() {
            return scCode;
        }

        public void setScCode(String scCode) {
            this.scCode = scCode;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getErrorData() {
        return errorData;
    }

    public void setErrorData(Object errorData) {
        this.errorData = errorData;
    }

    public DataBody getData() {
        return data;
    }

    public void setData(DataBody data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
