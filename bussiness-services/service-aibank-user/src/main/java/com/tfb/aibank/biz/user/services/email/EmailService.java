package com.tfb.aibank.biz.user.services.email;

import java.util.Date;
import java.util.List;

import com.tfb.aibank.integration.eai.msg.EB12020061RS;
import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.ChangeCustDataRecordRepository;
import com.tfb.aibank.biz.user.repository.entities.ChangeCustDataRecordEntity;
import com.tfb.aibank.biz.user.services.email.model.ModifyEmailRequest;
import com.tfb.aibank.biz.user.services.email.model.SaveChangeCustDataRequest;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB12020006RS;
import com.tfb.aibank.integration.eai.msg.EB12020024RS;
import com.tfb.aibank.integration.eai.msg.MVC110001RS;

import tw.com.ibm.mf.eb.EB12020024SvcRsType;

//@formatter:off
/**
* @(#)EmailService.java
* 
* <p>Description:Email相關服務</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EmailService {

    private EsbChannelGateway esbGateway;

    private ChangeCustDataRecordRepository changeCustDataRecordRepository;

    public EmailService(EsbChannelGateway esbChannelGateway, ChangeCustDataRecordRepository changeCustDataRecordRepository) {
        this.esbGateway = esbChannelGateway;
        this.changeCustDataRecordRepository = changeCustDataRecordRepository;
    }

    /**
     * email資料重複判斷
     * 
     * @param userId
     * @param email
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public boolean checkEmailDuplication(String userId, String email) throws XmlException, EAIException, EAIResponseException {
        List<EB12020024RS> datas = esbGateway.sendEB12020024(userId, email, "24", "4");
        boolean isDuplication = false;
        for (EB12020024RS data : datas) {
            EB12020024SvcRsType rsBody = data.getBodyAsType(EB12020024SvcRsType.class);
            if (StringUtils.isNotBlank(rsBody.getID()) && !StringUtils.equals(rsBody.getID(), userId)) {
                isDuplication = true;
                break;
            }
        }
        return isDuplication;
    }

    /**
     * 發送EB12020006 變更客戶基本資料 確認婚姻代碼
     * 
     * @param custId
     * @param email
     * @param nameCode
     * @param funcCod
     * @param func
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public boolean sendEB12020006(String custId, String email, String nameCode, String funcCod, String func) throws XmlException, EAIException, EAIResponseException {
        EB12020006RS rs = esbGateway.sendEB12020006(custId, email, nameCode, funcCod, func, null);
        return StringUtils.equals("2", rs.getBody().getMARRCOD());
    }

    /**
     * 變更email
     * 
     * @param custId
     * @param userId
     * @param email
     * @param oldCustEmail
     * @param request
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public boolean modifyEmailByEB12020006(String custId, String userId, String email, String oldCustEmail, ModifyEmailRequest request) throws XmlException, EAIException, EAIResponseException {
        esbGateway.sendEB12020006(custId, email, request.getNameCode(), request.getFuncCod(), request.getFunc(), request.getMobile());
        return true;
    }

    /**
     * 變更email(EB12020061)
     *
     * @param custId
     * @param userId
     * @param email
     * @param oldCustEmail
     * @param request
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public boolean modifyEmailByEB12020061(String custId, String userId, String email, String oldCustEmail, ModifyEmailRequest request) throws XmlException, EAIException, EAIResponseException {
        esbGateway.sendEB12020061(custId, email, request.getNameCode(), request.getFuncCod(), request.getFunc(), request.getMobile());
        return true;
    }

    /**
     * 變更email
     * 
     * @param custId
     * @param userId
     * @param email
     * @param oldCustEmail
     * @param request
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public boolean modifyEmailByMVC110001(String custId, String userId, String email, String oldCustEmail, ModifyEmailRequest request) throws XmlException, EAIException, EAIResponseException {
        MVC110001RS rs = esbGateway.sendMVC110001(custId, email, oldCustEmail, request.getReasonCode(), request.getReason());
        String code = rs.getBody().getMESSAGECODE();
        return StringUtils.equals("0000", code);
    }

    /**
     * 儲存變更個人資料紀錄
     * 
     * @param userId
     * @param email
     * @param oldMail
     * @param request
     * @return
     * @throws CryptException
     */
    public boolean saveChangeCustDataRecord(String userId, String email, String oldMail, SaveChangeCustDataRequest request) throws CryptException {
        ChangeCustDataRecordEntity entity = new ChangeCustDataRecordEntity();
        Date today = DateUtils.getToday();
        Date now = new Date();
        entity.setCompanyKey(request.getCompanyKey());
        entity.setNameCode(request.getNameCode());
        entity.setUserKey(request.getUserKey());
        entity.setUserId(userId);
        entity.setChangeItem("01");
        entity.setCustEmail(email);
        entity.setTxDate(today);
        entity.setTxStatus(request.getTxStatus());
        entity.setCreateTime(now);
        entity.setTxSource(request.getTxSource());
        entity.setReasonCode(request.getReasonCode());
        entity.setReason(request.getReason());
        entity.setOldCustEmail(oldMail);
        entity.setHostTxTime(request.getHostTxTime());
        entity.setUpdateTime(now);
        entity.setResendMvc110001(request.getResentMVC110001());
        entity.setTxErrorSystemId(request.getTxErrorSystemId());
        entity.setTxErrorCode(request.getTxErrorCode());
        entity.setTxErrorDesc(request.getTxErrorDesc());
        changeCustDataRecordRepository.save(entity);
        return true;
    }

}