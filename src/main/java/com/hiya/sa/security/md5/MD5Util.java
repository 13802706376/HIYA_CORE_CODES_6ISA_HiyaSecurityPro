package com.hiya.sa.security.md5;

import java.security.MessageDigest;

/**
 * 单向加密又称为不可逆加密算法，在加密过程中不使用密钥，明文由系统加密处理成密文，密文无法解密。一般适合于验证，在验证过程中，重新输入明文，
     并经过同样的加密算法处理，得到相同的密文并被系统重新认证。广泛使用于口令加密。
     
     MD5和SHA比较：
    1）对强行攻击的安全性：最显著和最重要的区别是SHA-1摘要比MD5摘要长32 位。使用强行技术，产生任何一个报文使其摘要等于给定报摘要的难度对MD5是2^128数量级的操作，而对SHA-1则是2^160数量级的操作。这样，SHA-1对强行攻击有更大的强度。
    2）对密码分析的安全性：由于MD5的设计，易受密码分析的攻击，SHA-1显得不易受这样的攻击。MD5已经得到破解，不安全，普遍不采用。
    3）速度：在相同的硬件上，SHA-1的运行速度比MD5慢。
 * @author zjq
 */
public class MD5Util
{
    public static String md5Encode(String inStr) throws Exception 
    {
        MessageDigest md5 = null;
        try 
        {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) 
        {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) 
        {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) 
            {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
