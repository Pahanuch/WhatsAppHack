package com.whapprapphck.other;

import android.app.Application;

import com.tapjoy.TJConnectListener;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyConnectFlag;
import com.whapprapphck.other.di.AppComponent;
import com.whapprapphck.other.di.DaggerAppComponent;

import java.util.Hashtable;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
        connectToTapjoy();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }

    private void connectToTapjoy(){

        Hashtable<String, Object> connectFlags = new Hashtable<>();
        connectFlags.put(TapjoyConnectFlag.ENABLE_LOGGING, true);      // remember to turn this off for your production builds!
        connectFlags.put(TapjoyConnectFlag.USER_ID, Const.TAP_USER_ID);

        Tapjoy.connect(getApplicationContext(), Const.TAP_JOY_SDK_KEY, connectFlags, new TJConnectListener() {
            @Override
            public void onConnectSuccess() {
                Tapjoy.setDebugEnabled(true); //Do not set this for apps released to a store!
            }

            @Override
            public void onConnectFailure() {
            }
        });
    }


}
