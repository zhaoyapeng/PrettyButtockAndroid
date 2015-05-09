package com.lary.health.ui.adaper;

import java.util.ArrayList;
import java.util.List;

import GymnasticsListItemModel.GymnasticeAdapter.ViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lary.health.R;
import com.lary.health.service.model.GymnasticsListItemModel;
import com.lary.health.service.model.OriginalItemModel;
import com.lary.health.service.model.OriginalListModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-9下午10:35:29
 * @Email zhaoyp@witmob.com
 * @Description 原创音乐adapter
 */
public class OriginalAdapter extends BaseAdapter{
	private Context mContext;
	private List<OriginalItemModel> list;
	private ImageLoader imageLoader;
	private DisplayImageOptions avatarOptions;

	public OriginalAdapter(Context mContext) {
		this.mContext = mContext;
		list = new ArrayList<OriginalItemModel>();
		imageLoader = ImageLoader.getInstance();
		avatarOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new SimpleBitmapDisplayer())
				.imageScaleType(ImageScaleType.EXACTLY).build();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gymnastics, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.update(list.get(position));
		return convertView;
	}

	public class ViewHolder {
		private ImageView gyImg;
		private TextView titleText, sizeText;

		public ViewHolder(View view) {
			gyImg = (ImageView)view.findViewById(R.id.img_gymnastics);
			titleText = (TextView)view.findViewById(R.id.text_title);
			sizeText = (TextView)view.findViewById(R.id.text_size);
		}
		
		public void update(OriginalItemModel model){
//			imageLoader.displayImage(model.getImgUrl(), gyImg,avatarOptions);
//			titleText.setText(model.getIntruduce());
		}
	}
	
	public void refreshData(List<OriginalItemModel> list){
		this.list.clear();
		this.list.addAll(list);
	}
	
	public void addData(List<OriginalItemModel> list){
		this.list.addAll(list);
	}
}
