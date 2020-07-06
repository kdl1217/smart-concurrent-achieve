package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 智联钥匙检测
 *
 * @author Kong, created on 2020-05-14T17:26.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IcdCheckParam {

    /**
     * 检查码
     */
    @ApiModelProperty(position = 1, value = "检查码")
    private String checkCode;
}
