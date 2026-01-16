package com.tfb.aibank.chl.general.qu003.model;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;

// @formatter:off
/**
 * @(#)NGNQU003060Rs.java
 * 
 * <p>Description:優惠 060 我的權益頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU003060Rs implements RsData {

    private String errorTitle;
    private String errorDesc;

    private HomepageCard homepageCard;

    private FinMgmMemberData finMgmMemberData;

    private Map<String, String> dicValueMap;
    
    private String premiumReward_url;
    
    //是否為高貢獻客戶
    private boolean highContributeCust;

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public HomepageCard getHomepageCard() {
        return homepageCard;
    }

    public void setHomepageCard(HomepageCard homepageCard) {
        this.homepageCard = homepageCard;
    }

    public FinMgmMemberData getFinMgmMemberData() {
        return finMgmMemberData;
    }

    public void setFinMgmMemberData(FinMgmMemberData finMgmMemberData) {
        this.finMgmMemberData = finMgmMemberData;
    }

    public Map<String, String> getDicValueMap() {
        return dicValueMap;
    }

    public void setDicValueMap(Map<String, String> dicValueMap) {
        this.dicValueMap = dicValueMap;
    }

    public String getPremiumReward_url() {
        return premiumReward_url;
    }

    public void setPremiumReward_url(String premiumReward_url) {
        this.premiumReward_url = premiumReward_url;
    }

    public boolean isHighContributeCust() {
        return highContributeCust;
    }

    public void setHighContributeCust(boolean highContributeCust) {
        this.highContributeCust = highContributeCust;
    }
}
