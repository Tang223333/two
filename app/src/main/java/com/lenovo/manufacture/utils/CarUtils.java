package com.lenovo.manufacture.utils;

import android.content.Intent;
import android.util.Log;

public class CarUtils {

    public static String getCarName(int carId){
        switch (carId){
            case 1:
                return "轿车汽车标准型";
            case 2:
                return "MPV汽车标准型";
            case 3:
                return "SUV汽车标准型";
            default:
                return "null";
        }
    }
    public static String getCarNameD(int carId){
        switch (carId){
            case 1:
                return "轿车汽车";
            case 2:
                return "MPV汽车";
            case 3:
                return "SUV汽车";
            default:
                return "null";
        }
    }
    public static String getCarPrice(int carId){
        switch (carId){
            case 1:
                return "2000";
            case 2:
                return "3000";
            case 3:
                return "4000";
            default:
                return "null";
        }
    }

    public static String getUserLineName(int userLineId){
        switch (userLineId){
            case 1:
                return "轿车车型生产线";
            case 2:
                return "MPV车型生产线";
            case 3:
                return "SUV车型生产线";
            default:
                return "";
        }
    }

    public static String getDdType(Integer type){
        switch (type){
            case 0:
                return "已下单";
            case 1:
                return "生产中";
            case 2:
                return "完成";
            default:
                return "null";
        }
    }

    public static Integer getCarColor(String color){
        if ("null".equals(color+"")){
            return 3;
        }else {
            return Integer.valueOf(color);
        }
    }

    public static String getInOutType(Integer type){
        if ((type+"").equals("null")){
            return "null";
        }
        switch (type){
            case 0:
                return "原材料";
            case 1:
                return "人员";
            case 2:
                return "生产线";
            case 3:
                return "维修生产环节";
            case 4:
                return "维修车辆";
            case 5:
                return "售出";
            default:
                return "null";
        }
    }

    public static String getKt(String kt){
        switch (kt){
            case "1":
                return "冷风";
            case "2":
                return "暖风";
            default:
                return "关";
        }
    }

    public static String getGz(Integer gz){
        if (gz==1) {
            return "开";
        }else {
            return "关";
        }
    }
}
