/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot005.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NSTOT005020Rq.java
* 
* <p>Description:『最近轉帳/常用/約定』帳號選擇元件 - 用轉出取得對應轉入 - RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/22, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT005050Rq implements RqData {

    /** 轉入類型 1:常用;2:約定 */
    private int type;

    /** 轉出帳號鍵值 */
    private String key;

    /** 需要同步約定 */
    private boolean syncAgreeIn;

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     *         the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *         the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    public boolean isSyncAgreeIn() {
        return syncAgreeIn;
    }

    public void setSyncAgreeIn(boolean syncAgreeIn) {
        this.syncAgreeIn = syncAgreeIn;
    }
}
