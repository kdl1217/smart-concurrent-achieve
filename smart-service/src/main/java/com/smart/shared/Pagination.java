package com.smart.shared;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author Kong, created on 2020-05-13T16:20.
 * @since 1.0.0-SNAPSHOT
 * @param <T>
 */
@Data
public class Pagination<T> implements Serializable {
    private static final long serialVersionUID = 3755706962843033939L;

    /**
     * 第几页
     */
    @ApiModelProperty(value = "第几页")
    private Integer pageNum;

    /**
     * 一页的纪录数
     */
    @ApiModelProperty(value = "一页的纪录数")
    private Integer pageSize;

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Integer totalData;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;

    /**
     * 本页数据列表
     */
    @ApiModelProperty(value = "本页数据列表")
    private List<T> list;

}
