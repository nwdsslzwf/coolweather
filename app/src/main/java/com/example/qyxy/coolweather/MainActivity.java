package com.example.qyxy.coolweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private province zhejiang_Province;
    private province jiangsu_Province;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zhejiang_Province.setProvinceName( "浙江省" );
        jiangsu_Province.setProvinceName( "江苏省" );

    }
}
