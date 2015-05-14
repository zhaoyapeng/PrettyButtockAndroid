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
import com.lary.health.service.model.GymnasticsListModel;
import com.lary.health.service.model.OriginalListModel;
import com.lary.health.ui.adaper.OriginalAdapter;
import com.lary.health.ui.widget.XListView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-8上午12:22:08
 * @Email zhaoyp@witmob.com
 * @Description 原创视频Fragment
 */
public class OriginalFragment extends BaseViewPagerFragment implements XListView.IXListViewListener {

	private XListView originalListView;
	private OriginalAdapter adapter;
	private int currentPage = 1;
	private int LIMIT = 10;

	@Override
	protected void initData() {
		adapter = new OriginalAdapter(mContext);
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_original, container, false);
		originalListView = (XListView) view.findViewById(R.id.listview_riginal);
		return view;
	}

	@Override
	protected void initWidgetActions() {
		originalListView.setAdapter(adapter);
		originalListView.setPullLoadEnable(false);
		originalListView.setPullLoadEnable(true);
		originalListView.setXListViewListener(this);
	}

	private void getOriginalRereshNet(int pageIndex, int pageSize) {

		String url = getString(R.string.base_url) + "api/system/GetVideo2?partner=meilitun&pageIndex=" + pageIndex
				+ "&pageSize=" + pageSize + "&sign="
				+ MD5.getMD5("pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&partner=meilitun" + "lary");
		VolleyGetRequest<OriginalListModel> request = new VolleyGetRequest<OriginalListModel>(url,
				OriginalListModel.class, new Listener<OriginalListModel>() {
					@Override
					public void onResponse(OriginalListModel model) {
						if (model.getCode() == 0) {
							adapter.refreshData(model.getRows());
							if (model.getTotalpage().equals(model.getCurpage())) {
								originalListView.setPullLoadEnable(false);
							} else {
								originalListView.setPullLoadEnable(true);
							}
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

	private void getOriginalAddNet(int pageIndex, int pageSize) {

		String url = getString(R.string.base_url) + "api/system/GetVideo2?partner=meilitun&pageIndex=" + pageIndex
				+ "&pageSize=" + pageSize + "&sign="
				+ MD5.getMD5("pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&partner=meilitun" + "lary");
		VolleyGetRequest<OriginalListModel> request = new VolleyGetRequest<OriginalListModel>(url,
				OriginalListModel.class, new Listener<OriginalListModel>() {
					@Override
					public void onResponse(OriginalListModel model) {
						if (model.getCode() == 0) {
							adapter.addData(model.getRows());
							if (model.getTotalpage().equals(model.getCurpage())) {
								originalListView.setPullLoadEnable(false);
							} else {
								originalListView.setPullLoadEnable(true);
							}
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
	public void onRefresh() {
		currentPage = 1;
		getOriginalRereshNet(currentPage, LIMIT);
	}

	@Override
	public void onLoadMore() {
		currentPage++;
		getOriginalAddNet(currentPage, LIMIT);
	}

	@Override
	protected void refreshData() {
		currentPage = 1;
		getOriginalRereshNet(currentPage, LIMIT);
	}

}
