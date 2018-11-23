package com.pltone.distmsg.entity;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 配送信息
 *
 * @author chenlong
 * @version 1.0 2018-11-22
 */
@Getter
@ToString
public class DistInfo {
    private String distributNO; //1811182022
    private String effectDate; //2018-11-18 20:22:24
    private String vehicNo; //桂A98326
    private int binNum; // 1
    private String deptId; //Z372SG001
    private String deptName; //中国石油广西南宁金明加油站
    private String depotNo; //391A
    private String depotName; //广西南宁油库


    private static final DateTimeFormatter DISTRIBUT_NO_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmm");
    private static final DateTimeFormatter EFFECT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static DistInfo build() {
        DistInfo distInfo = new DistInfo();
        LocalDateTime now = LocalDateTime.now();
        distInfo.distributNO = now.format(DISTRIBUT_NO_FORMATTER);
        distInfo.effectDate = now.format(EFFECT_DATE_FORMATTER);
        distInfo.vehicNo = BaseData.getCar();
        distInfo.binNum = BaseData.random().nextInt(3) + 1;
        Station depot = BaseData.getDepot();
        Station station = BaseData.getStation();
        distInfo.deptId = station.getNo();
        distInfo.deptName = station.getName();
        distInfo.depotNo = depot.getNo();
        distInfo.depotName = depot.getName();
        return distInfo;
    }

    public static void change(DistInfo distInfo) {
        Station station = BaseData.changeStation(distInfo.deptId);
        distInfo.deptId = station.getNo();
        distInfo.deptName = station.getName();
    }
}
