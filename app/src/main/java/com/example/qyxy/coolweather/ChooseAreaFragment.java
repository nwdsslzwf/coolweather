package com.example.qyxy.coolweather;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qyxy on 2019/3/14.
 */

public class ChooseAreaFragment extends Fragment {
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    private ProgressDialog progressDialog;
    private TextView titletext;
    private Button backButton;
    private ListView listView;
    private ArrayAdapter< String > adapter;
    private List< String > dataList = new ArrayList<>();
    private List< province > provinceList;
    private List< city > cityList;


    private province selectedProvince;
    private city selectedCity;
    private int currentLeverl = LEVEL_PROVINCE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View view = inflater.inflate( R.layout.choose_area, container, false );
        titletext = ( TextView ) view.findViewById( R.id.title_text );
        backButton = ( Button ) view.findViewById( R.id.back_button );
        listView = ( ListView ) view.findViewById( R.id.list_view );
        adapter = new ArrayAdapter< String >( getContext(), android.R.layout.simple_list_item_1, dataList );
        listView.setAdapter( adapter );



        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState ){
        super.onActivityCreated( savedInstanceState );
        titletext.setText( "中国" );
        backButton.setVisibility( View.GONE);

        add_City();
        add_Province();

        provinceList = DataSupport.findAll( province.class) ;
        if ( provinceList.size() > 0 ) {
            dataList.clear();
            for ( province proVince : provinceList ) {
                dataList.add( proVince.getProvinceName() );
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);   //不知道用来干嘛-----------------------------------------------------------------------------------------
        }

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id){

                if ( currentLeverl == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);  //获取点击的位置所对应的省的对象
                    titletext.setText(selectedProvince.getProvinceName());
                    dataList.clear();
                    cityList = DataSupport.where("province_Name = ?",
                            String.valueOf(selectedProvince.getProvinceName())).find(city.class);
                    //--这个很厉害---------cityList = DataSupport.findAll( city.class)

                    for (city City : cityList) {
                        dataList.add(City.getCityName());
                        backButton.setVisibility(View.VISIBLE);
                    }
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0);
                    currentLeverl = LEVEL_CITY;
                }else if( currentLeverl == LEVEL_CITY ){
                    String weatherId = cityList.get(position).getCityName();
                    Intent intent = new Intent(getActivity(), WeatherActivity.class);
                    intent.putExtra("city_name_name", weatherId);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        backButton.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v ){
                if ( currentLeverl == LEVEL_CITY ){
                    dataList.clear();
                    for ( province proVince : provinceList ) {
                        dataList.add( proVince.getProvinceName() );
                    }
                    currentLeverl = LEVEL_PROVINCE;
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0);   //不知道用来干嘛-----------------------------------------------------------------------------------------
                }
            }
        });
    }

   // public  void maketip(Context context, String tips){
    //    Toast.makeText(context, tips, Toast.LENGTH_SHORT).show();
   // }

    private void add_Province() {
        DataSupport.deleteAll(province.class);
        province zhejiang_Province = new province();
        province jiangsu_Province = new province();
        zhejiang_Province.setProvinceName("浙江省");
        jiangsu_Province.setProvinceName("江苏省");
        zhejiang_Province.save();
        jiangsu_Province.save();
        currentLeverl = LEVEL_PROVINCE;
    }

    private void add_City(){         //把市数据添加到数据库中
        DataSupport.deleteAll( city.class ); //清除数据库中之前的城市，不然进入一次app数据库又加入一次这个城市

        city huzhou_city = new city();
        huzhou_city.setCityName( "湖州" );
        huzhou_city.setProvince_Name( "浙江省" );
        huzhou_city.save();

        city hangzhou_city = new city();
        hangzhou_city.setCityName( "杭州" );
        hangzhou_city.setProvince_Name( "浙江省" );
        hangzhou_city.save();

        city nanjing_city = new city();
        nanjing_city.setCityName( "南京" );
        nanjing_city.setProvince_Name( "江苏省" );
        nanjing_city.save();

        city suzhou_city = new city();
        suzhou_city.setCityName( "苏州" );
        suzhou_city.setProvince_Name( "江苏省" );
        suzhou_city.save();
    }
}



