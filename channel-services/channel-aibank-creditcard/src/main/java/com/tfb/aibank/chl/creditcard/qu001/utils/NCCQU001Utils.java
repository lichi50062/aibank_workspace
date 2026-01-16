package com.tfb.aibank.chl.creditcard.qu001.utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.cardpaytype.CardPaytype;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001Bill;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001CardData;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001HistoricalBill;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001Installment;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001InstallmentGroup;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001PaymentInfo;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.UserResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW218RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW218RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW230RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW231RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW306RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW309RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RA021Repeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RB100Repeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW321RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW327RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW329RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW336RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW345RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW424RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW424RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW466RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.VB0051Response;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.model.creditcard.CreditCardStatus;
import com.tfb.aibank.common.code.AIBankConstants;

//@formatter:off
/**
* @(#)NCCQU001Utils.java
* 
* <p>Description:信用卡總覽 Utils</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NCCQU001Utils {
    @Autowired
    protected SystemParamCacheManager systemParamCacheManager;
    @Autowired
    private CreditCardResource creditCardResource;
    @Autowired
    private UserDataCacheService userDataCacheService;
    @Autowired
    private UserResource userResource;
    private static final IBLog logger = IBLog.getLog(NCCQU001Utils.class);
    public static final String NCCQU001_CACHE_KEY = "NCCQU001CacheKey";
    public static final String DEFAULT_CARD_FACE = "/static/CARD_IMAGES/-1.svg";
    public static final String CEW345R_VALID_TIME = "2025/04/01 09:00:00";// 保費權益設定新增單筆金額五萬以下設定生效時間

    /**
     * 取得CEW205R中的NcbSign NcbTwd 加總
     * 
     * @param cew205r
     * @return
     */
    public BigDecimal getTotalAmountFromCEW205R(List<CEW205RRepeat> cew205r) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CEW205RRepeat cew205rRepeat : cew205r) {
            if (StringUtils.equals("-", cew205rRepeat.getNcbSign())) {
                totalAmount = totalAmount.subtract(cew205rRepeat.getNcbTwd());
            }
            else {
                totalAmount = totalAmount.add(cew205rRepeat.getNcbTwd());
            }
        }
        return totalAmount;
    }

    /**
     * 結帳日前消費明細查詢
     * 
     * @param custId
     * @param custCrdno
     * @return
     */
    public CEW205RResponse sendCEW205R(String custId, String custCrdno) {
        BaseServiceResponse<CEW205RResponse> rs = creditCardResource.sendCEW205R(custId, custCrdno);
        return rs.getData();
    }

    /**
     * 額度及繳款查詢
     *
     * @param custAcid
     * @param custCdno
     * @return
     */
    public CEW303RResponse sendCEW303R(String custAcid, String custCdno) {
        BaseServiceResponse<CEW303RResponse> rs = creditCardResource.sendCEW303R(custAcid, custCdno);
        return rs.getData();
    }

    /**
     * 信用卡費已自動扣繳設定 查
     *
     * @param custId
     * @param custType
     * @return
     */
    public Boolean sendCEW309R(String custId, String custType) {
        return creditCardResource.sendCEW309R(custId, custType).getData();
    }

    /**
     * 信用卡費已自動扣繳設定
     *
     * @param custId
     * @param request
     * @return
     */
    public Boolean sendCEW309R(String custId, CEW309RRequest request) {
        return creditCardResource.sendCEW309R(custId, request).getData();
    }

    /**
     * 正卡:帳單明細查詢
     * 
     * @param custId
     * @param custIdBlmt
     * @return
     */
    public CEW314RResponse sendCEW314R(String custId, String custIdBlmt) {
        BaseServiceResponse<CEW314RResponse> rs = creditCardResource.sendCEW314R(custId, custIdBlmt);
        return rs.getData();
    }

    /**
     * 附卡:帳單明細查詢
     * 
     * @param vqidno
     * @param vqcdno
     * @param vqqrym
     * @return
     */
    public CEW218RResponse sendCEW218R(String vqidno, String vqcdno, String vqqrym) {
        BaseServiceResponse<CEW218RResponse> rs = creditCardResource.sendCEW218R(vqidno, vqcdno, vqqrym);
        return rs.getData();
    }

    /**
     * 富御金餘額查詢
     * 
     * @param accid
     * @param montyp
     * @return
     */
    public VB0051Response sendVB0051(String accid, String montyp) {
        BaseServiceResponse<VB0051Response> rs = creditCardResource.sendVB0051(accid, montyp);
        return rs.getData();
    }

    /**
     * 信用卡紅利積點查詢
     * 
     * @param accid
     * @return
     */
    public CEW306RResponse sendCEW306R(String accid) {
        BaseServiceResponse<CEW306RResponse> rs = creditCardResource.sendCEW306R(accid);
        return rs.getData();
    }

    /**
     * 虛擬卡資訊查詢
     * 
     * @param VQACID
     * @return
     * @throws Exception
     */
    public CEW336RResponse sendCEW336R(String VQACID) {
        BaseServiceResponse<CEW336RResponse> rs = creditCardResource.sendCEW336R(VQACID);
        return rs.getData();
    }

    /**
     * 年度累積消費查詢
     * 
     * @param CRDNO
     * @return
     * @throws Exception
     */
    public CEW321RResponse sendCEW321R(String CRDNO) {
        BaseServiceResponse<CEW321RResponse> rs = creditCardResource.sendCEW321R(CRDNO);
        return rs.getData();
    }

    /**
     * 保費權益設定查詢
     * 
     * @param ACID
     * @return
     * @throws Exception
     */
    public CEW327RResponse sendCEW327R(String ACID) {
        return creditCardResource.sendCEW327RByCustId(ACID);
    }

    /**
     * 保費權益設定查詢
     * 
     * @param ACID
     * @return
     * @throws Exception
     */
    public CEW345RResponse sendCEW345R(String ACID) {
        return creditCardResource.sendCEW345RByCustId(ACID);
    }

    /**
     * 判斷指定卡保費權益設定，新增單筆分期五萬以下是否生效
     * 
     */
    public boolean getCEW345Valid() {
        try {
            String cew345rValidTime = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CEW345R_VALID_TIME", CEW345R_VALID_TIME);
            Date startTime = DateUtils.getDateByDateFormat(cew345rValidTime, "yyyy/MM/dd HH:mm:ss");
            return startTime.compareTo(new Date()) <= 0;
        }
        catch (Exception e) {
            logger.error("取指定卡保費權益設定，新增單筆分期五萬以下是否生效錯誤" + e.getMessage());
        }
        return false;
    }

    /**
     * 查詢已綁定之行動支付卡
     * 
     * @param custId11
     * @return
     * @throws Exception
     */
    public CEW329RResponse sendCEW329R(String custId11) {
        BaseServiceResponse<CEW329RResponse> rs = creditCardResource.sendCEW329R(custId11);
        return rs.getData();
    }

    /**
     * 當期帳單已繳明細
     * 
     * @param custAcid
     * @return
     * @throws Exception
     */
    public CEW230RResponse sendCEW230R(String custAcid) {
        BaseServiceResponse<CEW230RResponse> rs = creditCardResource.sendCEW230R(custAcid);
        return rs.getData();
    }

    /**
     * 近六個月當期帳單已繳明細
     * 
     * @param custAcid
     * @return
     * @throws Exception
     */
    public CEW231RResponse sendCEW231R(String custAcid) {
        CEW231RResponse rsData = creditCardResource.sendCEW231R(custAcid);
        return rsData;
    }

    /**
     * 異動信用卡暱稱
     * 
     * @param viidno
     * @param request
     * @return
     * @throws Exception
     */
    public CEW424RResponse sendCEW424R(String viidno, CEW424RRequest request) {
        BaseServiceResponse<CEW424RResponse> rs = creditCardResource.sendCEW424R(viidno, request);
        return rs.getData();
    }

    /**
     * 含有啟用信用卡
     * 
     * @return
     */
    public boolean hasActivatedCreditCard(List<CreditCard> creditCards) {
        List<CreditCard> cards = creditCards.stream().filter(card -> card.isCardActive()).collect(Collectors.toList());
        logger.debug("已啟用卡片：有{}筆", null == cards ? 0 : CollectionUtils.size(cards));
        return CollectionUtils.isNotEmpty(cards);
    }

    /**
     * 正卡
     * 
     * @param creditCard
     * @return
     */
    public boolean isPrimaryCard(CreditCard creditCard) {
        return StringUtils.equals(creditCard.getCardHoldType().getCode(), "0");
    }

    /**
     * 正卡向下副卡
     * 
     * @param creditCard
     * @return
     */
    public boolean isUnderPrimaryCard(CreditCard creditCard) {
        return StringUtils.equals(creditCard.getCardHoldType().getCode(), "1");
    }

    /**
     * 附卡
     * 
     * @param creditCard
     * @return
     */
    public boolean isAdditionalCard(CreditCard creditCard) {
        return !StringUtils.equals(creditCard.getCardNo(), creditCard.getmCardNo());
    }

    /**
     * 擁有正卡
     * 
     * @param list
     * @return
     */
    public boolean hasPrimaryCard(List<NCCQQU001CardData> list) {
        List<NCCQQU001CardData> primaryCards = list.stream().filter(creditCard -> !creditCard.isAdditional()).collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(primaryCards);
    }

    /**
     * 擁有正卡
     * 
     * @param creditCards
     * @return
     */
    public boolean hasPrimaryCardForSessionData(List<CreditCard> creditCards) {
        if (null == creditCards)
            return false;
        List<CreditCard> primaryCards = creditCards.stream().filter(creditCard -> this.isPrimaryCard(creditCard)).collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(primaryCards);
    }

    /**
     * 判斷信用卡總覽首頁 請求的信用卡號是否存在 並且有消費明細
     * 
     * @param cardNo
     * @param cardInfos
     * @return
     */
    public boolean checkRqCardNoIsExist(String cardNo, List<NCCQQU001CardData> cardInfos) {
        Optional<NCCQQU001CardData> cardInfoOpt = cardInfos.stream().filter(info -> StringUtils.equals(cardNo, info.getCardNo()) && info.isHasCew205r()).findFirst();
        return cardInfoOpt.isPresent();
    }

    /**
     * 信用卡特殊戶
     *
     * @param status
     * @return
     */
    public boolean isSpecialCard(CreditCardStatus status) {
        return StringUtils.equalsAnyIgnoreCase(StringUtils.Y, status.getAccountStsCode());
    }

    /**
     * 持有信用卡
     *
     * @param status
     * @return
     */
    public boolean hasCreditCard(CreditCardStatus status) {
        return StringUtils.equalsAnyIgnoreCase(StringUtils.Y, status.getCardholderCode());
    }

    /**
     * 取得月份tab
     * 
     * @param month
     * @param isPrimary
     * @return
     */
    public List<Integer> getMonthArray(int month, boolean isPrimary) {
        int decrease = isPrimary ? 1 : 0;
        // isPrimary == false : month month-1 month-2 month-3 month-4
        // isPrimary == true : month-1 month-2 month-3 month-4 month-5
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Integer result = ((month - decrease - i < 1) ? month - decrease - i + 12 : month - decrease - i);
            array.add(result);
        }
        return array;
    }

    /**
     * 取得帳單月份tab首月份
     *
     * @param billingDate
     * @return
     */
    public int getMonth(Date billingDate) {
        if (null == billingDate)
            billingDate = new Date();
        // 創建一個 Calendar 對象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(billingDate);
        // 獲取月份，注意月份是從 0 開始的，所以要加 1
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 初始歷史帳單 正卡
     *
     * @param monthArray
     * @param historicalBills
     * @return
     */
    public List<NCCQQU001HistoricalBill> createHistoricalBills(List<Integer> monthArray, List<NCCQQU001HistoricalBill> historicalBills) {
        for (int i = 0; i < monthArray.size(); i++) {
            NCCQQU001HistoricalBill historicalBill = new NCCQQU001HistoricalBill();
            historicalBill.setMonth(monthArray.get(i));
            historicalBill.setQueryMonth(i + 2);
            historicalBills.add(historicalBill);
        }
        return historicalBills;
    }

    /**
     * 初始歷史帳單 正卡
     *
     * @param list
     * @param historicalBill
     * @param locale
     * @param cardInfos
     * @param underCardInfos
     * @throws ActionException
     */
    public void createHistoricalBill(List<CEW314RB100Repeat> list, NCCQQU001HistoricalBill historicalBill, CurrencyCodeCacheManager currencyCodeCacheManager, Locale locale, List<NCCQQU001CardData> cardInfos, List<NCCQQU001CardData> underCardInfos) throws ActionException {
        // 明細資料
        List<NCCQQU001Bill> bills = new ArrayList<>();
        // 分期明細資料
        List<NCCQQU001Bill> billsInstallments = new ArrayList<>();
        // 分期資料
        List<NCCQQU001InstallmentGroup> installments = new ArrayList<>();
        this.transHistoricalBill(list, bills, installments, billsInstallments, currencyCodeCacheManager, locale, cardInfos, underCardInfos, true);
        historicalBill.setBills(bills);
        historicalBill.setInstallments(installments);
    }

    /**
     * 初始歷史帳單 正卡
     *
     * CEW314R_Rs.TYPE] = NC 為卡號、NB 為消費/分期資料、ND 為補充資料。 B. 依主機回傳的 B100 區塊資料順序，若 [CEW314R_Rs.TYPE] = NC，直到下一筆[CEW314R_Rs.TYPE] = NC 的資料，都視為該卡號的資料。 C. 若[CEW314R_Rs.TYPE] = NB 的資料下一筆為[CEW314R_Rs.TYPE] = ND，則將該筆補充資料(ND)的
     * [CEW314R_Rs.B3TXT1]視為前一筆消費/分期資料(NB)的補充說明。
     *
     * @param list
     * @param bills
     * @param installments
     * @param locale
     * @param cardInfos
     * @param underCardInfos
     * @throws ActionException
     */
    public void transHistoricalBill(List<CEW314RB100Repeat> list, List<NCCQQU001Bill> bills, List<NCCQQU001InstallmentGroup> installments, List<NCCQQU001Bill> installmentBills, CurrencyCodeCacheManager currencyCodeCacheManager, Locale locale, List<NCCQQU001CardData> cardInfos, List<NCCQQU001CardData> underCardInfos, boolean joinOtherFee) throws ActionException {
        Date now = new Date();
        // fortify: Poor Style: Value Never Read
        // String cardName = StringUtils.EMPTY;
        String cardNo = StringUtils.EMPTY;
        for (CEW314RB100Repeat detail : list) {
            if (StringUtils.equalsAnyIgnoreCase("NC", detail.getB3type())) {
                cardNo = detail.getB3cdno();
            }
            else if (StringUtils.equalsAnyIgnoreCase("NB", detail.getB3type())) {
                NCCQQU001Bill bill = new NCCQQU001Bill();
                String maskCardNo = DataMaskUtil.maskCreditCard(cardNo);
                bill.setCardNo(maskCardNo);
                String findNumber = cardNo;
                // 正卡
                Optional<NCCQQU001CardData> cardOpt = cardInfos.stream().filter(cardInfo -> StringUtils.equals(cardInfo.getCardNo().replace(" ", ""), findNumber)).findFirst();
                cardOpt.ifPresent(nccqqu001CardData -> bill.setCardName(this.getCardName(nccqqu001CardData)));
                // 副卡
                Optional<NCCQQU001CardData> underCardOpt = underCardInfos.stream().filter(cardInfo -> StringUtils.equals(cardInfo.getCardNo().replace(" ", ""), findNumber)).findFirst();
                underCardOpt.ifPresent(nccqqu001CardData -> bill.setCardName(this.getCardName(nccqqu001CardData)));

                bill.setDate(detail.getB3phdy());
                bill.setDesc(detail.getB3txt0());
                bill.setSign(detail.getB3sing());
                bill.setAmount(detail.getB3lcam());
                bill.setPayDay(detail.getB3stdy());
                CardPaytype cardPayType = userDataCacheService.getCardPaytype(detail.getB3tktp(), locale);
                if (cardPayType != null) {
                    bill.setPayName(cardPayType.getPayName());
                    String token = detail.getB3tokn();
                    if (StringUtils.isNotBlank(token) && token.length() >= 4)
                        token = token.substring(token.length() - 4);
                    bill.setToken(token);
                }
                bill.setForeignSign(detail.getB3sing());
                bill.setCurrency(detail.getB3curr());
                bill.setCurrencyName(currencyCodeCacheManager.getCurrencyName(detail.getB3curr(), locale));
                bill.setForeignAmount(detail.getB3fcam());
                bill.setMemo(null);

                // 資料分類
                if (StringUtils.contains(detail.getB3txt0(), "分期利息退款")) {
                    // 判断字符串是否包含 "分期利息退款"
                    if (CollectionUtils.isNotEmpty(installments)) {
                        // 檢查{分期消費利息}後一筆消費/分期資料(NB)的[CEW314R_Rs.B3TXT0]內是否含”分期利息退款”，若是則為{利息退款}；若否則視為一般消費資料，不需合併為分期消費資料顯示。
                        NCCQQU001InstallmentGroup installment = installments.get(installments.size() - 1);
                        if (installment.getRefund() == null) {
                            installment.setRefund(this.copyFromBill(bill));
                            continue;
                        }
                    }
                }
                else if (StringUtils.contains(detail.getB3txt0(), "分期利息") || StringUtils.contains(detail.getB3txt0(), "年金利息")) {
                    // 判断字符串是否包含 "分期利息" 或是 "年金利息"
                    // 取{分期消費利息}前一筆消費/分期資料(NB)，為{分期消費本金}
                    if (CollectionUtils.isNotEmpty(bills)) {
                        NCCQQU001Bill lastBill = bills.get(bills.size() - 1);
                        NCCQQU001InstallmentGroup installment = new NCCQQU001InstallmentGroup();
                        installment.setDate(lastBill.getDate());
                        installment.setCapital(this.copyFromBill(lastBill));
                        // 刪除原本帳單明細內容
                        bills.remove(bills.size() - 1);
                        installment.setInterest(this.copyFromBill(bill));
                        installments.add(installment);
                        installmentBills.add(lastBill);
                        continue;
                    }
                }
                // 非分期資料存到帳單明細資料
                bills.add(bill);
            }
            else if (StringUtils.equalsAnyIgnoreCase("ND", detail.getB3type())) {
                NCCQQU001Bill lastElement = bills.get(bills.size() - 1);
                lastElement.setMemo(StringUtils.isNotBlank(lastElement.getMemo()) ? lastElement.getMemo() + detail.getB3txt1() : detail.getB3txt1());
            }
            else {
                // 其他費用項目資料
                if (StringUtils.equals(StringUtils.EMPTY, detail.getB3tokn()) && joinOtherFee) {
                    NCCQQU001Bill bill = new NCCQQU001Bill();
                    bill.setDesc(detail.getB3txt0());
                    bill.setAmount(detail.getB3lcam());
                    bill.setOtherFee(true);
                    // 放預設時間排除排序錯誤
                    bill.setDate(now);
                    bills.add(bill);
                }
            }
        }
        Comparator<NCCQQU001Bill> comparator = Comparator.comparing(NCCQQU001Bill::getDate).reversed();
        // fortify: Poor Style: Value Never Read
        bills.sort(comparator);
        Comparator<NCCQQU001InstallmentGroup> comparator2 = Comparator.comparing(NCCQQU001InstallmentGroup::getDate).reversed();
        installments.sort(comparator2);
    }

    /**
     * 將明細資料轉換成分期資料
     * 
     * @param bill
     * @throws ActionException
     */
    private NCCQQU001Installment copyFromBill(NCCQQU001Bill bill) throws ActionException {
        NCCQQU001Installment data = new NCCQQU001Installment();
        try {
            BeanUtils.copyProperties(data, bill);

            String cardNoString = StringUtils.substring(data.getCardNo(), data.getCardNo().length() - 4, data.getCardNo().length());
            data.setCardNo(cardNoString);
            String cardName = StringUtils.isBlank(data.getCardName()) ? I18NUtils.getMessage("ncc.default.card_name") : data.getCardName();
            data.setCardName(cardName);
            String dateString = DateFormatUtils.CE_DATE_FORMAT_MONTH_DAY.format(data.getDate());
            String payDayString = DateFormatUtils.CE_DATE_FORMAT.format(data.getPayDay());
            data.setDateString(dateString);
            data.setPayDayString(payDayString);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("轉換分期資料發生了錯誤：{}", e.getMessage());
            throw new ActionException("轉換分期資料發生了錯誤", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
        return data;
    }

    /**
     * 初始歷史帳單 附卡
     * 
     * @param response
     * @param monthArray
     * @param historicalBill
     * @param cardInfo
     */
    public void createHistoricalBill(CEW218RResponse response, NCCQQU001HistoricalBill historicalBill, NCCQQU001CardData cardInfo, CurrencyCodeCacheManager currencyCodeCacheManager, Locale locale) {
        List<NCCQQU001Bill> bills = new ArrayList<>();
        this.transHistoricalBill(response, bills, cardInfo, currencyCodeCacheManager, locale);
        historicalBill.setBills(bills);
        historicalBill.setVntwdt(response.getVntwdt());
    }

    /**
     * 初始歷史帳單 附卡
     * 
     * @param monthArray
     * @return
     */
    public List<NCCQQU001HistoricalBill> createHistoricalBills(List<Integer> monthArray) {
        List<NCCQQU001HistoricalBill> historicalBills = new ArrayList<>();
        for (int i = 0; i < monthArray.size(); i++) {
            NCCQQU001HistoricalBill historicalBill = new NCCQQU001HistoricalBill();
            historicalBill.setMonth(monthArray.get(i));
            historicalBill.setQueryMonth(i + 1);
            historicalBills.add(historicalBill);
        }
        return historicalBills;
    }

    /**
     * 產生前端帳單元件資料
     * 
     * @param response
     * @param bills
     * @param cardInfo
     */
    public void transHistoricalBill(CEW218RResponse response, List<NCCQQU001Bill> bills, NCCQQU001CardData cardInfo, CurrencyCodeCacheManager currencyCodeCacheManager, Locale locale) {
        List<CEW218RRepeat> repeats = response.getTxRepeats();
        String cardName = this.getCardName(cardInfo);
        for (CEW218RRepeat cew218rRepeat : repeats) {
            NCCQQU001Bill bill = new NCCQQU001Bill();
            bill.setDate(cew218rRepeat.getVnpchd());
            bill.setDesc(cew218rRepeat.getVntxt1());
            bill.setSign(cew218rRepeat.getVnsign());
            bill.setAmount(cew218rRepeat.getVntwd());
            bill.setPayDay(cew218rRepeat.getVnnccd());
            bill.setCardNo(cew218rRepeat.getVncard());
            bill.setCardName(cardName);
            bill.setForeignSign(cew218rRepeat.getVnsign());
            String currency = cew218rRepeat.getVnccur();
            bill.setCurrency(currency);
            // 幣別欄位不等於TWD且非空值判斷成外幣
            if (StringUtils.isNotBlank(currency) && !StringUtils.equals(CurrencyCode.TWD, currency)) {
                if (StringUtils.isNotBlank(currency)) {
                    bill.setCurrencyName(currencyCodeCacheManager.getCurrencyName(currency, locale));
                }
                bill.setForeignAmount(cew218rRepeat.getVncamt());
            }
            else {
                bill.setForeignAmount(BigDecimal.ZERO);
            }
            bills.add(bill);
        }
        IBUtils.sort(bills, new String[] { "date" }, new boolean[] { true }, true);
    }

    /**
     * 持有好市多卡
     * 
     * @return
     * @throws ActionException
     */
    public boolean isCostcoCard(String cardType) {
        // 虛擬卡片cardType
        String costcoCardType = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "COSTCO_CARD_TYPE");
        if (StringUtils.isNotBlank(costcoCardType)) {
            String[] costcoCards = costcoCardType.split(",");
            for (int i = 0; i < costcoCards.length; i++) {
                if (StringUtils.equals(cardType, costcoCards[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 搜尋是否有好市多卡
     * 
     * @param creditCards
     * @return
     */
    public boolean hasCostcoCard(List<CreditCard> creditCards) {
        Optional<CreditCard> cardOpt = creditCards.stream().filter(card -> isCostcoCard(card.getCardType())).findFirst();
        return cardOpt.isPresent();
    }

    /**
     * 由消費明細生成卡片資訊 參考來源來自cache中的cardInfos
     *
     * @param bills
     * @param cardInfos
     * @param underCardInfos
     * @return
     */
    public List<NCCQQU001CardData> createCardInfoByCEW314R(List<NCCQQU001Bill> bills, List<NCCQQU001Bill> installmentBills, List<NCCQQU001CardData> cardInfos, List<NCCQQU001CardData> underCardInfos) {
        List<NCCQQU001CardData> cardInfosForTab2 = new ArrayList<>();
        List<NCCQQU001Bill> allBills = new ArrayList<>();
        allBills.addAll(bills);
        allBills.addAll(installmentBills);
        for (NCCQQU001Bill repeat : allBills) {
            if (repeat.isOtherFee())
                continue;
            Optional<NCCQQU001CardData> opt = cardInfosForTab2.stream().filter(cardInfo -> StringUtils.equals(cardInfo.getCardNo(), repeat.getCardNo())).findAny();
            if (opt.isEmpty()) {
                Optional<NCCQQU001CardData> opt2 = cardInfos.stream().filter(cardInfo -> StringUtils.equals(cardInfo.getCardNo(), repeat.getCardNo())).findAny();
                if (opt2.isEmpty()) {
                    Optional<NCCQQU001CardData> opt3 = underCardInfos.stream().filter(cardInfo -> StringUtils.equals(cardInfo.getCardNo(), repeat.getCardNo())).findAny();
                    opt3.ifPresent(cardInfosForTab2::add);
                }
                else {
                    cardInfosForTab2.add(opt2.get());
                }
            }
        }
        return cardInfosForTab2;
    }

    /**
     * 到期日(信用卡背面) 11711 -> 1128
     * 
     * @param vnedym
     * @return
     */
    public String expiryCCCmm2mmYY(String vnedym) {
        if (StringUtils.isBlank(vnedym) || vnedym.length() < 5)
            return StringUtils.EMPTY;
        String year = vnedym.substring(0, 3);
        String month = vnedym.substring(3, 5);
        String yy = String.valueOf(NumberUtils.toInt(year) + 1911);
        return month + "/" + yy.substring(2, 4);
    }

    /**
     * 處理CEW314RA021
     * 
     * @param response
     * @param historicalBill
     */
    public void genA021Data(CEW314RResponse response, NCCQQU001HistoricalBill historicalBill) {
        if (CollectionUtils.isNotEmpty(response.getA021Repeats())) {
            CEW314RA021Repeat a021 = response.getA021Repeats().get(0);
            historicalBill.setAatpay(a021.getAatpay());
            historicalBill.setAatpin(a021.getAatpin());
            // 030
            // 本期應繳總額
            historicalBill.setAatpay(a021.getAatpay());
            // 結帳日
            historicalBill.setAacldy(a021.getAacldy());
            // 最低應繳
            historicalBill.setAamiin(a021.getAamiin());
            historicalBill.setAaminp(a021.getAaminp());
            // 繳款期限
            historicalBill.setAalmdy(a021.getAalmdy());
            // 前期應繳總額
            historicalBill.setAaolin(a021.getAaolin());
            historicalBill.setAaolbl(a021.getAaolbl());
            // 前期繳款(退)金額
            historicalBill.setAalpin(a021.getAalpin());
            historicalBill.setAalpam(a021.getAalpam());
            // 本期調整金額
            historicalBill.setAalpin(a021.getAalpin());
            historicalBill.setAanebl(a021.getAanebl());
            // 本期新增金額
            historicalBill.setAaciin(a021.getAaciin());
            historicalBill.setAacire(a021.getAacire());
            // 循環信用利率
            historicalBill.setAaintr(a021.getAaintr());
            // 帳單分期利率
            historicalBill.setAadint(a021.getAadint());

            NCCQU001PaymentInfo paymentInfo = new NCCQU001PaymentInfo(a021, response.getB060Repeats());
            historicalBill.setPaymentInfo(paymentInfo);

        }
    }

    /**
     * 取得卡片名稱
     * 
     * 優先顯示暱稱，沒有暱稱則顯示卡別
     * 
     * @param cardData
     * @return
     */
    public String getCardName(NCCQQU001CardData cardData) {
        String cardName = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(cardData.getCardNickName())) {
            cardName = cardData.getCardNickName();
        }
        else if (StringUtils.isNotBlank(cardData.getCardName())) {
            cardName = cardData.getCardName();
        }
        return cardName;
    }

    /**
     * 附卡取得CEW303R
     * 
     * @param cardNo
     * @param cache
     * @param billing
     * @return 0 失敗 1 成功 2 關帳
     */
    public String getCew303rAdds(String cardNo, NCCQU001Cache cache, CEW303RResponse billing) {
        billing = cache.getCew303rAdds().get(cardNo);
        if (null == billing) {
            try {
                billing = this.sendCEW303R(null, cardNo);
                cache.getCew303rAdds().put(cardNo, billing);
            }
            catch (ServiceException e) {
                logger.warn("取得信用卡帳務資料 sendCEW303R 查詢失敗", e);
                return e.getErrorCode();
            }
        }
        return null;
    }

    /** 是否為好市多正卡持有人 */
    public boolean isCostcoMerber(List<CreditCard> allCreditCards) {
        allCreditCards = allCreditCards.stream().filter(c -> StringUtils.isNotBlank(c.getCardNo())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(allCreditCards)) {
            // 電文成功，判斷是否持有Costco聯名卡
            List<String> cardInfo = new ArrayList<>();
            for (CreditCard cc : allCreditCards) {
                if (cc.getCardTypeInfo() != null) {
                    cardInfo.add(cc.getCardTypeInfo().getCardCode());
                }
            }

            if (cardInfo.stream().anyMatch(t -> t.equals("C001") || t.equals("C002") || t.equals("C003") || t.equals("C004"))) {
                // 正卡判斷條件:CE6220R_Rs.CARD_NO=M_CARD_NO，則視為正卡
                CreditCard creditCard = allCreditCards.stream().filter(c -> StringUtils.equals(c.getCardNo(), c.getmCardNo())).findFirst().orElse(null);
                if (creditCard != null) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 發查電文 CEW466R
     * 
     * @param type
     * @param input
     * @param dataOutput
     * @throws ActionException
     */
    public boolean getCostcoMemberInfo(String custIxd) {

        CEW466RRes res = userResource.getCostcoMemberInfo(custIxd, "09", "");

        if (StringUtils.equals("F", res.getAutoRenew())) {
            return true;
        }

        return false;
    }
}
