/**
 * 
 */
package com.tfb.aibank.chl.component.userdatacache.model;

//@formatter:off
/**
* @(#)EBHN002ResponseRepeat.java
* 
* <p>Description:EBHN002 房貸可增貸額度 Repeat</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/12 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EBHN002ResponseRepeat {

    /** 擔保品編號 */
    private String collateralNo;

    /** 擔保品座落地址 */
    private String collateralAddr;

    /** 房貸可增貸額度 */
    private String collateralAmttop;

    /**
     * @return the collateralNo
     */
    public String getCollateralNo() {
        return collateralNo;
    }

    /**
     * @param collateralNo
     *            the collateralNo to set
     */
    public void setCollateralNo(String collateralNo) {
        this.collateralNo = collateralNo;
    }

    /**
     * @return the collateralAd
     */
    public String getCollateralAddr() {
        return collateralAddr;
    }

    /**
     * @param collateralAd
     *            the collateralAd to set
     */
    public void setCollateralAddr(String collateralAddr) {
        this.collateralAddr = collateralAddr;
    }

    /**
     * @return the collateralAmttop
     */
    public String getCollateralAmttop() {
        return collateralAmttop;
    }

    /**
     * @param collateralAmttop
     *            the collateralAmttop to set
     */
    public void setCollateralAmttop(String collateralAmttop) {
        this.collateralAmttop = collateralAmttop;
    }

}
