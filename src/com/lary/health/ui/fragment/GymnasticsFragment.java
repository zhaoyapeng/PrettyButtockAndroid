package com.lary.health.ui.fragment;

import com.lary.health.R;
import com.lary.health.ui.widget.XListView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-8上午12:25:11
 * @Email zhaoyp@witmob.com
 * @Description 垫上体操fragment
 */
public class GymnasticsFragment extends BaseFragment implements XListView.IXListViewListener{
	private XListView gymnasticsList;

	@Override
	protected void initData() {
		
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_gymnastics, container, false);
		gymnasticsList = (XListView)view.findViewById(R.id.listview_gymnastuics);
		return view;
	}

	@Override
	protected void initWidgetActions() {
		
	}

	@Override
	public void onRefresh() {
		// TODO 刷新回调
		
	}

	@Override
	public void onLoadMore() {
		// TODO 更多回调
		
	}

}
