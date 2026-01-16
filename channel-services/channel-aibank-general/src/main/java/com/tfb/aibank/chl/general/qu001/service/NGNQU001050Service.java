package com.tfb.aibank.chl.general.qu001.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ArithUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.exchangerate.ExchangeRate;
import com.tfb.aibank.chl.component.exchangeratehistory.ExRateHistoryMax;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistory;
import com.tfb.aibank.chl.component.fxtranscurrencydiscount.FxTransCurrencyDiscount;
import com.tfb.aibank.chl.component.ratecurrency.RateCurrency;
import com.tfb.aibank.chl.component.ratecurrency.RateCurrencyCacheManager;
import com.tfb.aibank.chl.general.qu001.model.CurOrder;
import com.tfb.aibank.chl.general.qu001.model.DataInput;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.model.ExchangeRateVo;
import com.tfb.aibank.chl.general.qu001.utils.NGNQU001Util;
import com.tfb.aibank.chl.general.resource.dto.FxTransDiscountId;
import com.tfb.aibank.chl.general.resource.dto.RateCardUser;
import com.tfb.aibank.chl.session.AIBankUser;

/**
 * CHL (NGNQU001）服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void
 */

// @formatter:off
/**
 * @(#)NGNQU001050Service.java
 *
 * <p>Description: NGNQU001Service for chanel[NGNQU001]</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>NGNQU001 共用service</li>
 * </ol>
 */
//@formatter:on
@Service
@Qualifier("NGNQU001050Service")
public class NGNQU001050Service extends NGNQU001Service {

    private static final String DEF_FXEX_IB_DISCOUNT_CODE = "D000001";
    private static final String DEF_FXEX_DISCOUNT_CODE = "0000000";

    @Autowired
    RateCurrencyCacheManager rateCurrencyCacheManager;
    
