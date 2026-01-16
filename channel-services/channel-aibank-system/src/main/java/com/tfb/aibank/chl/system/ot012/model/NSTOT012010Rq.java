package com.tfb.aibank.chl.system.ot012.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT012010Rq.java
 * 
 * <p>Description: 高齡認知檢核</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT012010Rq implements RqData {
    private String taskNo;
    /** 充分暸解我接下來要申辦投資業務，而非存款業務 */
    private String question1;
    /** 充分暸解我接下來要申辦投資業務 - 是否了解 */
    private String answer1;
    /** 我充分暸解投資不保證獲利或報酬，而且可能會有虧損 */
    private String question2;
    /** 我充分暸解投資不保證獲利或報酬 - 是否了解 */
    private String answer2;
    /** 簡易運算題 */
    private String question3;
    /** 簡易運算題 答案 */
    private String answer3;

    /**
     * @return the taskNo
     */
    public String getTaskNo() {
        return taskNo;
    }

    /**
     * @param taskNo
     *            the taskNo to set
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
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

}
