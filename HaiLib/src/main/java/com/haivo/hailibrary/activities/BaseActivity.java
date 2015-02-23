package com.haivo.hailibrary.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.haivo.hailibrary.HaiLib;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chris on 7/5/14.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Inject protected Bus mBus;
    @Inject protected Picasso mPicasso;

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HaiLib.getHaiApplication().inject(this);
    }

    @Override protected void onResume() {
        super.onResume();
        mBus.register(this);
    }

    @Override protected void onPause() {
        super.onPause();
        mBus.unregister(this);
    }

    @Override protected void onDestroy() {
        if (!mSubscriptions.isUnsubscribed()) {
            mSubscriptions.unsubscribe();
        }
        super.onDestroy();
    }

    protected void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    protected void setActionBarTitle(String title) {
        if (getActionBar() != null) {
            getActionBar().setTitle(title);
        }
    }

    protected String getActionBarTitle() {
        return (getActionBar() == null) ? "" : getActionBar().getTitle().toString();
    }
}