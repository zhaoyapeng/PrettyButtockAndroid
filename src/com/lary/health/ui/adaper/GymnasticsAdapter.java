package com.lary.health.ui.adaper;

import com.lary.health.ui.fragment.GymnasticsFragment;
import com.lary.health.ui.fragment.OriginalFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-8上午12:26:34
 * @Email zhaoyp@witmob.com
 * @Description 垫上体操 Adapter
 */
public class GymnasticsAdapter extends FragmentStatePagerAdapter{

	public GymnasticsAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		if(arg0==0){
			return new GymnasticsFragment();
		}else{
			return new OriginalFragment();
		}
	
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
