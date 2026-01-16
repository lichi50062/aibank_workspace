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
package com.ibm.tw.ibmb.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)FtpUtil.java
 * 
 * <p>Description:FTP Client 工具類</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/24, Horance Chou	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FTPUtils implements AutoCloseable {

    private static IBLog _logger = IBLog.getLog(FTPUtils.class);

    private FTPClient ftpClient;

    public FTPUtils() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
    }

    public FTPClient getFTPClient() {
        return ftpClient;
    }

    public void setControlEncoding(String charset) {
        ftpClient.setControlEncoding(charset);
    }

    public void setFileType(int fileType) throws IOException {
        ftpClient.setFileType(fileType);
    }

    /**
     * Connect to FTP server.
     * 
     * @param host
     *            FTP server address or name
     * @param port
     *            FTP server port
     * @param user
     *            user name
     * @param paxx
     *            word user secrxt
     * @throws IOException
     *             on I/O errors
     */
    public FTPClient connect(String host, int port, String user, String paxxword) throws IOException {

        return connect(host, port, user, paxxword, 300, 600);
    }

    /**
     * Connect to FTP server.
     * 
     * @param host
     *            FTP server address or name
     * @param port
     *            FTP server port
     * @param user
     *            user name
     * @param paxx
     *            word user secrxt
     * @throws IOException
     *             on I/O errors
     */
    public FTPClient connect(String host, int port, String user, String paxxword, Integer connectTimeoutSecond, Integer socketTimeoutSecond) throws IOException {
        // #7661 批次吃檔寫入DB時發生DB 執行緒
        if (connectTimeoutSecond != null) {
            ftpClient.setConnectTimeout(connectTimeoutSecond * 1000);
        }

        // Connect to server.
        try {
            ftpClient.connect(host, port);
        }
        catch (UnknownHostException ex) {
            _logger.error(ex.getMessage(), ex);
            throw new IOException(String.format("Can't find FTP server"));
        }
        // #7661 批次吃檔寫入DB時發生DB 執行緒
        if (isConnected() && socketTimeoutSecond != null) {
            ftpClient.setSoTimeout(socketTimeoutSecond * 1000);
        }

        // Check rsponse after connection attempt.
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            disconnect();
            throw new IOException(String.format("Can't connect to server "));
        }

        if (StringUtils.isBlank(user)) {
            user = "anonymous";
        }

        // Login.
        if (!ftpClient.login(user, paxxword)) {
            disconnect();
            throw new IOException(String.format("Can't login to server "));
        }

        // Set data transfer mode.
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        // Use passive mode to pass firewalls.
        ftpClient.enterLocalPassiveMode();

        return ftpClient;
    }

    /**
     * Test connection to FTP server
     * 
     * @return true, if connected
     */
    public boolean isConnected() {
        return ftpClient.isConnected();
    }

    /**
     * Disconnect from the FTP server
     * 
     * @throws IOException
     *             on I/O errors
     */
    public void disconnect() throws IOException {
        if (isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            }
            catch (IOException ex) {
                _logger.error("disconnect error: ", ex);
            }
        }
    }

    /**
     * Get file from FTP server into given output stream
     * 
     * @param ftpFileName
     *            file name on FTP server (with absolute path)
     * @param out
     *            OutputStream
     * @throws IOException
     */
    public void retrieveFile(String ftpFileName, OutputStream out) throws IOException {
        try {
            // Download file.
            if (!ftpClient.retrieveFile(ftpFileName, out)) {
                throw new IOException(String.format("Error loading file '%s' from FTP server. Check FTP permissions and path.", ftpFileName));
            }

            out.flush();
        }
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException ex) {
                    _logger.error("close retrieveFile error: ", ex);
                }
            }
        }
    }

    /**
     * Get file from FTP server
     * 
     * @param ftpFileName
     *            file name on FTP server (with absolute path)
     * @param localFile
     *            local file to download into
     * @throws IOException
     *             on I/O errors
     */
    public void retrieveFile(String ftpFileName, File localFile) throws IOException {
        checkRemoteFileStatus(ftpFileName);
        OutputStream out = new BufferedOutputStream(new FileOutputStream(localFile));
        retrieveFile(ftpFileName, out);
    }

    /**
     * 檢查遠端檔案狀態
     * 
     * @param ftpFileName
     * @throws IOException
     */
    private void checkRemoteFileStatus(String ftpFileName) throws IOException {
        // Get file info.
        FTPFile[] fileInfoArray = ftpClient.listFiles(ftpFileName);
        if (fileInfoArray == null || fileInfoArray.length == 0) {
            throw new FileNotFoundException(String.format("File '%s' was not found on FTP server.", ftpFileName));
        }

        // Check file size.
        FTPFile fileInfo = fileInfoArray[0];
        long size = fileInfo.getSize();
        if (size > Integer.MAX_VALUE) {
            throw new IOException(String.format("File '%s' is too large.", ftpFileName));
        }
    }

    /**
     * Get file from FTP server
     * 
     * @param ftpFileName
     * @return
     * @throws IOException
     */
    public byte[] retrieveFile(String ftpFileName) throws IOException {
        checkRemoteFileStatus(ftpFileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        retrieveFile(ftpFileName, new BufferedOutputStream(baos));
        return baos.toByteArray();
    }

    /**
     * Put file on FTP server from given input stream
     * 
     * @param ftpFileName
     *            file name on FTP server
     * @param in
     *            InputStream
     * @throws IOException
     */
    public void storeFile(String ftpFileName, InputStream in) throws IOException {
        try {
            if (!ftpClient.storeFile(ftpFileName, in)) {
                throw new IOException(String.format("Can't upload file '%s' to FTP server. Check FTP permissions and path.", ftpFileName));
            }
        }
        finally {
            try {
                in.close();
            }
            catch (IOException ex) {
                _logger.error("close storeFile error: ", ex);
            }
        }
    }

    /**
     * 對遠端檔案修改檔案名稱
     * 
     * @param from
     * @param to
     * @throws IOException
     */
    public boolean rename(String from, String to) throws IOException {
        return ftpClient.rename(from, to);
    }

    /**
     * Delete the file from the FTP server.
     * 
     * @param ftpFileName
     *            file name on FTP server (with absolute path)
     * @throws IOException
     *             on I/O errors
     */
    public void deleteFile(String ftpFileName) throws IOException {
        if (!ftpClient.deleteFile(ftpFileName)) {
            throw new IOException(String.format("Can't remove file '%s' from FTP server.", ftpFileName));
        }
    }

    /**
     * Upload the file to the FTP server.
     * 
     * @param ftpFileName
     *            file name on FTP server (with absolute path)
     * @param localFile
     *            local file to upload
     * @throws IOException
     *             on I/O errors
     */
    public void upload(String ftpFileName, File localFile) throws IOException {
        // File check.
        if (!localFile.exists()) {
            _logger.error("Can't upload file. This file doesn't exist. FilePath:{}", localFile.getAbsolutePath());
            throw new IOException("Can't upload file. This file doesn't exist.");
        }

        // Upload.
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(localFile));
            if (!ftpClient.storeFile(ftpFileName, in)) {
                _logger.error("Can't upload file to FTP server. Check FTP permissions and path. ftpFileName:{}", ftpFileName);
                throw new IOException("Can't upload file to FTP server. Check FTP permissions and path.");
            }
        }
        finally {
            try {
                if (null != in) {
                    in.close();
                }
            }
            catch (IOException ex) {
                _logger.error("close upload error: ", ex);
            }
        }
    }

    /**
     * Upload the file to the FTP server.
     * 
     * @param ftpFileName
     *            file name on FTP server (with absolute path)
     * @param fileData
     *            byte[]
     * @throws IOException
     *             on I/O errors
     */
    public void upload(String ftpFileName, byte[] fileData) throws IOException {
        // Upload.
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(fileData);
            if (!ftpClient.storeFile(ftpFileName, in)) {
                throw new IOException(String.format("Can't upload file '%s' to FTP server. Check FTP permissions and path.", ftpFileName));
            }
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex) {
                _logger.error("close upload error: ", ex);
            }
        }
    }

    /**
     * 上傳目錄（會覆蓋)
     * 
     * @param remotePath
     *            遠端目錄 ex:/home/test/a
     * @param localPath
     *            本地端目錄 ex:D:/test/a
     * @throws IOException
     */
    public void uploadDir(String remotePath, String localPath) throws IOException {
        localPath = PathCleanUtils.cleanPath(localPath);
        File file = new File(localPath);
        if (file.exists()) {
            // 若切換遠端目錄失敗，建立目錄，並且再次切換進入遠端目錄
            if (!ftpClient.changeWorkingDirectory(remotePath)) {
                boolean makeDirectory = ftpClient.makeDirectory(remotePath);
                _logger.info("創建目錄結果：{}", makeDirectory);
                boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory(remotePath);
                _logger.info("切換目錄結果：{}", changeWorkingDirectory);
            }

            File[] ftpFiles = file.listFiles();
            for (File ftpFile : ftpFiles) {
                if (ftpFile.isDirectory() && StringUtils.notAllEquals(ftpFile.getName(), ".", "..")) {
                    uploadDir(remotePath + "/" + ftpFile.getName(), ftpFile.getPath());
                }
                else if (ftpFile.isFile()) {
                    upload(remotePath + "/" + ftpFile.getName(), ftpFile);
                }
            }
        }
    }

    /**
     * 下载目錄（會覆蓋)
     * 
     * @param remotePath
     *            遠端目錄 ex:/home/test/a
     * @param localPath
     *            本地端目錄 ex:D:/test/a
     * @return
     * @throws IOException
     */
    public void downloadDir(String remotePath, String localPath) throws IOException {
        localPath = PathCleanUtils.cleanPath(localPath);
        File file = new File(localPath);
        // #4504 Unchecked Return Value 文件存在或新增檔案夾成功才繼續
        boolean isSucessMkdirs = file.exists() || file.mkdirs();
        if (isSucessMkdirs) {
            FTPFile[] ftpFiles = ftpClient.listFiles(remotePath);
            for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
                FTPFile ftpFile = ftpFiles[i];
                if (ftpFile.isDirectory() && StringUtils.notAllEquals(ftpFile.getName(), ".", "..")) {
                    downloadDir(remotePath + "/" + ftpFile.getName(), localPath + "/" + ftpFile.getName());
                }
                else {
                    retrieveFile(remotePath + "/" + ftpFile.getName(), new File(localPath + "/" + ftpFile.getName()));
                }
            }
        }
        else {
            throw new IOException(String.format("remote file '%s' can mkdir to local file '%s'", remotePath, localPath));
        }

    }

    /**
     * List the file name in the given FTP directory.
     * 
     * @param ftpFilePathname
     *            file name on FTP server (with absolute path)
     * @return files relative names list
     * @throws IOException
     *             on I/O errors
     */
    public List<String> listFileNames(String ftpFilePathname) throws IOException {
        List<String> fileNames = new ArrayList<>();
        FTPFile[] ftpFiles = ftpClient.listFiles(ftpFilePathname);
        for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
            FTPFile ftpFile = ftpFiles[i];
            if (ftpFile.isFile()) {
                fileNames.add(ftpFile.getName());
            }
        }
        return fileNames;
    }

    /**
     * List the files in the given FTP directory.
     * 
     * @param filePath
     *            directory
     * @return list
     * @throws IOException
     */
    public List<FTPFile> listFiles(String filePath) throws IOException {
        List<FTPFile> fileList = new ArrayList<FTPFile>();
        FTPFile[] ftpFiles = ftpClient.listFiles(filePath);
        for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
            FTPFile ftpFile = ftpFiles[i];
            fileList.add(ftpFile);
        }
        return fileList;
    }

    /**
     * Send an FTP Server site specific command
     * 
     * @param args
     *            site command arguments
     * @throws IOException
     *             on I/O errors
     */
    public void sendSiteCommand(String args) throws IOException {
        if (isConnected()) {
            try {
                ftpClient.sendSiteCommand(args);
            }
            catch (IOException ex) {
                _logger.error("close sendSiteCommand: ", ex);
            }
        }
    }

    /**
     * Get current directory on FTP server
     * 
     * @return current directory
     */
    public String printWorkingDirectory() {
        if (isConnected()) {
            try {
                return ftpClient.printWorkingDirectory();
            }
            catch (IOException e) {
                _logger.error("close printWorkingDirectory: ", e);
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * Set working directory on FTP server
     * 
     * @param dir
     *            new working directory
     * @return true, if working directory changed
     */
    public boolean changeWorkingDirectory(String dir) {
        if (isConnected()) {
            try {
                return ftpClient.changeWorkingDirectory(dir);
            }
            catch (IOException e) {
                _logger.error("close changeWorkingDirectory: ", e);
            }
        }
        return false;
    }

    /**
     * Change working directory on FTP server to parent directory
     * 
     * @return true, if working directory changed
     */
    public boolean changeToParentDirectory() {
        if (isConnected()) {
            try {
                return ftpClient.changeToParentDirectory();
            }
            catch (IOException e) {
                _logger.error("close changeToParentDirectory: ", e);
            }
        }
        return false;
    }

    /**
     * Get parent directory name on FTP server
     * 
     * @return parent directory
     */
    public String printParentDirectory() {
        if (isConnected()) {
            String w = printWorkingDirectory(); // 取得「當前遠端目錄」名稱
            changeToParentDirectory(); // 切換至上層遠端目錄
            String p = printWorkingDirectory(); // 取得「當前遠端目錄」名稱
            changeWorkingDirectory(w); // 再切換回原本的目錄
            return p;
        }
        return StringUtils.EMPTY;
    }

    /**
     * 建立目錄
     * 
     * @param ftpFileName
     *            file name on FTP server (with absolute path)
     * @throws IOException
     */
    public boolean makeDirectory(String ftpFileName) throws IOException {
        return ftpClient.makeDirectory(ftpFileName);
    }

    @Override
    public void close() throws Exception {
        disconnect();
    }

}
