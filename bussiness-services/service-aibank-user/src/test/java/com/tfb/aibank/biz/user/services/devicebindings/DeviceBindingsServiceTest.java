package com.tfb.aibank.biz.user.services.devicebindings;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.AESUtils;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

class DeviceBindingsServiceTest {

    private static final IBLog logger = IBLog.getLog(DeviceBindingsServiceTest.class);

    @Test
    void retrieveUserBindingInfo() throws Exception {
        byte[] custId = Hex.decodeHex("0115baf876faa234c6f6b630f668690d");
        byte[] userId = Hex.decodeHex("8bbf6c6da828ac6e88ad87252e22131e");

        // 使用與快登身分證號解密相同KEY
        String key = "Qxi10Jk2eFuj9EnJ"; //在資料庫為：Qxi10Jk2eFuj9EnJ，不須再解碼

        String decCustId = new String(AESUtils.decryptToBytes(custId, key.getBytes(StandardCharsets.UTF_8)));
        String decUserId = new String(AESUtils.decryptToBytes(userId, key.getBytes(StandardCharsets.UTF_8)));

        logger.info("encCustId: {}", custId);
        logger.info("decCustId: {}", decCustId);
        logger.info("encUserId: {}", userId);
        logger.info("decUserId: {}", decUserId);
    }
}