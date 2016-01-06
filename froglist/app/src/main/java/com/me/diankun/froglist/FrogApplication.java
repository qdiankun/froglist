package com.me.diankun.froglist;

import android.app.Application;
import android.content.Context;

/**
 * Created by diankun on 2016/1/6.
 */
public class FrogApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


    public static Context getContext() {
        return mContext;
    }
}
