package com.tfb.aibank.chl.general.type;

//@formatter:off
/**
 * @(#)FastLoginAuthType.java
 *
 * <p>Description: 富邦證券資產帳戶總類</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/02/17, Benson
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public enum AssetAccountType {

    SECURITIES("證券帳戶"),
    SUBBROKER("複委託帳戶"),
    FUTURES   ("期貨帳戶");
    public String label;
    AssetAccountType(String label) { this.label = label; }

}
