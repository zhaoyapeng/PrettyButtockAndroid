package com.lary.health.ui.fragment;

import com.lary.health.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:31:12
 * @Email zhaoyp@witmob.com
 * @Description 首页fragment
 */
public class HomeFragment extends BaseViewPagerFragment{

	@Override
	protected void initData() {
		
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container,false);
		return view;
	}

	@Override
	protected void initWidgetActions() {
		
	}

	@Override
	protected void refreshData() {
		
	}

}
