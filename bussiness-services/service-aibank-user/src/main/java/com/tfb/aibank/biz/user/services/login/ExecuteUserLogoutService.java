/**
 * 
 */
package com.tfb.aibank.biz.user.services.login;

import java.util.Date;

import org.slf4j.MDC;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.EbLoginLogBRepository;
import com.tfb.aibank.biz.user.repository.UserLoginRepository;
import com.tfb.aibank.biz.user.repository.entities.EbLoginLogBEntity;
import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLogoutRequest;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLogoutResponse;
import com.tfb.aibank.chl.session.type.ChannelIdType;
import com.tfb.aibank.chl.session.type.LoginStatusType;

/**
 * @author john
 *
 */
public class ExecuteUserLogoutService {

    private static final IBLog logger = IBLog.getLog(ExecuteUserLogoutService.class);

    private UserLoginRepository userLoginRepository;

    private IdentityService identityService;

    private EbLoginLogBRepository ebLoginLogBRepository;

    public ExecuteUserLogoutService(UserLoginRepository userLoginRepository, IdentityService identityService, EbLoginLogBRepository ebLoginLogBRepository) {

        this.userLoginRepository = userLoginRepository;
        this.identityService = identityService;
        this.ebLoginLogBRepository = ebLoginLogBRepository;
    }

    public ExecuteUserLogoutResponse executeUserLogout(ExecuteUserLogoutRequest request) throws CryptException {

        if (request.getLoginLogPk() != null) {
            try {
                EbLoginLogBEntity entity = ebLoginLogBRepository.findByLoginLogKey(request.getLoginLogPk());
                if (entity != null) {
                    entity.setLogoutTime(new Date());
                    ebLoginLogBRepository.save(entity);
                }
            }
            catch (Exception ex) {
                logger.warn("EB_LOGIN_LOG_B 更新 LogOut Time 失敗", ex);
            }
        }

        ExecuteUserLogoutResponse response = new ExecuteUserLogoutResponse();

        if (StringUtils.isBlank(request.getCustId())) {
            return response;
        }

        if (request.getCustId().length() == 10) {
            request.setCustId(request.getCustId() + "0");
        }

        IdentityData identityData = identityService.query(request.getCustId().substring(0, 10), request.getCustId().substring(10), request.getUserId(), request.getCompanyKind());

        UserLoginEntity userLoginEntity = userLoginRepository.findByUserKey(identityData.getUserKey());
        if (userLoginEntity == null)
            return response;

        if (ChannelIdType.AIBank.getChannelId().equals(userLoginEntity.getChannelId())) {
            String sessionId = MDC.get(MDCKey.sessionid.name());

            if (sessionId != null) {
                sessionId = fixSessionId(sessionId);
                if (StringUtils.equals(sessionId, userLoginEntity.getSessionId())) {
                    userLoginEntity.setStatus(LoginStatusType.LOGOUT.getCode());
                    userLoginEntity.setSignonToken("");
                    userLoginEntity.setLastAccessTime(new Date());
                    userLoginRepository.save(userLoginEntity);
                }
            }
        }
        else {
            logger.debug("已在其他平台登入");
        }

        return response;
    }

    private String fixSessionId(String sessionId) {
        if (sessionId == null)
            return "";
        String sessId = sessionId.replace("-", "");
        if (sessId.length() > 32)
            return sessId.substring(0, 32);
        return sessId;
    }

}
