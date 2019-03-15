package com.example.qyxy.coolweather;

import org.litepal.crud.DataSupport;

/**
 * Created by qyxy on 2019/3/14.
 */

public class city extends DataSupport {
    private int id;
    private String cityName;
    private String province_Name;

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
    public void setProvince_Name( String name ){
        this.province_Name = name;
    }
    public String getProvince_Name(){
        return province_Name;
    }
}
