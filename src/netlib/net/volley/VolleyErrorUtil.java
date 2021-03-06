package netlib.net.volley;


import com.android.volley.VolleyError;

/**
 * @author zhaoyapeng
 * @version create time:15-3-25下午2:27
 * @Email zhaoyp@witmob.com
 * @Description ${volley 网络请求 错误处理类}
 */
public class VolleyErrorUtil {
    public static String disposeError(VolleyError volleyError) {
        String error = volleyError.toString();
        if (error.contains("TimeoutError")) {
            return "您的网络状况不好哦，让手机飞一会吧";
        }else if(error.contains("NoConnectionError")){
            return "请检查网络";
        }else if(error.contains("NetworkError")){
            return "服务端异常";
        }
        return "服务端异常";
    }
}
