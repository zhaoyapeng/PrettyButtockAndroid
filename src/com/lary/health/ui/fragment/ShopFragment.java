
package com.lary.health.ui.fragment;

import com.lary.health.R;
import com.lary.health.service.event.LoadingEvent;
import com.lary.health.service.sharepreferences.HomeSharepreferences;
import com.lary.health.ui.util.IsNetWorkConnectUtil;

import de.greenrobot.event.EventBus;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-4上午11:36:07
 * @Email zhaoyp@witmob.com
 * @Description 商城 fragment
 */
@SuppressLint("NewApi")
public class ShopFragment extends BaseViewPagerFragment {
	private WebView webView;
	private WebSettings settings;
	private WebChromeClient chromeClient;
	private WebViewClient client;
	private String url;

	@Override
	protected void initData() {

	}

	@Override
	protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shop, container, false);
		webView = (WebView) view.findViewById(R.id.webView);
		settings = webView.getSettings();
		return view;
	}

	@Override
	protected void initWidgetActions() {
		if (Build.VERSION.SDK_INT < 14) {
			settings.setTextSize(WebSettings.TextSize.NORMAL);
		} else {
			settings.setTextZoom(100);
		}
		webView.setInitialScale(57);
		settings.setSupportZoom(true);// 缩放
		settings.setBuiltInZoomControls(false);// 内部缩放设置
		settings.setJavaScriptEnabled(true);// 启用
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);
		settings.setLoadsImagesAutomatically(true);
		settings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		if (!IsNetWorkConnectUtil.isNetworkConnected(mContext)) {// 是否是无网状态
			settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
		}

		chromeClient = new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 100) {
//					hideLoadingDialog();
					LoadingEvent event = new LoadingEvent();
					event.setShow(false);
					EventBus.getDefault().post(event);
				} else if (newProgress == 0) {
//					showLoadingDialog();
					LoadingEvent event = new LoadingEvent();
					event.setShow(true);
					EventBus.getDefault().post(event);
				}
				super.onProgressChanged(view, newProgress);
			}

		};
		client = new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.e("tag", "shouldOverrideUrlLoading");
				Log.e("tag", "url =" + url);
				// view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				Log.e("tag", "onPageStarted");
//				showLoadingDialog();
				LoadingEvent event = new LoadingEvent();
				event.setShow(true);
				EventBus.getDefault().post(event);

			}
		};

		webView.setWebChromeClient(chromeClient);
		webView.setWebViewClient(client);
	

	}

	@Override
	protected void refreshData() {
		if (!HomeSharepreferences.getUrl(mContext).equals("")) {
			webView.loadUrl(HomeSharepreferences.getUrl(mContext));
		}
	}

}
