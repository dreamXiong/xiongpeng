package com.liguo.hgl.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密工具类
 * @author wzt
 */
public class Des3 {
    /** 密钥 */
    private static final String SECRET_KEY = "ydhgl@XFFQDKLMSAPPXX@@#$";

    /** 向量 */
    private static final String IV = "01234567";

    /** 加解密统一使用的编码方式 */
    private static final String ENCODING = "utf-8";

    /**
     * 3DES加密
     * 
     * @param plainText 普通文本
     * @return 加密结果
     * @throws Exception Exception
     */
    public static String encode(String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(SECRET_KEY.getBytes(ENCODING));
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(IV.getBytes(ENCODING));
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(ENCODING));
        return Base64.encode(encryptData);
    }

    /**
     * 3DES解密
     * 
     * @param encryptText 加密文本
     * @return 解密文本
     * @throws Exception Exception
     */
    public static String decode(String encryptText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(SECRET_KEY.getBytes(ENCODING));
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(IV.getBytes(ENCODING));
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

        return new String(decryptData, ENCODING);
    }

}
