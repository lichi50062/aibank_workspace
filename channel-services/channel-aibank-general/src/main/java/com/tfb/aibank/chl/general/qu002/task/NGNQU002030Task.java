package com.tfb.aibank.chl.general.qu002.task;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.branch.Branchmap;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002030Rq;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002030Rs;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002Cache;
import com.tfb.aibank.chl.general.qu002.model.NGNQU002Output;
import com.tfb.aibank.chl.general.qu002.service.NGNQU002Service;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.LineBindStatusResponse;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.model.ServiceBase;

// @formatter:off
/**
 * @(#)NGNQU002030Task.java
 * 
 * <p>Description:服務據點 030 搜尋結果頁</p>
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
public class NGNQU002030Task extends AbstractAIBankBaseTask<NGNQU002030Rq, NGNQU002030Rs> {

    @Autowired
    private UserResource userResource;

    @Autowired
    private NGNQU002Service service;

    private NGNQU002Output dataOutput = new NGNQU002Output();

    @Override
    public void validate(NGNQU002030Rq rqData) throws ActionException {
        if (StringUtils.isNoneBlank(rqData.getKeyword())) {
            if (rqData.getKeyword().length() > 20) {
                // 至多輸入 20 個字元
                this.addErrorFieldMap("keyword", I18NUtils.getMessage("ngn.qu002.keyword.length", getLocale()));
            }
        }
    }

    @Override
    public void handle(NGNQU002030Rq rqData, NGNQU002030Rs rsData) throws ActionException {
        NGNQU002Cache cache = getCache(NGNQU002Service.NCCQU001_CACHE_KEY, NGNQU002Cache.class);
        BigDecimal latitude = rqData.getLatitude();
        BigDecimal longitude = rqData.getLongitude();

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

        // 取得BRANCHMAP
        service.getAllBranchmap(dataOutput);
        boolean isQuerryAtm = StringUtils.equals("atm", rqData.getServiceType());
        String keyword = rqData.getKeyword();
        String area = rqData.getArea();
        String areaDetail = rqData.getAreaDetail();
        List<Boolean> tagTypes = rqData.getTagTypes();
        service.queryServiceBase(dataOutput.getBranchmapList(), isQuerryAtm, keyword, area, areaDetail, tagTypes, dataOutput, getLocale());
        List<Branchmap> results = dataOutput.getBranchmapList();
        service.getNearestBaseInformations(latitude, longitude, results, isQuerryAtm ? 2 : 1, getLocale(), cache.getLatitude(), cache.getLongitude(), dataOutput);
        List<ServiceBase> serviceBases = dataOutput.getServiceBases();
        serviceBases = IBUtils.sort(serviceBases, "distance", false);
        rsData.setAtm(isQuerryAtm);
        rsData.setServiceBases(serviceBases);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
