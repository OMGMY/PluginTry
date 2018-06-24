package com.cloud.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.cloud.module.PluginMainActivity;
import com.cloud.plugin.R;

import com.cloud.Paramters;
import com.cloud.UseProxyMethod;

import java.util.List;

import plugin.cloud.utils.PluginManager;

public class MainActivity extends AppCompatActivity {

   /* @BindViewId(R.id.toolbar)
    Toolbar mToolbar;
    @BindViewId(R.id.content_main)
    RelativeLayout mContentMain;
    @BindViewId(R.id.fab)
    FloatingActionButton mFab;

    @Hello("cloud")
    public String name;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* CloudApp.defineGetResources().getIdentifier("ppabcd","drawable","com.example.hanzhang.myapplication");
        int layoutId ;//=  CloudApp.defineGetResources().getIdentifier("item","layout","com.example.hanzhang.myapplication");// CloudApp.defineGetResources().getLayout(0x7f04001c);
       // final Class<?> assetManagerClazz;
        View view =null;
        view =LayoutInflater.from(this).inflate(CloudApp.defineGetResources().getLayout(0x7f040022),null) ;//(View) inflate.invoke(null,CloudApp.defineGetResources().getLayout(0x7f040022),null,false);
        layoutId =  getResources().getIdentifier("activity_main","layout","com.wangzai.imitatebutterknife");// CloudApp.defineGetResources().getLayout(0x7f04001c);
        setContentView(view);//
       //GenerateCode.bind(this);
        view.findViewById(0x7f0b0077).setBackground(CloudApp.defineGetResources().getDrawable(0x7f02005d));*/
        setContentView(R.layout.activity_main);
        ImageView button = (ImageView) findViewById(R.id.imageView);
        button.setBackground(PluginManager.getInstance().getResources().getDrawable(0x7f030001));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PluginManager.getInstance().startPlugin(MainActivity.this);
            }
        });

//        useProxyMethod(new ArrayList<String>());
//        show();
        // setSupportActionBar(mToolbar);
    }


    @UseProxyMethod("useProxyMethod")
    @Paramters({"java.util.list","cloud,doujinhai"})
    void useProxyMethod(List<String>list) {
        try {
            String[] value = PluginMainActivity.useProxyMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("not proxy");
    }
    @UseProxyMethod("show")
    void show(){
        try {
            PluginMainActivity.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("my show");
    }


//    @OnClick({R.id.toolbar, R.id.content_main, R.id.fab})
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.toolbar:
//                break;
//            case R.id.content_main:
//                break;
//            case R.id.fab:
//                System.out.println(name);
//                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                break;
//        }
//    }
}
