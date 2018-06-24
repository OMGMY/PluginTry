package plugin.cloud.base;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import plugin.cloud.cloud.activity.PluginProxyActivity;

public class BasePluginActivity extends AbsPluginActivity {

    private static String TAG = "BasePluginActivity";

    private PluginProxyActivity mProxy;
    /**
     *
     * @param proxyPluginAct
     * provided this method to invoke by reflect . inject proxy
     */
    public void setProxy(PluginProxyActivity proxyPluginAct){
        mProxy = proxyPluginAct;
    }

    /**
     * set layout to proxyActivity
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        if (mProxy != null){
            Log.d(TAG, "setContentView:mProxy "+layoutResID);
            mProxy.setContentView(layoutResID);
        } else {
            Log.d(TAG, "setContentView: "+layoutResID);
            super.setContentView(layoutResID);
        }

    }


    public void startActivity(String className) {
        mProxy.startActivity(className);
    }

    @Override
    public View findViewById(int id) {
        if(mProxy ==null){
         return super.findViewById(id);
        }
        return mProxy.findViewById(id);
    }

    @Override
    public Resources getResources() {
        if(mProxy == null){
           return super.getResources();
        }
        return mProxy.getResources();
    }

    @Override
    public void finish() {
        if(mProxy==null){
            super.finish();
            return ;
        }
        mProxy.finish();

    }
}
