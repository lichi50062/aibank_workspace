package com.tfb.aibank.chl.creditcard.qu001.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001CardData;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001HistoricalBill;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001030Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001030Rs;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.service.NCCQU001Service;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RA021Repeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW315RResponse;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001030Task.java
* 
* <p>Description:信用卡總覽 帳單詳細頁</p>
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
public class NCCQU001030Task extends AbstractAIBankBaseTask<NCCQU001030Rq, NCCQU001030Rs> {
    @Autowired
    private NCCQU001Utils utils;

    @Autowired
    private NCCQU001Service service;

    @Override
    public void validate(NCCQU001030Rq rqData) throws ActionException {
        logger.debug("NCCQU001030 validate....");
    }

    @Override
    public void handle(NCCQU001030Rq rqData, NCCQU001030Rs rsData) throws ActionException {
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        AIBankUser aibankUser = getLoginUser();
        if (StringUtils.equals("030", rqData.getPageFrom())) {
            this.doGet030Data(rqData.getCardNo(), rqData.getQueryMonth(), cache, aibankUser, rsData);
        }
        else {
            this.doGet010Data(cache, aibankUser, rsData);
        }

        service.cacheCEW315RResponse(cache, getLoginUser().getCustId());
        CEW315RResponse response = cache.getCew315Rresponse();
        if (response != null && StringUtils.isBlank(response.getWording()) && StringUtils.equals(response.getStatus(), "A")) {
            rsData.setIsBillInstallmentApply(true);
        }
    }

    /**
     * 歷史帳單
     *
     * @param cardNo
     * @param queryMonth
     * @param cache
     * @param aibankUser
     * @param rsData
     */
    private void doGet030Data(String cardNo, int queryMonth, NCCQU001Cache cache, AIBankUser aibankUser, NCCQU001030Rs rsData) throws ActionException {
        List<NCCQQU001HistoricalBill> historicalBills = null;
        NCCQQU001HistoricalBill historicalBill = null;
        CEW303RResponse data303 = new CEW303RResponse();
        if (StringUtils.isBlank(cardNo)) {
            // 正卡
            String custId = aibankUser.getCustId();
            historicalBills = cache.getCardNoMappingBills().get(custId);
            Optional<NCCQQU001HistoricalBill> opt = historicalBills.stream().filter(bills -> bills.getQueryMonth() == queryMonth).findAny();
            if (opt.isPresent()) {
                historicalBill = opt.get();
            }

            data303 = utils.sendCEW303R(aibankUser.getCustId(), StringUtils.EMPTY);

        }
        else {
            NCCQQU001CardData cardInfo = cache.getCardInfos().stream().filter(card -> StringUtils.equals(card.getCardNo(), cardNo)).findAny().orElse(null);
            if (cardInfo.isAdditional()) {
                // 附卡
                historicalBills = cache.getCardNoMappingBills().get(cardNo);
                Optional<NCCQQU001HistoricalBill> opt = historicalBills.stream().filter(bills -> bills.getQueryMonth() == queryMonth).findAny();
                if (opt.isPresent()) {
                    historicalBill = opt.get();
                }
            }

            data303 = utils.sendCEW303R(StringUtils.EMPTY, cardNo);

        }
        // 歷史帳單頁的月份
        if (null != historicalBill)
            rsData.setMonth(historicalBill.getMonth());

        // 已繳金額
        rsData.setAcctIdPayd(data303.getAcctIdPayd());
        rsData.setAcctIdLpdy(data303.getAcctIdLpdy());

        // 本期應繳總金額
        rsData.setAcctIdPayd(data303.getAcctIdPayd());

        // 本期剩餘應繳金額
        rsData.setAcctIdPayd(data303.getAcctIdPayd());
        rsData.setAcctIdDpayd(data303.getAcctIdDpayd());

        // 其他繳款資訊
        rsData.setAcctIdFAcno(data303.getAcctIdFAcno());
        rsData.setAcctIdChNum(data303.getAcctIdChNum());
        // 剩餘應繳
        rsData.setAcctIdPayd(data303.getAcctIdPayd());
        rsData.setAcctIdDpayd(data303.getAcctIdDpayd());

        if (historicalBill != null) {

            // 本期應繳總額
            rsData.setAatpay(historicalBill.getAatpay());

            // #5654 [信用卡總覽] 進入帳單詳細資訊頁無資料處理 請統一導共通錯誤頁顯示錯誤訊息 SVC02049 暫時無法取得您的帳單資訊
            if (historicalBill.getAacldy() == null) {
                throwActionException(ErrorCode.INFORMATION_TEMPORARILY_UNAVAILABLE);
            }

            // 結帳日
            rsData.setAacldy(historicalBill.getAacldy());

            // 最低應繳
            rsData.setAamiin(historicalBill.getAamiin());
            rsData.setAaminp(historicalBill.getAaminp());

            // 繳款期限
            rsData.setAalmdy(historicalBill.getAalmdy());

            // 前期應繳總額
            rsData.setAaolin(historicalBill.getAaolin());
            rsData.setAaolbl(historicalBill.getAaolbl());

            // 前期繳款(退)金額
            rsData.setAalpin(historicalBill.getAalpin());
            rsData.setAalpam(historicalBill.getAalpam());

            // 本期調整金額
            rsData.setAanein(historicalBill.getAanein());
            rsData.setAanebl(historicalBill.getAanebl());

            // 本期新增金額
            rsData.setAaciin(historicalBill.getAaciin());
            rsData.setAacire(historicalBill.getAacire());

            // 循環信用利率
            rsData.setAaintr(historicalBill.getAaintr());

            // 帳單分期利率
            String aadint = historicalBill.getAadint();

            if (this.checkNumber(aadint)) {
                // #6051 電文回覆00551 > 顯示5.51%
                BigDecimal value = new BigDecimal(aadint).divide(new BigDecimal(100), 4, RoundingMode.HALF_UP);
                rsData.setAadint(IBUtils.formatMoney(value, 2) + "%");
            }
            else {
                rsData.setAadint("--");
            }

            rsData.setAatpin(historicalBill.getAatpin());
        }
        else {
            throwActionException(ErrorCode.INFORMATION_TEMPORARILY_UNAVAILABLE);
        }
    }

