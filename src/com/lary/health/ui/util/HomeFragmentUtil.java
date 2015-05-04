package com.lary.health.ui.util;

import com.lary.health.ui.fragment.CircleFragment;
import com.lary.health.ui.fragment.HomeFragment;
import com.lary.health.ui.fragment.PersionFragment;
import com.lary.health.ui.fragment.ShopFragment;

import android.support.v4.app.Fragment;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:14:53
 * @Email zhaoyp@witmob.com
 * @Description 首页 fragment工具类
 */
public class HomeFragmentUtil {
	private final static int TYPE_HOME = 0;
	private final static int TYPE_CIRCLE = 1;
	private final static int TYPE_SHOP = 2;
	private final static int TYPE_PERSION = 3;

	public static Fragment getFragment(int index) {
		Fragment fragment = null;
		switch (index) {
		case TYPE_HOME:
			fragment = new HomeFragment();
			break;

		case TYPE_CIRCLE:
			fragment = new CircleFragment();
			break;

		case TYPE_SHOP:
			fragment = new ShopFragment();
			break;
		case TYPE_PERSION:
			fragment = new PersionFragment();
			break;
		}
		return fragment;
	}
}
