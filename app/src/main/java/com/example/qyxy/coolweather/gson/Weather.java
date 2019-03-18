package com.example.qyxy.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by qyxy on 2019/3/15.
 */

public class Weather {
    public Basic basic;
    public String status;
    @SerializedName( "daily_forecast" )
    public List< Forecast > forecastList;

    @SerializedName( "update" )
    public UpTime updata_time;
}
