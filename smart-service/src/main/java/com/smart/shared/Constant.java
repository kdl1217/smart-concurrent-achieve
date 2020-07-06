package com.smart.shared;

/**
 * @author Kong, created on 2020-05-13T11:14.
 * @Description: 常量
 * @since 1.0.0-SNAPSHOT
 */
public interface Constant {

    /**
     * 成功
     */
    String DEFAULT_SUCCESS = "0000";

    /**
     * 远控等待
     */
    String REMOTE_WAIT = "2501";

    /**
     * 钥匙基础数据
     */
    interface BasicInformation {
        /**
         * 钥匙IMEI
         *  867858011111 + 序号XXX
         */
        String imei = "867858011111";

        /**
         * 钥匙SN
         * SK01ZSY20010888 + 序号XXX
         */
        String sn = "SK01ZSY20010888";

        /**
         * 钥匙ICCID
         * 89860412101871111 + 序号XXX
         */
        String iccid = "89860412101871111";

        /**
         * 钥匙IMSI
         * 460046534122 + 序号XXX
         */
        String imsi = "460046534122";

        /**
         * 钥匙密钥，默认一样
         */
        String key = "21038cabb3004f28a05aa7a38dca4faf";

        /**
         * 钥匙PIN
         */
        String pin = "000000";

        /**
         * 钥匙供应商编码
         */
        String SUPPLIER_CODE = "INCAR";

        /**
         * 钥匙型号
         */
        String DEVICE_MODEL = "SK_TS01";
    }

    /**
     * 请求地址前缀
     */
    String Prefix = "/api/ickey";

    /**
     * 请求地址
     */
    interface RequestAddress {

        /**
         * 查询绑定状态
         */
        String QUERY_BIND_STATUS = Prefix + "/device/queryBindStatus";

        /**
         * 确认钥匙信息
         */
        String CONFIRM_KEY = Prefix + "/device/confirmKey";

        /**
         * 查询车况
         */
        String QUERY_VEHICLE_STATUS = Prefix + "/vehicle/queryVehicleStatus";

        /**
         * 查询报警集合
         */
        String QUERY_ALARM = Prefix + "/vehicle/queryAlarmList";

        /**
         * 查询最新OTA版本
         */
        String QUERY_LATEST_VERSION = Prefix + "/upgrade/queryLatestVersion";

        /**
         * 升级状态 【确认/取消】
         */
        String UPGRADE_STATUS = Prefix + "/upgrade/upgradeStatus";

        /**
         * 反馈OTA升级状态
         */
        String REPORT_UPGRADE_STATUS = Prefix + "/upgrade/reportUpgradeStatus";

        /**
         * 查参数设置信息
         */
        String QUERY_PARAM_CONFIG = Prefix + "/param/queryConfigInfo";

        /**
         * 反馈参数设置结果
         */
        String REPORT_PARAM_STATUS = Prefix + "/param/reportUpdateConfigStatus";

        /**
         * 请求更新密钥
         */
        String UPDATE_DEVICE_KEY = Prefix + "/secret/updateDeviceKey";

        /**
         * 反馈密钥更新状态
         */
        String REPORT_UPDATE_KEY_STATUS = Prefix + "/secret/reportUpdateKeyStatus";

        /**
         * 远程控制 - 寻车
         */
        String FIND_CAR = Prefix + "/remote/findCar";

        /**
         * 远程控制 - 车门 （车锁）
         */
        String CAR_DOOR = Prefix + "/remote/carDoor";

        /**
         * 远程控制 - 空调
         */
        String AIR_CONDITIONER = Prefix + "/remote/airConditioner";

        /**
         * 远程控制 - 车窗
         */
        String CAR_WIN = Prefix + "/remote/carWin";

        /**
         * 远程控制 - 后备箱
         */
        String CAR_TRUNK = Prefix + "/remote/carTrunk";

        /**
         * 远程控制 - 引擎
         */
        String CAR_POWER = Prefix + "/remote/carPower";

        /**
         * 远程控制 - 结果
         */
        String REMOTE_RESULT = Prefix + "/remote/result";
    }


    /**
     * Header 统一请求参数 属性
     */
    interface HeaderParam {
        /**
         * smart-key-imei (国际移动设备识别码)
         */
        String SMART_KEY_IMEI = "smart-key-imei";
        /**
         * smart-key-ts (时间戳)
         */
        String SMART_KEY_TS = "smart-key-ts";
        /**
         * smart-key-sign (签名)
         */
        String SMART_KEY_SIGN = "smart-key-sign";
        /**
         * smart-key-type (操作类型)
         */
        String SMART_KEY_TYPE = "smart-key-type";
        /**
         * smart-key-code (校验码)
         */
        String SMART_KEY_CODE = "smart-key-code";
    }

    /**
     * 请求车况类型
     */
    interface PackageType {
        /**
         * 正常请求
         */
        int NORMAL = 1;
        /**
         * 下拉请求
         */
        int REQUEST = 2;
    }

    /**
     * 远控类型
     */
    interface RemoteType {
        /**
         * 寻车
         */
        int FIND_CAR = 1;
        /**
         * 车门
         */
        int CAR_DOOR = 2;
        /**
         * 空调
         */
        int AIR_CONDITIONER = 3;
        /**
         * 车窗
         */
        int CAR_WINDOW = 4;
        /**
         *
         * 后备箱
         */
        int CAR_TRUNK = 5;
        /**
         * 引擎
         */
        int CAR_POWER = 6;
    }
}
