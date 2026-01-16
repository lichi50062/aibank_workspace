package com.tfb.aibank.chl.creditcard.tx005.task;

import java.math.BigDecimal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.resource.dto.CustomerCardApply;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005030Rq;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005030Rs;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005CacheData;
import com.tfb.aibank.chl.creditcard.tx005.service.NCCTX005Service;
import com.tfb.aibank.chl.creditcard.utils.NCCUtils;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)NCCTX005030Task.java
 * 
 * <p>Description:額度調整 030 財力證明上傳頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCTX005030Task extends AbstractAIBankBaseTask<NCCTX005030Rq, NCCTX005030Rs> {

    @Autowired
    private NCCUtils utils;

    @Override
    public void handle(NCCTX005030Rq rqData, NCCTX005030Rs rsData) throws ActionException {

        NCCTX005CacheData cache = super.getCache(NCCTX005Service.CACHE_KEY, NCCTX005CacheData.class);
        cache.setCompany(rqData.getCompany());
        cache.setJobTitle(rqData.getJobTitle());
        cache.setSeniorityY(rqData.getSeniorityY());
        cache.setSeniorityM(rqData.getSeniorityM());
        cache.setOfficeTelArea(rqData.getOfficeTelArea());
        cache.setOfficeTel(rqData.getOfficeTel());
        cache.setOfficeTelExt(rqData.getOfficeTelExt());
        cache.setCityCode1(rqData.getCityCode1());
        cache.setCityName(rqData.getCityName());
        cache.setAreaName(rqData.getAreaName());
        cache.setZipcode(rqData.getZipcode());
        cache.setOfficeAddress(rqData.getCityName() + rqData.getAreaName() + rqData.getOfficeAddress());
        cache.setOfficeZip(rqData.getZipcode());
        cache.setProofFiles(Collections.emptyList()); // 進入「上傳頁」重置 Cache 裡的上傳檔案資料
        setCache(NCCTX005Service.CACHE_KEY, cache);

        rsData.setAdjustItem(cache.getAdjustItem());
        rsData.setSkipProofResources(checkSkipProofResources(cache.getAdjustAmt(), cache.getCustomerCardApply()));
        rsData.setImageResize(ConvertUtils.str2Int(utils.getValue(AIBankConstants.CHANNEL_NAME, "NCCTX005_IMAGE_RESIZE"), 2000));
    }

    private boolean checkSkipProofResources(BigDecimal adjustAmt, CustomerCardApply customerCardApply) {
        if (adjustAmt == null || customerCardApply == null || customerCardApply.getAdjustAmt() == null) {
            return false;
        }
        return adjustAmt.compareTo(customerCardApply.getAdjustAmt()) <= 0;
    }

    @Override
    public void validate(NCCTX005030Rq rqData) throws ActionException {

        NCCTX005CacheData cache = super.getCache(NCCTX005Service.CACHE_KEY, NCCTX005CacheData.class);

        if (StringUtils.isBlank(rqData.getCompany())) {
            // 請輸入任職公司
            addErrorFieldMap("company", I18NUtils.getMessage("ncctx005.020.validate.company.required"));
        }
        else {
            String company = rqData.getCompany();
            if (!ValidatorUtility.checkStrLength(company, 40)) {
                // 長度限制為20個連續中文或40個連續英數字
                addErrorFieldMap("company", I18NUtils.getMessage("ncctx005.020.validate.other-usage.format"));
            }
            else if (!ValidatorUtility.isValidChar(company)) {
                // 不可輸入特殊符號
                addErrorFieldMap("company", I18NUtils.getMessage("ncctx005.020.validate.other-usage.special-char"));
            }
        }

        if (StringUtils.isBlank(rqData.getJobTitle())) {
            // 請輸入職稱
            addErrorFieldMap("jobTitle", I18NUtils.getMessage("ncctx005.020.validate.jog-title.required"));
        }
        else {
            String jobTitle = rqData.getJobTitle();
            if (!ValidatorUtility.checkStrLength(jobTitle, 40)) {
                // 長度限制為20個連續中文或40個連續英數字
                addErrorFieldMap("jobTitle", I18NUtils.getMessage("ncctx005.020.validate.other-usage.format"));
            }
            else if (!ValidatorUtility.isValidChar(jobTitle)) {
                // 不可輸入特殊符號
                addErrorFieldMap("jobTitle", I18NUtils.getMessage("ncctx005.020.validate.other-usage.special-char"));
            }
        }

        String yy = rqData.getSeniorityY();
        String mm = rqData.getSeniorityM();
        if (StringUtils.isBlank(yy) || StringUtils.isBlank(mm)) {
            // 請輸入年資
            addErrorFieldMap("seniorityY", I18NUtils.getMessage("ncctx005.020.validate.seniority.required"));
        }
        else {
            int month = ConvertUtils.str2Int(mm);
            if (month < 0 || month > 11) {
                // 月份限輸入0~11之間的數字
                addErrorFieldMap("seniorityY", I18NUtils.getMessage("ncctx005.020.validate.seniority.month.limit"));
            }
        }

        if (rqData.isUntouched()) {
            rqData.setOfficeTelArea(cache.getWorkInfo().getOfficeTelArea());
            rqData.setOfficeTel(cache.getWorkInfo().getOfficeTel());
        }
        else {
            if (StringUtils.isBlank(rqData.getOfficeTelArea())) {
                // 請輸入區碼
                addErrorFieldMap("officeTel", I18NUtils.getMessage("ncctx005.020.validate.office-tel-area.required"));
            }
            else if (StringUtils.isBlank(rqData.getOfficeTel())) {
                // 請輸入公司電話
                addErrorFieldMap("officeTel", I18NUtils.getMessage("ncctx005.020.validate.office-tel.required"));
            }
        }

        if (StringUtils.isBlank(rqData.getCityName())) {
            // 請選擇縣市
            addErrorFieldMap("officeAddress", I18NUtils.getMessage("ncctx005.020.validate.city.required"));
        }
        else {
            if (StringUtils.isBlank(rqData.getAreaName())) {
                // 請選擇鄉鎮市區
                addErrorFieldMap("officeAddress", I18NUtils.getMessage("ncctx005.020.validate.city-area.required"));
            }
            else {
                String officeAddress = rqData.getOfficeAddress();
                if (StringUtils.isBlank(officeAddress)) {
                    // 請輸入公司地址
                    addErrorFieldMap("officeAddress", I18NUtils.getMessage("ncctx005.020.validate.office-address.required"));
                }
                else if (!ValidatorUtility.checkLength(officeAddress, 4, 30)) {
                    // 請輸入完整地址
                    addErrorFieldMap("officeAddress", I18NUtils.getMessage("ncctx005.020.validate.office-address.full"));
                }
                else {
                    String pattern = "[~·～`”“’‘!！@＠#＃$＄%％^＾&＆*＊(（）)\\-+=|｜:：;?？＿「」『』、，。<>.,\\\\{}\\[\\]_\'\"\\/]+";
                    String regex = "(?:[\\u2700-\\u27bf]|(?:\\ud83c[\\udde6-\\uddff]){2}|[\\ud800-\\udbff][\\udc00-\\udfff]|[\\u0023-\\u0039]\\ufe0f?\\u20e3|\\u3299|\\u3297|\\u303d|\\u3030|\\u24c2|\\ud83c[\\udd70-\\udd71]|\\ud83c[\\udd7e-\\udd7f]|\\ud83c\\udd8e|\\ud83c[\\udde6-\\uddff]|\\ud83c[\\ude01\\uddff]|\\ud83c[\\ude01-\\ude02]|\\ud83c\\ude1a|\\ud83c\\ude2f|\\ud83c[\\ude32\\ude02]|\\ud83c\\ude1a|\\ud83c\\ude2f|\\ud83c[\\ude32-\\ude3a]|\\ud83c[\\ude50\\ude3a]|\\ud83c[\\ude50-\\ude51]|\\u203c|\\u2049|[\\u25aa-\\u25ab]|\\u25b6|\\u25c0|[\\u25fb-\\u25fe]|\\u00a9|\\u00ae|\\u2122|\\u2139|\\ud83c\\udc04|[\\u2600-\\u26FF]|\\u2b05|\\u2b06|\\u2b07|\\u2b1b|\\u2b1c|\\u2b50|\\u2b55|\\u231a|\\u231b|\\u2328|\\u23cf|[\\u23e9-\\u23f3]|[\\u23f8-\\u23fa]|\\ud83c\\udccf|\\u2934|\\u2935|[\\u2190-\\u21ff])";
                    if (ValidatorUtility.validateByRegex(officeAddress, pattern) || ValidatorUtility.validateByRegex(officeAddress, regex)) {
                        // 請輸入正確地址
                        addErrorFieldMap("officeAddress", I18NUtils.getMessage("ncctx005.020.validate.office-address.correct"));
                    }
                }
            }
        }
    }

}
