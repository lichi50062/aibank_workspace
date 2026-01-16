package com.tfb.aibank.chl.general.qu001.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.component.newfunctionintro.NewFunctionIntro;
import com.tfb.aibank.chl.general.resource.dto.NavigateSetting;
import com.tfb.aibank.chl.general.resource.dto.RespectInfo;

// @formatter:off
/**
 * @(#)NGNQU001010Rs.java
 *
 * <p>Description: NGNQU001010Rs</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[NGNQU001010Rs]</li>
 * </ol>
 */
//@formatter:on
@Component
public class NGNQU001010Rs implements RsData {

	/** 是否已登入 */
	private boolean loggedIn;

	/** 未讀取訊息量 */
	private Integer unreadCount;

	private RespectInfo respectInfo;

	private boolean customerBirthday;

	private String userNickname;

    /** local storage key */
    private String cacheKey;

	/** 首頁要顯示的牌卡id 和順序 */
	private List<Integer> displayHomepageCardIds;

	private List<Integer> displayHomepageCardIdsDefault;

	/** 公告類訊息DB[SYSTEM_NOTIFICATION_RECORD] 的ITEM_NO(參照BIZ來源)*/
	private List<String> systemNoticeRecItemNos;

	private List<Integer> allHomepageCardIds;

	/** 新功能介紹Table Model */
	private List<NewFunctionIntro> newFunctionIntros;
	
	/**失聯戶回傳*/
	private UnreachableCustomerInfo unreachableCustomerInfo;

	/**導頁設定內容*/
	private NavigateSetting navigateSetting;


	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Integer getUnreadCount() {
		return unreadCount;
	}

	public void setUnreadCount(Integer unreadCount) {
		this.unreadCount = unreadCount;
	}

	public RespectInfo getRespectInfo() {
		return respectInfo;
	}

	public void setRespectInfo(RespectInfo respectInfo) {
		this.respectInfo = respectInfo;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public List<Integer> getDisplayHomepageCardIds() {
		return displayHomepageCardIds;
	}

	public void setDisplayHomepageCardIds(List<Integer> displayHomepageCardIds) {
		this.displayHomepageCardIds = displayHomepageCardIds;
	}

	public List<Integer> getDisplayHomepageCardIdsDefault() {
		return displayHomepageCardIdsDefault;
	}

	public void setDisplayHomepageCardIdsDefault(List<Integer> displayHomepageCardIdsDefault) {
		this.displayHomepageCardIdsDefault = displayHomepageCardIdsDefault;
	}

	public List<String> getSystemNoticeRecItemNos() {
		return systemNoticeRecItemNos;
	}

	public void setSystemNoticeRecItemNos(List<String> systemNoticeRecItemNos) {
		this.systemNoticeRecItemNos = systemNoticeRecItemNos;
	}

	public List<Integer> getAllHomepageCardIds() {
		return allHomepageCardIds;
	}

	public void setAllHomepageCardIds(List<Integer> allHomepageCardIds) {
		this.allHomepageCardIds = allHomepageCardIds;
	}

    /**
     * @return the cacheKey
     */
    public String getCacheKey() {
        return cacheKey;
    }

    /**
     * @param cacheKey
     *            the cacheKey to set
     */
    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

	public List<NewFunctionIntro> getNewFunctionIntros() {
		return newFunctionIntros;
	}

	public void setNewFunctionIntros(List<NewFunctionIntro> newFunctionIntros) {
		this.newFunctionIntros = newFunctionIntros;
	}

	public boolean isCustomerBirthday() {
		return customerBirthday;
	}

	public void setCustomerBirthday(boolean customerBirthday) {
		this.customerBirthday = customerBirthday;
	}

	/**
	 * @return the unreachableCustomerInfo
	 */
	public UnreachableCustomerInfo getUnreachableCustomerInfo() {
		return unreachableCustomerInfo;
	}

	/**
	 * @param unreachableCustomerInfo
	 *            the unreachableCustomerInfo to set
	 */
	public void setUnreachableCustomerInfo(UnreachableCustomerInfo unreachableCustomerInfo) {
		this.unreachableCustomerInfo = unreachableCustomerInfo;
	}

    public NavigateSetting getNavigateSetting() {
        return navigateSetting;
    }

    public void setNavigateSetting(NavigateSetting navigateSetting) {
        this.navigateSetting = navigateSetting;
    }

}
