package general.qu001.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.component.menu.Menu;
import com.ibm.tw.ibmb.component.menu.MenuCacheManager;
import com.ibm.tw.ibmb.type.MenuCategory;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.component.exchangerate.ExchangeRate;
import com.tfb.aibank.chl.component.exchangerate.ExchangeRateService;
import com.tfb.aibank.chl.component.fxinterestrate.FxInterestRate;
import com.tfb.aibank.chl.component.fxinterestrate.FxInterestRateCacheManager;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.component.homepagecard.HomepageCardCacheManager;
import com.tfb.aibank.chl.general.qu001.model.DataInput;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.service.NGNQU001Service;
import com.tfb.aibank.chl.general.resource.InformationResource;
import com.tfb.aibank.chl.general.resource.NotificationResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.RespectInfo;
import com.tfb.aibank.chl.general.resource.dto.RetrieveUserHomePageCardResponse;
import com.tfb.aibank.chl.general.resource.dto.UserHomePageCardModel;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.type.CustomerType;

// @formatter:off
/**
 * @(#)NGNQU001ServiceTest.java
 *
 * <p>Description: </p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyP
 *  <li>測試 NGNQU001Service中method</li>
 * </ol>
 */
//@formatter:on
@ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.LENIENT)
public class NGNQU001ServiceTest {

    private static final IBLog logger = IBLog.getLog(NGNQU001ServiceTest.class);

    @Mock
    FxInterestRateCacheManager fxInterestRateCacheManager;
    @Spy
    @InjectMocks
    private NGNQU001Service service;
    @Mock
    private NotificationResource notificationResource;

    @Mock
    private UserResource userResource;

    @Mock
    private InformationResource informationResource;
    @Mock
    private CurrencyCodeCacheManager currencyCodeCacheManager;

    @Mock
    private ExchangeRateService exchangeRateService;
    @Mock
    private MenuCacheManager menuCacheManager;
    @Mock
    private HomepageCardCacheManager homepageCardCacheManager;

    /**
     * Test service.GetUserConfigedHomepageCards 取得使用者設定的HomepageCardUser牌卡設定
     * <p>
     * #7
     * </p>
     */
    // @Test
    void testGetUserConfigedHomepageCards() {
        BaseServiceResponse<RetrieveUserHomePageCardResponse> resp = mockRetrieveUserHomePageCardResponse();

        doReturn(resp).when(userResource).retrieveUserHomePageCard(anyString(), anyString(), anyString(), anyInt());

        DataInput input = new DataInput();
        input.setCustId("XX");
        input.setUserId("OO");
        input.setCompanyKind(99);
        DataOutput output = new DataOutput();

        // service.getUserConfigedHomepageCards(input, output);
        logger.info(IBUtils.attribute2Str(output.getDisplayHomepageCardIds()));
        assertThat(output.getDisplayHomepageCardIds()).hasSize(3);
    }

    private BaseServiceResponse<RetrieveUserHomePageCardResponse> mockRetrieveUserHomePageCardResponse() {

        RetrieveUserHomePageCardResponse cardResp = new RetrieveUserHomePageCardResponse();

        List<UserHomePageCardModel> cards;
        UserHomePageCardModel m1 = new UserHomePageCardModel();
        m1.setCardId(3);
        m1.setCardSort(1);
        UserHomePageCardModel m2 = new UserHomePageCardModel();
        m2.setCardId(2);
        m2.setCardSort(3);
        UserHomePageCardModel m3 = new UserHomePageCardModel();
        m3.setCardId(3);
        m3.setCardSort(4);
        UserHomePageCardModel m4 = new UserHomePageCardModel();
        m4.setCardId(4);
        m4.setCardSort(2);
        cards = Stream.of(m1, m2, m3, m4).toList();
        cardResp.setCards(cards);
        return BaseServiceResponse.of(cardResp);

    }

