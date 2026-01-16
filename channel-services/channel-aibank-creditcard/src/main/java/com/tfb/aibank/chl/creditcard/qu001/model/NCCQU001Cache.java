/**
 * 
 */
package com.tfb.aibank.chl.creditcard.qu001.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW306RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW315RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.VB0051Response;

//@formatter:off
/**
* @(#)NCCQU001Cache.java
* 
* <p>Description:信用卡總覽 cache</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQU001Cache {

    /**
     * 卡片資料暫存
     */
    private List<NCCQQU001CardData> cardInfos;

    /**
     * 卡片資料暫存 tab2
     */
    private List<NCCQQU001CardData> cardInfosTab2;

    /** 卡片資料暫存 (正卡項下副卡) */
    private List<NCCQQU001CardData> underCardInfos;

    /**
     * 帳務資料(主卡)
     */
    private CEW303RResponse cew303r;

    /**
     * 帳務資料(附卡)
     */
    private Map<String, CEW303RResponse> cew303rAdds = new HashMap<>();

    /**
     * 帳單明細
     */
    private CEW314RResponse cew314r;

    /**
     * 好多金餘額資料
     */
    private VB0051Response vb0051;

    /**
     * 好多金餘額上期資料
     */
    private VB0051Response lastVb0051;

    /**
     * 紅利、哩程回饋資料
     */
    private CEW306RResponse cew306r;

    /**
     * 自動扣繳設定資料
     */
    private Boolean automatic;

    /**
     * 用來暫存每張卡片對應的帳單資料
     */
    private HashMap<String, List<NCCQQU001HistoricalBill>> cardNoMappingBills = new HashMap<>();

    /**
     * 結帳日前消費明細資料
     */
    private List<CEW205RRepeat> cew205RRepeats;

    /** 是否為好市多正卡持有人 */
    private boolean isCostcoMerber;

    /** 是否為「COMPANY_KIND = 4，且為附卡人身分」 */
    private boolean additionalCardholder;

    /**
     * 信用卡帳單分期查詢
     */
    private CEW315RResponse cew315Rresponse;

    /**
     * @return the isCostcoMerber
     */
    public boolean isCostcoMerber() {
        return isCostcoMerber;
    }

    /**
     * @param isCostcoMerber
     *            the isCostcoMerber to set
     */
    public void setCostcoMerber(boolean isCostcoMerber) {
        this.isCostcoMerber = isCostcoMerber;
    }

    /**
     * @return the cardInfos
     */
    public List<NCCQQU001CardData> getCardInfos() {
        return cardInfos;
    }

    /**
     * @param cardInfos
     *            the cardInfos to set
     */
    public void setCardInfos(List<NCCQQU001CardData> cardInfos) {
        this.cardInfos = cardInfos;
    }

    /**
     * @return the cew303rAdds
     */
    public Map<String, CEW303RResponse> getCew303rAdds() {
        return cew303rAdds;
    }

    /**
     * @param cew303rAdds
     *            the cew303rAdds to set
     */
    public void setCew303rAdds(Map<String, CEW303RResponse> cew303rAdds) {
        this.cew303rAdds = cew303rAdds;
    }

    /**
     * @return the cew314r
     */
    public CEW314RResponse getCew314r() {
        return cew314r;
    }

    /**
     * @param cew314r
     *            the cew314r to set
     */
    public void setCew314r(CEW314RResponse cew314r) {
        this.cew314r = cew314r;
    }

    /**
     * @return the vb0051
     */
    public VB0051Response getVb0051() {
        return vb0051;
    }

    /**
     * @param vb0051
     *            the vb0051 to set
     */
    public void setVb0051(VB0051Response vb0051) {
        this.vb0051 = vb0051;
    }

    /**
     * @return the cew306r
     */
    public CEW306RResponse getCew306r() {
        return cew306r;
    }

    /**
     * @param cew306r
     *            the cew306r to set
     */
    public void setCew306r(CEW306RResponse cew306r) {
        this.cew306r = cew306r;
    }

    /**
     * @return the automatic
     */
    public Boolean getAutomatic() {
        return automatic;
    }

    /**
     * @param automatic
     *            the automatic to set
     */
    public void setAutomatic(Boolean automatic) {
        this.automatic = automatic;
    }

    /**
     * @return the cardNoMappingBills
     */
    public HashMap<String, List<NCCQQU001HistoricalBill>> getCardNoMappingBills() {
        return cardNoMappingBills;
    }

    /**
     * @param cardNoMappingBills
     *            the cardNoMappingBills to set
     */
    public void setCardNoMappingBills(HashMap<String, List<NCCQQU001HistoricalBill>> cardNoMappingBills) {
        this.cardNoMappingBills = cardNoMappingBills;
    }

    /**
     * @return the cew303r
     */
    public CEW303RResponse getCew303r() {
        return cew303r;
    }

    /**
     * @param cew303r
     *            the cew303r to set
     */
    public void setCew303r(CEW303RResponse cew303r) {
        this.cew303r = cew303r;
    }

    /**
     * @return the cew205RRepeats
     */
    public List<CEW205RRepeat> getCew205RRepeats() {
        return cew205RRepeats;
    }

    /**
     * @param cew205rRepeats
     *            the cew205RRepeats to set
     */
    public void setCew205RRepeats(List<CEW205RRepeat> cew205rRepeats) {
        cew205RRepeats = cew205rRepeats;
    }

    /**
     * @return the lastVb0051
     */
    public VB0051Response getLastVb0051() {
        return lastVb0051;
    }

    /**
     * @param lastVb0051
     *            the lastVb0051 to set
     */
    public void setLastVb0051(VB0051Response lastVb0051) {
        this.lastVb0051 = lastVb0051;
    }

    /**
     * @return the underCardInfos
     */
    public List<NCCQQU001CardData> getUnderCardInfos() {
        return underCardInfos;
    }

    /**
     * @param underCardInfos
     *            the underCardInfos to set
     */
    public void setUnderCardInfos(List<NCCQQU001CardData> underCardInfos) {
        this.underCardInfos = underCardInfos;
    }

    public List<NCCQQU001CardData> getCardInfosTab2() {
        return cardInfosTab2;
    }

    public void setCardInfosTab2(List<NCCQQU001CardData> cardInfosTab2) {
        this.cardInfosTab2 = cardInfosTab2;
    }

    public boolean isAdditionalCardholder() {
        return additionalCardholder;
    }

    public void setAdditionalCardholder(boolean additionalCardholder) {
        this.additionalCardholder = additionalCardholder;
    }

    public CEW315RResponse getCew315Rresponse() {
        return cew315Rresponse;
    }

    public void setCew315Rresponse(CEW315RResponse cew315Rresponse) {
        this.cew315Rresponse = cew315Rresponse;
    }

}
