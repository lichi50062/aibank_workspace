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
package com.ibm.tw.ibmb.component.systemparam;

import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)SystemParam.java
* 
* <p>Description:系統參數共用介面</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/17, jeff   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public interface ISystemParam extends IEnum {

    /** 取得系統參數類別 */
    public IBSystemId getCategory();

    /** 取得系統參數鍵值 */
    public String getKey();

    /** 是否為密碼欄位 */
    public int getPinFlag();

    /** 取得參數說明 */
    @Override
    public String getMemo();

}
