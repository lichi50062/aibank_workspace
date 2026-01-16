package com.tfb.aibank.biz.user.services.userprofile;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.gateway.EsbChannelGateway;
import com.tfb.aibank.biz.user.repository.CountryNameRepository;
import com.tfb.aibank.biz.user.repository.UserRepository;
import com.tfb.aibank.biz.user.repository.W8benInfoLogRepository;
import com.tfb.aibank.biz.user.repository.W8benSignLogRepository;
import com.tfb.aibank.biz.user.repository.entities.CountryNameEntity;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.biz.user.repository.entities.W8benInfoLogEntity;
import com.tfb.aibank.biz.user.repository.entities.W8benSignLogEntity;
import com.tfb.aibank.biz.user.services.userprofile.model.SaveW8BenDataRequest;
import com.tfb.aibank.biz.user.services.userprofile.model.UserProfileModel;
import com.tfb.aibank.chl.component.userdatacache.model.CustomerInformation;
import com.tfb.aibank.chl.component.userdatacache.model.EB032151Req;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIResponseException;
import com.tfb.aibank.integration.eai.msg.EB032151RS;

import tw.com.ibm.mf.eb.EB032151SvcRsType;

// @formatter:off
/**
 * @(#)UserService.java
 * 
 * <p>Description: user profile</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UserProfileService {

    private EsbChannelGateway esbGateway;

    private static final IBLog logger = IBLog.getLog(UserProfileService.class);

    private UserRepository userRepo;

    private IdentityService idService;

    private CountryNameRepository countryNameRepository;

    private W8benInfoLogRepository w8benInfoLogRepository;

    private W8benSignLogRepository w8benSignLogRepository;

    public UserProfileService(IdentityService idService, UserRepository userRepo, EsbChannelGateway esbGateway, CountryNameRepository countryNameRepository, W8benInfoLogRepository w8benInfoLogRepository, W8benSignLogRepository w8benSignLogRepository) {
        this.esbGateway = esbGateway;
        this.userRepo = userRepo;
        this.idService = idService;
        this.countryNameRepository = countryNameRepository;
        this.w8benInfoLogRepository = w8benInfoLogRepository;
        this.w8benSignLogRepository = w8benSignLogRepository;
    }

    /**
     * 更新user profile
     * 
     * @param custId
     * @return
     * @throws ActionException
     */
    public boolean updateUserNickname(UserProfileModel m) throws ActionException {
        IdentityData idData = getUser(m.getCustId(), m.getUidDup(), m.getUserUuid(), m.getCompanyKind());
        UserEntity entity = userRepo.findByCompanyKeyAndUserKey(idData.getCompanyKey(), idData.getUserKey());
        if (StringUtils.isNotBlank(m.getAvatar())) {
            entity.setAvatar(m.getAvatar());
        }
        if (StringUtils.isNotBlank(m.getNickName())) {
            entity.setNickName(m.getNickName());
        }
        userRepo.save(entity);
        return true;
    }

    /**
     * 取得使用者資料
     * 
     * @param userId
     * @param userUuid
     * @param companyKind
     * @return
     * @throws ActionException
     */
    private IdentityData getUser(String userId, String dup, String userUuid, Integer companyKind) throws ActionException {
        try {
            IdentityData identityData = idService.query(userId, dup, userUuid, companyKind);
            if (identityData != null && identityData.isAliveUser()) {
                return identityData;
            }
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
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
    public CustomerInformation sendEB032151(EB032151Req request) throws XmlException, EAIException, EAIResponseException {
        EB032151RS rs = esbGateway.sendEB032151(request);
        EB032151SvcRsType rsBody = rs.getBody();
        CustomerInformation result = new CustomerInformation();
        result.setAdvCod(rsBody.getADVCOD());
        result.setRefIdno(rsBody.getREFIDNO());
        result.setCustName(rsBody.getCUSTNAME());
        result.setBday(rsBody.getBDAY());
        result.setOrgType(rsBody.getORGTYPE());
        result.setOrgCheck(rsBody.getORGCHECK());
        result.setStockType(rsBody.getSTOCKTYPE());
        result.setIduCod(rsBody.getIDUCOD());
        result.setEntrpType(rsBody.getENTRPTYPE());
        result.setSex(rsBody.getSEX());
        result.setCustType(rsBody.getCUSTTYPE());
        result.setResidIdno(rsBody.getRESIDIDNO());
        result.setResidIdn01(rsBody.getRESIDIDN01());
        result.setResidDate(rsBody.getRESIDDATE());
        result.setResidEffDate(rsBody.getRESIDEFFDATE());
        result.setRiskUnit(rsBody.getRISKUNIT());
        result.setRiskRank(rsBody.getRISKRANK());
        result.setRankDate(rsBody.getRANKDATE());
        result.setRiskRate(rsBody.getRISKRATE());
        result.setIduCod2(rsBody.getIDUCOD2());
        result.setRiskDate01(rsBody.getRISKDATE01());
        result.setRiskRate01(rsBody.getRISKRATE01());
        result.setPdDate(rsBody.getPDDATE());
        result.setPdRate(rsBody.getPDRATE());
        result.setPdRisk(rsBody.getPDRISK());
        result.setCtryCod(rsBody.getCTRYCOD());
        result.setResume(rsBody.getRESUME());
        result.setCustSts(rsBody.getCUSTSTS());
        result.setCompany(rsBody.getCOMPANY());
        result.setTitle(rsBody.getTITLE());
        result.setBusZip(rsBody.getBUSZIP());
        result.setBusAddr1(rsBody.getBUSADDR1());
        result.setBusAddr2(rsBody.getBUSADDR2());
        result.setCttZip(rsBody.getCTTZIP());
        result.setCttAddr1(rsBody.getCTTADDR1());
        result.setCttAddr2(rsBody.getCTTADDR2());
        result.setPaySlip(rsBody.getPAYSLIP());
        result.setPayCode(rsBody.getPAYCODE());
        result.setPayZip(rsBody.getPAYZIP());
        result.setPayAddr1(rsBody.getPAYADDR1());
        result.setPayAddr2(rsBody.getPAYADDR2());
        result.setTelCod1(rsBody.getTELCOD1());
        result.setTelNo1(rsBody.getTELNO1());
        result.setTelExt1(rsBody.getTELEXT1());
        result.setTelTyp1(rsBody.getTELTYP1());
        result.setTelDay1(rsBody.getTELDAY1());
        result.setTelNight1(rsBody.getTELNIGHT1());
        result.setTelVoice1(rsBody.getTELVOICE1());
        result.setTelFax1(rsBody.getTELFAX1());
        result.setTelCod2(rsBody.getTELCOD2());
        result.setTelNo2(rsBody.getTELNO2());
        result.setTelExt2(rsBody.getTELEXT2());
        result.setTelTyp2(rsBody.getTELTYP2());
        result.setTelDay2(rsBody.getTELDAY2());
        result.setTelNight2(rsBody.getTELNIGHT2());
        result.setTelVoice2(rsBody.getTELVOICE2());
        result.setTelFax2(rsBody.getTELFAX2());
        result.setEmailAddr(rsBody.getEMAILADDR());
        result.setNoEmailFlg(rsBody.getNOEMAILFLG());
        result.setEngName(StringUtils.trimAllBigSpace(rsBody.getENGNAME()));
        result.setEngZip(rsBody.getENGZIP());
        result.setEngAddr1(StringUtils.trimAllBigSpace(rsBody.getENGADDR1()));
        result.setEngAddr2(StringUtils.trimAllBigSpace(rsBody.getENGADDR2()));
        result.setRegCapVal(rsBody.getREGCAPVAL());
        result.setCapValYy(rsBody.getCAPVALYY());
        result.setCapValMm(rsBody.getCAPVALMM());
        result.setCapVal(rsBody.getCAPVAL());
        result.setEmpCnt(rsBody.getEMPCNT());
        result.setAprovDate(rsBody.getAPROVDATE());
        result.setBusItem(rsBody.getBUSITEM());
        result.setContactPson(rsBody.getCONTACTPSON());
        result.setRespId(rsBody.getRESPID());
        result.setRespName(rsBody.getRESPNAME());
        result.setRespBday(rsBody.getRESPBDAY());
        result.setPrtSlip(rsBody.getPRTSLIP());
        result.setFrCmpid(rsBody.getFRCMPID());
        result.setFrIntno(rsBody.getFRINTNO());
        result.setFrRegno(rsBody.getFRREGNO());
        result.setBillsCheck(rsBody.getBILLSCHECK());
        result.setSrcTyp(rsBody.getSRCTYP());
        result.setLostFlg(rsBody.getLOSTFLG());
        result.setBillsUpdDate(rsBody.getBILLSUPDDATE());
        result.setBillsStrDate(rsBody.getBILLSSTRDATE());
        result.setBillsEndDate(rsBody.getBILLSENDDATE());
        result.setLstTxBrh1(rsBody.getLSTTXBRH1());
        result.setLstTxDate1(rsBody.getLSTTXDATE1());
        result.setLstTxBrh2(rsBody.getLSTTXBRH2());
        result.setLstTxDate2(rsBody.getLSTTXDATE2());
        result.setLstTxBrh3(rsBody.getLSTTXBRH3());
        result.setLstTxDate3(rsBody.getLSTTXDATE3());
        result.setLstTxBrh4(rsBody.getLSTTXBRH4());
        result.setLstTxDate4(rsBody.getLSTTXDATE4());
        result.setLstTxBrh5(rsBody.getLSTTXBRH5());
        result.setLstTxDate5(rsBody.getLSTTXDATE5());
        result.setLstTxBrh6(rsBody.getLSTTXBRH6());
        result.setLstTxDate6(rsBody.getLSTTXDATE6());
        result.setMarrage(rsBody.getMARRAGE());
        result.setChildNo(rsBody.getCHILDNO());
        result.setEducation(rsBody.getEDUCATION());
        result.setCareer(rsBody.getCAREER());
        result.setOldCustNo(rsBody.getOLDCUSTNO());
        result.setEdmAppv(rsBody.getEDMAPPV());
        result.setDmAppv(rsBody.getDMAPPV());
        result.setTmAppv(rsBody.getTMAPPV());
        result.setSmsAppv(rsBody.getSMSAPPV());
        result.setCustSts01(rsBody.getCUSTSTS01());
        result.setTdNotify(rsBody.getTDNOTIFY());
        result.setProtName(rsBody.getPROTNAME());
        result.setRateTyp(rsBody.getRATETYP());
        result.setMtnBrhRat(rsBody.getMTNBRHRAT());
        result.setMtnDateRat(rsBody.getMTNDATERAT());
        result.setRateYyy(rsBody.getRATEYYY());
        result.setRate(rsBody.getRATE());
        result.setFc55Brh(rsBody.getFC55BRH());
        result.setCcdFlg(rsBody.getCCDFLG());
        result.setPepFlg(rsBody.getPEPFLG());
        result.setBlkFlg(rsBody.getBLKFLG());
        result.setNegative(rsBody.getNegative());
        result.setFileSar(rsBody.getFileSAR());
        result.setEngCtry(rsBody.getENGCTRY());
        result.setSalary(rsBody.getSALARY());
        result.setConTel(rsBody.getCONTEL());
        result.setConTelExt(rsBody.getCONTELExt());
        result.setCompTel(rsBody.getCOMPTEL());
        result.setCompTelExt(rsBody.getCOMPTELExt());
        result.setResTel(rsBody.getRESTEL());
        result.setResTelExt(rsBody.getRESTELExt());
        result.setMobile(rsBody.getMOBILE());
        result.setFax(rsBody.getFAX());
        result.setOccupancy1(rsBody.getOccupancy1());
        result.setDefaultString6(rsBody.getDefaultString6());
        result.setCurrAddr1(rsBody.getCURRADDR1());
        result.setCurrAddr2(rsBody.getCURRADDR2());
        result.setCurrTel(rsBody.getCURRTEL());
        result.setCurrTelExt(rsBody.getCURRTELExt());

        List<CountryNameEntity> nameList = countryNameRepository.findByIsoCode(result.getCtryCod());

        if (CollectionUtils.isNotEmpty(nameList)) {
            result.setNameEnUs(nameList.get(0).getNameEnUs());
            result.setIsoCode2(nameList.get(0).getIsoCode2());
        }

        return result;
    }

    /**
     * 存取簽署畫面資料檔Log檔
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public Boolean saveW8BenData(SaveW8BenDataRequest request) throws ActionException {
        IdentityData idData = getUser(request.getCustId(), request.getUidDup(), request.getUserUuid(), request.getCompanyKind());
        W8benInfoLogEntity info = new W8benInfoLogEntity();
        info.setCompanyKey(idData.getCompanyKey());
        info.setUserKey(idData.getUserKey());
        info.setPlatform(request.getPlatForm());
        info.setDocNo(request.getDocNo());
        info.setDocVer(request.getDocVer());
        info.setEngName(request.getEngName());
        info.setCountryCod(request.getCountryCod());
        info.setBusAddr(request.getBusAddr());
        info.setCurrAddr(request.getCurrAddr());
        info.setItin(request.getItin());
        info.setFtin(request.getFtin());
        info.setFtinCheck(request.getFtinCheck());
        info.setReffrenceNum(request.getReferenceNum());
        info.setbDay(request.getBday());
        info.setEngCountryName(request.getEngCountryName());
        info.setTaxAgreementNum(request.getTaxAgreementNum());
        info.setRate(request.getRate());
        info.setIncomeType(request.getIncomeType());
        info.setExtraConditions(request.getExtraConditions());
        info.setTxDate(request.getTxDate());
        info.setCreateTime(request.getCreateTime());
        info.setClientIp(request.getClientIp());
        info.setClientPort(request.getClientPort());
        info.setTraceId(request.getTraceId());
        info.setbDay(request.getBday());
        w8benInfoLogRepository.save(info);
        W8benSignLogEntity sign = new W8benSignLogEntity();
        sign.setCompanyKey(idData.getCompanyKey());
        sign.setUserKey(idData.getUserKey());
        sign.setW8benInfoLogKey(info.getMasterKey());
        sign.setPlatform(request.getPlatForm());
        sign.setErrorCode(request.getErrorCode());
        sign.setErrorMsg(request.getErrorMsg());
        sign.setTxStatus(request.getTxStatus());
        sign.setEmail(request.getEmail());
        sign.setClientIp(request.getClientIp());
        sign.setClientPort(request.getClientPort());
        sign.setTraceId(request.getTraceId());
        sign.setTxDate(request.getTxDate());
        w8benSignLogRepository.save(sign);

        return true;
    }
}
