/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.services.communication;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlException;

import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.services.communication.model.EB12020007Rep;
import com.tfb.aibank.biz.user.services.communication.model.EB12020007Req;
import com.tfb.aibank.biz.user.services.communication.model.EB12020007Res;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB12020007RS;

import tw.com.ibm.mf.eai.TxRepeatType;
import tw.com.ibm.mf.eb.EB12020007RepeatType;
import tw.com.ibm.mf.eb.EB12020007SvcRsType;

// @formatter:off
/**
 * @(#)CommunicationInfoService.java
 * 
 * <p>Description:處理客戶「通訊資訊」相關服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/11, Edward	Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CommunicationInfoService {

    private EsbChannelGateway esbChannelGateway;

    public CommunicationInfoService(EsbChannelGateway esbChannelGateway) {
        this.esbChannelGateway = esbChannelGateway;
    }

    /**
     * 查詢客戶通訊資料 EB12020007
     * 
     * @param custId
     * @param nameCode
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB12020007Res queryCommunicationInfo(String custId, String nameCode) throws XmlException, EAIException, EAIResponseException {
        EB12020007Req request = new EB12020007Req();
        request.setFuncCod("07");
        request.setFunc("03");
        request.setIdno(custId);
        request.setNameCod(nameCode);
        EB12020007RS rs = esbChannelGateway.sendEB12020007(request);
        EB12020007SvcRsType rsType = rs.getBody();

        EB12020007Res response = convertRs2Response(rsType);
        return response;
    }

    private EB12020007Res convertRs2Response(EB12020007SvcRsType rsType) {
        EB12020007Res response = new EB12020007Res();
        response.setMsgCod(rsType.getMSGCOD());
        response.setMsgTxt(rsType.getMSGTXT());
        response.setCustNumber1(rsType.getCustNumber1());
        response.setCustName1(rsType.getCustName1());
        response.setOffsetsFiller(rsType.getOffsetsFiller());
        response.setIdNo1(rsType.getIDNo1());
        response.setIdType1(rsType.getIDType1());

        response.setAcctNumber01(rsType.getAcctNumber01());
        response.setAddress1UncFlag01(rsType.getAddress1UncFlag01());
        response.setAddress1A01(rsType.getAddress1A01());
        response.setAddress1B01(rsType.getAddress1B01());
        response.setPhoneRes1UncFlag01(rsType.getPhoneRes1UncFlag01());
        response.setPhoneRes101(rsType.getPhoneRes101());
        response.setPhoneResExt101(rsType.getPhoneResExt101());
        response.setPhoneBus1UncFlag01(rsType.getPhoneBus1UncFlag01());
        response.setPhoneBus101(rsType.getPhoneBus101());
        response.setPhoneBusExt101(rsType.getPhoneBusExt101());
        response.setAddressUncFlag201(rsType.getAddressUncFlag201());
        response.setAddress2A01(rsType.getAddress2A01());
        response.setAddress2B01(rsType.getAddress2B01());
        response.setMobileNo1UncFlag01(rsType.getMobileNo1UncFlag01());
        response.setMobileNo101(rsType.getMobileNo101());
        response.setFaxNo1UncFlag01(rsType.getFaxNo1UncFlag01());
        response.setFaxNo101(rsType.getFaxNo101());
        response.setPostCode11(rsType.getPostCode11());
        response.setPostCode12(rsType.getPostCode12());

        response.setAcctNumber02(rsType.getAcctNumber02());
        response.setAddress1UncFlag02(rsType.getAddress1UncFlag02());
        response.setAddress1A02(rsType.getAddress1A02());
        response.setAddress1B02(rsType.getAddress1B02());
        response.setPhoneRes1UncFlag02(rsType.getPhoneRes1UncFlag02());
        response.setPhoneRes102(rsType.getPhoneRes102());
        response.setPhoneResExt102(rsType.getPhoneResExt102());
        response.setPhoneBus1UncFlag02(rsType.getPhoneBus1UncFlag02());
        response.setPhoneBus102(rsType.getPhoneBus102());
        response.setPhoneBusExt102(rsType.getPhoneBusExt102());
        response.setAddressUncFlag202(rsType.getAddressUncFlag202());
        response.setAddress2A02(rsType.getAddress2A02());
        response.setAddress2B02(rsType.getAddress2B02());
        response.setMobileNo1UncFlag02(rsType.getMobileNo1UncFlag02());
        response.setMobileNo102(rsType.getMobileNo102());
        response.setFaxNo1UncFlag02(rsType.getFaxNo1UncFlag02());
        response.setFaxNo102(rsType.getFaxNo102());
        response.setPostCode21(rsType.getPostCode21());
        response.setPostCode22(rsType.getPostCode22());

        response.setAcctNumber03(rsType.getAcctNumber03());
        response.setAddress1UncFlag03(rsType.getAddress1UncFlag03());
        response.setAddress1A03(rsType.getAddress1A03());
        response.setAddress1B03(rsType.getAddress1B03());
        response.setPhoneRes1UncFlag03(rsType.getPhoneRes1UncFlag03());
        response.setPhoneRes103(rsType.getPhoneRes103());
        response.setPhoneResExt103(rsType.getPhoneResExt103());
        response.setPhoneBus1UncFlag03(rsType.getPhoneBus1UncFlag03());
        response.setPhoneBus103(rsType.getPhoneBus103());
        response.setPhoneBusExt103(rsType.getPhoneBusExt103());
        response.setAddressUncFlag203(rsType.getAddressUncFlag203());
        response.setAddress2A03(rsType.getAddress2A03());
        response.setAddress2B03(rsType.getAddress2B03());
        response.setMobileNo1UncFlag03(rsType.getMobileNo1UncFlag03());
        response.setMobileNo103(rsType.getMobileNo103());
        response.setFaxNo1UncFlag03(rsType.getFaxNo1UncFlag03());
        response.setFaxNo103(rsType.getFaxNo103());
        response.setPostCode31(rsType.getPostCode31());
        response.setPostCode32(rsType.getPostCode32());

        response.setAcctNumber04(rsType.getAcctNumber04());
        response.setAddress1UncFlag04(rsType.getAddress1UncFlag04());
        response.setAddress1A04(rsType.getAddress1A04());
        response.setAddress1B04(rsType.getAddress1B04());
        response.setPhoneRes1UncFlag04(rsType.getPhoneRes1UncFlag04());
        response.setPhoneRes104(rsType.getPhoneRes104());
        response.setPhoneResExt104(rsType.getPhoneResExt104());
        response.setPhoneBus1UncFlag04(rsType.getPhoneBus1UncFlag04());
        response.setPhoneBus104(rsType.getPhoneBus104());
        response.setPhoneBusExt104(rsType.getPhoneBusExt104());
        response.setAddressUncFlag204(rsType.getAddressUncFlag204());
        response.setAddress2A04(rsType.getAddress2A04());
        response.setAddress2B04(rsType.getAddress2B04());
        response.setMobileNo1UncFlag04(rsType.getMobileNo1UncFlag04());
        response.setMobileNo104(rsType.getMobileNo104());
        response.setFaxNo1UncFlag04(rsType.getFaxNo1UncFlag04());
        response.setFaxNo104(rsType.getFaxNo104());
        response.setPostCode41(rsType.getPostCode41());
        response.setPostCode42(rsType.getPostCode42());

        response.setAcctNumber05(rsType.getAcctNumber05());
        response.setAddress1UncFlag05(rsType.getAddress1UncFlag05());
        response.setAddress1A05(rsType.getAddress1A05());
        response.setAddress1B05(rsType.getAddress1B05());
        response.setPhoneRes1UncFlag05(rsType.getPhoneRes1UncFlag05());
        response.setPhoneRes105(rsType.getPhoneRes105());
        response.setPhoneResExt105(rsType.getPhoneResExt105());
        response.setPhoneBus1UncFlag05(rsType.getPhoneBus1UncFlag05());
        response.setPhoneBus105(rsType.getPhoneBus105());
        response.setPhoneBusExt105(rsType.getPhoneBusExt105());
        response.setAddressUncFlag205(rsType.getAddressUncFlag205());
        response.setAddress2A05(rsType.getAddress2A05());
        response.setAddress2B05(rsType.getAddress2B05());
        response.setMobileNo1UncFlag05(rsType.getMobileNo1UncFlag05());
        response.setMobileNo105(rsType.getMobileNo105());
        response.setFaxNo1UncFlag05(rsType.getFaxNo1UncFlag05());
        response.setFaxNo105(rsType.getFaxNo105());
        response.setPostCode51(rsType.getPostCode51());
        response.setPostCode52(rsType.getPostCode52());

        response.setAcctNumber06(rsType.getAcctNumber06());
        response.setAddress1UncFlag06(rsType.getAddress1UncFlag06());
        response.setAddress1A06(rsType.getAddress1A06());
        response.setAddress1B06(rsType.getAddress1B06());
        response.setPhoneRes1UncFlag06(rsType.getPhoneRes1UncFlag06());
        response.setPhoneRes106(rsType.getPhoneRes106());
        response.setPhoneResExt106(rsType.getPhoneResExt106());
        response.setPhoneBus1UncFlag06(rsType.getPhoneBus1UncFlag06());
        response.setPhoneBus106(rsType.getPhoneBus106());
        response.setPhoneBusExt106(rsType.getPhoneBusExt106());
        response.setAddressUncFlag206(rsType.getAddressUncFlag206());
        response.setAddress2A06(rsType.getAddress2A06());
        response.setAddress2B06(rsType.getAddress2B06());
        response.setMobileNo1UncFlag06(rsType.getMobileNo1UncFlag06());
        response.setMobileNo106(rsType.getMobileNo106());
        response.setFaxNo1UncFlag06(rsType.getFaxNo1UncFlag06());
        response.setFaxNo106(rsType.getFaxNo106());
        response.setPostCode61(rsType.getPostCode61());
        response.setPostCode62(rsType.getPostCode62());

        response.setAcctNumber07(rsType.getAcctNumber07());
        response.setAddress1UncFlag07(rsType.getAddress1UncFlag07());
        response.setAddress1A07(rsType.getAddress1A07());
        response.setAddress1B07(rsType.getAddress1B07());
        response.setPhoneRes1UncFlag07(rsType.getPhoneRes1UncFlag07());
        response.setPhoneRes107(rsType.getPhoneRes107());
        response.setPhoneResExt107(rsType.getPhoneResExt107());
        response.setPhoneBus1UncFlag07(rsType.getPhoneBus1UncFlag07());
        response.setPhoneBus107(rsType.getPhoneBus107());
        response.setPhoneBusExt107(rsType.getPhoneBusExt107());
        response.setAddressUncFlag207(rsType.getAddressUncFlag207());
        response.setAddress2A07(rsType.getAddress2A07());
        response.setAddress2B07(rsType.getAddress2B07());
        response.setMobileNo1UncFlag07(rsType.getMobileNo1UncFlag07());
        response.setMobileNo107(rsType.getMobileNo107());
        response.setFaxNo1UncFlag07(rsType.getFaxNo1UncFlag07());
        response.setFaxNo107(rsType.getFaxNo107());
        response.setPostCode71(rsType.getPostCode71());
        response.setPostCode72(rsType.getPostCode72());

        response.setAcctNumber08(rsType.getAcctNumber08());
        response.setAddress1UncFlag08(rsType.getAddress1UncFlag08());
        response.setAddress1A08(rsType.getAddress1A08());
        response.setAddress1B08(rsType.getAddress1B08());
        response.setPhoneRes1UncFlag08(rsType.getPhoneRes1UncFlag08());
        response.setPhoneRes108(rsType.getPhoneRes108());
        response.setPhoneResExt108(rsType.getPhoneResExt108());
        response.setPhoneBus1UncFlag08(rsType.getPhoneBus1UncFlag08());
        response.setPhoneBus108(rsType.getPhoneBus108());
        response.setPhoneBusExt108(rsType.getPhoneBusExt108());
        response.setAddressUncFlag208(rsType.getAddressUncFlag208());
        response.setAddress2A08(rsType.getAddress2A08());
        response.setAddress2B08(rsType.getAddress2B08());
        response.setMobileNo1UncFlag08(rsType.getMobileNo1UncFlag08());
        response.setMobileNo108(rsType.getMobileNo108());
        response.setFaxNo1UncFlag08(rsType.getFaxNo1UncFlag08());
        response.setFaxNo108(rsType.getFaxNo108());
        response.setPostCode81(rsType.getPostCode81());
        response.setPostCode82(rsType.getPostCode82());

        response.setAcctNumber09(rsType.getAcctNumber09());
        response.setAddress1UncFlag09(rsType.getAddress1UncFlag09());
        response.setAddress1A09(rsType.getAddress1A09());
        response.setAddress1B09(rsType.getAddress1B09());
        response.setPhoneRes1UncFlag09(rsType.getPhoneRes1UncFlag09());
        response.setPhoneRes109(rsType.getPhoneRes109());
        response.setPhoneResExt109(rsType.getPhoneResExt109());
        response.setPhoneBus1UncFlag09(rsType.getPhoneBus1UncFlag09());
        response.setPhoneBus109(rsType.getPhoneBus109());
        response.setPhoneBusExt109(rsType.getPhoneBusExt109());
        response.setAddressUncFlag209(rsType.getAddressUncFlag209());
        response.setAddress2A09(rsType.getAddress2A09());
        response.setAddress2B09(rsType.getAddress2B09());
        response.setMobileNo1UncFlag09(rsType.getMobileNo1UncFlag09());
        response.setMobileNo109(rsType.getMobileNo109());
        response.setFaxNo1UncFlag09(rsType.getFaxNo1UncFlag09());
        response.setFaxNo109(rsType.getFaxNo109());
        response.setPostCode91(rsType.getPostCode91());
        response.setPostCode92(rsType.getPostCode92());

        response.setAcctNumber10(rsType.getAcctNumber10());
        response.setAddress1UncFlag10(rsType.getAddress1UncFlag10());
        response.setAddress1A10(rsType.getAddress1A10());
        response.setAddress1B10(rsType.getAddress1B10());
        response.setPhoneRes1UncFlag10(rsType.getPhoneRes1UncFlag10());
        response.setPhoneRes110(rsType.getPhoneRes110());
        response.setPhoneResExt110(rsType.getPhoneResExt110());
        response.setPhoneBus1UncFlag10(rsType.getPhoneBus1UncFlag10());
        response.setPhoneBus110(rsType.getPhoneBus110());
        response.setPhoneBusExt110(rsType.getPhoneBusExt110());
        response.setAddressUncFlag210(rsType.getAddressUncFlag210());
        response.setAddress2A10(rsType.getAddress2A10());
        response.setAddress2B10(rsType.getAddress2B10());
        response.setMobileNo1UncFlag10(rsType.getMobileNo1UncFlag10());
        response.setMobileNo110(rsType.getMobileNo110());
        response.setFaxNo1UncFlag10(rsType.getFaxNo1UncFlag10());
        response.setFaxNo110(rsType.getFaxNo110());
        response.setPostCode101(rsType.getPostCode101());
        response.setPostCode102(rsType.getPostCode102());

        response.setLastAcctseq(rsType.getLastAcctseq());
        response.setAddr(rsType.getAddr());
        response.setPostcode(rsType.getPostcode());
        response.setPhone(rsType.getPhone());
        response.setPhoneExt(rsType.getPhoneExt());

        if (CollectionUtils.isNotEmpty(rsType.getTxRepeatList())) {
            List<EB12020007Rep> repeats = new ArrayList<>();
            for (TxRepeatType txRepeatType : rsType.getTxRepeatList()) {
                EB12020007RepeatType repeatType = (EB12020007RepeatType) txRepeatType.changeType(EB12020007RepeatType.type);
                EB12020007Rep repeat = new EB12020007Rep();
                repeat.setAcno(repeatType.getACNO());
                repeat.setCurr(repeatType.getCURR());
                repeat.setSubid(repeatType.getSUBID());
                repeats.add(repeat);
            }
            response.setRepeats(repeats);
        }
        return response;
    }
}
