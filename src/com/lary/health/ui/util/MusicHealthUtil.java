package com.lary.health.ui.util;

import android.support.v4.app.Fragment;

import com.lary.health.ui.fragment.CircleFragment;
import com.lary.health.ui.fragment.ConditionMusicFragment;
import com.lary.health.ui.fragment.FiveMusicFragment;
import com.lary.health.ui.fragment.HomeFragment;
import com.lary.health.ui.fragment.MakeMusicFragment;
import com.lary.health.ui.fragment.OriginalMusicFragment;
import com.lary.health.ui.fragment.PersionFragment;
import com.lary.health.ui.fragment.PrescreptionMusicFragmen;
import com.lary.health.ui.fragment.ShopFragment;
/**
 * 
 * @author du
 * 音乐养生fragment工具类
 *
 */
public class MusicHealthUtil {
	private final static int TYPE_FIVEMUSIC = 0;
	private final static int TYPE_CONDITIONMUSIC = 1;
	private final static int TYPE_ORGINALMUSIC = 2;
	private final static int TYPE_MAKEMUSIC = 3;
	private final static int TYPE_PRESCREPTIONMUSIC = 4;


	public static Fragment getFragment(int index) {
		Fragment fragment = null;
		switch (index) {
		case TYPE_FIVEMUSIC:
			fragment = new FiveMusicFragment();
			break;

		case TYPE_CONDITIONMUSIC:
			fragment = new ConditionMusicFragment();
			break;

		case TYPE_ORGINALMUSIC:
			fragment = new OriginalMusicFragment();
			break;
		case TYPE_MAKEMUSIC:
			fragment = new MakeMusicFragment();
			break;
		case TYPE_PRESCREPTIONMUSIC:
			fragment = new PrescreptionMusicFragmen();
			break;
		}
		return fragment;
	}
	
}
