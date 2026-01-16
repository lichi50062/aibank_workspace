/**
 * 
 */
package com.tfb.aibank.chl.general.ot003.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.component.accountselect.model.TwAccountSelector;
import com.tfb.aibank.chl.model.homepagecard.HomePageCard;

//@formatter:off
/**
* @(#)NGNOT003010Rs.java
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
public class NGNOT003010Rs implements RsData {
    /** 最近轉帳/常用/約定 元件資料 */
    private TwAccountSelector accountSelector;
    /** 是否登入 */
    private boolean loggedIn;
    /** 是信用卡戶 */
    private boolean isCredit;
    /** 線上開立數位存款帳戶/開戶進度查詢連結 */
    private String registerUrl;
    /** 信用卡會員登入內容 */
    private List<HomePageCard> homePageCards;
    /** 一般會員無帳號情境 */
    private boolean showNoAccount;
    /** 所有帳號都未開通轉出服務情境 */
    private boolean showNoOpenTrans;

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn
     *            the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * @return the isCredit
     */
    public boolean isCredit() {
        return isCredit;
    }

    /**
     * @param isCredit
     *            the isCredit to set
     */
    public void setCredit(boolean isCredit) {
        this.isCredit = isCredit;
    }

    /**
     * @return the accountSelector
     */
    public TwAccountSelector getAccountSelector() {
        return accountSelector;
    }

    /**
     * @param accountSelector
     *            the accountSelector to set
     */
    public void setAccountSelector(TwAccountSelector accountSelector) {
        this.accountSelector = accountSelector;
    }

    /**
     * @return the registerUrl
     */
    public String getRegisterUrl() {
        return registerUrl;
    }

    /**
     * @param registerUrl
     *            the registerUrl to set
     */
    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    /**
     * @return the showNoAccount
     */
    public boolean isShowNoAccount() {
        return showNoAccount;
    }

    /**
     * @param showNoAccount
     *            the showNoAccount to set
     */
    public void setShowNoAccount(boolean showNoAccount) {
        this.showNoAccount = showNoAccount;
    }

    /**
     * @return the showNoOpenTrans
     */
    public boolean isShowNoOpenTrans() {
        return showNoOpenTrans;
    }

    /**
     * @param showNoOpenTrans
     *            the showNoOpenTrans to set
     */
    public void setShowNoOpenTrans(boolean showNoOpenTrans) {
        this.showNoOpenTrans = showNoOpenTrans;
    }

    public List<HomePageCard> getHomePageCards() {
        return homePageCards;
    }

    public void setHomePageCards(List<HomePageCard> homePageCards) {
        this.homePageCards = homePageCards;
    }
}
