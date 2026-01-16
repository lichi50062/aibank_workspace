/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.qu001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001022Rq;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001022Rs;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001Cache;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.AiDataSyncStatusModel;
import com.tfb.aibank.chl.general.resource.dto.ApiRequestUser;
import com.tfb.aibank.chl.general.resource.dto.DataSyncStatusApiRequest;
import com.tfb.aibank.chl.general.resource.dto.QuickSearchResponse;
import com.tfb.aibank.chl.general.resource.dto.UpdateDataSyncResourceRequest;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.vo.UserVo;
import com.tfb.aibank.common.type.UpdateDataSyncStatusType;

//@formatter:off
/**
* @(#)NGNQU001022Task.java
* 
* <p>Description: 首頁證券牌卡 更新資料彙整牌面確認功能</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/06, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NGNQU001022Task extends AbstractAIBankBaseTask<NGNQU001022Rq, NGNQU001022Rs> {

    private static final IBLog logger = IBLog.getLog(NGNQU001022Task.class);

    @Autowired
    private UserResource userResource;

    @Override
    public void validate(NGNQU001022Rq rqData) throws ActionException {
        NGNQU001Cache cache = getCache(NGNQU001022Task.class.getCanonicalName(), NGNQU001Cache.class);
        boolean validateHasUser = (isLoggedIn() && getLoginUser() != null) || (cache.getQsearchData() != null && cache.getQsearchData().haveQuickSearchOn());
        if (!validateHasUser)
            throw new ActionException("no user to query data", CommonErrorCode.TASK_FIELD_ERROR);
    }

    @Override
    public void handle(NGNQU001022Rq rqData, NGNQU001022Rs rsData) throws ActionException {
        NGNQU001Cache cache = getCache(NGNQU001022Task.class.getCanonicalName(), NGNQU001Cache.class);

        UpdateDataSyncResourceRequest request = new UpdateDataSyncResourceRequest();
        ApiRequestUser customUser = new ApiRequestUser();
        if (cache == null || (cache != null && cache.getQsearchData() == null)) {
            AIBankUser user = getLoginUser();
            customUser.setCustId(user.getCustId());
            customUser.setDup(user.getUidDup());
            customUser.setUserId(user.getUserId());
            customUser.setCompanyKind(user.getCompanyKind());
            customUser.setBirthDay(user.getBirthDay());
            request.setUser(customUser);
        }
        else {
            QuickSearchResponse qSearch = cache.getQsearchData();
            customUser.setCustId(qSearch.getCustId());
            customUser.setDup(qSearch.getUidDup());
            customUser.setUserId(qSearch.getUserId());
            customUser.setCompanyKind(qSearch.getCompanyKind());
            customUser.setBirthDay(qSearch.getBirthday());
            request.setUser(customUser);
        }
        request.setTxn("NGNQU001");
        request.setUpdateDataSyncStatusType(UpdateDataSyncStatusType.findByCode(1));

        DataSyncStatusApiRequest apiRequest = new DataSyncStatusApiRequest();
        apiRequest.setSecur("N");
        request.setChangeStatusReq(apiRequest);

        logger.info("update user data sync status, request data: {}", request);
        AiDataSyncStatusModel userSyncStatusResponse = userResource.updateUserDataSyncStatus(request);
        logger.info("update user data sync status, userSyncStatusResponse: {}", userSyncStatusResponse);
        rsData.setSecurStatus(userSyncStatusResponse.getSecurStatus());
        rsData.setRequestItemSent(apiRequest.getSecur());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
