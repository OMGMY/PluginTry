package com.cloud.otherapk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.cloud.plugin.R;

import plugin.cloud.cloud.activity.PluginProxyActivity;
import plugin.cloud.iinterface.IActivitySimpleLifeCircleInterface;

/**
 *
 * Created by hanzhang on 2017/12/14.
 * 针对已有基类的页面就得单独重写了
 */

public class MyActivity extends Activity implements IActivitySimpleLifeCircleInterface{

    private PluginProxyActivity  activity = null;

    private Activity thisActivity = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Plugin_onCreate(savedInstanceState);
    }


    @Override
    public Resources getResources() {
        if(activity != null){
            return activity.getResources();
        }
        return super.getResources();
    }
    @Override
    public View findViewById(int id) {
        if(activity != null){
            return activity.findViewById(id);
        }
        return super.findViewById(id);
    }

    @Override
    public void Plugin_onCreate(Bundle savedInstanceState) {
        if(activity != null){
            thisActivity = activity;
            activity.setContentView(R.layout.activity_main);
        }else {
            thisActivity = this;
            setContentView(R.layout.activity_main);
        }
    }

    @Override
    public void Plugin_onStart() {

    }

    @Override
    public void Plugin_onRestart() {

    }

    @Override
    public void Plugin_onResume() {

    }

    @Override
    public void Plugin_onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void Plugin_onPause() {

    }

    @Override
    public void Plugin_onDestroy() {

    }

    @Override
    public void Plugin_onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void Plugin_onNewIntent(Intent intent) {

    }
}
