package com.example.rainmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rainmusic.activity.TitleActivity;
import com.example.rainmusic.fragment.CommFragment;
import com.example.rainmusic.fragment.MineFragment;
import com.example.rainmusic.fragment.RecomFragment;
import com.example.rainmusic.fragment.SoundFragment;
import com.example.rainmusic.ui.RoundImageView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Application
    RainApplication rainApp;
    //头部
    TitleActivity titleActivity;
    //下方RadioGroup
    RadioGroup mainRadioG;
    //开启侧边栏按钮
    ImageView menuStart;
    //侧边栏
    DrawerLayout mainDrawer;
    public LinearLayout mainTitle;
    public LinearLayout mainFood;
    //侧边栏内容
    NavigationView mainNavigation;
    RoundImageView mainMenuImg;
    TextView mainMenuText;
    Button mainMenuNight;
    Button mainMenuSet;
    Button mainMenuOut;
    //
    private SoundFragment soundF;
    private RecomFragment recomF;
    private CommFragment commF;
    private MineFragment mineF;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
                window.setAttributes(attributes);
            }
        }
        //状态栏中的文字颜色和图标颜色，需要android系统6.0以上，而且目前只有一种可以修改(一种是深色，一种是浅色即白色)
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_main);

        //获取Application
        rainApp = (RainApplication)getApplication();

        initMenu();
        init();
    }

    //初始化侧边栏
    private void initMenu(){
        mainDrawer = (DrawerLayout)findViewById(R.id.main_drawer);
        mainNavigation = (NavigationView)findViewById(R.id.main_navigation);
        //获取侧边栏的头部View
        View mainMenuHead = mainNavigation.getHeaderView(0);
        //获取控件
        mainMenuImg = mainMenuHead.findViewById(R.id.main_menu_title_img);
        mainMenuText = mainMenuHead.findViewById(R.id.main_menu_title_text);

        //设置item的条目颜色
        ColorStateList csl = (ColorStateList) getResources().getColorStateList(R.color.black);
        mainNavigation.setItemTextColor(csl);
        //去掉默认颜色显示原来颜色  设置为null显示本来图片的颜色
        mainNavigation.setItemIconTintList(null);
        //侧边栏的点击事件
        mainNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main_menu1:
                        //这是 开通会员
                        Toast.makeText(MainActivity.this,"敬请期待！",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_menu2:
                        //这是 上传歌曲
                        Log.d("TAG", "onNavigationItemSelected: 2");
                        break;
                    case R.id.main_menu3:
                        //这是 定时停止播放
                        Log.d("TAG", "onNavigationItemSelected: 3");
                        break;
                    case R.id.main_menu4:
                        //这是 个性装扮
                        Log.d("TAG", "onNavigationItemSelected: 4");
                        break;
                    case R.id.main_menu5:
                        //这是 关于
                        Log.d("TAG", "onNavigationItemSelected: 5");
                        mainDrawer.closeDrawers();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        //侧边栏下方的按钮
        mainMenuNight = (Button)findViewById(R.id.main_menu_night);
        mainMenuSet = (Button)findViewById(R.id.main_menu_set);
        mainMenuOut = (Button)findViewById(R.id.main_menu_out);
        mainMenuNight.setOnClickListener(this);
        mainMenuSet.setOnClickListener(this);
        mainMenuOut.setOnClickListener(this);

    }

    //初始化控件
    private void init(){
        //初始化头部
        titleActivity = new TitleActivity(getWindow().getDecorView());
        mainTitle = (LinearLayout)findViewById(R.id.main_title_layout);
        mainFood = (LinearLayout)findViewById(R.id.main_foot_layout);
        menuStart = (ImageView)findViewById(R.id.main_menu_start);
        menuStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainDrawer.openDrawer(Gravity.LEFT);
            }
        });

        mainRadioG = (RadioGroup)findViewById(R.id.main_radioG);
        mainRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
              switch (checkedId){
                  case R.id.main_one:
                      titleActivity.title_name.setText(R.string.main_name1);
                      setFragment(0);
                      showLayout();
                      break;
                  case R.id.main_two:
                      titleActivity.title_name.setText(R.string.main_name2);
                      setFragment(1);
                      break;
                  case R.id.main_three:
                      titleActivity.title_name.setText(R.string.main_name3);
                      setFragment(2);
                      break;
                  case R.id.main_four:
                      titleActivity.title_name.setText(R.string.main_name4);
                      setFragment(3);
                      break;
              }
            }
        });
        //设置为 推荐模块
        fm = getSupportFragmentManager();
        setFragment(1);
        RadioButton mainRadioB = (RadioButton)findViewById(R.id.main_two);
        mainRadioB.setChecked(true);
    }

    private void setFragment(int index){
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 0:
                if(soundF==null){
                    soundF = new SoundFragment();
                    ft.add(R.id.main_fragment, soundF);
                }else{
                    ft.show(soundF);
                }
                break;
            case 1:
                if(recomF==null){
                    recomF = new RecomFragment();
                    ft.add(R.id.main_fragment, recomF);
                }else{
                    ft.show(recomF);
                }
                break;
            case 2:
                if(commF==null){
                    commF = new CommFragment();
                    ft.add(R.id.main_fragment, commF);
                }else{
                    ft.show(commF);
                }
                break;
            case 3:
                if(mineF==null){
                    mineF = new MineFragment();
                    ft.add(R.id.main_fragment, mineF);
                }else{
                    ft.show(mineF);
                }
                break;
        }
        ft.commit();
    }

    //用于隐藏fragment
    private void hideFragment(FragmentTransaction ft){
        if(soundF!=null){
            ft.hide(soundF);
        }
        if(recomF!=null){
            ft.hide(recomF);
        }
        if(commF!=null){
            ft.hide(commF);
        }
        if(mineF!=null){
            ft.hide(mineF);
        }
    }

    //用于隐藏头部和尾部
    public void showLayout(){
        if (mainTitle.getVisibility() != View.VISIBLE){
            mainTitle.setVisibility(View.VISIBLE);
            mainFood.setVisibility(View.VISIBLE);
        }else{
            mainTitle.setVisibility(View.GONE);
            mainFood.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_menu_night:
                Toast.makeText(this,"main_menu_night",Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_menu_set:
                Toast.makeText(this,"main_menu_set",Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_menu_out:
                Toast.makeText(this,"main_menu_out",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
