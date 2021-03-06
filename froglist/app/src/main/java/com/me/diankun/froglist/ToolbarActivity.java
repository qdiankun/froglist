package com.me.diankun.froglist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by diankun on 2016/1/4.
 */
public abstract class ToolbarActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    protected ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initToolbar();

        initProgressDialog();
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
    }


    protected void initToolbar() {
        if (mToolbar == null)
            return;
        //动态设置背景色
        //mToolbar.setBackgroundColor(getColorPrimary());
        mToolbar.setTitle(R.string.app_name);
        //动态设置标题颜色
        //mToolbar.setTitleTextColor(getColor(R.color.action_bar_title_color));
        mToolbar.collapseActionView();
        setSupportActionBar(mToolbar);
        //是否有返回箭头
        if (canBack()) {
            if (getSupportActionBar() != null) {
                ActionBar actionbar = getSupportActionBar();
                actionbar.setDisplayHomeAsUpEnabled(true);// 给左上角图标的左边加上一个返回的图标"《"
                // 对应ActionBar.DISPLAY_HOME_AS_UP
                actionbar.setDisplayShowHomeEnabled(true); // 使左上角图标可点击，对应id为android.R.id.home
                actionbar.setHomeButtonEnabled(true); // false 就无法点击
                actionbar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            }
        }
    }

    //点击回去箭头返回
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 能否返回
     *
     * @return boolean
     */
    public boolean canBack() {
        return false;
    }

    public abstract int getLayoutId();


    public void openActivity(Class<? extends Activity> clazz) {

        Intent i = new Intent(this, clazz);
        startActivity(i);
    }

    protected void showProgressBar(boolean show) {
        showProgressBar(show, "");
    }

    protected void showProgressBar(boolean show, String message) {
        if (show) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        } else {
            mProgressDialog.hide();
        }
    }

    protected void showProgressBar(boolean show, int message) {
        String s = getString(message);
        showProgressBar(show, s);
    }

    protected void showProgressBar(int messageId) {
        String message = getString(messageId);
        showProgressBar(true, message);
    }
}
