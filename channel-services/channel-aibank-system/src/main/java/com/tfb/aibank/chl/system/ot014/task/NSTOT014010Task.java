package com.tfb.aibank.chl.system.ot014.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.system.ot014.model.NSTOT014010Rq;
import com.tfb.aibank.chl.system.ot014.model.NSTOT014010Rs;
import com.tfb.aibank.chl.system.ot014.model.NSTOT014Output;
import com.tfb.aibank.chl.system.ot014.service.NSTOT014Service;

// @formatter:off
/**
 * @(#)NSTOT014010Task.java
 * 
 * <p>Description:投資風險偏好輔助說明 010 投資風險偏好輔助說明 010</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT014010Task extends AbstractAIBankBaseTask<NSTOT014010Rq, NSTOT014010Rs> {

    @Autowired
    private NSTOT014Service service;
    @Autowired
    protected UserDataCacheService userDataCacheService;

    private final NSTOT014Output output = new NSTOT014Output();

    @Override
    public void validate(NSTOT014010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT014010Rq rqData, NSTOT014010Rs rsData) throws ActionException {
        //取得投資風險資訊
        service.getInvestRisk(getLoginUser(), getLocale(), output);
        //取得冷靜期
        service.getCoolPeriod(getLoginUser(), output);

        // 是否為非特定客戶之高資產客戶 0:否 1:是
        if (userDataCacheService.isHighWealthClient(getLoginUser())) {
            rsData.setHighAsset(1);
        }
        else {
            rsData.setHighAsset(0);
        }
        rsData.setRisk(output.getEbillCheck());
        rsData.setRiskTitle(output.getKycRiskLevelModel().getRiskName() + " " + output.getEbillCheck());
        rsData.setRiskDesc(output.getKycRiskLevelModel().getRiskDescription());
        rsData.setEffectiveDate(DateUtils.getDateStr(output.getEbillEndDate(), "/"));
        rsData.setTestDate(DateUtils.getDateStr(output.getEbillStartDate(), "/"));

        // 0:可以測試 1:今日已測試 2:已達7日3次上限 3:是否有待覆核 KYC
        if (StringUtils.isNotEmpty(output.getKycCountsResponse().getKycCounts()) && StringUtils.isNotEmpty(output.getKycCountsResponse().getKycWeekCounts())) {
            int todayCount = Integer.parseInt(output.getKycCountsResponse().getKycCounts());
            int weekCount = Integer.parseInt(output.getKycCountsResponse().getKycWeekCounts());
            String kycIsInReviewStatus = output.getKycCountsResponse().getIsInReviewStatus();
			if (todayCount >= output.getDaliyLimit()) {
				rsData.setCounts(1);
			} else if (weekCount >= output.getWeekLimit()) {
				rsData.setCounts(2);
			} else if (StringUtils.equals("Y", kycIsInReviewStatus)) {
				rsData.setCounts(3);
			} else {
				rsData.setCounts(0);
			}
        }
        if (StringUtils.isNotEmpty(output.getCoolingPeriodResponse().getCoolingPeriod())) {
            rsData.setCoolingDate(DateUtils.getDateStr(DateUtils.getSimpleDate(output.getCoolingPeriodResponse().getCoolingPeriod()), "/"));
        }

    }

}
