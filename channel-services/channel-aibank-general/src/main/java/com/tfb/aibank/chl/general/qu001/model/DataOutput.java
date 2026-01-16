package com.tfb.aibank.chl.general.qu001.model;

import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.component.newfunctionintro.NewFunctionIntro;
import com.tfb.aibank.chl.general.resource.dto.QuickSearchResponse;
import com.tfb.aibank.chl.general.resource.dto.RespectInfo;

import java.util.List;
// @formatter:off
/**
 * @(#)DataOutput.java
 *
 * <p>Description: DataInput</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[NGNQU001 中介物件]</li>
 * </ol>
 */
//@formatter:on
public class DataOutput {


    private Integer unreadCount;

    private RespectInfo respectInfo;

    /** 外幣優惠利率 */
    private List<FxInterestRateVo> fxInterestRates;

    private List<ExchangeRateVo> exchangeRates;

    /** 常用功能 */
    private List<UsualTaskVo> usualTasks;

    /** 可設定的功能列表 */
    private List<MenuVo> menusForSetting;

    /** 可供搜尋用以設定的功能列表 */
    private List<MenuVo> menusForSearching;


    private String userNickname;

    private List<Integer> displayHomepageCardIds;


    private List<Integer> displayHomepageCardIdsDefault;


    private List<Integer> allHomepageCardIds;

    /**
     * 存款卡片資料
     */
    private CardDataDeposit cardDataDeposit;
    /**
     * 信用卡卡片資料
     */
    private CardDataCreditCard cardDataCreditCard;

    /**
     * 投資卡片資料
     */
    private CardDataInvestment cardDataInvestment;

    /**
     * 貸款卡片資料
     */
    private CardDataLoan cardDataLoan;

    /**
     * 保險卡片資料
     */
    private CardDataInsurance cardDataInsurance;

    /**
     * 證券卡片資料
     * */
    private CardDataStock cardDataStock;

    /**
     * 免登速查後資料
     */
    private QuickSearchResponse quickSearchData;
    /** 首頁牌卡設定檔 */
    private HomepageCard homepageCard;

    private String dbuObu;

    /** 公告類訊息DB[SYSTEM_NOTIFICATION_RECORD] 的ITEM_NO(參照BIZ來源)*/
    private List<String> systemNoticeRecItemNos;

    /** 新功能介紹Table Model */
    private List<NewFunctionIntro> newFunctionIntros;

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public RespectInfo getRespectInfo() {
        return respectInfo;
    }

    public void setRespectInfo(RespectInfo respectInfo) {
        this.respectInfo = respectInfo;
    }

    public List<FxInterestRateVo> getFxInterestRates() {
        return fxInterestRates;
    }

    public void setFxInterestRates(List<FxInterestRateVo> fxInterestRates) {
        this.fxInterestRates = fxInterestRates;
    }

    public List<ExchangeRateVo> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(List<ExchangeRateVo> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public List<UsualTaskVo> getUsualTasks() {
        return usualTasks;
    }

    public void setUsualTasks(List<UsualTaskVo> usualTasks) {
        this.usualTasks = usualTasks;
    }

    public List<MenuVo> getMenusForSetting() {
        return menusForSetting;
    }

    public void setMenusForSetting(List<MenuVo> menusForSetting) {
        this.menusForSetting = menusForSetting;
    }

    public List<MenuVo> getMenusForSearching() {
        return menusForSearching;
    }

    public void setMenusForSearching(List<MenuVo> menusForSearching) {
        this.menusForSearching = menusForSearching;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public List<Integer> getDisplayHomepageCardIds() {
        return displayHomepageCardIds;
    }

    public void setDisplayHomepageCardIds(List<Integer> displayHomepageCardIds) {
        this.displayHomepageCardIds = displayHomepageCardIds;
    }

    public CardDataDeposit getCardDataDeposit() {
        return cardDataDeposit;
    }

    public void setCardDataDeposit(CardDataDeposit cardDataDeposit) {
        this.cardDataDeposit = cardDataDeposit;
    }

    public CardDataCreditCard getCardDataCreditCard() {
        return cardDataCreditCard;
    }

    public void setCardDataCreditCard(CardDataCreditCard cardDataCreditCard) {
        this.cardDataCreditCard = cardDataCreditCard;
    }

    public CardDataInvestment getCardDataInvestment() {
        return cardDataInvestment;
    }

    public void setCardDataInvestment(CardDataInvestment cardDataInvestment) {
        this.cardDataInvestment = cardDataInvestment;
    }

    public CardDataLoan getCardDataLoan() {
        return cardDataLoan;
    }

    public void setCardDataLoan(CardDataLoan cardDataLoan) {
        this.cardDataLoan = cardDataLoan;
    }

    public QuickSearchResponse getQuickSearchData() {
        return quickSearchData;
    }

    public void setQuickSearchData(QuickSearchResponse quickSearchData) {
        this.quickSearchData = quickSearchData;
    }

    public HomepageCard getHomepageCard() {
        return homepageCard;
    }

    public void setHomepageCard(HomepageCard homepageCard) {
        this.homepageCard = homepageCard;
    }

    public String getDbuObu() {
        return dbuObu;
    }

    public void setDbuObu(String dbuObu) {
        this.dbuObu = dbuObu;
    }

    public List<Integer> getDisplayHomepageCardIdsDefault() {
        return displayHomepageCardIdsDefault;
    }

    public void setDisplayHomepageCardIdsDefault(List<Integer> displayHomepageCardIdsDefault) {
        this.displayHomepageCardIdsDefault = displayHomepageCardIdsDefault;
    }

    public CardDataInsurance getCardDataInsurance() {
        return cardDataInsurance;
    }

    public void setCardDataInsurance(CardDataInsurance cardDataInsurance) {
        this.cardDataInsurance = cardDataInsurance;
    }

    public List<String> getSystemNoticeRecItemNos() {
        return systemNoticeRecItemNos;
    }

    public void setSystemNoticeRecItemNos(List<String> systemNoticeRecItemNos) {
        this.systemNoticeRecItemNos = systemNoticeRecItemNos;
    }

    public List<Integer> getAllHomepageCardIds() {
        return allHomepageCardIds;
    }

    public void setAllHomepageCardIds(List<Integer> allHomepageCardIds) {
        this.allHomepageCardIds = allHomepageCardIds;
    }

    public List<NewFunctionIntro> getNewFunctionIntros() {
        return newFunctionIntros;
    }

    public void setNewFunctionIntros(List<NewFunctionIntro> newFunctionIntros) {
        this.newFunctionIntros = newFunctionIntros;
    }

    public CardDataStock getCardDataStock() {
        return cardDataStock;
    }

    public void setCardDataStock(CardDataStock cardDataStock) {
        this.cardDataStock = cardDataStock;
    }
}
