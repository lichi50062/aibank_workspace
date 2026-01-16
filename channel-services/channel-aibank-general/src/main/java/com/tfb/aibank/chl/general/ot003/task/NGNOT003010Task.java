package com.tfb.aibank.chl.general.ot003.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.accountselect.TwAccountSelectorService;
import com.tfb.aibank.chl.component.accountselect.model.TwAccountSelector;
import com.tfb.aibank.chl.component.accountselect.model.TwPayerRecord;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.component.homepagecard.HomepageCardCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.general.ot003.model.NGNOT003010Rq;
import com.tfb.aibank.chl.general.ot003.model.NGNOT003010Rs;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.chl.model.homepagecard.HomePageCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.TransOutAcctType;

// @formatter:off
/**
 * @(#)NGNOT003010Task.java
 *
 * <p>Description: 收付</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/06/13, Alex PY Li
 *  <li>[新增說明]</li>
 * </ol>
 */
//@formatter:on
@Component
@Scope("prototype")
public class NGNOT003010Task extends AbstractAIBankBaseTask<NGNOT003010Rq, NGNOT003010Rs> {
    @Autowired
    private HomepageCardCacheManager homepageCardCacheManager;

    @Autowired
    private UserDataCacheService userAccountService;

    @Autowired
    private TwAccountSelectorService accountSelectorService;

    @Autowired
    protected SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NGNOT003010Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNOT003010Rq rqData, NGNOT003010Rs rsData) throws ActionException {
        AIBankUser loginUser = getLoginUser();
        if (null != loginUser) {
            rsData.setLoggedIn(true);
            if (loginUser.getCompanyKind() == 3 || loginUser.getCompanyKind() == 4) {
                rsData.setCredit(true);
                rsData.setHomePageCards(this.getHomePageCards());
                // 開啟線上開立數位存款帳戶URL，取SYSTEM_PARAM. PARAM_VALUE(CATEGORY=AI_BANK AND PARAM_KEY=LOGIN _REGISTER_URL)。
                String registerUrl = systemParamCacheManager.getValue("AI_BANK", "LOGIN_REGISTER_URL");
                rsData.setRegisterUrl(registerUrl);
            }
            else {
                // 讀取「臺幣約定轉出帳號」清單
                List<TransOutAccount> agreedOutAccounts = userAccountService.getTransOutAccounts(loginUser, getLocale(), TransOutAcctType.TW_SAVING_CHECK_DOC_NO_TRANS_OUT_ACCT, false);

                // 若無可使用的轉出帳號，導至「共通錯誤頁」，顯示錯誤訊息：親愛的客戶您好：本服務僅提供持有本行臺幣存款帳戶的行動銀行客戶使用，歡迎您至本行各服務據點申辦。如有疑問，請洽本行客服中心02-8751-6665
                if (CollectionUtils.isEmpty(agreedOutAccounts)) {
                    rsData.setShowNoAccount(true);
                    return;
                }
                // #1719 是否至少有一筆帳號開通轉出服務
                boolean hasOpenTransFlagY = agreedOutAccounts.stream().anyMatch(transOutAccount -> transOutAccount.isOpenTransFlag());
                if (!hasOpenTransFlagY) {
                    rsData.setShowNoOpenTrans(true);
                    return;
                }
                TwAccountSelector accountSelector = initAccountSelector(loginUser);
                this.filterAndSort(accountSelector.getPayers());
                logger.debug(JsonUtils.getJson(accountSelector));
                rsData.setAccountSelector(accountSelector);
            }
        }
    }

    // 過濾帳號及排序
    private List<TwPayerRecord> filterAndSort(List<TwPayerRecord> accts) {
        return accts.stream().filter(Objects::nonNull) // 排除空物件
                .filter(this.distinctByKey(TwPayerRecord::getPayerAccount)) // 過濾重複帳號
                .sorted(this.byAccountId) // 依帳號排序
                .collect(Collectors.toList());
    }

    /**
     * 依帳號排序
     */
    public Comparator<TwPayerRecord> byAccountId = Comparator.comparing(TwPayerRecord::getPayerAccount);

    /**
     * 過濾重複鍵值
     *
     * @param keyExtractor
     * @return
     */
    public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }

    private TwAccountSelector initAccountSelector(AIBankUser loginUser) {

        TwAccountSelector accountSelector = new TwAccountSelector();

        // 設置初始資料
        accountSelectorService.setInitData(true, TransOutAcctType.TW_SAVING_CHECK_DOC_NO_TRANS_OUT_ACCT, accountSelector, loginUser, getLocale());
        // 暫存
        setCache(TwAccountSelectorService.CACHE_KEY, accountSelector);

        return accountSelector;
    }

    /**
     * 取得信用卡登入牌卡
     *
     * @return
     */
    private List<HomePageCard> getHomePageCards() {
        Predicate<HomepageCard> adCondition = (hc) -> StringUtils.equals("NGNOT003", hc.getCardUsedTaskId()) && StringUtils.equals("TASK_CARD", hc.getCardTemplate()) && hc.getTaType() != 1;
        List<HomepageCard> cards = homepageCardCacheManager.getMulHomePageCardByPredicate(adCondition);
        List<HomePageCard> homePageCards = new ArrayList<HomePageCard>();
        if (CollectionUtils.isNotEmpty(cards)) {
            IBUtils.sort(cards, "startTime", true);
            for (HomepageCard card : cards) {
                HomePageCard homePageCard = new HomePageCard();
                homePageCard.setContent(card.getCardDesc());
                homePageCard.setCardevent(card.getCardEvent());
                homePageCard.setCardTarget(card.getCardTarget());
                homePageCards.add(homePageCard);
                break;
            }
        }
        return homePageCards;
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
