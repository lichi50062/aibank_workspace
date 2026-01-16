/**
 * 
 */
package com.tfb.aibank.chl.general.ot003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT003010Rq.java
*
* <p>Description: 收付 新增常用帳號</p>
*
* <p>Modify History:</p>
* <ol>v1, 2024/09/05, Yoyo Lin
*  <li>[新增說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT003012Rq implements RqData {

    /** 常用帳號暱稱 */
    private String favoriteName;

    /** 最近帳號key */
    private String favoriteAcctKey;

    /**
     * @return the favoriteName
     */
    public String getFavoriteName() {
        return favoriteName;
    }

    /**
     * @param favoriteName
     *            the favoriteName to set
     */
    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    /**
     * @return {@link #favoriteAcctKey}
     */
    public String getFavoriteAcctKey() {
        return favoriteAcctKey;
    }

    /**
     * @param favoriteAcctKey
     *            {@link #favoriteAcctKey}
     */
    public void setFavoriteAcctKey(String favoriteAcctKey) {
        this.favoriteAcctKey = favoriteAcctKey;
    }

}
