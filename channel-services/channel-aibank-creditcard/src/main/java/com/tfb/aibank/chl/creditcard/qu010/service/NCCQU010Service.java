package com.tfb.aibank.chl.creditcard.qu010.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.model.LabelValueBean;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.cardtype.CardType;
import com.tfb.aibank.chl.component.cardtype.CardTypeCacheManager;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.component.homepagecard.HomepageCardCacheManager;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010AnnualDetailData;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010BarChartData;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Cache;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010CardSummary;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010CategoryDetailData;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010CategoryRatioData;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010CategoryType;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010CreditCardRecommendation;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010DateInfo;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Input;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010MonthCategoryData;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010MonthConsumptionData;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010MonthDetailData;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Output;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010PaymentInfo;
import com.tfb.aibank.chl.creditcard.resource.InvestResource;
import com.tfb.aibank.chl.creditcard.resource.dto.AnnualDetailReq;
import com.tfb.aibank.chl.creditcard.resource.dto.AnnualDetailRes;
import com.tfb.aibank.chl.creditcard.resource.dto.AnnualDetailResRep;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryDetailReq;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryDetailRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryDetailResRep;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryReq;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryStats;
import com.tfb.aibank.chl.creditcard.resource.dto.ChargesStats;
import com.tfb.aibank.chl.creditcard.resource.dto.ConsumptionSummaryReq;
import com.tfb.aibank.chl.creditcard.resource.dto.ConsumptionSummaryRes;
import com.tfb.aibank.chl.creditcard.resource.dto.HighestAmt;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.creditcard.utils.NCCUtils;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.component.cardpicture.CardPictureCacheManager;
import com.tfb.aibank.component.dic.DicData;

