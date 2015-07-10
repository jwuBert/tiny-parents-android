package com.cloudiya.app.tiny_parents_android.activity;

/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/6/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.cloudiya.app.tiny_parents_android.API.TinyAPIClient;
import com.cloudiya.app.tiny_parents_android.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.rengwuxian.materialedittext.MaterialEditText;
import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Purpose: Login
 */

public class LoginActivity extends Activity {

  private static final String TAG = LoginActivity.class.getSimpleName();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_login);

    final MaterialEditText usernameEditText =
        (MaterialEditText) findViewById(R.id.usernameEditText);
    final MaterialEditText passwordEditText =
        (MaterialEditText) findViewById(R.id.passwordEditText);

    final Button loginButton = (Button) findViewById(R.id.login_button);
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Log.d(TAG, "Login button pressed.");
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String role = "1";

        RequestParams params = new RequestParams();
        params.put("uname", username);
        params.put("passwd", password);
        params.put("role", role);
        TinyAPIClient.userLogin(params, new JsonHttpResponseHandler() {
          @Override public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            Log.d(TAG, response.toString());
          }
        });
      }
    });
  }
}
