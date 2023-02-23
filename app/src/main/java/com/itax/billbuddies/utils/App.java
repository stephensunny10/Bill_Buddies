package com.itax.billbuddies.utils;

import android.app.Application;
import android.content.Context;

import io.paperdb.Paper;

public class App extends Application {
    private static App singleton = null;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        init();
    }

    private void init(){
        Paper.init(this);
    }

    public Context getContext() {
        return singleton.getContext();
    }

    public static App getInstance() {
        if (singleton == null) {
            singleton = new App();
        }
        return singleton;
    }

//    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
//        ConnectivityReceiver.connectivityReceiverListener = listener;
//    }
}
