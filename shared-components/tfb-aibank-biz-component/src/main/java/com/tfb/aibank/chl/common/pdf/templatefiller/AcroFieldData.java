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

/**
 * for PDF AcroField
 * 
 * @author horance
 *
 */
public class AcroFieldData {

    private String value;

    private Float fontSize;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public String toString() {
        return value;
    }

}
