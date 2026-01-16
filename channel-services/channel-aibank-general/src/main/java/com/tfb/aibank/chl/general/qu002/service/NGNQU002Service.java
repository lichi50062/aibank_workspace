package com.tfb.aibank.chl.general.qu002.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.i18n.I18nModel;
import com.ibm.tw.ibmb.model.LabelValueBean;
import com.tfb.aibank.chl.component.branch.Branchmap;
import com.tfb.aibank.chl.component.branch.BranchmapCacheManager;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002Output;
import com.tfb.aibank.chl.general.service.NGNService;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.ServiceLocationType;

// @formatter:off
/**
 * @(#)NGNQU002Service.java
 * 
 * <p>Description:CHL NGNQU002 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NGNQU002Service extends NGNService {

    public static final String NCCQU001_CACHE_KEY = "NGNQU002_CACHE_KEY";

    @Autowired
    private BranchmapCacheManager branchmapCacheManager;

    private final static String[] areaOptions = { "台北市", "新北市", "桃竹苗地區", "中彰投地區", "雲嘉南地區", "高屏地區", "基宜花東地區" };

    /**
     * 取得服務據點資料
     * 
     * @param dataOutput
     * @throws ActionException
     */
    public void getAllBranchmap(NGNQU002Output dataOutput) throws ActionException {
        try {
            dataOutput.setBranchmapList(branchmapCacheManager.getAllBranchmap());
        }
        catch (ServiceException e) {
            logger.error("服務據點資料取得失敗", e);
            throw new ActionException(AIBankErrorCode.LOAD_BRANCHMAP_FAIL);
        }
    }

    /**
     * 組成縣市選單
     * 
     * @return
     */
    public void buildAreaOptions(Locale userLocale, NGNQU002Output dataOutput) {
        List<String> areaNames = Arrays.asList(areaOptions);
        List<LabelValueBean> areaComboItems = new ArrayList<>();
        for (String areaName : areaNames) {
            String label = areaName;
            if (Locale.US.equals(userLocale)) {
                I18nModel i18nModel = i18nCacheManager.getSingleResult(userLocale, "NGNQU002_AREA", areaName);
                if (i18nModel != null && StringUtils.isNotBlank(i18nModel.getValue())) {
                    label = i18nModel.getValue();
                }
            }
            areaComboItems.add(new LabelValueBean(label, areaName));
        }
        dataOutput.setAreaComboItems(areaComboItems);
    }

    /**
     * 產生所在地區細項map
     * 
     * @param branchmapList
     * @param serviceType
     * @param areaComboItems
     * @return
     */
    public void buildAreaDetialMapOptions(List<Branchmap> branchmapList, int serviceType, List<LabelValueBean> areaComboItems, Locale userLocale, NGNQU002Output dataOutput) {
        Map<String, List<LabelValueBean>> areaDetailMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(areaComboItems)) {
            for (LabelValueBean comboItem : areaComboItems) {
                areaDetailMap.put(comboItem.getValue(), buildAreaDetialOptions(comboItem.getValue(), branchmapList, serviceType, userLocale));
            }
        }
        dataOutput.setAreaDetailComboItemsMap(areaDetailMap);
    }

    /**
     * 產生所在地區細項選項
     * 
     * @param value
     * @param branchmap
     * @param serviceType
     * @return
     */
    private List<LabelValueBean> buildAreaDetialOptions(String area, List<Branchmap> branchmapList, int serviceType, Locale userLocale) {
        // 區域 下拉選單
        List<LabelValueBean> areaDetialComboItems = new ArrayList<>();

        List<Branchmap> areaDetails = branchmapList.stream().filter(map -> serviceType == map.getServiceType() && StringUtils.equals(map.getArea(), area)).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(areaDetails)) {
            return areaDetialComboItems;
        }

        areaDetails.sort(Comparator.comparing(Branchmap::getAreaDetail, Comparator.nullsLast(Comparator.naturalOrder())));
        Map<String, String> temp = new HashMap<>();

        String detailName = StringUtils.EMPTY;
        for (Branchmap areaDetail : areaDetails) {
            detailName = areaDetail.getAreaDetail();
            if (temp.get(detailName) == null && StringUtils.isNotBlank(detailName)) {
                temp.put(detailName, detailName);
            }
            else {
                continue;
            }
            areaDetialComboItems.add(new LabelValueBean(detailName, detailName));
        }

        if (CollectionUtils.isNotEmpty(areaDetialComboItems) && Locale.US.equals(userLocale)) {
            for (LabelValueBean item : areaDetialComboItems) {
                String value = item.getValue();
                I18nModel i18nModel = i18nCacheManager.getSingleResult(userLocale, "NGNQU002_AREA_DETAIL", value);
                if (i18nModel != null && StringUtils.isNotBlank(i18nModel.getValue())) {
                    item.setLabel(i18nModel.getValue());
                }
            }
        }
        return areaDetialComboItems;
    }

    /**
     * 取得據點座標資訊
     * 
     * @param latitude
     * @param longitude
     * @param branchmap
     * @param serviceType
     * @param locale
     * @param dataOutput
     * @return
     */
    public void getNearestBaseInformations(BigDecimal latitude, BigDecimal longitude, List<Branchmap> branchmap, int serviceType, Locale locale, NGNQU002Output dataOutput) {
        dataOutput.setServiceBases(super.getNearestBaseInformations(latitude, longitude, branchmap, serviceType, locale));
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
     * @param dataOutput
     * @return
     */
    public void getNearestBaseInformations(BigDecimal latitude, BigDecimal longitude, List<Branchmap> branchmap, int serviceType, Locale locale, BigDecimal currentLat, BigDecimal currentLon, NGNQU002Output dataOutput) {
        dataOutput.setServiceBases(super.getNearestBaseInformations(latitude, longitude, branchmap, serviceType, locale, currentLat, currentLon));
    }

    /**
     * 查詢結果
     * 
     * @param branchmap
     * @param isQuerryAtm
     * @param keyword
     * @param area
     * @param areaDetail
     * @param tagTypes
     * @return
     */
    public void queryServiceBase(List<Branchmap> branchmaps, boolean isQuerryAtm, String keyword, String area, String areaDetail, List<Boolean> tagTypes, NGNQU002Output dataOutput, Locale userLocale) {
        if (CollectionUtils.isEmpty(branchmaps)) {
            dataOutput.setBranchmapList(branchmaps);
            return;
        }

        List<Branchmap> results = new ArrayList<>();
        for (Branchmap branchmap : branchmaps) {
            if (isQuerryAtm) {
                if (branchmap.getServiceType() == 1) {
                    continue;
                }
            }
            else {
                if (branchmap.getServiceType() == 2) {
                    continue;
                }
            }

            if (StringUtils.isNotBlank(keyword)) {
                if (Locale.US.equals(userLocale)) {
                    if (StringUtils.indexOf(branchmap.getEnName(), keyword) == -1 && StringUtils.indexOf(branchmap.getEnAddress(), keyword) == -1) {
                        continue;
                    }
                }
                else {
                    if (StringUtils.indexOf(branchmap.getName(), keyword) == -1 && StringUtils.indexOf(branchmap.getAddress(), keyword) == -1) {
                        continue;
                    }
                }
            }

            if (StringUtils.isNotBlank(area)) {
                if (!StringUtils.equals(area, branchmap.getArea())) {
                    continue;
                }
            }

            if (StringUtils.isNotBlank(areaDetail)) {
                if (!StringUtils.equals(areaDetail, branchmap.getAreaDetail())) {
                    continue;
                }
            }

            if (CollectionUtils.isNotEmpty(tagTypes)) {
                boolean allFalse = tagTypes.stream().allMatch(element -> element.equals(false));
                if (!allFalse) {
                    if (StringUtils.isBlank(branchmap.getTagType())) {
                        continue;
                    }
                    else {
                        List<String> queryType = genQurryTypeList(tagTypes);
                        List<String> types = Arrays.asList(branchmap.getTagType().split(","));
                        List<String> commonElements = queryType.stream().filter(types::contains).collect(Collectors.toList());
                        if (commonElements.size() == 0) {
                            continue;
                        }
                    }
                }
            }
            results.add(branchmap);
        }
        dataOutput.setBranchmapList(results);
    }

    private List<String> genQurryTypeList(List<Boolean> tagTypes) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < tagTypes.size(); i++) {
            if (tagTypes.get(i)) {
                result.add(Arrays.asList(ServiceLocationType.values()).get(i).getNameS());
            }
        }
        return result;
    }

}
