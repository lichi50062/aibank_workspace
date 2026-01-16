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
package com.ibm.tw.commons.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.ibm.tw.ibmb.validate.ValidatorUtility;

/**
 * 實作資料遮蔽原則
 *
 * @author horance
 * @version 1.0
 */
public class DataMaskUtil {

    private DataMaskUtil() {
        throw new IllegalAccessError("Utility class");
    }

    /** 預設隱碼符號 */
    public static final String DEFAULT_MASK = "*";

    public static final String CIRCLE_MASK = "Ｏ";

    public static final String BIG_STAR_MASK = "＊";

    public static final String EMAIL_SEPERATOR = "@";

    public static final String SYMBOL_DASH = "-";

    /**
     * <p>
     * 遮蔽 銀行帳號
     * </p>
     * <p>
     * 帳號<10碼建議銀行帳號倒數第6個字元至倒數第5個字元，以特殊字元(例如：*)或其他隨機字元遮蔽
     * </p>
     * <p>
     * 帳號>=10碼建議銀行帳號倒數第8個字元至倒數第5個字元，以特殊字元(例如：*)或其他隨機字元遮蔽
     * </p>
     *
     * @param bankAccount
     * @return
     */
    public static String maskBankAccount(String bankAccount) {
        if (StringUtils.isBlank(bankAccount)) {
            return bankAccount;
        }

        bankAccount = StringUtils.trimToEmptyEx(bankAccount);
        if (bankAccount.length() > 9) {
            return """
                    %s%s%s
                    """.formatted(bankAccount.substring(0, bankAccount.length() - 8), DEFAULT_MASK.repeat(4), bankAccount.substring(bankAccount.length() - 4));
        }
        else if (bankAccount.length() >= 5 && bankAccount.length() <= 9) {
            return """
                    %s%s%s
                    """.formatted(bankAccount.substring(0, bankAccount.length() - 4), DEFAULT_MASK.repeat(2), bankAccount.substring(bankAccount.length() - 4));
        }

        return bankAccount;
    }

    /**
     * 遮蔽 貸款帳號 <br />
     * 帳號<10碼建議銀行帳號倒數第6個字元至倒數第5個字元，以特殊字元(例如：*)或其他隨機字元遮蔽 帳號>10碼建議銀行帳號第7個字元至第10個字元，以特殊字元(例如：*)或其他隨機字元遮蔽
     * 
     * @param bankAccount
     * @return
     */
    public static String maskLoanAccount(String bankAccount) {
        if (StringUtils.isBlank(bankAccount)) {
            return bankAccount;
        }
        bankAccount = StringUtils.trimToEmptyEx(bankAccount);
        StringBuilder sb = new StringBuilder(4096);

        if (bankAccount.length() > 11) {
            for (int i = 0; i < bankAccount.length(); i++) {
                if (i < 8) {
                    sb.append(bankAccount.charAt(i));
                }
                if (i >= 8 && i < 12) {
                    sb.append(DEFAULT_MASK);
                }
                if (i >= 12) {
                    sb.append(bankAccount.charAt(i));
                }
            }
            bankAccount = sb.toString();
        }
        else if (bankAccount.length() >= 5 && bankAccount.length() <= 11) {
            if (bankAccount.length() == 5) {
                sb.append(bankAccount.substring(0, bankAccount.length() - 5));
            }
            else {
                sb.append(bankAccount.substring(0, bankAccount.length() - 6));
            }
            sb.append(DEFAULT_MASK).append(DEFAULT_MASK);
            sb.append(bankAccount.substring(bankAccount.length() - 4, bankAccount.length()));
            bankAccount = sb.toString();
        }

        return bankAccount;
    }