    /**
     * 檢核是否數字或小數
     *
     * @param input
     * @return
     */
    private boolean checkNumber(String input) {
        if (StringUtils.isBlank(input))
            return false;
        Pattern p1 = Pattern.compile("([0-9]+(\\.[0-9]+)?)");
        return p1.matcher(input).matches() && !ValidatorUtility.checkWhiteSpace(input);
    }

    /**
     * 當期帳單
     *
     * @param cache
     * @param aibankUser
     * @param rsData
     */
    private void doGet010Data(NCCQU001Cache cache, AIBankUser aibankUser, NCCQU001030Rs rsData) throws ActionException {
        CEW314RA021Repeat detail = new CEW314RA021Repeat();
        if (null != cache.getCew314r() && CollectionUtils.isNotEmpty(cache.getCew314r().getA021Repeats())) {
            detail = cache.getCew314r().getA021Repeats().get(0);
        }
        else {

            // #1517 [信用卡總覽] 進帳單詳細頁錯誤處理調整
            // 進030時如果重查CEW303R 和 CEW314R 回覆失敗，請導至共通錯誤頁顯示當下發送電文回覆的錯誤代碼和訊息
            CEW314RResponse cew314RResponse = utils.sendCEW314R(getLoginUser().getCustId(), "1");
            if (null != cew314RResponse && CollectionUtils.isNotEmpty(cew314RResponse.getA021Repeats())) {
                detail = cew314RResponse.getA021Repeats().get(0);
            }
        }

        CEW303RResponse data303 = cache.getCew303r();
        // 進度條 剩餘額度
        if (null == data303) {
            // #1517 [信用卡總覽] 進帳單詳細頁錯誤處理調整
            // 進030時如果重查CEW303R 和 CEW314R 回覆失敗，請導至共通錯誤頁顯示當下發送電文回覆的錯誤代碼和訊息
            CEW303RResponse response = utils.sendCEW303R(aibankUser.getCustId(), StringUtils.EMPTY);
            data303 = response;
            cache.setCew303r(data303);
        }

        rsData.setCurrentPeriod(true);

        // 已繳金額
        rsData.setAcctIdPayd(data303.getAcctIdPayd());
        rsData.setAcctIdLpdy(data303.getAcctIdLpdy());

        // 本期應繳總金額
        rsData.setAcctIdPayd(data303.getAcctIdPayd());

        // 本期剩餘應繳金額
        rsData.setAcctIdPayd(data303.getAcctIdPayd());
        rsData.setAcctIdDpayd(data303.getAcctIdDpayd());

        // 其他繳款資訊
        rsData.setAcctIdFAcno(data303.getAcctIdFAcno());
        rsData.setAcctIdChNum(data303.getAcctIdChNum());
        // 剩餘應繳
        rsData.setAcctIdPayd(data303.getAcctIdPayd());
        rsData.setAcctIdDpayd(data303.getAcctIdDpayd());

        if (detail != null) {
            // 本期應繳總額
            rsData.setAatpay(detail.getAatpay());

            // #5654 [信用卡總覽] 進入帳單詳細資訊頁無資料處理 請統一導共通錯誤頁顯示錯誤訊息 SVC02049 暫時無法取得您的帳單資訊
            if (detail.getAacldy() == null) {
                throwActionException(ErrorCode.INFORMATION_TEMPORARILY_UNAVAILABLE);
            }

            // 結帳日
            rsData.setAacldy(detail.getAacldy());

            // 最低應繳
            rsData.setAamiin(detail.getAamiin());
            rsData.setAaminp(detail.getAaminp());

            // 繳款期限
            rsData.setAalmdy(detail.getAalmdy());

            // 前期應繳總額
            rsData.setAaolin(detail.getAaolin());
            rsData.setAaolbl(detail.getAaolbl());

            // 前期繳款(退)金額
            rsData.setAalpin(detail.getAalpin());
            rsData.setAalpam(detail.getAalpam());

            // 本期調整金額
            rsData.setAanein(detail.getAanein());
            rsData.setAanebl(detail.getAanebl());

            // 本期新增金額
            rsData.setAaciin(detail.getAaciin());
            rsData.setAacire(detail.getAacire());

            // 循環信用利率
            rsData.setAaintr(detail.getAaintr());

            // 帳單分期利率
            String aadint = detail.getAadint();

            if (this.checkNumber(aadint)) {
                BigDecimal value = new BigDecimal(aadint).divide(new BigDecimal(100), 4, RoundingMode.HALF_UP);
                rsData.setAadint(IBUtils.formatMoney(value, 2) + "%");
            }
            else {
                rsData.setAadint("--");
            }

            rsData.setAatpin(detail.getAatpin());
        }
        else {
            throwActionException(ErrorCode.INFORMATION_TEMPORARILY_UNAVAILABLE);
        }

    }

}