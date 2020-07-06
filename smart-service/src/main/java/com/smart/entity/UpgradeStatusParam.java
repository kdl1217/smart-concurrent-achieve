package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OTA升级状态
 *
 * @author Kong, created on 2020-05-14T14:04.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpgradeStatusParam {

    /**
     * 流水号
     */
    @ApiModelProperty(position = 1, value = "流水号")
    private String serialNumber;


    /**
     * 当前软件版本号
     */
    @ApiModelProperty(position = 2, value = "当前软件版本号")
    private String softwareVersion;

    /**
     * 升级的软件版本号
     */
    @ApiModelProperty(position = 3, value = "升级的软件版本号")
    private String upgradeVersion;

    /**
     * 升级状态
     */
    @ApiModelProperty(position = 4, value = "升级状态")
    private Integer status;

}
