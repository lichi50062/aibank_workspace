package com.tfb.aibank.biz.component.videoauthapi.model;

//@formatter:off
/**
* @(#)VideoAuthResult.java
* 
* <p>Description:[視訊品質]</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/18, leiley 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class VideoCallQuality {

    /**
     * [聲音品質最差時Jitter數]
     */
    private Integer jitterMax;
    /**
     * [聲音品質最好時Jitter數]
     */
    private Integer jitterMin;
    /**
     * [聲音品質平均Jitter數]
     */
    private Float jitterAvg;

    /**
     * [影像品質最差時 PacketLoss數]
     */
    private Integer pckLossMax;
    /**
     * [影像品質最好時 PacketLoss數]
     */
    private Integer pckLossMin;
    /**
     * [影像品質平均 PacketLoss數]
     */
    private Float pckLossAvg;

    public Integer getJitterMax() {
        return jitterMax;
    }

    public void setJitterMax(Integer jitterMax) {
        this.jitterMax = jitterMax;
    }

    public Integer getJitterMin() {
        return jitterMin;
    }

    public void setJitterMin(Integer jitterMin) {
        this.jitterMin = jitterMin;
    }

    public Float getJitterAvg() {
        return jitterAvg;
    }

    public void setJitterAvg(Float jitterAvg) {
        this.jitterAvg = jitterAvg;
    }

    public Integer getPckLossMax() {
        return pckLossMax;
    }

    public void setPckLossMax(Integer pckLossMax) {
        this.pckLossMax = pckLossMax;
    }

    public Integer getPckLossMin() {
        return pckLossMin;
    }

    public void setPckLossMin(Integer pckLossMin) {
        this.pckLossMin = pckLossMin;
    }

    public Float getPckLossAvg() {
        return pckLossAvg;
    }

    public void setPckLossAvg(Float pckLossAvg) {
        this.pckLossAvg = pckLossAvg;
    }

    public VideoCallQuality() {
        super();
    }

    public VideoCallQuality(Integer jitterMax, Integer jitterMin, Float jitterAvg, Integer pckLossMax, Integer pckLossMin, Float pckLossAvg) {
        super();
        this.jitterMax = jitterMax;
        this.jitterMin = jitterMin;
        this.jitterAvg = jitterAvg;
        this.pckLossMax = pckLossMax;
        this.pckLossMin = pckLossMin;
        this.pckLossAvg = pckLossAvg;
    }

    @Override
    public String toString() {
        return "VideoCallQuality [jitterMax=" + jitterMax + ", jitterMin=" + jitterMin + ", jitterAvg=" + jitterAvg + ", pckLossMax=" + pckLossMax + ", pckLossMin=" + pckLossMin + ", pckLossAvg=" + pckLossAvg + "]";
    }
}
