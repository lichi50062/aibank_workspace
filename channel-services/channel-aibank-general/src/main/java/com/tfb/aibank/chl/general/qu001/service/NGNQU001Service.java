package com.tfb.aibank.chl.general.qu001.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.ArithUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.i18n.I18nModel;
import com.ibm.tw.ibmb.component.menu.Menu;
import com.ibm.tw.ibmb.component.menu.MenuCacheManager;
import com.ibm.tw.ibmb.type.MenuCategory;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.availabletask.AvailableTaskCacheManager;
import com.tfb.aibank.chl.component.availabletask.CcAvalibleTaskEntityVo;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.chl.component.customergroupid.CustomerGroupId;
import com.tfb.aibank.chl.component.customergroupid.CustomerGroupIdCacheManager;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusCondition;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusResult;
import com.tfb.aibank.chl.component.devicebinding.type.UserDeviceBindStatusType;
import com.tfb.aibank.chl.component.exchangerate.ExchangeRateService;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistory;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistoryCacheManager;
import com.tfb.aibank.chl.component.fxinterestrate.FxInterestRate;
import com.tfb.aibank.chl.component.fxinterestrate.FxInterestRateCacheManager;
import com.tfb.aibank.chl.component.fxtranscurrencydiscount.FxTransCurrencyDiscountCacheManager;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.component.homepagecard.HomepageCardCacheManager;
import com.tfb.aibank.chl.component.newfunctionintro.NewFunctionIntro;
import com.tfb.aibank.chl.component.newfunctionintro.NewFunctionIntroCacheManager;
import com.tfb.aibank.chl.component.overview.OverviewService;
import com.tfb.aibank.chl.component.overview.type.GD320140FuncCod;
import com.tfb.aibank.chl.component.overview.type.GD320140Source;
import com.tfb.aibank.chl.component.userdatacache.MutualFundResource;
import com.tfb.aibank.chl.component.userdatacache.model.LoanAccount;
import com.tfb.aibank.chl.component.userdatacache.model.NFEE071Req;
import com.tfb.aibank.chl.component.userdatacache.model.NFEE072Req;
import com.tfb.aibank.chl.component.usualtask.UsualTask;
import com.tfb.aibank.chl.component.usualtask.UsualTaskResource;
import com.tfb.aibank.chl.general.qu001.model.CardDataCreditCard;
import com.tfb.aibank.chl.general.qu001.model.CardDataDeposit;
import com.tfb.aibank.chl.general.qu001.model.CardDataInsurance;
import com.tfb.aibank.chl.general.qu001.model.CardDataInvestment;
import com.tfb.aibank.chl.general.qu001.model.CardDataLoan;
import com.tfb.aibank.chl.general.qu001.model.CardDataStock;
import com.tfb.aibank.chl.general.qu001.model.DataInput;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.model.FundCalculateModel;
import com.tfb.aibank.chl.general.qu001.model.FxInterestRateVo;
import com.tfb.aibank.chl.general.qu001.model.InvestProductTypeDisplayName;
import com.tfb.aibank.chl.general.qu001.model.InvestmentOverview;
import com.tfb.aibank.chl.general.qu001.model.MenuVo;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001Constants;
import com.tfb.aibank.chl.general.qu001.model.ProductType;
import com.tfb.aibank.chl.general.qu001.model.StockCardStateType;
import com.tfb.aibank.chl.general.qu001.model.StockType;
import com.tfb.aibank.chl.general.qu001.model.SystemNotificationRecord;
import com.tfb.aibank.chl.general.qu001.model.TopValueAssetAccount;
import com.tfb.aibank.chl.general.qu001.model.TopValueProduct;
import com.tfb.aibank.chl.general.qu001.model.UsualTaskVo;
import com.tfb.aibank.chl.general.qu001.utils.NGNQU001Util;
import com.tfb.aibank.chl.general.resource.CreditCardResource;
import com.tfb.aibank.chl.general.resource.ForeignExchangeResource;
import com.tfb.aibank.chl.general.resource.InvestResource;
import com.tfb.aibank.chl.general.resource.LoanResource;
import com.tfb.aibank.chl.general.resource.NotificationResource;
import com.tfb.aibank.chl.general.resource.SystemConfigResource;
import com.tfb.aibank.chl.general.resource.dto.AJWEE010Req;
import com.tfb.aibank.chl.general.resource.dto.AiDataSyncStatusModel;
import com.tfb.aibank.chl.general.resource.dto.AiFubonInsurDataResponse;
import com.tfb.aibank.chl.general.resource.dto.AiOtherInsurDataResponse;
import com.tfb.aibank.chl.general.resource.dto.AssetAccount;
import com.tfb.aibank.chl.general.resource.dto.AssetData;
import com.tfb.aibank.chl.general.resource.dto.AssetDetailedData;
import com.tfb.aibank.chl.general.resource.dto.AssetItemTotalData;
import com.tfb.aibank.chl.general.resource.dto.CEW314RResponse;
import com.tfb.aibank.chl.general.resource.dto.CreditCardOverview;
import com.tfb.aibank.chl.general.resource.dto.EB555692Request;
import com.tfb.aibank.chl.general.resource.dto.EB555692Response;
import com.tfb.aibank.chl.general.resource.dto.FubonStockApiRequest;
import com.tfb.aibank.chl.general.resource.dto.FubonStockApiResponse;
import com.tfb.aibank.chl.general.resource.dto.FubonStockTotalApiRequest;
import com.tfb.aibank.chl.general.resource.dto.FubonStockTotalApiResponse;
import com.tfb.aibank.chl.general.resource.dto.GD320140Req;
import com.tfb.aibank.chl.general.resource.dto.GoldPassBook;
import com.tfb.aibank.chl.general.resource.dto.HomeCardLoanRequest;
import com.tfb.aibank.chl.general.resource.dto.HomeCardLoanResponse;
import com.tfb.aibank.chl.general.resource.dto.NJWEE010Req;
import com.tfb.aibank.chl.general.resource.dto.NMP8YBRes;
import com.tfb.aibank.chl.general.resource.dto.NMP8YBResRep;
import com.tfb.aibank.chl.general.resource.dto.PolyInfoItem;
import com.tfb.aibank.chl.general.resource.dto.QuickSearchResponse;
import com.tfb.aibank.chl.general.resource.dto.RespectInfo;
import com.tfb.aibank.chl.general.resource.dto.RetrieveUserHomePageCardResponse;
import com.tfb.aibank.chl.general.resource.dto.UserHomePageCardModel;
import com.tfb.aibank.chl.general.resource.dto.WS00002Request;
import com.tfb.aibank.chl.general.resource.dto.WS00002Response;
import com.tfb.aibank.chl.general.service.NGNService;
import com.tfb.aibank.chl.general.type.AssetAccountType;
import com.tfb.aibank.chl.general.type.AssetItemType;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.chl.session.vo.CompanyVo;
import com.tfb.aibank.chl.session.vo.UserVo;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.MbErrorCode;
import com.tfb.aibank.common.model.BondOverview;
import com.tfb.aibank.common.model.FundOverview;
import com.tfb.aibank.common.model.OdsVpbnd1002;
import com.tfb.aibank.common.model.StockOverview;
import com.tfb.aibank.common.model.StructuredOverview;
import com.tfb.aibank.common.type.BondTxType;
import com.tfb.aibank.common.type.CompanyBUType;
import com.tfb.aibank.common.type.CreditCardIdType;
import com.tfb.aibank.common.type.InvestProductType;

/**
 * CHL (NGNQU001）服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void
 */

