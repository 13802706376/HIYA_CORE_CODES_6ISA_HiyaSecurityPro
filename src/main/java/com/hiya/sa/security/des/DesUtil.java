package com.hiya.sa.security.des;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DesUtil
{
    private static final String AES = "AES";

    public DesUtil()
    {
    }

    public static byte[] aesEncrypt(byte[] input, byte[] key)
    {
        return aes(input, key, Cipher.ENCRYPT_MODE);
    }

    public static byte[] aesDecrypt(byte[] input, byte[] key)
    {
        return aes(input, key, Cipher.DECRYPT_MODE);
    }

    private static byte[] aes(byte[] input, byte[] key, int mode)
    {
        try
        {
            SecretKey secretKey = new SecretKeySpec(key, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(mode, secretKey);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
