package com.me.diankun.froglist.ui;

import com.me.diankun.froglist.R;
import com.me.diankun.froglist.ToolbarActivity;

/**
 * Created by diankun on 2016/1/4.
 */
public class SettingActivity extends ToolbarActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public boolean canBack() {
        return true;
    }
}
