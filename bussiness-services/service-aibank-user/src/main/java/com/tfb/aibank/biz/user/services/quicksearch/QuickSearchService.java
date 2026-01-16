package com.tfb.aibank.biz.user.services.quicksearch;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.AccountCreditcardCheckRepository;
import com.tfb.aibank.biz.user.repository.CompanyRepository;
import com.tfb.aibank.biz.user.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.user.repository.UserRepository;
import com.tfb.aibank.biz.user.repository.entities.AccountCreditcardCheckEntity;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.services.quicksearch.model.QuickSearchModel;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB67050RS;

/**
 // @formatter:off
 * @(#)QSearchService.java
 * 
 * <p>Description:[免登速查-取得使用者資訊]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/1, Marty Pan
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class QuickSearchService {

    private static final IBLog logger = IBLog.getLog(QuickSearchService.class);

    private MbDeviceInfoRepository mbDeviceInfoRepository;

    private UserRepository userRepository;

    private CompanyRepository companyRepository;

    private EsbChannelGateway esbGateway;

    private AccountCreditcardCheckRepository accountCreditcardCheckRepository;

    private IdentityService identityService;

    private static final String QSEARCH_NO = "0";

    public QuickSearchService(MbDeviceInfoRepository mbDeviceInfoRepository, UserRepository userRepository, CompanyRepository companyRepository, EsbChannelGateway esbGateway, AccountCreditcardCheckRepository accountCreditcardCheckRepository, IdentityService identityService) {
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.esbGateway = esbGateway;
        this.accountCreditcardCheckRepository = accountCreditcardCheckRepository;
        this.identityService = identityService;
    }

    public QuickSearchModel getQuickSearchStatus(String deviceuuid) {
        QuickSearchModel qsearch = new QuickSearchModel();

        List<MbDeviceInfoEntity> mbDeviceInfoEntityList = mbDeviceInfoRepository.findByDeviceUuId(deviceuuid);
        MbDeviceInfoEntity mbDeviceInfoEntity = null;
        logger.debug("mbDeviceInfoEntityList: {}", mbDeviceInfoEntityList);

        // 查不到資料
        if (CollectionUtils.isEmpty(mbDeviceInfoEntityList)) {
            qsearch.setQsearchFlag(QSEARCH_NO);
        }
        else {
            // 如果有資料時取有enable + qsearchFlag = 1的
            Optional<MbDeviceInfoEntity> opt = mbDeviceInfoEntityList.stream().filter(mb -> (null != mb.getEnable() && 1 == mb.getEnable().intValue()) && (null != mb.getQsearchFlag() && 1 == mb.getQsearchFlag().intValue())).findFirst();

            if (opt.isPresent()) {
                // 如果可以進行到這裡，會以此筆資料內容作為資料查詢的對象
                mbDeviceInfoEntity = opt.get();
                // Fortify - Log Forging
                // if (logger.isDebugEnabled()) {
                // logger.debug("mbDeviceInfoEntity: {}", IBUtils.attribute2Str(mbDeviceInfoEntity));
                // }
                // 取qsearchFlag值，沒值就當沒有
                Integer qsearchFlag = NumberUtils.defaultValue(mbDeviceInfoEntity.getQsearchFlag(), 0);
                logger.debug("qsearchFlag: {}", qsearchFlag);
                qsearch.setQsearchFlag(qsearchFlag.toString());
            }
        }
        // 有免登速查
        if (qsearch.haveQuickSearchOn() && mbDeviceInfoEntity != null && mbDeviceInfoEntity.getUserKey() != null) {
            Integer userKey = mbDeviceInfoEntity.getUserKey();
            qsearch.setUserKey(userKey);
            Optional<UserEntity> userEntOpt = this.userRepository.findById(userKey);
            logger.debug("User: {}", userEntOpt.isPresent());
            UserEntity userEntity = null;
            if (userEntOpt.isPresent()) {
                userEntity = userEntOpt.get();
                qsearch.setUserId(userEntity.getUserUuid());
                qsearch.setNameCode(userEntity.getNameCode());
            }

            Integer cKxy = mbDeviceInfoEntity.getCompanyKey();
            qsearch.setCompanyKey(cKxy);

            Optional<CompanyEntity> companyEntOpt = this.companyRepository.findById(cKxy);
            logger.debug("Company: {}", companyEntOpt.isPresent());
            if (companyEntOpt.isPresent()) {
                CompanyEntity ce = companyEntOpt.get();
                qsearch.setCustId(ce.getCompanyUid());
                qsearch.setCompanyKind(ce.getCompanyKind());
                qsearch.setCompanyBUType(ce.getCompanyBUType());
                qsearch.setUidDup(ce.getUidDup());

                fetchAdditionalInformation(qsearch);
            }
        }
        return qsearch;
    }

    /**
     * 取得額外資訊
     */
    private void fetchAdditionalInformation(QuickSearchModel qsearch) {

        if (logger.isDebugEnabled()) {
            logger.debug("== fetchAdditionalInformation, QuickSearchModel ==: {}", IBUtils.attribute2Str(qsearch));
        }
        String cUidToSearch = identityService.encrypt(qsearch.getCustId());
        /**
         * 重號戶進入功能檢核處理 1. 查是否在 ACCOUNT_CREDITCARD_CHECK 黑名單中 2. 若在黑名單中，查 CEW319R的生日 與 EB67050的生日 是否相同
         */
        AccountCreditcardCheckEntity accountCreditcardCheckEntity = accountCreditcardCheckRepository.findByCompanyUid(cUidToSearch);

        if (accountCreditcardCheckEntity == null) {
            qsearch.setInAccountCreditcardCheck(false);
        }
        else {
            logger.debug("== fetchAdditionalInformation == InAccountCreditcardCheck -> true ");
            qsearch.setInAccountCreditcardCheck(true);

            Date birthday = getBirthDayByEB67050(qsearch.getCustId(), "11");

            if (birthday == null) {
                qsearch.setSameBirthday(false);
                return;
            }

            String birthdayStr = DateFormatUtils.SIMPLE_DATE_FORMAT.format(birthday);

            try {
                esbGateway.sendCEW319R("G", qsearch.getCustId(), null, birthdayStr, null, null);
            }
            catch (XmlException | EAIException | EAIResponseException | ActionException ex) {
                logger.warn("== fetchAdditionalInformation, sendCEW319R ==:", ex);
                qsearch.setSameBirthday(false);
                return;
            }
            qsearch.setSameBirthday(true);
            qsearch.setBirthday(birthday);
        }
    }

    public Date getBirthDayByEB67050(String custId, String idType) {
        Date birthday = null;
        try {
            EB67050RS eb67050rs = esbGateway.sendEB67050(custId, idType);
            if (Objects.nonNull(eb67050rs) && Objects.nonNull(eb67050rs.getBody().getBDAY())) {
                birthday = eb67050rs.getBody().getBDAY().getTime();
            }
        }
        catch (XmlException | EAIException | EAIResponseException ex) {
            logger.warn("== getBirthDayByEB67050 ==:", ex);
        }
        return birthday;
    }

}
