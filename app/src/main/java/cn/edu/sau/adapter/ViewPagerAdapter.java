package cn.edu.sau.adapter;


import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<View>mViewList;
    private List<String>mTitleLists;

    public ViewPagerAdapter(List<View>mViewList,List<String>mTitleLists){
        this.mViewList = mViewList;
        this.mTitleLists = mTitleLists;
    }

    @Override
    public int getCount() {
        return mViewList.size();      //页卡数
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;       //官方推荐
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViewList.get(position));           //添加页卡
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));         //删除页卡
    }

    public CharSequence getPageTitle(int position){
        return mTitleLists.get(position);             //页卡标题
    }
}