package com.tfb.aibank.chl.component.countryname.model;

import java.time.LocalDateTime;

/**
 * @(#)SystemControlModel.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/22, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class SystemControlModel {
    private String taskId;
    private String tablename;
    private String useFlag;
    private LocalDateTime updTime;
    
    public SystemControlModel() {
        
    }
    public SystemControlModel(String taskId, String tablename, String useFlag, LocalDateTime updTime) {
        super();
        this.taskId = taskId;
        this.tablename = tablename;
        this.useFlag = useFlag;
        this.updTime = updTime;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getTablename() {
        return tablename;
    }
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    public String getUseFlag() {
        return useFlag;
    }
    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag;
    }
    public LocalDateTime getUpdTime() {
        return updTime;
    }
    public void setUpdTime(LocalDateTime updTime) {
        this.updTime = updTime;
    }

    
    
}
