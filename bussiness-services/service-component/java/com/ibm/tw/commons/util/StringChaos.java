package com.ibm.tw.commons.util;

import java.security.SecureRandom;

import com.ibm.tw.commons.exception.CryptException;

// @formatter:off
/**
 * @(#)StringChaos.java
 *
 * <p>Description:富證字串混亂類別</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/31, Andy Kuo
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class StringChaos {

    /** 基礎字元清單 */
    private String baseList = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** 混亂種子區間 */
    private int[] chaosSeedIndex = new int[] { 10, 26, 26 };

    /**
     * 產生混亂字串
     * 
     * @param s
     * @throws IllegalArgumentException
     * @throws CryptException
     */
    public String chaos(String s) throws IllegalArgumentException, CryptException {
        try {

            int[] chaosSpeed = null;
            // fortify: null deference
            String chaosList = "";

            boolean flag = true;
            int tryTimes = 0;
            while (flag && tryTimes < 5) {
                tryTimes++;

                chaosSpeed = getChaosSpeed();
                chaosList = getChaosList(chaosSpeed);

                // 計算混亂程度:
                // 若混亂前後相同則本次不算
                // 若混亂程度超過一半則視為通過
                int sameCnt = 0;
                for (int i = 0; i < baseList.length(); i++) {
                    sameCnt += (baseList.charAt(i) == chaosList.charAt(i)) ? 1 : 0;
                }
                if (sameCnt == baseList.length()) {
                    tryTimes--;
                }
                else if (sameCnt < baseList.length() / 2) {
                    flag = false;
                }
            }

            StringBuffer sb = new StringBuffer();
            sb.append(hex(chaosSpeed));
            for (int j = 0; j < s.length(); j++) {
                // idx 加入 j 造成偏移，可防止相同字元辨識
                int idx = baseList.indexOf((int) s.charAt(j));
                if (idx < 0) {
                    throw new IllegalArgumentException(String.format("StringChaos.chaos() : Unknown character %s", s.charAt(j)));
                }
                sb.append(chaosList.charAt((idx + j) % baseList.length()));
            }
            return sb.toString();
        }
        catch (Exception ex) {
            throw new CryptException("string chaos failure", ex);
        }
    }

    /**
     * 產生混亂種子
     */
    private int[] getChaosSpeed() {
        int[] chaosSpeed = new int[chaosSeedIndex.length];
        int sum = 0;
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < chaosSeedIndex.length; i++) {
            chaosSpeed[i] = secureRandom.nextInt(chaosSeedIndex[i]) + sum;
            sum += chaosSeedIndex[i];
        }
        return chaosSpeed;
    }

    /**
     * 產生混亂字元清單
     */
    private String getChaosList(int[] chaosSeed) {
        char[] chs = baseList.toCharArray();
        for (int i = 0; i < chs.length; i += 2) {
            int[] idx = new int[chaosSeed.length + 1];
            idx[0] = i;
            for (int j = 0; j < chaosSeed.length; j++) {
                idx[j + 1] = (i + chaosSeed[j]) % chs.length;
            }

            char tmp = chs[idx[0]];
            for (int j = 0; j < idx.length - 1; j++) {
                chs[idx[j]] = chs[idx[j + 1]];
            }
            chs[idx[idx.length - 1]] = tmp;
        }
        return new String(chs, 0, chs.length);
    }

    /**
     * 轉為16進為字串
     */
    private String hex(int[] x) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < x.length; i++) {
            String hex = Integer.toHexString(x[i]);
            if (hex.length() < 2) {
                hex = "0" + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
