/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.model.creditcard;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.component.cardtype.CardType;
import com.tfb.aibank.common.type.CreditCardHoldType;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CreditCard.java
 * 
 * <p>Description:歸戶信用卡清單(來源: CE6220R)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/26, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "信用卡資訊")
public class CreditCard implements Serializable {

    private static final long serialVersionUID = -1962058911403121683L;

    public CreditCard() {
        // default constructor
    }

    /** 卡號 */
    @Schema(description = "卡號")
    private String cardNo;

    /** 1﹕主要卡片 2﹕相關卡片 */
    @Schema(description = "1﹕主要卡片 2﹕相關卡片")
    private String type;

    /** 持卡人ID */
    @Schema(description = "持卡人ID")
    private String cardHoldId;

    /** 中文姓名 */
    @Schema(description = "中文姓名")
    private String cardHoldName;

    /** 歸戶ID */
    @Schema(description = "歸戶ID")
    private String accountId;

    /** 卡種類(卡別) */
    @Schema(description = "卡種類(卡別)")
    private String cardType;

    /** 是否被上C或D-CODE ‘C’/’D’，若無則是空白 */
    @Schema(description = "是否被上C或D-CODE ‘C’/’D’，若無則是空白")
    private String authorizeBlockCode;

    /** X(1)﹕’M’(MASTER) /’V’(VISA)/’N’(NCCC) /’J’(JCB) X(2)﹕’C’/’G’/’P’表’一般卡、金卡、白金卡 X(3)﹕’S’/’M’ (slave/master) */
    @Schema(description = "X(1)﹕’M’(MASTER) /’V’(VISA)/’N’(NCCC) /’J’(JCB) X(2)﹕’C’/’G’/’P’表’一般卡、金卡、白金卡 X(3)﹕’S’/’M’ (slave/master)")
    private String cardType2;

    /** 開卡 Y﹕表開卡 N﹕表未開卡 */
    @Schema(description = "開卡 Y﹕表開卡 N﹕表未開卡")
    private String cardActiveCode;

    /** 到期日 MMYY */
    @Schema(description = "到期日 mmyy")
    private String cardExpired;

    /** 0/7﹕正常 1~6均不正常 */
    @Schema(description = "0/7﹕正常 1~6均不正常")
    private String cardStatus;

    /** 卡片最後一次事故日 */
    @Schema(description = "卡片最後一次事故日")
    private Date lastAccicentDay;

    /** 建檔日 */
    @Schema(description = "建檔日")
    private Date createDay;

    /** ISUDAY */
    @Schema(description = "ISUDAY")
    private Date issueDay;

    /** 郵寄退件碼(目前分兩種：RMC、UNKNOW CARD) */
    @Schema(description = "郵寄退件碼(目前分兩種：RMC、UNKNOW CARD)")
    private String returnMailCode;

    /** 信用卡黑名單(以歸戶來看) Y：黑名單 N：非黑名單 */
    @Schema(description = "信用卡黑名單(以歸戶來看) Y：黑名單 N：非黑名單")
    private String blackList;

    /** 最近改地址日期 */
    @Schema(description = "最近改地址日期")
    private Date lastAaddrChangedDate;

    /** 持卡狀態 */
    @Schema(description = "持卡狀態")
    private String cardHoldSts;

    /** 最近停卡日期 */
    @Schema(description = "最近停卡日期")
    private Date cardCutDay;

    /** TM註記 */
    @Schema(description = "TM註記")
    private String tmstsFlag;

    /** 信用卡黑名單(for掛補毀補會辦單) */
    @Schema(description = "信用卡黑名單(for掛補毀補會辦單)")
    private String blackListReissue;

    /** 到期月 */
    @Schema(description = "到期月")
    private String expireMonth;

    /** 正卡卡號 */
    @Schema(description = "正卡卡號")
    private String mCardNo;

    /** 是否可申請 apple pay (第一碼Y:可申請) */
    @Schema(description = "是否可申請 apple pay (第一碼Y:可申請)")
    private String tknFlag;

    /** HCE卡註記 */
    @Schema(description = "HCE卡註記")
    private String hceCard;

