package com.tfb.aibank.chl.general.qu001.model;

import com.tfb.aibank.chl.component.usualtask.UsualTask;

// @formatter:off
/**
 * @(#)UsualTaskVo.java
 *
 * <p>Description: 常用功能資料VO</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[常用功能資料VO]</li>
 * </ol>
 */
//@formatter:on
public class UsualTaskVo extends UsualTask {

    public UsualTaskVo() {
        super();
    }

    public UsualTaskVo(UsualTask usualTask) {
        super();
        setCompanyKey(usualTask.getCompanyKey());
        setUserKey(usualTask.getUserKey());
        setTaskSort(usualTask.getTaskSort());
        setMenuId(usualTask.getMenuId());
        setMenuName(usualTask.getMenuName());
        setTaskId(usualTask.getTaskId());
        setCreateTime(usualTask.getCreateTime());
        setUpdateTime(usualTask.getUpdateTime());
        setParentMenuId(usualTask.getParentMenuId());
        setParentMenuMame(usualTask.getParentMenuMame());
        setLinkParam(usualTask.getLinkParam());
        setLinkType(usualTask.getLinkType());
        setIconClass(usualTask.getIconClass());
    }



    @Override
    public String toString() {
        return "UsualTaskVo -> " + super.toString();
    }
}
