package com.tfb.aibank.chl.creditcard.qu016.service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;

import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu016.model.NCCQU016Output;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;

import com.tfb.aibank.chl.creditcard.resource.dto.AdditionalBenefit;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW344RResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class NCCQU016Service {

    private static final IBLog logger = IBLog.getLog(NCCQU016Service.class);

    @Autowired
    CreditCardResource creditCardResource;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public void getAdditionalBenefit(String custId, NCCQU016Output output) throws ActionException {

        BaseServiceResponse<CEW344RResponse> response = creditCardResource.sendCEW344R(custId);

        List<AdditionalBenefit> list = response.getData().getAdditionalBenefits();

        if (list != null && !list.isEmpty()) {

            // 標記是否為私銀權益 (pbTotalCount > 0)
            list.forEach(b -> b.setPrivateBanking(
                    b.getPbTotalUsableCount() != null && b.getPbTotalUsableCount().compareTo(BigDecimal.ZERO) > 0 ? "Y" : "N")
            );
            // 若私銀權益全部都是 "N"，即使可以已到可以顯示私銀日期也要關閉
            boolean hasAnyPb = list.stream()
                    .anyMatch(b -> "Y".equalsIgnoreCase(b.getPrivateBanking()));

            // 若權益中有任一私銀權益 且 開放查詢私銀權益
            boolean showPb = response.getData().isShowPrivateBanking() && hasAnyPb;

            /* 判斷是否至少有一項權益可用 */
            boolean hasAvailableBenefit = list.stream()
                    .map(AdditionalBenefit::getApplicable)
                    .filter(Objects::nonNull)
                    .anyMatch("Y"::equalsIgnoreCase);

            if (!hasAvailableBenefit) {
                throw ExceptionUtils.getActionException(ErrorCode.ADDITIONAL_BENEFIT_NOT_FOUND);
            }

            if(showPb){
                // 私銀權益排序
                list = list.stream()
                        .sorted(java.util.Comparator.comparingInt(b -> b.benefitType().rank()))
                        .toList();
            }else {
                list = list.stream()
                        // 濾掉 queryType = "7"、"8" 因為完全私銀權益
                        .filter(b -> {
                            String qt = b.getQueryType();
                            return qt == null || (!qt.trim().equals("7") && !qt.trim().equals("8"));
                        })
                        .sorted(java.util.Comparator.comparingInt(AdditionalBenefit::queryTypeRank))
                        .toList();
            }
            output.setDataList(list);

            LocalDate minStart = list.stream()
                    .map(AdditionalBenefit::getBenefitStartDate)
                    .map(this::parseSafely)
                    .filter(Objects::nonNull)
                    .min(LocalDate::compareTo)
                    .orElse(null);

            LocalDate maxEnd = list.stream()
                    .map(AdditionalBenefit::getBenefitEndDate)
                    .map(this::parseSafely)
                    .filter(Objects::nonNull)
                    .max(LocalDate::compareTo)
                    .orElse(null);

            output.setShowPrivateBanking(showPb);
            output.setEffectStartDate(minStart != null ? minStart.format(FMT) : "");
            output.setEffectEndDate(maxEnd   != null ? maxEnd.format(FMT)   : "");
            output.setDataList(list);

        }else{
            throw ExceptionUtils.getActionException(ErrorCode.ADDITIONAL_BENEFIT_NOT_FOUND);
        }
    };

    private LocalDate parseSafely(String text) {
        try {
            return text == null ? null : LocalDate.parse(text, FMT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}


