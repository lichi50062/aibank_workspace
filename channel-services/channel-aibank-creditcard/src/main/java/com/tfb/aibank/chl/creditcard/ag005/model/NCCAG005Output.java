package com.tfb.aibank.chl.creditcard.ag005.model;

import com.tfb.aibank.chl.creditcard.resource.dto.CEW013RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW327RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW345RResponse;

// @formatter:off
/**
 * @(#)NCCAG005Output.java
 * 
 * <p>Description:保費權益設定 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/28, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCAG005Output {

	 /** 電文 CEW327R 下行 */
    private CEW327RResponse cew327RResponse;
    
	/** 電文 CEW345R 下行 */
    private CEW345RResponse cew345RResponse;

    /** 電文 CEW013R 下行*/
    private CEW013RRes cew013rRes;

    public CEW327RResponse getCew327RResponse() {
        return cew327RResponse;
    }

    public void setCew327RResponse(CEW327RResponse cew327rResponse) {
        cew327RResponse = cew327rResponse;
    }
    
    public CEW345RResponse getCew345RResponse() {
        return cew345RResponse;
    }

    public void setCew345RResponse(CEW345RResponse cew345rResponse) {
        cew345RResponse = cew345rResponse;
    }

    public CEW013RRes getCew013rRes() {
        return cew013rRes;
    }

    public void setCew013rRes(CEW013RRes cew013rRes) {
        this.cew013rRes = cew013rRes;
    }

}
