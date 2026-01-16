/**
 * 
 */
package com.tfb.aibank.biz.user.services.login;

import java.util.Date;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.EbLoginLogBRepository;
import com.tfb.aibank.biz.user.repository.UserLoginRepository;
import com.tfb.aibank.biz.user.repository.entities.EbLoginLogBEntity;
import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;
import com.tfb.aibank.chl.session.type.LoginStatusType;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)LoginSessionService.java
* 
* <p>Description:Login Session 相關處理</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/03, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class LoginSessionService {

    private static final IBLog logger = IBLog.getLog(LoginSessionService.class);

    private UserLoginRepository userLoginRepository;

    private IdentityService identityService;

    private EbLoginLogBRepository ebLoginLogBRepository;

    public LoginSessionService(UserLoginRepository userLoginRepository, IdentityService identityService, EbLoginLogBRepository ebLoginLogBRepository) {

        this.identityService = identityService;
        this.userLoginRepository = userLoginRepository;
        this.ebLoginLogBRepository = ebLoginLogBRepository;

    }

    /**
     * 註冊登入Session
     * 
     * @param custId
     * @param userId
     * @param companyKind
     * @param sessionId
     * @return
     * @throws ActionException
     */
    public boolean registerAccessSession(String custId, String uidDup, String userId, Integer companyKind, Integer loginLogPk, String sessionId) throws ActionException {
        IdentityData identityData;

        try {
            if (StringUtils.isBlank(custId)) {
                logger.error("custId 錯誤 {}", custId);
                throw new ActionException(AIBankErrorCode.SESSION_UPDATE_FAIL);
            }
            identityData = identityService.query(custId, uidDup, userId, companyKind);
        }
        catch (CryptException e) {
            logger.error("IdentityService 錯誤", e);
            throw new ActionException(AIBankErrorCode.SESSION_UPDATE_FAIL);
        }

        UserLoginEntity userLoginEntity = userLoginRepository.findByUserKey(identityData.getUserKey());

        if (userLoginEntity == null) {
            throw new ActionException(AIBankErrorCode.SESSION_UPDATE_FAIL);
        }

        userLoginEntity.setSessionId(fixSessionId(sessionId));
        userLoginEntity.setLastAccessTime(new Date());
        userLoginEntity.setStatus(LoginStatusType.LOGIN.getCode());
        userLoginRepository.save(userLoginEntity);

        if (loginLogPk != null) {
            EbLoginLogBEntity entity = ebLoginLogBRepository.findByLoginLogKey(loginLogPk);
            if (entity != null) {
                entity.setSessionId(sessionId);
                ebLoginLogBRepository.save(entity);
            }
        }

        return true;
    }

    /**
     * 更新登入 Session Last_Access_Time
     * 
     * 如果Session 相同，更新 Last_Access_Time 如果Session 不相同， throw USER_LOGINED_IN_OTHER
     * 
     * @param custId
     * @param userId
     * @param companyKind
     * @param sessionId
     * @return
     * @throws ActionException
     */
    public boolean updateAccessTime(String custId, String uidDup, String userId, Integer companyKind, String sessionId) throws ActionException {

        IdentityData identityData;

        try {
            if (StringUtils.isBlank(custId)) {
                logger.error("custId 錯誤 {}", custId);
                throw new ActionException(AIBankErrorCode.SESSION_UPDATE_FAIL);
            }
            identityData = identityService.query(custId, uidDup, userId, companyKind);
        }
        catch (CryptException e) {
            logger.error("IdentityService 錯誤", e);
            throw new ActionException(AIBankErrorCode.SESSION_UPDATE_FAIL);
        }

        UserLoginEntity userLoginEntity = userLoginRepository.findByUserKey(identityData.getUserKey());

        if (userLoginEntity == null) {
            throw new ActionException(AIBankErrorCode.SESSION_UPDATE_FAIL);
        }

        if (StringUtils.isBlank(sessionId)) {
            throw new ActionException(AIBankErrorCode.SESSION_UPDATE_FAIL);
        }
        String modifySessionId = fixSessionId(sessionId);
        /** Sssion 相同，更新 LAST_ACCESS_TIME */
        if (modifySessionId.equals(userLoginEntity.getSessionId())) {
            userLoginEntity.setLastAccessTime(new Date());
            userLoginRepository.save(userLoginEntity);
            return true;
        }
        /** Sssion 不同，代表已登入其他 */
        throw new ActionException(AIBankErrorCode.USER_LOGINED_IN_OTHER);

    }

    private String fixSessionId(String sessionId) {
        if (StringUtils.isBlank(sessionId))
            return sessionId;

        String modifySessionId = sessionId.replace("-", "");
        if (modifySessionId.length() > 32)
            modifySessionId = modifySessionId.substring(0, 32);
        return modifySessionId;
    }
}
