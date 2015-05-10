package com.lary.health.ui.fragment;

import java.util.ArrayList;
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
import com.lary.health.service.model.GymnasticsListModel;
import com.lary.health.service.model.HomeModelNet;
import com.lary.health.ui.widget.XListView;

import GymnasticsListItemModel.GymnasticeAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-8上午12:25:11
 * @Email zhaoyp@witmob.com
 * @Description 垫上体操fragment
 */
public class GymnasticsFragment extends BaseViewPagerFragment implements XListView.IXListViewListener {
	private XListView gymnasticsList;
	private GymnasticeAdapter adapter;
	private int currentPage = 1;
	private int LIMIT = 10;

	@Override
	protected void initData() {
		adapter = new GymnasticeAdapter(mContext);
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_gymnastics, container, false);
		gymnasticsList = (XListView) view.findViewById(R.id.listview_gymnastuics);
		return view;
	}

	@Override
	protected void initWidgetActions() {
		gymnasticsList.setAdapter(adapter);
	}
	
	@Override
	public void onRefresh() {
		currentPage = 1;
		getGymnasticsRefreshNet(currentPage, LIMIT);
	}

	@Override
	public void onLoadMore() {
		currentPage++;
		getGymnasticsAddNet(currentPage, LIMIT);
	}

	/**
	 * 刷新数据
	 * */
	private void getGymnasticsRefreshNet(int pageIndex, int pageSize) {

		String url = getString(R.string.base_url) + "api/system/GetVideo1?partner=meilitun&pageIndex=" + pageIndex
				+ "&pageSize=" + pageSize + "&sign="
				+ MD5.getMD5("pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&partner=meilitun" + "lary");
		Log.e("tag", "网络请求url" + url);
		VolleyGetRequest<GymnasticsListModel> request = new VolleyGetRequest<GymnasticsListModel>(url,
				GymnasticsListModel.class, new Listener<GymnasticsListModel>() {
					@Override
					public void onResponse(GymnasticsListModel model) {
						if(model.getCode()==0){
							adapter.refreshData(model.getRows());
							Log.d("videourl",model.getRows().get(0).getVideoUrl());
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
				// TODO Auto-generated method stub
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
	
	/**
	 * 加载更多
	 * */
	private void getGymnasticsAddNet(int pageIndex, int pageSize) {

		String url = getString(R.string.base_url) + "api/system/GetVideo1?partner=meilitun&pageIndex=" + pageIndex
				+ "&pageSize=" + pageSize + "&sign="
				+ MD5.getMD5("pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&partner=meilitun" + "lary");
		Log.e("tag", "网络请求url" + url);
		VolleyGetRequest<GymnasticsListModel> request = new VolleyGetRequest<GymnasticsListModel>(url,
				GymnasticsListModel.class, new Listener<GymnasticsListModel>() {
					@Override
					public void onResponse(GymnasticsListModel model) {
						if(model.getCode()==0){
							adapter.addData(model.getRows());
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
				// TODO Auto-generated method stub
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
		currentPage=1;
		getGymnasticsRefreshNet(currentPage, LIMIT);
	}
}