// @formatter:off
/**
 * @(#)NGNQU001Service.java
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
@Qualifier("NGNQU001Service")
public class NGNQU001Service extends NGNService {

    @Autowired
    private CreditCardResource creditCardResource;
    @Autowired
    private FxInterestRateCacheManager fxInterestRateCacheManager;
    @Autowired
    private NotificationResource notificationResource;
    @Autowired
    protected ExchangeRateService exchangeRateService;
    @Autowired
    protected ExchangeRateHistoryCacheManager exchangeRateHistoryCacheManager;
    @Autowired
    private UsualTaskResource usualTaskResource;
    @Autowired
    private MenuCacheManager menuCacheManager;
    @Autowired
    private LoanResource loanResource;
    @Autowired
    private HomepageCardCacheManager homepageCardCacheManager;
    @Autowired
    protected ForeignExchangeResource foreignExchangeResource;
    @Autowired
    protected FxTransCurrencyDiscountCacheManager fxTransCurrDiscountCacheManager;
    @Autowired
    private SystemConfigResource systemConfigResource;
    @Autowired
    private DeviceBindingService deviceBindingService;
    @Autowired
    private NewFunctionIntroCacheManager newFunctionIntroCacheManager;
    @Autowired
    private CustomerGroupIdCacheManager customerGroupIdCacheManager;
    @Autowired
    private AvailableTaskCacheManager availableTaskCacheManager;
    @Autowired
    private InvestResource investResource;
    @Autowired
    private MutualFundResource mutualFundResource;
    @Autowired
    private OverviewService overviewService;

    public static final String LOAN_TAG_AIB_AMT = "%AIB_AMT%";
    public static final String LOAN_TAG_AIB_RATE1_PERIOD = "%AIB_RATE1_PERIOD%";
    public static final String LOAN_TAG_AIB_RATE1 = "%AIB_RATE1%";
    public static final String LOAN_TAG_AIB_RATE2 = "%AIB_RATE2%";
    public static final String LOAN_TAG_AIB_PERIOD = "%AIB_PERIOD%";

    private static final List<BondTxType> VALID_BOND_TX_TYPES_FOR_INVEST_CARD = List.of(BondTxType.INVENTORY, BondTxType.SUBSCRIPTION_IN_TRANSIT, BondTxType.REDEMPTION_IN_TRANSIT);
    private static final Map<InvestProductType, Integer> INVEST_SORT_ORDER = Map.of(InvestProductType.NANO, 1, InvestProductType.FUND, 2, InvestProductType.OFF_STOCKS, 3, InvestProductType.OFF_BONDS, 4, InvestProductType.OFF_STRUCTURED, 5, InvestProductType.COMBO, 6, InvestProductType.GOLD, 7, InvestProductType.FOREIGN_BONDS, 8);

    public static final String TASKID_NGNQU001 = "NGNQU001";

    public static final String TEMPLATE_HOMEPAGE = "HOMEPAGE";

    public static final String CATEGORY_HOMEPAGE = "HOMEPAGE";

    public static final String[] NIGHTMODE_EAI_ERR_CD = new String[] { "X202", "X220" };

    /**
     * 查詢登入使用者的信用卡持有狀態 + 是否持有正卡
     */
    public boolean userIsSpecialOrNoCreditCard(AIBankUser aiBankUser, Locale locale) throws ActionException {
        // 是否為特殊戶或未有信用卡, 呼叫下列method後，信用卡狀態會放在aiBankUser中
        boolean isSpecialOrNoCreditCard = this.userDataCacheService.checkCreditCardStatus(aiBankUser);
        logger.debug("isSpecialOrNoCreditCard: {}", isSpecialOrNoCreditCard);

        // 如果已經是特殊戶或沒卡
        if (isSpecialOrNoCreditCard)
            return isSpecialOrNoCreditCard;

        CreditCardIdType idType = null;

        // 檢查是否持有正卡
        try {
            idType = this.userDataCacheService.getCreditCardIdType(aiBankUser, locale);
        }
        catch (ActionException e) {
            logger.error(" == userDataCacheService.getCreditCardIdType == hasError", e);
        }

        return null == idType || !idType.isPrimaryCard();
    }

    /**
     * 在查詢物件中設置相關資訊
     */
    public void fillUserQueryParam(AIBankUser user, DataInput input, DataOutput output) {
        if (null != user) {
            input.setCustId(user.getCustId());
            input.setUserId(user.getUserId());
            input.setCompanyKind(user.getCompanyKind());
            input.setUidDup(user.getUidDup());
        }
        else if (null != output.getQuickSearchData()) {
            QuickSearchResponse qSearchData = output.getQuickSearchData();
            String custId = qSearchData.getCustId();
            input.setCustId(custId);
            input.setUidDup(qSearchData.getUidDup());
            input.setUserId(qSearchData.getUserId());
            input.setCompanyKind(qSearchData.getCompanyKind());
        }
    }

    protected boolean isUserDeviceBinded(AIBankUser aiBankUser, DataInput input) {
        // 檢查使用者與裝置綁定狀態
        CheckUserDeviceStatusCondition condition = new CheckUserDeviceStatusCondition();
        condition.setDeviceIxd(input.getDeviceUuid());
        condition.setLoginUser(aiBankUser);
        condition.setLocale(input.getLocale().toString());
        CheckUserDeviceStatusResult result = new CheckUserDeviceStatusResult();
        logger.debug("== CheckUserDeviceStatusCondition ==.......");
        deviceBindingService.checkUserDeviceStatus(condition, result);
        // 狀態
        UserDeviceBindStatusType status = result.getStatus();
        logger.debug("== CheckUserDeviceStatusCondition == status:{}", status);
        return status.equals(UserDeviceBindStatusType.BINDED);
    }

    /**
     * 取得未讀訊息數量
     */
    public void getUnreadMessageCountInThreeMonth(AIBankUser aiBankUser, DataInput input, DataOutput output) {
        // 所有查詢參數都有值時
        if (null != aiBankUser && !StringUtils.isAnyEmpty(aiBankUser.getCustId(), aiBankUser.getUserId())) {
            // 登入者裝置有綁定才查訊息
            if (isUserDeviceBinded(aiBankUser, input)) {
                Integer unreadCnt = 0;
                try {
                    unreadCnt = notificationResource.getUnreadCountInThreeMonths(aiBankUser.getCustId(), aiBankUser.getUserId(), aiBankUser.getUidDup(), aiBankUser.getCompanyKind());
                }
                catch (ServiceException e) {
                    logger.error("getUnreadMessageCountInThreeMonth: ", e);
                }
                output.setUnreadCount(unreadCnt);
            }
        }
    }

    /**
     * 取得系統公告編號
     */
    public void getSystemNotiRecs(DataOutput output) {
        try {
            // 查詢公告類訊息DB[SYSTEM_NOTIFICATION_RECORD]，取得已登入客戶
            List<SystemNotificationRecord> systemNotiRecs = notificationResource.getUnreadThreeMonthsFromSystemNotify();
            if (CollectionUtils.isNotEmpty(systemNotiRecs)) {
                output.setSystemNoticeRecItemNos(systemNotiRecs.stream().map(SystemNotificationRecord::getItemNo).toList());
            }
        }
        catch (ServiceException e) {
            logger.error("getUnreadThreeMonthsFromSystemNotify: ", e);
        }
    }

    /**
     * 取得招呼語資料
     */
    public void getRespectInfo(AIBankUser aiBankUser, DataInput input, DataOutput output) {
        // [1] 先取當下預設，執行此段後，會先取得當下時段招呼語，
        // 未取得時output.respectInfo 為 null
        // #7601: 預設招呼語不顯示; setDefaultRespectInfo(input, output)

        // [2] 如果是登入者生日，由i18N取祝賀語
        if (isUserBirthday(aiBankUser)) {
            Map<String, I18nModel> i18nCategoryMap = i18nCacheManager.getI18nByCategory(input.getLocale().toString(), CATEGORY_HOMEPAGE);

            if (MapUtils.isNotEmpty(i18nCategoryMap)) {
                I18nModel i18nModel = i18nCategoryMap.get("BIRTHDAY");
                if (null != i18nModel) {
                    RespectInfo respectInfo = new RespectInfo();
                    respectInfo.setRespectDesc(i18nModel.getValue());
                    output.setRespectInfo(respectInfo);
                }
            }
        }
        else {
            // [3] 由DB-RespectInfos 取生效中資料，多筆時依「ORDER_NO：設定排序值」,「RESPECT_KEY：資料流水號」排序
            List<RespectInfo> infos = informationResource.getRespectInfos();

            if (infos != null && null != input.getLocale()) {
                infos = infos.stream().filter(inf -> input.getLocale().toString().equals(inf.getLocale())).collect(Collectors.toList());
            }
            // fortify: Redundant Null Check
            if (infos != null && CollectionUtils.isNotEmpty(infos)) {

                List<RespectInfo> effectiveResInfos = new ArrayList<>();

                for (var rInfo : infos) {
                    if (Objects.nonNull(rInfo.getOrderNo()) && Objects.nonNull(rInfo.getStartDate()) && Objects.nonNull(rInfo.getEndDate())) {
                        Date now = new Date();
                        if (now.after(rInfo.getStartDate()) && now.before(rInfo.getEndDate())) {
                            effectiveResInfos.add(rInfo);
                        }
                    }
                }

                if (CollectionUtils.isNotEmpty(effectiveResInfos)) {
                    IBUtils.sort(effectiveResInfos, new String[] { "orderNo", "endDate", "respectKey" }, new boolean[] { false, false, false });
                    output.setRespectInfo(effectiveResInfos.get(0));
                }
            }
        }

    }

    public boolean isUserBirthday(AIBankUser aiBankUser) {
        boolean isUserBirthday = false;

        if (null != aiBankUser) {
            if (null != aiBankUser.getBirthDay()) {
                LocalDate ldUserBDay = LocalDate.ofInstant(aiBankUser.getBirthDay().toInstant(), ZoneId.systemDefault());
                LocalDate ldToday = LocalDate.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

                MonthDay userBMd = MonthDay.of(ldUserBDay.getMonthValue(), ldUserBDay.getDayOfMonth());
                MonthDay sysMd = MonthDay.of(ldToday.getMonthValue(), ldToday.getDayOfMonth());
                isUserBirthday = userBMd.equals(sysMd);
            }
        }
        return isUserBirthday;
    }

    //@formatter:off
    /**
     * 查詢DB 取得外幣活存利率資料，參考SQL：
     * SELECT INTEREST_RATE
     * FROM FX_INTEREST_RATE
     * WHERE FX_INTEREST_RATE.TYPE_NO=100
     * AND CURRENCY_ENAME in ('USD','JPY','CNY');
     */
    //@formatter:on
    public void getFxInterestRate(DataInput input, DataOutput output) {
        // 抓SQL的利率TYPE_NO改為220
        List<FxInterestRate> fxIntRates = fxInterestRateCacheManager.getFxInterestRatesByTypeNo("220");
        List<FxInterestRate> fxIntRatesFiltered = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(fxIntRates)) {

            List<String> targetCurCodes = Stream.of("USD", "CNY", "JPY").toList();
            // Predicate<FxInterestRate> curToShow = fx -> {
            // boolean rateType0 = null != fx.getRateType() && fx.getRateType().intValue() == 0;
            // boolean inTargetCurs = targetCurCodes.contains(fx.getCurrencyEname());
            // return rateType0 && inTargetCurs;
            // };

            for (String targetCurCode : targetCurCodes) {
                for (FxInterestRate fxIntRate : fxIntRates) {
                    boolean rateType0 = Objects.equals(fxIntRate.getRateType(), 0);
                    if (rateType0 && StringUtils.equals(targetCurCode, fxIntRate.getCurrencyEname())) {
                        fxIntRatesFiltered.add((fxIntRate));
                        break;
                    }
                }
            }

            List<FxInterestRateVo> vos = fxIntRatesFiltered.stream().map(NGNQU001Util.copyFxInterestRate).toList();
            List<FxInterestRateVo> vosToShow = new ArrayList<>();
            for (FxInterestRateVo vo : vos) {
                // 最多三張
                if (vosToShow.size() < 3) {
                    vosToShow.add(vo);
                }
            }
            vosToShow.forEach(vo -> {
                String cName = currencyCodeCacheManager.getCurrencyName(vo.getCurrencyEname(), input.getLocale());
                vo.setCurrencyDispName(cName);
                if (null == vo.getTxTime()) {
                    vo.setTxTime(new Date());
                }
                vo.setTxTimeStr(DateFormatUtils.CE_DATETIME_FORMAT.format(vo.getTxTime()));
            });
            output.setFxInterestRates(vosToShow);
        }
    }

    /**
     * 查詢使用者常用功能，如果沒有設定，帶入預設值
     * 
     * @param aibankUser
     * @param input
     * @param output
     * @param appVer
     */
    public void queryUsualTasks(AIBankUser aibankUser, DataInput input, DataOutput output, String appVer) {
        if (null == aibankUser) {
            return;
        }

        List<UsualTask> usualTasks = aibankUser.getUsualTasks();
        List<UsualTaskVo> usualTaskVos;
        // (A)cache中沒有常用功能
        if (CollectionUtils.isEmpty(usualTasks)) {
            usualTasks = usualTaskResource.getUsualTasks(aibankUser.getCustId(), aibankUser.getUserId(), aibankUser.getUidDup(), aibankUser.getCompanyKind());
            // (B) DB 中有常用功能，放進user暫存
            // 沒有資料，取得預設資料，但不放入user暫存
            if (CollectionUtils.isNotEmpty(usualTasks)) {
                for (Iterator<UsualTask> it = usualTasks.iterator(); it.hasNext();) {
                    UsualTask usualTask = it.next();
                    Menu menu = menuCacheManager.getMenuByMenuId(usualTask.getMenuId());
                    if (menu == null || menu.getIsOpen() == 0) {
                        it.remove();
                        continue;
                    }
                }
                aibankUser.setUsualTasks(usualTasks);
            }
            else {
                MenuCategory menuCate = aibankUser.getCustomerType().isGeneral() ? MenuCategory.AIDBU : MenuCategory.AICCU;
                if (Locale.US.equals(input.getLocale())) {
                    menuCate = aibankUser.getCustomerType().isGeneral() ? MenuCategory.AIDBU_EN : MenuCategory.AICCU_EN;
                }
                usualTasks = getDefaultTasks(menuCate, input.getLocale(), appVer);
            }
        }
        usualTasks.forEach(u -> fillData(u, input.getLocale()));
        usualTaskVos = usualTasks.stream().map(UsualTaskVo::new).toList();
        output.setUsualTasks(usualTaskVos);
    }

    /**
     * 未登入時取得[一般用戶的預設功能]，按裝置目前設定語系讀取
     */
    public void getDefaultUsualTasks(DataInput input, DataOutput output, String appVer) {
        MenuCategory menuCategory = Locale.US.equals(input.getLocale()) ? MenuCategory.AIDBU_EN : MenuCategory.AIDBU;
        List<UsualTask> usualTasks = getDefaultTasks(menuCategory, input.getLocale(), appVer);
        usualTasks.forEach(u -> fillData(u, input.getLocale()));
        List<UsualTaskVo> usualTaskVos = usualTasks.stream().map(UsualTaskVo::new).toList();
        output.setUsualTasks(usualTaskVos);
    }

    /**
     * 取得預設常用功能
     */
    protected List<UsualTask> getDefaultTasks(MenuCategory menuCate, Locale locale, String appVer) {

        String cateToGetUsualTask = menuCate.getCategory();
        List<UsualTask> usualTasks = new ArrayList<>();

        List<String> keys = Stream.of(NGNQU001Constants.P_KEY_USUAL_TASK_DEFAULT.split(",")).toList();

        List<String> menuIds = new ArrayList<>();

        // 查SystemParam中設定的常用功能TaskId
        for (String key : keys) {
            String menuId = systemParamCacheManager.getValue(cateToGetUsualTask, key);
            menuIds.add(menuId);
        }
        logger.debug("getDefaultTasks. category: {}, menuIds: {}", cateToGetUsualTask, menuIds);

        // 由 MENU_ID 取得 MENU 資訊，此處不考慮語系
        for (String menuId : menuIds) {
            Menu menu = menuCacheManager.getMenuByMenuId(menuId);
            logger.debug("getDefaultTasks. menu: {}, menuId: {}", menu, menuId);

            // #8383 #8355 U-22017 新增顯示判斷 MENU.IS_OPEN = 1 才顯示常用功能
            if (null != menu && menu.getIsOpen() == 1) {
                UsualTask ut = convertMenuToUsualTask(menu, locale);
                usualTasks.add(ut);
            }
        }
        return usualTasks;
    }

    /**
     * 將 UsualTask 轉換為 UsualTaskVo 轉換時對照Menu Table資料，取得parentMenuId， 再取menuId == parentMenuId 的資料，由其中取得iconClass
     */
    protected void fillData(UsualTask usualTask, Locale locale) {
        // UsualTask 的 IconClass 若已有值，則不重取一次
        if (CollectionUtils.isNotEmpty(menuCacheManager.getAllMenus())) {
            Menu menu = menuCacheManager.getMenuByMenuId(usualTask.getMenuId());
            if (null != menu) {
                usualTask.setMenuId(menu.getMenuId());
                usualTask.setParentMenuId(menu.getParentMenuId());
                usualTask.setMenuName(StringUtils.defaultString(menu.getMenuName(locale.toString())));
                usualTask.setParentMenuMame(StringUtils.defaultString(menu.getParentMenuName(locale.toString())));
                usualTask.setIconClass(menu.getIconClass());
                usualTask.setLinkType(menu.getLinkType());
                usualTask.setLinkParam(menu.getLinkParam());
            }
        }
    }

    protected UsualTask convertMenuToUsualTask(Menu menu, Locale locale) {
        UsualTask ut = new UsualTask();
        ut.setTaskId(menu.getTaskId());
        ut.setMenuId(menu.getMenuId());
        ut.setIconClass(menu.getIconClass());
        ut.setParentMenuId(menu.getParentMenuId());
        ut.setLinkType(menu.getLinkType());
        ut.setLinkParam(menu.getLinkParam());
        ut.setMenuName(StringUtils.defaultString(menu.getMenuName(locale.toString())));
        ut.setParentMenuMame(StringUtils.defaultString(menu.getParentMenuName(locale.toString())));
        return ut;
    }

    /**
     * 取得所有可以提供使用者設定為常用功能的功能列表 A. 依據一般會員、信用卡會員登入顯示對應的選單內容 B. 取得功能選單，選單分為四層，分別為：業務大類、功能群組、功能節點、分類。
     * 
     * @param aibankUser
     * @param input
     * @param output
     * @param menuCategory
     */
    public void getMenusForUserSetting(AIBankUser aibankUser, DataInput input, DataOutput output, MenuCategory menuCategory) {
        if (null == aibankUser) {
            return;
        }

        // 當前登入方式（身份）對應的所有選單 + IS_DISPLAY = 1(非隱藏功能)
        List<Menu> allMenusByCustomerType = this.menuCacheManager.getMenusByCategory(menuCategory.getCategory()).stream().filter(NGNQU001Util.menuIsDisplay).collect(Collectors.toList());

        if (logger.isDebugEnabled()) {
            logger.debug("== getMenusForUserSetting ==, menuCategory:{}, allMenusByCustomerType: {}", menuCategory, allMenusByCustomerType);
        }

        if (CollectionUtils.isEmpty(allMenusByCustomerType)) {
            return;
        }
        // 業務大類MENU_IDs
        List<Menu> businessCateMenus = allMenusByCustomerType.stream().filter(NGNQU001Util.isBusinessCateAndIsDisplay).collect(Collectors.toList());

        // 依ParentMenuId為key的 map (不包含 parentMenuId = ROOT的資料)
        Map<String, List<Menu>> menuGroupMapByParentMenuId = allMenusByCustomerType.stream().filter(NGNQU001Util.getParentMenuIdsNotRootMenu).collect(Collectors.groupingBy(Menu::getParentMenuId));

        List<MenuVo> menusForSetting = new ArrayList<>();

        for (Menu bizCateMenu : businessCateMenus) {
            // 取得「功能群組」menus, 此層取parentMenuId = 業務大類menuId
            List<Menu> funGroupMenusInThisBizCate = menuGroupMapByParentMenuId.getOrDefault(bizCateMenu.getMenuId(), Collections.emptyList());
            // TODO: 要先過濾一下linkType

            for (Menu funGroupMenu : funGroupMenusInThisBizCate) {
                // 取「功能節點」(實際上的功能點，但有可能下有再細分分類)
                List<Menu> funGrpNodes = menuGroupMapByParentMenuId.get(funGroupMenu.getMenuId());
                if (CollectionUtils.isNotEmpty(funGrpNodes)) {
                    // #8383 #8355 U-22017 新增顯示判斷 MENU.IS_OPEN = 1 才顯示
                    funGrpNodes = funGrpNodes.stream().filter(x -> x.getIsOpen() == 1).collect(Collectors.toList());
                }

                // 先查看此「功能群組」下是否有任何成員menu，沒有就跳過
                if (CollectionUtils.isEmpty(funGrpNodes)) {
                    processMenuNodes(menuGroupMapByParentMenuId, bizCateMenu, funGroupMenu, null, menusForSetting, input.getLocale());
                }
                else {
                    processMenuNodes(menuGroupMapByParentMenuId, bizCateMenu, funGroupMenu, funGrpNodes, menusForSetting, input.getLocale());
                }
            }
        }
        output.setMenusForSetting(menusForSetting);
    }

    /**
     * menuGroupMapByParentMenuId -> 以ParentMenuId為key分組的map bizCateMenu -> 業務大類 menu<br>
     * funGroupMenu -> 功能群組 menu<br>
     */
    protected void processMenuNodes(Map<String, List<Menu>> menuGroupMapByParentMenuId, Menu bizCateMenu, Menu funGroupMenu, List<Menu> funGrpNodes, List<MenuVo> menusForSetting, Locale locale) {

        if (CollectionUtils.isNotEmpty(funGrpNodes)) {
            for (Menu node : funGrpNodes) {

                MenuVo vo = NGNQU001Util.menuToMenuVo(bizCateMenu, funGroupMenu, node, locale);

                // 向menuGroupMapByParentMenuId取得 以當前vo的menuId為parentMenuId的資料
                List<Menu> nodeMenus = menuGroupMapByParentMenuId.get(vo.getMenuId());

                // 當前vo的menuId向下無menu，是最後node
                if (CollectionUtils.isEmpty(nodeMenus)) {
                    vo.setNodeFlag(0);
                    menusForSetting.add(vo);
                }
                else {
                    // 當前vo的menuId向下有menu，取最後一層
                    vo.setNodeFlag(1);

                    List<MenuVo> lastNodeVos = nodeMenus.stream().map(n -> {
                        MenuVo lastVo = NGNQU001Util.menuToMenuVo(bizCateMenu, funGroupMenu, n, locale);
                        lastVo.setParentMenuName(vo.getMenuName());
                        lastVo.setNodeFlag(0);
                        return lastVo;
                    }).collect(Collectors.toList());

                    vo.setNodeMenus(lastNodeVos);
                    menusForSetting.add(vo);
                }
            }
        }
        else {
            MenuVo vo = NGNQU001Util.funGroupMenuToMenuVo(bizCateMenu, funGroupMenu, locale);
            menusForSetting.add(vo);
        }
    }

    public void getMenusForUserSearching(AIBankUser aibankUser, DataInput input, DataOutput output, String appVer, MenuCategory menuCategory) {
        if (null == aibankUser) {
            return;
        }

        // 當前登入方式（身份）對應的所有選單 + IS_DISPLAY = 1(非隱藏功能) + CanBeSearch = 1
        List<Menu> allMenusToSeach = this.menuCacheManager.getMenusByCategoryFilterByVersion(menuCategory.getCategory(), appVer);
        if (logger.isDebugEnabled()) {
            logger.debug("allMenusToSeach: {} ", IBUtils.attribute2Str(allMenusToSeach));
        }
        allMenusToSeach = allMenusToSeach.stream().filter(NGNQU001Util.menuIsDisplay.and(NGNQU001Util.menuCanBeSearch)).collect(Collectors.toList());
        // #8383 #8355 U-22017 新增顯示判斷 MENU.IS_OPEN = 1 才顯示
        allMenusToSeach = allMenusToSeach.stream().filter(x -> x.getIsOpen() == 1).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(allMenusToSeach)) {
            return;
        }

        List<MenuVo> menusForSearch = new ArrayList<>();
        List<String> menuIds = allMenusToSeach.stream().map(Menu::getMenuId).collect(Collectors.toList());

        Map<String, List<String>> menuIdKeywordMap = systemConfigResource.getMenuKeywordsForSearch(menuIds);

        for (Menu menu : allMenusToSeach) {
            MenuVo menuVo = NGNQU001Util.menuToMenuVo(null, null, menu, input.getLocale());

            if (menuIdKeywordMap.containsKey(menuVo.getMenuId())) {
                menuVo.setKeywords(menuIdKeywordMap.get(menuVo.getMenuId()));
            }
            menusForSearch.add(menuVo);
        }
        output.setMenusForSearching(menusForSearch);
    }

    public void getMenusForSearchingNotLogin(DataInput input, DataOutput output, String appVer) {
        List<MenuVo> menusForSearchAIDBU = getMenusByCategory((Locale.US.equals(input.getLocale()) ? MenuCategory.AIDBU_EN : MenuCategory.AIDBU), input, appVer);
        List<MenuVo> menusForSearchAICCU = getMenusByCategory((Locale.US.equals(input.getLocale()) ? MenuCategory.AICCU_EN : MenuCategory.AICCU), input, appVer);
        menusForSearchAIDBU.addAll(menusForSearchAICCU);
        output.setMenusForSearching(menusForSearchAIDBU);
    }

    public List<MenuVo> getMenusByCategory(MenuCategory menuCategory, DataInput input, String appVer) {

        // 依menuCategory取得對應的所有選單
        // 當前登入方式（身份）對應的所有選單 + IS_DISPLAY = 1(非隱藏功能) + CanBeSearch = 1
        List<Menu> allMenusToSeach = this.menuCacheManager.getMenusByCategoryFilterByVersion(menuCategory.getCategory(), appVer);
        if (logger.isDebugEnabled()) {
            logger.debug("allMenusToSeach: {} ", IBUtils.attribute2Str(allMenusToSeach));
        }
        allMenusToSeach = allMenusToSeach.stream().filter(NGNQU001Util.menuIsDisplay.and(NGNQU001Util.menuCanBeSearch)).collect(Collectors.toList());
        // #8383 #8355 U-22017 新增顯示判斷 MENU.IS_OPEN = 1 才顯示
        allMenusToSeach = allMenusToSeach.stream().filter(x -> x.getIsOpen() == 1).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(allMenusToSeach)) {
            return Collections.emptyList();
        }

        List<MenuVo> menusForSearch = new ArrayList<>();
        List<String> menuIds = allMenusToSeach.stream().map(Menu::getMenuId).collect(Collectors.toList());

        Map<String, List<String>> menuIdKeywordMap = systemConfigResource.getMenuKeywordsForSearch(menuIds);

        for (Menu menu : allMenusToSeach) {
            MenuVo menuVo = NGNQU001Util.menuToMenuVo(null, null, menu, input.getLocale());

            if (menuIdKeywordMap.containsKey(menuVo.getMenuId())) {
                menuVo.setKeywords(menuIdKeywordMap.get(menuVo.getMenuId()));
            }
            menusForSearch.add(menuVo);
        }
        return menusForSearch;
    }

    /**
     * 更新使用者常用功能
     */
    public void updateUsualTasks(AIBankUser user, DataInput input) {
        List<UsualTask> utsToUpdate = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(input.getUsualTasks())) {
            input.getUsualTasks().forEach(utv -> {
                utsToUpdate.add(convertToUsualTask(utv));
            });
        }
        List<UsualTask> tasks = usualTaskResource.updateUsualTasks(user.getCustId(), user.getUserId(), user.getUidDup(), user.getCompanyKind(), utsToUpdate);
        user.setUsualTasks(tasks);
    }

    public UsualTask convertToUsualTask(UsualTaskVo vo) {
        UsualTask task = new UsualTask();
        task.setMenuId(vo.getMenuId());
        task.setMenuName(vo.getMenuName());
        task.setTaskId(vo.getTaskId());
        task.setTaskSort(vo.getTaskSort());
        return task;
    }

    /**
     * 取得使用者設定的HomepageCardUser牌卡設定
     */
    public void getUserConfigedHomepageCards(DataInput input, DataOutput output) {
        // #8241 呼叫retrieveUserHomePageCard時，若以下參數有任何不存在，會得到400回應，導致X9999
        if (null == input.getCompanyKind() || StringUtils.isAnyBlank(input.getCustId(), input.getUserId(), input.getUidDup())) {
            return;
        }

        RetrieveUserHomePageCardResponse sr = this.userResource.retrieveUserHomePageCard(input.getCustId(), input.getUserId(), input.getUidDup(), input.getCompanyKind());

        List<Integer> displayHomepageCardIds;
        if (null != sr && CollectionUtils.isNotEmpty(sr.getCards())) {
            displayHomepageCardIds = new ArrayList<>();
            List<UserHomePageCardModel> cardModels = sr.getCards();

            List<Integer> validedCardIds = getDefaultHomepageCardIdCanShow();

            cardModels = cardModels.stream().filter(cm -> validedCardIds.contains(cm.getCardId())).collect(Collectors.toList());

            // 如果是信用卡會員登入時，信用卡牌放第一張
            cardModels = cardModels.stream().sorted(Comparator.comparing(UserHomePageCardModel::getCardSort)).toList();

            for (UserHomePageCardModel model : cardModels) {
                if (displayHomepageCardIds.size() < 5) {
                    displayHomepageCardIds.add(model.getCardId());
                }
            }
            output.setDisplayHomepageCardIds(displayHomepageCardIds);
        }
    }

    /** 取首頁可以顯示的HOMEPAGE_CARD cardIds */
    public List<Integer> getDefaultHomepageCardIdCanShow() {
        List<Integer> cardIds = new ArrayList<>();
        Predicate<HomepageCard> filterNGNQU001HomepageCard = (hc) -> TASKID_NGNQU001.equals(hc.getCardUsedTaskId()) && TEMPLATE_HOMEPAGE.equals(hc.getCardTemplate());
        List<HomepageCard> homepageCards = homepageCardCacheManager.getMulHomePageCardByPredicate(filterNGNQU001HomepageCard);

        if (CollectionUtils.isNotEmpty(homepageCards)) {
            // SELECT * FROM HOMEPAGE_CARD WHERE IS_QUERY='0' AND CARD_FOLD='0';
            Predicate<HomepageCard> cardsForNGNQU001 = hc -> (null != hc.getCardUsed() && hc.getCardUsed() == 0) && (null != hc.getQuery() && hc.getQuery() == 0) && "0".equals(hc.getCardFold());
            homepageCards = homepageCards.stream().filter(cardsForNGNQU001).collect(Collectors.toList());
            for (HomepageCard model : homepageCards) {
                cardIds.add(model.getCardId());
            }
        }
        return cardIds;
    }

    /**
     * 取「未登入」時預設的牌卡
     */
    public void getDefaultHomepageCardIdToShowV2(DataOutput output) {

        Predicate<HomepageCard> filterNGNQU001HomepageCard = (hc) -> TASKID_NGNQU001.equals(hc.getCardUsedTaskId()) && TEMPLATE_HOMEPAGE.equals(hc.getCardTemplate()) && (0 == hc.getTaType() || (null != hc.getCardId() && hc.getCardId() == 4 && hc.getTaType() == 1));
        List<HomepageCard> homepageCards = homepageCardCacheManager.getMulHomePageCardByPredicate(filterNGNQU001HomepageCard);

        if (logger.isDebugEnabled()) {
            if (CollectionUtils.isNotEmpty(homepageCards)) {
                logger.debug("[getDefaultHomepageCardIdToShowV2] homepageCards ====>");
                homepageCards.forEach(hc -> {
                    logger.debug("cardKey:[{}], cardId:[{}], cardUsed:[{}],cardUsedTaskId:[{}], cardSort:[{}]", hc.getCardKey(), hc.getCardId(), hc.getCardUsed(), hc.getCardUsedTaskId(), hc.getCardSort());
                });

            }
        }

        if (CollectionUtils.isNotEmpty(homepageCards)) {
            // 原始過濾條件(交易最初的篩選條件) CardUsed = 0 AND IS_QUERY='0' AND CARD_FOLD='0';
            // 取「無查詢權限」+「無查詢權限」+「未收折」的資料
            Predicate<HomepageCard> cardsForNGNQU001 = hc -> (null != hc.getCardUsed() && hc.getCardUsed() == 0) && (null != hc.getQuery() && hc.getQuery() == 0) && "0".equals(hc.getCardFold());
            List<HomepageCard> homepageCardTmpList = homepageCards.stream().filter(cardsForNGNQU001).collect(Collectors.toList());

            logger.debug("[getDefaultHomepageCardIdToShowV2] homepageCardTmpList ====>");
            homepageCardTmpList.forEach(hc -> {
                logger.debug("cardKey:[{}], cardId:[{}], cardUsed:[{}],cardUsedTaskId:[{}], cardSort:[{}]", hc.getCardKey(), hc.getCardId(), hc.getCardUsed(), hc.getCardUsedTaskId(), hc.getCardSort());
            });

            String defaultHomepageCardIds = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DEFAULT_HOMEPAGE_CARD");
            logger.debug("[defaultHomepageCardIds] : {}", defaultHomepageCardIds);
            List<Integer> cardIdStored = new ArrayList<>();
            if (StringUtils.isNotBlank(defaultHomepageCardIds)) {
                Stream.of(defaultHomepageCardIds.split(",")).forEach(id -> {
                    cardIdStored.add(ConvertUtils.str2Int(StringUtils.trim(id)));
                });
            }

            if (CollectionUtils.isNotEmpty(cardIdStored)) {
                homepageCardTmpList.forEach(hcs -> {
                    if (cardIdStored.contains(hcs.getCardId())) {
                        int indexInSort = cardIdStored.indexOf(hcs.getCardId());
                        hcs.setCardSortDynamic(indexInSort + 1);
                    }
                    else {
                        // 沒有比對到預設值時，原值(為了避免cardSort == null，預設20)加上50，讓排序時放在後面
                        hcs.setCardSortDynamic(NumberUtils.defaultValue(hcs.getCardSort(), 20) + 50);
                    }
                });
            }
            else {
                homepageCardTmpList.forEach(hct -> {
                    hct.setCardSortDynamic(NumberUtils.defaultValue(hct.getCardSort(), 20) + 50);
                });
            }

            logger.debug("[getDefaultHomepageCardIdToShowV2] homepageCardTmpList add SortDynamic ====>");
            homepageCardTmpList.forEach(hc -> {
                logger.debug("cardKey:[{}], cardId:[{}], cardUsed:[{}],cardUsedTaskId:[{}], cardSort:[{}], SortDynamic: [{}]", hc.getCardKey(), hc.getCardId(), hc.getCardUsed(), hc.getCardUsedTaskId(), hc.getCardSort(), hc.getCardSortDynamic());
            });

            // 依「cardSortDynamic」排序
            homepageCards = homepageCardTmpList.stream().sorted(Comparator.comparing(HomepageCard::getCardSortDynamic)).collect(Collectors.toList());

            logger.debug("[getDefaultHomepageCardIdToShowV2] homepageCardTmpList after SortDynamic ====>");
            homepageCards.forEach(hc -> {
                logger.debug("cardKey:[{}], cardId:[{}], cardUsed:[{}],cardUsedTaskId:[{}], cardSort:[{}], SortDynamic: [{}]", hc.getCardKey(), hc.getCardId(), hc.getCardUsed(), hc.getCardUsedTaskId(), hc.getCardSort(), hc.getCardSortDynamic());
            });

            List<HomepageCard> homepageCardsDistinct = new LinkedList<>();
            Set<Integer> cardIdSet = new HashSet<>();
            // 同樣的CardId會有多筆的情境，在經過排序後，篩選每個cardId的第1筆
            for (HomepageCard homepageCard : homepageCards) {
                if (!cardIdSet.contains(homepageCard.getCardId())) {
                    cardIdSet.add(homepageCard.getCardId());
                    homepageCardsDistinct.add(homepageCard);
                }
            }

            logger.debug("[getDefaultHomepageCardIdToShowV2] all cardIds : {}", homepageCardsDistinct.stream().map(HomepageCard::getCardId).collect(Collectors.toList()));

            List<Integer> cardIds = new ArrayList<>();

            for (HomepageCard model : homepageCardsDistinct) {
                if (!cardIds.contains(model.getCardId()) && cardIds.size() < 3) {
                    cardIds.add(model.getCardId());
                }
            }

            logger.debug("[getDefaultHomepageCardIdToShowV2] cardIds to show: {}", cardIds);
            // cardIds = cardIds.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            output.setDisplayHomepageCardIdsDefault(cardIds);
        }

    }

    /**
     * 查詢免登速查資料
     */
    public void getQSearchStatus(String deviceUuid, Locale locale, DataOutput output) throws ActionException {
        QuickSearchResponse resp = userResource.getQuickSearchStatus(deviceUuid);
        // 有免登速查
        if (null != resp && resp.haveQuickSearchOn()) {
            // 未登入，做一個空的AiBankUser
            AIBankUser emptyUser = new AIBankUser(null);
            emptyUser.setCustId(resp.getCustId());

            boolean isSpecialOrNoCreditCard = true;

            try {
                isSpecialOrNoCreditCard = userDataCacheService.checkCreditCardStatus(emptyUser);
            }
            catch (ServiceException e) {
                logger.error("== userDataCacheService.checkCreditCardStatus ==: ", e);
            }
            logger.debug("isSpecialOrNoCreditCard: {}", isSpecialOrNoCreditCard);

            // 如果不是特殊戶或持有信用卡
            if (!isSpecialOrNoCreditCard) {
                CreditCardIdType idType = null;
                // 檢查是否持有正卡
                try {
                    idType = this.userDataCacheService.getCreditCardIdType(emptyUser, locale);
                    isSpecialOrNoCreditCard = !idType.isPrimaryCard();
                }
                catch (ActionException e) {
                    isSpecialOrNoCreditCard = true;
                    logger.error(" == userDataCacheService.getCreditCardIdType == hasError", e);
                }
            }
            resp.setSpecialUserOrNoCreditCard(isSpecialOrNoCreditCard);
        }
        output.setQuickSearchData(resp);
    }

    // @formatter:off
    /**
     * 取得產品「存款」資料
     * <hr>
     */
    // @formatter:on
    public void getHomeCardDataDeposit(DataInput input, DataOutput output) {

        EB555692Request request = new EB555692Request();
        String custId = input.getCustId() + checkAndGetUidDup(input.getUidDup());
        request.setCustId(custId);
        request.setNameCode(input.getNameCode());

        EB555692Response response = userResource.sendEB555692ForDepositAmount(request);

        if (null != response && !StringUtils.isY(response.getNoDataFlag())) {
            CardDataDeposit cardDataDeposit = new CardDataDeposit();
            cardDataDeposit.setTotalAmtNTD(response.getTotalAmtNtd());
            cardDataDeposit.setTotalAmtFRG(response.getTotalAmtFrg());
            cardDataDeposit.setTotalAmt(response.getTotalAmt());

            HomepageCard homepageCard = getHomepageCardByCardId(ProductType.DEPOSIT.getCardId(), null);
            cardDataDeposit.setCardTarget(homepageCard.getCardTarget());
            output.setCardDataDeposit(cardDataDeposit);
        }
        else {
            this.getHomePageCardV2(ProductType.DEPOSIT.getCardId(), input, output);
        }

    }

    public void validGeneralCustomerInBlackList(AIBankUser aiBankUser) throws ActionException {
        CcAvalibleTaskEntityVo ccTask = availableTaskCacheManager.getCcAvailableTask("NCCQU001");
        // A. 檢核該功能是否不提供黑名單客戶使用
        if (ccTask == null || ccTask.getAvalibleFlag() == 1) {
            return;
        }
        // (B) 不在黑名單內，進入該功能
        if (!aiBankUser.isInAccountCreditcardCheck()) {
            return;
        }

        if (aiBankUser.isSameBirthday()) {
            return;
        }
        // 拋出此錯誤時，視為無信用卡，顯示廣告
        throw ExceptionUtils.getActionException(MbErrorCode.MB2201);
    }

    /**
     * 取得產品「信用卡」資料
     *
     * @throws ActionException
     */
    public void getHomeCardDataCreditCard(DataInput input, DataOutput output, AIBankUser aiBankUser) throws ActionException {

        // 【一般會員登入】由總覽電文[BPM001 or BPM003]取得1. 已消費金額, 2. 尚未繳納金額
        logger.debug("getHomeCardDataCreditCard: custId[{}], nameCode[{}], isDbu[{}]", input.getCustId(), input.getNameCode(), input.isDbu());
        CreditCardOverview overview = null;

        AIBankUser aiBankUserForQuery = null != aiBankUser ? aiBankUser : new AIBankUser(null);
        // 未登入時(aiBankUserForQuery 為新Construct Object)，把custId置入
        if (StringUtils.isEmpty(aiBankUserForQuery.getCustId())) {
            aiBankUserForQuery.setCustId(input.getCustId());
            if (StringUtils.isNotEmpty(input.getNameCode())) { // 有nameCode表示為一般會員
                aiBankUserForQuery.setCustomerType(CustomerType.GENERAL);
                aiBankUserForQuery.setInAccountCreditcardCheck(input.isInAccountCreditcardCheck());
                aiBankUserForQuery.setSameBirthday(input.isSameBirthday());
            }
            else {
                aiBankUserForQuery.setCustomerType(CustomerType.CARDMEMBER);
            }
        }

        // 現在一般用戶和信用卡會員均打CEW304R電文，對電文回覆X012/X202/X220時，把錯誤代碼換回原始錯誤errorCode
        try {
            if (aiBankUserForQuery.getCustomerType().isGeneral()) {
                this.validGeneralCustomerInBlackList(aiBankUserForQuery);
                overview = creditCardResource.getUserCreditCardOverviewValueA(input.getCustId(), input.getNameCode(), input.isDbu());
            }
            else {
                overview = creditCardResource.getCardMemberCreditCardOverviewA(input.getCustId());
            }
        }
        catch (ServiceException e) {
            logger.error("= sendCEW304R error getExtraInfo: {}", e.getExtraInfo());
            // 如果電文回覆X開頭錯誤，原本的errorCode 會是EAI01，多判斷原始EAI錯誤代碼
            // 當與信用卡牌卡資訊相關的電文(CEW301R/CE6220R/CEW303R/CEW313R/CEW304R)回覆X012/X202/X220時，把錯誤代碼換回原始錯誤errorCode
            // CEW304R 等價於 CEW313R
            if (null != e.getExtraInfo() && null != e.getExtraInfo().get(Constants.EXTRA_CODE_REFERENCE)) {
                String extraCode = e.getExtraInfo().get(Constants.EXTRA_CODE_REFERENCE);
                if (StringUtils.equalsAny(extraCode, "X012", "X220", "X202")) {
                    e.setErrorCode(extraCode);
                }
            }
            throw e;
        }

        if (overview == null) {
            logger.error("查詢客戶信用卡消費未繳總覽(一般用戶) - A / 查詢客戶信用卡消費未繳總覽(信用卡用戶) 查無資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        CardDataCreditCard ccCard = new CardDataCreditCard();

        // int creditCardBillMonth = getCreditCardBillMonthViaCEW314R(input);

        logger.debug("== getHomeCardDataCreditCard == , overview: {}", IBUtils.attribute2Str(overview));
        // 已消費金額
        if (null != overview) {
            ccCard.setConsumptionAmt(overview.getUnbilledConsumptionAmount());
            CardDataCreditCard.BillToPay billToPay = new CardDataCreditCard.BillToPay();
            billToPay.setAmtToPay(overview.getCcBillRemainAmt());
            // billToPay.setBillMonthInt(creditCardBillMonth);
            ccCard.setBillToPay(billToPay);
        }

        // 取得使用者的卡別
        CreditCardIdType creditCardIdType = userDataCacheService.getCreditCardIdType(aiBankUserForQuery, input.getLocale());
        logger.debug("== getHomeCardDataCreditCard == , CreditCardIdType: {}", creditCardIdType);

        /***************************
         * 使用 CreditCardOverview(來自CEW304R) 中的歸戶額度及歸戶餘額 20250514 David Huang *** // CEW303R // CustCdno -> (1) 若持卡類別=附卡時，上送AI Bank User取得的客戶信用卡卡號，否則不上送 // CustAcid -> (1) 若持卡類別=正卡時，上送AI Bank User取得客戶的身分證字號，否則不上送 String custCdno = null; String custAcid =
         * null; if (creditCardIdType.isPrimaryCard()) { custAcid = input.getCustId(); } else { // 持有附卡 List<CreditCard> cards = userDataCacheService.getEffectiveCreditCards(aiBankUserForQuery, input.getLocale()); if (CollectionUtils.isNotEmpty(cards)) {
         * custCdno = cards.get(0).getCardNo(); } }
         * 
         * CEW303RRes cEW303RRes; try { cEW303RRes = creditCardResource.sendCEW303R(custAcid, custCdno); } catch (ServiceException e) { logger.error("= sendCEW303R error getExtraInfo: {}", e.getExtraInfo()); // 如果電文回覆X開頭錯誤，原本的errorCode 會是EAI01，多判斷原始EAI錯誤代碼
         * // 當與信用卡牌卡資訊相關的電文(CEW301R/CE6220R/CEW303R/CEW313R)回覆X202/X220時，把錯誤代碼換回原始錯誤errorCode if (null != e.getExtraInfo() && null != e.getExtraInfo().get(Constants.EXTRA_CODE_REFERENCE)) { String extraCode =
         * e.getExtraInfo().get(Constants.EXTRA_CODE_REFERENCE); if (StringUtils.equalsAny(extraCode, "X220", "X202")) { e.setErrorCode(extraCode); } } throw e; }
         * 
         * if (null != cEW303RRes) { // 歸戶信用額度 BASE 100% BigDecimal acctIdCram = NumberUtils.defaultValue(cEW303RRes.getAcctIdCram(), BigDecimal.ZERO); // 歸戶可用餘額 BigDecimal acctIdPcbl = NumberUtils.defaultValue(cEW303RRes.getAcctIdPcbl(), BigDecimal.ZERO);
         * 
         * // ** 已使用額度(金額) BigDecimal quotaUsed = acctIdCram.subtract(acctIdPcbl);
         * 
         * // logger.debug("== getHomeCardDataCreditCard == ,acctIdCram[{}], acctIdPcbl[{}], quotaUsed[{}]", acctIdCram, acctIdPcbl, quotaUsed);
         * 
         * // ** 已使用額度(進度條上的百分比數值) double quotaUsedInPercent = 0; if (acctIdCram.doubleValue() > 0) { double quotaUsedPortion = ArithUtils.div(quotaUsed.doubleValue(), acctIdCram.doubleValue(), 4); quotaUsedInPercent = ArithUtils.mul(quotaUsedPortion,
         * 100); } ccCard.setCreditUsedPercentage(quotaUsedInPercent); ccCard.setRemainingAmount(acctIdPcbl); ccCard.setCreditLimit(acctIdCram); }
         ***********************************************************************************************************/

        // 歸戶信用額度 BASE 100%
        BigDecimal acctIdCram = overview.getAcctIdCram();
        // 歸戶可用餘額
        BigDecimal acctIdPcbl = overview.getAcctIdPcbl();
        // ** 已使用額度(金額)
        BigDecimal quotaUsed = acctIdCram.subtract(acctIdPcbl);

        // ** 已使用額度(進度條上的百分比數值)
        double quotaUsedInPercent = 0;
        if (acctIdCram.doubleValue() > 0) {
            double quotaUsedPortion = ArithUtils.div(quotaUsed.doubleValue(), acctIdCram.doubleValue(), 4);
            quotaUsedInPercent = ArithUtils.mul(quotaUsedPortion, 100);
        }
        ccCard.setCreditUsedPercentage(quotaUsedInPercent);
        ccCard.setRemainingAmount(acctIdPcbl);
        ccCard.setCreditLimit(acctIdCram);

        // if (logger.isDebugEnabled()) {
        // logger.debug("== getHomeCardDataCreditCard == ,ccCard: {}", IBUtils.attribute2Str(ccCard));
        // }
        // ObjectUtils.isEmpty()
        output.setCardDataCreditCard(ccCard);
    }

    public int getCreditCardBillMonthViaCEW314R(DataInput input) {
        int monthInInt = LocalDate.now().getMonthValue();
        CEW314RResponse response;
        try {
            response = creditCardResource.sendCEW314R(input.getCustId(), "1");
            if (logger.isDebugEnabled()) {
                logger.debug("== getCreditCardBillMonthViaCEW314R == response: {}", IBUtils.attribute2Str(response));
            }
            if (null != response && CollectionUtils.isNotEmpty(response.getA021Repeats())) {
                Date aacldy = response.getA021Repeats().get(0).getAacldy();
                logger.debug("== getCreditCardBillMonthViaCEW314R == A021 aacldy: {}", aacldy);
                if (null != aacldy) {
                    monthInInt = DateUtils.getMonthIntFromDate(aacldy);
                }
            }
        }
        catch (ServiceException e) {
            logger.error("取得帳單明細 sendCEW314R 查詢失敗 ", e);
        }
        logger.debug("== getCreditCardBillMonthViaCEW314R == return monthInInt: {}", monthInInt);
        return monthInInt;
    }

    /**
     * 取得產品「投資」資料
     */
    public void getHomeCardDataInvestment(DataInput input, DataOutput output) {

        logger.debug("== getHomeCardDataInvestment == > custId[{}], nameCode[{}], isDbu[{}], isHaveCreditCard[{}]", input.getCustId(), input.getNameCode(), input.isDbu(), input.isHaveCreditCard());
        InvestmentOverview overview = userResource.getInvestOverviewData(input.getCustId(), input.getNameCode(), input.isDbu(), input.isHaveCreditCard());

        // EB12020011 數位存款開戶 -> (H) 單一戶名代碼檢核
        // 取得所有的EB12020011_Rs.TxRepeat.SUBID，比對SUBID若有任一值不相同，代表不為單一戶名
        Boolean singleName = null;
        if (input.getAiBankUser() != null) {
            try {
                userDataCacheService.checkIsSingleAccount(input.getAiBankUser());
                singleName = true;
            }
            catch (ActionException e) {
                logger.error("error while checking for is single account, probably not single account, {}", e.getMessage());
                singleName = false;
            }
        }

        if (null == overview.getProductValMap() || overview.getProductValMap().isEmpty()) {
            this.getHomePageCardV2(ProductType.INVESTMENT.getCardId(), input, output);
        }
        else {
            List<OdsVpbnd1002> odsVpbnd1002s = investResource.queryOdsVpbnd1002(input.getCustId(), "BOND");
            BigDecimal odsVpbndAssetTotal = odsVpbnd1002s.stream().filter(Objects::nonNull).map(OdsVpbnd1002::getMarketValueAmtTwd).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal investTtlAmt = overview.getInvestTtlAmt();
            BigDecimal addInOdsAmt = investTtlAmt.add(odsVpbndAssetTotal);
            overview.setInvestTtlAmt(addInOdsAmt);
            overview.getProductValMap().put(InvestProductType.FOREIGN_BONDS.getMemo(), odsVpbndAssetTotal);

            // 先檢查是否完全無投資內容, 全部等於0
            if (BigDecimal.ZERO.compareTo(overview.getInvestTtlAmt()) >= 0) {
                this.getHomePageCardV2(ProductType.INVESTMENT.getCardId(), input, output);
                return;
            }

            CardDataInvestment investCard = new CardDataInvestment();

            investCard.setTotalAmount(overview.getInvestTtlAmt());

            // @formatter:off
            /*
             * 根據現值排序, 假如現值相同, 則依序: 
             * 「奈米投」、「基金」、「海外ETF/股票」、「海外債券」、「結構型商品」、「組合式商品」、「黃金存摺」、「外國債券(自營)」
             */
            // @formatter:on
            List<TopValueProduct> topValueProducts = new ArrayList<>();
            List<Map.Entry<String, BigDecimal>> productEntries = overview.getProductValMap().entrySet().stream().filter(entry -> null != entry.getValue()).sorted((a, b) -> {
                if (b.getValue().compareTo(a.getValue()) > 0)
                    return 1;
                else if (b.getValue().compareTo(a.getValue()) < 0)
                    return -1;
                else {
                    Integer sortOrderA = INVEST_SORT_ORDER.get(InvestProductType.find(a.getKey()));
                    Integer sortOrderB = INVEST_SORT_ORDER.get(InvestProductType.find(b.getKey()));
                    return sortOrderA - sortOrderB;
                }
            }).collect(Collectors.toList());

            // 卡片只放現值最高的前2名
            for (int i = 0; i < 2; i++) {
                Map.Entry<String, BigDecimal> entry = productEntries.get(i);
                InvestProductTypeDisplayName investProductType = InvestProductTypeDisplayName.findByMemo(entry.getKey());
                TopValueProduct product = new TopValueProduct(investProductType.getProductTypeName(), entry.getValue(), null, investProductType.name());
                topValueProducts.add(product);
            }
            investCard.setTopValueProducts(topValueProducts);

            HomepageCard homepageCard = getHomepageCardByCardId(ProductType.INVESTMENT.getCardId(), null);
            investCard.setCardTarget(homepageCard.getCardTarget());
            investCard.setSingleName(singleName);

            output.setCardDataInvestment(investCard);
        }

    }

    /**
     * 判斷誤別碼及回傳值
     */
    private String checkAndGetUidDup(String uidDup) {
        if (StringUtils.isBlank(uidDup) || "0".equals(uidDup)) {
            return StringUtils.EMPTY;
        }
        return uidDup;
    }

    public void getHomeCardDataLoan(DataInput input, DataOutput output) throws ActionException {

        List<LoanAccount> loanAccounts = new ArrayList<>();
        String custId = StringUtils.EMPTY;

        boolean loanHomecardOptimizeFlag = Boolean.parseBoolean(systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "AIBANK_LOAN_HOMECARD_OPTIMIZE_FLAG", "false"));

        try {
            if (null != input.getAiBankUser()) {
                custId = input.getAiBankUser().getCustIdAndCheckDup();
                if (loanHomecardOptimizeFlag) {
                    // 使用優化後的邏輯
                    loanAccounts = userDataCacheService.getAccountInfoHomeCardLoan(input.getAiBankUser(), false);
                }
                else {
                    loanAccounts = userDataCacheService.getAccountInfoLoan(input.getAiBankUser(), false);
                }

            }
            else {
                custId = input.getCustId() + checkAndGetUidDup(input.getUidDup());
                if (loanHomecardOptimizeFlag) {
                    // 使用優化後的邏輯
                    loanAccounts = userDataCacheService.getAccountInfoHomeCardLoan(input.getCustId(), input.getUidDup(), input.getUserId(), input.getCompanyKind(), input.getNameCode());
                }
                else {
                    loanAccounts = userDataCacheService.getAccountInfoLoan(input.getCustId(), input.getUidDup(), input.getUserId(), input.getCompanyKind(), input.getNameCode());
                }

            }
        }
        catch (ServiceException e) {
            // 若電文發送失敗Timeout，導至「共通錯誤頁」顯示錯誤訊息(SVC07002)：資料查詢失敗，如有任何疑問，請洽客服02-8751-6665。
            logger.error("==[getAccountInfoLoan]== fail, ", e);
            throw ExceptionUtils.getActionException(e);
        }

        if (CollectionUtils.isEmpty(loanAccounts)) {
            // 沒有貸款帳號，顯示預設廣告
            HomeCardLoanRequest request = new HomeCardLoanRequest();
            request.setCustId(custId);
            HomeCardLoanResponse response = loanResource.getLoanCustomerGroupData(request);

            boolean bizUser = StringUtils.length(custId) == 8; // 企業客群
            String taGroup = bizUser ? "5" : "4";
            if (null != response && null != response.getAibFlag()) {
                taGroup = response.getAibFlag().toString();
                input.setHomeCardLoanResponse(response);
            }

            input.setTaGroup(taGroup);

            getHomePageCardV2(ProductType.LOAN.getCardId(), input, output);
            return;
        }

        // 若任一貸款帳號為特殊貸款狀態
        boolean isSpecialStatus = loanAccounts.stream().allMatch(la -> la.isSpecialStatusButI02());

        if (isSpecialStatus) {
            // 特殊狀態有特定版型，不用塞其他內容
            CardDataLoan cardDataLoan = new CardDataLoan();
            cardDataLoan.setUnusualFlag(StringUtils.Y);
            output.setCardDataLoan(cardDataLoan);
            return;
        }
        else {
            loanAccounts = loanAccounts.stream().filter(la -> !la.isSpecialStatusButI02()).collect(Collectors.toList());
        }
        logger.debug("==[loanAccounts]== after filter, size:{}", loanAccounts.size());

        // ACCOUNT_INFO_LOAN裡的ProductType
        Set<String> productTypeSet = new HashSet<>();
        boolean withInstallmentLoan = false; // 有分期型
        boolean withRevolvingLoan = false; // 有循環型

        BigDecimal totalLoanAmt = BigDecimal.ZERO;
        // 「還可動用」金額加總
        BigDecimal totalOdavail1 = BigDecimal.ZERO;

        for (var loanAcct : loanAccounts) {
            String productType = loanAcct.getProductType();
            logger.debug("=productType=: [{}], loanAcct: {}", productType, IBUtils.attribute2Str(loanAcct));
            // productType => C 之外都是分期型，所以之分 A / C
            if (!"C".equals(productType)) {
                productType = "A";
            }
            productTypeSet.add(productType);

            boolean revolvingLoan = "C".equals(productType);
            boolean installmentLoan = Stream.of("A", "B").toList().contains(productType);

            if (installmentLoan) {
                withInstallmentLoan = installmentLoan;
            }
            if (revolvingLoan) {
                withRevolvingLoan = revolvingLoan;
            }

            if (CurrencyCode.TWD.equals(loanAcct.getCurCod())) {
                totalLoanAmt = totalLoanAmt.add(NumberUtils.defaultValue(loanAcct.getActBal(), BigDecimal.ZERO));
                if (revolvingLoan) {
                    totalOdavail1 = totalOdavail1.add(NumberUtils.defaultValue(loanAcct.getOdAvil1(), BigDecimal.ZERO));
                }
            }
            else {
                BigDecimal avalAmtTwd = calculateExchangeRate(loanAcct.getCurCod(), loanAcct.getActBal());
                totalLoanAmt = totalLoanAmt.add(avalAmtTwd);
                if (revolvingLoan) {
                    totalOdavail1 = totalOdavail1.add(calculateExchangeRate(loanAcct.getCurCod(), loanAcct.getOdAvil1()));
                }
            }
        }

        CardDataLoan cardDataLoan = new CardDataLoan();
        cardDataLoan.setTotalLoanAmt(totalLoanAmt);
        cardDataLoan.setTotalOdavail1(totalOdavail1);
        cardDataLoan.setWithInstallmentLoan(withInstallmentLoan);
        cardDataLoan.setWithRevolvingLoan(withRevolvingLoan);
        cardDataLoan.setLoanTypeCount(productTypeSet.size());
        // 有分期行貸款 且 貸款類型只有一種 => 只有分期信貸
        cardDataLoan.setOnlyInstallmentLoan(cardDataLoan.getLoanTypeCount() == 1 && cardDataLoan.isWithInstallmentLoan());

        HomeCardLoanRequest request = new HomeCardLoanRequest();
        request.setCustId(custId);
        HomeCardLoanResponse response = loanResource.getLoanCustomerGroupData(request);
        // 最高增貸
        if (cardDataLoan.isOnlyInstallmentLoan()) {
            // 查ODS_LOANCUST_LIST_? 有資料，且aibFlag 是 1 or 2 or 3
            if (Objects.nonNull(response.getAibFlag()) && Stream.of(1, 2, 3).anyMatch(flg -> Objects.equals(flg, response.getAibFlag()))) {
                cardDataLoan.setLoanIncreaseMaxAmt(response.getLoanIncreaseMaxAmt());
            }
            else {
                // 個人戶不在1,2,3類客群時
                String aibAmtValue = StringUtils.defaultString(dicCacheManager.getValue("LOAN", "LOANCUST4_AIB_AMT"), "3000000");
                Long aibAmt = ConvertUtils.str2Long(aibAmtValue);
                cardDataLoan.setLoanIncreaseMaxAmt(aibAmt);
            }
        }

        HomepageCard homepageCard = getHomepageCardByCardId(ProductType.LOAN.getCardId(), null);
        cardDataLoan.setCardTarget(homepageCard.getCardTarget());
        output.setCardDataLoan(cardDataLoan);

    }

    /**
     * <pre>
     * 計算外幣金額乘以匯率，取約當臺幣金額
     * 使用前ㄧ工作日收盤價
     * </pre>
     *
     * @throws Exception
     */
    private BigDecimal calculateExchangeRate(String foreignCurrency, BigDecimal foreignAmount) throws ActionException {

        BigDecimal twdAmount = BigDecimal.ZERO;

        if (StringUtils.isNotBlank(foreignCurrency) && foreignAmount != null && foreignAmount.compareTo(BigDecimal.ZERO) != 0) {

            ExchangeRateHistory rateHistory = exchangeRateHistoryCacheManager.getPreviousBizdayExchangeRates().stream().filter(x -> StringUtils.equals(foreignCurrency, x.getCurrencyEname1())).findFirst().orElse(null);

            if (rateHistory == null || rateHistory.getBuy() == null) {
                logger.error(String.format("Can't find Yesterday close rate of currency [%s]", foreignCurrency));
                throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
            }
            twdAmount = foreignAmount.multiply(rateHistory.getBuy()).setScale(0, RoundingMode.HALF_UP);
        }
        return twdAmount;
    }

    /**
     * 取得產品「保險」資料
     */
    public void getHomeCardDataInsurance(DataInput input, DataOutput output) {
        CardDataInsurance cardDataInsurance = new CardDataInsurance();
        // 取得使用者生日
        if (null == input.getBirthday()) {
            input.setBirthday(userResource.getCustomerBirthday(input.getCustId(), "11"));
        }
        getInsurance(input.getCustId(), input.getBirthday(), input.getLoginIp(), input.getSession(), cardDataInsurance);
        countInsuranceAmmount(cardDataInsurance);

        output.setCardDataInsurance(cardDataInsurance);
    }

    /**
     * 取得產品「證券」資料 進來這裡表示已登入 or 有免登速查
     */
    public void getHomeCardDataStock(DataInput input, DataOutput output) {
        AIBankUser user = input.getAiBankUser();
        String custId = user.getCustId();
        // 有nameCode表示為一般會員 (免登)
        String nameCode = user.getNameCode();
        Date birthDay = user.getBirthDay();
        // 發送EB67050取得用戶生日
        if (birthDay == null) {
            birthDay = userResource.getCustomerBirthday(custId, "11");
            input.getAiBankUser().setBirthDay(birthDay);
        }

        output.setCardDataStock(new CardDataStock());
        AiDataSyncStatusModel model = userResource.getUserDataSyncStatusInfo(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind());

        // 信用卡會員顯示廣告
        if (CustomerType.CARDMEMBER.equals(user.getCustomerType()) || StringUtils.isBlank(nameCode)) {
            getHomePageCardV2(ProductType.STOCK.getCardId(), input, output);
            return;
        }

        // 未登入有開免登
        // 已登入但無資料
        // 已登入但資料彙整關閉
        // 未開啟自動彙整
        if ((model == null || (model != null && model.getSecurStatus() == null)) || StringUtils.equals("N", model.getSecurStatus())) {
            output.getCardDataStock().setCardState(StockCardStateType.NOT_ACTIVE.getState());
            return;
        }

        // 有開啟自動彙整
        // 打API獲取證券資訊(富邦證券整註記異動)
        output.getCardDataStock().setAutoSummary("Y");
        FubonStockApiRequest apiRequest = new FubonStockApiRequest();
        apiRequest.setAssetcode("ALL");
        apiRequest.setIdno(custId);
        apiRequest.setBdate(DateUtils.getDateTimeStrByDateFormat(birthDay, "yyyyMMdd"));
        apiRequest.setChannel("T_AI_BANK");
        FubonStockApiResponse apiResponse = investResource.getFubonStockInfo(apiRequest);

        // 資料撈取失敗
        if (apiResponse.getIsValid() == null) {
            output.getCardDataStock().setCardState(StockCardStateType.FAILED.getState());
            return;
        }

        // 已銷戶
        if ("N".equals(apiResponse.getIsValid())) {
            output.getCardDataStock().setCardState(StockCardStateType.NOT_FOUND.getState());
            return;
        }

        // API成功
        output.getCardDataStock().setTotalAmount(Optional.ofNullable(apiResponse.getSumAssetAmtTwd()).orElse(BigDecimal.ZERO));
        List<AssetData> assetData = apiResponse.getDatas();
        List<AssetDetailedData> details = assetData.stream().map(AssetData::getAssets).flatMap(Collection::stream).collect(Collectors.toList());
        BigDecimal inCountryStocksSum = details.stream().filter(dt -> StringUtils.equals("IN", StockType.findByAssetCode(dt.getAssetcode()).getType())).map(AssetDetailedData::getAssetAmtTwd).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal outCountryStocksSum = details.stream().filter(dt -> StringUtils.equals("OUT", StockType.findByAssetCode(dt.getAssetcode()).getType())).map(AssetDetailedData::getAssetAmtTwd).reduce(BigDecimal.ZERO, BigDecimal::add);

        output.getCardDataStock().setInCountryTotalAmount(inCountryStocksSum);
        output.getCardDataStock().setOutCountryTotalAmount(outCountryStocksSum);
        output.getCardDataStock().setCardState(StockCardStateType.SUCCESS.getState());
    }

    /**
     * 取得產品「證券」資料 進來這裡表示已登入 or 有免登速查
     */
    public void getHomeCardDataStockV2(DataInput input, DataOutput output) {
        AIBankUser user = input.getAiBankUser();
        String custId = user.getCustId();
        // 有nameCode表示為一般會員 (免登)
        String nameCode = user.getNameCode();
        Date birthDay = user.getBirthDay();
        // 發送EB67050取得用戶生日
        if (birthDay == null) {
            birthDay = userResource.getCustomerBirthday(custId, "11");
            input.getAiBankUser().setBirthDay(birthDay);
        }

        output.setCardDataStock(new CardDataStock());
        AiDataSyncStatusModel model = userResource.getUserDataSyncStatusInfo(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind());

        // 信用卡會員顯示廣告
        if (CustomerType.CARDMEMBER.equals(user.getCustomerType()) || StringUtils.isBlank(nameCode)) {
            getHomePageCardV2(ProductType.STOCK.getCardId(), input, output);
            return;
        }

        // 未登入有開免登
        // 已登入但無資料
        // 已登入但資料彙整關閉
        // 未開啟自動彙整
        if ((model == null || (model != null && model.getSecurStatus() == null)) || StringUtils.equals("N", model.getSecurStatus())) {
            output.getCardDataStock().setCardState(StockCardStateType.NOT_ACTIVE.getState());
            return;
        }

        // 有開啟自動彙整
        // 打API獲取證券資訊(富邦證券整註記異動)
        output.getCardDataStock().setAutoSummary("Y");
        FubonStockTotalApiRequest apiRequest = new FubonStockTotalApiRequest();
        apiRequest.setIdno(custId);
        apiRequest.setBdate(DateUtils.getDateTimeStrByDateFormat(birthDay, "yyyyMMdd"));

        FubonStockTotalApiResponse apiResponse = investResource.getFubonStockTotalInfo(apiRequest);
        // 資料撈取失敗
        if (apiResponse.getIsValid() == null) {
            output.getCardDataStock().setCardState(StockCardStateType.FAILED.getState());
            return;
        }

        // 已銷戶
        if ("N".equals(apiResponse.getIsValid())) {
            output.getCardDataStock().setCardState(StockCardStateType.NOT_FOUND.getState());
            return;
        }

        // 有任一帳戶(證券、複委託、期貨)但未勾選同意授權
        if ("U".equals(apiResponse.getIsValid())) {
            output.getCardDataStock().setCardState(StockCardStateType.NOT_AGREE.getState());
            return;
        }

        // API成功
        List<AssetItemTotalData> assetTotalData = apiResponse.getAssets();
        List<TopValueAssetAccount> topValueAssetAccounts = toTop2Accounts(assetTotalData);

        output.getCardDataStock().setTotalAmount(Optional.ofNullable(apiResponse.getTotalAssetAmtTwd()).orElse(BigDecimal.ZERO));
        output.getCardDataStock().setTopValueAssetAccountList(topValueAssetAccounts);
        output.getCardDataStock().setCardState(StockCardStateType.SUCCESS.getState());
    }

    /**
     * 依 assetCode 找 AssetItemType；再累加進對應帳戶
     */
    public List<TopValueAssetAccount> toTop2Accounts(List<AssetItemTotalData> rawList) {

        Map<AssetAccountType, AssetAccount> map = new EnumMap<>(AssetAccountType.class);

        rawList.forEach(item -> AssetItemType.of(item.getAssetCode()).ifPresent(t -> map.computeIfAbsent(t.assetAccountType, AssetAccount::new).add(item)));

        /* 確保三帳戶都存在；若無資產金額為 0 */
        Arrays.stream(AssetAccountType.values()).forEach(t -> map.computeIfAbsent(t, AssetAccount::new));

        Comparator<AssetAccount> comp = (a1, a2) -> {
            int m = a2.getTotalAmtTwd().compareTo(a1.getTotalAmtTwd()); // 金額大 > 小
            return (m != 0) ? m : Integer.compare(AssetAccountType.valueOf(a1.getAccountType()).ordinal(), AssetAccountType.valueOf(a2.getAccountType()).ordinal()); // 金額同：SECURITIES > SUBBROKER > FUTURES
        };

        return map.values().stream().sorted(comp).limit(2) // 固定回 2 筆（可能含 0 元帳戶）
                .map(a -> new TopValueAssetAccount(a.getAccountType(), a.getTotalAmtTwd())).collect(Collectors.toList());
    }

    /**
     * taGroup => 貸款客群
     */
    protected HomepageCard getHomepageCardByCardId(int cardId, String taGroup) {
        Predicate<HomepageCard> filterNGNQU001HomepageCard = (hc) -> TASKID_NGNQU001.equals(hc.getCardUsedTaskId()) && TEMPLATE_HOMEPAGE.equals(hc.getCardTemplate());
        List<HomepageCard> hcs = homepageCardCacheManager.getMulHomePageCardByPredicate(filterNGNQU001HomepageCard);

        if (CollectionUtils.isEmpty(hcs)) {
            return new HomepageCard();
        }
        hcs = hcs.stream().filter(hc -> hc.getCardId() == cardId && Objects.equals(0, hc.getCardUsed())).collect(Collectors.toList());

        HomepageCard targetTemp;
        HomepageCard target = new HomepageCard();

        if (CollectionUtils.isNotEmpty(hcs)) {
            if (StringUtils.isBlank(taGroup)) {
                targetTemp = hcs.stream().findFirst().get();
            }
            else {
                targetTemp = hcs.stream().filter(hc -> taGroup.equals(hc.getTaGroup())).findFirst().orElse(new HomepageCard());
            }
            if (null != targetTemp) {
                target.setCardKey(targetTemp.getCardKey());
                target.setCardId(targetTemp.getCardId());
                target.setCardUsed(targetTemp.getCardUsed());
                target.setCardUsedTaskId(targetTemp.getCardUsedTaskId());
                target.setCardTemplate(targetTemp.getCardTemplate());
                target.setCardName(targetTemp.getCardName());
                target.setCardSort(targetTemp.getCardSort());
                target.setCardBg(targetTemp.getCardBg());
                target.setCardIcon(targetTemp.getCardIcon());
                target.setCardDesc(targetTemp.getCardDesc());
                target.setCardTarget(targetTemp.getCardTarget());
                target.setCardParam(targetTemp.getCardParam());
                target.setCardFold(targetTemp.getCardFold());
                target.setShowFlag(targetTemp.getShowFlag());
                target.setQuery(targetTemp.getQuery());
                target.setTaGroup(targetTemp.getTaGroup());
                target.setCreateTime(targetTemp.getCreateTime());
                target.setUpdateTime(targetTemp.getUpdateTime());
                target.setStartTime(targetTemp.getStartTime());
                target.setEndTime(targetTemp.getEndTime());
                target.setTaType(targetTemp.getTaType());
                target.setCardSortDynamic(targetTemp.getCardSortDynamic());
                target.setFromNoApplyOnline(targetTemp.getFromNoApplyOnline());
                target.setFromNoCallback(targetTemp.getFromNoCallback());
            }
        }
        return target;
    }

    /**
     * 依據HomePageCard cardId取得預設HomePageCard 資料
     */
    public void getHomePageCardV2(int cardId, DataInput input, DataOutput output) {

        Predicate<HomepageCard> filterNGNQU001HomepageCard = (hc) -> TASKID_NGNQU001.equals(hc.getCardUsedTaskId()) && TEMPLATE_HOMEPAGE.equals(hc.getCardTemplate());
        List<HomepageCard> hcs = null;

        if (StringUtils.isNotBlank(input.getCustId())) {
            hcs = homepageCardCacheManager.getMulHomePageCardByPredicate(filterNGNQU001HomepageCard, input.getCustId());
        }
        // fortify: null deference
        if (hcs == null || CollectionUtils.isEmpty(hcs) || hcs.stream().noneMatch(hc -> null != hc.getCardId() && hc.getCardId() == cardId)) {
            // 沒custId，取非UNICA資料
            filterNGNQU001HomepageCard = filterNGNQU001HomepageCard.and((hc) -> 0 == hc.getTaType() || (null != hc.getCardId() && hc.getCardId() == 4 && hc.getTaType() == 1));
            hcs = homepageCardCacheManager.getMulHomePageCardByPredicate(filterNGNQU001HomepageCard);
        }

        if (CollectionUtils.isNotEmpty(hcs)) {

            IBUtils.sort(hcs, new String[] { "updateTime" }, new boolean[] { true });

            Optional<HomepageCard> homepageCardOpt = hcs.stream().filter(hc -> hc.getCardId() == cardId && hc.getCardUsed().intValue() == 0).findFirst();
            if (homepageCardOpt.isPresent()) {
                output.setHomepageCard(homepageCardOpt.get());
                switch (input.getProductType()) {
                case DEPOSIT -> {
                    output.setCardDataDeposit(new CardDataDeposit(homepageCardOpt.get()));
                }
                case CREDITCARD -> {
                    output.setCardDataCreditCard(new CardDataCreditCard(homepageCardOpt.get()));
                }
                case INVESTMENT -> {
                    output.setCardDataInvestment(new CardDataInvestment(homepageCardOpt.get()));
                }
                case LOAN -> {
                    HomepageCard homepageCard = getHomepageCardByCardId(ProductType.LOAN.getCardId(), input.getTaGroup());

                    workOnLoanCard(homepageCard, input);

                    output.setCardDataLoan(new CardDataLoan(homepageCard));
                }
                case INSURANCE -> {
                    output.setCardDataInsurance(new CardDataInsurance(homepageCardOpt.get()));
                }
                case STOCK -> {
                    output.setCardDataStock(new CardDataStock(homepageCardOpt.get()));
                }
                case UNKNOWN -> {
                    throw new UnsupportedOperationException("Unimplemented case: " + input.getProductType());
                }
                default -> throw new IllegalArgumentException("Unexpected value: " + input.getProductType());
                }
            }
        }
    }

    /**
     * 處理(加工)貸款廣告卡片資料
     */
    private void workOnLoanCard(HomepageCard homepageCard, DataInput input) {

        HomeCardLoanResponse responseData = null;
        Long aibAmt = null;
        Integer aibRate1Period = null;
        BigDecimal aibRate1 = null;
        BigDecimal aibRate2 = null;
        Integer aibPeriod = null;

        if (null != input.getHomeCardLoanResponse()) {
            responseData = input.getHomeCardLoanResponse();
            aibAmt = responseData.getAibAmt();
            aibRate1Period = responseData.getAibRate1Period();
            aibRate1 = responseData.getAibRate1();
            aibRate2 = responseData.getAibRate2();
            aibPeriod = responseData.getAibPeriod();
        }
        else {
            if ("4".equals(input.getTaGroup())) {
                // 個人戶不在1,2,3類客群時
                String aibAmtValue = StringUtils.defaultString(dicCacheManager.getValue("LOAN", "LOANCUST4_AIB_AMT"), "3000000");
                aibAmt = ConvertUtils.str2Long(aibAmtValue);
            }
        }

        logger.debug("==[workOnLoanCard]== responseData: {}", IBUtils.attribute2Str(responseData));

        String cardDesc = StringUtils.defaultString(homepageCard.getCardDesc(), "");

        // LOAN_TAG_AIB_AMT = "%AIB_AMT%";
        // LOAN_TAG_AIB_RATE1_PERIOD = "%AIB_RATE1_PERIOD%";
        // LOAN_TAG_AIB_RATE1 = "%AIB_RATE1%";
        // LOAN_TAG_AIB_RATE2 = "%AIB_RATE2%";
        // LOAN_TAG_AIB_PERIOD = "%AIB_PERIOD%";

        if (StringUtils.contains(cardDesc, LOAN_TAG_AIB_AMT)) {
            String replace = "";

            if (Locale.US.equals(input.getLocale())) {
                replace = aibAmt != null ? (aibAmt.intValue() / 10000) + "" : "";
            }
            else {
                replace = aibAmt != null ? StringUtils.getMoneyStr((aibAmt.intValue() / 10000) + "") : "";
            }

            cardDesc = StringUtils.replace(cardDesc, LOAN_TAG_AIB_AMT, replace);
        }

        if (StringUtils.contains(cardDesc, LOAN_TAG_AIB_RATE1_PERIOD)) {
            String replace = aibRate1Period != null ? aibRate1Period.toString() : "";
            cardDesc = StringUtils.replace(cardDesc, LOAN_TAG_AIB_RATE1_PERIOD, replace);
        }

        if (StringUtils.contains(cardDesc, LOAN_TAG_AIB_RATE1)) {
            String replace = aibRate1 != null ? aibRate1.toString() : "";
            cardDesc = StringUtils.replace(cardDesc, LOAN_TAG_AIB_RATE1, replace);
        }

        if (StringUtils.contains(cardDesc, LOAN_TAG_AIB_RATE2)) {
            String replace = aibRate2 != null ? aibRate2.toString() : "";
            cardDesc = StringUtils.replace(cardDesc, LOAN_TAG_AIB_RATE2, replace);
        }

        if (StringUtils.contains(cardDesc, LOAN_TAG_AIB_PERIOD)) {
            String replace = aibPeriod != null ? (aibPeriod.intValue() / 12) + "" : "";
            cardDesc = StringUtils.replace(cardDesc, LOAN_TAG_AIB_PERIOD, replace);
        }

        homepageCard.setCardDesc(cardDesc);
    }

    public void getDbuObu(AIBankUser aiBankUser, QuickSearchResponse quickSearchResponse, DataOutput output) throws ActionException {
        logger.debug(" == getDbuObu == have loginUSER:{}, quickSearchResponse: {}", null != aiBankUser, IBUtils.attribute2Str(quickSearchResponse));

        AIBankUser aiBankUserForQuery = null;
        int companyKind = -1;

        if (null != aiBankUser) {
            aiBankUserForQuery = aiBankUser;
            if (null != aiBankUser.getCompanyVo()) {
                companyKind = NumberUtils.defaultValue(aiBankUser.getCompanyVo().getCompanyKind(), -1);
            }
        }
        else if (null != quickSearchResponse && quickSearchResponse.haveQuickSearchOn()) {
            UserVo vo = new UserVo();
            vo.setUserUuid(quickSearchResponse.getUserId());
            vo.setNameCode(quickSearchResponse.getNameCode());
            aiBankUserForQuery = new AIBankUser(vo);
            aiBankUserForQuery.setCustId(quickSearchResponse.getCustId());
            CompanyVo cv = new CompanyVo();
            cv.setUidDup(quickSearchResponse.getUidDup());
            cv.setCompanyKind(quickSearchResponse.getCompanyKind());
            companyKind = NumberUtils.defaultValue(quickSearchResponse.getCompanyKind(), -1);
            aiBankUserForQuery.setCompanyVo(cv);
        }
        logger.debug("[getDbuObu] aiBankUserForQuery: {}", IBUtils.attribute2Str(aiBankUserForQuery));

        // 信用卡網路會員不查DBU/OBU
        // 判斷方式：1.company = [3 or 4]
        // 2. 登入方式是「不是」一般會員登入
        // 3. 或沒有nameCode
        if (null == aiBankUserForQuery || companyKind == -1 || companyKind == 3 || companyKind == 4 || StringUtils.isBlank(aiBankUserForQuery.getNameCode())) {
            logger.debug("== [getDbuObu] == aiBankUserForQuery is null");
            output.setDbuObu("");
            return;
        }
        CompanyBUType buType = userDataCacheService.getBuType(aiBankUserForQuery);
        logger.debug("[getDbuObu] dbuObu: {}", buType);
        output.setDbuObu(buType.getMemo());
    }

    /**
     * 取所有首頁牌卡，供前端比對是否有效
     */
    public void getAllHomepageCards(DataOutput output, boolean loggedIn) {
        Predicate<HomepageCard> filterNGNQU001HomepageCard = (hc) -> TASKID_NGNQU001.equals(hc.getCardUsedTaskId()) && TEMPLATE_HOMEPAGE.equals(hc.getCardTemplate());
        List<HomepageCard> homepageCards = homepageCardCacheManager.getMulHomePageCardByPredicate(filterNGNQU001HomepageCard);
        if (CollectionUtils.isNotEmpty(homepageCards)) {
            Predicate<HomepageCard> cardsForNGNQU001 = hc -> (null != hc.getCardUsed() && hc.getCardUsed() == 0);

            if (!loggedIn) {
                // 未登入只取TaType == 0，貸款牌卡除外
                cardsForNGNQU001 = cardsForNGNQU001.and(hc -> hc.getTaType() == 0 || (null != hc.getCardId() && hc.getCardId() == 4 && hc.getTaType() == 1));
            }

            Stream<HomepageCard> homepageCardStream = homepageCards.stream().filter(cardsForNGNQU001);
            Set<Integer> cardIds = homepageCardStream.map(HomepageCard::getCardId).collect(Collectors.toSet());
            output.setAllHomepageCardIds(new ArrayList<>(cardIds));
            return;
        }

        // no cards at all
        output.setAllHomepageCardIds(Collections.emptyList());
    }

    /**
     * 取得TABLE [NEW_FUNCTION_INTRO] 有效資料
     */
    public void getNewFunctionsIntro(AIBankUser aiBankUser, DataInput input, DataOutput dataOutput) {
        if (Objects.isNull(aiBankUser))
            return;

        List<CustomerGroupId> cgi = customerGroupIdCacheManager.getCustomerGroupIdByKey(aiBankUser.getCompanyKey());
        logger.debug("== getNewFunctionsIntro, CustomerGroupId ==> {}", cgi);

        // 只取對應到的語系
        List<NewFunctionIntro> newFunctionIntros = newFunctionIntroCacheManager.getNewFunctionIntrosByLocale(input.getLocale().toString());
        logger.debug("== getNewFunctionsIntro, newFunctionIntros ==> {}", cgi);

        if (CollectionUtils.isEmpty(newFunctionIntros))
            return;

        // 檢查由Cache取出的資料是否在時間上是否為有效資料
        Date now = new Date();
        newFunctionIntros = newFunctionIntros.stream().filter(nfi -> now.after(nfi.getStartTime()) && (null == nfi.getEndTime() || now.before(nfi.getEndTime()))).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(newFunctionIntros))
            return;

        // 沒有GroupId時，取[NEW_FUNCTION_INTRO] CUSTOMER_GROUP_ID = NULL 的資料
        Predicate<NewFunctionIntro> blankGroupId = nf -> StringUtils.isBlank(nf.getCustomerGroupId());

        if (CollectionUtils.isNotEmpty(cgi)) {
            List<String> groupIds = cgi.stream().map(CustomerGroupId::getGroupId).collect(Collectors.toList());
            Predicate<NewFunctionIntro> blankGroupIdsContained = nf -> groupIds.contains(nf.getCustomerGroupId());
            blankGroupId = blankGroupId.or(blankGroupIdsContained);
        }

        newFunctionIntros = newFunctionIntros.stream().filter(blankGroupId).collect(Collectors.toList());
        logger.debug("== getNewFunctionsIntro, newFunctionIntros matching customerGroupId ==> {}", newFunctionIntros);
        IBUtils.sort(newFunctionIntros, "sort", false);
        dataOutput.setNewFunctionIntros(newFunctionIntros);
    }

    public BigDecimal getRoeByInvestProductType(DataInput input, InvestProductType investProductType) {
        return switch (investProductType) {
        case GOLD -> getGoldRoe(input.getCustId(), input.getNameCode());
        case NANO -> getNanoRoe(input.getCustId(), input.getNameCode(), "1");
        case OFF_STOCKS -> getOffStocksRoe(input.getCustId(), input.getUserId(), input.getNameCode());
        case OFF_STRUCTURED, OFF_BONDS -> getOffStructuredOffBondsRoe(input.isDbu(), input.getCustId(), input.getUserId(), input.getNameCode(), "1", investProductType);
        case FUND -> getFundRoe(input.isDbu(), input.getAiBankUser() == null ? input.getCustId() : input.getAiBankUser().getCustIdAndCheckDup(), input.getNameCode(), "1");
        case COMBO -> getComboRoe(input.getCustId());
        case FOREIGN_BONDS -> getForeignBondsRoe(input.getCustId());
        default -> BigDecimal.ZERO;
        };
    }

    private BigDecimal getGoldRoe(String custId, String nameCode) {
        GD320140Req req = new GD320140Req();
        req.setSource(GD320140Source.TYPE_D.getCode());
        req.setCategory("1");
        req.setFuncCod(GD320140FuncCod.COD_01.getCode());
        req.setIdno(custId);
        req.setNameCod(nameCode);
        req.setFuncCod("01");
        req.setConveyEnd("Y");
        List<GoldPassBook> goldInfo = investResource.queryGoldInfoForInvestCard(req);
        if (goldInfo == null || goldInfo.isEmpty())
            return null;
        return goldInfo.stream().map(gd -> "-".equals(gd.getYieldS()) ? gd.getYield().negate() : gd.getYield()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getNanoRoe(String custId, String curAcctName, String func) {
        NMP8YBRes res = investResource.queryNMP8YB(custId, curAcctName, func);
        if (null != res && CollectionUtils.isNotEmpty(res.getRepeats())) {
            List<NMP8YBResRep> repeats = res.getRepeats();
            BigDecimal marketValTwdSum = new BigDecimal(repeats.stream().mapToLong(r -> r.getMarketValTwd() == null ? 0 : r.getMarketValTwd().intValue()).sum());
            BigDecimal increaseAmtTwdSum = new BigDecimal(repeats.stream().mapToLong(r -> r.getIncreaseAmtTwd() == null ? 0 : r.getIncreaseAmtTwd().intValue()).sum());
            return marketValTwdSum.subtract(increaseAmtTwdSum).divide(BigDecimal.ZERO.equals(increaseAmtTwdSum) ? BigDecimal.ONE : increaseAmtTwdSum, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        }
        return null;
    }

    private BigDecimal getOffStocksRoe(String custId, String userId, String nameCode) {
        List<StockOverview> offStocks = overviewService.getStockOverviewList(custId, userId, nameCode);
        if (CollectionUtils.isEmpty(offStocks)) {
            return null;
        }

        BigDecimal acctBalSum = offStocks.stream().map(StockOverview::getAcctBal).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal acctBalCostSum = offStocks.stream().map(StockOverview::getAcctBalCost).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        return acctBalSum.subtract(acctBalCostSum).divide(BigDecimal.ZERO.equals(acctBalCostSum) ? BigDecimal.ONE : acctBalCostSum, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
    }

    private BigDecimal getOffStructuredOffBondsRoe(boolean isDbu, String custId, String userId, String nameCode, String type, InvestProductType productType) {
        List<BondOverview> offStructuredItems;
        if (isDbu) {
            NJWEE010Req req = new NJWEE010Req();
            req.setCustId(custId);
            req.setCurAcctId(userId);
            req.setCurAcctName(nameCode);
            req.setType(type); // 全部
            req.setProduct(InvestProductType.OFF_BONDS.equals(productType) ? "1" : "2"); // 1: 海外債 2: 境外結構式商品
            offStructuredItems = investResource.queryNJWEE010(req);
        }
        else {
            AJWEE010Req req = new AJWEE010Req();
            req.setCustId(custId);
            req.setCurAcctId(userId);
            req.setCurAcctName(nameCode);
            req.setType(type); // 全部
            req.setProduct(InvestProductType.OFF_BONDS.equals(productType) ? "1" : "2"); // 1: 海外債 2: 境外結構式商品
            offStructuredItems = investResource.queryAJWEE010(req);
        }
        if (offStructuredItems == null || offStructuredItems.isEmpty())
            return null;

        List<BondOverview> validBonds = offStructuredItems.stream().filter(this::validStructuredItemForInvestCard).collect(Collectors.toList());
        // check if twd, if not -> convert into twd
        validBonds.forEach(bond -> {
            if (StringUtils.isNotBlank(bond.getTrustCur()) && !"TWD".equals(bond.getTrustCur())) {
                try {
                    bond.setAdjustInterest(calculateExchangeRate(bond.getTrustCur(), bond.getAdjustInterest()));
                }
                catch (ActionException e) {
                    logger.error("error while calculating exchange rate, {}", e.getMessage());
                }
            }
        });

        BigDecimal refAmtNTSum = validBonds.stream().map(bond -> {
            if (BondTxType.INVENTORY.equals(bond.getBondTxType()) || BondTxType.REDEMPTION_IN_TRANSIT.equals(bond.getBondTxType()))
                return bond.getRefAmtNT();
            if (BondTxType.SUBSCRIPTION_IN_TRANSIT.equals(bond.getBondTxType()))
                return bond.getTrustAmtNT();
            return BigDecimal.ZERO;
        }).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        /**
         * 持有境內外結構型商品且有回覆0001+1002，需先判斷他們的電文回傳欄位NJWEE010.EviNum是否相等，若相等的話{調整後累積現金配息折臺}請以0001為主，1002的就用0計算
         */
        Map<String, BondOverview> bondEviNumMapping = validBonds.stream().filter(item -> item.getBondTxType().isInventory()).collect(Collectors.toMap(BondOverview::getEviNum, Function.identity()));
        BigDecimal adjustInterestSum = validBonds.stream().map(bond -> {
            // 境內外結構型商品
            if (!InvestProductType.OFF_BONDS.equals(productType)) {
                if (bond.getBondTxType().isRedemptionInTransit() && bondEviNumMapping.containsKey(bond.getEviNum())) {
                    return BigDecimal.ZERO;
                }
                else {
                    return bond.getAdjustInterest();
                }
            }
            else {
                return bond.getAdjustInterest();
            }
        }).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal trustAmtNTSum = validBonds.stream().map(BondOverview::getTrustAmtNT).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        // 已付前手息
        BigDecimal frontfee1Sum = validBonds.stream().map(bond -> getTwdAmt(bond.getCurCode(), bond.getFrontfee1())).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        // 應收前手息
        BigDecimal frontfee2Sum = validBonds.stream().map(bond -> getTwdAmt(bond.getCurCode(), bond.getFrontfee2())).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        return refAmtNTSum.subtract(trustAmtNTSum).add(adjustInterestSum).subtract(frontfee1Sum).add(frontfee2Sum).divide(BigDecimal.ZERO.equals(trustAmtNTSum) ? BigDecimal.ONE : trustAmtNTSum, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
    }

    private BigDecimal getFundRoe(boolean isDbu, String custId, String curAcctName, String type) {
        List<FundOverview> fundOverviews;
        List<FundCalculateModel> validFunds;

        if (isDbu) {
            NFEE072Req req = new NFEE072Req();
            req.setCustId(custId);
            req.setCurAcctName(curAcctName);
            req.setType("1");
            fundOverviews = mutualFundResource.queryNFEE072(req);
            if (fundOverviews == null || fundOverviews.isEmpty())
                return null;
            validFunds = fundOverviews.stream().filter(this::validFundItemForDbu).map(fo -> {
                FundCalculateModel fm = new FundCalculateModel();
                fm.setInvAmt(NumberUtils.defaultValue(fo.getCurAmtNT(), BigDecimal.ZERO));
                fm.setAdjustAmt(NumberUtils.defaultValue(fo.getAccAllocateRewNT(), BigDecimal.ZERO));
                if (fo.getFundTxType().isInventory())
                    fm.setRefAmt(NumberUtils.defaultValue(fo.getCurBalNT(), BigDecimal.ZERO));
                else if (fo.getFundTxType().isPurchaseInTransit())
                    fm.setRefAmt(NumberUtils.defaultValue(fo.getCurAmtNT(), BigDecimal.ZERO));
                else if (fo.getFundTxType().isConversionInTransit() || fo.getFundTxType().isRedemptionInTransit())
                    fm.setRefAmt(NumberUtils.defaultValue(fo.getRefAmtNT(), BigDecimal.ZERO));

                return fm;
            }).collect(Collectors.toList());

        }
        else {
            NFEE071Req req = new NFEE071Req();
            req.setCustId(custId);
            req.setCurAcctName(curAcctName);
            req.setType("1");
            fundOverviews = mutualFundResource.queryNFEE071(req);
            if (fundOverviews == null || fundOverviews.isEmpty())
                return null;
            validFunds = fundOverviews.stream().filter(this::validFundItemForObu).map(fo -> {
                FundCalculateModel fm = new FundCalculateModel();
                fm.setInvAmt(NumberUtils.defaultValue(fo.getCurAmtNT(), BigDecimal.ZERO));
                fm.setAdjustAmt(NumberUtils.defaultValue(fo.getAccAllocateRewNT(), BigDecimal.ZERO));
                if (fo.getFundTxType().isSinglePurchase())
                    fm.setRefAmt(NumberUtils.defaultValue(fo.getCurBalNT(), BigDecimal.ZERO));
                else if (fo.getFundTxType().isPurchaseInTransit())
                    fm.setRefAmt(NumberUtils.defaultValue(fo.getCurAmtNT(), BigDecimal.ZERO));
                else if (fo.getFundTxType().isConversionInTransit() || fo.getFundTxType().isRedemptionInTransit())
                    fm.setRefAmt(NumberUtils.defaultValue(fo.getRefAmtNT(), BigDecimal.ZERO));

                return fm;
            }).collect(Collectors.toList());
        }

        BigDecimal refAmtTotal = validFunds.stream().map(FundCalculateModel::getRefAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal invAmtTotal = validFunds.stream().map(FundCalculateModel::getInvAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal adjustAmtTotal = validFunds.stream().map(FundCalculateModel::getAdjustAmt).reduce(BigDecimal.ZERO, BigDecimal::add);

        return refAmtTotal.add(adjustAmtTotal).subtract(invAmtTotal).divide(BigDecimal.ZERO.equals(invAmtTotal) ? BigDecimal.ONE : invAmtTotal, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
    }

    private BigDecimal getComboRoe(String custId) {
        List<StructuredOverview> siItems = investResource.sendSPWEBINQ(custId);
        List<StructuredOverview> dciItems = investResource.sendDCDBPBNK(custId);

        BigDecimal refAmt = BigDecimal.ZERO;
        BigDecimal plamt = BigDecimal.ZERO;
        BigDecimal ivamt2 = BigDecimal.ZERO;
        BigDecimal dcdAmountNtd = BigDecimal.ZERO;
        BigDecimal interestAmount = BigDecimal.ZERO;
        BigDecimal dcdAmount = BigDecimal.ZERO;

        if (CollectionUtils.isNotEmpty(siItems)) {
            refAmt = siItems.stream().map(item -> {
                if (item.getProdType().isSPWEBINQ())
                    return getTwdAmt(item.getCcy(), item.getRefAmtOri());
                if (item.getProdType().isSPWEBQ2())
                    return getTwdAmt(item.getCurCode(), item.getRefAmtOri());
                return BigDecimal.ZERO;
            }).reduce(BigDecimal.ZERO, BigDecimal::add);

            plamt = siItems.stream().map(item -> {
                if (item.getProdType().isSPWEBINQ())
                    return getTwdAmt(item.getCcy(), item.getPlamt());
                return BigDecimal.ZERO;
            }).reduce(BigDecimal.ZERO, BigDecimal::add);

            ivamt2 = siItems.stream().map(item -> {
                if (item.getProdType().isSPWEBINQ())
                    return getTwdAmt(item.getCcy(), item.getIvamt2());
                if (item.getProdType().isSPWEBQ2())
                    return item.getInvestAmt();
                return BigDecimal.ZERO;
            }).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        if (CollectionUtils.isNotEmpty(dciItems)) {
            dcdAmountNtd = dciItems.stream().map(item -> getTwdAmt(item.getCurrency(), item.getDcdCurAmount())).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
            interestAmount = dciItems.stream().map(item -> getTwdAmt(item.getCurrency(), item.getInterestAmount())).reduce(BigDecimal.ZERO, BigDecimal::add);
            dcdAmount = dciItems.stream().map(item -> getTwdAmt(item.getCurrency(), item.getDcdAmount())).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        BigDecimal refAmtSum = refAmt.add(dcdAmountNtd);
        BigDecimal adjAmtSum = plamt.add(interestAmount);
        BigDecimal invAmtSum = ivamt2.add(dcdAmount);

        if (BigDecimal.ZERO.compareTo(invAmtSum) == 0) {
            return BigDecimal.ZERO;
        }

        return refAmtSum.add(adjAmtSum).subtract(invAmtSum).divide(invAmtSum, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
    }

    private BigDecimal getForeignBondsRoe(String custId) {
        List<OdsVpbnd1002> odsVpbnd1002s = investResource.queryOdsVpbnd1002(custId, "BOND");
        if (CollectionUtils.isEmpty(odsVpbnd1002s)) {
            return BigDecimal.ZERO;
        }

        BigDecimal marketValueAmtTwdSum = odsVpbnd1002s.stream().filter(Objects::nonNull).map(OdsVpbnd1002::getMarketValueAmtTwd).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal investCostAmtTwdSum = odsVpbnd1002s.stream().filter(Objects::nonNull).map(OdsVpbnd1002::getInvestCostAmtTwd).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (investCostAmtTwdSum != null && BigDecimal.ZERO.compareTo(investCostAmtTwdSum) >= 0) {
            return BigDecimal.ZERO;
        }
        return marketValueAmtTwdSum.subtract(investCostAmtTwdSum).divide(investCostAmtTwdSum, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    }

    private Boolean validStructuredItemForInvestCard(BondOverview bondOverview) {
        return VALID_BOND_TX_TYPES_FOR_INVEST_CARD.contains(bondOverview.getBondTxType());
    }

    private Boolean validFundItemForDbu(FundOverview fundOverview) {
        return fundOverview.getFundTxType().isInventory() || fundOverview.getFundTxType().isPurchaseInTransit() || fundOverview.getFundTxType().isConversionInTransit() || fundOverview.getFundTxType().isRedemptionInTransit();
    }

    private Boolean validFundItemForObu(FundOverview fundOverview) {
        return fundOverview.getFundTxType().isSinglePurchase() || fundOverview.getFundTxType().isPurchaseInTransit() || fundOverview.getFundTxType().isConversionInTransit() || fundOverview.getFundTxType().isRedemptionInTransit();
    }

    public BigDecimal getTwdAmt(String curCode, BigDecimal amt) {
        if (amt == null)
            return BigDecimal.ZERO;

        if (StringUtils.equals(curCode, CurrencyCode.TWD))
            return amt;

        return amt.multiply(getRate(curCode));
    }

    public BigDecimal getRate(String curCode) {
        if (StringUtils.isBlank(curCode)) {
            logger.error("no curCode found: {}", curCode);
            return BigDecimal.ZERO;
        }
        List<ExchangeRateHistory> allExchangeRate = exchangeRateHistoryCacheManager.getExchangeRateHistory();
        List<ExchangeRateHistory> curRateHiss = allExchangeRate.stream().filter(exRate -> "0".equals(exRate.getRateFlag()) && "0".equals(exRate.getRateType()) && curCode.equals(exRate.getCurrencyEname1())).collect(Collectors.toList());
        BigDecimal rate = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(curRateHiss)) {
            IBUtils.sort(curRateHiss, new String[] { "txTime" }, new boolean[] { true });
            rate = curRateHiss.get(0).getBuy();
        }
        return rate;
    }

    /**
     * 取得保單
     *
     * @param output
     */
    private void getInsurance(String custId, Date birthDay, String loginIp, String sessionId, CardDataInsurance output) {

        List<AiFubonInsurDataResponse> aiFubonInsurDataResponses = insuranceResource.getAiFubonInsuranceData(custId, DateUtils.getSimpleDateFormat(birthDay));
        output.setAiFubonInsurDataResponses(aiFubonInsurDataResponses);
        logger.debug("AiFubonInsurDataResponse {}", IBUtils.attribute2Str(aiFubonInsurDataResponses));
        List<AiOtherInsurDataResponse> aiOtherInsurDataResponses = insuranceResource.getAiOtherInsurData(custId);
        logger.debug("aiOtherInsurDataResponse {}", IBUtils.attribute2Str(aiOtherInsurDataResponses));
        output.setAiOtherInsurDataResponses(aiOtherInsurDataResponses);
        WS00002Request ws00002Request = new WS00002Request();
        int countExpire = 0;
        boolean showCompulsoryInsNotice = false;
        ws00002Request.setOwnerId(custId);
        ws00002Request.setOwnerBirth(DateUtils.getDateStr(birthDay, ""));
        ws00002Request.setCustId(custId);
        ws00002Request.setRealIp(loginIp);
        ws00002Request.setSessionId(sessionId);
        try {
            WS00002Response ws00002Response = insuranceResource.getInsuranceInfo(ws00002Request);
            List<PolyInfoItem> polyInfoItems = new ArrayList<PolyInfoItem>();

            if (CollectionUtils.isNotEmpty(ws00002Response.getPolyInfo())) {

                // 先過濾所有有效(Status=1)的汽車機車強制險項目
                List<PolyInfoItem> tempItems = ws00002Response.getPolyInfo().stream().filter(poly -> StringUtils.equalsAny(poly.getProdType(), "02", "03") && StringUtils.equals(poly.getStatus(), "1")).collect(Collectors.toList());

                if (CollectionUtils.isNotEmpty(tempItems)) {
                    // 若任一{強制險投保起日}大於系統日，將{是否顯示強制險提示訊息}設為N
                    showCompulsoryInsNotice = !tempItems.stream().anyMatch(poly -> DateUtils.getSimpleDate(poly.getPolicyEffectiveDate()).after(DateUtils.getToday()));
                }

                // 若是否顯示強制險提示訊息(showCompulsoryInsNotice)為true，才進行下方累計
                if (showCompulsoryInsNotice) {
                    for (PolyInfoItem polyInfoItem : ws00002Response.getPolyInfo()) {
                        if (StringUtils.equalsAny(polyInfoItem.getProdType(), "02", "03")) {
                            if (StringUtils.equals(polyInfoItem.getStatus(), "1")) {
                                polyInfoItem.setInsuranceExpire(false);
                                if (DateUtils.getSimpleDate(polyInfoItem.getPolicyEffectiveDate()).after(DateUtils.getToday())) {
                                    polyInfoItem.setInsuranceExpire(false);
                                }
                                else if (DateUtils.getDaysBetween(DateUtils.getSimpleDate(polyInfoItem.getPolicyEffectiveDate()), DateUtils.getToday()) <= 60 && DateUtils.getDaysBetween(DateUtils.getSimpleDate(polyInfoItem.getPolicyEffectiveDate()), DateUtils.getToday()) >= 0) {
                                    polyInfoItem.setInsuranceExpire(true);
                                    countExpire++;
                                }
                                polyInfoItems.add(polyInfoItem);
                            }
                        }
                    }
                }
            }

            ws00002Response.setPolyInfo(polyInfoItems);
            output.setWs00002Response(ws00002Response);
            output.setCountExpire(countExpire);
            output.setShowCompulsoryInsNotice(showCompulsoryInsNotice);
        }
        catch (ServiceException e) {
            logger.error(e.getMessage());
            output.setWs00002Response(null);
            output.setCountExpire(countExpire);
            output.setShowCompulsoryInsNotice(showCompulsoryInsNotice);
        }
    }

    /**
     * 計算累計保費
     *
     * @param output
     */
    private void countInsuranceAmmount(CardDataInsurance output) {
        // 累計保費
        BigDecimal ammount = BigDecimal.ZERO;
        // 預估保費
        BigDecimal premAmmount = BigDecimal.ZERO;
        // 傳統型保險
        BigDecimal classicalAmmount = BigDecimal.ZERO;
        // 投資型保險
        BigDecimal investAmmount = BigDecimal.ZERO;
        // 健康傷害險
        BigDecimal healthAmmount = BigDecimal.ZERO;
        // 預設台幣
        BigDecimal rate = BigDecimal.ONE;

        int healthCount = 0;
        int investCount = 0;
        int classicalCount = 0;

        List<ExchangeRateHistory> allExchangeRate = exchangeRateHistoryCacheManager.getPreviousBizdayExchangeRates();

        // 加總使用者所持有的所有保險商品的{富壽累計保費}及{非富壽累計保費}，並依照{幣別}計算折台金額。
        if (CollectionUtils.isNotEmpty(output.getAiFubonInsurDataResponses())) {
            for (AiFubonInsurDataResponse response : output.getAiFubonInsurDataResponses()) {
                rate = BigDecimal.ONE;
                for (ExchangeRateHistory exchangeRateHistory : allExchangeRate) {
                    if (StringUtils.equals(exchangeRateHistory.getCurrencyEname1(), response.getInsuranceCurrency()) && Objects.isNull(exchangeRateHistory.getCurrencyEname2()) && StringUtils.equals(exchangeRateHistory.getRateType(), "0") && StringUtils.equals(exchangeRateHistory.getRateFlag(), "0")) {
                        rate = rate.multiply(exchangeRateHistory.getBuy());
                    }
                }

                if (Objects.nonNull(response.getInsurancePaid())) {
                    ammount = ammount.add(response.getInsurancePaid().multiply(rate).setScale(0, RoundingMode.HALF_UP));
                }
                premAmmount = premAmmount.add(response.getAccuPrem().multiply(rate));

                switch (response.getInsuranceType()) {
                // S、I：健康傷害險
                case "S", "I", "健康傷害險" -> {
                    healthAmmount = healthAmmount.add(response.getInsurancePaid().multiply(rate).setScale(0, RoundingMode.HALF_UP));
                    healthCount++;
                }
                // U：投資型保單-投資型保險(連結基金) F：投資型保單-連動債
                case "U", "F", "投資型保險" -> {
                    investAmmount = investAmmount.add(response.getInsurancePaid().multiply(rate).setScale(0, RoundingMode.HALF_UP));
                    investCount++;
                }
                // 空白：傳統
                case "", "傳統型保險" -> {
                    classicalAmmount = classicalAmmount.add(response.getInsurancePaid().multiply(rate).setScale(0, RoundingMode.HALF_UP));
                    classicalCount++;
                }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(output.getAiOtherInsurDataResponses())) {

            for (AiOtherInsurDataResponse response : output.getAiOtherInsurDataResponses()) {
                rate = BigDecimal.ONE;
                for (ExchangeRateHistory exchangeRateHistory : allExchangeRate) {
                    if (StringUtils.equals(exchangeRateHistory.getCurrencyEname1(), response.getInsuranceCurrency()) && Objects.isNull(exchangeRateHistory.getCurrencyEname2()) && StringUtils.equals(exchangeRateHistory.getRateType(), "0") && StringUtils.equals(exchangeRateHistory.getRateFlag(), "0")) {
                        rate = rate.multiply(exchangeRateHistory.getBuy());
                    }
                }

                if (Objects.nonNull(response.getInsurancePaid())) {
                    ammount = ammount.add(response.getInsurancePaid().multiply(rate).setScale(0, RoundingMode.HALF_UP));
                }

                switch (response.getInsuranceType()) {
                case "健康傷害險" -> {
                    healthAmmount = healthAmmount.add(response.getInsurancePaid().multiply(rate).setScale(0, RoundingMode.HALF_UP));
                    healthCount++;
                }
                case "投資型保險" -> {
                    investAmmount = investAmmount.add(response.getInsurancePaid().multiply(rate).setScale(0, RoundingMode.HALF_UP));
                    investCount++;
                }
                case "傳統型保險" -> {
                    classicalAmmount = classicalAmmount.add(response.getInsurancePaid().multiply(rate).setScale(0, RoundingMode.HALF_UP));
                    classicalCount++;
                }
                }
            }
        }
        // 若為0，視為未持有保險商品
        output.setHasAmt(!Objects.equals(ammount, BigDecimal.ZERO));
        output.setAmmount(ammount);
        output.setPremAmmount(premAmmount);
        output.setClassicalAmmount(classicalAmmount);
        output.setInvestAmmount(investAmmount);
        output.setHealthAmmount(healthAmmount);
        output.setHealthCount(healthCount);
        output.setInvestCount(investCount);
        output.setClassicalCount(classicalCount);

    }

}
