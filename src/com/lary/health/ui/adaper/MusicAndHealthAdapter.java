package com.lary.health.ui.adaper;

import java.util.ArrayList;
import java.util.List;

import com.lary.health.R;
import com.lary.health.R.layout;
import com.lary.health.service.model.GymnasticsListItemModel;
import com.lary.health.service.model.MusicHealthModel;
import com.lary.health.service.model.MusicHealthModelNet;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 音乐养生适配器
 * @author duronggang
 *
 * 2015年5月13日  duronggang@buybal.com
 */
public class MusicAndHealthAdapter extends BaseAdapter {

	private Context mContext;
	private List<MusicHealthModel> list;
	private ImageLoader imageLoader;
	private DisplayImageOptions avatarOptions;

	public MusicAndHealthAdapter(Context mContext){
		this.mContext = mContext;
		list = new ArrayList<MusicHealthModel>();
		imageLoader = ImageLoader.getInstance();
		avatarOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new SimpleBitmapDisplayer())
				.imageScaleType(ImageScaleType.EXACTLY).build();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.music_health_item, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.update(list.get(position));
		return convertView;
	}
	public class ViewHolder{

		private ImageView healthImg;
		private TextView nameTv;
		private TextView introuceTv;
		private TextView timeTv;
		public MusicHealthModel model;
		
		public ViewHolder(View view){
			healthImg = (ImageView) view.findViewById(R.id.news_img);
			nameTv = (TextView) view.findViewById(R.id.health_nametv);
			introuceTv = (TextView) view.findViewById(R.id.health_introudcetv);
			timeTv = (TextView) view.findViewById(R.id.health_timetv);
		}
		
		public void update(MusicHealthModel model){
			this.model = model;
			imageLoader.displayImage(mContext.getResources().getString(R.string.base_url_no)+model.getImgUrl(), healthImg,avatarOptions);
			nameTv.setText(model.getName());
			introuceTv.setText(model.getStrContent());
			timeTv.setText(model.getCreateDate());
		}
	}
	
	public void refreshData(List<MusicHealthModel> list){
		this.list.clear();
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	
	public void addData(List<MusicHealthModel> list){
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	}

