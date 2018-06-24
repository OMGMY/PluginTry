package plugin.cloud.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hanzhang on 2017/12/12.
 */

public class ToastUtils {

    public static void toast(String des, Context context){
        Toast.makeText(context,des,Toast.LENGTH_LONG).show();
    }
}
