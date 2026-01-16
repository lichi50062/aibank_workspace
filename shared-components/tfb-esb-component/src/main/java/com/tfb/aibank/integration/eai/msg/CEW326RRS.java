package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;

import tw.com.ibm.mf.eb.CEW326RSvcRqType;
import tw.com.ibm.mf.eb.CEW326RSvcRsType;

//@formatter:off
/**
* @(#)CEW326RRS.java
* 
* <p>Description:分期未入帳金額查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/02, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW326RRS extends EAIResponse<CEW326RSvcRqType, CEW326RSvcRsType> {

    public CEW326RRS() {
        super("CEW326R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equals("V003", errId);
    }
}
