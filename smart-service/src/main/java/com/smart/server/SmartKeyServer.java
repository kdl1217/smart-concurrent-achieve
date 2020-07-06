package com.smart.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smart.entity.*;
import com.smart.model.*;
import com.smart.shared.Constant;
import com.smart.shared.JsonMessage;
import com.smart.shared.Pagination;
import com.smart.smartkey.HeaderFactory;
import com.smart.smartkey.IcdBasicData;
import com.smart.util.HttpClientUtil;
import com.smart.util.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

/**
 * 请求钥匙服务
 *
 * @author Kong, created on 2020-06-12T15:20.
 * @since 1.0.0-SNAPSHOT
 */
@Log4j2
@Component
public class SmartKeyServer {

    /**
     * 服务地址
     */
    @Value("${icd.server-address}")
    protected String serverAddress;


    /**
     * 获取钥匙绑定信息
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<BindData> queryBindStatus(String imei) {
        JsonMessage<BindData> jsonMessage = null;
        try {
            IcdCheckParam icdCheckParam = IcdBasicData.getIcdCheckParam(imei);
            // 请求钥匙绑定信息
            jsonMessage = httpPostRequest(Constant.RequestAddress.QUERY_BIND_STATUS,
                    imei, JSON.toJSONString(icdCheckParam));
        } catch (Exception e) {
            log.error("queryBindStatus error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 用户确认钥匙信息
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<String> confirmKey(String imei) {
        JsonMessage<String> jsonMessage = null;
        try {
            jsonMessage = httpPostRequest(Constant.RequestAddress.CONFIRM_KEY, imei, null);
        } catch (Exception e) {
            log.error("confirmKey error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 查询车辆车况信息
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<RunDrive> queryVehicleStatus(String imei) {
        JsonMessage<RunDrive> jsonMessage = null;
        try {
            VehicleQueryParam vehicleQueryParam = new VehicleQueryParam(Constant.PackageType.NORMAL);
            jsonMessage = httpPostRequest(Constant.RequestAddress.QUERY_VEHICLE_STATUS,
                    imei, JSON.toJSONString(vehicleQueryParam));
        } catch (Exception e) {
            log.error("queryVehicleStatus error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 请求查询报警信息
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<Pagination<Alarm>> queryAlarmList(String imei) {
        JsonMessage<Pagination<Alarm>> jsonMessage = null;
        try {
            AlarmParam alarmParam = new AlarmParam(1, 10);
            jsonMessage = httpPostRequest(Constant.RequestAddress.QUERY_ALARM,
                    imei, JSON.toJSONString(alarmParam));
        } catch (Exception e) {
            log.error("queryAlarmList error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 查询钥匙最新OTA版本信息
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<UpgradeVersion> queryLatestVersion(String imei) {
        JsonMessage<UpgradeVersion> jsonMessage = null;
        try {
            IcdUpgradeParam upgradeParam = new IcdUpgradeParam(Constant.BasicInformation.SUPPLIER_CODE,
                    Constant.BasicInformation.DEVICE_MODEL, null);
            jsonMessage = httpPostRequest(Constant.RequestAddress.QUERY_LATEST_VERSION,
                    imei, JSON.toJSONString(upgradeParam));
        } catch (Exception e) {
            log.error("queryLatestVersion error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 上报OTA升级状态【用户确认/拒绝】
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<String> upgradeStatus(String imei) {
        JsonMessage<String> jsonMessage = null;
        try {
            // OTA相关功能没有，不用存储细节部分 【上下文】
            long serialNumber = Instant.now().toEpochMilli();
            UpgradeStatusParam statusParam = new UpgradeStatusParam(String.valueOf(serialNumber), null, null, null);
            jsonMessage = httpPostRequest(Constant.RequestAddress.UPGRADE_STATUS,
                    imei, JSON.toJSONString(statusParam));
        } catch (Exception e) {
            log.error("upgradeStatus error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * OTA升级结果反馈
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<String> reportUpgradeStatus(String imei) {
        JsonMessage<String> jsonMessage = null;
        try {
            // OTA相关功能没有，不用存储细节部分 【上下文】
            long serialNumber = Instant.now().toEpochMilli();
            UpgradeResultParam resultParam = new UpgradeResultParam(String.valueOf(serialNumber), null, null, null, null, null);
            jsonMessage = httpPostRequest(Constant.RequestAddress.REPORT_UPGRADE_STATUS,
                    imei, JSON.toJSONString(resultParam));
        } catch (Exception e) {
            log.error("reportUpgradeStatus error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 查询钥匙参数信息
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<IckeyParam> queryConfigInfo(String imei) {
        JsonMessage<IckeyParam> jsonMessage = null;
        try {
            long serialNumber = Instant.now().toEpochMilli();
            IcdParamsParam icdParam = new IcdParamsParam(String.valueOf(serialNumber),
                    Constant.BasicInformation.SUPPLIER_CODE,
                    Constant.BasicInformation.DEVICE_MODEL, null);
            jsonMessage = httpPostRequest(Constant.RequestAddress.QUERY_PARAM_CONFIG,
                    imei, JSON.toJSONString(icdParam));
            // 成功，有返回对应的参数版本，存储流水号
            if (null != jsonMessage && null == jsonMessage.getData()) {
                IcdBasicData.paramSerialMap.put(imei, serialNumber);
            }
        } catch (Exception e) {
            log.error("queryConfigInfo error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 反馈钥匙参数设置
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<String> reportUpdateConfigStatus(String imei) {
        JsonMessage<String> jsonMessage = null;
        try {
            Long serialNumber = IcdBasicData.paramSerialMap.get(imei);
            if (null != serialNumber) {
                // 反馈成功
                IcdParamResultParam resultParam = new IcdParamResultParam(String.valueOf(serialNumber),
                        0, null, Date.from(Instant.now()));
                jsonMessage = httpPostRequest(Constant.RequestAddress.REPORT_PARAM_STATUS,
                        imei, JSON.toJSONString(resultParam));
            }
        } catch (Exception e) {
            log.error("reportUpdateConfigStatus error!!!, this imei is {}", imei);
        } finally {
            // 执行完反馈删除参数流水号
            IcdBasicData.paramSerialMap.remove(imei);
        }
        return jsonMessage;
    }

    /**
     * 更新钥匙密钥
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<Secret> updateDeviceKey(String imei) {
        JsonMessage<Secret> jsonMessage = null;
        try {
            long serialNumber = Instant.now().toEpochMilli();
            SecretRequestParam requestParam = new SecretRequestParam(String.valueOf(serialNumber));
            jsonMessage = httpPostRequest(Constant.RequestAddress.UPDATE_DEVICE_KEY,
                    imei, JSON.toJSONString(requestParam));
            // 成功，有返回对应的参数版本，存储流水号
            if (null != jsonMessage && jsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)) {
                IcdBasicData.secretSerialMap.put(imei, serialNumber);
            }
        } catch (Exception e) {
            log.error("updateDeviceKey error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }


    /**
     * 反馈密钥更新结果
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<String> reportUpdateKeyStatus(String imei) {
        JsonMessage<String> jsonMessage = null;
        try {
            Long serialNumber = IcdBasicData.secretSerialMap.get(imei);
            if (null != serialNumber) {
                // 反馈失败，不更新密钥
                SecretReportParam reportParam = new SecretReportParam(String.valueOf(serialNumber),
                        1, null, Date.from(Instant.now()));
                jsonMessage = httpPostRequest(Constant.RequestAddress.REPORT_UPDATE_KEY_STATUS,
                        imei, JSON.toJSONString(reportParam));
            }
        } catch (Exception e) {
            log.error("reportUpdateKeyStatus error!!!, this imei is {}", imei);
        } finally {
            // 执行完反馈删除密钥更新流水号
            IcdBasicData.secretSerialMap.remove(imei);
        }
        return jsonMessage;
    }

    /**
     * 远控 - 寻车
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<RemoteTime> findCar(String imei) {
        JsonMessage<RemoteTime> jsonMessage = null;
        try {
            RemoteOperationParam operationParam = new RemoteOperationParam(1, IcdBasicData.pinMap.get(imei));
            jsonMessage = httpPostRequest(Constant.RequestAddress.FIND_CAR,
                    imei, JSON.toJSONString(operationParam));
            // 下发完远控存储流水号
            if (null != jsonMessage && jsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)) {
                IcdBasicData.remoteSerialMap.put(imei, jsonMessage.getData().getSerialNumber());
            }
        } catch (Exception e) {
            log.error("findCar error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 远控 - 车门
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<RemoteTime> carDoor(String imei) {
        JsonMessage<RemoteTime> jsonMessage = null;
        try {
            RemoteOperationParam operationParam = new RemoteOperationParam(1, IcdBasicData.pinMap.get(imei));
            jsonMessage = httpPostRequest(Constant.RequestAddress.CAR_DOOR,
                    imei, JSON.toJSONString(operationParam));
            // 下发完远控存储流水号
            if (null != jsonMessage && jsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)) {
                IcdBasicData.remoteSerialMap.put(imei, jsonMessage.getData().getSerialNumber());
            }
        } catch (Exception e) {
            log.error("carDoor error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 远控 - 空调
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<RemoteTime> airConditioner(String imei) {
        JsonMessage<RemoteTime> jsonMessage = null;
        try {
            RemoteOperationParam operationParam = new RemoteOperationParam(1, IcdBasicData.pinMap.get(imei));
            jsonMessage = httpPostRequest(Constant.RequestAddress.AIR_CONDITIONER,
                    imei, JSON.toJSONString(operationParam));
            // 下发完远控存储流水号
            if (null != jsonMessage && jsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)) {
                IcdBasicData.remoteSerialMap.put(imei, jsonMessage.getData().getSerialNumber());
            }
        } catch (Exception e) {
            log.error("airConditioner error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }


    /**
     * 远控 - 车窗
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<RemoteTime> carWin(String imei) {
        JsonMessage<RemoteTime> jsonMessage = null;
        try {
            RemoteOperationParam operationParam = new RemoteOperationParam(1, IcdBasicData.pinMap.get(imei));
            jsonMessage = httpPostRequest(Constant.RequestAddress.CAR_WIN,
                    imei, JSON.toJSONString(operationParam));
            // 下发完远控存储流水号
            if (null != jsonMessage && jsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)) {
                IcdBasicData.remoteSerialMap.put(imei, jsonMessage.getData().getSerialNumber());
            }
        } catch (Exception e) {
            log.error("carWin error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 远控 - 后备箱
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<RemoteTime> carTrunk(String imei) {
        JsonMessage<RemoteTime> jsonMessage = null;
        try {
            RemoteOperationParam operationParam = new RemoteOperationParam(1, IcdBasicData.pinMap.get(imei));
            jsonMessage = httpPostRequest(Constant.RequestAddress.CAR_TRUNK,
                    imei, JSON.toJSONString(operationParam));
            // 下发完远控存储流水号
            if (null != jsonMessage && jsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)) {
                IcdBasicData.remoteSerialMap.put(imei, jsonMessage.getData().getSerialNumber());
            }
        } catch (Exception e) {
            log.error("carTrunk error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }



    /**
     * 远控 - 引擎
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<RemoteTime> carPower(String imei) {
        JsonMessage<RemoteTime> jsonMessage = null;
        try {
            RemoteOperationParam operationParam = new RemoteOperationParam(1, IcdBasicData.pinMap.get(imei));
            jsonMessage = httpPostRequest(Constant.RequestAddress.CAR_POWER,
                    imei, JSON.toJSONString(operationParam));
            // 下发完远控存储流水号
            if (null != jsonMessage && jsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)) {
                IcdBasicData.remoteSerialMap.put(imei, jsonMessage.getData().getSerialNumber());
            }
        } catch (Exception e) {
            log.error("carPower error!!!, this imei is {}", imei);
        }
        return jsonMessage;
    }

    /**
     * 查询远控结果
     * @param imei  钥匙IMEI
     * @return  HTTP 返回数据
     */
    public JsonMessage<String> result(String imei) {
        JsonMessage<String> jsonMessage = null;
        try {
            Long serialNumber = IcdBasicData.remoteSerialMap.get(imei);
            if (null != serialNumber) {
                RemoteResultParam resultParam = new RemoteResultParam(serialNumber);
                jsonMessage = httpPostRequest(Constant.RequestAddress.REMOTE_RESULT,
                        imei, JSON.toJSONString(resultParam));
            }
        } catch (Exception e) {
            log.error("result error!!!, this imei is {}", imei);
        } finally {
            // 远控完成之后，删除远控流水号
            if (null != jsonMessage && jsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)) {
                IcdBasicData.remoteSerialMap.remove(imei);
            }
        }
        return jsonMessage;
    }

    /**
     * 组装请求钥匙接口
     * @param url           请求地址
     * @param imei          钥匙IMEI
     * @param json          请求参数
     * @param <T>           泛型
     * @return      HTTP 返回数据
     * @throws Exception        请求异常和类型转换异常
     */
    private <T> JsonMessage<T> httpPostRequest(String url, String imei, String json) throws Exception {
        String key = IcdBasicData.icdKeyMap.get(imei);
        if (StringUtil.isBlank(key)) {
            return null;
        }
        // 请求钥匙绑定信息
        String response = HttpClientUtil.httpPostRequest(requestAddress(url),
                HeaderFactory.getHeaderParams(imei, key), json);
        return JSON.parseObject(response, new TypeReference<JsonMessage<T>>(){});
    }

    private String requestAddress(String url) {
        return serverAddress + url;
    }
}
