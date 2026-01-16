package com.tfb.aibank.biz.user.gateway;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.biz.user.services.communication.model.EB12020007Req;
import com.tfb.aibank.biz.user.services.depositassets.model.EB555692Request;
import com.tfb.aibank.biz.user.services.depositassets.model.InvAssetOverviewRequest;
import com.tfb.aibank.biz.user.services.login.model.ChangeUuidAndPinBlockRequest;
import com.tfb.aibank.biz.user.services.login.model.ExecuteUserLoginRequest;
import com.tfb.aibank.chl.component.userdatacache.model.EB032151Req;
import com.tfb.aibank.chl.component.userdatacache.model.EB552171Req;
import com.tfb.aibank.chl.component.userdatacache.model.HasTrustAcct;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.ChangRecordItemType;
import com.tfb.aibank.common.util.BaNCSUtil;
import com.tfb.aibank.common.util.UserUtils;
import com.tfb.aibank.integration.eai.EAI;
import com.tfb.aibank.integration.eai.EAIChannel;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIFactory;
import com.tfb.aibank.integration.eai.EAIOverviewRequest;
import com.tfb.aibank.integration.eai.EAIOverviewResponse;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.AJW084RQ;
import com.tfb.aibank.integration.eai.msg.AJW084RS;
import com.tfb.aibank.integration.eai.msg.BKDCD002RQ;
import com.tfb.aibank.integration.eai.msg.BKDCD002RS;
import com.tfb.aibank.integration.eai.msg.BPM001RQ;
import com.tfb.aibank.integration.eai.msg.BPM001RS;
import com.tfb.aibank.integration.eai.msg.BPM002RQ;
import com.tfb.aibank.integration.eai.msg.BPM002RS;
import com.tfb.aibank.integration.eai.msg.BPM003RQ;
import com.tfb.aibank.integration.eai.msg.BPM003RS;
import com.tfb.aibank.integration.eai.msg.BPM004RQ;
import com.tfb.aibank.integration.eai.msg.BPM004RS;
import com.tfb.aibank.integration.eai.msg.BPM006RQ;
import com.tfb.aibank.integration.eai.msg.BPM006RS;
import com.tfb.aibank.integration.eai.msg.CE6220RRQ;
import com.tfb.aibank.integration.eai.msg.CE6220RRS;
import com.tfb.aibank.integration.eai.msg.CEW013RRQ;
import com.tfb.aibank.integration.eai.msg.CEW013RRS;
import com.tfb.aibank.integration.eai.msg.CEW301RRQ;
import com.tfb.aibank.integration.eai.msg.CEW301RRS;
import com.tfb.aibank.integration.eai.msg.CEW302RRQ;
import com.tfb.aibank.integration.eai.msg.CEW302RRS;
import com.tfb.aibank.integration.eai.msg.CEW319RRQ;
import com.tfb.aibank.integration.eai.msg.CEW319RRS;
import com.tfb.aibank.integration.eai.msg.CEW323RRQ;
import com.tfb.aibank.integration.eai.msg.CEW323RRS;
import com.tfb.aibank.integration.eai.msg.CEW4661RRQ;
import com.tfb.aibank.integration.eai.msg.CEW4661RRS;
import com.tfb.aibank.integration.eai.msg.CEW466RRQ;
import com.tfb.aibank.integration.eai.msg.CEW466RRS;
import com.tfb.aibank.integration.eai.msg.EB032151RQ;
import com.tfb.aibank.integration.eai.msg.EB032151RS;
import com.tfb.aibank.integration.eai.msg.EB032154RQ;
import com.tfb.aibank.integration.eai.msg.EB032154RS;
import com.tfb.aibank.integration.eai.msg.EB032159RQ;
import com.tfb.aibank.integration.eai.msg.EB032159RS;
import com.tfb.aibank.integration.eai.msg.EB032179RQ;
import com.tfb.aibank.integration.eai.msg.EB032179RS;
import com.tfb.aibank.integration.eai.msg.EB032333RQ;
import com.tfb.aibank.integration.eai.msg.EB032333RS;
import com.tfb.aibank.integration.eai.msg.EB032675RQ;
import com.tfb.aibank.integration.eai.msg.EB032675RS;
import com.tfb.aibank.integration.eai.msg.EB067217RQ;
import com.tfb.aibank.integration.eai.msg.EB067217RS;
import com.tfb.aibank.integration.eai.msg.EB12020006RQ;
import com.tfb.aibank.integration.eai.msg.EB12020006RS;
import com.tfb.aibank.integration.eai.msg.EB12020007RQ;
import com.tfb.aibank.integration.eai.msg.EB12020007RS;
import com.tfb.aibank.integration.eai.msg.EB12020024RQ;
import com.tfb.aibank.integration.eai.msg.EB12020024RS;
import com.tfb.aibank.integration.eai.msg.EB12020061RQ;
import com.tfb.aibank.integration.eai.msg.EB12020061RS;
import com.tfb.aibank.integration.eai.msg.EB172650RQ;
import com.tfb.aibank.integration.eai.msg.EB172650RS;
import com.tfb.aibank.integration.eai.msg.EB552170RQ;
import com.tfb.aibank.integration.eai.msg.EB552170RS;
import com.tfb.aibank.integration.eai.msg.EB552171RQ;
import com.tfb.aibank.integration.eai.msg.EB552171RS;
import com.tfb.aibank.integration.eai.msg.EB552175RQ;
import com.tfb.aibank.integration.eai.msg.EB552175RS;
import com.tfb.aibank.integration.eai.msg.EB552190RQ;
import com.tfb.aibank.integration.eai.msg.EB552190RS;
import com.tfb.aibank.integration.eai.msg.EB555691RQ;
import com.tfb.aibank.integration.eai.msg.EB555691RS;
import com.tfb.aibank.integration.eai.msg.EB555692RQ;
import com.tfb.aibank.integration.eai.msg.EB555692RS;
import com.tfb.aibank.integration.eai.msg.EB5556981RQ;
import com.tfb.aibank.integration.eai.msg.EB5556981RS;
import com.tfb.aibank.integration.eai.msg.EB5556982RQ;
import com.tfb.aibank.integration.eai.msg.EB5556982RS;
import com.tfb.aibank.integration.eai.msg.EB67050RQ;
import com.tfb.aibank.integration.eai.msg.EB67050RS;
import com.tfb.aibank.integration.eai.msg.EB67115RQ;
import com.tfb.aibank.integration.eai.msg.EB67115RS;
import com.tfb.aibank.integration.eai.msg.FC032155RQ;
import com.tfb.aibank.integration.eai.msg.FC032155RS;
import com.tfb.aibank.integration.eai.msg.FEP512161RQ;
import com.tfb.aibank.integration.eai.msg.FEP512161RS;
import com.tfb.aibank.integration.eai.msg.FEP512166RQ;
import com.tfb.aibank.integration.eai.msg.FEP512166RS;
import com.tfb.aibank.integration.eai.msg.GD320140RQ;
import com.tfb.aibank.integration.eai.msg.GD320140RS;
import com.tfb.aibank.integration.eai.msg.MVC110001RQ;
import com.tfb.aibank.integration.eai.msg.MVC110001RS;
import com.tfb.aibank.integration.eai.msg.N8048NRQ;
import com.tfb.aibank.integration.eai.msg.N8048NRS;
import com.tfb.aibank.integration.eai.msg.NF032333RQ;
import com.tfb.aibank.integration.eai.msg.NF032333RS;
import com.tfb.aibank.integration.eai.msg.NJW084RQ;
import com.tfb.aibank.integration.eai.msg.NJW084RS;
import com.tfb.aibank.integration.eai.msg.NMP8YBRQ;
import com.tfb.aibank.integration.eai.msg.NMP8YBRS;
import com.tfb.aibank.integration.eai.msg.NR048NRQ;
import com.tfb.aibank.integration.eai.msg.NR048NRS;
import com.tfb.aibank.integration.eai.msg.RTWEBP01RQ;
import com.tfb.aibank.integration.eai.msg.RTWEBP01RS;
import com.tfb.aibank.integration.eai.msg.RTWEBP02RQ;
import com.tfb.aibank.integration.eai.msg.RTWEBP02RS;
import com.tfb.aibank.integration.eai.msg.SPWEBQ1RQ;
import com.tfb.aibank.integration.eai.msg.SPWEBQ1RS;
import com.tfb.aibank.integration.eai.msg.UK084NRQ;
import com.tfb.aibank.integration.eai.msg.UK084NRS;
import com.tfb.aibank.integration.eai.msg.VN084N1RQ;
import com.tfb.aibank.integration.eai.msg.VN084N1RS;
import com.tfb.aibank.integration.eai.msg.VN084NRQ;
import com.tfb.aibank.integration.eai.msg.VN084NRS;
import com.tfb.aibank.integration.eai.type.EAIHeaderInfo;

