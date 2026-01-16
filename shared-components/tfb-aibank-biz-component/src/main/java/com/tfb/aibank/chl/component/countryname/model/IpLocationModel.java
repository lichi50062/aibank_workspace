package com.tfb.aibank.chl.component.countryname.model;

import java.util.Objects;

import com.ibm.tw.commons.util.IpUtils;

/**
 * @(#)IpLocationModel.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/22, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class IpLocationModel {
        
        private Long ipFrom;
        
        private Long ipTo;
        
        private String countryCode;
        
        private String countryName;
        
        private String source;
        
        private String queryIp;
        
        private boolean found;
        
        // 範圍查詢專用欄位
        private Long rangeSize;
        
        private String ipFromStr;
        
        private String ipToStr;

        
        /**
         * 預設建構子
         */
        public IpLocationModel() {}
        
        /**
         * 基本建構子
         */
        public IpLocationModel(Long ipFrom, Long ipTo, String countryCode, String countryName, 
                              String source, String queryIp, boolean found) {
            this.ipFrom = ipFrom;
            this.ipTo = ipTo;
            this.countryCode = countryCode;
            this.countryName = countryName;
            this.source = source;
            this.queryIp = queryIp;
            this.found = found;
        }
        

        

        
        public Long getIpFrom() {
            return ipFrom;
        }

        public void setIpFrom(Long ipFrom) {
            this.ipFrom = ipFrom;
        }

        public Long getIpTo() {
            return ipTo;
        }

        public void setIpTo(Long ipTo) {
            this.ipTo = ipTo;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getQueryIp() {
            return queryIp;
        }

        public void setQueryIp(String queryIp) {
            this.queryIp = queryIp;
        }

        public boolean isFound() {
            return found;
        }

        public void setFound(boolean found) {
            this.found = found;
        }

        public Long getRangeSize() {
            return rangeSize;
        }

        public void setRangeSize(Long rangeSize) {
            this.rangeSize = rangeSize;
        }

        public String getIpFromStr() {
            return ipFromStr;
        }

        public void setIpFromStr(String ipFromStr) {
            this.ipFromStr = ipFromStr;
        }

        public String getIpToStr() {
            return ipToStr;
        }

        public void setIpToStr(String ipToStr) {
            this.ipToStr = ipToStr;
        }
       
        
        // ==================== 實用方法 ====================
        
        /**
         * 計算IP範圍資訊
         */
        public void calculateRangeInfo() {
            if (found && ipFrom != null && ipTo != null) {
                this.rangeSize = ipTo - ipFrom + 1;
                try {
                    this.ipFromStr = IpUtils.longToIp(ipFrom);
                    this.ipToStr = IpUtils.longToIp(ipTo);
                } catch (Exception e) {
                    // 忽略轉換錯誤
                }
            }
        }
        
        /**
         * 檢查是否為有效的IP範圍
         */
        public boolean isValidRange() {
            return found && ipFrom != null && ipTo != null && ipFrom <= ipTo;
        }
        
        /**
         * 檢查查詢的IP是否在範圍內
         */
        public boolean isQueryIpInRange() {
            if (!found || ipFrom == null || ipTo == null) {
                return false;
            }
            
            try {
                Long queryIpNumber = IpUtils.ipToLong(queryIp);
                return queryIpNumber >= ipFrom && queryIpNumber <= ipTo;
            } catch (Exception e) {
                return false;
            }
        }
        
        /**
         * 取得來源描述
         */
        public String getSourceDescription() {
            if ("IP_LOCATION_A".equals(source)) {
                return "Primary IP Location Database";
            } else if ("IP_LOCATION_B".equals(source)) {
                return "Secondary IP Location Database";
            } else {
                return "Unknown Source";
            }
        }
        
        // ==================== Object Methods ====================
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IpLocationModel that = (IpLocationModel) o;
            return found == that.found &&
                   Objects.equals(ipFrom, that.ipFrom) &&
                   Objects.equals(ipTo, that.ipTo) &&
                   Objects.equals(queryIp, that.queryIp) &&
                   Objects.equals(source, that.source);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(ipFrom, ipTo, queryIp, source, found);
        }
        
        @Override
        public String toString() {
            return String.format("IpLocationModel{queryIp='%s', source='%s', found=%s, countryCode='%s', countryName='%s'}", 
                queryIp, source, found, countryCode, countryName);
        }
        
}
