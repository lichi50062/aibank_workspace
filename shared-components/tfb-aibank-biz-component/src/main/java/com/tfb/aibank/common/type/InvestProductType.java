package com.tfb.aibank.common.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

public enum InvestProductType implements IEnum {

    GOLD("goldTtlAmt", "黃金", "nboqu001.drawer.header.gold", "NBOQU009"),

    NANO("nanoTtlAmt", "奈米投", "nboqu001.drawer.header.nano", "NNFOT001"),

    OFF_STOCKS("offStocksTtlAmt", "海外ETF/股票", "nboqu001.drawer.header.offstocks", "NBOQU006"),

    OFF_STRUCTURED("offStructuredTtlAmt", "境外結構型商品", "nboqu001.drawer.header.offstructured", "NBOQU008"),

    OFF_BONDS("offBondsTtlAmt", "海外債", "nboqu001.drawer.header.offbonds", "NBOQU005"),

    FUND("fundTtlAmt", "基金", "nboqu001.drawer.header.fund", "NBOQU015"),

    COMBO("comboTtlAmt", "組合式商品", "nboqu001.drawer.header.combo", "NBOQU007"),

    FOREIGN_BONDS("foreignBondsTtlAmt", "外國債券", "nboqu001.drawer.header.foreignBonds", "NBOQU018");

    private String memo;
    private String productTypeName;
    private String overviewProductTypeName;
    private String txn;

    InvestProductType(String memo, String productTypeName, String overviewProductTypeName, String txn) {
        this.memo = memo;
        this.productTypeName = productTypeName;
        this.overviewProductTypeName = overviewProductTypeName;
        this.txn = txn;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public String getOverviewProductTypeName() {
        return I18NUtils.getMessage(this.overviewProductTypeName);
    }

    public String getTxn() {
        return txn;
    }

    public static InvestProductType find(String memo) {
        for (InvestProductType invType : values()) {
            if (StringUtils.equals(invType.getMemo(), memo))
                return invType;
        }
        return null;
    }

    public static InvestProductType findByName(String name) {
        for (InvestProductType invType : values()) {
            if (StringUtils.equals(invType.name(), name))
                return invType;
        }
        return null;
    }
}
