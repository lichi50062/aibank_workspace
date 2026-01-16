/**
 * 
 */
package com.tfb.aibank.biz.systemconfig.services.availtask.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)UidDupAvailableTaskEntityVo.java
* 
* <p>Description:
*   提供有誤別碼使用者登入時，限制其可使用的功能 repository
*   
* </p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/05, John 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UidDupAvailableTaskEntityVo {

    /**
     * 開放/限制使用的TASK_ID
     */
    @Schema(description = "開放/限制使用的TASK_ID")
    private String taskId;

    /**
     * 0:限制使用/1:可使用
     */
    @Schema(description = "0:限制使用/1:可使用")
    private int avalibleFlag;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 功能描述
     */
    @Schema(description = "功能描述")
    private String taskDesc;

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the avalibleFlag
     */
    public int getAvalibleFlag() {
        return avalibleFlag;
    }

    /**
     * @param avalibleFlag
     *            the avalibleFlag to set
     */
    public void setAvalibleFlag(int avalibleFlag) {
        this.avalibleFlag = avalibleFlag;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the taskDesc
     */
    public String getTaskDesc() {
        return taskDesc;
    }

    /**
     * @param taskDesc
     *            the taskDesc to set
     */
    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

}
