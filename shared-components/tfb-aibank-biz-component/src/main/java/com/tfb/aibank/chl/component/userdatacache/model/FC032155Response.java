package com.tfb.aibank.chl.component.userdatacache.model;

import java.util.List;

//@formatter:off
/**
* @(#)FC032155Response.java
* 
* <p>Description:本行客戶註記下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on

public class FC032155Response {
    private List<FC032155RsBody> rsList;

    /**
     * @return the rsList
     */
    public List<FC032155RsBody> getRsList() {
        return rsList;
    }

    /**
     * @param rsList the rsList to set
     */
    public void setRsList(List<FC032155RsBody> rsList) {
        this.rsList = rsList;
    }
}
