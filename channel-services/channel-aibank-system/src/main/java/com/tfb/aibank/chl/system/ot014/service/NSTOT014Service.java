package com.tfb.aibank.chl.system.ot014.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.i18n.I18NCacheManager;
import com.ibm.tw.ibmb.component.i18n.I18nModel;
import com.tfb.aibank.chl.component.userdatacache.model.EB032675Res;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.system.ot014.model.AnsItem;
import com.tfb.aibank.chl.system.ot014.model.NSTOT014Output;
import com.tfb.aibank.chl.system.ot014.model.QAItem;
import com.tfb.aibank.chl.system.resource.dto.KYCAnswerResponse;
import com.tfb.aibank.chl.system.resource.dto.PeopleSoftRes;
import com.tfb.aibank.chl.system.service.NSTService;

// @formatter:off
/**
 * @(#)NSTOT014Service.java
 * 
 * <p>Description:CHL NSTOT014 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NSTOT014Service extends NSTService {
    @Autowired
    private I18NCacheManager i18NCacheManager;

    /**
     * 取得投資風險資訊
     *
     * @param loginUser
     * @param locale
     * @param output
     */
    public void getInvestRisk(AIBankUser loginUser, Locale locale, NSTOT014Output output) {
        //A.	客戶的投資風險偏好等級
        output.setEbillCheck(loginUser.getLoginMsgRs().getEbillCheck());
        //B.	客戶前次測驗日期
        Calendar ebillStartDate = loginUser.getLoginMsgRs().getEbillStrDate();
        output.setEbillStartDate(ebillStartDate == null ? null : loginUser.getLoginMsgRs().getEbillStrDate().getTime());
        //C.	客戶風險偏好有效日期
        Calendar ebillEndDate = loginUser.getLoginMsgRs().getEbillEndDate();
        output.setEbillEndDate(ebillEndDate == null ? null : ebillEndDate.getTime());
        //取得投資風險偏好資訊
        output.setKycRiskLevelModel(investResource.getKycRiskLevel(loginUser.getLoginMsgRs().getEbillCheck(), locale.toString()));
    }

    /**
     * 取得kyc冷靜期及次數
     *
     * @param loginUser
     * @param output
     */
    public void getCoolPeriod(AIBankUser loginUser, NSTOT014Output output) {
        output.setCoolingPeriodResponse(investResource.queryCoolingPeriod("chkCoolingPeriod", loginUser.getCompanyVo().getCompanyUid()));
        output.setKycCountsResponse(investResource.queryKycCountsAndLimit("getKycCounts", loginUser.getCompanyVo().getCompanyUid()));
        String weekLimitS = systemParamCacheManager.getValue("PIB", "KYC_WEEK_MAX_COUNT");
        String dailyLimitS = systemParamCacheManager.getValue("PIB", "KYC_DAY_MAX_COUNT");
        output.setWeekLimit(ConvertUtils.str2Int(weekLimitS, 3));
        output.setDaliyLimit(ConvertUtils.str2Int(dailyLimitS, 1));
    }

    /**
     * 取得KYC第一部分
     *
     * @param loginUser
     * @param locale
     * @param output
     */
    public void getUserInformation(AIBankUser loginUser, Locale locale, NSTOT014Output output) {
        EB032675Res eb032675Res = userResource.sendEB032675(loginUser.getCustId());
        KYCAnswerResponse kycAnswerResponse = preferencesResource.sendEB12020006KYC(loginUser.getCompanyVo().getCompanyUid(), loginUser.getNameCode(), "06", "01");
        output.setEmail(kycAnswerResponse.getEmail());
        List<QAItem> qaItems = new ArrayList<>();
        QAItem qaItem1 = new QAItem(I18NUtils.getMessage("nstot014.question.q1"), i18nCacheManager.getSingleResult(locale, "KYC_EDU", kycAnswerResponse.getEduCod()).getValue());
        if (StringUtils.isNotBlank(qaItem1.getAnswer())) {
            qaItems.add(qaItem1);
        }
        // {職業選擇}選項 DIC.DIC_VALUE (CATEGORY = KYC_JOB_ITEM)
        Map<String, I18nModel> map = i18NCacheManager.getI18nByCategory(locale.toString(), "KYC_JOB_ITEM");
        List<I18nModel> jobList = new ArrayList<>(map.values());

        Optional<I18nModel> opt = jobList.stream().filter(data -> StringUtils.equals(StringUtils.substring(data.getKey(), data.getKey().length() - 4), kycAnswerResponse.getOccupation())).findAny();
        if (opt.isPresent()) {
            QAItem qaItem2 = new QAItem(I18NUtils.getMessage("nstot014.question.q2"), opt.get().getValue());
            if (StringUtils.isNotBlank(qaItem2.getAnswer())) {
                qaItems.add(qaItem2);
            }
        }

        if (StringUtils.equals(kycAnswerResponse.getMarrage(), "S") || StringUtils.equals(kycAnswerResponse.getMarrage(), "1")) {
            QAItem qaItem3 = new QAItem(I18NUtils.getMessage("nstot014.question.q3"), I18NUtils.getMessage("nstot014.answer.a3.n"));
            if (StringUtils.isNotBlank(qaItem3.getAnswer())) {
                qaItems.add(qaItem3);
            }
        }
        else if (StringUtils.equals(kycAnswerResponse.getMarrage(), "M") || StringUtils.equals(kycAnswerResponse.getMarrage(), "2")) {
            QAItem qaItem3 = new QAItem(I18NUtils.getMessage("nstot014.question.q3"), I18NUtils.getMessage("nstot014.answer.a3.y"));
            if (StringUtils.isNotBlank(qaItem3.getAnswer())) {
                qaItems.add(qaItem3);
            }
        }

        QAItem qaItem4 = new QAItem(I18NUtils.getMessage("nstot014.question.q4"), I18NUtils.getMessage("nstot014.answer.a4", kycAnswerResponse.getChildCod()));
        if (StringUtils.equals(kycAnswerResponse.getChildCod(), "4")) {
            // 子女人數 > 回應4應顯示"超過3人"
            qaItem4 = new QAItem(I18NUtils.getMessage("nstot014.question.q4"), I18NUtils.getMessage("nstot014.answer.a4More", 3));
        }
        if (StringUtils.equals(kycAnswerResponse.getChildCod(), "5")) {
            qaItem4 = new QAItem(I18NUtils.getMessage("nstot014.question.q4"), I18NUtils.getMessage("nstot014.answer.a4None"));
        }
        if (StringUtils.isNotBlank(kycAnswerResponse.getChildCod())) {
            qaItems.add(qaItem4);
        }
        QAItem qaItem5 = new QAItem(I18NUtils.getMessage("nstot014.question.q5"), " ");
        if (eb032675Res != null) {
            // 非Y顯示"無"
            // Y顯示"有，但不影響本人對投資商品及其風險之理解"
            qaItem5 = new QAItem(I18NUtils.getMessage("nstot014.question.q5"), StringUtils.isY(eb032675Res.getSickType()) ? I18NUtils.getMessage("nstot014.answer.a5.2") : I18NUtils.getMessage("nstot014.answer.a5.1"));
        }
        if (StringUtils.isNotBlank(qaItem5.getAnswer())) {
            qaItems.add(qaItem5);
        }
        output.setQaList(qaItems);
    }

    /**
     * 取得KYC第二部分
     *
     * @param aiBankUser
     * @param locale
     * @param idType
     * @param output
     */
    public void getPeopleSoft(AIBankUser aiBankUser, Locale locale, String idType, NSTOT014Output output) {
        List<PeopleSoftRes> res = investResource.getPeopleSoftList(aiBankUser.getCompanyVo().getCompanyUid(), locale.toString(), idType);
        List<QAItem> qaItems = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(res)) {
            for (PeopleSoftRes peopleSoftRes : res) {
                QAItem qaItem = null;
                if (peopleSoftRes.getAnswer().contains("_")) {
                    List<AnsItem> ansItemList = new ArrayList<>();
                    String[] split = peopleSoftRes.getAnswer().split(",");
                    int i = 0;
                    for (String s : split) {
                        String[] ans = s.split("_");
                        AnsItem ansItem = new AnsItem(numToLetterBySubstr(i + 1) + "." + ans[0], ans[1]);
                        ansItemList.add(ansItem);
                        i++;
                    }
                    qaItem = new QAItem(peopleSoftRes.getqNameC(), ansItemList);
                }
                else {
                    qaItem = new QAItem(peopleSoftRes.getqNameC(), peopleSoftRes.getAnswer());
                }
                qaItems.add(qaItem);
            }
        }
        output.setQaList2(qaItems);
    }

    private char numToLetterBySubstr(int i) {
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        if (i > 0 && i <= 25) {
            return LETTERS.substring(i - 1, i).charAt(0);
        }
        else {
            return '?';
        }
    }

}
