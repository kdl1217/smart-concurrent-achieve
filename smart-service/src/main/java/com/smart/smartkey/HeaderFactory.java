package com.smart.smartkey;

import com.smart.shared.Constant;
import com.smart.util.EncryptUtil;
import com.smart.util.MD5Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Header 工厂
 *
 * @author Kong, created on 2020-06-12T15:35.
 * @since 1.0.0-SNAPSHOT
 */
public class HeaderFactory {

    // 默认操作类型
    private static final String TYPE = "query";

    /**
     * 获取Header参数
     * @param imei
     * @param key
     * @return
     */
    public static Map<String, Object> getHeaderParams(String imei, String key) {
        Map<String, Object> headerMap = new HashMap<>();
        // 获取签名和校验码
        long ts = System.currentTimeMillis();
        String sign = getSign(imei, key, ts);
        String code = getCode(imei, ts, sign);

        headerMap.put(Constant.HeaderParam.SMART_KEY_IMEI, imei);
        headerMap.put(Constant.HeaderParam.SMART_KEY_TS, ts);
        headerMap.put(Constant.HeaderParam.SMART_KEY_SIGN, sign);
        headerMap.put(Constant.HeaderParam.SMART_KEY_TYPE, TYPE);
        headerMap.put(Constant.HeaderParam.SMART_KEY_CODE, code);

        return headerMap;
    }

    /**
     * 获取签名
     * @param imei  IMEI
     * @param key   密钥
     * @param ts    时间戳
     * @return      签名
     */
    public static String getSign(String imei, String key, long ts) {
        return MD5Util.getEncode32(imei + key + ts);
    }

    /**
     * 获取校验码
     * @param imei  IMEI
     * @param ts    时间戳
     * @param sign  签名
     * @return 校验码
     */
    public static String getCode(String imei, long ts, String sign) {
        return EncryptUtil.sha256(imei + ts + sign);
    }

}
