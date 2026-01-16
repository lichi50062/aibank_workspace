package com.tfb.aibank.chl.creditcard.qu001.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//@formatter:off
/**
* @(#)NCCQQU001BillingDetail.java
* 
* <p>Description:信用卡總覽 歷史帳單</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQQU001HistoricalBill {
    private NCCQQU001BonusRewards bonusRewards;
    /** 頁面錯誤 */
    private boolean pageError;
    /** 頁面錯誤代碼 */
    private String pageErrorCode;
    /** 顯示月份 */
    private int month;
    /** 查詢月份 VQQRYM */
    private int queryMonth;
    /** 帳單 */
    private List<NCCQQU001Bill> bills;
    // 分期資料
    private List<NCCQQU001InstallmentGroup> installments;

    /** CEW218R 附卡 台幣總金額 X(10) */
    private String vntwdt;

    /** 好多金 正負值 X(1) */
    private String signFlg3;
    /** 好多金 本期增加 9(9) */
    private BigDecimal addamt;

    // 以下為正卡
    /** CEW314R 正卡 帳單金額 */
    private BigDecimal aatpay;
    /** CEW314R 正卡 正負號 */
    private String aatpin;

    /** 正負號 X(1) */
    private String aaciin;
    /** 循環利率 X(5) */
    private BigDecimal aaintr;
    /** 帳單分期利率 X(5) */
    private String aadint;
    /** 正負號 X(1) */
    private String aanein;
    /** 本期新增 X(9) */
    private BigDecimal aacire;
    /** 正負號 X(1) */
    private String aalpin;
    /** 本期調整 X(9) */
    private BigDecimal aanebl;
    /** 正負號 X(1) */
    private String aaolin;
    /** 繳退金額 X(9) */
    private BigDecimal aalpam;
    /** 繳款期限 X(7) */
    private Date aalmdy;
    /** 上期應繳 X(9) */
    private BigDecimal aaolbl;
    /** 最低應繳 X(9) */
    private BigDecimal aaminp;
    /** 正負號 X(1) */
    private String aamiin;
    /** 結帳日 X(7) */
    private Date aacldy;
    /** 【前期應繳】與【繳(退)款金額】牌卡資料 */
    private NCCQU001PaymentInfo paymentInfo;

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
     * @return the pageError
     */
    public boolean isPageError() {
        return pageError;
    }
    
    /**
     * @param pageError
     *            the pageError to set
     */
    public void setPageError(boolean pageError) {
        this.pageError = pageError;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }
    
    /**
     * @param month
     *            the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }
    /**
     * @return the queryMonth
     */
    public int getQueryMonth() {
        return queryMonth;
    }
    
    /**
     * @param queryMonth
     *            the queryMonth to set
     */
    public void setQueryMonth(int queryMonth) {
        this.queryMonth = queryMonth;
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

    /**
     * @return the vntwdt
     */
    public String getVntwdt() {
        return vntwdt;
    }
    
    /**
     * @param vntwdt
     *            the vntwdt to set
     */
    public void setVntwdt(String vntwdt) {
        this.vntwdt = vntwdt;
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
     * @return the aaintr
     */
    public BigDecimal getAaintr() {
        return aaintr;
    }

    /**
     * @param aaintr
     *            the aaintr to set
     */
    public void setAaintr(BigDecimal aaintr) {
        this.aaintr = aaintr;
    }

    /**
     * @return the aadint
     */
    public String getAadint() {
        return aadint;
    }

    /**
     * @param aadint
     *            the aadint to set
     */
    public void setAadint(String aadint) {
        this.aadint = aadint;
    }

    /**
     * @return the aanein
     */
    public String getAanein() {
        return aanein;
    }

    /**
     * @param aanein
     *            the aanein to set
     */
    public void setAanein(String aanein) {
        this.aanein = aanein;
    }

    /**
     * @return the aacire
     */
    public BigDecimal getAacire() {
        return aacire;
    }

    /**
     * @param aacire
     *            the aacire to set
     */
    public void setAacire(BigDecimal aacire) {
        this.aacire = aacire;
    }

    /**
     * @return the aalpin
     */
    public String getAalpin() {
        return aalpin;
    }

    /**
     * @param aalpin
     *            the aalpin to set
     */
    public void setAalpin(String aalpin) {
        this.aalpin = aalpin;
    }

    /**
     * @return the aanebl
     */
    public BigDecimal getAanebl() {
        return aanebl;
    }

    /**
     * @param aanebl
     *            the aanebl to set
     */
    public void setAanebl(BigDecimal aanebl) {
        this.aanebl = aanebl;
    }

    /**
     * @return the aaolin
     */
    public String getAaolin() {
        return aaolin;
    }

    /**
     * @param aaolin
     *            the aaolin to set
     */
    public void setAaolin(String aaolin) {
        this.aaolin = aaolin;
    }

    /**
     * @return the aalpam
     */
    public BigDecimal getAalpam() {
        return aalpam;
    }

    /**
     * @param aalpam
     *            the aalpam to set
     */
    public void setAalpam(BigDecimal aalpam) {
        this.aalpam = aalpam;
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
     * @return the aaolbl
     */
    public BigDecimal getAaolbl() {
        return aaolbl;
    }

    /**
     * @param aaolbl
     *            the aaolbl to set
     */
    public void setAaolbl(BigDecimal aaolbl) {
        this.aaolbl = aaolbl;
    }

    /**
     * @return the aaminp
     */
    public BigDecimal getAaminp() {
        return aaminp;
    }

    /**
     * @param aaminp
     *            the aaminp to set
     */
    public void setAaminp(BigDecimal aaminp) {
        this.aaminp = aaminp;
    }

    /**
     * @return the aamiin
     */
    public String getAamiin() {
        return aamiin;
    }

    /**
     * @param aamiin
     *            the aamiin to set
     */
    public void setAamiin(String aamiin) {
        this.aamiin = aamiin;
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
     * @return the aaciin
     */
    public String getAaciin() {
        return aaciin;
    }

    /**
     * @param aaciin
     *            the aaciin to set
     */
    public void setAaciin(String aaciin) {
        this.aaciin = aaciin;
    }

    /**
     * @return the pageErrorCode
     */
    public String getPageErrorCode() {
        return pageErrorCode;
    }

    /**
     * @param pageErrorCode
     *            the pageErrorCode to set
     */
    public void setPageErrorCode(String pageErrorCode) {
        this.pageErrorCode = pageErrorCode;
    }

    /**
     * @return the bonusRewards
     */
    public NCCQQU001BonusRewards getBonusRewards() {
        return bonusRewards;
    }

    /**
     * @param bonusRewards the bonusRewards to set
     */
    public void setBonusRewards(NCCQQU001BonusRewards bonusRewards) {
        this.bonusRewards = bonusRewards;
    }

    public NCCQU001PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(NCCQU001PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}