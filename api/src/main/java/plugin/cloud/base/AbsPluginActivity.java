package plugin.cloud.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import plugin.cloud.iinterface.IProxyActivityInferce;

/**
 * Created by hanzhang on 2017/12/12.
 */

public class AbsPluginActivity extends Activity implements IProxyActivityInferce {

    public static String TAG = "AbsPluginActivity";

    @Override
    public void Plugin_onCreate(Bundle savedInstanceState) {
        Log.i("AbsPluginActivity","Plugin_onCreate");
    }

    @Override
    public void Plugin_onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i("AbsPluginActivity","Plugin_onRestoreInstanceState");
    }

    @Override
    public void Plugin_onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.i("AbsPluginActivity","Plugin_onRestoreInstanceState");
    }

    @Override
    public void Plugin_onPostCreate(Bundle savedInstanceState) {
        Log.i("AbsPluginActivity","Plugin_onPostCreate");
    }

    @Override
    public void Plugin_onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.i("AbsPluginActivity","Plugin_onPostCreate");
    }

    @Override
    public void Plugin_onStart() {
        Log.i("AbsPluginActivity","Plugin_onStart");
    }

    @Override
    public void Plugin_onRestart() {
        Log.i("AbsPluginActivity","Plugin_onRestart");
    }

    @Override
    public void Plugin_onStateNotSaved() {
        Log.i("AbsPluginActivity","Plugin_onStateNotSaved");
    }

    @Override
    public void Plugin_onResume() {
        Log.i("AbsPluginActivity","Plugin_onResume");
    }

    @Override
    public void Plugin_onPostResume() {
        Log.i("AbsPluginActivity","Plugin_onPostResume");
    }

    @Override
    public void Plugin_onNewIntent(Intent intent) {
        Log.i("AbsPluginActivity","Plugin_onNewIntent");
    }

    @Override
    public void Plugin_onSaveInstanceState(Bundle outState) {
        Log.i("AbsPluginActivity","Plugin_onSaveInstanceState");
    }

    @Override
    public void Plugin_onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.i("AbsPluginActivity","Plugin_onSaveInstanceState");
    }

    @Override
    public void Plugin_onPause() {
        Log.i("AbsPluginActivity","Plugin_onPause");
    }

    @Override
    public void Plugin_onDestroy() {
        Log.i("AbsPluginActivity","Plugin_onDestroy");
    }
}
