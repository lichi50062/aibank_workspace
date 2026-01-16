package com.tfb.aibank.chl.system.ot003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NSTOT003053Rq.java
* 
* <p>Description:安控驗證共用 - 檢核是否可進行安控 - RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/05, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT003060Rq implements RqData {

    private String taskId;

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

}
