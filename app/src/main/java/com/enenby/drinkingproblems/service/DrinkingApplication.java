package com.enenby.drinkingproblems.service;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class DrinkingApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