    /**
     * 取得登入使用者自訂匯率區塊幣別
     */
    public List<String> getUserRateCards(AIBankUser aiBankUser) {
        List<RateCardUser> userCards = this.userResource.retrieveUserRateCards(aiBankUser.getCustId(), aiBankUser.getUserId(), aiBankUser.getCompanyKind(), StringUtils.defaultString(aiBankUser.getUidDup(), "0"));
        List<String> targetExchangeCurs = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(userCards)) {
            for (RateCardUser rateCardUser : userCards) {
                if (targetExchangeCurs.size() == 3) { // 最多3張
                    break;
                }
                targetExchangeCurs.add(rateCardUser.getCurrencyEname1());
            }
        }
        return targetExchangeCurs;
    }

    private List<CurOrder> getSystemCurWithOrder() {
        List<RateCurrency> rateCurrencies = rateCurrencyCacheManager.getRateCurrenciesByRateType("0"); 

        if (CollectionUtils.isEmpty(rateCurrencies)) {
            return Collections.emptyList();
        } else {
            return rateCurrencies.stream().map(CurOrder::new).collect(Collectors.toList());
        }
    }

    private List<String> orderCurs(List<CurOrder> curOrders, List<String> curCodes) {
        if (CollectionUtils.isEmpty(curOrders) || CollectionUtils.isEmpty(curCodes)) {
            return curCodes;
        }
        return curOrders.stream().filter(co -> curCodes.indexOf(co.getCurCode()) > -1)
                .sorted(Comparator.comparing(CurOrder::getSeq)).map(CurOrder::getCurCode).collect(Collectors.toList());
    }

    //@formatter:off
    /**
     * 查詢DB EXCHANGE_RATE取得新臺幣兌外幣即期匯率資料，
     * 參考SQL： SELECT * FROM EXCHANGE_RATE WHERE RATE_TYPE=0 and EXCHANGE_TYPE_NO=0 and order by CURRENCY_SORT
     */
    //@formatter:on
    public void getExchangeRate(DataInput input, DataOutput output, AIBankUser aiBankUser) {
        List<ExchangeRate> exchangeRates = exchangeRateService.getExchangeRatesByRateType("0");

        List<CurOrder> curOrders = this.getSystemCurWithOrder();
        logger.debug("== getExchangeRate == curOrders:{}", IBUtils.attribute2Str(curOrders));

        boolean haveCustomTarget = false;
        boolean havePreferentialCur = false; // 優惠幣別優先
        if (CollectionUtils.isNotEmpty(exchangeRates)) {
            //這個濾完才是台幣兌外幣的匯率
            exchangeRates = exchangeRates.stream().filter(ex -> "0".equals(ex.getExchangeTypeNo())).collect(Collectors.toList());

            List<ExchangeRate> targetExchangeRates = new ArrayList<>();

            // 如果自訂幣別不足3筆，補上未設定幣別
            List<String> defaultExCurs = Stream.of("USD", "JPY", "CNY").collect(Collectors.toList());
            defaultExCurs = orderCurs(curOrders, defaultExCurs);
            logger.debug("== getExchangeRate == defaultExCurs:{}", IBUtils.attribute2Str(defaultExCurs));

            // 依DB參照主要的優惠幣別
            List<String> mostPreferentialCurs = Stream.of("USD", "JPY", "CNY", "EUR", "AUD", "HKD").collect(Collectors.toList());
            mostPreferentialCurs = orderCurs(curOrders, mostPreferentialCurs);
            logger.debug("== getExchangeRate == mostPreferentialCurs:{}", IBUtils.attribute2Str(mostPreferentialCurs));

            List<String> customSetCurs = null;
            // (3)如客戶有設定匯率自訂幣別，以客戶自訂幣別為主，依照設定顯示客戶設定的前1~3個幣別，如不滿3筆，以「優惠匯率」遞補，或仍不滿3筆，再以預設幣別遞補。
            // (4)如客戶未設定匯率自訂幣別，預設顯示臺幣對美元、日圓、人民幣；若有優惠匯率，「優先顯示優惠匯率牌卡」，如不滿3筆，再以預設幣別遞補

            List<String> targetCurCodes = new ArrayList<>();
            List<String> preferentialRateCurCodes;
            if (null != aiBankUser || (Objects.isNull(aiBankUser) && CollectionUtils.isNotEmpty(input.getCustCurList()))) {

                if (Objects.nonNull(aiBankUser)) {
                    customSetCurs = getUserRateCards(aiBankUser);
                } else {
                    customSetCurs = input.getCustCurList();
                }
                haveCustomTarget = CollectionUtils.isNotEmpty(customSetCurs);
                if (haveCustomTarget) {// 如果有客戶設定幣別
                    targetCurCodes = customSetCurs;
                }
                // 有登入時，先取優惠匯率，如有符合優惠，把優惠結算更新回ExchangeRate
                preferentialRateCurCodes = getPreferentialRate(exchangeRates, aiBankUser, mostPreferentialCurs, input.getLocale());
                logger.debug("== preferentialRateCurCodes == :{}", preferentialRateCurCodes);
                defaultExCurs = checkAndReSortDefaultExCurs(defaultExCurs, curOrders, preferentialRateCurCodes);
                logger.debug("== after checkAndReSortDefaultExCurs, defaultExCurs == :{}", defaultExCurs);
                if (!haveCustomTarget || customSetCurs.size() < 3) {
                    // 檢查目標幣別是否有3筆，如不足3筆，補上預設幣別
                    for (String defCur : defaultExCurs) {
                        if (targetCurCodes.size() < 3 && !targetCurCodes.contains(defCur)) {
                            targetCurCodes.add(defCur);
                        }
                    }
                }

            } else {
                // 未登入時，需發查網銀優惠和專案優惠顯示，如有符合優惠，把優惠結算更新回ExchangeRate
                preferentialRateCurCodes = getPreferentialRateNotLogin(exchangeRates, mostPreferentialCurs, input.getLocale());
                if (CollectionUtils.isNotEmpty(preferentialRateCurCodes)) {
                    havePreferentialCur = true;
                    // 檢查目標幣別是否有3筆，如不足3筆，補上優惠匯率幣別，但優惠匯率幣別需要是預設中的
                    for (String prefCur : preferentialRateCurCodes) {
                        if (targetCurCodes.size() < 3 && !targetCurCodes.contains(prefCur) && defaultExCurs.contains(prefCur)) {
                            targetCurCodes.add(prefCur);
                        }
                    }
                }
                // 檢查目標幣別是否有3筆，如不足3筆，補上預設幣別
                for (String defCur : defaultExCurs) {
                    if (targetCurCodes.size() < 3 && !targetCurCodes.contains(defCur)) {
                        targetCurCodes.add(defCur);
                    }
                }
            }

            for (String targCur : targetCurCodes) {
                exchangeRates.stream().filter(ex -> targCur.equals(ex.getCurrencyEname1())).findFirst().ifPresent(ex -> {
                    targetExchangeRates.add(ex);
                });
            }
            List<ExchangeRateVo> vos = targetExchangeRates.stream().map(NGNQU001Util.copyExchangeRate).collect(Collectors.toList());

            processExchangeRateData(vos, input, targetCurCodes, aiBankUser);
            if (logger.isDebugEnabled()) {
                logger.debug("[getExchangeRate] vos:{}", IBUtils.attribute2Str(vos));
            }
            // 非User自訂幣別時 ＋ 或沒有優惠幣別時 才排序，否則無法如同User設定時的順序呈現
            if (CollectionUtils.isNotEmpty(vos) && !haveCustomTarget && !havePreferentialCur) {
                //IBUtils.sort(vos, new String[]{"orderSeq"}, new boolean[]{false});
            }

            output.setExchangeRates(vos);
        } else {
            output.setExchangeRates(Collections.emptyList());
        }
    }

    /**
     * 重新排列預設幣別順序
     * @param defaultExCurs
     * @param curOrders => DB來的幣別排序
     * @param preferentialRateCurCodes => 有優惠的幣別
     */
    private List<String> checkAndReSortDefaultExCurs(List<String> defaultExCurs, List<CurOrder> curOrders, List<String> preferentialRateCurCodes) {
        if( CollectionUtils.isEmpty(defaultExCurs) || CollectionUtils.isEmpty(curOrders) || CollectionUtils.isEmpty(preferentialRateCurCodes)){
            return defaultExCurs;
        }

        List<CurOrder> defaultExCurOrders = curOrders.stream().filter( co -> defaultExCurs.contains(co.getCurCode())).collect(Collectors.toList());

        defaultExCurOrders.forEach( deco -> {
            //如果預設幣別不在有優惠的幣別裡，把排序序號加到99
            if(!preferentialRateCurCodes.contains(deco.getCurCode())){
                deco.setSeq(99);
            }
        });
        defaultExCurOrders.sort(Comparator.comparingInt(CurOrder::getSeq));

        return defaultExCurOrders.stream().map(CurOrder::getCurCode).collect(Collectors.toList());
    }

    private List<String> getPreferentialRateNotLogin(List<ExchangeRate> exchangeRates, List<String> mostPreferentialCurs, Locale locale) {
        List<String> prefRateCurs = new ArrayList<>();
        List<String> discountCodes = Stream.of(DEF_FXEX_DISCOUNT_CODE, DEF_FXEX_IB_DISCOUNT_CODE).toList();
        Map<String, BigDecimal> curDiscntMap = foreignExchangeResource.getFxTransCurrencyDiscountsByCodes(discountCodes, "MASS", locale.toString());
        logger.debug("== getPreferentialRateNotLogin == curDiscntMap:{}", curDiscntMap);
        if (MapUtils.isNotEmpty(curDiscntMap)) {
            for (String prefCur : mostPreferentialCurs) {
                if (curDiscntMap.containsKey(prefCur)) {
                    for (ExchangeRate exchangeRate : exchangeRates) {
                        if (prefCur.equals(exchangeRate.getCurrencyEname1())) {
                            BigDecimal discount = curDiscntMap.get(prefCur);
                            exchangeRate.setDiscount(discount);
                            exchangeRate.setSellAfterDiscount(exchangeRate.getSell().subtract(discount));
                            exchangeRate.setWithPreferentialRate(true);
                            // 適用優惠匯率計算，(參考匯率-優惠匯率折扣)；若低於成本匯率，以成本匯率為適用優惠匯率。
                            if (exchangeRate.getSellAfterDiscount().compareTo(exchangeRate.getSmallSellCost()) == -1) {
                                exchangeRate.setSellAfterDiscount(exchangeRate.getSmallSellCost());
                            }
                        }
                    }
                    prefRateCurs.add(prefCur);
                }
            }
        }
        return prefRateCurs;
    }

    protected List<String> getPreferentialRate(List<ExchangeRate> exchangeRates, AIBankUser aiBankUser, List<String> mostPreferentialCurs, Locale locale) {
        List<String> prefRateCurs = new ArrayList<>();

        List<String> discountCodes = Stream.of(DEF_FXEX_DISCOUNT_CODE, DEF_FXEX_IB_DISCOUNT_CODE).collect(Collectors.toList());
        String fxTransDiscountIdDiscountCode = getUserFxTransDiscountIdCode(aiBankUser);
        if (StringUtils.isNotEmpty(fxTransDiscountIdDiscountCode)) {
            discountCodes.add(fxTransDiscountIdDiscountCode);
        }
        String massCheck = Objects.nonNull(aiBankUser) ? aiBankUser.getMassCheckTrimmed() : "MASS";
        Map<String, BigDecimal> curDiscntMap = foreignExchangeResource.getFxTransCurrencyDiscountsByCodes(discountCodes, massCheck, locale.toString());
        logger.debug("== getPreferentialRate is curDiscntMap == : {}", curDiscntMap);

        for (String prefCur : mostPreferentialCurs) {
            logger.debug("== getPreferentialRate now check this cur ==, prefCur: {}", prefCur);

            boolean tfbEmployee = Objects.isNull(aiBankUser) ? false : userDataCacheService.isEmployee(aiBankUser);
            logger.debug("== getPreferentialRate is tfbEmployee == : {}", tfbEmployee);
            //非富邦員工
            if (!tfbEmployee) {
                BigDecimal discount = null;
                if (MapUtils.isNotEmpty(curDiscntMap)) {
                    discount = curDiscntMap.get(prefCur);
                }

                logger.debug("== getPreferentialRate -> END ==, discount: {}", discount);
                if (null != discount) {
                    for (ExchangeRate exchangeRate : exchangeRates) {
                        if (prefCur.equals(exchangeRate.getCurrencyEname1())) {
                            exchangeRate.setDiscount(discount);
                            exchangeRate.setSellAfterDiscount(exchangeRate.getSell().subtract(discount));
                            exchangeRate.setWithPreferentialRate(true);
                            // 適用優惠匯率計算，(參考匯率-優惠匯率折扣)；若低於成本匯率，以成本匯率為適用優惠匯率。
                            if (exchangeRate.getSellAfterDiscount().compareTo(exchangeRate.getSmallSellCost()) == -1) {
                                exchangeRate.setSellAfterDiscount(exchangeRate.getSmallSellCost());
                            }
                        }
                    }
                    prefRateCurs.add(prefCur);
                }
            } else {
                //富邦員工
                for (ExchangeRate exchangeRate : exchangeRates) {
                    if (prefCur.equals(exchangeRate.getCurrencyEname1())) {
                        exchangeRate.setSellAfterDiscount(exchangeRate.getSmallSellCost());
                        exchangeRate.setDiscount(exchangeRate.getSell().subtract(exchangeRate.getSmallSellCost()));
                        exchangeRate.setWithPreferentialRate(true);
                    }
                }
                prefRateCurs.add(prefCur);
            }
        }
        return prefRateCurs;
    }

    /**
     * [ 匯率分析 ] (1) 情境一：[即時優惠匯率(我買)]低於{平均買匯成本}。 A. 若符合，顯示：”低於我買入均價”+{平均買匯成本}。 B. 若不符合，判斷情境二。 (2) 情境二：[牌告匯率(我買)]是否低於{近4個營業日之最低價}。 A. 若符合，顯示：”來到本週最低價”。 B. 若不符合，顯示情境三。 (3) 情境三：計算本週漲/跌幅。 A. 算式：{本週漲跌幅}=([牌告匯率(我買)]-{4個營業日前之收盤匯率})/{4個營業日前之收盤匯率}*100%，顯示至小數後2位。 B.
     * 若為正值，顯示：”本週上漲”+{本週漲跌幅}。 C. 若為負值，顯示：”本週下跌”+{本週漲跌幅}。 D. 若為零，不顯示此欄位。
     */
    protected void processExchangeRateData(List<ExchangeRateVo> vos, DataInput input, List<String> targetCurCodes, AIBankUser aiBankUser) {

        Map<String, BigDecimal> userExchangeRecMap = new HashMap<>();
        // 有登入時，依據幣別查使用者過去半年買匯平均成本
        // 「信用卡用戶」登入沒有nameCode，無法查詢
        if (null != aiBankUser && aiBankUser.getCustomerType().isGeneral()) {
            userExchangeRecMap = foreignExchangeResource.getUserExchangeCostInNMonth(aiBankUser.getCustIdAndCheckDup(), aiBankUser.getNameCode(), targetCurCodes, 6);
        }

        for (ExchangeRateVo vo : vos) {
            // 先處理必須的額外資訊:幣別顯示名稱、交易時間格式化
            String cName = currencyCodeCacheManager.getCurrencyName(vo.getCurrencyEname1(), input.getLocale());
            vo.setCurrencyDispName(cName);
            vo.setOrderSeq(ConvertUtils.str2Int("3"));
            if (null == vo.getTxTime()) {
                vo.setTxTime(new Date());
            }
            vo.setTxTimeStr(DateFormatUtils.CE_DATETIME_FORMAT.format(vo.getTxTime()));

            logger.debug("== processExchangeRateData, current vo == : {}", IBUtils.attribute2Str(vo));

            // 開始進行匯率優惠相關資料整理
            if (null != aiBankUser) {
                // 有登入才查優惠
                // 有此幣別換匯紀錄(有過去平均成本)，才可計算「[即時優惠匯率(我買)]低於{平均買匯成本}」
                // 若無紀錄不執行此區塊
                boolean tfbEmployee = userDataCacheService.isEmployee(aiBankUser);

                BigDecimal custTxValBuyAvg = null;
                if (null != userExchangeRecMap && null != userExchangeRecMap.get(vo.getCurrencyEname1())) {
                    custTxValBuyAvg = userExchangeRecMap.get(vo.getCurrencyEname1());
                }

                if (null != custTxValBuyAvg) {
                    BigDecimal custBuyBest = vo.getSell(); //目前[牌告賣價]
                    if (tfbEmployee) {// 行員優惠
                        custBuyBest = vo.getSmallSellCost();
                    } else if (null != vo.getSellAfterDiscount()) { // 薪轉或網銀優惠
                        custBuyBest = vo.getSellAfterDiscount();
                    }
                    if (custBuyBest.doubleValue() < custTxValBuyAvg.doubleValue()) {
                        // 顯示「低於我買均價
                        // fortify: Redundant Null Check
                        vo.setRateInfo(I18NUtils.getMessage("ngn.qu001.card.exchange.below.mylowest", custTxValBuyAvg.toString()));
                        vo.setSellOriginal(vo.getSell());
                        if (Objects.nonNull(vo.getSellAfterDiscount())) {
                            vo.setSell(vo.getSellAfterDiscount());
                        }
                        vo.setOrderSeq(0);
                        vo.setRateInfoClass(0);
                    } else {
                        //「低於我買均價」沒結果繼續往下
                        processCompareRateHistory(vo);
                    }
                } else {
                    //沒有交易歷史資料，直接分析其他情境
                    processCompareRateHistory(vo);
                }
            } else {
                // 未登入時比較歷史匯率 [情境二 or 情境三]
                processCompareRateHistory(vo);
            }
        }

    }

    /**
     * 取得適用優惠匯率 ##查詢適用專案優惠匯率折扣<br>
     * <p>
     * SELECT DISCOUNT FROM FX_TRANS_CURRENCY_DISCOUNT WHERE RANK={取得AI Bank User [MASS_CHK]，若無帶"MASS"} AND DISCOUNT_CODE=D000001(外幣優惠專案) AND {系統日}>=START_DATE AND {系統日}<= END_DATE AND CURRENCY_ENAME={幣別} AND LOCALE={登入語系};
     * </p>
     */
    public BigDecimal getCurExDiscount(String currencyEname, AIBankUser aiBankUser, Locale locale) {
        BigDecimal discount = null;
        Predicate<FxTransCurrencyDiscount> findFx = getDiscountPredicate("D000001", aiBankUser.getMassCheckTrimmed(), currencyEname, locale);
        List<FxTransCurrencyDiscount> discounts = fxTransCurrDiscountCacheManager.getFxTransCurrDiscountsByCondition(findFx);

        if (CollectionUtils.isNotEmpty(discounts)) {
            FxTransCurrencyDiscount currencyDiscount = discounts.get(0);
            discount = currencyDiscount.getDiscount();
        }
        return discount;
    }

    /**
     * 取得適用優惠匯率 ##查詢固定幣別優惠匯率折扣<br>
     * <p>
     * SELECT DISCOUNT FROM FX_TRANS_CURRENCY_DISCOUNT WHERE RANK={取得AI Bank User [MASS_CHK]，若無帶"MASS"} AND DISCOUNT_CODE=0000000(一般換匯) AND {系統日}>=START_DATE AND {系統日}<= END_DATE AND CURRENCY_ENAME={幣別} AND LOCALE={登入語系};
     * </p>
     */
    public BigDecimal getCurFixedExDiscount(String currencyEname, AIBankUser aiBankUser, Locale locale) {
        BigDecimal discount = null;
        Predicate<FxTransCurrencyDiscount> findFx = getDiscountPredicate("0000000", aiBankUser.getMassCheckTrimmed(), currencyEname, locale);
        logger.debug("== getCurFixedExDiscount ==, aiBankUser.getMassCheckTrimmed(): {}", aiBankUser.getMassCheckTrimmed());
        List<FxTransCurrencyDiscount> discounts = fxTransCurrDiscountCacheManager.getFxTransCurrDiscountsByCondition(findFx);

        if (CollectionUtils.isNotEmpty(discounts)) {
            FxTransCurrencyDiscount currencyDiscount = discounts.get(0);
            discount = currencyDiscount.getDiscount();
        }
        return discount;
    }


    public String getUserFxTransDiscountIdCode(AIBankUser aiBankUser) {
        if (Objects.isNull(aiBankUser))
            return StringUtils.EMPTY;

        FxTransDiscountId fxTransDiscountId = foreignExchangeResource.getUserFxTransDiscountId(aiBankUser.getCustId(), aiBankUser.getUserId(), aiBankUser.getUidDup(), aiBankUser.getCompanyKind());
        if (null != fxTransDiscountId && StringUtils.isNotEmpty(fxTransDiscountId.getDiscountCode())) {
            return fxTransDiscountId.getDiscountCode();
        } else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 取得適用優惠匯率 ##針對薪轉戶、聯合員工優惠查詢適用優惠匯率匯率折扣<br>
     * <p>
     * SELECT DISCOUNT FROM FX_TRANS_CURRENCY_DISCOUNT WHERE RANK={取得AI Bank User [MASS_CHK]，若無帶"MASS"} AND DISCOUNT_CODE={從login user 取得COMPANY_KEY查詢DB[FX_TRANS_DISCOUNT_ID.DISCOUNT_CODE]} AND {系統日}>=START_DATE AND {系統日}<= END_DATE AND CURRENCY_ENAME={幣別}
     * AND LOCALE={登入語系};
     * </p>
     * 1) 先從FX_TRANS_DISCOUNT_ID 查 DISCOUNT_CODE 1) 用AIBankUser [MASS_CHK] + DISCOUNT_CODE + 幣別 + 語系 查 FX_TRANS_CURRENCY_DISCOUNT
     */
    public BigDecimal getEmployeeDiscount(String currencyEname, AIBankUser aiBankUser, Locale locale) {
        BigDecimal discount = null;

        String fxTransDiscountIdDiscountCode = getUserFxTransDiscountIdCode(aiBankUser);

        // 查不到discountId 就當沒這項
        if (StringUtils.isNotEmpty(fxTransDiscountIdDiscountCode)) {
            logger.debug("== getEmployeeDiscount ==, fxTransDiscountIdDiscountCode[{}], MassCheck[{}]", fxTransDiscountIdDiscountCode, aiBankUser.getMassCheckTrimmed());

            Predicate<FxTransCurrencyDiscount> findFx = getDiscountPredicate(fxTransDiscountIdDiscountCode, aiBankUser.getMassCheckTrimmed(), currencyEname, locale);

            List<FxTransCurrencyDiscount> discounts = fxTransCurrDiscountCacheManager.getFxTransCurrDiscountsByCondition(findFx);

            if (CollectionUtils.isNotEmpty(discounts)) {
                FxTransCurrencyDiscount currencyDiscount = discounts.get(0);
                discount = currencyDiscount.getDiscount();
            }
        }
        return discount;
    }

    public Predicate<FxTransCurrencyDiscount> getDiscountPredicate(String discountCode, String massCheck, String currencyName, Locale locale) {
        logger.debug("== getDiscountPredicate ==, discountCode[{}], MassCheck[{}], currencyName[{}], locale[{}]", discountCode, massCheck, currencyName, locale.toString());
        return (fx) -> {
            return discountCode.equals(fx.getDiscountCode()) && massCheck.equals(fx.getRank()) && currencyName.equals(fx.getCurrencyEname()) && locale.toString().equals(fx.getLocale());
        };
    }

    /**
     * 比較歷史匯率， 以區間尋找當前優於某區間最低點 a. 若符合[牌告匯率(我買)]低於{近一週之買匯最低價}，顯示："買匯來到近1週最低價"。 b. 若符合[牌告匯率(我買)]低於{近一個月之買匯最低價}，顯示："買匯來到近1個月最低價"。 c. 若符合[牌告匯率(我買)]低於{近三個月之買匯最低價}，顯示："買匯來到近3個月最低價"。 d. 若上述皆不符合，顯示情境三。
     */
    protected void processCompareRateHistory(ExchangeRateVo vo) {
        logger.debug("== processCompareRateHistory == current vo: {}", IBUtils.attribute2Str(vo));

        //如果cache裡資料更新的日期不是當下的日期，重讀資料
        if (!exchangeRateHistoryCacheManager.getLoadAndCacheDate().equals(DateFormatUtils.CE_DATE_FORMAT.format(new Date()))) {
            exchangeRateHistoryCacheManager.forceReload();
        }
        Map<String, Map<String, ExRateHistoryMax>> threeRangesMap = exchangeRateHistoryCacheManager.getThreeRangesExRateHistoryRangeMap();

        if (logger.isDebugEnabled()) {
            logger.debug("threeRangesMap: {}", IBUtils.attribute2Str(threeRangesMap));
        }

        List<String> ranges = Stream.of("1w", "1m", "3m").toList();

        Predicate<ExchangeRateHistory> predicate;
        List<ExchangeRateHistory> rates;
        if (null != threeRangesMap && !threeRangesMap.isEmpty()) {
            for (String rangeTxt : ranges) {
                logger.debug("== processCompareRateHistory, rangeTxt:{},  datas: {}  ==", rangeTxt, threeRangesMap.get(rangeTxt));

                Map<String, ExRateHistoryMax> rateLHMap = threeRangesMap.get(rangeTxt);

                if (null != rateLHMap && !rateLHMap.isEmpty()) {
                    String rateLHKey = vo.getCurrencyEname1() + "_TWD_0";
                    logger.debug("== vo vs rateLHKey key: {}  ==", IBUtils.attribute2Str(vo));
                    logger.debug("== rateLHKey key: {}  ==", rateLHKey);
                    ExRateHistoryMax maxData = rateLHMap.get(rateLHKey);

                    if (logger.isDebugEnabled())
                        logger.debug("== ExRateHistoryMax == maxData: {}", maxData);

                    if (null != maxData) {
                        logger.debug("== have maxData range: {}, key: {}, maxData:{}  ==", rangeTxt, rateLHKey,  IBUtils.attribute2Str(maxData));
                        BigDecimal minRate = maxData.getSellMin(); // 過去?區間最低利率
                        if (vo.getSell().compareTo(minRate) < 0) {
                            // 來到本週最低價
                            String info = "";
                            switch (rangeTxt) {
                                case "1w" -> info = I18NUtils.getMessage("ngn.qu001.card.exchange.lowest.week");
                                case "1m" -> info = I18NUtils.getMessage("ngn.qu001.card.exchange.lowest.month");
                                case "3m" -> info = I18NUtils.getMessage("ngn.qu001.card.exchange.lowest.threemonth");
                            }
                            vo.setRateInfo(info);
                            vo.setRateInfoKey(rangeTxt);
                            vo.setRateInfoClass(0);
                            vo.setOrderSeq(1);
                        }else{
                            logger.debug("== have maxData not: {}  ==",rateLHKey);
                        }
                    }
                }
            }
        }

        // rateInfo 還沒有資料，繼續比較其他
        if (StringUtils.isEmpty(vo.getRateInfo())) {
            // 取 系統日減一個月之收盤匯率
            String rateLHKey = vo.getCurrencyEname1() + "_TWD_0";
            //如果cache裡資料更新的日期不是當下的日期，重讀資料
            if (!exchangeRateHistoryCacheManager.getLoadAndCacheDate().equals(DateFormatUtils.CE_DATE_FORMAT.format(new Date()))) {
                exchangeRateHistoryCacheManager.forceReload();
            }
            ExchangeRateHistory rateHistory = exchangeRateHistoryCacheManager.getExchangeRateHistoryAboutOneMonthAgoByKey(rateLHKey);
            logger.debug("oneMonth firstDayRate: {} ", rateHistory);

            if (null != rateHistory) {
                BigDecimal currRate = vo.getSell();
                BigDecimal firstDayRate = rateHistory.getSell();
                BigDecimal varyAmt = currRate.subtract(firstDayRate);
                logger.debug("firstDayRate: {}, varAmt:{}", firstDayRate, varyAmt);
                double varyAmtDived = ArithUtils.div(varyAmt.doubleValue(), firstDayRate.doubleValue(), 4);
                double varyAmtDivedPercent = ArithUtils.mul(varyAmtDived, 100);
                DecimalFormat df = new DecimalFormat("0.00");
                logger.debug("在本週的波動幅度 {}", varyAmtDivedPercent);
                String rateInfo = "";
                if (varyAmtDived <= 0) {
                    // 本週下跌
                    rateInfo = I18NUtils.getMessage("ngn.qu001.card.exchange.trend.down", varyAmtDivedPercent + "%");
                    vo.setRateInfoKey("down");
                } else {
                    // varyAmtDived > 0 [redmine# 349]
                    rateInfo = I18NUtils.getMessage("ngn.qu001.card.exchange.trend.up", varyAmtDivedPercent + "%");
                    vo.setRateInfoKey("up");
                }
                vo.setRateInfoParam(df.format(Math.abs(varyAmtDivedPercent)));
                rateInfo = StringUtils.replace(rateInfo, "-", "");
                vo.setRateInfo(rateInfo);
                vo.setRateInfoClass(1);
            }

        }

    }


}
