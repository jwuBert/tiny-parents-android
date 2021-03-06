package com.cloudiya.app.tiny_parents_android.activity;

/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/6/15.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.cloudiya.app.tiny_parents_android.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Purpose: MainActivity, use to check app status.
 */

public class MainActivity extends Activity{

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Initial Realm storage

    // Create global configuration and initialize ImageLoader with this config
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
    ImageLoader.getInstance().init(config);

    // Check whether user already login in or not
    SharedPreferences sharedPref = this.getSharedPreferences(
        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

    int isAlreadyLogin = sharedPref.getInt(getString(R.string.is_already_login), 0);
    if (isAlreadyLogin > 0) {
      // Show home activity
      Intent homeIntent = new Intent(this, HomeActivity.class);
      startActivity(homeIntent);

      // Prevent back to MainActivity
      finish();
    } else {
      // Show login activity
      Intent loginIntent = new Intent(this, LoginActivity.class);
      startActivity(loginIntent);

      // Prevent back to MainActivity
      finish();
    }

  }
}