    /**
     * Test service.getDefaultHomepageCardIdToShow 測試取得非登入時顯示的homecard id
     * <p>
     * #6
     * </p>
     */
    // @Test
    void testGetDefaultHomepageCardIdToShow() {
        List<HomepageCard> homepageCards = mockHomepageCard();

        doReturn(homepageCards).when(homepageCardCacheManager).getHomepageCards();

        DataOutput output = new DataOutput();

        // service.getDefaultHomepageCardIdToShow(output);

        logger.info(IBUtils.attribute2Str(output.getDisplayHomepageCardIds()));

        assertThat(output.getDisplayHomepageCardIds()).isNotNull();
        assertThat(output.getDisplayHomepageCardIds()).hasSize(3);
    }

    List<HomepageCard> mockHomepageCard() {
        List<HomepageCard> cards = new ArrayList<>();

        HomepageCard c1 = new HomepageCard();
        c1.setCardId(1);
        c1.setCardSort(1);
        c1.setQuery(0);
        c1.setCardFold("0");
        HomepageCard c2 = new HomepageCard();
        c2.setCardId(2);
        c2.setCardSort(3);
        c2.setQuery(0);
        c2.setCardFold("0");
        HomepageCard c3 = new HomepageCard();
        c3.setCardId(3);
        c3.setCardSort(2);
        c3.setQuery(0);
        c3.setCardFold("0");
        HomepageCard c4 = new HomepageCard();
        c4.setCardId(4);
        c4.setCardSort(0);
        c4.setQuery(0);
        c4.setCardFold("0");

        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);

