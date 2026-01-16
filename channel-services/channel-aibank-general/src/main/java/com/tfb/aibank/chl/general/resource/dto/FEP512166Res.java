package com.tfb.aibank.chl.general.resource.dto;

import com.tfb.aibank.common.model.TxHeadRs;

// @formatter:off
/**
 * @(#)FEP512166Res.java
 * 
 * <p>Description:客戶分群跨行手續費優惠查詢(FEP512166)下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FEP512166Res extends TxHeadRs {

    private static final long serialVersionUID = 668949296596376569L;

    /** 提款優惠次數 */
    private Integer specCsCnt;

    /** 提款優惠剩餘 */
    private Integer remainCsCnt;

    /** 轉帳優惠次數 */
    private Integer specTrCnt;

    /** 轉帳優惠剩餘 */
    private Integer remainTrCnt;

    /** 使用他行設備轉帳減免次數 */
    private Integer specTrCnt2;

    /** 使用他行設備轉帳剩餘次數 */
    private Integer remainTrCnt2;

    /** 客戶分群等級 */
    private String vipFlag;

    /** 客戶分群等級中文 */
    private String vipFlagName;

    /** 其他優惠身份註記1 */
    private String srcFlg1;

    /** 其他優惠身份註記2 */
    private String srcFlg2;

    /** 其他優惠身份註記3 */
    private String srcFlg3;

    /** 其他優惠身份註記4 */
    private String srcFlg4;

    /** 其他優惠身份註記5 */
    private String srcFlg5;

    /** 其他優惠身份註記6 */
    private String srcFlg6;

    public Integer getSpecCsCnt() {
        return specCsCnt;
    }

    public void setSpecCsCnt(Integer specCsCnt) {
        this.specCsCnt = specCsCnt;
    }

    public Integer getRemainCsCnt() {
        return remainCsCnt;
    }

    public void setRemainCsCnt(Integer remainCsCnt) {
        this.remainCsCnt = remainCsCnt;
    }

    public Integer getSpecTrCnt() {
        return specTrCnt;
    }

    public void setSpecTrCnt(Integer specTrCnt) {
        this.specTrCnt = specTrCnt;
    }

    public Integer getRemainTrCnt() {
        return remainTrCnt;
    }

    public void setRemainTrCnt(Integer remainTrCnt) {
        this.remainTrCnt = remainTrCnt;
    }

    public Integer getSpecTrCnt2() {
        return specTrCnt2;
    }

    public void setSpecTrCnt2(Integer specTrCnt2) {
        this.specTrCnt2 = specTrCnt2;
    }

    public Integer getRemainTrCnt2() {
        return remainTrCnt2;
    }

    public void setRemainTrCnt2(Integer remainTrCnt2) {
        this.remainTrCnt2 = remainTrCnt2;
    }

    public String getVipFlag() {
        return vipFlag;
    }

    public void setVipFlag(String vipFlag) {
        this.vipFlag = vipFlag;
    }

    public String getVipFlagName() {
        return vipFlagName;
    }

    public void setVipFlagName(String vipFlagName) {
        this.vipFlagName = vipFlagName;
    }

    public String getSrcFlg1() {
        return srcFlg1;
    }

    public void setSrcFlg1(String srcFlg1) {
        this.srcFlg1 = srcFlg1;
    }

    public String getSrcFlg2() {
        return srcFlg2;
    }

    public void setSrcFlg2(String srcFlg2) {
        this.srcFlg2 = srcFlg2;
    }

    public String getSrcFlg3() {
        return srcFlg3;
    }

    public void setSrcFlg3(String srcFlg3) {
        this.srcFlg3 = srcFlg3;
    }

    public String getSrcFlg4() {
        return srcFlg4;
    }

    public void setSrcFlg4(String srcFlg4) {
        this.srcFlg4 = srcFlg4;
    }

    public String getSrcFlg5() {
        return srcFlg5;
    }

    public void setSrcFlg5(String srcFlg5) {
        this.srcFlg5 = srcFlg5;
    }

    public String getSrcFlg6() {
        return srcFlg6;
    }

    public void setSrcFlg6(String srcFlg6) {
        this.srcFlg6 = srcFlg6;
    }

}
