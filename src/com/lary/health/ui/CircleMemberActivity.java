package com.lary.health.ui;

import com.lary.health.R;
import com.lary.health.ui.widget.XListView;


/**
 * @author zhaoyapeng
 * @version create time:2015-5-13下午7:47:35
 * @Email zhaoyp@witmob.com
 * @Description 美丽圈组成员
 */
public class CircleMemberActivity extends BaseFragmentActivity{
	private XListView listView;
	@Override
	protected void initData() {
		
	}

	@Override
	protected void initView() {
		setContentView(R.layout.activity_circle_member);
		listView = (XListView)findViewById(R.id.listView);
	}

	@Override
	protected void initWidgetAciotns() {
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(false);
	}

}
