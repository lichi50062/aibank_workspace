package com.tfb.aibank.chl.creditcardactivities.service;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcardactivities.resource.CreditCardResource;
import com.tfb.aibank.chl.service.AIBankChannelService;

// @formatter:off
/**
 * @(#)NCAService.java
 * 
 * <p>Description:CHL 信用卡活動(NCA）服務類別，撰寫此大類共同的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCAService extends AIBankChannelService {

    @Autowired
    protected CreditCardResource creditCardResource;

    /**
     * 驗證表單卡號末三碼
     * 
     * @param task
     * @param fieldName
     * @param value
     */
    public void validateCvv(AbstractAIBankBaseTask<?, ?> task, String fieldName, String value, Locale locale) {
        if (StringUtils.isBlank(value)) {
            task.addErrorFieldMap(fieldName, I18NUtils.getMessage("validate.cvv.notempty.desc", locale));
        }

        if (!ValidatorUtility.checkLength(value, 3, 3)) {
            task.addErrorFieldMap(fieldName, I18NUtils.getMessage("validate.cvv.lennotthree.desc", locale));
        }
    }

    /**
     * 驗證表單卡片效期
     * 
     * @param task
     * @param fieldName
     * @param value
     */
    public void validateCardExpireDate(AbstractAIBankBaseTask<?, ?> task, String fieldName, String value, Locale locale) {
        if (StringUtils.isBlank(value)) {
            task.addErrorFieldMap(fieldName, I18NUtils.getMessage("validate.cardexpire.notempty.desc", locale));
        }

        if (!DateUtils.isValidDateStr(value, "MMyy")) {
            task.addErrorFieldMap(fieldName, I18NUtils.getMessage("validate.cardexpire.formaterror.desc", locale));
        }
    }

    /**
     * 驗證表單卡號
     * 
     * @param task
     * @param fieldName
     * @param value
     */
    public void validateCardNo(AbstractAIBankBaseTask<?, ?> task, String fieldName, String value, Locale locale) {
        if (StringUtils.isBlank(value)) {
            task.addErrorFieldMap(fieldName, I18NUtils.getMessage("validate.cardno.notempty.desc", locale));
        }
    }

    /**
     * 驗證表單出生日期欄位
     * 
     * @param task
     * @param fieldName
     * @param value
     */
    public void validateBirthday(AbstractAIBankBaseTask<?, ?> task, String fieldName, String value, Locale locale) {
        if (StringUtils.isBlank(value)) {
            task.addErrorFieldMap(fieldName, I18NUtils.getMessage("validate.birth.notempty.desc", locale));
        }

        // 限系統日以前日期
        Date birth = DateUtils.getSimpleDate(value);
        if (birth.after(new Date())) {
            task.addErrorFieldMap(fieldName, I18NUtils.getMessage("validate.birth.after.desc", locale));
        }
    }

}
