package com.cloud.app;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.cloud.module.ResourceRegister;
import com.cloud.ResourcePath;
import com.cloud.UseProxy;

import plugin.cloud.utils.PluginManager;

//import com.cloud.module.PluginMainActivity;

/**
 * Created by hanzhang on 2017/10/14.
 */
@ResourcePath("/storage/emulated/0/app-debug.apk")
@UseProxy(true)
public class CloudApp extends Application {

    private static Context mApplication;
    private Resources oldResource;

    public static Context getPaoPaoContext() {
        return mApplication.getApplicationContext();
    }
    public  static Resources resources = null;

    public static Resources defineGetResources(){
        if(resources == null){
            resources = createResources(mApplication.getApplicationContext());
        }
        return resources;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        oldResource = super.getResources();
        PluginManager.getInstance().install(this,"/storage/emulated/0/app-debug.apk");
        try {
            //PluginMainActivity.useProxyMethod(new String[]{"32"});
        } catch (Exception e) {
            e.printStackTrace();
        }
      //  GlobalRegister.registerModules(this);
        mApplication = this;
        resources = createResources(this);
    }

    public static Resources createResources( Context context){
        final AssetManager assetManager = ResourceRegister.createAssetManager(context);
        Resources superRes = context.getResources();
        return new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
    }

    @Override
    public Resources getResources() {


        Resources newRes =  PluginManager.getInstance().getAppResource();
        return newRes == null?oldResource:newRes;
    }
}
