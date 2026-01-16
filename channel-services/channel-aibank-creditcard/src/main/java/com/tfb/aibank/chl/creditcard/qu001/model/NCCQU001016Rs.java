package com.tfb.aibank.chl.creditcard.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001012Rs.java
* 
* <p>Description:信用卡總覽 功能首頁 取得紅利回饋 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/08, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001016Rs implements RsData {
    /** 紅利回饋資料發生錯誤 */
    private boolean cew306rError;
    /** 紅利回饋 */
    private NCCQQU001BonusRewards bonusRewards;
    
    /**
     * @return the cew306rError
     */
    public boolean isCew306rError() {
        return cew306rError;
    }
    
    /**
     * @param cew306rError
     *            the cew306rError to set
     */
    public void setCew306rError(boolean cew306rError) {
        this.cew306rError = cew306rError;
    }

    /**
     * @return the bonusRewards
     */
    public NCCQQU001BonusRewards getBonusRewards() {
        return bonusRewards;
    }

    /**
     * @param bonusRewards
     *            the bonusRewards to set
     */
    public void setBonusRewards(NCCQQU001BonusRewards bonusRewards) {
        this.bonusRewards = bonusRewards;
    }

}
