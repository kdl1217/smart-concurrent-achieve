package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 智联钥匙参数版本参数
 *
 * @author Kong, created on 2020-05-14T17:22.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IcdParamsParam {

    /**
     * 流水号
     */
    @ApiModelProperty(position = 1, value = "流水号")
    private String serialNumber;

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
     * 参数版本号
     */
    @ApiModelProperty(position = 4, value = "参数版本号")
    private String configVersion;
}
