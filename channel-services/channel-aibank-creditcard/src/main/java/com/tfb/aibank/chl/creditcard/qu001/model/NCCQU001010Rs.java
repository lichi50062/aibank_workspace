package com.tfb.aibank.chl.creditcard.qu001.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001010Rs.java
* 
* <p>Description:信信用卡總覽 功能首頁 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001010Rs implements RsData {
    /** 導交易頁面 */
    private String GOTO;

    /** 來源交易 */
    private String txnSource;

    /** 卡片皆未開啟 */
    private boolean cardUnopened;
    /** 讀取卡片資料發生問題 */
    private boolean cardError;
    /** 未出帳消費明細無資料 */
    private boolean noSummary;
    /** 沒有信用卡 */
    private boolean noCreditCard;
    /** 信用卡卡號 錨點 */
    private String cardKey;
    /** 持有正卡 */
    private boolean hasPrimaryCard;

    // 初始資料
    /** 目前刷卡消費金額 */
    private BigDecimal totalAmount;
    /** 卡片資料暫存 */
    private List<NCCQQU001CardData> cardInfos;
    /** 卡片資料暫存 (正卡項下副卡) */
    private List<NCCQQU001CardData> underCardInfos;
    /** 未出帳消費明細發生錯誤 */
    private boolean cew205rError;
    /** 未出帳消費明細發生錯誤-錯誤代碼 */
    private String cew205rErrorCode;
    /** 信用卡帳務資料發生錯誤 */
    private boolean cew303rError;
    /** 信用卡帳務資料發生錯誤-錯誤代碼 */
    private String cew303rErrorCode;
    /** 下次結帳日 */
    private Date nextCheckoutDate;
    /** 消費額度 */
    private BigDecimal quota;
    /** 消費剩餘額度 */
    private BigDecimal balQuota;

    // tab2
    private boolean tabII;
    /** 帳單明細發生錯誤 */
    private boolean cew314Error;
    /** 帳單明細發生錯誤-錯誤代碼 */
    private String cew314ErrorCode;
    /** 好多金 正負值 X(1) */
    private String signFlg3;
    /** 好多金 本期增加 9(9) */
    private BigDecimal addamt;
    /** 好多金 失敗 */
    private boolean costcoError;
    /** 紅利回饋資料發生錯誤 */
    private boolean cew306rError;
    /** 紅利回饋 */
    private NCCQQU001BonusRewards bonusRewards;
    /** 自動扣繳設定資料 */
    private boolean automatic;
    /** 正負號 X(1) */
    private String aatpin;
    /** 結帳日 X(7) */
    private Date aacldy;
    /** 繳款期限 X(7) */
    private Date aalmdy;
    /** 繳款期限是否大於系統日 */
    private Boolean moreThenSysdate;
    /** 本期應繳 X(9) */
    private BigDecimal aatpay;

    /** 本期應繳金額 X(9) */
    private BigDecimal acctIdSbal;
    /** 本期最低應繳金額 X(9) */
    private BigDecimal acctIdMinp;
    /** 本期已繳金額 X(9) */
    private BigDecimal acctIdPayd;
    /** 本期帳單剩餘應繳金額 X(9) */
    private BigDecimal acctIdDpayd;
    /** 遲繳狀況 X(1) */
    private String acctIdDlusts;

    /** 未出帳消費明細 */
    private List<NCCQQU001Bill> bills;

    // 分期資料
    private List<NCCQQU001InstallmentGroup> installments;

    /** 【前期應繳】與【繳(退)款金額】牌卡資料 */
    private NCCQU001PaymentInfo paymentInfo;

    /** 是否為「COMPANY_KIND = 4，且為附卡人身分」 */
    private boolean additionalCardholder;

    /** 申請帳單分期是否申請 */
    private boolean isBillInstallmentApply = true;

    public String getTxnSource() {
        return txnSource;
    }

    public void setTxnSource(String txnSource) {
        this.txnSource = txnSource;
    }

    /**
     * @return the cardUnopened
     */
    public boolean isCardUnopened() {
        return cardUnopened;
    }

    /**
     * @param cardUnopened
     *            the cardUnopened to set
     */
    public void setCardUnopened(boolean cardUnopened) {
        this.cardUnopened = cardUnopened;
    }

    /**
     * @return the cardError
     */
    public boolean isCardError() {
        return cardError;
    }

    /**
     * @param cardError
     *            the cardError to set
     */
    public void setCardError(boolean cardError) {
        this.cardError = cardError;
    }

    /**
     * @return the noSummary
     */
    public boolean isNoSummary() {
        return noSummary;
    }

    /**
     * @param noSummary
     *            the noSummary to set
     */
    public void setNoSummary(boolean noSummary) {
        this.noSummary = noSummary;
    }

    /**
     * @return the noCreditCard
     */
    public boolean isNoCreditCard() {
        return noCreditCard;
    }

    /**
     * @param noCreditCard
     *            the noCreditCard to set
     */
    public void setNoCreditCard(boolean noCreditCard) {
        this.noCreditCard = noCreditCard;
    }

    /**
     * @return the hasPrimaryCard
     */
    public boolean isHasPrimaryCard() {
        return hasPrimaryCard;
    }

    /**
     * @param hasPrimaryCard
     *            the hasPrimaryCard to set
     */
    public void setHasPrimaryCard(boolean hasPrimaryCard) {
        this.hasPrimaryCard = hasPrimaryCard;
    }

    /**
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount
     *            the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the cardInfos
     */
    public List<NCCQQU001CardData> getCardInfos() {
        return cardInfos;
    }

    /**
     * @param cardInfos
     *            the cardInfos to set
     */
    public void setCardInfos(List<NCCQQU001CardData> cardInfos) {
        this.cardInfos = cardInfos;
    }

    /**
     * @return the cew205rError
     */
    public boolean isCew205rError() {
        return cew205rError;
    }

    /**
     * @param cew205rError
     *            the cew205rError to set
     */
    public void setCew205rError(boolean cew205rError) {
        this.cew205rError = cew205rError;
    }

    /**
     * @return the cew303rError
     */
    public boolean isCew303rError() {
        return cew303rError;
    }

    /**
     * @param cew303rError
     *            the cew303rError to set
     */
    public void setCew303rError(boolean cew303rError) {
        this.cew303rError = cew303rError;
    }

    /**
     * @return the nextCheckoutDate
     */
    public Date getNextCheckoutDate() {
        return nextCheckoutDate;
    }

    /**
     * @param nextCheckoutDate
     *            the nextCheckoutDate to set
     */
    public void setNextCheckoutDate(Date nextCheckoutDate) {
        this.nextCheckoutDate = nextCheckoutDate;
    }

    /**
     * @return the quota
     */
    public BigDecimal getQuota() {
        return quota;
    }

    /**
     * @param quota
     *            the quota to set
     */
    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    /**
     * @return the balQuota
     */
    public BigDecimal getBalQuota() {
        return balQuota;
    }

    /**
     * @param balQuota
     *            the balQuota to set
     */
    public void setBalQuota(BigDecimal balQuota) {
        this.balQuota = balQuota;
    }

    /**
     * @return the tabII
     */
    public boolean isTabII() {
        return tabII;
    }

    /**
     * @param tabII
     *            the tabII to set
     */
    public void setTabII(boolean tabII) {
        this.tabII = tabII;
    }

    /**
     * @return the cew314Error
     */
    public boolean isCew314Error() {
        return cew314Error;
    }

    /**
     * @param cew314Error
     *            the cew314Error to set
     */
    public void setCew314Error(boolean cew314Error) {
        this.cew314Error = cew314Error;
    }

    /**
     * @return the signFlg3
     */
    public String getSignFlg3() {
        return signFlg3;
    }

    /**
     * @param signFlg3
     *            the signFlg3 to set
     */
    public void setSignFlg3(String signFlg3) {
        this.signFlg3 = signFlg3;
    }

    /**
     * @return the addamt
     */
    public BigDecimal getAddamt() {
        return addamt;
    }

    /**
     * @param addamt
     *            the addamt to set
     */
    public void setAddamt(BigDecimal addamt) {
        this.addamt = addamt;
    }

    /**
     * @return the automatic
     */
    public boolean isAutomatic() {
        return automatic;
    }

    /**
     * @param automatic
     *            the automatic to set
     */
    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    /**
     * @return the aatpin
     */
    public String getAatpin() {
        return aatpin;
    }

    /**
     * @param aatpin
     *            the aatpin to set
     */
    public void setAatpin(String aatpin) {
        this.aatpin = aatpin;
    }

    /**
     * @return the aacldy
     */
    public Date getAacldy() {
        return aacldy;
    }

    /**
     * @param aacldy
     *            the aacldy to set
     */
    public void setAacldy(Date aacldy) {
        this.aacldy = aacldy;
    }

    /**
     * @return the aalmdy
     */
    public Date getAalmdy() {
        return aalmdy;
    }

    /**
     * @param aalmdy
     *            the aalmdy to set
     */
    public void setAalmdy(Date aalmdy) {
        this.aalmdy = aalmdy;
    }

    /**
     * @return the aatpay
     */
    public BigDecimal getAatpay() {
        return aatpay;
    }

    /**
     * @param aatpay
     *            the aatpay to set
     */
    public void setAatpay(BigDecimal aatpay) {
        this.aatpay = aatpay;
    }

    /**
     * @return the bills
     */
    public List<NCCQQU001Bill> getBills() {
        return bills;
    }

    /**
     * @param bills
     *            the bills to set
     */
    public void setBills(List<NCCQQU001Bill> bills) {
        this.bills = bills;
    }

    /**
     * @return the acctIdSbal
     */
    public BigDecimal getAcctIdSbal() {
        return acctIdSbal;
    }

    /**
     * @param acctIdSbal
     *            the acctIdSbal to set
     */
    public void setAcctIdSbal(BigDecimal acctIdSbal) {
        this.acctIdSbal = acctIdSbal;
    }

    /**
     * @return the acctIdMinp
     */
    public BigDecimal getAcctIdMinp() {
        return acctIdMinp;
    }

    /**
     * @param acctIdMinp
     *            the acctIdMinp to set
     */
    public void setAcctIdMinp(BigDecimal acctIdMinp) {
        this.acctIdMinp = acctIdMinp;
    }

    /**
     * @return the acctIdPayd
     */
    public BigDecimal getAcctIdPayd() {
        return acctIdPayd;
    }

    /**
     * @param acctIdPayd
     *            the acctIdPayd to set
     */
    public void setAcctIdPayd(BigDecimal acctIdPayd) {
        this.acctIdPayd = acctIdPayd;
    }

    /**
     * @return the acctIdDpayd
     */
    public BigDecimal getAcctIdDpayd() {
        return acctIdDpayd;
    }

    /**
     * @param acctIdDpayd
     *            the acctIdDpayd to set
     */
    public void setAcctIdDpayd(BigDecimal acctIdDpayd) {
        this.acctIdDpayd = acctIdDpayd;
    }

    /**
     * @return the acctIdDlusts
     */
    public String getAcctIdDlusts() {
        return acctIdDlusts;
    }

    /**
     * @param acctIdDlusts
     *            the acctIdDlusts to set
     */
    public void setAcctIdDlusts(String acctIdDlusts) {
        this.acctIdDlusts = acctIdDlusts;
    }

    /**
     * @return the gOTO
     */
    public String getGOTO() {
        return GOTO;
    }

    /**
     * @param gOTO
     *            the gOTO to set
     */
    public void setGOTO(String gOTO) {
        GOTO = gOTO;
    }

    /**
     * @return the cew306rError
     */
    public boolean isCew306rError() {
        return cew306rError;
    }

    /**
     * @param cew306rError
     *            the cew306rError to set
     */
    public void setCew306rError(boolean cew306rError) {
        this.cew306rError = cew306rError;
    }

    /**
     * @return the costcoError
     */
    public boolean isCostcoError() {
        return costcoError;
    }

    /**
     * @param costcoError
     *            the costcoError to set
     */
    public void setCostcoError(boolean costcoError) {
        this.costcoError = costcoError;
    }

    /**
     * @return the underCardInfos
     */
    public List<NCCQQU001CardData> getUnderCardInfos() {
        return underCardInfos;
    }

    /**
     * @param underCardInfos
     *            the underCardInfos to set
     */
    public void setUnderCardInfos(List<NCCQQU001CardData> underCardInfos) {
        this.underCardInfos = underCardInfos;
    }

    /**
     * @return the cardKey
     */
    public String getCardKey() {
        return cardKey;
    }

    /**
     * @param cardKey
     *            the cardKey to set
     */
    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    /**
     * @return the bonusRewards
     */
    public NCCQQU001BonusRewards getBonusRewards() {
        return bonusRewards;
    }

    /**
     * @param bonusRewards
     *            the bonusRewards to set
     */
    public void setBonusRewards(NCCQQU001BonusRewards bonusRewards) {
        this.bonusRewards = bonusRewards;
    }

    /**
     * @return the moreThenSysdate
     */
    public Boolean getMoreThenSysdate() {
        return moreThenSysdate;
    }

    /**
     * @param moreThenSysdate
     *            the moreThenSysdate to set
     */
    public void setMoreThenSysdate(Boolean moreThenSysdate) {
        this.moreThenSysdate = moreThenSysdate;
    }

    /**
     * @return the installments
     */
    public List<NCCQQU001InstallmentGroup> getInstallments() {
        return installments;
    }

    /**
     * @param installments
     *            the installments to set
     */
    public void setInstallments(List<NCCQQU001InstallmentGroup> installments) {
        this.installments = installments;
    }

    public NCCQU001PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(NCCQU001PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getCew303rErrorCode() {
        return cew303rErrorCode;
    }

    public void setCew303rErrorCode(String cew303rErrorCode) {
        this.cew303rErrorCode = cew303rErrorCode;
    }

    public String getCew205rErrorCode() {
        return cew205rErrorCode;
    }

    public void setCew205rErrorCode(String cew205rErrorCode) {
        this.cew205rErrorCode = cew205rErrorCode;
    }

    public String getCew314ErrorCode() {
        return cew314ErrorCode;
    }

    public void setCew314ErrorCode(String cew314ErrorCode) {
        this.cew314ErrorCode = cew314ErrorCode;
    }

    public boolean isAdditionalCardholder() {
        return additionalCardholder;
    }

    public void setAdditionalCardholder(boolean additionalCardholder) {
        this.additionalCardholder = additionalCardholder;
    }

    public boolean isBillInstallmentApply() {
        return isBillInstallmentApply;
    }

    public void setBillInstallmentApply(boolean isBillInstallmentApply) {
        this.isBillInstallmentApply = isBillInstallmentApply;
    }

}
