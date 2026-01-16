/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfb.aibank.chl.component.notification.model.BillHunterNotify;
import com.tfb.aibank.chl.component.notification.model.CreateOtpRecordRequest;
import com.tfb.aibank.chl.component.notification.model.CreateOtpRecordResponse;
import com.tfb.aibank.chl.component.notification.model.CreatePersonalNotificationRequest;
import com.tfb.aibank.chl.component.notification.model.CreatePersonalNotificationResponse;
import com.tfb.aibank.chl.component.notification.model.EmailNotify;
import com.tfb.aibank.chl.component.notification.model.SMSNotify;
import com.tfb.aibank.chl.component.notification.model.UpdateOtpRecordRequest;
import com.tfb.aibank.chl.component.notification.model.UpdateOtpRecordResponse;

import io.swagger.v3.oas.annotations.Operation;

// @formatter:off
/**
 * @(#)NotificationResource.java
 * 
 * <p>Description:溝通 service-aibank-notificaiotn 的介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FeignClient(name = "aibank-biz-component-notification-to-notification-service", url = "${aibank.common.feign.service-aibank-notification-url:http://svc-biz-notification:8080}")
public interface NotificationResource {

    @Operation(summary = "發送Email通知")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notifies/email/{companyKind},{uidDup}/add", produces = MediaType.APPLICATION_JSON_VALUE)
    EmailNotify sendMail(@RequestHeader("custId") String custId, @PathVariable("uidDup") String uidDup, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind, @RequestBody EmailNotify emailNotify);

    @Operation(summary = "寄送交易結果Email通知")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notifies/email/transaction-result/{companyKind},{uidDup}/add", produces = MediaType.APPLICATION_JSON_VALUE)
    EmailNotify sendTxnResultMail(@RequestHeader("custId") String custId, @PathVariable("uidDup") String uidDup, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind, @RequestBody EmailNotify emailNotify);

    @Operation(summary = "發送簡訊通知")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notifies/sms/{companyKind}/add", produces = MediaType.APPLICATION_JSON_VALUE)
    SMSNotify sendSMS(@RequestHeader("custId") String custId, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind, @RequestBody SMSNotify smsNotify);

    @Operation(summary = "發送簡訊通知")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notifies/sms/{companyKind}/{uidDup}/add", produces = MediaType.APPLICATION_JSON_VALUE)
    SMSNotify sendSMS(@RequestHeader("custId") String custId, @PathVariable("uidDup") String uidDup, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind, @RequestBody SMSNotify smsNotify);

    @Operation(summary = "發送BillHunter補寄帳單")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notifies/bill-hunter/{companyKind},{uidDup}/add", produces = MediaType.APPLICATION_JSON_VALUE)
    BillHunterNotify sendBillHunter(@RequestHeader("custId") String custId, @PathVariable("uidDup") String uidDup, @RequestHeader("userId") String userId, @PathVariable("companyKind") Integer companyKind, @RequestBody BillHunterNotify smsNotify);

    @Operation(summary = "建立OTP發送紀錄")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/otp-records/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CreateOtpRecordResponse createOtpRecord(@RequestBody CreateOtpRecordRequest request);

    @Operation(summary = "更新OTP發送紀錄")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/otp-records/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    UpdateOtpRecordResponse updateOtpRecord(@RequestBody UpdateOtpRecordRequest request);

    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/personal-notification/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreatePersonalNotificationResponse createPersonalNotification(@RequestBody CreatePersonalNotificationRequest request);

    @Operation(summary = "存入遠端資源")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notifies/remoteattachment/add")
    Boolean remoteAttachment(@RequestParam("remoteResourceUrl") String remoteResourceUrl, @RequestParam("localUrl") String localUrl);

    @Operation(summary = "發送Email通知")
    @PostMapping(value = "${aibank.common.api-base-uri:}/v1.0/notifies/email/task/add", produces = { MediaType.APPLICATION_JSON_VALUE })
    EmailNotify sendMail(@RequestBody EmailNotify notify);

}
