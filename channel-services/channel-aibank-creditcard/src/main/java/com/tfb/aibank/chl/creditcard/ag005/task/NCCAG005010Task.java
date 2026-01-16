package com.tfb.aibank.chl.creditcard.ag005.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag005.model.InsurFeeBenefitsType;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005010Rq;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005010Rs;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005CacheData;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005CardInfo;
import com.tfb.aibank.chl.creditcard.ag005.model.NCCAG005Output;
import com.tfb.aibank.chl.creditcard.ag005.service.NCCAG005Service;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW327RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW327RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW345RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW345RResponse;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.CreditCardIdType;

// @formatter:off
/**
 * @(#)NCCAG005010Task.java
 * 
 * <p>Description:保費權益設定 010 保費權益信用卡清單</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/28, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG005010Task extends AbstractAIBankBaseTask<NCCAG005010Rq, NCCAG005010Rs> {

    @Autowired
    private NCCAG005Service service;
    @Autowired
    private UserDataCacheService userDataCacheService;

    private NCCAG005Output dataOutput = new NCCAG005Output();

    @Override
    public void validate(NCCAG005010Rq rqData) throws ActionException {
        if (StringUtils.isNotBlank(rqData.getCardKey())) {
            AIBankUser loginUser = getLoginUser();
            Locale userLocale = getLocale();
            List<CreditCard> allCreditCards = userDataCacheService.getAllCreditCards(loginUser, userLocale);
            boolean isExist = allCreditCards.stream().anyMatch(o -> StringUtils.equals(o.getCardKey(), rqData.getCardKey()));
            if (!isExist) {
                throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
            }
        }
    }

	@Override
    public void handle(NCCAG005010Rq rqData, NCCAG005010Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        Locale userLocale = getLocale();

        // 信用卡戶況與註記
        userDataCacheService.validateCreditCardStatus(loginUser);

        // 檢查客戶是否擁有信用卡，且為正卡持有人
        CreditCardIdType creditCardIdType = userDataCacheService.getCreditCardIdType(loginUser, userLocale);
        if (!creditCardIdType.isPrimaryCard()) {
            throw ExceptionUtils.getActionException(ErrorCode.PRIMARY_CARDHOLDER_ONLY);
        }

        String type = "1";
        String input = loginUser.getCustId();
        // 若有傳入信用卡識別碼，則從信用卡歸戶清單中取出該張信用卡資訊
        if (StringUtils.isNotBlank(rqData.getCardKey())) {
            List<CreditCard> allCreditCards = userDataCacheService.getAllCreditCards(loginUser, userLocale);
            CreditCard creditCard = allCreditCards.stream().filter(c -> StringUtils.equals(c.getCardKey(), rqData.getCardKey())).findFirst().orElseThrow(() -> ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND));
            type = "2";
            input = creditCard.getCardNo();
        }
        //保費權益設定新增單筆金額五萬以下設定生效後走CEW345R電文，未生效走原來CEW327R
        if(service.getCEW345Valid()) {
        	// 發查電文 CEW345R 取得保費權益資訊
            service.sendCEW345R(type, input, dataOutput);
            if (dataOutput.getCew345RResponse() == null || CollectionUtils.isEmpty(dataOutput.getCew345RResponse().getTxRepeats())) {
                // 保費權益僅提供特定卡別設定，點選下方按鈕<a class="btn-text" href="NCCOT001">看相關卡片介紹</a>。如有疑問，請來電信用卡客服<a class="btn-text" href="tel:02-87511313">02-8751-1313</a>，由專人協助處理。
                throw ExceptionUtils.getActionException(ErrorCode.CHECK_INSURANCE_FEE_BENEFITS);
            }
        } else {
        	// 發查電文 CEW327R 取得保費權益資訊
            service.sendCEW327R(type, input, dataOutput);
            if (dataOutput.getCew327RResponse() == null || CollectionUtils.isEmpty(dataOutput.getCew327RResponse().getTxRepeats())) {
                // 保費權益僅提供特定卡別設定，點選下方按鈕<a class="btn-text" href="NCCOT001">看相關卡片介紹</a>。如有疑問，請來電信用卡客服<a class="btn-text" href="tel:02-87511313">02-8751-1313</a>，由專人協助處理。
                throw ExceptionUtils.getActionException(ErrorCode.CHECK_INSURANCE_FEE_BENEFITS);
            }
        }
        // 電文成功，判斷是否擁有符合「可設定權益」的卡片
        List<NCCAG005CardInfo> cardInfoList = new ArrayList<>();
        List<CreditCard> allCreditCards = userDataCacheService.getAllCreditCards(loginUser, userLocale);
        //保費權益設定新增單筆金額五萬以下設定生效後走CEW345R電文，未生效走原來CEW327R
        if(service.getCEW345Valid()) {
        	CEW345RResponse cew345RRes = dataOutput.getCew345RResponse();
        	//單筆金額五萬以下設定生效時間
        	String cew345rValidTime = service.getCEW345ValidTime();
			Date startTime = DateUtils.getDateByDateFormat(cew345rValidTime, "yyyy/MM/dd");
            for (CEW345RRepeat repeat : cew345RRes.getTxRepeats()) {
                CreditCard creditCard = allCreditCards.stream().filter(c -> StringUtils.equals(c.getCardNo(), repeat.getCrdNo())).findFirst().orElse(null);
                if (creditCard != null) {
                    NCCAG005CardInfo cardInfo = new NCCAG005CardInfo();
                    cardInfo.setCardNo(creditCard.getCardNo());
                    cardInfo.setCardNxMask(creditCard.getSimpleCardNo());
                    cardInfo.setCardName(creditCard.getCardName());
                    cardInfo.setChangeDate(repeat.getChangeDateA());
                    cardInfo.setChangeDateUnderFive(repeat.getChangeDateB());
                    cardInfo.setInsurFeeBenefits(InsurFeeBenefitsType.findByCode(repeat.getInsuTypeA()));
					// 單筆未達5萬元,若客戶原設定為「9期0利率」或「12期0利率」則預設顯示0.5%回饋
					if (StringUtils.equals(repeat.getInsuTypeB(),
							InsurFeeBenefitsType.NINE_PERIODS_ZERO_INTEREST_RATE.getCode())
							|| StringUtils.equals(repeat.getInsuTypeB(),
									InsurFeeBenefitsType.TWELVE_PERIODS_ZERO_INTEREST_RATE.getCode())) {
						cardInfo.setInsurFeeBenefitsUnderFive(InsurFeeBenefitsType.FEEDBACK);
					} else {
						cardInfo.setInsurFeeBenefitsUnderFive(InsurFeeBenefitsType.findByCode(repeat.getInsuTypeB()));
					}
                    
                    cardInfo.setCardType(repeat.getCrdTyp());
                    cardInfo.setCardKey(creditCard.getCardKey());
                    // 組成「保費權益設定」
            		if (cardInfo.getInsurFeeBenefitsUnderFive() != null) {
            			// 5萬元(含)以上：OO期0利率，最近變更YYYY/MM/DD
            			// 5萬元以下：OO期0利率，最近變更YYYY/MM/DD
            			StringBuilder insuType = new StringBuilder(0);
            			insuType.append(I18NUtils.getMessage("nccag005.insur_fee_benefits.insu_type_a"));
            			insuType.append(cardInfo.getInsurFeeBenefits().getI18NMemo());
            			if(repeat.getChangeDateA() != null) {
            				insuType.append(I18NUtils.getMessage("nccag005.insur_fee_benefits.recent-changes"));
            				insuType.append(DateUtils.getCEDateStr(repeat.getChangeDateA()));
            			}
            			insuType.append("<br>");
            			insuType.append(I18NUtils.getMessage("nccag005.insur_fee_benefits.insu_type_b"));
            			insuType.append(cardInfo.getInsurFeeBenefitsUnderFive().getI18NMemo());
						// 5萬元以下  ，若2024/4/1 09:00後有異動則帶最近變更日
						if (repeat.getChangeDateB() != null && startTime != null
								&& startTime.compareTo(repeat.getChangeDateB()) <= 0) {
							insuType.append(I18NUtils.getMessage("nccag005.insur_fee_benefits.recent-changes"));
							insuType.append(DateUtils.getCEDateStr(cardInfo.getChangeDateUnderFive()));
						}
            			//顯示保費權益
                        cardInfo.setShowMemo(insuType.toString());
            		} else {
            			//OO期0利率，最近變更YYYY/MM/DD
            			StringBuilder insuType = new StringBuilder(0);
            			insuType.append(cardInfo.getInsurFeeBenefits().getI18NMemo());
            			if(repeat.getChangeDateA() != null) {
            				insuType.append(I18NUtils.getMessage("nccag005.insur_fee_benefits.recent-changes"));
            				insuType.append(DateUtils.getCEDateStr(cardInfo.getChangeDate()));
            			}
            			//顯示保費權益
                        cardInfo.setShowMemo(insuType.toString());
            		}
                    cardInfoList.add(cardInfo);
                }
            }
        } else {
        	CEW327RResponse cew327RRes = dataOutput.getCew327RResponse();
            for (CEW327RRepeat repeat : cew327RRes.getTxRepeats()) {
                CreditCard creditCard = allCreditCards.stream().filter(c -> StringUtils.equals(c.getCardNo(), repeat.getCdno())).findFirst().orElse(null);
                if (creditCard != null) {
                    NCCAG005CardInfo cardInfo = new NCCAG005CardInfo();
                    cardInfo.setCardNo(creditCard.getCardNo());
                    cardInfo.setCardNxMask(creditCard.getSimpleCardNo());
                    cardInfo.setCardName(creditCard.getCardName());
                    cardInfo.setChangeDate(repeat.getChangedate());
                    cardInfo.setInsurFeeBenefits(InsurFeeBenefitsType.findByCode(repeat.getInsutype()));
                    cardInfo.setCardType(repeat.getCrdtyp());
                    cardInfo.setCardKey(creditCard.getCardKey());
                    //OO期0利率，最近變更YYYY/MM/DD
        			StringBuilder insuType = new StringBuilder(0);
        			insuType.append(cardInfo.getInsurFeeBenefits().getI18NMemo());
        			if(repeat.getChangedate() != null) {
        				insuType.append(I18NUtils.getMessage("nccag005.insur_fee_benefits.recent-changes"));
        				insuType.append(DateUtils.getCEDateStr(cardInfo.getChangeDate()));
        			}
        			//顯示保費權益
                    cardInfo.setShowMemo(insuType.toString());
                    cardInfoList.add(cardInfo);
                }
            }
        }

        if (CollectionUtils.isEmpty(cardInfoList)) {
            logger.info("客戶沒有擁有「可設定權益」的卡片。");
            throw ExceptionUtils.getActionException(ErrorCode.CHECK_INSURANCE_FEE_BENEFITS);
        }
        // 排序，以卡號大到小
        IBUtils.sort(cardInfoList, "cardNo", true);

        // 若有值，將該張信用卡排序至第一筆
        if (StringUtils.isNotBlank(rqData.getSelectedCardKey())) {
            NCCAG005CardInfo selected = cardInfoList.stream().filter(c -> StringUtils.equals(c.getCardKey(), rqData.getSelectedCardKey())).findFirst().orElseThrow(() -> ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND));
            cardInfoList = cardInfoList.stream().filter(c -> StringUtils.notEquals(c.getCardKey(), rqData.getSelectedCardKey())).collect(Collectors.toList());
            cardInfoList.add(0, selected);
        }

        rsData.setType(type);
        rsData.setCardInfoList(cardInfoList);

        NCCAG005CacheData cache = new NCCAG005CacheData();
        cache.setCardInfoList(cardInfoList);
        this.setCache(NCCAG005Service.CACHE_KEY, cache);

        // 傳遞至前端的資料，移除卡號
        rsData.getCardInfoList().stream().forEach(c -> {
            c.setCardNo(null);
        });
    }

}
