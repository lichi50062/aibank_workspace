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
package com.tfb.aibank.chl.component.countryname;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.IpUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.tfb.aibank.chl.component.countryname.model.CountryNameModel;
import com.tfb.aibank.chl.component.countryname.model.IpLocationAndCountryName;
import com.tfb.aibank.chl.component.countryname.model.IpLocationModel;
import com.tfb.aibank.chl.component.countryname.model.SystemControlModel;

// @formatter:off
/**
 * @(#)IpLocationAndCountryNameCacheManager.java
 * 
 * <p>Description:IP 國別名稱 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/12, Benson Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class IpLocationAndCountryNameCacheManager extends AbstractCacheManager {

    @Autowired
    private IpLocationAndCountryNameResource resource;

    // SystemControl
    private SystemControlModel systemControl = new SystemControlModel();

    /** A, B -> IpLocation TW Only */
    private List<IpLocationModel> ipLocationsTwOnly = new ArrayList<>();

    /* Locale (zh_TW, en_US)-> CountryName **/

    // private Map<String,List<CountryNameModel>> countryNameGroupByLocale = new HashMap<>();;
    private Map<String, Map<String, List<CountryNameModel>>> countryNameGroupByLocale = new HashMap<>();;

    /**
     * 
     * @param ipAddress
     * @param locale
     * @return
     */
    public IpLocationAndCountryName getCountryNameByIpLocationAndLocale(String ipAddress, Locale locale) {
        IpLocationAndCountryName model = new IpLocationAndCountryName();

        model.setQueryIp(ipAddress);

        if (locale == null) {
            locale = Locale.TAIWAN;
        }

        try {

            String useFlag = systemControl.getUseFlag();

            IpLocationModel ipLocation = null;

            if (IpUtils.isValidIpv4Address(ipAddress)) {
                // 轉換IP地址為數字
                Long ipNumber = IpUtils.ipV4ToLong(ipAddress);

                ipLocation = this.ipLocationsTwOnly.stream().filter(location -> ipNumber >= location.getIpFrom() && ipNumber <= location.getIpTo()).findFirst().orElse(null);
            }
            else {
                logger.warn("invalid Ip: {}", ipAddress);
            }

            // 查不到在查1次
            if (ipLocation == null || !ipLocation.isFound()) {
                logger.warn("ip not found: {}", ipAddress);

                ipLocation = resource.getIpLocation(useFlag, ipAddress);

                if (ipLocation == null || !ipLocation.isFound()) {

                    logger.warn("ip not found again: {}", ipAddress);

                    return model;
                }
            }

            String countryCode = ipLocation.getCountryCode();

            List<CountryNameModel> countryNameModelList = this.countryNameGroupByLocale.get(locale.toString()).get(countryCode);

            if (!CollectionUtils.isEmpty(countryNameModelList)) {
                model.setCountryCode(countryNameModelList.get(0).getCountryCode());
                model.setCountryName(countryNameModelList.get(0).getCountryName());
                model.setIpFrom(ipLocation.getIpFrom());
                model.setIpTo(ipLocation.getIpTo());
            }
        }
        catch (Throwable e) {
            logger.error("getCountryNameByIpLocationAndLocale error: {}", ipAddress);
        }

        return model;
    }

    /**
     * 
     * @param ipAddress
     * @return
     * @throws Throwable
     */
    // private Optional<IpLocationModel> getIpLocationModelByIpAddress(String ipAddress) throws Throwable {
    //
    // Long ipNumber = IpUtils.ipToLong(ipAddress);
    //
    // logger.debug("IP Number: ipAddress: {}, {}",ipAddress, ipNumber == null ? null: ipNumber);
    //
    // return ipLocations.stream().filter(model -> isIpInRange(ipNumber, model)).findFirst();
    // }

    /**
     * 傳入 IP 與Model 比較內容是否符合
     * 
     * @param ipNumber
     * @param model
     * @return
     */
    // private boolean isIpInRange(Long ipNumber, IpLocationModel model) {
    // if (model == null || model.getIpFrom() == null || model.getIpTo() == null) {
    // return false;
    // }
    // return ipNumber >= model.getIpFrom() && ipNumber <= model.getIpTo();
    // }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.IP_LOCATION_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {

        SystemControlModel systemControl = resource.getIpLocationSystemControlTable();

        if (systemControl == null) {
            logger.warn("cannot find getIpLocationSystemControlTable");
            return;
        }

        String useFlag = systemControl.getUseFlag();

        // 重新取 IpLocationA and IpLocationB (只查TW)
        List<IpLocationModel> twIpLocationResult = resource.getIpLocationByUseFlagAndCountryCode(useFlag, "TW");

        if (logger.isDebugEnabled()) {
            logger.debug("IpLocationAndCountryNameCacheManager ->  twIpLocationResult: {}", twIpLocationResult.size());
        }

        // logger.debug("IpLocationAndCountryNameCacheManager -> loadCache useFlag: {}, results: {}", useFlag,allIpLocationResult == null ? 0 : allIpLocationResult.size());

        // zh_TW, en_US
        List<CountryNameModel> allCountryName = resource.getAllCountryName();

        if (logger.isDebugEnabled()) {
            logger.debug("IpLocationAndCountryNameCacheManager -> countryNameGroupByLocale: {}", countryNameGroupByLocale == null ? "" : countryNameGroupByLocale.entrySet());
        }

        Map<String, Map<String, List<CountryNameModel>>> newCountryNameGroupByLocale = allCountryName.stream().collect(Collectors.groupingBy(CountryNameModel::getLocale, Collectors.groupingBy(CountryNameModel::getCountryCode)));

        // TODO Atomic Update
        this.countryNameGroupByLocale = newCountryNameGroupByLocale;
        // this.ipLocations = allIpLocationResult;
        this.systemControl = systemControl;
        this.ipLocationsTwOnly = twIpLocationResult == null ? new ArrayList<>() : twIpLocationResult;

    }

    @Override
    protected boolean isFirstLoad() {
        return systemControl.getUpdTime() == null || countryNameGroupByLocale.isEmpty();
    }

}
