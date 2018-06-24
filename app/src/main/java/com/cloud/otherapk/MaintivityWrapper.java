package com.cloud.otherapk;

import android.content.Intent;
import android.os.Bundle;

import plugin.cloud.base.PluginActivityWarpper;

/**
 * Created by hanzhang on 2017/12/14.
 */

public class MaintivityWrapper extends PluginActivityWarpper {
    @Override
    public void Plugin_onCreate(Bundle savedInstanceState) {
        super.Plugin_onCreate(savedInstanceState);
        targetActivity.Plugin_onCreate(savedInstanceState);
    }

    @Override
    public void Plugin_onNewIntent(Intent intent) {
        super.Plugin_onNewIntent(intent);
        targetActivity.Plugin_onNewIntent(intent);
    }
}
