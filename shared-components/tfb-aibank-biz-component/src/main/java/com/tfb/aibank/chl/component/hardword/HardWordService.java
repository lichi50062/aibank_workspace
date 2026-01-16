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
package com.tfb.aibank.chl.component.hardword;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.ibm.tw.commons.log.IBLog;

// @formatter:off
/**
 * @(#)HardWordService.java
 * 
 * <p>Description:處理「難字」相關服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/26, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

@Service
public class HardWordService {

    protected static IBLog logger = IBLog.getLog(HardWordService.class);

    /** 自定義字體檔案位置 */
    private static final String CUSTOM_FONT_PATH = "config/fonts/AsTTE_00.tte";

    /** 載入的自定義字體 */
    private Font customFont = null;

    /**
     * 生成自定義字體的圖像字節數組，如果傳入文件參數，也輸出到圖像文件
     *
     * @param word
     *            繪圖字符串
     * @param fontSize
     *            繪圖字體size
     * @param width
     *            圖像寬度
     * @param height
     *            圖像高度
     * @param imgFile
     *            圖像文件
     * 
     * @return
     */
    public byte[] genCustomWordImage(String word, float fontSize, int width, int height, File imgFile) {
        return genWordImage(word, loadCustomFont(fontSize), width, height, imgFile);
    }

    /**
     * 使用指定字體生成字符串的圖像字節數組，如果傳入文件參數，也輸出到圖像文件
     *
     * @param word
     *            繪圖字符串
     * @param font
     *            繪圖字體
     * @param width
     *            圖像寬度
     * @param height
     *            圖像高度
     * @param imgFile
     *            圖像文件
     * 
     * @return
     */
    private byte[] genWordImage(String word, Font font, int width, int height, File imgFile) {
        Font myFont = font;
        int myWidth = width;
        int myHeight = height;

        if (null == font) {
            myFont = new Font("微軟雅黑", Font.BOLD, 24);
        }

        BufferedImage bufImg = new BufferedImage(myWidth, myHeight, BufferedImage.TYPE_INT_BGR);

        try {
            Graphics g = bufImg.getGraphics();
            g.setClip(0, 0, myWidth, myHeight);
            g.setColor(Color.white); // 先用白色填充
            g.fillRect(0, 0, myWidth, myHeight);
            Color myColor = new Color(60, 60, 60); // '#3C3C3C'
            g.setColor(myColor); // 再以'#3C3C3C'顏色設置繪畫色

            Rectangle clip = g.getClipBounds();

            g.setFont(myFont); // 設置畫筆字體

            FontMetrics fm = g.getFontMetrics(myFont);
            int ascent = fm.getAscent();
            int descent = fm.getDescent();
            int y = (clip.height - (ascent + descent)) / 2 + ascent;
            g.drawString(word, 0, y);

            g.dispose();

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            ImageIO.write(bufImg, "png", os);

            if (null != imgFile) {
                ImageIO.write(bufImg, "png", imgFile);
            }

            return os.toByteArray();
        }
        catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * 載入自定義字體
     * 
     * @param fontSize
     * @return
     */
    private Font loadCustomFont(float fontSize) {

        if (!Objects.isNull(customFont)) {
            return customFont;
        }

        Font derivedFont = null;

        // fortify: null check
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader == null) {
            logger.warn("error getting classloader");
            throw new IllegalStateException("failed to get classloader");
        }
        InputStream is = null;
        try {
            is = classLoader.getResourceAsStream(CUSTOM_FONT_PATH);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            // 設置字體大小, float型
            derivedFont = customFont.deriveFont(Font.BOLD, fontSize);
        }
        // fortify: Poor Error Handling: Program Catches NullPointerException
        catch (IOException | FontFormatException | IndexOutOfBoundsException e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            try {
                if (null != is) {
                    is.close();
                }
            }
            catch (IOException ioe) {
                logger.error(ioe.getMessage(), ioe);
            }
        }
        return derivedFont;
    }
}
