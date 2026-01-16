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
package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)KnowledgeCheckLogEntity.java
 * 
 * <p>Description:交易高齡認知檢核 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/20, Alex PY Li	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "KNOWLEDGE_CHECK_LOG")
public class KnowledgeCheckLogEntity {
    /** 資料鍵值 */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KNOWLEDGE_CHECK_LOG_SEQ")
    @SequenceGenerator(name = "KNOWLEDGE_CHECK_LOG_SEQ", sequenceName = "KNOWLEDGE_CHECK_LOG_SEQ", allocationSize = 1)
    @Column(name = "RECORD_KEY")
    private Integer recordKey;
    /** 公司鍵值 */
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;
    /** 使用者鍵值 */
    @Column(name = "USER_KEY")
    private Integer userKey;
    /** 交易代碼 */
    @Column(name = "TASK_ID")
    private String taskId;
    /** 超齡問題1題目 */
    @Column(name = "QUESTION_1")
    private String question1;
    /** 超齡問題1答案 */
    @Column(name = "ANSWER_1")
    private String answer1;
    /** 超齡問題2題目 */
    @Column(name = "QUESTION_2")
    private String question2;
    /** 超齡問題2答案 */
    @Column(name = "ANSWER_2")
    private String answer2;
    /** 超齡問題3題目 */
    @Column(name = "QUESTION_3")
    private String question3;
    /** 超齡問題3答案 */
    @Column(name = "ANSWER_3")
    private String answer3;
    /** CLIENT IP */
    @Column(name = "CLIENT_IP")
    private String clientIp;
    /** CLIENT PORT */
    @Column(name = "CLIENT_PORT")
    private String clientPort;
    /** TRACE ID */
    @Column(name = "TRACE_ID")
    private String traceId;
    /** 測試時間 */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * @return the recordKey
     */
    public Integer getRecordKey() {
        return recordKey;
    }

    /**
     * @param recordKey
     *            the recordKey to set
     */
    public void setRecordKey(Integer recordKey) {
        this.recordKey = recordKey;
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

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
     * @return the question1
     */
    public String getQuestion1() {
        return question1;
    }

    /**
     * @param question1
     *            the question1 to set
     */
    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    /**
     * @return the answer1
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * @param answer1
     *            the answer1 to set
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    /**
     * @return the question2
     */
    public String getQuestion2() {
        return question2;
    }

    /**
     * @param question2
     *            the question2 to set
     */
    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    /**
     * @return the answer2
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * @param answer2
     *            the answer2 to set
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * @return the question3
     */
    public String getQuestion3() {
        return question3;
    }

    /**
     * @param question3
     *            the question3 to set
     */
    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    /**
     * @return the answer3
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * @param answer3
     *            the answer3 to set
     */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return the clientPort
     */
    public String getClientPort() {
        return clientPort;
    }

    /**
     * @param clientPort
     *            the clientPort to set
     */
    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    /**
     * @return the traceId
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId
     *            the traceId to set
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
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

}
