package com.lenovo.manufacture.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getYearToD(int time){
        Long timeZ=Long.valueOf(time);
        timeZ=timeZ*1000;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
        return simpleDateFormat.format(new Date(timeZ));
    }

    public static String getYearToM(int time){
        if ((time+"").equals("0")){
            return "null";
        }
        Long timeZ=Long.valueOf(time);
        timeZ=timeZ*1000;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(timeZ));
    }
}
