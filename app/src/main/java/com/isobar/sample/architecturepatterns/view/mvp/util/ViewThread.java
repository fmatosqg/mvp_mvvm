package com.isobar.sample.architecturepatterns.view.mvp.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by fabio.goncalves on 19/01/2017.
 */

public class ViewThread extends Thread {

    private final Runnable runnable;

    public ViewThread(Runnable runnable) {
        super(runnable);
        this.runnable = runnable;
    }

    @Override
    public void run() {

        if (isUIThread()) {
            super.run();
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(runnable);
        }
    }

    public static boolean isUIThread() {
        return Looper.getMainLooper().isCurrentThread();
    }
}
