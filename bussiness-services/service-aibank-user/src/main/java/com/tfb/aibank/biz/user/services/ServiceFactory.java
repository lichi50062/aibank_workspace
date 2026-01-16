package com.tfb.aibank.biz.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.AccountCreditcardCheckRepository;
import com.tfb.aibank.biz.user.repository.AiBankCountryNameRepository;
import com.tfb.aibank.biz.user.repository.AiDataSyncLogRepository;
import com.tfb.aibank.biz.user.repository.AiDataSyncStatusRepository;
import com.tfb.aibank.biz.user.repository.AiSsoAuthDataRepository;
import com.tfb.aibank.biz.user.repository.AiSsoSettingRepository;
import com.tfb.aibank.biz.user.repository.AibankPushCategoryRepository;
import com.tfb.aibank.biz.user.repository.BankEmployeeRepository;
import com.tfb.aibank.biz.user.repository.BiometricsBlackListRepository;
import com.tfb.aibank.biz.user.repository.CardMemberRecordRepository;
import com.tfb.aibank.biz.user.repository.CardUserProfileRepository;
import com.tfb.aibank.biz.user.repository.ChangeCustDataRecordRepository;
import com.tfb.aibank.biz.user.repository.ChangePasswordRecordRepository;
import com.tfb.aibank.biz.user.repository.ChangePasswordTipsRepository;
import com.tfb.aibank.biz.user.repository.CompanyRepository;
import com.tfb.aibank.biz.user.repository.CountryNameRepository;
import com.tfb.aibank.biz.user.repository.CustomerGroupIdRepository;
import com.tfb.aibank.biz.user.repository.DebitCardRepository;
import com.tfb.aibank.biz.user.repository.DeviceModelRepository;
import com.tfb.aibank.biz.user.repository.EbLoginLogBRepository;
import com.tfb.aibank.biz.user.repository.ExchangeRateHistoryRepository;
import com.tfb.aibank.biz.user.repository.FinancialDataOrderRepository;
import com.tfb.aibank.biz.user.repository.GeoIPLocationDataRepository;
import com.tfb.aibank.biz.user.repository.GeoIPRangeDataRepository;
import com.tfb.aibank.biz.user.repository.HomePageCardUserRepository;
import com.tfb.aibank.biz.user.repository.IpLocationARepository;
import com.tfb.aibank.biz.user.repository.IpLocationBRepository;
import com.tfb.aibank.biz.user.repository.KnowledgeCheckLogRepository;
import com.tfb.aibank.biz.user.repository.LinePNPRecordRepository;
import com.tfb.aibank.biz.user.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.user.repository.MbGestureProfileRepository;
import com.tfb.aibank.biz.user.repository.MbLoginLogRepository;
import com.tfb.aibank.biz.user.repository.NonAIMbDeviceInfoEntityRepository;
import com.tfb.aibank.biz.user.repository.OnboardingTransferLogRepository;
import com.tfb.aibank.biz.user.repository.RateCardUserRepository;
import com.tfb.aibank.biz.user.repository.ReadChangeRightsInterestsRepository;
import com.tfb.aibank.biz.user.repository.SequenceRepositoryCustom;
import com.tfb.aibank.biz.user.repository.SystemControlTableRepository;
import com.tfb.aibank.biz.user.repository.TrustDeviceRepository;
import com.tfb.aibank.biz.user.repository.TwoFactorAuthRepository;
import com.tfb.aibank.biz.user.repository.UserLoginRepository;
import com.tfb.aibank.biz.user.repository.UserRepository;
import com.tfb.aibank.biz.user.repository.UsualTaskRepository;
import com.tfb.aibank.biz.user.repository.W8benInfoLogRepository;
import com.tfb.aibank.biz.user.repository.W8benSignLogRepository;
import com.tfb.aibank.biz.user.repository.WalletPaymentSettingRepository;
import com.tfb.aibank.biz.user.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.user.repository.aib.PushSubscriptionRepository;
import com.tfb.aibank.biz.user.repository.aib.WBPlusAccessLogRepository;
import com.tfb.aibank.biz.user.repository.piblog.CardCostcoDuesRepository;
import com.tfb.aibank.biz.user.repository.piblog.LoginUidLimitationRepository;
import com.tfb.aibank.biz.user.resource.SecurityResource;
import com.tfb.aibank.biz.user.services.accountsecurity.AccountSecurityService;
import com.tfb.aibank.biz.user.services.bankingunit.BankingUnitService;
import com.tfb.aibank.biz.user.services.bpm.BPMService;
import com.tfb.aibank.biz.user.services.communication.CommunicationInfoService;
import com.tfb.aibank.biz.user.services.companychange.CompanyChangeService;
import com.tfb.aibank.biz.user.services.costco.CostcoCardService;
import com.tfb.aibank.biz.user.services.custdatarecord.CustDataRecordService;
import com.tfb.aibank.biz.user.services.customergroupid.CustomerGroupIdService;
import com.tfb.aibank.biz.user.services.datasync.AiDataSyncLogService;
import com.tfb.aibank.biz.user.services.datasync.AiDataSyncStatusService;
import com.tfb.aibank.biz.user.services.debitcard.DebitCardService;
import com.tfb.aibank.biz.user.services.depositassets.DepositAssetService;
import com.tfb.aibank.biz.user.services.devicebindings.DeviceBindingsService;
import com.tfb.aibank.biz.user.services.email.EmailService;
import com.tfb.aibank.biz.user.services.employee.EmpolyeeService;
import com.tfb.aibank.biz.user.services.encryptasset.EncryptAssetService;
import com.tfb.aibank.biz.user.services.feediscount.FeeDiscountService;
import com.tfb.aibank.biz.user.services.financialdataorder.FinancialDataOrderService;
import com.tfb.aibank.biz.user.services.homepagecard.HomePageCardService;
import com.tfb.aibank.biz.user.services.identitytype.IdentityTypeService;
import com.tfb.aibank.biz.user.services.inveligiblecheck.InvestEligibleCheckService;
import com.tfb.aibank.biz.user.services.knowledgecheck.KnowledgeCheckService;
import com.tfb.aibank.biz.user.services.linebindcheck.LineBindCheckService;
import com.tfb.aibank.biz.user.services.log.WBPlusAccessLogService;
import com.tfb.aibank.biz.user.services.login.ExecuteChangeCcUserPinService;
import com.tfb.aibank.biz.user.services.login.ExecuteUserLoginService;
import com.tfb.aibank.biz.user.services.login.ExecuteUserLogoutService;
import com.tfb.aibank.biz.user.services.login.LoginSessionService;
import com.tfb.aibank.biz.user.services.nopain.NoPainTransferService;
import com.tfb.aibank.biz.user.services.otp.OtpService;
import com.tfb.aibank.biz.user.services.push.PushService;
import com.tfb.aibank.biz.user.services.quicksearch.QuickSearchService;
import com.tfb.aibank.biz.user.services.ratecarduser.RateCardUserService;
import com.tfb.aibank.biz.user.services.sso.SsoAuthService;
import com.tfb.aibank.biz.user.services.twofactor.TwoFactorAuthService;
import com.tfb.aibank.biz.user.services.unfatcaflag.UnFatcaFlagService;
import com.tfb.aibank.biz.user.services.user.UserService;
import com.tfb.aibank.biz.user.services.usermark.UserMarkService;
import com.tfb.aibank.biz.user.services.userprofile.UserProfileService;
import com.tfb.aibank.biz.user.services.usualtask.UsualTaskService;
import com.tfb.aibank.component.accountinfo.AccountInfoCacheManager;

