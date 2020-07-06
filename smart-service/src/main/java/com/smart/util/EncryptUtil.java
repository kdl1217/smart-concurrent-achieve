package com.smart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * EncryptUtil
 *
 * @author Kong, created on 2020-05-13T12:53.
 * @since 1.0.0-SNAPSHOT
 */
public class EncryptUtil {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);


    private static final String CHARSET_UTF8 = "utf-8";
    private static final String KEY_ALGORITHM_AES = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";
    //Hash SHA加密方式
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_512 = "SHA-512";


    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM_AES);
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) {
                kgen.init(128, new SecureRandom(password.getBytes()));
            } else {
                SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                secureRandom.setSeed(password.getBytes());
                kgen.init(128, secureRandom);
            }
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM_AES);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM_AES);
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            // 加密
            return result;
        } catch (NoSuchAlgorithmException e) {
            logger.error("encrypt error", e);
        } catch (NoSuchPaddingException e) {
            logger.error("encrypt error", e);
        } catch (InvalidKeyException e) {
            logger.error("encrypt error", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("encrypt error", e);
        } catch (IllegalBlockSizeException e) {
            logger.error("encrypt error", e);
        } catch (BadPaddingException e) {
            logger.error("encrypt error", e);
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM_AES);
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) {
                kgen.init(128, new SecureRandom(password.getBytes()));
            } else {
                SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                secureRandom.setSeed(password.getBytes());
                kgen.init(128, secureRandom);
            }

            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM_AES); // 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key); // 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error("decrypt error", e);
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 生成sign签名
     *
     * @param password 密钥
     */
    public static String getSign(String password) {
        if (StringUtil.isBlank(password)) {
            return null;
        }
        byte[] bytes = encrypt(String.valueOf(System.currentTimeMillis()), password);
        if (null == bytes) {
            return null;
        }
        return parseByte2HexStr(bytes);
    }


    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public static String sha256(String strText) {
        return sha(strText, SHA_256);
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    public static String sha512(String strText) {
        return sha(strText, SHA_512);
    }

    /**
     * 字符串 SHA 加密
     *
     * @param strText
     * @param strType
     * @return
     */
    private static String sha(final String strText, final String strType) {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密类型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 类型结果
                byte[] byteBuffer = messageDigest.digest();

                // 将 byte 转换为 string
                StringBuffer strHexString = new StringBuffer();
                // 遍历 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回结果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                logger.error("sha error", e);
            }
        }
        return strResult;
    }

}
