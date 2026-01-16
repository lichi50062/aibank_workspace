package com.tfb.aibank.chl.creditcard.tx005.model;

// @formatter:off
/**
 * @(#)NCCTX005ProofOfFinancial.java
 * 
 * <p>Description:財力證明檔案</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCTX005ProofFile {

    /** 檔案名稱 */
    private String name;
    /** 檔案內容 */
    private String base64Image;
    /** 檔案大小 */
    private long size;
    /** 檔案格式 */
    private String type;

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
