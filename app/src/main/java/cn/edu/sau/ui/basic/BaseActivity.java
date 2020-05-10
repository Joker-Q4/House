package cn.edu.sau.ui.basic;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewConfiguration;


import java.lang.reflect.Field;

import butterknife.ButterKnife;
import cn.edu.sau.JokerApplication;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        JokerApplication.getInstance().mActivityStack.addActivity(this);
        setOverflowShowingAlways();
        super.onCreate(savedInstanceState);

        initContentView();
        ButterKnife.bind(this);
        init();
        initViews();
        initEvents();
    }

    /**
     * 设置总是显示溢出菜单
     */
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void finish() {
        super.finish();
        JokerApplication.getInstance().mActivityStack.removeActivity(this);
    }

    /**
     * 初始化布局
     */
    protected abstract void initContentView();

    /**
     * 初始化
     */
    protected void init() {}

    /**
     * 初始化View
     */
    protected abstract void initViews();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();
}
