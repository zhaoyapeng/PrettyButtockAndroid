package com.lary.health.ui;

import com.lary.health.service.event.IEvent;

import de.greenrobot.event.EventBus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:44:53
 * @Email zhaoyp@witmob.com
 * @Description 所有Activity基类
 */
public abstract class BaseFragmentActivity extends FragmentActivity{
     @Override
    protected void onCreate(Bundle arg0) {
    	super.onCreate(arg0);
    	initBaseData();
    	initData();
    	initView();
    	initWidgetAciotns();
    }
    
     private void initBaseData(){
    	 EventBus.getDefault().register(this);
     }
     
     protected abstract void initData();
     
     protected abstract void initView();
     
     protected abstract void initWidgetAciotns();
     
     
     public void onEvent(IEvent event){
    	 
     }
     
     @Override
    protected void onDestroy() {
    	super.onDestroy();
    	EventBus.getDefault().unregister(this);
    }
     
}
