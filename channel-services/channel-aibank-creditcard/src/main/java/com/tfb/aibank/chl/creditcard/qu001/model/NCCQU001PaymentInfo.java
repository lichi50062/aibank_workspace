package com.tfb.aibank.chl.creditcard.qu001.model;

//@formatter:off
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RA021Repeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RB060Repeat; /**
* @(#)NCCQQU001CardData.java
* 
* <p>Description:信用卡總覽交易帳單額外資訊</p>
* <p>【前期應繳】與【繳(退)款金額】牌卡資料</p>
* <p>Modify History:</p>
* v1.0, 2025/02/24,
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQU001PaymentInfo {

    /** 上期應繳 X(9) */
    private BigDecimal aaolbl;
    /** 正負號 X(1) */
    private String aaolin;

    /** 上期應繳 - 顯示 */
    private String paymentAmtDueDisplay;

    /** 繳(退)款金額 - 顯示 */
    private String amtPayOrRefundDisplay;

    /** CEW314R.B060Repeats 【繳(退)款金額】明細 */
    private List<CEW314RB060Repeat> b060Repeats;

    public NCCQU001PaymentInfo() {
    }

    public NCCQU001PaymentInfo(CEW314RA021Repeat cew314RA021Repeat, List<CEW314RB060Repeat> b060Repeats) {

        if( null != cew314RA021Repeat ) {
            this.aaolbl = cew314RA021Repeat.getAaolbl();
            this.aaolin = cew314RA021Repeat.getAaolin();
            String plusMinus = "+".equals(this.aaolin) ? "" : this.aaolin;
            this.paymentAmtDueDisplay = plusMinus + "$" + IBUtils.formatMoney(aaolbl);
        }

        if(CollectionUtils.isNotEmpty(b060Repeats)) {

            BigDecimal amtPayOrRefund = ConvertUtils.str2BigDecimal("0");

            for(var b060Repeat : b060Repeats) {
                String sign = b060Repeat.getB1sing();

                BigDecimal camAmt = b060Repeat.getB1lcam();

                if( "-".equals(sign)) {
                    camAmt = camAmt.negate();
                }
                amtPayOrRefund = amtPayOrRefund.add(camAmt);

                String plusMinus = "+".equals(sign) ? "" : sign;

                b060Repeat.setAmountDisplay(plusMinus + "$" + IBUtils.formatMoney(camAmt.abs()));

                if( null != b060Repeat.getB1stdy() ) {
                   Date b1StdyDate = ConvertUtils.calendar2Date(b060Repeat.getB1stdy());
                   b060Repeat.setAccountDayDisplay(DateFormatUtils.format(b1StdyDate, "MM/dd"));
                }
                b060Repeat.setB1phdy(null);
                b060Repeat.setB1stdy(null);
            }

            String amtPayOrRefundSign = amtPayOrRefund.compareTo(BigDecimal.ZERO) < 0 ? "-" : "";

            this.amtPayOrRefundDisplay = amtPayOrRefundSign + "$" + IBUtils.formatMoney(amtPayOrRefund.abs());

            this.b060Repeats = b060Repeats;
        }

    }

    public BigDecimal getAaolbl() {
        return aaolbl;
    }

    public void setAaolbl(BigDecimal aaolbl) {
        this.aaolbl = aaolbl;
    }

    public String getAaolin() {
        return aaolin;
    }

    public void setAaolin(String aaolin) {
        this.aaolin = aaolin;
    }

    public String getAmtPayOrRefundDisplay() {
        return amtPayOrRefundDisplay;
    }

    public void setAmtPayOrRefundDisplay(String amtPayOrRefundDisplay) {
        this.amtPayOrRefundDisplay = amtPayOrRefundDisplay;
    }

    public List<CEW314RB060Repeat> getB060Repeats() {
        return b060Repeats;
    }

    public void setB060Repeats(List<CEW314RB060Repeat> b060Repeats) {
        this.b060Repeats = b060Repeats;
    }

    public String getPaymentAmtDueDisplay() {
        return paymentAmtDueDisplay;
    }

    public void setPaymentAmtDueDisplay(String paymentAmtDueDisplay) {
        this.paymentAmtDueDisplay = paymentAmtDueDisplay;
    }
}
