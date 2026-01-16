package com.tfb.aibank.chl.common.pdf.declarationform.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ibm.tw.commons.util.StringUtils;

/**
 * 申報書資料
 */
public class DeclarationForm {

    /** 申報書版本號 */
    private String docVer;

    // 第一聯:主機上傳、銀行留存 / 第二聯：使用者留存(預設)
    private DeclarationPageEnum page = DeclarationPageEnum.SECOND;

    /** 結構: S 結售: B */
    private String settlement;

    /** 申報日期 YYYY/MM/DD */
    private Date txDate;

    /** 申報義務人 */
    private String name;

    /** 身份別 */
    private String idType;

    /** largeExchangeApplyData 身份別 */
    private String qId;

    /** 是否為居留證號 */
    private boolean isResident;

    /** 身份證號 */
    private String idNo;

    /** 使用者國別 */
    private String nation;

    /** 出生日期 YYYY/MM/DD */
    private Date birthDay;

    /** 居留證發給日期 YYYY/MM/DD */
    private Date issuanceDate;

    /** 居留證到期日期 YYYY/MM/DD */
    private Date expiryDate;

    /** 交易性質 - 其他匯出款項 */
    private String remittance;

    /** 匯款金額 - 幣別 */
    private String currency;

    /** 匯款金額 - 金幣 */
    private BigDecimal amount;

    /** 受款地區國別 */
    private String countries;

    /** 地址 */
    private String address = StringUtils.EMPTY;

    /** 電話 */
    private String tel = StringUtils.EMPTY;

    /** 送件編號 */
    private String caseNo = StringUtils.EMPTY;

    /** 外匯水單編號 */
    private String exchangeNo = StringUtils.EMPTY;

    /** 申報義務人員簽章(網行銀: 網路申報) */
    private String bankingSignature = StringUtils.EMPTY;

    /** EB065252下行的TOP欄位 */
    private String top;

    /** 是否已查扣 */
    private boolean isPermit;

    /** 查扣狀態 1:查扣成功 */
    private String permitStatus;

    /** 查扣金額 等值金額USD */
    private BigDecimal permitAmt;

    /** 央媒申報之證件號碼 */
    private String permitIdno;

    /** 結匯匯款-分類 */
    private String category;

    /** 結匯匯款-細分類 */
    private String subdivision;

    /** 結匯匯款-原匯款分類 */
    private String classO;

    /** 申報書資料建立時間 */
    private Date createTime;

    public DeclarationPageEnum getPage() {
        return page;
    }

    public void setPage(DeclarationPageEnum page) {
        this.page = page;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.defaultString(name);
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public boolean isResident() {
        return isResident;
    }

    public void setResident(boolean resident) {
        isResident = resident;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(Date issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getRemittance() {
        return remittance;
    }

    public void setRemittance(String remittance) {
        this.remittance = StringUtils.defaultString(remittance);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = StringUtils.defaultString(currency);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = StringUtils.defaultString(address);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = StringUtils.defaultString(tel);
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = StringUtils.defaultString(caseNo);
    }

    public String getExchangeNo() {
        return exchangeNo;
    }

    public void setExchangeNo(String exchangeNo) {
        this.exchangeNo = StringUtils.defaultString(exchangeNo);
    }

    public String getBankingSignature() {
        return bankingSignature;
    }

    public void setBankingSignature(String bankingSignature) {
        this.bankingSignature = StringUtils.defaultString(bankingSignature);
    }

    public String getDocVer() {
        return docVer;
    }

    public void setDocVer(String docVer) {
        this.docVer = docVer;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public boolean isPermit() {
        return isPermit;
    }

    public void setPermit(boolean isPermit) {
        this.isPermit = isPermit;
    }

    public String getPermitStatus() {
        return permitStatus;
    }

    public void setPermitStatus(String permitStatus) {
        this.permitStatus = permitStatus;
    }

    public BigDecimal getPermitAmt() {
        return permitAmt;
    }

    public void setPermitAmt(BigDecimal permitAmt) {
        this.permitAmt = permitAmt;
    }

    public String getPermitIdno() {
        return permitIdno;
    }

    public void setPermitIdno(String permitIdno) {
        this.permitIdno = permitIdno;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getClassO() {
        return classO;
    }

    public void setClassO(String classO) {
        this.classO = classO;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public enum DeclarationPageEnum {
        FIRST, SECOND
    }
}
