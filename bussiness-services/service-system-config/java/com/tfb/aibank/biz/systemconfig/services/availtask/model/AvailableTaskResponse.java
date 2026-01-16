/**
 * 
 */
package com.tfb.aibank.biz.systemconfig.services.availtask.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)AvailableTaskResponse.java
* 
* <p>Description:
*   提供有誤別碼使用者登入時，限制其可使用的功能 repository
*   
* </p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/05, John 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AvailableTaskResponse {

    @Schema(description = "CcAvalibleTaskEntity")
    private List<CcAvalibleTaskEntityVo> ccAvalibleTaskEntityVo;

    @Schema(description = "UidDupAvailableTaskEntityVo")
    private List<UidDupAvailableTaskEntityVo> uidDupAvailableTaskEntityVo;

    /**
     * @return the ccAvalibleTaskEntityVo
     */
    public List<CcAvalibleTaskEntityVo> getCcAvalibleTaskEntityVo() {
        return ccAvalibleTaskEntityVo;
    }

    /**
     * @param ccAvalibleTaskEntityVo
     *            the ccAvalibleTaskEntityVo to set
     */
    public void setCcAvalibleTaskEntityVo(List<CcAvalibleTaskEntityVo> ccAvalibleTaskEntityVo) {
        this.ccAvalibleTaskEntityVo = ccAvalibleTaskEntityVo;
    }

    /**
     * @return the uidDupAvailableTaskEntityVo
     */
    public List<UidDupAvailableTaskEntityVo> getUidDupAvailableTaskEntityVo() {
        return uidDupAvailableTaskEntityVo;
    }

    /**
     * @param uidDupAvailableTaskEntityVo
     *            the uidDupAvailableTaskEntityVo to set
     */
    public void setUidDupAvailableTaskEntityVo(List<UidDupAvailableTaskEntityVo> uidDupAvailableTaskEntityVo) {
        this.uidDupAvailableTaskEntityVo = uidDupAvailableTaskEntityVo;
    }

}
