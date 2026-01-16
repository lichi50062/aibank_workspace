package com.tfb.aibank.biz.pushlistener.services.personsubscription;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.collections.CollectionUtils;

import com.google.gson.JsonObject;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.pushlistener.repository.CompanyRepository;
import com.tfb.aibank.biz.pushlistener.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.pushlistener.repository.OfferContentDetailRepository;
import com.tfb.aibank.biz.pushlistener.repository.OfferMasterRepository;
import com.tfb.aibank.biz.pushlistener.repository.OfferNotificationInfoRepository;
import com.tfb.aibank.biz.pushlistener.repository.UserRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.CustomizedNotificationRecordRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.PushSubscriptionRepository;
import com.tfb.aibank.biz.pushlistener.repository.aib.entities.CustomizedNotificationRecordEntity;
import com.tfb.aibank.biz.pushlistener.repository.aib.entities.MbDevicePushInfoEntity;
import com.tfb.aibank.biz.pushlistener.repository.aib.entities.PushSubscriptionEntity;
import com.tfb.aibank.biz.pushlistener.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.pushlistener.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.pushlistener.repository.entities.OfferContentDetailEntity;
import com.tfb.aibank.biz.pushlistener.repository.entities.OfferMasterEntity;
import com.tfb.aibank.biz.pushlistener.repository.entities.OfferNotificationInfoEntity;
import com.tfb.aibank.biz.pushlistener.repository.entities.UserEntity;
import com.tfb.aibank.biz.pushlistener.services.personsubscription.model.PersonSubscriptionContent;
import com.tfb.aibank.biz.pushlistener.services.personsubscription.model.PersonSubscriptionPushModel;
import com.tfb.aibank.biz.pushlistener.services.personsubscription.model.PersonSubscriptionPushModel.Offer;
import com.tfb.aibank.biz.pushlistener.services.personsubscription.model.PersonSubscriptionPushModel.Result;
import com.tfb.aibank.biz.pushlistener.services.personsubscription.model.PushSubscriptionStatus;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.component.dic.DicCacheManager;
import com.tfb.aibank.component.dic.DicData;

