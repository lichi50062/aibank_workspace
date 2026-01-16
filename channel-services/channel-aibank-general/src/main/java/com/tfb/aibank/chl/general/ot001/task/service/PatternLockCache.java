/**
 * 
 */
package com.tfb.aibank.chl.general.ot001.task.service;

//@formatter:off
/**
* @(#)PatternLockCache.java 
* 
* <p>Description:登入程序 Helper</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
public class PatternLockCache {

    /**
     * 圖形因子
     */
    private String coefficient;

    /**
     * 圖形密碼暫存
     */
    private String pinBlock;

    /**
     * @return the coefficient
     */
    public String getCoefficient() {
        return coefficient;
    }

    /**
     * @param coefficient
     *            the coefficient to set
     */
    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * @return the pinBlock
     */
    public String getPinBlock() {
        return pinBlock;
    }

    /**
     * @param pinBlock
     *            the pinBlock to set
     */
    public void setPinBlock(String pinBlock) {
        this.pinBlock = pinBlock;
    }

}
