package com.tfb.aibank.chl.creditcard.tx004.task;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW316RRepeat;
import com.tfb.aibank.chl.creditcard.tx004.model.CreditCardData;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004010Rq;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004010Rs;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004Cache;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004Input;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004Output;
import com.tfb.aibank.chl.creditcard.tx004.service.NCCTX004Service;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@formatter:off
/**
* @(#)NCCTX004010Task.java
*
* <p>Description:NCCTX004_道路救援登錄 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/24
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCTX004010Task extends AbstractAIBankBaseTask<NCCTX004010Rq, NCCTX004010Rs> {
    @Autowired
    protected NCCTX004Service service;

    private NCCTX004Input input = new NCCTX004Input();

    private NCCTX004Output output = new NCCTX004Output();

    @Override
    public void validate(NCCTX004010Rq rqData) throws ActionException {
        // 若為信用卡特殊戶，引導至「共通錯誤頁」顯示錯誤訊息：本行目前無法查詢您的信用卡資料，如有疑問，請洽本行信用卡客服中心02-8751-1313。
        service.checkCreditCardStatus(getLoginUser());
    }

    @Override
    public void handle(NCCTX004010Rq rqData, NCCTX004010Rs rsData) throws ActionException {

        input.setLocale(getLocale());
        input.setCardKey(rqData.getCardKey());
        service.getCreditCardsAndRoadsideAssistStatus(getLoginUser(), input, output);

        NCCTX004Cache cache = getCache();
        cache.setCreditCardDatas(output.getCreditCardDatas());

        List<CreditCardData> rsCreditCardDatas = output.getCreditCardDatas().stream().map(this::copyCreditCardData).collect(Collectors.toList());

        rsCreditCardDatas.forEach( ccd -> {
            ccd.setCardNoMask(DataMaskUtil.maskCreditCardShowTail(ccd.getCardNo()));
            if(Objects.nonNull(ccd.getRoadsideAssistData())){
                ccd.getRoadsideAssistData().setCrdNo(null);
            }
            ccd.setCardNo(null);
        });
        rsData.setCreditCardDatas(rsCreditCardDatas);
        rsData.setPrimaryCardHolder(output.isPrimaryCardHolder());
        setCache(NCCTX004Cache.class.getCanonicalName(), cache);
        //log removed for fortify
    }

    private CreditCardData copyCreditCardData(CreditCardData sourceData){
        CreditCardData newData = new CreditCardData();

        newData.setCardNo(sourceData.getCardNo());
        newData.setCardNoMask(sourceData.getCardNoMask());
        newData.setCardType(sourceData.getCardType());
        newData.setCardName(sourceData.getCardName());
        newData.setCardCategory(sourceData.getCardCategory());
        newData.setCardNickName(sourceData.getCardNickName());   
        newData.setAdditional(sourceData.isAdditional());
        newData.setCardKey(sourceData.getCardKey());
        newData.setDesignateCard(sourceData.isDesignateCard());
        newData.setRegistered(sourceData.isRegistered());
        if(Objects.nonNull(sourceData.getRoadsideAssistData())){
            CEW316RRepeat newRepeat = new CEW316RRepeat();
            try {
                BeanUtils.copyProperties(newRepeat, sourceData.getRoadsideAssistData());
                newData.setRoadsideAssistData(newRepeat);
            } catch (IllegalAccessException e) {
                logger.warn("== copy CEW316RRepeat fail ==:", e);
            } catch (InvocationTargetException e) {
                logger.warn("== copy CEW316RRepeat fail ==:", e);
            }
        }
        return newData;
    }

    private NCCTX004Cache getCache() {
        NCCTX004Cache cache = getCache(NCCTX004Cache.class.getCanonicalName(), NCCTX004Cache.class);
        if (cache == null) {
            cache = new NCCTX004Cache();
        }
        return cache;
    }
}