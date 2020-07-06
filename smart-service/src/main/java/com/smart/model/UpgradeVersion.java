package com.smart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 智联钥匙OTA升级包
 *
 * @author Kong, created on 2020-05-14T13:35.
 * @since 1.0.0-SNAPSHOT
 */
@Data
public class UpgradeVersion {

    /**
     * 版本ID
     */
    @ApiModelProperty(position = 1, value = "版本ID")
    private Integer id;

    /**
     * 供应商代码
     */
    @ApiModelProperty(position = 2, value = "供应商代码")
    private String supplierCode;

    /**
     * 钥匙型号
     */
    @ApiModelProperty(position = 3, value = "钥匙型号")
    private String deviceModel;

    /**
     * 版本名
     */
    @ApiModelProperty(position = 4, value = "版本名")
    private String versionName;

    /**
     * 是否为强制升级版本 0否，1是
     */
    @ApiModelProperty(position = 5, value = "是否为强制升级版本 0否，1是")
    private Integer mandatoryUpgrade;

    /**
     * 升级包下载地址
     */
    @ApiModelProperty(position = 6, value = "升级包下载地址")
    private String upgradePackPath;

    /**
     * 升级包大小
     */
    @ApiModelProperty(position = 7, value = "升级包大小")
    private Long upgradePackSize;

    /**
     * MD5校验码
     */
    @ApiModelProperty(position = 8, value = "MD5校验码")
    private String hashCode;

    /**
     * 版本说明
     */
    @ApiModelProperty(position = 9, value = "版本说明")
    private String versionDescription;

    /**
     * 上传时间
     */
    @ApiModelProperty(position = 10, value = "上传时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(position = 11, value = "修改时间")
    private Date updateTime;

    public UpgradeVersion() {

    }
}
