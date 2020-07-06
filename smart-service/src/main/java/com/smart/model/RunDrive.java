package com.smart.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 车辆整车行驶数据
 *
 * @author Kong, created on 2020-05-14T10:29.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@ApiModel(description = "车辆整车行驶数据")
public class RunDrive implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9085764279307716042L;

    /**
     * 钥匙SN
     */
    @ApiModelProperty(position = 1, value = "钥匙SN")
    private String deviceSn;

    /**
     * 启动状态: 1-启动(keyon),2-运行(run),3-熄火(keyoff),4-其他状态 ,
     */
    @ApiModelProperty(position = 2, value = "启动状态: 1-启动(keyon),2-运行(run),3-熄火(keyoff),4-其他状态")
    private Integer carStatus;

    /**
     * 充电状态： 0-未知;1-停车充电；2-行驶充电；3-未充电状态，4-充电完成；（纯燃油车此值为空）
     */
    @ApiModelProperty(position = 3, value = "充电状态： 0-未知;1-停车充电；2-行驶充电；3-未充电状态，4-充电完成；（纯燃油车此值为空）")
    private Integer chargeStatus;

    /**
     * 运行模式: 0-未知; 1-纯电； 2-混动； 3-燃油；
     */
    @ApiModelProperty(position = 4, value = "运行模式: 0-未知; 1-纯电； 2-混动； 3-燃油；")
    private Integer runStatus;

    /**
     * 车速(km/h)
     */
    @ApiModelProperty(position = 5, value = "车速(km/h)")
    private String vehicleSpeed;

    /**
     * 剩余油量 (L)
     */
    @ApiModelProperty(position = 6, value = "剩余油量 (L)")
    private String fuelQuantity;

    /**
     * 剩余百分比油量 (%)
     */
    @ApiModelProperty(position = 7, value = "剩余百分比油量 (%)")
    private String percentageQuantity;

    /**
     * 剩余电量（百分比）;纯燃油车此值为空
     */
    @ApiModelProperty(position = 8, value = "剩余电量（百分比）;纯燃油车此值为空")
    private String soc;

    /**
     * 续航里程(km)
     */
    @ApiModelProperty(position = 9, value = "续航里程(km)")
    private String rechargeMileage;

    /**
     * 总里程(km)
     */
    @ApiModelProperty(position = 10, value = "总里程(km)")
    private String mileage;

    /**
     * 电压（V）
     */
    @ApiModelProperty(position = 11, value = "电压（V）")
    private String voltage;

    /**
     * 电流（A）
     */
    @ApiModelProperty(position = 12, value = "电流（A）")
    private String current;

    /**
     * DC-DC状态 0-未知; 1-工作；2-断开; 纯燃油车此值为空
     */
    @ApiModelProperty(position = 13, value = "DC-DC状态 0-未知; 1-工作；2-断开; 纯燃油车此值为空")
    private Integer dcdcStatus;

    /**
     * 档位: 0-空档, 1-1档, 2-2档, 3-3档, 4-4档, 5-5档, 6-6档, 11-L档,12-S档,13-倒档, 14-D档, 15-P档
     */
    @ApiModelProperty(position = 14, value = "档位: 0-空档, 1-1档, 2-2档, 3-3档, 4-4档, 5-5档, 6-6档,11-L档,12-S档,13-倒档, 14-D档, 15-P档")
    private Integer clutchStatus;

    /**
     * 车外温度(℃)
     */
    @ApiModelProperty(position = 15, value = "车外温度(℃)")
    private String outsideTemperature;

    /**
     * 车内温度(℃)
     */
    @ApiModelProperty(position = 16, value = "车内温度(℃)")
    private String insideTemperature;

    /**
     * 当前位置中文地址
     */
    @ApiModelProperty(position = 17, value = "当前位置中文地址")
    private String address;

    /**
     * 中控锁状态 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 18, value = "中控锁状态 0-未知, 1-开, 2-关")
    private Integer centralLockStatus;

    /**
     * 左前门状态 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 19, value = "左前门状态 0-未知, 1-开, 2-关")
    private Integer leftFrontDoorStatus;

    /**
     * 左后门状态 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 20, value = "左后门状态 0-未知, 1-开, 2-关")
    private Integer leftBackDoorStatus;

    /**
     * 右前门状态 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 21, value = "右前门状态 0-未知, 1-开, 2-关")
    private Integer rightFrontDoorStatus;

    /**
     * 右后门状态 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 22, value = "右后门状态 0-未知, 1-开, 2-关")
    private Integer rightBackDoorStatus;

    /**
     * 后备箱状态 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 23, value = "后备箱状态 0-未知, 1-开, 2-关")
    private Integer trunkStatus;

    /**
     * 引擎盖状态 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 24, value = "引擎盖状态 0-未知, 1-开, 2-关")
    private Integer frontCover;

    /**
     * 近光灯 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 25, value = "近光灯 0-未知, 1-开, 2-关")
    private Integer dippedHeadlightStatus;

    /**
     * 远光灯 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 26, value = "远光灯 0-未知, 1-开, 2-关")
    private Integer beanLightStatus;

    /**
     * 危险警示灯状态 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 27, value = "危险警示灯状态 0-未知, 1-开, 2-关")
    private Integer dangerLightStatus;

    /**
     * 前雾灯状态 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 28, value = "前雾灯状态 0-未知, 1-开, 2-关")
    private Integer frontFoglightStatus;

    /**
     * 后雾灯状态 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 29, value = "后雾灯状态 0-未知, 1-开, 2-关")
    private Integer backFoglightStatus;

    /**
     * 左前窗状态:
     * 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 30, value = "左前窗状态 0-未知, 1-开, 2-关")
    private Integer leftFrontWindowStatus;

    /**
     * 右前窗状态:
     * 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 31, value = "右前窗状态 0-未知, 1-开, 2-关")
    private Integer rightFrontWindowStatus;

    /**
     * 左后窗状态:
     * 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 32, value = "左后窗状态 0-未知, 1-开, 2-关")
    private Integer leftBackWindowStatus;

    /**
     * 右后窗状态:
     * 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 33, value = "右后窗状态 0-未知, 1-开, 2-关")
    private Integer rightBackWindowStatus;

    /**
     * 天窗状态
     * 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 34, value = "天窗状态 0-未知, 1-开, 2-关")
    private Integer skylightStatus;

    /**
     * 左前胎压状态
     * 0-未知，1-正常，2-异常
     */
    @ApiModelProperty(position = 35, value = "左前胎压状态 0-未知, 1-开, 2-关")
    private Integer leftFrontTirePressure;

    /**
     * 左后胎压状态
     * 0-未知，1-正常，2-异常
     */
    @ApiModelProperty(position = 36, value = "左后胎压状态 0-未知, 1-开, 2-关")
    private Integer leftRearTirePressure;

    /**
     * 右前胎压状态
     * 0-未知，1-正常，2-异常
     */
    @ApiModelProperty(position = 37, value = "右前胎压状态 0-未知, 1-开, 2-关")
    private Integer rightFrontTirePressure;

    /**
     * 右后胎压状态
     * 0-未知，1-正常，2-异常
     */
    @ApiModelProperty(position = 38, value = "右后胎压状态 0-未知, 1-开, 2-关")
    private Integer rightRearTirePressure;

    /**
     * 瞬时油耗（单位：L/100KM）
     */
    @ApiModelProperty(position = 39, value = "瞬时油耗（单位：L/100KM）")
    private String dynamicalFuel;

    /**
     * 平均油耗（单位：L/100KM）
     */
    @ApiModelProperty(position = 40, value = "平均油耗（单位：L/100KM）")
    private String avgFuel;

    /**
     * 空调状态: 0-未知, 1-开, 2-关
     */
    @ApiModelProperty(position = 41, value = "空调状态: 0-未知, 1-开, 2-关")
    private Integer airConditionerStatus;

    /**
     * 空调风量状态：0 关风扇。 1: 一级风， 2：二级风 ...
     */
    @ApiModelProperty(position = 42, value = "空调风量状态：0 关风扇。 1: 一级风， 2：二级风 ...")
    private Integer airConditionerFanStatus;

    /**
     * 采集时间
     */
    @ApiModelProperty(position = 43, value = "采集时间(车辆数据时间)")
    private Date detectionTime;

    /**
     * 接收时间(状态更新时间)
     */
    @ApiModelProperty(position = 44, value = "接收时间(状态更新时间)")
    private Date receiveTime;

    /**
     * 流水号
     */
    @ApiModelProperty(position = 45, value = "流水号")
    private Long serialNumber = 0L;

    /**
     * 最新数据：
     * 0 – 非最新数据，1 – 最新数据
     * 默认 1 - 最新数据
     */
    @ApiModelProperty(position = 46, value = "最新数据：0 – 非最新数据，1 – 最新数据")
    private Integer latest = 1;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

