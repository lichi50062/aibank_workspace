package com.tfb.aibank.biz.component.videoauthapi.model;

// @formatter:off
/**
 * @(#)CheckWaitCountRequest.java
 * 
 * <p>Description:[視訊 api : checkWaitCount 等待人數資訊]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CheckWaitCountRequest {

    private String VQ;

    private String Skill;

    private String Channel;

    /**
     * @param videoAuthApiCheckWaitCountVQ
     * @param videoAuthApiCheckWaitCountSkill
     * @param string
     */
    public CheckWaitCountRequest(String vQ, String skill, String channel) {
        this.VQ = vQ;
        this.Skill = skill;
        this.Channel = channel;
    }

    /**
     * @return the vQ
     */
    public String getVQ() {
        return VQ;
    }

    /**
     * @param vQ
     *            the vQ to set
     */
    public void setVQ(String vQ) {
        VQ = vQ;
    }

    /**
     * @return the skill
     */
    public String getSkill() {
        return Skill;
    }

    /**
     * @param skill
     *            the skill to set
     */
    public void setSkill(String skill) {
        Skill = skill;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return Channel;
    }

    /**
     * @param channel
     *            the channel to set
     */
    public void setChannel(String channel) {
        Channel = channel;
    }

}
