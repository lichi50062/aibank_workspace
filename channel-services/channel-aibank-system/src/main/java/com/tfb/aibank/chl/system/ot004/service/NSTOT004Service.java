package com.tfb.aibank.chl.system.ot004.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.model.ClientRequest;
import com.ibm.tw.ibmb.component.errorcode.ErrorCodeData;
import com.ibm.tw.ibmb.component.errorcode.ErrorInfoData;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.system.ot004.model.AddWidgetRecordOutput;
import com.tfb.aibank.chl.system.ot004.model.AddWidgetRecordInput;
import com.tfb.aibank.chl.system.ot004.model.ErrorCodeForHandShake;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004010Rq;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004Input;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004Output;
import com.tfb.aibank.chl.system.resource.PreferencesResource;
import com.tfb.aibank.chl.system.resource.dto.AibankPersonalResultpage;
import com.tfb.aibank.chl.system.resource.dto.AibankTxnResultpage;
import com.tfb.aibank.chl.system.resource.dto.Announcement;
import com.tfb.aibank.chl.system.resource.dto.UpdatePushTokenRequest;
import com.tfb.aibank.chl.system.resource.dto.WidgetRecordRequest;
import com.tfb.aibank.chl.system.service.NSTService;

// @formatter:off
/**
 * @(#)NSTOT004Service.java
 * 
 * <p>Description:CHL NSTOT004 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NSTOT004Service extends NSTService {

    @Autowired
    private PreferencesResource preferencesResource;

    /**
     * 收集 SYSTEM_ID 等於 APP、IMP 的錯誤代碼資訊
     * 
     * @return
     * @throws ActionException
     */
    public void getErrorDateListByAppAndImp(Locale locale, List<ErrorCodeForHandShake> output) throws ActionException {
        Map<String, Map<String, Map<String, ErrorCodeData>>> errorCodeAppMap = errorCodeCacheManager.getErrorCodeMap(IBSystemId.APP.getSystemId(), locale.toString());
        Map<String, Map<String, Map<String, ErrorInfoData>>> errorInfoAppMap = errorCodeCacheManager.getErrorInfoMap(IBSystemId.APP.getSystemId(), locale.toString());

        Map<String, Map<String, Map<String, ErrorCodeData>>> errorCodeIMPMap = errorCodeCacheManager.getErrorCodeMap(IBSystemId.IMP.getSystemId(), locale.toString());
        Map<String, Map<String, Map<String, ErrorInfoData>>> errorInfoIMPMap = errorCodeCacheManager.getErrorInfoMap(IBSystemId.IMP.getSystemId(), locale.toString());

        List<ErrorCodeForHandShake> errorDateListByAppAndImp = new ArrayList<>();
        addNonNullOrDefaultErrorCodeData(errorDateListByAppAndImp, errorCodeAppMap, errorInfoAppMap);
        addNonNullOrDefaultErrorCodeData(errorDateListByAppAndImp, errorCodeIMPMap, errorInfoIMPMap);
        output.addAll(errorDateListByAppAndImp);
    }

    /**
     * errorCode 處理
     * 
     * @param errorDateList
     * @param errorCodeMap
     * @param channelId
     */
    private void addNonNullOrDefaultErrorCodeData(List<ErrorCodeForHandShake> errorDateList, Map<String, Map<String, Map<String, ErrorCodeData>>> errorCodeMap, Map<String, Map<String, Map<String, ErrorInfoData>>> errorInfoMap) {
        List<ErrorCodeForHandShake> errorCodeHandShakeList = new ArrayList<>();
        for (Map.Entry<String, Map<String, Map<String, ErrorCodeData>>> entry1 : errorCodeMap.entrySet()) {
            String errorCode = entry1.getKey();
            Map<String, Map<String, ErrorCodeData>> pageMap = entry1.getValue();
            Map<String, Map<String, ErrorInfoData>> errorInfoLv2 = errorInfoMap.get(errorCode);

            for (Map.Entry<String, Map<String, ErrorCodeData>> entry2 : pageMap.entrySet()) {
                String pageId = entry2.getKey();
                Map<String, ErrorCodeData> chlIdMap = entry2.getValue();
                Map<String, ErrorInfoData> errorInfoLv3 = Objects.isNull(errorInfoLv2) ? null : errorInfoLv2.get(pageId);

                for (Map.Entry<String, ErrorCodeData> entry3 : chlIdMap.entrySet()) {
                    String chlId = entry3.getKey();
                    ErrorCodeData errorCodeData = entry3.getValue();
                    ErrorInfoData errorInfoData = errorInfoLv3 == null ? null : errorInfoLv3.get(chlId);
                    errorCodeHandShakeList.add(convertErrorCode(errorCodeData, errorInfoData));
                }
            }
        }
        errorDateList.addAll(errorCodeHandShakeList);
    }

    /**
     * 組成 ErrorCodeForHandShakeVo
     * 
     * @param errorCode
     * @param errorInfo
     * @return
     */
    private ErrorCodeForHandShake convertErrorCode(ErrorCodeData errorCode, ErrorInfoData errorInfo) {
        ErrorCodeForHandShake errorCodeForHandShakeVo = new ErrorCodeForHandShake();
        errorCodeForHandShakeVo.setErrorCode(errorCode.getErrorCode());
        errorCodeForHandShakeVo.setSystemId(errorCode.getSystemId());
        errorCodeForHandShakeVo.setTitle(errorCode.getTitle());
        errorCodeForHandShakeVo.setExternalDesc(errorCode.getExternalDesc());
        if (errorInfo != null) {
            errorCodeForHandShakeVo.setDirectButtonName1(errorInfo.getDirectButtonName1());
            errorCodeForHandShakeVo.setDirectButtonName2(errorInfo.getDirectButtonName2());
            errorCodeForHandShakeVo.setDirectTaskId1(errorInfo.getDirectTaskId1());
            errorCodeForHandShakeVo.setDirectTaskId2(errorInfo.getDirectTaskId2());
        }
        return errorCodeForHandShakeVo;
    }

    /**
     * 紀錄交易結果頁畫面
     * 
     * @param aibankUser
     * @param dataInput
     * @return
     */
    public void addTxnResultPageRecord(AIBankUser aibankUser, NSTOT004Input dataInput, NSTOT004Output dataOutput) {
        if (aibankUser != null) {
            AibankTxnResultpage model = new AibankTxnResultpage();
            model.setTraceId(dataInput.getTraceId());
            model.setCompanyKey(aibankUser.getCompanyKey());
            model.setUserKey(aibankUser.getUserKey());
            model.setResData(dataInput.getResData());
            model.setPageId(dataInput.getPageId());
            Boolean execResult = informationResource.addTxnResultPageRecord(model);
            dataOutput.setExecResult(execResult);
        }
    }

    /**
     * 讀取符合系統時間介於開始日和結束日之間的公告紀錄，並且以更新日期進行降幂排序
     * 
     * @param dataOutput
     */
    public void getAnnouncementList(NSTOT004Output dataOutput) {
        List<Announcement> announcementList = informationResource.getAnnouncementList();
        dataOutput.setAnnoList(announcementList);
    }

    /**
     * 紀錄個人化版位畫面
     * 
     * @param aibankUser
     * @param dataInput
     * @return
     */
    public void addPersonalResultPageRecord(AIBankUser aibankUser, NSTOT004Input dataInput, NSTOT004Output dataOutput) {
        if (aibankUser != null) {
            AibankPersonalResultpage model = new AibankPersonalResultpage();
            model.setTraceId(dataInput.getTraceId());
            model.setTrackingId(dataInput.getTrackingId());
            model.setActionPointId(dataInput.getActionPointId());
            model.setTemplateId(dataInput.getTemplateId());
            model.setOfferId(dataInput.getOfferId());
            model.setCompanyKey(aibankUser.getCompanyKey());
            model.setUserKey(aibankUser.getUserKey());
            model.setPageId(dataInput.getPageId());
            model.setResData(dataInput.getResData());
            model.setCreateTime(new Date());
            Boolean execResult = informationResource.addPersonalResultPageRecord(model);
            dataOutput.setExecResult(execResult);
        }
    }


    /**
     * 更新 Push Tpken
     * 
     * @param rqData
     */
    @Async
    public void updateFirstDownloadRecord(ClientRequest clientRequest, NSTOT004010Rq rqData) {
        UpdatePushTokenRequest request = new UpdatePushTokenRequest();
        request.setDeviceUuid(clientRequest.getDeviceIxd());
        request.setPlatform(clientRequest.getPlatform());
        request.setPushFlag(0);
        request.setPushToken(rqData.getPushToken());
        request.setHasLogined(rqData.getHasLogined());
        try {
            preferencesResource.updatePushToken(request);
        }
        catch (ServiceException ex) {
            logger.warn("更新 Push Tpken fail", ex);
        }
    }

    /**
     * 從 DIC 取得 個股商品分時走勢圖URL
     * 
     * @param dataOutput
     */
    public void getETFDataURL(NSTOT004Output dataOutput) {
        dataOutput.setEtfDataURL(getDicValue("INVEST", "ETFData_URL"));
    }

    /**
     * 非同步寫入 WidgetRecord
     * @param input
     * @param output
     */
    @Async
    public void addWidgetRecord(AddWidgetRecordInput input, AddWidgetRecordOutput output) {
        // parsing memo for taskId and widgetType
        Hashtable<String, String[]> queryStrMap = IBUtils.parseQueryString(StringUtils.substringAfterLast(input.getMemo(), "?"));

        // 來自 Widget
        String widget = getFirstValue(queryStrMap.get("from"));
        if (!"widget".equalsIgnoreCase(widget)) {
            if (logger.isDebugEnabled()) {
                logger.debug(" handleOpenUrl not from widget, skip add record");
            }
            output.setResult("SKIPPED");
            return;
        }
        String taskId = getFirstValue(queryStrMap.get("taskId"));
        String type = StringUtils.defaultIfBlank(getFirstValue(queryStrMap.get("type")), "A");
        String platform = StringUtils.lowerCase(input.getRequest().getPlatform());
        String deviceCode = input.getRequest().getDeviceIxd();
        AIBankUser aibankUser = input.getAibankUser();
        WidgetRecordRequest request = new WidgetRecordRequest();
        request.setDeviceCode(deviceCode);
        request.setPlatform(platform);
        request.setTaskId(taskId);
        request.setType(type);
        if (aibankUser != null) {
            request.setCompanyKey(aibankUser.getCompanyKey());
            request.setUserKey(aibankUser.getUserKey());
        }
        String result = this.informationResource.addWidgetRecord(request);
        output.setResult(result);
    }

    private String getFirstValue(String[] values) {
        return (values == null || values.length < 1) ? null : values[0];
    }


}
