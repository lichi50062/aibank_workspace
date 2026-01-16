package com.tfb.aibank.biz.component.etrans;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;

// @formatter:off
/**
 * @(#)SSLClient.java
 * 
 * <p>Description:e化繳費網 晶片卡前置系統 HTTP 工具</p>
 * <p>後續改寫，請使用{@link com.ibm.tw.commons.httpclient.HttpClient5}</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/16, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SSLClient {

    private static IBLog _logger = IBLog.getLog(SSLClient.class);

    private final static String ENCODING = "MS950"; // 電文編碼
    private String connectUrl = "";
    private int connectTimeout = 6000;
    private String msgId = null;

    /**
     * 連線HttpsURLConnection
     */
    private HttpsURLConnection conn;

    /**
     * 建構子，初始化相關設定
     */
    public SSLClient(SystemParamCacheManager systemParamCacheManager) {
        // 富邦產險電文網址
        // https://10.0.45.24/FubonPayment/Fubon8010
        // http://60.251.184.60/FubonPayment/Fubon8010
        // https://b2c.518fb.com/FubonPayment/Fubon8010
        this.connectUrl = systemParamCacheManager.getValue("EXT", "EXT_FUBON_PAYMENT_URL");
        this.connectTimeout = Integer.parseInt(systemParamCacheManager.getValue("EXT", "EXT_FUBON_PAYMENT_TIMEOUT"));
        if (StringUtils.isBlank(this.connectUrl)) {
            _logger.error("讀取 SYSTEM_PARAM.PARAM_KEY=\"EXT_FUBON_PAYMENT_URL\" 失敗，未設定。");
        }
        else {
            _logger.debug("SSLClient connectUrl : {}", this.connectUrl);
        }
    }

    /**
     * 建立連線
     * 
     * @return
     * @throws Exception
     */
    public boolean connect() throws IOException {

        URL url = null;
        try {
            _logger.debug("連線位置 m_URL===" + connectUrl);
            url = new URL(connectUrl);
        }
        catch (MalformedURLException e1) {
            _logger.error(e1.getMessage(), e1);
            throw e1;
        }

        conn = (HttpsURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setDefaultUseCaches(false);
        conn.setAllowUserInteraction(true);
        conn.setConnectTimeout(connectTimeout);

        return true;
    }

    /**
     * 傳送資料
     * 
     * @param messageId
     * @param sndData
     * @throws Exception
     */
    public void send(String messageId, byte[] sndData) throws ActionException {

        msgId = messageId;

        if (sndData == null) {
            _logger.error(getClass().getName() + ".send()：傳送資料為空字串，請確認上傳電文內容");
            throw new ActionException("TCP09", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
        OutputStream m_out = null;
        try {
            m_out = conn.getOutputStream();
            m_out.write(sndData);
            m_out.flush();
        }
        catch (IOException ioe) {
            _logger.error(getClass().getName() + ".send()：傳送失敗", ioe);
            throw new ActionException("TCP04", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
        finally {
            IOUtils.closeQuietly(m_out);
        }
    }

    /**
     * 接收資料
     * 
     * @param messageId
     * @return
     * @throws ActionException
     */
    public byte[] receive(String messageId) throws ActionException {

        if (!messageId.equalsIgnoreCase(msgId)) {
            _logger.error(getClass().getName() + ".receive()：呼叫 TCPIP 的 MessageId 錯誤：呼叫的 MessageId[" + messageId + "], 訊息存放的 MessageId[" + msgId + "]");
            throw new ActionException("TCP08", CommonErrorCode.UNKNOWN_EXCEPTION);
        }

        try {
            String resCode = "" + conn.getResponseCode();
            _logger.debug("resCode=" + resCode);
            if (!resCode.equals("200")) {
                _logger.error("connect Fail!");
            }
            else {
                _logger.debug("connect Succ!");
            }
        }
        catch (IOException e) {
            _logger.error(e.getMessage(), e);
        }

        StringBuffer sb = new StringBuffer();

        InputStream m_in = null;
        InputStreamReader reader = null;
        try {
            m_in = conn.getInputStream();
            reader = new InputStreamReader(m_in, ENCODING);
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        }
        catch (IOException ioe) {
            _logger.error(getClass().getName() + ".receive()：資料收取發生 IO 例外", ioe);
            throw new ActionException("TCP05", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
        catch (IndexOutOfBoundsException ioobe) {
            _logger.error(getClass().getName() + ".receive()：資料收取發生 IndexOutOfBoundsException", ioobe);
            throw new ActionException("TCP10", CommonErrorCode.UNKNOWN_EXCEPTION);
        }
        finally {
            IOUtils.closeQuietly(m_in);
            IOUtils.closeQuietly(reader);
        }

        return sb.toString().getBytes();
    }
}
