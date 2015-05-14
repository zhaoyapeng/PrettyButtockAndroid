package netlib.net.volley;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * @author zhaoyapeng
 * @version create time:${date}${time}
 * @Email zhaoyp@witmob.com
 * @Description ${volley实例类}
 */
public class VolleyUtil {
    private static RequestQueue requestQueue;
    private static ImageLoader imageLoader;

    public static RequestQueue getQueue(Context mContext) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        return requestQueue;
    }

//    public static ImageLoader getImageLoader(Context mContext) {
//        if (imageLoader == null) {
//            imageLoader = new ImageLoader(getQueue(mContext), BitmapCacheUtil.getImageCache());
//        }
//        return imageLoader;
//    }


}
