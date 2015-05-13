package com.lary.health.ui.adaper;

import java.util.ArrayList;
import java.util.List;

import com.lary.health.R;
import com.lary.health.service.model.CircleItemModel;
import com.lary.health.service.model.OriginalItemModel;
import com.lary.health.ui.adaper.OriginalAdapter.ViewHolder;
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
 * @author zhaoyapeng
 * @version create time:2015-5-12下午11:38:18
 * @Email zhaoyp@witmob.com
 * @Description 群组adapter
 */
public class CirclegGroupsAdapter extends BaseAdapter {

	private List<CircleItemModel> circleList;
	private Context mContext;
	private ImageLoader imageLoader;
	private DisplayImageOptions avatarOptions;

	public CirclegGroupsAdapter(Context mContext) {
		this.mContext = mContext;
		circleList = new ArrayList<CircleItemModel>();
		imageLoader = ImageLoader.getInstance();
		avatarOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new SimpleBitmapDisplayer())
				.imageScaleType(ImageScaleType.EXACTLY).build();
	}

	@Override
	public int getCount() {
		return circleList.size();
	}

	@Override
	public Object getItem(int position) {
		return circleList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_circle, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.update(circleList.get(position));
		return convertView;
	}

	public class ViewHolder {
		private ImageView avatarImg;
		private TextView titleText;
		public CircleItemModel model;

		public ViewHolder(View view) {
			avatarImg = (ImageView) view.findViewById(R.id.img_avatar);
			titleText = (TextView) view.findViewById(R.id.text_name);
		}

		public void update(CircleItemModel model) {
			this.model = model;
			imageLoader.displayImage(mContext.getString(R.string.base_url) + model.getImageurl(), avatarImg,
					avatarOptions);
			titleText.setText(model.getName());
		}
	}

	public void refreshData(List<CircleItemModel> list) {
		this.circleList.clear();
		this.circleList.addAll(list);
		notifyDataSetChanged();
	}

}
