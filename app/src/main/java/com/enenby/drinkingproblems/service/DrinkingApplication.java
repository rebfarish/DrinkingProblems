package com.enenby.drinkingproblems.service;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/**
 * The type Drinking application.
 */
public class DrinkingApplication extends Application {

  private static DrinkingApplication instance = null;

  private GoogleSignInClient client;
  private GoogleSignInAccount account;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    Stetho.initializeWithDefaults(this);

    GoogleSignInOptions options = new GoogleSignInOptions.Builder(
        GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestId()
        .build();
    client = GoogleSignIn.getClient(this,options);

  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static DrinkingApplication getInstance() {
    return instance;
  }

  /**
   * Gets client.
   *
   * @return the client
   */
  public GoogleSignInClient getClient() {
    return client;
  }

  /**
   * Sets client.
   *
   * @param client the client
   */
  public void setClient(GoogleSignInClient client) {
    this.client = client;
  }

  /**
   * Gets account.
   *
   * @return the account
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  /**
   * Sets account.
   *
   * @param account the account
   */
  public void setAccount(GoogleSignInAccount account) {
    this.account = account;
  }
}
