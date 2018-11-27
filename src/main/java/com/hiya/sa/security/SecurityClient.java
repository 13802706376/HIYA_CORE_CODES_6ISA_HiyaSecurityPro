package com.hiya.sa.security;

import com.hiya.sa.security.base64.Base64Util;
import com.hiya.sa.security.des.DesUtil;
import com.hiya.sa.security.md5.MD5Util;
import com.hiya.sa.security.rsa.RSAUtil;
import com.hiya.sa.security.sha.SHAUtil;
public class SecurityClient
{

    public static void main(String[] args) throws Exception
    {
        String orig = new String("amigoxiexiexingxing");
        System.out.println("原始：" + orig);
        System.out.println("MD5后：" + MD5Util.md5Encode(orig));
        System.out.println("----------------------------------------");
        
        
        
        System.out.println("原始：" + orig);
        System.out.println("SHA后：" + SHAUtil.shaEncode(orig));
        System.out.println("----------------------------------------");
        
        
        
        System.out.println("原始：" + orig);
        System.out.println("Base64编码后：" + Base64Util.encryptBASE64(orig));
        System.out.println("Base64解码后：" + Base64Util.decryptBASE64(Base64Util.encryptBASE64(orig)));
        System.out.println("----------------------------------------");
        
        
        
        String key="9588028820109132570743325311898426347857298773549468758875018579537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";
        System.out.println("原始：" + orig);
        String result = new String(DesUtil.aesEncrypt(orig.getBytes(),key.getBytes()));
        System.out.println("加密后："+result);
        String decryResult =new String( DesUtil.aesDecrypt(result.getBytes(), key.getBytes()));
        System.out.println("解密后："+decryResult);
        System.out.println("----------------------------------------");
        
        
        
        RSAUtil.generateKeyPair();
        RSAUtil.genKeyPair();
        String source = "你好nihao";// 要加密的字符串  
        System.out.println("准备用公钥加密的字符串为：" + source);  
          
        String cryptograph = RSAUtil.encrypt(source);// 生成的密文  
        System.out.print("用公钥加密后的结果为:" + cryptograph);  
        System.out.println();  
         
        String target = RSAUtil.decrypt(cryptograph);// 解密密文  
        System.out.println("用私钥解密后的字符串为：" + target);  
        System.out.println();     
        System.out.println("----------------------------------------");
        
        
    }
}
