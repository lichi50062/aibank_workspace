package com.tfb.aibank.chl.creditcard.tx004.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW316RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW316RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW316RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CardCarnoApply;
import com.tfb.aibank.chl.creditcard.tx004.model.CreditCardData;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004Input;
import com.tfb.aibank.chl.creditcard.tx004.model.NCCTX004Output;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.service.AIBankChannelService;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCTX004Service.java
* 
* <p>Description:NCCTX004_道路救援登錄 NCCTX004Service</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/24
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NCCTX004Service extends AIBankChannelService {
    private static final IBLog logger = IBLog.getLog(NCCTX004Service.class);

    @Autowired
    CreditCardResource creditCardResource;

    public void checkCreditCardStatus(AIBankUser aiBankUser) throws ActionException {
        userDataCacheService.validateCreditCardStatus(aiBankUser);
    }

    public void getCreditCardsAndRoadsideAssistStatus(AIBankUser aiBankUser, NCCTX004Input input, NCCTX004Output output) throws ActionException {

        List<CreditCard> creditCardList = userDataCacheService.getAllCreditCards(aiBankUser, input.getLocale());

        if (CollectionUtils.isNotEmpty(creditCardList)) {

            if (StringUtils.isNotEmpty(input.getCardKey())) {
                boolean inputCardNoExist = creditCardList.stream().anyMatch(cc -> StringUtils.equals(cc.getCardKey(), input.getCardKey()));
                if (!inputCardNoExist) {
                    ExceptionUtils.getActionException(ErrorCode.INPUT_CARD_NO_NOT_MATCH);
                }

            }
            List<CreditCardData> creditCardDatas = creditCardList.stream().map(this::makeCreditCardData).collect(Collectors.toList());

            // 取得使用者的卡別
            // ***** 此交易使用Company.company_kind作為 正卡人/附卡人依據
            // company_kind = 4 => 附卡人
            // CreditCardIdType creditCardIdType = userDataCacheService.getCreditCardIdType(aiBankUser, input.getLocale());
            // logger.debug("== getHomeCardDataCreditCard == , CreditCardIdType: {}", creditCardIdType);

            // output.setPrimaryCardHolder(creditCardIdType.isPrimaryCard());

            output.setPrimaryCardHolder(!Objects.equals(4, aiBankUser.getCompanyKind()));

            String cardNo = "";
            // 如果使用者持卡狀態為附卡持有者，且是信用卡網路會員登錄
            // 如果使用者持卡狀態為附卡持有者，且是一般會員登錄

            if (!output.isPrimaryCardHolder()) {
                if (aiBankUser.getCustomerType().isGeneral()) {
                    // cardNo =
                    Optional<CreditCard> ccOption = creditCardList.stream().filter(creditCard -> StringUtils.equals(creditCard.getCardStatus(), "0") && creditCard.isCardActive()).findFirst();
                    if (ccOption.isPresent()) {
                        cardNo = ccOption.get().getCardNo();
                    }
                    else {
                        throw ExceptionUtils.getActionException(ErrorCode.NO_CARD_FOR_ROADSIDE_ASSIST);
                    }
                }
                else {
                    if (null == aiBankUser.getCardUserProfileVo()) {
                        throw ExceptionUtils.getActionException(ErrorCode.NO_CARD_FOR_ROADSIDE_ASSIST);
                    }
                    cardNo = aiBankUser.getCardUserProfileVo().getCardNo();
                }
            }

            Map<String, CEW316RRepeat> roadsideAssistMap = queryRoadsideAssistData(aiBankUser, cardNo);
            Set<String> cardsCanHaveRoadsideAssist = roadsideAssistMap.keySet();

            creditCardDatas = creditCardDatas.stream().filter(ccd -> cardsCanHaveRoadsideAssist.contains(ccd.getCardNo())).collect(Collectors.toList());

            for (var creditCardData : creditCardDatas) {
                if (StringUtils.isNotEmpty(input.getCardKey())) {
                    if (input.getCardKey().equals(creditCardData.getCardKey())) {
                        creditCardData.setDesignateCard(true);
                    }
                }
                creditCardData.setRoadsideAssistData(roadsideAssistMap.get(creditCardData.getCardNo()));
                if (Objects.nonNull(creditCardData.getRoadsideAssistData()) && StringUtils.isNotEmpty(creditCardData.getRoadsideAssistData().getCarNo())) {
                    creditCardData.setRegistered(true);
                }
            }
            IBUtils.sort(creditCardDatas, new String[] { "registered", "cardNo" }, new boolean[] { true, true });
            output.setCreditCardDatas(creditCardDatas);
        }
    }

    public Map<String, CEW316RRepeat> queryRoadsideAssistData(AIBankUser aiBankUser, String inputCardNo) throws ActionException {
        Map<String, CEW316RRepeat> roadsideMap = new HashMap<>();
        CEW316RRequest request = new CEW316RRequest();
        // 「1」=> 查詢
        request.setDataType("1");

        Integer companyKind = aiBankUser.getCompanyKind();

        if (Objects.equals(companyKind, 4) && StringUtils.isNotEmpty(inputCardNo)) {
            request.setCustCrdno(inputCardNo);
        }
        else {
            request.setCustId11(aiBankUser.getCustId());
        }
        CEW316RResponse response316 = null;

        try {
            response316 = creditCardResource.roadsideAssistanceOperation(request);
        }
        catch (ServiceException e) {
            if ("V003".equals(e.getErrorCode())) {
                throw ExceptionUtils.getActionException(ErrorCode.NO_CARD_FOR_ROADSIDE_ASSIST);
            }
        }

        if (null != response316) {
            // (A) 若CEW316R_Rs.TxHead.HERRID = V003，代表查無任何可提供道路救援之信用卡
            if ("V003".equals(response316.getErrorCode())) {
                throw ExceptionUtils.getActionException(ErrorCode.NO_CARD_FOR_ROADSIDE_ASSIST);
            }
            if (CollectionUtils.isNotEmpty(response316.getRepeats())) {
                response316.getRepeats().forEach(re -> {
                    re.setOpDate(DateUtils.getROCDate(re.getOpDay(), ""));
                    if (Objects.nonNull(re.getOpDate())) {
                        re.setOpDateSimple(DateFormatUtils.CE_DATE_FORMAT.format(re.getOpDate()));
                    }
                });
                roadsideMap = response316.getRepeats().stream().collect(Collectors.toMap(CEW316RRepeat::getCrdNo, Function.identity(), (existing, replacement) -> existing));
            }
        }
        return roadsideMap;
    }

    protected CreditCardData makeCreditCardData(CreditCard source) {
        // fortify - Privacy Violation - 變數名稱調整 follow NCCQU011 變數名稱
        CreditCardData target = new CreditCardData();
        target.setCardNo(source.getCardNo());
        target.setCardKey(source.getCardKey());
        target.setCardName(source.getCardName());
        target.setCardNickName(source.getCardNickname());
        target.setUnderPrimaryCard(isUnderPrimaryCard(source));
        target.setAdditional(isAdditionalCard(source));

        return target;
    }

    /**
     * 正卡向下副卡
     *
     * @param creditCard
     * @return
     */
    protected boolean isUnderPrimaryCard(CreditCard creditCard) {
        return StringUtils.equals(creditCard.getCardHoldType().getCode(), "1");
    }

    /**
     * 附卡 (只要是非正卡)
     *
     * @param creditCard
     * @return
     */
    protected boolean isAdditionalCard(CreditCard creditCard) {
        return !creditCard.getCardHoldType().isPrimaryCard();
    }

    /**
     * 進行 新增/更新/刪除 道路救援資料
     */
    public void doCardCarNoConfig(AIBankUser aiBankUser, NCCTX004Input input, NCCTX004Output output, MBAccessLog mbAccessLog) throws ActionException {

        CardCarnoApply applyData = initCardCarnoApplyData(aiBankUser, input);

        CEW316RRequest request = new CEW316RRequest();
        // 上送2：登錄/刪除
        request.setDataType("2");

        request.setTxtType(input.getTxtType());
        if (Stream.of("A", "U").anyMatch(txtType -> txtType.equals(request.getTxtType()))) {
            // (1) 若TxtType=A或U，上送「編輯頁」輸入之[車號]
            request.setCarnum(input.getCarNo());
        }

        Integer companyKind = aiBankUser.getCompanyKind();

        CreditCardData cardData = input.getCreditCardData();
        request.setCustCrdno(cardData.getCardNo());
        request.setCustId11(aiBankUser.getCustId());
        if (Objects.equals(companyKind, 4)) {
            // (1) 若AI Bank User取得COMPANY_KIND=4，上送「功能首頁」該張信用卡的卡號
        }
        else {
            // (1) 若AI Bank User取得COMPANY_KIND<>4，上送AI Bank User取得使用者的身分證字號 COMPANY_UID
        }
        CEW316RResponse response316 = null;

        try {
            response316 = creditCardResource.roadsideAssistanceOperation(request);
            // 沒有ErrorCode
            if (StringUtils.isEmpty(response316.getErrorCode())) {
                applyData.setTxStatus("0");
            }

        }
        catch (ServiceException e) {
            logger.error("== roadsideAssistanceOperation err ==: ", e);
            applyData.setTxStatus("1");
            applyData.setHostTxTime(new Date());
            ActionException ae = handleException(mbAccessLog, e);

            applyData.setTxErrorSystemId(ae.getSystemId());
            applyData.setTxErrorCode(ae.getErrorCode());
            applyData.setTxErrorDesc(ae.getErrorDesc());
            throw ae;
        }
        finally {
            applyData = updateCardCarnoApplyData(applyData);
        }
        output.setApplyData(applyData);

    }

    private CardCarnoApply updateCardCarnoApplyData(CardCarnoApply applyData) {
        if (logger.isDebugEnabled()) {
            logger.debug("== updateCardCarnoApplyData == to update: {}", IBUtils.attribute2Str(applyData));
        }
        applyData = creditCardResource.updateCardCarnoApplyData(applyData);
        return applyData;
    }

    protected CardCarnoApply initCardCarnoApplyData(AIBankUser aiBankUser, NCCTX004Input input) {
        CardCarnoApply applyData = new CardCarnoApply();

        applyData.setCompanyKey(aiBankUser.getCompanyKey());
        applyData.setNameCode(aiBankUser.getNameCode());
        applyData.setUserKey(aiBankUser.getUserKey());
        applyData.setUserId(aiBankUser.getUserId());
        applyData.setTxKind(input.getTxKindForCardCarnoApply());
        applyData.setCarNo(input.getCarNo());
        CreditCardData creditCardData = input.getCreditCardData();
        applyData.setCardNo(creditCardData.getCardNo());
        applyData.setHostTxTime(new Date());
        applyData.setTxStatus("4"); // 4 => 未明
        Date txDate = new Date();
        DateUtils.clearTime(txDate);
        applyData.setTxDate(txDate);
        applyData.setHostTxTime(new Date());
        applyData.setTxSource("3");
        applyData = creditCardResource.initCardCarnoApplyData(applyData);

        if (logger.isDebugEnabled()) {
            logger.debug("== initCardCarnoApplyData == after init: {}", IBUtils.attribute2Str(applyData));
        }
        return applyData;
    }

}
