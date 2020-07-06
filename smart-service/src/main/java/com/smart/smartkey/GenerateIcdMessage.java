package com.smart.smartkey;

import com.smart.entity.IcdEquipment;
import com.smart.shared.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * 生成钥匙数据
 *      -- 生成的数据需要与钥匙库保持一致
 *
 * @author Kong, created on 2020-06-12T15:56.
 * @since 1.0.0-SNAPSHOT
 */
@Component
public class GenerateIcdMessage {

    /**
     * 初始化生成数量
     */
    @Value("${icd.generate-number}")
    protected int smartKeyNumber;

    /**
     * 初始化钥匙数据
     */
    public void init() {
        for (int i = 0; i < smartKeyNumber; i++) {
            String imei = MessageFormat.format(Constant.BasicInformation.imei + "{0,number,000}", i);
            String sn = MessageFormat.format(Constant.BasicInformation.sn + "{0,number,000}", i);
            String iccid = MessageFormat.format(Constant.BasicInformation.iccid + "{0,number,000}", i);
            String imsi = MessageFormat.format(Constant.BasicInformation.imsi + "{0,number,000}", i);
            IcdBasicData.icdMap.put(imei, new IcdEquipment(imei, sn, iccid, imsi));
            IcdBasicData.icdKeyMap.put(imei, Constant.BasicInformation.key);
            IcdBasicData.pinMap.put(imei, Constant.BasicInformation.pin);
        }
    }
}
