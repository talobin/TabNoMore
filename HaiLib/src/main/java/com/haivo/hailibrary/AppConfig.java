package com.haivo.hailibrary;

import retrofit.RestAdapter;

/**
 * Created by dannyroa on 7/3/14.
 */
public class AppConfig {
    public final static String API_BASE_URL = "https://snapi.sincerely.com/";
    public final static RestAdapter.LogLevel REST_ADAPTER_LOG_LEVEL = RestAdapter.LogLevel.NONE;
    public static final boolean DEBUG = false;
}
