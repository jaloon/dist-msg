package com.pltone.distmsg.entity;

import java.util.Random;

/**
 * 基础数据
 *
 * @author chenlong
 * @version 1.0 2018-11-22
 */
public class BaseData {
    private Station[] depots;
    private Station[] stations;
    private String[] cars;
    private Random random;

    public Station[] getDepots() {
        return depots;
    }

    public Station[] getStations() {
        return stations;
    }

    public String[] getCars() {
        return cars;
    }

    public Random getRandom() {
        return random;
    }

    private BaseData() {
        depots = new Station[]{
                new Station("391A", "广西南宁油库"),
                new Station("398A", "柳州柳江油库"),
                new Station("GX0LJCD", "广西柳州油库")
        };
        stations = new Station[]{
                new Station("Z372SG001", "柳州三桥站"),
                new Station("Z372SG002", "柳州跃进站"),
                new Station("Z372SG003", "柳州胜翔站"),
                new Station("Z372SG007", "柳州西城站"),
                new Station("Z372SG009", "柳州柳乐站"),
                new Station("Z372SG016", "柳州学院站")
        };
        cars = new String[]{
                "桂A98326",
                "桂A98328",
                "桂A98329",
                "桂A98330",
                "桂A98331",
                "桂N62367"
        };
        random = new Random();
    }

    private static final BaseData instance;

    static {
        instance = new BaseData();
    }

    public static BaseData getInstance() {
        return instance;
    }

    public static Station getDepot() {
        return instance.depots[instance.random.nextInt(instance.depots.length)];
    }

    public static Station getStation() {
        return instance.stations[instance.random.nextInt(instance.stations.length)];
    }

    public static Station changeStation(Station station) {
        Station different = getStation();
        while (different.equals(station)) {
            different = getStation();
        }
        return different;
    }

    public static Station changeStation(String stationNo) {
        Station different = getStation();
        while (different.getNo().equals(stationNo)) {
            different = getStation();
        }
        return different;
    }

    public static String getCar() {
        return instance.cars[instance.random.nextInt(instance.cars.length)];
    }

    public static Random random() {
        return instance.random;
    }
}
