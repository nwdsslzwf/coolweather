package com.example.qyxy.coolweather.db;

import DataSupport;

/**
 * Created by qyxy on 2019/3/14.
 */

public class City extends DataSupport {
    private int id;
    private String cityName;

    public int getId(){
        return id;
    }
    public void setId( int id ){
        this.id = id;
    }
    public String getCityName(){
        return cityName;
    }
    public void setCityName( String cityName ){
        this.cityName = cityName;
    }
}
