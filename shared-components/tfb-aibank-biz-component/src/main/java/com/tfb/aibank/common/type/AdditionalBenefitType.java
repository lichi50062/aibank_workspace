package com.tfb.aibank.common.type;

public enum AdditionalBenefitType {

    DOMESTIC_AIRPORT_TRANSFER("1", 1, "國內機場接送"),
    ASIA_AIRPORT_TRANSFER   ("7", 2, "精選亞洲機場接送"),
    AIRPORT_LOUNGE          ("2", 3, "機場貴賓室"),
    GOLF                    ("8", 4, "高爾夫擊球優惠"),
    CITY_PARKING            ("4", 5, "市區停車"),
    ROAD_ASSISTANCE         ("5", 6, "道路救援"),
    AIRPORT_OUTER_PARKING   ("3", 7, "機場外圍停車"),
    HSR_TICKET_DISCOUNT     ("6", 8, "高鐵購票優惠"),
    UNKNOWN                 ("",  Integer.MAX_VALUE, "未知");

    private final String code;
    private final int rank;
    private final String label;

    AdditionalBenefitType(String code, int rank, String label) {
        this.code = code;
        this.rank = rank;
        this.label = label;
    }

    public int rank() { return rank; }
    public String code() { return code; }
    public String label() { return label; }

    private static final java.util.Map<String, AdditionalBenefitType> BY_CODE =
            java.util.Arrays.stream(values())
                    .collect(java.util.stream.Collectors.toMap(
                            AdditionalBenefitType::code, t -> t, (a,b)->a));

    public static AdditionalBenefitType fromCode(String code) {
        String k = (code == null) ? "" : code.trim();
        return BY_CODE.getOrDefault(k, UNKNOWN);
    }

    /** 依 queryType（字串）取得數字排序鍵；解析失敗排最後（非私銀排序用） */
    public static int queryTypeRank(String queryType) {
        if (queryType == null) return Integer.MAX_VALUE;
        String s = queryType.trim();
        if (s.isEmpty()) return Integer.MAX_VALUE;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}
