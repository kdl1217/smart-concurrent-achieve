package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 密钥请求
 *
 * @author Kong, created on 2020-05-14T11:12.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecretRequestParam {

    /**
     * 流水号
     */
    @ApiModelProperty(position = 1, value = "流水号")
    private String serialNumber;

}
