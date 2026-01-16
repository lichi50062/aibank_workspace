package com.tfb.aibank.biz.user.services.financialdataorder;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.FinancialDataOrderRepository;
import com.tfb.aibank.biz.user.repository.entities.FinancialDataOrderEntity;
import com.tfb.aibank.biz.user.services.financialdataorder.model.FinancialDataOrderModel;

//@formatter:off
/**
* @(#)FinancialDataOrderService.java
* 
* <p>Description:CHL FinancialDataOrderService 提供「投資商品排序」相關服務</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/14, Leiley
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class FinancialDataOrderService {

    private static final IBLog logger = IBLog.getLog(FinancialDataOrderService.class);

    private IdentityService identityService;

    private FinancialDataOrderRepository financialDataOrderRepository;

    public FinancialDataOrderService(IdentityService identityService, FinancialDataOrderRepository financialDataOrderRepository) {
        this.identityService = identityService;
        this.financialDataOrderRepository = financialDataOrderRepository;
    }

    public List<FinancialDataOrderModel> findByUser(String custId, String userId, String uidDup, Integer companyKind, String taskId) throws ActionException {
        // 取得使用者資訊
        IdentityData identityData = getUser(custId, userId, uidDup, companyKind);
        return financialDataOrderRepository.findByCompanyKeyAndUserKeyAndTaskId(identityData.getCompanyKey(), identityData.getUserKey(), taskId).stream().map(this::convertToModel).toList();
    }

    /**
     * 更新資料
     * 
     * @param dataOrder
     * @param custId
     * @param userId
     * @param companyKind
     * @return
     * @throws Exception
     */
    public FinancialDataOrderModel saveData(FinancialDataOrderModel dataOrder, String custId, String userId, int companyKind, String uidDup) throws ActionException {

        IdentityData identityData = getUser(custId, userId, uidDup, companyKind);
        dataOrder.setCompanyKxy(identityData.getCompanyKey());
        dataOrder.setUserKxy(identityData.getUserKey());

        FinancialDataOrderEntity entity = this.convertToEntity(dataOrder);

        // 【FORTIFY：Access Control: Database】COMPANY_KEY、USER_KEY 就是身分識別屬性欄位
        List<FinancialDataOrderEntity> queryResults = financialDataOrderRepository.findByCompanyKeyAndUserKeyAndTaskId(entity.getCompanyKey(), entity.getUserKey(), ValidateParamUtils.validParam(entity.getTaskId()));
        if (CollectionUtils.isNotEmpty(queryResults)) {
            financialDataOrderRepository.delete(queryResults.get(0));
        }
        entity = financialDataOrderRepository.save(entity);

        return this.convertToModel(entity);
    }

    /**
     * 取得使用者資料
     *
     * @param custId
     * @param userId
     * @param companyKind
     * @return
     * @throws ActionException
     */
    private IdentityData getUser(String custId, String userId, String uidDup, int companyKind) throws ActionException {
        try {
            IdentityData identityData = identityService.query(custId, uidDup, userId, companyKind);
            if (identityData != null && identityData.isAliveUser()) {
                return identityData;
            }
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

    private FinancialDataOrderEntity convertToEntity(FinancialDataOrderModel model) {
        FinancialDataOrderEntity ent = new FinancialDataOrderEntity();
        ent.setCompanyKey(model.getCompanyKxy());
        ent.setUserKey(model.getUserKxy());
        ent.setTaskId(model.getTaskId());
        ent.setDataOrder(model.getDataOrder());
        ent.setCreateTime(model.getCreateTime() == null ? new Date() : model.getCreateTime());
        return ent;
    }

    private FinancialDataOrderModel convertToModel(FinancialDataOrderEntity entity) {
        FinancialDataOrderModel model = new FinancialDataOrderModel();
        model.setCompanyKxy(entity.getCompanyKey());
        model.setUserKxy(entity.getUserKey());
        model.setTaskId(entity.getTaskId());
        model.setDataOrder(entity.getDataOrder());
        model.setCreateTime(entity.getCreateTime());
        return model;
    }
}
