package com.tfb.aibank.chl.general.qu001.model;

import java.math.BigDecimal;
import java.util.List;

import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.general.resource.dto.AiFubonInsurDataResponse;
import com.tfb.aibank.chl.general.resource.dto.AiOtherInsurDataResponse;
import com.tfb.aibank.chl.general.resource.dto.WS00002Response;

// @formatter:off
/**
 * @(#)CardDataInsurance.java
 *
 * <p>Description: 功能牌卡-保險資料</p>
 *
 * <p>Modify History:</p>
 * v1, 2023/05/22, MartyPan
 * <ol>
 *  <li>功能牌卡-貸款資料</li>
 * </ol>
 */
//@formatter:on
public class CardDataInsurance extends CardDataParent {


    public CardDataInsurance() {
        super();
    }

    public CardDataInsurance(HomepageCard homepageCard) {
        super(homepageCard);
    }

    /** 取得富壽保險商品資料 */
    private List<AiFubonInsurDataResponse> aiFubonInsurDataResponses;
    /** 取得非富壽保險商品資料 */
    private List<AiOtherInsurDataResponse> aiOtherInsurDataResponses;
    /** 強制險效期資訊 */
    private WS00002Response ws00002Response;
    /** 強制險效期到期數量 */
    private int countExpire;
    /** 是否顯示累計保費 */
    private Boolean hasAmt;
    /** 累計保費 */
    private BigDecimal ammount;
    /** 傳統型保險 */
    private BigDecimal classicalAmmount;
    /** 投資型保險 */
    private BigDecimal investAmmount;
    /** 健康傷害險 */
    private BigDecimal healthAmmount;
    /** 預估保費 */
    private BigDecimal premAmmount;
    /** 查發是否失敗 */
    private Boolean loadingError;

    private String custId;

    private String birthDay;
    /** 是否開啟免登速查 */
    private Boolean hasQuickSearch;

    /** 健康傷害保險種類計算 */
    private int healthCount = 0;
    /** 投資型保險種類計算 */
    private int investCount = 0;
    /** 傳統型保險種類計算 */
    private int classicalCount = 0;
    /** 是否顯示強制險提示訊息 */
    private boolean showCompulsoryInsNotice = false;

    public List<AiFubonInsurDataResponse> getAiFubonInsurDataResponses() {
        return aiFubonInsurDataResponses;
    }

    public void setAiFubonInsurDataResponses(List<AiFubonInsurDataResponse> aiFubonInsurDataResponses) {
        this.aiFubonInsurDataResponses = aiFubonInsurDataResponses;
    }

    public List<AiOtherInsurDataResponse> getAiOtherInsurDataResponses() {
        return aiOtherInsurDataResponses;
    }

    public void setAiOtherInsurDataResponses(List<AiOtherInsurDataResponse> aiOtherInsurDataResponses) {
        this.aiOtherInsurDataResponses = aiOtherInsurDataResponses;
    }

    public WS00002Response getWs00002Response() {
        return ws00002Response;
    }

    public void setWs00002Response(WS00002Response ws00002Response) {
        this.ws00002Response = ws00002Response;
    }

    public int getCountExpire() {
        return countExpire;
    }

    public void setCountExpire(int countExpire) {
        this.countExpire = countExpire;
    }

    public Boolean getHasAmt() {
        return hasAmt;
    }

    public void setHasAmt(Boolean hasAmt) {
        this.hasAmt = hasAmt;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }

    public BigDecimal getClassicalAmmount() {
        return classicalAmmount;
    }

    public void setClassicalAmmount(BigDecimal classicalAmmount) {
        this.classicalAmmount = classicalAmmount;
    }

    public BigDecimal getInvestAmmount() {
        return investAmmount;
    }

    public void setInvestAmmount(BigDecimal investAmmount) {
        this.investAmmount = investAmmount;
    }

    public BigDecimal getHealthAmmount() {
        return healthAmmount;
    }

    public void setHealthAmmount(BigDecimal healthAmmount) {
        this.healthAmmount = healthAmmount;
    }

    public BigDecimal getPremAmmount() {
        return premAmmount;
    }

    public void setPremAmmount(BigDecimal premAmmount) {
        this.premAmmount = premAmmount;
    }

    public Boolean getLoadingError() {
        return loadingError;
    }

    public void setLoadingError(Boolean loadingError) {
        this.loadingError = loadingError;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public Boolean getHasQuickSearch() {
        return hasQuickSearch;
    }

    public void setHasQuickSearch(Boolean hasQuickSearch) {
        this.hasQuickSearch = hasQuickSearch;
    }

    public int getHealthCount() {
        return healthCount;
    }

    public void setHealthCount(int healthCount) {
        this.healthCount = healthCount;
    }

    public int getInvestCount() {
        return investCount;
    }

    public void setInvestCount(int investCount) {
        this.investCount = investCount;
    }

    public int getClassicalCount() {
        return classicalCount;
    }

    public void setClassicalCount(int classicalCount) {
        this.classicalCount = classicalCount;
    }

    public boolean isShowCompulsoryInsNotice() {
        return showCompulsoryInsNotice;
    }

    public void setShowCompulsoryInsNotice(boolean showCompulsoryInsNotice) {
        this.showCompulsoryInsNotice = showCompulsoryInsNotice;
    }
}
