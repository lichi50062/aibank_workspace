package com.tfb.aibank.chl.general.qu003.task;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003050Rq;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003050Rs;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003050RsData;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003Output;
import com.tfb.aibank.chl.general.qu003.service.NGNQU003Service;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.CreditCardIdType;

// @formatter:off
/**
 * @(#)NGNQU003050Task.java
 * 
 * <p>Description:優惠 050 點數兌換頁</p>
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
public class NGNQU003050Task extends AbstractAIBankBaseTask<NGNQU003050Rq, NGNQU003050Rs> {

    @Autowired
    private NGNQU003Service service;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Override
    public void validate(NGNQU003050Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU003050Rq rqData, NGNQU003050Rs rsData) throws ActionException {
        NGNQU003Output output = new NGNQU003Output();
        boolean showPersonalPoint = false;
        AIBankUser loginUser = getLoginUser();
        if (loginUser != null) {
            // 取得信用卡戶況、信用卡持有註記，參考【共通業務辦法】-信用卡交易處理原則-信用卡戶況及註記
            service.checkShowPersonalPointData(loginUser, output);
            showPersonalPoint = output.isShowPersonalPointData();
            // AI Bank User取得信用卡身分別，參考【共通業務辦法】-信用卡交易處理原則-信用卡身分別。

            CreditCardIdType creditCardIdType = userDataCacheService.getCreditCardIdType(loginUser, getLocale());

            // 正卡人才顯示
            showPersonalPoint = showPersonalPoint && loginUser.getCreditCardIdType() != null && creditCardIdType.isPrimaryCard();

            if (Objects.equals(4, loginUser.getCompanyKind())) {
                //如果登入時的資料CompanyKind == 4 表示是附卡人，附卡人不需要顯示點數
                showPersonalPoint = false;
            }

            // 查詢點數餘額 若電文失敗或逾時，則不顯示個人點數資料
            if (showPersonalPoint) {
                service.getBonusPoint(loginUser.getCustId(), output);
                NGNQU003050RsData bonus = new NGNQU003050RsData();
                service.getPoint(bonus, Arrays.asList("9000", "0000"), output);
                NGNQU003050RsData mileage = new NGNQU003050RsData();
                service.getPoint(mileage, List.of("0040"), output);
                NGNQU003050RsData fuhua = new NGNQU003050RsData();
                service.getPoint(fuhua, List.of("0008"), output);

                rsData.setBonus(bonus);
                rsData.setMileage(mileage);
                rsData.setFuhua(fuhua);
            }
        }

        // 取得紅利商品資料
        service.getRewardProducts(output);
        rsData.setBonusPoints(output.getBonusPoints());
        rsData.setMileagePoints(output.getMileagePoints());
        rsData.setFuhuaPoints(output.getFuhuaPoints());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