import tw.com.ibm.mf.eai.TxBodyRqType;
import tw.com.ibm.mf.eai.TxHeadRqType;
import tw.com.ibm.mf.eb.AJW084SvcRqType;
import tw.com.ibm.mf.eb.BKDCD002SvcRqType;
import tw.com.ibm.mf.eb.BPM001SvcRqType;
import tw.com.ibm.mf.eb.BPM002SvcRqType;
import tw.com.ibm.mf.eb.BPM003SvcRqType;
import tw.com.ibm.mf.eb.BPM004SvcRqType;
import tw.com.ibm.mf.eb.BPM006SvcRqType;
import tw.com.ibm.mf.eb.CE6220RSvcRqType;
import tw.com.ibm.mf.eb.CEW013RSvcRqType;
import tw.com.ibm.mf.eb.CEW301RSvcRqType;
import tw.com.ibm.mf.eb.CEW302RSvcRqType;
import tw.com.ibm.mf.eb.CEW319RSvcRqType;
import tw.com.ibm.mf.eb.CEW323RSvcRqType;
import tw.com.ibm.mf.eb.CEW4661RSvcRqType;
import tw.com.ibm.mf.eb.CEW466RSvcRqType;
import tw.com.ibm.mf.eb.EB032151SvcRqType;
import tw.com.ibm.mf.eb.EB032154SvcRqType;
import tw.com.ibm.mf.eb.EB032159SvcRqType;
import tw.com.ibm.mf.eb.EB032179SvcRqType;
import tw.com.ibm.mf.eb.EB032333SvcRqType;
import tw.com.ibm.mf.eb.EB032675SvcRqType;
import tw.com.ibm.mf.eb.EB067217SvcRqType;
import tw.com.ibm.mf.eb.EB12020006SvcRqType;
import tw.com.ibm.mf.eb.EB12020007SvcRqType;
import tw.com.ibm.mf.eb.EB12020024SvcRqType;
import tw.com.ibm.mf.eb.EB12020061SvcRqType;
import tw.com.ibm.mf.eb.EB172650SvcRqType;
import tw.com.ibm.mf.eb.EB552170SvcRqType;
import tw.com.ibm.mf.eb.EB552171SvcRqType;
import tw.com.ibm.mf.eb.EB552175SvcRqType;
import tw.com.ibm.mf.eb.EB552190SvcRqType;
import tw.com.ibm.mf.eb.EB555691SvcRqType;
import tw.com.ibm.mf.eb.EB555692SvcRqType;
import tw.com.ibm.mf.eb.EB5556981SvcRqType;
import tw.com.ibm.mf.eb.EB5556982SvcRqType;
import tw.com.ibm.mf.eb.EB67050SvcRqType;
import tw.com.ibm.mf.eb.EB67115SvcRqType;
import tw.com.ibm.mf.eb.FC032155SvcRqType;
import tw.com.ibm.mf.eb.FEP512161SvcRqType;
import tw.com.ibm.mf.eb.FEP512166SvcRqType;
import tw.com.ibm.mf.eb.GD320140SvcRqType;
import tw.com.ibm.mf.eb.MVC110001SvcRqType;
import tw.com.ibm.mf.eb.N8048N000ASvcRsType;
import tw.com.ibm.mf.eb.N8048NSvcRqType;
import tw.com.ibm.mf.eb.NF032333SvcRqType;
import tw.com.ibm.mf.eb.NJW084SvcRqType;
import tw.com.ibm.mf.eb.NMP8YBSvcRqType;
import tw.com.ibm.mf.eb.NR048N000ASvcRsType;
import tw.com.ibm.mf.eb.NR048NSvcRqType;
import tw.com.ibm.mf.eb.RTWEBP01SvcRqType;
import tw.com.ibm.mf.eb.RTWEBP02SvcRqType;
import tw.com.ibm.mf.eb.SPWEBQ1SvcRqType;
import tw.com.ibm.mf.eb.UK084NSvcRqType;
import tw.com.ibm.mf.eb.VN084N1SvcRqType;
import tw.com.ibm.mf.eb.VN084NSvcRqType;

