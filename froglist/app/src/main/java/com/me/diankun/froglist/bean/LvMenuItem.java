package com.me.diankun.froglist.bean;

import android.text.TextUtils;

/**
 * Created by Administrator on 2015-8-7.
 */
public class LvMenuItem {

    private int type;
    private String name;
    private int icon;

    //没有Icon
    private static final int NO_ICON = 0;

    public static final int TYPE_NORMAL = 0;//正常，有Icon和文字
    public static final int TYPE_NOICON = 1;//只有文字
    public static final int TYPE_SEPARATOR = 2;//分割线


    //分割线构造函数
    public LvMenuItem() {
        this(null);
    }

    //只有name的构造函数
    public LvMenuItem(String name) {
        this(NO_ICON,name);
    }

    //有name和Icon的构造函数
    public LvMenuItem(int icon,String name) {

        this.name = name;
        this.icon = icon;

        //Logger.e((icon == NO_ICON && TextUtils.isEmpty(name))+"");

        if (icon == NO_ICON && TextUtils.isEmpty(name)) {
            this.type = TYPE_SEPARATOR;
        } else if (icon == NO_ICON && !TextUtils.isEmpty(name)) {
            this.type = TYPE_NOICON;
        } else {
            this.type = TYPE_NORMAL;
        }

        //若当前类型是TYPE_SEPARATOR，并且name是空的情况下抛出异常
        if (this.type != TYPE_SEPARATOR && TextUtils.isEmpty(name)) {
            throw new IllegalArgumentException("you need set a name for a non-SEPARATOR item");
        }
    }


    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }
}
