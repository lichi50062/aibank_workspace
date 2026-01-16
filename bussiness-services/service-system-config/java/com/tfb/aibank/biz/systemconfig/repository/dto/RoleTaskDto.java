package com.tfb.aibank.biz.systemconfig.repository.dto;

/**
 * 資料表「ROLE」和「ROLE_TASK」INNER JOIN 回傳的結果
 * 
 * @author Edward Tien
 */
public class RoleTaskDto {

    public RoleTaskDto(Integer roleKey, String roleName, String taskId) {
        this.roleKey = roleKey;
        this.roleName = roleName;
        this.taskId = taskId;
    }

    /** 角色鍵值 */
    private int roleKey;

    /** 角色名稱 */
    private String roleName;

    /** 交易功能代號 */
    private String taskId;

    public int getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(int roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
