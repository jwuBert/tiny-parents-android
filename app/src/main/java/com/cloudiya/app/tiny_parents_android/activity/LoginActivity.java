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
import android.widget.Toast;
import com.cloudiya.app.tiny_parents_android.API.TinyAPIClient;
import com.cloudiya.app.tiny_parents_android.R;
import com.cloudiya.app.tiny_parents_android.helper.PersistModelHelper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.rengwuxian.materialedittext.MaterialEditText;
import io.realm.Realm;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    final MaterialEditText passwordEditText =
        (MaterialEditText) findViewById(R.id.password_edit_text);
    final MaterialEditText usernameEditText =
        (MaterialEditText) findViewById(R.id.username_edit_text);
    final Button loginButton = (Button) findViewById(R.id.login_button);
    final Button forgetPasswordButton = (Button) findViewById(R.id.forget_password_button);

    //listener the event of click loginButton
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Log.d(TAG, "Login button pressed.");
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String role = "1";
        // test username is valid
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(username);
        // test username or password is null
        if (username.length() <= 0) {
          usernameEditText.setError(getString(R.string.username_edit_text_empty_input_error));
          return;
        }
        if (!matcher.matches()) {
          usernameEditText.setError(getString(R.string.phone_edit_text_empty_input_error));
          return;
        }

        if (password.length() <= 0) {
          passwordEditText.setError(getString(R.string.password_edit_text_empty_input_error));
          return;
        }

        RequestParams params = new RequestParams();
        params.put("uname", username);
        params.put("passwd", password);
        params.put("role", role);
        TinyAPIClient.userLogin(params, new JsonHttpResponseHandler() {
          @Override public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            try {
              int ret = response.getInt("ret");
              String msg = response.getString(getString(R.string.login_string_api_error));
              if (ret > 0) {
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
              }
            } catch (JSONException e) {
              e.printStackTrace();
            }

            Log.d(TAG, response.toString());
            // Show home activity
            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(homeIntent);

            // Prevent back to LoginActivity
            finish();

            // Save app state to SharedPreference
            SharedPreferences sharedPref =
                LoginActivity.this.getSharedPreferences(getString(R.string.preference_file_key),
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.is_already_login), 1);

            try {
              // Save security token to sharedPreferences temporarily.
              // Because the KeyStore is useless in a rooted device.
              // Maybe later find some security storage to store token alike security information.
              editor.putString(getString(R.string.API_token), response.getString("token"));

              // Save current login parent to SharedPreference
              editor.putString(getString(R.string.current_parent_ID), response.getString("uid"));
            } catch (JSONException e) {
              e.printStackTrace();
            }

            // Save shared preference in background thread
            editor.apply();

            //// Save models to Realm
            Realm realm = Realm.getInstance(getApplicationContext());
            PersistModelHelper.saveUserModel(response, realm);
          }

          @Override public void onFailure(int statusCode, Header[] headers, Throwable throwable,
              JSONObject errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);
            Toast.makeText(LoginActivity.this, getString(R.string.login_land_network_error),
                Toast.LENGTH_SHORT).show();
          }
        });
      }
    });

    //listener the event of click forgetPasswordButton
    forgetPasswordButton.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View view) {
        Log.d(TAG, "forgetPasswordButton pressed");
        //show ForgetPasswordActivity
        Intent forgetPasswordIntent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(forgetPasswordIntent);
      }
    });
  }
}