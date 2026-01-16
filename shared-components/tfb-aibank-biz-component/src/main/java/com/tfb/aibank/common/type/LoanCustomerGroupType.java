/**
 * 
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)CreditCardIdentityType.java
* 
* <p>Description:貸款客群別</p>
* <p>企業客群</p>
* <p>客群一</p>
* <p>客群二</p>
* <p>客群三</p>
* <p>客群四</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum LoanCustomerGroupType implements IEnum {

    GROUP_ENTERPRISE(0, "企業客群"),

    GROUP_1(1, "客群一"),

    GROUP_2(2, "客群二"),

    GROUP_3(3, "客群三"),

    GROUP_4(4, "客群四");

    /** 客群 * */
    private int code;

    /** 狀態說明* */
    private String memo;

    LoanCustomerGroupType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static LoanCustomerGroupType find(int code) {
        for (LoanCustomerGroupType type : LoanCustomerGroupType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return LoanCustomerGroupType.GROUP_4;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.tw.commons.type.IEnum#getMemo()
     */
    @Override
    public String getMemo() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

}
