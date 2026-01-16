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
package com.tfb.aibank.biz.component.etrans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;

// @formatter:off
/**
 * @(#)SocketClient.java
 * 
 * <p>Description:e化繳費網 晶片卡前置系統 Socket 工具</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/16, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SocketClient {

    private static IBLog _logger = IBLog.getLog(SocketClient.class);

    public Socket mrSocket = null;
    public OutputStream mrDataOut = null;
    public InputStream mrDataIn = null;
    public String msServerIP = null;
    public int miServerPort = 0;
    public int miTimeout = 0;
    public String msThreadName = null;

    /**
     * 建構子
     */
    public SocketClient(SystemParamCacheManager systemParamCacheManager) {
        this.msServerIP = systemParamCacheManager.getValue("EXT", "EXT_MSG_SERVER_IP");
        this.miServerPort = ConvertUtils.str2Int(systemParamCacheManager.getValue("EXT", "EXT_MSG_SERVER_PORT", "9999"));
        this.miTimeout = ConvertUtils.str2Int(systemParamCacheManager.getValue("EXT", "EXT_MSG_SERVER_TIMEOUT", "5000"));
        if (StringUtils.isBlank(this.msServerIP)) {
            _logger.error("讀取 SYSTEM_PARAM.PARAM_KEY=\"EXT_MSG_SERVER_IP\" 失敗，未設定。");
        }
        else {
            _logger.debug("SocketClient msServerIP : {}, miServerPort : {}", this.msServerIP, this.miServerPort);
        }
    }

    /**
     * 建立連線
     * 
     * @throws UnknownHostException
     * @throws IOException
     */
    public void createSocket() throws UnknownHostException, IOException {
        mrSocket = new Socket(msServerIP, miServerPort);
        mrDataOut = mrSocket.getOutputStream();
        mrDataIn = mrSocket.getInputStream();
    }

    /**
     * 關閉連線
     */
    public void closeSocket() {
        try {
            if (mrDataOut != null) {
                mrDataOut.close();
            }
            if (mrDataIn != null) {
                mrDataIn.close();
            }
            if (mrSocket != null) {
                mrSocket.close();
            }
        }
        catch (IOException e) {
            _logger.warn("關閉連線失敗，不中止程序。");
            _logger.error(e.getMessage(), e);
        }
    }

    /**
     * 傳送資料
     * 
     * @param baUpCode
     * @return
     * @throws IOException
     * @throws ActionException
     */
    public byte[] doSocket(byte[] baUpCode) throws IOException, ActionException {
        byte[] baDownCode;

        int baUpLength = baUpCode.length;
        byte baUpCodeAll[] = new byte[baUpLength + 2];

        baUpCodeAll[0] = (byte) (baUpLength / 256);
        baUpCodeAll[1] = (byte) (baUpLength % 256);

        for (int i = 0; i < baUpLength; i++) {
            baUpCodeAll[i + 2] = baUpCode[i];
        }

        msThreadName = "TCP-" + Thread.currentThread().getName();
        // Send data
        mrDataOut.write(baUpCodeAll);
        mrDataOut.flush();
        // Receive data
        mrSocket.setSoTimeout(miTimeout);

        mrDataIn.available();

        byte[] b = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = 0;
        // 前面兩個byte 是長度檢查碼
        byte[] checkLengthBytes = new byte[2];
        int bytesRead = mrDataIn.read(checkLengthBytes);
        if (bytesRead > 0) {
            _logger.debug("前面兩個 byte 長度檢查");
        }
        // 資料內容
        while ((length = mrDataIn.read(b)) > 0) {
            baos.write(b, 0, length);
        }
        baDownCode = baos.toByteArray();

        // 長度檢查碼 bytes to integer
        int checkLength = Integer.parseInt(ConvertUtils.byteArray2HexString(checkLengthBytes), 16);
        if (baDownCode != null && baDownCode.length != checkLength) {
            throw new ActionException("長度檢查碼發現錯誤，長度檢查碼[" + checkLength + "],資料長度[" + baDownCode.length + "]", CommonErrorCode.UNKNOWN_EXCEPTION);

        }
        _logger.debug(" baDownCode:" + ConvertUtils.byteArray2HexString(baDownCode));

        if (baDownCode == null || baDownCode.length == 0) {
            // 下傳資料錯誤
            throw new ActionException("晶片卡前置系統 主機資料下傳有誤", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
        return baDownCode;

    }

    /**
     * 傳送資料
     * 
     * @param baUpCode
     * @param baDownCodeLen
     * @return
     * @throws IOException
     * @throws ActionException
     */
    @SuppressWarnings("unused")
    public byte[] doSocket(byte[] baUpCode, int baDownCodeLen) throws IOException, ActionException {

        byte[] baDownCode = new byte[baDownCodeLen];
        int iRcvLen = 0;

        int baUpLength = baUpCode.length;
        byte baUpCodeAll[] = new byte[baUpLength + 2];

        baUpCodeAll[0] = (byte) (baUpLength / 256);
        baUpCodeAll[1] = (byte) (baUpLength % 256);

        for (int i = 0; i < baUpLength; i++) {
            baUpCodeAll[i + 2] = baUpCode[i];
        }

        msThreadName = "TCP-" + Thread.currentThread().getName();
        // Send data
        mrDataOut.write(baUpCodeAll);
        mrDataOut.flush();
        // Receive data
        mrSocket.setSoTimeout(miTimeout);

        mrDataIn.available();

        if ((iRcvLen = mrDataIn.read(baDownCode)) == -1) {
            // 資料下傳錯誤
            throw new ActionException("晶片卡前置系統主機資料下傳有誤，請簽出後重新交易！", CommonErrorCode.UNKNOWN_EXCEPTION);

        }

        return baDownCode;
    }
}
