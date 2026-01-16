package com.tfb.aibank.common.util;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.common.type.TxStatusType;

// @formatter:off
/**
 * @(#)ETransUtils.java
 * 
 * <p>Description:e 化繳費網 共用工具程式</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ETransUtils {

    private static final IBLog logger = IBLog.getLog(ETransUtils.class);

    // 固定常數
    public static final String TX_CODE_BAOAN = "2"; // 保安宮
    public static final String TX_CODE_TPE_LAND = "3"; // 臺北市地政

    // 處理類型
    public static final String PROCESS_TYPE_ACCOUNT = "1"; // 金融帳戶
    public static final String PROCESS_TYPE_IC_1 = "2"; // IC卡1代
    public static final String PROCESS_TYPE_IC_2 = "3"; // IC卡2代

    // 電文 共用變數
    public static final String MSG_HEAD_TRACE_NO = "000000"; // HEAD_TRACE_NO
    public static final String MSG_MSG_TYPE = "0200"; // 訊息類別代碼
    public static final String MSG_BIT_MAP_CONFIG = "7220000000C20007"; // BIT_MAP_CONFIG
    public static final String MSG_ACCT_NO_LEN = "16"; // 帳號長度
    public static final String MSG_HANDLE_CODE = "000000"; // 處理代碼
    public static final String MSG_CHANNEL_ID = "000000000000000"; // 通路代號
    public static final String MSG_BATCH_DATA_LEN = "008"; // (批次結帳資料) X(3) 008
    public static final String MSG_BATCH_NO = "000000"; // (批次號碼) X(6) 000000
    public static final String MSG_VER_NO = "00"; // (版本編號) X(2) 00
    public static final String MSG_ORDER_NO = "                "; // (訂單號碼) X(16) 16碼空白
    public static final String MSG_STORE_NO = "                              "; // 特約商店代碼) X(30) 30碼空白
    public static final String MSG_IN_BANK_CODE = "01200000"; // 轉入單位代碼 X(8) 01200000
    public static final String MSG_DATA_LEN_226X = "100"; // 連線交易資料長度 226X
    public static final String MSG_DATA_LEN_256X = "187"; // 連線交易資料長度 256X
    public static final String MSG_TERM_TYPE_226X = "6533"; // TERM_TYPE(端末設備型態) X(4) 6533
    public static final String MSG_TERM_TYPE_256X = "6534"; // TERM_TYPE(端末設備型態) X(4) 6534

    public static final String MSG_PAY_TYPE_956X = "59999";
    public static final String MSG_COLL_NO_956X = "188888880001"; // COLL_NO (代收編號) X(12) 01-08：18888888(委託單位代號) 09-12： 0001(費用代號)

    public static String getPROCESS_TYPE_ACCOUNT() {
        return PROCESS_TYPE_ACCOUNT;
    }

    public static String getPROCESS_TYPE_IC_1() {
        return PROCESS_TYPE_IC_1;
    }

    public static String getPROCESS_TYPE_IC_2() {
        return PROCESS_TYPE_IC_2;
    }

    /**
     * 亂數字元 for 轉換 身分證字號
     */
    public static final int NUMBER_ARR[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    /**
     * 亂數字元 for 端末代號
     */
    public static final int TERM_NO_NUMBER_ARR[] = { 1, 2, 3 };

    /**
     * 取得交易方式
     * 
     * @return
     */
    public static String getProcType(String cardReader) {

        // 晶片金融卡繳款
        if (StringUtils.equals("0", cardReader)) {
            return PROCESS_TYPE_IC_1; // 第1代讀卡機
        }
        else if (StringUtils.equals("1", cardReader)) {
            return PROCESS_TYPE_IC_2; // 第2代讀卡機
        }

        return "";
    }

    /**
     * 取得 轉換後的 身分證字號
     * 
     * @param id
     * @return
     */
    public static String conertIdNumber(String id) {

        if (StringUtils.isBlank(id)) {
            return id;
        }

        // PAYER_ID (身分證字號) 金融帳戶輸入頁輸入的「身分證字號」
        // 須轉換第一碼英文字母為數字
        // (A:10，B:11，C:12，D:13，E:14，F:15，G:16，H:17，I:34，J:18，K:19，L:20，M:21，
        // N:22，O:35，P:23，Q:24，R:25，S:26，T:27，U:28，V:29，W:32，X:30，Y:31，Z:33)
        String firstAlpha = id.substring(0, 1).toUpperCase();

        String first = "";
        if (StringUtils.equals(firstAlpha, "A")) {
            first = "10";
        }
        else if (StringUtils.equals(firstAlpha, "B")) {
            first = "11";
        }
        else if (StringUtils.equals(firstAlpha, "C")) {
            first = "12";
        }
        else if (StringUtils.equals(firstAlpha, "D")) {
            first = "13";
        }
        else if (StringUtils.equals(firstAlpha, "E")) {
            first = "14";
        }
        else if (StringUtils.equals(firstAlpha, "F")) {
            first = "15";
        }
        else if (StringUtils.equals(firstAlpha, "G")) {
            first = "16";
        }
        else if (StringUtils.equals(firstAlpha, "H")) {
            first = "17";
        }
        else if (StringUtils.equals(firstAlpha, "I")) {
            first = "34";
        }
        else if (StringUtils.equals(firstAlpha, "J")) {
            first = "18";
        }
        else if (StringUtils.equals(firstAlpha, "K")) {
            first = "19";
        }
        else if (StringUtils.equals(firstAlpha, "L")) {
            first = "20";
        }
        else if (StringUtils.equals(firstAlpha, "M")) {
            first = "21";
        }
        else if (StringUtils.equals(firstAlpha, "N")) {
            first = "22";
        }
        else if (StringUtils.equals(firstAlpha, "O")) {
            first = "35";
        }
        else if (StringUtils.equals(firstAlpha, "P")) {
            first = "23";
        }
        else if (StringUtils.equals(firstAlpha, "Q")) {
            first = "24";
        }
        else if (StringUtils.equals(firstAlpha, "R")) {
            first = "25";
        }
        else if (StringUtils.equals(firstAlpha, "S")) {
            first = "26";
        }
        else if (StringUtils.equals(firstAlpha, "T")) {
            first = "27";
        }
        else if (StringUtils.equals(firstAlpha, "U")) {
            first = "28";
        }
        else if (StringUtils.equals(firstAlpha, "V")) {
            first = "29";
        }
        else if (StringUtils.equals(firstAlpha, "W")) {
            first = "32";
        }
        else if (StringUtils.equals(firstAlpha, "X")) {
            first = "30";
        }
        else if (StringUtils.equals(firstAlpha, "Y")) {
            first = "31";
        }
        else if (StringUtils.equals(firstAlpha, "Z")) {
            first = "33";
        }

        String end = id.substring(1, id.length());
        return first + end;
    }

    /**
     * 取得 轉換前的 身分證字號
     * 
     * @param id
     * @return CBS問題單#74644
     */
    public static String conertNumberToId(String id) {

        if (StringUtils.isBlank(id)) {
            return id;
        }

        if (id.toUpperCase().matches("^([0-9]{11})$")) {
            // PAYER_ID (身分證字號) 金融帳戶輸入頁輸入的「身分證字號」
            // 須轉換第一碼英文字母為數字
            // (A:01:A，B:02，C:03，D:04，E:05，F:06，G:07，H:08，I:09，J:10，K:11，L:12，M:13，
            // N:14，O:15，P:16，Q:17，R:18，S:19，T:20，U:21，V:22，W:23，X:24，Y:25，Z:26)
            String firstTwoNum = id.substring(0, 2);

            String firstAlpha = "";
            if (StringUtils.equals(firstTwoNum, "01")) {
                firstAlpha = "A";
            }
            else if (StringUtils.equals(firstTwoNum, "02")) {
                firstAlpha = "B";
            }
            else if (StringUtils.equals(firstTwoNum, "03")) {
                firstAlpha = "C";
            }
            else if (StringUtils.equals(firstTwoNum, "04")) {
                firstAlpha = "D";
            }
            else if (StringUtils.equals(firstTwoNum, "05")) {
                firstAlpha = "E";
            }
            else if (StringUtils.equals(firstTwoNum, "06")) {
                firstAlpha = "F";
            }
            else if (StringUtils.equals(firstTwoNum, "07")) {
                firstAlpha = "G";
            }
            else if (StringUtils.equals(firstTwoNum, "08")) {
                firstAlpha = "H";
            }
            else if (StringUtils.equals(firstTwoNum, "09")) {
                firstAlpha = "I";
            }
            else if (StringUtils.equals(firstTwoNum, "10")) {
                firstAlpha = "J";
            }
            else if (StringUtils.equals(firstTwoNum, "11")) {
                firstAlpha = "K";
            }
            else if (StringUtils.equals(firstTwoNum, "12")) {
                firstAlpha = "L";
            }
            else if (StringUtils.equals(firstTwoNum, "13")) {
                firstAlpha = "M";
            }
            else if (StringUtils.equals(firstTwoNum, "14")) {
                firstAlpha = "N";
            }
            else if (StringUtils.equals(firstTwoNum, "15")) {
                firstAlpha = "O";
            }
            else if (StringUtils.equals(firstTwoNum, "16")) {
                firstAlpha = "P";
            }
            else if (StringUtils.equals(firstTwoNum, "17")) {
                firstAlpha = "Q";
            }
            else if (StringUtils.equals(firstTwoNum, "18")) {
                firstAlpha = "R";
            }
            else if (StringUtils.equals(firstTwoNum, "19")) {
                firstAlpha = "S";
            }
            else if (StringUtils.equals(firstTwoNum, "20")) {
                firstAlpha = "T";
            }
            else if (StringUtils.equals(firstTwoNum, "21")) {
                firstAlpha = "U";
            }
            else if (StringUtils.equals(firstTwoNum, "22")) {
                firstAlpha = "V";
            }
            else if (StringUtils.equals(firstTwoNum, "23")) {
                firstAlpha = "W";
            }
            else if (StringUtils.equals(firstTwoNum, "24")) {
                firstAlpha = "X";
            }
            else if (StringUtils.equals(firstTwoNum, "25")) {
                firstAlpha = "Y";
            }
            else if (StringUtils.equals(firstTwoNum, "26")) {
                firstAlpha = "Z";
            }

            String end = id.substring(2, id.length());
            return firstAlpha + end;
        }
        else {
            return id;
        }
    }

    /**
     * 取得 TermNo 端末代號
     * 
     * @param prefix
     * @return200305
     */
    public static String getTermNo(String prefix) {
        // 20030 5 + iNextTerm_NO + ” “ //TODO
        // iNextTerm_N：1-3(為random產生)
        // 顯示格式：2003051 、2003052 、2003053
        SecureRandom rnd = new SecureRandom();
        return prefix + TERM_NO_NUMBER_ARR[rnd.nextInt(TERM_NO_NUMBER_ARR.length)] + " ";
    }

    /**
     * 取得 處理代碼
     * 
     * @param transType
     *            0 : 金融帳戶, 1 晶片卡
     * @param bankNo
     *            銀行代號
     * @return
     * @throws Exception
     */
    public static String getPcode(String transType, String bankNo) throws ActionException {
        // (處理代碼)
        // (1) 晶片卡(他行)：2562
        // (2) 晶片卡(自行)：2563
        // (3) 金融帳戶(他行)：2262
        // (4) 金融帳戶(自行)：2263
        if (StringUtils.equals("0", transType)) {
            if (StringUtils.equals("012", bankNo)) {
                return "2263";
            }
            else {
                return "2262";
            }
        }
        else if (StringUtils.equals("1", transType)) {
            if (StringUtils.equals("012", bankNo)) {
                return "2563";
            }
            else {
                return "2562";
            }
        }
        else {
            logger.error("transType 必須為 0:金融帳戶 或 1:晶片卡");
            throw ExceptionUtils.getActionException("transType 必須為 0:金融帳戶 或 1:晶片卡", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    /**
     * 取得 繳款類別
     * 
     * @param txType
     *            0:富邦信用卡, 1:富邦產險
     * @param transType
     *            0 : 金融帳戶, 1 晶片卡
     * @return
     */
    public static String getPayType(String txType, String transType) {

        if (StringUtils.equals("0", txType)) {
            // 富邦信用卡
            if (StringUtils.equals("0", transType)) {
                return "00035";
            }
            else if (StringUtils.equals("1", transType)) {
                return "00115";
            }

        }
        else if (StringUtils.equals("1", txType)) {
            // 富邦產險
            if (StringUtils.equals("0", transType)) {
                return "00048";
            }
            else if (StringUtils.equals("1", transType)) {
                return "00089";
            }

        }

        return "";
    }

    /**
     * 取得 繳款類別
     * 
     * @param payType
     *            畫面 繳款方式
     * @return
     * @throws ActionException
     */
    public static String getTxItem(String payType) throws ActionException {
        /*
         * 依照付款方式不同儲存 0 (晶片金融卡) 1 (金融帳戶)
         */
        if (StringUtils.equals("0", payType)) {
            return "1";
        }
        if (StringUtils.equals("1", payType)) {
            return "0";
        }
        else {
            throw new ActionException("畫面 繳款方式 必須為 0 or 1", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    /**
     * 轉換 TxStatus
     * 
     * @param respCode
     * @param fescRespCode
     * @return
     */
    public static String converTxStatus(String respCode, String fescRespCode) {
        // 0：交易成功 256X/226X.RESP_CODE=00 and FESC_RESP_CODE =4001
        // 1：交易失敗 256X/226X.RESP_CODE<>00 or (256X/226X.RESP_CODE=00 and 256X/226X.FESC_RESP_CODE<>4001)
        if (StringUtils.equals("00", respCode) && StringUtils.equals("4001", fescRespCode)) {
            return TxStatusType.SUCCESS.getCode();
        }
        else if (StringUtils.notEquals("00", respCode) || (StringUtils.equals("00", respCode) && StringUtils.notEquals("4001", fescRespCode))) {
            return TxStatusType.FAIL.getCode();
        }
        return TxStatusType.PROCESS.getCode();
    }

    /**
     * 轉換 stan
     * 
     * @param stan
     * @return
     */
    public static String converStanNo(String stan) {
        /*
         * 256X/226X.STAN 若電文回覆符合下述狀況則存null (1) null (2) 7個0 (3) 8個0
         */
        if (null == stan || StringUtils.equals("0000000", stan) || StringUtils.equals("00000000", stan)) {
            return null;
        }
        else {
            return stan;
        }
    }

    /**
     * 檢核是否在營業時間內
     * 
     * @param accountDate
     * @return
     * @throws ActionException
     */
    public static boolean isInAccountDay(Date accountDate) throws ActionException {

        /*
         * (1) 檢核是否在營業時間內， Select ACCOUNT_DAY from ACCOUNT_DAY 【ACCOUNT_DAY = 系統日】且【系統時間 > 09：00】
         */
        Calendar sysTime = Calendar.getInstance();
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);

        long startDate = Long.parseLong(String.format("%1$tY%1$tm%1$td%1$tH%1$tM00", startTime));
        long sysDate = Long.parseLong(String.format("%1$tY%1$tm%1$td%1$tH%1$tM00", sysTime));
        boolean inAccountDay = true;
        if (DateUtils.getDaysBetween(accountDate, startTime.getTime()) != 1 || sysDate < startDate) {
            inAccountDay = false;
        }
        return inAccountDay;
    }

    /**
     * 轉換 轉出帳號格式 (靠右左補0 lengt 16)
     * 
     * @param outAccount
     * @return
     * @throws ActionException
     */
    public static String formatOutAccount(String outAccount) throws ActionException {
        if (StringUtils.isBlank(outAccount)) {
            throw new ActionException("轉出帳號為空值", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
        if (outAccount.length() > 16) {
            throw new ActionException("轉出帳號最長為16碼", CommonErrorCode.UNKNOWN_EXCEPTION);
        }

        return StringUtils.leftPad(outAccount, 16, "0");
    }

}
