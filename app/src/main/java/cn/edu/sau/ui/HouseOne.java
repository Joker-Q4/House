package cn.edu.sau.ui;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import cn.edu.sau.joker.R;
import cn.edu.sau.ui.basic.BaseActivity;
import cn.edu.sau.ui.basic.BaseFragment;

public class HouseOne extends BaseActivity {

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_houseone);
    }

    @Override
    protected void initViews() {
        initMainFragment();
    }

    /**
     * 初始化内容Fragment
     *
     * @return void
     */
    public void initMainFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BaseFragment mFragment = FragmentHouseOne.newInstance();
        transaction.replace(R.id.main_act_container, mFragment, mFragment.getFragmentName());
        transaction.commit();
    }

    @Override
    protected void initEvents() {

    }
}
