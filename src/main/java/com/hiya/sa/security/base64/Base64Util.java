package com.hiya.sa.security.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 我们知道在计算机中任何数据都是按ascii码存储的，而ascii码的128～255之间的值是不可见字符。而在网络上交换数据时，比如说从A地传到B地，
 * 往往要经过多个路由设备，由于不同的设备对字符的处理方式有一些不同，这样那些不可见字符就有可能被处理错误，这是不利于传输的。所以就先
 * 把数据先做一个Base64编码，统统变成可见字符，这样出错的可能性就大降低了(这段来自知乎)。不仅如此,比如说我们可以使用HTML来内嵌Base64编
 * 码的图片,这样就避免了不必要的外部资源加载,不过还是要量力而为,这种场景一般适用于尺寸比较小的图片,如果是高清图片,用这种方式会导致Base64编
 * 码后的字符串很大,反而影响加载速度.
 * @author zjq
 *
 */
public class Base64Util
{
    /** 
     * BASE64解密 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String decryptBASE64(String desc) throws Exception 
    {  
        return new String(Base64.getDecoder().decode(desc),StandardCharsets.UTF_8);
    }  
      
    /** 
     * BASE64加密 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptBASE64(String orig ) throws Exception 
    {  
        return   Base64.getEncoder().encodeToString(orig.getBytes(StandardCharsets.UTF_8));
    }  
}
