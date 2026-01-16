package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import com.tfb.aibank.biz.user.repository.entities.support.BankEmployeeEntityListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)BankEmployeeEntityListener.java
 *
 * <p>Description:行員檔Entity</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/06/04
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "BANK_EMPLOYEE")
@EntityListeners(BankEmployeeEntityListener.class)
public class BankEmployeeEntity {

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 資料鍵值
     */
    @Id
    @Column(name = "EMPLOYEE_KEY")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_EMPLOYEE_SEQ")
    @SequenceGenerator(name = "BANK_EMPLOYEE_SEQ", sequenceName = "BANK_EMPLOYEE_SEQ", allocationSize = 1)
    private Integer employeeKey;

    /**
     * 身份證號
     */
    @Basic
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    /**
     * I:退休人員; A:在職人員
     */
    @Basic
    @Column(name = "HR_STATUS")
    private String hrStatus;

    /**
     * 取得
     * 
     * @return Date
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定
     * 
     * @param createTime
     *            要設定的
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getEmployeeId() {
        return this.employeeId;
    }

    /**
     * 設定
     * 
     * @param employeeId
     *            要設定的
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * 取得 employeeKey
     * 
     * @return 傳回 employeeKey。
     */
    public Integer getEmployeeKey() {
        return employeeKey;
    }

    /**
     * 設定 employeeKey
     * 
     * @param employeeKey
     *            要設定的 employeeKey。
     */
    public void setEmployeeKey(Integer employeeKey) {
        this.employeeKey = employeeKey;
    }

    /**
     * 取得 hrStatus
     *
     * @return String
     */
    public String getHrStatus() {
        return hrStatus;
    }

    /**
     * 設定 hrStatus
     *
     * @param hrStatus
     */
    public void setHrStatus(String hrStatus) {
        this.hrStatus = hrStatus;
    }

}
