package plugin.cloud.iinterface;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by hanzhang on 2017/12/12.
 */

public interface IProxyActivityInferce {



    public void Plugin_onCreate(Bundle savedInstanceState) ;

    public void Plugin_onRestoreInstanceState(Bundle savedInstanceState);

    public void Plugin_onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) ;

    public void Plugin_onPostCreate(Bundle savedInstanceState);

    public void Plugin_onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState);

    public void Plugin_onStart();

    public void Plugin_onRestart();

    public void Plugin_onStateNotSaved();

    public void Plugin_onResume();

    public void Plugin_onPostResume();

    public void Plugin_onNewIntent(Intent intent);

    public void Plugin_onSaveInstanceState(Bundle outState);

    public void Plugin_onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState);

    public void Plugin_onPause();

    public void Plugin_onDestroy();
}
