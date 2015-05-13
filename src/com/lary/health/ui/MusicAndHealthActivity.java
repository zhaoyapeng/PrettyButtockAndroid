package com.lary.health.ui;

import java.util.HashMap;
import java.util.Map;

import netlib.net.volley.VolleyGetRequest;
import netlib.net.volley.VolleyUtil;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.lary.health.R;
import com.lary.health.MD5Util.MD5;
import com.lary.health.service.model.GymnasticsListModel;
import com.lary.health.service.model.MusicHealthModelNet;
import com.lary.health.ui.adaper.MusicAndHealthAdapter;
import com.lary.health.ui.adaper.MusicAndHealthAdapter.ViewHolder;
import com.lary.health.ui.widget.XListView;

/**
 * 音乐养生
 * @author duronggang
 *
 * 2015年5月13日  duronggang@buybal.com
 */
public class MusicAndHealthActivity extends BaseFragmentActivity implements XListView.IXListViewListener,OnClickListener{

	private XListView healthList;
	private int currentPage = 1;
	private int LIMIT = 10;
	private TextView backBt;
	private MusicAndHealthAdapter adapter;
	private String type = "refresh";
	
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		adapter = new MusicAndHealthAdapter(MusicAndHealthActivity.this);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_musicheath);
		backBt = (TextView) findViewById(R.id.back_tv);
		healthList = (XListView) findViewById(R.id.listview_heath);
		healthList.setAdapter(adapter);
		healthList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ViewHolder viewHolder = (ViewHolder) view.getTag();
				Intent intent = new Intent(MusicAndHealthActivity.this,
						DetailWebActivity.class);
				intent.putExtra("webUrl", MusicAndHealthActivity.this.getResources().getString(R.string.base_url)+"/home/singlepage/"+viewHolder.model.getId());
				startActivity(intent);
			}
		});
	}

	@Override
	protected void initWidgetAciotns() {
		// TODO Auto-generated method stub
		backBt.setOnClickListener(this);
		type = "refresh";
		currentPage = 1;
		gethealthsRefreshNet(currentPage,LIMIT);

	}
	
	/**
	 * 刷新数据
	 * */
	private void gethealthsRefreshNet(int pageIndex, int pageSize) {

		String url = getString(R.string.base_url) + "api/system/GetHealthMusicList?partner=meilitun&pageIndex=" + pageIndex
				+ "&pageSize=" + pageSize + "&sign="
				+ MD5.getMD5("pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&partner=meilitun" + "lary");
		Log.e("tag", "网络请求url" + url);
		VolleyGetRequest<MusicHealthModelNet> request = new VolleyGetRequest<MusicHealthModelNet>(url,
				MusicHealthModelNet.class, new Listener<MusicHealthModelNet>() {
					@Override
					public void onResponse(MusicHealthModelNet model) {
						if(model.getCode()==0){
							if("more".equals(type)){
								
							}else{
							adapter.refreshData(model.getRows());
							}
							if(model.getTotalpage().equals(model.getCurpage())){
								healthList.setPullLoadEnable(false);
							}else{
								healthList.setPullLoadEnable(true);
							}
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

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		type = "refresh";
		currentPage = 1;
		gethealthsRefreshNet(currentPage,LIMIT);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		type = "more";
		currentPage++;
		gethealthsRefreshNet(currentPage,LIMIT);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MusicAndHealthActivity.this.finish();
	}

}
