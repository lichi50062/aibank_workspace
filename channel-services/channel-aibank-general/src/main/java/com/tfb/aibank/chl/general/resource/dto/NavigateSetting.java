package com.tfb.aibank.chl.general.resource.dto;

/**
 * // @formatter:off
/**
 * @(#)NavigateSetting.java
 * 
 * <p>Description:導頁設定內容</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/30, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
 
public class NavigateSetting {

    /**導頁來源  */
    private String from;
    
    /**導到指定種類,  1 (sso外開)，2(一般外開)  */
    private String type;
    
    /**導到指定交易代號或SSO ChanneKeyl*/
    private String target;
    
    /**導到頁的額外參數:  */
    private String param;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "NavigateSetting [from=" + from + ", type=" + type + ", target=" + target + ", param=" + param + "]";
    }

}