// @formatter:off
/**
 * @(#)EsbChannelGateway.java
 * 
 * <p>Description:提供「與電文主機溝通」的類別</p>
 * <p>EsbChannelGateway 作為與電文主機溝通的閘道，上送資訊(Input)和下行資訊(Output)應簡潔單純。應避免在此對資料本身進行任何加工動作。</p>
 * <p>禁止@Autowired其他資源。</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/12/18, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class EsbChannelGateway {

    @Autowired
    private com.tfb.aibank.integration.eai.EAIFactory eaiFactory;

    private static final IBLog logger = IBLog.getLog(EsbChannelGateway.class);

    private static final String HTLID_FOR_BMP = "2004215";

    /**
     * EB555692 網路銀行歸戶資產查詢
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public EB555692RS sendEB555692(EB555692Request request) throws XmlException, EAIException, EAIResponseException {

        EAI<EB555692RQ, EB555692RS> eai = eaiFactory.newInstance(EAIChannel.CBS, EB555692RQ.class, EB555692RS.class);
        eai.getRequest().getHeader().setHTLID(request.getHTLID());
        EB555692SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setFunction(request.getFunction());
        rqBody.setIDNO(request.getCustId());
        rqBody.setUSERNO(request.getNameCode());
        rqBody.setTYPE(request.getType());
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(request.getCustId()));
        return eai.sendAndReceive("EB555692 網路銀行歸戶資產查詢");
    }

    /**
     * 登入用
     * 
     * @param request
     * @param memo
     * @param cipherPass
     * @param encAcnoID
     * @return
     * @throws CryptException
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB5556981RS sendEB5556981(ExecuteUserLoginRequest request, String memo, String cipherPass, String encAcnoID) throws CryptException, XmlException, EAIException, EAIResponseException {
        // 發登入電文
        // 2018/03/22 LiYa 配合CBS調整
        EAI<EB5556981RQ, EB5556981RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB5556981RQ.class, EB5556981RS.class);
        EB5556981SvcRqType rqBody = eai.getRequest().getBody();
        // HTLID 為 2004011
        eai.getRequest().getHeader().setHTLID("2004111");

        // IDNO 先 trim 空白
        // (1) 若為變更使用者代碼與密碼，ITEM_NO上送1
        // (2) 若使用帳密登入，ITEM_NO上送2
        // (3) 若使用快速登入，ITEM_NO上送3
        rqBody.setITEMNO("2"); // 帳密登入，上送2
        rqBody.setIDNO(StringUtils.upperCase(StringUtils.trimToEmptyEx(request.getUid())));
        // 登入時寫死 0001
        rqBody.setNAMECOD("0001");
        rqBody.setUSERID(StringUtils.upperCase(StringUtils.trimToEmptyEx(request.getUuid())));

        rqBody.setPASSWORD(cipherPass);
        rqBody.setNEWUSERID(StringUtils.EMPTY);
        rqBody.setNEWPASSWORD(StringUtils.EMPTY);
        rqBody.setACNOID(encAcnoID);
        rqBody.setREMARK(StringUtils.EMPTY);
        rqBody.setBUSEB5(StringUtils.EMPTY);
        rqBody.setACNOIN(StringUtils.EMPTY);
        // if ("m3".equals(request.getLoginType())) {
        // rqBody.setACNOID(request.getAccountNo());
        // }
        // else {
        // rqBody.setACNOID("");
        // }

        // 手勢登入上行欄位
        if (StringUtils.equals("1", request.getPwType())) {
            // String idNo = StringUtils.rightPad(StringUtils.upperCase(request.getUid()), 16, " ");
            String newPass = StringUtils.EMPTY;
            rqBody.setITEMNO("3");
            rqBody.setPASSWORD(newPass);
            rqBody.setACNOID(StringUtils.EMPTY);
        } // 若是指紋、顏值登入。
        else if ("Y".equals(request.getIsSignatureLogin())) {
            // String idNo = StringUtils.rightPad(StringUtils.upperCase(request.getUid()), 16, " ");
            String newPass = StringUtils.EMPTY;
            rqBody.setITEMNO("3");
            rqBody.setPASSWORD(newPass);
            rqBody.setACNOID(StringUtils.EMPTY);
        }

        // 2018/03/22 LiYa 配合CBS調整
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(StringUtils.upperCase(StringUtils.trimToEmptyEx(request.getUid()))));
        logger.info("EB556981IDTYPE:" + request.getUid());
        logger.info("EB556981IDTYPE:" + BaNCSUtil.getIDTYPE(StringUtils.upperCase(StringUtils.trimToEmptyEx(request.getUid()))));
        EB5556981RS rs = null;

        rs = eai.sendAndReceive(memo);

        return rs;
    }

    /**
     * for 使用者代號 / 密碼變更
     * 
     * @param request
     * @param memo
     * @param cryptoProxy
     * @param encPwd
     * @param encPwdMAC
     * @param encNewPwd
     * @param encNewPwdMAC
     * @return
     * @throws Exception
     */
    public EB5556981RS sendEB5556981(ChangeUuidAndPinBlockRequest request, String memo, String encPwd, String encPwdMAC, String encNewPwd, String encNewPwdMAC) throws XmlException, EAIException, EAIResponseException {
        EAI<EB5556981RQ, EB5556981RS> eai = eaiFactory.newInstance(EAIChannel.CBS, EB5556981RQ.class, EB5556981RS.class);
        EB5556981RQ rq = eai.getRequest();
        eai.getRequest().getHeader().setHTLID("2004111");

        String nameCode = StringUtils.isNoneBlank(request.getNameCode()) ? request.getNameCode() : "0001";

        String newUserId = request.getNewUserId();

        rq.getBody().setIDNO(request.getCustId());
        rq.getBody().setNAMECOD(nameCode);
        rq.getBody().setITEMNO("1"); // 變便使用者代碼與密碼
        rq.getBody().setUSERID(request.getUserId());
        rq.getBody().setPASSWORD(encPwd);
        rq.getBody().setNEWUSERID(newUserId);
        rq.getBody().setACNOID(encPwdMAC);
        rq.getBody().setACNOIN(StringUtils.EMPTY);
        rq.getBody().setIDTYPE(BaNCSUtil.getIDTYPE(request.getCustId()));

        /** 變更代碼時，EB5556981_Rq.BUS_EB_5 = N */
        if (ChangRecordItemType.isChangeUserid(request.getChangeItem()) || ChangRecordItemType.isChangeUseridAndPinBlock(request.getChangeItem())) {
            rq.getBody().setBUSEB5("N");
        }
        else {
            rq.getBody().setBUSEB5(StringUtils.EMPTY);
        }

        /**
         * 僅有變更使用者代碼時，EB5556981_Rq.NEWPASS_WORD = 不帶值 EB5556981_Rq.REMARK = 不帶值
         */
        if (ChangRecordItemType.isChangeUserid(request.getChangeItem())) {
            rq.getBody().setNEWPASSWORD("");
            rq.getBody().setREMARK("");
        }
        else {
            rq.getBody().setNEWPASSWORD(encNewPwd);
            rq.getBody().setREMARK(encNewPwdMAC);
        }

        EB5556981RS rs = null;
        try {
            rs = eai.sendAndReceive("這是EB5556981 變更電文");
        }
        catch (EAIResponseException e) {
            logger.error("", e);
            throw e;
        }

        return rs;

    }

    /**
     * 發送是否已申請行銀電文
     * 
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     * @throws Exception
     * @throws ActionException
     */
    public EB552190RS sendEB552190(String uid, String uuid, String pinBlock) throws XmlException, EAIException, EAIResponseException {

        EAI<EB552190RQ, EB552190RS> eai = eaiFactory.newInstance(EAIChannel.CBS, EB552190RQ.class, EB552190RS.class);
        EB552190RQ request = eai.getRequest();
        request.getHeader().setHFUNC("1");
        request.getHeader().setHTLID("2004111");

        EB552190SvcRqType rqBody = request.getBody();
        rqBody.setITEMNO("Z"); // 選項
        rqBody.setIDNO(uid); // 身分證字號
        rqBody.setNAMECOD(StringUtils.EMPTY); // 戶名代碼
        rqBody.setUSERID(uuid); // 使用者代碼
        // rqBody.setPASS-WORD(CryptoProxy.desEncryption(HSMEnvType.FOR_MOBILE_MAC, StringUtils.rightPad(pass-word, 16, " "))); // 使用者密碼, 【使用者密碼靠左右補空白到16位】HSM加密
        rqBody.setBUSEB5("Y"); // 確認判斷
        rqBody.setBUSET5(StringUtils.EMPTY); // 上次狀態別
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(uid)); // 證件類別

        rqBody.setPASSWORD(pinBlock);
        return eai.sendAndReceive("是否已申請行動網銀");

    }

    public EB032154RS sendEB032154(String uid) throws XmlException, EAIException, EAIResponseException {

        String id = StringUtils.substring(uid, 0, 10);
        String idDup = StringUtils.substring(uid, 10, 11);
        if (StringUtils.isBlank(idDup)) {
            idDup = "0";
        }

        EAI<EB032154RQ, EB032154RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB032154RQ.class, EB032154RS.class);

        eai.getRequest().getHeader().setHTLID("8064111"); // @mobile
        EB032154SvcRqType rqBody = eai.getRequest().getBody();

        if (!StringUtils.equals("0", idDup)) {
            id = id + idDup;
        }
        rqBody.setCUSTNO(id);
        // 201803 ENOCH CBS調整
        rqBody.setFUNC("1"); // 1:查詢 2:維護
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(id));

        return eai.sendAndReceive("取得使用者行動電話");
    }

    /**
     * 取得使用者Email
     * 
     * @param uid
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public EB032159RS sendEB032159(String uid) throws XmlException, EAIException, EAIResponseException {
        return sendEB032159ByFunc(uid, "0");
    }

    /**
     * EB032159 電子對帳單查詢詳細狀態
     *
     * @param uid
     * @param func
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public EB032159RS sendEB032159ByFunc(String uid, String func) throws XmlException, EAIException, EAIResponseException {

        String id = StringUtils.substring(uid, 0, 10);
        String idDup = StringUtils.substring(uid, 10, 11);
        if (StringUtils.isBlank(idDup)) {
            idDup = "0";
        }

        EAI<EB032159RQ, EB032159RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB032159RQ.class, EB032159RS.class);
        eai.getRequest().getHeader().setHTLID("8064215"); // @mobile
        EB032159SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setFUNC(func);

        if (!StringUtils.equals("0", idDup)) {
            id = id + idDup;
        }
        rqBody.setCUSTNO(id);
        rqBody.setENQIND("N");
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(id));

        return eai.sendAndReceive("取得使用者電子對帳單設定");
    }

    /**
     * EB067217 取得ID項下所有的誤別碼資訊
     */
    public EB067217RS sendEB067217(String custId, String idType) throws XmlException, EAIException, EAIResponseException {
        EAI<EB067217RQ, EB067217RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB067217RQ.class, EB067217RS.class);
        eai.getRequest().getHeader().setHTLID("2004111");
        EB067217SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setIDNO(custId);
        rqBody.setIDTYPE(idType);
        return eai.sendAndReceive("EB67050 查詢ID項下Email");
    }

    /**
     * GD320140 查詢ID項下Email
     */
    public EB67050RS sendEB67050(String custId, String idType) throws XmlException, EAIException, EAIResponseException {
        EAI<EB67050RQ, EB67050RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB67050RQ.class, EB67050RS.class);
        eai.getRequest().getHeader().setHTLID("2004111");
        EB67050SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setCUSTNO(custId);
        rqBody.setIDTYPE(idType);
        return eai.sendAndReceive("EB67050 查詢ID項下Email");
    }

    public UK084NRS sendUK084N(InvAssetOverviewRequest rq) throws XmlException, EAIException, EAIResponseException {
        EAI<UK084NRQ, UK084NRS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, UK084NRQ.class, UK084NRS.class);
        eai.getRequest().getHeader().setHTLID("");
        UK084NSvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setAcctId16(rq.getCustIxd());
        rqBody.setCustPswd32(rq.getCurAcctId());
        rqBody.setCustId(rq.getCustIxd());
        rqBody.setCurAcctId(rq.getCurAcctId());
        rqBody.setCurAcctName(rq.getCurAcctName());
        UK084NRS rs = eai.sendAndReceive("UK084N 網路銀行歸戶資產查詢");
        return rs;
    }

    /**
     * 發查N8048N(股票定期定額歷史交易明細)電文 <br/>
     * 我的投資使用，不做recursive
     */
    public boolean sendN8048NOverview(HasTrustAcct rq) throws XmlException, EAIException, EAIResponseException {
        EAI<N8048NRQ, N8048NRS> eai = eaiFactory.newInstance(EAIChannel.AS400, N8048NRQ.class, N8048NRS.class);

        if (StringUtils.isNotBlank(rq.getHDRVQ1())) {
            eai.getRequest().getHeader().setHDRVQ1(rq.getHDRVQ1());
        }

        if (StringUtils.isNotBlank(rq.getHTLID())) {
            eai.getRequest().getHeader().setHTLID(rq.getHTLID());
        }

        if (StringUtils.isNotBlank(rq.getHFMTID())) {
            eai.getRequest().getHeader().setHFMTID(rq.getHFMTID());
        }

        N8048NSvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setCustId(rq.getCustIxd());
        rqBody.setCurAcctName(rq.getCurAcctName());
        rqBody.setTrustAcct("A");
        rqBody.setCurAcc(StringUtils.defaultString(rq.getCurAcc()));

        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -3);
        rqBody.setStartDt(c);
        rqBody.setEndDt(Calendar.getInstance());

        N8048NRS rs = eai.sendAndReceiveRaw("N8048N 股票定期定額歷史交易明細-取得信託帳號", null);
        if (rs != null && StringUtils.equals(rs.getHeader().getHERRID(), "0000")) {
            if (StringUtils.equals(rs.getHeader().getHFMTID(), "000A")) {
                N8048N000ASvcRsType changeType = (N8048N000ASvcRsType) rs.getBody().changeType(N8048N000ASvcRsType.type);
                return changeType.getOccur() > 0;
            }
        }
        return false;
    }

    /**
     * 發查NR048N(股票歷史交易明細)電文 <br/>
     * 我的投資使用，不做recursive
     */
    public boolean sendNR048NOverview(HasTrustAcct rq) throws XmlException, EAIException, EAIResponseException {
        EAI<NR048NRQ, NR048NRS> eai = eaiFactory.newInstance(EAIChannel.AS400, NR048NRQ.class, NR048NRS.class);

        if (StringUtils.isNotBlank(rq.getHDRVQ1())) {
            eai.getRequest().getHeader().setHDRVQ1(rq.getHDRVQ1());
        }

        if (StringUtils.isNotBlank(rq.getHTLID())) {
            eai.getRequest().getHeader().setHTLID(rq.getHTLID());
        }

        if (StringUtils.isNotBlank(rq.getHFMTID())) {
            eai.getRequest().getHeader().setHFMTID(rq.getHFMTID());
        }

        NR048NSvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setCustId(rq.getCustIxd());
        rqBody.setCurAcctName(rq.getCurAcctName());
        rqBody.setCurAcc("");
        rqBody.setStartDt("");
        rqBody.setEndDt("");
        rqBody.setTrustAcct("B");

        NR048NRS rs = eai.sendAndReceiveRaw("NR048N 股票歷史交易明細-取得信託帳號", null);
        if (rs != null && StringUtils.equals(rs.getHeader().getHERRID(), "0000")) {
            if (StringUtils.equals(rs.getHeader().getHFMTID(), "000A")) {
                NR048N000ASvcRsType changeType = (NR048N000ASvcRsType) rs.getBody().changeType(NR048N000ASvcRsType.type);
                return changeType.getOccur() > 0;
            }
        }
        return false;
    }

    /**
     * VN084N 基金資產總覽 (DBU)
     */
    public VN084NRS sendVN084N(InvAssetOverviewRequest rq) throws XmlException, EAIException, EAIResponseException {
        EAI<VN084NRQ, VN084NRS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, VN084NRQ.class, VN084NRS.class);
        eai.getRequest().getHeader().setHTLID("");
        VN084NSvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setAcctId16(rq.getAcctId16());
        rqBody.setCustPswd32(rq.getCustPswd32());
        rqBody.setCustId(rq.getCustIxd());
        rqBody.setCurAcctId(rq.getCurAcctId());
        rqBody.setCurAcctName(rq.getCurAcctName());
        VN084NRS rs = eai.sendAndReceive("VN084N 基金資產總覽 (DBU)");
        return rs;
    }

    /**
     * VN084N1 基金資產總覽 (OBU)
     */
    public VN084N1RS sendVN084N1(InvAssetOverviewRequest rq) throws XmlException, EAIException, EAIResponseException {
        EAI<VN084N1RQ, VN084N1RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, VN084N1RQ.class, VN084N1RS.class);
        eai.getRequest().getHeader().setHTLID("");
        VN084N1SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setAcctId16(rq.getAcctId16());
        rqBody.setCustPswd32(rq.getCustPswd32());
        rqBody.setCustId(rq.getCustIxd());
        rqBody.setCurAcctId(rq.getCurAcctId());
        rqBody.setCurAcctName(rq.getCurAcctName());
        VN084N1RS rs = eai.sendAndReceive("VN084N「1」 基金資產總覽 (OBU)");
        return rs;
    }

    /**
     * NJW084 債券資產總覽 (DBU)
     */
    public NJW084RS sendNJW084(InvAssetOverviewRequest rq) throws XmlException, EAIException, EAIResponseException {
        EAI<NJW084RQ, NJW084RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, NJW084RQ.class, NJW084RS.class);
        eai.getRequest().getHeader().setHTLID("");
        NJW084SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setAcctId16(rq.getAcctId16());
        rqBody.setCustPswd32(rq.getCustPswd32());
        rqBody.setCustId(rq.getCustIxd());
        rqBody.setCurAcctId(rq.getCurAcctId());
        rqBody.setCurAcctName(rq.getCurAcctName());
        NJW084RS rs = eai.sendAndReceive("NJW084 債券資產總覽 (DBU)");
        return rs;
    }

    /**
     * AJW084 債券OBU資產總覽
     */
    public AJW084RS sendAJW084(InvAssetOverviewRequest rq) throws XmlException, EAIException, EAIResponseException {
        EAI<AJW084RQ, AJW084RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, AJW084RQ.class, AJW084RS.class);
        eai.getRequest().getHeader().setHTLID("");
        AJW084SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setAcctId16(rq.getAcctId16());
        rqBody.setCustPswd32(rq.getCustPswd32());
        rqBody.setCustId(rq.getCustIxd());
        rqBody.setCurAcctId(rq.getCurAcctId());
        rqBody.setCurAcctName(rq.getCurAcctName());
        AJW084RS rs = eai.sendAndReceive("NJW084 債券OBU資產總覽");
        return rs;
    }

    /**
     * NMP8YB 奈米投電文
     */
    public NMP8YBRS sendNMP8YB(InvAssetOverviewRequest rq) throws XmlException, EAIException, EAIResponseException {
        EAI<NMP8YBRQ, NMP8YBRS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, NMP8YBRQ.class, NMP8YBRS.class);
        eai.getRequest().getHeader().setHTLID("028WEB"); // #2352 改成 028WEB
        NMP8YBSvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setCustId(rq.getCustIxd());
        rqBody.setCurAcctName(rq.getCurAcctNameNano());
        rqBody.setFUNC(rq.getFuncNano());
        NMP8YBRS rs = eai.sendAndReceive("NMP8YB 奈米投電文");
        return rs;
    }

    /**
     * SPWEBQ1 組合式商品資產總覽(AS400 DCI+SI)
     */
    public SPWEBQ1RS sendSPWEBQ1(InvAssetOverviewRequest rq) throws XmlException, EAIException, EAIResponseException {
        EAI<SPWEBQ1RQ, SPWEBQ1RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, SPWEBQ1RQ.class, SPWEBQ1RS.class);
        eai.getRequest().getHeader().setHTLID("");
        SPWEBQ1SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setAcctId16(rq.getCustIxd());
        rqBody.setCustPswd32(rq.getCurAcctId());
        rqBody.setCustId(rq.getCustIxd());
        rqBody.setCurAcctId(rq.getCurAcctId());
        rqBody.setCurAcctName(rq.getCurAcctName());
        SPWEBQ1RS rs = eai.sendAndReceive("SPWEBQ1RS 組合式商品資產總覽(AS400 DCI+SI)");
        return rs;
    }

    /**
     * BKDCD002 組合式商品資產總覽(DCI)
     */
    public BKDCD002RS sendBKDCD002(InvAssetOverviewRequest rq) throws XmlException, EAIException, EAIResponseException {
        EAI<BKDCD002RQ, BKDCD002RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, BKDCD002RQ.class, BKDCD002RS.class);
        eai.getRequest().getHeader().setHTLID("");
        BKDCD002SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setCUSTID(rq.getCustIxd());
        rqBody.setTradeStatus(rq.getTradeStatus());
        BKDCD002RS rs = eai.sendAndReceive("BKDCD002 組合式商品資產總覽(DCI)");
        return rs;
    }

    /**
     * 網路銀行轉出帳號建檔及維護
     * 
     * @param req
     * @return
     * @throws Exception
     */
    public EB552171RS sendEB552171(String custId, String userId, EB552171Req req) throws XmlException, EAIException, EAIResponseException {

        EAI<EB552171RQ, EB552171RS> eai = eaiFactory.newInstance(EAIChannel.CBS, EB552171RQ.class, EB552171RS.class);

        if (StringUtils.isNotEmpty(req.getHTLID())) {
            eai.getRequest().getHeader().setHTLID(req.getHTLID());
        }
        else {
            eai.getRequest().getHeader().setHTLID("2004388");
        }

        EB552171SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setIDNO(UserUtils.getTrimmedCompanyUidDup(custId, req.getUidDup()));
        rqBody.setUSERID(userId);
        rqBody.setNAMECOD(req.getNameCode());
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));

        return eai.sendAndReceive("網路銀行轉出帳號建檔及維護");
    }

    /**
     * 發送EB552170，取得OTP申請狀態
     * 
     * @param custId
     * @param dup
     * @param userId
     * @param nameCode
     * @return
     * @throws Exception
     */
    public EB552170RS sendEB552170ForRetrieveUserOTPStatus(String custId, String dup, String userId, String nameCode) throws XmlException, EAIException, EAIResponseException {

        EAI<EB552170RQ, EB552170RS> eb552170Adaptor = eaiFactory.newInstance(EAIChannel.InternetBank, EB552170RQ.class, EB552170RS.class);
        EB552170RQ eb552170rq = eb552170Adaptor.getRequest();
        eb552170rq.getHeader().setHTLID("2004111");
        EB552170SvcRqType eb552170rqBody = eb552170rq.getBody();
        eb552170rqBody.setITEMNO("0");
        eb552170rqBody.setIDNO(UserUtils.getTrimmedCompanyUidDup(custId, dup));
        eb552170rqBody.setNAMECOD(nameCode);
        eb552170rqBody.setUSERID(userId);
        eb552170rqBody.setUSERIDLEVEL("1");
        eb552170rqBody.setCFPBrhCode("00200");
        eb552170rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));

        return eb552170Adaptor.sendAndReceive("確認是否已申請OTP");
    }

    /**
     * GD320140 黃金
     */
    public GD320140RS sendGD320140(InvAssetOverviewRequest rq) throws XmlException, EAIException, EAIResponseException {
        EAI<GD320140RQ, GD320140RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, GD320140RQ.class, GD320140RS.class);
        eai.getRequest().getHeader().setHTLID("2004115"); // #2352 改成 2004115
        GD320140SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setIDNO(rq.getGoldIdNo());
        rqBody.setNAMECOD(rq.getGoldNameCod());
        rqBody.setFUNCCOD(rq.getGoldFuncCod());
        return eai.sendAndReceive("GD320140 黃金");
    }

    /**
     * 客戶分群跨行手續費優惠查詢
     * 
     * @param custId
     * @return
     * @throws Exception
     */
    public FEP512166RS sendFEP512166(String custId) throws XmlException, EAIException, EAIResponseException {

        EAI<FEP512166RQ, FEP512166RS> eai = eaiFactory.newInstance(EAIChannel.CBS, FEP512166RQ.class, FEP512166RS.class);

        eai.getRequest().getHeader().setHTLID("2004111");

        FEP512166SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setIDNo(custId);

        return eai.sendAndReceive("客戶分群跨行手續費優惠查詢(FEP512166)");
    }

    /**
     * 自動化手續費優惠查詢
     * 
     * @param acctId
     *            帳號
     * @return
     * @throws Exception
     */
    public FEP512161RS sendFEP512161(String acctId) throws XmlException, EAIException, EAIResponseException {

        EAI<FEP512161RQ, FEP512161RS> eai = eaiFactory.newInstance(EAIChannel.CBS, FEP512161RQ.class, FEP512161RS.class);

        eai.getRequest().getHeader().setHTLID("8064111");

        FEP512161SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setTxnSource("N");
        rqBody.setHeadFunCode("1");
        rqBody.setHeadUUID(StringUtils.EMPTY);
        rqBody.setActNo(StringUtils.right(acctId, 14)); // 取14碼

        return eai.sendAndReceive("自動化手續費優惠查詢(FEP512161)");
    }

    /**
     * CEW323R 信用卡行銀推播訊息通知設定
     * 
     * @param custId
     * @param func:功能碼
     *            I查詢，J申請，K取消
     * @param type:類別碼
     * @return
     */
    public CEW323RRS sendCEW323R(String custId, String func, String type) throws XmlException, EAIException, EAIResponseException {
        EAI<CEW323RRQ, CEW323RRS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, CEW323RRQ.class, CEW323RRS.class);
        CEW323RSvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setVQFUNC(func);
        rqBody.setVQHDID(custId);
        rqBody.setVQTYPE(type);

        return eai.sendAndReceive("取得信用卡行銀推播訊息通知設定(CEW323R)");
    }

    /**
     * EB552175 存款帳務推播訊息通知設定
     * 
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public EB552175RS sendEB552175(String custId, String nameCode, String itemNo) throws XmlException, EAIException, EAIResponseException {
        EAI<EB552175RQ, EB552175RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB552175RQ.class, EB552175RS.class);

        eai.getRequest().getHeader().setHTLID(EAIHeaderInfo.EB552175.getHTLID());
        EB552175SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setITEMNO(itemNo);
        rqBody.setIDNO(custId);
        rqBody.setNAMECOD(nameCode);
        rqBody.setTYPE("A");
        rqBody.setREQDATE01(DateFormatUtils.SIMPLE_DATE_FORMAT.format(new Date()));
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));
        rqBody.setFORMID("DEP90002");

        return eai.sendAndReceive(EAIHeaderInfo.EB552175.getMemo());
    }

    /**
     * 檢查信用卡狀態
     * 
     * @return
     */
    public CEW301RRS sendCEW301R(String uid) throws XmlException, EAIException, EAIResponseException {
        EAI<CEW301RRQ, CEW301RRS> eaiAdaptor = eaiFactory.newInstance(EAIChannel.AS400, CEW301RRQ.class, CEW301RRS.class);
        CEW301RSvcRqType rq = eaiAdaptor.getRequest().getBody();
        rq.setID(uid);

        CEW301RRS rs = eaiAdaptor.sendAndReceive("登入檢查信用卡狀態");
        return rs;
    }

    /**
     * 檢查信用卡狀態
     * 
     * @return
     */
    public CEW302RRS sendCEW302R(String uid) throws XmlException, EAIException, EAIResponseException {
        EAI<CEW302RRQ, CEW302RRS> eaiAdaptor = eaiFactory.newInstance(EAIChannel.AS400, CEW302RRQ.class, CEW302RRS.class);
        CEW302RSvcRqType rq = eaiAdaptor.getRequest().getBody();
        rq.setID(uid);

        CEW302RRS rs = eaiAdaptor.sendAndReceive("登入檢查信用卡狀態");
        return rs;
    }

    public EAIOverviewResponse<?, ?> sendBPM001Overview(String cusid, String cusno) throws XmlException, EAIException, EAIResponseException {
        EAI<BPM001RQ, BPM001RS> eaiAdaptor = eaiFactory.newOverviewInstance(EAIChannel.CBS, BPM001RQ.class, BPM001RS.class);
        EAIOverviewRequest<? extends TxBodyRqType> request = eaiAdaptor.getRequest();
        request.getHeader().setHTLID(HTLID_FOR_BMP);
        BPM001SvcRqType rq = (BPM001SvcRqType) request.getBody();
        rq.setCUSID(cusid);
        rq.setCUSNO(cusno);
        return eaiAdaptor.sendAndReceiveOverview("總覽電文BPM001");
    }

    public EAIOverviewResponse<?, ?> sendBPM002Overview(String cusid, String cusno) throws XmlException, EAIException, EAIResponseException {
        EAI<BPM002RQ, BPM002RS> eaiAdaptor = eaiFactory.newOverviewInstance(EAIChannel.CBS, BPM002RQ.class, BPM002RS.class);
        EAIOverviewRequest<? extends TxBodyRqType> request = eaiAdaptor.getRequest();
        request.getHeader().setHTLID(HTLID_FOR_BMP);
        BPM002SvcRqType rq = (BPM002SvcRqType) request.getBody();
        rq.setCUSID(cusid);
        rq.setCUSNO(cusno);
        return eaiAdaptor.sendAndReceiveOverview("總覽電文BPM002");
    }

    public EAIOverviewResponse<?, ?> sendBPM003Overview(String cusid, String cusno) throws XmlException, EAIException, EAIResponseException {
        EAI<BPM003RQ, BPM003RS> eaiAdaptor = eaiFactory.newOverviewInstance(EAIChannel.CBS, BPM003RQ.class, BPM003RS.class);
        EAIOverviewRequest<? extends TxBodyRqType> request = eaiAdaptor.getRequest();
        request.getHeader().setHTLID(HTLID_FOR_BMP);
        BPM003SvcRqType rq = (BPM003SvcRqType) request.getBody();
        rq.setCUSID(cusid);
        rq.setCUSNO(cusno);
        return eaiAdaptor.sendAndReceiveOverview("總覽電文BPM003");
    }

    public EAIOverviewResponse<?, ?> sendBPM004Overview(String cusid, String cusno) throws XmlException, EAIException, EAIResponseException {
        EAI<BPM004RQ, BPM004RS> eaiAdaptor = eaiFactory.newOverviewInstance(EAIChannel.CBS, BPM004RQ.class, BPM004RS.class);
        EAIOverviewRequest<? extends TxBodyRqType> request = eaiAdaptor.getRequest();
        request.getHeader().setHTLID(HTLID_FOR_BMP);
        BPM004SvcRqType rq = (BPM004SvcRqType) request.getBody();
        rq.setCUSID(cusid);
        rq.setCUSNO(cusno);
        return eaiAdaptor.sendAndReceiveOverview("總覽電文BPM004");
    }

    public EAIOverviewResponse<?, ?> sendBPM006Overview(String custId, String nameCod) throws XmlException, EAIException, EAIResponseException {
        EAI<BPM006RQ, BPM006RS> eaiAdaptor = eaiFactory.newOverviewInstance(EAIChannel.AS400, BPM006RQ.class, BPM006RS.class);
        EAIOverviewRequest<? extends TxBodyRqType> request = eaiAdaptor.getRequest();
        request.getHeader().setHTLID(HTLID_FOR_BMP);
        BPM006SvcRqType rq = (BPM006SvcRqType) request.getBody();
        rq.setCUSID(custId);
        rq.setCUSNO(nameCod);
        return eaiAdaptor.sendAndReceiveOverview("投資分析明細BPM006");
    }

    /**
     * 取得存款帳號所屬分行代碼
     */
    public NF032333RS sendNF032333(String acctNo) throws XmlException, EAIException, EAIResponseException {
        EAI<NF032333RQ, NF032333RS> eai = eaiFactory.newInstance(EAIChannel.CBS, NF032333RQ.class, NF032333RS.class);
        eai.getRequest().getHeader().setHTLID(EAIHeaderInfo.NF032333.getHTLID()); // #8694 改成 2004133
        NF032333SvcRqType requestBody = eai.getRequest().getBody();
        requestBody.setACCNO(StringUtils.leftPad(acctNo, 16, '0'));

        return eai.sendAndReceive(EAIHeaderInfo.NF032333.getMemo());
    }

    /**
     * 取得本行客戶註記(FC032155)
     * 
     * @param custId
     * @return
     * @throws Exception
     */
    public List<FC032155RS> sendFC032155(String custId) throws XmlException, EAIException, EAIResponseException {
        EAI<FC032155RQ, FC032155RS> eai = eaiFactory.newInstance(EAIChannel.CBS, FC032155RQ.class, FC032155RS.class);
        eai.getRequest().getHeader().setHTLID("2004111"); // #2352 改成 2004111
        FC032155SvcRqType requestBody = eai.getRequest().getBody();
        requestBody.setFUNC("0");
        requestBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));
        requestBody.setCUSTNO(StringUtils.left(custId, 10));
        return eai.sendAndReceiveForConversationTxBody(null, "取得本行客戶註記(FC032155)");
    }

    /**
     * 發送EB552170，取得單一使用者代碼
     * 
     * @param custId
     * @param dup
     * @return
     * @throws Exception
     */
    public EB552170RS sendEB552170ForSingleUser(String custId, String dup) throws XmlException, EAIException, EAIResponseException {

        EAI<EB552170RQ, EB552170RS> eb552170Adaptor = eaiFactory.newInstance(EAIChannel.InternetBank, EB552170RQ.class, EB552170RS.class);
        EB552170RQ eb552170rq = eb552170Adaptor.getRequest();
        eb552170rq.getHeader().setHTLID("2004111");
        EB552170SvcRqType eb552170rqBody = eb552170rq.getBody();
        eb552170rqBody.setITEMNO("K");
        eb552170rqBody.setIDNO(UserUtils.getTrimmedCompanyUidDup(custId, dup));
        eb552170rqBody.setUSERIDLEVEL("1");
        eb552170rqBody.setCFPBrhCode("00200");
        eb552170rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));

        return eb552170Adaptor.sendAndReceive("取得單一使用者代碼(EB552170)");
    }

    /**
     * 發送EB032179
     * 
     * @param funcCod
     * @param custId
     * @param itemCod
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB032179RS sendEB032179(String funcCod, String custId, String itemCod) throws XmlException, EAIException, EAIResponseException {
        EAI<EB032179RQ, EB032179RS> eai = eaiFactory.newInstance(EAIChannel.EB, EB032179RQ.class, EB032179RS.class);
        EB032179RQ eb032179Rq = eai.getRequest();
        eb032179Rq.getHeader().setHTLID(EAIHeaderInfo.EB032179.getHTLID());
        EB032179SvcRqType eb032179rqBody = eb032179Rq.getBody();
        eb032179rqBody.setFUNCCOD(funcCod);
        eb032179rqBody.setCUSTNO(custId);
        eb032179rqBody.setITEMCOD(itemCod);
        eb032179rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));
        return eai.sendAndReceive(EAIHeaderInfo.EB032179.getMemo());
    }

    /**
     * 檢核單一戶名(EB5556982)
     * 
     * @param custId
     * @param userId
     * @param nameCode
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB5556982RS sendEB5556982(String custId, String userId, String nameCode) throws XmlException, EAIException, EAIResponseException {
        EAI<EB5556982RQ, EB5556982RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB5556982RQ.class, EB5556982RS.class);
        eai.getRequest().getHeader().setTXMODE("N");
        eai.getRequest().getHeader().setHTLID("2004111");
        EB5556982SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setIDNO(custId);
        rqBody.setNAMECOD(nameCode);
        rqBody.setITEMNO("1");
        rqBody.setUSERID(userId);
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));
        return eai.sendAndReceive("檢核單一戶名(EB5556982)");
    }

    /**
     * 發送EB032333
     * 
     * @param accNo
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB032333RS sendEB032333(String accNo) throws XmlException, EAIException, EAIResponseException {
        EAI<EB032333RQ, EB032333RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB032333RQ.class, EB032333RS.class);
        EB032333SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setACCNO(accNo);
        return eai.sendAndReceive("發送EB032333");
    }

    /**
     * 申辦信用卡客戶資料查詢
     * 
     * @param custId
     *            身份證字號
     * @return
     * @throws Exception
     */
    public CEW013RRS sendCEW013R(String custId) throws XmlException, EAIException, EAIResponseException {

        EAI<CEW013RRQ, CEW013RRS> eai = eaiFactory.newInstance(EAIChannel.AS400, CEW013RRQ.class, CEW013RRS.class);

        eai.getRequest().getHeader().setHTLID("028WEB");

        CEW013RSvcRqType rqBody = eai.getRequest().getBody();

        rqBody.setID(custId);

        return eai.sendAndReceive("申辦信用卡客戶資料查詢(CEW013R)");
    }

    /**
     * Email資料重複判斷 發送EB12020024
     * 
     * @param accNo
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public List<EB12020024RS> sendEB12020024(String custId, String email, String funcCod, String func) throws XmlException, EAIException, EAIResponseException {
        EAI<EB12020024RQ, EB12020024RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB12020024RQ.class, EB12020024RS.class);
        TxHeadRqType header = eai.getRequest().getHeader();
        header.setHTLID("2004115");
        EB12020024SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setFUNCCOD(funcCod);
        rqBody.setFUNC(func);
        rqBody.setIDNO(custId);
        rqBody.setEMAILADDR(email);
        return eai.sendAndReceiveForConversationTxBody(null, "Email資料重複判斷(EB12020024)");
    }

    /**
     * EMAIL驗證平台電文 發送MVC110001
     * 
     * @param custId
     * @param email
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public MVC110001RS sendMVC110001(String custId, String email, String oldCustEmail, String reasonCode, String reason) throws XmlException, EAIException, EAIResponseException {
        EAI<MVC110001RQ, MVC110001RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, MVC110001RQ.class, MVC110001RS.class);
        eai.getRequest().getHeader().setHTLID("028WEB"); // #2352 改成 028WEB
        MVC110001SvcRqType rqBody = eai.getRequest().getBody();
        TxHeadRqType rqHeader = eai.getRequest().getHeader();
        rqHeader.setHWSID("WMSR");
        rqHeader.setHSTANO("3426031");
        rqHeader.setHTXTID("MVC110001");
        rqBody.setUUID(UUID.randomUUID().toString().replaceAll("-", ""));
        rqBody.setBRANCH("00200");
        rqBody.setTELLERNO("2004215");
        rqBody.setTXCODE("110001");
        rqBody.setCHNL("ON");
        rqBody.setSUBCHNL("01");
        rqBody.setCUSTID(custId);
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));
        if (StringUtils.isNotBlank(oldCustEmail))
            rqBody.setPREVEMAILADDR(oldCustEmail);
        rqBody.setAFTEREMAILADDR(email);
        if (StringUtils.isNotBlank(reasonCode))
            rqBody.setREASON(reasonCode);
        if (StringUtils.isNotBlank(reason))
            rqBody.setREMARK(reason);
        // #21938 N改Y
        // N : 會發送給客戶email驗證信，客戶要點選確認後，才會將資料更新到email驗證平台，報表也才會顯示更新後的內容
        // Y : 不會發送給客戶email驗證信，但透過電文上送至email驗證平台的資料 「會」 進行更新；網銀/舊行銀為此方式
        rqBody.setONOFFLINE("Y");
        return eai.sendAndReceive("EMAIL驗證平台電文(MVC110001)");
    }

    /**
     * 變更客戶基本資料 發送EB12020006
     * 
     * @param custId
     * @param email
     * @param nameCode
     * @param funcCod
     * @param func
     * @param mobileNo
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB12020006RS sendEB12020006(String custId, String email, String nameCode, String funcCod, String func, String mobileNo) throws XmlException, EAIException, EAIResponseException {
        EAI<EB12020006RQ, EB12020006RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB12020006RQ.class, EB12020006RS.class);
        eai.getRequest().getHeader().setHTLID("2004215");
        EB12020006SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setFUNCCOD(funcCod);
        rqBody.setFUNC(func);
        rqBody.setIDNO(custId);
        rqBody.setNAMECOD(nameCode);
        rqBody.setEMAIL(email);
        if (StringUtils.isNotBlank(mobileNo))
            rqBody.setCELL(mobileNo);
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));
        return eai.sendAndReceive("變更客戶基本資料(EB12020006)");
    }

    /**
     * 變更客戶基本資料 發送EB12020061
     *
     * @param custId
     * @param email
     * @param nameCode
     * @param funcCod
     * @param func
     * @param mobileNo
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB12020061RS sendEB12020061(String custId, String email, String nameCode, String funcCod, String func, String mobileNo) throws XmlException, EAIException, EAIResponseException {
        EAI<EB12020061RQ, EB12020061RS> eai = eaiFactory.newInstance(EAIChannel.InternetBank, EB12020061RQ.class, EB12020061RS.class);
        eai.getRequest().getHeader().setHTLID("2004215");
        EB12020061SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setFUNCCOD(funcCod);
        rqBody.setFUNC(func);
        rqBody.setIDNO(custId);
        rqBody.setNAMECOD(nameCode);
        rqBody.setFILLER("");
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));
        rqBody.setEMAIL(email);
        rqBody.setCELL(mobileNo);
        rqBody.setEmailuncflag(" ");
        rqBody.setCelluncflag(mobileNo);
        return eai.sendAndReceive("變更客戶基本資料(EB12020061)");
    }

    /**
     * 發送EB555691確認是否為虛擬帳號
     * 
     * @param custId
     * @param uidDup
     * @param userId
     * @param nameCode
     * @param transOut
     * @param transIn
     * @return
     * @throws Exception
     */
    public EB555691RS sendEB555691ForCheckVirtualAccount(String custId, String uidDup, String userId, String nameCode, String transOut, String transIn) throws XmlException, EAIException, EAIResponseException {
        EAI<EB555691RQ, EB555691RS> eai = eaiFactory.newInstance(EAIChannel.CBS, EB555691RQ.class, EB555691RS.class);
        eai.getRequest().getHeader().setHTLID("2004012");
        EB555691SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setIDNO(UserUtils.getTrimmedCompanyUidDup(custId, uidDup));
        rqBody.setNAMECOD(nameCode);
        rqBody.setUSERID(userId);
        rqBody.setACNOOUT(transIn);
        rqBody.setCKTYPE("77");
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));
        return eai.sendAndReceive("發送EB555691確認是否為虛擬帳號");
    }

    /**
     * 查詢歸戶信用卡清單
     * 
     * @param pin
     * @param type
     * @return
     * @throws Exception
     */
    public List<CE6220RRS> sendCE6220R(String pin, String type) throws XmlException, EAIException, EAIResponseException {

        EAI<CE6220RRQ, CE6220RRS> eai = eaiFactory.newInstance(EAIChannel.AS400, CE6220RRQ.class, CE6220RRS.class);

        CE6220RSvcRqType rqBody = eai.getRequest().getBody();

        rqBody.setPIN(pin);

        rqBody.setTYPE(type);

        return eai.sendAndReceiveForConversationTxBody(null, "查詢歸戶信用卡清單(CE6220R)");
    }

    /**
     * Costco會員自動續約
     * 
     * @param custIxd
     * @param requestCode
     * @param autoRenew
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public CEW466RRS sendCEW466R(String custIxd, String requestCode, String autoRenew) throws XmlException, EAIException, EAIResponseException {
        EAI<CEW466RRQ, CEW466RRS> eai = eaiFactory.newInstance(EAIChannel.AS400, CEW466RRQ.class, CEW466RRS.class);
        CEW466RSvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setRequestType("01");
        rqBody.setRequestCode(requestCode);
        rqBody.setMemberIDNumber(custIxd);
        rqBody.setIsForeigner("");
        rqBody.setApplicationNumber("");
        rqBody.setRequestDate(ConvertUtils.date2Calendar(new Date()));
        rqBody.setMemberNumber("0");
        rqBody.setAutoRenew(autoRenew);

        return eai.sendAndReceive("Costco會員自動續約(CEW466R)");
    }

    /**
     * 好市多會員自動續約通知
     * 
     * @param custIxd
     * @param responseCode
     * @param voabnd
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public CEW4661RRS sendCEW4661R(String custIxd, String responseCode, String voabnd) throws XmlException, EAIException, EAIResponseException {
        EAI<CEW4661RRQ, CEW4661RRS> eai = eaiFactory.newInstance(EAIChannel.AS400, CEW4661RRQ.class, CEW4661RRS.class);
        CEW4661RSvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setRenewid(custIxd);
        rqBody.setRenewflg("Y");
        rqBody.setRenewSrc("3");
        Date dt = new Date();
        rqBody.setRenewdate(ConvertUtils.date2Calendar(dt)); // 民國年月日
        rqBody.setRenewtime(ConvertUtils.date2Calendar(dt));
        rqBody.setRenewrcd(responseCode);
        rqBody.setRenewabn(voabnd);

        return eai.sendAndReceive("好市多會員自動續約通知(CEW4661R)");
    }

    /**
     * CEW319R 信用卡客戶身分驗證
     * 
     * @param type
     * @param custId
     * @param cardNo
     * @param birthDay
     * @param ccv2
     * @param expireDay
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     * @throws ActionException
     */
    public CEW319RRS sendCEW319R(String type, String custId, String cardNo, String birthDay, String ccv2, String expireDay) throws XmlException, EAIException, EAIResponseException, ActionException {

        EAI<CEW319RRQ, CEW319RRS> eai = eaiFactory.newInstance(EAIChannel.AS400, CEW319RRQ.class, CEW319RRS.class);

        eai.getRequest().getHeader().setHTLID("028WEB");

        CEW319RSvcRqType rqBody = eai.getRequest().getBody();

        rqBody.setITYPE(type);
        rqBody.setICRDID(custId);

        if (!StringUtils.isBlank(cardNo)) {
            if (cardNo.length() != 16) {
                logger.error("卡號錯誤 {}", cardNo);
                throw new ActionException(AIBankErrorCode.ARG_NOT_PASS_IN);
            }
            rqBody.setICRDA8(cardNo.substring(0, 8));
            rqBody.setICRDB8(cardNo.substring(8));
        }

        if (!StringUtils.isBlank(birthDay)) {
            rqBody.setIBIRDY(birthDay);
        }

        if (!StringUtils.isBlank(ccv2)) {
            rqBody.setICVV2(ccv2);
        }

        if (!StringUtils.isBlank(expireDay)) {
            rqBody.setIEDMY(expireDay);
        }

        return eai.sendAndReceive(null, "信用卡客戶身分驗證(CEW319R)");
    }

    /**
     * 電文(EB032675)，查詢客戶各類投資資格註記(禁銷、FATCA、弱勢、專業投資人法人)
     * 
     * @param custId
     * @return
     * @throws Exception
     */
    public EB032675RS sendEB032675(String custId) throws XmlException, EAIException, EAIResponseException {

        return sendEB032675("CRRAMS", custId, StringUtils.EMPTY, "1");
    }


    /**
     * 電文(EB032675)，更新客戶各類投資資格註記(禁銷、FATCA、弱勢、專業投資人法人)
     *
     * @param custId
     *            \
     * @param busAddr1
     * @return
     * @throws Exception
     */
    public EB032675RS sendEB032675Modify(String custId, String busAddr1) throws XmlException, EAIException, EAIResponseException {
        return sendEB032675("CRRISK", custId, busAddr1, "2");
    }

    /**
     * 電文(EB032675)，查詢客戶各類投資資格註記(禁銷、FATCA、弱勢、專業投資人法人)
     * 
     * @param iduCod
     *            功能類別
     * @param custId
     *            客戶統編
     * @param busAddr1
     *            功能說明
     * @param FUNC
     *            FUNC
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB032675RS sendEB032675(String iduCod, String custId, String busAddr1, String FUNC) throws XmlException, EAIException, EAIResponseException {

        EAI<EB032675RQ, EB032675RS> eai = eaiFactory.newInstance(EAIChannel.CBS, EB032675RQ.class, EB032675RS.class);

        eai.getRequest().getHeader().setHTLID("2004115");

        EB032675SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setIDUCOD(iduCod);
        rqBody.setCUSTNO(custId);
        rqBody.setBUSADDR1(StringUtils.defaultString(busAddr1));
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));
        rqBody.setFUNC(FUNC);
        return eai.sendAndReceive("查詢客戶各類投資資格註記(禁銷、FATCA、弱勢、專業投資人法人)(EB032675)");
    }

    /**
     * EB032151 客戶基本資料維護
     * 
     * @param request
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     * @throws Exception
     */
    public EB032151RS sendEB032151(EB032151Req request) throws XmlException, EAIException, EAIResponseException {

        EAI<EB032151RQ, EB032151RS> eai = eaiFactory.newInstance(EAIChannel.AS400, EB032151RQ.class, EB032151RS.class);

        if (StringUtils.isNotBlank(request.getHTLID())) {
            eai.getRequest().getHeader().setHTLID(request.getHTLID());
        }
        if (StringUtils.isNotBlank(request.getHFMTID())) {
            eai.getRequest().getHeader().setHFMTID(request.getHFMTID());
        }

        EB032151SvcRqType rqBody = eai.getRequest().getBody();

        rqBody.setFUNC(StringUtils.defaultString(request.getFunc()));
        rqBody.setCUSTNO(StringUtils.defaultString(request.getCustNo()));
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(request.getCustNo()));

        return eai.sendAndReceive("客戶基本資料維護(EB032151)");
    }

    /**
     * 發送儲蓄信託明細
     *
     * @param custId
     *            custId + uidDup
     * @param userId
     * @param nameCode
     * @param beginYm
     *            yyyyMM
     * @param endYm
     *            yyyyMM
     * @param cmpId
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public List<RTWEBP01RS> sendRTWEBP01(String custId, String userId, String nameCode, String beginYm, String endYm, String cmpId) throws XmlException, EAIException, EAIResponseException {
        EAI<RTWEBP01RQ, RTWEBP01RS> eai = eaiFactory.newInstance(EAIChannel.AS400, RTWEBP01RQ.class, RTWEBP01RS.class);
        eai.getRequest().getHeader().setHFMTID("0001");

        String beginRocYmd = DateUtils.getROCDateStr(DateUtils.getDateByDateFormat(beginYm, DateFormatUtils.SIMPLE_DATE_FORMAT_YEAR_MONTH.getPattern()), "");
        String endRocYmd = DateUtils.getROCDateStr(DateUtils.getDateByDateFormat(endYm, DateFormatUtils.SIMPLE_DATE_FORMAT_YEAR_MONTH.getPattern()), "");

        RTWEBP01SvcRqType body = eai.getRequest().getBody();
        body.setCustId(custId);
        body.setCurAcctId(userId);
        body.setCurAcctName(nameCode);
        body.setBeginYM(beginRocYmd.substring(0, beginRocYmd.length() - 2));
        body.setEndYM(endRocYmd.substring(0, endRocYmd.length() - 2));
        body.setCmpID(cmpId);
        body.setCmpNot("2");

        return eai.sendAndReceiveForConversationTxBody("儲蓄信託明細");
    }

    /**
     * 員工持股信託公司查詢
     *
     * @param custId
     *            custId + uidDup
     * @param userId
     * @param nameCode
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public RTWEBP02RS sendRTWEBP02(String custId, String userId, String nameCode) throws XmlException, EAIException, EAIResponseException {
        EAI<RTWEBP02RQ, RTWEBP02RS> eai = eaiFactory.newInstance(EAIChannel.AS400, RTWEBP02RQ.class, RTWEBP02RS.class);
        RTWEBP02RQ req = eai.getRequest();
        req.getHeader().setHFMTID("0001");

        RTWEBP02SvcRqType body = req.getBody();
        body.setCustId(custId);
        body.setCurAcctId(userId);
        body.setCurAcctName(nameCode);
        body.setCmpNot("2");

        return eai.sendAndReceive("員工持股信託公司查詢");
    }

    /**
     * 查詢/變更客戶通訊資料
     * <p>
     * 上送欄位眾多，後續開發時自行填充，傳入的值可以在 Service 類別中賦予
     * </p>
     * 
     * @param request
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB12020007RS sendEB12020007(EB12020007Req request) throws XmlException, EAIException, EAIResponseException {

        EAI<EB12020007RQ, EB12020007RS> eai = eaiFactory.newInstance(EAIChannel.CBS, EB12020007RQ.class, EB12020007RS.class);

        eai.getRequest().getHeader().setHTLID("2004115");
        if (StringUtils.isNotBlank(request.getHTLID())) {
            eai.getRequest().getHeader().setHTLID(request.getHTLID());
        }

        EB12020007SvcRqType rqBody = eai.getRequest().getBody();

        rqBody.setFUNCCOD(request.getFuncCod());
        rqBody.setFUNC(request.getFunc());
        rqBody.setIDNO(request.getIdno());
        rqBody.setNAMECOD(request.getNameCod());
        if (StringUtils.isNotBlank(request.getIdno())) {
            rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(request.getIdno()));
        }
        return eai.sendAndReceive("查詢/變更客戶通訊資料(EB12020007)");
    }

    /**
     * 電文(EB67115)，取得客戶是否具備FATCA排外註記
     * 
     * @param custId
     *            客戶統編
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB67115RS sendEB67115(String custId) throws XmlException, EAIException, EAIResponseException {

        EAI<EB67115RQ, EB67115RS> eai = eaiFactory.newInstance(EAIChannel.CBS, EB67115RQ.class, EB67115RS.class);

        eai.getRequest().getHeader().setHTLID("2004115");

        EB67115SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setCUSTNO(custId);
        rqBody.setIDTYPE(BaNCSUtil.getIDTYPE(custId));
        return eai.sendAndReceive("FATCA排外註記 (EB67115)");
    }

    /**
     * 從帳號反查ID (EB172650)
     * 
     * @param depositAcct
     *            存款帳號
     * @param curCode
     *            幣別
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB172650RS sendEB172650(String depositAcct, String curCode) throws XmlException, EAIException, EAIResponseException {

        EAI<EB172650RQ, EB172650RS> eai = eaiFactory.newInstance(EAIChannel.CBS, EB172650RQ.class, EB172650RS.class);

        eai.getRequest().getHeader().setHTLID(EAIHeaderInfo.EB172650.getHTLID());

        EB172650SvcRqType rqBody = eai.getRequest().getBody();
        rqBody.setFUNCCOD("0"); // 固定上送
        rqBody.setACNOFS(depositAcct);
        rqBody.setCUR1(curCode);
        return eai.sendAndReceive(EAIHeaderInfo.EB172650.getMemo());
    }

}
