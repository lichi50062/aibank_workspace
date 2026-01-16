package com.ibm.tw.ibmb.component.i18n;

// @formatter:off
/**
 * @(#)I18nModel.java
 * 
 * <p>Description:AIBank 多語系檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class I18nModel {

    /** 分類 */
    private String category;

    /** 鍵值 */
    private String key;

    /** 參數值 */
    private String value;

    /** 順序 */
    private String locale;

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

}