package com.tfb.aibank.chl.general.qu001.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.menu.Menu;
import com.tfb.aibank.chl.component.exchangerate.ExchangeRate;
import com.tfb.aibank.chl.component.fxinterestrate.FxInterestRate;
import com.tfb.aibank.chl.general.qu001.model.ExchangeRateVo;
import com.tfb.aibank.chl.general.qu001.model.FxInterestRateVo;
import com.tfb.aibank.chl.general.qu001.model.MenuVo;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001Constants;

//@formatter:off
/**
* @(#)NGNQU001Util.java
*
* <p>Description:NGNQU001Util 工具類別</p>
* <p>物件過濾，物件複製，物件賦值</p>
*
* <p>Modify History:</p>
* v1.0, 2023/05/24, Marty Pan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NGNQU001Util {
    public static final Predicate<Menu> menuIsDisplay = m -> 1 == m.getIsDisplay();

    public static final Predicate<Menu> menuCanBeSearch = m -> 1 == m.getCanBeSearch();
    /**
     * <h3>過濾Menu取得業務大類Menus</h3>
     * <p>
     * i. 上層屬根目錄者，PARENT_MENU_ID = “ROOT”。 ii. 非隱藏功能，IS_DISPLAY = 1
     * </p>
     */
    public static final Predicate<Menu> isBusinessCateAndIsDisplay = m -> NGNQU001Constants.MENU_BUSINESS_ROOT.equals(m.getParentMenuId()) && 1 == m.getIsDisplay();
    /**
     * <h3>過濾Menu取得menuId有值且不等於ROOT的Menus</h3>
     * <p>
     * 上層屬根目錄者，PARENT_MENU_ID != “ROOT”。 ii. 非隱藏功能，IS_DISPLAY = 1
     * </p>
     */
    public static final Predicate<Menu> getParentMenuIdsNotRootMenu = m -> StringUtils.isNotEmpty(m.getParentMenuId()) && !NGNQU001Constants.MENU_BUSINESS_ROOT.equals(m.getParentMenuId());
    public static final Function<? super ExchangeRate, ExchangeRateVo> copyExchangeRate = ori -> {
        ExchangeRateVo vo = new ExchangeRateVo();
        vo.setRateKey(ori.getRateKey());
        vo.setTxTime(ori.getTxTime());
        vo.setRateType(ori.getRateType());
        vo.setCurrencyEname1(ori.getCurrencyEname1());
        vo.setCurrencyEname2(ori.getCurrencyEname2());
        vo.setExchangeTypeNo(ori.getExchangeTypeNo());
        vo.setBuy(ori.getBuy());
        vo.setSell(ori.getSell());
        vo.setSmallBuyCost(ori.getSmallBuyCost());
        vo.setSmallSellCost(ori.getSmallSellCost());
        vo.setLargeBuyCost(ori.getLargeBuyCost());
        vo.setLargeSellCost(ori.getLargeSellCost());
        vo.setCurrencySort(ori.getCurrencySort());
        vo.setCreateTime(ori.getCreateTime());
        vo.setToTwd(ori.getToTwd());
        vo.setToUsd(ori.getToUsd());
        vo.setWithPreferentialRate(ori.isWithPreferentialRate());
        vo.setSellOriginal(ori.getSellOriginal());
        vo.setSellAfterDiscount(ori.getSellAfterDiscount());
        vo.setDiscount(ori.getDiscount());
        return vo;
    };
    public static final Function<FxInterestRate, FxInterestRateVo> copyFxInterestRate = ori -> {
        FxInterestRateVo vo = new FxInterestRateVo();
        vo.setRateKey(ori.getRateKey());
        vo.setRateType(ori.getRateType());
        vo.setTxTime(ori.getTxTime());
        vo.setCurrencyEname(ori.getCurrencyEname());
        vo.setTypeNo(ori.getTypeNo());
        vo.setInterestRate(ori.getInterestRate());
        vo.setCurrencySort(ori.getCurrencySort());
        vo.setCreateTime(ori.getCreateTime());
        return vo;
    };

    private NGNQU001Util() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * <h3>過濾Menu取得LINK_TYPE 不等於 0(導功能)、1(開啟URL)、2(開啟Drawer)</h3>
     */
    // public static Predicate<Menu> isMenuFunctionGroupLinkType = m ->
    public static final MenuVo menuToMenuVo(Menu bizCateMenu, Menu funGroupMenu, Menu menu, Locale locale) {
        MenuVo vo = new MenuVo();
        vo.setMenuId(menu.getMenuId());
        vo.setTaskId(menu.getTaskId());
        vo.setMenuName(menu.getMenuName(locale.toString()));
        vo.setCategory(menu.getCategory());
        vo.setIconClass(menu.getIconClass());
        vo.setMenuVer(menu.getMenuVer());
        vo.setLinkType(menu.getLinkType());
        vo.setLinkParam(menu.getLinkParam());
        vo.setParentMenuId(menu.getParentMenuId());
        vo.setIsNew(menu.getIsNew());
        vo.setIsOpen(menu.getIsOpen());

        if (null != bizCateMenu) {
            vo.setBizCateMenuId(bizCateMenu.getMenuId());
            vo.setBizCateName(bizCateMenu.getMenuName(locale.toString()));
        }
        if (null != funGroupMenu) {
            vo.setFunctionGroupMenuId(funGroupMenu.getMenuId());
            vo.setFunctionGroupMenuName(funGroupMenu.getMenuName(locale.toString()));
            vo.setFunctionGroupIconClass(funGroupMenu.getIconClass());
        }

        return vo;
    }

    public static final MenuVo funGroupMenuToMenuVo(Menu bizCateMenu, Menu menu, Locale locale) {
        MenuVo vo = new MenuVo();
        vo.setMenuId(menu.getMenuId());
        vo.setTaskId(menu.getTaskId());
        vo.setMenuName(menu.getMenuName(locale.toString()));
        vo.setCategory(menu.getCategory());
        vo.setIconClass(menu.getIconClass());
        vo.setMenuVer(menu.getMenuVer());
        vo.setLinkType(menu.getLinkType());
        vo.setLinkParam(menu.getLinkParam());
        vo.setParentMenuId(menu.getParentMenuId());
        vo.setIsNew(menu.getIsNew());
        vo.setIsOpen(menu.getIsOpen());

        if (null != bizCateMenu) {
            vo.setBizCateMenuId(bizCateMenu.getMenuId());
            vo.setBizCateName(bizCateMenu.getMenuName(locale.toString()));
        }
        // 自己就是functiongroup
        {
            vo.setFunctionGroupMenuId(menu.getMenuId());
            vo.setFunctionGroupMenuName(menu.getMenuName(locale.toString()));
            vo.setFunctionGroupIconClass(menu.getIconClass());
        }

        return vo;
    }

    public static final boolean actBalGreaterThanZero(BigDecimal actBal) {
        actBal = null != actBal ? actBal.setScale(0, RoundingMode.FLOOR) : BigDecimal.ZERO;
        return actBal.compareTo(BigDecimal.ZERO) > 0;
    }

    public static final BigDecimal valueToZeroScale(BigDecimal val) {
        if (null == val)
            return BigDecimal.ZERO;
        return val.setScale(0, RoundingMode.FLOOR);
    }

}
