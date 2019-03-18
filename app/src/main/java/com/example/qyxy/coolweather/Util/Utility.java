package com.example.qyxy.coolweather.Util;

import android.content.Context;
import android.widget.Toast;

import com.example.qyxy.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by qyxy on 2019/3/15.
 */

public class Utility {
    public static Weather handleWeatherResponse(String response, Context context ){
        try{
            //Toast.makeText(context, "正在解析服务器数据1", Toast.LENGTH_SHORT).show();
            JSONObject jsonObject = new JSONObject( response );
            JSONArray jsonArray = jsonObject.getJSONArray( "HeWeather6" );
            String weatherContent = jsonArray.getJSONObject( 0 ).toString();
            Gson gson = new Gson();
            Weather weather = new Weather();
            weather = gson.fromJson(weatherContent, Weather.class );
            return weather;
        }catch ( Exception e ){
            //Toast.makeText(context, "yichang", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return null;
    }
}
