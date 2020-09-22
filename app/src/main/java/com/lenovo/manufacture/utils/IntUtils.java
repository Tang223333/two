package com.lenovo.manufacture.utils;

import java.text.DecimalFormat;

public class IntUtils {

    public static String getInt(int i){
        return new DecimalFormat(",###").format(i);
    }
}
