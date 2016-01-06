/*
 * Copyright (c) 2014, 青岛司通科技有限公司 All rights reserved.
 * File Name：ShowToast.java
 * Version：V1.0
 * Author：zhaokaiqiang
 * Date：2014-8-7
 */
package com.me.diankun.froglist.utils;

import android.widget.Toast;

import com.me.diankun.froglist.FrogApplication;

/**
 * @author zhaokaiqiang
 * @ClassName: com.drd.piaojubao.utils.ShowToast
 * @Description: 显示Toast的工具类
 * @date 2014-8-7 上午11:21:48
 */
public class ShowToast {

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Description: 显示短时间Toast
	 */
	public static void Short(CharSequence sequence) {
		Toast.makeText(FrogApplication.getContext(), sequence, Toast.LENGTH_SHORT).show();
	}


	/**
	 * 显示短时间Toast
	 * @param resId
	 */
	public static void Short(int resId) {
		Toast.makeText(FrogApplication.getContext(), FrogApplication.getContext().getResources().getString(resId), Toast.LENGTH_SHORT).show();
	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Description: 显示长时间Toast
	 */
	public static void Long(CharSequence sequence) {
		Toast.makeText(FrogApplication.getContext(), sequence, Toast.LENGTH_SHORT).show();
	}

}
