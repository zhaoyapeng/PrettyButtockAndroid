package com.lary.health.ui;

import java.util.HashMap;
import java.util.Map;

import netlib.net.volley.VolleyGetRequest;
import netlib.net.volley.VolleyUtil;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
 * @version create time:2015-5-13下午11:19:46
 * @Email zhaoyp@witmob.com
 * @Description 搜索activity
 */
public class SearchMemberActiviy extends BaseFragmentActivity {

	private XListView listView;
	private CirclegGroupsAdapter adapter;
	private Button searchBtn;
	private EditText searchEdit;

	@Override
	protected void initData() {
		adapter = new CirclegGroupsAdapter(this);
	}

	@Override
	protected void initView() {
		setContentView(R.layout.activity_serach_member);
		listView = (XListView) findViewById(R.id.listView);
		searchBtn = (Button) findViewById(R.id.btn_search);
		searchEdit = (EditText) findViewById(R.id.edit_search);
	}

	@Override
	protected void initWidgetAciotns() {
		listView.setAdapter(adapter);
		searchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getCircleNet(searchEdit.getText().toString());
			}
		});

	}

	/**
	 * 模糊查询
	 * */
	private void getCircleNet(String name) {

		String url = getString(R.string.base_url) + "api/system/SearchCircleList?partner=meilitun&name=" + name + "&sign="
				+ MD5.getMD5("name=" + name + "&partner=meilitun" + "lary");
		Log.e("tag", "网络请求url" + url);
		VolleyGetRequest<CircleListModel> request = new VolleyGetRequest<CircleListModel>(url, CircleListModel.class,
				new Listener<CircleListModel>() {
					@Override
					public void onResponse(CircleListModel model) {
						if (model.getCode() == 0) {
							adapter.refreshData(model.getRows());
						}
						Toast.makeText(SearchMemberActiviy.this, "网络请求成功", Toast.LENGTH_SHORT).show();
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("tag", "VolleyError" + arg0);
						Toast.makeText(SearchMemberActiviy.this, "网络请求失败了" + arg0, Toast.LENGTH_SHORT).show();
					}

				}, SearchMemberActiviy.this) {

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
		VolleyUtil.getQueue(SearchMemberActiviy.this).add(request);
	}
}
