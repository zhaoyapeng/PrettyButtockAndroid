
package netlib.net.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import netlib.model.BannerModel;
import netlib.model.BaseModel;

/**
 * @author zhaoyapeng
 * @version create time:15/1/21_下午4:21
 * @Description 重写关于volley get请求方法
 */
public class VolleyGetRequest<T extends BaseModel> extends Request<T> {
	private static final int DEFAULT_NET_TYPE = 0;

	private final Response.Listener<T> mListener;
	private Class<T> modelClass;
	private Context mContext;

	/**
	 * 重新定义缓存key
	 * 
	 * @return
	 */
	@Override
	public String getCacheKey() {
		String params = "";
		try {
			params = getParams().toString();
		} catch (Exception e) {

		}
		return getUrl() + params;
	}

	/**
	 * 
	 * null 正常逻辑 可以重写此方法，返回json数据 TODO
	 * 
	 * @return
	 */
	public String getDemoRespose() {
		return null;
	}

	public VolleyGetRequest(String url, Class<T> modelClass, Response.Listener<T> listener,
			Response.ErrorListener errorListener, Context mContext) {
		super(Method.GET, url, errorListener);
		mListener = listener;
		this.modelClass = modelClass;
		this.mContext = mContext;
	}

	private Response<T> responseWrapper;

	/**
	 * 解析 response
	 * 
	 * @param response
	 * @return
	 */
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		String parsed;

		try {
			parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			Log.e("tag", "网络请求数据1=" + parsed);
			responseWrapper = Response.success(
					new Gson().fromJson(
							parsed.replaceAll("\\\\", "").substring(1, parsed.replaceAll("\\\\", "").length() - 1),
							modelClass), VolleyHttpHeaderParser.volleyParseCacheHeaders(response));
			return responseWrapper;
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}

	}

	@Override
	protected void deliverResponse(T response) {
		((BaseModel) response).cacheKey = this.getCacheKey();
		((BaseModel) response).isCache = responseWrapper.intermediate;
		mListener.onResponse(response);
	}
}
