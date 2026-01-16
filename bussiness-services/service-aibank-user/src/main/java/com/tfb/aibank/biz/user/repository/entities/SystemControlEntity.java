package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.tfb.aibank.biz.user.repository.entities.pk.SystemControlEntityPk;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "SYSTEM_CONTROL_TABLE", schema = "DBUSERNEB")
@IdClass(SystemControlEntityPk.class)
public class SystemControlEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TASK_ID", length = 20, nullable = false)
    private String taskId;
    
    @Id
    @Column(name = "TABLENAME", length = 50, nullable = false)
    private String tablename;
    
    @Column(name = "USEFLAG", length = 1)
    private String useFlag = "A";
    
    @Column(name = "UPD_TIME")
    @CreationTimestamp
    private LocalDateTime updTime;
    
    // Constructors
    public SystemControlEntity() {}
    
    public SystemControlEntity(String taskId, String tablename) {
        this.taskId = taskId;
        this.tablename = tablename;
    }
    
    // Getters and Setters
    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }
    
    public String getTablename() { return tablename; }
    public void setTablename(String tablename) { this.tablename = tablename; }
    
    public String getUseFlag() { return useFlag; }
    public void setUseFlag(String useFlag) { this.useFlag = useFlag; }
    
    public LocalDateTime getUpdTime() { return updTime; }
    public void setUpdTime(LocalDateTime updTime) { this.updTime = updTime; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemControlEntity that = (SystemControlEntity) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(tablename, that.tablename);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(taskId, tablename);
    }

}
