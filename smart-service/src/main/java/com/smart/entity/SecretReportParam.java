package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 智联钥匙密钥更新结果反馈
 *
 * @author Kong, created on 2020-05-14T13:43.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecretReportParam {

    /**
     * 流水号
     */
    @ApiModelProperty(position = 1, value = "流水号")
    private String serialNumber;

    /**
     * 结果 0：成功，非0：失败
     */
    @ApiModelProperty(position = 2, value = "结果 0：成功，非0：失败")
    private Integer result;

    /**
     * 失败原因
     */
    @ApiModelProperty(position = 3, value = "失败原因")
    private String message;

    /**
     * 更新时间
     */
    @ApiModelProperty(position = 4, value = "更新时间")
    private Date updateTime;
}
