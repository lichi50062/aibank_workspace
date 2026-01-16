package com.tfb.aibank.chl.creditcard.tx005.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.model.LabelValueBean;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.FileUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.PathCleanUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.component.cityandarea.CityAndAreaCacheManager;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.resource.dto.CE6210RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW332RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW332RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CreditLimitsApplyData;
import com.tfb.aibank.chl.creditcard.resource.dto.EBCC001Response;
import com.tfb.aibank.chl.creditcard.resource.dto.EBCC002Request;
import com.tfb.aibank.chl.creditcard.resource.dto.EBCC002Response;
import com.tfb.aibank.chl.creditcard.service.NCCService;
import com.tfb.aibank.chl.creditcard.tx005.model.AdjustItemType;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005AdjustItem;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005CacheData;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005CreditCard;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005Output;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005ProofFile;
import com.tfb.aibank.chl.creditcard.tx005.model.NCCTX005WorkInfo;
import com.tfb.aibank.chl.creditcard.tx005.model.QuotaUsageType;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.type.TxStatusType;

// @formatter:off
/**
 * @(#)NCCTX005Service.java
 * 
 * <p>Description:CHL NCCTX005 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCCTX005Service extends NCCService {

    private static IBLog logger = IBLog.getLog(NCCTX005Service.class);

    public static final String CACHE_KEY = "NCCTX005_CACHE_KEY";

    @Autowired
    private CityAndAreaCacheManager cityAndAreaCacheManager;

    /**
     * 歸戶下附卡查詢電文
     * 
     * @param aibankUser
     * @param userLocale
     * @param dataOutput
     * @throws ActionException
     */
    public void sendCEW332R(AIBankUser aibankUser, Locale userLocale, NCCTX005Output dataOutput) throws ActionException {
        CEW332RResponse cew332rRes = creditCardResource.sendCEW332R(aibankUser.getCustId());
        if (StringUtils.notEquals(cew332rRes.getRspCode(), Constants.STATUS_CODE_SUCCESS)) {
            throw ExceptionUtils.getActionException(ErrorCode.UNABLE_TO_ADJUST_THE_AMOUNT);
        }
        // 若Email為空白或是不符合格式
        if (StringUtils.isBlank(cew332rRes.getVnMail()) || !ValidatorUtility.checkEMail(cew332rRes.getVnMail())) {
            throw ExceptionUtils.getActionException(ErrorCode.CREDIT_CARD_EMAIL_ERROR);
        }

        // 工作資訊
        dataOutput.setWorkInfo(new NCCTX005WorkInfo(cew332rRes));

        // 附卡資訊
        List<String> checkRepeat = new ArrayList<>();
        List<NCCTX005CreditCard> creditCards = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(cew332rRes.getTxRepeats())) {
            for (CEW332RRepeat rep : cew332rRes.getTxRepeats()) {
                if (checkRepeat.contains(rep.getVnHdid())) {
                    // ID重複，忽略這筆
                    continue;
                }
                NCCTX005CreditCard card = new NCCTX005CreditCard();
                card.setVnCnam(rep.getVnCnam());
                card.setVnHdid(rep.getVnHdid());
                card.setVnCpma(ConvertUtils.str2BigDecimal(rep.getVnCpma()).multiply(new BigDecimal("1000"))); // 乘以 1000
                card.setMaskCnam(DataMaskUtil.maskCustName(rep.getVnCnam()));
                card.setMaskHdid(DataMaskUtil.maskIdNo(rep.getVnHdid()));
                card.setVnCdno(rep.getVnCdno());
                creditCards.add(card);

                checkRepeat.add(rep.getVnHdid());
            }

            List<CreditCard> allCreditCards = userDataCacheService.getAllCreditCards(aibankUser, userLocale);
            for (Iterator<NCCTX005CreditCard> it2 = creditCards.iterator(); it2.hasNext();) {
                NCCTX005CreditCard creditCard = it2.next();
                // 判斷此卡片，是否屬於歸戶卡片的一員
                boolean isMatch = allCreditCards.stream().anyMatch(c -> StringUtils.equals(c.getCardNo(), creditCard.getVnCdno()));
                if (!isMatch) {
                    logger.info("此附卡不屬於歸戶卡片清單中，移除選項。卡號：{}", DataMaskUtil.maskCreditCard(creditCard.getVnCdno()));
                    it2.remove(); // 此附卡不屬於歸戶卡片清單中，移除選項
                    continue;
                }
            }
        }
        dataOutput.setCreditCards(creditCards);
    }

    /**
     * 取得「調整項目」選項
     * 
     * @param aibankUser
     * @param creditCards
     * @param userLocale
     * @param dataOutput
     * @throws ActionException
     */
    public void getAdjustItems(AIBankUser aibankUser, List<NCCTX005CreditCard> creditCards, Locale userLocale, NCCTX005Output dataOutput) throws ActionException {

        List<NCCTX005AdjustItem> adjustItems = prepareAdjustItems();

        for (Iterator<NCCTX005AdjustItem> it = adjustItems.iterator(); it.hasNext();) {
            NCCTX005AdjustItem adjustItem = it.next();
            switch (adjustItem.getItem()) {
            case TURN_UP:
                LabelValueBean bean1 = sendEBCC001(aibankUser.getCustId(), AdjustItemType.TURN_UP.getCode(), userLocale);
                if (bean1 != null) {
                    adjustItem.setEmsgId(bean1.getValue());
                    adjustItem.setEmsgTxt(bean1.getLabel());
                }
                break;
            case DOWNGRADE:
                LabelValueBean bean2 = sendEBCC001(aibankUser.getCustId(), AdjustItemType.DOWNGRADE.getCode(), userLocale);
                if (bean2 != null) {
                    adjustItem.setEmsgId(bean2.getValue());
                    adjustItem.setEmsgTxt(bean2.getLabel());
                }
                break;
            case ADDITIONAL_CARD_LIMIT:
                if (CollectionUtils.isNotEmpty(creditCards)) {
                    List<LabelValueBean> collect = new ArrayList<>();
                    List<String> checkRepeat = new ArrayList<>();
                    for (Iterator<NCCTX005CreditCard> it2 = creditCards.iterator(); it2.hasNext();) {
                        NCCTX005CreditCard creditCard = it2.next();

                        // 排除已發送電文的附卡人ID
                        if (checkRepeat.contains(creditCard.getVnHdid())) {
                            continue;
                        }

                        checkRepeat.add(creditCard.getVnHdid());

                        //20250716 by Tank, 正卡人調整附卡額度，不須檢核附卡狀態，EBCC001維持呼叫，但當bean非null時不排除
                        LabelValueBean bean = sendEBCC001(creditCard.getVnHdid(), AdjustItemType.ADDITIONAL_CARD_LIMIT.getCode(), userLocale);
                        //if (bean != null) {
                        //    collect.add(bean);
                        //    it2.remove(); // 此附卡不可啟案，移除選項
                        //}
                    }
                    // 表示所有附卡都不可啟案，將 EMSGID 用「；」串接
                    if (CollectionUtils.isEmpty(creditCards)) {
                        IBUtils.sort(collect, "value", false); // 依 EMSGID 升冪排序
                        adjustItem.setEmsgId(collect.stream().map(LabelValueBean::getValue).collect(Collectors.joining(";")));
                        adjustItem.setEmsgTxt(collect.get(0).getLabel());
                    }
                }
                else {
                    // 無附卡資訊，移除選項
                    it.remove();
                }
                break;
            }
        }

        // 所有調整項目皆不可啟案，抛出例外
        if (adjustItems.stream().allMatch(a -> StringUtils.isNotBlank(a.getEmsgId()))) {
            String emsgId = adjustItems.stream().map(NCCTX005AdjustItem::getEmsgId).collect(Collectors.toSet()).stream().collect(Collectors.joining(";"));
            logger.debug("所有調整項目皆不可啟案，EMSGID : {}", emsgId);
            // 取編號最小的代碼，對照錯誤代碼取得錯誤訊息。
            List<String> codes = Arrays.asList(emsgId.split(";"));
            codes.sort(String.CASE_INSENSITIVE_ORDER); // 按ASCII碼排序

            ErrorCode errorCode = getErrorCode(codes.get(0)); // 編號最小的代碼
            ActionException aex = ExceptionUtils.getActionException(errorCode);
            aex.setParams(emsgId);
            logger.error("所有調整項目皆不可啟案，抛出例外。ERROR_CODE:{}", errorCode);
            throw aex;
        }

        dataOutput.setAdjustItems(adjustItems);
    }

    /**
     * 固定額度啟案檢核電文
     * 
     * @param custid
     * @param adjType
     *            處理方式，1:調升 2:調降 3:附卡限額
     * @param locale
     * @param dataOutput
     */
    private LabelValueBean sendEBCC001(String custid, String adjType, Locale userLocale) throws ActionException {
        EBCC001Response response = creditCardResource.sendEBCC001(custid, adjType);
        // 表示有「不可啟案原因」
        if (response != null && StringUtils.isNotBlank(response.getEmsgId())) {
            return new LabelValueBean(response.getEmsgTxt(), response.getEmsgId());
        }
        return null;
    }

    /**
     * 依傳入的代碼，決定錯誤代碼
     * 
     * @param code
     * @return
     * @throws ActionException
     */
    private ErrorCode getErrorCode(String code) throws ActionException {
        switch (code) {
        case "01":
            return ErrorCode.QUOTA_ADJUSTMENT_IN_PROGRESS_WITH_MESGID;
        case "02":
        case "05":
            return ErrorCode.NO_VALID_CARD_WITH_MESGID;
        case "04":
        case "06":
        case "07":
        case "08":
        case "09":
            return ErrorCode.UNABLE_TO_ADJUST_THE_AMOUNT_WITH_MESGID;
        default:
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    /**
     * 準備「調整項目」下拉選單
     * 
     * @return
     */
    private List<NCCTX005AdjustItem> prepareAdjustItems() {
        List<NCCTX005AdjustItem> adjustItems = new ArrayList<>();
        for (AdjustItemType adjustItem : AdjustItemType.values()) {
            adjustItems.add(new NCCTX005AdjustItem(adjustItem));
        }
        return adjustItems;
    }

    /**
     * 查詢信用額度
     * 
     * @param aibankUser
     * @param userLocale
     * @param creditCards
     * @param dataOutput
     * @throws ActionException
     */
    public void getCreditLimit(AIBankUser aibankUser, Locale userLocale, List<NCCTX005CreditCard> creditCards, NCCTX005Output dataOutput) throws ActionException {

        CE6210RResponse ce6210RResponse = creditCardResource.getCardUserBaseInfo(aibankUser.getCustId());
        BigDecimal creditLimit = ce6210RResponse.getCreditAmt();
        dataOutput.setCreditLimit(creditLimit); // 目前信用額度

        // 發查 CE6210R 取得附卡持有人姓名，原本 CEW332R 電文下行就有中文姓名(VNCNAM)，客戶審閱規格後，要求改從 CE6210R 取
        Map<String, String> map = new HashMap<>();
        for (NCCTX005CreditCard creditCard : creditCards) {
            String id = creditCard.getVnHdid();
            String name = map.get(id);
            if (StringUtils.isBlank(name)) {
                ce6210RResponse = creditCardResource.getCardUserBaseInfo(id);
                name = ce6210RResponse.getCardHolderName();
            }
            creditCard.setCustName(name);
        }
    }

    /**
     * 取得「額度用途」下拉選單
     * 
     * @param dataOutput
     */
    public void getQuotaUsageList(NCCTX005Output dataOutput) {
        List<QuotaUsageType> quotaUsageList = new ArrayList<>();
        for (QuotaUsageType quotaUsage : QuotaUsageType.values()) {
            quotaUsageList.add(quotaUsage);
        }
        dataOutput.setQuotaUsageList(quotaUsageList);
    }

    /**
     * 檢核「免財力證明調額白名單」
     * 
     * @param custId
     * @throws ActionException
     */
    public void getCustomerCardApply(String custId, NCCTX005Output dataOutput) throws ActionException {
        dataOutput.setCustomerCardApply(this.creditCardResource.getCustomerCardApply(custId));
    }

    /**
     * 讀取「縣市」、「鄉鎮市區」資訊
     * 
     * @param userLocale
     * @param dataOutput
     * @throws ActionException
     */
    public void getCityAndArea(Locale userLocale, NCCTX005Output dataOutput) throws ActionException {
        dataOutput.setCities(cityAndAreaCacheManager.getCityList(userLocale));
    }

    /**
     * <ol>
     * <li>新增一筆額度調整交易資料</li>
     * <li>上傳檔案至 FTP</li>
     * <li>發送電文進行額度調整</li>
     * <li>更新額度調整交易資料</li>
     * </ol>
     * 
     * @param aibankUser
     * @param request
     * @param data
     * @param cache
     * @param dataOutput
     * @param accessLogEntity
     */
    public void doTransaction(AIBankUser aibankUser, EBCC002Request request, CreditLimitsApplyData data, NCCTX005CacheData cache, NCCTX005Output dataOutput, MBAccessLog accessLogEntity) {

        // 新增一筆額度調整交易資料
        CreditLimitsApplyData txnData = creditCardResource.saveCreditLimitsApplyData(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getCompanyKind(), data);

        // 調高固定額度，才會有上傳檔案的行為
        if (StringUtils.equals(txnData.getTxType(), "1") && CollectionUtils.isNotEmpty(cache.getProofFiles())) {
            // 上傳檔案至 FTP
            List<String> fileNameList = cache.getProofFiles().stream().map(NCCTX005ProofFile::getName).collect(Collectors.toList());
            boolean uploadResult = creditCardResource.uploadProofFile(fileNameList);
            logger.info("財力證明檔案上傳結果：{}", uploadResult);
            if (uploadResult) {
                txnData.setUploadTime(new Date()); // 上傳檔案完成系統時間
            }
        }

        // 發送電文進行額度調整
        doEBCC002(request, txnData, accessLogEntity);

        // 更新額度調整交易資料
        creditCardResource.updateCreditLimitsApplyData(txnData);

        dataOutput.setCreditLimitsApplyData(txnData);
    }

    private void doEBCC002(EBCC002Request request, CreditLimitsApplyData txnData, MBAccessLog accessLogEntity) {
        String errorSystemId = StringUtils.EMPTY;
        String errorCode = StringUtils.EMPTY;
        String errorDesc = StringUtils.EMPTY;
        ServiceException sex = null;
        BaseServiceResponse<EBCC002Response> response = null;
        EBCC002Response res = null;
        try {
            response = creditCardResource.sendEBCC002(request);
            errorCode = response.getStatus().getErrorCode();
            if (StringUtils.equals(errorCode, Constants.STATUS_CODE_SUCCESS)) {
                res = response.getData();
            }
        }
        catch (ServiceException ex) {
            sex = ex;
            logger.error(ex.getMessage(), ex);
            ActionException aex = handleException(accessLogEntity, ex);
            errorSystemId = aex.getSystemId();
            errorCode = aex.getErrorCode();
            errorDesc = aex.getErrorDesc();
        }
        finally {
            // 交易失敗才寫入
            if (StringUtils.notEquals(errorCode, Constants.STATUS_CODE_SUCCESS)) {
                txnData.setTxErrorSystemId(errorSystemId);
                txnData.setTxErrorCode(errorCode);
                txnData.setTxErrorDesc(errorDesc);
            }
            // 上送主機交易時間
            txnData.setHostTxTime(new Date());
            txnData.setTxStatus(getTxStatusType(sex).getCode());
            // 交易成功才寫入
            if (TxStatusType.SUCCESS.getCode().equals(txnData.getTxStatus())) {
                if (res != null) {
                    txnData.setApplyNo(res.getApplyno()); // EBCC002.APPLYNO
                }
            }
        }
    }

    /**
     * 儲存財力證明檔案
     * 
     * @param aibankUser
     * @param proofFiles
     * @throws ActionException
     */
    public void saveProofFiles(AIBankUser aibankUser, List<NCCTX005ProofFile> proofFiles) throws ActionException {
        if (CollectionUtils.isNotEmpty(proofFiles)) {
            final NumberFormat nf = new DecimalFormat("000");
            int seq = 1;
            for (NCCTX005ProofFile proofFile : proofFiles) {
                StringBuilder sb = new StringBuilder(0);
                sb.append(DateUtils.getROCDateStr(new Date(), StringUtils.EMPTY)).append("_");
                sb.append(aibankUser.getCustId()).append("_9_");
                sb.append(nf.format(seq)).append(".").append(FilenameUtils.getExtension(proofFile.getName()));
                // file rename
                proofFile.setName(sb.toString());
                seq++;

                // save file
                String directory = getValue(AIBankConstants.CHANNEL_NAME, "SHARED_FOLDER") + File.separator + "NCCTX005";
                if (PathCleanUtils.isLocalPathValid(PathCleanUtils.cleanPath(directory))) {
                    File file = new File(PathCleanUtils.cleanPath(directory), PathCleanUtils.cleanPath(proofFile.getName()));
                    try {
                        FileUtils.writeBase64StrToFile(file, proofFile.getBase64Image());
                        logger.info("儲存檔案成功。檔案：{}", file.getCanonicalPath());
                    }
                    catch (IOException e) {
                        throw ExceptionUtils.getActionException(e);
                    }
                }
            }
        }
    }

}
