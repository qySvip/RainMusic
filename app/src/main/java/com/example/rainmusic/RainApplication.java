package com.example.rainmusic;

import android.app.Application;

public class RainApplication extends Application {

    private String UUID;

    @Override
    public void onCreate() {
        super.onCreate();
        setUUID("123456789");
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