@Service
public class ServiceFactory {

    @Autowired
    private Environment environment;
    @Autowired
    private EsbChannelGateway esbGateway;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private ExchangeRateHistoryRepository exchangeRateHistoryRepository;
    @Autowired
    private UserRepository userProfileRepository;
    @Autowired
    private UsualTaskRepository usualTaskRepository;
    @Autowired
    private MbDeviceInfoRepository mbDeviceInfoRepository;
    @Autowired
    private MbDevicePushInfoRepository mbDevicePushInfoRepository;
    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private ReadChangeRightsInterestsRepository readChangeRightsInterestsRepository;
    @Autowired
    private HomePageCardUserRepository homePageCardUserRepository;
    @Autowired
    private GeoIPRangeDataRepository geoIPRangeDataRepository;
    @Autowired
    private GeoIPLocationDataRepository geoIPLocationDataRepository;
    @Autowired
    private CardUserProfileRepository cardUserProfileRepository;
    @Autowired
    private MbGestureProfileRepository mbGestureProfileRepository;
    @Autowired
    private RateCardUserRepository rateCardUserRepository;
    @Autowired
    private BankEmployeeRepository bankEmployeeRepository;
    @Autowired
    private AccountInfoCacheManager accountInfoCacheManager;
    @Autowired
    private SystemParamCacheManager systemParamCacheManager;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SecurityResource securityResource;
    @Autowired
    private LoginUidLimitationRepository LoginUidLimitationRepository;
    @Autowired
    private ChangePasswordRecordRepository changePasswordRecordRepository;
    @Autowired
    private ChangePasswordTipsRepository changePasswordTipsRepository;
    @Autowired
    private LinePNPRecordRepository linePNPRecordRepository;
    @Autowired
    private DeviceModelRepository deviceModelRepository;
    @Autowired
    private ChangeCustDataRecordRepository changeCustDataRecordRepository;
    @Autowired
    private BiometricsBlackListRepository biometricsBlackListRepository;
    @Autowired
    private CardMemberRecordRepository cardMemberRecordRepository;
    @Autowired
    private DebitCardRepository debitCardRepository;
    @Autowired
    private AccountCreditcardCheckRepository accountCreditcardCheckRepository;
    @Autowired
    private CustomerGroupIdRepository customerGroupIdRepository;
    @Autowired
    private AiSsoAuthDataRepository aiSsoAuthDataRepository;
    @Autowired
    private AiSsoSettingRepository aiSsoSettingRepository;
    @Autowired
    private PushSubscriptionRepository pushSubscriptionRepository;
    @Autowired
    private KnowledgeCheckLogRepository knowledgeCheckLogRepository;
    @Autowired
    private AiDataSyncStatusRepository aiDataSyncStatusRepository;
    @Autowired
    private AiDataSyncLogRepository aiDataSyncLogRepository;
    @Autowired
    private SequenceRepositoryCustom sequenceRepositoryCustom;
    @Autowired
    private MbLoginLogRepository mbLoginLogRepository;
    @Autowired
    private FinancialDataOrderRepository financialDataOrderRepository;
    @Autowired
    private CountryNameRepository countryNameRepository;
    @Autowired
    private CardCostcoDuesRepository cardCostcoDuesRepository;
    @Autowired
    private W8benInfoLogRepository w8benInfoLogRepository;
    @Autowired
    private W8benSignLogRepository w8benSignLogRepository;
    @Autowired
    private NonAIMbDeviceInfoEntityRepository nonAIMbDeviceInfoEntityRepository;
    @Autowired
    private OnboardingTransferLogRepository onboardingTransferLogRepository;
    @Autowired
    private EbLoginLogBRepository ebLoginLogBRepository;
    @Autowired
    private AibankPushCategoryRepository pushCategoryRepository;
    @Autowired
    private WalletPaymentSettingRepository walletPaymentSettingRepository;
    @Autowired
    private WBPlusAccessLogRepository wbPlusAccessLogRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AiBankCountryNameRepository aiBankCountryNameRepository;
    
