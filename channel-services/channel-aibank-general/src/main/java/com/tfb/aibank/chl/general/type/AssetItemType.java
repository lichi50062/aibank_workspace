package com.tfb.aibank.chl.general.type;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@formatter:off
/**
 * @(#)FastLoginAuthType.java
 *
 * <p>Description: 富邦證券資產帳戶 所屬資產項目總類</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/02/17, Benson
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public enum AssetItemType {

    /** 國內股票 → 111 開頭；要打股票明細 API */
    DOMESTIC_STOCK(
            AssetAccountType.SECURITIES, "國內股票",
            List.of("111A01"),
            true),


    /** 海外股 + 境外基金 → 211 / 221 */
    OVERSEAS_STOCK_FUND(
            AssetAccountType.SUBBROKER, "海外股+境外基金",
            List.of("211A01", "221A01"),
            false),

    /** 國內期權 + 國外期貨 → 3C1 / 3F1 */
    FUTURES_OPTION(
            AssetAccountType.FUTURES, "國內期權+國外期貨",
            List.of("3C1A01", "3F1A01"),
            false);

    public AssetAccountType assetAccountType;
    public  String      label;
    private List<String> prefixes;
    public  boolean     needDetail;


    AssetItemType(AssetAccountType at, String label, List<String> prefixes,
            boolean needDetail) {
        this.assetAccountType  = at;
        this.label        = label;
        this.prefixes     = prefixes;
        this.needDetail   = needDetail;
    }

    /** 判斷 assetCode 屬於哪個 AssetItemType */
    public static Optional<AssetItemType> of(String assetCode){
        return Arrays.stream(values())
                .filter(t -> t.prefixes.stream()
                        .anyMatch(assetCode::startsWith))
                .findFirst();
    }
}
