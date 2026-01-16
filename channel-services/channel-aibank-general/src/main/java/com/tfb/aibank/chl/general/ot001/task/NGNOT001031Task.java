package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001031Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001031Rs;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001100RsErrorStatus;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.type.RemarkContentType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001031Task.java 
* 
* <p>Description:裝置綁定條款頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001031Task extends AbstractAIBankBaseTask<NGNOT001031Rq, NGNOT001031Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001031Task.class);

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private LoginService service;

    @Override
    public void validate(NGNOT001031Rq rqData) throws ActionException {
        // 條款頁，無需 validate
    }

    @Override
    public void handle(NGNOT001031Rq rqData, NGNOT001031Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001031 start====");

        ActionException err = new ActionException(AIBankErrorCode.FIDO_SDK_ERROR);
        NGNOT001100RsErrorStatus errorStatus = new NGNOT001100RsErrorStatus();
        errorStatus.setSys(err.getSystemId());
        errorStatus.setCode(err.getErrorCode());
        errorStatus.setDesc(err.getErrorDesc());
        rsData.setErrorStatus(errorStatus);

        rsData.setNextPage(rqData.getNextPage());

        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS.getType(), LoginService.FASTLOGIN_TERMS_REMARK_KEY, getLocale().toString());
        if (remarkContent == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        rsData.setContractTitle(remarkContent.getTitle());
        rsData.setContractContent(remarkContent.getContent());

        String fidoPortalUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_SDK_URL");
        rsData.setFidoPortalUrl(fidoPortalUrl);

        String businessNo = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_BUSINESS_NO");
        rsData.setBusinessNo(businessNo);
        rsData.setMemberNo(service.getMemberNo(getLoginUser().getCustId(), getRequest().getDeviceIxd()));

        rsData.setOnboardingType(getLoginUser().getOnboardingType());
    }

    protected String getMessage(String messageId) {
        return I18NUtils.getMessage(messageId);
    }

    protected String getMessage(String messageId, Object... params) {
        return I18NUtils.getMessage(messageId, params);
    }
}