    @Autowired
    private IpLocationARepository ipLocationARepository;
    @Autowired
    private IpLocationBRepository ipLocationBRepository;
    @Autowired
    private SystemControlTableRepository systemControlTableRepository;
        
    @Autowired
    private TrustDeviceRepository trustDeviceRepository;
     
    @Autowired
    private TwoFactorAuthRepository twoFactorAuthRepository;
     

    /** 提供「手續費優惠」相關服務 */
    public FeeDiscountService createFeeDiscountService() {
        return new FeeDiscountService(esbGateway);
    }

    public DepositAssetService createDepositAssetService() {
        return new DepositAssetService(esbGateway, accountInfoCacheManager, exchangeRateHistoryRepository);
    }

    /** 登入 */
    public ExecuteUserLoginService executeUserLoginService() {
        // @formatter:off
        return new ExecuteUserLoginService(
                esbGateway, userProfileRepository,
                systemParamCacheManager,
                userLoginRepository,
                securityResource,
                companyRepository,
                readChangeRightsInterestsRepository,
                geoIPRangeDataRepository,
                geoIPLocationDataRepository,
                cardUserProfileRepository,
                mbDeviceInfoRepository,
                identityService,
                LoginUidLimitationRepository,
                changePasswordTipsRepository,
                mbGestureProfileRepository,
                changePasswordRecordRepository,
                cardMemberRecordRepository,
                accountCreditcardCheckRepository,
                mbDevicePushInfoRepository,
                mbLoginLogRepository,
                environment,
                nonAIMbDeviceInfoEntityRepository,
                ebLoginLogBRepository,
                pushSubscriptionRepository,
                createTwoFactorAuthService()
        );
        // @formatter:on
    }

