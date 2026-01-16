package com.tfb.aibank.chl.creditcard.qu001.model;

//@formatter:off
/**
* @(#)NCCQQU001BillingDetail.java
* 
* <p>Description:信用卡總覽 紅利回饋</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/28, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQQU001BonusRewards {
    /** 紅利 */
    private NCCQQU001BonusReward dividend;

    /** 哩程 */
    private NCCQQU001BonusReward mileage;

    /** 福華 */
    private NCCQQU001BonusReward fuhua;

    /**
     * @return the dividend
     */
    public NCCQQU001BonusReward getDividend() {
        return dividend;
    }

    /**
     * @param dividend
     *            the dividend to set
     */
    public void setDividend(NCCQQU001BonusReward dividend) {
        this.dividend = dividend;
    }

    /**
     * @return the mileage
     */
    public NCCQQU001BonusReward getMileage() {
        return mileage;
    }

    /**
     * @param mileage
     *            the mileage to set
     */
    public void setMileage(NCCQQU001BonusReward mileage) {
        this.mileage = mileage;
    }

    /**
     * @return the fuhua
     */
    public NCCQQU001BonusReward getFuhua() {
        return fuhua;
    }

    /**
     * @param fuhua
     *            the fuhua to set
     */
    public void setFuhua(NCCQQU001BonusReward fuhua) {
        this.fuhua = fuhua;
    }

}
