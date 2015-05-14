package com.lary.health.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;

import com.lary.health.R;
import com.lary.health.ui.util.IsNetWorkConnectUtil;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-10下午5:22:48
 * @Email zhaoyp@witmob.com
 * @Description webView Activity
 */
@SuppressLint("NewApi")
public class DetailWebActivity extends BaseFragmentActivity {

	private WebView webView;
	private WebSettings settings;
	private WebChromeClient chromeClient;
	private WebViewClient client;
	private String url;

	@Override
	protected void initData() {
	
		url = getIntent().getStringExtra("webUrl");
		Log.e("tag", "url="+url);
	}

	@Override
	protected void initView() {
		setContentView(R.layout.activity_webview);
		webView = (WebView) findViewById(R.id.webView);
		settings = webView.getSettings();

	}

	@Override
	protected void initWidgetAciotns() {
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
		if (!IsNetWorkConnectUtil.isNetworkConnected(this)) {// 是否是无网状态
			settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
		}

		chromeClient = new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 100) {
					hideLoadingDialog();
				} else if (newProgress == 0) {
					showLoadingDialog();
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
				showLoadingDialog();

			}
		};

		webView.setWebChromeClient(chromeClient);
		webView.setWebViewClient(client);
		webView.loadUrl(url);
	}

}
