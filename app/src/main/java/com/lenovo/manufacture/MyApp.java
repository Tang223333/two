package com.lenovo.manufacture;

import android.app.Application;

import com.google.gson.Gson;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class MyApp extends Application {

    private static Handler sHandler;
    private static Gson sGson;

    @Override
    public void onCreate() {
        super.onCreate();
        sGson=new Gson();
        sHandler=new Handler();
    }

    public static Handler getHandler(){
        return sHandler;
    }

    public static Gson getGson(){
        return sGson;
    }

    public static void getPostError(Context context) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
