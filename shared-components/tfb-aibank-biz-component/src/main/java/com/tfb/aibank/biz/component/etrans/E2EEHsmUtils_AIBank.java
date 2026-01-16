package com.tfb.aibank.biz.component.etrans;

import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.ibm.tw.commons.log.IBLog;

@Component
public class E2EEHsmUtils_AIBank extends E2EEUtils_AIBank {

    private static IBLog logger = IBLog.getLog(E2EEHsmUtils_AIBank.class);

    private static final String GENERATE_RANDOM_1_AND_SIGN = "/atm/generateRandom1AndSign";
    private static final String DECRYPT_RANDOM_2_AND_VERIFY = "/atm/decryptRandom2AndVerify";
    private static final String DES_DECIPHER = "/atm/desDecipher";
    private static final String ENCRYPT_DATA_AND_GENERATE_MAC = "/atm/encryptDataAndGenerateMAC";
    private static final String DECRYPT_DATA_AND_VERIFY_MAC = "/atm/decryptDataAndVerifyMAC";

    // 2.1 產生BHO元件用的RN1
    public RSAResult_JSB generateRandom1AndSign(String keyVersion) throws E2EEException {
        RSAResult_JSB result = new RSAResult_JSB();
        JsonObject rqBody = new JsonObject();
        rqBody.addProperty("keyVersion", keyVersion);

        JsonObject bodyObj = checkPostE2EERes(GENERATE_RANDOM_1_AND_SIGN, rqBody);

        JsonObject data = bodyObj.getAsJsonObject("data");
        result.setRSAKeyType(data.get("RSAKeyType").getAsInt());
        result.setRSAKeyLength(data.get("RSAKeyLength").getAsInt());
        result.setRSAKeyLengthAsByte(data.get("RSAKeyLengthAsByte").getAsInt());
        result.setTxCode(data.get("txCode").getAsString());
        result.setKeyVersion(data.get("keyVersion").getAsString());
        result.setRN1(data.get("RN1").getAsString());
        result.setClientEncRN1(data.get("clientEncRN1").getAsString());
        result.setServerSignRN1(data.get("serverSignRN1").getAsString());
        return result;
    }

    // 2.2 驗證元件回傳的RN2並產生session key
    public RSAResult_JSB decryptRandom2AndVerify(RSAResult_JSB result) throws E2EEException {
        JsonObject rqBody = new JsonObject();
        rqBody.addProperty("keyVersion", result.getKeyVersionClient());
        rqBody.addProperty("txCode", result.getTxCode());
        rqBody.addProperty("RN1", result.getRN1());
        rqBody.addProperty("encRn2", result.getEncRn2());
        rqBody.addProperty("encRn2Hash", result.getEncRn2Hash());
        rqBody.addProperty("sessionKeyHash", result.getSessionKeyHash());

        JsonObject bodyObj = checkPostE2EERes(DECRYPT_RANDOM_2_AND_VERIFY, rqBody);

        JsonObject data = bodyObj.getAsJsonObject("data");
        result.setSessionKey(data.get("sessionKey").getAsString());
        result.setEncSessionKey(data.get("encSessionKey").getAsString());
        result.setSessionKeyHash(data.get("sessionKeyHash").getAsString());
        return result;
    }

    // 2.3 利用session key解密元件加密後的密文
    public RSAResult_JSB desDecipher(RSAResult_JSB result) throws E2EEException {
        JsonObject rqBody = new JsonObject();
        rqBody.addProperty("txCode", result.getTxCode());
        rqBody.addProperty("sessionKey", result.getSessionKey());
        rqBody.addProperty("cipherText", result.getCipherText());
        rqBody.addProperty("isLocalEnv", result.getIsLocalEnv());

        JsonObject bodyObj = checkPostE2EERes(DES_DECIPHER, rqBody);

        JsonObject data = bodyObj.getAsJsonObject("data");
        result.setPlainText(data.get("plainText").getAsString());
        return result;
    }

    // 2.4 DES加密並產生MAC值
    public RSAResult_JSB encryptDataAndGenerateMAC(RSAResult_JSB result) throws E2EEException {
        JsonObject rqBody = new JsonObject();
        rqBody.addProperty("hsmEnvType", result.getHsmEnvType());
        rqBody.addProperty("plainText", result.getPlainText());

        JsonObject bodyObj = checkPostE2EERes(ENCRYPT_DATA_AND_GENERATE_MAC, rqBody);

        JsonObject data = bodyObj.getAsJsonObject("data");
        result.setCipherText(data.get("cipherText").getAsString());
        result.setMac(data.get("mac").getAsString());
        return result;
    }

    // 2.5 DES解密並驗證MAC值
    public RSAResult_JSB decryptDataAndVerifyMAC(RSAResult_JSB result) throws E2EEException {
        JsonObject rqBody = new JsonObject();
        rqBody.addProperty("hsmEnvType", result.getHsmEnvType());
        rqBody.addProperty("cipherText", result.getCipherText());

        JsonObject bodyObj = checkPostE2EERes(DECRYPT_DATA_AND_VERIFY_MAC, rqBody);

        JsonObject data = bodyObj.getAsJsonObject("data");
        result.setPlainText(data.get("plainText").getAsString());
        return result;
    }

