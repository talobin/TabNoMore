package com.haivo;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by hvo on 2/22/15.
 */
@Module(
    includes = {
    },
    staticInjections = {
    },
    injects = {
    })
public class HaiModule {
    private HaiApp mHaiApp;

    public HaiModule(HaiApp app) {
        mHaiApp = app;
    }

    @Provides @Singleton Application provideApplication() {
        return mHaiApp;
    }
}