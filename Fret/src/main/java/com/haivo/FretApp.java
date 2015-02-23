package com.haivo;

import com.haivo.hailibrary.HaiApplication;
import com.haivo.hailibrary.HaiLib;
import dagger.ObjectGraph;

/**
 * Created by hvo on 2/22/15.
 */
public class FretApp extends HaiApplication {
    private static final String PREF_NAME = "TabNoMore";
    private static final String APP_NAME = "Un Decided";
    private static final int APP_ICON_RES_ID = R.drawable.ic_launcher;
    private static final int APP_PUSH_ICON_RES_ID = R.drawable.ic_launcher;

    @Override
    protected Object createDaggerModule() {
        return new FretModule(this);
    }

    @Override public String getAppProductName() {
        return APP_NAME;
    }

    @Override
    public String getAppName() {
        return APP_NAME;
    }

    @Override
    public String getPrefName() {
        return PREF_NAME;
    }

    @Override
    public int getAppIcon() {
        return APP_ICON_RES_ID;
    }

    @Override
    public int getPushIcon() {
        return APP_PUSH_ICON_RES_ID;
    }
}
