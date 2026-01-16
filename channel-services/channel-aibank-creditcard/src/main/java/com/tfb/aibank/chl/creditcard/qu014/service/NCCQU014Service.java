package com.tfb.aibank.chl.creditcard.qu014.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu014.model.NCCQU014Input;
import com.tfb.aibank.chl.creditcard.qu014.model.NCCQU014Output;
import com.tfb.aibank.chl.creditcard.qu014.model.RateInfoGroup;
import com.tfb.aibank.chl.creditcard.resource.InformationResource;
import com.tfb.aibank.chl.creditcard.resource.dto.RateInfo;

//@formatter:off
/**
* @(#)NCCQU001Service.java
* 
* <p>Description:信用卡總覽 NCCQU001Service</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NCCQU014Service {
    private static final IBLog logger = IBLog.getLog(NCCQU014Service.class);

    @Autowired
    protected UserDataCacheService cardService;

    @Autowired
    InformationResource informationResource;

    public void queryRateInfos(NCCQU014Input input, NCCQU014Output output) throws ActionException {

        List<String> rateTypeNos = Stream.of("610", "927", "926", "520").toList();
        List<RateInfo> rateInfos = informationResource.queryRateInfos("1", rateTypeNos);


        if(CollectionUtils.isEmpty(rateInfos)){
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        rateInfos.stream().filter(in -> null != in.getCreateTime()).map(RateInfo::getCreateTime).sorted(Comparator.reverseOrder()).findFirst().ifPresent(tm-> {
            output.setInfoCreateTime(DateFormatUtils.CE_DATE_FORMAT.format(tm));
        });

        for (RateInfo info: rateInfos) {
            String rateTypeName = null != info.getRateTypeNameMap() ? info.getRateTypeNameMap().getOrDefault(input.getLocale().toString(), "") : "";
            rateTypeName = StringUtils.substringAfter(rateTypeName, "(");
            rateTypeName = StringUtils.substringBefore(rateTypeName, "\u5229\u7387");
            if (StringUtils.contains(rateTypeName, ")")) {
                rateTypeName = RegExUtils.removeAll(rateTypeName, "\\)");
            }
            info.setRateTypeName(rateTypeName);
            info.setEffectDateDsp(DateFormatUtils.CE_DATE_FORMAT.format(info.getEffectDate()));
            info.setRateTypeNameDsp(getRateTypeNoDisplay(info.getRateTypeNo()));
        }

        Map<String, List<RateInfo>> rateInfoMapByEffDate = rateInfos.stream().collect(Collectors.groupingBy(RateInfo::getEffectDateDsp));

        List<RateInfoGroup> groups = new ArrayList<>();
        for (Map.Entry<String, List<RateInfo>> entry : rateInfoMapByEffDate.entrySet()) {

            //最多放五次
            if( groups.size() >= 5)
                break;

            RateInfoGroup group = new RateInfoGroup();
            group.setEffectDateStr(entry.getKey());
            group.setRateInfos(entry.getValue());
            IBUtils.sort(group.getRateInfos(), "rate", true);
            groups.add(group);
        }

        IBUtils.sort(groups, "effectDateStr", true);
        groups.get(0).setNewest(true);
        output.setInfoGroups(groups);
    }


    /**
     * 依Rate_Type_No 取得對應文字
     * A.	‘610’：最高。
     * B.	‘927’：次高。
     * C.	‘926’：次低。
     * D.	‘520’：最低。
     * */
    public String getRateTypeNoDisplay(String rateTypeNo){
        var result = switch (rateTypeNo) {
            case "610" ->  I18NUtils.getMessage("ncc.ratetypeno.610.highest");
            case "927" ->  I18NUtils.getMessage("ncc.ratetypeno.927.high.second");
            case "926" ->  I18NUtils.getMessage("ncc.ratetypeno.926.low.second");
            case "520" ->  I18NUtils.getMessage("ncc.ratetypeno.520.low.lowest");
            default -> rateTypeNo;
        };
        return result;
    }


}
