package com.smart.shared;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口返回对象
 *
 * @param <T> 返回数据泛型
 * @author Kong, created on 2020-05-13T11:14.
 * @since 1.0.0-SNAPSHOT
 */
@Data
public class JsonMessage<T> {

    @ApiModelProperty(value = "结果状态：0成功，其它失败")
    private String status;          // 结果状态：0成功，其它失败

    @ApiModelProperty(value = "说明信息")
    private String message;    // 说明信息

    @ApiModelProperty(value = "返回数据")
    private T data;            // 返回数据

    public JsonMessage(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public JsonMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public JsonMessage(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public JsonMessage(String status) {
        this.status = status;
    }

    public JsonMessage() {
    }

}
