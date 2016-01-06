package com.me.diankun.froglist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.me.diankun.froglist.MainActivity;
import com.me.diankun.froglist.R;
import com.me.diankun.froglist.ToolbarActivity;
import com.me.diankun.froglist.bean.MyUser;
import com.me.diankun.froglist.utils.ConfigConstants;
import com.me.diankun.froglist.utils.NetWorkUtil;
import com.me.diankun.froglist.utils.ShowToast;
import com.me.diankun.froglist.view.LoginAutoCompleteEdit;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends ToolbarActivity {

    @Bind(R.id.edit_name)
    LoginAutoCompleteEdit edit_name;
    @Bind(R.id.edit_email)
    LoginAutoCompleteEdit edit_email;
    @Bind(R.id.edit_paswd)
    EditText edit_paswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolbar.setTitle("注册");

        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, ConfigConstants.applicationId);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public boolean canBack() {
        return true;
    }


    @OnClick(R.id.btn_register)
    void register() {
        String name = edit_name.getText().toString();
        String email = edit_email.getText().toString();
        String paswd = edit_paswd.getText().toString();

        if (TextUtils.isEmpty(name)) {
            ShowToast.Short(R.string.toast_error_name_null);
            return;
        }
        if (TextUtils.isEmpty(email)) {
            ShowToast.Short(R.string.toast_error_email_null);
            return;
        }
        if (TextUtils.isEmpty(paswd)) {
            ShowToast.Short(R.string.toast_error_password_null);
            return;
        }

        boolean netWorkConnected = NetWorkUtil.isNetWorkConnected(this);
        if (!netWorkConnected) {
            ShowToast.Short(R.string.network_tips);
            return;
        }

        showProgressBar(true, "正在注册");
        MyUser user = new MyUser();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(paswd);
        user.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                showProgressBar(false);
                ShowToast.Short("注册成功");
                //发广播通知登陆页面退出
                sendBroadcast(new Intent(ConfigConstants.ACTION_REGISTER_SUCCESS_FINISH) );
                // 启动主页
                openActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                showProgressBar(false);
                ShowToast.Short("注册失败:" + s);
                KLog.e(s);
            }
        });
    }
}
