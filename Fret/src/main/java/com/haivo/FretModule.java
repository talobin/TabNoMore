package com.haivo;

import android.app.Application;
import android.content.Context;
import com.haivo.hailibrary.HaiModule;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by hvo on 2/22/15.
 */
@Module(
    includes = {
        HaiModule.class,
    },
    staticInjections = {
    },
    injects = {
        GuitarTunerActivity.class
    })
public class FretModule {
    private FretApp mFretApp;

    public FretModule(FretApp app) {
        mFretApp = app;
    }

    @Provides @Singleton Application provideApplication() {
        return mFretApp;
    }
}