//@formatter:off
/**
* @(#)PersonSubscriptionService.java
* 
* <p>Description:推播「個人化訂閱」</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/15, Evan 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class PersonSubscriptionService {
    private static IBLog logger = IBLog.getLog(PersonSubscriptionService.class);

    private static final String PERSON_SUBSCRIPTION_FLAG = "ESMBT041";

    private static final String TEMPLATE_ID = "A1";

    private static AESCipherUtils aesCipherUtils = null;

    private OfferMasterRepository offerMasterRepository;

    private CustomizedNotificationRecordRepository customizedNotificationRecordRepository;

    private OfferContentDetailRepository offerContentDetailRepository;

    private OfferNotificationInfoRepository offerNotificationInfoRepository;

    private UserRepository userRepository;

    private SystemParamCacheManager systemParamCacheManager;

    private DicCacheManager dicCacheManager;

    private MbDeviceInfoRepository mbDeviceInfoRepository;

    private MbDevicePushInfoRepository mbDevicePushInfoRepository;

    private PushSubscriptionRepository pushSubscriptionRepository;

    private CompanyRepository companyRepository;

    public PersonSubscriptionService(OfferMasterRepository offerMasterRepository, CustomizedNotificationRecordRepository customizedNotificationRecordRepository, OfferContentDetailRepository offerContentDetailRepository, OfferNotificationInfoRepository offerNotificationInfoRepository, UserRepository userRepository, SystemParamCacheManager systemParamCacheManager, DicCacheManager dicCacheManager, MbDeviceInfoRepository mbDeviceInfoRepository, MbDevicePushInfoRepository mbDevicePushInfoRepository, PushSubscriptionRepository pushSubscriptionRepository, CompanyRepository companyRepository) {
        this.offerMasterRepository = offerMasterRepository;
        this.customizedNotificationRecordRepository = customizedNotificationRecordRepository;
        this.offerContentDetailRepository = offerContentDetailRepository;
        this.offerNotificationInfoRepository = offerNotificationInfoRepository;
        this.userRepository = userRepository;
        this.systemParamCacheManager = systemParamCacheManager;
        this.dicCacheManager = dicCacheManager;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.mbDevicePushInfoRepository = mbDevicePushInfoRepository;
        this.pushSubscriptionRepository = pushSubscriptionRepository;
        this.companyRepository = companyRepository;
    }

    /**
     * 加密工具
     * 
     * @return
     */
    private AESCipherUtils getAESCipherUtils() {
        if (aesCipherUtils == null) {
            SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
            aesCipherUtils = new AESCipherUtils(provider);
        }
        return aesCipherUtils;
    }

    /**
     * 用戶訂閱推薦處理
     * 
     * @param message
     */
    public void handle(String message) {
        PersonSubscriptionPushModel pushModel = JsonUtils.getB2EObject(message, PersonSubscriptionPushModel.class);
        if (pushModel != null && CollectionUtils.isNotEmpty(pushModel.getResult())) {
            byte[] key = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DATA_PLATFORM_ENCRYPT_KEY").getBytes();
            byte[] iv = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DATA_PLATFORM_ENCRYPT_IV").getBytes();

            pushModel.getResult().forEach((resultObj) -> {
                transResult(resultObj, key, iv);
            });
        }
        else {
            logger.info("{}", PERSON_SUBSCRIPTION_FLAG);
        }

    }

    /**
     * 個人化 用戶訂閱推薦
     * 
     * @param result
     */
    private void transResult(Result result, byte[] key, byte[] iv) {

        logger.info("當前處理資料" + PERSON_SUBSCRIPTION_FLAG + ",userTag: " + JsonUtils.getJson(result.getUserTag()) + ", OfferList:" + JsonUtils.getJson(result.getOfferList()) + ",id後五碼" + StringUtils.right(result.getId(), 5));
        String deCodeId = dataCenterDecode(result.getId(), key, iv);
        result.setId(deCodeId);
        List<CustomizedNotificationRecordEntity> recordEntities = new ArrayList<>();

        Map<String, OfferNotificationInfoEntity> offerNotificationInfoMap = new HashMap<>();

        if (CollectionUtils.isEmpty(result.getOfferList())) {
            // // 一直印log 會爆掉 直接看無法解析 id
            logger.info("{} OfferList無推播資料", PERSON_SUBSCRIPTION_FLAG);
            return;
        }
        List<UserEntity> userEntities = queryUserByCompanyUidAndDup(result.getId());
        if (CollectionUtils.isEmpty(userEntities)) {
            logger.info("{} DB 無此id資料", PERSON_SUBSCRIPTION_FLAG);
            return;
        }
        List<List<String>> allProductNoAndPersonalNo = new ArrayList<>();
        for (var tp01 : result.getUserTag().getTp01()) {
            List<String> productNoAndPersonalNo = genProductNoAndPersonalNo(tp01);
            if (CollectionUtils.isNotEmpty(productNoAndPersonalNo)) {
                allProductNoAndPersonalNo.add(productNoAndPersonalNo);
            }
        }

        for (Offer offer : result.getOfferList()) {
            logger.info("{}當前處理offer:{}", PERSON_SUBSCRIPTION_FLAG, JsonUtils.getJson(offer));
            // 查詢 OFFER_MASTER
            List<OfferMasterEntity> offerMasterEntities = offerMasterRepository.queryByOfferId(offer.getOfferId());

            if (CollectionUtils.isEmpty(offerMasterEntities)) {
                logger.info("{} ID({})無OFFER_MASTER資料，OFFER_ID：{}", PERSON_SUBSCRIPTION_FLAG, result.getId(), offer.getOfferId());
                continue;
            }

            // 0517 已確認 會哪多筆offerMasterEntity 一筆一筆比對
            for (OfferMasterEntity offerMasterEntity : offerMasterEntities) {
                Integer offerMasterKey = offerMasterEntity.getOfferMasterKey();
                // 查詢 OFFER_CONTENT_DETAIL
                List<OfferContentDetailEntity> offerContentDetailListByA10 = offerContentDetailRepository.findByTemplateId(TEMPLATE_ID);

                List<OfferContentDetailEntity> offerContentDetailEntities = new ArrayList<>();
                List<String> currentProductNoAndPersonalNo = new ArrayList<>();
                for (var productNoAndPersonalNo : allProductNoAndPersonalNo) {
                    if (StringUtils.equals(productNoAndPersonalNo.get(0), offerMasterEntity.getProductType1())) {
                        currentProductNoAndPersonalNo.addAll(productNoAndPersonalNo);
                    }
                }
                // userTag 處理
                if (CollectionUtils.isNotEmpty(currentProductNoAndPersonalNo)) {
                    List<OfferContentDetailEntity> offerContentDetailEntByProductNoAndPersonalNo = getOfferContentDetailList(offerMasterEntity, currentProductNoAndPersonalNo, result.getUserTag().getTf01(), PERSON_SUBSCRIPTION_FLAG, offerContentDetailListByA10);
                    offerContentDetailEntities.addAll(offerContentDetailEntByProductNoAndPersonalNo);
                }
                else {
                    List<OfferContentDetailEntity> offerContentDetailEntByProductNoAndPersonalNo = getOfferContentDetailList(offerMasterEntity, new ArrayList<>(), result.getUserTag().getTf01(), PERSON_SUBSCRIPTION_FLAG, offerContentDetailListByA10);
                    offerContentDetailEntities.addAll(offerContentDetailEntByProductNoAndPersonalNo);
                }

                if (CollectionUtils.isEmpty(offerContentDetailEntities)) {
                    logger.info("{} ID({})無OFFER_CONTENT_DETAIL資料，OFFER_MASTER_KEY：{}", PERSON_SUBSCRIPTION_FLAG, result.getId(), offerMasterKey);
                    continue;
                }
                // 查詢 OFFER_NOTIFICATION_INFO
                OfferNotificationInfoEntity offerNotificationInfoEntity = getOfferNotificationInfo(offerMasterKey, TEMPLATE_ID, offerNotificationInfoMap);
                if (offerNotificationInfoEntity == null) {
                    logger.info("{} ID({})無OFFER_NOTIFICATION_INFO資料，OFFER_MASTER_KEY：{}", PERSON_SUBSCRIPTION_FLAG, result.getId(), offerMasterKey);
                    continue;
                }
                List<PushSubscriptionStatus> pushSubscriptionStatus = getPushSubscriptionStatus(result.getId(), "");
                // 更新 當前id發送次數 由於前面如果有無資料狀況 流程會等於無效
                for (OfferContentDetailEntity offerContentDetailEntity : offerContentDetailEntities) {
                    for (PushSubscriptionStatus subscriptionStatus : pushSubscriptionStatus) {
                        if (subscriptionStatus.isEnable() && StringUtils.equalsAny(String.valueOf(subscriptionStatus.getNotifyPass()), "1", "2")) {
                            Integer userKey = subscriptionStatus.getUserKey();
                            CustomizedNotificationRecordEntity entity = new CustomizedNotificationRecordEntity();
                            entity.setCompanyKey(subscriptionStatus.getCompanyKey());
                            entity.setUserKey(userKey);
                            entity.setIsPush(checkIsPush(subscriptionStatus.getNotifyPass()) ? 1 : 0);
                            entity.setPriority(9);
                            String offerContentDetail = offerContentDetailEntity.getContent();
                            if (offer.getOfferInfo() != null) {
                                offerContentDetail = findKeyReplace(offerContentDetail, offer.getOfferInfo());
                            }
                            PersonSubscriptionContent content = JsonUtils.getB2EObject(offerContentDetail, PersonSubscriptionContent.class);
                            if (Objects.nonNull(content)) {
                                // 推播訊息
                                entity.setPushMessage(content.getPushMessage());
                                entity.setPushParam(content.getUrl());
                                entity.setTitleMessage(content.getTitle());
                                entity.setMessage(content.getContent());
                                entity.setMessageLinkTitle(content.getLinkName());
                                entity.setMessageLink(content.getLink());
                                entity.setMessageParam("{}");
                            }
                            else {
                                logger.info("{} OfferInfo 解析後為空 offerContentDetail:{},", PERSON_SUBSCRIPTION_FLAG, offerContentDetail);
                                logger.info(PERSON_SUBSCRIPTION_FLAG + JsonUtils.getJson(offer));
                            }
                            entity.setBusType(offerNotificationInfoEntity.getType());
                            entity.setSendStatus("W");
                            entity.setStatus("O");
                            entity.setIsRead(0);
                            entity.setIsAction(0);
                            entity.setStartDate(new Date());
                            if (Objects.isNull(offerNotificationInfoEntity.getEffectiveDate())) {
                                entity.setEndDate(offerMasterEntity.getEndDate());
                            }
                            else {
                                Integer effectiveAddDay = ConvertUtils.str2Int(offerNotificationInfoEntity.getEffectiveDate(), 0);
                                Date effectiveDate = DateUtils.addDays(new Date(), effectiveAddDay > 0 ? effectiveAddDay : 0);
                                entity.setEndDate(effectiveDate);
                            }
                            entity.setUpdateTime(new Date());
                            entity.setCreateTime(new Date());
                            entity.setPushTime(new Date());
                            entity.setOfferId(offer.getOfferId());
                            recordEntities.add(entity);
                        }

                    }
                }

            }
        }
        logger.info("{} 當前id後五碼：{} 新增 customizedNotificationRecord 筆數 {}", PERSON_SUBSCRIPTION_FLAG, StringUtils.right(result.getId(), 5), recordEntities.size());
        customizedNotificationRecordRepository.saveAll(recordEntities);
    }

    /**
     * 讀取 情境文案檔
     * 
     * @param offerMasterKey
     * @param behaviorTag
     * @return
     */
    private List<OfferContentDetailEntity> getOfferContentDetailList(OfferMasterEntity offerMasterEntity, List<String> productNoAndPersonalNo, List<String> tf01s, String taskId, List<OfferContentDetailEntity> offerContentDetailListByA1O) {
        // 個人屬性流水號
        String personalAttrSerialNum = CollectionUtils.isNotEmpty(productNoAndPersonalNo) ? productNoAndPersonalNo.get(1).toUpperCase() : "";

        // 理財屬性流水號
        String financialAttrSerialNo = genFinancialAttrSerialNo(tf01s).toUpperCase();

        // 產品別
        List<OfferContentDetailEntity> offerContentDetailEnts = offerContentDetailListByA1O.stream().filter(offerContentDetail -> Objects.equals(offerContentDetail.getOfferMasterKey(), offerMasterEntity.getOfferMasterKey())).toList();
        if (StringUtils.isNotBlank(personalAttrSerialNum) && StringUtils.isNotBlank(financialAttrSerialNo)) {
            // mapping tp01, tf01
            List<OfferContentDetailEntity> offerContentDetailtptf = offerContentDetailEnts.stream().filter((ent) -> {
                List<String> itemList = transFormBehaviorTag(ent.getBehaviorTag(), taskId);
                return StringUtils.equals(itemList.get(0), personalAttrSerialNum) && StringUtils.equals(itemList.get(1), financialAttrSerialNo);
            }).toList();
            if (CollectionUtils.isNotEmpty(offerContentDetailtptf)) {
                return offerContentDetailtptf;
            }
        }
        if (StringUtils.isNotBlank(financialAttrSerialNo)) {
            // mapping All,tf01
            List<OfferContentDetailEntity> offerContentDetailALLtf = offerContentDetailEnts.stream().filter(ent -> {
                List<String> itemList = transFormBehaviorTag(ent.getBehaviorTag(), taskId);
                return StringUtils.equals(itemList.get(0), "ALL") && StringUtils.equals(itemList.get(1), financialAttrSerialNo);
            }).toList();
            if (CollectionUtils.isNotEmpty(offerContentDetailALLtf)) {
                return offerContentDetailALLtf;
            }
        }
        if (StringUtils.isNotBlank(personalAttrSerialNum)) {
            // mapping tp01, All
            List<OfferContentDetailEntity> offerContentDetailtpALL = offerContentDetailEnts.stream().filter((ent) -> {
                List<String> itemList = transFormBehaviorTag(ent.getBehaviorTag(), taskId);
                return StringUtils.equals(itemList.get(0), personalAttrSerialNum) && StringUtils.equals(itemList.get(1), "ALL");
            }).toList();
            if (CollectionUtils.isNotEmpty(offerContentDetailtpALL)) {
                return offerContentDetailtpALL;
            }
        }

        // 兩個BeHavior轉大寫都是ALL的 OfferContentDetail
        List<OfferContentDetailEntity> offerContentDetailALLALL = offerContentDetailEnts.stream().filter(ent -> {
            List<String> itemList = transFormBehaviorTag(ent.getBehaviorTag(), taskId);
            return StringUtils.equals(itemList.get(0), "ALL") && StringUtils.equals(itemList.get(1), "ALL");
        }).toList();
        return offerContentDetailALLALL;

    }

    private List<String> transFormBehaviorTag(String behaviorTag, String taskId) {
        List<String> resultList = List.of(behaviorTag.split(","));
        List<String> uppercaseList = new ArrayList<>();

        resultList.forEach(str -> uppercaseList.add(str.toUpperCase()));
        if (CollectionUtils.isEmpty(uppercaseList) || uppercaseList.size() < 2) {
            logger.warn(" transFormBehaviorTag 無東西 behaviorTag: " + behaviorTag + " ,taskId: " + taskId);
            return List.of("", "");
        }
        else {
            return uppercaseList;
        }

    }

    /**
     * 取得個人屬性
     * 
     * @param tf01
     * @return
     */
    private List<String> genProductNoAndPersonalNo(String tp01) {
        String[] parts = tp01.split("-");
        if (parts.length == 4 && "B".equals(parts[0]) && "CUSA".equals(parts[1])) {
            Optional<DicData> productCodeOpt = dicCacheManager.getDicListByCategory("OFFER_RANKING_PRODUCT_TYPE").stream().filter(dic -> StringUtils.equals(dic.getDicValue(), parts[2])).findAny();

            DicData personalAttrData = dicCacheManager.getDicByCategoryAndKey("OFFER_RANKING_PERSONAL_ATTR", parts[3]);

            String productCode = productCodeOpt.isPresent() ? productCodeOpt.get().getDicKey() : "";
            String personalAttrSerialNum = personalAttrData != null ? personalAttrData.getDicValue() : "";

            return List.of(productCode, personalAttrSerialNum);
        }
        else {
            return new ArrayList<>();
        }
    }

    /**
     * 取得理財屬性
     * 
     * @param tf01
     * @return
     */
    private String genFinancialAttrSerialNo(List<String> tf01s) {
        if (CollectionUtils.isEmpty(tf01s) || StringUtils.isBlank(tf01s.get(0))) {
            return "";
        }

        String[] parts = tf01s.get(0).split("-");

        if (parts.length == 4 && "B".equals(parts[0]) && "CUSA".equals(parts[1]) && "CUS".equals(parts[2])) {
            String financialAttributeSerialNumber = parts[3];
            DicData dicData = dicCacheManager.getDicByCategoryAndKey("OFFER_RANKING_FINANCIAL_ATTR", financialAttributeSerialNumber);
            return dicData.getDicValue();
        }
        else {
            throw new IllegalArgumentException(PERSON_SUBSCRIPTION_FLAG + " tf01輸入不符合預期");
        }
    }

    /**
     * 讀取 OFFER_NOTIFICATION_INFO
     * 
     * @param offerMasterKey
     * @param templateId
     * @return
     */
    public OfferNotificationInfoEntity getOfferNotificationInfo(Integer offerMasterKey, String templateId, Map<String, OfferNotificationInfoEntity> offerNotificationInfoMap) {
        String offerMasterKeyStr = String.valueOf(offerMasterKey);
        if (offerNotificationInfoMap.containsKey(offerMasterKeyStr + templateId)) {
            return offerNotificationInfoMap.get(offerMasterKeyStr + templateId);
        }
        else {
            OfferNotificationInfoEntity offerNotificationInfoEntity = offerNotificationInfoRepository.findByOfferMasterKeyAndTemplateId(offerMasterKey, templateId);
            offerNotificationInfoMap.put(offerMasterKeyStr + templateId, offerNotificationInfoEntity);
            return offerNotificationInfoEntity;
        }
    }

    /**
     * 取代掉情境版位檔的內容
     * 
     * @param content
     * @param offerInfo
     * @return
     */
    private String findKeyReplace(String content, JsonObject offerInfo) {
        String result = content;
        // 逐個取出鍵和值
        for (String key : offerInfo.keySet()) {
            String value = offerInfo.get(key).getAsString();
            result = result.replace("${" + key + "}", value);
        }
        return result;
    }

    /**
     * 用身分證號查與相應Company有關 userId
     * 
     * @param custId
     * @return
     */
    private List<UserEntity> queryUserByCompanyUidAndDup(String custId) {
        String uid = StringUtils.trim(StringUtils.left(custId, 10));
        String dup = "0";
        if (StringUtils.length(custId) == 11) {
            dup = StringUtils.right(custId, 1);
            if (StringUtils.isBlank(dup)) {
                dup = "0";
            }
        }
        String companyUid = encrypt(uid);
        return userRepository.findByCompanyUidAndDup(companyUid, dup);
    }

    /**
     * 加密
     * 
     * @param plainText
     * @return
     */
    private String encrypt(String plainText) {
        String hexString = plainText;
        try {
            hexString = getAESCipherUtils().encrypt(plainText);
        }
        catch (CryptException e) {
            logger.info("encrypt fail. plainText = {}", plainText);
        }
        return hexString;
    }

    /**
     * 數據中台AES256識別碼解密
     * 
     * @param encodedMsg
     * @return
     */
    public String dataCenterDecode(String encodedMsg, byte[] key, byte[] iv) {

        IvParameterSpec ivspec = iv == null ? null : new IvParameterSpec(iv);
        try {
            byte[] encryptedBytes = hexStringToByteArray(encodedMsg);
            byte[] decryptedBytes = AESUtils.decryptBytesInCBC(encryptedBytes, new SecretKeySpec(key, "AES"), ivspec);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        }
        catch (CryptException e) {
            logger.error(e.getMessage());
        }
        return "";
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            // Parse the hexadecimal string two characters at a time
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    // @formatter:off
    /**
     * <p>檢查客戶是否有綁定裝置且訂閱推播，一律收集</p>
     * <p>傳回值包含推播必須的資訊，以及「是否裝置已綁定」、「是否開啟推播通知」、「是否訂閱推播通知」</p>
     * <p>完成度最高的程式邏輯</p>
     * <p>檢查 isSubscribe = true，表示有訂閱推播。 </p>
     * 
     * @param custId
     * @param pushCode
     * @return
     */
    // @formatter:on
    public List<PushSubscriptionStatus> getPushSubscriptionStatus(String custId, String pushCode) {
        List<PushSubscriptionStatus> statusList = new ArrayList<>();
        List<CompanyEntity> companyEntities = getCompanyEntities(custId);
        if (CollectionUtils.isEmpty(companyEntities)) {
            logger.info("查詢 DB.COMPANY，查無資料。");
        }
        else {
            for (CompanyEntity companyEntity : companyEntities) {
                Integer companyKey = companyEntity.getCompanyKey();
                Integer companyKind = companyEntity.getCompanyKind();
                List<MbDeviceInfoEntity> mbDeviceInfoEntities = mbDeviceInfoRepository.findByCompanyKey(companyKey);
                if (CollectionUtils.isEmpty(mbDeviceInfoEntities)) {
                    logger.info("查詢 DB.AIBANK_MB_DEVICE_INFO.COMPANY_KEY = {}, 查無資料。", companyKey);
                    continue;
                }
                for (MbDeviceInfoEntity mbDeviceInfoEntity : mbDeviceInfoEntities) {
                    Integer userKey = mbDeviceInfoEntity.getUserKey();
                    Integer deviceInfoKey = mbDeviceInfoEntity.getDeviceInfoKey();
                    String deviceUuid = mbDeviceInfoEntity.getDeviceUuId();
                    // 取 AIBANK_MB_DEVICE_INFO.DEVICE_INFO_KEY，用來查詢 PUSH_SUBSCRIPTION
                    PushSubscriptionStatus status = new PushSubscriptionStatus();
                    status.setCompanyKey(companyKey);
                    status.setCompanyKind(companyKind);
                    status.setPushCode(pushCode);
                    status.setUserKey(userKey);
                    status.setDeviceUuId(deviceUuid);
                    status.setDeviceBound(true); // 註記是否有「裝置綁定」
                    // 讀取 MB_DEVICE_PUSH_INFO.NOTIFY_FLAG，判斷是否授權
                    MbDevicePushInfoEntity mbDevicePushInfoEntity = mbDevicePushInfoRepository.findByCompanyKeyAndUserKey(companyKey, userKey);
                    if (mbDevicePushInfoEntity != null) {
                        Integer notifyFlag = mbDevicePushInfoEntity.getNotifyFlag() == null ? 0 : mbDevicePushInfoEntity.getNotifyFlag();
                        Integer notifyPass = mbDevicePushInfoEntity.getNotifyPass() == null ? 0 : mbDevicePushInfoEntity.getNotifyPass();
                        status.setNotifyFlag(notifyFlag);
                        status.setNotifyPass(notifyPass);
                        status.setEnable(notifyFlag == 1); // 註記是否「開啟訊息通知」
                        List<PushSubscriptionEntity> pushSubscriptionEntities = pushSubscriptionRepository.findByDeviceInfoKeyAndPushCode(deviceInfoKey, pushCode);
                        if (CollectionUtils.isNotEmpty(pushSubscriptionEntities)) {
                            status.setSubscribe(true); // 註記是否「訂閱推播」
                        }
                        else {
                            logger.info("客戶(COMPANY_KEY={}, USER_KEY={})，於 MySQL.PUSH_SUBSCRIPTION 查無資料。發送推播通知之裝置(DEVICE_ID={})、推播代號(PUSH_CODE={})。", companyKey, userKey, deviceUuid, pushCode);
                        }
                    }
                    else {
                        logger.info("客戶(COMPANY_KEY={}, USER_KEY={})，於 MySQL.MB_DEVICE_PUSH_INFO 查無資料。", companyKey, userKey);
                    }
                    statusList.add(status);
                }
            }
        }
        return statusList;
    }

    /**
     * 依身分證字號查詢 DB.COMPANY，僅取 COMPANY_KIND IN (1, 2, 3, 4) 的記錄
     * 
     * @param custId
     * @return
     */
    public List<CompanyEntity> getCompanyEntities(String inputCustIxd) {
        String custIxd = inputCustIxd;
        String uid = StringUtils.trim(StringUtils.left(custIxd, 10));
        String dup = "0";
        if (StringUtils.length(custIxd) == 11) {
            dup = StringUtils.right(custIxd, 1);
            if (StringUtils.isBlank(dup)) {
                dup = "0";
            }
        }
        // 【FORTIFY：Access Control: Database】uid、dup 就是身分識別屬性欄位
        List<CompanyEntity> companyEntities = companyRepository.findByCompanyUidAndUidDup(encrypt(ValidateParamUtils.validParam(uid)), ValidateParamUtils.validParam(dup));
        return companyEntities.stream().filter(x -> {
            int companyKind = ConvertUtils.integer2Int(x.getCompanyKind());
            return companyKind >= 1 && companyKind <= 4;
        }).collect(Collectors.toList());
    }

    /**
     * 依 MB_DEVICE_PUSH_INFO.NOTIFY_PASS 檢查當前系統時間是否允許發送訊息。0:未開啟訊息通知(全天不可發送) 1:已開啟訊息通知(全天可發送) 2:夜間勿擾(2200~0800不發送)
     * 
     * @param notifyPass
     * @return
     */
    public boolean checkIsPush(Integer notifyPass) {
        if (notifyPass == null) {
            return false;
        }
        if (notifyPass == 1) {
            return true;
        }
        else if (notifyPass == 2) {
            String sysTime = DateFormatUtils.SIMPLE_TIME_FORMAT_HOUR_MINUTE.format(new Date());
            if (sysTime.compareTo("0800") > 0 && sysTime.compareTo("2200") < 0) {
                return true;
            }
        }
        return false;
    }

}
