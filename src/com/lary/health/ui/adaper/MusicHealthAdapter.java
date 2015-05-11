package com.lary.health.ui.adaper;

import com.lary.health.ui.fragment.FiveMusicFragment;
import com.lary.health.ui.util.MusicHealthUtil;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MusicHealthAdapter extends FragmentStatePagerAdapter {

	public MusicHealthAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		return FiveMusicFragment.getInstance(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}



}
