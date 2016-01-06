package com.me.diankun.froglist.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;

import com.me.diankun.froglist.R;
import com.me.diankun.froglist.ToolbarActivity;
import com.me.diankun.froglist.bean.MyUser;
import com.me.diankun.froglist.utils.ConfigConstants;
import com.me.diankun.froglist.utils.ShowToast;
import com.me.diankun.froglist.view.LoginAutoCompleteEdit;
import com.me.diankun.froglist.view.LoginEditText;
import com.socks.library.KLog;

import org.simple.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by diankun on 2016/1/6.
 */
public class LoginActivity extends ToolbarActivity {

    @Bind(R.id.editName)
    LoginAutoCompleteEdit editName;
    @Bind(R.id.editPassword)
    LoginEditText editPassword;

    private MyBroadCaseReceiver receiver = new MyBroadCaseReceiver();

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setTitle("登录");

        //注册退出广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConfigConstants.ACTION_REGISTER_SUCCESS_FINISH);
        registerReceiver(receiver, filter);
    }

    @OnClick(R.id.loginButton)
    void login() {
        String email = editName.getText().toString();
        String passwd = editPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            ShowToast.Short(R.string.toast_error_email_null);
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ShowToast.Short(R.string.toast_error_password_null);
            return;
        }
        showProgressBar(true, "正在登录");
        BmobUser.loginByAccount(this, email, passwd, new LogInListener<MyUser>() {

            @Override
            public void done(MyUser user, BmobException e) {
                showProgressBar(false);
                if (user != null) {
                    ShowToast.Short("登录成功");
                    EventBus.getDefault().post(user,"login_success");
                    finish();
                }
            }
        });
    }

    @OnClick(R.id.register)
    void register() {
        openActivity(RegisterActivity.class);
    }

    @Override
    public boolean canBack() {
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    class MyBroadCaseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && ConfigConstants.ACTION_REGISTER_SUCCESS_FINISH.equals(intent.getAction())) {
                finish();
            }
        }
    }
}
