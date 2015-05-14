package com.lary.health.ui.fragment;

import java.util.HashMap;
import java.util.Map;

import netlib.net.volley.VolleyGetRequest;
import netlib.net.volley.VolleyUtil;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.lary.health.R;
import com.lary.health.MD5Util.MD5;
import com.lary.health.service.model.CircleListModel;
import com.lary.health.service.model.GymnasticsListModel;
import com.lary.health.ui.CircleMemberActivity;
import com.lary.health.ui.SearchMemberActiviy;
import com.lary.health.ui.adaper.CirclegGroupsAdapter;
import com.lary.health.ui.adaper.CirclegGroupsAdapter.ViewHolder;
import com.lary.health.ui.widget.XListView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:41:50
 * @Email zhaoyp@witmob.com
 * @Description 美丽圈Fragment
 */
public class CircleFragment extends BaseViewPagerFragment {

	private XListView listview;
	private CirclegGroupsAdapter adapter;
	private Button serachBtn;

	@Override
	protected void initData() {
		adapter = new CirclegGroupsAdapter(mContext);
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_circle, container, false);
		listview = (XListView) view.findViewById(R.id.listView);
		serachBtn = (Button) view.findViewById(R.id.btn_search);
		return view;
	}

	@Override
	protected void initWidgetActions() {
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ViewHolder holder = (ViewHolder) view.getTag();
				Intent intent = new Intent(mContext, CircleMemberActivity.class);
				intent.putExtra("id", holder.model.getId());
				startActivity(intent);
			}
		});
		serachBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, SearchMemberActiviy.class);
				startActivity(intent);
			}
		});
		getCircleNet("82");
	}

	/**
	 * 获取美丽圈群组
	 * */
	private void getCircleNet(String userId) {

		String url = getString(R.string.base_url) + "api/system/GetCircleList?partner=meilitun&id=" + userId + "&sign="
				+ MD5.getMD5("id=" + userId + "&partner=meilitun" + "lary");
		Log.e("tag", "网络请求url" + url);
		VolleyGetRequest<CircleListModel> request = new VolleyGetRequest<CircleListModel>(url, CircleListModel.class,
				new Listener<CircleListModel>() {
					@Override
					public void onResponse(CircleListModel model) {
						if (model.getCode() == 0) {
							adapter.refreshData(model.getRows());
						}
						Toast.makeText(mContext, "网络请求成功", Toast.LENGTH_SHORT).show();
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("tag", "VolleyError" + arg0);
						Toast.makeText(mContext, "网络请求失败了" + arg0, Toast.LENGTH_SHORT).show();
					}

				}, mContext) {

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
		VolleyUtil.getQueue(mContext).add(request);
	}

	@Override
	protected void refreshData() {
		getCircleNet("82");
	}

}
