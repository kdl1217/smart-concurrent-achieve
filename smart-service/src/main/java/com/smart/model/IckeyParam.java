package com.smart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 智联钥匙参数版本信息
 *
 * @author Kong, created on 2020-05-14T16:54.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
public class IckeyParam implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(position = 1, value = "ID")
    private Integer id;

    /**
     * 供应商代码
     */
    @ApiModelProperty(position = 2, value = "供应商代码")
    private String supplierCode;

    /**
     * 钥匙型号
     */
    @ApiModelProperty(position = 4, value = "钥匙型号")
    private String deviceModel;

    /**
     * 最新参数版本号
     */
    @ApiModelProperty(position = 5, value = "最新参数版本号")
    private String configVersion;

    /**
     * 唤醒屏幕变暗周期
     */
    @ApiModelProperty(position = 8, value = "唤醒屏幕变暗周期，单位：秒")
    private Integer screenDimmedCycle;

    /**
     * 不操作屏幕由暗到关闭周期
     */
    @ApiModelProperty(position = 9, value = "不操作屏幕由暗到关闭周期，单位：秒")
    private Integer screenOffCycle;

    /**
     * 进入深度休眠时间
     */
    @ApiModelProperty(position = 10, value = "进入深度休眠时间，单位：分钟")
    private Integer intoDeepDormancy;

    /**
     * 近距离寻找钥匙间隔震动次数
     */
    @ApiModelProperty(position = 11, value = "近距离寻找钥匙间隔震动次数，单位：次")
    private Integer vibrationFrequency;

    /**
     * 近距离寻找钥匙持续震动时间
     */
    @ApiModelProperty(position = 12, value = "近距离寻找钥匙持续震动时间，单位：秒")
    private Integer durationVibration;

    /**
     * 近距离寻找钥匙每次间隔时间
     */
    @ApiModelProperty(position = 13, value = "近距离寻找钥匙每次间隔时间，单位：秒")
    private Integer intervalTime;

    /**
     * 正常模式请求数据周期
     */
    @ApiModelProperty(position = 14, value = "正常模式请求数据周期，单位：秒")
    private Integer locationRequestCycle;

    /**
     * 休眠模式请求数据周期
     */
    @ApiModelProperty(position = 15, value = "休眠模式请求数据周期，单位：秒")
    private Integer dormancyLocationRequestCycle;

    /**
     * 正常模式定位数据上报周期,单位:秒
     */
    @ApiModelProperty(position = 16, value = "正常模式定位数据上报周期,单位:秒")
    private Integer locationReportSeconds;

    /**
     * 休眠模式定位数据上报周期,单位:分钟
     */
    @ApiModelProperty(position = 17, value = "休眠模式定位数据上报周期,单位:分钟")
    private Integer dormancyLocationReportSeconds;

    /**
     * 深度休眠模式上报定位信息的周期
     */
    @ApiModelProperty(position = 18, value = "深度休眠模式上报定位信息的周期,单位:分钟")
    private Integer deepReportSeconds;

    /**
     * 远程启动车辆时间
     */
    @ApiModelProperty(position = 19, value = "远程启动车辆时间,单位:分钟")
    private Integer remoteStartTime;

    /**
     * 远程空调时间
     */
    @ApiModelProperty(position = 20, value = "远程空调时间,单位:分钟")
    private Integer remoteAirConditionerTime;

    /**
     * 密钥更新周期，单位:天
     */
    @ApiModelProperty(position = 21, value = "密钥更新周期，单位:天")
    private Integer secretUpdateCycle;

    /**
     * 用户拒绝OTA升级后，间隔周期再提醒用户升级，单位：天
     */
    @ApiModelProperty(position = 22, value = "用户拒绝OTA升级后，间隔周期再提醒用户升级，单位：天")
    private Integer upgradeRemindCycle;
    /**
     * 短信白名单
     */
    @ApiModelProperty(position = 23, value = "短信白名单")
    private String smsWhiteList;

    /**
     * 版本号
     */
    @ApiModelProperty(position = 24, value = "版本号")
    private Integer versionNo;

    /**
     * 数据创建时间
     */
    @ApiModelProperty(position = 25, value = "数据创建时间")
    private Date createTime;

    /**
     * 数据更新时间
     */
    @ApiModelProperty(position = 26, value = "数据更新时间")
    private Date updateTime;

    /**
     * 参数版本
     */
    @ApiModelProperty(position = 27, value = "参数版本")
    private String versionParamNo;
}
