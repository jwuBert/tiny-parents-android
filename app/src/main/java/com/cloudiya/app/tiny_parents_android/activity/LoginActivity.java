package com.cloudiya.app.tiny_parents_android.activity;

/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/6/15.
 */

import android.app.Activity;
import android.os.Bundle;
import com.cloudiya.app.tiny_parents_android.R;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Purpose: Login
 */

public class LoginActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_login);

    MaterialEditText usernameEditText = (MaterialEditText) findViewById(R.id.usernameEditText);
    MaterialEditText passwordEditText = (MaterialEditText) findViewById(R.id.passwordEditText);
  }

}
