package com.cloudiya.app.tiny_parents_android.API;

/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/10/15.
 */

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Purpose: Static API client
 *
 * Usage:(make user login request)
 *
 * </pre>
 *    RequestParams params = new RequestParams();
 *    params.put("uname", username);
 *    params.put("passwd", password);
 *    params.put("role", role);
 *    TinyAPIClient.userLogin(params, new JsonHttpResponseHandler() {
 *      &#064;Override public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
 *        super.onSuccess(statusCode, headers, response);
 *        Log.d(TAG, response.toString());
 *      }
 *    });
 * </pre>
 */

public class TinyAPIClient {

  private static final String BASE_URL = "https://api.68baobao.cn:7443/openapi/v2";
  private static AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    client.get(getAbsoluteUrl(url), params, responseHandler);
  }

  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    client.post(getAbsoluteUrl(url), params, responseHandler);
  }

  private static String getAbsoluteUrl(String relativeUrl) {
    return BASE_URL + relativeUrl;
  }

  public static void userLogin(RequestParams params, AsyncHttpResponseHandler responseHandler) {
    get("/user/login/", params, responseHandler);
  }

  public static void getSMSCode(RequestParams params, AsyncHttpResponseHandler responseHandler) {
    get("/user/get_smscode/", params, responseHandler);
  }

  public static void ModifyPwd(RequestParams params, AsyncHttpResponseHandler responseHandler) {
    get("user/modify_pwd_bySms/", params, responseHandler);
  }

  public static void feedback(RequestParams params, AsyncHttpResponseHandler responseHandler) {
    get("app/feedback/", params, responseHandler);
  }

}
