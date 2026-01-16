package com.tfb.aibank.chl.general.ag003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ag003.model.NGNAG003011Rq;
import com.tfb.aibank.chl.general.ag003.model.NGNAG003011Rs;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.UserProfile;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNAG003011Task.java
 * 
 * <p>Description:大頭貼及暱稱設定 011 儲存 </p>
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
public class NGNAG003011Task extends AbstractAIBankBaseTask<NGNAG003011Rq, NGNAG003011Rs> {

    @Autowired
    private UserResource resource;

    @Override
    public void validate(NGNAG003011Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNAG003011Rq rqData, NGNAG003011Rs rsData) throws ActionException {

        if (!ValidatorUtility.isValidChar(rqData.getNickname())) {
            setRsError(new ActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND));
            return;
        }

        AIBankUser user = getLoginUser();
        UserProfile u = new UserProfile();
        u.setCustId(user.getCustId());
        u.setUserUuid(user.getUserId());
        u.setUidDup(user.getUidDup());
        u.setCompanyKind(user.getCompanyKind());
        u.setAvatar(rqData.getAvatar());
        String nickname = StringUtils.numberToFullWidthChar(rqData.getNickname());
        u.setNickName(nickname);

        resource.updateUserNickname(u);

        // 更新 session 裡的 user 資訊
        getLoginUser().getUserData().setAvatar(u.getAvatar());
        getLoginUser().getUserData().setNickName(nickname);

        rsData.setSuccess(true);

    }

}
