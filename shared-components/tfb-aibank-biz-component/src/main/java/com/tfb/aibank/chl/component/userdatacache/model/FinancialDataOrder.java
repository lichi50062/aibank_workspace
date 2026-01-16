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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)FinancialDataOrder.java
* 
* <p>Description:投資總覽 011 商品排序</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/26, Leiley
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class FinancialDataOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主檔鍵值
     */
    private Integer orderKxy;

    /**
     * 公司鍵值
     */
    private Integer companyKxy;

    /**
     * 使用者鍵值
     */
    private Integer userKxy;

    /**
     * 使用者代碼
     */
    private String uxd;

    /**
     * 排序類型. 功能代碼
     * <p>
     * NMI：[奈米投]牌卡 FUND：[基金]牌卡 ETF：[海外ETF/股票]牌卡 BOND：[海外債]牌卡 SN：[境內外結構型商品]牌卡 SIDCI：[組合式商品]牌卡 GOLD：[黃金存摺]牌卡
     */
    private String taskId;

    /**
     * 資料排序: 每組最多三筆資料，以逗號分隔
     */
    private String dataOrder;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    public List<String> getDataOrderList() {
        List<String> dataOrders = new ArrayList<>();
        if (StringUtils.isNotEmpty(getDataOrder())) {
            dataOrders = Stream.of(StringUtils.split(getDataOrder(), ",")).map(mem -> StringUtils.trimToEmptyEx(mem)).collect(Collectors.toList());
        }
        return dataOrders;
    }

    /**
     * @return the orderKxy
     */
    public Integer getOrderKxy() {
        return orderKxy;
    }

    /**
     * @param orderKxy
     *         the orderKxy to set
     */
    public void setOrderKxy(Integer orderKxy) {
        this.orderKxy = orderKxy;
    }

    /**
     * @return the companyKxy
     */
    public Integer getCompanyKxy() {
        return companyKxy;
    }

    /**
     * @param companyKxy
     *         the companyKxy to set
     */
    public void setCompanyKxy(Integer companyKxy) {
        this.companyKxy = companyKxy;
    }

    /**
     * @return the uxd
     */
    public String getUxd() {
        return uxd;
    }

    /**
     * @param uxd
     *         the uxd to set
     */
    public void setUxd(String uxd) {
        this.uxd = uxd;
    }

    /**
     * @return the userKxy
     */
    public Integer getUserKxy() {
        return userKxy;
    }

    /**
     * @param userKxy
     *         the userKxy to set
     */
    public void setUserKxy(Integer userKxy) {
        this.userKxy = userKxy;
    }

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *         the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDataOrder() {
        return dataOrder;
    }

    public void setDataOrder(String dataOrder) {
        this.dataOrder = dataOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FinancialDataOrder{" + "orderKey=" + orderKxy + ", companyKey=" + companyKxy + ", userKey=" + userKxy + ", userId='" + uxd + '\'' + ", taskId='" + taskId + '\'' + ", dataOrder='" + dataOrder + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }
}
