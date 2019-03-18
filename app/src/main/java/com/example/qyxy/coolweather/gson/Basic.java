package com.example.qyxy.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qyxy on 2019/3/15.
 */

public class Basic {

    @SerializedName( "cid" )
    public String weatherId;

    @SerializedName( "location" )
    public String cityName;

    public String parent_city;

    @SerializedName( "admin_area" )
    public String city_to_province;

    public String cnty;

    public String lat;

    public String lon;

    public String tz;
}
