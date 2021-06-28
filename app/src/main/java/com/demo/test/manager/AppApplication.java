package com.demo.test.manager;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2021/6/23.
 */

public class AppApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        // 上下文
        mContext = getApplicationContext();
        try {
            AppManager.opendatabases(mContext,AppConfig.DB_BASIC_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
