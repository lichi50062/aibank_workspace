package com.tfb.aibank.biz.pushsender.repository.aib;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.pushsender.repository.aib.entities.SystemNotificationRecordEntity;

/**
 * 系統通知訊息 Repository
 * 
 * @author Edward Tien
 */
public interface SystemNotificationRecordRepository extends JpaRepository<SystemNotificationRecordEntity, Integer> {

    @Modifying
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Query(value = "update SYSTEM_NOTIFICATION_RECORD x set UPDATE_TIME = ?1, SEND_STATUS = 'P', RESEND_COUNT = (x.RESEND_COUNT + 1), x.PUSH_KEY = ?3 where ( x.SEND_STATUS in ('W','F') and x.IS_PUSH <> 0 and x.STATUS = 'O' and subtime(?1,'2:0:0.00') < x.START_DATE and x.START_DATE <= ?1 and x.END_DATE > ?1 ) and x.RESEND_COUNT <= ?2 limit 1000", nativeQuery = true)
    int updateSendStatus(Date updateTime, int limit, String pushKey);

    List<SystemNotificationRecordEntity> findBySendStatusAndIsPushAndPushKey(String sendStatus, int isPush, String pushKey);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Query("update SystemNotificationRecordEntity c set c.sendStatus = :status where c.systemNotificationRecordKey = :key ")
    int updateSendStatusByKey(@Param("status") String status, @Param("key") Integer key);

}
