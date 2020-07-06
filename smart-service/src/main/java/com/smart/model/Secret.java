package com.smart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 智联钥匙密钥信息
 *
 * @author Kong, created on 2020-05-14T11:43.
 * @since 1.0.0-SNAPSHOT
 */
@Data
public class Secret {

    /**
     * 钥匙IMEI
     */
    @ApiModelProperty(position = 1, value = "IMEI")
    private String imei;

    /**
     * 密钥KEY
     */
    @ApiModelProperty(position = 2, value = "密钥KEY")
    private String deviceKey;
}