        return cards;
    }

    /**
     * Test service.getMenusForUserSetting 測試取得所有可以提供使用者設定為常用功能的功能列表
     * <p>
     * #5
     * </p>
     */
    // @Test
    void testGetMenusForUserSetting() {
        List<Menu> parentMenus = mockParentMenus();

        doReturn(parentMenus).when(menuCacheManager).getMenusByParentMenuId("ROOT");
        Locale defLocale = Locale.TAIWAN;

        List<Menu> childrenMenus = mockChildrenMenus();
        doReturn(childrenMenus).when(menuCacheManager).getMenusByParentMenuId("idMc");

        AIBankUser user = new AIBankUser(null);
        user.setCustomerType(CustomerType.GENERAL);
        DataInput input = new DataInput();
        input.setLocale(defLocale);
        DataOutput output = new DataOutput();

        service.getMenusForUserSetting(user, input, output, MenuCategory.AIDBU);

        logger.info(IBUtils.attribute2Str(output.getMenusForSetting()));

        assertThat(output.getMenusForSetting()).isNotNull();
        assertThat(output.getMenusForSetting()).hasSize(3);
    }

    List<Menu> mockParentMenus() {
        List<Menu> rootMenus = new ArrayList<>();
        Menu mA = new Menu();
        mA.setMenuId("idMa");
        mA.setParentMenuId("ROOT");
        mA.setIsDisplay(-1);
        mA.setCategory("NDBU");
        mA.setIconClass("css");
        Map<String, String> nameMapA = new HashMap<>();
        nameMapA.put("zh_TW", "nameIDMA");
        mA.setMenuNameMap(nameMapA);
        Menu mB = new Menu();
        mB.setMenuId("idMb");
        mB.setParentMenuId("ROOT");
        mB.setIsDisplay(1);
        mB.setCategory("NCCU");
        mB.setIconClass("css");
        Map<String, String> nameMapB = new HashMap<>();
        nameMapB.put("zh_TW", "nameIDMB");
        mB.setMenuNameMap(nameMapB);
        Menu mC = new Menu();
        mC.setMenuId("idMc");
        mC.setParentMenuId("ROOT");
        mC.setIsDisplay(1);
        mC.setCategory("NDBU");
        mC.setIconClass("css");
        Map<String, String> nameMapC = new HashMap<>();
        nameMapC.put("zh_TW", "nameIDMC");
        mC.setMenuNameMap(nameMapC);
        Menu mD = new Menu();
        mD.setMenuId("idMd");
        mD.setParentMenuId("ROOT");
        mD.setIsDisplay(1);
        mD.setCategory("NDBU");
        mD.setIconClass("css");
        Map<String, String> nameMapD = new HashMap<>();
        nameMapD.put("zh_TW", "nameIDMD");
        mD.setMenuNameMap(nameMapD);
        rootMenus.add(mA);
        rootMenus.add(mB);
        rootMenus.add(mC);
        rootMenus.add(mD);
        return rootMenus;
    }

    List<Menu> mockChildrenMenus() {
        List<Menu> childrenMenus = new ArrayList<>();
        Menu mA = new Menu();
        mA.setMenuId("idMaChild");
        mA.setParentMenuId("ROOT");
        mA.setIsDisplay(-1);
        mA.setCategory("NDBU");
        Menu mB = new Menu();
        mB.setMenuId("idMbChild");
        mB.setParentMenuId("ROOT");
        mB.setIsDisplay(1);
        mB.setCategory("NCCU");
        Menu mC = new Menu();
        mC.setMenuId("idMcChild");
        mC.setParentMenuId("ROOT");
        mC.setIsDisplay(1);
        mC.setCategory("NDBU");
        Menu mD = new Menu();
        mD.setMenuId("idMdChild");
        mD.setParentMenuId("ROOT");
        mD.setIsDisplay(1);
        mD.setCategory("NDBU");
        childrenMenus.add(mA);
        childrenMenus.add(mB);
        childrenMenus.add(mC);
        childrenMenus.add(mD);
        return childrenMenus;
    }

    /**
     *
     * //不測試 Test service.getExchangeRate 測試取得匯率資料
     * <p>
     * #4
     * </p>
     */
    void testGetExchangeRate() {
        List<ExchangeRate> ratesInCache = mockExchangeRates();

        doReturn(ratesInCache).when(exchangeRateService).getExchangeRatesByRateType(anyString());
        Locale defLocale = Locale.TAIWAN;

        doReturn("美金").when(currencyCodeCacheManager).getCurrencyName("USD", defLocale);
        doReturn("日圓").when(currencyCodeCacheManager).getCurrencyName("JPY", defLocale);
        doReturn("人民幣").when(currencyCodeCacheManager).getCurrencyName("CNY", defLocale);
        // doReturn("歐元").when(currencyCodeCacheManager).getCurrencyName("EUR", defLocale);

        DataInput input = new DataInput();
        input.setLocale(defLocale);
        // DataOutput output = new DataOutput();

        // service.getExchangeRate(input, output, null);

        // assertThat(output.getExchangeRates()).isNotNull();
        // assertThat(output.getExchangeRates()).hasSize(3);
    }

    private List<ExchangeRate> mockExchangeRates() {
        List<ExchangeRate> rates = new ArrayList<>();
        ExchangeRate rateUSD = new ExchangeRate();
        ExchangeRate rateUSDTYPENO99 = new ExchangeRate();
        ExchangeRate rateAUD = new ExchangeRate();
        ExchangeRate rateJPY = new ExchangeRate();
        ExchangeRate rateCNY = new ExchangeRate();
        rateUSD.setCurrencyEname1("USD");
        rateUSD.setExchangeTypeNo("0");
        // ===========================
        rateUSDTYPENO99.setCurrencyEname1("USD");
        rateUSDTYPENO99.setExchangeTypeNo("11");
        // ===========================
        rateAUD.setCurrencyEname1("AUD");
        rateAUD.setExchangeTypeNo("0");
        // ===========================
        rateJPY.setCurrencyEname1("JPY");
        rateJPY.setExchangeTypeNo("0");
        // ===========================
        rateCNY.setCurrencyEname1("CNY");
        rateCNY.setExchangeTypeNo("0");
        rates.add(rateUSD);
        rates.add(rateUSDTYPENO99);
        rates.add(rateAUD);
        rates.add(rateJPY);
        rates.add(rateCNY);
        return rates;
    }

    /**
     * Test service.getGetFxInterestRate 測試取得外幣存款利率
     * <p>
     * #3
     * </p>
     */
    @Test
    void testGetFxInterestRate() {
        List<FxInterestRate> ratesInCache = mockFxInterestRates();

        doReturn(ratesInCache).when(fxInterestRateCacheManager).getFxInterestRatesByTypeNo(anyString());
        Locale defLocale = Locale.TAIWAN;

        doReturn("美金").when(currencyCodeCacheManager).getCurrencyName("USD", defLocale);
        doReturn("日圓").when(currencyCodeCacheManager).getCurrencyName("JPY", defLocale);
        doReturn("人民幣").when(currencyCodeCacheManager).getCurrencyName("CNY", defLocale);

        DataInput input = new DataInput();
        input.setLocale(defLocale);
        DataOutput output = new DataOutput();

        service.getFxInterestRate(input, output);

        assertThat(output.getFxInterestRates()).isNotNull();
        assertThat(output.getFxInterestRates()).hasSize(3);
    }

    /**
     * 產生mock 存款利率資料
     */
    private List<FxInterestRate> mockFxInterestRates() {
        List<FxInterestRate> rates = new ArrayList<>();
        FxInterestRate rateUSD = new FxInterestRate();
        rateUSD.setCurrencyEname("USD");
        rateUSD.setRateType(0);
        FxInterestRate rateJPY = new FxInterestRate();
        rateJPY.setCurrencyEname("JPY");
        rateJPY.setRateType(0);
        FxInterestRate rateEUR = new FxInterestRate();
        rateEUR.setCurrencyEname("EUR");
        rateEUR.setRateType(0);
        FxInterestRate rateCNY = new FxInterestRate();
        rateCNY.setCurrencyEname("CNY");
        rateCNY.setRateType(0);
        rates.add(rateUSD);
        rates.add(rateJPY);
        rates.add(rateEUR);
        rates.add(rateCNY);
        return rates;
    }

    /**
     * Test service.getRespectInfo 測試取得招呼語
     * <p>
     * #2
     * </p>
     */
    @Test
    void testGetRespectInfo() {

        DataOutput output = new DataOutput();
        DataInput input = new DataInput();

        List<RespectInfo> mockInfos = Collections.emptyList();
        // 測試沒有拿到招呼語
        {
            doReturn(mockInfos).when(informationResource).getRespectInfos();
            service.getRespectInfo(null, input, output);
            assertThat(output.getRespectInfo()).isNull();
        }
        // 測試有拿到招呼語
        {
            mockInfos = Stream.of(new RespectInfo()).collect(Collectors.toList());
            doReturn(mockInfos).when(informationResource).getRespectInfos();
            service.getRespectInfo(null, input, output);
            assertThat(output.getRespectInfo()).isNotNull();
        }
    }

    /**
     * Test service.getUnreadMessageCountInThreeMonth 測試取得三個月內未讀訊息量
     * <p>
     * #1
     * </p>
     */
    // @Test
    void testGetUnreadMessageCountInThreeMonth() {

        DataInput input = new DataInput();
        input.setCustId("A123456789");
        input.setUserId("AA1234");
        input.setCompanyKind(2);
        DataOutput output = new DataOutput();

        // 以下2個變數是「notificationResource.getUnreadCountInThreeMonths」時要帶入的值，
        // 隨便給一下

        // 這邊假裝呼叫「notificationResource.getUnreadCountInThreeMonths」時，帶入值寫「anyInt()」，
        // 由於上方已經把companyKey, userKey設3，因此可觸發此Mock method，但如果companyKey, userKey
        // 有任一為null，下方doReturn...... 不會生效
        doReturn(30).when(notificationResource).getUnreadCountInThreeMonths(anyString(), anyString(), anyString(), anyInt());
        // when(notificationResource.getUnreadCountInThreeMonths(anyInt(),anyInt())).thenReturn(2);

        service.getUnreadMessageCountInThreeMonth(null, input, output);
        assertEquals(30, output.getUnreadCount());
    }

}
