package com.lary.health.ui.fragment;

import com.lary.health.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:40:59
 * @Email zhaoyp@witmob.com
 * @Description 个人中心Fragment
 */
public class PersionFragment extends BaseViewPagerFragment{

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_persion, container, false);
		return view;
	}

	@Override
	protected void initWidgetActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void refreshData() {
		// TODO Auto-generated method stub
		
	}

	public void getrecode(){
		
	}
	
}
