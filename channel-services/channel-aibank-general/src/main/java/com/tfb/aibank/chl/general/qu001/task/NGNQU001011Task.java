package com.tfb.aibank.chl.general.qu001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001011Rq;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001011Rs;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.UserProfile;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NGNQU001011Task.java
 *
 * <p>Description: 首頁-更新暱稱</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/11/15, MartyPan
 *  <li>更新暱稱</li>
 * </ol>
 */
//@formatter:on

@Component
@Scope("prototype")
public class NGNQU001011Task extends AbstractAIBankBaseTask<NGNQU001011Rq, NGNQU001011Rs> {

    @Autowired
    private UserResource resource;

    @Override
    public void validate(NGNQU001011Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNQU001011Rq rqData, NGNQU001011Rs rsData) throws ActionException {

        if (isLoggedIn()) {
            AIBankUser user = getLoginUser();
            UserProfile u = new UserProfile();
            u.setCustId(user.getCustId());
            u.setUserUuid(user.getUserId());
            u.setUidDup(user.getUidDup());
            u.setCompanyKind(user.getCompanyKind());
            String nickname = StringUtils.numberToFullWidthChar(rqData.getNickname());
            u.setNickName(nickname);

            resource.updateUserNickname(u);

            // 更新 session 裡的 user 資訊
            getLoginUser().getUserData().setNickName(nickname);
        }
    }


    @Override
    protected boolean isRequireLogin() {
        return true;
    }
}
