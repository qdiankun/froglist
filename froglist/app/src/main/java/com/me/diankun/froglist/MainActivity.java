package com.me.diankun.froglist;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.me.diankun.froglist.adapter.MenuItemAdapter;
import com.me.diankun.froglist.bean.LvMenuItem;
import com.me.diankun.froglist.bean.MyUser;
import com.me.diankun.froglist.ui.LoginActivity;
import com.me.diankun.froglist.ui.SettingActivity;
import com.me.diankun.froglist.ui.fragment.TickListFragment;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends ToolbarActivity {


    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.lv_left_menu)
    ListView mLVLeftMenu;

    private LinearLayout ll_login_register;
    private TextView tv_email;

    //设置
    ImageView mSettingImage;

    private List<LvMenuItem> mDatas;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
        setHeaderView();
        initDrawableToggle();

        // 注册对象
        EventBus.getDefault().register(this);
    }

    private void initDatas() {
        if (mDatas == null) {
            mDatas = new ArrayList<LvMenuItem>(Arrays.asList(
                    new LvMenuItem("三只青蛙"),
                    new LvMenuItem(R.drawable.inbox_normal, "收集箱"),
                    new LvMenuItem(),
                    new LvMenuItem("普通清单"),
                    new LvMenuItem(R.drawable.list_index_icon_white_light, "Sub Item 2")));
        }
    }

    private void setHeaderView() {
        View view = LayoutInflater.from(this).inflate(R.layout.header_just_username, mLVLeftMenu, false);
        mLVLeftMenu.addHeaderView(view);
        mLVLeftMenu.setAdapter(new MenuItemAdapter(mDatas, this));
        mSettingImage = (ImageView) view.findViewById(R.id.iv_setting);
        ll_login_register = (LinearLayout) view.findViewById(R.id.ll_login_register);
        tv_email = (TextView) view.findViewById(R.id.tv_email);
        mSettingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        mLVLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("TAG", " i = " + i);
                switch (i) {
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, TickListFragment.newInstance(0)).commit();
                }
                openOrCloseDrawer();
            }
        });
        ll_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(LoginActivity.class);
            }
        });
    }

    private void initDrawableToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerOpened(drawerView);
            }
        };
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        //mDrawerLayout.setScrimColor(getColor(R.color.drawer_scrim_color));
    }

    //ToolBar 左上角添加三个横线
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
        if (mToolbar != null) {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openOrCloseDrawer();
                }
            });
        }
    }

    private void openOrCloseDrawer() {
        if (mDrawerLayout.isDrawerOpen(mLVLeftMenu)) {
            mDrawerLayout.closeDrawer(mLVLeftMenu);
        } else {
            mDrawerLayout.openDrawer(mLVLeftMenu);
        }
    }

    @Subscriber(tag = "login_success")
    private void updateMyUser(MyUser myUser) {
        ll_login_register.setBackgroundColor(Color.TRANSPARENT);
        tv_email.setText(myUser.getEmail());
    }


    @Override
    protected void onDestroy() {
        // 注销
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
