package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OTA升级状态反馈
 *
 * @author Kong, created on 2020-05-14T14:04.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpgradeResultParam {

    /**
     * 流水号
     */
    @ApiModelProperty(position = 1, value = "流水号")
    private String serialNumber;

    /**
     * 结果
     */
    @ApiModelProperty(position = 2, value = "结果")
    private Integer result;

    /**
     * 失败原因
     */
    @ApiModelProperty(position = 3, value = "失败原因")
    private String message;

    /**
     * 升级前版本名称
     */
    @ApiModelProperty(position = 4, value = "升级前版本名称")
    private String preversionName;

    /**
     * 升级后版本名称
     */
    @ApiModelProperty(position = 5, value = "升级后版本名称")
    private String versionName;

    /**
     * 车机供应商代码
     */
    @ApiModelProperty(position = 6, value = "车机供应商代码")
    private String supplierCode;

}
