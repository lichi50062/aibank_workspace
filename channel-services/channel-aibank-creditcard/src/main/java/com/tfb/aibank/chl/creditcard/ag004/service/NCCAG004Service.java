package com.tfb.aibank.chl.creditcard.ag004.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CE6210RResponse;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCAG004Service.java
* 
* <p>Description:刷卡通知設定</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/14, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on

@Service
public class NCCAG004Service extends NCCService {
    private static final IBLog logger = IBLog.getLog(NCCAG004Service.class);

    public static final String NCCAG004_CACHE_KEY = "NCCAG004CacheKey";
    @Autowired
    private CreditCardResource creditCardResource;

    /**
     * 取得卡戶email
     * 
     * @param user
     * @param output
     */
    public String getCardUserEmail(AIBankUser user) {
        CE6210RResponse res = creditCardResource.getCardUserBaseInfo(user.getCustId());
        return res.geteMail();
    }

}
