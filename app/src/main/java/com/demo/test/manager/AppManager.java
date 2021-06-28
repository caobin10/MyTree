package com.demo.test.manager;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2021/6/24.
 */

public class AppManager {

    public static void opendatabases(Context context,String dbName) throws Exception{
        InputStream is;
        OutputStream os;
        File dbDir = new File(context.getFilesDir().getParent()+ "/databases");
        if (!dbDir.exists()){
            dbDir.mkdir();
        }
        File outDbFile;
        outDbFile = new File(dbDir, dbName);
        if(!outDbFile.exists()){
            os = new FileOutputStream(outDbFile);
            byte[] buffer = new byte[1024];
            int length;
            is = context.getAssets().open(dbName);
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
            is.close();
            os.close();
        }
    }
}
