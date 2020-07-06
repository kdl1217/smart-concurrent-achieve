package com.smart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 远程控制时间控制
 *
 * @author Kong, created on 2020-05-14T10:05.
 * @since 1.0.0-SNAPSHOT
 */
@Data
public class RemoteTime {

    /**
     * 锁定时长
     */
    @ApiModelProperty(position = 1, value = "锁定时长")
    private Integer lockTime;

    /**
     * PIN码有效时长
     */
    @ApiModelProperty(position = 2, value = "PIN码有效时长")
    private Integer effectiveTime;

    /**
     * 返回流水号
     */
    @ApiModelProperty(position = 3, value = "返回流水号")
    private Long serialNumber;

    public RemoteTime(Integer lockTime, Integer effectiveTime, Long serialNumber) {
        this.lockTime = lockTime;
        this.effectiveTime = effectiveTime;
        this.serialNumber = serialNumber;
    }
}
