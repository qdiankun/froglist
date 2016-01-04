package com.me.diankun.froglist;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.me.diankun.froglist.adapter.MenuItemAdapter;
import com.me.diankun.froglist.bean.LvMenuItem;
import com.me.diankun.froglist.ui.SettingActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends ToolbarActivity {


    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.lv_left_menu)
    ListView mLVLeftMenu;
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
        mSettingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Fragment fragment = new SettingFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
//                openOrCloseDrawer();
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
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

}
