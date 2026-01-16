/**
 * 
 */
package com.tfb.aibank.chl.component.paytype;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
public class PayItems {

    /**
     * 委託單位代號
     */
    @Schema(description = "委託單位代號")
    private String agencyCode;

    /**
     * 項目 繳費 01：中華電信公司電信費 02：台北市水費 03：台灣電力電費 04：陽明山瓦斯費 06：勞工保險費 07：台灣省/高雄市水費 08：勞工退休金 11：台灣大哥大電信費 12：台灣大哥大預付卡 13：台灣大哥大企業通路費 14：遠傳電信費 15：學費 16：富邦人壽 17：富邦產險 繳稅 A：汽機車使用牌照稅 B：房屋稅 C：營業稅 D：地價稅 E：綜合所得稅 F：營利事業所得稅
     */
    @Schema(description = "項目")
    private String itemCode;

    /**
     * 項目名稱
     */
    @Schema(description = "項目名稱")
    private String itemName;

    /**
     * 順序
     */
    @Schema(description = "順序")
    private Integer itemSort;

    /**
     * 語系
     */
    @Schema(description = "語系")
    private String locale;

    /**
     * 繳款類別 0：繳費 1：繳稅
     */
    @Schema(description = "繳款類別 0：繳費 1：繳稅")
    private String payType;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 更新時間
     */
    @Schema(description = "更新時間")
    private Date updateTime;

    /**
     * @return the agencyCode
     */
    public String getAgencyCode() {
        return agencyCode;
    }

    /**
     * @param agencyCode
     *            the agencyCode to set
     */
    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
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
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName
     *            the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the itemSort
     */
    public Integer getItemSort() {
        return itemSort;
    }

    /**
     * @param itemSort
     *            the itemSort to set
     */
    public void setItemSort(Integer itemSort) {
        this.itemSort = itemSort;
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
     * @return the payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType
     *            the payType to set
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

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
