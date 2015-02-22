package com.haivo;

import android.app.Application;
import com.noisepages.nettoyeur.R;
import dagger.ObjectGraph;

/**
 * Created by hvo on 2/22/15.
 */
public abstract class HaiApp extends Application {
    private static final String PREF_NAME = "TabNoMore";
    private static final String APP_NAME = "Un Decided";
    private static final int APP_ICON_RES_ID = R.drawable.ic_launcher;
    private static final int APP_PUSH_ICON_RES_ID = R.drawable.ic_launcher;

    private static HaiApp sInstance;
    private ObjectGraph objectGraph;

    /**
     * Injects object into the dagger graph.
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public static HaiApp getInstance() {
        return sInstance;
    }

    private void initDaggerObjectGraph() {
        objectGraph = ObjectGraph.create(createDaggerModule());
        objectGraph.injectStatics();
    }

    @Override public void onCreate() {
        super.onCreate();
        sInstance = this;
        initDaggerObjectGraph();
    }

    protected Object createDaggerModule() {
        return new HaiModule(this);
    }

    public String getAppName() {
        return APP_NAME;
    }

    public String getPrefName() {
        return PREF_NAME;
    }

    public int getAppIcon() {
        return APP_ICON_RES_ID;
    }

    public int getPushIcon() {
        return APP_PUSH_ICON_RES_ID;
    }
}
