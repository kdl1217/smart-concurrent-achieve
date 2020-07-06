package com.smart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 报警数据
 *
 * @author Kong, created on 2020-05-14T14:33.
 * @since 1.0.0-SNAPSHOT
 */
@Data
public class Alarm {

    /**
     * 报警类型
     */
    @ApiModelProperty(position = 1, value = "报警类型")
    private String alarmType;

    /**
     * 报警发生时间
     */
    @ApiModelProperty(position = 2, value = "报警发生时间")
    private Date alarmTime;

    /**
     * 报警地址中文
     */
    @ApiModelProperty(position = 3, value = "报警地址中文")
    private String alarmAdress;
}
