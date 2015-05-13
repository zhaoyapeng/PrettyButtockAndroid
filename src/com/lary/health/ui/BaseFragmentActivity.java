package com.lary.health.ui;

import com.lary.health.R;
import com.lary.health.service.event.IEvent;
import com.lary.health.ui.widget.LoadingDialog;

import de.greenrobot.event.EventBus;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:44:53
 * @Email zhaoyp@witmob.com
 * @Description 所有Activity基类
 */
public abstract class BaseFragmentActivity extends FragmentActivity{
	protected LoadingDialog loadingDialog;
     @Override
    protected void onCreate(Bundle arg0) {
    	super.onCreate(arg0);
    	initBaseData();
    	initData();
    	initView();
    	initWidgetAciotns();
    }
    
     private void initBaseData(){
    	 initDialog();
    	 EventBus.getDefault().register(this);
     }
     
     protected abstract void initData();
     
     protected abstract void initView();
     
     protected abstract void initWidgetAciotns();
     
     
     public void onEvent(IEvent event){
    	 
     }
     
 	protected void initDialog() {
		if (loadingDialog == null) {
			loadingDialog = new LoadingDialog(this, R.style.my_dialog);
			loadingDialog.setCancelable(true);
			loadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						if (loadingDialog != null)
							loadingDialog.cancel();
					}
					return true;
				}
			});
			loadingDialog.setCanceledOnTouchOutside(false);
		}
	}

	/**
	 * 显示loading框
	 */
	protected void showLoadingDialog() {
		if (loadingDialog != null) {
			if (!loadingDialog.isShowing()) {
				loadingDialog.show();
			}
		}

	}

	/**
	 * 隐藏loading框
	 */
	protected void hideLoadingDialog() {
		if (loadingDialog != null) {
			if (loadingDialog.isShowing()) {
				loadingDialog.dismiss();
				;
			}
		}
	}
     
     @Override
    protected void onDestroy() {
    	super.onDestroy();
    	EventBus.getDefault().unregister(this);
    }
     
}
