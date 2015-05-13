package com.lary.health.ui;

import netlib.util.Player;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.lary.health.R;
import com.lary.health.ui.adaper.MusicHealthAdapter;
import com.lary.health.ui.fragment.BaseFragment;
import com.lary.health.ui.widget.HomeViewPager;

/**
 * 原创音乐
 * @author duronggang
 *
 * 2015年5月13日  duronggang@buybal.com
 */
public class MusicHealthActivity extends BaseFragmentActivity implements
		OnClickListener {
	private HomeViewPager musicViewPager;
	private MusicHealthAdapter adapter;
	private final static int TYPE_FIVEMUSIC = 0;
	private final static int TYPE_CONDITIONMUSIC = 1;
	private final static int TYPE_ORGINALMUSIC = 2;
	private final static int TYPE_MAKEMUSIC = 3;
	private final static int TYPE_PRESCREPTIONMUSIC = 4;
	private RadioButton btn_five_music, btn_condition_music,
			btn_original_music, btn_make_music, btn_prescription_music;
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		adapter = new MusicHealthAdapter(getSupportFragmentManager());
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_musichealth);
		musicViewPager = (HomeViewPager) findViewById(R.id.music_view_pager);
		btn_five_music = (RadioButton) findViewById(R.id.btn_five_music);
		btn_condition_music = (RadioButton) findViewById(R.id.btn_condition_music);
		btn_original_music = (RadioButton) findViewById(R.id.btn_original_music);
		btn_make_music = (RadioButton) findViewById(R.id.btn_make_music);
		btn_prescription_music = (RadioButton) findViewById(R.id.btn_prescription_music);

	}

	@Override
	protected void initWidgetAciotns() {
		// TODO Auto-generated method stub
		musicViewPager.setAdapter(adapter);
		musicViewPager.setNotTouchScoll(true);
		musicViewPager.setOffscreenPageLimit(5);
		musicViewPager.setCurrentItem(TYPE_ORGINALMUSIC, false);
		btn_five_music.setOnClickListener(this);
		btn_condition_music.setOnClickListener(this);
		btn_original_music.setOnClickListener(this);
		btn_make_music.setOnClickListener(this);
		btn_prescription_music.setOnClickListener(this);
		btn_original_music.setChecked(true);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_five_music:
			musicViewPager.setCurrentItem(TYPE_FIVEMUSIC, false);

			break;
		case R.id.btn_condition_music:
			musicViewPager.setCurrentItem(TYPE_CONDITIONMUSIC, false);

			break;
		case R.id.btn_original_music:
			musicViewPager.setCurrentItem(TYPE_ORGINALMUSIC, false);

			break;
		case R.id.btn_make_music:
			musicViewPager.setCurrentItem(TYPE_MAKEMUSIC, false);

			break;
		case R.id.btn_prescription_music:
			musicViewPager.setCurrentItem(TYPE_PRESCREPTIONMUSIC, false);

			break;

		default:
			break;
		}

	}
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	//	if()
		return super.onKeyDown(keyCode, event);
	}
}
