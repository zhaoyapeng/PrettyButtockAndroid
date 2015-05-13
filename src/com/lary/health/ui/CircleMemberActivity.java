package com.lary.health.ui;

import java.util.HashMap;
import java.util.Map;

import netlib.net.volley.VolleyGetRequest;
import netlib.net.volley.VolleyUtil;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.lary.health.R;
import com.lary.health.MD5Util.MD5;
import com.lary.health.service.model.CircleListModel;
import com.lary.health.service.model.CircleMemberListModel;
import com.lary.health.ui.adaper.CircleMemberAdapter;
import com.lary.health.ui.adaper.CirclegGroupsAdapter;
import com.lary.health.ui.widget.XListView;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-13下午7:47:35
 * @Email zhaoyp@witmob.com
 * @Description 美丽圈组成员
 */
public class CircleMemberActivity extends BaseFragmentActivity {
	private XListView listView;
	private CircleMemberAdapter adapter;
	private String id;

	@Override
	protected void initData() {
		adapter = new CircleMemberAdapter(this);
		id = getIntent().getStringExtra("id");
	}

	@Override
	protected void initView() {
		setContentView(R.layout.activity_circle_member);
		listView = (XListView) findViewById(R.id.listView);
	}

	@Override
	protected void initWidgetAciotns() {
		listView.setAdapter(adapter);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);


		getCircleNet(id);
	}

	/**
	 * 获取美丽圈群组成员
	 * */
	private void getCircleNet(String id) {

		String url = getString(R.string.base_url) + "api/system/GetCircleMembers?partner=meilitun&id=" + id + "&sign="
				+ MD5.getMD5("id=" + id + "&partner=meilitun" + "lary");

		Log.e("tag", "网络请求url" + url);
		VolleyGetRequest<CircleMemberListModel> request = new VolleyGetRequest<CircleMemberListModel>(url,
				CircleMemberListModel.class, new Listener<CircleMemberListModel>() {
					@Override
					public void onResponse(CircleMemberListModel model) {
						if (model.getCode() == 0) {
							adapter.refreshData(model.getRows());
						}
						Toast.makeText(CircleMemberActivity.this, "网络请求成功", Toast.LENGTH_SHORT).show();
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("tag", "VolleyError" + arg0);
						Toast.makeText(CircleMemberActivity.this, "网络请求失败了" + arg0, Toast.LENGTH_SHORT).show();
					}

				}, CircleMemberActivity.this) {

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// Auto-generated method stub
				HashMap<String, String> hashMap = new HashMap<String, String>();
				// hashMap.put("Accept", "application/json");
				// hashMap.put("content-Type",
				// "application/json; charset=UTF-8");
				hashMap.put("contentType", "application/x-www-form-urlencoded");
				return hashMap;
			}
		};
		request.setShouldCache(false);
		VolleyUtil.getQueue(CircleMemberActivity.this).add(request);
	}

}
