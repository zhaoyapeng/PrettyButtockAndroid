package com.lary.health.welcome;

import com.lary.health.R;
import com.lary.health.ui.HomeActivity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;

public class StartActivity extends Activity {
	boolean isFirstIn = false;

	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;
	// 延迟3秒
	private static final long SPLASH_DELAY_MILLIS = 3000;
	private static int TIME = 1000;

	private static final String SHAREDPREFERENCES_NAME = "first_pref";
	private Animation animation;
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		view = View.inflate(this, R.layout.activity_start, null);
		setContentView(view);
		into();
	}

	// 进入主程序的方法
	private void into() {
		// 如果网络可用则判断是否第一次进入，如果是第一次则进入欢迎界面
		// 读取SharedPreferences中需要的数据
		// 使用SharedPreferences来记录程序的使用次数
		SharedPreferences preferences = getSharedPreferences(
				SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		// 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
		isFirstIn = preferences.getBoolean("isFirstIn", true);
		// 设置动画效果是alpha，在anim目录下的alpha.xml文件中定义动画效果
		animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
		// 给view设置动画效果
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			// 这里监听动画结束的动作，在动画结束的时候开启一个线程，这个线程中绑定一个Handler,并
			// 在这个Handler中调用goHome方法，而通过postDelayed方法使这个方法延迟500毫秒执行，达到
			// 达到持续显示第一屏500毫秒的效果
			@Override
			public void onAnimationEnd(Animation arg0) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent intent;
						// 如果第一次，则进入引导页WelcomeActivity
						if (isFirstIn) {
							intent = new Intent(StartActivity.this,
									GuideActivity.class);
						} else {
							intent = new Intent(StartActivity.this,
									HomeActivity.class);
						}
						startActivity(intent);
						// 设置Activity的切换效果
						overridePendingTransition(R.anim.in_from_right,
								R.anim.out_to_left);
						StartActivity.this.finish();
					}
				}, TIME);
			}
		});

	}
}
