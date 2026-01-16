package com.tfb.aibank.chl.creditcard.ag001.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.ibm.tw.commons.log.IBLog;

//@formatter:off
/**
* @(#)NCCAG001Service.java
*
* <p>Description:預借現金密碼設定</p>
*
* <p>Modify History:</p>
* v1.0, 2023/08/10 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NCCAG001Service {
    private static final IBLog logger = IBLog.getLog(NCCAG001Service.class);

    public final static String NCCAG001_CACHE_KEY = "NCCAG001_CACHE_KEY";

    /**
     * 生成亂數 交易代碼
     * 
     * @return
     */
    public String getTxCode() {
        int leftLimit = 65; // letter 'A'
        int rightLimit = 90; // letter 'Z'
        int targetStringLength = 5;
        SecureRandom random = new SecureRandom();

        return random.ints(leftLimit, rightLimit + 1).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

    }

}
