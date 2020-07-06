package com.smart.smartkey;

import com.smart.entity.IcdCheckParam;
import com.smart.entity.IcdEquipment;
import com.smart.util.MD5Util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 钥匙基础数据
 *
 * @author Kong, created on 2020-06-12T15:58.
 * @since 1.0.0-SNAPSHOT
 */
public class IcdBasicData {

    /**
     * imei对应钥匙集合
     */
    public static Map<String, IcdEquipment> icdMap = new ConcurrentHashMap<>();

    /**
     * imei对应PIN集合
     */
    public static Map<String, String> pinMap = new ConcurrentHashMap<>();

    /**
     * imei对应密钥集合
     */
    public static Map<String, String> icdKeyMap = new ConcurrentHashMap<>();

    /**
     * imei对应的流水号集合
     * 参数设置
     */
    public static Map<String, Long> paramSerialMap = new ConcurrentHashMap<>();

    /**
     * imei对应的流水号集合
     * 密钥更新
     */
    public static Map<String, Long> secretSerialMap = new ConcurrentHashMap<>();

    /**
     * imei对应的流水号集合
     * 远控
     */
    public static Map<String, Long> remoteSerialMap = new ConcurrentHashMap<>();

    /**
     * 获取请求查询绑定状态对象
     * @param imei
     * @return
     */
    public static IcdCheckParam getIcdCheckParam(String imei) {
        IcdEquipment icdEquipment = icdMap.get(imei);
        if (null == icdEquipment) {
            throw new IllegalArgumentException("get Icd equipment error!! this imei is " + imei);
        }
        String checkCode = MD5Util.getEncode32(icdEquipment.getSn() + icdEquipment.getIccid() + icdEquipment.getImsi());
        return new IcdCheckParam(checkCode);
    }
}
