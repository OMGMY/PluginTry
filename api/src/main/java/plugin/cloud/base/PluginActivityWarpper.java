package plugin.cloud.base;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import plugin.cloud.cloud.activity.PluginProxyActivity;
import plugin.cloud.iinterface.IActivitySimpleLifeCircleInterface;
import plugin.cloud.iinterface.IProxyActivityInferce;

/**
 * Created by hanzhang on 2017/12/14.
 */

public class PluginActivityWarpper implements IProxyActivityInferce{

   protected IActivitySimpleLifeCircleInterface targetActivity;

    PluginProxyActivity pluginProxyActivity;

    /**
     *
     * @param proxyPluginAct
     * provided this method to invoke by reflect . inject proxy
     */
    public void setProxy(PluginProxyActivity proxyPluginAct){
        pluginProxyActivity = proxyPluginAct;
    }

    /**
     *
     */
    public void setContentView(int layoutResID) {

        if (pluginProxyActivity != null){
            pluginProxyActivity.setContentView(layoutResID);
        }

    }
    @Override
    public void Plugin_onCreate(Bundle savedInstanceState) {
            targetActivity.Plugin_onCreate(savedInstanceState);
    }

    @Override
    public void Plugin_onRestoreInstanceState(Bundle savedInstanceState) {
            targetActivity.Plugin_onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void Plugin_onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {

    }

    @Override
    public void Plugin_onPostCreate(Bundle savedInstanceState) {

    }

    @Override
    public void Plugin_onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {

    }

    @Override
    public void Plugin_onStart() {

    }

    @Override
    public void Plugin_onRestart() {

    }

    @Override
    public void Plugin_onStateNotSaved() {

    }

    @Override
    public void Plugin_onResume() {

    }

    @Override
    public void Plugin_onPostResume() {

    }

    @Override
    public void Plugin_onNewIntent(Intent intent) {

    }

    @Override
    public void Plugin_onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void Plugin_onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

    }

    @Override
    public void Plugin_onPause() {

    }

    @Override
    public void Plugin_onDestroy() {

    }

    public void startActivity(String className) {
        pluginProxyActivity.startActivity(className);
    }


    public View findViewById(int id) {
        return pluginProxyActivity.findViewById(id);
    }


    public Resources getResources() {
        return pluginProxyActivity.getResources();
    }

    public void finish(){
        pluginProxyActivity.finish();
    }
}
