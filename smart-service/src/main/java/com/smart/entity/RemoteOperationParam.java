package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 远程控制操作参数
 *
 * @author Kong, created on 2020-05-14T14:32.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoteOperationParam {

    /**
     * 操作类型（1-开, 2-关）
     */
    @ApiModelProperty(position = 1, value = "操作类型（1-开, 2-关）")
    private Integer type;

    /**
     * 钥匙PIN码
     */
    @ApiModelProperty(position = 2, value = "钥匙PIN码")
    private String pin;
}
