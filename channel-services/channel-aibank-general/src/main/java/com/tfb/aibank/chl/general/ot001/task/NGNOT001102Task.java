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
package com.tfb.aibank.chl.general.ot001.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001102Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001102Rs;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.dto.DoRevokeRequest;
import com.tfb.aibank.chl.general.resource.dto.DoRevokeResponse;
import com.tfb.aibank.chl.general.resource.dto.QueryLogResponse;
import com.tfb.aibank.chl.general.resource.dto.QueryLogResponseRepeat;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001102Task.java 
* 
* <p>Description:FIDO 綁定註銷</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/10, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NGNOT001102Task extends AbstractAIBankBaseTask<NGNOT001102Rq, NGNOT001102Rs> {

    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NGNOT001102Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001102Rq rqData, NGNOT001102Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        QueryLogResponse queryLogResponse = securityResource.queryLog(loginUser.getCustIdWithDup());

        // QueryLog失敗，不繼續執行
        if (!StringUtils.equals("0", queryLogResponse.getStatus())) {
            logger.info("FIDO綁定註銷異常...QueryLog失敗");

            if (!"NGNOT001".equals(getTaskId().toUpperCase())) {
                throw ExceptionUtils.getActionException(AIBankErrorCode.FIDO_SDK_ERROR);
            }

            rsData.setSuccess(false);
            rsData.setErrorCode(queryLogResponse.getError());
            rsData.setErrorDesc(queryLogResponse.getError_description());
            return;
        }

        // QueryLog沒資料，不繼續執行
        if (CollectionUtils.isEmpty(queryLogResponse.getData())) {
            logger.info("FIDO綁定註銷異常...QueryLog沒資料");
            rsData.setSuccess(true);
            rsData.setNoQueryLogData(true);
            return;
        }

        // 逐筆KeyId執行DoRevoke
        int devokeCount = 0;
        List<String> keyIds = new ArrayList<>();
        for (QueryLogResponseRepeat queryLog : queryLogResponse.getData()) {

            if (StringUtils.isBlank(queryLog.getFidoKeyId())) {
                continue;
            }
            String keyId = StringUtils.defaultString(queryLog.getFidoKeyId());
            DoRevokeRequest doRevokeRequest = new DoRevokeRequest();
            doRevokeRequest.setCustId(loginUser.getCustId());
            doRevokeRequest.setUidDup(loginUser.getUidDup());
            doRevokeRequest.setUserId(loginUser.getUserId());
            doRevokeRequest.setCompanyKind(loginUser.getCompanyKind());
            doRevokeRequest.setKeyId(queryLog.getFidoKeyId());
            doRevokeRequest.setDeviceId(getRequest().getDeviceIxd());
            DoRevokeResponse doRevokeResponse = securityResource.doRevoke(doRevokeRequest);

            // DoRevoke失敗
            if (StringUtils.equals(doRevokeResponse.getStatus(), "0")) {
                devokeCount++;
                keyIds.add(keyId);
            }
            else {
                logger.info("FIDO綁定註銷異常...DoRevoke失敗({})", keyId);
                logger.info("FIDO綁定註銷異常...已註銷{}筆({})", keyId, keyIds.stream().collect(Collectors.joining(",")));

                if (!"NGNOT001".equals(getTaskId().toUpperCase())) {
                    throw ExceptionUtils.getActionException(AIBankErrorCode.FIDO_SDK_ERROR);
                }
                rsData.setSuccess(false);
                rsData.setErrorCode(queryLogResponse.getError());
                rsData.setErrorDesc(queryLogResponse.getError_description());
                return;
            }
        }

        logger.info("FIDO綁定註銷完成...共註銷{}筆資料", devokeCount);
        rsData.setSuccess(true);
    }

}
