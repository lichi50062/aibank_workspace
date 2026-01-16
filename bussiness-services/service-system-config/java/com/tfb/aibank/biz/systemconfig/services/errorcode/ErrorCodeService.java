package com.tfb.aibank.biz.systemconfig.services.errorcode;

import java.util.List;
import java.util.stream.Collectors;

import com.tfb.aibank.biz.systemconfig.repository.ErrorCodeRepository;
import com.tfb.aibank.biz.systemconfig.repository.ErrorInfoRepository;
import com.tfb.aibank.biz.systemconfig.repository.TxSystemMapRepository;
import com.tfb.aibank.biz.systemconfig.repository.entities.ErrorCodeEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.ErrorInfoEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.TxSystemMapEntity;
import com.tfb.aibank.biz.systemconfig.services.errorcode.model.ErrorCodeModel;
import com.tfb.aibank.biz.systemconfig.services.errorcode.model.ErrorInfoModel;
import com.tfb.aibank.biz.systemconfig.services.errorcode.model.TxSystemMapModel;

// @formatter:off
/**
 * @(#)ErrorCodeService.java
 * 
 * <p>Description:提供「錯誤代碼」相關服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ErrorCodeService {

    private ErrorCodeRepository errorCodeRepository;

    private ErrorInfoRepository errorInfoRepository;

    private TxSystemMapRepository txSystemMapRepository;

    /**
     * 預設 constructor
     */
    public ErrorCodeService(ErrorCodeRepository errorCodeRepository, ErrorInfoRepository errorInfoRepository, TxSystemMapRepository txSystemMapRepository) {
        this.errorCodeRepository = errorCodeRepository;
        this.errorInfoRepository = errorInfoRepository;
        this.txSystemMapRepository = txSystemMapRepository;
    }

    /**
     * 依 errorNodeList and locale 查詢 ErrorCode 資料
     * 
     * @return
     */
    public List<ErrorCodeModel> findBySystemIdAndLocale(List<String> systemIdList, List<String> localeList) {
        List<ErrorCodeEntity> entities = this.errorCodeRepository.findBySystemIdAndLocale(systemIdList.toArray(new String[0]), localeList.toArray(new String[0]));
        return entities.stream().map(this::buildErrorCodeModel).collect(Collectors.toList());
    }

    /**
     * 讀取全部的 ERROR_CODE
     * 
     * @return
     */
    public List<ErrorCodeModel> getAllErrorCode() {
        List<ErrorCodeEntity> entities = this.errorCodeRepository.findAll();
        return entities.stream().map(this::buildErrorCodeModel).collect(Collectors.toList());
    }

    /**
     * 讀取全部的 ERROR_INFO
     * 
     * @return
     */
    public List<ErrorInfoModel> getAllErrorInfo() {
        List<ErrorInfoEntity> entities = this.errorInfoRepository.findAll();
        return entities.stream().map(this::buildErrorInfoModel).collect(Collectors.toList());
    }

    /**
     * 讀取全部的 TX_SYSTEM_MAP
     * 
     * @return
     */
    public List<TxSystemMapModel> getAllTxSystemMap() {
        List<TxSystemMapEntity> entities = this.txSystemMapRepository.findAll();
        return entities.stream().map(this::buildTxSystemMapModel).collect(Collectors.toList());
    }

    private ErrorCodeModel buildErrorCodeModel(ErrorCodeEntity entity) {
        ErrorCodeModel model = new ErrorCodeModel();
        model.setSystemId(entity.getSystemId());
        model.setErrorCode(entity.getErrorCode());
        model.setPageId(entity.getPageId());
        model.setLocale(entity.getLocale());
        model.setSeverity(entity.getSeverity());
        model.setErrorFlag(entity.getErrorFlag());
        model.setTitle(entity.getTitle());
        model.setInternalDesc(entity.getInternalDesc());
        model.setExternalDesc(entity.getExternalDesc());
        model.setDisplayFlag(entity.getDisplayFlag());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUserKey(entity.getUpdateUserKey());
        return model;
    }

    private ErrorInfoModel buildErrorInfoModel(ErrorInfoEntity entity) {
        ErrorInfoModel model = new ErrorInfoModel();
        model.setSystemId(entity.getSystemId());
        model.setErrorCode(entity.getErrorCode());
        model.setPageId(entity.getPageId());
        model.setLocale(entity.getLocale());
        model.setInfo(entity.getInfo());
        model.setDirectButtonName1(entity.getDirectButtonName1());
        model.setDirectTaskId1(entity.getDirectTaskId1());
        model.setDirectButtonName2(entity.getDirectButtonName2());
        model.setDirectTaskId2(entity.getDirectTaskId2());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());
        model.setUpdateUserKey(entity.getUpdateUserKey());
        return model;
    }

    private TxSystemMapModel buildTxSystemMapModel(TxSystemMapEntity entity) {
        TxSystemMapModel model = new TxSystemMapModel();
        model.setTxId(entity.getTxId());
        model.setSrcId(entity.getSrcId());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());
        return model;
    }
}
