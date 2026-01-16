package com.tfb.aibank.biz.user.services.countryname;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.biz.user.repository.AiBankCountryNameRepository;
import com.tfb.aibank.biz.user.repository.IpLocationARepository;
import com.tfb.aibank.biz.user.repository.IpLocationBRepository;
import com.tfb.aibank.biz.user.repository.SystemControlTableRepository;
import com.tfb.aibank.biz.user.repository.entities.AiBankCountryNameEntity;
import com.tfb.aibank.biz.user.repository.entities.IpLocationAEntity;
import com.tfb.aibank.biz.user.repository.entities.IpLocationBEntity;
import com.tfb.aibank.biz.user.repository.entities.SystemControlEntity;
import com.tfb.aibank.biz.user.repository.entities.pk.SystemControlEntityPk;
import com.tfb.aibank.biz.user.services.countryname.model.CountryNameVo;

public class AiBankCountryNameService {
    
    private static final IBLog logger = IBLog.getLog(AiBankCountryNameService.class);

    private AiBankCountryNameRepository aiBankCountryNameRepository;
    
    private IpLocationARepository ipLocationARepository;
    
    private IpLocationBRepository ipLocationBRepository;
    
    private SystemControlTableRepository systemControlTableRepository;

    
 
    public AiBankCountryNameService(AiBankCountryNameRepository aiBankCountryNameRepository, IpLocationARepository ipLocationARepository, IpLocationBRepository ipLocationBRepository, SystemControlTableRepository systemControlTableRepository) {
        super();
        this.aiBankCountryNameRepository = aiBankCountryNameRepository;
        this.ipLocationARepository = ipLocationARepository;
        this.ipLocationBRepository = ipLocationBRepository;
        this.systemControlTableRepository = systemControlTableRepository;
    }
    
    
    public CountryNameVo getCountryNameByIp(String ipAddress, String locale) {
        
        CountryNameVo vo = new CountryNameVo("", "", "");
        
        String countryCode = "";
        String countryName = "";
        Long ipFrom = null;
        Long ipTo = null;

        try {
            // SystemControlTable 
            // ESMBT100 IP_LOCATION
            
            SystemControlEntity systemControl = this.systemControlTableRepository.findById(new SystemControlEntityPk("ESMBT100", "IP_LOCATION")).orElse( new SystemControlEntity());
            String useFlag = "";
            
            if (!StringUtils.isBlank(systemControl.getTaskId())) {
                useFlag = systemControl.getUseFlag();
            }
            Long ipNumber = IpUtils.ipToLong(ipAddress);
            
            if ("A".equals(useFlag)) {
                IpLocationAEntity location = this.ipLocationARepository.findByIpNumber(ipNumber).orElse(new IpLocationAEntity());
                countryCode = location.getCountryCode();
                ipFrom = location.getIpFrom();
                ipTo = location.getIpTo();
            } if ("B".equals(useFlag)) {
                IpLocationBEntity location = this.ipLocationBRepository.findByIpNumber(ipNumber).orElse(new IpLocationBEntity());
                countryCode = location.getCountryCode();
                ipFrom = location.getIpFrom();
                ipTo = location.getIpTo();
            }
            
//            AiBankCountryNameEntity countryNameEntity = aiBankCountryNameRepository.findById(new AiBankCountryNameEntityPk(countryCode, locale)).orElse (new AiBankCountryNameEntity());
            List<AiBankCountryNameEntity> countryNames = aiBankCountryNameRepository.findByCountryName(countryCode);
            
            for (AiBankCountryNameEntity name: countryNames) {
                if (compareLastTwoChars(locale, name.getLocale())) {
                    countryName = name.getCountryName();    
                    break;
                }
            }
        } catch(Throwable e) {
            logger.error("getCountryName Failed: "+ ipAddress ,e);
        }
        
        vo.setCode(countryCode);
        vo.setName(countryName);
        vo.setLocale(locale);
        vo.setIpFrom(ipFrom);
        vo.setIpTo(ipTo);
        
        logger.debug("countryName: "+ countryName);
        return vo;
    }
    
    
    private  boolean compareLastTwoChars(String str1, String str2) {
        // 處理null值
        if (str1 == null || str2 == null) {
            return str1 == str2; // 兩個都是null則相等
        }
        
        // 檢查字串長度
        if (str1.length() < 2 || str2.length() < 2) {
            return false; // 長度不足2的字串視為不匹配
        }
        
        // 提取最後2個字符並轉為小寫比較
        String suffix1 = str1.substring(str1.length() - 2).toLowerCase();
        String suffix2 = str2.substring(str2.length() - 2).toLowerCase();
        
        return suffix1.equals(suffix2);
    }
    
}
