package com.hiya.sa.security.sha;

import java.security.MessageDigest;

/**
 * 单向加密又称为不可逆加密算法，在加密过程中不使用密钥，明文由系统加密处理成密文，密文无法解密。一般适合于验证，在验证过程中，重新输入明文，
 * 并经过同样的加密算法处理，得到相同的密文并被系统重新认证。广泛使用于口令加密。现在已成为公认的最安全的散列算法之一，并被广泛使用。
 * 该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），
 * 并把它们转化为长度较短、位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。散列函数值可以说是对明文的一种“指纹”或是“摘要”
 * 所以对散列值的数字签名就可以视为对此明文的数字签名。
 * @author zjq
 *
 */
public class SHAUtil
{
    public static String shaEncode(String inStr) throws Exception 
    {
        MessageDigest sha = null;
        try 
        {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) 
        {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) 
        {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) { 
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
