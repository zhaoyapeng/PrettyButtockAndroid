package com.lary.health.ui;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.lary.health.R;
import com.lary.health.ui.adaper.GymnasticsAdapter;
import com.lary.health.ui.widget.HomeViewPager;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-8上午12:03:16
 * @Email zhaoyp@witmob.com
 * @Description 垫上美操
 */
public class GymnasticsBeautifulActivity extends BaseFragmentActivity {

	private HomeViewPager viewPager;
	private GymnasticsAdapter adapter;
	private RadioButton originalBtn, gymnasticsBtn;

	@Override
	protected void initData() {
		adapter = new GymnasticsAdapter(getSupportFragmentManager());
	}

	@Override
	protected void initView() {
		setContentView(R.layout.activity_gymnastics_beau);
		viewPager = (HomeViewPager) findViewById(R.id.viewpager);
		originalBtn = (RadioButton) findViewById(R.id.btn_original);
		gymnasticsBtn = (RadioButton) findViewById(R.id.btn_gymnastics);
	}

	@Override
	protected void initWidgetAciotns() {
		viewPager.setNotTouchScoll(true);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(adapter);
		gymnasticsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				gymnasticsBtn.setChecked(true);
				originalBtn.setChecked(false);
				viewPager.setCurrentItem(0, false);
			}
		});

		originalBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				gymnasticsBtn.setChecked(false);
				originalBtn.setChecked(true);
				viewPager.setCurrentItem(1, false);
			}
		});
	}

}
