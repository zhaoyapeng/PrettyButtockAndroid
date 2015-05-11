package com.lary.health.ui.fragment;

import com.lary.health.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 
 * @author du 五行音乐
 */
public class FiveMusicFragment extends BaseFragment {
	private int index;

	public static FiveMusicFragment getInstance(int index) {
		FiveMusicFragment q = new FiveMusicFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("index", index);
		q.setArguments(bundle);
		return q;
	}

	@Override
	protected void initData() {
		index = getArguments().getInt("index");
		Toast.makeText(mContext, index + "", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_five_music, container, false);
		return view;
	}

	@Override
	protected void initWidgetActions() {
		// TODO Auto-generated method stub

	}

}
