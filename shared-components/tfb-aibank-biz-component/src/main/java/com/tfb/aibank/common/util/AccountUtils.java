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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.chl.model.account.AgreedInAccount;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.type.AccountClass;

// @formatter:off
/**
 * @(#)AccountUtils.java
 * 
 * <p>Description:處理「帳號」相關共用行為</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AccountUtils {

    private static IBLog logger = IBLog.getLog(AccountUtils.class.getName());

    private static final String S390_CHK_168 = "168";
    private static final String S390_CHK_OBU = "560";
    private static final String CBS_CHK_168 = "168";
    private static final String CBS_CHK1_SV = "234";
    private static final String CBS_CHK1_LN = "56789";
    private static final String CBS_CHK2_DBU = "01234567";
    private static final String CBS_CHK2_OBU = "89";

    // 390 VS CBS 帳號類型對應
    // {390類型判斷, CBS類型判斷, CBS類型判斷方式}
    private static final String S45 = "S45";
    private static final String S56 = "S56";
    private static final String S46 = "S46";
    private static final String S15 = "S15";
    private static final String MP_STARTWITH = "StartWith";
    private static final String MP_FULLPRODCODE = "FullProdCode";
    private static final String MP_BYPATTERN = "ByPattern";

    private static final int prodType_index = 0;
    private static final int acct390_key_index = 1;
    private static final int acct390_rule_index = 2;
    private static final int acctCBS_key_index = 3;
    private static final int acctCBS_rule_index = 4;

    private static List<String[]> SV_ACCT_TYPE_10 = new ArrayList<String[]>();
    static {
        // 活存
        SV_ACCT_TYPE_10.add(new String[] { "102", "02", S56, "8211", MP_STARTWITH });// 活期存款(台幣活期?)
        SV_ACCT_TYPE_10.add(new String[] { "116", "16", S56, "8219", MP_STARTWITH });// 活期證券存款(台幣活期?)
        SV_ACCT_TYPE_10.add(new String[] { "104", "04", S56, "8216", MP_STARTWITH });// 行員儲蓄存款(台幣活儲?)
        SV_ACCT_TYPE_10.add(new String[] { "203", "03", S56, "8223", MP_STARTWITH });// 公教儲蓄存款(台幣活儲?)
        SV_ACCT_TYPE_10.add(new String[] { "210", "10", S56, "8221", MP_STARTWITH });// 活期儲蓄存款(台幣活儲?)
        SV_ACCT_TYPE_10.add(new String[] { "120", "20", S56, "8212", MP_STARTWITH });// 活期綜合存款(台幣綜合活期?)
        SV_ACCT_TYPE_10.add(new String[] { "221", "21", S56, "8222", MP_STARTWITH });// 活儲綜合存款(台幣綜合活儲?)
        // 支存
        SV_ACCT_TYPE_10.add(new String[] { "101", "01", S56, "^820109[1-7]{1}[0-9]{7}$", MP_BYPATTERN });// 支票存款
        SV_ACCT_TYPE_10.add(new String[] { "131", "131", S46, "^8201098[1-3]{1}[0-9]{6}$", MP_BYPATTERN });// 公庫存款(公庫保管金?)
        SV_ACCT_TYPE_10.add(new String[] { "331", "331", S46, "^8201098[4-6]{1}[0-9]{6}$", MP_BYPATTERN });// 特種基金(公庫特種基金?)
        SV_ACCT_TYPE_10.add(new String[] { "531", "531", S46, "^8201098[7-9]{1}[0-9]{6}$", MP_BYPATTERN });// 國庫存款(國庫活期?)
        SV_ACCT_TYPE_10.add(new String[] { "531", "205", S46, "^8201098[7-9]{1}[0-9]{6}$", MP_BYPATTERN });// ????(央行國庫局?)
        SV_ACCT_TYPE_10.add(new String[] { "105", "105", S46, "^8201099[0-9]{7}$", MP_BYPATTERN });// 同業存款
    }

    private static List<String[]> SV_ACCT_TYPE_20 = new ArrayList<String[]>();
    static {
        // 定存
        SV_ACCT_TYPE_20.add(new String[] { "244", "44", S56, "36190301", MP_FULLPRODCODE });// 零存整付/定儲
        SV_ACCT_TYPE_20.add(new String[] { "244", "44", S56, "36110601", MP_FULLPRODCODE });// 零存整付/定儲 新零整
        SV_ACCT_TYPE_20.add(new String[] { "145", "45", S56, "33190201", MP_FULLPRODCODE });// 如意存款/定期
        SV_ACCT_TYPE_20.add(new String[] { "645", "45", S56, "33190201", MP_FULLPRODCODE });// 如意存款/定期
        SV_ACCT_TYPE_20.add(new String[] { "247", "47", S56, "36190201", MP_FULLPRODCODE });// 如意存款/定儲
        SV_ACCT_TYPE_20.add(new String[] { "647", "47", S56, "36190201", MP_FULLPRODCODE });// 如意存款/定儲
        SV_ACCT_TYPE_20.add(new String[] { "177", "177", S46, "8241", MP_STARTWITH });// 台幣定存主帳號
        SV_ACCT_TYPE_20.add(new String[] { "128", "128", S46, "8249", MP_STARTWITH });// NCD
        SV_ACCT_TYPE_20.add(new String[] { "377", "377", S46, "8245", MP_STARTWITH });// 公庫定存主帳號
    }

    private static List<String[]> SV_ACCT_TYPE_40 = new ArrayList<String[]>();
    static {
        // 外活
        SV_ACCT_TYPE_40.add(new String[] { "17", "17", S45, "8311", MP_STARTWITH });// (DBU)外幣活期
        SV_ACCT_TYPE_40.add(new String[] { "17", "56017", S15, "8381", MP_STARTWITH });// (OBU)外幣活期
        SV_ACCT_TYPE_40.add(new String[] { "18", "18", S45, "8312", MP_STARTWITH });// (DBU)外幣綜合
        SV_ACCT_TYPE_40.add(new String[] { "18", "56018", S15, "8382", MP_STARTWITH });// (OBU)外幣綜合
        SV_ACCT_TYPE_40.add(new String[] { "19", "19", S45, "8319", MP_STARTWITH });// (DBU)外幣活期同存
        SV_ACCT_TYPE_40.add(new String[] { "19", "56019", S15, "8389", MP_STARTWITH });// (OBU)外幣活期同存
    }

    private static List<String[]> SV_ACCT_TYPE_50 = new ArrayList<String[]>();
    static {
        // 外定
        SV_ACCT_TYPE_50.add(new String[] { "27", "27", S45, "8341", MP_STARTWITH });// (DBU)外幣定存主帳號
        SV_ACCT_TYPE_50.add(new String[] { "27", "56027", S15, "8391", MP_STARTWITH });// (OBU)外幣定存主帳號
        SV_ACCT_TYPE_50.add(new String[] { "37", "37", S45, "^83[5-7]{1}[0-9]{11}$", MP_BYPATTERN });//
        SV_ACCT_TYPE_50.add(new String[] { "37", "37", S45, "^839[6-9]{1}[0-9]{10}$", MP_BYPATTERN });//
    }

    private static List<String[]> SV_ACCT_TYPE_32 = new ArrayList<String[]>();
    static {
        // 一本萬利臺幣定存
        SV_ACCT_TYPE_32.add(new String[] { "126", "126", S46, "825", MP_STARTWITH });// 台幣綜定-定存
        SV_ACCT_TYPE_32.add(new String[] { "626", "626", S46, "825", MP_STARTWITH });// 台幣綜定-定存
        SV_ACCT_TYPE_32.add(new String[] { "236", "236", S46, "826", MP_STARTWITH });// 台幣綜定-整整
        SV_ACCT_TYPE_32.add(new String[] { "636", "636", S46, "826", MP_STARTWITH });// 台幣綜定-整整
        SV_ACCT_TYPE_32.add(new String[] { "256", "256", S46, "827", MP_STARTWITH });// 台幣綜定-存本
        SV_ACCT_TYPE_32.add(new String[] { "656", "656", S46, "827", MP_STARTWITH });// 台幣綜定-存本
    }

    private static List<String[]> SV_ACCT_TYPE_168 = new ArrayList<String[]>();
    static {
        // 一本萬利(臺幣, 外幣)活期
        SV_ACCT_TYPE_168.add(new String[] { "168", "168", S46, "8168", MP_STARTWITH });// 一本萬利
    }

    private static List<String[]> SV_ACCT_TYPE_70 = new ArrayList<String[]>();
    static {
        // 組合式商品
        SV_ACCT_TYPE_70.add(new String[] { "677", "677", S46, "8345", MP_STARTWITH });// 組合式商品
        SV_ACCT_TYPE_70.add(new String[] { "677", "560677", S15, "8395", MP_STARTWITH });// 組合式商品(OBU)
    }

    private static List<String[]> SV_ACCT_TYPE = new ArrayList<String[]>();
    static {
        SV_ACCT_TYPE.addAll(SV_ACCT_TYPE_10);
        SV_ACCT_TYPE.addAll(SV_ACCT_TYPE_20);
        SV_ACCT_TYPE.addAll(SV_ACCT_TYPE_40);
        SV_ACCT_TYPE.addAll(SV_ACCT_TYPE_50);
        SV_ACCT_TYPE.addAll(SV_ACCT_TYPE_32);
        SV_ACCT_TYPE.addAll(SV_ACCT_TYPE_168);
        SV_ACCT_TYPE.addAll(SV_ACCT_TYPE_70);
    }

    /**
     * 判斷帳號分類
     * 
     * 390存款帳號：1.長度12位
     * 
     * 390存款帳號168：1.長度12位 且 2.第4-6位為168
     * 
     * 390存款帳號OBU：1.長度12位 且 2.第1-3位為560
     * 
     * 390放款帳號：1.長度為14位 且 2.第1碼不為8
     * 
     * CBS存款帳號：1.長度為14位 且 2.第1碼為8 且 3.第3碼為2-4
     * 
     * CBS存款帳號168：1.長度為14位 且 2.第1碼為8 且 3.第3-5碼為168
     * 
     * CBS存款帳號OBU：1.長度為14位 且 2.第1碼為8 且 3.第3碼為2-4 且 4.第4碼為8-9
     * 
     * CBS放款帳號：1.長度為14位 且 2.第1碼為8 且 3.第3碼為5-9
     * 
     * CBS放款帳號OBU：1.長度為14位 且 2.第1碼為8 且 3.第3碼為5-9 且 4.第4碼為8-9
     * 
     * @param acct
     *            帳號
     * @return
     */
    public static AccountClass verify(String acct) {
        if (acct != null) {
            String s = StringUtils.trimLeftZero(acct);
            int len = s.length();

            if (len == 12) { // 390_SV
                if (s.startsWith(S390_CHK_168, 3)) { // 390_SV_168
                    return AccountClass.CLASS_390_SV_168;
                }
                else if (s.startsWith(S390_CHK_OBU)) { // 390_SV_OBU
                    return AccountClass.CLASS_390_SV_OBU;
                }
                return AccountClass.CLASS_390_SV;
            }
            else if (len == 14) {
                if (!s.startsWith("8")) { // 390_LN
                    return AccountClass.CLASS_390_LN;
                }
                else { // CBS
                    if (s.startsWith(CBS_CHK_168, 1)) { // CBS_SV_168
                        return AccountClass.CLASS_CBS_SV_168;
                    }

                    String chk1 = s.substring(1, 2);
                    String chk2 = s.substring(2, 3);
                    if (CBS_CHK1_SV.indexOf(chk1) >= 0) { // CBS_SV
                        if (CBS_CHK2_DBU.indexOf(chk2) >= 0) { // CBS_SV_DBU
                            return AccountClass.CLASS_CBS_SV;
                        }
                        else if (CBS_CHK2_OBU.indexOf(chk2) >= 0) { // CBS_SV_OBU
                            return AccountClass.CLASS_CBS_SV_OBU;
                        }
                    }
                    else if (CBS_CHK1_LN.indexOf(chk1) >= 0) { // CBS_LN
                        if (CBS_CHK2_DBU.indexOf(chk2) >= 0) { // CBS_LN_DBU
                            return AccountClass.CLASS_CBS_LN;
                        }
                        else if (CBS_CHK2_OBU.indexOf(chk2) >= 0) { // CBS_LN_OBU
                            return AccountClass.CLASS_CBS_LN_OBU;
                        }
                    }
                }
            }
        }
        return AccountClass.CLASS_UNKNOWN;
    }

    /**
     * 取得帳號科目別(產品別)
     * 
     * @param acct
     * @return
     */
    public static String getAcctType(String acct) {
        return getAcctType(acct, verify(acct));
    }

    /**
     * 取得帳號科目別(產品別)
     * 
     * @param acct
     * @param acctClass
     * @return
     */
    public static String getAcctType(String acct, AccountClass acctClass) {
        int len = StringUtils.length(StringUtils.trimToEmptyEx(acct));
        if (StringUtils.isNotBlank(acct) && len >= 12 && len <= 16) {
            // 帳號補滿16位
            acct = StringUtils.leftPad(acct, 16, "0");

            // 判斷為390存款帳號，科目別需取自帳號12碼第4-6碼
            if (acctClass.is390SV()) {
                return StringUtils.mid(acct, 7, 3);
            }
            // 判斷為390放款帳號，科目別需取自帳號14碼第5-8碼
            else if (acctClass.is390LN()) {
                return StringUtils.mid(acct, 6, 4);
            }
            // 判斷為CBS放款帳號，科目別需取自帳號14碼第2-4碼
            else if (acctClass.isCBSLN()) {
                return StringUtils.mid(acct, 3, 3);
            }
            // 判斷為CBS存款帳號，先嘗試轉換為390科目
            else if (acctClass.isCBSSV()) {
                String acctType = get390SvType(acct, "", "");
                if (StringUtils.isBlank(acctType)) {
                    return StringUtils.mid(acct, 3, 3);
                }
                else {
                    return acctType;
                }
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * 獲取帳號類型(含專案別做判斷)
     * 
     * @param id
     * @param acct
     * @param cur
     * @param slipNo
     * @param prodCode
     * @param prodSubCode
     * @param lnType
     * @param prodStatus
     * @param stuSpNo
     *            專案別
     * @return
     */
    public static String[] getType(String id, String acct, String cur, String slipNo, String prodCode, String prodSubCode, String lnType, String prodStatus, String stuSpNo) {
        String type = null;
        String loanType = null;
        String acctStatus = "0"; // acctStatus 0 : 正常 / 1 : 異常
        AccountClass acctClass = verify(acct);

        boolean isTWD = StringUtils.equals(cur, "TWD"); // 是否為台幣帳號
        // boolean isTD = StringUtils.isNotEmpty(slipNo); // 是否有存單號碼

        if (isMatchType(acct, prodCode, prodSubCode, SV_ACCT_TYPE_10)) {
            // 臺幣活存
            type = "10";
        }
        else if (isMatchType(acct, prodCode, prodSubCode, SV_ACCT_TYPE_20)) {
            // 臺幣定存
            type = "20";
        }
        else if (isMatchType(acct, prodCode, prodSubCode, SV_ACCT_TYPE_10)) {
            // 支存
            type = "10";
        }
        else if (isMatchType(acct, prodCode, prodSubCode, SV_ACCT_TYPE_40)) {
            // 外幣活存
            type = "40";
        }
        else if (isMatchType(acct, prodCode, prodSubCode, SV_ACCT_TYPE_50)) {
            // 外幣定存
            type = "50";
        }
        else if (acctClass.is168() && isTWD) {
            // 一本萬利臺幣活存
            type = "31";
        }
        else if (isMatchType(acct, prodCode, prodSubCode, SV_ACCT_TYPE_32)) {
            // 一本萬利臺幣定存
            type = "32";
        }
        else if (acctClass.is168() && !isTWD) {
            // 一本萬利外幣活存
            type = "33";
        }
        else if (isMatchType(acct, prodCode, prodSubCode, SV_ACCT_TYPE_70)) {
            // 組合式商品
            type = "70";
        }
        else {
            type = StringUtils.EMPTY;
        }

        return new String[] { StringUtils.trimToEmptyEx(type), StringUtils.trimToEmptyEx(loanType), StringUtils.trimToEmptyEx(acctStatus) };
    }

    /**
     * 獲取帳號類型(含專案別做判斷) For 貸款
     * 
     * @param id
     * @param acct
     * @param cur
     * @param slipNo
     * @param prodCode
     * @param prodSubCode
     * @param lnType
     * @param prodStatus
     * @param stuSpNo
     *            專案別
     * @return
     */
    public static String[] getLoanType(String id, String acct, String cur, String slipNo, String prodCode, String prodSubCode, String lnType, String prodStatus, String stuSpNo) {
        String type = null;
        String loanType = null;
        String acctStatus = "0"; // acctStatus 0 : 正常 / 1 : 異常

        // CBS放款帳號
        int idLen = StringUtils.length(id);

        if (StringUtils.indexOfAny(prodStatus, new String[] { "01", "51", "10", "60" }) >= 0) {
            // 逾期
            acctStatus = "1";
        }

        if (StringUtils.indexOfAny(prodStatus, new String[] { "03", "04", "53", "54" }) >= 0) {
            // 呆帳
            type = "66";
            loanType = "01";
        }
        else if (StringUtils.indexOfAny(prodStatus, new String[] { "21", "02", "71", "52" }) >= 0) {
            // 逾催
            type = "66";
            loanType = "02";
        }
        else if (StringUtils.equals(stuSpNo, "GN29")) {// 勞工紓困貸款 專案別GN29
            // v1.1 調整勞工紓困的判斷位置，阻擋紓困貸逾催呆戶
            type = "63";
            loanType = "03";
        }
        else if (StringUtils.indexOfAny(prodCode, new String[] { "6800", "5300" }) >= 0) {
            // 存單質借
            type = "70";
            loanType = "01";
        }
        else if (StringUtils.startsWith(prodCode, "54")) {
            type = "71";
            loanType = "01";
        }
        else if (StringUtils.equals(prodCode, "5301")) {
            // 綜存質借
            type = "67";
            loanType = "01";
        }
        else if (StringUtils.indexOfAny(prodCode, new String[] { "5001" }) >= 0) {
            // 房貸循環型-額度式透支->5001
            type = "69";
            loanType = "01";
        }
        else if (StringUtils.indexOfAny(prodCode, new String[] { "5002" }) >= 0) {
            // 房貸循環型-支存透支->5002
            type = "69";
            loanType = "03";
        }
        else if (StringUtils.indexOfAny(prodCode, new String[] { "5004" }) >= 0) {
            // 房貸循環型-回復式透支->5004
            type = "69";
            loanType = "02";
        }
        else if (StringUtils.startsWith(prodCode, "50")) {
            // 一般房貸分期
            type = "61";
            loanType = "01";
        }
        else if (prodCode.matches("^690[2-5]{1}")) {
            // 房貸 - 公教
            type = "61";
            loanType = "02";
        }
        else if (prodCode.matches("^690[0-1]{1}")) {
            // 房貸 - 國宅
            type = "61";
            loanType = "03";
        }
        else if (prodCode.matches("^510[1-2]{1}")) {
            // 循環型信貸
            type = "73";
            loanType = "01";
        }
        else if (StringUtils.startsWith(prodCode, "51")) {
            // 信貸分期
            type = "62";
            loanType = "01";
        }
        else if (StringUtils.equals(prodCode, "5200")) {
            // 學貸 - 就貸
            type = "63";
            loanType = "01";
        }
        else if (StringUtils.equals(prodCode, "5201")) {
            // 學貸 - 留貸
            type = "63";
            loanType = "02";
        }
        else if (idLen == 10 && StringUtils.indexOfAny(lnType, new String[] { "C61", "C79", "CF5", "GP1", "TD1", "T01" }) < 0) {
            // 個人週轉金-一般周轉
            type = "64";
            loanType = "01";
        }
        else if (idLen == 10 && (StringUtils.equals(lnType, "GP1") || StringUtils.equals(lnType, "GP2"))) {
            // 個人週轉金-政策性貸款
            type = "64";
            loanType = "03";
        }
        else if (idLen == 10 && StringUtils.indexOfAny(lnType, new String[] { "C61", "C79", "CF5" }) >= 0) {
            // 個人週轉金-購屋貸款
            type = "64";
            loanType = "04";
        }
        else if (idLen == 8 && StringUtils.indexOfAny(lnType, new String[] { "BP1", "TD1", "T01", "IF2", "IF1", "CB1", "EF1", "EF2", "GP1" }) < 0) {
            // 企業貸款-一般企業貸款
            type = "65";
            loanType = "01";
        }
        else if (idLen == 8 && StringUtils.equals(lnType, "GP1")) {
            // 企業貸款-政策性貸款
            type = "65";
            loanType = "03";
        }
        else if (idLen == 8 && StringUtils.indexOfAny(lnType, new String[] { "BP1", "IF2", "IF1", "CB1", "EF1", "EF2" }) >= 0) {
            // 企業貸款-貿易融資
            type = "65";
            loanType = "04";
        }
        else if (prodCode.matches("^600[0-1]{1}")) {
            // 保證業務
            type = "65";
            loanType = "05";
        }

        return new String[] { StringUtils.trimToEmptyEx(type), StringUtils.trimToEmptyEx(loanType), StringUtils.trimToEmptyEx(acctStatus) };
    }

    /**
     * 獲取帳號類型(無專案別)
     * 
     * @param idno
     *            身份證號
     * @param acno
     *            帳號
     * @param curCod
     *            幣別
     * @param slipNo
     *            存單號碼/定存序號
     * @param prodCode
     *            產品大類
     * @param prodSubCode
     *            產品子類
     * @param lnType
     *            性質別
     * @param waLnBadDebtInd
     *            逾催呆註記
     * @return
     */
    public static String[] getType(String idno, String acno, String curCod, String slipNo, String prodCode, String prodSubCode, String lnType, String waLnBadDebtInd) {
        return getType(idno, acno, curCod, slipNo, prodCode, prodSubCode, lnType, waLnBadDebtInd, null);
    }

    /**
     * 是否符合390存款帳號類型
     * 
     * @param acno
     *            帳號
     * @param prodCode
     *            產品大類
     * @param prodSubCode
     *            產品子類
     * @param svAcctType
     * @return
     */
    public static boolean isMatchType(String acno, String prodCode, String prodSubCode, List<String[]> svAcctType) {
        boolean isMatched = false;

        AccountClass acctClass = verify(acno);
        String s = acno == null ? "" : StringUtils.trimLeftZero(acno);

        for (String[] acctType : svAcctType) {
            isMatched = checkMatchType(s, prodCode, prodSubCode, acctClass, acctType);

            if (isMatched) {
                break;
            }
        }

        return isMatched;
    }

    public static String get390SvType(String acct, String prodCode, String prodSubCode) {

        AccountClass acctClass = verify(acct);
        String s = acct == null ? "" : StringUtils.trimLeftZero(acct);
        for (String[] acctType : SV_ACCT_TYPE) {
            if (checkMatchType(s, prodCode, prodSubCode, acctClass, acctType)) {
                return acctType[prodType_index];
            }
        }

        return StringUtils.EMPTY;
    }

    public static boolean isMatchAcctType(String acct, String[] matchTypes) {

        String type = get390SvType(acct, "", "");

        for (String matchType : matchTypes) {
            if (matchType.equals(type)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMatchAcctType(String acct, String prodCode, String prodSubCode, String[] matchTypes) {

        String type = get390SvType(acct, prodCode, prodSubCode);

        for (String matchType : matchTypes) {
            if (matchType.equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 檢核是否符合指定帳號類型
     * 
     * @param acct
     * @param prodCode
     * @param prodSubCode
     * @param acctClass
     * @param acctType
     * @return
     */
    private static boolean checkMatchType(String acct, String prodCode, String prodSubCode, AccountClass acctClass, String[] acctType) {
        boolean flag = false;
        if (acctClass.is390SV()) {
            if (S45.equals(acctType[acct390_rule_index])) {
                flag = StringUtils.equals(acctType[acct390_key_index], StringUtils.substring(acct, 3, 5));
            }
            else if (S56.equals(acctType[acct390_rule_index])) {
                flag = StringUtils.equals(acctType[acct390_key_index], StringUtils.substring(acct, 4, 6));
            }
            else if (S46.equals(acctType[acct390_rule_index])) {
                flag = StringUtils.equals(acctType[acct390_key_index], StringUtils.substring(acct, 3, 6));
            }
            else if (S15.equals(acctType[acct390_rule_index])) {
                flag = StringUtils.equals(acctType[acct390_key_index], StringUtils.substring(acct, 0, 5));
            }
        }
        else if (acctClass.isCBSSV()) {
            if (MP_STARTWITH.equals(acctType[acctCBS_rule_index])) {
                flag = StringUtils.startsWith(acct, acctType[acctCBS_key_index]);
            }
            else if (MP_FULLPRODCODE.equals(acctType[acctCBS_rule_index])) {
                flag = StringUtils.equals(prodCode + prodSubCode, acctType[acctCBS_key_index]);
            }
            else if (MP_BYPATTERN.equals(acctType[acctCBS_rule_index])) {
                flag = acct.matches(acctType[acctCBS_key_index]);
            }
        }
        return flag;
    }

    /**
     * 是否為新零存整付帳號(新零存判定規則:產品大類3621、3622或產品大類+產品子類36110601)
     * 
     * @param prodCode
     *            產品大類+產品子類
     * @return
     */
    @Deprecated
    public static boolean isDepositLumpSumAccount(String prodCode) {
        return StringUtils.startsWith(prodCode, "3621") || StringUtils.equals(prodCode, "36110601");
    }

    /**
     * 參考【共通業務辦法】-業務辦法-帳號-歸戶帳號-臺幣帳務總覽-台幣定存，ACCOUNT_TYPE = 20，ACCOUNT_SUB_TYPE = 23
     * 
     * @param acctType
     *            帳號類型
     * @param acctSubType
     *            帳號子類型
     * @return
     */
    public static boolean isDepositLumpSumAccount(String acctType, String acctSubType) {
        return StringUtils.equals(acctType, "20") && StringUtils.equals(acctSubType, "23");
    }

    /**
     * 是否為支票存款 (科目別101調整為產品大類以13開頭且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isCheckDeposit(String aType, String iCat) {
        return StringUtils.startsWith(aType, "13") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為臺活（包括原科目別，102：活期存款；116：活期證券存款；104：行員儲蓄存款；120：活期綜合存款；203：公教儲蓄存款；210：活期儲蓄存款；221：活儲綜合存款）
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isAllTwdDemandDeposit(String aType, String iCat) {
        return ((StringUtils.startsWith(aType, "146") || StringUtils.startsWith(aType, "147") || StringUtils.startsWith(aType, "126") || StringUtils.startsWith(aType, "127")) && StringUtils.endsWith(iCat, "01")) || isBankEmployeeDeposit(aType, iCat) || isComprehensiveDemandDeposit(aType, iCat) || ((StringUtils.startsWith(aType, "166") || StringUtils.startsWith(aType, "167")) && StringUtils.endsWith(iCat, "01")) || isComprehensiveCurrentDeposit(aType, iCat);
    }

    /**
     * 是否為活期存款 (因為科目別102/116無法區分，此方法是將102/116合併。科目別102&116調整為產品大類前三位是146、147、126、127且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isDemandDeposit(String aType, String iCat) {
        return (StringUtils.startsWith(aType, "146") || StringUtils.startsWith(aType, "147") || StringUtils.startsWith(aType, "126") || StringUtils.startsWith(aType, "127")) && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為台幣一本萬利 (科目別168且幣別是台幣調整為產品大類前三位是162、163且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @return
     */
    public static boolean is168TWD(String aType, String iCat) {
        return StringUtils.startsWithAny(aType, "162", "163") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為一本萬利 (科目別168調整為產品大類前三位是162、163、152、153)
     * 
     * @param aType
     *            產品大類
     * @return
     */
    public static boolean is168Full(String aType) {
        return StringUtils.startsWithAny(aType, "162", "163", "152", "153");
    }

    /**
     * 是否為外幣一本萬利 (科目別168調整為產品大類前三位是152、153且產品子類不以01結尾 或前三位是162、163且子類以00結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean is168Foreign(String aType, String iCat) {
        return (StringUtils.startsWithAny(aType, "152", "153") && !StringUtils.endsWith(iCat, "01")) || (StringUtils.startsWithAny(aType, "162", "163") && StringUtils.endsWith(iCat, "00"));
    }

    /**
     * 是否為公教存款 (科目別203 調整為產品大類以166、167開頭且產品標幟是H、K，且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @param prodInd
     *            產品標幟
     * @return
     */
    public static boolean isGovEduDeposit(String aType, String iCat, String prodInd) {
        // 科目別203 調整為產品大類以166、167開頭且產品標幟是H、K，且產品子類以01結尾
        return ((StringUtils.startsWith(aType, "166") || StringUtils.startsWith(aType, "167")) && (StringUtils.equals(prodInd, "H") || StringUtils.equals(prodInd, "K"))) && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為行員存款 (科目別104調整為產品大類以17開頭，且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isBankEmployeeDeposit(String aType, String iCat) {
        // 科目別104調整為產品大類以17開頭，且產品子類以01結尾
        return StringUtils.startsWith(aType, "17") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為活期儲蓄存款 (科目別210 調整為產品大類前三位是166、167且產品標幟PROD_IND不是H、K，且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @param prodInd
     *            產品標幟
     * @return
     */
    public static boolean isDemandSavingDeposit(String aType, String iCat, String prodInd) {
        // 科目別210 調整為產品大類以166、167開頭且產品標幟PROD_IND不是H、K，且產品子類以01結尾
        return ((StringUtils.startsWith(aType, "166") || StringUtils.startsWith(aType, "167")) && !(StringUtils.equals(prodInd, "H") || StringUtils.equals(prodInd, "K"))) && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為活期證券 (科目別116 調整為產品大類前三位是146、147、126、127且產品標幟是T且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @param prodInd
     *            產品標幟
     * @return
     */
    public static boolean isDemandSecurities(String aType, String iCat, String prodInd) {
        // 科目別116 調整為產品大類前三位是146、147、126、127且產品標幟是T且產品子類以01結尾
        return (StringUtils.startsWith(aType, "146") || StringUtils.startsWith(aType, "147") || StringUtils.startsWith(aType, "126") || StringUtils.startsWith(aType, "127")) && StringUtils.equals(prodInd, "T") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否如意存款-定期(科目別145、645調整為以產品大類、產品子類判斷)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isRuyiRegularDeposit(String aType, String iCat) {
        // 如意存款(科目別145、645調整為以產品大類3319、產品子類0201判斷)
        return StringUtils.equals(aType, "3319") && StringUtils.equals(iCat, "0201");
    }

    /**
     * 是否如意存款-定儲247(科目別247調整為以產品大類、產品子類判斷)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isRuyiDeposit247(String aType, String iCat) {
        // 如意存款(科目別247調整為以產品大類3619、產品子類0201,0401判斷)
        return StringUtils.equals(aType, "3619") && (StringUtils.equals(iCat, "0201") || StringUtils.equals(iCat, "0401"));
    }

    /**
     * 是否如意存款-定儲647(科目別以647調整為以產品大類、產品子類判斷)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isRuyiDeposit647(String aType, String iCat) {
        // 如意存款(科目別647調整為以產品大類3629、產品子類0101判斷)
        return StringUtils.equals(aType, "3629") && StringUtils.equals(iCat, "0101");
    }

    /**
     * 是否如意存款(原科目別145、645、247、647整合為一個方法，表示所有如意存款帳號)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isRuyiDeposit(String aType, String iCat) {
        return isRuyiRegularDeposit(aType, iCat) || isRuyiDeposit247(aType, iCat) || isRuyiDeposit647(aType, iCat);
    }

    /**
     * 是否為綜活_活期存款 (科目別120調整為產品大類以144,145,124,125開頭，且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isComprehensiveDemandDeposit(String aType, String iCat) {
        // 科目別120調整為產品大類以144,145,124,125開頭，且產品子類以01結尾
        return (StringUtils.startsWith(aType, "144") || StringUtils.startsWith(aType, "145") || StringUtils.startsWith(aType, "124") || StringUtils.startsWith(aType, "125")) && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為綜活_活期儲蓄 (科目別221調整為產品大類以164、165開頭,且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isComprehensiveCurrentDeposit(String aType, String iCat) {
        // 科目別221調整為產品大類以164、165開頭,且產品子類以01結尾
        return (StringUtils.startsWith(aType, "164") || StringUtils.startsWith(aType, "165")) && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為外活 (科目別17調整為產品大類以156、157、126、127開頭且產品子類不以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isForeignCurrencyDeposit(String aType, String iCat) {
        // 科目別17調整為產品大類以156、157、126、127開頭且產品子類不以01結尾(01:台幣)
        return (StringUtils.startsWith(aType, "156") || StringUtils.startsWith(aType, "157") || StringUtils.startsWith(aType, "126") || StringUtils.startsWith(aType, "127")) && !StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為外綜活 (科目別18調整為產品大類以154、155開頭且產品子類不以01結尾)
     * 
     * @param aType
     *            產品大類
     * @return
     */
    public static boolean isForeignComprehensiveCurrency(String aType, String iCat) {
        // 科目別18調整為產品大類以154、155開頭且產品子類不以01結尾
        return (StringUtils.startsWith(aType, "154") || StringUtils.startsWith(aType, "155")) && !StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為全部外活之一 (原科目別17、18整合為一個method，表示所有外活帳號)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     * @return
     */
    public static boolean isAllForeignDeposit(String aType, String iCat) {
        return isForeignCurrencyDeposit(aType, iCat) || isForeignComprehensiveCurrency(aType, iCat);
    }

    /**
     * 是否為同業存款 (科目別105調整為產品大類以121開頭且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @return
     */
    public static boolean isInterBankDeposit105(String aType, String iCat) {
        // 科目別105調整為產品大類以121開頭且產品子類以01結尾
        return StringUtils.startsWith(aType, "121") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為央行國庫局 (科目別205調整為產品大類以111開頭且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @return
     */
    public static boolean isCentralBankFiscus(String aType, String iCat) {
        // 科目別205調整為產品大類以111開頭且產品子類以01結尾
        return StringUtils.startsWith(aType, "111") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為同業定存(科目別237調整為產品大類以31開頭且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     */
    public static boolean isInterbankRegularDeposit(String aType, String iCat) {
        // 科目別237調整為產品大類以31開頭且產品子類以01結尾
        return StringUtils.startsWith(aType, "31") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為外定(科目別27調整為產品大類351且產品子類不以01結尾(01:台幣))
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     */
    public static boolean isForeignCurrency(String aType, String iCat) {
        return StringUtils.startsWith(aType, "351") && !StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為組合式商品(科目別677調整為產品大類30開頭)
     * 
     * @param aType
     *            產品大類
     */
    public static boolean isStructuredInvestments(String aType) {
        return StringUtils.startsWith(aType, "30");
    }

    /**
     * 是否為可轉讓定存(科目別128調整為產品大類34且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     */
    public static boolean isNCD(String aType, String iCat) {
        return StringUtils.startsWith(aType, "34") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為綜存存本-定儲(科目別256/656調整為產品大類382開頭且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     */
    public static boolean isComprehensiveDeposit(String aType, String iCat) {
        return StringUtils.startsWith(aType, "382") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為綜存整整-定儲(科目別236/636調整為產品大類372開頭且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     */
    public static boolean isComprehensiveStorage(String aType, String iCat) {
        return StringUtils.startsWith(aType, "372") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為綜合定存-定期(科目別126/626調整為產品大類332開頭且產品子類以01結尾)
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     */
    public static boolean isComprehensiveFixedDeposit(String aType, String iCat) {
        return StringUtils.startsWith(aType, "332") && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為外幣同業存款(科目別19調整為產品大類12開頭且產品子類不以01結尾(01:台幣))
     * 
     * @param aType
     *            產品大類
     * @param iCat
     *            產品子類
     */
    public static boolean isInterbankDeposit19(String aType, String iCat) {
        return StringUtils.startsWith(aType, "12") && !StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否為公庫存款 產品大類18開頭或者產品大類39開頭(公庫定存)
     * 
     * @param aType
     *            產品大類
     * 
     */
    public static boolean isGovDeposit(String aType) {
        return StringUtils.startsWith(aType, "18") || StringUtils.startsWith(aType, "39");
    }

    /**
     * 是否為公庫定存 科目別 377 調整為產品大類【3911、3912、3913、3914、3915】且產品子類以01結尾
     * 
     * @param aType
     *            產品大類
     */
    public static boolean isGovTreasuryDeposit(String aType, String iCat) {
        return StringUtils.contains("3911、3912、3913、3914、3915", aType) && StringUtils.endsWith(iCat, "01");
    }

    /**
     * 是否實體存單 產品大類 第1碼是3,第3碼是1實體存單
     * 
     * @param aType
     *            產品大類
     * @return
     */
    public static boolean isPhysicalDeposit(String aType) {
        return StringUtils.startsWith(aType, "3") && StringUtils.equals(StringUtils.substring(aType, 2, 3), "1");
    }

    /**
     * 是否為聘僱人員離職儲金3611-0101 科目別"246"調整為產品大類3611，產品子類0101
     * 
     * @param aType
     * @param iCat
     * @return
     */
    public static boolean isQuitEmploySavings(String aType, String iCat) {
        return StringUtils.equals(aType, "3611") && StringUtils.equals(iCat, "0101");
    }

    /**
     * 是否OBU帳號
     * 
     * @param branchId
     *            傳入5碼完整分行別
     * @return
     */
    public static boolean isOBU(String branchId) {
        return StringUtils.equals(branchId, "00560") || StringUtils.startsWith(branchId, "05");
    }

    /**
     * 是否數存帳戶
     * 
     * @param digitalFlg
     *            數位存款註記 0:數一高風險, 1:數一低風險, 2:數二, 3:數二, 4:數三信用卡申請, 5:數三他行帳戶申請-低轉帳額度, A:櫃實體帳戶
     * @return
     */
    public static boolean isDigitalType(String digitalFlg) {
        return StringUtils.equals(digitalFlg, "0") || StringUtils.equals(digitalFlg, "1") || StringUtils.equals(digitalFlg, "2") || StringUtils.equals(digitalFlg, "3") || StringUtils.equals(digitalFlg, "4") || StringUtils.equals(digitalFlg, "5");
    }

    /**
     * 是否為信託帳號368(此方法限信託帳號使用)
     * 
     * @param acctId
     *            信託帳號
     * @return
     */
    public static boolean isAcctId368(String acctId) {
        String acctNo = StringUtils.trimLeftZero(acctId);
        int len = acctNo.length();
        if (len == 12) {
            return StringUtils.equals("368", StringUtils.substring(acctNo, 3, 6));
        }
        else if (len == 14) {
            return StringUtils.startsWith(acctNo, "8178") || StringUtils.startsWith(acctNo, "8388");
        }
        return false;
    }

    /**
     * <pre>
     * 若為本行帳號, 帳號取後14位顯示
     *
     * 舊帳號12碼 XXXXXXXXXXXX => 00XXXXXXXXXXXX
     * 新帳號14碼 => XXXXXXXXXXXXXX
     * >14碼帳號 => 0000XXXXXXXXXXXX => 00XXXXXXXXXXXX
     * >14碼帳號 => 0100XXXXXXXXXXXX => 0100XXXXXXXXXXXX
     * </pre>
     *
     * @param bankId
     * @param accountNo
     * @return
     */
    public static String getDisplayAccountId(String bankId, String accountNo) {
        if (StringUtils.isBlank(accountNo)) {
            return "";
        }
        if (StringUtils.equals(AIBankConstants.TFB_BANK_CODE, bankId)) {
            // 移除帳號左右兩邊半型空白、全型空白
            accountNo = StringUtils.trimAllBigSpace(accountNo);
            // 若字數小於14碼，左補0至14碼
            if (accountNo.length() < 14) {
                // 小於14碼補齊14碼
                accountNo = StringUtils.leftPad(accountNo, 14, "0");
            }
            else {
                // 大於14碼且前兩碼為00，切為14碼
                if (StringUtils.startsWith(accountNo, "00")) {
                    accountNo = StringUtils.right(accountNo, 14);
                }
            }
        }
        return accountNo;
    }

    // @formatter:off
    /** 
     * 依據長度左邊去掉0
     * 0000000000123456,12  -> 000000123456
     * 0000000000123456,14  -> 00000000123456
     * 0100000000123456,14  -> 0100000000123456
     * 
     * @param input
     * @param length
     * @return
     */
    // @formatter:on
    public static String leftZeroRemove(String input, int length) {
        if (StringUtils.isBlank(input)) {
            return input;
        }
        return StringUtils.leftPadZero(StringUtils.trimLeftZero(input), length);
    }

    /**
     * 將帳號右靠左補0至16位，再進行比對
     * 
     * @param acctId1
     * @param acctId2
     * @return
     */
    public static boolean isEquals(String acctId1, String acctId2) {
        return StringUtils.equals(StringUtils.leftPad(acctId1, 16, "0"), StringUtils.leftPad(acctId2, 16, "0"));
    }

    /**
     * <h3>檢查並置換帳號CurCod為XXX且沒有相關子帳號的物件</h3>
     * 
     * <pre>
     *     判斷CUR_COD=XXX、ACCOUNT_TYPE=40且底下無任何幣別之帳號時
     *     顯示該主帳號，幣別預設帶美金，餘額視為0
     * </pre>
     */
    public static List<TransOutAccount> processTransOutAcctWithXXX(List<TransOutAccount> accounts) {
        // 先把CURCOD = XXX 和 非XXX的區隔出來
        List<TransOutAccount> acctDatas = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(accounts)) {
            Predicate<TransOutAccount> curcodXXX = acc -> AIBankConstants.MAIN_ACCOUNT_CUR_CODE.equals(acc.getCurCod());

            List<TransOutAccount> curcodXXXAccts = accounts.stream().filter(curcodXXX).collect(Collectors.toList());
            List<TransOutAccount> curcodNotXXXAccts = accounts.stream().filter(curcodXXX.negate()).collect(Collectors.toList());

            acctDatas = curcodNotXXXAccts;
            if (CollectionUtils.isNotEmpty(curcodXXXAccts)) {
                if (CollectionUtils.isNotEmpty(curcodNotXXXAccts)) {
                    for (var xxx : curcodXXXAccts) {
                        // 如果這筆curcod = xxx的資料沒有其他不同幣別的相同帳號
                        final String xxxAcno = xxx.getAcno();
                        if (curcodNotXXXAccts.stream().noneMatch(notx -> Objects.equals(xxxAcno, notx.getAcno()))) {
                            xxx = convertToDefaultFxCurAccount(xxx);
                            acctDatas.add(xxx);
                        }
                    }
                }
                else {
                    // 如果沒有curcod=XXX以外的帳號
                    for (var xxx : curcodXXXAccts) {
                        xxx = convertToDefaultFxCurAccount(xxx);
                        acctDatas.add(xxx);
                    }
                }
            }
        }
        return acctDatas;
    }

    public static List<AgreedInAccount> processTransInAcctWithXXX(List<AgreedInAccount> accounts) {
        // 先把CURCOD = XXX 和 非XXX的區隔出來
        List<AgreedInAccount> acctDatas = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(accounts)) {
            Predicate<AgreedInAccount> curcodXXX = acc -> AIBankConstants.MAIN_ACCOUNT_CUR_CODE.equals(acc.getCurr());

            List<AgreedInAccount> curcodXXXAccts = accounts.stream().filter(curcodXXX).collect(Collectors.toList());
            List<AgreedInAccount> curcodNotXXXAccts = accounts.stream().filter(curcodXXX.negate()).collect(Collectors.toList());

            if (logger.isDebugEnabled()) {
                logger.debug("==[processTransInAcctWithXXX]== curcodXXXAccts: {}", IBUtils.attribute2Str(curcodXXXAccts));
                logger.debug("==[processTransInAcctWithXXX]== curcodNotXXXAccts: {}", IBUtils.attribute2Str(curcodNotXXXAccts));
            }

            acctDatas = curcodNotXXXAccts;
            if (CollectionUtils.isNotEmpty(curcodXXXAccts)) {
                if (CollectionUtils.isNotEmpty(curcodNotXXXAccts)) {
                    for (var xxx : curcodXXXAccts) {
                        // 如果這筆curcod = xxx的資料沒有其他不同幣別的相同帳號
                        final String xxxAcno = xxx.getAcno();
                        if (curcodNotXXXAccts.stream().noneMatch(notx -> Objects.equals(xxxAcno, notx.getAcno()))) {
                            // 如果沒有和CurCod = XXX有相同的帳號，轉換成預設帳號
                            xxx = convertToDefaultFxCurAccount(xxx);
                            acctDatas.add(xxx);
                        }
                        else {
                            // 如果有和XXX 一樣的帳號，把CurCod != XXX的帳號分行，都改成和XXX一樣
                            for (var nonXXX : curcodNotXXXAccts) {
                                if (xxx.getAcno().equals(nonXXX.getAcno())) {
                                    nonXXX.setBranchNo(xxx.getBranchNo());
                                }
                            }
                        }
                    }
                }
                else {
                    // 如果沒有curcod=XXX以外的帳號
                    for (var xxx : curcodXXXAccts) {
                        xxx = convertToDefaultFxCurAccount(xxx);
                        acctDatas.add(xxx);
                    }
                }
            }
        }
        return acctDatas;
    }

    public static TransOutAccount convertToDefaultFxCurAccount(TransOutAccount account) {
        account.setCurCod(CurrencyCode.USD);
        account.setAccountKey(account.getAcno() + "_" + account.getCurCod());
        account.setAvalAmt(BigDecimal.ZERO);
        account.setActBal(BigDecimal.ZERO);
        account.setActBalNT(BigDecimal.ZERO);
        return account;
    }

    public static AgreedInAccount convertToDefaultFxCurAccount(AgreedInAccount account) {
        account.setCurr(CurrencyCode.USD);
        return account;
    }

    // @formatter:off
    /**
     * 過濾左側「0」之後，判斷是否為 168 帳號
     * 長度12位 且 2.第4-6位為168
     * 長度為14位 且 2.第1碼為8 且 3.第2-4碼為168
     * 
     * @param inputAcctId
     * @return
     */
    // @formatter:on
    public static boolean is168Account(String inputAcctId) {
        String acctId = StringUtils.trimLeftZero(inputAcctId);
        int len = StringUtils.length(acctId);
        if (len == 12) {
            return StringUtils.equals(StringUtils.substring(acctId, 3, 6), "168");
        }
        else if (len == 14) {
            return StringUtils.startsWith(acctId, "8168");
        }
        return false;
    }

    /**
     * 把帳號轉換成只顯示末5碼
     * 
     * <pre>
     *  0000001234567890 -> ...67890
     * </pre>
     *
     * @param accountNo
     * @return
     */
    public static String getDisplayAccountIdLastFive(String accountNo) {
        if (StringUtils.isNotBlank(accountNo)) {
            accountNo = "..." + StringUtils.right(accountNo, 5);
        }
        return accountNo;
    }

    /**
     * 把帳號轉換成只顯示末5碼，自訂前導符號
     * 
     * <pre>
     *  0000001234567890 -> {自傳入前導符號}67890
     * </pre>
     *
     * @param accountNo
     * @return
     */
    public static String getDisplayAccountIdLastFive(String accountNo, String initSymbol) {
        String defaultSymbol = "...";
        if (StringUtils.isNotBlank(initSymbol)) {
            defaultSymbol = initSymbol;
        }

        if (StringUtils.isNotBlank(accountNo)) {
            accountNo = defaultSymbol + StringUtils.right(accountNo, 5);
        }
        return accountNo;
    }

    /**
     * 投資類交易帳號，顯示「{帳號名稱} {帳號(14碼)}」
     * 
     * @param account
     * @return
     */
    public static String getInvestAcctDisplay1(TransOutAccount account) {
        return new StringBuilder(0).append(account.getDisplayAcctNickname()).append(" ").append(account.getDisplayAccountId()).toString();
    }

    /**
     * 投資類交易帳號，顯示「"可投資餘額" {幣別名稱} {餘額(存單或可用餘額 - 透支總限額)}」
     * 
     * @param account
     * @return
     */
    public static String getInvestAcctDisplay2(TransOutAccount account) {
        StringBuilder sb = new StringBuilder(0);
        sb.append(I18NUtils.getMessage("common.investable_balance")); // 可投資餘額
        sb.append(StringUtils.SPACE).append(account.getCurName()).append(StringUtils.SPACE);
        sb.append(IBUtils.formatMoneyStr(account.getOdTotLimit(), account.getCurCode()));
        return sb.toString();
    }
}
