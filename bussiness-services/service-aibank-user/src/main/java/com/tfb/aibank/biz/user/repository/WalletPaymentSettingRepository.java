package com.tfb.aibank.biz.user.repository;

import com.tfb.aibank.biz.user.repository.entities.WalletPaymentSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface WalletPaymentSettingRepository extends JpaRepository<WalletPaymentSettingEntity, Integer> {

    // 查詢付款設定 by 公司鍵值, 使用者鍵值和裝置UUID
    Optional<WalletPaymentSettingEntity> findByCompanyKeyAndUserKeyAndDeviceUuid(Integer companyKey, Integer userKey, String deviceUuid);

}
