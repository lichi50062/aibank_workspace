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
package com.tfb.aibank.chl.common.pdf.html2pdf;

import com.pd4ml.PageSize;

// @formatter:off
/**
 * @(#)HPdfContants.java
 * 
 * <p>Description:html->pdf常用常數</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/12, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class HPdfContants {
    public static final String PDF_HEADER = "PdfHeader";
    public static final String PDF_FOOTER = "PdfFooter";
    public static final String PDF_BODY = "PdfBody";
    // public static final String PDF_FONT = "MSJH.ttf";
    // public static final String PDF_FONT = "Monotype Sans Duospace WT TC TW";

    public static final String PDF_FONT = "body { font-family: WT Sans Duo TW; }";
    // PD4ML
    public static final PageSize format = PageSize.A4;
    public static final int TOP = 10;
    public static final int LEFT = 10;
    public static final int RIGHT = 10;
    public static final int BOTTOM = 10;
    public static final String UNITS = "mm";
    public static final int USER_SPACE_WIDTH = 780;
    public static final String NO_PASSWOD = "empty";
}