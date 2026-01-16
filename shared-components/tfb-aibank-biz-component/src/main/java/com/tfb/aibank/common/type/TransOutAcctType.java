/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import java.util.List;
import java.util.function.Predicate;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;
import com.tfb.aibank.chl.component.userdatacache.AccountsResource;
import com.tfb.aibank.chl.component.userdatacache.model.EB5557891Res;
import com.tfb.aibank.chl.component.userdatacache.model.EB5557891ResRep;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)TransOutAcctType.java
 * 
 * <p>Description:轉出帳號類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * 命名規則，請遵照以下描述，若有缺漏，請自行補充
 * <ol>
 *  <li>轉出註記：TRANS_OUT</li>
 *  <li>文件編號：DOC_NO</li>
 *  <li>提高限額：RAISE_LIMIT</li>
 *  <li>幣別不等於XXX：CUR_CODE_NOT_XXX</li>
 *  <li>幣別不等於TWD：CUR_CODE_NOT_TWD</li>
 *  <li>臺幣：TW</li>
 *  <li>外幣：FX</li>
 *  <li>活存：SAVING</li>
 *  <li>定存：FIXED</li>
 *  <li>支存：CHECK</li>
 * </ol>
 */
// @formatter:on
public enum TransOutAcctType implements IEnum {

