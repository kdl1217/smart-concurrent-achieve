package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 钥匙查询车辆状态
 *
 * @author Kong, created on 2020-05-14T15:55.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
public class VehicleQueryParam {

    /**
     * 请求类型：1 – 自动刷新，2 – 下拉刷新
     */
    @ApiModelProperty(position = 1, value = "请求类型：1 – 自动刷新，2 – 下拉刷新")
    private Integer type;

    /**
     * 流水号
     */
    @ApiModelProperty(position = 2, value = "流水号")
    private Long serialNumber;


    public VehicleQueryParam(Integer type) {
        this.type = type;
    }
}
