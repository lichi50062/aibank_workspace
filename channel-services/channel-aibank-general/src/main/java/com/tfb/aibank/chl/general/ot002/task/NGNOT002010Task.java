package com.tfb.aibank.chl.general.ot002.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot002.model.NGNOT002010Rq;
import com.tfb.aibank.chl.general.ot002.model.NGNOT002010Rs;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.ExecuteUserLogoutRequest;
import com.tfb.aibank.chl.session.AIBankUser;

import jakarta.servlet.http.HttpSession;

//@formatter:off
/**
* @(#)NGNOT002010Task.java 
* 
* <p>Description:登出頁</p>
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
public class NGNOT002010Task extends AbstractAIBankBaseTask<NGNOT002010Rq, NGNOT002010Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT002010Task.class);

    @Autowired
    private UserResource userResource;

    @Override
    public void validate(NGNOT002010Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT002010Rq rqData, NGNOT002010Rs rsData) throws ActionException {
        logger.debug("==== NGNOT002010 start====");
        AIBankUser user = getLoginUser();
        if (user != null) {
            try {
                ExecuteUserLogoutRequest request = new ExecuteUserLogoutRequest();
                request.setCustId(user.getCustIdWithDup());
                request.setUserId(user.getUserId());
                request.setCompanyKind(user.getCompanyKind());
                request.setLoginLogPk(user.getLoginLogPk());

                userResource.executeUserLogout(request);
            }
            catch (ServiceException ex) {
                logger.debug("Logout fail ", ex);
            }
        }

        super.logoutUser();

        InvalidSession();

    }

    // * 清除 session */
    protected final void InvalidSession() {
        // 取得目前 session
        HttpSession session = getHttpServletRequest().getSession(false);
        session.invalidate();
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
