/**
 * 
 */
package com.tfb.aibank.biz.component.e2ee;

import java.util.Date;

/**
 * @author john
 *
 */
public class TokenVo {

    private String accessToken;
    private String refreshToken;
    // token過期時間
    private Date expiredTime;
    
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public Date getExpiredTime() {
        return expiredTime;
    }
    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
    @Override
    public String toString() {
        return "TokenVo [accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", expiredTime="
                + expiredTime + "]";
    }
}
