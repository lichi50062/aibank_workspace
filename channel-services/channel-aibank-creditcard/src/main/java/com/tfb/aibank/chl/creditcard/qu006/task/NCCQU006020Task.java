package com.tfb.aibank.chl.creditcard.qu006.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu006.model.NCCQU006020Rq;
import com.tfb.aibank.chl.creditcard.qu006.model.NCCQU006020Rs;
import com.tfb.aibank.chl.creditcard.qu006.model.NCCQU006020RsRepeats;
import com.tfb.aibank.chl.creditcard.qu006.service.NCCQU006Service;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.BonusExchangeResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.BonusExchangeResponseRepeat;

//@formatter:off
/**
* @(#)NCCQU006020Task.java
*
* <p>Description:點數回饋中心 兌換紀錄頁/p>
*
* <p>Modify History:</p>
* v1.0, 2023/08/10 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU006020Task extends AbstractAIBankBaseTask<NCCQU006020Rq, NCCQU006020Rs> {
    @Autowired
    protected NCCQU006Service service;
    @Autowired
    private CreditCardResource creditCardResource;

    @Override
    public void validate(NCCQU006020Rq rqData) throws ActionException {
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public void handle(NCCQU006020Rq rqData, NCCQU006020Rs rsData) throws ActionException {
        BonusExchangeResponse response = null;

        try {
            response = creditCardResource.getBonusExchangeList(getLoginUser().getCustId());
        }
        catch (ServiceException ex) {
            logger.error("getBonusExchangeList-電文CEW212R 失敗", ex);
            rsData.setStatus(2);
            return;
        }

        if (response.getItems() == null) {
            rsData.setStatus(1);
            return;
        }

        List<NCCQU006020RsRepeats> exchangeList = new ArrayList<NCCQU006020RsRepeats>();
        for (BonusExchangeResponseRepeat m : response.getItems()) {
            NCCQU006020RsRepeats one = new NCCQU006020RsRepeats();

            Date oneDate = new Date();
            /** 兌換日期 */
            String dateStr = m.getBtdTrdt();
            if (StringUtils.isNoneBlank(dateStr)) {
                dateStr = dateStr.replace("/", "").replace("-", "");
                oneDate = DateUtils.getROCDate(dateStr, "");
                one.setBtdTrdt(DateFormatUtils.CE_DATE_FORMAT_MONTH_DAY.format(oneDate));
            }
            else {
                one.setBtdTrdt("");
            }

            /** 商品中文 */
            one.setBtdChnm(m.getBtdChnm());

            /** 自付額 */
            one.setBtdAmt("$" + IBUtils.formatMoney(m.getBtdAmt()));

            /** 兌換點數 */
            one.setBtdPdns(IBUtils.formatMoney(m.getBtdPdns()));

            /** 兌換數量 */
            one.setBtdCnt(IBUtils.formatMoney(m.getBtdCnt()));

            /** 兌換狀態 */
            one.setBtdTxt1(m.getBtdTxt1());

            /** 顯示Header */
            one.setDisplayHeader(false);

            Calendar cal = Calendar.getInstance();
            cal.setTime(oneDate);

            /** 顯示Header Date */
            one.setHeaderDate(cal.getTimeInMillis());

            /** 顯示Header Month */
            one.setHeaderMonth(cal.get(Calendar.MONTH) + 1);

            /** 顯示用Header Month */
            one.setHeaderMonthDisplay(DateUtils.getMonthShortName(one.getHeaderMonth(), getLocale()));

            /** 顯示Header Year */
            one.setHeaderYear(cal.get(Calendar.YEAR));

            exchangeList.add(one);
        }

        IBUtils.sort(exchangeList, "headerDate", true);

        int thisYear = 0;
        int thisMonth = 0;
        for (NCCQU006020RsRepeats m : exchangeList) {
            if (m.getHeaderYear() != thisYear || m.getHeaderMonth() != thisMonth) {
                m.setDisplayHeader(true);
                thisYear = m.getHeaderYear();
                thisMonth = m.getHeaderMonth();
            }
        }

        rsData.setExchangeList(exchangeList);
    }
}
