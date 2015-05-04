package com.lary.health.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:21:37
 * @Email zhaoyp@witmob.com
 * @Description 所有Fragment基类
 */
public abstract class BaseFragment extends Fragment{
	private Context mContext;
     @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	mContext = getActivity();
    }
     
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	initData();
    	View view = initViews(inflater, container, savedInstanceState);
    	initWidgetActions();
    	return view;
    }
     
     protected abstract void initData();
     
     protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
     
     protected abstract void initWidgetActions();
}
