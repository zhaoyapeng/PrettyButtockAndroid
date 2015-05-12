package com.lary.health.ui;

import java.util.HashMap;
import java.util.Map;

import netlib.net.volley.VolleyGetRequest;
import netlib.net.volley.VolleyUtil;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.lary.health.R;
import com.lary.health.MD5Util.MD5;
import com.lary.health.service.model.GymnasticsListModel;
import com.lary.health.ui.widget.XListView;

public class MusicAndHealthActivity extends BaseFragmentActivity {

	private XListView healthList;
	private int currentPage = 1;
	private int LIMIT = 10;
	private TextView backBt;
	
	
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_musicheath);
		backBt = (TextView) findViewById(R.id.back_tv);
		healthList = (XListView) findViewById(R.id.listview_heath);
	}

	@Override
	protected void initWidgetAciotns() {
		// TODO Auto-generated method stub
		gethealthsRefreshNet(1,10);

	}
	
	/**
	 * 刷新数据
	 * */
	private void gethealthsRefreshNet(int pageIndex, int pageSize) {

		String url = getString(R.string.base_url) + "api/system/ GetHealthMusicList?partner=meilitun&pageIndex=" + pageIndex
				+ "&pageSize=" + pageSize + "&sign="
				+ MD5.getMD5("pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&partner=meilitun" + "lary");
		Log.e("tag", "网络请求url" + url);
		VolleyGetRequest<GymnasticsListModel> request = new VolleyGetRequest<GymnasticsListModel>(url,
				GymnasticsListModel.class, new Listener<GymnasticsListModel>() {
					@Override
					public void onResponse(GymnasticsListModel model) {
						if(model.getCode()==0){
					//		adapter.refreshData(model.getRows());
//							if(model.getTotalpage().equals(model.getCurpage())){
//								gymnasticsList.setPullLoadEnable(false);
//							}else{
//								gymnasticsList.setPullLoadEnable(true);
//							}
						}
						Toast.makeText(MusicAndHealthActivity.this, "网络请求成功", Toast.LENGTH_SHORT).show();
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("tag", "VolleyError" + arg0);
						Toast.makeText(MusicAndHealthActivity.this, "网络请求失败了" + arg0, Toast.LENGTH_SHORT).show();
					}

				}, MusicAndHealthActivity.this) {

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
		VolleyUtil.getQueue(MusicAndHealthActivity.this).add(request);
	}

}