    /** 是否提供e-mail消費簡訊 */
    @Schema(description = "是否提供e-mail消費簡訊")
    private String consumeMessage;

    /** 申請消費訊息狀態碼 */
    @Schema(description = "申請消費訊息狀態碼")
    private String applyConsumeMessage;

    /** 是否可設定保費權益 */
    @Schema(description = "是否可設定保費權益")
    private String insuFlag;

    /** 卡片暱稱 */
    @Schema(description = "卡片暱稱")
    private String cardNickname;

    /** 記名式悠遊卡 */
    @Schema(description = "記名式悠遊卡")
    private String tsccFlag;

    /**
     * 信用卡類別 空白：一般信用卡 1：實體採購卡/好市多採購卡 2：網路採購卡 3：市府公務卡 4：代扣卡
     */
    @Schema(description = "信用卡類別")
    private String vpsF16;

    /** 卡面圖檔 (DB.CARD_PICTURE.IMAGE_URL) */
    @Schema(description = "卡面圖檔")
    private String imageURL;

    /** 持卡類別，分為正卡、正卡項下附卡、附卡三種 */
    @Schema(description = "持卡類別")
    private CreditCardHoldType cardHoldType;

    /** 識別資訊(唯一值) */
    private String cardKey;

    // =========== 以下為額外擴充欄位 ===========

    /** 信用卡卡別資訊 */
    @Schema(description = "信用卡卡別資訊")
    private CardType cardTypeInfo;

    // @formatter:off
    /**
     * <ol>信用卡名稱 
     *  <li>若有設定暱稱時，顯示{暱稱}。</li>
     *  <li>若未設定暱稱時，顯示{卡別}；若無{卡別}資料，預設顯示“富邦信用卡”</li>
     * </ol>
     */
    // @formatter:on
    @Schema(description = "信用卡名稱")
    private String cardName;

    // =========== 以上為額外擴充欄位 ===========

    // =========== 以下為額外擴充方法 ===========

    /**
     * 卡片是否已啟用
     * 
     * @return
     */
    public boolean isCardActive() {
        return StringUtils.isY(getCardActiveCode());
    }

