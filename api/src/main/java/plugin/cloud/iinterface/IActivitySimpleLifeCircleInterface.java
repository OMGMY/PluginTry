package plugin.cloud.iinterface;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by hanzhang on 2017/12/14.
 */

public interface IActivitySimpleLifeCircleInterface {

    public void Plugin_onCreate(Bundle savedInstanceState) ;

    public void Plugin_onStart();

    public void Plugin_onRestart();

    public void Plugin_onResume();

    public void Plugin_onSaveInstanceState(Bundle outState);

    public void Plugin_onPause();

    public void Plugin_onDestroy();

    public void Plugin_onRestoreInstanceState(Bundle savedInstanceState);

    public void Plugin_onNewIntent(Intent intent);
}
