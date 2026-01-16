package com.tfb.aibank.chl.creditcard.resource.dto;



import java.io.Serializable;
import java.util.List;

public class CEW344RResponse implements Serializable{
    private List<AdditionalBenefit> additionalBenefits;

    private boolean showPrivateBanking;

    public List<AdditionalBenefit> getAdditionalBenefits() {
        return additionalBenefits;
    }

    public void setAdditionalBenefits(List<AdditionalBenefit> additionalBenefits) {
        this.additionalBenefits = additionalBenefits;
    }

    public boolean isShowPrivateBanking() {
        return showPrivateBanking;
    }

    public void setShowPrivateBanking(boolean showPrivateBanking) {
        this.showPrivateBanking = showPrivateBanking;
    }
}
