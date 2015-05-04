package com.lary.health.ui;

import com.lary.health.R;
import com.lary.health.R.layout;
import com.lary.health.ui.adaper.HomeAdapter;
import com.lary.health.ui.widget.HomeViewPager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class HomeActivity extends BaseFragmentActivity {
	private HomeViewPager homeViewPager;
	private HomeAdapter adapter;

	private RadioButton homeBtn, circleBtn, shopBtn, persionBtn;

	private final static int TYPE_HOME = 0;
	private final static int TYPE_CIRCLE = 1;
	private final static int TYPE_SHOP = 2;
	private final static int TYPE_PERSION = 3;

	@Override
	protected void initData() {
		adapter = new HomeAdapter(getSupportFragmentManager());
	}

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		homeViewPager = (HomeViewPager) findViewById(R.id.view_pager);
		homeBtn = (RadioButton) findViewById(R.id.btn_home);
		circleBtn = (RadioButton) findViewById(R.id.btn_circle);
		shopBtn = (RadioButton) findViewById(R.id.btn_shop);
		persionBtn = (RadioButton) findViewById(R.id.btn_persion);
	}

	@Override
	protected void initWidgetAciotns() {
		homeViewPager.setAdapter(adapter);
		homeViewPager.setNotTouchScoll(true);
		homeViewPager.setOffscreenPageLimit(4);

		homeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				homeViewPager.setCurrentItem(TYPE_HOME,false);
			}
		});

		circleBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				homeViewPager.setCurrentItem(TYPE_CIRCLE,false);
			}
		});

		shopBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				homeViewPager.setCurrentItem(TYPE_SHOP,false);
			}
		});

		persionBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				homeViewPager.setCurrentItem(TYPE_PERSION,false);
			}
		});
	}

}
