/**
 * 
 */
package com.tfb.aibank.component.accountinfoLoan;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)AccountInfoLoanName.java
* 
* <p>Description:貸款帳號名稱資訊檔</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/11/14, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AccountInfoLoanNameVo {

    /**
     * 顯示名稱
     */
    @Schema(description = "DISPLAY_TEXT")
    private String displayText;

    /**
     * 項目
     */
    @Schema(description = "ITEM")
    private String item;

    /**
     * 鍵值
     */
    @Schema(description = "ITEM_KEY")
    private Integer itemKey;

    /**
     * 項目值
     */
    @Schema(description = "ITEM_VALUE")
    private String itemValue;

    /**
     * 語系
     */
    @Schema(description = "LOCALE")
    private String locale;

    /**
     * @return the displayText
     */
    public String getDisplayText() {
        return displayText;
    }

    /**
     * @param displayText
     *            the displayText to set
     */
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item
     *            the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the itemKey
     */
    public Integer getItemKey() {
        return itemKey;
    }

    /**
     * @param itemKey
     *            the itemKey to set
     */
    public void setItemKey(Integer itemKey) {
        this.itemKey = itemKey;
    }

    /**
     * @return the itemValue
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * @param itemValue
     *            the itemValue to set
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
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
