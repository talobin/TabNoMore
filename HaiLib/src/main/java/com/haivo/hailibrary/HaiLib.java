package com.haivo.hailibrary;

/**
 * Created by dannyroa on 9/17/14.
 */
public class HaiLib {

    private static HaiApplication haiApplication;

    public static void initialize(HaiApplication app) {
        haiApplication = app;
    }

    public static HaiApplication getHaiApplication() {
        return haiApplication;
    }

}
