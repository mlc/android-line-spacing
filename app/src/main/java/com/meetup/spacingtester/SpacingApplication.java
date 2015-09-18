package com.meetup.spacingtester;

import android.app.Application;

import com.scopely.fontain.Fontain;

public class SpacingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fontain.init(this);
    }
}
