package com.lary.health.ui.fragment;

import android.os.Bundle;
import android.view.View;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4下午12:44:05
 * @Email zhaoyp@witmob.com
 * @Description 首页viewPager fragment 当前fragment显示时，加载数据
 */
public abstract class BaseViewPagerFragment extends BaseFragment{
	 protected boolean isInit; // 是否可以开始加载数据

	    @Override
	    public void setUserVisibleHint(boolean isVisibleToUser) {
	        super.setUserVisibleHint(isVisibleToUser);
	        // 每次切换fragment时调用的方法
	        if (isVisibleToUser) {
	            showData();
	        } else {
	        }
	    }

	    @Override
	    public void onViewCreated(View view, Bundle savedInstanceState) {
	        super.onViewCreated(view, savedInstanceState);
	        isInit = true;
	    }

	    @Override
	    public void onResume() {
	        super.onResume();
	        // 判断当前fragment是否显示
	        if (getUserVisibleHint()) {
	            showData();
	        }
	    }

	    /**
	     * 初始化数据
	     */
	    protected void showData() {
	        if (isInit) {
	            isInit = false;// 加载数据完成
	            // 加载各种刷新数据
	            refreshData();
	        }
	    }

	    protected abstract void refreshData();
}
