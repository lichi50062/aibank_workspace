/**
 * 
 */
package com.tfb.aibank.chl.common.fbsecurity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.SecureRandom;

//@formatter:off
/**
* @(#)FBSecurity.java 
* 
* <p>Description:由富邦提供，處理加解密的操作</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
public class FBSecurity {
    private static char getRandomChar(String input) {
        int random = 0;
        SecureRandom secureRandom = new SecureRandom();
        do {
            random = (int) ((secureRandom.nextDouble() * 1000) % 128);
        }
        while (input.indexOf(random) >= 0);
        return (char) random;
    }

    private static String charToHexString(char inChar) {
        String outChar = Integer.toHexString(inChar).toUpperCase();
        if (outChar.length() > 2) {
            outChar = outChar.substring(outChar.length() - 2);
        }
        else if (outChar.length() < 2) {
            outChar = "0" + outChar;
        }
        return outChar;
    }

    public static String encrypt(String input) {
        if (input.getBytes().length % 2 != 0) {
            input += " ";
        }
        byte[] inputArray = input.getBytes();
        int length = inputArray.length;
        char hChar = getRandomChar(input);
        char lChar = getRandomChar(input);
        char[] tempArray = new char[length];
        char[] outputArray = new char[length];
        for (int i = 0; i < length; i++) {
            outputArray[i] = (char) inputArray[i];
        }
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < length; k++) {
                if (k < length / 2) {
                    tempArray[k + (length / 2)] = outputArray[k];
                }
                else {
                    tempArray[k - (length / 2)] = outputArray[k];
                }
            }
            for (int j = 0; j < length; j++) {
                if (j % 2 == 0) {
                    outputArray[j] = (char) (tempArray[j] ^ hChar);
                }
                else {
                    outputArray[j] = (char) (tempArray[j] ^ lChar);
                }
            }
        }
        String output = charToHexString(hChar).toUpperCase();
        for (int i = 0; i < outputArray.length; i++) {
            output += charToHexString(outputArray[i]).toUpperCase();
        }
        output += charToHexString(lChar).toUpperCase();
        return output;
    }

    public static String decrypt(String input) {
        try {
            char hChar = (char) Integer.parseInt(input.substring(0, 2), 16);
            char lChar = (char) Integer.parseInt(input.substring(input.length() - 2), 16);
            int length = input.length() / 2 - 1;
            char[] retrivePwd = new char[length - 1];
            for (int i = 1; i < length; i++) {
                retrivePwd[i - 1] = (char) Integer.parseInt(input.substring(i * 2, (i * 2) + 2), 16);
            }
            length = retrivePwd.length;
            byte[] tempArray = new byte[length];
            char[] outputArray = new char[length];
            for (int i = 0; i < retrivePwd.length; i++) {
                tempArray[i] = (byte) retrivePwd[i];
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < length; j++) {
                    if (j % 2 == 0) {
                        outputArray[j] = (char) (tempArray[j] ^ hChar);
                    }
                    else {
                        outputArray[j] = (char) (tempArray[j] ^ lChar);
                    }
                }
                for (int k = 0; k < length; k++) {
                    if (k < length / 2) {
                        tempArray[k + (length / 2)] = (byte) outputArray[k];
                    }
                    else {
                        tempArray[k - (length / 2)] = (byte) outputArray[k];
                    }
                }
            }
            if (new String(tempArray, Charset.defaultCharset()).trim().length() < 1) {
                return "[ERROR]:DECRYPTION_LENGTH_ZERO";
            }
            else {
                return new String(tempArray, "big5").trim();
            }
        }
        catch (NumberFormatException | UnsupportedEncodingException e) {
            return "[ERROR]:" + e.getMessage();
        }
    }

    /**
     * 1.修改日期:2013/06/27 2.修改人員:aho.hsu 3.需求單號:201306210192-00 4.修改內容:配合信卡帳單生成系統專案網銀作業需求
     */
    public static String encryptf(String input) {
        if (input.getBytes().length % 2 != 0) {
            input += " ";
        }
        byte[] inputArray = input.getBytes();
        int length = inputArray.length;
        char hChar = (char) (input.charAt(input.length() - 1) * 0.9);
        char lChar = (char) (input.charAt(0) * 0.7);
        char[] tempArray = new char[length];
        char[] outputArray = new char[length];
        for (int i = 0; i < length; i++) {
            outputArray[i] = (char) inputArray[i];
        }
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < length; k++) {
                if (k < length / 2) {
                    tempArray[k + (length / 2)] = outputArray[k];
                }
                else {
                    tempArray[k - (length / 2)] = outputArray[k];
                }
            }
            for (int j = 0; j < length; j++) {
                if (j % 2 == 0) {
                    outputArray[j] = (char) (tempArray[j] ^ hChar);
                }
                else {
                    outputArray[j] = (char) (tempArray[j] ^ lChar);
                }
            }
        }
        String output = charToHexString(hChar).toUpperCase();
        for (int i = 0; i < outputArray.length; i++) {
            output += charToHexString(outputArray[i]).toUpperCase();
        }
        output += charToHexString(lChar).toUpperCase();
        return output;
    }
}