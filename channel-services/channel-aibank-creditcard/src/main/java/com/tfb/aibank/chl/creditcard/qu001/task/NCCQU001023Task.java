package com.tfb.aibank.chl.creditcard.qu001.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.cardpaytype.CardPaytype;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001PayType;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001023Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001023Rs;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW329RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW329RResponse;

//@formatter:off
/**
* @(#)NCCQU001023Task.java
* 
* <p>Description:信用卡總覽 卡片管理頁 重新取得信用卡綁定行動支付類型資料</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU001023Task extends AbstractAIBankBaseTask<NCCQU001023Rq, NCCQU001023Rs> {
    @Autowired
    private NCCQU001Utils utils;
    @Autowired
    private UserDataCacheService cardService;

    @Override
    public void validate(NCCQU001023Rq rqData) throws ActionException {
        logger.debug("NCCQU001023 validate....");
    }

    @Override
    public void handle(NCCQU001023Rq rqData, NCCQU001023Rs rsData) throws ActionException {
        String custId = getLoginUser().getCustId();
        try {
            CEW329RResponse response = utils.sendCEW329R(custId);
            List<CEW329RRepeat> cew329rRepeats = response.getTxRepeats();
            List<NCCQQU001PayType> payTypes = new ArrayList<>();
            for (CEW329RRepeat cew329rRepeat : cew329rRepeats) {
                NCCQQU001PayType payType = new NCCQQU001PayType();
                CardPaytype cardPayType = cardService.getCardPaytype(cew329rRepeat.getHcttyp(), getLocale());
                payType.setHcttyp(StringUtils.defaultString(cardPayType.getPayName(), cew329rRepeat.getHcttyp()));
                payType.setOrderNo(cardPayType.getOrderNo());
                payType.setHchcen(cew329rRepeat.getHchcen());
                payTypes.add(payType);
            }
            Comparator<NCCQQU001PayType> comparator = Comparator.comparing(NCCQQU001PayType::getOrderNo);
            payTypes = payTypes.stream().sorted(comparator).collect(Collectors.toList());
            rsData.setPayTypes(payTypes);
        }
        catch (ServiceException e) {
            logger.warn("取得信用卡綁定行動支付類型 sendCEW329R 查詢失敗:", e);
            rsData.setCew329rError(true);
        }
    }

}