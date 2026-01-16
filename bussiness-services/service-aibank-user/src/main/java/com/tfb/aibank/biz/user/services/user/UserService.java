package com.tfb.aibank.biz.user.services.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.CompanyRepository;
import com.tfb.aibank.biz.user.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.user.repository.MbGestureProfileRepository;
import com.tfb.aibank.biz.user.repository.UserRepository;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.MbGestureProfileEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.services.login.model.UnLoginEmailsResponse;
import com.tfb.aibank.biz.user.services.user.model.EB172650Res;
import com.tfb.aibank.biz.user.services.user.model.GesturesPwdRequest;
import com.tfb.aibank.biz.user.services.user.model.SsoStatusTypeGestures;
import com.tfb.aibank.biz.user.services.user.model.UserDataModel;
import com.tfb.aibank.common.util.BaNCSUtil;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB032154RS;
import com.tfb.aibank.integration.eai.msg.EB032159RS;
import com.tfb.aibank.integration.eai.msg.EB067217RS;
import com.tfb.aibank.integration.eai.msg.EB172650RS;
import com.tfb.aibank.integration.eai.msg.EB67050RS;

import tw.com.ibm.mf.eai.TxRepeatType;
import tw.com.ibm.mf.eb.EB032154SvcRsType;
import tw.com.ibm.mf.eb.EB067217RepeatType;
import tw.com.ibm.mf.eb.EB172650SvcRsType;

public class UserService {

    private static final IBLog logger = IBLog.getLog(UserService.class);

    private CompanyRepository companyRepository;

    private UserRepository userRepository;

    private MbDeviceInfoRepository mbDeviceInfoRepository;

    private MbGestureProfileRepository mbGestureProfileRepository;

    private EsbChannelGateway esbGateway;

    private static final int MAX_PASSWORD_FAIL_COUNT = 3;

    public UserService(EsbChannelGateway esbChannelGateway, CompanyRepository companyRepository, UserRepository userRepository, MbDeviceInfoRepository mbDeviceInfoRepository, MbGestureProfileRepository mbGestureProfileRepository) {
        this.esbGateway = esbChannelGateway;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.mbGestureProfileRepository = mbGestureProfileRepository;
    }

    public UserDataModel getUserAndCompany(Integer userKey, Integer companyKey) throws XmlException, EAIException, EAIResponseException {
        // 【FORTIFY：Access Control: Database】誤判，COMPANY_KEY、USER_KEY 就是身分識別屬性欄位
        CompanyEntity companyEntity = companyRepository.findByCompanyKey(companyKey);
        UserEntity userEntity = userRepository.findByCompanyKeyAndUserKey(companyKey, userKey);

        UserDataModel userDataModel = new UserDataModel();
        userDataModel.setCustId(companyEntity.getCompanyUid());
        userDataModel.setUserId(userEntity.getUserUuid());
        userDataModel.setNameCode(userEntity.getNameCode());
        userDataModel.setCompanyKind(companyEntity.getCompanyKind());
        userDataModel.setUidDup(companyEntity.getUidDup());
        userDataModel.setUserKey(userKey);

        UnLoginEmailsResponse unLoginEmailsResponse = getUnLoginEmails(companyEntity.getCompanyUid(), userEntity.getUserType());
        if (unLoginEmailsResponse.getEmails() != null) {
            userDataModel.setEmail(String.join(";", unLoginEmailsResponse.getEmails()));
        }

        userDataModel.setMobile(getUserMobile(companyEntity.getCompanyUid()));

        logger.info("custId: {}, userId: {}, nameCode: {}, companyKind: {}, uidDup: {}, email: {}, mobile: {}", userDataModel.getCustId(), userDataModel.getUserId(), userDataModel.getNameCode(), userDataModel.getCompanyKind(), userDataModel.getUidDup(), userDataModel.getEmail(), userDataModel.getMobile());

        return userDataModel;
    }

