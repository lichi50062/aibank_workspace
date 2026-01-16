/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.ibm.tw.ibmb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ibm.tw.commons.util.StringUtils;

/**
 * 登入的使用者的基本資訊
 * 
 * @author Alex LS Chen
 *
 */
@JsonSerialize
public class B2CWebUser<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public B2CWebUser() {
    }

    public B2CWebUser(T userData) {
        this.userData = userData;
    }

    public T getUserData() {
        return userData;
    }

    /**
     * 各種 User 沿申資料
     */
    private T userData;

    /**
     * 身分證字號
     */
    private String custId;

    /**
     * 使用者代號
     */
    private String userId;

    /**
     * 加密後的密碼
     */
    private String pinblock;

    /**
     * Encrypted ID for adobe insight;
     */
    private String insightId;

    /**
     * 使用者擁有的角色
     */
    private Set<String> allRoleIds = new TreeSet<>();

    /**
     * 使用者可使用的全部交易
     */
    private Set<String> allTaskIds = new TreeSet<>();

    /**
     * 創建時間
     */
    private Date createTime = new Date();

    /**
     * 
     * @param roleId
     * @return
     */
    public boolean hasRole(String roleId) {
        return allRoleIds.contains(StringUtils.lowerCase(roleId));
    }

    /**
     * 判斷傳入 Id 是否有權限
     * 
     * @param taskId
     * @return
     */
    public boolean hasPermission(String taskId) {
        return allTaskIds.contains(StringUtils.lowerCase(taskId));
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPinblock() {
        return pinblock;
    }

    public void setPinblock(String pinblock) {
        this.pinblock = pinblock;
    }

    public void setUserData(T userData) {
        this.userData = userData;
    }

    /**
     * @param allRoleIds
     *            the allRoleIds to set
     */
    public void addRoleIds(String... roleIds) {
        Arrays.asList(roleIds).forEach(roleId -> {
            this.allRoleIds.add(StringUtils.lowerCase(roleId));
        });
    }

    /**
     * @param allTasks
     *            the allTasks to set
     */
    public void addTaskIds(String... taskIds) {
        Arrays.asList(taskIds).forEach(taskId -> {
            this.allTaskIds.add(StringUtils.lowerCase(taskId));
        });
    }

    /**
     * @param allTasks
     *            the allTasks to set
     */
    public void removeTaskIds(String taskId) {

        this.allTaskIds.remove(StringUtils.lowerCase(taskId));
    }

    public List<String> getAllRoles() {
        List<String> allRolesList = new ArrayList<>(this.allRoleIds.size());
        allRolesList.addAll(allRoleIds);
        return allRolesList;
    }

    /**
     * @return the insightId
     */
    public String getInsightId() {
        return insightId;
    }

    /**
     * @param insightId
     *            the insightId to set
     */
    public void setInsightId(String insightId) {
        this.insightId = insightId;
    }

    /**
     * return AllTaskIds
     * 
     * @return
     */
    public String getAllTaskIds() {
        return allTaskIds.stream().collect(Collectors.joining(","));
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
