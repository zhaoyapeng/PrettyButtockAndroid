package netlib.net.volley;

import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by mac on 15-2-3.
 */
public abstract  class VolleySuccessListener<T> implements Response.Listener<T>{

    @Override
    public void onResponse(T t) {

    }

    public abstract  void onResponse(T t,Request<?> request,boolean isCache) ;
}