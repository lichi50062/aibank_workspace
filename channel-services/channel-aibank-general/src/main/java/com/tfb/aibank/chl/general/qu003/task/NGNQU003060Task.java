package com.tfb.aibank.chl.general.qu003.task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.ibmb.component.errorcode.ErrorCodeCacheManager;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.component.homepagecard.HomepageCardCacheManager;
import com.tfb.aibank.chl.general.error.ErrorCode;
import com.tfb.aibank.chl.general.qu003.model.FinMgmMemberData;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003060Rq;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003060Rs;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003Output;
import com.tfb.aibank.chl.general.qu003.service.NGNQU003Service;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.component.dic.DicCacheManager;
import com.tfb.aibank.component.dic.DicData;

// @formatter:off
/**
 * @(#)NGNQU003060Task.java
 * 
 * <p>Description:優惠 060 我的權益頁</p>
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
public class NGNQU003060Task extends AbstractAIBankBaseTask<NGNQU003060Rq, NGNQU003060Rs> {

    @Autowired
    private NGNQU003Service service;

    @Autowired
    private ErrorCodeCacheManager errorCodeCacheManager;

    @Autowired
    protected HomepageCardCacheManager homepageCardCacheManager;

    @Autowired
    protected DicCacheManager dicCacheManager;
    
    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    NGNQU003Output output = new NGNQU003Output();

    @Override
    public void validate(NGNQU003060Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU003060Rq rqData, NGNQU003060Rs rsData) throws ActionException {

        if (getLoginUser().getCustomerType().equals(CustomerType.CARDMEMBER)) {
            this.prepareNoAccountInfo(rsData);
            return;
        }

        service.getTransOutAccounts(getLoginUser(), getLocale(), output);

        if (CollectionUtils.isEmpty(output.getTransOutAcctsTW()) && CollectionUtils.isEmpty(output.getTransOutAcctsFR())) {
            this.prepareNoAccountInfo(rsData);
            return;
        }

        FinMgmMemberData mainData = new FinMgmMemberData();
        mainData.setTransOutAcctsTW(output.getTransOutAcctsTW());
        mainData.setTransOutAcctsFR(output.getTransOutAcctsFR());
        mainData.setOutAccountNos();

        //發送電文查詢客戶{所屬會員等級}
        service.getFinancialMgmMemberLevel(getLoginUser(), mainData);

        //(4)	判斷是否為{薪轉會員} + 取得薪轉優惠資訊
        service.checkAndProcessSalaryAccountFavor(getLoginUser(), mainData);

        // (5)	依客戶轉出帳號逐筆發送電文判斷是否為{身障優惠帳號}
        service.checkDisabilityAccts(mainData);

        //(6)發送電文取得每月跨行優惠資訊，不論成功失敗均接續下一步驟
        service.checkCrossBankTxFavor(getLoginUser(), mainData);

        //(7)	發送電文取得每月銀行作業優惠資訊，不論成功失敗均接續下一步驟。
        service.checkBankOperationFavor(getLoginUser(), mainData);

        service.getServiceAdvisor(getLoginUser(), mainData, getLocale());

        rsData.setFinMgmMemberData(mainData);

        gatherDatas(rsData);
        
        service.checkHighContributeCust(getLoginUser(), output);
        
        rsData.setHighContributeCust(output.isHighContributeCust());
        
        String premiumReward_url = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "PREMIUM_REWARD_URL");
        rsData.setPremiumReward_url(premiumReward_url);

    }

    private void gatherDatas(NGNQU003060Rs rsData) {

        List<DicData> productTypeList = this.dicCacheManager.getDicListByCategory("PROMOTION");

        if (CollectionUtils.isNotEmpty(productTypeList)) {
            Map<String, String> dicValueMap = productTypeList.stream().collect(Collectors.toMap(DicData::getDicKey, DicData::getDicValue, (ax, bx) -> ax));
            if (getLocale().equals(Locale.US) && null != dicValueMap) {
                for (Map.Entry<String, String> entry : dicValueMap.entrySet()) {
                    if (entry.getKey().matches("^PROMOTION_.+_[6789]{1}$")) {
                        if (NumberUtils.isParsable(entry.getValue())) {
                            BigDecimal value = ConvertUtils.str2BigDecimal(entry.getValue());
                            BigDecimal one = new BigDecimal("1");
                            BigDecimal hundred = new BigDecimal("100");
                            BigDecimal ten = new BigDecimal("10");

                            value = value.divide(ten);
                            value = one.subtract(value);
                            value = hundred.multiply(value).setScale(0, BigDecimal.ROUND_HALF_UP);
                            entry.setValue(value + "%");
                        }
                    }
                }
            }
            rsData.setDicValueMap(dicValueMap);
        }

    }

    private void prepareNoAccountInfo(NGNQU003060Rs rsData) {
        IBUtils.ErrorDescription errorDesc = IBUtils.getErrorDescMessage(errorCodeCacheManager, ExceptionUtils.getActionException(ErrorCode.CC_USER_NO_ACCT).getStatus(), getLocale(), getFromPage());

        rsData.setErrorTitle(errorDesc.getTitle());
        rsData.setErrorDesc(errorDesc.getErrorDesc());

        Predicate<HomepageCard> getNDSQU006HomepageCard = (hc) -> "PROMOTION-NOACCT".equals(hc.getCardUsedTaskId()) && "TASK_CARD".equals(hc.getCardTemplate());

        List<HomepageCard> homepageCards = homepageCardCacheManager.getMulHomePageCardByPredicate(getNDSQU006HomepageCard, getLoginUser().getCustId());

        if (CollectionUtils.isNotEmpty(homepageCards)) {
            rsData.setHomepageCard(homepageCards.get(0));
        }
    }

}
