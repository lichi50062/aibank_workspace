package com.tfb.aibank.biz.systemconfig.services.systemparam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.systemconfig.repository.SystemParamRepository;
import com.tfb.aibank.biz.systemconfig.repository.entities.SystemParamEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.pk.SystemParamEntityPk;
import com.tfb.aibank.biz.systemconfig.services.systemparam.model.SystemParamModel;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)SystemParamService.java
 * 
 * <p>Description:處理「系統參數檔」相關行為</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/23, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SystemParamService {

    private static final IBLog logger = IBLog.getLog(SystemParamService.class);

    private SystemParamRepository systemParamRepository;

    private AESCipherUtils aesCipherUtils;

    public SystemParamService(SystemParamRepository systemParamRepository) {
        this.systemParamRepository = systemParamRepository;
    }

    /**
     * 取得 SYSTEM_PARAM 所有的資料
     * 
     * @return
     */
    public List<SystemParamModel> getAllData() {
        List<SystemParamModel> modelList = this.systemParamRepository.findAll(Sort.by(Direction.ASC, "category", "paramKey")).stream().map(this::convertSystemParam).collect(Collectors.toList());
        // 針對 PASSWORD_FLAG = 1 的紀錄，進行解密動作
        if (modelList.stream().anyMatch(x -> x.getPasswordFlag() == 1)) {
            for (SystemParamModel model : modelList) {
                if (model.getPasswordFlag() == 1) {
                    model.setParamValue(decrypt(model.getParamValue()));
                }
            }
        }
        return modelList;
    }

    private SystemParamModel convertSystemParam(SystemParamEntity entity) {
        SystemParamModel model = new SystemParamModel();
        model.setCategory(entity.getCategory());
        model.setMemo(entity.getMemo());
        model.setParamKey(entity.getParamKey());
        model.setParamValue(entity.getParamValue());
        model.setPasswordFlag(entity.getPasswordFlag());
        return model;
    }

    public SystemParamModel updateParam(SystemParamModel param) {
        SystemParamEntityPk pk = new SystemParamEntityPk();
        pk.setCategory(param.getCategory());
        pk.setParamKey(param.getParamKey());
        // 【FORTIFY：Access Control: Database】誤判，條件為主鍵
        Optional<SystemParamEntity> entityOpt = this.systemParamRepository.findById(pk);
        if (entityOpt.isPresent()) {
            SystemParamEntity e = entityOpt.get();
            e.setParamValue(param.getParamValue());
            e.setMemo(param.getMemo());
            e.setPasswordFlag(param.getPasswordFlag());
            this.systemParamRepository.saveAndFlush(e);
            return param;
        }
        return null;
    }

    /**
     * 對資料進行解密
     * 
     * @param cipherText
     * @return
     */
    public String decrypt(String cipherText) {
        if (this.aesCipherUtils == null) {
            SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
            aesCipherUtils = new AESCipherUtils(provider);
        }
        String plainText = StringUtils.EMPTY;
        try {
            plainText = aesCipherUtils.decrypt(cipherText);
        }
        catch (CryptException e) {
            logger.error(String.format("error decrypt %s", cipherText), e);
        }
        return plainText;
    }

    /**
     * 依指定條件查詢
     * 
     * @param category
     * @param paramKey
     * @return
     */
    public SystemParamModel findByCategoryAndParamKey(String category, String paramKey) {
        // fortify: Access Control - High
        category = ValidateParamUtils.validParam(category);
        paramKey = ValidateParamUtils.validParam(paramKey);
        SystemParamEntityPk pk = new SystemParamEntityPk(category, paramKey);
        Optional<SystemParamEntity> opt = systemParamRepository.findById(pk);
        if (opt.isPresent()) {
            return convertSystemParam(opt.get());
        }
        return null;
    }

    /**
     * 依指定條件查詢
     * 
     * @param category
     * @return
     */
    public List<SystemParamModel> findByCategory(String category) {
        List<SystemParamEntity> entities = systemParamRepository.findByCategory(category);
        return entities.stream().map(this::convertSystemParam).collect(Collectors.toList());
    }

}
