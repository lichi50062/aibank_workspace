package com.tfb.aibank.biz.pushsender.repository.aib;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.pushsender.repository.aib.entities.CustomizedNotificationRecordEntity;
import com.tfb.aibank.biz.pushsender.repository.dto.CustomizedNotificationDeviceInfoDto;

// @formatter:off
/**
 * @(#)CustomizedNotificationRecordRepository.java
 * 
 * <p>Description:智能通知訊息 Repository</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/01, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface CustomizedNotificationRecordRepository extends JpaRepository<CustomizedNotificationRecordEntity, Integer> {

    static final String SEND_SQL = """
             with pushRecords as (
                select t1.CUSTOMIZED_NOTIFICATION_RECORD_KEY from CUSTOMIZED_NOTIFICATION_RECORD t1 FORCE INDEX (CNR_PART_SEND_IDX_03)
                where
                  t1.SEND_STATUS in ('W','F') and t1.IS_PUSH = 1 and t1.STATUS = 'O' and subtime(?1,'2:0:0.00') < t1.START_DATE
                  and t1.START_DATE <= ?1 and t1.END_DATE > ?1
                  and DAYOFYEAR(subtime(?1,'2:0:0.00')) <= t1.MSG_DOY and t1.MSG_DOY <= DAYOFYEAR(?1)
                  and t1.RESEND_COUNT <= ?4
                  ORDER BY t1.PRIORITY DESC LIMIT 1000
                  FOR UPDATE
             )
             update CUSTOMIZED_NOTIFICATION_RECORD a set a.UPDATE_TIME = ?1,
                    a.SEND_STATUS = 'P', a.RESEND_COUNT = (a.RESEND_COUNT + 1), a.PUSH_KEY = ?5
             where CUSTOMIZED_NOTIFICATION_RECORD_KEY in (select CUSTOMIZED_NOTIFICATION_RECORD_KEY from pushRecords);
            """;

    static final String CROSS_YEAR_SEND_SQL = """
            with pushRecords as (
               select t1.CUSTOMIZED_NOTIFICATION_RECORD_KEY from CUSTOMIZED_NOTIFICATION_RECORD t1 FORCE INDEX (CNR_PART_SEND_IDX_03)
               where
                 t1.SEND_STATUS in ('W','F') and t1.IS_PUSH = 1 and t1.STATUS = 'O' and subtime(?1,'2:0:0.00') < t1.START_DATE
                 and t1.START_DATE <= ?1 and t1.END_DATE > ?1
                 and (DAYOFYEAR(subtime(?1,'2:0:0.00')) <= t1.MSG_DOY and t1.MSG_DOY < 367) or (t1.MSG_DOY <= DAYOFYEAR(?1) and t1.MSG_DOY > 0))
                 and t1.RESEND_COUNT <= ?4
                 ORDER BY t1.PRIORITY DESC LIMIT 1000
                 FOR UPDATE
            )
            update CUSTOMIZED_NOTIFICATION_RECORD a set a.UPDATE_TIME = ?1,
                   a.SEND_STATUS = 'P', a.RESEND_COUNT = (a.RESEND_COUNT + 1), a.PUSH_KEY = ?5
            where CUSTOMIZED_NOTIFICATION_RECORD_KEY in (select CUSTOMIZED_NOTIFICATION_RECORD_KEY from pushRecords);
           """;

    static final String FETCH_SQL = """
    		select new com.tfb.aibank.biz.pushsender.repository.dto.CustomizedNotificationDeviceInfoDto(a.customizedNotificationRecordKey, a.priority, 
             a.pushMessage, a.titleMessage, b.deviceUuId, b.devicePlatform, b.pushToken, b.notifyPass, a.offerId)
             from CustomizedNotificationRecordEntity a INNER JOIN MbDevicePushInfoEntity b ON a.userKey = b.userKey 
             where a.sendStatus = 'P' and a.isPush = 1 and a.pushKey = :pushKey and :msgDoyStart <= a.msgDoy and a.msgDoy <= :msgDoyEnd
    		""";

    static final String CROSS_YEAR_FETCH_SQL = """
    		select new com.tfb.aibank.biz.pushsender.repository.dto.CustomizedNotificationDeviceInfoDto(a.customizedNotificationRecordKey, a.priority, 
             a.pushMessage, a.titleMessage, b.deviceUuId, b.devicePlatform, b.pushToken, b.notifyPass, a.offerId)
             from CustomizedNotificationRecordEntity a INNER JOIN MbDevicePushInfoEntity b ON a.userKey = b.userKey 
             where a.sendStatus = 'P' and a.isPush = 1 and a.pushKey = :pushKey 
             and ((:msgDoyStart <= a.msgDoy and a.msgDoy < 367) or (a.msgDoy <= :msgDoyEnd and a.msgDoy > 0))
    		""";
    
    @Modifying
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Query(value = SEND_SQL, nativeQuery = true)
    int updateSendStatus(Date updateTime, String datetime, String time, int resendCount, String pushKey);

    @Modifying
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Query(value = CROSS_YEAR_SEND_SQL, nativeQuery = true)
    int updateSendStatusCrossYear(Date updateTime, String datetime, String time, int resendCount, String pushKey);
    
    // @formatter:off
    @Query(value = FETCH_SQL)
    // @formatter:on
    List<CustomizedNotificationDeviceInfoDto> fetchMarketingNotificationData(@Param("pushKey") String pushKey, @Param("msgDoyStart") int msgDoyStart, @Param("msgDoyEnd") int msgDoyEnd);

    // @formatter:off
    @Query(value = CROSS_YEAR_FETCH_SQL)
    // @formatter:on
    List<CustomizedNotificationDeviceInfoDto> fetchMarketingNotificationDataCrossYear(@Param("pushKey") String pushKey, @Param("msgDoyStart") int msgDoyStart, @Param("msgDoyEnd") int msgDoyEnd);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Query("update CustomizedNotificationRecordEntity c set c.sendStatus = :status where c.customizedNotificationRecordKey = :key ")
    int updateSendStatusByKey(@Param("status") String status, @Param("key") Integer key);

}
