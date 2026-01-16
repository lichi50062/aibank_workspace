package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;

import java.util.List;
// @formatter:off
/**
 * @(#)NGNQU001041Rq.java
 *
 * <p>Description: NGNQU001041Rq</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[NGNQU001041Rq]</li>
 * </ol>
 */
//@formatter:on
@Component
public class NGNQU001041Rq implements RqData {

    private List<UsualTaskVo> usualTasks;

    public List<UsualTaskVo> getUsualTasks() {
        return usualTasks;
    }

    public void setUsualTasks(List<UsualTaskVo> usualTasks) {
        this.usualTasks = usualTasks;
    }
}
