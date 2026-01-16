package com.tfb.aibank.biz.user.services.accountsecurity;

import java.util.List;
import java.util.stream.Collectors;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.ChangePasswordRecordRepository;
import com.tfb.aibank.biz.user.repository.ChangePasswordTipsRepository;
import com.tfb.aibank.biz.user.repository.entities.ChangePasswordRecordEntity;
import com.tfb.aibank.biz.user.repository.entities.ChangePasswordTipsEntity;
import com.tfb.aibank.biz.user.services.accountsecurity.model.ChangePasswordRecordModel;
import com.tfb.aibank.biz.user.services.accountsecurity.model.ChangePasswordTipsModel;
import com.tfb.aibank.common.util.BizExceptionUtils;

// @formatter:off
/**
 * @(#)AccountSecurityService.java
 * 
 * <p>Description:處理「帳戶安全」相關服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AccountSecurityService {

    private IdentityService identityService;

    private ChangePasswordTipsRepository changePasswordTipsRepository;

    private ChangePasswordRecordRepository changePasswordRecordRepository;

    public AccountSecurityService(IdentityService identityService, ChangePasswordTipsRepository changePasswordTipsRepository, ChangePasswordRecordRepository changePasswordRecordRepository) {
        this.identityService = identityService;
        this.changePasswordTipsRepository = changePasswordTipsRepository;
        this.changePasswordRecordRepository = changePasswordRecordRepository;
    }

    /**
     * 取得 CHANGE_PASSWORD_TIPS
     * 
     * @param custId
     * @param uidDup
     * @param userId
     * @param companyKind
     * @return
     * @throws Exception
     */
    public List<ChangePasswordTipsModel> getChangePasswordTips(String custId, String uidDup, String userId, Integer companyKind) throws ActionException {
        IdentityData identityData;
        try {
            identityData = identityService.query(custId, uidDup, userId, companyKind);
        }
        catch (CryptException e) {
            throw BizExceptionUtils.getActionException(e);
        }
        catch (Exception e) {
            throw BizExceptionUtils.getBizActionException(e);
        }
        Integer companyKey = identityData.getCompanyKey();
        Integer userKey = identityData.getUserKey();
        List<ChangePasswordTipsEntity> entities = changePasswordTipsRepository.findByCompanyKeyAndUserKey(companyKey, userKey);
        return entities.stream().map(this::convertChangePasswordTips).collect(Collectors.toList());
    }

    private ChangePasswordTipsModel convertChangePasswordTips(ChangePasswordTipsEntity entity) {
        ChangePasswordTipsModel model = new ChangePasswordTipsModel();
        model.setMasterKey(entity.getMasterKey());
        model.setUserKey(entity.getUserKey());
        model.setCompanyKey(entity.getCompanyKey());
        model.setCreateTime(entity.getCreateTime());
        model.setTxDate(entity.getTxDate());
        return model;
    }

    /**
     * 取得 CHANGE_PASSWORD_RECORD
     * 
     * @param custId
     * @param uidDup
     * @param userId
     * @param companyKind
     * @return
     * @throws Exception
     */
    public List<ChangePasswordRecordModel> getChangePasswordRecord(String custId, String uidDup, String userId, Integer companyKind) throws CryptException {
        IdentityData identityData = identityService.query(custId, uidDup, userId, companyKind);
        Integer companyKey = identityData.getCompanyKey();
        Integer userKey = identityData.getUserKey();
        List<ChangePasswordRecordEntity> entities = changePasswordRecordRepository.findByCompanyKeyAndUserKey(companyKey, userKey);
        return entities.stream().map(this::convertChangePasswordRecord).collect(Collectors.toList());
    }

    private ChangePasswordRecordModel convertChangePasswordRecord(ChangePasswordRecordEntity entity) {
        ChangePasswordRecordModel model = new ChangePasswordRecordModel();
        // 交易存取記錄鍵值
        model.setAccessLogKey(entity.getAccessLogKey());
        // 變更項目 0：網銀密碼 1：網銀使用者代碼 2：網銀使用者代碼與網銀密碼 3：重設語音密碼 4：臨櫃提款密碼
        model.setChangeItem(entity.getChangeItem());
        // 公司鍵值
        model.setCompanyKey(entity.getCompanyKey());
        // 建立時間
        model.setCreateTime(entity.getCreateTime());
        // 上送主機交易時間
        model.setHostTxTime(entity.getHostTxTime());
        // 用戶代碼
        model.setNameCode(entity.getNameCode());
        // 晶片金融卡密碼變更使用
        model.setOutAccount(entity.getOutAccount());
        // 晶片金融卡密碼變更使用
        model.setOutBank(entity.getOutBank());
        // 資料鍵值
        model.setRecordKey(entity.getRecordKey());
        // 交易日期
        model.setTxDate(entity.getTxDate());
        // 交易錯誤代碼
        model.setTxErrorCode(entity.getTxErrorCode());
        // 交易錯誤訊息
        model.setTxErrorDesc(entity.getTxErrorDesc());
        // 錯誤系統代碼
        model.setTxErrorSystemId(entity.getTxErrorSystemId());
        // 交易狀態 0：交易成功 1：交易失敗 4：交易未明
        model.setTxStatus(entity.getTxStatus());
        // 更新時間
        model.setUpdateTime(entity.getUpdateTime());
        // 使用者代碼
        model.setUserId(entity.getUserId());
        // 使用者鍵值
        model.setUserKey(entity.getUserKey());
        // 客戶IP
        model.setClientIp(entity.getClientIp());
        // 交易來源
        model.setTxSource(entity.getTxSource());

        return model;
    }
}
