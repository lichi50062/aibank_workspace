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
package com.tfb.aibank.biz.user.services.inveligiblecheck;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.services.inveligiblecheck.model.EB032675Res;
import com.tfb.aibank.biz.user.services.inveligiblecheck.model.EB032675ResRep;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB032675RS;
import com.tfb.aibank.integration.eai.msg.EB5556982RS;

import tw.com.ibm.mf.eai.TxRepeatType;
import tw.com.ibm.mf.eb.EB032675D007SvcRsType;
import tw.com.ibm.mf.eb.EB032675D009RepeatType;
import tw.com.ibm.mf.eb.EB032675D009SvcRsType;

// @formatter:off
/**
 * @(#)InvestEligibleCheckService.java
 * 
 * <p>Description:金融商品交易資格查詢服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Marty Pan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class InvestEligibleCheckService {

    private EsbChannelGateway esbGateway;

    public InvestEligibleCheckService(EsbChannelGateway esbGateway) {
        this.esbGateway = esbGateway;
    }

    /**
     * 電文(EB032675)，查詢客戶各類投資資格註記(禁銷、FATCA、弱勢、專業投資人法人)
     * 
     * @param custId
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public EB032675Res sendEB032675(String custId) throws XmlException, EAIException, EAIResponseException {

        EB032675RS rs = this.esbGateway.sendEB032675(custId);

        String hfmtid = rs.getHeader().getHFMTID();

        EB032675Res response = new EB032675Res();

        switch (hfmtid) {
        case "D007":
            EB032675D007SvcRsType rsBodyD007 = (EB032675D007SvcRsType) rs.getBody().changeType(EB032675D007SvcRsType.type);
            response.setFunction(rsBodyD007.getFUNCTION());
            response.setRange(rsBodyD007.getRANGE());
            response.setOccur(rsBodyD007.getOCCUR());
            break;
        case "D009":
            EB032675D009SvcRsType rsBodyD009 = (EB032675D009SvcRsType) rs.getBody().changeType(EB032675D009SvcRsType.type);
            response.setAction(rsBodyD009.getACTION());
            response.setCnt(rsBodyD009.getCNT());
            response.setRcCod(rsBodyD009.getRCCOD());
            response.setSrcTyp(rsBodyD009.getSRCTYP());
            response.setMtnDate(rsBodyD009.getMTNDATE());
            response.setTxFlg(rsBodyD009.getTXFLG());
            response.setDesc(rsBodyD009.getDESC());
            response.setProdTyp(rsBodyD009.getPRODTYP());
            response.setDeadTyp(rsBodyD009.getDEADTYP());
            response.setRejTyp(rsBodyD009.getREJTYP());
            response.setDmFlg(rsBodyD009.getDMFLG());
            response.setEdmFlg(rsBodyD009.getEDMFLG());
            response.setSmsFlg(rsBodyD009.getSMSFLG());
            response.setTmFlg(rsBodyD009.getTMFLG());
            response.setInfoFlg(rsBodyD009.getINFOFLG());
            response.setAcc1Flg(rsBodyD009.getACC1FLG());
            response.setAcc2Flg(rsBodyD009.getACC2FLG());
            response.setAcc3Flg(rsBodyD009.getACC3FLG());
            response.setAcc4Flg(rsBodyD009.getACC4FLG());
            response.setAcc5Flg(rsBodyD009.getACC5FLG());
            response.setAcc6Flg(rsBodyD009.getACC6FLG());
            response.setVulFlag(rsBodyD009.getVULFLAG());
            response.setTrustFlag(rsBodyD009.getTRUSTFLAG());
            response.setAgeUn70Flag(rsBodyD009.getAGEUN70FLAG());
            response.setEduOvJrFlag(rsBodyD009.getEDUOVJRFLAG());
            response.setHealthFlag(rsBodyD009.getHEALTHFLAG());
            response.setBillsCheck(rsBodyD009.getBILLSCHECK());
            response.setObuFlg(rsBodyD009.getOBUFLG());
            response.setSickType(rsBodyD009.getSICKTYPE());
            response.setBondFlag(rsBodyD009.getBONDFLAG());
            response.setEndDate(rsBodyD009.getENDDATE());
            response.setFiller(rsBodyD009.getFILLER());
            response.setBn01Flg(rsBodyD009.getBN01FLG());
            response.setLegalFlg(rsBodyD009.getLEGALFLG());
            response.setF2287Flg(rsBodyD009.getF2287FLG());
            response.setIdnF(rsBodyD009.getIDNF());
            response.setInvestFlg(rsBodyD009.getINVESTFLG());
            response.setInvestTyp(rsBodyD009.getINVESTTYP());
            response.setInvestExp(rsBodyD009.getINVESTEXP());
            response.setInvestDue(rsBodyD009.getINVESTDUE());

            response.setProductType1(rsBodyD009.getProductType1());
            response.setTraderId1(rsBodyD009.getTraderID1());
            response.setProductType2(rsBodyD009.getProductType2());
            response.setTraderId2(rsBodyD009.getTraderID2());
            response.setProductType3(rsBodyD009.getProductType3());
            response.setTraderId3(rsBodyD009.getTraderID3());
            response.setProductType4(rsBodyD009.getProductType4());
            response.setTraderId4(rsBodyD009.getTraderID4());
            response.setProductType5(rsBodyD009.getProductType5());
            response.setTraderId5(rsBodyD009.getTraderID5());
            response.setProductType6(rsBodyD009.getProductType6());
            response.setTraderId6(rsBodyD009.getTraderID6());
            response.setProductType7(rsBodyD009.getProductType7());
            response.setTraderId7(rsBodyD009.getTraderID7());
            response.setProductType8(rsBodyD009.getProductType8());
            response.setTraderId8(rsBodyD009.getTraderID8());
            response.setProductType9(rsBodyD009.getProductType9());
            response.setTraderId9(rsBodyD009.getTraderID9());
            response.setProductType10(rsBodyD009.getProductType10());
            response.setTraderId10(rsBodyD009.getTraderID10());
            response.setProductType11(rsBodyD009.getProductType11());
            response.setTraderId11(rsBodyD009.getTraderID11());
            response.setProductType12(rsBodyD009.getProductType12());
            response.setTraderId12(rsBodyD009.getTraderID12());
            response.setProductType13(rsBodyD009.getProductType13());
            response.setTraderId13(rsBodyD009.getTraderID13());
            response.setProductType14(rsBodyD009.getProductType14());
            response.setTraderId14(rsBodyD009.getTraderID14());
            response.setProductType15(rsBodyD009.getProductType15());
            response.setTraderId15(rsBodyD009.getTraderID15());
            response.setProductType16(rsBodyD009.getProductType16());
            response.setTraderId16(rsBodyD009.getTraderID16());
            response.setProductType17(rsBodyD009.getProductType17());
            response.setTraderId17(rsBodyD009.getTraderID17());
            response.setProductType18(rsBodyD009.getProductType18());
            response.setTraderId18(rsBodyD009.getTraderID18());
            response.setProductType19(rsBodyD009.getProductType19());
            response.setTraderId19(rsBodyD009.getTraderID19());
            response.setProductType20(rsBodyD009.getProductType20());
            response.setTraderId20(rsBodyD009.getTraderID20());

            List<EB032675ResRep> repeats = new ArrayList<>();
            for (TxRepeatType txRepeatType : rsBodyD009.getTxRepeatList()) {
                EB032675D009RepeatType repeatType = (EB032675D009RepeatType) txRepeatType.changeType(EB032675D009RepeatType.type);
                repeats.add(new EB032675ResRep(repeatType.getACCTNO()));
            }
            response.setRepeats(repeats);
            break;
        }

        return response;
    }

    /**
     * 電文(EB032675)，查詢客戶各類投資資格註記(禁銷、FATCA、弱勢、專業投資人法人)
     *
     * @param custId
     * @param busAddr1
     * @param custId
     * @return
     * @throws EAIResponseException
     * @throws EAIException
     * @throws XmlException
     */
    public EB032675Res sendEB032675Modify(String custId, String busAddr1) throws XmlException, EAIException, EAIResponseException {
        EB032675RS rs = this.esbGateway.sendEB032675Modify(custId, busAddr1);

        String hfmtid = rs.getHeader().getHFMTID();

        EB032675Res response = new EB032675Res();

        switch (hfmtid) {
        case "D007":
            EB032675D007SvcRsType rsBodyD007 = (EB032675D007SvcRsType) rs.getBody().changeType(EB032675D007SvcRsType.type);
            response.setFunction(rsBodyD007.getFUNCTION());
            response.setRange(rsBodyD007.getRANGE());
            response.setOccur(rsBodyD007.getOCCUR());
            break;
        case "D009":
            EB032675D009SvcRsType rsBodyD009 = (EB032675D009SvcRsType) rs.getBody().changeType(EB032675D009SvcRsType.type);
            response.setAction(rsBodyD009.getACTION());
            response.setCnt(rsBodyD009.getCNT());
            response.setRcCod(rsBodyD009.getRCCOD());
            response.setSrcTyp(rsBodyD009.getSRCTYP());
            response.setMtnDate(rsBodyD009.getMTNDATE());
            response.setTxFlg(rsBodyD009.getTXFLG());
            response.setDesc(rsBodyD009.getDESC());
            response.setProdTyp(rsBodyD009.getPRODTYP());
            response.setDeadTyp(rsBodyD009.getDEADTYP());
            response.setRejTyp(rsBodyD009.getREJTYP());
            response.setDmFlg(rsBodyD009.getDMFLG());
            response.setEdmFlg(rsBodyD009.getEDMFLG());
            response.setSmsFlg(rsBodyD009.getSMSFLG());
            response.setTmFlg(rsBodyD009.getTMFLG());
            response.setInfoFlg(rsBodyD009.getINFOFLG());
            response.setAcc1Flg(rsBodyD009.getACC1FLG());
            response.setAcc2Flg(rsBodyD009.getACC2FLG());
            response.setAcc3Flg(rsBodyD009.getACC3FLG());
            response.setAcc4Flg(rsBodyD009.getACC4FLG());
            response.setAcc5Flg(rsBodyD009.getACC5FLG());
            response.setAcc6Flg(rsBodyD009.getACC6FLG());
            response.setVulFlag(rsBodyD009.getVULFLAG());
            response.setTrustFlag(rsBodyD009.getTRUSTFLAG());
            response.setAgeUn70Flag(rsBodyD009.getAGEUN70FLAG());
            response.setEduOvJrFlag(rsBodyD009.getEDUOVJRFLAG());
            response.setHealthFlag(rsBodyD009.getHEALTHFLAG());
            response.setBillsCheck(rsBodyD009.getBILLSCHECK());
            response.setObuFlg(rsBodyD009.getOBUFLG());
            response.setSickType(rsBodyD009.getSICKTYPE());
            response.setBondFlag(rsBodyD009.getBONDFLAG());
            response.setEndDate(rsBodyD009.getENDDATE());
            response.setFiller(rsBodyD009.getFILLER());
            response.setBn01Flg(rsBodyD009.getBN01FLG());
            response.setLegalFlg(rsBodyD009.getLEGALFLG());
            response.setF2287Flg(rsBodyD009.getF2287FLG());
            response.setIdnF(rsBodyD009.getIDNF());
            response.setInvestFlg(rsBodyD009.getINVESTFLG());
            response.setInvestTyp(rsBodyD009.getINVESTTYP());
            response.setInvestExp(rsBodyD009.getINVESTEXP());
            response.setInvestDue(rsBodyD009.getINVESTDUE());

            response.setProductType1(rsBodyD009.getProductType1());
            response.setTraderId1(rsBodyD009.getTraderID1());
            response.setProductType2(rsBodyD009.getProductType2());
            response.setTraderId2(rsBodyD009.getTraderID2());
            response.setProductType3(rsBodyD009.getProductType3());
            response.setTraderId3(rsBodyD009.getTraderID3());
            response.setProductType4(rsBodyD009.getProductType4());
            response.setTraderId4(rsBodyD009.getTraderID4());
            response.setProductType5(rsBodyD009.getProductType5());
            response.setTraderId5(rsBodyD009.getTraderID5());
            response.setProductType6(rsBodyD009.getProductType6());
            response.setTraderId6(rsBodyD009.getTraderID6());
            response.setProductType7(rsBodyD009.getProductType7());
            response.setTraderId7(rsBodyD009.getTraderID7());
            response.setProductType8(rsBodyD009.getProductType8());
            response.setTraderId8(rsBodyD009.getTraderID8());
            response.setProductType9(rsBodyD009.getProductType9());
            response.setTraderId9(rsBodyD009.getTraderID9());
            response.setProductType10(rsBodyD009.getProductType10());
            response.setTraderId10(rsBodyD009.getTraderID10());
            response.setProductType11(rsBodyD009.getProductType11());
            response.setTraderId11(rsBodyD009.getTraderID11());
            response.setProductType12(rsBodyD009.getProductType12());
            response.setTraderId12(rsBodyD009.getTraderID12());
            response.setProductType13(rsBodyD009.getProductType13());
            response.setTraderId13(rsBodyD009.getTraderID13());
            response.setProductType14(rsBodyD009.getProductType14());
            response.setTraderId14(rsBodyD009.getTraderID14());
            response.setProductType15(rsBodyD009.getProductType15());
            response.setTraderId15(rsBodyD009.getTraderID15());
            response.setProductType16(rsBodyD009.getProductType16());
            response.setTraderId16(rsBodyD009.getTraderID16());
            response.setProductType17(rsBodyD009.getProductType17());
            response.setTraderId17(rsBodyD009.getTraderID17());
            response.setProductType18(rsBodyD009.getProductType18());
            response.setTraderId18(rsBodyD009.getTraderID18());
            response.setProductType19(rsBodyD009.getProductType19());
            response.setTraderId19(rsBodyD009.getTraderID19());
            response.setProductType20(rsBodyD009.getProductType20());
            response.setTraderId20(rsBodyD009.getTraderID20());

            List<EB032675ResRep> repeats = new ArrayList<>();
            for (TxRepeatType txRepeatType : rsBodyD009.getTxRepeatList()) {
                EB032675D009RepeatType repeatType = (EB032675D009RepeatType) txRepeatType.changeType(EB032675D009RepeatType.type);
                repeats.add(new EB032675ResRep(repeatType.getACCTNO()));
            }
            response.setRepeats(repeats);
            break;
        }

        return response;
    }

    /**
     * 授權之交易人員身份證字號檢核
     * 
     * @param custId
     * @param busAddr1
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public Boolean authorizedIdVerification(String custId, String busAddr1) throws XmlException, EAIException, EAIResponseException {
        EB032675RS rs3 = esbGateway.sendEB032675("CRRAML", custId, busAddr1, "3");

        String hfmtid = rs3.getHeader().getHFMTID();

        if (!StringUtils.equals("D009", hfmtid)) {

            return false;
        }
        EB032675D009SvcRsType rsBodyD009 = (EB032675D009SvcRsType) rs3.getBody().changeType(EB032675D009SvcRsType.type);
        boolean legalFlg = checkLegalFlg(busAddr1, rsBodyD009.getTraderID1(), rsBodyD009.getProductType1()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID2(), rsBodyD009.getProductType2()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID3(), rsBodyD009.getProductType3()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID4(), rsBodyD009.getProductType4()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID5(), rsBodyD009.getProductType5()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID6(), rsBodyD009.getProductType6()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID7(), rsBodyD009.getProductType7()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID8(), rsBodyD009.getProductType8()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID9(), rsBodyD009.getProductType9()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID10(), rsBodyD009.getProductType10()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID11(), rsBodyD009.getProductType11()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID12(), rsBodyD009.getProductType12()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID13(), rsBodyD009.getProductType13()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID14(), rsBodyD009.getProductType14()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID15(), rsBodyD009.getProductType15()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID16(), rsBodyD009.getProductType16()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID17(), rsBodyD009.getProductType17()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID18(), rsBodyD009.getProductType18()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID19(), rsBodyD009.getProductType19()) || checkLegalFlg(busAddr1, rsBodyD009.getTraderID20(), rsBodyD009.getProductType20());
        EB032675RS rs4 = esbGateway.sendEB032675("CRRAML", busAddr1, "", "4");
        EB032675D009SvcRsType rsBody4 = (EB032675D009SvcRsType) rs4.getBody().changeType(EB032675D009SvcRsType.type);
        return legalFlg && !StringUtils.isN(rsBody4.getF2287FLG());

    }

    /**
     * 檢核客戶授權人是否正確
     * 
     * @param busAddr
     * @param traderId
     * @param productType
     * @return
     */
    private boolean checkLegalFlg(String busAddr, String traderId, String productType) {

        return StringUtils.equalsIgnoreCase(busAddr, traderId) && "01".equals(productType);
    }

    /**
     * 是否為專業投資人檢核
     * 
     * @param custId
     * @param userId
     * @param nameCode
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public Boolean isProfessionalInvestor(String custId, String userId, String nameCode) throws XmlException, EAIException, EAIResponseException {

        EB5556982RS response = esbGateway.sendEB5556982(custId, userId, nameCode);

        return StringUtils.isY(response.getBody().getINVESTCOD());
    }

    /**
     * 客戶基本資料
     * 
     * @param custId
     * @param busAddr1
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public EB032675Res customerInvestBaseInfo(String custId, String busAddr1) throws XmlException, EAIException, EAIResponseException {
        EB032675RS rs = esbGateway.sendEB032675("CRRAML", custId, busAddr1, "4");
        EB032675Res response = new EB032675Res();
        String hfmtid = rs.getHeader().getHFMTID();

        switch (hfmtid) {
        case "D007":
            EB032675D007SvcRsType rsBodyD007 = (EB032675D007SvcRsType) rs.getBody().changeType(EB032675D007SvcRsType.type);
            response.setFunction(rsBodyD007.getFUNCTION());
            response.setRange(rsBodyD007.getRANGE());
            response.setOccur(rsBodyD007.getOCCUR());
            break;
        case "D009":
            EB032675D009SvcRsType rsBodyD009 = (EB032675D009SvcRsType) rs.getBody().changeType(EB032675D009SvcRsType.type);
            response.setAction(rsBodyD009.getACTION());
            response.setCnt(rsBodyD009.getCNT());
            response.setRcCod(rsBodyD009.getRCCOD());
            response.setSrcTyp(rsBodyD009.getSRCTYP());
            response.setMtnDate(rsBodyD009.getMTNDATE());
            response.setTxFlg(rsBodyD009.getTXFLG());
            response.setDesc(rsBodyD009.getDESC());
            response.setProdTyp(rsBodyD009.getPRODTYP());
            response.setDeadTyp(rsBodyD009.getDEADTYP());
            response.setRejTyp(rsBodyD009.getREJTYP());
            response.setDmFlg(rsBodyD009.getDMFLG());
            response.setEdmFlg(rsBodyD009.getEDMFLG());
            response.setSmsFlg(rsBodyD009.getSMSFLG());
            response.setTmFlg(rsBodyD009.getTMFLG());
            response.setInfoFlg(rsBodyD009.getINFOFLG());
            response.setAcc1Flg(rsBodyD009.getACC1FLG());
            response.setAcc2Flg(rsBodyD009.getACC2FLG());
            response.setAcc3Flg(rsBodyD009.getACC3FLG());
            response.setAcc4Flg(rsBodyD009.getACC4FLG());
            response.setAcc5Flg(rsBodyD009.getACC5FLG());
            response.setAcc6Flg(rsBodyD009.getACC6FLG());
            response.setVulFlag(rsBodyD009.getVULFLAG());
            response.setTrustFlag(rsBodyD009.getTRUSTFLAG());
            response.setAgeUn70Flag(rsBodyD009.getAGEUN70FLAG());
            response.setEduOvJrFlag(rsBodyD009.getEDUOVJRFLAG());
            response.setHealthFlag(rsBodyD009.getHEALTHFLAG());
            response.setBillsCheck(rsBodyD009.getBILLSCHECK());
            response.setObuFlg(rsBodyD009.getOBUFLG());
            response.setSickType(rsBodyD009.getSICKTYPE());
            response.setBondFlag(rsBodyD009.getBONDFLAG());
            response.setEndDate(rsBodyD009.getENDDATE());
            response.setFiller(rsBodyD009.getFILLER());
            response.setBn01Flg(rsBodyD009.getBN01FLG());
            response.setLegalFlg(rsBodyD009.getLEGALFLG());
            response.setF2287Flg(rsBodyD009.getF2287FLG());
            response.setIdnF(rsBodyD009.getIDNF());
            response.setInvestFlg(rsBodyD009.getINVESTFLG());
            response.setInvestTyp(rsBodyD009.getINVESTTYP());
            response.setInvestExp(rsBodyD009.getINVESTEXP());
            response.setInvestDue(rsBodyD009.getINVESTDUE());

            response.setProductType1(rsBodyD009.getProductType1());
            response.setTraderId1(rsBodyD009.getTraderID1());
            response.setProductType2(rsBodyD009.getProductType2());
            response.setTraderId2(rsBodyD009.getTraderID2());
            response.setProductType3(rsBodyD009.getProductType3());
            response.setTraderId3(rsBodyD009.getTraderID3());
            response.setProductType4(rsBodyD009.getProductType4());
            response.setTraderId4(rsBodyD009.getTraderID4());
            response.setProductType5(rsBodyD009.getProductType5());
            response.setTraderId5(rsBodyD009.getTraderID5());
            response.setProductType6(rsBodyD009.getProductType6());
            response.setTraderId6(rsBodyD009.getTraderID6());
            response.setProductType7(rsBodyD009.getProductType7());
            response.setTraderId7(rsBodyD009.getTraderID7());
            response.setProductType8(rsBodyD009.getProductType8());
            response.setTraderId8(rsBodyD009.getTraderID8());
            response.setProductType9(rsBodyD009.getProductType9());
            response.setTraderId9(rsBodyD009.getTraderID9());
            response.setProductType10(rsBodyD009.getProductType10());
            response.setTraderId10(rsBodyD009.getTraderID10());
            response.setProductType11(rsBodyD009.getProductType11());
            response.setTraderId11(rsBodyD009.getTraderID11());
            response.setProductType12(rsBodyD009.getProductType12());
            response.setTraderId12(rsBodyD009.getTraderID12());
            response.setProductType13(rsBodyD009.getProductType13());
            response.setTraderId13(rsBodyD009.getTraderID13());
            response.setProductType14(rsBodyD009.getProductType14());
            response.setTraderId14(rsBodyD009.getTraderID14());
            response.setProductType15(rsBodyD009.getProductType15());
            response.setTraderId15(rsBodyD009.getTraderID15());
            response.setProductType16(rsBodyD009.getProductType16());
            response.setTraderId16(rsBodyD009.getTraderID16());
            response.setProductType17(rsBodyD009.getProductType17());
            response.setTraderId17(rsBodyD009.getTraderID17());
            response.setProductType18(rsBodyD009.getProductType18());
            response.setTraderId18(rsBodyD009.getTraderID18());
            response.setProductType19(rsBodyD009.getProductType19());
            response.setTraderId19(rsBodyD009.getTraderID19());
            response.setProductType20(rsBodyD009.getProductType20());
            response.setTraderId20(rsBodyD009.getTraderID20());

            List<EB032675ResRep> repeats = new ArrayList<>();
            for (TxRepeatType txRepeatType : rsBodyD009.getTxRepeatList()) {
                EB032675D009RepeatType repeatType = (EB032675D009RepeatType) txRepeatType.changeType(EB032675D009RepeatType.type);
                repeats.add(new EB032675ResRep(repeatType.getACCTNO()));
            }
            response.setRepeats(repeats);
            break;
        }
        return response;
    }
}
