package com.lary.health.ui.fragment;

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
import com.lary.health.musicListModel.MusicListAdapter;
import com.lary.health.service.model.GymnasticsListModel;
import com.lary.health.ui.PlayVideoActivity;
import com.lary.health.ui.widget.XListView;

import GymnasticsListItemModel.GymnasticeAdapter.ViewHolder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author du 五行音乐
 */
public class FiveMusicFragment extends BaseViewPagerFragment implements XListView.IXListViewListener{
	private int index;
	private TextView music_title, music_back, music_small_title, zhuanji_tv;
	private ImageView ori_music_uplod;
	private MusicListAdapter adapter;
	private XListView listview_music;
	private int currentPage = 1;
	private int LIMIT = 10;
	private ViewHolder holder;


	public static FiveMusicFragment getInstance(int index) {
		FiveMusicFragment q = new FiveMusicFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("index", index);
		q.setArguments(bundle);
		return q;
	}

	@Override
	protected void initData() {
		adapter = new MusicListAdapter(getActivity());
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_five_music, container,
				false);
		music_title = (TextView) view.findViewById(R.id.music_title_tv);
		music_back = (TextView) view.findViewById(R.id.back_tv);
		music_small_title = (TextView) view
				.findViewById(R.id.music_small_title);
		zhuanji_tv = (TextView) view.findViewById(R.id.zhuanji_tv);
		ori_music_uplod = (ImageView) view.findViewById(R.id.ori_music_uplod);
		listview_music = (XListView) view.findViewById(R.id.listview_music);
		return view;
	}

	@Override
	protected void initWidgetActions() {
		// TODO Auto-generated method stub
		listview_music.setAdapter(adapter);
//		listview_music.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				holder = (ViewHolder)view.getTag();
//			
//			}
//		});
		listview_music.setXListViewListener(this);
		listview_music.setPullRefreshEnable(true);
		listview_music.setPullRefreshEnable(false);
	}

	public void getMusicList(int pageIndex, int pageSize, int index) {
		String url = getString(R.string.base_url)
				+ "api/system/GetAudio?partner=meilitun&pageIndex="
				+ pageIndex
				+ "&pageSize="
				+ pageSize
				+ "&audiotype="
				+ index
				+ "&sign="
				+ MD5.getMD5("audiotype=" + index + "&pageIndex=" + pageIndex
						+ "&pageSize=" + pageSize + "&partner=meilitun"
						+ "lary");
		Log.e("tag", "网络请求url" + url);
		VolleyGetRequest<GymnasticsListModel> request = new VolleyGetRequest<GymnasticsListModel>(
				url, GymnasticsListModel.class,
				new Listener<GymnasticsListModel>() {
					@Override
					public void onResponse(GymnasticsListModel model) {
						if (model.getCode() == 0) {
							
							 adapter.refreshData(model.getRows());
							 if(TextUtil.isEmpty(model.getTotalpage())||TextUtil.isEmpty(model.getCurpage())){
								 return;
							 }
							 if(model.getTotalpage().equals(model.getCurpage())){
								 listview_music.setPullLoadEnable(false);
							 }else{
								 listview_music.setPullLoadEnable(true);
							 }

						}
						Toast.makeText(mContext, "网络请求成功", Toast.LENGTH_SHORT)
								.show();
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("tag", "VolleyError" + arg0);
						Toast.makeText(mContext, "网络请求失败了" + arg0,
								Toast.LENGTH_SHORT).show();
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

	//加载更多
	public void addMusicList(int pageIndex, int pageSize, int index) {
		String url = getString(R.string.base_url)
				+ "api/system/GetAudio?partner=meilitun&pageIndex="
				+ pageIndex
				+ "&pageSize="
				+ pageSize
				+ "&audiotype="
				+ index
				+ "&sign="
				+ MD5.getMD5("audiotype=" + index + "&pageIndex=" + pageIndex
						+ "&pageSize=" + pageSize + "&partner=meilitun"
						+ "lary");
		Log.e("tag", "网络请求url" + url);
		VolleyGetRequest<GymnasticsListModel> request = new VolleyGetRequest<GymnasticsListModel>(
				url, GymnasticsListModel.class,
				new Listener<GymnasticsListModel>() {
					@Override
					public void onResponse(GymnasticsListModel model) {
						if (model.getCode() == 0) {
							
							adapter.addData(model.getRows());
							 if(TextUtil.isEmpty(model.getTotalpage())||TextUtil.isEmpty(model.getCurpage())){
								 return;
							 }
							 if(model.getTotalpage().equals(model.getCurpage())){
								 listview_music.setPullLoadEnable(false);
							 }else{
								 listview_music.setPullLoadEnable(true);
							 }

						}
						Toast.makeText(mContext, "网络请求成功", Toast.LENGTH_SHORT)
								.show();
					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.e("tag", "VolleyError" + arg0);
						Toast.makeText(mContext, "网络请求失败了" + arg0,
								Toast.LENGTH_SHORT).show();
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
		// TODO Auto-generated method stub
		index = getArguments().getInt("index");
		switch (index) {
		case 0:
			music_title.setText("五行音乐");
			music_small_title.setText("五行音乐");
			isVisiiable(true);
			break;
		case 1:
			music_title.setText("音乐调理");
			music_small_title.setText("音乐调理");
			isVisiiable(true);

			break;
		case 2:
			music_title.setText("原创音乐");
			music_small_title.setText("原创音乐");
			isVisiiable(false);

			break;
		case 3:
			music_title.setText("美丽臀制造");
			music_small_title.setText("美丽臀制造");
			isVisiiable(true);

			break;
		case 4:
			music_title.setText("音乐处方");
			music_small_title.setText("音乐处方");
			isVisiiable(true);

			break;
		default:
			break;
		}
		getMusicList(1, 10, index + 1);
	}

	public void isVisiiable(boolean isvis) {
		if (isvis) {
			zhuanji_tv.setVisibility(View.VISIBLE);
			ori_music_uplod.setVisibility(View.GONE);
		} else {
			zhuanji_tv.setVisibility(View.GONE);
			ori_music_uplod.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		currentPage = 1;
		getMusicList(1, 10, index + 1);

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		currentPage++;
		addMusicList(currentPage,10, index + 1);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(adapter != null)
		adapter.stopPLay();
	}


	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(adapter != null)
		adapter.stopPLay();
	}
	
}
