package com.example.qyxy.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.StringTokenizer;

/**
 * Created by qyxy on 2019/3/15.
 */

public class Forecast {

    //public String cond_code_d;
    //public String cond_code_n;

    public String cond_txt_d;  //天气
    public String cond_txt_n;

    @SerializedName("data")
    public String my_data; //

    public String hum;
    public String mr;
    public String ms;
    public String pcpn;
    public String pop;
    public String pres;
    public String sr;
    public String ss;

    public String tmp_max; //
    public String tmp_min; //

    public String uv_index;
    public String vis;
    public String wind_deg;
    public String wind_dir;
    public String wind_sc;
    public String wind_spd;

}
