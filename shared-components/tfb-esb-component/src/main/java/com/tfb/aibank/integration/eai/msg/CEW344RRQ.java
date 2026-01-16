package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import tw.com.ibm.mf.eb.CEW344RSvcRqType;

public class CEW344RRQ extends EAIRequest<CEW344RSvcRqType> {
    private static final long serialVersionUID = -7581350443750586351L;

    /**
     * 建構子
     */
    public CEW344RRQ() {
        super("CEW344R");
    }
}
