/**
 * 
 */
package com.tfb.aibank.chl.component.taxitemtype;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
public class TaxItemsType {

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 繳稅項目
     */
    @Schema(description = "繳稅項目")
    private String itemCode;

    /**
     * 語系
     */
    @Schema(description = "語系")
    private String locale;

    /**
     * 繳稅繳款類別
     */
    @Schema(description = "繳稅繳款類別")
    private String typeCode;

    /**
     * 繳稅繳款類別名稱
     */
    @Schema(description = "繳稅繳款類別名稱")
    private String typeName;

    /**
     * 順序
     */
    @Schema(description = "順序")
    private Integer typeSort;

    /**
     * 更新時間
     */
    @Schema(description = "更新時間")
    private Date updateTime;

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode
     *            the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    /**
     * @return the typeCode
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * @param typeCode
     *            the typeCode to set
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName
     *            the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return the typeSort
     */
    public Integer getTypeSort() {
        return typeSort;
    }

    /**
     * @param typeSort
     *            the typeSort to set
     */
    public void setTypeSort(Integer typeSort) {
        this.typeSort = typeSort;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
