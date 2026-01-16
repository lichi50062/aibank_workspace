package com.tfb.aibank.chl.general.ag003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ag003.model.NGNAG003010Rq;
import com.tfb.aibank.chl.general.ag003.model.NGNAG003010Rs;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)NGNAG003010Task.java
 * 
 * <p>Description:大頭貼及暱稱設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNAG003010Task extends AbstractAIBankBaseTask<NGNAG003010Rq, NGNAG003010Rs> {

    @Autowired
    protected SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NGNAG003010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNAG003010Rq rqData, NGNAG003010Rs rsData) throws ActionException {

        String defaultAvatar = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "AVATAR_DEFAULT");

        rsData.setDefaultAvatar(defaultAvatar);
        rsData.setAvatar(StringUtils.defaultString(getLoginUser().getUserData().getAvatar()));
        rsData.setNickname(StringUtils.toHalfChar(StringUtils.defaultString(getLoginUser().getUserData().getNickName())));
    }

}
