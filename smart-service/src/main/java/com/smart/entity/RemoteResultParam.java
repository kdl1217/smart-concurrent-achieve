package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 远程控制结果参数
 *
 * @author Kong, created on 2020-05-14T14:32.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoteResultParam {

    /**
     * 返回流水号
     */
    @ApiModelProperty(position = 1, value = "返回流水号")
    private Long serialNumber;
}
