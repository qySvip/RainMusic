package com.example.rainmusic.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.rainmusic.R;
import com.example.rainmusic.adapter.MineListAdapter;
import com.example.rainmusic.bean.mineItemBean;
import com.gcssloop.widget.RCRelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MineFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private List<mineItemBean> itemList;
    private ListView mineListView;
    private RCRelativeLayout mineViewOne;
    private RCRelativeLayout mineViewTwo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);

        init(view);

        return view;
    }

    private void init(View view){
        mineListView = (ListView)view.findViewById(R.id.mine_list);
        mineViewOne = (RCRelativeLayout)view.findViewById(R.id.mine_view_one);
        mineViewTwo = (RCRelativeLayout)view.findViewById(R.id.mine_view_two);
        mineViewOne.setOnClickListener(this);
        mineViewTwo.setOnClickListener(this);
        itemList = new ArrayList<>();
        for (int i = 0;i<20;i++){
            itemList.add(new mineItemBean(R.mipmap.img_head,"歌单"+i,i+"首",true));
        }
        itemList.add(new mineItemBean(R.mipmap.img_head,"添加歌单","",false));
        //设置ListView的适配器
        mineListView.setAdapter(new MineListAdapter(getActivity(),itemList,mListener));
        mineListView.setOnItemClickListener(this);
    }

    //ListItem的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();
    }

    //实现类，响应按钮点击事件
    private MineListAdapter.mineListClickListener mListener = new MineListAdapter.mineListClickListener() {
        @Override
        public void mineListClick(int position, View v) {
            Log.d("TAG", "mineListClick: 按钮点击事件");
            Toast.makeText(getActivity(),"按钮" +position, Toast.LENGTH_SHORT).show();
        }
     };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_view_one:
                Toast.makeText(getActivity(),"这是喜爱音乐",Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_view_two:
                Toast.makeText(getActivity(),"这是本地音乐",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