    /**
     * 遮蔽 中華民國身分證字號<br />
     * 如不須使用身分證字號，則建議身分證號碼第5個字元至第8個字元，以特殊字元(例如：*)或其他隨機字元遮蔽 <br />
     * 
     * @param custId
     * @return
     */
    public static String maskCustId(String custId) {
        if (StringUtils.isBlank(custId)) {
            return custId;
        }
        custId = StringUtils.trimToEmptyEx(custId);
        if (custId.length() > 7) {
            custId = """
                    %s%s%s
                    """.formatted(custId.substring(0, 4), DEFAULT_MASK.repeat(4), StringUtils.left(custId.substring(8), 10));
        }
        return custId;
    }

    /**
     * 遮蔽使用者代碼，奇數文字顯示，偶數文字以*遮蔽。例如：A*B*C*D*
     * 
     * @param userId
     * @return
     */
    public static String maskUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return userId;
        }
        userId = StringUtils.trimToEmptyEx(userId);

        List<String> tmpList = new ArrayList<>();
        for (int i = 0; i < StringUtils.length(userId); i++) {
            if (i % 2 == 0) {
                tmpList.add(String.valueOf(userId.charAt(i)));
            }
            else {
                tmpList.add(DEFAULT_MASK);
            }
        }
        return StringUtils.join(tmpList, StringUtils.EMPTY);
    }

    /**
     * <p>
     * 遮蔽 信用卡卡號
     * </p>
     * <p>
     * 第7-12位以“*”隱碼
     * </p>
     *
     * @param cardNo
     * @return
     */
    public static String maskCreditCard(String cardNo) {
        String tmpCardNo = StringUtils.trimToEmptyEx(cardNo);
        if (StringUtils.isBlank(tmpCardNo) || StringUtils.length(tmpCardNo) != 16) {
            return cardNo;
        }

        StringBuilder sb = new StringBuilder(4096);
        sb.append(StringUtils.left(tmpCardNo, 4)).append(" ");
        sb.append(StringUtils.mid(tmpCardNo, 4, 2)).append(DEFAULT_MASK).append(DEFAULT_MASK).append(" ");
        sb.append(DEFAULT_MASK).append(DEFAULT_MASK).append(DEFAULT_MASK).append(DEFAULT_MASK).append(" ");
        sb.append(StringUtils.right(tmpCardNo, 4));
        return sb.toString();

    }

    /**
     * <p>
     * 遮蔽 信用卡卡號
     * </p>
     * <p>
     * 只顯示9個符號 -> ···· 1234
     * </p>
     *
     * @param cardNo
     *            => 16碼
     * @return
     */
    public static String maskCreditCardShowTail(String cardNo) {
        String tmpCardNo = StringUtils.trimToEmptyEx(cardNo);
        if (StringUtils.isBlank(tmpCardNo) || StringUtils.length(tmpCardNo) != 16) {
            return cardNo;
        }
        tmpCardNo = StringUtils.substring(tmpCardNo, 12, 16);
        return "···· " + tmpCardNo;
    }

    /**
     * <p>
     * 遮蔽 帳號, 只顯示後5碼
     * </p>
     *
     * @param acno
     *            帳號
     * @return
     */
    public static String maskAccountShowTail(String acno) {
        String tmpAcno = StringUtils.trimToEmptyEx(acno);
        if (StringUtils.isBlank(tmpAcno)) {
            return acno;
        }
        tmpAcno = StringUtils.substring(tmpAcno, tmpAcno.length() - 5);
        return "···· " + tmpAcno;
    }

    /**
     * 遮蔽 保單號碼或其他識別號碼<br />
     * 如不須使用保單號碼或其他識別號碼，則建議第5個字元至第8個字元，以(例如：*)或其他隨機字元遮蔽 <br />
     * 
     * @param id
     * @return
     */
    public static String maskPolicyNo(String id) {
        if (StringUtils.isBlank(id)) {
            return id;
        }
        id = StringUtils.trimToEmptyEx(id);
        if (id.length() > 7) {
            StringBuilder sb = new StringBuilder(4096);
            sb.append(id.substring(0, 4));
            sb.append(DEFAULT_MASK).append(DEFAULT_MASK);
            sb.append(DEFAULT_MASK).append(DEFAULT_MASK);
            sb.append(id.substring(8));
            id = sb.toString();
        }
        return id;
    }

    /**
     * 遮蔽 保單號碼<br />
     * 如不須使用保單號碼或其他識別號碼，則7碼以上倒數第3個字元至第6個字元，6碼以下倒數第3字元至第4個字元，以(例如：*)或其他隨機字元遮蔽 <br />
     *
     * @param id
     * @return
     */
    public static String maskInsuranceNo(String id) {
        if (StringUtils.isBlank(id)) {
            return id;
        }
        id = StringUtils.trimToEmptyEx(id);
        if (id.length() > 7) {
            String sb = id.substring(0, id.length() - 6) + DEFAULT_MASK + DEFAULT_MASK + DEFAULT_MASK + DEFAULT_MASK + id.substring(id.length() - 2);
            id = sb;
        }
        else {
            String sb = id.substring(0, id.length() - 4) + DEFAULT_MASK + DEFAULT_MASK + id.substring(id.length() - 2);
            id = sb;
        }
        return id;
    }

    /**
     * 1.如不須使用之電話號碼，客戶電話號碼第4碼-第8碼以特殊字元(例如：*)或其他隨機字元取代 或 2.固定位置(只保留前面四字元)後面遮蔽
     *
     * @param mobile
     * @return
     */
    public static String maskMobile(String mobile) {
        if (StringUtils.isBlank(mobile) || mobile.length() < 4) {
            return mobile;
        }

        mobile = StringUtils.trim(mobile);
        StringBuilder sb = new StringBuilder(40960);
        sb.append(mobile.substring(0, 3));
        for (int i = 4; i <= 8; i++) {
            sb.append(DEFAULT_MASK);
        }
        if (mobile.length() > 8) {
            sb.append(mobile.substring(8));
        }

        return sb.toString();
    }

    /**
     * 市內電話隱碼
     *
     * @param phone
     * @return
     */
    public static String maskPhone(String phone) {
        if (StringUtils.isBlank(phone) || phone.length() < 3) {
            return phone;
        }

        phone = StringUtils.trim(phone);
        StringBuilder sb = new StringBuilder(40960);
        sb.append(phone.substring(0, 2));
        for (int i = 0; i < 4; i++) {
            sb.append(DEFAULT_MASK);
        }
        if (phone.length() > 6) {
            sb.append(phone.substring(6));
        }

        return sb.toString();
    }

    /**
     * 如不須使用電子郵遞地址，第1字元到第4字元字元皆以特殊字元(例如：*)或其他隨機字元取代
     * 
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return email;
        }
        // 判斷是否為Email
        if (!StringUtils.contains(email, "@")) {
            return email;
        }
        StringBuilder sb = new StringBuilder(4096);
        String[] emailParts = StringUtils.split(email, ";");
        for (int i = 0; i < emailParts.length; i++) {
            String part = emailParts[i];

            if (StringUtils.contains(part, "@")) {
                String prefix = part.substring(0, part.indexOf("@"));
                String padding = "****";
                sb.append(StringUtils.left(padding, prefix.length()));
                if (prefix.length() > 4) {
                    sb.append(prefix.substring(4));
                }
                sb.append(part.substring(part.indexOf("@"), part.length()));
            }
            else {
                sb.append(part);
            }

            if (i + 1 < emailParts.length) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    /**
     * 遮蔽服務單位
     * 
     * @param unit
     * @return
     */
    public static String maskServiceUnit(String unit) {
        if (unit == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder(4096);
        for (int i = 0; i < unit.length(); i++) {
            if ((i + 1) % 2 == 1) {
                sb.append(unit.charAt(i));
            }
            else {
                sb.append("*");
            }
        }
        return sb.toString();
    }

    /**
     * 遮蔽職稱、職業、教育程度
     * 
     * @param title
     * @return
     */
    public static String maskJobTitle(String title) {
        return maskServiceUnit(title);
    }

    /**
     * 遮蔽 匿稱 只顯示第一碼和最後一碼
     *
     * @param appellation
     * @return
     */
    public static String maskAppellation(String appellation) {
        String tmpValue = StringUtils.trimToEmptyEx(appellation);
        if (StringUtils.isBlank(tmpValue)) {
            return tmpValue;
        }
        StringBuilder sb = new StringBuilder(4096);
        sb.append(appellation.substring(0, 1));
        for (Integer i = 0; i < appellation.length() - 2; i++) {
            sb.append(CIRCLE_MASK);
        }
        sb.append(appellation.substring(appellation.length() - 1, appellation.length()));

        return sb.toString();
    }

    /**
     * <p>
     * 第一字元到第六字元遮蔽顯示
     * </p>
     * <p>
     * Evan King => ******ing
     * </p>
     *
     * @param engName
     * @return
     */
    public static String maskEnglishName(String engName) {
        if (StringUtils.isBlank(engName))
            return engName;
        if (engName.length() <= 6) {
            return "******";
        }
        else {
            return "******" + engName.substring(6);
        }
    }

    /**
     * <p>
     * 以Ｏ隱碼，以空格分開單字，保留每一單字中第一個字母與最後一個字母，其餘隱碼
     * </p>
     * <p>
     * 範例：KaaaaaaaS -> KＯＯＯＯＯＯＯS
     * </p>
     *
     * @param engName
     * @return
     */
    public static String maskEnglishNameBetween(String engName) {
        if (StringUtils.isBlank(engName))
            return engName;

        String firstLetter = StringUtils.left(engName, 1);
        String lastLetter = StringUtils.right(engName, 1);

        int midContentLength = engName.length() - 2;

        StringJoiner joiner = new StringJoiner("");

        joiner.add(firstLetter);
        for (int i = 0; i < midContentLength; i++) {
            joiner.add(CIRCLE_MASK);
        }
        joiner.add(lastLetter);

        return joiner.toString();
    }

    /**
     * <p>
     * 判斷中文或非中文名後進行隱碼
     * </p>
     * <p>
     * 非中文名以英文隱碼規則隱碼
     * </p>
     *
     * @param custName
     * @return
     */
    public static String maskUserName(String custName) {

        if (StringUtils.isBlank(custName)) {
            return custName;
        }

        custName = StringUtils.trimToEmptyEx(custName);

        if (!ValidatorUtility.isEnglishName(custName)) {
            return maskCustName(custName);
        }
        else {
            List<String> nameList = Arrays.asList(StringUtils.getTokens(custName, " "));

            List<String> maskedList = new ArrayList<>();
            for (var part : nameList) {
                maskedList.add(maskEnglishNameBetween(part));
            }
            custName = maskedList.stream().collect(Collectors.joining("　"));
        }
        return custName;
    }

    /**
     * <p>
     * 以○隱碼，三個字以下隱第二碼、四個字以上隱第三碼
     * </p>
     * <p>
     * 如兩個字： 陳O (隱第2碼) 三個字： 藍O郁 (隱第2碼) 四個字到六個字：歐陽O美 (隱第3碼) 七個字以上：顯示前2碼和後2碼
     * </p>
     *
     * @param custName
     * @return
     */
    public static String maskCustName(String custName) {

        if (StringUtils.isBlank(custName)) {
            return "";
        }

        StringBuilder sb = new StringBuilder(4096);

        int len = StringUtils.length(StringUtils.trimToEmptyEx(custName));
        // 七個字以上
        if (len > 6) {
            sb.append(StringUtils.left(custName, 2));
            for (int i = 4; i < len; i++) {
                sb.append(CIRCLE_MASK);
            }
            sb.append(StringUtils.right(custName, 2));
        }
        else if (len > 4) {
            // 五個字到六個字
            sb.append(StringUtils.left(custName, 2)).append(CIRCLE_MASK).append(StringUtils.substring(custName, 3));
        }
        // 四個字
        else if (len == 4) {
            sb.append(StringUtils.left(custName, 1)).append(CIRCLE_MASK).append(CIRCLE_MASK).append(StringUtils.right(custName, 1));
        }
        // 三個字以下
        else {
            sb.append(StringUtils.left(custName, 1));
            if (len > 1) {
                sb.append(CIRCLE_MASK);
            }
            if (len > 2) {
                sb.append(StringUtils.right(custName, 1));
            }
        }
        return sb.toString();
    }

    /**
     * <p>
     * 隱碼公司戶名
     * </p>
     * <p>
     * A. 當公司戶名為7碼(含)以下，只隱藏第2碼，如：中○電信公司。 B. 當公司戶名超過7碼，則只顯示前2及後2碼，其餘隱藏，如：中國○○○○銀行。
     * </p>
     *
     * @param custName
     * @return
     */
    public static String maskCorpName(String corpName) {

        if (StringUtils.isBlank(corpName)) {
            return "";
        }

        StringBuilder sb = new StringBuilder(4096);

        int len = StringUtils.length(StringUtils.trimToEmptyEx(corpName));
        // 七個字以上
        if (len > 6) {
            sb.append(StringUtils.left(corpName, 2));
            for (int i = 4; i < len; i++) {
                sb.append(CIRCLE_MASK);
            }
            sb.append(StringUtils.right(corpName, 2));
        }
        // 以下
        else {
            sb.append(StringUtils.left(corpName, 1));
            if (len > 1) {
                sb.append(CIRCLE_MASK);
            }
            if (len > 2) {
                sb.append(StringUtils.substring(corpName, 2));
            }
        }
        return sb.toString();
    }

    /**
     * 遮蔽 身分證字號
     * 
     * 固定顯示前四後二，其餘以*顯示
     * 
     * <pre>
     * DataMaskUtil.maskIdNo("A123456789") = A123****89
     * </pre>
     * 
     * @param idNo
     * @return
     */
    public static String maskIdNo(String idNo) {
        return maskIdNo(idNo, 4, 2);
    }

    /**
     * 遮蔽 身分證字號, 根據參數調整顯示位數, 為達規則統一, 此功能暫不開放呼叫
     * 
     * @param idNo
     * @param leftLength
     *            前方顯示位數
     * @param rightLength
     *            後方顯示位數
     * @return
     */
    private static String maskIdNo(String idNo, int leftLength, int rightLength) {

        if (StringUtils.isBlank(idNo)) {
            return "";
        }

        int maskLength = idNo.length() - leftLength - rightLength;

        if (maskLength <= 0) {
            return idNo;
        }

        StringBuilder sb = new StringBuilder(4096);

        sb.append(StringUtils.left(idNo, leftLength));// 前四碼

        for (int i = 0; i < maskLength; i++) {
            sb.append(DEFAULT_MASK);
        }
        sb.append(StringUtils.right(idNo, rightLength));// 後三碼

        return sb.toString();

    }

    /**
     * 地址，僅前６碼明碼，其餘以特殊字元遮蔽
     * 
     * @param address
     * @return
     */
    public static String maskAddress(String address) {
        if (StringUtils.isBlank(address) || StringUtils.length(address) <= 6) {
            return address;
        }
        StringBuilder result = new StringBuilder(0);
        result.append(StringUtils.left(address, 6));
        for (int i = 6; i < address.length(); i++) {
            result.append(DEFAULT_MASK);
        }
        return result.toString();
    }

    /**
     * 遮蔽英文地址
     *
     * <p>
     * 依{英文住址}長度取固定比例（50%），在中間以”*”進行隱碼。
     * </p>
     *
     * @param addressENs
     * @return
     */
    public static String maskAddressEN(String addressENs) {
        if (StringUtils.isBlank(addressENs))
            return StringUtils.EMPTY;

        String addressEN1 = addressENs;

        int length = addressEN1.length();
        int markStart = length / 4;
        int markEnd = (length * 3) / 4;

        StringBuilder maskAddr = new StringBuilder(addressEN1);

        for (int i = markStart; i < markEnd; i++) {
            if (addressEN1.charAt(i) != ' ') {
                maskAddr.setCharAt(i, '*');
            }
        }

        return maskAddr.toString();
    }

    /**
     * 隱碼規則為：最後一碼以“*”隱碼，例如：1973/03/1*
     *
     * @param birthday
     * @return
     */
    public static String maskBirthday(String birthday) {
        StringBuilder sb = new StringBuilder(4096);
        sb.append(StringUtils.left(birthday, (birthday.length() - 1))).append(DEFAULT_MASK);
        return sb.toString();
    }

    /**
     * <p>
     * 遮蔽 48+ID 帳號
     * </p>
     * <p>
     * 帳號補滿16位後，倒數4，5，6，位隱碼 ex: 0004801101***165
     * </p>
     *
     * @param bankAccount
     * @return
     */
    public static String mask48IdNo(String bankAccount) {
        if (StringUtils.isBlank(bankAccount)) {
            return bankAccount;
        }

        String tmpBankAccount = StringUtils.trimToEmptyEx(bankAccount);
        tmpBankAccount = StringUtils.leftPad(tmpBankAccount, 16, "0");

        StringBuilder sb = new StringBuilder(4096);

        for (int i = 0; i < tmpBankAccount.length(); i++) {
            if (i < 10) {
                sb.append(tmpBankAccount.charAt(i));
            }
            if (i >= 10 && i < 13) {
                sb.append(DEFAULT_MASK);
            }
            if (i >= 13 && i <= 15) {
                sb.append(tmpBankAccount.charAt(i));
            }
        }
        tmpBankAccount = sb.toString();

        return tmpBankAccount;
    }

    /**
     * <p>
     * 信託帳號
     * </p>
     * <p>
     * "···"+{帳號後5位}
     * </p>
     *
     * @param acct
     * @return
     */
    public static String maskTrustAcct(String acct) {
        if (StringUtils.isBlank(acct) || acct.length() < 5)
            return StringUtils.EMPTY;
        // 只保留後五碼
        String lastFiveDigits = acct.substring(Math.max(0, acct.length() - 5));
        return "···" + lastFiveDigits;
    }

    /**
     * <p>
     * 遮蔽中華電信用戶號碼<br>
     * 隱碼規則：固定隱倒數三、四碼
     * </p>
     *
     * <pre>
     * DataMaskUtil.maskRegId("0912345678") = 091234**78
     * DataMaskUtil.maskRegId("29005678") = 2900**78
     * </pre>
     *
     * @param regId
     * @return
     */
    public static String maskRegId(String regId) {

        String tmpRegId = StringUtils.trimToEmptyEx(regId);
        if (StringUtils.isBlank(tmpRegId) || StringUtils.length(tmpRegId) < 3) {
            return tmpRegId;
        }

        int length = StringUtils.length(tmpRegId);

        StringBuilder sb = new StringBuilder(4096);
        // 如果小於４碼
        if (length == 3) {
            sb.append(DEFAULT_MASK);
        }
        else if (length == 4) {
            sb.append(DEFAULT_MASK).append(DEFAULT_MASK);
        }
        else {
            sb.append(StringUtils.substring(tmpRegId, 0, length - 4));
            sb.append(DEFAULT_MASK).append(DEFAULT_MASK);
        }

        sb.append(StringUtils.substring(tmpRegId, length - 2, length)); // 倒數1,2碼
        return sb.toString();
    }

    /**
     * 分機隱碼 <br>
     * ext 1->* <br>
     * ext 12->*2 <br>
     * ext 123->1*3 <br>
     * ext 1234->1**4 <br>
     * 
     * @param ext
     * @return
     */
    public static String maskExt(String ext) {

        String tmpExt = StringUtils.trimToEmptyEx(ext);
        if (StringUtils.isBlank(tmpExt)) {
            return tmpExt;
        }
        int length = StringUtils.length(tmpExt);
        if (length == 1) {
            return DEFAULT_MASK;
        }
        else if (length == 2) {
            return DEFAULT_MASK + ext.substring(1);
        }
        else {
            return ext.substring(0, 1) + StringUtils.repeat(DEFAULT_MASK, length - 2) + ext.substring(length - 1);
        }
    }

    /**
     * 信用卡格式化
     */
    public static String formatCreditCard(String cardNo) {
        String tmpCardNo = StringUtils.trimToEmptyEx(cardNo);
        if (StringUtils.isBlank(tmpCardNo) || StringUtils.length(tmpCardNo) != 16) {
            return cardNo;
        }
        // 卡號為034或037打頭則為AE卡
        if (StringUtils.equals(tmpCardNo.substring(0, 3), "034") || StringUtils.equals(tmpCardNo.substring(0, 3), "037")) {
            String aeCardNo = tmpCardNo.substring(1);
            return formatData(aeCardNo, "####-######-#####");
        }
        return formatData(tmpCardNo, "####-####-####-####");
    }

    /**
     * 格式化資料
     */
    public static String formatData(String number, String mask) {

        int index = 0;
        StringBuilder masked = new StringBuilder(4096);
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                masked.append(number.charAt(index));
                index++;
            }
            else if (c == '*') {
                masked.append(c);
                index++;
            }
            else {
                masked.append(c);
            }
        }
        return masked.toString();
    }

    /**
     * email 第 1 字元到第 4 字元以特殊字元(*)遮蔽
     *
     *
     * @param email
     * @return
     */
    public static String maskEmailFirst4Chars(String email) {

        if (StringUtils.isBlank(email)) {
            return "";
        }

        return "****" + StringUtils.substring(email, Math.min(4, email.length() - 4));

    }

    /**
     * 隱碼帳號餘額 有幾位數字都轉成*號
     *
     * @param balance
     *            原始餘額顯示格式
     * 
     * @return
     */
    public static String maskAccountBalance(BigDecimal balance) {

        if (null == balance) {
            return "";
        }

        return StringUtils.leftPad("", balance.toPlainString().length(), "*");
    }

    /**
     * 契約編號隱碼, 第5~9碼隱碼處理 契約編號必定為12碼
     *
     * @param contractNo
     * @return
     */
    public static String maskContractNo(String contractNo) {
        if (StringUtils.isBlank(contractNo) || contractNo.length() < 12)
            return "";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < contractNo.length(); i++)
            builder.append(i >= 4 && i <= 8 ? "*" : contractNo.charAt(i));

        return builder.toString();
    }

    /**
     * 地址隱碼, 只秀最末端tailLength長度的內容, 其餘隱碼, DEFAULT_MASK = "*"
     *
     * @param strToMask
     * @param tailLength
     * @return
     */
    public static String maskAddressENShowTail(String strToMask, int tailLength) {
        return maskAddressENShowTail(strToMask, tailLength, DEFAULT_MASK);
    }

    /**
     * 地址隱碼, 只秀最末端tailLength長度的內容, 其餘隱碼
     *
     * @param strToMask
     * @param tailLength
     * @param maskChar
     * @return
     */
    public static String maskAddressENShowTail(String strToMask, int tailLength, String maskChar) {
        if (StringUtils.isBlank(strToMask)) {
            return strToMask;
        }

        return String.format("%s%s", StringUtils.leftPad(StringUtils.EMPTY, strToMask.length() - tailLength, maskChar), StringUtils.substring(strToMask, strToMask.length() - tailLength));
    }

}
