package com.haivo.hailibrary.data;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;

/**
 * Created by dannyroa on 7/3/14.
 */
public class MainBus extends Bus {

    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mMainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            });
        }
    }

}
