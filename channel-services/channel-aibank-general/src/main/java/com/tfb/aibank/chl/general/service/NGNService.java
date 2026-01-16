package com.tfb.aibank.chl.general.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.ibmb.component.menu.MenuCacheManager;
import com.tfb.aibank.chl.component.task.RoleTaskCacheManager;
import com.tfb.aibank.chl.general.resource.CreditCardResource;
import com.tfb.aibank.chl.general.resource.InformationResource;
import com.tfb.aibank.chl.general.resource.InsuranceResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.utils.NGNUtils;
import com.tfb.aibank.chl.service.AIBankChannelService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.RoleTemplateType;

//@formatter:off
/**
 * @(#)NGNService.java
 * 
 * <p>Description:CHL 通用(NGN）服務類別，撰寫此大類共同的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

@Service("NGNService")
public class NGNService extends AIBankChannelService {

    @Autowired
    protected UserResource userResource;
    @Autowired
    protected InformationResource informationResource;
    @Autowired
    protected InsuranceResource insuranceResource;
    @Autowired
    protected CreditCardResource creditCardResource;

    @Autowired
    private NGNUtils utils;

    @Autowired
    private RoleTaskCacheManager roleTaskCacheManager;

    @Autowired
    private MenuCacheManager menuCacheManager;

    /**
     * 是否顯示「滿意度評分」
     * 
     * @param aibankUser
     * @return
     */
    public boolean checkShowSatisfactionRating(AIBankUser aibankUser) {
        // 檢查30天內是否已經顯示
        boolean display = informationResource.checkHasBeenShownSatisfaction(aibankUser.getCustId(), aibankUser.getUidDup(), aibankUser.getUserId(), aibankUser.getCompanyKind());
        return !display;
    }

    /**
     * 取得 Task 清單
     * 
     * @param user
     * @return
     */
    public String[] getTaskList(AIBankUser user) {

        RoleTemplateType roleTemplateType = user.getRoleTemplateType();
        List<String> allTasks = roleTaskCacheManager.getTasksForRole(roleTemplateType.getName());

        Set<String> taskList = new HashSet<String>();
        for (String task : allTasks) {
            taskList.add(task);
        }
        return taskList.toArray(String[]::new);
    }

    /**
     * 長者預設字體大小
     * 
     * @param date
     * @return
     */
    public int getFontSize(Date date) {

        String ageString = systemParamCacheManager.getValue("AIBANK", "ELDER_AGE");

        if (ageString == null || date == null)
            return 0;

        try {
            int age = Integer.parseInt(ageString);

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());

            cal.add(Calendar.YEAR, age * -1);

            if (date.before(cal.getTime())) {
                return 1;
            }
        }
        catch (NumberFormatException ex) {
            logger.warn("ELDER_AGE 未設定");
        }

        return 0;
    }

    /**
     * 字串轉Hex String
     * 
     * @param input
     * @return
     */
    public String stringToHex(String input) {
        StringBuilder hexString = new StringBuilder();

        for (char ch : input.toCharArray()) {
            String hex = Integer.toHexString(ch);
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
