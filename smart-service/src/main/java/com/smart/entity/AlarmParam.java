package com.smart.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 报警请求参数
 *
 * @author Kong, created on 2020-05-14T14:21.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmParam {

    /**
     * 当前页码
     */
    @ApiModelProperty(position = 1, value = "当前页码")
    private Integer pageNum;

    /**
     * 每页的数据记录条数
     */
    @ApiModelProperty(position = 1, value = "每页的数据记录条数")
    private Integer pageSize;
}