    /** 好市多會費代扣繳申請 */
    public CostcoCardService createCostcoCardService() {
        return new CostcoCardService(identityService, esbGateway, cardCostcoDuesRepository);
    }

    /** 更換信用卡使用者密碼 */
    public ExecuteChangeCcUserPinService executeChangeUserPinService() {
        return new ExecuteChangeCcUserPinService(userProfileRepository, userLoginRepository, securityResource, mbDeviceInfoRepository, changePasswordRecordRepository, nonAIMbDeviceInfoEntityRepository);
    }

    /** 登出 */
    public ExecuteUserLogoutService executeUserLogoutService() {
        return new ExecuteUserLogoutService(userLoginRepository, identityService, ebLoginLogBRepository);
    }

    public OtpService createOtpService() {
        return new OtpService(esbGateway);
    }

    public UsualTaskService createUsualTaskService() {
        return new UsualTaskService(identityService, usualTaskRepository);
    }

    /** 裝置綁定服務 */
    public DeviceBindingsService createDeviceService() {
        return new DeviceBindingsService(identityService, mbDeviceInfoRepository, mbGestureProfileRepository, companyRepository, userProfileRepository, linePNPRecordRepository, deviceModelRepository, biometricsBlackListRepository, mbDevicePushInfoRepository, pushSubscriptionRepository, esbGateway, pushCategoryRepository, walletPaymentSettingRepository, systemParamCacheManager);
    }

    /** userprofile */
    public UserProfileService createUserProfileService() {
        return new UserProfileService(identityService, userProfileRepository, esbGateway, countryNameRepository, w8benInfoLogRepository, w8benSignLogRepository);
    }

    public HomePageCardService createHomePageCardService() {
        return new HomePageCardService(identityService, homePageCardUserRepository, mbDeviceInfoRepository);
    }

    public BPMService createBPMService() {
        return new BPMService(esbGateway);
    }

    public FinancialDataOrderService createFinancialDataOrderService() {
        return new FinancialDataOrderService(identityService, financialDataOrderRepository);
    }

    public QuickSearchService createQuickSearchService() {
        return new QuickSearchService(mbDeviceInfoRepository, userProfileRepository, companyRepository, esbGateway, accountCreditcardCheckRepository, identityService);
    }

    public RateCardUserService createRateCardUserService() {
        return new RateCardUserService(identityService, rateCardUserRepository);
    }

    public BankingUnitService createBankingUnitService() {
        return new BankingUnitService(esbGateway);
    }

    public UserMarkService createUserMarkService() {
        return new UserMarkService(esbGateway);
    }

