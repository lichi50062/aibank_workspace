package com.tfb.aibank.chl.general.qu003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003010Rq;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003010Rs;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003Output;
import com.tfb.aibank.chl.general.qu003.service.NGNQU003Service;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.type.CustomerType;

// @formatter:off
/**
 * @(#)NGNQU003010Task.java
 * 
 * <p>Description:優惠 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNQU003010Task extends AbstractAIBankBaseTask<NGNQU003010Rq, NGNQU003010Rs> {

    @Autowired
    private NGNQU003Service service;

    @Override
    public void validate(NGNQU003010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU003010Rq rqData, NGNQU003010Rs rsData) throws ActionException {
        AIBankUser loginUser = getLoginUser();
        NGNQU003Output output = new NGNQU003Output();
        service.findPromotionProduct(loginUser, getLocale(), output);

        rsData.setPromotions(output.getPromotions());
        rsData.setPromotionCategories(output.getPromotionCategories());
        rsData.setBanners(output.getBanners());

        if (null != loginUser) {
            rsData.setCcMemberLogin(CustomerType.CARDMEMBER.equals(loginUser.getCustomerType()));

            if (!rsData.isCcMemberLogin()) {
                service.checkMissonStatus(loginUser, getLocale(), output);

                rsData.setMissionActivityQualification(output.getMissionActivityQualification());
                rsData.setMissionBannerLevel(output.getMissionBannerLevel());
                rsData.setMissionCompleteCount(output.getMissionCompleteCount());
                rsData.setMissionRate(output.getMissionRate());
            }
            else {
                rsData.setMissionActivityQualification(StringUtils.Y);
                rsData.setMissionBannerLevel("B");
            }
        }
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
