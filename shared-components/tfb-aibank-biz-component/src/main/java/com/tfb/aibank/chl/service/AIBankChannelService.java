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
package com.tfb.aibank.chl.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.errorcode.ErrorCodeCacheManager;
import com.ibm.tw.ibmb.component.i18n.I18NCacheManager;
import com.ibm.tw.ibmb.component.i18n.I18nModel;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.branch.Branchmap;
import com.tfb.aibank.chl.component.businessday.BusinessDayCacheManager;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.type.ResultCodeType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.model.ServiceBase;
import com.tfb.aibank.common.type.TxStatusType;
import com.tfb.aibank.component.dic.DicCacheManager;

// @formatter:off
/**
 * @(#)AIBankChannelService.java
 * 
 * <p>Description:CHL層，所有 Service 類別的父類別</p>
 * <p>作為所有 CHL Service 的父類別，盡量不要在此 Autowird XXXResource.java</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AIBankChannelService {

    protected static IBLog logger = IBLog.getLog(AIBankChannelService.class);

    @Autowired
    protected UserDataCacheService userDataCacheService;
    @Autowired
    protected SystemParamCacheManager systemParamCacheManager;
    @Autowired
    protected BusinessDayCacheManager businessDayCacheManager;
    @Autowired
    protected ErrorCodeCacheManager errorCodeCacheManager;
    @Autowired
    protected CurrencyCodeCacheManager currencyCodeCacheManager;
    @Autowired
    protected I18NCacheManager i18nCacheManager;
    @Autowired
    protected DicCacheManager dicCacheManager;
    @Autowired
    protected RemarkContentCacheManager remarkContentCacheManager;

    /**
     * 從 SYSTEM_PARAM 取值
     * 
     * @param paramKey
     * @return
     */
    public String getValue(String paramKey) {
        return getValue(AIBankConstants.CHANNEL_NAME, paramKey);
    }

    /**
     * 從 SYSTEM_PARAM 取值
     * 
     * @param category
     * @param paramKey
     * @return
     */
    public String getValue(String category, String paramKey) {
        return systemParamCacheManager.getValue(category, paramKey);
    }

    /**
     * 鑑於交易失敗時，例外會被catch另行處理，導致底層記錄 accessLog 缺少相關資訊。故在此記錄。
     * 
     * @param accessLogEntity
     * @param sex
     * @return
     */
    public ActionException handleException(MBAccessLog accessLogEntity, ServiceException sex) {
        ActionException aex = ExceptionUtils.getActionException(sex);
        accessLogEntity.setErrorCode(aex.getErrorCode());
        accessLogEntity.setErrorDesc(getErrorDesc(aex.getSystemId(), aex.getErrorCode(), aex.getErrorDesc()));
        accessLogEntity.setErrorSystemId(aex.getSystemId());
        accessLogEntity.setResultCode((aex.isTimeout() ? ResultCodeType.RESULT_UNKNOWN : ResultCodeType.RESULT_FAILED).getResultCode());
        return aex;
    }

    /**
     * 鑑於交易失敗時，例外會被catch另行處理，導致底層記錄 accessLog 缺少相關資訊。故在此記錄。
     * 
     * @param accessLogEntity
     * @param aex
     * @return
     */
    public ActionException handleException(MBAccessLog accessLogEntity, ActionException aex) {
        accessLogEntity.setErrorCode(aex.getErrorCode());
        accessLogEntity.setErrorDesc(getErrorDesc(aex.getSystemId(), aex.getErrorCode(), aex.getErrorDesc()));
        accessLogEntity.setErrorSystemId(aex.getSystemId());
        accessLogEntity.setResultCode((aex.isTimeout() ? ResultCodeType.RESULT_UNKNOWN : ResultCodeType.RESULT_FAILED).getResultCode());
        return aex;
    }

    /**
     * 以 ServiceException 判斷交易結果(即時交易)，若是預約交易，請參考 getTxStatusType(ServiceException, boolean)
     * 
     * @param sex
     * @return FAIL、PROCESS、SUCCESS
     */
    public TxStatusType getTxStatusType(ServiceException sex) {
        return getTxStatusType(sex, false);
    }

    /**
     * 以 ServiceException 判斷交易結果
     * 
     * @param sex
     * @param isReserve
     *            是否為預約交易
     * @return
     */
    public TxStatusType getTxStatusType(ServiceException sex, boolean isReserve) {
        if (sex == null) {
            return isReserve ? TxStatusType.RESERVED : TxStatusType.SUCCESS;
        }
        return checkEsbIsError(sex) ? TxStatusType.FAIL : (checkEsbIsUnknown(sex) ? TxStatusType.PROCESS : (isReserve ? TxStatusType.RESERVED : TxStatusType.SUCCESS));
    }

    /**
     * 判斷電文非成功的情況，是否屬於「失敗」
     * 
     * @param sex
     * @return
     */
    public boolean checkEsbIsError(ServiceException sex) {
        return StringUtils.isNotBlank(sex.getExtraInfo().get(Constants.SERVICE_CODE_REFERENCE));
    }

    /**
     * 判斷電文非成功的情況，是否屬於「未明」
     * 
     * @param sex
     * @return
     */
    public boolean checkEsbIsUnknown(ServiceException sex) {
        return StringUtils.isBlank(sex.getExtraInfo().get(Constants.SERVICE_CODE_REFERENCE));
    }

    /**
     * 以 SYSTEM_ID 和 ERROR_CODE 從 DB 讀取錯誤訊息
     * 
     * @param systemId
     * @param errorCode
     * @param errorDesc
     * @return
     */
    protected String getErrorDesc(String systemId, String errorCode, String errorDesc) {
        String errorDescDB = IBUtils.getErrorDesc(errorCodeCacheManager, systemId, errorCode, Locale.forLanguageTag(MDC.get(MDCKey.languagetag.name())), MDC.get(MDCKey.frompage.name()));
        return StringUtils.isNotBlank(errorDescDB) ? errorDescDB : errorDesc;
    }

    /**
     * 指定日期是否為「營業日」
     * 
     * @param date
     * @return
     */
    protected boolean isBusinessDay(Date date) {
        if (date == null) {
            return false;
        }
        return businessDayCacheManager.isBusinessDay(date);
    }

    /**
     * 指定日期是否為「台北營業日」
     * 
     * @param date
     * @return
     */
    protected boolean isTaipeiBusinessDay(Date date) {
        if (date == null) {
            return false;
        }
        return businessDayCacheManager.isTaipeiBusinessDay(date);
    }

    /**
     * 取得區間內的所有營業日
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Date> getBusinessDayInRange(Date startDate, Date endDate) {
        return businessDayCacheManager.getBusinessDayInRange(startDate, endDate);
    }

    /**
     * 取得傳入日期的前一營業日
     * 
     * @param inputDate
     * @return
     */
    protected Date getPreviousBusinessDay(Date inputDate) {
        return businessDayCacheManager.getPreviousBusinessDay(inputDate);
    }

    /**
     * 讀取指定日期區間裡的營業日
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    protected List<String> getBusinessDays(Date startDate, Date endDate) {
        // 傳入的日期資料，濾掉時分秒
        DateUtils.clearTime(startDate);
        DateUtils.clearTime(endDate);
        // 讀取所有營業日資訊
        List<Date> allBusinessDays = businessDayCacheManager.getAllBusinessDay();
        // 比對後，留下非營業日期
        List<String> result = new ArrayList<>();
        Date date = startDate;
        while (date.compareTo(endDate) <= 0) {
            if (allBusinessDays.contains(date)) {
                result.add(DateFormatUtils.CE_DATE_FORMAT.format(date));
            }
            date = DateUtils.addDay(date, 1); // 加一天
        }
        return result;
    }

    /**
     * 讀取指定日期區間裡的「非」營業日，供小日曆使用
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    protected List<String> getNotBusinessDays(Date startDate, Date endDate) {
        // 傳入的日期資料，濾掉時分秒
        DateUtils.clearTime(startDate);
        DateUtils.clearTime(endDate);
        // 讀取所有營業日資訊
        List<Date> allBusinessDays = businessDayCacheManager.getAllBusinessDay();
        // 比對後，留下非營業日期
        List<String> result = new ArrayList<>();
        Date date = startDate;
        while (date.compareTo(endDate) <= 0) {
            if (!allBusinessDays.contains(date)) {
                result.add(DateFormatUtils.CE_DATE_FORMAT.format(date));
            }
            date = DateUtils.addDay(date, 1); // 加一天
        }
        return result;
    }

    /**
     * 讀取 投資類幣別排序編號
     * 
     * @param currencyCode
     *            幣別代碼
     * @return
     */
    protected Integer getInvestCurrencySortNum(String currencyCode) {
        return getInvestCurrencySortMap().get(currencyCode);
    }

    /**
     * 讀取 投資類幣別排序Map
     * 
     * @return Map<幣別代碼, 順序(值大者為先)>
     */
    protected Map<String, Integer> getInvestCurrencySortMap() {
        String value = dicCacheManager.getValue("CURRENCY_SORT", "AIBANK_INVEST");
        if (StringUtils.isBlank(value)) {
            return Collections.emptyMap();
        }
        int base = 1000;
        Map<String, Integer> currencyMap = new HashMap<>();
        List<String> currencyList = Arrays.asList(value.split(","));
        for (int i = 0; i < currencyList.size(); i++) {
            String currency = currencyList.get(i);
            currencyMap.put(currency, (base - i));
        }
        return currencyMap;
    }

    /**
     * 讀取 投資類幣別排序List
     * 
     * @return
     */
    protected List<String> getInvestCurrencySortList() {
        String value = dicCacheManager.getValue("CURRENCY_SORT", "AIBANK_INVEST");
        if (StringUtils.isBlank(value)) {
            return Collections.emptyList();
        }
        return Arrays.asList(value.split(","));
    }

    /**
     * 依幣別代碼取得幣別名稱
     * 
     * @param code
     * @return
     */
    protected String getCurrencyName(String code) {
        return getCurrencyName(code, Locale.TAIWAN);
    }

    /**
     * 依幣別代碼取得幣別名稱
     * 
     * @param code
     * @param locale
     * @return
     */
    protected String getCurrencyName(String code, Locale locale) {
        if (locale == null) {
            return getCurrencyName(code);
        }
        return currencyCodeCacheManager.getCurrencyName(code, locale.toString());
    }

    /**
     * 從 DIC 取值
     * 
     * @param category
     * @param key
     * @return
     */
    protected String getDicValue(String category, String key) {
        return dicCacheManager.getValue(category, key);
    }

    /**
     * 取的加密後的因子
     * 
     * @param deviceId
     * @param plain
     * @return
     */
    protected String getCoefficient(String deviceId, String plain) {
        if (StringUtils.isBlank(deviceId) || deviceId.length() < 16) {
            return "";
        }
        String key = deviceId.substring(0, 16);
        byte[] encodeFactor = null;
        try {
            encodeFactor = AESUtils.encryptCoefficient(plain.getBytes(), new SecretKeySpec(key.getBytes(), "AES"));
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
        }
        return new String(Hex.encode(encodeFactor));
    }

    /**
     * 取得據點座標資訊
     * 
     * @param latitude
     * @param longitude
     * @param branchmap
     * @param serviceType
     * @param locale
     * @return
     */
    protected List<ServiceBase> getNearestBaseInformations(BigDecimal latitude, BigDecimal longitude, List<Branchmap> branchmap, int serviceType, Locale locale) {
        return getNearestBaseInformations(latitude, longitude, branchmap, serviceType, locale, latitude, longitude);
    }

    /**
     * 取得據點座標資訊
     * 
     * @param latitude
     * @param longitude
     * @param branchmap
     * @param serviceType
     * @param locale
     * @param currentLat
     * @param currentLon
     * @return
     */
    protected List<ServiceBase> getNearestBaseInformations(BigDecimal latitude, BigDecimal longitude, List<Branchmap> branchmap, int serviceType, Locale locale, BigDecimal currentLat, BigDecimal currentLon) {
        if (latitude == null || longitude == null || branchmap == null)
            return null;
        List<ServiceBase> serviceBases = new ArrayList<>();
        for (Branchmap map : branchmap) {
            if (map.getServiceType() != serviceType) {
                continue;
            }
            ServiceBase serviceBase = new ServiceBase();
            BigDecimal calDistance = calculateDistance(ConvertUtils.str2BigDecimal(map.getLatitude(), 5), ConvertUtils.str2BigDecimal(map.getLongitude(), 5), currentLat, currentLon, 6);
            BigDecimal radiusDistance = calculateDistance(ConvertUtils.str2BigDecimal(map.getLatitude(), 5), ConvertUtils.str2BigDecimal(map.getLongitude(), 5), latitude, longitude, 6);
            serviceBase.setName(map.getName(locale));
            serviceBase.setAddress(map.getAddress(locale));
            serviceBase.setLatitude(ConvertUtils.str2BigDecimal(map.getLatitude()));
            serviceBase.setLongitude(ConvertUtils.str2BigDecimal(map.getLongitude()));
            serviceBase.setDistance(calDistance);
            serviceBase.setRadiusDistance(radiusDistance);
            serviceBase.setBranchCode(map.getBranchCode());
            serviceBase.setArea(map.getArea());
            serviceBase.setAreaDetail(map.getAreaDetail());
            serviceBase.setServiceType(map.getServiceType());
            if (StringUtils.isNotBlank(map.getTagType())) {
                serviceBase.setTabTypes(genTagTypes(map.getTagType(), locale));
            }
            serviceBases.add(serviceBase);
        }
        if (CollectionUtils.isNotEmpty(serviceBases)) {
            serviceBases = IBUtils.sort(serviceBases, "radiusDistance", false);
        }
        return serviceBases;
    }

    /**
     * 產生tagTypes
     * 
     * @param types
     * @param userLocale
     * @return
     */
    private List<String> genTagTypes(String types, Locale userLocale) {
        String[] tagTypeArray = types.split(",");
        List<String> tagTypes = new ArrayList<>();
        for (String tagType : tagTypeArray) {
            if (StringUtils.isNotBlank(tagType)) {
                if (Locale.US.equals(userLocale)) {
                    I18nModel i18nModel = i18nCacheManager.getSingleResult(userLocale, "NGNQU002_TAG", tagType);
                    if (i18nModel != null && StringUtils.isNotBlank(i18nModel.getValue())) {
                        tagType = i18nModel.getValue();
                    }
                }
                tagTypes.add(tagType);
            }
        }
        return tagTypes;
    }

    /**
     * 計算經緯距離
     * 
     * @param latitude1
     * @param longitude1
     * @param latitude2
     * @param longitude2
     * @param precision
     * @return
     */
    private BigDecimal calculateDistance(BigDecimal latitude1, BigDecimal longitude1, BigDecimal latitude2, BigDecimal longitude2, Integer precision) {
        double lat1 = latitude1 == null ? 0 : latitude1.doubleValue();
        double lon1 = longitude1 == null ? 0 : longitude1.doubleValue();
        double lat2 = latitude2 == null ? 0 : latitude2.doubleValue();
        double lon2 = longitude2 == null ? 0 : longitude2.doubleValue();
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        // to km
        dist = dist * 1.609344;

        // 直線距離，四捨五入取至小數點後指定位數
        return ConvertUtils.double2BigDecimal(dist, precision);
    }

    /**
     * converts decimal degrees to radians
     * 
     * @param deg
     * @return
     */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * converts radians to decimal degrees
     * 
     * @param rad
     * @return
     */
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    /**
     * get i18n value from category and key, return default if not found
     *
     *
     * @param category
     * @param key
     * @param locale
     * @param defaultValue
     * @return
     */
    public String getI18nValue(String category, String key, String locale, String defaultValue) {
        return getI18nValue(category, key, LocaleUtils.toLocale(locale), defaultValue);
    }

    /**
     * get i18n value from category and key, return default if not found
     *
     * @param category
     * @param key
     * @param locale
     * @param defaultValue
     * @return
     */
    public String getI18nValue(String category, String key, Locale locale, String defaultValue) {
        Map<String, I18nModel> i18nMap = i18nCacheManager.getI18nByCategory(locale, category);
        return Optional.ofNullable(i18nMap.get(key)).map(I18nModel::getValue).filter(StringUtils::isNotBlank).orElse(defaultValue);
    }

    /**
     * 是否為「高齡投資」
     * <p>
     * 高齡年紀：AI_SYSTEM_PARAM (PARAM_KEY = TRANSFER_SERVICE_AGE_LIMIT))
     * </p>
     * 
     * @param aibankUser
     * @return
     */
    protected boolean isOldAge(AIBankUser aibankUser) {
        Date birthday = aibankUser.getBirthDay();
        if (birthday == null) {
            return false;
        }
        String age = getValue("TRANSFER_SERVICE_AGE_LIMIT");
        Date yearsLater = DateUtils.addYears(birthday, ConvertUtils.str2Int(age, 0));
        Date sysDate = DateUtils.getStartDate(new Date());
        return yearsLater.compareTo(sysDate) < 0;
    }

}
