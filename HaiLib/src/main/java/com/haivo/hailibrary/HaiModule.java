package com.haivo.hailibrary;

import com.haivo.hailibrary.data.DataModule;
import dagger.Module;

/**
 * Created by chris on 10/1/14.
 */
@Module(includes = { DataModule.class },
        complete = false,
        library = true,
        staticInjections = {
        },
        injects = {

        })
public final class HaiModule {
}
