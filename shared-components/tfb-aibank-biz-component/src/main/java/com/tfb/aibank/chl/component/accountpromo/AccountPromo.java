package com.tfb.aibank.chl.component.accountpromo;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.chl.component.homepagecard.CardEvent;

public class AccountPromo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主檔鍵值
     */
    private Integer accountPromoKey;

    /**
     * 牌卡內容
     */
    private String description;

    /**
     * 牌卡標語
     */
    private String slogan;

    /**
     * 牌卡連結
     */
    private String url;

    /**
     * 牌卡推薦帳戶類型=>1：無臺幣活存 2：無臺幣定存3：無外幣活存4：無外幣定存5：無臺幣帳戶6：無外幣帳戶7：月月存
     */
    private String depositType;

    /**
     * 月月存利息加碼
     */
    private Integer rateBonus;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 語系
     */
    private String locale;

    /**
     * 有加入HomepageCard資料時，CARD_DESC可以放的位置
     */
    private String homecardDesc;

    /** HOMEPAGE_CARD 資料 */
    private CardEvent cardevent;

    /** DB.ACCOUNT_PROMO 無對應資料 */
    private boolean noDbData;

    public Integer getAccountPromoKey() {
        return accountPromoKey;
    }

    public void setAccountPromoKey(Integer accountPromoKey) {
        this.accountPromoKey = accountPromoKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public Integer getRateBonus() {
        return rateBonus;
    }

    public void setRateBonus(Integer rateBonus) {
        this.rateBonus = rateBonus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getHomecardDesc() {
        return homecardDesc;
    }

    public void setHomecardDesc(String homecardDesc) {
        this.homecardDesc = homecardDesc;
    }

    public CardEvent getCardevent() {
        return cardevent;
    }

    public void setCardevent(CardEvent cardevent) {
        this.cardevent = cardevent;
    }

    public boolean isNoDbData() {
        return noDbData;
    }

    public void setNoDbData(boolean noDbData) {
        this.noDbData = noDbData;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
