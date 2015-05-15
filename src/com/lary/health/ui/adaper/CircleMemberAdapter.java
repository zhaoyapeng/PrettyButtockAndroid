package com.lary.health.ui.adaper;

import java.util.ArrayList;
import java.util.List;

import com.lary.health.R;
import com.lary.health.service.model.CircleItemModel;
import com.lary.health.service.model.CircleMemberModel;
import com.lary.health.ui.adaper.CirclegGroupsAdapter.ViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
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
 * @version create time:2015-5-13下午7:51:45
 * @Email zhaoyp@witmob.com
 * @Description 美丽圈组 成员adapter
 */
public class CircleMemberAdapter extends BaseAdapter {
	private Context mContext;
	private ImageLoader imageLoader;
	private DisplayImageOptions avatarOptions;
	private List<CircleMemberModel> circleList;

	public CircleMemberAdapter(Context mContext) {
		this.mContext = mContext;
		circleList = new ArrayList<CircleMemberModel>();
		imageLoader = ImageLoader.getInstance();
		avatarOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new SimpleBitmapDisplayer())
				.imageScaleType(ImageScaleType.EXACTLY).displayer(new RoundedBitmapDisplayer(1000)).build();
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
		public CircleMemberModel model;

		public ViewHolder(View view) {
			avatarImg = (ImageView) view.findViewById(R.id.img_avatar);
			titleText = (TextView) view.findViewById(R.id.text_name);
		}

		public void update(CircleMemberModel model) {
			this.model = model;
			imageLoader.displayImage(mContext.getString(R.string.base_url) + model.getPhotoImage(), avatarImg,
					avatarOptions);
			titleText.setText(model.getName());
		}
	}

	public void refreshData(List<CircleMemberModel> circleList) {
		this.circleList.clear();
		this.circleList.addAll(circleList);
		notifyDataSetChanged();
	}

}
