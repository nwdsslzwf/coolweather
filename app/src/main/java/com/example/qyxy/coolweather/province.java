package com.example.qyxy.coolweather;

import org.litepal.crud.DataSupport;

/**
 * Created by qyxy on 2019/3/14.
 */

public class province extends DataSupport {
    private int id;
    private String provinceName;

    public int getId(){
        return id;
    }
    public void setId( int id ){
        this.id = id;
    }
    public String getProvinceName(){
        return provinceName;
    }
    public void setProvinceName( String provinceName ){
        this.provinceName = provinceName;
    }
}

