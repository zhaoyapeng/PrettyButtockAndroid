package com.lary.health.ui.fragment;

import com.lary.health.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:31:12
 * @Email zhaoyp@witmob.com
 * @Description 首页fragment
 */
public class HomeFragment extends BaseViewPagerFragment {

	private ImageView ceshiImage;
	private ImageLoader imageLoader;
	private DisplayImageOptions avatarOptions;

	@Override
	protected void initData() {
		imageLoader = ImageLoader.getInstance();
		avatarOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new SimpleBitmapDisplayer())
				.imageScaleType(ImageScaleType.EXACTLY).build();
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		ceshiImage = (ImageView) view.findViewById(R.id.img_ceshi);
		return view;
	}

	@Override
	protected void initWidgetActions() {
		String url ="http://a.hiphotos.baidu.com/image/w%3D230/sign=1a3ed992a60f4bfb8cd09957334f788f/1e30e924b899a901d434ed3b1f950a7b0208f5be.jpg";
		imageLoader.displayImage(url, ceshiImage, avatarOptions);
		
		
	}

	@Override
	protected void refreshData() {

	}

}
