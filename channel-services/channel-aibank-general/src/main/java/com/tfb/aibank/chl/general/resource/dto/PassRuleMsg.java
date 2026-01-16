/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.general.error.ErrorCode;

/**
 * @author john
 *
 */
public enum PassRuleMsg {

    SUCCESS(null, null), //
    PASSWORD_BLANK_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.LOGIN_RULE_NORMAL, PassRuleType.LOGIN_RULE_4PASS, PassRuleType.LOGIN_RULE_INITIAL, PassRuleType.CHANGE_UUID_RULE)), "001,002", null), //
    PASSWORD_LENGTH_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.LOGIN_RULE_NORMAL, PassRuleType.LOGIN_RULE_4PASS, PassRuleType.LOGIN_RULE_INITIAL, PassRuleType.CHANGE_UUID_RULE)), "003,004", "length"), //
    PASSWORD_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.LOGIN_RULE_4PASS, PassRuleType.LOGIN_RULE_INITIAL)), "005,006", "containNumber"), //
    PASSWORD_ENG_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.LOGIN_RULE_NORMAL, PassRuleType.CHANGE_UUID_RULE)), "013,014", "containEngUp,containNumber"),

    OLD_PASSWORD_BLANK_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "001", null), //
    NEW_PASSWORD_BLANK_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "002", null), //
    OLD_PASSWORD_LENGTH_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "003", null), //
    NEW_PASSWORD_LENGTH_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "004", null), //
    OLD_PASSWORD_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "005", null), //
    OLD_PASSWORD_ENG_NUM_ONLY_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "013", null), //
    NEW_PASSWORD_ENG_NUM_ONLY_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "014", null), //
    NEW_PASSWORD_ENG_NUM_MIX_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "008", null),

    COMPARE_UID_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "011", null), //
    COMPARE_UUID_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHANGE_UUID_RULE)), "012", null), //
    COMPARE_OLD_PASSWORD_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "009", null), //
    COMPARE_CONFIRM_PASSWORD_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "010", null),

    // 新密碼連續4碼英數&重複4碼英數字
    // 015:原網銀密碼不可有重複英數字
    // 016:新的網銀密碼不可有重複英數字
    // 017:原網銀密碼不可有連續英數字
    // 018:新的網銀密碼不可有連續英數字
    OLD_PASSWORD_REPEAT_ENG_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "015", null), //
    NEW_PASSWORD_REPEAT_ENG_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "016", null), //
    OLD_PASSWORD_CONSECUTIVE_ENG_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "017", null), //
    NEW_PASSWORD_CONSECUTIVE_ENG_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "018", null),

    UNDEFINED_ERR(null, null);

    List<PassRuleType> passRuleTypes;
    String failCodes;
    String invalidTagName;

    //
    PassRuleMsg(List<PassRuleType> passRuleTypes, String failCodes, String invalidTagName) {
        this.passRuleTypes = passRuleTypes;
        this.failCodes = failCodes;
        this.invalidTagName = invalidTagName;
    }

    PassRuleMsg(String failCodes, String invalidTagName) {
        this.failCodes = failCodes;
        this.invalidTagName = invalidTagName;
    }

    public PassRuleMsg getPassRuleErrMsg(PassRuleType checkRule, JsonObject rsbody) throws ActionException {
        String failCode = rsbody.get("failCode").getAsString();

        for (PassRuleMsg errType : PassRuleMsg.values()) {
            if (errType.passRuleTypes != null && errType.passRuleTypes.contains(checkRule)) {
                for (String fail : errType.failCodes.split(",")) {
                    if (fail.equals(failCode)) {
                        return errType;
                    }
                }
            }
        }
        throw ExceptionUtils.getActionException(ErrorCode.PASSWORD_VALIDATE_ERROR);

        // new ActionException("密碼驗證時發生錯誤" + failCode + ":" + failMessage);
    }

    public PassRuleMsg getPassRuleErrMsg_OLD(JsonObject reason) throws ActionException {
        String ruleName = reason.get("ruleName").getAsString();
        String param = reason.get("param").getAsString();
        if (!StringUtils.isBlank(param)) {
            ruleName = ruleName + ":" + param;
        }
        for (PassRuleMsg code : PassRuleMsg.values()) {
            for (String rulename : code.invalidTagName.split(",")) {
                if (ruleName.equals(rulename)) {
                    return code;
                }
            }
        }
        throw ExceptionUtils.getActionException(ErrorCode.PASSWORD_VALIDATE_ERROR);
    }

}
