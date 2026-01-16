/**
 * 
 */
package com.tfb.aibank.biz.user.repository.piblog.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)LoginUidLimitationEntity.java
* 
* <p>Description:登入身分證限制</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "LOGIN_UID_LIMITATION")
public class LoginUidLimitationEntity {

    /**
     * 限制通路 AIBANK
     */
    @Id
    @Column(name = "CHANNEL")
    private String channel;

    /**
     * 身分證字號 (身分證字號以SHA256雜湊)
     */
    @Id
    @Column(name = "HASH_UID")
    private String hashUid;

    /**
     * 限制之行銀裝置ID
     */
    @Column(name = "DEVICE_ID")
    private String deviceId;

    /**
     * 限制內網之IP (一次僅限設定一個IP)
     */
    @Column(name = "IP")
    private String ip;

    /**
     * 備註
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 建立時間
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel
     *            the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the hashUid
     */
    public String getHashUid() {
        return hashUid;
    }

    /**
     * @param hashUid
     *            the hashUid to set
     */
    public void setHashUid(String hashUid) {
        this.hashUid = hashUid;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
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