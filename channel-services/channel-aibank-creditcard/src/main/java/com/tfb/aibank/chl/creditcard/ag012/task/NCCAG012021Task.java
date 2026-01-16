package com.tfb.aibank.chl.creditcard.ag012.task;

import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.ConvertUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag012.cache.NCCAG012CacheData;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012021Rq;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012021Rs;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012CardInfo;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012LockStatusInfo;
import com.tfb.aibank.chl.creditcard.ag012.service.NCCAG012Service;

// @formatter:off
/**
 * @(#)NCCAG012021Task.java
 * 
 * <p>Description:信用卡交易設定 021 設定頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG012021Task extends AbstractAIBankBaseTask<NCCAG012021Rq, NCCAG012021Rs> {

    @Override
    public void validate(NCCAG012021Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG012021Rq rqData, NCCAG012021Rs rsData) throws ActionException {
        NCCAG012CacheData cache = getCache(NCCAG012Service.NCCAG012_CACHE_KEY, NCCAG012CacheData.class);
        NCCAG012CardInfo cardInfoForTx = cache.getCardInfoForTx();

        BigDecimal txAmtLimitInCountry = ConvertUtils.str2BigDecimal(rqData.getTxAmtLimitInCountry());
        Boolean isTxAmtLimitInCountryOverZero = txAmtLimitInCountry.compareTo(BigDecimal.ZERO) > 0;
        if (Boolean.TRUE.equals(rqData.getLockRealInCountry())) {
            cardInfoForTx.setPilPStatus(0);
            cardInfoForTx.setPilPMoney(BigDecimal.ZERO);
        }
        else {
            cardInfoForTx.setPilPStatus(Boolean.TRUE.equals(isTxAmtLimitInCountryOverZero) ? 1 : 2);
            cardInfoForTx.setPilPMoney(txAmtLimitInCountry);
        }
        if (Boolean.TRUE.equals(rqData.getLockNotRealInCountry())) {
            cardInfoForTx.setPilCStatus(0);
            cardInfoForTx.setPilCMoney(BigDecimal.ZERO);
        }
        else {
            cardInfoForTx.setPilCStatus(Boolean.TRUE.equals(isTxAmtLimitInCountryOverZero) ? 1 : 2);
            cardInfoForTx.setPilCMoney(txAmtLimitInCountry);
        }

        if (Boolean.TRUE.equals(rqData.getLockActionPayInCountry())) {
            cardInfoForTx.setPilTStatus(0);
            cardInfoForTx.setPilTCMoney(BigDecimal.ZERO);
        }
        else {
            cardInfoForTx.setPilTStatus(Boolean.TRUE.equals(isTxAmtLimitInCountryOverZero) ? 1 : 2);
            cardInfoForTx.setPilTCMoney(txAmtLimitInCountry);
        }

        BigDecimal txAmtLimitOutCountry = ConvertUtils.str2BigDecimal(rqData.getTxAmtLimitOutCountry());
        Boolean isTxAmtLimitOutCountryOverZero = txAmtLimitOutCountry.compareTo(BigDecimal.ZERO) > 0;

        if (Boolean.TRUE.equals(rqData.getLockRealOutCountry())) {
            cardInfoForTx.setPifPStatus(0);
            cardInfoForTx.setPifPMoney(BigDecimal.ZERO);
        }
        else {
            cardInfoForTx.setPifPStatus(Boolean.TRUE.equals(isTxAmtLimitOutCountryOverZero) ? 1 : 2);
            cardInfoForTx.setPifPMoney(txAmtLimitOutCountry);
        }

        if (Boolean.TRUE.equals(rqData.getLockNotRealOutCountry())) {
            cardInfoForTx.setPifCStatus(0);
            cardInfoForTx.setPifCMoney(BigDecimal.ZERO);
        }
        else {
            cardInfoForTx.setPifCStatus(Boolean.TRUE.equals(isTxAmtLimitOutCountryOverZero) ? 1 : 2);
            cardInfoForTx.setPifCMoney(txAmtLimitOutCountry);
        }

        if (Boolean.TRUE.equals(rqData.getLockActionPayOutCountry())) {
            cardInfoForTx.setPifTStatus(0);
            cardInfoForTx.setPifTMoney(BigDecimal.ZERO);
        }
        else {
            cardInfoForTx.setPifTStatus(Boolean.TRUE.equals(isTxAmtLimitOutCountryOverZero) ? 1 : 2);
            cardInfoForTx.setPifTMoney(txAmtLimitOutCountry);
        }

        // 如果是全部鎖定
        if (Boolean.TRUE.equals(rqData.getAllLockStatus())) {
            cardInfoForTx.setAllStatus(0);
            // 國內實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定
            cardInfoForTx.setPilPStatus(0);
            // 國內實體卡交易金額限制 */
            cardInfoForTx.setPilPMoney(BigDecimal.ZERO);
            // 國外實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定
            cardInfoForTx.setPifPStatus(0);
            // 國外實體卡交易金額限制 */
            cardInfoForTx.setPifPMoney(BigDecimal.ZERO);
            // 國內非實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定
            cardInfoForTx.setPilCStatus(0);
            // 國內非實體卡交易金額限制 */
            cardInfoForTx.setPilCMoney(BigDecimal.ZERO);
            // 國外非實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定
            cardInfoForTx.setPifCStatus(0);
            // 國外非實體卡交易金額限制 */
            cardInfoForTx.setPifCMoney(BigDecimal.ZERO);
            // 國內行動支付交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定
            cardInfoForTx.setPilTStatus(0);
            // 國內行動支付交易金額限制 */
            cardInfoForTx.setPilTCMoney(BigDecimal.ZERO);
            // 國外行動支付交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定
            cardInfoForTx.setPifTStatus(0);
            // 國外行動支付交易金額限制
            cardInfoForTx.setPifTMoney(BigDecimal.ZERO);
        }
        else {
            Boolean isAllNotLock = !isTxAmtLimitOutCountryOverZero && !isTxAmtLimitInCountryOverZero;
            cardInfoForTx.setAllStatus(Boolean.TRUE.equals(isAllNotLock) ? 2 : 1);
        }
        cache.setCardInfoForTx(cardInfoForTx);

        NCCAG012LockStatusInfo lockStatusInfo = new NCCAG012LockStatusInfo();
        lockStatusInfo.setTxAmtLimitInCountry(rqData.getTxAmtLimitInCountry());
        lockStatusInfo.setLockRealInCountry(rqData.getLockRealInCountry());
        lockStatusInfo.setLockNotRealInCountry(rqData.getLockNotRealInCountry());
        lockStatusInfo.setLockActionPayInCountry(rqData.getLockActionPayInCountry());
        lockStatusInfo.setTxAmtLimitOutCountry(rqData.getTxAmtLimitOutCountry());
        lockStatusInfo.setLockRealOutCountry(rqData.getLockRealOutCountry());
        lockStatusInfo.setLockNotRealOutCountry(rqData.getLockNotRealOutCountry());
        lockStatusInfo.setLockActionPayOutCountry(rqData.getLockActionPayOutCountry());
        cache.setLockStatusInfo(lockStatusInfo);
        setCache(NCCAG012Service.NCCAG012_CACHE_KEY, cache);
    }

}
