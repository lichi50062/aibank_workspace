package com.tfb.aibank.chl.creditcard.qu009.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;
import com.tfb.aibank.chl.component.cardpaytype.CardPaytype;
import com.tfb.aibank.chl.component.cardpaytype.CardPaytypeCacheManager;
import com.tfb.aibank.chl.component.debitcard.DebitCard;
import com.tfb.aibank.chl.component.debitcard.DebitCardCacheManager;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu009.cache.NCCQU009CacheData;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009010Rs;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009011Rq;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009AccModel;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009DebitCardModel;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009PurchaseByMonth;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009PurchaseDetail;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009PurchaseModel;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009TabModel;
import com.tfb.aibank.chl.creditcard.resource.dto.DebitCardBillRep;
import com.tfb.aibank.chl.creditcard.resource.dto.DebitCardTxnDetailRs;
import com.tfb.aibank.chl.creditcard.resource.dto.EB602655ARepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.FEP852835Repeat;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCQU009Service.java
 * 
 * <p>Description:CHL NCCQU009 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCCQU009Service extends NCCService {

    private static final IBLog logger = IBLog.getLog(NCCQU009Service.class);

    public static final String CACHE_KEY = "NCCQU009_CACHE_KEY";

    @Autowired
    private DebitCardCacheManager debitCardCacheManager;

    @Autowired
    private CardPaytypeCacheManager cardPaytypeCacheManager;

    /**
     * 取得客戶臺幣活存帳號對應的簽帳金融卡卡號
     * 
     * @param acnos
     * @param agreedOutAccMap
     * @return
     * @throws ActionException
     */
    private void getDebitCardNosByAccNo(List<String> acnos, Locale locale, Map<String, TransOutAccount> agreedOutAccMap, List<NCCQU009AccModel> accountDataOutput, Set<String> cardNoOutput) throws ActionException {
        List<EB602655ARepeat> eb602655ARepeats = creditCardResource.queryDebitCardDatas(acnos);
        // 若無資料，引導至共通錯誤頁 親愛的客戶您好，您尚未申請本服務，建議您可至本行服務據點辦理，謝謝。
        if (CollectionUtils.isEmpty(eb602655ARepeats)) {
            ActionException axe = ExceptionUtils.getActionException(ErrorCode.NO_SAVING_ACC_ERROR);
            ErrorDescription errDes = IBUtils.getErrorDescMessage(errorCodeCacheManager, axe, locale, MDC.get(MDCKey.frompage.name()));
            throw ExceptionUtils.getActionException(new ErrorStatus(errDes.getSys(), errDes.getCode(), SeverityType.ERROR, errDes.getDesc()));
        }
        Set<String> accNoSet = new HashSet<>();
        // 重複 accNo 過濾
        List<NCCQU009AccModel> accountDatas = eb602655ARepeats.stream().filter(repeat -> accNoSet.add(repeat.getAcno())).map(repeat -> {
            TransOutAccount accountObj = agreedOutAccMap.get(repeat.getAcno());
            if (Objects.isNull(accountObj)) {
                throw new IllegalArgumentException("eb602655A 取得的帳號非用戶歸戶帳號");
            }
            return new NCCQU009AccModel(accountObj, repeat);
        }).toList();
        accountDataOutput.addAll(accountDatas);
        Set<String> cardNoSet = eb602655ARepeats.stream().map(repeat -> repeat.getCardNo()).distinct().collect(Collectors.toSet());
        cardNoOutput.addAll(cardNoSet);
    }

    /**
     * 首頁資料
     * 
     * @param user
     * @param agreedOutAccs
     * @param rsData
     * @param locale
     * @throws ActionException
     */
    public void setHomePageData(AIBankUser user, List<TransOutAccount> agreedOutAccs, NCCQU009CacheData cache, NCCQU009010Rs rsData, Locale locale) throws ActionException {
        List<String> acnos = agreedOutAccs.stream().map(acc -> acc.getAcno()).toList();
        // <acno,TransOutAccount>
        Map<String, TransOutAccount> agreedOutAccMap = agreedOutAccs.stream().collect(Collectors.toMap(TransOutAccount::getAcno, Function.identity(), (existing, replacement) -> existing));

        List<NCCQU009AccModel> accountDatas = new ArrayList<>();
        Set<String> cardNoSet = new HashSet<>();
        getDebitCardNosByAccNo(acnos, locale, agreedOutAccMap, accountDatas, cardNoSet);

        // EB602655A 取得可用帳號
        List<String> accNoCanUse = accountDatas.stream().map(NCCQU009AccModel::getAcno).distinct().toList();

        List<FEP852835Repeat> cardStatusRepeat = creditCardResource.queryDebitCardStatus(accNoCanUse, "", user.getCompanyVo().getCompanyUid()).stream().filter(repeat -> {
            return cardNoSet.contains(repeat.getCardNo());
        }).toList();
        cache.setCardStatusList(cardStatusRepeat);
        rsData.setAccountDatas(accountDatas);
        try {
            // 從前一期拿時間 已取得年份
            DebitCardTxnDetailRs debitCardTxnDetailRs = creditCardResource.getDebitCardTxnDetails(accNoCanUse.get(0), "1");

            Date currentDate = Objects.isNull(debitCardTxnDetailRs.getLsnYYYMM()) ? new Date() : debitCardTxnDetailRs.getLsnYYYMM();

            List<NCCQU009TabModel> tabDatas = genTabModels(currentDate);

            rsData.setTabDatas(tabDatas);
        }
        catch (ServiceException ex) {

            Date currentDate = new Date();

            List<NCCQU009TabModel> tabDatas = genTabModels(currentDate);

            rsData.setTabDatas(tabDatas);
        }

        try {
            // 最新未出帳
            DebitCardTxnDetailRs newDebitCardTxnDetailRs = creditCardResource.getDebitCardTxnDetails(acnos.get(0), "");

            List<NCCQU009DebitCardModel> debitCardModel = getDebitCardStatusModel(cardStatusRepeat, newDebitCardTxnDetailRs, locale, accNoCanUse.get(0));
            rsData.setDebitCardModel(debitCardModel);

            /** 簽帳卡資料 ex: 最新 => <-1,資料> , 本期=><1,資料> */
            Map<String, List<NCCQU009DebitCardModel>> debitCardModelMap = new HashMap<>();
            debitCardModelMap.put("-1" + accountDatas.get(0).getAcno(), debitCardModel);

            cache.setDebitCardModelMap(debitCardModelMap);
            // 預設 PurchaseByMonth
            Map<String, NCCQU009PurchaseModel> purchaseCacheMap = cache.getPurchaseByMonthMap();
            NCCQU009PurchaseModel tabIdPurchaseModel = genPurchaseDataByMonth(locale, newDebitCardTxnDetailRs, debitCardModel, cache);
            purchaseCacheMap.put("-1" + accountDatas.get(0).getAcno(), tabIdPurchaseModel);
            cache.setPurchaseByMonthMap(purchaseCacheMap);
        }
        catch (ServiceException ex) {
            // 查詢失敗
            Map<String, NCCQU009PurchaseModel> purchaseCacheMap = cache.getPurchaseByMonthMap();
            NCCQU009PurchaseModel tabIdPurchaseModel = new NCCQU009PurchaseModel();
            tabIdPurchaseModel.setTxnResultFlag(NCCQU009PurchaseModel.TXN_RESULT_ERROR);
            purchaseCacheMap.put("-1" + acnos.get(0), tabIdPurchaseModel);
            cache.setPurchaseByMonthMap(purchaseCacheMap);
        }

    }

    /**
     * 建立Tab資料
     * 
     * @param currentDate
     * @return
     */
    private List<NCCQU009TabModel> genTabModels(Date currentDate) {
        List<NCCQU009TabModel> tabDatas = new ArrayList<>();
        NCCQU009TabModel newTabModel = new NCCQU009TabModel();
        newTabModel.setTabId("-1");
        newTabModel.setTabDisplay(I18NUtils.getMessage("nccqu009.tab.new.desc"));
        newTabModel.setTabDisplayText(I18NUtils.getMessage("nccqu009.tab.current.term"));
        newTabModel.setYear("9999");
        tabDatas.add(newTabModel);
        IntStream.rangeClosed(0, 5).forEach(tabId -> {
            Date tabDate = DateUtils.addMonth(currentDate, 0 - tabId);
            NCCQU009TabModel tabModel = new NCCQU009TabModel();
            tabModel.setTabId(String.valueOf(tabId));
            tabModel.setTabDisplay(String.valueOf(DateUtils.getMonthIntFromDate(tabDate)));
            tabModel.setTabDisplayText(I18NUtils.getMessage("nccqu009.tab.variable.term", tabId + 1));
            tabModel.setYear(String.valueOf(DateUtils.getYearIntFromDate(tabDate)));

            tabDatas.add(tabModel);
        });
        return tabDatas;
    }

    /**
     * 取得卡片資料
     * 
     * @param cardStatusRepeat
     * @return
     */
    private List<NCCQU009DebitCardModel> getDebitCardStatusModel(List<FEP852835Repeat> cardStatusRepeat, DebitCardTxnDetailRs newDebitCardTxnDetailRs, Locale locale, String queryAccno) {
        List<NCCQU009DebitCardModel> nccqu009DebitCardModels = new ArrayList<>();
        // TODO 所有卡片取得待確認
        NCCQU009DebitCardModel debitCardAllModel = new NCCQU009DebitCardModel();
        debitCardAllModel.setCardName(I18NUtils.getMessage("nccqu009.debitcard.all.desc"));
        debitCardAllModel.setCardNo("");
        debitCardAllModel.setCardPicPath(debitCardCacheManager.getDebitCardImgPath("-1", locale));
        debitCardAllModel.setDisplayCardNo("");

        List<DebitCardBillRep> debitCardBillReps = newDebitCardTxnDetailRs.getDebitCardBillRep();
        Set<String> debitCardNoSet = debitCardBillReps.stream().map(DebitCardBillRep::getCardNo).collect(Collectors.toSet());

        if (CollectionUtils.isNotEmpty(debitCardBillReps)) {
            List<FEP852835Repeat> cardStatusRepeatFilterByAcno = cardStatusRepeat.stream().filter(cardStatus -> StringUtils.equals(cardStatus.getAcno(), queryAccno)).toList();
            Map<String, FEP852835Repeat> cardStatusFilterByAcnoMap = cardStatusRepeatFilterByAcno.stream().collect(Collectors.toMap((cardStatus) -> StringUtils.left(cardStatus.getCardNo(), 6) + StringUtils.right(cardStatus.getCardNo(), 4), Function.identity(), (existing, replacement) -> existing));
            if (debitCardBillReps.size() > 1) {
                BigDecimal total = debitCardBillReps.stream().map(DebitCardBillRep::getTwdAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                debitCardAllModel.setTotalExpenditure(IBUtils.formatMoney(total, 0, "$"));
                BigDecimal averageNcbtwd = total.divide(BigDecimal.valueOf(debitCardBillReps.isEmpty() ? 1 : debitCardBillReps.size()), 2, RoundingMode.HALF_UP);
                debitCardAllModel.setAverageExpenditure(IBUtils.formatMoney(averageNcbtwd, 0, "$"));
                debitCardAllModel.setAverageExpenditureOrigin(averageNcbtwd);
                nccqu009DebitCardModels.add(debitCardAllModel);

            }
            // #1850 會有VDW00XR 有資料但是FEP852835沒有資料情況
            debitCardNoSet.forEach(cardNo -> {
                FEP852835Repeat cardStatus = cardStatusFilterByAcnoMap.get(StringUtils.left(cardNo, 6) + StringUtils.right(cardNo, 4));
                NCCQU009DebitCardModel debitCardModel = new NCCQU009DebitCardModel();
                if (Objects.isNull(cardStatus)) {
                    DebitCard debitCard = debitCardCacheManager.getDebitCardInfo("-1", locale);
                    debitCardModel.setCardName(debitCard.getCardName());
                    debitCardModel.setCardNo(cardNo);
                    debitCardModel.setCardPicPath(debitCard.getCardPicture());
                    debitCardModel.setDisplayCardNo(DataMaskUtil.maskCreditCard(cardNo));
                    // --- 因為多了 filter 選項 所以會被前端複寫
                    List<BigDecimal> debitCardBillByCard = debitCardBillReps.stream().filter(repeat -> StringUtils.equals(StringUtils.right(repeat.getCardNo(), 4), StringUtils.right(cardNo, 4))).filter(repeat -> StringUtils.equals(StringUtils.left(repeat.getCardNo(), 6), StringUtils.left(cardNo, 6))).map(DebitCardBillRep::getTwdAmt).toList();
                    // 合計消費
                    BigDecimal totalncb = debitCardBillByCard.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                    debitCardModel.setTotalExpenditure(IBUtils.formatMoney(totalncb, 0, "$"));
                    BigDecimal averageNcbtwdByCard = totalncb.divide(BigDecimal.valueOf(debitCardBillByCard.isEmpty() ? 1 : debitCardBillByCard.size()), 2, RoundingMode.HALF_UP);
                    debitCardModel.setAverageExpenditure(IBUtils.formatMoney(averageNcbtwdByCard, 0, "$"));
                    debitCardModel.setAverageExpenditureOrigin(averageNcbtwdByCard);
                    // ----
                }
                else {

                    DebitCard debitCard = debitCardCacheManager.getDebitCardInfo(cardStatus.getCarFlg(), locale);

                    debitCardModel.setCardName(debitCard.getCardName());
                    debitCardModel.setCardNo(cardNo);
                    debitCardModel.setCardPicPath(debitCard.getCardPicture());
                    debitCardModel.setDisplayCardNo(DataMaskUtil.maskCreditCard(cardNo));
                    // --- 因為多了 filter 選項 所以會被前端複寫
                    List<BigDecimal> debitCardBillByCard = debitCardBillReps.stream().filter(repeat -> StringUtils.equals(StringUtils.right(repeat.getCardNo(), 4), StringUtils.right(cardStatus.getCardNo(), 4))).filter(repeat -> StringUtils.equals(StringUtils.left(repeat.getCardNo(), 6), StringUtils.left(cardStatus.getCardNo(), 6))).map(DebitCardBillRep::getTwdAmt).toList();
                    // 合計消費
                    BigDecimal totalncb = debitCardBillByCard.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                    debitCardModel.setTotalExpenditure(IBUtils.formatMoney(totalncb, 0, "$"));
                    BigDecimal averageNcbtwdByCard = totalncb.divide(BigDecimal.valueOf(debitCardBillByCard.isEmpty() ? 1 : debitCardBillByCard.size()), 2, RoundingMode.HALF_UP);
                    debitCardModel.setAverageExpenditure(IBUtils.formatMoney(averageNcbtwdByCard, 0, "$"));
                    debitCardModel.setAverageExpenditureOrigin(averageNcbtwdByCard);
                    // ----
                }
                nccqu009DebitCardModels.add(debitCardModel);

            });

        }

        return nccqu009DebitCardModels;
    }

    /**
     * 
     * 取得查詢當月資料明細
     * 
     * @param tabId
     * @param accNo
     * @param output
     */
    public void getDetailByQueryMonth(String tabId, NCCQU009011Rq rqData, NCCQU009CacheData cache, Locale locale) {
        Map<String, List<NCCQU009DebitCardModel>> debitCardCacheMap = cache.getDebitCardModelMap();
        Map<String, NCCQU009PurchaseModel> purchaseCacheMap = cache.getPurchaseByMonthMap();
        // tadId為-1時 給空的打微服務
        String rqTabId = StringUtils.equals(tabId, "-1") ? "" : tabId;
        try {
            DebitCardTxnDetailRs debitCardTxnDetailRs = creditCardResource.getDebitCardTxnDetails(rqData.getAccNo(), rqTabId);

            List<NCCQU009DebitCardModel> debitCardModels = getDebitCardStatusModel(cache.getCardStatusList(), debitCardTxnDetailRs, locale, rqData.getAccNo());

            debitCardCacheMap.put(tabId + rqData.getAccNo(), debitCardModels);

            cache.setDebitCardModelMap(debitCardCacheMap);

            NCCQU009PurchaseModel tabIdPurchaseModel = genPurchaseDataByMonth(locale, debitCardTxnDetailRs, debitCardModels, cache);

            purchaseCacheMap.put(tabId + rqData.getAccNo(), tabIdPurchaseModel);

            cache.setPurchaseByMonthMap(purchaseCacheMap);
        }
        catch (ServiceException ex) {
            logger.error(ex.getLocalizedMessage());

            NCCQU009PurchaseModel tabIdPurchaseModel = new NCCQU009PurchaseModel();
            tabIdPurchaseModel.setTxnResultFlag(NCCQU009PurchaseModel.TXN_RESULT_ERROR);
            purchaseCacheMap.put(tabId + rqData.getAccNo(), tabIdPurchaseModel);
            cache.setPurchaseByMonthMap(purchaseCacheMap);
        }

    }

    /**
     * 單月消費明細
     * 
     * @param tabId
     * @param accNo
     * @param cache
     * @return
     */
    public NCCQU009PurchaseModel genPurchaseDataByMonth(Locale locale, DebitCardTxnDetailRs debitCardTxnDetailRs, List<NCCQU009DebitCardModel> debitCardModels, NCCQU009CacheData cache) {
        NCCQU009PurchaseModel purchaseModel = new NCCQU009PurchaseModel();
        // <卡號末4碼,卡名>
        Map<String, String> debitCardModelMap = debitCardModels.stream().collect(Collectors.toMap(cardObj -> StringUtils.right(cardObj.getCardNo(), 4), NCCQU009DebitCardModel::getCardName));

        List<NCCQU009PurchaseByMonth> purchaseByMonths = new ArrayList<>();
        List<DebitCardBillRep> debitCardBillRep = debitCardTxnDetailRs.getDebitCardBillRep();
        if (CollectionUtils.isNotEmpty(debitCardBillRep)) {
            // 日期大到小排序
            debitCardBillRep.sort(Comparator.comparing(DebitCardBillRep::getPurchaseDate));
            // 現有的日期排序 為了需要特別處理的日期顯示
            List<String> vdw008RepCclar1Sort = debitCardBillRep.stream().sorted(Comparator.comparing(DebitCardBillRep::getPurchaseDate).reversed()).map(DebitCardBillRep::getPurchaseDate).map(repeat -> {
                return StringUtils.left(DateUtils.getCEDateStr(repeat), 7);
            }).distinct().toList();
            // 使用Stream API按groupName分組
            Map<String, List<DebitCardBillRep>> vdw008RMap = debitCardBillRep.stream().collect(Collectors.groupingBy((repeat) -> {
                Date cclar1Date = repeat.getPurchaseDate();
                // 年月分組
                return StringUtils.left(DateUtils.getCEDateStr(cclar1Date), 7);
            }));
            String currentYYYY = String.valueOf(DateUtils.getYearIntFromDate(new Date()));
            for (String purchaseDateFormat : vdw008RepCclar1Sort) {
                List<DebitCardBillRep> debitCardBillReps = vdw008RMap.get(purchaseDateFormat).stream().sorted(Comparator.comparing(DebitCardBillRep::getPurchaseDate).reversed()).toList();
                NCCQU009PurchaseByMonth purchaseData = new NCCQU009PurchaseByMonth();
                String cclar1YYYY = StringUtils.left(purchaseDateFormat, 4);
                if (StringUtils.notEquals(currentYYYY, cclar1YYYY)) {
                    purchaseData.setYy(cclar1YYYY);
                }
                purchaseData.setMm(StringUtils.substring(purchaseDateFormat, 5, 7));

                List<NCCQU009PurchaseDetail> purchaseDetails = new ArrayList<>();
                for (DebitCardBillRep repeat : debitCardBillReps) {
                    NCCQU009PurchaseDetail purchaseDetail = new NCCQU009PurchaseDetail();
                    purchaseDetail.setPurchaseDate(StringUtils.right(DateUtils.getCEDateStr(repeat.getPurchaseDate()), 5));
                    // TODO待確認格式
                    purchaseDetail.setPurchaseDesc(repeat.getPurchaseDesc());
                    purchaseDetail.setPurchaseAmt(IBUtils.formatMoney(repeat.getTwdAmt(), 0, "$"));
                    purchaseDetail.setPurchaseOriginAmt(repeat.getTwdAmt());
                    purchaseDetail.setPostDate(DateUtils.getCEDateStr(repeat.getPostDate()));
                    CardPaytype cardPayType = cardPaytypeCacheManager.getCardPaytype(repeat.getvCardType(), locale.toString());
                    if (!Objects.isNull(cardPayType)) {
                        purchaseDetail.setPayType(cardPayType.getPayName());
                        // 行動支付
                        purchaseDetail.setIsMobliePay(StringUtils.isNotBlank(cardPayType.getPayName()));
                    }
                    purchaseDetail.setvCardNo(StringUtils.right(repeat.getvCardNo(), 4));
                    if (StringUtils.notEquals(repeat.getCur(), "TWD")) {
                        String curName = currencyCodeCacheManager.getCurrencyName(repeat.getCur(), locale);
                        // TODO 待測資確認格式
                        purchaseDetail.setCurAmt(IBUtils.formatMoney(repeat.getCurAmt(), IBUtils.getMoneyScale(repeat.getCur()), StringUtils.isBlank(curName) ? repeat.getCur() + " " : curName + " "));
                        // 海外消費
                        purchaseDetail.setIsFxCur(StringUtils.notEquals(repeat.getCur(), "TWD"));
                    }
                    if (StringUtils.isNotBlank(repeat.getCardNo())) {
                        purchaseDetail.setCardName(debitCardModelMap.get(StringUtils.right(repeat.getCardNo(), 4)));
                        purchaseDetail.setCardDisplay("····" + StringUtils.right(repeat.getCardNo(), 4));
                        purchaseDetail.setCardNo(repeat.getCardNo());
                    }

                    purchaseDetails.add(purchaseDetail);
                }

                purchaseData.setPurchaseDetails(purchaseDetails);
                purchaseByMonths.add(purchaseData);
            }
            purchaseModel.setTxnResultFlag(NCCQU009PurchaseModel.TXN_RESULT_HAS_DATA);
        }
        else {
            if (Boolean.TRUE.equals(debitCardTxnDetailRs.getIsVDW002RNcbSts1())) {
                purchaseModel.setTxnResultFlag(NCCQU009PurchaseModel.TXN_RESULT_NO_DATA_NO_PAY);
            }
            else {
                purchaseModel.setTxnResultFlag(NCCQU009PurchaseModel.TXN_RESULT_NO_DATA);
            }
        }
        purchaseModel.setPurchaseByMonth(purchaseByMonths);
        return purchaseModel;
    }

}