    /**
     * 取得行動電話 該客戶為本行客戶但非本行網路銀行客戶 ForEP32
     *
     * @param uid
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public String getUserMobile(String uid) throws XmlException, EAIException, EAIResponseException {

        EB032154RS rs = esbGateway.sendEB032154(uid);

        EB032154SvcRsType rsType = rs.getBody();
        String mobileNo = "";

        if (!StringUtils.isEmpty(rsType.getHandphone())) {
            mobileNo = rsType.getHandphone();
        }
        logger.info("EB032154電文 mobileno" + rsType.getHandphone());

        return mobileNo;
    }

    /**
     * 手勢密碼驗證(驗證後也會更新快登與手勢資訊MbGestureProfile、MbDeviceInfo)
     *
     * @param request
     * @throws ActionException
     * @throws Exception
     */
    public String verifyGesturesPwd(GesturesPwdRequest request) throws ActionException {

        logger.info("手勢密碼驗證:" + request.getDeviceId() + ", challenge:" + request.getChallenge());

        MbDeviceInfoEntity mbDeviceInfoEntities = mbDeviceInfoRepository.findByDeviceUuid(ValidateParamUtils.validParam(request.getDeviceId()));

        if (mbDeviceInfoEntities == null) {
            logger.error("手勢密碼:查無 MB_DEVICE_INFO");
            // 本手機尚未綁定快速登入
            return SsoStatusTypeGestures.DEVICE_NOT_BINDED.getStatusCode();
        }

        if (mbDeviceInfoEntities.getEnable() != 1 || mbDeviceInfoEntities.getLoginPasswdType() != 1) {
            logger.error("手勢密碼:MB_DEVICE_INFO 狀態錯誤 LoginPasswdType={}", mbDeviceInfoEntities.getLoginPasswdType());
            // 本手機尚未綁定快速登入
            return SsoStatusTypeGestures.DEVICE_NOT_BINDED.getStatusCode();
        }

        CompanyEntity companyEntity = companyRepository.findByCompanyKey(mbDeviceInfoEntities.getCompanyKey());

        if (companyEntity == null) {
            logger.error("CompanyEntity not found {}", IBUtils.toDataModel(mbDeviceInfoEntities.getCompanyKey(), Integer.class));
            // 本手機尚未綁定快速登入
            return SsoStatusTypeGestures.DEVICE_NOT_BINDED.getStatusCode();
        }

        if (!companyEntity.getCompanyUid().equals(request.getCustId())) {
            logger.error("手勢密碼:Company 資訊不符 {} = {}", IBUtils.toDataModel(DataMaskUtil.maskCustId(companyEntity.getCompanyUid()), String.class), IBUtils.toDataModel(DataMaskUtil.maskCustId(request.getCustId()), String.class));
            // 本手機尚未綁定快速登入
            return SsoStatusTypeGestures.DEVICE_NOT_BINDED.getStatusCode();
        }

        UserEntity userEntity = userRepository.findByUserKey(mbDeviceInfoEntities.getUserKey());
        if (userEntity == null) {
            logger.error("UserEntity not found {}", IBUtils.toDataModel(mbDeviceInfoEntities.getCompanyKey(), Integer.class));
            // 本手機尚未綁定快速登入
            return SsoStatusTypeGestures.DEVICE_NOT_BINDED.getStatusCode();
        }

        if (!userEntity.getUserUuid().equals(request.getUserId())) {
            logger.error("手勢密碼:User Profile 資訊不符 {} = {}", userEntity.getUserUuid(), request.getUserId());
            // 本手機尚未綁定快速登入
            return SsoStatusTypeGestures.DEVICE_NOT_BINDED.getStatusCode();
        }

        MbGestureProfileEntity mbGestureProfileEntity = mbGestureProfileRepository.findByDeviceInfoKey(mbDeviceInfoEntities.getDeviceInfoKey());
        if (mbGestureProfileEntity == null) {
            logger.error("MbGestureProfileEntity not found {}", IBUtils.toDataModel(mbDeviceInfoEntities.getCompanyKey(), Integer.class));
            // 本手機尚未綁定快速登入
            return SsoStatusTypeGestures.DEVICE_NOT_BINDED.getStatusCode();
        }

        // 密碼驗證成功先更為0
        String pinBlock = request.getChallenge().replace("@", "");
        if (mbGestureProfileEntity.getPassword().equals(pinBlock)) {
            mbGestureProfileEntity.setPwdErrorCount(0);
            mbGestureProfileEntity.setStatus(0);
            mbGestureProfileRepository.save(mbGestureProfileEntity);
            return SsoStatusTypeGestures.SUCCESS.getStatusCode();
        }

        int pwdErrorCount = mbGestureProfileEntity.getPwdErrorCount() + 1;
        mbGestureProfileEntity.setPwdErrorTime(new Date());
        mbGestureProfileEntity.setPwdErrorCount(pwdErrorCount);
        if (pwdErrorCount >= MAX_PASSWORD_FAIL_COUNT) {
            mbGestureProfileEntity.setStatus(-2);
        }
        else {
            if (mbGestureProfileEntity.getStatus() == null) {
                mbGestureProfileEntity.setStatus(0);
            }
        }

        mbGestureProfileRepository.save(mbGestureProfileEntity);

        if (pwdErrorCount >= MAX_PASSWORD_FAIL_COUNT) {
            mbDeviceInfoEntities.setLoginPasswdType(0);
            mbDeviceInfoEntities.setLoginFlag(0);
            mbDeviceInfoRepository.save(mbDeviceInfoEntities);
            // 錯誤代碼+手勢密碼連續輸入錯誤三次已被鎖定，請您先用使用者密碼方式登入後重新進行手勢密碼設定，如有疑問，請洽客戶服務專線02-8751-6665。
            return SsoStatusTypeGestures.FAST_LOGIN_LOACKED.getStatusCode();
        }
        else if (pwdErrorCount == 1) {
            // 錯誤代碼+手勢密碼輸入錯誤一次，如有疑問，請洽客戶服務專線02-8751-6665。
            return SsoStatusTypeGestures.FAST_LOGIN_FAIL_1.getStatusCode();
        }
        else {
            // 錯誤代碼+手勢密碼連續輸入錯誤兩次(連續錯誤三次將被鎖定)，若您已遺忘手勢密碼，可先用使用者密碼登入後至【個人服務>密碼設定/變更>行動銀行手勢密碼設定/變更】中取消手勢密碼，再重新進行手勢密碼設定，如有疑問，請洽客戶服務專線02-8751-6665。
            return SsoStatusTypeGestures.FAST_LOGIN_FAIL_2.getStatusCode();
        }
    }

