package com.lary.health.ui;

import java.util.HashMap;
import java.util.Map;

import netlib.net.volley.VolleyGetRequest;
import netlib.net.volley.VolleyUtil;
import netlib.util.TextUtil;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.lary.health.R;
import com.lary.health.MD5Util.MD5;
import com.lary.health.service.model.ExerciseRecordModelNet;
import com.lary.health.service.model.GymnasticsListModel;

public class ExerciseRecordActivity extends BaseFragmentActivity {

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initWidgetAciotns() {
		// TODO Auto-generated method stub
		getRecord(1,10);
	}
	
	public void getRecord(int pageIndex, int pageSize){
		String url = getString(R.string.base_url)
				+ "api/system/GetTraningRecords?partner=meilitun&pageIndex="
				+ pageIndex
				+ "&pageSize="
				+ pageSize
				+ "&Id="
				+ "82"
				+ "&sign="
				+ MD5.MD5Encode("Id=" + "82" + "&pageIndex=" + pageIndex
						+ "&pageSize=" + pageSize + "&partner=meilitun"
						+ "lary");
		Log.e("tag", "网络请求url" + url);
		VolleyGetRequest<ExerciseRecordModelNet> request = new VolleyGetRequest<ExerciseRecordModelNet>(
				url, ExerciseRecordModelNet.class,
				new Listener<ExerciseRecordModelNet>() {
					@Override
					public void onResponse(ExerciseRecordModelNet model) {
						
						if (model.getCode() == 0) {
							
						//	 adapter.refreshData(model.getRows());
							 if(TextUtil.isEmpty(model.getTotalpage())||TextUtil.isEmpty(model.getCurpage())){
								 return;
							 }
							 if(model.getTotalpage().equals(model.getCurpage())){
							//	 listview_music.setPullLoadEnable(false);
							 }else{
							//	 listview_music.setPullLoadEnable(true);
							 }

						}
						Toast.makeText(ExerciseRecordActivity.this, "网络请求成功", Toast.LENGTH_SHORT)
								.show();
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("tag", "VolleyError" + arg0);
						Toast.makeText(ExerciseRecordActivity.this, "网络请求失败了" + arg0,
								Toast.LENGTH_SHORT).show();
					}

				}, ExerciseRecordActivity.this) {

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
		VolleyUtil.getQueue(ExerciseRecordActivity.this).add(request);
	
	}

}
