package com.tfb.aibank.chl.creditcard.ag002.task;

import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002CacheData;
import com.tfb.aibank.chl.creditcard.resource.dto.RecommendInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002010Rq;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002010Rs;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002DataOutput;
import com.tfb.aibank.chl.creditcard.ag002.service.NCCAG002Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW302RRes;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCAG002010Task.java
 * 
 * <p>Description:信用卡電子帳單設定 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG002010Task extends AbstractAIBankBaseTask<NCCAG002010Rq, NCCAG002010Rs> {

    @Autowired
    private UserDataCacheService userCacheService;

    @Autowired
    private NCCAG002Service service;

    private NCCAG002DataOutput dataOutput = new NCCAG002DataOutput();

    @Override
    public void validate(NCCAG002010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG002010Rq rqData, NCCAG002010Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        // 檢核信用卡特殊戶、或未持有信用
        userCacheService.validateCreditCardStatus(loginUser);

        // 檢核持卡狀況
        userCacheService.validatePrimaryCardholderOnly(loginUser, getLocale());

        try {
            // 發送信用卡卡片總覽電文
            service.sendCEW302R(loginUser.getCustId(), dataOutput);
        }
        catch (ServiceException | ActionException e) {
            logger.error(e.getMessage(), e);
            rsData.setQueryfail(true);
        }

        // 有推薦碼才做
        RecommendInfo recommendInfo = null;
        if(StringUtils.isNotBlank(rqData.getUrlCode())){
            recommendInfo = service.getShortenerParams(rqData.getUrlCode());
        }


        CEW302RRes res = dataOutput.getCew302RResponse();
        if (res != null) {
            rsData.setEmail(DataMaskUtil.maskEmail(res.getEmailAddress()));
            rsData.setApplyElectronicBill(StringUtils.isY(res.getApplyEmail()));
            rsData.setApplyPhysicalBill(StringUtils.isY(res.getApplyBill()));
        }

        NCCAG002CacheData cacheData = new NCCAG002CacheData();
        cacheData.setRecommendInfo(recommendInfo);
        super.setCache(NCCAG002Service.CACHE_KEY, cacheData);
    }
}
