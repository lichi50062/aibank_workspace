package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* 
* <p>Description:</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250610, benson
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001016Rq implements RqData {
    
    /* null 0 -> init, 1 -> query, 2 -> timeout, 3 -> cancel*/
    private Integer action;

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

  
    
}
