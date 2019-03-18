package com.example.qyxy.coolweather;

import android.content.SharedPreferences;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qyxy.coolweather.Util.HttpUtil;
import com.example.qyxy.coolweather.Util.Utility;
import com.example.qyxy.coolweather.gson.Forecast;
import com.example.qyxy.coolweather.gson.Weather;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

 public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdataTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout forecastLayout;
     private ImageView bingPicImg;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toast.makeText(WeatherActivity.this, "进去WeatherActivity", Toast.LENGTH_SHORT).show();
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdataTime = (TextView) findViewById(R.id.title_updata_time);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //String weatherString = prefs.getString("weather", null);
        String weatherString = null;
        if(weatherString != null){
            Toast.makeText(WeatherActivity.this, "weatherString != null", Toast.LENGTH_SHORT).show();
           // Weather weather = new Weather();
            //weather= Utility.handleWeatherResponse(weatherString, WeatherActivity.this);
            //weather.updata.time = "2017.3.17";
            //showWeatherInfo(weather);
        }else {
              //Toast.makeText(WeatherActivity.this, "weatherString == null", Toast.LENGTH_SHORT).show();
              String city_name_name = getIntent().getStringExtra("city_name_name");   //////////////////////////////////////////////我不是这样的，记住改
              weatherLayout.setVisibility(View.INVISIBLE);
              //Looper.prepare();
              Toast.makeText(WeatherActivity.this, city_name_name, Toast.LENGTH_SHORT).show();
              requestWeather(city_name_name);
        }
    }

    public void requestWeather(final String weatherId){
        Toast.makeText(WeatherActivity.this, "刚刚进入requestWeather", Toast.LENGTH_SHORT).show();
        String weatherUrl = "https://free-api.heweather.net/s6/weather/forecast?location="+ weatherId +"&key=1ee0e21a7ba444859bdb92af2986f50b";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException{
               final String responseText = response.body().string();
                //Toast.makeText(WeatherActivity.this, "正在解析服务器数据1", Toast.LENGTH_SHORT).show();

                final Weather weather_this = Utility.handleWeatherResponse(responseText, WeatherActivity.this);
                //String cityName = weather_this.basic.cityName;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                         weatherLayout.setVisibility(View.VISIBLE);

                        //degreeText.setText(weather_this.status);
                        //degreeText.setText(responseText);
                         //weatherInfoText.setText(responseText);
                        if("ok".equals(weather_this.status) && weather_this != null){
                            //degreeText.setText(responseText);
                            //Toast.makeText(WeatherActivity.this, "成功获取天气信息", Toast.LENGTH_SHORT).show();
                            //  错在这里  weather =null  不知道为什么！！！！！！！！！！！！！！if(weather_this != null && "ok".equals(weather_this.status))
                            //SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            //editor.putString("weather", responseText);
                            //editor.apply();
                            showWeatherInfo(weather_this);
                        }else {
                            //degreeText.setText(responseText);
                        }
                    }
                });
            }
            @Override
            public void onFailure(Call call, IOException e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(WeatherActivity.this, "获取天气信息失败222", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        );

        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException{
                final String bingPic = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e){
                e.printStackTrace();
            }
        });
    }
    private void showWeatherInfo(Weather weather){
        String cityName = weather.basic.cityName;
        String updata_time_this = weather.updata_time.loc;    //为什么把Weather.class 里的updata_time改成updata就不行了？？？？？？？？
        //String degree = "25";
        //String weatherInfo = weather.now.nore.info;

        titleCity.setText(cityName);
        titleUpdataTime.setText(updata_time_this);
        //degreeText.setText(degree);
        //weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();   //?????????????????????????????????????????????

       for(Forecast forecast : weather.forecastList ){
            View view = LayoutInflater.from(this).
                    inflate(R.layout.forecast_ite, forecastLayout, false);  //不懂--------------------------、
            TextView dataText = (TextView) view.findViewById(R.id.data_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);
            dataText.setText(forecast.my_data);    //dataText.setText(forecast.my_data);  这样做  字符串 显示不出来 ，推断为null，不知为何？？？？？
            infoText.setText(forecast.cond_txt_d);
            maxText.setText(forecast.tmp_max);
            minText.setText(forecast.tmp_min);
            forecastLayout.addView(view);
        }
        weatherLayout.setVisibility(View.VISIBLE);
    }
}
