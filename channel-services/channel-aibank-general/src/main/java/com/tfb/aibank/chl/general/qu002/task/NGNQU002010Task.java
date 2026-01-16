package com.tfb.aibank.chl.general.qu002.task;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002010Rq;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002010Rs;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002Cache;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002Output;
import com.tfb.aibank.chl.general.qu002.service.NGNQU002Service;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.LineBindStatusResponse;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.model.ServiceBase;

// @formatter:off
/**
 * @(#)NGNQU002010Task.java
 * 
 * <p>Description:服務據點 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNQU002010Task extends AbstractAIBankBaseTask<NGNQU002010Rq, NGNQU002010Rs> {
    @Autowired
    private UserResource userResource;
    @Autowired
    private NGNQU002Service service;

    private NGNQU002Output dataOutput = new NGNQU002Output();

    @Override
    public void validate(NGNQU002010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU002010Rq rqData, NGNQU002010Rs rsData) throws ActionException {
        NGNQU002Cache cache = new NGNQU002Cache();
        boolean showAtmTab = StringUtils.equals("ATM", rqData.getGOTO());
        BigDecimal latitude = rqData.getLatitude();
        BigDecimal longitude = rqData.getLongitude();

        // 取得BRANCHMAP
        service.getAllBranchmap(dataOutput);

        rsData.setLineLink(service.getValue(AIBankConstants.CHANNEL_NAME, "LINE_ONLINE_LINK"));

        // Line綁定狀態檢查
        if (isLoggedIn()) {
            LineBindStatusResponse response = userResource.checkLineBindStatus(getLoginUser().getCustId());
            if (StringUtils.equals(Constants.STATUS_CODE_SUCCESS, response.getErrorCode())) {
                rsData.setBind(StringUtils.isY(response.getBindStatus()));
            }
            else {
                // 查詢失敗，導至「共通錯誤頁」
                throw new ActionException(IBSystemId.LINEBC.getSystemId(), response.getErrorCode(), SeverityType.ERROR, response.getErrorMessage());
            }
        }

        if (latitude == null || longitude == null) {
            String point = service.getValue(AIBankConstants.CHANNEL_NAME, "DEFAULT_BRANCH");
            if (StringUtils.isNotBlank(point)) {
                String[] pointArray = StringUtils.split(point, ",");
                if (pointArray.length >= 2) {
                    latitude = ConvertUtils.str2BigDecimal(pointArray[0]);
                    longitude = ConvertUtils.str2BigDecimal(pointArray[1]);
                }
            }
        }

        cache.setLatitude(latitude);
        cache.setLongitude(longitude);

        BigDecimal distance = new BigDecimal(2.5);

        // 分行 並取得最近分行
        service.getNearestBaseInformations(latitude, longitude, dataOutput.getBranchmapList(), 1, getLocale(), dataOutput);
        List<ServiceBase> branchs = dataOutput.getServiceBases();
        branchs = IBUtils.sort(branchs, "distance", false);
        if (CollectionUtils.isNotEmpty(branchs)) {
            List<ServiceBase> nearbyBranchs = branchs.stream().filter(branch -> branch.getRadiusDistance().compareTo(distance) <= 0).collect(Collectors.toList());
            rsData.setBranchs(nearbyBranchs);
        }
        // atm 並取得最近atm
        service.getNearestBaseInformations(latitude, longitude, dataOutput.getBranchmapList(), 2, getLocale(), dataOutput);
        List<ServiceBase> atms = dataOutput.getServiceBases();
        atms = IBUtils.sort(atms, "distance", false);
        if (CollectionUtils.isNotEmpty(atms)) {
            List<ServiceBase> nearbyAtms = atms.stream().filter(atm -> atm.getRadiusDistance().compareTo(distance) <= 0).collect(Collectors.toList());
            rsData.setAtms(nearbyAtms);
        }

        rsData.setShowAtmTab(showAtmTab);

        rsData.setOverseasBranchesLink(service.getValue(AIBankConstants.CHANNEL_NAME, "OVERSEAS_BRANCHERS_LINK"));

        setCache(NGNQU002Service.NCCQU001_CACHE_KEY, cache);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