    public IdentityTypeService createIdentityTypeService() {
        return new IdentityTypeService(esbGateway);
    }

    public LineBindCheckService createLineBindCheckService() {
        return new LineBindCheckService(systemParamCacheManager);
    }

    public CustDataRecordService createCustDataRecordService() {
        return new CustDataRecordService(changeCustDataRecordRepository, identityService);
    }

    public CompanyChangeService createCompanyChangeService() {
        return new CompanyChangeService(identityService, companyRepository);
    }

    public EmpolyeeService createEmpolyeeService() {
        return new EmpolyeeService(esbGateway, identityService, bankEmployeeRepository);
    }

    public LoginSessionService createLoginSessionService() {
        return new LoginSessionService(userLoginRepository, identityService, ebLoginLogBRepository);
    }

    public EmailService createEmailService() {
        return new EmailService(esbGateway, changeCustDataRecordRepository);
    }

    public AccountSecurityService createAccountSecurityService() {
        return new AccountSecurityService(identityService, changePasswordTipsRepository, changePasswordRecordRepository);
    }

    public DebitCardService createDebitCardService() {
        return new DebitCardService(debitCardRepository);
    }

    public CustomerGroupIdService createCustomerGroupIdService() {
        return new CustomerGroupIdService(customerGroupIdRepository);
    }

    public SsoAuthService createSsoAuthService() {
        return new SsoAuthService(identityService, aiSsoAuthDataRepository, aiSsoSettingRepository, systemParamCacheManager, mbDeviceInfoRepository, companyRepository, userProfileRepository, mbGestureProfileRepository, securityResource, userLoginRepository, esbGateway);
    }

    public InvestEligibleCheckService createInvestEligibleCheckService() {
        return new InvestEligibleCheckService(esbGateway);
    }
    
    public UnFatcaFlagService createUnFatcaFlagService() {
        return new UnFatcaFlagService(esbGateway);
    }

    public KnowledgeCheckService createKnowledgeCheckService() {
        return new KnowledgeCheckService(knowledgeCheckLogRepository, identityService);
    }

    public AiDataSyncStatusService createAiDataSyncStatusService() {
        return new AiDataSyncStatusService(aiDataSyncStatusRepository, aiDataSyncLogRepository, systemParamCacheManager, sequenceRepositoryCustom, identityService);
    }

    public AiDataSyncLogService createAiDataSyncLogService() {
        return new AiDataSyncLogService(aiDataSyncLogRepository);
    }

    public EncryptAssetService createEncryptAssetService() {
        return new EncryptAssetService();
    }

    public PushService createPushService() {
        return new PushService(mbDevicePushInfoRepository);
    }

    public WBPlusAccessLogService createWBPlusAccessLogService() {
        return new WBPlusAccessLogService(wbPlusAccessLogRepository);
    }

    /**
     * 無痛移轉旅程
     * 
     * @return
     */
    public NoPainTransferService createNoPainTransferService() {
        // @formatter:off
        return new NoPainTransferService(
                esbGateway,
                companyRepository,
                userProfileRepository,
                userLoginRepository,
                cardUserProfileRepository,
                mbDeviceInfoRepository,
                mbDevicePushInfoRepository,
                mbLoginLogRepository,
                environment,
                systemParamCacheManager,
                onboardingTransferLogRepository,
                identityService,
                changePasswordTipsRepository);
        // @formatter:on
    }

    public CommunicationInfoService createCommunicationInfoService() {
        return new CommunicationInfoService(esbGateway);
    }

    /**
     * 使用者相關資訊
     */
    public UserService createUserService() {
        return new UserService(esbGateway, companyRepository, userRepository, mbDeviceInfoRepository, mbGestureProfileRepository);
    }
    
    public TwoFactorAuthService createTwoFactorAuthService(){
        
        return new TwoFactorAuthService(trustDeviceRepository, mbDeviceInfoRepository, mbDevicePushInfoRepository, twoFactorAuthRepository);
    }
    
}
