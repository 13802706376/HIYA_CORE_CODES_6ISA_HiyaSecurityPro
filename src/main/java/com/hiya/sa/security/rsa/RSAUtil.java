package com.hiya.sa.security.rsa;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAUtil
{
    private static final String ALGORITHM = "RSA";
    private static final int KEYSIZE = 1024;
    private static String PUBLIC_KEY_FILE = "PublicKey";
    private static String PRIVATE_KEY_FILE = "PrivateKey";

    /**
     * 生成密钥对(文件)
     * 
     * @throws Exception
     */
    public static void generateKeyPair() throws Exception
    {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEYSIZE, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();
        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try
        {
            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            oos1.close();
            oos2.close();
        }
    }

    /**
     * 生成密钥对(字符串)
     * 
     * @throws Exception
     */
    public static void genKeyPair() throws NoSuchAlgorithmException
    {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEYSIZE, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();
        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();
        String publicKeyBase64 = new BASE64Encoder().encode(publicKeyBytes);
        String privateKeyBase64 = new BASE64Encoder().encode(privateKeyBytes);
        System.out.println("publicKeyBase64.length():" + publicKeyBase64.length());
        System.out.println("publicKeyBase64:" + publicKeyBase64);
        System.out.println("privateKeyBase64.length():" + privateKeyBase64.length());
        System.out.println("privateKeyBase64:" + privateKeyBase64);
    }

    /**
     * 加密方法
     * @param source源数据
     * @return
     * @throws Exception
     */
    public static String encrypt(String source) throws Exception
    {
        Key publicKey = getKey(PUBLIC_KEY_FILE);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        byte[] b1 = cipher.doFinal(b);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);
    }

    /**
     * 解密算法
     * @param cryptograph密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String cryptograph) throws Exception
    {
        Key privateKey = getKey(PRIVATE_KEY_FILE);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    private static Key getKey(String fileName) throws Exception, IOException
    {
        Key key;
        ObjectInputStream ois = null;
        try
        {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            key = (Key) ois.readObject();
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            ois.close();
        }
        return key;
    }
}
