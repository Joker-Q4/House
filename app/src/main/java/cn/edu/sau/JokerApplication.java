package cn.edu.sau;

import android.app.Application;
import android.content.Context;

import cn.edu.sau.ui.basic.ActivityStack;


public class JokerApplication extends Application {

    protected static JokerApplication jokerApplication = null;
    /** 上下文 */
    protected Context mContext = null;
    /** Activity 栈 */
    public ActivityStack mActivityStack = null;

    @Override
    public void onCreate() {
        super.onCreate();
        // 由于Application类本身已经单例，所以直接按以下处理即可
        jokerApplication = this;
        mContext = getApplicationContext();     // 获取上下文
        mActivityStack = new ActivityStack();   // 初始化Activity 栈

        initConfiguration();
    }

    /**
     * 获取当前类实例对象
     * @return
     */
    public static JokerApplication getInstance(){
        return jokerApplication;
    }

    /**
     * @description 初始化配置文件
     */
    private void initConfiguration() {

    }
}
