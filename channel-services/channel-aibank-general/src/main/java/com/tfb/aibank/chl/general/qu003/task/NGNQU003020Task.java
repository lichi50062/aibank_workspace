package com.tfb.aibank.chl.general.qu003.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003020Rq;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003020Rs;
import com.tfb.aibank.component.dic.DicCacheManager;

// @formatter:off
/**
 * @(#)NGNQU003020Task.java
 * 
 * <p>Description:優惠 020 搜尋頁</p>
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
public class NGNQU003020Task extends AbstractAIBankBaseTask<NGNQU003020Rq, NGNQU003020Rs> {

    @Autowired
    private DicCacheManager dicCacheManager;

    @Override
    public void validate(NGNQU003020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU003020Rq rqData, NGNQU003020Rs rsData) throws ActionException {
        String str = dicCacheManager.getValue("PROMOTION", "PROMOTION_KEYWORDS");
        if (StringUtils.isNotBlank(str)) {
            List<String> keywords = List.of(StringUtils.split(str, ","));
            rsData.setKeywords(keywords);
        }
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
