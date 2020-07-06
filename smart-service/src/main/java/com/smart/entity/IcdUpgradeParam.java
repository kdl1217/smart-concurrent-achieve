package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 智联钥匙OTA升级版本
 *
 * @author Kong, created on 2020-05-14T11:22.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IcdUpgradeParam {

    /**
     * 供应商代码
     */
    @ApiModelProperty(position = 1, value = "供应商代码")
    private String supplierCode;

    /**
     * 钥匙型号
     */
    @ApiModelProperty(position = 2, value = "钥匙型号")
    private String deviceModel;

    /**
     * 钥匙当前软件版本号
     */
    @ApiModelProperty(position = 3, value = "钥匙当前软件版本号")
    private String softwareVersion;

}
