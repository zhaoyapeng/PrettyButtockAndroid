package com.lary.health.ui;

import android.support.v4.view.ViewPager;

import com.lary.health.R;
import com.lary.health.ui.adaper.GymnasticsAdapter;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-8上午12:03:16
 * @Email zhaoyp@witmob.com
 * @Description 垫上美操
 */
public class GymnasticsBeautifulActivity extends BaseFragmentActivity{

	private ViewPager viewPager;
	private GymnasticsAdapter adapter;
	@Override
	protected void initData() {
		adapter = new GymnasticsAdapter(getSupportFragmentManager());
	}

	@Override
	protected void initView() {
		setContentView(R.layout.activity_gymnastics_beau);
		viewPager = (ViewPager)findViewById(R.id.viewpager);
	}

	@Override
	protected void initWidgetAciotns() {
		viewPager.setAdapter(adapter);
	}

}