    /** 臺外幣帳務總覽/開啟轉帳服務-臺幣活存、臺幣支存、外幣活存 */
    TW_FX_TRANS_OUT_ACCT_OVERVIEW_TW_FX_SAVING_CHECK("臺外幣帳務總覽-臺幣活存、臺幣支存、外幣活存") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TW_TRANS_OUT_ACCT_OVERVIEW_TW_SAVING.filter().or(FX_TRANS_OUT_ACCT_OVERVIEW_FX_SAVING.filter()).or(TYPE_10_23);
        }
    },
    //
    // /** 開啟轉帳服務-臺幣活存、臺幣支存、外幣活存 (含不存在於 D005之歸戶帳號) */
    // TW_FX_OUT_ACCT_ALL("臺幣活存、臺幣支存、外幣活存(含不存在於 D005之歸戶帳號)") {
    // @Override
    // public Predicate<EB5557891ResRep> filter() {
    // return TW_TRANS_OUT_ACCT_OVERVIEW_TW_SAVING.filter().or(FX_TRANS_OUT_ACCT_OVERVIEW_FX_SAVING.filter()).or(TYPE_10_23);
    // }
    // },

    /** 臺幣帳務總覧 */
    TW_TRANS_OUT_ACCT_OVERVIEW("臺幣帳務總覧") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_20_01).or(TYPE_20_02).or(TYPE_20_03).or(TYPE_20_04).or(TYPE_20_21).or(TYPE_20_22).or(TYPE_20_23).or(TYPE_20_31).or(TYPE_20_32).or(TYPE_20_33).or(TYPE_20_41).or(TYPE_20_43).or(TYPE_20_44).or(TYPE_20_51).or(TYPE_20_52).or(TYPE_20_53).or(TYPE_31_01.and(CUR_CODE_NOT_XXX)).or(TYPE_32_01).or(TYPE_32_02).or(TYPE_32_03);
        }
    },

    /** 外幣帳務總覧 */
    FX_TRANS_OUT_ACCT_OVERVIEW("外幣帳務總覧") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return (TYPE_33_01.and(CUR_CODE_NOT_XXX)).or(TYPE_33_02.and(CUR_CODE_NOT_XXX)).or(TYPE_40_01.and(CUR_CODE_NOT_XXX)).or(TYPE_40_02.and(CUR_CODE_NOT_XXX)).or(TYPE_40_03.and(CUR_CODE_NOT_XXX)).or(TYPE_50_01).or(TYPE_50_02);
        }
    },

    /** 外幣帳務總覧, 含CURCODE=XXX */
    FX_TRANS_OUT_ACCT_OVERVIEW_XXX("外幣帳務總覧") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return (TYPE_33_01).or(TYPE_33_02).or(TYPE_40_01).or(TYPE_40_02).or(TYPE_40_03).or(TYPE_50_01).or(TYPE_50_02);
        }
    },

    /** 臺幣帳務總覧-臺幣活存 */
    TW_TRANS_OUT_ACCT_OVERVIEW_TW_SAVING("臺幣帳務總覧-臺幣活存") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_30).or(TYPE_31_01);
        }
    },

    /** 外幣帳務總覧-外幣活存 */
    FX_TRANS_OUT_ACCT_OVERVIEW_FX_SAVING("外幣帳務總覧-外幣活存") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return (TYPE_33_01.and(CUR_CODE_NOT_XXX)).or(TYPE_33_02.and(CUR_CODE_NOT_XXX)).or(TYPE_40_01.and(CUR_CODE_NOT_XXX)).or(TYPE_40_02.and(CUR_CODE_NOT_XXX)).or(TYPE_40_03.and(CUR_CODE_NOT_XXX));
        }
    },

    /** 臺幣活存轉出帳號(含文件編號) */
    TW_SAVING_DOC_NO_TRANS_OUT_ACCT("臺幣活存轉出帳號(含文件編號)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(DOC_NO_0134).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_30).or(TYPE_31_01));
        }
    },

    /** 臺幣活存轉出帳號(含文件編號、不含公教) */
    TW_SAVING_DOC_NO_TRANS_OUT_ACCT_EXCLUDE_TYPE1012("臺幣活存轉出帳號(含文件編號、不含公教)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(DOC_NO_0134).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_30).or(TYPE_31_01));
        }
    },

    /** 臺幣活存支存轉出帳號 */
    TW_SAVING_CHECK_TRANS_OUT_ACCT("臺幣活存支存轉出帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_31_01));
        }
    },

    /** 臺幣活存支存轉出帳號(含文件編號) */
    TW_SAVING_CHECK_DOC_NO_TRANS_OUT_ACCT("臺幣活存支存轉出帳號(含文件編號)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(DOC_NO_0134).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_31_01));
        }
    },

    /** 臺幣活存支存轉出帳號(含文件編號、不含公教) */
    TW_SAVING_CHECK_DOC_NO_TRANS_OUT_ACCT_EXCLUDE_TYPE1012("臺幣活存支存轉出帳號(含文件編號、不含公教)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(DOC_NO_0134).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_31_01));
        }
    },

    /** 臺幣支存帳號 */
    TW_CHECK_TRANS_OUT_ACCT("臺幣支存帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(TYPE_10_23);
        }
    },

    /** 臺幣定存轉出帳號(含文件編號) */
    TW_FIXED_TRANS_OUT_ACCT("臺幣定存轉出帳號(含文件編號)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(DOC_NO_0134).and(TYPE_10_10.or(TYPE_10_11).or(TYPE_31_01));
        }
    },

    /** 臺外幣轉出帳號 */
    TW_FX_TRANS_OUT_ACCT("臺外幣轉出帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(TYPE_31_01.or(TYPE_33_01).or(TYPE_33_02).or(TYPE_40_02));
        }
    },

    /** 臺外幣活存轉出帳號(含文件編號) */
    TW_FX_SAVING_DOC_NO_TRANS_OUT_ACCT("臺外幣活存轉出帳號(含文件編號)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(DOC_NO_0134).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_30).or(TYPE_31_01).or(TYPE_33_01).or(TYPE_33_02).or(TYPE_40_01).or(TYPE_40_02).or(TYPE_40_03));
        }
    },

    /** 換匯約定轉出帳號 */
    FX_REMIT_TRANS_OUT_ACCT("換匯約定轉出帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(TYPE_33_01.or(TYPE_33_02).or(TYPE_40_01).or(TYPE_40_02).or(TYPE_40_03));
        }
    },

    /** 換匯約定轉出帳號(含Curcod=XXX) */
    FX_REMIT_TRANS_OUT_ACCT_XXX("換匯約定轉出帳號(含Curcod=XXX)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and((TYPE_33_01.and(CUR_CODE_NOT_XXX)).or(TYPE_33_02.and(CUR_CODE_NOT_XXX)).or(TYPE_40_01).or(TYPE_40_02).or(TYPE_40_03));
        }
    },

    /** 外幣定存轉出帳號(含文件編號) */
    FX_FIXED_DOC_NO_TRANS_OUT_ACCT("外幣定存轉出帳號(含文件編號)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(CUR_CODE_NOT_TWD).and(DOC_NO_0134).and(TYPE_31_01.or(TYPE_33_01).or(TYPE_33_02).or(TYPE_40_01).or(TYPE_40_02).or(TYPE_40_03));
        }
    },

    /** 基金、投資扣款帳號 */
    FUND_INVEST_DEBIT_ACCT("基金、投資扣款帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(OPEN_TRANS).and(TRANS_IN).and(CUR_CODE_NOT_XXX).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_31_01).or(TYPE_33_01).or(TYPE_33_02).or(TYPE_40_01).or(TYPE_40_02).or(TYPE_40_03));
        }
    },

    /** 臺外幣存款帳號 (排除公庫活存、央行國庫局、同業存款、支存) */
    TW_FX_DEPOSIT_ACCT("臺外幣存款帳號 (排除公庫活存、央行國庫局、同業存款、支存)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12.and(WITHOUT_H_K_E)).or(TYPE_10_30.and(WITHOUT_F)).or(TYPE_31_01).or(TYPE_33_01).or(TYPE_33_02));
        }
    },

    /** 轉出帳號 */
    TRANS_OUT_ACCT("轉出帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX);
        }
    },

    /** 臺幣活存支存帳號 */
    TW_SAVING_CHECK_ACCT("臺幣活存支存帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return CUR_CODE_NOT_XXX.and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_31_01));
        }
    },

    /** 臺外幣帳務總覽 */
    TW_FX_SAVING_CHECK_TRANS_OUT_ACCT("臺外幣支存轉出帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_20_01).or(TYPE_20_02).or(TYPE_20_03).or(TYPE_20_04).or(TYPE_20_21).or(TYPE_20_22).or(TYPE_20_31).or(TYPE_20_32).or(TYPE_20_33).or(TYPE_20_41).or(TYPE_20_43).or(TYPE_20_44).or(TYPE_20_51).or(TYPE_20_52).or(TYPE_20_53).or(TYPE_31_01.and(CUR_CODE_NOT_XXX)).or(TYPE_32_01).or(TYPE_32_02).or(TYPE_32_03).or((TYPE_33_01.and(CUR_CODE_NOT_XXX)).or(TYPE_33_02.and(CUR_CODE_NOT_XXX)).or(TYPE_40_01.and(CUR_CODE_NOT_XXX)).or(TYPE_40_02.and(CUR_CODE_NOT_XXX)).or(TYPE_40_03.and(CUR_CODE_NOT_XXX)).or(TYPE_50_01).or(TYPE_50_02));
        }
    },

    /** 臺外幣轉帳適用的轉出帳號 (常用與約定帳號) */
    TW_FX_DOC_TRANS_OUT_ACCT("臺外幣轉帳適用的轉出帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_NOT_XXX).and(DOC_NO_0134).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_31_01).or(TYPE_33_01).or(TYPE_33_02).or(TYPE_40_01).or(TYPE_40_02).or(TYPE_40_03));
        }
    },

    /** 臺外幣預約交易適用的轉出帳號 */
    TW_FX_RESERVE_TRANS_OUT_ACCT("臺外幣預約交易適用的轉出帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_31_01.and(CUR_CODE_NOT_XXX)).or(TYPE_33_01.and(CUR_CODE_NOT_XXX)).or(TYPE_33_02.and(CUR_CODE_NOT_XXX)).or(TYPE_40_01).or(TYPE_40_02).or(TYPE_40_03));
        }
    },

    /** 台幣定存帳號 */
    TWD_DEPOSIT_ACCT("台幣定存帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return CUR_CODE_NOT_XXX.and(TYPE_20_01.or(TYPE_20_02).or(TYPE_20_03).or(TYPE_20_04).or(TYPE_20_21).or(TYPE_20_22).or(TYPE_20_31).or(TYPE_20_32).or(TYPE_20_33).or(TYPE_20_41).or(TYPE_20_43).or(TYPE_20_44).or(TYPE_20_51).or(TYPE_20_52).or(TYPE_20_53).or(TYPE_32_01).or(TYPE_32_02).or(TYPE_32_03));
        }
    },

    /** 外幣優利可承作帳號 */
    FX_DEPOSIT_PROJECT_TRANS_OUT_ACCT("外幣優利可承作帳號") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(IS_DIGITAL.or(TYPE_31_01).or(TYPE_33_01).or(TYPE_33_02).or(TYPE_40_02));
        }
    },

    /** 繳貸款扣款帳號(全部台幣帳號) */
    PAY_LOAN_ACCT_ALL("繳貸款扣款帳號(全部台幣帳號)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_IS_TWD).and(DOC_NO_014).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_14).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_31_01));
        }
    },
    /** 繳貸款扣款帳號(全部台幣帳號-可轉出) */
    PAY_LOAN_ACCT_TRANS_OUT("繳貸款扣款帳號-可轉出") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return TRANS_OUT.and(CUR_CODE_IS_TWD).and(DOC_NO_14).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_14).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_31_01));
        }
    },
    /** 常用與約定帳號管理(臺幣帳號) */
    TWD_COMMONLY_USED_AND_AGREED_ACCT("常用與約定帳號管理(臺幣帳號)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return CUR_CODE_NOT_XXX.and(DOC_NO_0134).and(TYPE_10_01.or(TYPE_10_10).or(TYPE_10_11).or(TYPE_10_12).or(TYPE_10_21).or(TYPE_10_22).or(TYPE_10_23).or(TYPE_10_30).or(TYPE_31_01));
        }
    },
    /** 常用與約定帳號管理(外幣帳號) */
    FOREIGN_COMMONLY_USED_AND_AGREED_ACCT("常用與約定帳號管理(外幣帳號)") {
        @Override
        public Predicate<EB5557891ResRep> filter() {
            return CUR_CODE_NOT_XXX.and(TYPE_33_01.or(TYPE_33_02).or(TYPE_40_01).or(TYPE_40_02).or(TYPE_40_03));
        }
    };

    /** 說明 */
    private String memo;

    TransOutAcctType(String memo) {
        this.memo = memo;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 讀取 AIBankUser 裡暫存的帳號資料
     * 
     * @param aibankUser
     * @return
     */
    public List<TransOutAccount> getTransOutAccounts(AIBankUser aibankUser) {
        return aibankUser.getTransOutAccountsMap().get(this);
    }

    /**
     * 發查電文，取得帳號清單
     * 
     * @param aibankUser
     * @param accountResource
     * @return
     */
    public EB5557891Res getTransOutAccounts(AIBankUser aibankUser, AccountsResource accountResource) {
        String custId = aibankUser.getCustIdAndCheckDup();
        String userId = aibankUser.getUserId();
        String nameCode = aibankUser.getNameCode();
        return accountResource.getTransOutAccounts(custId, userId, nameCode, this);
    }

    /**
     * 將帳號資料，存入 AIBankUser 暫存
     * 
     * @param aibankUser
     * @param accounts
     */
    public void setTransOutAccounts(AIBankUser aibankUser, List<TransOutAccount> accounts) {
        aibankUser.getTransOutAccountsMap().put(this, accounts);
    }

    /**
     * 過濾條件
     * 
     * @return
     */
    public abstract Predicate<EB5557891ResRep> filter();

    // =============== 以下作為過濾條件的設定，請盡量將規則細化 ===============
    /** 幣別不等於「三個X」 */
    Predicate<EB5557891ResRep> CUR_CODE_NOT_XXX = o -> StringUtils.notEquals(o.getCurCod(), AIBankConstants.MAIN_ACCOUNT_CUR_CODE);
    /** 幣別不等於「TWD」 */
    Predicate<EB5557891ResRep> CUR_CODE_NOT_TWD = o -> StringUtils.notEquals(o.getCurCod(), CurrencyCode.TWD);
    /** 幣別等於「TWD」 */
    Predicate<EB5557891ResRep> CUR_CODE_IS_TWD = o -> StringUtils.equals(o.getCurCod(), CurrencyCode.TWD);
    /** 轉出註記-可以轉出 */
    Predicate<EB5557891ResRep> TRANS_OUT = o -> o.isTransOutFlag();
    /** 文件編號-(前兩碼須為Y0、Y1、Y3、Y4) */
    Predicate<EB5557891ResRep> DOC_NO_0134 = o -> StringUtils.startsWithAny(o.getDocNo(), "Y0", "Y1", "Y3", "Y4");
    /** 文件編號-(前兩碼須為Y0、Y1、Y4) */
    Predicate<EB5557891ResRep> DOC_NO_014 = o -> StringUtils.startsWithAny(o.getDocNo(), "Y0", "Y1", "Y4");
    /** 文件編號-(前兩碼須為Y1、Y4) */
    Predicate<EB5557891ResRep> DOC_NO_14 = o -> StringUtils.startsWithAny(o.getDocNo(), "Y1", "Y4");
    /** 提高限額-有提高限額 */
    Predicate<EB5557891ResRep> RAISE_LIMIT = o -> o.isIncreaseFlag();
    /** 無對應EB5557891.D005的帳號 */
    Predicate<EB5557891ResRep> NOT_INCLUDED_D005 = o -> o.getEb5557891D005Rep() == null;
    /** 開啟轉帳服務註記-已開啟 */
    Predicate<EB5557891ResRep> OPEN_TRANS = o -> o.getEb5557891D005Rep() != null;
    /** 限約定轉入帳號註記 */
    Predicate<EB5557891ResRep> TRANS_IN = o -> (o.getEb5557891D005Rep() != null) && (StringUtils.isBlank(o.getEb5557891D005Rep().getAcnoInFlag()) || StringUtils.equalsAny(o.getEb5557891D005Rep().getAcnoInFlag(), "Y", "N"));
    /** 數存帳號 */
    Predicate<EB5557891ResRep> IS_DIGITAL = o -> StringUtils.equalsAny(o.getDigitalFlg(), "0", "1", "2", "3", "4", "5");

    Predicate<EB5557891ResRep> WITHOUT_H_K_E = o -> !StringUtils.equalsAny(o.getProdInd(), "H", "K", "E");
    Predicate<EB5557891ResRep> WITHOUT_F = o -> !StringUtils.equals("F", o.getProdInd());

    /** 臺幣活存 */
    Predicate<EB5557891ResRep> TW_SAVING = o -> StringUtils.equalsAny(o.getAcctType(), "10", "31") && !(StringUtils.equals(o.getAcctType(), "10") && StringUtils.equalsAny(o.getAcctSubType(), "21", "22", "23"));

    /** 外幣活存 */
    Predicate<EB5557891ResRep> FX_SAVING = o -> StringUtils.notEquals(o.getCurCod(), CurrencyCode.TWD) && StringUtils.equalsAny(o.getAcctType(), "33", "40");
    /** 外幣定存 */
    Predicate<EB5557891ResRep> FX_FIXED = o -> StringUtils.notEquals(o.getCurCod(), CurrencyCode.TWD) && StringUtils.equalsAny(o.getAcctType(), "50");

    /** 活期存款 */
    Predicate<EB5557891ResRep> TYPE_10_01 = o -> StringUtils.equals(o.getAcctType(), "10") && StringUtils.equals(o.getAcctSubType(), "01");
    /** 綜活-活期存款 */
    Predicate<EB5557891ResRep> TYPE_10_10 = o -> StringUtils.equals(o.getAcctType(), "10") && StringUtils.equals(o.getAcctSubType(), "10");
    /** 綜活-活期儲蓄 */
    Predicate<EB5557891ResRep> TYPE_10_11 = o -> StringUtils.equals(o.getAcctType(), "10") && StringUtils.equals(o.getAcctSubType(), "11");
    /** 公教存款 */
    Predicate<EB5557891ResRep> TYPE_10_12 = o -> StringUtils.equals(o.getAcctType(), "10") && StringUtils.equals(o.getAcctSubType(), "12");
    /** 公庫活存 */
    Predicate<EB5557891ResRep> TYPE_10_14 = o -> StringUtils.equals(o.getAcctType(), "10") && StringUtils.equals(o.getAcctSubType(), "14");
    /** 央行國庫局 */
    Predicate<EB5557891ResRep> TYPE_10_21 = o -> StringUtils.equals(o.getAcctType(), "10") && StringUtils.equals(o.getAcctSubType(), "21");
    /** 同業存款 */
    Predicate<EB5557891ResRep> TYPE_10_22 = o -> StringUtils.equals(o.getAcctType(), "10") && StringUtils.equals(o.getAcctSubType(), "22");
    /** 支票存款 */
    Predicate<EB5557891ResRep> TYPE_10_23 = o -> StringUtils.equals(o.getAcctType(), "10") && StringUtils.equals(o.getAcctSubType(), "23");
    /** 行員存款 */
    Predicate<EB5557891ResRep> TYPE_10_30 = o -> StringUtils.equals(o.getAcctType(), "10") && StringUtils.equals(o.getAcctSubType(), "30");

    /** 臺幣定存主帳戶 */
    Predicate<EB5557891ResRep> TYPE_20_01 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "01");
    /** 定存-固定 */
    Predicate<EB5557891ResRep> TYPE_20_02 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "02");
    /** 定存-機動 */
    Predicate<EB5557891ResRep> TYPE_20_03 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "03");
    /** 定存 */
    Predicate<EB5557891ResRep> TYPE_20_04 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "04");
    /** 零存整付-固定 */
    Predicate<EB5557891ResRep> TYPE_20_21 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "21");
    /** 零存整付-244 */
    Predicate<EB5557891ResRep> TYPE_20_22 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "22");
    /** 零存整付-階梯式 */
    Predicate<EB5557891ResRep> TYPE_20_23 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "23");
    /** 如意存款-定期 */
    Predicate<EB5557891ResRep> TYPE_20_31 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "31");
    /** 如意存款-定儲247 */
    Predicate<EB5557891ResRep> TYPE_20_32 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "32");
    /** 如意存款-定儲647 */
    Predicate<EB5557891ResRep> TYPE_20_33 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "33");
    /** 同業定存 */
    Predicate<EB5557891ResRep> TYPE_20_41 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "41");
    /** 可轉讓定存 */
    Predicate<EB5557891ResRep> TYPE_20_43 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "43");
    /** 郵匯定存 */
    Predicate<EB5557891ResRep> TYPE_20_44 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "44");
    /** 定存_無卡號124 */
    Predicate<EB5557891ResRep> TYPE_20_51 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "51");
    /** 定存_日盛資轉 */
    Predicate<EB5557891ResRep> TYPE_20_52 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "52");
    /** 聘雇人員離職定儲金 */
    Predicate<EB5557891ResRep> TYPE_20_53 = o -> StringUtils.equals(o.getAcctType(), "20") && StringUtils.equals(o.getAcctSubType(), "53");

    /** 臺幣一本萬利 */
    Predicate<EB5557891ResRep> TYPE_31_01 = o -> StringUtils.equals(o.getAcctType(), "31") && StringUtils.equals(o.getAcctSubType(), "01");
    /** 綜存存本-定儲 */
    Predicate<EB5557891ResRep> TYPE_32_01 = o -> StringUtils.equals(o.getAcctType(), "32") && StringUtils.equals(o.getAcctSubType(), "01");
    /** 綜存整整-定儲 */
    Predicate<EB5557891ResRep> TYPE_32_02 = o -> StringUtils.equals(o.getAcctType(), "32") && StringUtils.equals(o.getAcctSubType(), "02");
    /** 綜合定存-定期 */
    Predicate<EB5557891ResRep> TYPE_32_03 = o -> StringUtils.equals(o.getAcctType(), "32") && StringUtils.equals(o.getAcctSubType(), "03");
    /** 外幣一本萬利1 */
    Predicate<EB5557891ResRep> TYPE_33_01 = o -> StringUtils.equals(o.getAcctType(), "33") && StringUtils.equals(o.getAcctSubType(), "01");
    /** 外幣一本萬利2 */
    Predicate<EB5557891ResRep> TYPE_33_02 = o -> StringUtils.equals(o.getAcctType(), "33") && StringUtils.equals(o.getAcctSubType(), "02");

    /** 外幣存款 */
    Predicate<EB5557891ResRep> TYPE_40_01 = o -> StringUtils.equals(o.getAcctType(), "40") && StringUtils.equals(o.getAcctSubType(), "01");
    /** 外幣綜活 */
    Predicate<EB5557891ResRep> TYPE_40_02 = o -> StringUtils.equals(o.getAcctType(), "40") && StringUtils.equals(o.getAcctSubType(), "02");
    /** 外幣同業存款 */
    Predicate<EB5557891ResRep> TYPE_40_03 = o -> StringUtils.equals(o.getAcctType(), "40") && StringUtils.equals(o.getAcctSubType(), "03");

    /** 外定 */
    Predicate<EB5557891ResRep> TYPE_50_01 = o -> StringUtils.equals(o.getAcctType(), "50") && StringUtils.equals(o.getAcctSubType(), "01");
    /** 外綜定 */
    Predicate<EB5557891ResRep> TYPE_50_02 = o -> StringUtils.equals(o.getAcctType(), "50") && StringUtils.equals(o.getAcctSubType(), "02");

}