    /**
     * 未登入時查詢 Email
     *
     * @param custId
     *            - ID
     * @param loginType
     *            0/1 一般會員/信用卡會員
     */
    private UnLoginEmailsResponse getUnLoginEmails(String custId, int loginType) {

        UnLoginEmailsResponse response = new UnLoginEmailsResponse();
        List<String> eMails = new ArrayList<String>();
        if (loginType == 0) {
            try {
                eMails = getUserEmailByEB67050(custId);
            }
            catch (ActionException | XmlException | EAIException | EAIResponseException e) {
                logger.error("查詢 Email EB67050 失敗", e);
            }
        }
        else {
            try {
                eMails = getUserEmailByEB032159(custId);
            }
            catch (XmlException | EAIException | EAIResponseException e) {
                logger.error("查詢 Email EB032159 失敗", e);
            }
        }
        response.setEmails(eMails);
        return response;
    }

    /**
     * 取得ID+誤別碼下所有 e-mail
     *
     * @param uid
     * @param idType
     * @return
     * @throws ActionException
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    private List<String> getUserEmailByEB67050(String uid) throws ActionException, XmlException, EAIException, EAIResponseException {
        String idType = BaNCSUtil.getIDTYPE(uid);

        EB067217RS eb067217rs = esbGateway.sendEB067217(uid, idType);

        List<String> emailList = new ArrayList<String>();
        for (TxRepeatType txRepeatType : eb067217rs.getBody().getTxRepeatList()) {
            EB067217RepeatType repeatType = (EB067217RepeatType) txRepeatType.changeType(EB067217RepeatType.type);
            String id = repeatType.getIDNO();
            if (StringUtils.isBlank(id)) {
                continue;
            }
            EB67050RS eb67050rs = esbGateway.sendEB67050(id, idType);

            emailList.add(eb67050rs.getBody().getEMAILADDR());
        }
        return emailList;
    }

    /**
     * 呼叫EB032159 取得 email
     *
     * @param uid
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    private List<String> getUserEmailByEB032159(String uid) throws XmlException, EAIException, EAIResponseException {
        EB032159RS rs = esbGateway.sendEB032159(uid);
        List<String> emailList = new ArrayList<String>();

        String email = StringUtils.trimToEmptyEx(rs.getBody().getEMAILADDR());
        logger.info("EB032159電文 email" + email);
        emailList.add(email);

        return emailList;
    }

    /**
     * 發查電文 EB172650 查詢客戶存款資訊
     * 
     * @param depositAcct
     *            存款帳號
     * @param curCode
     *            幣別
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB172650Res sendEB172650(String depositAcct, String curCode) throws XmlException, EAIException, EAIResponseException {

        EB172650RS rs = esbGateway.sendEB172650(depositAcct, curCode);

        EB172650SvcRsType rsBody = rs.getBody();

        // EB172650 電文下行 Body 有55個欄位，之後有需求者，自行擴充填入
        EB172650Res response = new EB172650Res();

        response.setIdno(StringUtils.right(rsBody.getIDNO(), 12)); // 取12位

        return response;
    }

}
