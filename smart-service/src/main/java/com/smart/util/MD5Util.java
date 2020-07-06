package com.smart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 *
 * @author Kong, created on 2020-05-13T12:53.
 * @since 1.0.0-SNAPSHOT
 */
public class MD5Util {

    /**
     * Logger
     */
    private static Logger logger = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 获取16位大写加密字符串
     *
     * @param str 需要加密的字符串
     * @return 加密后的16位字符串
     * @author Teemol
     */
    public static String getEncode16(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : digest.digest()) {
                builder.append(Integer.toHexString((b >> 4) & 0xf));
                builder.append(Integer.toHexString(b & 0xf));
            }

            return builder.substring(8, 24).toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            logger.error("getEncode16 error", e);
        }

        return null;
    }

    /**
     * 获取32位大写加密字符串
     *
     * @param str 需要加密的字符串
     * @return 加密后的32位字符串
     * @author Teemol
     */
    public static String getEncode32(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : digest.digest()) {
                builder.append(Integer.toHexString((b >> 4) & 0xf));
                builder.append(Integer.toHexString(b & 0xf));
            }

            return builder.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            logger.error("getEncode32 error", e);
        }

        return null;
    }

    /**
     * 根据文件计算出文件的MD5
     *
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }

        MessageDigest digest = null;
        byte[] buffer = new byte[1024];
        int len;
        try (FileInputStream in = new FileInputStream(file)) {
            digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("getFileMD5 error", e);
        } catch (FileNotFoundException e) {
            logger.error("getFileMD5 error", e);
        } catch (IOException e) {
            logger.error("getFileMD5 error", e);
        }

        if (null == digest) {
            return null;
        }

        BigInteger bigInt = new BigInteger(1, digest.digest());

        return bigInt.toString(16);
    }

}
