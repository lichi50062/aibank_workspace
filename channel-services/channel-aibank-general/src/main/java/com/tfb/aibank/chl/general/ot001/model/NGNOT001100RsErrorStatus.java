/**
 * 
 */
package com.tfb.aibank.chl.general.ot001.model;

//@formatter:off
/**
* @(#)NGNOT001100Rs.java 
* 
* <p>Description:登入 FIDO 綁定初始化 錯誤訊息</p>
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
public class NGNOT001100RsErrorStatus {

    /** 系統代碼 */
    private String sys = "";

    /** 錯誤代碼 */
    private String code;

    /** 狀態描述 */
    private String desc = "";

    /**
     * @return the sys
     */
    public String getSys() {
        return sys;
    }

    /**
     * @param sys
     *            the sys to set
     */
    public void setSys(String sys) {
        this.sys = sys;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
