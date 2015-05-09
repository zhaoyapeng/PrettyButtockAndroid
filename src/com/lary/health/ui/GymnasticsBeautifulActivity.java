package com.lary.health.ui;

import java.util.HashMap;
import java.util.Map;

import netlib.model.TestBean;
import netlib.net.volley.VolleyGetRequest;
import netlib.net.volley.VolleyUtil;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.lary.health.R;
import com.lary.health.MD5Util.MD5;
import com.lary.health.ui.adaper.GymnasticsAdapter;
import com.lary.health.ui.widget.HomeViewPager;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-8上午12:03:16
 * @Email zhaoyp@witmob.com
 * @Description 垫上美操
 */
public class GymnasticsBeautifulActivity extends BaseFragmentActivity {

	private HomeViewPager viewPager;
	private GymnasticsAdapter adapter;
	private RadioButton originalBtn, gymnasticsBtn;

	@Override
	protected void initData() {
		adapter = new GymnasticsAdapter(getSupportFragmentManager());
	}

	@Override
	protected void initView() {
		setContentView(R.layout.activity_gymnastics_beau);
		viewPager = (HomeViewPager) findViewById(R.id.viewpager);
		originalBtn = (RadioButton) findViewById(R.id.btn_original);
		gymnasticsBtn = (RadioButton) findViewById(R.id.btn_gymnastics);
	}

	@Override
	protected void initWidgetAciotns() {
		viewPager.setNotTouchScoll(true);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(adapter);
		gymnasticsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				gymnasticsBtn.setChecked(true);
				originalBtn.setChecked(false);
				viewPager.setCurrentItem(0, false);
				getHomeInfoNet();
			}
		});

		originalBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				gymnasticsBtn.setChecked(false);
				originalBtn.setChecked(true);
				viewPager.setCurrentItem(1, false);
			}
		});
	}
	
	
	private void getHomeInfoNet(){
		String url = getString(R.string.base_url)+"api/system/GetVideo1?&partner=meilitun&sign=67b195dbd8ae9717df622af85b60f390"+"&pageIndex=1&pageSize=5";
		VolleyGetRequest<TestBean> request = new VolleyGetRequest<TestBean>(url, TestBean.class,
				new Listener<TestBean>() {

					@Override
					public void onResponse(TestBean arg0) {
						Toast.makeText(GymnasticsBeautifulActivity.this, "网络请求成功", Toast.LENGTH_SHORT).show();
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("tag", "VolleyError" + arg0);
						Toast.makeText(GymnasticsBeautifulActivity.this, "网络请求失败了" + arg0, Toast.LENGTH_SHORT).show();
					}

				}, GymnasticsBeautifulActivity.this) {
			
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
		};
		request.setShouldCache(false);
		VolleyUtil.getQueue(GymnasticsBeautifulActivity.this).add(request);
	}
	
	private String getMD5(String mdStr){
		String sign =MD5.getMD5(mdStr);
		return sign;
	}

}
