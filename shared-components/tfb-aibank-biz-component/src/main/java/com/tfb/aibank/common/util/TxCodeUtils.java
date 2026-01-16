/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;

// @formatter:off
/**
 * @(#)TxCodeUtils.java
 * 
 * <p>Description:簡訊動態密碼-交易代碼</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TxCodeUtils {

    private static final IBLog logger = IBLog.getLog(TxCodeUtils.class);

    public static final int TX_CODE_LENGTH = 5;

    public static final String LETTER_ARR[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    public static final int NUMBER_ARR[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    /**
     * 產生交易代碼 (數字型態)
     * 
     * @return
     */
    public static String genTxCodeNumberType(int txCodeLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < txCodeLength; i++) {
            sb.append(NUMBER_ARR[new SecureRandom().nextInt(NUMBER_ARR.length)]);
        }
        return sb.toString();
    }

    /**
     * 產生交易代碼 (英數字型態 不重複) 最大長度36(全部涵蓋)
     * 
     * @return
     * @throws Exception
     */
    public static String genTxCodeMixedType(int txCodeLength) throws ActionException {

        if (txCodeLength > 36) {
            logger.error("txCodeLength must <= 36");
            throw ExceptionUtils.getActionException("txCodeLength must <= 36", CommonErrorCode.UNKNOWN_EXCEPTION);
        }

        String[] LETTER_NUMBER_ARR = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

        List<String> stringList = new ArrayList<String>(Arrays.asList(LETTER_NUMBER_ARR));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < txCodeLength; i++) {
            int index = new SecureRandom().nextInt(stringList.size());
            sb.append(stringList.get(index));
            stringList.remove(index);
        }
        return sb.toString();
    }

    /**
     * 產生交易代碼 (文字型態)
     * 
     * @return
     */
    public static String genTxCode(int txCodeLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < txCodeLength; i++) {
            sb.append(LETTER_ARR[new SecureRandom().nextInt(LETTER_ARR.length)]);
        }
        return sb.toString();
    }

    /**
     * 產生交易代碼 (文字型態)
     * 
     * @return
     */
    public static String genTxCode() {
        return genTxCode(TX_CODE_LENGTH);
    }

    /**
     * 產生TAC_LEN(晶片金融卡TAC長度) 三碼
     * 
     * @return
     */
    public static String procTACLength(String tacHexData) {
        String tacTmp = "000" + (tacHexData.length() / 2); // HexString 2個字元--> 1 Byte
        return tacTmp.substring(tacTmp.length() - 3, tacTmp.length());
    }

}
