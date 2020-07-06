package com.smart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 钥匙设备
 *
 * @author Kong, created on 2020-06-12T16:00.
 * @since 1.0.0-SNAPSHOT
 */
@Data
@AllArgsConstructor
public class IcdEquipment {

    private String imei;

    private String sn;

    private String iccid;

    private String imsi;
}
