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

		ceshiNet = (Button) findViewById(R.id.btn_net);
		ceshiEvent = (Button) findViewById(R.id.btn_event);
		btn_video = (Button) findViewById(R.id.btn_video);
		btn_video.setVisibility(View.VISIBLE);
		btn_medir = (Button) findViewById(R.id.btn_medir);
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
				homeViewPager.setCurrentItem(TYPE_CIRCLE, false);
			}
		});

		shopBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				homeViewPager.setCurrentItem(TYPE_SHOP, false);
			}
		});

		persionBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				homeViewPager.setCurrentItem(TYPE_PERSION, false);
			}
		});

		ceshiNet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ceshiNet();
			}
		});
		ceshiEvent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EventBus.getDefault().post(new cdshiEvent());
			}
		});
		btn_video.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(HomeActivity.this,PlayVideoActivity.class);
				startActivity(in);
			}
		});
		btn_medir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String sign =MD5.getMD5("lary"+"app@qq.com"+"123");
				Log.d("sign", sign);
				Toast.makeText(HomeActivity.this, sign, 0).show();
	
			}
		});
	}

	/**
	 * 测试网络请求
	 */
	protected void ceshiNet() {
		String url = "http://119.10.27.126:8080/api/system/register?partner=meilituan&sign=7cf64073abfc6d2fd8658899ef8df676";
		VolleyPostRequest<TestBean> request = new VolleyPostRequest<TestBean>(url, TestBean.class,
				new Listener<TestBean>() {

					@Override
					public void onResponse(TestBean arg0) {
						Toast.makeText(HomeActivity.this, "网络请求成功", Toast.LENGTH_SHORT).show();
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("tag", "VolleyError" + arg0);
						Toast.makeText(HomeActivity.this, "网络请求失败了" + arg0, Toast.LENGTH_SHORT).show();
					}

				}, this) {
			
			@Override
				public Map<String, String> getHeaders()
						throws AuthFailureError {
					// TODO Auto-generated method stub
				HashMap<String, String> hashMap = new HashMap<String,String>();
			//	hashMap.put("Accept", "application/json");
				//hashMap.put("content-Type", "application/json; charset=UTF-8"); 
				hashMap.put("contentType", "application/x-www-form-urlencoded");
					return hashMap;
				}
                 @Override              
                protected Map<String, String> getParams() throws AuthFailureError {
                	   HashMap<String, String > map = new HashMap<String, String>();
                	//   map.put("sign", "7cf64073abfc6d2fd8658899ef8df676");
                	   map.put("nickname", "lary");
                	   map.put("email", "app@qq.com");
                	   map.put("password", "123");
                	// map.put("contentType", "application/x-www-form-urlencoded");
                	return map;
                }
		};
		request.setShouldCache(false);
		VolleyUtil.getQueue(HomeActivity.this).add(request);
	}

	@Override
	public void onEvent(IEvent event) {
		super.onEvent(event);
		Toast.makeText(HomeActivity.this, "测试Event成功", Toast.LENGTH_SHORT).show();
	}

}
