package com.tfb.aibank.chl.component.userdatacache.model;

import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * @(#)SDACTQ12ResRep.java
 *
 * <p>Description:SDACTQ12 信託資產_SI產品約當臺幣</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Andy Kuo	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SDACTQ12ResRep {

    /** 活存帳號 */
    private String acctId14;

    /** Tx Repeat */
    private List<SDACTQ12TxRepeat> repeats = new ArrayList<>();

    /**
     * @return the acctId14
     */
    public String getAcctId14() {
        return acctId14;
    }

    /**
     * @param acctId14
     *            the acctId14 to set
     */
    public void setAcctId14(String acctId14) {
        this.acctId14 = acctId14;
    }

    /**
     * @return the repeats
     */
    public List<SDACTQ12TxRepeat> getRepeats() {
        return repeats;
    }

    /**
     * @param repeats
     *            the repeats to set
     */
    public void setRepeats(List<SDACTQ12TxRepeat> repeats) {
        this.repeats = repeats;
    }

}
