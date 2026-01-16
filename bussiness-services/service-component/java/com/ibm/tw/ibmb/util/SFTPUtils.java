/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.ibm.tw.ibmb.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Vector;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

// @formatter:off
/**
 * @(#)SFTPUtil.java
 * 
 * <p>Description:[SFTP 工具類]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SFTPUtils {

    private static IBLog logger = IBLog.getLog(SFTPUtils.class);

    private ChannelSftp sftp;

    private Session session;

    /** 私鑰 */
    private String privateKey;

    public SFTPUtils() {
        // default constructor
    }

    /**
     * 連接SFTP伺服器
     * 
     * @throws Exception
     */
    public void connect(String host, int port, String username, String passwxxd) throws JSchException {
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                jsch.addIdentity(privateKey);// 設置私鑰
            }
            session = jsch.getSession(username, host, port);
            logger.info("Session is build");
            if (passwxxd != null) {
                session.setPassword(passwxxd);
            }

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            // close Kerberos
            config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");
            session.setConfig(config);
            // 設置 Socket Timeout 30 min
            int socketTimeout = 1800000;
            session.setTimeout(socketTimeout);
            session.connect();
            logger.info("Session is connected");

            Channel channel = session.openChannel("sftp");
            channel.connect();
            logger.info("channel is connected");

            sftp = (ChannelSftp) channel;
            // fortify Privacy Violation
            logger.info("sftp server host port is connect successfull");
        }
        catch (JSchException e) {
            logger.error(String.format("Cannot connect to specified sftp server. Exception message is: %s", e.getMessage()), e);
            throw e;
        }
    }

    /**
     * 關閉連接 server
     */
    public void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                logger.info("sftp is closed already");
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
                logger.info("sshSession is closed already");
            }
        }
    }

    /**
     * Set working directory on FTP server
     * 
     * @param dir
     *            new working directory
     * @return true, if working directory changed
     */
    public boolean changeWorkingDirectory(String dir) {
        try {
            sftp.cd(dir);
        }
        catch (SftpException e) {
            logger.warn("directory is not exist");
            try {
                sftp.mkdir(dir);
                sftp.cd(dir);
            }
            catch (SftpException e1) {
                logger.warn("make directory error");
            }
        }
        return false;
    }

    /**
     * 將輸入流的數據上傳到sftp作為檔
     *
     * @param目錄 上傳到該目錄
     * @param sftpFileName
     *            sftp端檔名
     * @param 英寸
     *            輸入流
     * @throws SftpException
     * @throws例外
     */
    public void upload(String directory, String sftpFileName, InputStream input) throws SftpException {
        try {
            sftp.cd(directory);
        }
        catch (SftpException e) {
            logger.warn("directory is not exist");
            sftp.mkdir(directory);
            sftp.cd(directory);
        }
        sftp.put(input, sftpFileName);
        logger.info("file:{} is upload successful", sftpFileName);
    }

    /**
     * 上傳單個檔
     *
     * @param目錄 上傳到SFTP目錄
     * @param uploadFile
     *            要上傳的檔，包括路徑
     * @throws FileNotFoundException
     * @throws SftpException
     * @throws例外
     */
    public void upload(String directory, String uploadFile) throws FileNotFoundException, SftpException {
        File file = new File(uploadFile);
        try (FileInputStream fos = new FileInputStream(file)) {
            upload(directory, file.getName(), fos);
        }
        catch (IOException e) {
            logger.error("Error closing FileInputStream: {}", e.getMessage());
        }
    }

    /**
     * 將byte[]上傳到sftp，作為檔。 注意：從String生成byte[]是，要指定字元集。
     * 
     * @param directory
     *            上傳到sftp目錄
     * @param sftpFileName
     *            檔案在sftp端的命名
     * @param byteArr
     *            要上傳的位元組陣列
     * @throws SftpException
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, byte[] byteArr) throws SftpException {
        upload(directory, sftpFileName, new ByteArrayInputStream(byteArr));
    }

    /**
     * 將字串按照指定的字元編碼上傳到 sftp
     *
     * @param目錄 上傳到SFTP目錄
     * @param sftpFileName
     *            檔案在sftp端的命名
     * @param dataStr
     *            待上傳的數據
     * @param charsetName
     *            sftp上的檔，按該字元編碼保存
     * @throws UnsupportedEncodingException
     * @throws SftpException
     * @throws例外
     */
    public void upload(String directory, String sftpFileName, String dataStr, String charsetName) throws UnsupportedEncodingException, SftpException {
        upload(directory, sftpFileName, new ByteArrayInputStream(dataStr.getBytes(charsetName)));
    }

    /**
     * 下載檔案
     *
     * @param directory
     *            下載目錄
     * @param downloadFile
     *            下載的檔案
     * @param saveFile
     *            存在本地的路徑
     * @throws SftpException
     * @throws FileNotFoundException
     */
    public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException {
        if (StringUtils.isNotBlank(directory)) {
            sftp.cd(directory);
        }
        logger.info("File : {} start downloading.", downloadFile);
        File file = new File(saveFile);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            sftp.get(downloadFile, fos);
        }
        catch (IOException ioEx) {
            logger.error(ioEx.getMessage(), ioEx);
        }
        logger.info("File : {} download completed.", downloadFile);
    }

    /**
     * 删除文件
     * 
     * @param directory
     *            要删除文件所在目錄
     * @param deleteFile
     *            要删除的文件
     * @throws SftpException
     * @throws Exception
     */
    public void delete(String directory, String deleteFile) throws SftpException {
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }

    /**
     * 列出目錄下的文件
     * 
     * @param directory
     *            要列出的目錄
     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector<?> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

}
