package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import tw.com.ibm.mf.eb.CEW344RSvcRqType;
import tw.com.ibm.mf.eb.CEW344RSvcRsType;

public class CEW344RRS extends EAIResponse<CEW344RSvcRqType, CEW344RSvcRsType> {

    /**
     * 建構子
     */
    public CEW344RRS() {
        super("CEW344R");
    }

//    @Override
//    protected boolean isNoData(String errId) {
//        if (StringUtils.equals("V003", errId)) {
//            return true;
//        }
//        return false;
//    }
}
