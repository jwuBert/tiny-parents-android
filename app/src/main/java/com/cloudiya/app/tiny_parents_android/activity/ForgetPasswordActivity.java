package com.cloudiya.app.tiny_parents_android.activity;

/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/6/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.cloudiya.app.tiny_parents_android.API.TinyAPIClient;
import com.cloudiya.app.tiny_parents_android.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.rengwuxian.materialedittext.MaterialEditText;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Purpose:
 */

public class ForgetPasswordActivity extends Activity {
  private static final String TAG = ForgetPasswordActivity.class.getSimpleName();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_forgetpassword);

    final MaterialEditText usernameEditText =
        (MaterialEditText) findViewById(R.id.usernameEditText);
    final MaterialEditText getSMSCodeEditText =
        (MaterialEditText) findViewById(R.id.code_edit_text);
    final MaterialEditText modifyPwdEditText =
        (MaterialEditText) findViewById(R.id.password_edit_text);
    final MaterialEditText modifyPwdEditText2 =
        (MaterialEditText) findViewById(R.id.password_edit_text2);
    final Button getSmsCodeButton = (Button) findViewById(R.id.btn_button);
    final Button ModifyPasswordButton = (Button) findViewById(R.id.submit_button);

    getSmsCodeButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        String phone = usernameEditText.getText().toString();
        String role = "1";

        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("role", role);
        TinyAPIClient.getSMSCode(params, new JsonHttpResponseHandler() {
              @Override
              public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d(TAG, response.toString());
                try {
                  int ret = response.getInt("ret");
                  String msg = response.getString("msg");
                  if (ret > 0) {
                    Toast.makeText(ForgetPasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
                  }
                } catch (JSONException e) {
                  e.printStackTrace();
                }
              }
            }

        );
      }
    });

    ModifyPasswordButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        String phone = usernameEditText.getText().toString();
        String SMSCode = getSMSCodeEditText.getText().toString();
        String newPassword = modifyPwdEditText.getText().toString();
        String newPasswordRepeated = modifyPwdEditText2.getText().toString();
        String role = "1";

        if (!newPassword.equals(newPasswordRepeated)) {
          Toast.makeText(ForgetPasswordActivity.this,
              getString(R.string.password_not_consist_message), Toast.LENGTH_SHORT).show();
          return;
        }
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("code", SMSCode);
        params.put("newPwd", newPasswordRepeated);
        params.put("role", role);

        TinyAPIClient.ModifyPwd(params, new JsonHttpResponseHandler() {
          @Override public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            Log.d(TAG, response.toString());
            try {
              int ret = response.getInt("ret");
              String msg = response.getString("msg");
              if (ret > 0) {
                Toast.makeText(ForgetPasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
              }
            } catch (JSONException e) {
              e.printStackTrace();
            }

            Intent intent = new Intent(ForgetPasswordActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(ForgetPasswordActivity.this,
                getString(R.string.password_success_forget_pwd), Toast.LENGTH_SHORT).show();
          }

          @Override public void onFailure(int statusCode, Header[] headers, Throwable throwable,
              JSONObject errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);
            Toast.makeText(ForgetPasswordActivity.this,
                getString(R.string.error_Internet_forget_pwd), Toast.LENGTH_SHORT).show();
          }
        });
      }
    });
  }
}
