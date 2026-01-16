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
package com.tfb.aibank.chl.system.ot007.task;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.menu.Menu;
import com.ibm.tw.ibmb.component.menu.MenuCacheManager;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.datacenter.DataCenterResource;
import com.tfb.aibank.chl.component.datacenter.model.OfferAction;
import com.tfb.aibank.chl.component.datacenter.model.OfferActionRequest;
import com.tfb.aibank.chl.component.datacenter.model.OfferActionResponse;
import com.tfb.aibank.chl.component.datacenter.model.UserTagResponse;
import com.tfb.aibank.chl.component.task.TaskPage;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.system.ot007.model.NSTOT007010Rq;
import com.tfb.aibank.chl.system.ot007.model.NSTOT007010Rs;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NSTOT007010Task.java
* 
* <p>Description:個人化體驗</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/08, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NSTOT007010Task extends AbstractAIBankBaseTask<NSTOT007010Rq, NSTOT007010Rs> {
    @Autowired
    protected SystemParamCacheManager systemParamCacheManager;
    @Autowired
    protected DataCenterResource dataCenterResource;
    @Autowired
    protected MenuCacheManager menuCacheManager;

    @Override
    public void validate(NSTOT007010Rq rqData) throws ActionException {
        String actionPointId = rqData.getActionPointId();
        if (StringUtils.isBlank(actionPointId)) {
            logger.warn("版位ID沒傳入");
            throw new ActionException(AIBankErrorCode.DATA_CENTER_API_ERROR);
        }
    }

    @Override
    public void handle(NSTOT007010Rq rqData, NSTOT007010Rs rsData) throws ActionException {
        String actionPointId = rqData.getActionPointId();
        if (isLoggedIn()) {
            /** 數據中台 userTag 取得使用者標籤設定 */
            String dataCenterFlag = StringUtils.EMPTY;
            AIBankUser aibankUser = getLoginUser();
            // 是否可發數據中台
            dataCenterFlag = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DATA_PLATFORM_FLAG");
            if (StringUtils.equals(StringUtils.Y, dataCenterFlag)) {
                try {
                    // 檢查有沒有發過 UserTag
                    if (!StringUtils.equals(StringUtils.Y, aibankUser.getUserTagQuery())) {
                        UserTagResponse dataCenterResponse = dataCenterResource.getUserTag(aibankUser.getCustId(), aibankUser.getUidDup());
                        aibankUser.setPdrsonalityTag(dataCenterResponse.getPdrsonalityTag());
                        aibankUser.setRiskTag(dataCenterResponse.getRiskTag());
                        aibankUser.setUserTagQuery(StringUtils.Y);
                    }
                }
                catch (ServiceException e) {
                    logger.warn(" 數據中台取得使用者標籤失敗 ", e);
                }
                try {
                    OfferActionRequest request = new OfferActionRequest();
                    request.setCustId(aibankUser.getCustId());
                    request.setDup(aibankUser.getUidDup());
                    request.setSessionId(getSessionId());
                    request.setDeviceIxd(getDeviceIxd());
                    request.setPdrsonalityTag(aibankUser.getPdrsonalityTag());
                    request.setRiskTag(aibankUser.getRiskTag());

                    request.setActionPointId(actionPointId);
                    request.setPrePageId(rqData.getPrePageId());
                    request.setCurPageId(rqData.getCurPageId());

                    request.setLoginTime(aibankUser.getUserLoginVo().getLoginTime());
                    TaskPage taskPage = getTaskPage(rqData.getCurPageId());
                    if (null == taskPage) {
                        request.setPageName(rqData.getCurPageId());
                    }
                    else {
                        // #4947 前台 個人化版位 調整 - API邏輯 + Celebrus API
                        // 改成拿taskName而非pageName
                        request.setPageName(getTaskName(taskPage.getTaskId()));
                    }

                    OfferActionResponse offerRankingResponse = dataCenterResource.getOfferAction(request);
                    List<OfferAction> offerActions = offerRankingResponse.getOfferActions();
                    rsData.setOfferActions(offerActions);
                }
                catch (ServiceException e) {
                    logger.warn(" 數據中台取得使用者版位失敗 ", e);
                }
            }
            rsData.setTraceId(MDC.get(MDCKey.traceId.name()));
        }
    }

    private String getTaskName(String taskId) {
        List<Menu> menus = menuCacheManager.getMenusByTaskId(taskId);
        List<String> names = menus.stream()
                .map(Menu::getMenuCName)
                .distinct()
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(names)) {
            return StringUtils.EMPTY;
        }
        else {
            return getMenuName(names);
        }
    }

    private String getMenuName(List<String> menuNameList) {
        return StringUtils.join(menuNameList, "/");
    }
}
