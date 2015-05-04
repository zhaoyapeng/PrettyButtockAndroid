package com.lary.health;

import java.io.File;

import netlib.util.LibIOUtil;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4下午5:21:24
 * @Email zhaoyp@witmob.com
 * @Description 自定义Application
 */
public class HealthApplocation extends Application {

	@Override
	public void onCreate() {
		initImageLoader();
		super.onCreate();
	}

	public void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
				.threadPriority(Thread.NORM_PRIORITY).denyCacheImageMultipleSizesInMemory()
				.diskCache(new UnlimitedDiscCache(new File(LibIOUtil.getImagePath(this)))) // default
				.diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(50 * 1024 * 1024) // 50Mb
				.threadPoolSize(5).tasksProcessingOrder(QueueProcessingType.LIFO)
				// .writeDebugLogs() // Remove for release app
				.build();
		ImageLoader.getInstance().init(config);
	}
}
