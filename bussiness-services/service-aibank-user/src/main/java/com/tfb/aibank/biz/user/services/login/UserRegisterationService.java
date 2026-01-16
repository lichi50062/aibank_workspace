package com.tfb.aibank.biz.user.services.login;

import java.math.BigDecimal;
import java.util.Date;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.exception.DatabaseException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.user.repository.CompanyRepository;
import com.tfb.aibank.biz.user.repository.UserLoginRepository;
import com.tfb.aibank.biz.user.repository.UserRepository;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.repository.entities.UserLoginEntity;
import com.tfb.aibank.common.type.CompanyBUType;
import com.tfb.aibank.common.type.CompanyKindType;
import com.tfb.aibank.common.type.CompanyStatusType;
import com.tfb.aibank.common.type.FxmlType;
import com.tfb.aibank.common.type.UaaLevelType;
import com.tfb.aibank.common.type.UserStatusType;
import com.tfb.aibank.common.type.UserType;
import com.tfb.aibank.common.util.BizExceptionUtils;

// @formatter:off
/**
 * @(#)UserRegisterationService.java
 * 
 * <p>Description:使用者註冊 & 資料同步 Service</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, John Chang 	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UserRegisterationService {

    private static final IBLog logger = IBLog.getLog(ExecuteUserLoginService.class);

    private CompanyRepository companyRepository;
    private UserRepository userProfileRepository;
    private UserLoginRepository userLoginRepository;

    UserRegisterationService(CompanyRepository companyRepository, UserRepository userProfileRepository, UserLoginRepository userLoginRepository) {
        this.companyRepository = companyRepository;
        this.userProfileRepository = userProfileRepository;
        this.userLoginRepository = userLoginRepository;
    }

    /**
     * 建立新 company (非卡戶)
     *
     * @param idAndDup
     * @return
     */
    // formatter:off
    public CompanyEntity registerNewCompany(String idAndDup) {
        String[] uids = parseIDNO(idAndDup);
        // create Company Entity
        CompanyEntity company = createNewCompanyEntity(uids[0], uids[1]);
        this.companyRepository.save(company);
        return company;
    }

    // formatter:off:on
    /**
     * 建立新 company (卡戶)
     * 
     * @param uid
     * @param companyKind
     * @param companyName
     * @param establishDate
     * @param emails
     * @param registerTime
     * @return
     */
    public CompanyEntity registerNewCompany(String uid, CompanyKindType companyKind, String companyName, Date establishDate, String emails, Date registerTime) {
        CompanyEntity company = createNewCompanyEntity(uid, companyKind, companyName, establishDate, emails, registerTime);
        this.companyRepository.save(company);
        return company;
    }

    /**
     * 建立新使用者 (非卡戶)
     *
     * @param idAndDup
     *            id + 重覆碼
     * @param userId
     *            使用者代碼
     * @throws DatabaseException
     */
    public UserEntity registerNewCompanyAndUser(AESCipherUtils aesCipherUtils, LoginUser loginUser, String idAndDup, String userId) throws ActionException {
        String[] uids = parseIDNO(idAndDup);
        CompanyKindType companyKind = StringUtils.length(uids[0]) == 8 ? CompanyKindType.COMPANY : CompanyKindType.PERSONAL;

        logger.info("先依UID查詢 Company 是否存在");
        CompanyEntity company = null;
        try {
            String eUid = aesCipherUtils.encrypt(ValidateParamUtils.validParam(uids[0]));
            // 【FORTIFY：Access Control: Database】eUid、uids[1] 就是身分識別屬性欄位
            company = companyRepository.findByUidDupAndKind(ValidateParamUtils.validParam(eUid), ValidateParamUtils.validParam(uids[1]), companyKind.getCode());
            if (company == null) {
                // create Company Entity
                company = createNewCompanyEntity(uids[0], uids[1]);
                company.setCompanyKind(companyKind.getCode());
                company = companyRepository.save(company);
            }
            loginUser.setCompanyEntity(company);
        }
        catch (CryptException ex) {
            logger.error(ex.getMessage(), ex);
            throw BizExceptionUtils.getActionException(ex);
        }

        UserEntity userEntity = registerNewCompanyAndUser(aesCipherUtils, loginUser, company, userId, userProfileRepository, userLoginRepository);
        return userEntity;
    }

    /**
     * 將 TFB ID NO 分解為 uid 及 dup '81231238' --> '81231238', '0' 'A123456789' --> 'A123456789', '0' 'A1234567891' --> 'A123456789', '1' '81231238 1' --> '81231238', '1' 'A12345678923' --> 'A123456789', '2' '81231238 23' --> '81231238', '2'
     *
     * @param idno
     * @return
     */
    public static String[] parseIDNO(String idno) {
        // 取左邊10位去空白
        String uid = StringUtils.trimToEmptyEx(StringUtils.left(idno, 10));
        // 取第10位, 沒有值則帶0
        String uidDup = StringUtils.defaultIfEmpty(StringUtils.substring(idno, 10, 11), "0");
        String[] uids = new String[] { uid, uidDup };
        return uids;
    }

    /**
     * 建立新使用者 (非卡戶)
     * 
     * @param aesCipherUtils
     * @param loginUser
     * @param company
     * @param userId
     * @param userRepository
     * @param userLoginRepository
     * @return
     */
    public UserEntity registerNewCompanyAndUser(AESCipherUtils aesCipherUtils, LoginUser loginUser, CompanyEntity company, String userId, UserRepository userRepository, UserLoginRepository userLoginRepository) {
        logger.info("註冊新使用者");

        logger.info("先依UID、UserId查詢 User 是否存在");
        UserEntity userEntity = userRepository.findByUidAndidDupAndUuid(company.getCompanyUid(), company.getUidDup(), userId, CompanyKindType.find(company.getCompanyKind()).getCode());
        if (userEntity == null) {
            logger.info("建立新的 User Entity");
            userEntity = createNewUserEntitty(company, userId);
            userRepository.save(userEntity);
            loginUser.setUserEntity(userEntity);

            logger.info("建立 User Login Entity");
            UserLoginEntity userLoginEntity = createNewUserLoginEntity(company, userEntity);
            userLoginRepository.save(userLoginEntity);
            loginUser.setUserLoginEntity(userLoginEntity);

            logger.info("註冊成功");
        }
        else {
            logger.info("使用者代號已存在");
        }
        return userEntity;
    }

    /**
     * 建立新使用者 (卡戶)
     * 
     * @param ixdAndDup
     * @param userIxd
     * @param company
     * @param secrxt
     * @param userStatus
     * @param loginStatus
     * @param pxdErrorCount
     * @param lastLoginTime
     * @return
     */
    public UserEntity registerNewCompanyAndUser(String idAndDup, String userId, CompanyEntity company, String secret, Integer userStatus, Integer loginStatus, Integer pwdErrorCount, Date lastLoginTime) {
        logger.info("註冊新使用者");

        String[] uids = parseIDNO(idAndDup);

        logger.info("先依UID、UserId查詢 User 是否存在");
        UserEntity userEntity = this.userProfileRepository.findByUidAndidDupAndUuid(uids[0], uids[1], userId, CompanyKindType.find(company.getCompanyKind()).getCode());
        if (userEntity == null) {
            logger.info("建立新的 User Entity");
            userEntity = createNewUserEntitty(company, userId, secret, userStatus);
            this.userProfileRepository.save(userEntity);
            logger.info("建立 User Login Entity");
            UserLoginEntity userLoginEntity = createNewUserLoginEntity(company, userEntity, loginStatus, pwdErrorCount, lastLoginTime);
            this.userLoginRepository.save(userLoginEntity);
            logger.info("註冊結束, commit");
        }
        else {
            logger.info("使用者代號已存在");
        }
        return userEntity;
    }

    private UserLoginEntity createNewUserLoginEntity(CompanyEntity company, UserEntity userEntity, Integer loginStatus, Integer pwdErrorCount, Date lastLoginTime) {
        UserLoginEntity userLoginEntity = createNewUserLoginEntity(company, userEntity);
        if (loginStatus != null) {
            userLoginEntity.setStatus(loginStatus);
        }
        if (pwdErrorCount != null) {
            userLoginEntity.setPwdErrorCount(pwdErrorCount);
        }
        if (lastLoginTime != null) {
            userLoginEntity.setLastLoginTime(lastLoginTime);
        }
        return userLoginEntity;
    }

    /**
     * @param company
     * @param userEntity
     * @return
     */
    public UserLoginEntity createNewUserLoginEntity(CompanyEntity company, UserEntity userEntity) {
        UserLoginEntity userLoginEntity = new UserLoginEntity();
        userLoginEntity.setCompanyKey(company.getCompanyKey());
        userLoginEntity.setUserKey(userEntity.getUserKey());
        userLoginEntity.setStatus(-1);
        userLoginEntity.setPwdErrorCount(0);
        userLoginEntity.setPwdErrorTime(null);
        userLoginEntity.setPwdChangeTime(null);
        userLoginEntity.setPwdForceChange(0);
        userLoginEntity.setSessionId("");
        userLoginEntity.setClientIp("");
        userLoginEntity.setLoginTime(null);
        userLoginEntity.setLastAccessTime(null);
        userLoginEntity.setLastLoginTime(null);
        return userLoginEntity;
    }

    /**
     * 建立使用者 for 卡戶
     * 
     * @param company
     * @param userIxd
     *            使用者代碼
     * @param secrxt
     *            秘密代碼
     * @param usxrCname
     *            中文姓名
     * @param status
     *            狀態
     * @return
     * @throws Exception
     */
    private UserEntity createNewUserEntitty(CompanyEntity company, String userId, String secret, Integer status) {
        UserEntity userEntity = createNewUserEntitty(company, userId);

        userEntity.setCompanyKey(company.getCompanyKey());
        userEntity.setUserUuid(userId);
        userEntity.setSecret(secret);
        userEntity.setUserCname(company.getCompanyName());
        userEntity.setStatus(status);

        return userEntity;
    }

    /**
     * 建立使用者 for 非卡戶
     * 
     * @param company
     * @param userId
     *            使用者代號
     * @param param
     * @return
     */
    public UserEntity createNewUserEntitty(CompanyEntity company, String userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCompanyKey(company.getCompanyKey());

        userEntity.setUserUuid(userId);
        // 前端不存密碼
        userEntity.setUserCname("");
        userEntity.setUserEname("");
        userEntity.setSecret("");
        userEntity.setStatus(UserStatusType.ALIVE.getCode());
        userEntity.setUserType(UserType.UAA_ROOT0.getCode());
        userEntity.setLocale(null);
        userEntity.setOtpType(0);
        userEntity.setOtpCert(null);
        userEntity.setSmartCardType(1);
        userEntity.setFxmlType(FxmlType.NONE.getCode());
        userEntity.setFxmlCert(null);
        userEntity.setMobileRight(1);
        userEntity.setLoginPeriod(null);
        userEntity.setLoginStartTime(null);
        userEntity.setLoginEndTime(null);
        userEntity.setDepartmentName("");
        userEntity.setTel1(null);
        userEntity.setTel2(null);
        userEntity.setTel3(null);
        userEntity.setFax1(null);
        userEntity.setFax2(null);
        userEntity.setMobile(null);
        userEntity.setEmails(null);
        BigDecimal UNLIMITED = new BigDecimal(-1);
        userEntity.setTwEditQuota(UNLIMITED);
        userEntity.setTwVerifyQuota(UNLIMITED);
        userEntity.setTwPassQuota(UNLIMITED);
        userEntity.setTwDailyPassQuota(UNLIMITED);
        userEntity.setFxEditQuota(UNLIMITED);
        userEntity.setFxVerifyQuota(UNLIMITED);
        userEntity.setFxPassQuota(UNLIMITED);
        userEntity.setFxDailyPassQuota(UNLIMITED);
        userEntity.setTwPassDate(null);
        userEntity.setTwTotalPassAmt(null);
        userEntity.setFxPassDate(null);
        userEntity.setFxTotalPassAmt(null);
        return userEntity;
    }

    /**
     * 建立 CompanyEntity for 卡戶
     * 
     * @param uid
     * @param companyKind
     * @param companyName
     * @param establishDate
     * @param emails
     * @param registerTime
     * @return
     * @throws Exception
     */
    private CompanyEntity createNewCompanyEntity(String uid, CompanyKindType companyKind, String companyName, Date establishDate, String emails, Date registerTime) {
        CompanyEntity company = createNewCompanyEntity(uid, "0");

        company.setCompanyKind(companyKind.getCode());
        company.setCompanyName(companyName);
        company.setEstablishDate(establishDate);
        company.setEmails(emails);
        company.setRegisterTime(registerTime);
        return company;
    }

    /**
     * @param idno
     *            使用者統編
     * @param dup
     *            重覆碼
     * @throws Exception
     */
    private CompanyEntity createNewCompanyEntity(String idno, String dup) {
        CompanyEntity company = new CompanyEntity();

        company.setCompanyUid(idno);
        company.setUidDup(dup);
        company.setStatus(CompanyStatusType.ALIVE.getCode());
        // 判斷公司戶或個人戶
        company.setCompanyKind(StringUtils.length(idno) == 8 ? CompanyKindType.COMPANY.getCode() : CompanyKindType.PERSONAL.getCode());
        // DBU/OBU 每次登入時發電文判斷, 所以這邊一律存 unknown
        company.setCompanyBUType(CompanyBUType.UNKNOWN.getCode());
        company.setCompanyName("");
        company.setCompanyEname("");
        company.setUaaLevel(UaaLevelType.TYPE0.getCode());
        company.setDefaultFlowSchemaKey(0);
        company.setSchemaSuitOperatorKey(0);
        company.setSchemaSuitCreateTime(null);
        company.setEstablishDate(null);
        company.setDefaultBranchId(null);
        company.setTel(null);
        company.setFax(null);
        company.setMobile(null);
        company.setEmails(null);
        company.setRetryFlag(0);
        company.setLoginFlag(0);
        company.setSalaryFlag(0);
        company.setOnlinePayeeFlag(0);
        company.setRoot1TxFlag(0);
        company.setRoot2TxFlag(0);
        company.setPayerAccountFlag(0);
        company.setPayeeAccountFlag(0);
        company.setFaxFlag(0);
        company.setTwAtmFlag(0);
        company.setTwRemitFlag(0);
        company.setTwFxmlFlag(0);
        company.setTwPayeeFlag(0);
        company.setTwAmtQuota(BigDecimal.ZERO);
        company.setTwInterRemitQuota(BigDecimal.ZERO);
        company.setRegisterTime(new Date());
        company.setCancelTime(null);
        company.setLastBranchId(null);
        company.setLastEditorKey(0);
        company.setLastManagerKey(0);
        company.setLastUpdateTime(new Date());
        company.setQueryOnlyFlag(0);
        return company;
    }

}