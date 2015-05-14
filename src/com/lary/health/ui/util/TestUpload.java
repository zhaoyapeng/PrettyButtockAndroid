package com.lary.health.ui.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

/**
 * @author zhaoyapeng
 * @version create time:2014-4-21下午4:52:33
 * @Email zhaoyapeng@witmob.com
 * @Description 上传图片
 */
@SuppressLint("HandlerLeak")
public class TestUpload {
	private final static String TAG = "UploadImgsUtil";

	private static final int SET_CONNECTION_TIMEOUT = 10000;
	private static final int SET_SOCKET_TIMEOUT = 20000;

	private static final int SUCCESS = 10;
	private static final int FAIL = 20;

	private Context context;

	public TestUpload(Context context) {
		super();
		this.context = context;
	}

	// 上传文件
	public void excute(final String actionUrl, final String uploadFile, final UploadListener listener) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case SUCCESS:
					if (listener != null) {
						listener.Success(msg.obj.toString());
					}
					break;
				case FAIL:
					if (listener != null) {
						listener.Fail(msg.obj.toString());
					}
					break;
				default:
					break;
				}
			}
		};
		new Thread(new Runnable() {

			@Override
			public void run() {
				Log.e("tag", "jsonStr2=========");
				HttpURLConnection con = null;
				InputStream is = null;
				DataOutputStream ds = null;
				FileInputStream fStream = null;
				Log.e(TAG, "uploadUrl = " + actionUrl + ";uploadFile=" + uploadFile);
				String end = "\r\n";
				String twoHyphens = "--";
				String boundary = "*****";
				String fileName = "";
				if (uploadFile != null && uploadFile.lastIndexOf(File.separator) > -1)
					fileName = uploadFile.substring(uploadFile.lastIndexOf(File.separator) + 1);
				try {
					URL url = new URL(actionUrl);
					con = getNewHttpURLConnection(url, context);

					con.setDoInput(true);
					con.setDoOutput(true);
					con.setUseCaches(false);
					con.setConnectTimeout(SET_CONNECTION_TIMEOUT);
					con.setReadTimeout(SET_SOCKET_TIMEOUT);
					con.setRequestMethod("POST");
					con.setRequestProperty("Connection", "Keep-Alive");// 设置维持长连接
					con.setRequestProperty("Charset", "UTF-8");// 设置字符集
					con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);// 设置文件类型
					ds = new DataOutputStream(con.getOutputStream());
					ds.writeBytes(twoHyphens + boundary + end);
					String reqDisposition = "";
					reqDisposition = "Content-Disposition: form-data;name=\"headphoto\";filename=\"" + fileName + "\""
							+ end;
					Log.e("tag", con.getRequestProperty("Content-Type"));
					Log.e("tag", "reqDisposition = " + reqDisposition);
					ds.writeBytes(reqDisposition);
					/** 新添加的 **/
					// ds.writeBytes("Content-Type: application/octet-stream\r\n");
					ds.writeBytes("Content-Type: " + fileName.substring(fileName.indexOf("."), fileName.length())
							+ "\r\n");
					ds.writeBytes(end);

					fStream = new FileInputStream(uploadFile);
					int bufferSize = 1024;
					byte[] buffer = new byte[bufferSize];

					int length = -1;
					while ((length = fStream.read(buffer)) != -1) {
						Log.e(TAG, "<<<<<<<<<<<<<<<<<<<<<" + buffer.toString());
						ds.write(buffer, 0, length);
					}
					ds.writeBytes(end);
					ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

					ds.flush();
					int res = con.getResponseCode();// 获取响应码 code=200 成功
					if (res != 200) {
						Message message = new Message();
						message.what = FAIL;
						message.obj = "文件上传失败";
						handler.sendMessage(message);
						return;
					}
					is = con.getInputStream();
					String jsonStr = convertStreamToJson(is);
					Log.e(TAG, res + "<<<<<<<<<<<@@@@<<<<<<<jsonStr  = " + jsonStr);
					if (TextUtils.isEmpty(jsonStr)) {
						Message message = new Message();
						message.what = FAIL;
						message.obj = "文件上传失败";
						handler.sendMessage(message);
						return;
					}
					// // 将返回数据转成Map集合，可根据实际情况转换
					// returnMap = new Gson().fromJson(jsonStr, new
					// TypeToken<Map<String, String>>() {
					// }.getType());
					// 解析失败
					// 解析成功
					Message message = new Message();
					message.what = SUCCESS;
					message.obj = jsonStr;
					handler.sendMessage(message);
				} catch (MalformedURLException e) {
					e.printStackTrace();
					Message message = new Message();
					message.what = FAIL;
					message.obj = "网络异常";
					handler.sendMessage(message);
					return;
				} catch (ProtocolException e) {
					e.printStackTrace();
					Message message = new Message();
					message.what = FAIL;
					message.obj = "网络连接失败";
					handler.sendMessage(message);
					return;
				} catch (IOException e) {
					e.printStackTrace();
					Message message = new Message();
					message.what = FAIL;
					message.obj = "文件上传失败";
					handler.sendMessage(message);
					return;
				} finally {
					try {
						if (is != null) {
							is.close();
						}
						if (fStream != null) {
							fStream.close();
						}
						if (ds != null) {
							ds.flush();
							ds.close();
						}
						if (con != null) {
							con.disconnect();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public interface UploadListener {
		public void Success(String jsonStr);

		public void Fail(String failInfo);
	}

	public static HttpURLConnection getNewHttpURLConnection(URL url, Context context) {
		HttpURLConnection connection = null;
		Cursor mCursor = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
			WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			Log.e("tag", "wifiManager.isWifiEnabled() = " + wifiManager.isWifiEnabled());
			if (!wifiManager.isWifiEnabled()) {
				// 获取当前正在使用的APN接入点
				Uri uri = Uri.parse("content://telephony/carriers/preferapn");
				mCursor = context.getContentResolver().query(uri, null, null, null, null);
				if (mCursor != null && mCursor.moveToFirst()) {
					// 游标移至第一条记录，当然也只有一条
					String proxyStr = mCursor.getString(mCursor.getColumnIndex("proxy"));
					if (proxyStr != null && proxyStr.trim().length() > 0) {
						Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyStr, 80));
						connection = (HttpURLConnection) url.openConnection(proxy);
					}
				}
			}
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				return (HttpURLConnection) url.openConnection();
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}
		} finally {
			if (mCursor != null) {
				mCursor.close();
			}
		}
	}

	public static String convertStreamToJson(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8 * 1024);
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			Log.e("convertStreamToString", "convertStreamToString error");
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}

		return sb.toString();
	}
}
