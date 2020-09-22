package com.lenovo.manufacture.utils;

import com.google.gson.JsonObject;
import com.lenovo.manufacture.MyApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyOk {

    private static OkHttpClient client = new OkHttpClient();
    private static  String BASE_URL="http://192.168.3.13:8085/";

    public static String getString(String url, FormBody.Builder builder){
        Request request = new Request.Builder()
                .url(BASE_URL+url)
                .post(builder.build())
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T>T getT(String url,FormBody.Builder builder,Class<T> classt){
        String string = getString(url, builder);
        return MyApp.getGson().fromJson(string,classt);
    }

    public static <T>List<T> getTList(String url,FormBody.Builder builder,Class<T> tClass){
        String string = getString(url, builder);
        List<T> tList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(string);
            JSONArray jsonArray=new JSONArray(jsonObject.getString("data"));
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject=new JSONObject(jsonArray.get(i).toString());
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()){
                    String next = keys.next();
                    tList.add(MyApp.getGson().fromJson(jsonObject.getString(next),tClass));
                }
            }
            return tList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
