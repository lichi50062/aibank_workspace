package com.tfb.aibank.biz.systemconfig.repository.entities.pk;

import java.io.Serializable;

import com.ibm.tw.commons.util.ConvertUtils;

// @formatter:off
/**
 * @(#)RoleTaskEntityPk.java
 * 
 * <p>Description:角色交易清單 Entity PK</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/25, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class RoleTaskEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleKey;
    private Integer companyKey;
    private String taskId;

    public RoleTaskEntityPk() {
    }

    public RoleTaskEntityPk(String str) {
        fromString(str);
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return roleKey + "::" + companyKey + "::" + taskId;
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((roleKey == null) ? 0 : roleKey.hashCode());
        rs = rs * 37 + ((companyKey == null) ? 0 : companyKey.hashCode());
        rs = rs * 37 + ((taskId == null) ? 0 : taskId.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        RoleTaskEntityPk other = (RoleTaskEntityPk) obj;
        return ((roleKey == null && other.roleKey == null) || (roleKey != null && roleKey.equals(other.roleKey))) && ((companyKey == null && other.companyKey == null) || (companyKey != null && companyKey.equals(other.companyKey))) && ((taskId == null && other.taskId == null) || (taskId != null && taskId.equals(other.taskId)));
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            roleKey = null;
        else
            roleKey = ConvertUtils.str2Int(str);
        str = toke.nextToken();
        if ("null".equals(str))
            companyKey = null;
        else
            companyKey = ConvertUtils.str2Int(str);
        str = toke.nextToken();
        if ("null".equals(str))
            taskId = null;
        else
            taskId = str;
    }

    protected static class Tokenizer {
        private final String str;
        private int last;

        public Tokenizer(String str) {
            this.str = str;
        }

        public String nextToken() {
            int next = str.indexOf("::", last);
            String part;
            if (next == -1) {
                part = str.substring(last);
                last = str.length();
            }
            else {
                part = str.substring(last, next);
                last = next + 2;
            }
            return part;
        }
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(Integer roleKey) {
        this.roleKey = roleKey;
    }
}