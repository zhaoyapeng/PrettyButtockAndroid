package com.lary.health.ui;

import java.util.HashMap;
import java.util.Map;

import netlib.net.volley.VolleyGetRequest;
import netlib.net.volley.VolleyUtil;
import netlib.util.StringUtil;
import netlib.util.TextUtil;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.lary.health.R;
import com.lary.health.MD5Util.MD5;
import com.lary.health.service.model.HomeModelNet;
import com.lary.health.ui.BaseFragmentActivity;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends BaseFragmentActivity implements OnClickListener{

	private ImageView backBt;
	private ImageView loginBt;
	private EditText userNameEt;
	private EditText passwordEt;
	private TextView regester_tv;
	
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_login);
		backBt = (ImageView) findViewById(R.id.iv_back);
		loginBt = (ImageView) findViewById(R.id.login_bt);
		userNameEt = (EditText) findViewById(R.id.user_login_et);
		passwordEt = (EditText) findViewById(R.id.user_password_et);
		regester_tv = (TextView) findViewById(R.id.regester_tv);
	}

	@Override
	protected void initWidgetAciotns() {
		// TODO Auto-generated method stub
		backBt.setOnClickListener(this);
		loginBt.setOnClickListener(this);
		regester_tv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_back:
			this.finish();
			break;
		case R.id.login_bt:
			login();
			break;
		case R.id.regester_tv:
			break;
		default:
			break;
		}
	}

	public void login(){
		String userName = userNameEt.getText().toString().trim();
		String passWord = passwordEt.getText().toString().trim();
		String type = "0";
		if(TextUtil.isEmpty(userName) || TextUtil.isEmpty(passWord))
		{
			Toast.makeText(LoginActivity.this, "用户名/密码不能为空", 1).show();
			return;
		}
		if(TextUtil.isEmail(userName)){
			type = "1";
		}
		String url = getString(R.string.base_url)+"api/system/Login?partner=meilitun&sign="+MD5.getMD5("name="+userName+"&partner=meilitun&password="+passWord+"&type="+type+"lary")+"&name="+userName+"&password="+passWord+"&type="+type;
		VolleyGetRequest<HomeModelNet> request = new VolleyGetRequest<HomeModelNet>(url, HomeModelNet.class,
				new Listener<HomeModelNet>() {
					@Override
					public void onResponse(HomeModelNet arg0) {
						Toast.makeText(LoginActivity.this, "网络请求成功", Toast.LENGTH_SHORT).show();
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("tag", "VolleyError" + arg0);
						Toast.makeText(LoginActivity.this, "网络请求失败了" + arg0, Toast.LENGTH_SHORT).show();
					}

				}, LoginActivity.this) {
			
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
		VolleyUtil.getQueue(LoginActivity.this).add(request);
	}
		
	
	
}
