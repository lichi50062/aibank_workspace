package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;
import java.util.Objects;

/**
 * @(#)SystemControlEntityPk.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/20, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class SystemControlEntityPk implements Serializable{

    private static final long serialVersionUID = 1L;

    private String taskId;
    
    private String tablename;
    
    public SystemControlEntityPk() {}
    
    public SystemControlEntityPk(String taskId, String tablename) {
        this.taskId = taskId;
        this.tablename = tablename;
    }
    
    // Getters and Setters
    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }
    
    public String getTablename() { return tablename; }
    public void setTablename(String tablename) { this.tablename = tablename; }

    @Override
    public int hashCode() {
        return Objects.hash(tablename, taskId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SystemControlEntityPk other = (SystemControlEntityPk) obj;
        return Objects.equals(tablename, other.tablename) && Objects.equals(taskId, other.taskId);
    }
     
}