    /**
     * 卡種名稱
     * 
     * @return
     */
    public String getCardLevelDesc() {
        if (this.cardTypeInfo != null) {
            return Optional.ofNullable(this.cardTypeInfo.getCardLevelDesc()).orElse(StringUtils.EMPTY);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 是否已綁定行動支付
     * 
     * @return
     */
    public boolean isHceCard() {
        return StringUtils.isY(getHceCard());
    }

    /**
     * 是否為記名式悠遊卡
     * 
     * @return
     */
    public boolean isTsccFlag() {
        return StringUtils.isY(getTsccFlag());
    }

    /**
     * 是否可設定保費權益
     * 
     * @return
     */
    public boolean isInsuFlag() {
        return StringUtils.isY(getInsuFlag());
    }

    /**
     * 是否可申請國際行動支付卡號
     * 
     * @return
     */
    public boolean isTknFlag() {
        return StringUtils.isY(getTknFlag());
    }

    /**
     * 是否為「正卡」
     * 
     * @return
     */
    public boolean isPositive() {
        return StringUtils.equals(getCardNo(), getmCardNo());
    }

    /**
     * 是否為「附卡」
     * 
     * @return
     */
    public boolean isSupplementary() {
        return StringUtils.notEquals(getCardNo(), getmCardNo());
    }

    /**
     * 簡碼顯示
     * 
     * @return
     */
    public String getSimpleCardNo() {
        return "····" + StringUtils.right(getCardNo(), 4);
    }

    // =========== 以上為額外擴充方法 ===========

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardHoldId() {
        return cardHoldId;
    }

    public void setCardHoldId(String cardHoldId) {
        this.cardHoldId = cardHoldId;
    }

    public String getCardHoldName() {
        return cardHoldName;
    }

    public void setCardHoldName(String cardHoldName) {
        this.cardHoldName = cardHoldName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getAuthorizeBlockCode() {
        return authorizeBlockCode;
    }

    public void setAuthorizeBlockCode(String authorizeBlockCode) {
        this.authorizeBlockCode = authorizeBlockCode;
    }

    public String getCardType2() {
        return cardType2;
    }

    public void setCardType2(String cardType2) {
        this.cardType2 = cardType2;
    }

    public String getCardActiveCode() {
        return cardActiveCode;
    }

    public void setCardActiveCode(String cardActiveCode) {
        this.cardActiveCode = cardActiveCode;
    }

    public String getCardExpired() {
        return cardExpired;
    }

    public void setCardExpired(String cardExpired) {
        this.cardExpired = cardExpired;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getLastAccicentDay() {
        return lastAccicentDay;
    }

    public void setLastAccicentDay(Date lastAccicentDay) {
        this.lastAccicentDay = lastAccicentDay;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public Date getIssueDay() {
        return issueDay;
    }

    public void setIssueDay(Date issueDay) {
        this.issueDay = issueDay;
    }

    public String getReturnMailCode() {
        return returnMailCode;
    }

    public void setReturnMailCode(String returnMailCode) {
        this.returnMailCode = returnMailCode;
    }

    public String getBlackList() {
        return blackList;
    }

    public void setBlackList(String blackList) {
        this.blackList = blackList;
    }

    public Date getLastAaddrChangedDate() {
        return lastAaddrChangedDate;
    }

    public void setLastAaddrChangedDate(Date lastAaddrChangedDate) {
        this.lastAaddrChangedDate = lastAaddrChangedDate;
    }

    public String getCardHoldSts() {
        return cardHoldSts;
    }

    public void setCardHoldSts(String cardHoldSts) {
        this.cardHoldSts = cardHoldSts;
    }

    public Date getCardCutDay() {
        return cardCutDay;
    }

    public void setCardCutDay(Date cardCutDay) {
        this.cardCutDay = cardCutDay;
    }

    public String getTmstsFlag() {
        return tmstsFlag;
    }

    public void setTmstsFlag(String tmstsFlag) {
        this.tmstsFlag = tmstsFlag;
    }

    public String getBlackListReissue() {
        return blackListReissue;
    }

    public void setBlackListReissue(String blackListReissue) {
        this.blackListReissue = blackListReissue;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(String expireMonth) {
        this.expireMonth = expireMonth;
    }

    public String getmCardNo() {
        return mCardNo;
    }

    public void setmCardNo(String mCardNo) {
        this.mCardNo = mCardNo;
    }

    public String getTknFlag() {
        return tknFlag;
    }

    public void setTknFlag(String tknFlag) {
        this.tknFlag = tknFlag;
    }

    public String getHceCard() {
        return hceCard;
    }

    public void setHceCard(String hceCard) {
        this.hceCard = hceCard;
    }

    public String getConsumeMessage() {
        return consumeMessage;
    }

    public void setConsumeMessage(String consumeMessage) {
        this.consumeMessage = consumeMessage;
    }

    public String getApplyConsumeMessage() {
        return applyConsumeMessage;
    }

    public void setApplyConsumeMessage(String applyConsumeMessage) {
        this.applyConsumeMessage = applyConsumeMessage;
    }

    public String getInsuFlag() {
        return insuFlag;
    }

    public void setInsuFlag(String insuFlag) {
        this.insuFlag = insuFlag;
    }

    public String getCardNickname() {
        return cardNickname;
    }

    public void setCardNickname(String cardNickname) {
        this.cardNickname = cardNickname;
    }

    public String getTsccFlag() {
        return tsccFlag;
    }

    public void setTsccFlag(String tsccFlag) {
        this.tsccFlag = tsccFlag;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public CreditCardHoldType getCardHoldType() {
        return cardHoldType;
    }

    public void setCardHoldType(CreditCardHoldType holdType) {
        this.cardHoldType = holdType;
    }

    public CardType getCardTypeInfo() {
        return cardTypeInfo;
    }

    public void setCardTypeInfo(CardType cardTypeInfo) {
        this.cardTypeInfo = cardTypeInfo;
    }

    public String getCardKey() {
        return cardKey;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    public String getVpsF16() {
        return vpsF16;
    }

    public void setVpsF16(String vpsF16) {
        this.vpsF16 = vpsF16;
    }

}
