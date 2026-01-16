package com.tfb.aibank.chl.creditcard.ot003.task;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.ot003.model.NCCOT003010Rq;
import com.tfb.aibank.chl.creditcard.ot003.model.NCCOT003010Rs;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCOT003010Task.java
 * 
 * <p>Description:超商繳信用卡款 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCOT003010Task extends AbstractAIBankBaseTask<NCCOT003010Rq, NCCOT003010Rs> {

    @Autowired
    private UserDataCacheService userCacheService;

    @Autowired
    private CreditCardResource creditCardResource;

    @Override
    public void validate(NCCOT003010Rq rqData) throws ActionException {
        // 檢核行為在handle
    }

    @Override
    public void handle(NCCOT003010Rq rqData, NCCOT003010Rs rsData) throws ActionException {
        AIBankUser loginUser = getLoginUser();
        String custId = loginUser.getCustId();
        // (1) 若為信用卡特殊戶，引導至「共通錯誤頁」顯示錯誤代碼與錯誤訊息
        userCacheService.validateCreditCardStatus(loginUser);
        // (2) 發送電文取得信用卡帳務資訊。
        // A. 若電文發送成功且有帳單資訊，接續下一步驟。
        // B. 本期無帳單資訊，則引導至「共通錯誤頁」，並顯示錯誤代碼(SVC02001)及錯誤訊息：本期無帳單資訊
        CEW303RResponse response = null;
        try {
            response = this.sendCEW303R(custId, StringUtils.EMPTY);
        }
        catch (ServiceException e) {
            logger.warn("取得信用卡帳務資料 sendCEW303R 查詢失敗:", e);
            if (StringUtils.equals(e.getSystemId(), IBSystemId.SVC.name()) && StringUtils.equals(e.getErrorCode(), "02051")) {
                throw e;
            }
            else {
                throwActionException(ErrorCode.NO_BILLING_INFORMATION);
            }
        }

        if (null == response || response.getAcctIdCldy() == null) {
            // fortify: null deference
            throw ExceptionUtils.getActionException(ErrorCode.NO_BILLING_INFORMATION);
        }

        // (3) 判斷是否有傳入{繳款類別}、{繳款金額}參數，
        if (this.incomingParametersQualified(rqData.getTxType(), rqData.getTxAmount())) {
            rsData.setGoNextPage(true);
            rsData.setMonth(this.getMonth(response.getAcctIdCldy()));
            return;
        }
        // (4) 發送電文取得自動扣繳設定狀態。
        // A. 若電文發送成功，則接續下一步驟。
        // B. 若非上述，則視為未設定，並接續下一步驟。
        boolean automatic = this.sendCEW309R(custId, "Q");

        rsData.setMsgType(this.getMsgType(automatic, response.getAcctIdSbal(), response.getAcctIdPayd(), response.getAcctIdMinp(), StringUtils.equals("1", response.getAcctIdDlusts())));
        rsData.setMonth(this.getMonth(response.getAcctIdCldy()));
        rsData.setCheckoutDay(DateUtils.getCEDateStr(response.getAcctIdCldy()));
        rsData.setPaymentDeadline(DateUtils.getCEDateStr(response.getAcctIdDudy()));
        rsData.setAmountPaidDay(DateUtils.getCEDateStr(response.getAcctIdLpdy()));

        rsData.setAcctIdSbal(response.getAcctIdSbal());
        rsData.setAcctIdPayd(response.getAcctIdPayd());
        rsData.setAcctIdDpayd(response.getAcctIdDpayd());
        rsData.setAcctIdMinp(response.getAcctIdMinp());
    }

    /**
     * X月份帳單
     * 
     * @param acctIdCldy
     * @return
     */
    private String getMonth(Date acctIdCldy) {
        if (acctIdCldy == null) {
            logger.error("應婉婷要求，將此處程式碼還原，待BU確認處理方式後再修改。");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(acctIdCldy);
        int month = calendar.get(Calendar.MONTH) + 1;
        return String.valueOf(month);
    }

    /**
     * 提示訊息
     * 
     // @formatter:off
     * @param automatic 自動扣繳
     * @param acctIdSbal 帳單金額
     * @param acctIdPayd 已繳金額
     * @param acctIdMinp 最低應繳金額
     * @param isDelay 超過繳款期限
     * @return
     */
     // @formatter:on
    private String getMsgType(boolean automatic, BigDecimal acctIdSbal, BigDecimal acctIdPayd, BigDecimal acctIdMinp, boolean isDelay) {
        // 若有重複情境，以以下順序優先
        // A. 已設定自動扣繳，請確認避免重複繳款。
        if (automatic)
            return "A";
        // B. 提醒您，本期帳單已繳清。
        // {帳單金額} > 0 且 {已繳金額} >= {帳單金額}
        if (acctIdSbal.compareTo(BigDecimal.ZERO) > 0 && acctIdPayd.compareTo(acctIdSbal) >= 0)
            return "B";
        // C. 提醒您，本期帳單已繳最低應繳。
        // {帳單金額} > 0 、 {已繳金額} >= {最低應繳金額}、且{已繳金額}<{帳單金額}
        if (acctIdSbal.compareTo(BigDecimal.ZERO) > 0 && acctIdPayd.compareTo(acctIdMinp) >= 0 && acctIdSbal.compareTo(acctIdPayd) > 0)
            return "C";
        // D. 提醒您，本期帳單無應繳金額。
        // {帳單金額} <= 0。
        if (BigDecimal.ZERO.compareTo(acctIdSbal) >= 0)
            return "D";
        // E. 已超過繳款期限，請儘速繳款；若已繳款，請忽略此訊息。
        if (isDelay)
            return "E";
        // F. 提醒您，本期帳單尚未繳納至最低應繳金額。
        // {已款金額}<{最低應繳金額}，且{已繳金額}>0。
        if (acctIdMinp.compareTo(acctIdPayd) > 0 && acctIdPayd.compareTo(BigDecimal.ZERO) > 0)
            return "F";
        return StringUtils.EMPTY;
    }

    /**
     * 判斷{繳款類別}、{繳款金額}參數的正確性
     * 
     * @param txType
     * @param txAmount
     * @return
     */
    private boolean incomingParametersQualified(String txType, BigDecimal txAmount) {
        return StringUtils.equalsAny(txType, "0", "1", "2") && (txAmount.compareTo(BigDecimal.ZERO) > 0 && txAmount.scale() == 0 && txAmount.compareTo(new BigDecimal("20000")) <= 0);
    }

    /**
     * 額度及繳款查詢
     *
     * @param custAcid
     * @param custCdno
     * @return
     */
    public CEW303RResponse sendCEW303R(String custAcid, String custCdno) {
        BaseServiceResponse<CEW303RResponse> rs = creditCardResource.sendCEW303R(custAcid, custCdno);
        return rs.getData();
    }

    /**
     * 信用卡費已自動扣繳設定 查
     * 
     * @param custId
     * @param custType
     * @return
     */
    public Boolean sendCEW309R(String custId, String custType) {
        try {
            return creditCardResource.sendCEW309R(custId, custType).getData();
        }
        catch (ServiceException se) {
            logger.warn("自動扣繳設定資料 sendCEW309R 查詢失敗:", se);
        }
        return Boolean.FALSE;
    }
}
