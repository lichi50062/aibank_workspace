package com.tfb.aibank.chl.component.security.sso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tfb.aibank.chl.component.security.sso.type.SsoLoginAuthType;

//@formatter:off
/**
* @(#)FastLoginAuthStep.java
* 
* <p>Description:Fast Login Step</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/17, Benson
* <ol>
*  <li>初版</li>
* </ol>
* 
*/
//@formatter:on
public class SsoLoginAuthStep implements Serializable {

    private static final long serialVersionUID = 2992322996639429072L;
    
	private List<SsoLoginAuthType> primaryTypes; // OR關係的認證類型, 只能是1,2,3 
    private SsoLoginAuthType fallbackType;  // #後的認證類型, 只能是 3 , 4
    private SsoLoginAuthType additionalType; // +後的認證類型, 只能是 3, 4
    private List<SsoLoginAuthType> usedAuthTypes; 

    public SsoLoginAuthStep(List<SsoLoginAuthType> primaryTypes) {
        this.primaryTypes = primaryTypes;
    }
    
   
    public Optional<SsoLoginAuthType> getNextAuthType(SsoLoginAuthType currentType, boolean isSuccess) {
        
       if (currentType == null) {
            if (!primaryTypes.isEmpty()) {
                SsoLoginAuthType firstType = primaryTypes.get(0);
                usedAuthTypes.add(firstType);
                return Optional.of(firstType);
            }
            return Optional.empty();
        }
        if (usedAuthTypes == null ) {
            this.usedAuthTypes = new ArrayList<>();
        }
        
        if (!usedAuthTypes.contains(currentType)) {
            usedAuthTypes.add(currentType);
        }
        
        if (!isSuccess && fallbackType != null && !usedAuthTypes.contains(fallbackType)) {
//            usedAuthTypes.add(fallbackType);
            return Optional.of(fallbackType);
        }

        // 如果認證成功且有額外認證
        if (isSuccess && additionalType != null && !usedAuthTypes.contains(additionalType)) {
//            usedAuthTypes.add(additionalType);
            return Optional.of(additionalType);
        }

        // 沒有下一步認證
        return Optional.empty();
    
    }
     
 
    public void setPrimaryTypes(List<SsoLoginAuthType> primaryTypes) {
        this.primaryTypes = primaryTypes;
    }

    public List<SsoLoginAuthType> getPrimaryTypes() {
        return primaryTypes;
    }

    public Optional<SsoLoginAuthType> getFallbackType() {
        return Optional.ofNullable(fallbackType);
    }

    public Optional<SsoLoginAuthType> getAdditionalType() {
        return Optional.ofNullable(additionalType);
    }

    public void setFallbackType(SsoLoginAuthType fallbackType) {
        this.fallbackType = fallbackType;
    }

    public void setAdditionalType(SsoLoginAuthType additionalType) {
        this.additionalType = additionalType;
    }
    
    /**
     * 檢查是否有快速登入方式（圖形密碼或生物辨識）
     */
    public boolean hasQuickLogin() {
        return hasPattern() || hasBiometric();
    }

    /**
     * 檢查是否有圖形密碼認證
     */
    public boolean hasPattern() {
        return primaryTypes.contains(SsoLoginAuthType.PATTERN);
    }

    /**
     * 檢查是否有生物辨識認證
     */
    public boolean hasBiometric() {
        return primaryTypes.contains(SsoLoginAuthType.BIOMETRIC) ;
    }

    /**
     * 檢查是否有三層式密碼認證
     */
    public boolean hasThreeLayer() {
        return primaryTypes.contains(SsoLoginAuthType.THREE_LAYER) ||
                this.hasThreeLayerInAdditionalType() ||
               this.hasThreeLayerInFallbackType();
    }
    /**
     * 
     * @return
     */
    public boolean hasThreeLayerInParimary() {
        return primaryTypes.contains(SsoLoginAuthType.THREE_LAYER) ;
    }
    

    public boolean hasThreeLayerInFallbackType() {
        return getFallbackType().map(type -> type == SsoLoginAuthType.THREE_LAYER).orElse(false);
    }
    
    public boolean hasThreeLayerInAdditionalType() {
        return getAdditionalType().map(type -> type == SsoLoginAuthType.THREE_LAYER).orElse(false);
    }
    /**
     * 檢查是否有簡訊OTP認證
     */
    public boolean hasSmsOtp() {
        return 
               hasSmsOtpInFallbackType() ||
               hasSmsOtpInAdditionalType();
    }
    
    public boolean hasSmsOtpInFallbackType() {
        return getFallbackType().map(type -> type == SsoLoginAuthType.SMS_OTP).orElse(false);
    }
    
    public boolean hasSmsOtpInAdditionalType() {
        return        getAdditionalType().map(type -> type == SsoLoginAuthType.SMS_OTP).orElse(false);
    }
    /**
     * 檢查是否有多種驗證方式（主要認證類型是否多於一種）
     */
    public boolean hasMultipleAuthTypes() {
        return primaryTypes.size() > 1;
    }

    /**
     * 檢查是否支援失敗後的備用驗證方式（是否有#後的認證類型）
     */
    public boolean hasFallbackAuth() {
        return fallbackType != null;
    }

    /**
     * 檢查是否需要額外的驗證步驟（是否有+後的認證類型）
     */
    public boolean hasAdditionalAuth() {
        return additionalType != null;
    }
    
    /**
     * 獲取主要認證類型的代碼列表
     * @return 包含所有主要認證類型代碼的List
     */
    public List<String> getPrimaryTypeCodes() {
        return primaryTypes.stream()
                .map(SsoLoginAuthType::getCode)
                .collect(Collectors.toList());
    }

    /**
     * 獲取備用認證類型的代碼
     * @return Optional<String> 備用認證類型的代碼，如果沒有則返回空
     */
    public String getFallbackTypeCode() {
        return getFallbackType().map(SsoLoginAuthType::getCode).orElse( "" );
    }

    /**
     * 獲取額外認證類型的代碼
     * @return Optional<String> 額外認證類型的代碼，如果沒有則返回空
     */
    public String getAdditionalTypeCode() {
        return getAdditionalType().map(SsoLoginAuthType::getCode).orElse("");
    }



}
