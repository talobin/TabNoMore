package com.haivo.hailibrary;

import android.app.Application;
import dagger.ObjectGraph;

/**
 * Created by dannyroa on 9/17/14.
 */
public abstract class HaiApplication extends Application {

    private static HaiApplication sInstance;
    private ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        sInstance = this;
        HaiLib.initialize(this);
        initDaggerObjectGraph();
    }

    //<editor-fold desc="Abstract Methods">

    protected abstract Object createDaggerModule();

    /**
     * @return the app with product name (e.g Ink Cards, Sesame Gifts). Used on Android Launcher and
     * Homescreen
     */
    public abstract String getAppProductName();

    /**
     * @return the app name (e.g Ink, Sesame ).
     */
    public abstract String getAppName();



    /**
     * @return the name of the preference file.
     */
    public abstract String getPrefName();


    /**
     * @return the app icon resource id.
     */
    public abstract int getAppIcon();

    /**
     * @return the push icon resource id.
     */
    public abstract int getPushIcon();
    //</editor-fold>

    /**
     * Injects object into the dagger graph.
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public static HaiApplication getInstance() {
        return sInstance;
    }

    private void initDaggerObjectGraph() {
        objectGraph = ObjectGraph.create(createDaggerModule());
        objectGraph.injectStatics();
    }
}
