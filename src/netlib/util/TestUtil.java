package netlib.util;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by witmob on 15-1-5.
 */
public class TestUtil {

    // 这是用来做测试数据使用的。 获取raw下文件 String。
    public static String readTextFileFromRawResourceId(Context context, int resourceId) {
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(
                resourceId)));

        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                builder.append(line).append("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return builder.toString();
    }
}