package com.lary.health.ui;

import java.util.HashMap;
import java.util.Map;

import netlib.net.volley.VolleyPostRequest;
import netlib.net.volley.VolleyUtil;

import com.android.volley.Response.ErrorListener;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;

import netlib.model.BaseModel;
import netlib.model.TestBean;

import com.android.volley.Response.Listener;
import com.lary.health.R;
import com.lary.health.MD5Util.MD5;
import com.lary.health.service.event.IEvent;
import com.lary.health.service.event.cdshiEvent;
import com.lary.health.ui.adaper.HomeAdapter;
import com.lary.health.ui.widget.HomeViewPager;

import de.greenrobot.event.EventBus;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class HomeActivity extends BaseFragmentActivity {
	private HomeViewPager homeViewPager;
	private HomeAdapter adapter;

	private RadioButton homeBtn, circleBtn, shopBtn, persionBtn;
	
	private final static int TYPE_HOME = 0;
	private final static int TYPE_CIRCLE = 1;
	private final static int TYPE_SHOP = 2;
	private final static int TYPE_PERSION = 3;

	private Button ceshiNet, ceshiEvent,btn_video,btn_medir;
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
				homeViewPager.setCurrentItem(TYPE_HOME, false);
			}
		});

		circleBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				homeViewPager.setCurrentItem(TYPE_SHOP, false);
			}
		});

		shopBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				homeViewPager.setCurrentItem(TYPE_CIRCLE, false);
			}
		});

		persionBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				homeViewPager.setCurrentItem(TYPE_PERSION, false);
			}
		});
		homeBtn.setChecked(true);
	}

	
}
