package com.lary.health.ui;

import java.util.HashMap;
import java.util.Map;

import netlib.model.BaseModel;
import netlib.net.volley.VolleyPostRequest;
import netlib.net.volley.VolleyUtil;
import netlib.util.TextUtil;
import android.content.Intent;
import android.media.AudioRecord.OnRecordPositionUpdateListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.lary.health.R;
import com.lary.health.MD5Util.MD5;

public class RegesterActivity extends BaseFragmentActivity implements
		OnClickListener {

	private Button registerBt;
	private ImageView backBt;
	private EditText nickname_ed, email_ed, pwd_ed, repwd_ed, qq_ed, phones_ed,
			address_ed;

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_register);
		registerBt = (Button) findViewById(R.id.register_bt);
		backBt = (ImageView) findViewById(R.id.iv_back);
		nickname_ed = (EditText) findViewById(R.id.nickname_ed);
		email_ed = (EditText) findViewById(R.id.email_ed);
		pwd_ed = (EditText) findViewById(R.id.pwd_ed);
		repwd_ed = (EditText) findViewById(R.id.repwd_ed);
		qq_ed = (EditText) findViewById(R.id.qq_ed);
		phones_ed = (EditText) findViewById(R.id.phones_ed);
		address_ed = (EditText) findViewById(R.id.address_ed);
	}

	@Override
	protected void initWidgetAciotns() {
		// TODO Auto-generated method stub
		registerBt.setOnClickListener(this);
		backBt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.register_bt:
			registerData();
			break;
		case R.id.iv_back:
			finish();
			break;
		default:
			break;
		}
	}

	public void registerData() {
		final String nickName = nickname_ed.getText().toString().trim();
		final String email = email_ed.getText().toString().trim();
		final String pwd = pwd_ed.getText().toString().trim();
		final String repwd = repwd_ed.getText().toString().trim();
		final String qq = qq_ed.getText().toString().trim();
		final String phoneNum = phones_ed.getText().toString().trim();
		final String address = address_ed.getText().toString().trim();
		final String url = getResources().getString(R.string.base_url)
				+ "api/system/register?partner=meilituan&sign="
				+ MD5.MD5Encode("partner=meilituan&address=" + address + "&email="
						+ email + "&mobile=" + phoneNum + "&nickname="
						+ nickName + "&password=" + pwd + "&qq=" + qq + "lary");

		if (TextUtil.isEmpty(nickName)) {
			Toast.makeText(RegesterActivity.this, "请填写昵称", 0).show();
			return;
		}
		if (TextUtil.isEmpty(email)) {
			Toast.makeText(RegesterActivity.this, "请填写邮箱", 0).show();
			return;
		}
		if (TextUtil.isEmpty(pwd)) {
			Toast.makeText(RegesterActivity.this, "请填写密码", 0).show();
			return;
		}
		if (TextUtil.isEmpty(repwd)) {
			Toast.makeText(RegesterActivity.this, "请填写确认密码", 0).show();
			return;
		}
		if (!pwd.equals(repwd)) {
			Toast.makeText(RegesterActivity.this, "两次密码不一致", 0).show();
			return;
		}
		VolleyPostRequest<BaseModel> request = new VolleyPostRequest<BaseModel>(
				url, BaseModel.class, new Listener<BaseModel>() {

					@Override
					public void onResponse(BaseModel arg0) {
						// TODO Auto-generated method stub
						if (0 == arg0.getCode()) {
							Toast.makeText(RegesterActivity.this, "注册成功", 1)
									.show();
							RegesterActivity.this.finish();
/*							Intent reIn = new Intent(RegesterActivity.this,
									LoginActivity.class);
							startActivity(reIn);*/
						} else {
							Toast.makeText(RegesterActivity.this,
									arg0.getMessage(), 1).show();

						}
					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(RegesterActivity.this, "注册失败" + arg0, 1)
								.show();

					}

				}, RegesterActivity.this) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> hashMap = new HashMap<String, String>();
				// hashMap.put("Accept", "application/json");
				// hashMap.put("content-Type",
				// "application/json; charset=UTF-8");
				hashMap.put("contentType", "application/x-www-form-urlencoded");
				return hashMap;
			}

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> hashMap = new HashMap<String, String>();
				hashMap.put("nickname", nickName);
				hashMap.put("email", email);
				hashMap.put("password", pwd);
				hashMap.put("mobile", phoneNum);
				hashMap.put("address", address);
				hashMap.put("qq", qq);
				return hashMap;
			}
		};
		request.setShouldCache(false);
		VolleyUtil.getQueue(RegesterActivity.this).add(request);

	}

}
