package com.tfb.aibank.chl.component.fxtranscurrencydiscount;
// @formatter:off
import java.math.BigDecimal;
import java.util.Date; /**
 * @(#)FxTransCurrencyDiscountModel.java
 * 
 * <p>Description:[換匯優惠檔]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/9,
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FxTransCurrencyDiscount {

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 幣別名稱
     */
    private String currencyEname;

    /**
     * 說明
     */
    private String description;

    /**
     * 優惠
     */
    private BigDecimal discount;

    /**
     * 優惠代號
     */
    private String discountCode;

    /**
     * 語系
     */
    private String locale;

    /**
     * 等級
     */
    private String rank;

    /**
     * 專案優惠開始時間
     */
    private Date startDate;


    /**
     * 專案優惠結束時間
     */
    private Date endDate;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCurrencyEname() {
        return currencyEname;
    }

    public void setCurrencyEname(String currencyEname) {
        this.currencyEname = currencyEname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