    private JsonObject checkPostE2EERes(String url, JsonObject rqBody) throws E2EEException {
        JsonObject rsbody = postE2EE(url, rqBody);
        String errorCode = rsbody.get("errorCode").getAsString();
        if (errorCode.equals("0")) {
            return rsbody;
        }
        else {
            logger.error("呼叫" + url + "發生錯誤，errorCode: " + errorCode);
            throw new E2EEException(errorCode);
        }
    }

    // private static JsonObject getJsonObject1() {
    // JsonObject json = new JsonObject();
    // json.addProperty("errorCode", "0");
    // json.addProperty("errorMessage", "Generate Random1 and Sign Successful.");
    //
    // JsonObject data = new JsonObject();
    // data.addProperty("RSAKeyType", 3);
    // data.addProperty("RSAKeyLength", 1024);
    // data.addProperty("RSAKeyLengthAsByte", 128);
    // data.addProperty("txCode", "3039303231323837");
    // data.addProperty("keyVersion", "31303138");
    // data.addProperty("RN1", "52DF7F40C7DFABFD");
    // data.addProperty("clientEncRN1",
    // "2BEC82E59DC636A5FFBC9C2267A5D56FB889F6103BE17E3DAF70BF073E92F0F0A0366D4B03E71E58169F23F968DCC36A871C4C43508A82F536F362BB1406E10D5A39DBB23E56A786871DAF8A6236030835394B992B67758EBE933B86DC3B39CEB436CC8B2BB6C5BBCE1FE5B1C7332326F1562E2B02F827842D38602221E86F0D");
    // data.addProperty("serverSignRN1",
    // "30F155007508F83E0B43E7CB0C8FE9C62E3BCBFBE05CAD2D5E5C62E20C6A2E71F5848C49D4394919E1977FA156EE0958215AB4845A37F6FC929BEA64AC82D76DDADE889750FB4853B890D4362532924BA39E448BE2790216CDEA1EFB9DED42A778FFEC1870BD264C96B5FD0B77CF73A198CFD3C1ADA6905C8A5AB96521F8EB37");
    // json.add("data", data);
    // return json;
    // }
    //
    // private static JsonObject getJsonObject2() {
    // JsonObject json = new JsonObject();
    // json.addProperty("errorCode", "0");
    // json.addProperty("errorMessage", "Decrypt Random2 and Verify Successful.");
    //
    // JsonObject data = new JsonObject();
    // data.addProperty("sessionKey", "52DF7F40C7DFABFD102098B0E9233237");
    // data.addProperty("encSessionKey",
    // "78243EFD6F1F2D5D4E0D663E85F706D9C55CEEFBC531E20C3C1B9F74FD9AF2CA96F13BF5D0287EF8CEB10FA2782223DA3CDFD67DF67D98144EF72131B9396862E2136EAE49B59DF331375DD155EB783C62D700F1319E28FC1750955C3B0049F1CEF50564C2DFE8D1084D09C639ABE0D267B039FD2A95637BC027E52108152377");
    // data.addProperty("sessionKeyHash", "17E5BE444476F9AE144B2926088BAD357B01846F");
    // json.add("data", data);
    // return json;
    // }
    //
    // private static JsonObject getJsonObject3() {
    // JsonObject json = new JsonObject();
    // json.addProperty("errorCode", "0");
    // json.addProperty("errorMessage", "DES Decipher Successful.");
    //
    // JsonObject data = new JsonObject();
    // data.addProperty("plainText", "7549DEBBAE0759E7");
    // json.add("data", data);
    // return json;
    // }
    //
    // private static JsonObject getJsonObject4() {
    // JsonObject json = new JsonObject();
    // json.addProperty("errorCode", "0");
    // json.addProperty("errorMessage", "Encrypt Data and Generate MAC Successful.");
    //
    // JsonObject data = new JsonObject();
    // data.addProperty("cipherText",
    // "53834303935303230307220000000C20007D7BBFFEF50C564BCD1FCE69F72F613B3AD0AC806AB179A09AD0AC806AB179A09A327A0D9D7332BFB6CAD84A37F6F5102413316BA3136F4B653233B8F2A0B0796AD0AC806AB179A096641B26CDC563C0973630CD714F425688198C034766F1B4BA05E9DC48857CCCB7CD114FE9A289C6EAD0AC806AB179A09AD0AC806AB179A09AD0AC806AB179A0997CDF28421BDF5B8AD0AC806AB179A0953180598015026120874708640F7E8113146AF40F87C3A32");
    // data.addProperty("mac", "3146AF40F87C3A32");
    // json.add("data", data);
    // return json;
    // }
    //
    // private static JsonObject getJsonObject5() {
    // JsonObject json = new JsonObject();
    // json.addProperty("errorCode", "0");
    // json.addProperty("errorMessage", "Decrypt Data and Verify MAC Successful.");
    //
    // JsonObject data = new JsonObject();
    // data.addProperty("plainText",
    // "353834303935303231307220000002C00085313635353039313538303030303030313030303030303030303030303030303030303030303830383137323931303538343039353030323030333035332030303030303030303030303030303030363530303030303030343030313131303730312B303030303031333938313730302B303030303031333938313730302020202020202020202020202020202030303030303038303030303030303004040404");
    // json.add("data", data);
    // return json;
    // }

}
