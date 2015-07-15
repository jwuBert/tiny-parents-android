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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.cloudiya.app.tiny_parents_android.API.TinyAPIClient;
import com.cloudiya.app.tiny_parents_android.R;
import com.cloudiya.app.tiny_parents_android.helper.PersistModelHelper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.rengwuxian.materialedittext.MaterialEditText;
import io.realm.Realm;
import org.apache.http.Header;
import org.json.JSONException;
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

            // Show home activity
            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(homeIntent);

            // Prevent back to LoginActivity
            finish();

            // Save app state to sharedPreference
            SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.is_already_login), 1);

            // Save security token to sharedPreferences temporarily.
            // Because the KeyStore is useless in a rooted device.
            // Maybe later find some security storage to store token alike security information.
            // TODO: Change a more security strategy to store token, keys .etc. (Important not Emergent)
            try {
              editor.putString("APIToken", response.getString("token"));
            } catch (JSONException e) {
              e.printStackTrace();
            }

            // Save shared preference in background thread
            editor.apply();

            // Save models to Realm
            Realm realm = Realm.getInstance(getApplicationContext());
            PersistModelHelper.saveUserModel(response, realm);
          }
        });
      }
    });
  }
}
