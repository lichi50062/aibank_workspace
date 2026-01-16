package com.tfb.aibank.biz.user.services.financialdataorder.model;

import java.io.Serializable;
import java.util.Date;

public class FinancialDataOrderModel implements Serializable {
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
     * 排序交易代號. taskId
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

}
