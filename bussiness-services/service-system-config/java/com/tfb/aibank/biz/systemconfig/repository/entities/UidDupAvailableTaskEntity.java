/**
 * 
 */
package com.tfb.aibank.biz.systemconfig.repository.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)TxSystemMapRepository.java
* 
* <p>Description:提供有誤別碼使用者登入時，限制其可使用的功能 repository</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/05, John 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "UID_DUP_AVALIBLE_TASK")
public class UidDupAvailableTaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 開放/限制使用的TASK_ID
     */
    @Id
    @Column(name = "TASK_ID") // VARCHAR2
    private String taskId;

    /**
     * 0:限制使用/1:可使用
     */
    @Basic
    @Column(name = "AVALIBLE_FLAG") // NUMBER
    private int avalibleFlag;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME") // TIMESTAMP(6)
    private Date createTime;

    /**
     * 功能描述
     */
    @Basic
    @Column(name = "TASK_DESC") // VARCHAR2
    private String taskDesc;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getAvalibleFlag() {
        return avalibleFlag;
    }

    public void setAvalibleFlag(int avalibleFlag) {
        this.avalibleFlag = avalibleFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

}
