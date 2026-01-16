/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.util;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.i18n.I18NCacheManager;
import com.ibm.tw.ibmb.component.i18n.I18nModel;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.mfdinfo.MfdInfo;
import com.tfb.aibank.chl.component.mfdinfo.MfdInfoCacheManager;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.model.fund.Terms;
import com.tfb.aibank.chl.type.RemarkContentType;
import com.tfb.aibank.component.dic.DicCacheManager;

// @formatter:off
/**
 * @(#)TermsUtils.java
 * 
 * <p>Description:條款工具類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/03, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class TermsUtils {

    private final IBLog logger = IBLog.getLog(TermsUtils.class);

    @Autowired
    private MfdInfoCacheManager mfdInfoCacheManager;
    @Autowired
    private DicCacheManager dicCacheManager;
    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;
    @Autowired
    private I18NCacheManager i18NCacheManager;

    /** DIC 中，基金相關交易Category */
    public static final String BUY_FUND_CATEGORY = "BUYFUND";

    /**
     * 基金公開說明書
     * 
     * @param fundCode
     * @throws ActionException
     */
    public Terms getTerms4FundDisclosureProspectus(String fundCode) throws ActionException {
        String value = dicCacheManager.getValue(BUY_FUND_CATEGORY, "URL_FUND_PROSPECTUS");
        String link = MessageFormat.format(value, fundCode);
        if (StringUtils.isBlank(value)) {
            logger.error("基金公開說明書，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        Terms terms = new Terms();
        terms.setLink(link);
        return terms;
    }

    /**
     * 通路報酬、費用率及報酬率
     * 
     * @param fundCode
     * @throws ActionException
     */
    public Terms getTerms4ChannelRemuneration(String fundCode) throws ActionException {
        String value = dicCacheManager.getValue(BUY_FUND_CATEGORY, "URL_FUND_RETURN");
        String link = MessageFormat.format(value, fundCode);
        if (StringUtils.isBlank(value)) {
            logger.error("通路報酬、費用率及報酬率，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        Terms terms = new Terms();
        terms.setLink(link);
        return terms;
    }

    /**
     * 電子服務相關規定
     * 
     * @param fundCategory
     * @param txType
     * @param userLocale
     */
    public Terms getTerms4ElectronicServicesRegulations(String fundCategory, String txType, Locale userLocale) throws ActionException {
        String label = "電子服務相關規定";
        RemarkContent remarkContent = null;
        if (StringUtils.equalsAny(fundCategory, "0002", "0003") && StringUtils.equalsAny(txType, "Y", "A")) {
            remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "MFD_CONTRACT_PROJ", userLocale);
        }
        else {
            remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "MFD_CONTRACT", userLocale);
        }
        if (remarkContent == null) {
            logger.error("電子服務相關規定，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, remarkContent);
    }

    /**
     * 電子服務相關規定 for [基金申購][FUND心投]
     * 
     * @param fundProject
     *            是否是fund心投
     * @param userLocale
     */
    public Terms getTerms4ElectronicServicesRegulationsFundProj(boolean fundProject, Locale userLocale) throws ActionException {
        String label = "電子服務相關規定";
        RemarkContent remarkContent = null;
        if (!fundProject) {
            remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "MFD_CONTRACT", userLocale);
        }
        else {
            remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "MFD_CONTRACT_PROJ", userLocale);
        }
        if (remarkContent == null) {
            logger.error("電子服務相關規定，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, remarkContent);
    }

    /**
     * 檢查基金標的，是否為「後收型」
     * 
     * @param fundCode
     * @param userLocale
     * @return
     */
    public boolean checkBankendFunds(String fundCode, Locale userLocale) {
        MfdInfo mfdInfo = mfdInfoCacheManager.getMfdInfo(fundCode, userLocale.toString());
        return mfdInfo != null && StringUtils.isY(mfdInfo.getIsBackEnd());
    }

    /**
     * 後收型基金約定事項
     * 
     * @throws ActionException
     */
    public Terms getTerms4BankendFundAgreed() throws ActionException {
        String label = "後收型基金約定事項";
        String title = "後收型基金約定事項";
        String link = dicCacheManager.getValue(BUY_FUND_CATEGORY, "BACKEND_FUNDS_URL");
        if (StringUtils.isBlank(link)) {
            logger.error("後收型基金約定事項，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, title, null, link);
    }

    /**
     * <pre>
     * 檢查基金標的，是否為「境外後收型」
     * [後收基金] -> MFD_INFO.ISBACKEND == Y
     * [境外基金] -> MFD_INFO.REGION IS NULL AND MFD_INFO.FUND_VALUATION IS NULL
     * </pre>
     * 
     * @param fundCode
     * @param userLocale
     * @return
     */
    public boolean checkOverseasFundsBackend(String fundCode, Locale userLocale) {
        MfdInfo mfdInfo = mfdInfoCacheManager.getMfdInfo(fundCode, userLocale.toString());
        return mfdInfo != null && StringUtils.isY(mfdInfo.getIsBackEnd()) && StringUtils.isBlank(mfdInfo.getRegion()) && StringUtils.isBlank(mfdInfo.getFundValuation());
    }

    /**
     * 檢查基金標的，是否為「目標到期型」
     * 
     * @param fundCode
     * @param userLocale
     * @return
     */
    public boolean checkTargetMaturityFund(String fundCode, Locale userLocale) {
        return StringUtils.isNotBlank(getTargetMaturityFundLinkURL(fundCode, userLocale));
    }

    private String getTargetMaturityFundLinkURL(String fundCode, Locale userLocale) {

        String link = StringUtils.EMPTY;
        MfdInfo mfdInfo = mfdInfoCacheManager.getMfdInfo(fundCode, userLocale.toString());
        if (mfdInfo != null && StringUtils.isY(mfdInfo.getIsTarget())) {
            String[] groupList = { "A", "B", "C" };
            for (String group : groupList) {
                String value = dicCacheManager.getValue(BUY_FUND_CATEGORY, "TARGET_FUND_" + group);
                if (StringUtils.indexOf(value, fundCode) != -1) {
                    link = dicCacheManager.getValue(BUY_FUND_CATEGORY, String.format("TARGET_FUND_%s_URL", group));
                    break;
                }
            }
        }
        return link;
    }

    /**
     * 目標到期型基金風險預告書
     * 
     * @param fundCode
     * @param userLocale
     * @throws ActionException
     */
    public Terms getTerms4TargetMaturityFundRiskNotice(String fundCode, Locale userLocale) throws ActionException {
        String label = "目標到期型基金風險預告書";
        String title = "目標到期型基金風險預告書";
        String link = getTargetMaturityFundLinkURL(fundCode, userLocale);
        if (StringUtils.isBlank(link)) {
            logger.error("目標到期型基金風險預告書，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, title, null, link);
    }

    /**
     * 商品風險告知重要事項
     * 
     * @param userLocale
     * @throws ActionException
     */
    public Terms getTerms4ProductRiskInformation(Locale userLocale) throws ActionException {
        String label = "商品風險告知重要事項";
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "MFD_SPECIAL_CLAIM", userLocale);
        if (remarkContent == null) {
            logger.error("商品風險告知重要事項，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, remarkContent);
    }

    /**
     * 基金風險預告事項
     * 
     * @param userLocale
     * @throws ActionException
     */
    public Terms getTerms4FundRiskWarning(Locale userLocale) throws ActionException {
        String label = "基金風險預告事項";
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "MFD_RISK_STATEMENT", userLocale);
        if (remarkContent == null) {
            logger.error("基金風險預告事項，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, remarkContent);
    }

    /**
     * 貸款投資風險說明
     *
     * @param userLocale
     * @throws ActionException
     */
    public Terms getLoanToInvestRiskRemarkContent(Locale userLocale) throws ActionException {
        String label = "貸款投資風險說明";
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "MFD_LOANTOINVEST", userLocale);
        if (remarkContent == null) {
            logger.error("貸款投資風險說明，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, remarkContent);
    }

    private String getI18NValue(Locale userLocale, String category, String key) {
        I18nModel i18nModel = i18NCacheManager.getSingleResult(userLocale, category, key);
        return i18nModel == null ? StringUtils.EMPTY : i18nModel.getValue();
    }

    /**
     * 電子服務相關規定 for 結構型商品
     * 
     * @param userLocale
     * @return
     * @throws ActionException
     */
    public Terms getTerms4ElectronicServicesRegulationsSN(Locale userLocale) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_01_TITLE");
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "NNFTX007_050_01_TERMS", userLocale);
        String warning = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_01_NOTES");
        if (remarkContent == null || StringUtils.isAllBlank(label, warning)) {
            logger.error("電子服務相關規定，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, remarkContent, warning);
    }

    /**
     * 產品說明書客戶須知暨收執證明
     * 
     * @param userLocale
     * @return
     * @throws ActionException
     */
    public Terms getTerms4ProductBrochureSN(Locale userLocale, String fileLink) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_02_TITLE");
        String title = I18NUtils.getMessage("terms.please_click_open_the_file"); // 請點擊開啟檔案
        String link = fileLink;
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.MEMO_EXTENSION, "NNFTX007_050_02_NOTES", userLocale);
        String warning = remarkContent != null ? remarkContent.getContent() : null;
        if (StringUtils.isAllBlank(label, title, link, warning)) {
            logger.error("產品說明書客戶須知暨收執證明，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        Terms terms = new Terms(label, title, null, link);
        terms.setWarning(warning);
        terms.setRemarkKey(remarkContent != null ? remarkContent.getRemarkKey() : null);
        return terms;
    }

    /**
     * 申購提醒事項
     * 
     * @param userLocale
     * @return
     * @throws ActionException
     */
    public Terms getTerms4PurchaseReminderSN(Locale userLocale) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_03_TITLE");
        RemarkContent remarkContent1 = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "NNFTX007_050_03_TERMS01", userLocale);
        Terms terms = new Terms(label, remarkContent1);
        RemarkContent remarkContent2 = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "NNFTX007_050_03_TERMS02", userLocale);
        String title2 = remarkContent2.getTitle();
        String content2 = remarkContent2.getContent();
        terms.setTitle2(title2);
        terms.setContent2(content2);
        terms.setRemarkKey2(remarkContent2.getRemarkKey());
        if (remarkContent1 == null || remarkContent2 == null || StringUtils.isAllBlank(label, title2, content2)) {
            logger.error("申購提醒事項，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return terms;
    }

    /**
     * 商品風險告知重要事項
     * 
     * @param userLocale
     * @throws ActionException
     */
    public Terms getTerms4ProductRiskInformationSN(Locale userLocale) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_04_TITLE");
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "NNFTX007_050_04_TERMS", userLocale);
        String warning = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_04_NOTES");
        if (remarkContent == null || StringUtils.isAllBlank(label, warning)) {
            logger.error("商品風險告知重要事項，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, remarkContent, warning);
    }

    /**
     * 貸款投資風險說明
     * 
     * @param userLocale
     * @return
     * @throws ActionException
     */
    public Terms getTerms4LoanToInvestRiskRemarkContentSN(Locale userLocale) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_05_TITLE");
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "NNFTX007_050_05_TERMS", userLocale);
        String warning = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_05_NOTES");
        if (remarkContent == null || StringUtils.isAllBlank(label, warning)) {
            logger.error("貸款投資風險說明，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, remarkContent, warning);
    }

    /**
     * 審閱期聲明(一個選項)
     * 
     * @param userLocale
     * @return
     * @throws ActionException
     */
    public Terms getTerms4ReviewPeriodStatementCheckbox(Locale userLocale) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_06_TITLE");
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.MEMO_EXTENSION, "NNFTX007_050_06_NOTES", userLocale);
        String title = remarkContent.getContent();
        String content = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_06_TERMS01");
        if (StringUtils.isAllBlank(label, title, content)) {
            logger.error("審閱期聲明(一個選項)，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        Terms terms = new Terms(label, title, content);
        terms.setRemarkKey(remarkContent.getRemarkKey());
        return terms;
    }

    /**
     * 審閱期聲明(二個選項)
     * 
     * @param userLocale
     * @return
     * @throws ActionException
     */
    public Terms getTerms4ReviewPeriodStatementRadio(Locale userLocale) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_06_TITLE");
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.MEMO_EXTENSION, "NNFTX007_050_06_NOTES", userLocale);
        String title = remarkContent.getContent();
        String content = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_06_TERMS01");
        String content2 = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_06_TERMS02");
        if (StringUtils.isAllBlank(label, title, content, content2)) {
            logger.error("審閱期聲明(二個選項)，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        Terms terms = new Terms(label, title, content);
        terms.setContent2(content2);
        terms.setRemarkKey(remarkContent.getRemarkKey());
        return terms;
    }

    /**
     * 不保本交易自主聲明書-A1
     * 
     * @param userLocale
     * @param grntRate
     * @return
     * @throws ActionException
     */
    public Terms getTerms4NonGuaranteedTransactionDeclarationA1(Locale userLocale, BigDecimal grntRate) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_07_TITLE");
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "NNFTX007_050_07_TERMS_A1", userLocale);
        if (remarkContent == null || StringUtils.isBlank(label)) {
            logger.error("不保本交易自主聲明書(A1)，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        Terms terms = new Terms(label, remarkContent);
        terms.setContent(StringUtils.replace(terms.getContent(), "{{position}}", IBUtils.formatMoneyStr(grntRate)));
        return terms;
    }

    /**
     * 不保本交易自主聲明書-A2
     * 
     * @param userLocale
     * @return
     * @throws ActionException
     */
    public Terms getTerms4NonGuaranteedTransactionDeclarationA2(Locale userLocale) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_07_TITLE");
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "NNFTX007_050_07_TERMS_A2", userLocale);
        if (remarkContent == null || StringUtils.isBlank(label)) {
            logger.error("不保本交易自主聲明書(A2)，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, remarkContent);
    }

    /**
     * 不保本交易自主聲明書-B1
     * 
     * @param userLocale
     * @param grntRate
     * @return
     * @throws ActionException
     */
    public Terms getTerms4NonGuaranteedTransactionDeclarationB1(Locale userLocale, BigDecimal grntRate) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_07_TITLE");
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "NNFTX007_050_07_TERMS_B1", userLocale);
        if (remarkContent == null || StringUtils.isBlank(label)) {
            logger.error("不保本交易自主聲明書(B1)，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        Terms terms = new Terms(label, remarkContent);
        terms.setContent(StringUtils.replace(terms.getContent(), "{{position}}", IBUtils.formatMoneyStr(grntRate)));
        return terms;
    }

    /**
     * 不保本交易自主聲明書-B2
     * 
     * @param userLocale
     * @return
     * @throws ActionException
     */
    public Terms getTerms4NonGuaranteedTransactionDeclarationB2(Locale userLocale) throws ActionException {
        String label = getI18NValue(userLocale, "STRUCTURED_NOTE", "NNFTX007_050_07_TITLE");
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "NNFTX007_050_07_TERMS_B2", userLocale);
        if (remarkContent == null || StringUtils.isBlank(label)) {
            logger.error("不保本交易自主聲明書(B2)，查無條款資料。");
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        return new Terms(label, remarkContent);
    }

}
