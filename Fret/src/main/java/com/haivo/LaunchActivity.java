package com.haivo;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by hvo on 2/22/15.
 */
public class LaunchActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GuitarTunerActivity.launch(this);
        finish();
    }
}
