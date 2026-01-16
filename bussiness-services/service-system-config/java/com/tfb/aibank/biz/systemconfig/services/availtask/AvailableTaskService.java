/**
 * 
 */
package com.tfb.aibank.biz.systemconfig.services.availtask;

import java.util.ArrayList;
import java.util.List;

import com.tfb.aibank.biz.systemconfig.repository.CcAvalibleTaskRepository;
import com.tfb.aibank.biz.systemconfig.repository.UidDupAvailableTaskRepository;
import com.tfb.aibank.biz.systemconfig.repository.entities.CcAvalibleTaskEntity;
import com.tfb.aibank.biz.systemconfig.repository.entities.UidDupAvailableTaskEntity;
import com.tfb.aibank.biz.systemconfig.services.availtask.model.AvailableTaskResponse;
import com.tfb.aibank.biz.systemconfig.services.availtask.model.CcAvalibleTaskEntityVo;
import com.tfb.aibank.biz.systemconfig.services.availtask.model.UidDupAvailableTaskEntityVo;

//@formatter:off
/**
* @(#)AvailTaskService.java
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
public class AvailableTaskService {

    CcAvalibleTaskRepository ccAvalibleTaskRepository;

    UidDupAvailableTaskRepository uidDupAvailableTaskRepository;

    public AvailableTaskService(CcAvalibleTaskRepository ccAvalibleTaskRepository, UidDupAvailableTaskRepository uidDupAvailableTaskRepository) {
        this.ccAvalibleTaskRepository = ccAvalibleTaskRepository;
        this.uidDupAvailableTaskRepository = uidDupAvailableTaskRepository;
    }

    public AvailableTaskResponse getAvailableTask() {
        AvailableTaskResponse response = new AvailableTaskResponse();

        List<CcAvalibleTaskEntity> ccAvalibleTaskEntity = ccAvalibleTaskRepository.findAll();
        List<CcAvalibleTaskEntityVo> ccAvalibleTaskEntityVo = new ArrayList<CcAvalibleTaskEntityVo>();
        for (CcAvalibleTaskEntity c : ccAvalibleTaskEntity) {
            CcAvalibleTaskEntityVo cc = new CcAvalibleTaskEntityVo();
            cc.setTaskId(c.getTaskId());
            cc.setAvalibleFlag(c.getAvalibleFlag());
            cc.setCreateTime(c.getCreateTime());
            cc.setTaskDesc(c.getTaskDesc());
            cc.setPlatform(c.getPlatform());
            ccAvalibleTaskEntityVo.add(cc);
        }

        List<UidDupAvailableTaskEntity> uidDupAvailableTaskEntity = uidDupAvailableTaskRepository.findAll();
        List<UidDupAvailableTaskEntityVo> uidDupAvailableTaskEntityVo = new ArrayList<UidDupAvailableTaskEntityVo>();
        for (UidDupAvailableTaskEntity u : uidDupAvailableTaskEntity) {
            UidDupAvailableTaskEntityVo uu = new UidDupAvailableTaskEntityVo();
            uu.setTaskId(u.getTaskId());
            uu.setAvalibleFlag(u.getAvalibleFlag());
            uu.setTaskDesc(u.getTaskDesc());
            uu.setCreateTime(u.getCreateTime());
            uidDupAvailableTaskEntityVo.add(uu);
        }

        response.setCcAvalibleTaskEntityVo(ccAvalibleTaskEntityVo);
        response.setUidDupAvailableTaskEntityVo(uidDupAvailableTaskEntityVo);
        return response;
    }

}
