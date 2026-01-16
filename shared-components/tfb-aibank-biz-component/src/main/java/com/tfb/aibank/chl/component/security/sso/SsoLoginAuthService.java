package com.tfb.aibank.chl.component.security.sso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.tfb.aibank.chl.component.security.sso.type.SsoLoginAuthType;
//@formatter:off
/**
* @(#)FastLoginAuthService.java
* 
* <p>Description:Fast Login Service</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/17, Benson
* <ol>
*  <li>初版</li>
* </ol>
* 
*/
//@formatter:on
@Service
public class SsoLoginAuthService {
	
	/**
	 * 產出及驗證 Auth 種類
	 * @param authType
	 * @return
	 * @throws IllegalArgumentException
	 */
    public SsoLoginAuthStep parse(String ssoAuthType) {
        try {
            // 驗證輸入
            validateFastLoginAuthTypeString(ssoAuthType);

            // 處理加號分隔的情況
            String[] plusParts = ssoAuthType.split("\\+");
            if (plusParts.length > 2) {
                throw new IllegalArgumentException( "不能包含多個加號");
            }

            String mainPart = plusParts[0];
            SsoLoginAuthType additionalType = null;
            
            // 驗證加號後的部分
            if (plusParts.length > 1) {
                String additionalCode = plusParts[1];
                if (!StringUtils.equals("4", additionalCode)) {
                    throw new IllegalArgumentException("+後僅允許為4");
                }
                additionalType = SsoLoginAuthType.SMS_OTP;
            }

            // 處理井號分隔的情況
            String[] hashParts = mainPart.split("#");
            if (hashParts.length > 2) {
                throw new IllegalArgumentException("不能包含多個井號");
            }

            String primaryPart = hashParts[0];
            SsoLoginAuthType fallbackType = null;
            
            // 驗證井號後的部分
            if (hashParts.length > 1) {
                String fallbackCode = hashParts[1];
                if (!StringUtils.equals("3", fallbackCode) && !StringUtils.equals("4", fallbackCode)) {
                    throw new IllegalArgumentException("#後僅允許為3或4");
                }
                fallbackType = SsoLoginAuthType.find(fallbackCode);
            }

            // 處理主要認證類型（OR關係）
            List<String> primaryCodes = Arrays.asList(primaryPart.split("\\|"));
            
            // 驗證主要認證類型
            validatePrimaryTypes(primaryCodes);
            
            List<SsoLoginAuthType> primaryTypes = primaryCodes.stream()
                    .map(SsoLoginAuthType::find)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

            // 創建並返回認證步驟物件
            SsoLoginAuthStep authStep = new SsoLoginAuthStep(primaryTypes);
            if (fallbackType != null) {
                authStep.setFallbackType(fallbackType);
            }
            if (additionalType != null) {
                authStep.setAdditionalType(additionalType);
            }

            return authStep;

 
        } catch (Exception e) {
            throw new IllegalArgumentException(  "平台資訊無效驗證失敗");
        }
    }

    private void validateFastLoginAuthTypeString(String fastLoginAuthTypeStr) {
        if (fastLoginAuthTypeStr == null || fastLoginAuthTypeStr.trim().isEmpty()) {
            throw new IllegalArgumentException( "認證類型不能為空");
        }

        // 基本格式驗證
        if (!fastLoginAuthTypeStr.matches("^[1-3](\\|[1-3])*(#[34])?(\\+4)?$")) {
            throw new IllegalArgumentException( "認證類型格式不正確");
        }
    }

    private void validatePrimaryTypes(List<String> primaryCodes) {
        // 檢查是否有重複的認證類型
        if (primaryCodes.size() != new HashSet<>(primaryCodes).size()) {
            throw new IllegalArgumentException( "主要認證類型不能重複");
        }

        // 檢查每個認證類型的有效性
        for (String code : primaryCodes) {
            if (!isValidPrimaryType(code)) {
                throw new IllegalArgumentException ("無效的主要認證類型: " + code);
            }
        }

        // 檢查是否包含SMS_OTP(4)
        if (primaryCodes.contains("4")) {
            throw new IllegalArgumentException( "4僅能在+或#後出現");
        }
    }

    private boolean isValidPrimaryType(String code) {
        return "1".equals(code) || "2".equals(code) || "3".equals(code);
    }

}
