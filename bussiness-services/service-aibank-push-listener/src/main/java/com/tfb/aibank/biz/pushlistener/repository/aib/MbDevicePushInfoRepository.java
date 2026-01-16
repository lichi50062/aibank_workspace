package com.tfb.aibank.biz.pushlistener.repository.aib;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfb.aibank.biz.pushlistener.repository.aib.entities.MbDevicePushInfoEntity;

// @formatter:off
/**
 * @(#)MbDeviceInfoRepository.java
 * 
 * <p>Description:行動裝置設定檔 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/07, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface MbDevicePushInfoRepository extends JpaRepository<MbDevicePushInfoEntity, Integer> {

    List<MbDevicePushInfoEntity> findByCompanyKey(Integer companyKey);

    MbDevicePushInfoEntity findByCompanyKeyAndUserKey(Integer companyKey, Integer userKey);

    MbDevicePushInfoEntity findByCompanyKeyAndUserKeyAndDeviceUuId(Integer companyKey, Integer userKey, String deviceUuId);

}
