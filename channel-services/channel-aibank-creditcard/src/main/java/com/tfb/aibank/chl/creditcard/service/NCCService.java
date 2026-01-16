package com.tfb.aibank.chl.creditcard.service;

import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW315RResponse;
import com.tfb.aibank.chl.service.AIBankChannelService;

//@formatter:off
/**
* @(#)NCCService.java
* 
* <p>Description:CHL 信用卡(NCC）服務類別，撰寫此大類共同的「邏輯」程式，所有method必須宣告 void</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCService extends AIBankChannelService {

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

    /**
     * 從 SYSTEM_PARAM 取值
     * 
     * @param category
     * @param paramKey
     * @return
     */
    @Override
    public String getValue(String category, String paramKey) {
        return systemParamCacheManager.getValue(category, paramKey);
    }

    /**
     * #9018 #8041 與BU確認針對電文CEW315R HERRID=0000且WORDING <> 空值情境，在特定路徑以錯誤訊息顯示
     * 
     * 檢查是否需拋錯
     * 
     * @param response
     * @throws ActionException
     */
    public void validateCew315RRS(CEW315RResponse response) throws ActionException {
        if (StringUtils.equals(StringUtils.trimToEmptyEx(response.getHFMTID()), "0002")) {
            if (StringUtils.isNotBlank(response.getWording())) {
                throw new ActionException(IBSystemId.AI.getSystemId(), splitCew315RRSWording(response.getWording())[0], SeverityType.ERROR, splitCew315RRSWording(response.getWording())[1]);
            }
        }
    }

    /**
     * #9018 #8041 與BU確認針對電文CEW315R HERRID=0000且WORDING <> 空值情境，在特定路徑以錯誤訊息顯示
     * 
     * 將wording 欄位分成 errorCode 和 errorMsg
     * 
     * @param input
     * @return
     */
    private String[] splitCew315RRSWording(String input) {
        String regex = "^(?<alphanumeric>\\p{Alnum}+)(?<remaining>.*)$";
        var matcher = Pattern.compile(regex).matcher(input);

        // 使用模式匹配进行解构
        return matcher.matches() ? new String[] { matcher.group("alphanumeric"), matcher.group("remaining") } : new String[] { "", input };
    }
}
