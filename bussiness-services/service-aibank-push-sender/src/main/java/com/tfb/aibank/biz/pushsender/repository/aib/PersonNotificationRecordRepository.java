package com.tfb.aibank.biz.pushsender.repository.aib;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tfb.aibank.biz.pushsender.repository.aib.entities.PersonNotificationRecordEntity;
import com.tfb.aibank.biz.pushsender.repository.dto.PersonNotificationDeviceInfoDto;

/**
 * 個人化通知訊息 Repository
 * 
 * @author Edward Tien
 */
public interface PersonNotificationRecordRepository extends JpaRepository<PersonNotificationRecordEntity, Integer> {

	static final String SEND_SQL = """
			with pushRecords as (
				select t1.PERSON_NOTIFICATION_RECORD_KEY from PERSON_NOTIFICATION_RECORD t1 FORCE INDEX (PNR_PART_SEND_IDX_03)
			    where
			      t1.SEND_STATUS in ('W','F') and t1.IS_PUSH = 1 and t1.STATUS = 'O' and subtime(?1,'2:0:0.00') < t1.START_DATE
			      and t1.START_DATE <= ?1 and t1.END_DATE > ?1
			      and DAYOFYEAR(subtime(?1,'2:0:0.00')) <= t1.MSG_DOY and t1.MSG_DOY <= DAYOFYEAR(?1)
			      and t1.RESEND_COUNT <= ?4
			      ORDER BY t1.PRIORITY DESC LIMIT 1000
			      FOR UPDATE
			)
			update PERSON_NOTIFICATION_RECORD a set a.UPDATE_TIME = ?1,
			        a.SEND_STATUS = 'P', a.RESEND_COUNT = (a.RESEND_COUNT + 1), a.PUSH_KEY = ?5
			where PERSON_NOTIFICATION_RECORD_KEY in (select PERSON_NOTIFICATION_RECORD_KEY from pushRecords);
			""";

	static final String CROSS_YEAR_SEND_SQL = """
			with pushRecords as (
				select t1.PERSON_NOTIFICATION_RECORD_KEY from PERSON_NOTIFICATION_RECORD t1 FORCE INDEX (PNR_PART_SEND_IDX_03)
			    where
			      t1.SEND_STATUS in ('W','F') and t1.IS_PUSH = 1 and t1.STATUS = 'O' and subtime(?1,'2:0:0.00') < t1.START_DATE
			      and t1.START_DATE <= ?1 and t1.END_DATE > ?1
                  and (DAYOFYEAR(subtime(?1,'2:0:0.00')) <= t1.MSG_DOY and t1.MSG_DOY < 367) or (t1.MSG_DOY <= DAYOFYEAR(?1) and t1.MSG_DOY > 0))
			      and t1.RESEND_COUNT <= ?4
			      ORDER BY t1.PRIORITY DESC LIMIT 1000
			      FOR UPDATE
			)
			update PERSON_NOTIFICATION_RECORD a set a.UPDATE_TIME = ?1,
			        a.SEND_STATUS = 'P', a.RESEND_COUNT = (a.RESEND_COUNT + 1), a.PUSH_KEY = ?5
			where PERSON_NOTIFICATION_RECORD_KEY in (select PERSON_NOTIFICATION_RECORD_KEY from pushRecords);
			""";
	
	static final String FETCH_SQL = """
			select new com.tfb.aibank.biz.pushsender.repository.dto.PersonNotificationDeviceInfoDto(a.personNotificationRecordKey, a.priority, a.pushMessage, a.titleMessage, b.deviceUuId, b.devicePlatform, b.pushToken, b.notifyPass)
             from PersonNotificationRecordEntity a INNER JOIN MbDevicePushInfoEntity b ON a.userKey = b.userKey 
             where a.sendStatus = 'P' and a.isPush = 1 and a.pushKey = :pushKey and :msgDoyStart <= a.msgDoy and a.msgDoy <= :msgDoyEnd
			""";

	static final String CROSS_YEAR_FETCH_SQL = """
			select new com.tfb.aibank.biz.pushsender.repository.dto.PersonNotificationDeviceInfoDto(a.personNotificationRecordKey, a.priority, a.pushMessage, a.titleMessage, b.deviceUuId, b.devicePlatform, b.pushToken, b.notifyPass)
             from PersonNotificationRecordEntity a INNER JOIN MbDevicePushInfoEntity b ON a.userKey = b.userKey 
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
    List<PersonNotificationDeviceInfoDto> fetchPersonNotificationData(String pushKey, int msgDoyStart, int msgDoyEnd);

    // @formatter:off
    @Query(value = CROSS_YEAR_FETCH_SQL)
    // @formatter:on
    List<PersonNotificationDeviceInfoDto> fetchPersonNotificationDataCrossYear(String pushKey, int msgDoyStart, int msgDoyEnd);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Query("update PersonNotificationRecordEntity c set c.sendStatus = :status where c.personNotificationRecordKey = :key ")
    int updateSendStatusByKey(@Param("status") String status, @Param("key") Integer key);

}
