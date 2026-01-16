package com.ibm.tw.commons.util;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

/**
 * @(#)IpUtils.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/22, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class IpUtils {

    // IPv4 正則表達式
    private static final Pattern IPV4_PATTERN = Pattern.compile(
        "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"
    );
    
    // IPv6 正則表達式 (簡化版)
    private static final Pattern IPV6_PATTERN = Pattern.compile(
        "^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$|" +  // 完整格式
        "^::1$|" +                                        // localhost
        "^::$|" +                                         // 全零
        "^([0-9a-fA-F]{1,4}:){1,7}:$|" +                // 尾部壓縮
        "^:([0-9a-fA-F]{1,4}:){1,7}$|" +                // 前端壓縮
        "^([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}$"   // 中間壓縮
    );
    
    /**
     * IP地址類型枚舉
     */
    public enum IpType {
        IPV4, IPV6, UNKNOWN
    }
    
    public static class IpConversionResult {
        private final IpType type;
        private final BigInteger numericValue;
        private final String originalIp;
        
        public IpConversionResult(IpType type, BigInteger numericValue, String originalIp) {
            this.type = type;
            this.numericValue = numericValue;
            this.originalIp = originalIp;
        }
        
        // Getters
        public IpType getType() { return type; }
        public BigInteger getNumericValue() { return numericValue; }
        public String getOriginalIp() { return originalIp; }
        
        @Override
        public String toString() {
            return String.format("IpConversionResult{type=%s, value=%s, ip='%s'}", 
                type, numericValue, originalIp);
        }
    }
    
    /**
     * ip v4 to long 
     * @param ipAddress
     * @return
     * @throws UnknownHostException
     */
    public static long ipV4ToLong(String ipAddress) throws UnknownHostException {
        if (!isValidIpAddress(ipAddress)) {
            throw new IllegalArgumentException("Invalid IP address: " + ipAddress);
        }
        
        // 確保是IPv4地址
        if (detectIpType(ipAddress) != IpType.IPV4) {
            throw new IllegalArgumentException("Only IPv4 addresses are supported in ipToLong(): " + ipAddress);
        }
        
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        byte[] bytes = inetAddress.getAddress();
        
        long result = 0;
        for (byte b : bytes) {
            result = (result << 8) | (b & 0xFF);
        }
        
        return result;
    }
    
    /**
     * 檢測IP地址類型
     */
    public static IpType detectIpType(String ipAddress) {
        if (ipAddress == null || ipAddress.trim().isEmpty()) {
            return IpType.UNKNOWN;
        }
        
        if (IPV4_PATTERN.matcher(ipAddress).matches()) {
            return IpType.IPV4;
        }
        
        try {
            InetAddress addr = InetAddress.getByName(ipAddress);
            if (addr instanceof Inet4Address) {
                return IpType.IPV4;
            } else if (addr instanceof Inet6Address) {
                return IpType.IPV6;
            }
        } catch (UnknownHostException e) {
            // IP格式錯誤
        }
        
        return IpType.UNKNOWN;
    }
    
    /**
     * 統一的IP轉換方法 - 支援IPv4和IPv6
     */
    public static IpConversionResult convertIpToBigInteger(String ipAddress) throws UnknownHostException {
        IpType type = detectIpType(ipAddress);
        
        switch (type) {
            case IPV4:
                return new IpConversionResult(IpType.IPV4, ipv4ToBigInteger(ipAddress), ipAddress);
            case IPV6:
                return new IpConversionResult(IpType.IPV6, ipv6ToBigInteger(ipAddress), ipAddress);
            default:
                throw new IllegalArgumentException("Invalid IP address: " + ipAddress);
        }
    }
    

    /**
     * 驗證是否為有效的IPv4地址
     * 
     * @param ip IP地址字符串
     * @return 是否為有效IPv4地址
     */
    public static boolean isValidIpv4Address(String ip) {
        if (ip == null || ip.trim().isEmpty()) {
            return false;
        }
        
        return IPV4_PATTERN.matcher(ip).matches();
    }
    /**
     * IPv4 轉 BigInteger
     */
    public static BigInteger ipv4ToBigInteger(String ipv4) throws UnknownHostException {
        if (detectIpType(ipv4) != IpType.IPV4) {
            throw new IllegalArgumentException("Not a valid IPv4 address: " + ipv4);
        }
        
        InetAddress addr = InetAddress.getByName(ipv4);
        byte[] bytes = addr.getAddress();
        
        // IPv4是4個字節，轉換為正數
        return new BigInteger(1, bytes);
    }
    
    /**
     * IPv6 轉 BigInteger
     */
    public static BigInteger ipv6ToBigInteger(String ipv6) throws UnknownHostException {
        if (detectIpType(ipv6) != IpType.IPV6) {
            throw new IllegalArgumentException("Not a valid IPv6 address: " + ipv6);
        }
        
        InetAddress addr = InetAddress.getByName(ipv6);
        byte[] bytes = addr.getAddress();
        
        // IPv6是16個字節，轉換為正數
        return new BigInteger(1, bytes);
    }
    
    /**
     * BigInteger 轉 IPv4
     */
    public static String bigIntegerToIpv4(BigInteger value) {
        if (value.compareTo(BigInteger.ZERO) < 0 || 
            value.compareTo(BigInteger.valueOf(0xFFFFFFFFL)) > 0) {
            throw new IllegalArgumentException("Value out of IPv4 range: " + value);
        }
        
        byte[] bytes = new byte[4];
        byte[] valueBytes = value.toByteArray();
        
        // 處理BigInteger的字節數組（可能少於4字節）
        int offset = Math.max(0, valueBytes.length - 4);
        int length = Math.min(4, valueBytes.length);
        System.arraycopy(valueBytes, offset, bytes, 4 - length, length);
        
        try {
            return InetAddress.getByAddress(bytes).getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Failed to convert to IPv4", e);
        }
    }
    
    /**
     * BigInteger 轉 IPv6
     */
    public static String bigIntegerToIpv6(BigInteger value) {
        if (value.compareTo(BigInteger.ZERO) < 0 || 
            value.bitLength() > 128) {
            throw new IllegalArgumentException("Value out of IPv6 range: " + value);
        }
        
        byte[] bytes = new byte[16];
        byte[] valueBytes = value.toByteArray();
        
        // 處理BigInteger的字節數組（可能少於16字節）
        int offset = Math.max(0, valueBytes.length - 16);
        int length = Math.min(16, valueBytes.length);
        System.arraycopy(valueBytes, offset, bytes, 16 - length, length);
        
        try {
            return InetAddress.getByAddress(bytes).getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Failed to convert to IPv6", e);
        }

    }
    /**
     * 
     * @param ipAddress
     * @return
     * @throws UnknownHostException
     */
    public static long ipToLong(String ipAddress) throws UnknownHostException {
            
            // ===== 步驟1：基本驗證 =====
            if (!isValidIpv4Address(ipAddress)) {
                throw new IllegalArgumentException("Only IPv4 addresses are supported. Invalid input: " + ipAddress);
            }
            
            // ===== 步驟2：確保是IPv4 =====
            if (detectIpType(ipAddress) != IpType.IPV4) {
                throw new IllegalArgumentException("Only IPv4 addresses are supported in ipToLong(): " + ipAddress);
            }
            
            // ===== 步驟3：使用InetAddress解析 =====
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            byte[] bytes = inetAddress.getAddress();
            
            // ===== 步驟4：字節陣列轉換為長整數 =====
            long result = 0;
            for (byte b : bytes) {
                result = (result << 8) | (b & 0xFF);
            }
            
            return result;
        }
    
   public static String longToIp(long ip) {
        
        // ===== 步驟1：範圍驗證 =====
        if (ip < 0 || ip > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("IP value out of IPv4 range: " + ip + ". Valid range: 0 to 4294967295");
        }
        
        // ===== 步驟2：位運算提取各個字節 =====
        return String.format("%d.%d.%d.%d",
            (ip >> 24) & 0xFF,    // 第1個字節 (最高位)
            (ip >> 16) & 0xFF,    // 第2個字節
            (ip >> 8) & 0xFF,     // 第3個字節
            ip & 0xFF             // 第4個字節 (最低位)
        );
    }
   
    /**
     * 檢查IP是否在指定範圍內 (支援IPv4和IPv6)
     */
    public static boolean isIpInRange(String ip, BigInteger fromIp, BigInteger toIp) {
        try {
            IpConversionResult result = convertIpToBigInteger(ip);
            BigInteger ipValue = result.getNumericValue();
            return ipValue.compareTo(fromIp) >= 0 && ipValue.compareTo(toIp) <= 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 比較兩個IP地址的大小
     */
    public static int compareIps(String ip1, String ip2) throws UnknownHostException {
        IpConversionResult result1 = convertIpToBigInteger(ip1);
        IpConversionResult result2 = convertIpToBigInteger(ip2);
        
        // 不同類型的IP不能直接比較
        if (result1.getType() != result2.getType()) {
            throw new IllegalArgumentException("Cannot compare different IP types: " + 
                result1.getType() + " vs " + result2.getType());
        }
        
        return result1.getNumericValue().compareTo(result2.getNumericValue());
    }
    
    /**
     * 驗證IP地址格式
     */
    public static boolean isValidIpAddress(String ip) {
        return detectIpType(ip) != IpType.UNKNOWN;
    }
    
    /**
     * 取得IP地址的下一個地址
     */
    public static String getNextIp(String ip) throws UnknownHostException {
        IpConversionResult result = convertIpToBigInteger(ip);
        BigInteger nextValue = result.getNumericValue().add(BigInteger.ONE);
        
        switch (result.getType()) {
            case IPV4:
                return bigIntegerToIpv4(nextValue);
            case IPV6:
                return bigIntegerToIpv6(nextValue);
            default:
                throw new IllegalArgumentException("Invalid IP type");
        }
    }
    
    /**
     * 取得IP地址範圍內的所有地址數量
     */
    public static BigInteger getIpRangeSize(BigInteger fromIp, BigInteger toIp) {
        if (fromIp.compareTo(toIp) > 0) {
            throw new IllegalArgumentException("fromIp must be <= toIp");
        }
        return toIp.subtract(fromIp).add(BigInteger.ONE);
    }

}
