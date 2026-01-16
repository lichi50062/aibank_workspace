package com.tfb.aibank.biz.user.services.login.model;

import com.tfb.aibank.chl.session.vo.MbDeviceInfoVo;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ExecuteUserLoginResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "ExecuteUserLoginResponse")
public class ExecuteUserLoginResponse extends AbstractExecuteUserLoginResponse {
    
    @Schema(description = "MB_Device_Info_Entity 其它裝置")
    private MbDeviceInfoVo mbDeviceInfoVoBindedByOtherDevice;


    public ExecuteUserLoginResponse() {
        super();
    }


    public MbDeviceInfoVo getMbDeviceInfoVoBindedByOtherDevice() {      
        if (mbDeviceInfoVoBindedByOtherDevice == null) {
            mbDeviceInfoVoBindedByOtherDevice = new MbDeviceInfoVo();
        }

        return mbDeviceInfoVoBindedByOtherDevice;
    }


    public void setMbDeviceInfoVoBindedByOtherDevice(MbDeviceInfoVo mbDeviceInfoVoBindedByOtherDevice) {
        this.mbDeviceInfoVoBindedByOtherDevice = mbDeviceInfoVoBindedByOtherDevice;
    }
    
    
}
