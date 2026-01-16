package com.tfb.aibank.chl.creditcard.qu011.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011CacheData;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011CostcoBalance;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011CostcoDetail;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011CreditCard;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011Output;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.VB0051Response;
import com.tfb.aibank.chl.creditcard.resource.dto.VB0052Rep;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCQU011Service.java
 * 
 * <p>Description:CHL NCCQU011 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCCQU011Service extends NCCService {

    public static final String CACHE_KEY = "NCCQU011_CACHE_KEY";

    /**
     * 發查電文(VB0051)取得好多金餘額
     * 
     * @param aibankUser
     * @param montyp
     * @param dataOutput
     */
    public void getCostcoBalance(AIBankUser aibankUser, String montyp, NCCQU011Output dataOutput) {
        String custId = aibankUser.getCustId();
        BaseServiceResponse<VB0051Response> rs = creditCardResource.sendVB0051(custId, montyp);
        NCCQU011CostcoBalance balance = new NCCQU011CostcoBalance(rs.getData());
        // 無資料
        if (StringUtils.notEquals(balance.getRspCode(), Constants.STATUS_CODE_SUCCESS)) {
            balance.setLastamt(BigDecimal.ZERO);
            balance.setUseamt(BigDecimal.ZERO);
            balance.setAddamt(BigDecimal.ZERO);
            balance.setAdjamt(BigDecimal.ZERO);
            balance.setThisamt(BigDecimal.ZERO);
            balance.setExpamt(BigDecimal.ZERO);
        }
        else {
            // 替金額設置正負號
            balance.setLastamt(addSign(balance.getSignFlg1(), balance.getLastamt()));
            balance.setUseamt(addSign(balance.getSignFlg2(), balance.getUseamt()));
            balance.setAddamt(addSign(balance.getSignFlg3(), balance.getAddamt()));
            balance.setAdjamt(addSign(balance.getSignFlg4(), balance.getAdjamt()));
            balance.setThisamt(addSign(balance.getSignFlg5(), balance.getThisamt()));
            balance.setExpamt(addSign(balance.getSignFlg6(), balance.getExpamt()));
        }
        dataOutput.setCostcoBalance(balance);
    }

    /**
     * 設置正負號
     * 
     * @param sign
     * @param amt
     * @return
     */
    private BigDecimal addSign(String sign, BigDecimal amt) {
        return (amt != null && StringUtils.equals(sign, "-")) ? amt.negate() : amt;
    }

    /**
     * 發查電文(VB0052)取得好多金明細，montyp = 0 ~ 6，逐次發查電文(VB0052)，直到取到資料為止
     * 
     * @param aibankUser
     * @param dataOutput
     * @param cache
     */
    public void getCostcoDetails(AIBankUser aibankUser, NCCQU011Output dataOutput, NCCQU011CacheData cache) {
        List<NCCQU011CostcoDetail> costcoDetails = new ArrayList<>();
        int montyp = getMontyp(cache.getMontyp());
        while (montyp <= 6 && CollectionUtils.isEmpty(costcoDetails)) {
            cache.setMontyp(Integer.toString(montyp));
            BaseServiceResponse<List<VB0052Rep>> rs = creditCardResource.sendVB0052(aibankUser.getCustId(), cache.getMontyp());
            List<VB0052Rep> repList = rs.getData();
            costcoDetails = repList.stream().map(this::buildCostcoDetail).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(costcoDetails)) {
                montyp = getMontyp(cache.getMontyp());
            }
        }
        setMonthDisplay(costcoDetails);
        // 排序：以交易日期，降冪排序
        IBUtils.sort(costcoDetails, "txndate", true);
        dataOutput.setCostcoDetails(costcoDetails);
    }

    /**
     * 好多金明細，設置月份小標
     * 
     * @param costcoDetails
     * @return
     */
    private void setMonthDisplay(List<NCCQU011CostcoDetail> costcoDetails) {
        if (CollectionUtils.isNotEmpty(costcoDetails)) {
            // 對讀取的交易明細，設置月份小標
            String monthDisplay = StringUtils.EMPTY;
            int sysYear = Calendar.getInstance().get(Calendar.YEAR);
            for (NCCQU011CostcoDetail detail : costcoDetails) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(detail.getTxndate());
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                if (sysYear == year) {
                    monthDisplay = month + I18NUtils.getMessage("nccqu011.month"); // MM月
                }
                else {
                    monthDisplay = month + I18NUtils.getMessage("nccqu011.month") + "/" + year; // MM月/yyyy
                }
                detail.setMonthDisplay(monthDisplay);
            }
        }
    }

    private int getMontyp(String beforeMontyp) {
        if (StringUtils.isBlank(beforeMontyp)) {
            return 0;
        }
        else {
            int before = ConvertUtils.str2Int(beforeMontyp);
            return before + 1;
        }
    }

    private NCCQU011CostcoDetail buildCostcoDetail(VB0052Rep rep) {
        NCCQU011CostcoDetail detail = new NCCQU011CostcoDetail();
        detail.setTxndate(rep.getTxndate());
        detail.setTxndateMMDD(rep.getTxndate());
        detail.setSignflg(rep.getSignflg());
        detail.setTxnpnts(StringUtils.equals(rep.getSignflg(), "-") ? rep.getTxnpnts().negate() : rep.getTxnpnts());
        detail.setTxntxt(rep.getTxntxt());
        detail.setCrdno(rep.getCrdno());
        detail.setTxntype(rep.getTxntype());
        detail.setMertype(rep.getMertype());

        // 備註
        switch (detail.getTxntype()) {
        case "ADD":
            if (StringUtils.equals(detail.getSignflg(), "+")) {
                // 回饋
                detail.setMemo(I18NUtils.getMessage("nccqu011.give_back"));
            }
            else if (StringUtils.equals(detail.getSignflg(), "-")) {
                // 回饋扣回
                detail.setMemo(I18NUtils.getMessage("nccqu011.rebate"));
            }
            break;
        case "USE":
            // 折抵
            detail.setMemo(I18NUtils.getMessage("nccqu011.discount"));
            break;
        case "ADJ":
            // 調整
            detail.setMemo(I18NUtils.getMessage("nccqu011.adjustment"));
            break;
        case "EXP":
            // 到期歸零
            detail.setMemo(I18NUtils.getMessage("nccqu011.expires_to_zero"));
            break;
        }
        return detail;
    }

    /**
     * 取得「期數」的集合
     * 
     * @param custId
     * @param custCdno
     * @param dataOutput
     */
    public void getPeriodList(String custId, NCCQU011Output dataOutput) {
        BaseServiceResponse<CEW303RResponse> rs = creditCardResource.sendCEW303R(custId, StringUtils.EMPTY);
        CEW303RResponse response = rs.getData();
        Date billingDate = response.getAcctIdCldy() == null ? new Date() : response.getAcctIdCldy();

        List<String> periodList = new ArrayList<>();
        periodList.add(DateFormatUtils.format(DateUtils.addMonths(billingDate, 1), "yyyy/MM"));
        for (int i = 0; i <= 5; i++) {
            periodList.add(DateFormatUtils.format(billingDate, "yyyy/MM"));
            billingDate = DateUtils.addMonths(billingDate, -1);
        }
        dataOutput.setPeriodList(periodList);
    }

    /**
     * 取得好多金明細後，設置{信用卡名稱}+<空白>+{信用卡卡號}
     * 
     * 
     * @param aibankUser
     * @param userLocale
     * @param costcoDetails
     * @throws ActionException
     */
    public void setCardName(AIBankUser aibankUser, Locale userLocale, List<NCCQU011CostcoDetail> costcoDetails) throws ActionException {
        List<CreditCard> allCreditCards = userDataCacheService.getAllCreditCards(aibankUser, userLocale);
        if (CollectionUtils.isNotEmpty(costcoDetails)) {
            for (NCCQU011CostcoDetail detail : costcoDetails) {
                String cardNo = detail.getCrdno();
                CreditCard creditCard = allCreditCards.stream().filter(c -> StringUtils.equals(c.getCardNo(), cardNo)).findFirst().orElse(null);
                if (creditCard != null) {
                    StringBuilder sb = new StringBuilder(0);
                    sb.append(creditCard.getCardName()).append(" ").append(creditCard.getSimpleCardNo());
                    detail.setCrdno(sb.toString());
                    detail.setCardKey(creditCard.getCardKey()); // 設置信用卡識別資訊
                }
                // 表示此交易使用的信用卡，不在客戶擁有的信用卡清單之中，將卡號進行隱碼處理。
                else {
                    StringBuilder sb = new StringBuilder(0);
                    sb.append(I18NUtils.getMessage("ncc.default.card_name")).append(" ").append("····").append(StringUtils.right(cardNo, 4));
                    detail.setCrdno(sb.toString());
                    detail.setCardKey(UUID.randomUUID().toString()); // 設置信用卡識別資訊
                }
            }
        }
    }

    /**
     * 讀取信用卡清單
     * 
     * @param aibankUser
     * @param userLocale
     * @param dataOutput
     * @throws ActionException
     */
    public void getCreditCardList(AIBankUser aibankUser, Locale userLocale, NCCQU011Output dataOutput) throws ActionException {
        List<CreditCard> allCreditCards = userDataCacheService.getAllCreditCards(aibankUser, userLocale);
        List<NCCQU011CreditCard> cardInfos = allCreditCards.stream().map(this::buildCreditCard).collect(Collectors.toList());
        String[] sortProperty = { "cardHoldType", "cardNo" };
        boolean[] reverse = { false, false };
        IBUtils.sort(cardInfos, sortProperty, reverse);

        // 卡號僅作為排序使用，排序後清除
        for (NCCQU011CreditCard card : cardInfos) {
            card.setCardNo(null);
        }
        dataOutput.setCardInfos(cardInfos);
    }

    private NCCQU011CreditCard buildCreditCard(CreditCard source) {
        NCCQU011CreditCard target = new NCCQU011CreditCard();
        target.setCardNo(source.getCardNo());
        target.setCardKey(source.getCardKey());
        StringBuilder sb = new StringBuilder(0);
        sb.append(source.getCardName()).append(" ").append("····").append(StringUtils.right(source.getCardNo(), 4));
        target.setCardName(sb.toString());
        target.setCardHoldType(source.getCardHoldType().getCode());
        target.setSecondaryUnderPrimaryCard(source.getCardHoldType().isSecondaryUnderPrimaryCard());
        return target;
    }

}
