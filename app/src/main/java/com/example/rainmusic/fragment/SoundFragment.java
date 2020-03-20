package com.example.rainmusic.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.rainmusic.MainActivity;
import com.example.rainmusic.R;

//这是音动模块
public class SoundFragment extends Fragment implements View.OnClickListener {

    private Button soundZoom;
    private MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sound,container, false);
        init(view);
        return  view;
    }

    private void init(View view){
        mainActivity = (MainActivity)getActivity();
        soundZoom = (Button)view.findViewById(R.id.sound_zoom);
        soundZoom.setOnClickListener(this);
    }

    //点击方法
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sound_zoom:
                mainActivity.showLayout();
                break;
        }
    }
}
