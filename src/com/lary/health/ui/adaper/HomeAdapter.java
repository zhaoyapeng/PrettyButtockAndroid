package com.lary.health.ui.adaper;

import com.lary.health.ui.util.HomeFragmentUtil;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:18:32
 * @Email zhaoyp@witmob.com
 * @Description 首页adapter
 */
public class HomeAdapter extends FragmentStatePagerAdapter{

	public HomeAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return HomeFragmentUtil.getFragment(position);
	}

	@Override
	public int getCount() {
		return 4;
	}

}
