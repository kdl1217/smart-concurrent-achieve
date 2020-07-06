package com.smart.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 钥匙绑定数据
 *
 * @author Kong, created on 2020-05-14T14:49.
 * @since 1.0.0-SNAPSHOT
 */
@Data
public class BindData {

    /**
     * 绑定结果 0=未绑定，1=已绑定
     */
    @ApiModelProperty(position = 1, value = "绑定结果 0=未绑定，1=已绑定")
    private Integer bindStatus;

    /**
     * 钥匙SN
     */
    @ApiModelProperty(position = 2, value = "钥匙SN")
    private String deviceSn;

    /**
     * 车辆VIN
     */
    @ApiModelProperty(position = 3, value = "车辆VIN")
    private String vin;

    /**
     * ICCID
     */
    @ApiModelProperty(position = 4, value = "ICCID")
    private String iccid;

    /**
     * 钥匙PIN码
     */
    @ApiModelProperty(position = 5, value = "钥匙PIN码")
    private String pinCode;

    /**
     * 绑定时间
     */
    @ApiModelProperty(position = 6, value = "绑定时间")
    private Date bindTime;
}
