package com.tfb.aibank.chl.creditcard.qu001.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.component.cardpaytype.CardPaytype;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001Bill;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001BonusReward;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001BonusRewards;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001CardData;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001CardLockStatus;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CE5552RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.CE5552RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW231RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW231RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RB500Repeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW315RResponse;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

//@formatter:off
/**
* @(#)NCCQU001Service.java
* 
* <p>Description:信用卡總覽 NCCQU001Service</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NCCQU001Service extends NCCService {
    private static final IBLog logger = IBLog.getLog(NCCQU001Service.class);

    @Autowired
    private NCCQU001Utils utils;

    /**
     * 取得本期未出帳消費明細
     * 
     * @param custId
     * @param cardInfos
     * @param creditCards
     * @param cew205RRepeats
     * @throws Exception
     */
    public void getConsumerDetails(String custId, List<NCCQQU001CardData> cardInfos, List<CreditCard> creditCards, List<CEW205RRepeat> cew205RRepeats) {
        if (CollectionUtils.isEmpty(cardInfos))
            return;

        boolean hasPrimaryCard = utils.hasPrimaryCard(cardInfos);

        CEW205RResponse response = new CEW205RResponse();

        if (hasPrimaryCard) {
            // 身分證字號查
            response = utils.sendCEW205R(custId, "");
        }
        else {
            NCCQQU001CardData firstCard = cardInfos.get(0);

            CreditCard creditCard = creditCards.stream().filter(card -> StringUtils.equals(card.getCardKey(), firstCard.getCardKey())).findFirst().orElse(new CreditCard());

            // 以卡號查
            response = utils.sendCEW205R("", creditCard.getCardNo());
            firstCard.setSendCew205r(true);
        }
        cew205RRepeats.addAll(response.getTxRepeats());
    }

    /**
     * 儲存基本的卡片資料
     * 
     * @param creditCards
     * @param virtualCard
     * @param cardInfos
     */
    public void saveBaseCardCache(List<CreditCard> creditCards, String virtualCard, List<NCCQQU001CardData> cardInfos, List<NCCQQU001CardData> underCardInfos) {
        for (CreditCard creditCard : creditCards) {
            NCCQQU001CardData data = new NCCQQU001CardData();
            data.setCardKey(creditCard.getCardKey());
            data.setCardNo(DataMaskUtil.maskCreditCard(creditCard.getCardNo()));
            data.setCardType(creditCard.getCardType());
            data.setUnderPrimaryCard(utils.isUnderPrimaryCard(creditCard));
            data.setAdditional(utils.isAdditionalCard(creditCard));
            data.setVirtualCard(StringUtils.indexOf(virtualCard, creditCard.getCardType()) > -1);
            data.setInsuFlag(creditCard.isInsuFlag());
            data.setHceCard(creditCard.isHceCard());
            data.setCardNickName(creditCard.getCardNickname());
            data.setCardActiveFlag(StringUtils.equals(StringUtils.Y, creditCard.getCardActiveCode()));
            data.setCardExpired(creditCard.getCardExpired());
            if (creditCard.getCardTypeInfo() != null)
                data.setCardName(creditCard.getCardTypeInfo().getCardTypeName());
            data.setCostco(utils.isCostcoCard(creditCard.getCardType()));
            data.setTknFlag(StringUtils.equals(StringUtils.Y, creditCard.getTknFlag()));
            if (StringUtils.isBlank(creditCard.getImageURL())) {
                data.setCardFace(NCCQU001Utils.DEFAULT_CARD_FACE);
            }
            else {
                data.setCardFace(creditCard.getImageURL());
            }
            data.setCardCategory(creditCard.getCardLevelDesc());
            data.setVpsCard(StringUtils.isNotBlank(creditCard.getVpsF16()));
            if (data.isUnderPrimaryCard()) {
                data.setMCardNo(DataMaskUtil.maskCreditCard(creditCard.getmCardNo()));
                underCardInfos.add(data);
            }
            else {
                cardInfos.add(data);
            }

        }
        // {卡別}：小>大 正卡>正卡項下附卡 {信用卡卡號}：小>大
        Collections.sort(cardInfos, Comparator.comparing((NCCQQU001CardData cardData) -> !cardData.isAdditional()).thenComparing(Comparator.comparing(NCCQQU001CardData::getCardNo)));

        for (NCCQQU001CardData info : cardInfos) {
            info.setCardNo(DataMaskUtil.maskCreditCard(info.getCardNo()));
        }
    }

    /**
     * 轉換CEW230R
     * 
     * @param response
     * @param bills
     */
    public void transCew231R(CEW231RResponse response, List<NCCQQU001Bill> bills) {
        List<CEW231RRepeat> repeats = response.getTxRepeats();
        for (CEW231RRepeat repeat : repeats) {
            NCCQQU001Bill bill = new NCCQQU001Bill();
            bill.setDate(repeat.getRwdPday());
            bill.setDesc(repeat.getRwdtxt());
            bill.setSign(repeat.getRwdAmtSign());
            bill.setAmount(repeat.getRwdAmt());
            bill.setPayDay(repeat.getRwdNcday());
            bills.add(bill);
        }
        Comparator<NCCQQU001Bill> comparator = Comparator.comparing(NCCQQU001Bill::getDate).reversed();
        // fortify調整
        Collections.sort(bills, comparator);
    }

    /**
     * 有消費明細後 將卡片資料排序
     * 
     * @param cew205rRepeats
     * @param cardInfos
     * @param underCardInfos
     */
    public void checkCew205rHasData(List<CEW205RRepeat> cew205rRepeats, List<NCCQQU001CardData> cardInfos, List<NCCQQU001CardData> underCardInfos) {
        for (NCCQQU001CardData cardData : cardInfos) {
            Optional<CEW205RRepeat> opt = cew205rRepeats.stream().filter(repeat -> StringUtils.equals(DataMaskUtil.maskCreditCard(repeat.getNcbCard()), cardData.getCardNo())).findAny();
            if (opt.isPresent()) {
                cardData.setHasCew205r(true);
            }
            List<NCCQQU001CardData> underCards = underCardInfos.stream().filter(underCardInfo -> StringUtils.equals(underCardInfo.getMCardNo(), cardData.getCardNo())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(underCards)) {
                for (NCCQQU001CardData underCard : underCardInfos) {
                    Optional<CEW205RRepeat> underOpt = cew205rRepeats.stream().filter(repeat -> StringUtils.equals(DataMaskUtil.maskCreditCard(repeat.getNcbCard()), underCard.getCardNo())).findAny();
                    if (underOpt.isPresent())
                        cardData.setHasCew205r(true);
                }
            }
        }
        // 卡號小>大 有消費明細>無消費明細 正卡>附卡
        // Collections.sort(cardInfos, Comparator.comparing(NCCQQU001CardData::isAdditional).thenComparing((NCCQQU001CardData cardData) -> !cardData.isHasCew205r()).thenComparing((NCCQQU001CardData cardData) -> cardData.getCardNo()));

        Collections.sort(cardInfos, new Comparator<NCCQQU001CardData>() {
            @Override
            public int compare(NCCQQU001CardData card1, NCCQQU001CardData card2) {
                // 主卡排在附属卡之前
                int result = Boolean.compare(!card2.isAdditional(), !card1.isAdditional());

                if (result == 0) {
                    // 具有消费明细的卡片排在没有消费明细的卡片之前
                    result = Boolean.compare(card2.isHasCew205r(), card1.isHasCew205r());

                    if (result == 0) {
                        // 卡號
                        result = card1.getCardNo().compareTo(card2.getCardNo());
                    }
                }
                return result;
            }
        });
    }

    /**
     * 未出帳消費明細整理
     * 
     * @param cew205rRepeats
     * @param bills
     * @param userDataCacheService
     * @param currencyCodeCacheManager
     * @param locale
     * @param cardInfos
     * @param underCardInfos
     */
    public void transCew205R(List<CEW205RRepeat> repeats, List<NCCQQU001Bill> bills, UserDataCacheService userDataCacheService, CurrencyCodeCacheManager currencyCodeCacheManager, Locale locale, List<NCCQQU001CardData> cardInfos, List<NCCQQU001CardData> underCardInfos) {

        for (CEW205RRepeat repeat : repeats) {
            NCCQQU001Bill bill = new NCCQQU001Bill();
            String cardNo = DataMaskUtil.maskCreditCard(repeat.getNcbCard());
            bill.setCardNo(cardNo);
            bill.setSingleInstallment(StringUtils.isY(repeat.getOreFlag()));
            bill.setAreadySingleInstallment(StringUtils.equals("S", repeat.getOreFlag()));
            bill.setDate(repeat.getNcbPchd());
            bill.setDesc(repeat.getNcbTxt1());
            bill.setSign(repeat.getNcbSign());
            bill.setAmount(repeat.getNcbTwd());
            bill.setPayDay(repeat.getNcbNccd());
            bill.setSeq(repeat.getNcSeqn());
            bill.setCloseDate(repeat.getNcCday() == null ? StringUtils.EMPTY : DateFormatUtils.CE_DATE_FORMAT.format(repeat.getNcCday()));
            CardPaytype cardPayType = userDataCacheService.getCardPaytype(repeat.getMptTyp(), locale);
            if (cardPayType != null) {
                bill.setPayName(cardPayType.getPayName());
                String token = repeat.getMpayCd();
                if (StringUtils.isNotBlank(token))
                    bill.setToken(StringUtils.substring(token, token.length() - 4, token.length()));
            }
            Optional<NCCQQU001CardData> opt1 = underCardInfos.stream().filter(card -> StringUtils.equals(card.getCardNo(), cardNo)).findAny();
            if (opt1.isPresent()) {
                bill.setCardKey(opt1.get().getCardKey());
                bill.setCardName(utils.getCardName(opt1.get()));
            }
            Optional<NCCQQU001CardData> opt2 = cardInfos.stream().filter(card -> StringUtils.equals(card.getCardNo(), cardNo)).findAny();
            if (opt2.isPresent()) {
                bill.setCardKey(opt2.get().getCardKey());
                bill.setCardName(utils.getCardName(opt2.get()));
            }
            bill.setForeignSign(repeat.getNcbSign());
            bill.setCurrency(repeat.getSrcCur());
            if (StringUtils.isNotBlank(repeat.getSrcCur())) {
                String currencyName = currencyCodeCacheManager.getCurrencyName(repeat.getSrcCur(), locale);
                bill.setCurrencyName(currencyName);
            }

            bill.setForeignAmount(repeat.getSrcAmt());
            bill.setGroup(repeat.getNcGrop());
            bills.add(bill);
        }
        Comparator<NCCQQU001Bill> comparator = Comparator.comparing(NCCQQU001Bill::getDate).reversed();
        // fortify調整
        Collections.sort(bills, comparator);
    }

    /**
     * 無中生有已停用卡片
     * 
     * @param cew205rRepeats
     * @param cardInfos
     * @param underCardInfos
     */
    public void createSomethingNothing(List<CEW205RRepeat> cew205rRepeats, List<NCCQQU001CardData> cardInfos, List<NCCQQU001CardData> underCardInfos) {
        for (CEW205RRepeat repeat : cew205rRepeats) {
            Optional<NCCQQU001CardData> opt = cardInfos.stream().filter(cardInfo -> StringUtils.equals(cardInfo.getCardNo(), DataMaskUtil.maskCreditCard(repeat.getNcbCard()))).findAny();
            Optional<NCCQQU001CardData> opt2 = underCardInfos.stream().filter(cardInfo -> StringUtils.equals(cardInfo.getCardNo(), DataMaskUtil.maskCreditCard(repeat.getNcbCard()))).findAny();
            if (opt.isEmpty() && opt2.isEmpty()) {
                NCCQQU001CardData stopCard = new NCCQQU001CardData();
                stopCard.setCardNo(DataMaskUtil.maskCreditCard(repeat.getNcbCard()));
                stopCard.setCardNickName(repeat.getVnnkNm());
                stopCard.setStop(true);
                stopCard.setCardFace(NCCQU001Utils.DEFAULT_CARD_FACE);
                cardInfos.add(stopCard);
            }
        }
    }

    /**
     * 從CEW314R B500 取得紅利回饋相關資訊
     * 
     * @param b500Repeats
     * @param bonusRewards
     */
    public void getBouns(List<CEW314RB500Repeat> b500Repeats, NCCQQU001BonusRewards bonusRewards) {
        for (CEW314RB500Repeat repeat : b500Repeats) {
            if (repeat.getB5tadd().compareTo(BigDecimal.ZERO) <= 0)
                continue;
            // 取CEW314R FORMAT_ID = B500的資料，將CEW314R_Rs.B5TXT0 Trim掉空白後進行類型對照。
            String desc = StringUtils.trimAllBigSpace(repeat.getB5txt0());
            // 若B5ASIN為空 {正負號}預設帶入+號
            String asin = StringUtils.isBlank(repeat.getB5asin()) ? "+" : repeat.getB5asin();
            // 紅利資料來源 “富邦紅利點” 和 “世界無限點”
            if (StringUtils.equalsAny(desc, "富邦紅利點", "世界無限點")) {
                NCCQQU001BonusReward bonus = new NCCQQU001BonusReward();
                bonus.setB5tadd(repeat.getB5tadd());
                bonus.setB5asin(asin);
                bonusRewards.setDividend(bonus);
            }
            // 哩程資料來源 “富邦哩程”
            else if (StringUtils.equals(desc, "富邦哩程")) {
                NCCQQU001BonusReward bonus = new NCCQQU001BonusReward();
                bonus.setB5tadd(repeat.getB5tadd());
                bonus.setB5asin(asin);
                bonusRewards.setMileage(bonus);
            }
            // 福華點 “福華點”
            else if (StringUtils.equalsAny(desc, "福華點")) {
                NCCQQU001BonusReward bonus = new NCCQQU001BonusReward();
                bonus.setB5tadd(repeat.getB5tadd());
                bonus.setB5asin(asin);
                bonusRewards.setFuhua(bonus);
            }
        }
    }

    /**
     * 查 CE5552R 信用卡資料
     * 
     * @param cardData
     * @param output
     */
    public void queryCardControlData(CreditCard cardData, NCCQU001CardLockStatus output) {
        CE5552RRequest request = new CE5552RRequest();
        request.setPiFunc("Q");
        request.setPiCdNo(cardData.getCardNo());

        try {
            CE5552RResponse cardControlRes = creditCardResource.sendCE5552R(request);
            // /** 最近變更日期 YYYY/MM/DD */
            output.setPichDy(DateUtils.getCEDateStr(cardControlRes.getPiChDy()));

            // /** 國內實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPilPStatus(controlTag(cardControlRes.getPiLPhy(), cardControlRes.getPiLPam()));
            // /** 國內實體卡交易金額限制 */
            output.setPilPMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiLPam()));
            // /** 國外實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPifPStatus(controlTag(cardControlRes.getPiFPhy(), cardControlRes.getPiFPam()));
            // /** 國外實體卡交易金額限制 */
            output.setPifPMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiFPam()));
            // /** 國內非實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPilCStatus(controlTag(cardControlRes.getPiLCnp(), cardControlRes.getPiLCam()));
            // /** 國內非實體卡交易金額限制 */
            output.setPilCMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiLCam()));
            // /** 國外非實體卡交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPifCStatus(controlTag(cardControlRes.getPiFCnp(), cardControlRes.getPiFCam()));
            // /** 國外非實體卡交易金額限制 */
            output.setPifCMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiFCam()));
            // /** 國內行動支付交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPilTStatus(controlTag(cardControlRes.getPiLTkn(), cardControlRes.getPiLTam()));
            // /** 國內行動支付交易金額限制 */
            output.setPilTCMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiLTam()));
            // /** 國外行動支付交易控管狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            output.setPifTStatus(controlTag(cardControlRes.getPiFTkn(), cardControlRes.getPiFTam()));
            // /** 國外行動支付交易金額限制 */
            output.setPifTMoney(ConvertUtils.str2BigDecimal(cardControlRes.getPiFTam()));
            // @formatter:off
            Boolean isAllLock = output.getPilPStatus() == 0 && output.getPifPStatus() == 0 
                    && output.getPilCStatus() == 0 && output.getPifCStatus() == 0 
                    && output.getPilTStatus() == 0 && output.getPifTStatus() == 0;
            Boolean isAllNoLock = output.getPilPStatus() == 2 && output.getPifPStatus() == 2 
                    && output.getPilCStatus() == 2 && output.getPifCStatus() == 2
                    && output.getPilTStatus() == 2 && output.getPifTStatus() == 2;
            // @formatter:on
            // /** 所有狀態 0: 全鎖定 1:部分鎖定 2:未鎖定 */
            if (Boolean.TRUE.equals(isAllLock)) {
                output.setAllStatus(0);
            }
            else if (Boolean.TRUE.equals(isAllNoLock)) {
                output.setAllStatus(2);
            }
            else {
                output.setAllStatus(1);
            }
        }
        catch (ServiceException ex) {
            logger.error(ex.getMessage(), ex);
            output.setIsCe5552rError(true);
        }

    }

    /** 0: 全鎖定 1:部分鎖定 2:未鎖定 */
    private Integer controlTag(String txnStatus, String amt) {
        if (StringUtils.equals(txnStatus, "D")) {
            BigDecimal amtBd = ConvertUtils.str2BigDecimal(amt);
            return amtBd.compareTo(BigDecimal.ZERO) == 0 ? 0 : 1;
        }
        else {
            return 2;
        }
    }

    /**
     * 信用卡帳單分期查詢到緩存
     * 
     * @param cache
     * @param custId
     */
    public void cacheCEW315RResponse(NCCQU001Cache cache, String custId) {
        if (cache.getCew315Rresponse() == null) {
            try {
                CEW315RResponse response = creditCardResource.sendCEW315R(custId);
                validateCew315RRS(response);
                cache.setCew315Rresponse(response);
            }
            catch (ServiceException | ActionException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

}
