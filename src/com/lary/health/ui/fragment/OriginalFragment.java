package com.lary.health.ui.fragment;

import com.lary.health.R;
import com.lary.health.ui.widget.XListView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-8上午12:22:08
 * @Email zhaoyp@witmob.com
 * @Description 原创视频Fragment
 */
public class OriginalFragment extends BaseFragment{

	private XListView originalListView;
	@Override
	protected void initData() {
		
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_original, container, false);
		originalListView = (XListView)view.findViewById(R.id.listview_riginal);
		return view;
	}

	@Override
	protected void initWidgetActions() {
		// TODO Auto-generated method stub
		
	}

}
