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
package com.tfb.aibank.biz.component.datacenter.model;

//@formatter:off
/**
* @(#)OfferActionRq.java
* 
* <p>Description:數據中心基本欄位 - Rq</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/08, Alex PY Li 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UserTagRq implements DataCenterBaseRq {

    /*
     * (non-Javadoc)
     * 
     * @see com.tfb.aibank.biz.component.datacenter.model.DataCenterBaseRq#getPath()
     */
    @Override
    public String getPath() {
        return "/userTags";
    }

    /** 為唯一識別碼=使用者身份證字號(10碼)+誤別碼(1碼)，將採 AES256加密機制 */
    private String id;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }


}
