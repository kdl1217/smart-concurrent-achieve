package com.smart.run;

import com.smart.model.BindData;
import com.smart.model.RemoteTime;
import com.smart.server.SmartKeyServer;
import com.smart.shared.Constant;
import com.smart.shared.JsonMessage;
import com.smart.smartkey.GenerateIcdMessage;
import com.smart.smartkey.IcdBasicData;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 启动器
 *
 * @author Kong, created on 2020-06-16T08:53.
 * @since 1.0.0-SNAPSHOT
 */
@Log4j2
@Component
public class BootRunner implements CommandLineRunner {

    @Autowired
    protected GenerateIcdMessage generateIcdMessage;

    @Autowired
    protected SmartKeyServer keyServer;
    /**
     * 已激活设备数量
     */
    private AtomicInteger activeNumber = new AtomicInteger(0);
    /**
     * 线程集合
     */
    private List<Thread> workers = new ArrayList<>();

    /**
     * 启动线程
     */
    private boolean start = true;

    /**
     * 执行定时任务
     */
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    @Override
    public void run(String... args) throws Exception {

        // 初始化设备信息
        generateIcdMessage.init();

        // 创建线程任务
        createThreadTask();

        // 启动线程
        this.workers.forEach(Thread::start);

        // 关闭服务时，关闭线程。
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            log.info("shutdown ...");
            this.start = false;
            this.workers.forEach(Thread::interrupt);
            this.workers.clear();
            this.scheduledExecutorService.shutdown();
        }));
    }

    /**
     * 监控数据
     */
    public void monitor() {
        this.scheduledExecutorService.schedule(() -> {
            log.info("current connect device number is {} ...", activeNumber.get());
        }, 30, TimeUnit.SECONDS);
    }

    /**
     * 创建线程任务
     */
    private void createThreadTask() {
        IcdBasicData.icdMap.forEach((key, value) -> {
            Thread worker = new Thread(() -> {
                while (this.start) {
                    try {
                        // 查询设备绑定信息
                        JsonMessage<BindData> bindDataJsonMessage = keyServer.queryBindStatus(key);
                        if (!bindDataJsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)) {
                            return;
                        }
                        waitAMoment();

                        // 用户确认绑定信息
                        JsonMessage<String> confirmJsonMessage = keyServer.confirmKey(key);
                        if (!confirmJsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)
                                || !confirmJsonMessage.getStatus().equals("2014")) {
                            return;
                        } else {
                            // 激活设备信息
                            this.activeNumber.addAndGet(1);
                        }
                        waitAMoment();

                        // 查询车况信息  （不需要返回数据）
                        keyServer.queryVehicleStatus(key);
                        waitAMoment();
                        // 查询报警信息  （不需要返回数据）
                        keyServer.queryAlarmList(key);
                        waitAMoment();

                        // 查询钥匙最新OTA版本信息
                        keyServer.queryLatestVersion(key);
                        waitAMoment();
                        // 上报OTA升级状态
                        keyServer.upgradeStatus(key);
                        waitAMoment();
                        // OTA升级结果反馈
                        keyServer.reportUpgradeStatus(key);
                        waitAMoment();

                        // 查询钥匙参数信息
                        keyServer.queryConfigInfo(key);
                        waitAMoment();
                        // 反馈钥匙参数设置
                        keyServer.reportUpdateConfigStatus(key);
                        waitAMoment();

                        // 更新钥匙密钥
                        keyServer.updateDeviceKey(key);
                        waitAMoment();
                        // 反馈密钥更新结果
                        keyServer.reportUpdateKeyStatus(key);
                        waitAMoment();

                        // 远控 - 寻车
                        remote(key, Constant.RemoteType.FIND_CAR);
                        waitAMoment();

                        // 远控 - 车门
                        remote(key, Constant.RemoteType.CAR_DOOR);
                        waitAMoment();

                        // 远控 - 空调
                        remote(key, Constant.RemoteType.AIR_CONDITIONER);
                        waitAMoment();

                        // 远控 - 车窗
                        remote(key, Constant.RemoteType.CAR_WINDOW);
                        waitAMoment();

                        // 远控 - 后备箱
                        remote(key, Constant.RemoteType.CAR_TRUNK);
                        waitAMoment();

                        // 远控 - 引擎
                        remote(key, Constant.RemoteType.CAR_POWER);
                        waitAMoment();
                    } catch (Exception e) {
                        log.error("execute request exception :{}", ExceptionUtils.getMessage(e));
                    }
                }
            });
            this.workers.add(worker);
        });
    }

    /**
     * 等待一会
     */
    private void waitAMoment() throws InterruptedException {
        Thread.sleep(30000);
    }

    /**
     * 下发远控指令
     * @param imei          钥匙IMEI
     * @param remoteType    远控类型 {@link com.smart.shared.Constant.RemoteType}
     */
    public void remote(String imei, int remoteType) {
        JsonMessage<RemoteTime> remoteTimeJsonMessage = null;
        // 调用远控接口
        switch (remoteType) {
            case Constant.RemoteType.FIND_CAR:          // 寻车
                remoteTimeJsonMessage = keyServer.findCar(imei);
                break;
            case Constant.RemoteType.CAR_DOOR:          // 车门
                remoteTimeJsonMessage = keyServer.carDoor(imei);
                break;
            case Constant.RemoteType.AIR_CONDITIONER:   // 空调
                remoteTimeJsonMessage = keyServer.airConditioner(imei);
                break;
            case Constant.RemoteType.CAR_WINDOW:        // 车窗
                remoteTimeJsonMessage = keyServer.carWin(imei);
                break;
            case Constant.RemoteType.CAR_TRUNK:         // 后备箱
                remoteTimeJsonMessage = keyServer.carTrunk(imei);
                break;
            case Constant.RemoteType.CAR_POWER:         // 引擎
                remoteTimeJsonMessage = keyServer.carPower(imei);
                break;
        }
        // 验证后准备开始查询结果
        if (null != remoteTimeJsonMessage && remoteTimeJsonMessage.getStatus().equals(Constant.DEFAULT_SUCCESS)) {
            // 远控下发成功后，查询结果
            remoteResult(imei);
        }
    }

    /**
     * 轮询获取远控结果
     * @param imei 钥匙IMEI
     */
    private void remoteResult(String imei) {
        try {
            // 轮询次数限制
            int tryNum = 30;
            // 轮询查询结果
            while (tryNum > 0) {
                JsonMessage<String> result = keyServer.result(imei);
                if (null == result || !result.getStatus().equals(Constant.REMOTE_WAIT)) {
                    break;
                }
                tryNum --;
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            log.error("remoteResult error, {}", ExceptionUtils.getMessage(e));
        }

    }

}
