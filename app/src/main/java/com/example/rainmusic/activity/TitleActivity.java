package com.example.rainmusic.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rainmusic.R;

public class TitleActivity {

    //头部搜索框
    public RelativeLayout title_search;
    //听歌识曲
    public ImageView title_earing;
    //fragment名称
    public TextView title_name;

    public TitleActivity(View v){

        title_search = (RelativeLayout)v.findViewById(R.id.main_title_search);
        title_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: 点击搜索框");
            }
        });

        title_earing = (ImageView)v.findViewById(R.id.main_title_ear);
        title_earing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: 点击听歌识曲");
            }
        });

        title_name = (TextView)v.findViewById(R.id.main_title_name);

    }
}
