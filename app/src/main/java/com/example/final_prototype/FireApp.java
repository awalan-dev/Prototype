package com.example.final_prototype;

import android.app.Application;
import com.firebase.client.Firebase;

//import com.google.firebase.analytics.FirebaseAnalytics;

public class FireApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}

