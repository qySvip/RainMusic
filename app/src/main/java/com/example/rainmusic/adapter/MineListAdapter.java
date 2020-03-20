package com.example.rainmusic.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rainmusic.R;
import com.example.rainmusic.bean.mineItemBean;

import java.util.List;

public class MineListAdapter extends BaseAdapter{

    private List<mineItemBean> mList;//数据源
    private LayoutInflater mInflater;//布局装载器对象
    private mineListClickListener mineListListener;//按钮点击接口

    public MineListAdapter(Context context,List<mineItemBean> list,mineListClickListener mineListClickListener){
        mList = list;
        mInflater = LayoutInflater.from(context);
        mineListListener = mineListClickListener;
    }

    //显示的数据数量
    @Override
    public int getCount() {
        return mList.size();
    }

    //指定的索引对应的数据项
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    //指定的索引对应的数据项ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    //返回每一项的显示内容
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //如果view没有被实例化过，缓存池中没有对应的缓存
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_mine,null);

            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.mine_list_img);
            viewHolder.title = (TextView)convertView.findViewById(R.id.mine_list_title);
            viewHolder.content = (TextView)convertView.findViewById(R.id.mine_list_content);
            viewHolder.button = (ImageView)convertView.findViewById(R.id.mine_list_button);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        mineItemBean bean = mList.get(position);
        //设置数据
        if (bean.bool){
            viewHolder.imageView.setImageResource(bean.mineListImgId);
            viewHolder.title.setText(bean.mineListTitle);
            viewHolder.title.setGravity(Gravity.BOTTOM);
            viewHolder.content.setText(bean.mineListContent);
            viewHolder.content.setVisibility(View.VISIBLE);
        }else{
            viewHolder.imageView.setImageResource(bean.mineListImgId);
            viewHolder.content.setVisibility(View.GONE);
            viewHolder.title.setGravity(Gravity.CENTER);
            viewHolder.title.setText(bean.mineListTitle);

        }
        //按钮点击事件
        viewHolder.button.setOnClickListener(mineListListener);
        viewHolder.button.setTag(position);
        return convertView;
    }

    // ViewHolder用于缓存控件，三个属性分别对应item布局文件的三个控件
    class ViewHolder{
        public ImageView imageView;
        public TextView title;
        public TextView content;
        public ImageView button;
    }

    public static abstract class mineListClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            mineListClick((Integer) v.getTag(),v);
        }
        public abstract void mineListClick(int position, View v);
    }
}

