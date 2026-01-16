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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.common.type.IDType;

// @formatter:off
/**
 * @(#)BaNCSUtil.java
 * 
 * <p>Description:證件號格式 與 證件類型對應關係</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BaNCSUtil {

    /**
     * 依證件號判斷證件類型(IDTYPE)
     * 
     * @param idno
     * @return
     */
    public static String getIDTYPE(String idno) {
        return getIDType(idno).getCode();
    }

    /**
     * 依證件號判斷證件類型(IDTYPE)
     * 
     * @param idno
     *            證件號
     * @return ID_TYPE
     */
    public static IDType getIDType(String idno) {
        // sIDNO：證件號長度
        int len = idno.length();
        // 證件號全部為數字
        if (NumberUtils.isDigits(idno)) {
            switch (len) {
            // 本國法人統一編號：證件號長度=8，IDTYPE=21
            case 8:
                return IDType.LEGAL_ENTITY_CARD_NUMBER;
            // 內政部配賦統一證號：證件號長度=7，證件號長度=10，IDTYPE=19
            case 7:
            case 10:
                return IDType.CERTIFICATE_NUMBER;
            // 非屬上述規則，IDTYPE=39
            default:
                return IDType.NONE_OF_ABOVE;
            }
        }
        // 證件號不為全數字
        else {
            switch (len) {
            // 證件號長度=10
            case 10:
                // 身分證統一編號：第1位:英文字母、第2位:1或2、第3~10位:數字，IDTYPE=11
                if (isID11(idno)) {
                    return IDType.ID_CARD_NUMBER_11;
                }
                // 身分證統一編號：第1位:英文字母、第2位:英文字母或8或9、第3~10位:數字，IDTYPE=12
                else if (isID12(idno)) {
                    return IDType.ID_CARD_NUMBER_12;
                }
                // 境外自然人證號：第1~8位:數字、第9~10位:英文字母，IDTYPE=13
                else if (NumberUtils.isDigits(idno.substring(0, 8)) && isLetter(idno.substring(8, idno.length()))) {
                    return IDType.OVERSEAS_NATURAL_ID_NUMBER;
                }
                // 虛擬ID(市庫統制帳戶)：固定為「88treasury」，IDTYPE=33
                else if (idno.equals("88treasury")) {
                    return IDType.VIRTUAL_ID;
                }
                // 虛擬ID：第1~2位:固定為「88」、第3~10位:英文字母，IDTYPE=33
                else if (idno.substring(0, 2).equals("88") && isLetter(idno.substring(2, idno.length()))) {
                    return IDType.VIRTUAL_ID;
                }
                // 非屬上述規則，IDTYPE=39
                return IDType.NONE_OF_ABOVE;

            // 證件號長度=11
            case 11:
                // 身分證統一編號：第1位:英文字母、第2位:1或2、第3~10位:數字，第11位:不需判斷，IDTYPE=11
                if (isID11(idno.substring(0, 10))) {
                    return IDType.ID_CARD_NUMBER_11;
                }
                // 身分證統一編號：第1位:英文字母、第2位:英文字母或8或9、第3~10位:數字，第11位:不需判斷，IDTYPE=12
                else if (isID12(idno.substring(0, 10))) {
                    return IDType.ID_CARD_NUMBER_12;
                }
                // 境外自然人證號：第1~8位:數字、第9~10位:英文字母、第11位:不需判斷，IDTYPE=13
                else if (NumberUtils.isDigits(idno.substring(0, 8)) && isLetter(idno.substring(8, 10))) {
                    return IDType.OVERSEAS_NATURAL_ID_NUMBER;
                }
                // 本國法人統一編號：第1~8位:數字，IDTYPE=21
                else if (NumberUtils.isDigits(idno.substring(0, 8)) && StringUtils.length(StringUtils.trimAllBigSpace(idno)) == 8) {
                    return IDType.LEGAL_ENTITY_CARD_NUMBER;
                }
                // 徵審ID：第1~3位:固定為「TFB」、第4~12位:數字，IDTYPE=31
                else if (idno.substring(0, 3).equals("TFB") && NumberUtils.isDigits(idno.substring(3, idno.length()))) {
                    return IDType.EXAMINATION_ID;
                }
                // 集團ID：第1~3位:固定為「GRP」、第4~12位:數字，IDTYPE=32
                else if (idno.substring(0, 3).equals("GRP") && NumberUtils.isDigits(idno.substring(3, idno.length()))) {
                    return IDType.GROUP_ID;
                }
                // SWIFT Code：第1~6位:英文字母、第7~11位:英文字母或數字，IDTPYE=23
                else if (isLetter(idno.substring(0, 6)) && (isLetterOrDigit(idno.substring(6, idno.length())))) {
                    return IDType.SWITF_CODE;
                }
                // 虛擬ID：第1~2位:固定為「88」、第3~11位:英文字母，IDTYPE=33
                else if (idno.substring(0, 2).equals("88") && isLetter(idno.substring(2, idno.length()))) {
                    return IDType.VIRTUAL_ID;
                }
                // 非屬上述規則，IDTYPE=39
                return IDType.NONE_OF_ABOVE;

            // 證件號長度=8
            case 8:
                // 境外法人虛擬統編/聯徵中心編配境外法人虛擬統編：第1~4位:英文字母、第5~8位:數字，IDTYPE=22
                if (isLetter(idno.substring(0, 4)) && NumberUtils.isDigits(idno.substring(4, idno.length()))) {
                    return IDType.OVERSEAS_VIRTUAL_NUMBER;
                }
                // SWIFT Code：第1~6位:英文字母、第7~8位:英文字母或數字，IDTPYE=23
                else if (isLetter(idno.substring(0, 6)) && (isLetterOrDigit(idno.substring(6, idno.length())))) {
                    return IDType.SWITF_CODE;
                }
                // 虛擬ID(Walking客戶)：固定為「88Walkin」，IDTYPE=33
                else if (idno.equals("88Walkin")) {
                    return IDType.VIRTUAL_ID;
                }
                // 虛擬ID：第1~2位:固定為「88」、第3~8位:英文字母，IDTYPE=33
                else if (idno.substring(0, 2).equals("88") && isLetter(idno.substring(2, idno.length()))) {
                    return IDType.VIRTUAL_ID;
                }
                // 非屬上述規則，IDTYPE=39
                return IDType.NONE_OF_ABOVE;

            // 證件號長度 <> 10、11、8
            default:
                // 虛擬ID：第1~2位:固定為「88」、第3位以後:英文字母，IDTYPE=33
                if (idno.substring(0, 2).equals("88") && isLetter(idno.substring(2, idno.length()))) {
                    return IDType.VIRTUAL_ID;
                }
                // 非屬上述規則，IDTYPE=39
                return IDType.NONE_OF_ABOVE;
            }
        }
    }

    /**
     * 判斷字串內容是否全為英文字母
     * 
     * @param str
     * @return
     */
    private static boolean isLetter(String str) {
        return str.chars().allMatch(Character::isLetter);
    }

    /**
     * 判斷字串內容是否為英文字母 or 數字
     * 
     * @param str
     * @return boolean
     */
    private static boolean isLetterOrDigit(String str) {
        return str.codePoints().allMatch(Character::isLetterOrDigit);
    }

    /**
     * 判斷本國人ID
     * 
     * @param str
     * @return boolean
     */
    private static boolean isID11(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z][1-2][0-9]{8}$");
        Matcher result = pattern.matcher(str);
        if (!result.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判斷外國人ID
     * 
     * @param str
     * @return boolean
     */
    private static boolean isID12(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z][A-Za-z8-9][0-9]{8}$");
        Matcher result = pattern.matcher(str);
        if (!result.matches()) {
            return false;
        }
        return true;
    }

    // @formatter:off
    /**
     * 取用 身分證字號(10碼)＋ 檢查 誤別碼(1碼)，誤別碼有值且不為0時才帶入誤別碼
     * 
     * 若重覆碼為0, 則取前10位,否則回覆11位:
     * 'A1234567890' --> 'A123456789'
     * '87178818 0' --> '87178818'
     * 'A1234567891' --> 'A1234567891'
     * '87178818  1' --> '87178818  1'
     * 
     * @param custIxd
     * @param uidDup
     * @return
     */
    // @formatter:on
    public static String getCustIdAndCheckDup(String custIxd, String uidDup) {
        if (StringUtils.isBlank(uidDup) || StringUtils.equals("0", uidDup)) {
            return custIxd;
        }
        else {
            return StringUtils.rightPad(custIxd, 10) + uidDup;
        }
    }

    /**
     * 拆解「證件號」為「身分證字號」、「誤別碼」
     * 
     * @param idNumber
     * @return 0: 身分證字號；1:誤別碼
     */
    public static String[] convertIdNumber(String idNumber) {
        if (StringUtils.length(idNumber) > 10) {
            return new String[] { StringUtils.trimAllBigSpace(StringUtils.left(idNumber, 10)), StringUtils.substring(idNumber, 10, 11) };
        }
        else {
            return new String[] { StringUtils.trimAllBigSpace(idNumber), "0" };
        }
    }
}