// @formatter:off
/**
 * @(#)NCCQU010Service.java
 * 
 * <p>Description:CHL NCCQU010 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCCQU010Service extends NCCService {

    public static final String CACHE_KEY = "NCCQU010_CACHE";

    private static final String ANALYSIS_CONDITIONAL_MESSAGE = "ANALYSIS_CONDITIONAL_MESSAGE";

    private static final String TRAN_STAT = "TRAN_STAT";

    @Autowired
    private CardTypeCacheManager cardTypeCacheManager;

    @Autowired
    private HomepageCardCacheManager homepageCardCacheManager;

    @Autowired
    private CardPictureCacheManager cardPictureCacheManager;

    @Autowired
    private InvestResource investResource;

    @Autowired
    private NCCUtils utils;

    private final String LIMIT = "100";

    public static final int QR_SUCC_0 = 0;
    public static final int QR_FAIL_1 = 1;
    public static final int QR_NO_CARD_2 = 2;
    public static final int QR_NODATA_3 = 3;
    public static final int QU_MOREDATA_4 = 4;
    public static final int QR_NON_PRIMARY_CARD = 5;

    private static final String FOREIGN_AMT_DISPLAY_TEMPL = "%s %s";

    /**
     * 發送 消費金額彙總資訊查詢 OAuth API
     *
     * @return
     */
    public void queryConsumptionSummary(AIBankUser aiBankUser, NCCQU010Input input, NCCQU010Output output, boolean isLastMonth) {

        try {
            ConsumptionSummaryReq req = new ConsumptionSummaryReq();
            req.setCustId(aiBankUser.getCustId());
            req.setStartYearMonth(input.getStartYearMonth());

            if (isLastMonth) {
                req.setStartYearMonth(input.getEndYearMonth());
            }
            else {
                req.setStartYearMonth(input.getStartYearMonth());
            }

            req.setEndYearMonth(input.getEndYearMonth());

            ConsumptionSummaryRes res = investResource.getConsumptionSummary(req);

            output.getTrendSummary().setQueryResult(res.getQueryResult());
            output.getDistributionSummary().setQueryResult(res.getQueryResult());

            output.setQueryResult(res.getQueryResult());

            if (!isLastMonth) {
                output.setConsumptionSummary(res);
            }
            else {
                if (output.getConsumptionSummary() != null) {
                    output.getConsumptionSummary().setHighestAmt(res.getHighestAmt());
                }
            }
        }
        catch (Exception ex) {
            output.setQueryResult(QR_FAIL_1);
        }

    }

    /**
     * 發送 各消費類別金額查詢 OAuth API
     *
     * @param input
     * @param output
     */
    public void queryCategoryConsumption(AIBankUser aiBankUser, NCCQU010Input input, NCCQU010Output output) {
        try {
            CategoryReq req = new CategoryReq();
            req.setCustId(aiBankUser.getCustId());
            req.setStartYearMonth(input.getStartYearMonth());
            req.setEndYearMonth(input.getEndYearMonth());
            req.setCategoryGroup(input.getCategoryGroup());

            CategoryRes res = investResource.getCategoryConsumption(req);
            output.getRatioSummary().setQueryResult(res.getQueryResult());
            output.setCategoryGroup(input.getCategoryGroup());
            output.setCategoryConsumption(res);

        }
        catch (Exception ex) {
            output.setQueryResult(QR_FAIL_1);
        }
    }

    /**
     * 是否在查詢年月之內
     * 
     * @param category
     * @param yearMonths
     * @return
     */
    private boolean isBetween(CategoryStats category, List<String> yearMonths) {
        if (CollectionUtils.isEmpty(yearMonths) || category == null) {
            return false;
        }

        for (String yearMonth : yearMonths) {
            if (yearMonth.equals(category.getYearMonth())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 過濾 各消費類別金額查詢 OAuth API
     *
     * @param input
     * @param output
     */
    public void filterCategoryConsumption(AIBankUser aiBankUser, NCCQU010Input input, NCCQU010Output output, NCCQU010Cache cache) {
        try {

            List<CategoryStats> categoryStats = cache.getLatestTwoYearCategoryData().get(input.getCategoryGroup());

            if (!CollectionUtils.isEmpty(categoryStats)) {

                List<CategoryStats> filtered = new ArrayList<CategoryStats>();

                for (CategoryStats category : categoryStats) {
                    if (isBetween(category, cache.getLatestYearAnalysisYearMonths())) {
                        filtered.add(category);
                    }
                }

                CategoryRes res = new CategoryRes();
                res.setQueryResult(cache.getRatioSummary().getQueryResult());
                res.setCategoryStats(filtered);

                output.setCategoryConsumption(res);
                output.getRatioSummary().setQueryResult(cache.getQueryResult());
                output.setCategoryConsumption(res);
            }

        }
        catch (Exception ex) {
            output.setQueryResult(QR_FAIL_1);
        }
    }

    /**
     * 發送 各類別消費明細查詢 OAuth API
     * 
     * @param aiBankUser
     * @param input
     * @param output
     */
    public void queryCategoryDetails(AIBankUser aiBankUser, NCCQU010Input input, NCCQU010Output output) {

        try {
            CategoryDetailReq req = new CategoryDetailReq();

            req.setCategoryGroup(input.getCategoryGroup());
            req.setCategory(input.getItemCategory());
            req.setCustId(aiBankUser.getCustId());
            // YYYY-MM
            req.setYearMonth(input.getSelectedYearMonth());
            req.setSkip(input.getSkip());
            // Default: 100
            req.setLimit(LIMIT);

            CategoryDetailRes res = investResource.getCategoryDetails(req);
            output.setCategoryDetails(res.getCategoryDetails());
        }
        catch (Exception ex) {
            output.setQueryResult(QR_FAIL_1);
        }
    }

    /**
     * 發送 整年消費明細搜尋 OAuth API
     *
     * @param input
     * @param output
     */
    public void queryAnnualDetails(AIBankUser aiBankUser, NCCQU010Input input, NCCQU010Output output) {
        try {
            AnnualDetailReq req = new AnnualDetailReq();
            req.setCustId(aiBankUser.getCustId());
            // YYYY-MM
            req.setYearMonth(input.getSelectedYearMonth());
            req.setSkip(input.getSkip());

            // {資料處理上限}資料來源：DIC.DIC_VALUE (WHERE DIC_KEY = TRANS_STAT_LIMIT)，DB設定預設5000
            String limit = dicCacheManager.getValue(TRAN_STAT, "TRANS_STAT_LIMIT");
            String recordLimit = StringUtils.isNotEmpty(limit) ? limit : "5000";
            req.setLimit(recordLimit);
            AnnualDetailRes res = investResource.getAnnualDetails(req);
            if (res != null) {
                output.setQueryResult(res.getQueryResult());
                handleData(res, input, output);
            }

            output.setRecordLimit(ConvertUtils.str2Int(recordLimit));
        }
        catch (Exception ex) {
            output.setQueryResult(QR_FAIL_1);
        }
    }

    /**
     * 取得錯誤資訊
     * 
     * @param output
     */
    public void getErrorInfo(String type, NCCQU010Output output) {
        if (StringUtils.equals(type, "NoCreditCard")) {
            // 本服務僅供信用卡客戶使用
            output.setErrorTitle(I18NUtils.getMessage("nccqu010.010.error.title.no-credit-card"));
            // 立即線上申辦信用卡，或是可於營業時間至分行辦理
            output.setErrorMessage(I18NUtils.getMessage("nccqu010.010.error.message.no-credit-card"));
        }
        else {
            // 近一年無信用卡帳單資訊
            output.setErrorTitle(I18NUtils.getMessage("nccqu010.010.error.title.no-data"));
            // 將於最近一期信用卡帳單結帳後，提供相關分析內容
            output.setErrorMessage(I18NUtils.getMessage("nccqu010.010.error.message.no-data"));
        }
    }

    /**
     * 取得信用卡推薦資訊
     * 
     * @param output
     */
    public void getCreditCardRecommendationInfo(NCCQU010Output output) {
        Predicate<HomepageCard> filterNNCCQU010 = (hc) -> StringUtils.equals("NCCQU010", hc.getCardUsedTaskId()) && StringUtils.equals("TASK_CARD", hc.getCardTemplate()) && hc.getCardId() == 9 && hc.getCardUsed() == 2;
        List<HomepageCard> homepageCards = homepageCardCacheManager.getMulHomePageCardByPredicate(filterNNCCQU010);

        if (CollectionUtils.isNotEmpty(homepageCards)) {
            NCCQU010CreditCardRecommendation card = new NCCQU010CreditCardRecommendation();
            HomepageCard homepageCard = homepageCards.get(0);
            card.setTitle(I18NUtils.getMessage("nccqu010.010.card.recommendation.title"));
            card.setContent(homepageCard.getCardDesc());
            card.setLinkMessage(I18NUtils.getMessage("nccqu010.010.know.more"));
            card.setLinkUrl(homepageCard.getCardTarget());
            output.setCreditCardRecommendation(card);
        }
    }

    /**
     * 取得近一年分析月
     * 
     * @param output
     */
    public void getLatestYearAnalysisMonths(NCCQU010Output output) {
        DateTimeFormatter dateTimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM");
        Date now = new Date();
        LocalDate currentLocalDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Integer dicDate = Optional.ofNullable(getDicValue(TRAN_STAT, "NCCQU010_DATA_UPDATE_DATE")).filter(StringUtils::isNotBlank).map(Integer::valueOf).orElse(5);
        Date specificDayOfMonth = getSpecifiedDayOfMonth(now, dicDate);
        // 20250430 by 鴻揚, 營業日後改M-1，否則為M-2
        LocalDate endLocalDate = now.after(specificDayOfMonth) ? currentLocalDate.minusMonths(1) : currentLocalDate.minusMonths(2);
        LocalDate startLocalDate = endLocalDate.minusMonths(23);
        LocalDate lastYearMonth = endLocalDate.minusMonths(11);
        output.setStartYearMonth(startLocalDate.format(dateTimeFormatPattern));
        output.setEndYearMonth(endLocalDate.format(dateTimeFormatPattern));
        output.setLastYearMonth(lastYearMonth.format(dateTimeFormatPattern));

        List<String> analysisYearMonths = new ArrayList<>();
        // 設定1年區間
        for (int i = 0; i < 12; i++) {
            LocalDate date = endLocalDate.minusMonths(i);
            analysisYearMonths.add(date.format(dateTimeFormatPattern));
        }
        output.setLatestYearAnalysisYearMonths(analysisYearMonths);
    }

    /**
     * 取得近6月分析月
     * 
     * @param output
     */
    public void getLatestSizMonthAnalysisMonths(NCCQU010Output output) {
        DateTimeFormatter dateTimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM");
        Date now = new Date();
        LocalDate currentLocalDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Integer dicDate = Optional.ofNullable(getDicValue(TRAN_STAT, "NCCQU010_DATA_UPDATE_DATE")).filter(StringUtils::isNotBlank).map(Integer::valueOf).orElse(5);
        Date specificDayOfMonth = getSpecifiedDayOfMonth(now, dicDate);
        // 20250430 by 鴻揚, 營業日後改M-1，否則為M-2
        LocalDate endLocalDate = now.after(specificDayOfMonth) ? currentLocalDate.minusMonths(1) : currentLocalDate.minusMonths(2);
        LocalDate startLocalDate = endLocalDate.minusMonths(23);
        output.setStartYearMonth(startLocalDate.format(dateTimeFormatPattern));
        output.setEndYearMonth(endLocalDate.format(dateTimeFormatPattern));

        List<String> analysisYearMonths = new ArrayList<>();
        // 設定6月區間
        for (int i = 0; i < 6; i++) {
            LocalDate date = endLocalDate.minusMonths(i);
            analysisYearMonths.add(date.format(dateTimeFormatPattern));
        }
        output.setLatestSixMonthYearMonths(analysisYearMonths);
    }

    /**
     * 取得系統當月 4 號 00：00:00
     * 
     * @return
     */
    private Date getSpecifiedDayOfMonth(Date date, int specifiedDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, specifiedDay);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得消費金額彙總資訊
     * 
     * @param aiBankUser
     * @param output
     */
    public void getConsumptionSummary(AIBankUser aiBankUser, NCCQU010Input input, NCCQU010Output output) {
        output.setQueryResult(QR_SUCC_0);

        // 消費金額彙總資訊查詢
        queryConsumptionSummary(aiBankUser, input, output, false);

        if (output.getQueryResult() == 0 && CollectionUtils.isEmpty(output.getConsumptionSummary().getChargeStats())) {
            output.setQueryResult(QR_NODATA_3);
            return;
        }
        // 消費金額彙總資訊查詢
        queryConsumptionSummary(aiBankUser, input, output, true);
        // 各消費類別金額查詢
        input.setStartYearMonth(input.getEndYearMonth());
        queryCategoryConsumption(aiBankUser, input, output);
        if (!isCategoryConsumptionValid(output.getCategoryConsumption())) {
            input.setCategoryGroup(NCCQU010CategoryType.CARD.getType());
            queryCategoryConsumption(aiBankUser, input, output);
            if (!isCategoryConsumptionValid(output.getCategoryConsumption())) {
                input.setCategoryGroup(NCCQU010CategoryType.REGION.getType());
                queryCategoryConsumption(aiBankUser, input, output);
            }
        }

        // 趨勢分析牌卡
        setTrendSummary(input, output);
        // 類別分析牌卡
        setRatioSummary(input, output, isCategoryConsumptionValid(output.getCategoryConsumption()));
        // 月曆消費分析牌卡
        setDistributionSummary(input, output);

    }

    /**
     * 消費類別金額 是否符合類別分析牌卡所需
     * 
     * @param categoryRes
     * @return
     */
    private boolean isCategoryConsumptionValid(CategoryRes categoryRes) {
        BigDecimal maxTxnAmount = BigDecimal.ZERO;
        if (categoryRes == null || CollectionUtils.isEmpty(categoryRes.getCategoryStats())) {
            return false;
        }
        for (CategoryStats category : categoryRes.getCategoryStats()) {

            if (category.getTxnAmt().compareTo(maxTxnAmount) > 0) {
                maxTxnAmount = category.getTxnAmt();
            }
            if (category.getTxnAmt().compareTo(BigDecimal.ZERO) < 0) {
                return false;
            }
        }
        if (maxTxnAmount.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }

    /**
     * 整理趨勢牌卡資料
     * 
     * @param input
     * @param output
     */
    private void setTrendSummary(NCCQU010Input input, NCCQU010Output output) {
        String title;
        String content1;
        String content2 = null;
        String errorTitle = null;
        String errorMessage = null;
        List<NCCQU010BarChartData> barChartData = new ArrayList<>();

        ConsumptionSummaryRes consumptionSummary = output.getConsumptionSummary();
        NCCQU010CardSummary trendSummary = output.getTrendSummary();
        getLatestSizMonthAnalysisMonths(output);

        if (consumptionSummary == null || trendSummary.getQueryResult() == 1) {
            trendSummary.setQueryResult(QR_FAIL_1);
            output.setTrendSummary(trendSummary);
            return;
        }

        List<ChargesStats> currentMonthConsumptionData = consumptionSummary.getChargeStats().stream().filter(x -> StringUtils.equals(x.getYearMonth(), input.getEndYearMonth())).collect(Collectors.toList());
        BigDecimal currentMonthSum = currentMonthConsumptionData.stream().filter(Objects::nonNull).map(ChargesStats::getTxnAmt).reduce(BigDecimal.ZERO, BigDecimal::add);

        // 計算最近2年內資料
        calculateConsumptionData(input, output);

        List<ChargesStats> latestSixMonthConsumptionData = output.getLatestSixMonthConsumptionSummaryData();
        IBUtils.sort(latestSixMonthConsumptionData, "yearMonth", false);

        BigDecimal latestHalfYearAvg = output.getLatestSixMonthConsumptionAvg();
        BigDecimal latestYearAvg = output.getLatestYearConsumptionAvg();

        // C 樣式(無法計算樣式): {過去半年平均消費}、{過去一年平均消費}皆 <= 0
        if (latestHalfYearAvg.compareTo(BigDecimal.ZERO) <= 0 && latestYearAvg.compareTo(BigDecimal.ZERO) <= 0) {
            title = I18NUtils.getMessage("nccqu010.010.trend.calc-exception.title");
            content1 = "--";
            trendSummary.setCardType("C");
        }
        else {
            if (latestHalfYearAvg.compareTo(BigDecimal.ZERO) > 0) { // 若 {過去半年平均消費} > 0

                if (currentMonthSum.compareTo(BigDecimal.ZERO) < 0) { // B1 樣式(近半年平均消費金額): {分析月消費總額} < 0
                    // 近半年平均消費金額
                    title = I18NUtils.getMessage("nccqu010.010.trend.latest-six-month-avg-amt");
                    content1 = "$" + IBUtils.formatMoney(latestHalfYearAvg, "TWD");
                    trendSummary.setCardType("B1");
                }
                else { // A1 樣式(近半年平均差異百分比): {分析月消費總額} >= 0
                       // 月消費比近半年平均
                    title = DateUtils.getMonthShortName(utils.getMonthValue(input.getEndYearMonth()), input.getLocale()) + I18NUtils.getMessage("nccqu010.010.trend.latest-six-month-avg-ratio");
                    BigDecimal ratio = ((currentMonthSum.subtract(latestHalfYearAvg)).divide(latestHalfYearAvg, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                    String sign = ratio.compareTo(BigDecimal.ZERO) < 0 ? "" : "+";
                    content1 = sign + ConvertUtils.bigDecimal2Str(ratio) + "%";
                    content2 = I18NUtils.getMessage("nccqu010.010.label.consumption-amt") + " $" + IBUtils.formatMoney(currentMonthSum, "TWD");
                    // 若為A1、A2樣式，依過去6個月的消費金額資料，顯示對應長條圖
                    barChartData = getLast6MonthCharData(latestSixMonthConsumptionData, output.getLatestSixMonthYearMonths());

                    trendSummary.setCardType("A1");
                }

            }
            else { // 若 {過去半年平均消費} <= 0 且 {過去一年平均消費} > 0

                if (currentMonthSum.compareTo(BigDecimal.ZERO) < 0) { // B2 樣式(近一年平均消費金額): {分析月消費總額} < 0
                    // 近一年平均消費金額
                    title = I18NUtils.getMessage("nccqu010.010.trend.latest-one-year-avg-amt");
                    content1 = "$" + IBUtils.formatMoney(latestYearAvg, "TWD");
                    trendSummary.setCardType("B2");

                }
                else { // A2 樣式(近一年平均差異百分比): {分析月消費總額} >= 0
                       // 月消費比近一年平均
                    title = DateUtils.getMonthShortName(utils.getMonthValue(input.getEndYearMonth()), input.getLocale()) + I18NUtils.getMessage("nccqu010.010.trend.latest-one-year-avg-ratio");
                    BigDecimal ratio = ((currentMonthSum.subtract(latestYearAvg)).divide(latestYearAvg, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                    String sign = ratio.compareTo(BigDecimal.ZERO) < 0 ? "" : "+";
                    content1 = sign + ConvertUtils.bigDecimal2Str(ratio) + "%";
                    content2 = I18NUtils.getMessage("nccqu010.010.label.consumption-amt") + " $" + IBUtils.formatMoney(currentMonthSum, "TWD");
                    // 若為A1、A2樣式，依過去6個月的消費金額資料，顯示對應長條圖。(避免畫面過於複雜，A2樣式長條圖僅顯示近半年)
                    barChartData = getLast6MonthCharData(latestSixMonthConsumptionData, output.getLatestSixMonthYearMonths());

                    trendSummary.setCardType("A2");
                }
            }
        }

        if (StringUtils.isEmpty(content1)) {
            output.setTrendSummary(null);

        }
        else {
            trendSummary.setTitle(title);
            trendSummary.setContent1(content1);
            trendSummary.setContent2(content2);
            trendSummary.setErrorTitle(errorTitle);
            trendSummary.setErrorMessage(errorMessage);

            if (!CollectionUtils.isEmpty(barChartData)) {
                List<NCCQU010BarChartData> tmp = new ArrayList<NCCQU010BarChartData>();
                for (int i = barChartData.size() - 1; i >= 0; i--) {
                    if (barChartData.get(i).getPercentage().compareTo(BigDecimal.ZERO) < 0) {
                        tmp.add(new NCCQU010BarChartData(BigDecimal.ZERO));
                    }
                    else {
                        tmp.add(barChartData.get(i));
                    }
                }
                trendSummary.setBarChartData(tmp);
            }
            trendSummary.setQueryResult(QR_SUCC_0);
            output.setTrendSummary(trendSummary);
        }
    }

    /**
     * 取 近六個月 的 長條圖資訊
     * 
     * @param latestSixMonthConsumptionData
     * @param latestSixMonthYearMonths
     * @return
     */
    private List<NCCQU010BarChartData> getLast6MonthCharData(List<ChargesStats> latestSixMonthConsumptionData, List<String> latestSixMonthYearMonths) {
        List<NCCQU010BarChartData> charData = new ArrayList<NCCQU010BarChartData>();
        for (String yearMonth : latestSixMonthYearMonths) {
            boolean isNotFound = true;
            for (ChargesStats cs : latestSixMonthConsumptionData) {
                if (StringUtils.equals(cs.getYearMonth(), yearMonth)) {
                    isNotFound = false;
                    charData.add(new NCCQU010BarChartData(cs.getTxnAmt()));
                }
            }
            if (isNotFound) {
                charData.add(new NCCQU010BarChartData(BigDecimal.ZERO));
            }
        }
        return charData;
    }

    private void setRatioSummary(NCCQU010Input input, NCCQU010Output output, boolean isValid) {
        String title = StringUtils.EMPTY;
        String content1;
        String content2;
        String errorTitle = null;
        String errorMessage = null;
        BigDecimal highestCategoryRatio = new BigDecimal(0);

        // 消費金額彙總資訊
        ConsumptionSummaryRes consumptionSummary = output.getConsumptionSummary();
        CategoryRes category = output.getCategoryConsumption();
        NCCQU010CardSummary ratioSummary = output.getRatioSummary();

        if (consumptionSummary == null || category == null || ratioSummary.getQueryResult() == 1) {
            ratioSummary.setQueryResult(QR_FAIL_1);
            output.setRatioSummary(ratioSummary);
            return;
        }

        List<ChargesStats> currentMonthSummary = consumptionSummary.getChargeStats().stream().filter(x -> StringUtils.equals(x.getYearMonth(), input.getEndYearMonth())).collect(Collectors.toList());
        BigDecimal currentMonthSum = currentMonthSummary.stream().filter(Objects::nonNull).map(ChargesStats::getTxnAmt).reduce(BigDecimal.ZERO, BigDecimal::add);

        // E 樣式(無法計算樣式): {分析月消費總額} <= 0
        if (currentMonthSum.compareTo(BigDecimal.ZERO) <= 0 || !isValid) {
            // 消費佔比
            title = I18NUtils.getMessage("nccqu010.010.ratio.calc-exception.title");
            content1 = "--";
            // {分析月} 月 有類別消費包含負值，無法計算佔比
            content2 = I18NUtils.getMessage("nccqu010.010.label.month", DateUtils.getMonthShortName(utils.getMonthValue(input.getEndYearMonth()), input.getLocale())) + I18NUtils.getMessage("nccqu010.010.ratio.calc-exception.content2");

        }
        else {
            CategoryStats highestConsumptionData = category.getCategoryStats().stream().filter(x -> StringUtils.equals(x.getYearMonth(), input.getEndYearMonth())).max(Comparator.comparing(CategoryStats::getTxnAmt)).orElse(null);
            // {分析起始月}月{消費類別}類消費佔. {消費類別}顯示文字：DIC.MEMO (CATEGORY = ‘TRAN_STAT’ AND DIC_VALUE = {消費類別})。若取無則直接顯示未分類
            StringBuilder titleBuilder = new StringBuilder();
            if (highestConsumptionData != null) {
                titleBuilder.append(I18NUtils.getMessage("nccqu010.010.label.month", DateUtils.getMonthShortName(utils.getMonthValue(input.getEndYearMonth()), input.getLocale()))).append(getCategoryDesc(input, highestConsumptionData.getCategory()));
                if (StringUtils.equals(NCCQU010CategoryType.LIFE.getType(), input.getCategoryGroup())) {
                    titleBuilder.append(I18NUtils.getMessage("nccqu010.010.ratio.life.consumption.title"));
                }
                else {
                    titleBuilder.append(I18NUtils.getMessage("nccqu010.010.ratio.life.consumption.title_short"));
                }

                title = titleBuilder.toString();
            }
            BigDecimal highestRatioConsumptionSum = highestConsumptionData != null ? highestConsumptionData.getTxnAmt() : new BigDecimal(0);
            highestCategoryRatio = (highestRatioConsumptionSum.divide(currentMonthSum, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
            content1 = ConvertUtils.bigDecimal2Str(highestCategoryRatio) + "%";
            content2 = I18NUtils.getMessage("nccqu010.010.label.consumption-amt") + " $" + IBUtils.formatMoney(highestRatioConsumptionSum, "TWD");
        }

        if (StringUtils.isEmpty(content1)) {
            output.setRatioSummary(null);
        }
        else {
            ratioSummary.setTitle(title);
            ratioSummary.setContent1(content1);
            ratioSummary.setContent2(content2);
            ratioSummary.setErrorTitle(errorTitle);
            ratioSummary.setErrorMessage(errorMessage);
            ratioSummary.setHighestCategoryRatio(highestCategoryRatio);
            ratioSummary.setQueryResult(QR_SUCC_0);
            output.setRatioSummary(ratioSummary);
        }
    }

    /**
     * {消費類別}、{國內/國外}顯示文字：DIC.MEMO (CATEGORY = ‘TRAN_STAT’ AND DIC_VALUE = {消費類別})。若取無則直接顯示"未分類"。 {卡片名稱}顯示文字：CARD_TYPE.CARD_NAME (CARD_TYPE = Rs.categoriesStats.category)。若取無則直接顯示"未分類"
     * 
     * @param category
     * @return
     */
    private String getCategoryDesc(NCCQU010Input input, String category) {
        String defaultCategory = I18NUtils.getMessage("nccqu010.010.label.other-category");

        if (StringUtils.equals(input.getCategoryGroup(), NCCQU010CategoryType.CARD.getType())) {
            String cardName = getCardName(input, category);
            return !StringUtils.equals(cardName, category) ? cardName : defaultCategory;

        }
        else {
            DicData dicData = dicCacheManager.getDicListByCategory(TRAN_STAT).stream().filter(x -> StringUtils.equals(x.getDicValue(), category)).findFirst().orElse(null);
            return dicData != null ? dicData.getMemo() : defaultCategory;
        }
    }

    /**
     * {消費類別} / {國內/國外}：DIC.MEMO (CATEGORY = ‘TRAN_STAT’ AND DIC_VALUE = {消費類別})。若取無則直接顯示Rs.categoriesStats.category
     *
     * @param category
     * @return
     */
    private int getCategoryIndex(NCCQU010Input input, String category) {
        if (StringUtils.equals(input.getCategoryGroup(), NCCQU010CategoryType.CARD.getType())) {
            return 11;
        }

        DicData dicData = dicCacheManager.getDicListByCategory(TRAN_STAT).stream().filter(x -> StringUtils.equals(x.getDicValue(), category)).findFirst().orElse(null);
        return dicData != null ? dicData.getIndexNo() : 11;
    }

    /**
     * 取得信用卡名稱
     * 
     * @param input
     * @param cardType
     * @return
     */
    private String getCardName(NCCQU010Input input, String cardType) {
        CardType cardTypeModel = cardTypeCacheManager.getCardType(cardType, input.getLocale());
        return cardTypeModel != null ? cardTypeModel.getCardTypeName() : cardType;
    }

    /**
     * 取得信用卡 Image URL: (1) {卡片類型}資料來源：各消費類別金額Rs.categoriesStats.category (2) 卡面路徑：CARD_PICTURE.IMAGE_URL (CARD_TYPE = {卡片類型})
     * 
     * @return
     */
    private String getCardImageUrl(String cardType) {
        return cardPictureCacheManager.getCardPicture(cardType);
    }

    private void setDistributionSummary(NCCQU010Input input, NCCQU010Output output) {
        String title;
        String content1;
        String content2;
        String errorTitle = null;
        String errorMessage = null;

        ConsumptionSummaryRes consumptionSummary = output.getConsumptionSummary();

        NCCQU010CardSummary distributionSummary = output.getDistributionSummary();
        if (consumptionSummary == null || distributionSummary.getQueryResult() == 1) {
            distributionSummary.setQueryResult(QR_FAIL_1);
            output.setDistributionSummary(distributionSummary);
            return;
        }
        HighestAmt currentMonthHighestAmtData = consumptionSummary.getHighestAmt();

        if (currentMonthHighestAmtData == null || currentMonthHighestAmtData.getTxnNtdAmt().compareTo(BigDecimal.ZERO) <= 0) { // 單筆消費最高金額} <=0 (無消費或僅有負向消費)，顯示G樣式
            title = I18NUtils.getMessage("nccqu010.010.distribtuon.calc-exception.title");
            content1 = "--";
            // {分析月} 月 無新增消費
            content2 = I18NUtils.getMessage("nccqu010.010.label.month", DateUtils.getMonthShortName(utils.getMonthValue(input.getEndYearMonth()), input.getLocale())) + I18NUtils.getMessage("nccqu010.010.distribtuon.calc-exception.content2");

        }
        else { // 若{單筆消費最高金額} > 0，顯示F樣式
               // {分析月} 月 單筆消費最高
            title = I18NUtils.getMessage("nccqu010.010.label.month", DateUtils.getMonthShortName(utils.getMonthValue(input.getEndYearMonth()), input.getLocale())) + I18NUtils.getMessage("nccqu010.010.distribtuon.highest-month-consumption.title");
            content1 = "$" + IBUtils.formatMoney(currentMonthHighestAmtData.getTxnNtdAmt(), "TWD");
            // {消費日期}+<空格>+{消費說明}
            content2 = currentMonthHighestAmtData.getDate() + " " + currentMonthHighestAmtData.getMerchant();
        }

        if (StringUtils.isEmpty(content1)) {
            output.setDistributionSummary(null);
        }
        else {
            distributionSummary.setTitle(title);
            distributionSummary.setContent1(content1);
            distributionSummary.setContent2(content2);
            distributionSummary.setErrorTitle(errorTitle);
            distributionSummary.setErrorMessage(errorMessage);
            distributionSummary.setQueryResult(QR_SUCC_0);
            output.setDistributionSummary(distributionSummary);
        }
    }

    /**
     * 計算最近 半年 & 一年 內資料
     * 
     * @param input
     * @param output
     */
    public void calculateConsumptionData(NCCQU010Input input, NCCQU010Output output) {
        ConsumptionSummaryRes consumptionSummary = output.getConsumptionSummary();

        // 近半年
        List<String> latestSixMonths = new ArrayList<>();
        for (int i = 0; i < input.getLatestYearAnalysisYearMonths().size() / 2; i++) {
            latestSixMonths.add(input.getLatestYearAnalysisYearMonths().get(i));
        }

        output.setLatestSixMonthYearMonths(latestSixMonths);

        List<ChargesStats> latestSixMonthConsumptionData = consumptionSummary.getChargeStats().stream().filter(x -> latestSixMonths.contains(x.getYearMonth())).collect(Collectors.toList());
        BigDecimal latestSixMonthSum = latestSixMonthConsumptionData.stream().filter(Objects::nonNull).map(ChargesStats::getTxnAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal latestSixMonthAvg = CollectionUtils.isEmpty(latestSixMonthConsumptionData) ? new BigDecimal(0) : latestSixMonthSum.divide(new BigDecimal(6), 0, RoundingMode.HALF_UP);

        output.setLatestSixMonthConsumptionSummaryData(latestSixMonthConsumptionData);
        output.setLatestSixMonthConsumptionAvg(latestSixMonthAvg);

        // 近1年
        List<ChargesStats> latestYearConsumptionData = consumptionSummary.getChargeStats().stream().filter(x -> input.getLatestYearAnalysisYearMonths().contains(x.getYearMonth())).collect(Collectors.toList());
        BigDecimal latestYearSum = latestYearConsumptionData.stream().filter(Objects::nonNull).map(ChargesStats::getTxnAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal latestYearAvg = CollectionUtils.isEmpty(latestYearConsumptionData) ? new BigDecimal(0) : latestYearSum.divide(new BigDecimal(12), 0, RoundingMode.HALF_UP);

        output.setLatestYearConsumptionSummaryData(latestYearConsumptionData);
        output.setLatestYearConsumptionAvg(latestYearAvg);

        // 近2年
        output.setLatestTwoYearConsumptionData(consumptionSummary.getChargeStats());
    }

    /**
     * 取得每月消費匯總資料 020
     * 
     * @param input
     * @param output
     */
    public void getMonthConsumptionData(NCCQU010Input input, NCCQU010Output output) {
        List<NCCQU010MonthConsumptionData> monthConsumtpionData = new ArrayList<>();

        for (String yearMonth : input.getLatestYearAnalysisYearMonths()) {
            NCCQU010MonthConsumptionData data = new NCCQU010MonthConsumptionData();

            data.setMonth(utils.getMonthValue(yearMonth));
            data.setYearMonth(yearMonth);
            data.setYearMonthDisplay(I18NUtils.getMessage("nccqu010.010.label.month", DateUtils.getMonthShortName(utils.getMonthValue(yearMonth), input.getLocale())));

            List<ChargesStats> currentMonthConsumptionData = input.getLatestTwoYearConsumptionData().stream().filter(y -> StringUtils.equals(y.getYearMonth(), yearMonth)).collect(Collectors.toList());
            BigDecimal currentMonthSum = currentMonthConsumptionData.stream().filter(Objects::nonNull).map(ChargesStats::getTxnAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
            String sign = currentMonthSum.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "-";
            data.setConsumptionAmtDisplay(sign + "$" + IBUtils.formatMoney(currentMonthSum.abs(), "TWD"));
            data.setConsumptionAmt(currentMonthSum);

            if (currentMonthSum.compareTo(BigDecimal.ZERO) >= 0) { // {分析月消費總額} >= 0

                // 近半年
                if (input.getLatestSixMonthConsumptionAvg().compareTo(BigDecimal.ZERO) == 0) {
                    data.setLatestHalfYearAvgRatioDisplay("--");
                }
                else {
                    BigDecimal latestHalfYearRatio = BigDecimal.ZERO;
                    if (input.getLatestSixMonthConsumptionAvg().compareTo(BigDecimal.ZERO) > 0) {
                        latestHalfYearRatio = ((currentMonthSum.subtract(input.getLatestSixMonthConsumptionAvg())).divide(input.getLatestSixMonthConsumptionAvg(), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                    }
                    data.setLatestHalfYearRatio(latestHalfYearRatio);
                    sign = latestHalfYearRatio.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "-";
                    data.setLatestHalfYearAvgRatioDisplay(sign + ConvertUtils.bigDecimal2Str(latestHalfYearRatio.abs()) + "%");

                }

                // 前一年
                if (input.getLatestYearConsumptionAvg().compareTo(BigDecimal.ZERO) == 0) {
                    data.setLatestYearAvgRatioDisplay("--");
                }
                else {
                    BigDecimal latestYearRatio = BigDecimal.ZERO;
                    if (input.getLatestYearConsumptionAvg().compareTo(BigDecimal.ZERO) > 0) {
                        latestYearRatio = ((currentMonthSum.subtract(input.getLatestYearConsumptionAvg())).divide(input.getLatestYearConsumptionAvg(), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                    }

                    data.setLatestYearRatio(latestYearRatio);
                    sign = latestYearRatio.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "-";
                    data.setLatestYearAvgRatioDisplay(sign + ConvertUtils.bigDecimal2Str(latestYearRatio.abs()) + "%");

                }

                String lastYearSameMonth = utils.getPreviousMonthFromNow(yearMonth, -12);
                List<ChargesStats> lastYearSameMonthConsumptionData = input.getLatestTwoYearConsumptionData().stream().filter(y -> StringUtils.equals(y.getYearMonth(), lastYearSameMonth)).collect(Collectors.toList());
                BigDecimal lastYearSameMonthSum = lastYearSameMonthConsumptionData.stream().filter(Objects::nonNull).map(ChargesStats::getTxnAmt).reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal yearOnYearRatio = BigDecimal.ZERO;
                if (lastYearSameMonthSum.compareTo(BigDecimal.ZERO) > 0) {
                    yearOnYearRatio = ((currentMonthSum.subtract(lastYearSameMonthSum)).divide(lastYearSameMonthSum, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                    data.setYearOnYearRatio(yearOnYearRatio);
                    sign = yearOnYearRatio.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "-";
                    data.setYearOnYearRatioDisplay(sign + ConvertUtils.bigDecimal2Str(yearOnYearRatio.abs()) + "%");
                }
                else {
                    data.setYearOnYearRatio(BigDecimal.ZERO);
                    data.setYearOnYearRatioDisplay("--");
                }

            }

            monthConsumtpionData.add(data);
        }

        List<NCCQU010MonthConsumptionData> latestSixMonthConsumptionData = monthConsumtpionData.stream().filter(x -> input.getLatestSixMonthYearMonths().contains(x.getYearMonth())).collect(Collectors.toList());
        output.setLatestSixMonthConsumptionData(latestSixMonthConsumptionData);
        output.setLatestYearMonthConsumptionData(monthConsumtpionData);
    }

    /**
     * 取得每月各類別消費資料 030
     * 
     * @param input
     * @param output
     */
    public void getMonthCategoryData(NCCQU010Input input, NCCQU010Output output) {
        List<NCCQU010MonthCategoryData> monthCategoryData = new ArrayList<>();

        Set<String> itemCategories = input.getLatestYearCategoryData().stream().map(CategoryStats::getCategory).collect(Collectors.toSet());

        getLatestSizMonthAnalysisMonths(output);

        // By 項目區分，合計6個月資料
        Map<String, BigDecimal> sumOf6Month = new HashMap<String, BigDecimal>();

        for (CategoryStats s : input.getLatestYearCategoryData()) {
            if ("utilities".equals(s.getCategory())) {
                logger.info("{} {} {}", s.getCategory(), s.getYearMonth(), s.getTxnAmt());
            }
        }

        for (CategoryStats data : input.getLatestYearCategoryData()) {
            for (String yearMonth : output.getLatestSixMonthYearMonths()) {
                if (!StringUtils.equals(yearMonth, data.getYearMonth())) {
                    continue;
                }
                if ("utilities".equals(data.getCategory())) {
                    logger.info("add : {}", data.getTxnAmt());
                }
                BigDecimal sum = sumOf6Month.get(data.getCategory());
                if (sum == null) {
                    sum = BigDecimal.ZERO;
                }
                sum = sum.add(data.getTxnAmt());
                sumOf6Month.put(data.getCategory(), sum);
            }
        }

        for (Map.Entry<String, BigDecimal> entry : sumOf6Month.entrySet()) {
            String key = entry.getKey();
            BigDecimal value = entry.getValue();
            sumOf6Month.put(key, value.divide(new BigDecimal(6), 2, RoundingMode.HALF_UP));
        }

        for (String yearMonth : input.getLatestYearAnalysisYearMonths()) {
            NCCQU010MonthCategoryData monthData = new NCCQU010MonthCategoryData();

            if (StringUtils.isNotEmpty(input.getSelectedYearMonth())) {
                if (!StringUtils.equals(input.getSelectedYearMonth(), yearMonth)) {
                    continue;
                }
            }

            monthData.setCategoryGroup(input.getCategoryGroup());
            monthData.setMonth(utils.getMonthValue(yearMonth));
            monthData.setYearMonth(yearMonth);
            monthData.setYearMonthDisplay(I18NUtils.getMessage("nccqu010.010.label.month", DateUtils.getMonthShortName(utils.getMonthValue(yearMonth), input.getLocale())));

            List<CategoryStats> currentMonthCategoryData = input.getLatestYearCategoryData().stream().filter(y -> StringUtils.equals(y.getYearMonth(), yearMonth)).collect(Collectors.toList());
            BigDecimal currentMonthSum = currentMonthCategoryData.stream().filter(Objects::nonNull).map(CategoryStats::getTxnAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
            String sign = currentMonthSum.compareTo(BigDecimal.ZERO) < 0 ? "-" : "";
            String moneySign = currentMonthSum.compareTo(BigDecimal.ZERO) != 0 ? "$" : "";
            monthData.setConsumptionAmtDisplay(sign + moneySign + IBUtils.formatMoney(currentMonthSum.abs(), "TWD"));
            monthData.setConsumptionAmt(currentMonthSum);

            if (currentMonthSum.compareTo(BigDecimal.ZERO) >= 0) { // {分析月消費總額} >= 0
                if (input.getLatestSixMonthConsumptionAvg().compareTo(BigDecimal.ZERO) == 0) {
                    monthData.setLatestHalfYearAvgRatioDisplay("--");
                }
                else {
                    BigDecimal latestHalfYearRatio = ((currentMonthSum.subtract(input.getLatestSixMonthConsumptionAvg())).divide(input.getLatestSixMonthConsumptionAvg(), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                    monthData.setLatestHalfYearRatio(latestHalfYearRatio);
                    sign = latestHalfYearRatio.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "-";
                    monthData.setLatestHalfYearAvgRatioDisplay(sign + ConvertUtils.bigDecimal2Str(latestHalfYearRatio.abs()) + "%");
                }

                if (input.getLatestYearConsumptionAvg().compareTo(BigDecimal.ZERO) == 0) {
                    monthData.setLatestYearAvgRatioDisplay("--");
                }
                else {
                    BigDecimal latestYearRatio = ((currentMonthSum.subtract(input.getLatestYearConsumptionAvg())).divide(input.getLatestYearConsumptionAvg(), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                    monthData.setLatestYearRatio(latestYearRatio);
                    sign = latestYearRatio.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "-";
                    monthData.setLatestYearAvgRatioDisplay(sign + ConvertUtils.bigDecimal2Str(latestYearRatio.abs()) + "%");
                }

                String lastYearSameMonth = utils.getPreviousMonthFromNow(yearMonth, -12);
                List<ChargesStats> lastYearSameMonthConsumptionData = input.getLatestTwoYearConsumptionData().stream().filter(y -> StringUtils.equals(y.getYearMonth(), lastYearSameMonth)).collect(Collectors.toList());
                BigDecimal lastYearSameMonthSum = lastYearSameMonthConsumptionData.stream().filter(Objects::nonNull).map(ChargesStats::getTxnAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal yearOnYearRatio = BigDecimal.ZERO;
                if (lastYearSameMonthSum.compareTo(BigDecimal.ZERO) > 0) {
                    yearOnYearRatio = ((currentMonthSum.subtract(lastYearSameMonthSum)).divide(lastYearSameMonthSum, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                }
                monthData.setYearOnYearRatio(yearOnYearRatio);
                sign = yearOnYearRatio.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "-";
                monthData.setYearOnYearRatioDisplay(sign + ConvertUtils.bigDecimal2Str(yearOnYearRatio.abs()) + "%");
            }

            List<NCCQU010CategoryRatioData> categoryRatioData = new ArrayList<>();

            for (String itemCategory : itemCategories) {
                NCCQU010CategoryRatioData ratioData = new NCCQU010CategoryRatioData();

                if (StringUtils.equals(input.getCategoryGroup(), NCCQU010CategoryType.CARD.getType())) {
                    ratioData.setItemImgUrl(getCardImageUrl(itemCategory));
                }
                ratioData.setItemDesc(getCategoryDesc(input, itemCategory));
                ratioData.setItemCategory(itemCategory);
                ratioData.setItemCategoryIndex(getCategoryIndex(input, itemCategory));

                List<CategoryStats> categoryConsumptionData = currentMonthCategoryData.stream().filter(x -> StringUtils.equals(x.getCategory(), itemCategory)).collect(Collectors.toList());
                BigDecimal categoryConsumptionSum = categoryConsumptionData.stream().filter(Objects::nonNull).map(CategoryStats::getTxnAmt).reduce(BigDecimal.ZERO, BigDecimal::add);

                if (categoryConsumptionSum.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }

                if (categoryConsumptionSum.compareTo(BigDecimal.ZERO) > 0) { // {項目消費總額} > 0
                    BigDecimal categoryRatio = (categoryConsumptionSum.divide(currentMonthSum, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                    ratioData.setRatioDisplay(ConvertUtils.bigDecimal2Str(categoryRatio) + "%");
                    ratioData.setRatio(categoryRatio);
                }

                sign = categoryConsumptionSum.compareTo(BigDecimal.ZERO) < 0 ? "-" : "";
                ratioData.setTxnAmtDisplay(sign + "$" + IBUtils.formatMoney(categoryConsumptionSum.abs(), "TWD"));
                ratioData.setTxnAmt(categoryConsumptionSum);

                if (categoryConsumptionSum.compareTo(BigDecimal.ZERO) < 0) {
                    monthData.setHasMinusAmt(true);
                }

                ratioData.setAverageRatio(moneySign);

                ratioData.setAverageRatio(getAverageRatio(ratioData.getTxnAmt(), itemCategory, sumOf6Month));
                ratioData.setLastYearRatio(getLastYearRatio(ratioData.getTxnAmt(), yearMonth, itemCategory, input.getLatestTwoYearCategoryData()));
                categoryRatioData.add(ratioData);
            }

            IBUtils.sort(categoryRatioData, new String[] { "ratioDisplay", "txnAmtDisplay" }, new boolean[] { true, true }, true);
            monthData.setCategoryRatioData(categoryRatioData);

            // 剩餘項目
            if (categoryRatioData.size() > 4) {
                List<NCCQU010CategoryRatioData> otherCategoryRatioData = new ArrayList<>();
                for (int i = 0; i < categoryRatioData.size(); i++) {
                    if (i < 4) {
                        continue;
                    }
                    otherCategoryRatioData.add(categoryRatioData.get(i));
                }

                BigDecimal otherCategoryConsumptionSum = otherCategoryRatioData.stream().filter(Objects::nonNull).map(NCCQU010CategoryRatioData::getTxnAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal otherCategoryRatio = (otherCategoryConsumptionSum.divide(currentMonthSum, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                monthData.setOtherRatioDisplay(ConvertUtils.bigDecimal2Str(otherCategoryRatio) + "%");
                monthData.setOtherItemCountDisplay("...+" + (categoryRatioData.size() - 3));
            }

            monthCategoryData.add(monthData);
        }

        output.setMonthCategoryData(monthCategoryData);
    }

    /**
     * 取過去 2 年的各消費類別金額查詢
     * 
     * @param aiBankUser
     * @param input
     * @param output
     */
    public void getLatestTwoYearCategoryData(AIBankUser aiBankUser, NCCQU010Input input, NCCQU010Output output) {

        CategoryReq req = new CategoryReq();
        req.setCustId(aiBankUser.getCustId());
        req.setStartYearMonth(getYearMonth(input.getEndYearMonth(), -23));
        req.setEndYearMonth(input.getEndYearMonth());
        req.setCategoryGroup(input.getCategoryGroup());

        CategoryRes res = investResource.getCategoryConsumption(req);
        output.getRatioSummary().setQueryResult(res.getQueryResult());
        output.setLatestTwoYearCategoryData(res.getCategoryStats());
        output.setCategoryGroup(input.getCategoryGroup());

    }

    /**
     * diff 月份差距 0 等於當月
     * 
     * @param diff
     * @return
     */
    public String getYearMonth(String yearMonth, int diff) {
        Date current = new Date();
        try {
            current = DateFormatUtils.SQL_YEAR_MONTH_FORMAT.parse(yearMonth);
        }
        catch (ParseException e) {
            logger.warn("截止日錯誤", e);
        }

        Calendar cal = Calendar.getInstance();

        cal.setTime(current);

        cal.add(Calendar.MONTH, diff);

        return DateFormatUtils.SQL_YEAR_MONTH_FORMAT.format(cal.getTime());

    }

    /**
     * 算與6月平均的比值
     * 
     * @param txnAmt
     * @param category
     * @param sumOf6Month
     * @return
     */
    private String getAverageRatio(BigDecimal txnAmt, String category, Map<String, BigDecimal> sumOf6Month) {

        if (txnAmt.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }

        BigDecimal average = sumOf6Month.get(category);

        if (average == null || average.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }

        BigDecimal diff = txnAmt.subtract(average);

        BigDecimal categoryRatio = (diff.divide(average, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);

        if (categoryRatio.compareTo(BigDecimal.ZERO) >= 0) {
            return "+" + ConvertUtils.bigDecimal2Str(categoryRatio) + "%";
        }
        else {
            return ConvertUtils.bigDecimal2Str(categoryRatio) + "%";
        }

    }

    private String getLastYearRatio(BigDecimal txnAmt, String yearMonth, String category, List<CategoryStats> datas) {

        String lastYearMonth = getLastYear(yearMonth);
        for (CategoryStats data : datas) {
            if (StringUtils.equals(lastYearMonth, data.getYearMonth()) && StringUtils.equals(category, data.getCategory())) {

                if (txnAmt == null || txnAmt.compareTo(BigDecimal.ZERO) == 0 || data.getTxnAmt() == null || data.getTxnAmt().compareTo(BigDecimal.ZERO) == 0) {
                    return "--";
                }

                BigDecimal diff = txnAmt.subtract(data.getTxnAmt());

                BigDecimal categoryRatio = (diff.divide(data.getTxnAmt(), 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
                if (categoryRatio.compareTo(BigDecimal.ZERO) >= 0) {
                    return "+" + ConvertUtils.bigDecimal2Str(categoryRatio) + "%";
                }
                else {
                    return ConvertUtils.bigDecimal2Str(categoryRatio) + "%";
                }
            }
        }
        return "--";
    }

    private String getLastYear(String yearMonth) {
        try {
            Date current = DateFormatUtils.SQL_YEAR_MONTH_FORMAT.parse(yearMonth);

            Calendar cal = Calendar.getInstance();
            cal.setTime(current);
            cal.add(Calendar.YEAR, -1);

            return DateFormatUtils.SQL_YEAR_MONTH_FORMAT.format(cal.getTime());

        }
        catch (ParseException e) {
            logger.warn("", e);
        }
        return yearMonth;
    }

    /**
     * 取得指定月份指定類別消費明細資料 031 040
     * 
     * @param input
     * @param output
     */
    public void getMonthDetailData(NCCQU010Input input, NCCQU010Output output) {
        NCCQU010MonthDetailData monthData = new NCCQU010MonthDetailData();
        String yearMonth = input.getSelectedYearMonth();
        List<CategoryDetailResRep> categoryDetails = input.getCategoryDetails();

        monthData.setItemCategory(getCategoryDesc(input, input.getItemCategory()));
        monthData.setMonth(utils.getMonthValue(yearMonth));
        monthData.setYearMonth(yearMonth);
        monthData.setYearMonthDisplay(I18NUtils.getMessage("nccqu010.010.label.month", DateUtils.getMonthShortName(utils.getMonthValue(yearMonth), input.getLocale())));
        monthData.setConsumptionDayCountDisplay(categoryDetails.stream().map(CategoryDetailResRep::getDate).collect(Collectors.toSet()).size() + I18NUtils.getMessage("nccqu010.label.day"));
        monthData.setDetailItemCountDisplay(I18NUtils.getMessage("nccqu010.label.total") + " " + categoryDetails.size() + " " + I18NUtils.getMessage("nccqu010.label.records"));

        Date middleDayOfMonth = getSpecifiedDayOfMonth(DateUtils.getYearMonthDate(yearMonth), 16);

        // 各類別消費明細
        List<NCCQU010CategoryDetailData> categoryDetailData = new ArrayList<>();
        for (CategoryDetailResRep detail : categoryDetails) {
            NCCQU010CategoryDetailData detailData = new NCCQU010CategoryDetailData();
            detailData.setFocusOnFirstHalfOfMonth(detail.getDate().before(middleDayOfMonth));
            detailData.setTxnDate(detail.getDate());
            detailData.setTxnDateDisplay(DateFormatUtils.CE_DATE_FORMAT.format(detail.getDate()));
            detailData.setItem(detail.getMerchant());
            detailData.setTxnNtdAmt(detail.getTxnNtdAmt());
            String sign = detail.getTxnNtdAmt().compareTo(BigDecimal.ZERO) < 0 ? "-" : "";
            detailData.setTxnAmtDisplay(sign + "$" + IBUtils.formatMoney(detail.getTxnNtdAmt().abs(), "TWD"));

            List<CategoryDetailResRep> currentDateData = categoryDetails.stream().filter(y -> y.getDate().compareTo(detail.getDate()) == 0).collect(Collectors.toList());
            detailData.setDetailItemCountDisplay(I18NUtils.getMessage("nccqu010.label.total") + " " + currentDateData.size() + " " + I18NUtils.getMessage("nccqu010.label.records"));

            BigDecimal currentDateSum = currentDateData.stream().filter(Objects::nonNull).map(CategoryDetailResRep::getTxnNtdAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
            sign = currentDateSum.compareTo(BigDecimal.ZERO) < 0 ? "-" : "";
            detailData.setTxnAmtSumDisplay(sign + "$" + IBUtils.formatMoney(currentDateSum.abs(), "TWD"));

            NCCQU010PaymentInfo paymentInfo = new NCCQU010PaymentInfo();
            // Rs.accountingDate
            paymentInfo.setAccountingDate(detail.getAccountingDate());
            // {信用卡名稱}：Rs.card
            paymentInfo.setCard(getCardName(input, detail.getCard()));
            // {信用卡卡號後4碼}: Rs.lastFourDigits
            paymentInfo.setLastFourDigits(detail.getLastFourDigits());
            // Rs.payChannel，若為null時不顯示此欄位
            paymentInfo.setPayMethod(detail.getPayChannel());
            // Rs.vDigits，若為null時不顯示此欄位
            paymentInfo.setVirtualCardLast4Digits(detail.getvDigits());
            // {幣別名稱}：Rs.txnCurrency, {外幣金額}：Rs.txnAmt 若兩者其中之一為null時不顯示此欄位
            if (!StringUtils.isEmpty(detail.getTxnCurrency()) && detail.getTxnAmt() != null) {
                CurrencyCode currencyCode = currencyCodeCacheManager.getCurrencyCode(detail.getTxnCurrency(), input.getLocale());
                String money = IBUtils.formatMoney(detail.getTxnAmt(), detail.getTxnCurrency());
                String foreignAmtDisplay = Optional.ofNullable(currencyCode).filter(cc -> StringUtils.isNotBlank(cc.getCurrencyName())).map(cc -> String.format(FOREIGN_AMT_DISPLAY_TEMPL, cc.getCurrencyName(), money)).orElse(String.format(FOREIGN_AMT_DISPLAY_TEMPL, detail.getTxnCurrency(), money));
                paymentInfo.setForeignAmtDisplay(foreignAmtDisplay);
            }
            detailData.setPaymentInfo(paymentInfo);

            categoryDetailData.add(detailData);
        }

        IBUtils.sort(categoryDetailData, "txnDate", true);
        monthData.setCategoryDetailData(categoryDetailData);

        NCCQU010CategoryDetailData highestConsumptionData = categoryDetailData.stream().max(Comparator.comparing(x -> ConvertUtils.str2BigDecimal(x.getTxnAmtSumDisplay()))).orElse(null);
        monthData.setHighestConsumptionDateDisplay((highestConsumptionData == null || ConvertUtils.str2BigDecimal(highestConsumptionData.getTxnAmtSumDisplay()).compareTo(BigDecimal.ZERO) < 0) ? "--" : DateUtils.getDateTimeStrByDateFormat(highestConsumptionData.getTxnDate(), "MM/dd"));

        List<NCCQU010CategoryDetailData> firstHalfOfMonthData = categoryDetailData.stream().filter(NCCQU010CategoryDetailData::getFocusOnFirstHalfOfMonth).collect(Collectors.toList());
        List<NCCQU010CategoryDetailData> secondHalfOfMonthData = categoryDetailData.stream().filter(x -> !x.getFocusOnFirstHalfOfMonth()).collect(Collectors.toList());
        BigDecimal firstHalfOfMonthSum = firstHalfOfMonthData.stream().filter(Objects::nonNull).map(NCCQU010CategoryDetailData::getTxnNtdAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal secondHalfOfMonthSum = secondHalfOfMonthData.stream().filter(Objects::nonNull).map(NCCQU010CategoryDetailData::getTxnNtdAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
        int compareResult = firstHalfOfMonthSum.compareTo(secondHalfOfMonthSum);
        monthData.setFocusOnFirstHalfOfMonth(compareResult >= 0);

        BigDecimal currentMonthSum = categoryDetailData.stream().filter(Objects::nonNull).map(NCCQU010CategoryDetailData::getTxnNtdAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal halfOfMonthSum;
        BigDecimal ratio;
        if (compareResult >= 0) {
            halfOfMonthSum = firstHalfOfMonthSum;
            ratio = ((firstHalfOfMonthSum.subtract(currentMonthSum)).divide(BigDecimal.ZERO.equals(currentMonthSum) ? BigDecimal.ONE : currentMonthSum, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);

        }
        else {
            halfOfMonthSum = secondHalfOfMonthSum;
            ratio = ((secondHalfOfMonthSum.subtract(currentMonthSum)).divide(BigDecimal.ZERO.equals(currentMonthSum) ? BigDecimal.ONE : currentMonthSum, 2, RoundingMode.HALF_UP)).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
        }
        String sign = halfOfMonthSum.compareTo(BigDecimal.ZERO) < 0 ? "-" : "";
        String halfOfMonthSumDisplay = sign + "$" + IBUtils.formatMoney(halfOfMonthSum.abs(), "TWD");
        String ratioDisplay = ConvertUtils.bigDecimal2Str(ratio) + "%";

        // 上半個月 / 下半個月
        String firstOrSecondHalfOfMonth = compareResult >= 0 ? I18NUtils.getMessage("nccqu010.040.label.first-half-of-month") : I18NUtils.getMessage("nccqu010.040.label.second-half-of-month");
        // 共消費
        firstOrSecondHalfOfMonth = firstOrSecondHalfOfMonth + I18NUtils.getMessage("nccqu010.040.label.sum");

        monthData.setHalfOfMonthAmtAndRatioDisplay(firstOrSecondHalfOfMonth + " " + halfOfMonthSumDisplay + " " + I18NUtils.getMessage("nccqu010.040.label.ratio-on-month") + " " + ratioDisplay);

        output.setMonthDetailData(monthData);
        if (CollectionUtils.isNotEmpty(categoryDetailData)) {
            output.setQueryResult(QR_SUCC_0);
        }
        else {
            output.setQueryResult(QR_NODATA_3);
        }
    }

    private void handleData(AnnualDetailRes res, NCCQU010Input input, NCCQU010Output output) {
        if (CollectionUtils.isEmpty(res.getAnnualDetails())) {
            return;
        }

        List<NCCQU010AnnualDetailData> annualDetails = res.getAnnualDetails().stream().map(data -> {
            NCCQU010AnnualDetailData detail = new NCCQU010AnnualDetailData();

            if (null == data.getDate()) {
                return detail;
            }

            setTxDateDisplay(detail, data, input.getLocale());

            detail.setItemName(data.getMerchant());
            detail.setTxnNtdAmt(data.getTxnNtdAmt());

            BigDecimal txnNtdAmt = data.getTxnNtdAmt();
            String sign = txnNtdAmt.compareTo(BigDecimal.ZERO) < 0 ? "-" : "";
            detail.setTxAmtDisplay(sign + "$" + IBUtils.formatMoney(txnNtdAmt.abs(), "TWD"));

            NCCQU010PaymentInfo paymentInfo = new NCCQU010PaymentInfo();
            // Rs.accountingDate
            paymentInfo.setAccountingDate(data.getAccountingDate());
            // {信用卡名稱}：Rs.card
            paymentInfo.setCard(getCardName(input, data.getCard()));
            // {信用卡卡號後4碼}: Rs.lastFourDigits
            paymentInfo.setLastFourDigits(data.getLastFourDigits());
            // Rs.payChannel，若為null時不顯示此欄位
            paymentInfo.setPayMethod(data.getPayChannel());
            // Rs.vDigits，若為null時不顯示此欄位
            paymentInfo.setVirtualCardLast4Digits(data.getvDigits());
            // {幣別名稱}：Rs.txnCurrency, {外幣金額}：Rs.txnAmt 若兩者其中之一為null時不顯示此欄位
            if (!StringUtils.isEmpty(data.getTxnCurrency()) && data.getTxnAmt() != null) {
                CurrencyCode currencyCode = currencyCodeCacheManager.getCurrencyCode(data.getTxnCurrency(), input.getLocale());
                String money = IBUtils.formatMoney(data.getTxnAmt(), data.getTxnCurrency());
                String foreignAmtDisplay = Optional.ofNullable(currencyCode).filter(cc -> StringUtils.isNotBlank(cc.getCurrencyName())).map(cc -> String.format(FOREIGN_AMT_DISPLAY_TEMPL, cc.getCurrencyName(), money)).orElse(String.format(FOREIGN_AMT_DISPLAY_TEMPL, data.getTxnCurrency(), money));
                paymentInfo.setForeignAmtDisplay(foreignAmtDisplay);
            }

            detail.setPaymentInfo(paymentInfo);
            return detail;

        }).collect(Collectors.toList());

        List<NCCQU010AnnualDetailData> foundAnnualDetails = annualDetails.stream().filter(x -> x.getItemName().contains(input.getSearchKeyword())).collect(Collectors.toList());
        calcSearchItemSum(foundAnnualDetails, output);

        IBUtils.sort(annualDetails, "txDate", true);
        output.setAnnualDetails(annualDetails);
    }

    /**
     * 計算搜尋項目總額
     * 
     * @param foundAnnualDetails
     * @param output
     */
    public void calcSearchItemSum(List<NCCQU010AnnualDetailData> foundAnnualDetails, NCCQU010Output output) {
        BigDecimal sum = foundAnnualDetails.stream().filter(Objects::nonNull).map(NCCQU010AnnualDetailData::getTxnNtdAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
        String sign = sum.compareTo(BigDecimal.ZERO) < 0 ? "-" : "";
        output.setTxnAmtSumDisplay(I18NUtils.getMessage("nccqu010.060.label.total-found") + " " + foundAnnualDetails.size() + " " + I18NUtils.getMessage("nccqu010.label.records") + I18NUtils.getMessage("nccqu010.060.label.sum") + " " + sign + "$" + IBUtils.formatMoney(sum.abs(), "TWD"));
    }

    private void setTxDateDisplay(NCCQU010AnnualDetailData detail, AnnualDetailResRep data, Locale locale) {
        if (null == data.getDate()) {
            return;
        }

        detail.setTxDate(data.getDate());
        detail.setTxDateDisplay(DateFormatUtils.format(data.getDate(), "MM/dd"));
        detail.setTxDateYYYYMM(DateFormatUtils.format(data.getDate(), "yyyyMM"));
        detail.setTxYear(DateFormatUtils.format(data.getDate(), "yyyy"));
        detail.setMonthNum(String.valueOf(DateUtils.getMonthIntFromDate(data.getDate())));

        LocalDateTime ldt = LocalDateTime.ofInstant(data.getDate().toInstant(), ZoneId.systemDefault());
        final DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM", locale);
        detail.setMonthDisplay(ldt.format(monthFormatter));
    }

    /**
     * 取得時間區間清單
     * 
     * @param output
     */
    public void getPeriods(NCCQU010Output output) {
        List<LabelValueBean> periods = new ArrayList<>();
        // 近半年
        periods.add(new LabelValueBean(I18NUtils.getMessage("nccqu010.020.label.period.latest-half-year"), "0"));
        // 近一年
        periods.add(new LabelValueBean(I18NUtils.getMessage("nccqu010.020.label.period.latest-year"), "1"));
        output.setPeriods(periods);
    }

    /**
     * 取得分析月清單
     * 
     * @param input
     * @param output
     */
    public void getYearMonths(NCCQU010Input input, NCCQU010Output output) {
        List<NCCQU010DateInfo> yearMonths = new ArrayList<>();

        for (String yearMonth : input.getLatestYearAnalysisYearMonths()) {
            NCCQU010DateInfo dateInfo = new NCCQU010DateInfo();
            dateInfo.setLabel(I18NUtils.getMessage("nccqu010.010.label.month", DateUtils.getMonthShortName(utils.getMonthValue(yearMonth), input.getLocale())));
            dateInfo.setValue(yearMonth);
            dateInfo.setTxYear(utils.getYearValue(yearMonth));
            dateInfo.setTxMonth(utils.getMonthValue(yearMonth));

            yearMonths.add(dateInfo);
        }

        output.setYearMonths(yearMonths);
    }

    /**
     * 檢查是否有跨去年
     * 
     * @param input
     * @param output
     */
    public void checkYear(NCCQU010Input input, NCCQU010Output output) {
        int currentYear = LocalDate.now().getYear();

        //@formatter:off
        /**
         * 有可能遇到以下情況:
         * 當前日期: 2025-01-02
         * 不過資料日期都是2024, 2023
         * 因此去年應該為2023, 今年為2024, 這樣前端顯示才會合理
         * 如果資料都是2025, 2024, 則前端選擇月份應該為: ["選擇月份", ""]
         */
        //@formatter:on
        List<String> yearMonths = input.getLatestYearAnalysisYearMonths();
        if (CollectionUtils.isNotEmpty(yearMonths)) {
            List<Integer> yearMonthsList = yearMonths.stream().map(utils::getYearValue).collect(Collectors.toSet()).stream().sorted((a, b) -> b - a).collect(Collectors.toList());
            yearMonthsList.removeIf(yms -> currentYear == yms);
            if (CollectionUtils.isNotEmpty(yearMonthsList)) {
                output.setHasLastYear(true);

                if (yearMonthsList.size() > 1) {
                    output.setCurrentYearDisplay(I18NUtils.getMessage("nccqu010.label.year", String.valueOf(yearMonthsList.get(0))));
                }
                output.setLastYearDisplay(I18NUtils.getMessage("nccqu010.label.year", String.valueOf(yearMonthsList.get(yearMonthsList.size() - 1))));
            }
        }
    }

    public String getThisYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return Integer.toString(cal.get(Calendar.YEAR));
    }

    /**
     * 取得消費類別清單
     * 
     * @param output
     */
    public void getConsumptionCategories(NCCQU010Output output) {
        List<LabelValueBean> consumptionCategories = new ArrayList<>();
        consumptionCategories.add(new LabelValueBean(I18NUtils.getMessage("nccqu010.030.label.category.life"), NCCQU010CategoryType.LIFE.getType()));
        consumptionCategories.add(new LabelValueBean(I18NUtils.getMessage("nccqu010.030.label.category.region"), NCCQU010CategoryType.REGION.getType()));
        consumptionCategories.add(new LabelValueBean(I18NUtils.getMessage("nccqu010.030.label.category.card"), NCCQU010CategoryType.CARD.getType()));
        output.setConsumptionCategories(consumptionCategories);
    }

    /**
     * 條件式版位是否開啟
     *
     * @param dicKey
     * @param output
     */
    public void getConditionalMessageEnabled(String dicKey, NCCQU010Output output) {
        DicData dic = dicCacheManager.getDicByCategoryAndKey(ANALYSIS_CONDITIONAL_MESSAGE, dicKey);
        output.setConditionalMessageEnabled(StringUtils.isY(dic.getDicValue()));
    }
}
