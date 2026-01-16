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
package com.tfb.aibank.chl.common.pdf.templatefiller;

public enum PDFFile {

    FXEX_SALE("FXEX", "Sale.pdf"),

    FXEX_PURCHASE("FXEX", "Purchase.pdf");

    private String txn;

    private String fileName;

    PDFFile(String txn, String fileName) {
        this.txn = txn;
        this.fileName = fileName;
    }

    public String getTxn() {
        return txn;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

}
