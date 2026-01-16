package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001014Rs.java 
* 
* <p>Description:取得時間註記Task</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230605, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001015Rs implements RsData {

    /**
     * 時間因子
     */
    private String passWithTime;

    /**
     * @return the passWithTime
     */
    public String getPassWithTime() {
        return passWithTime;
    }

    /**
     * @param passWithTime
     *            the passWithTime to set
     */
    public void setPassWithTime(String passWithTime) {
        this.passWithTime = passWithTime;
    }

}
