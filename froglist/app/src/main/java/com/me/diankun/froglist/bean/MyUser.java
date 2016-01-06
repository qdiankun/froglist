package com.me.diankun.froglist.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by diankun on 2016/1/6.
 */
public class MyUser extends BmobUser {

    //别名
    private String nickname;


    @Override
    public String toString() {
        return super.toString() + " \t nickname = "+nickname;
    }
}
