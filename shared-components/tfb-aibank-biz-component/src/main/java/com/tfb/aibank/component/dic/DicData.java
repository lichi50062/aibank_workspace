package com.tfb.aibank.component.dic;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)DicData.java
 * 
 * <p>Description:DIC 資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "DIC 資料")
public class DicData {

    /** 分類 */
    @Schema(description = "分類")
    private String category;

    /** 說明 */
    @Schema(description = "說明")
    private String memo;

    /** 鍵值 */
    @Schema(description = "鍵值")
    private String dicKey;

    /** 參數值 */
    @Schema(description = "參數值")
    private String dicValue;

    /** 順序 */
    @Schema(description = "順序")
    private int indexNo;

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
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the dicKey
     */
    public String getDicKey() {
        return dicKey;
    }

    /**
     * @param dicKey
     *            the dicKey to set
     */
    public void setDicKey(String dicKey) {
        this.dicKey = dicKey;
    }

    /**
     * @return the dicValue
     */
    public String getDicValue() {
        return dicValue;
    }

    /**
     * @param dicValue
     *            the dicValue to set
     */
    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    /**
     * @return the indexNo
     */
    public int getIndexNo() {
        return indexNo;
    }

    /**
     * @param indexNo
     *            the indexNo to set
     */
    public void setIndexNo(int indexNo) {
        this.indexNo = indexNo;
    }

}