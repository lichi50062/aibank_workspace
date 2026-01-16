package com.tfb.aibank.chl.creditcard.qu006.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu006.model.NCCQU006010Rq;
import com.tfb.aibank.chl.creditcard.qu006.model.NCCQU006010Rs;
import com.tfb.aibank.chl.creditcard.qu006.model.NCCQU006010RsBonRepeats;
import com.tfb.aibank.chl.creditcard.qu006.service.NCCQU006Service;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW306RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW306RResponse;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;

//@formatter:off
/**
* @(#)NCCQU006010Task.java
*
* <p>Description:點數回饋中心 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/08/10 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU006010Task extends AbstractAIBankBaseTask<NCCQU006010Rq, NCCQU006010Rs> {
    @Autowired
    protected NCCQU006Service service;

    @Autowired
    private CreditCardResource creditCardResource;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NCCQU006010Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NCCQU006010Rq rqData, NCCQU006010Rs rsData) throws ActionException {

        AIBankUser aibankUser = getLoginUser();

        /** 是否為特殊戶或未有信用卡 */
        userDataCacheService.validateCreditCardStatus(getLoginUser());

        /** 未持有信用卡 */
        userDataCacheService.getAllCreditCards(aibankUser, getLocale());

        /** 附卡人引導至「共通錯誤頁」 */
        if (!userDataCacheService.isPrimaryCard(aibankUser, getLocale())) {
            throw new ActionException(ErrorCode.PRIMARY_CARDHOLDER_ONLY);
        }

        BaseServiceResponse<CEW306RResponse> cew306RResponse = null;
        /** 查詢信用卡紅利積點 */
        try {
            cew306RResponse = creditCardResource.sendCEW306R(aibankUser.getCustId());
        }
        catch (ServiceException ex) {
            logger.error("CEW306R 查詢失敗", ex);
            rsData.setIsReload(1);
            return;
        }

        String costoCampaignUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "COSTCO_CAMPAIGN_URL");
        rsData.setCostoCampaignUrl(costoCampaignUrl);

        String bonusCampaignUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "BONUS_CAMPAIGN_URL");
        rsData.setBonusCampaignUrl(bonusCampaignUrl);

        String milesCampaignUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "MILES_CAMPAIGN_URL");
        rsData.setMilesCampaignUrl(milesCampaignUrl);

        String howardCampaignUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "HOWARD_CAMPAIGN_URL");
        rsData.setHowardCampaignUrl(howardCampaignUrl);

        /** 好多金 */
        boolean isFoundC001 = false;
        /** 紅利 */
        boolean isFound9000 = false;
        boolean isFound0000 = false;
        /** 哩程 */
        boolean isFound0040 = false;
        /** 福華點 */
        boolean isFound0008 = false;

        NCCQU006010RsBonRepeats bonC001 = null;
        NCCQU006010RsBonRepeats bon9000 = null;
        NCCQU006010RsBonRepeats bon0040 = null;
        NCCQU006010RsBonRepeats bon0008 = null;
        NCCQU006010RsBonRepeats bon0000 = null;

        List<NCCQU006010RsBonRepeats> bonData = new ArrayList<NCCQU006010RsBonRepeats>();
        if (cew306RResponse != null && cew306RResponse.getData() != null && cew306RResponse.getData().getTxRepeats() != null) {
            for (CEW306RRepeat m : cew306RResponse.getData().getTxRepeats()) {
                NCCQU006010RsBonRepeats one = new NCCQU006010RsBonRepeats();

                /** 紅利認同碼 X(4) */
                one.setBonGrop(m.getBonGrop());

                /** 紅利點數 X(9) 含 紅利正負號 X(1) */
                if ("-".equals(m.getBonsing())) {
                    one.setBonBsav("-" + IBUtils.formatMoney(m.getBonBsav()));
                    one.setBonBsavOriginal(m.getBonBsav().multiply(new BigDecimal(-1)));
                }
                else {
                    one.setBonBsav(IBUtils.formatMoney(m.getBonBsav()));
                    one.setBonBsavOriginal(m.getBonBsav());
                }

                /** 最近到期點數 X(9) */
                one.setBonEamt(IBUtils.formatMoney(m.getBonEamt()));

                /** 到期日 X(7) */
                if (StringUtils.isNotBlank(m.getBonEday())) {
                    Date date = DateUtils.getROCDate(m.getBonEday(), StringUtils.EMPTY);
                    if (date != null)
                        one.setBonEday(DateFormatUtils.CE_DATE_FORMAT.format(date));
                    else
                        one.setBonEday(StringUtils.EMPTY);
                }
                else {
                    one.setBonEday(StringUtils.EMPTY);
                }

                if ("C001".equals(one.getBonGrop())) {
                    isFoundC001 = true;
                    bonC001 = one;
                }
                if ("9000".equals(one.getBonGrop())) {
                    isFound9000 = true;
                    bon9000 = one;
                }
                if ("0000".equals(one.getBonGrop())) {
                    isFound0000 = true;
                    bon0000 = one;
                }
                if ("0040".equals(one.getBonGrop())) {
                    isFound0040 = true;
                    bon0040 = one;
                }
                if ("0008".equals(one.getBonGrop())) {
                    isFound0008 = true;
                    bon0008 = one;
                }
            }
        }

        if (isFoundC001) {
            bonData.add(bonC001);
        }
        else {
            NCCQU006010RsBonRepeats one = new NCCQU006010RsBonRepeats();
            one.setBonGrop("C001");
            one.setBonBsav("0");
            bonData.add(one);
        }

        // fortify: null deference
        if (isFound9000 && bon9000 != null) {
            if (isFound0000 && bon0000 != null) {
                rsData.setUnlimitPoint(IBUtils.formatMoney(bon9000.getBonBsavOriginal()));
                bon9000.setBonBsav(IBUtils.formatMoney(bon9000.getBonBsavOriginal().add(bon0000.getBonBsavOriginal())));
                bon9000.setBonEamt(bon0000.getBonEamt());
                bon9000.setBonEday(bon0000.getBonEday());

                rsData.setRedPoint(bon0000.getBonBsav());
                rsData.setEndPoint(bon0000.getBonEamt());
                rsData.setEndDate(bon0000.getBonEday());
            }
            else {
                if (bon9000 != null) {
                    bon9000.setBonEamt("");
                    bon9000.setBonEday("");
                    rsData.setUnlimitPoint(bon9000.getBonBsav());
                }
                rsData.setRedPoint("0");
            }

            bonData.add(bon9000);

        }
        else {
            rsData.setUnlimitPoint("0");
            if (isFound0000 && bon0000 != null) {
                bonData.add(bon0000);

                rsData.setRedPoint(bon0000.getBonBsav());
                rsData.setEndPoint(bon0000.getBonEamt());
                rsData.setEndDate(bon0000.getBonEday());
            }
            else {
                NCCQU006010RsBonRepeats one = new NCCQU006010RsBonRepeats();
                one.setBonGrop("9000");
                one.setBonBsav("0");
                bonData.add(one);
            }
        }

        if (isFound0040) {
            bonData.add(bon0040);
        }
        else {
            NCCQU006010RsBonRepeats one = new NCCQU006010RsBonRepeats();
            one.setBonGrop("0040");
            one.setBonBsav("0");
            bonData.add(one);
        }

        if (isFound0008) {
            bonData.add(bon0008);
        }
        else {
            NCCQU006010RsBonRepeats one = new NCCQU006010RsBonRepeats();
            one.setBonGrop("0008");
            one.setBonBsav("0");
            bonData.add(one);
        }

        rsData.setBonData(bonData);
    }

}