package com.tfb.aibank.chl.creditcard.tx005.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.tx005.model.AdjustItemType;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005040Rq;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005040Rs;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005CacheData;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005ProofFile;
import com.tfb.aibank.chl.creditcard.tx005.service.NCCTX005Service;

// @formatter:off
/**
 * @(#)NCCTX005040Task.java
 * 
 * <p>Description:額度調整 040 確認頁</p>
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
public class NCCTX005040Task extends AbstractAIBankBaseTask<NCCTX005040Rq, NCCTX005040Rs> {

    @Override
    public void validate(NCCTX005040Rq rqData) throws ActionException {
        // 若非可略過財力證明，則必須有要上傳檔案
        NCCTX005CacheData cache = super.getCache(NCCTX005Service.CACHE_KEY, NCCTX005CacheData.class);
        if (StringUtils.equals(rqData.getAdjustItem(), AdjustItemType.TURN_UP.getCode()) && !rqData.isSkipProofResources()) {
            if (CollectionUtils.isEmpty(cache.getProofFiles())) {
                throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
            }
        }
    }

    @Override
    public void handle(NCCTX005040Rq rqData, NCCTX005040Rs rsData) throws ActionException {

        NCCTX005CacheData cache = super.getCache(NCCTX005Service.CACHE_KEY, NCCTX005CacheData.class);

        AdjustItemType adjustItem = cache.getAdjustItem();

        // 調整項目
        rsData.setAdjustItem(adjustItem);
        // 預計調整金額
        rsData.setAdjustAmt(cache.getAdjustAmt());
        // 目前額度
        rsData.setCurrentQuota(cache.getCurrentQuota());
        if (adjustItem.isTurnUp()) {
            // 額度用途
            rsData.setQuotaUsage(cache.getQuotaUsage());
            // 其他用途(額度用途選擇「其他」時必填)
            rsData.setOtherUsage(cache.getOtherUsage());
            // 保費全額(額度用途選擇「人壽保費扣繳(分期)」時必填)
            rsData.setFullInsurAmt(cache.getFullInsurAmt());
            // 任職公司
            rsData.setCompany(cache.getCompany());
            // 職稱
            rsData.setJobTitle(cache.getJobTitle());
            // 年資
            rsData.setSeniority(I18NUtils.getMessage("ncctx005.seniority", cache.getSeniorityY(), cache.getSeniorityM()));
            // 公司電話
            StringBuilder tel = new StringBuilder(0);
            if (StringUtils.isNotBlank(cache.getOfficeTelArea())) {
                tel.append(cache.getOfficeTelArea()).append("-");
            }
            tel.append(cache.getOfficeTel());
            if (StringUtils.isNotBlank(cache.getOfficeTelExt())) {
                tel.append(" ").append("#").append(cache.getOfficeTelExt());
            }
            rsData.setOfficeTel(tel.toString());
            // 公司地址
            rsData.setOfficeAddress(cache.getOfficeAddress());
            // 財力證明
            List<NCCTX005ProofFile> proofFiles = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(cache.getProofFiles())) {
                for (NCCTX005ProofFile proofFile : cache.getProofFiles()) {
                    NCCTX005ProofFile copyFile = IBUtils.deepCopy(proofFile);
                    copyFile.setBase64Image(StringUtils.EMPTY);
                    proofFiles.add(copyFile);
                }
                rsData.setProofFiles(proofFiles);
            }
        }
        else if (adjustItem.isAdditionalCardLimit()) {
            // 附卡持卡人中文姓名(隱碼)
            rsData.setMaskCnam(cache.getSupplementaryCard().getMaskCnam());
            // 附卡持卡人ID(隱碼)
            rsData.setMaskHdid(cache.getSupplementaryCard().getMaskHdid());
        }
    }

}
