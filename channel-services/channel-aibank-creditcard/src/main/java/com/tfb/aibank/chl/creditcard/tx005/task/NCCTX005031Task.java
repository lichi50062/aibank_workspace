package com.tfb.aibank.chl.creditcard.tx005.task;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005031Rq;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005031Rs;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005CacheData;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005ProofFile;
import com.tfb.aibank.chl.creditcard.tx005.service.NCCTX005Service;

// @formatter:off
/**
 * @(#)NCCTX005031Task.java
 * 
 * <p>Description:額度調整 031 處理財力證明上傳檔案</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/13, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCTX005031Task extends AbstractAIBankBaseTask<NCCTX005031Rq, NCCTX005031Rs> {

    @Override
    public void validate(NCCTX005031Rq rqData) throws ActionException {

        if (StringUtils.isBlank(rqData.getAction()) || StringUtils.notAllEquals(rqData.getAction(), "ADD", "DEL")) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        if (StringUtils.equals(rqData.getAction(), "ADD") && rqData.getProofFile() == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCTX005031Rq rqData, NCCTX005031Rs rsData) throws ActionException {

        NCCTX005CacheData cache = super.getCache(NCCTX005Service.CACHE_KEY, NCCTX005CacheData.class);

        String action = rqData.getAction();

        switch (action) {
        case "ADD":
            cache.getProofFiles().add(rqData.getProofFile());

            // 按3碼流水號規則賦予檔案名稱
            if (CollectionUtils.isNotEmpty(cache.getProofFiles())) {
                final NumberFormat nf = new DecimalFormat("000");
                int seq = 1;
                for (NCCTX005ProofFile file : cache.getProofFiles()) {
                    StringBuilder sb = new StringBuilder(0);
                    sb.append(nf.format(seq)).append(".").append(FilenameUtils.getExtension(file.getName()));
                    file.setName(sb.toString());
                    seq++;
                }
            }

            break;
        case "DEL":
            cache.getProofFiles().remove(rqData.getDelIdx());
            break;
        }

        setCache(NCCTX005Service.CACHE_KEY, cache);
    }

}
