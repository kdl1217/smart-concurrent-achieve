package com.smart.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * StringUtil
 *
 * @author Kong, created on 2020-05-13T11:14.
 * @since 1.0.0-SNAPSHOT
 */
public class StringUtil extends StringUtils {

    /**
     * Logger
     */
    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    /**
     * 拼接数据（物联网卡数据格式 ：xx_xx_xx）
     *
     * @param params
     * @return
     */
    public static String getMsisdns(List<String> params) {
        StringBuffer sb = new StringBuffer(params.get(0));
        for (int i = 1; i < params.size(); i++) {
            sb.append("_");
            sb.append(params.get(i));
        }
        return sb.toString();
    }

    /**
     * 获取签名
     * @param md5
     * @return
     */
    public static String getSign(String md5) {
        String sign = "";
        if (md5.length() != 32) {
            throw new IllegalArgumentException("MD5 length mismatch!");
        }
        // 下标
        int index = 0;
        for (int i = 0; i < 32; i++) {
            if (Math.pow(2, index) - 1 == i) {
                sign += md5.charAt(i);
                index++;
            }
        }
        return sign;
    }

}
