package com.ant.wgz.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class msgService2 extends Service {
    public msgService2() {
    }

    private int MsgLength;

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000*60*1);
                        //当前消息总数
                        MsgLength = 1;
                        int mCurrentMsgLength = getCurrentMsgSize();
                        if (mCurrentMsgLength!=MsgLength){

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        }).start();


    }

    private int getCurrentMsgSize() {
        return 5;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
