package com.haivo.hailibrary.data;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import com.haivo.hailibrary.AppConfig;
import com.squareup.otto.Bus;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import java.util.Date;
import javax.inject.Singleton;

/**
 */

@Module(complete = false,
        library = true,
        staticInjections = {
        })
public class DataModule {

    @Provides @Singleton Bus provideBus() {
        return new MainBus();
    }

    @Provides @Singleton Picasso providePicasso(Application application) {
        //We currently using the size of cache enough for 8 images
        //If causes OOM error. Will have to decrease
        int memClass = ((ActivityManager) application.getApplicationContext()
                                                     .getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int cacheSizeBytes = (1024 * 1024 * memClass / 12) * 2;
        final Picasso p =
            new Picasso.Builder(application.getApplicationContext()).memoryCache(new LruCache(
                cacheSizeBytes)).build();
        p.setIndicatorsEnabled(AppConfig.DEBUG);
        return p;
    }
}
