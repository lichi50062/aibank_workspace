package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001036Rq.java 
* 
* <p>Description:已驗過OTP</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230928, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001037Rq implements RqData {
    /** 是否完成OTP驗證 */
    private boolean isOtpAuthed;

    /**
     * @return the isOtpAuthed
     */
    public boolean isOtpAuthed() {
        return isOtpAuthed;
    }

    /**
     * @param isOtpAuthed
     *            the isOtpAuthed to set
     */
    public void setOtpAuthed(boolean isOtpAuthed) {
        this.isOtpAuthed = isOtpAuthed;
    }

}
