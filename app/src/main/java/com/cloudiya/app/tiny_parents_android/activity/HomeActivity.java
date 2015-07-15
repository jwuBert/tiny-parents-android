/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/13/15.
 *
 * Purpose: Home screen
 *
 * Purpose: Home screen
 *
 * Purpose: Home screen
 */

/**
 * Purpose: Home screen
 */

package com.cloudiya.app.tiny_parents_android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import com.cloudiya.app.tiny_parents_android.R;
import com.cloudiya.app.tiny_parents_android.fragment.HomeFragment;
import com.cloudiya.app.tiny_parents_android.model.Parent;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import io.realm.Realm;
import io.realm.RealmResults;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;

public class HomeActivity extends MaterialNavigationDrawer<Fragment>
    implements MaterialAccountListener {

  private static final String TAG = LoginActivity.class.getSimpleName();

  @Override public void init(Bundle bundle) {

    SharedPreferences sharedPref =
        getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    String currentParentID = sharedPref.getString(getString(R.string.current_parent_ID), null);

    Realm realm = Realm.getInstance(getApplicationContext());
    RealmResults<Parent> currentParentResult =
        realm.where(Parent.class).equalTo("userID", currentParentID).findAll();
    Parent currentParent;
    if (currentParentResult.size() > 1) {
      Log.e(TAG, "found multiple login user records");
      return;
    } else if (currentParentResult.size() == 0) {
      Log.e(TAG, "found zero login user records");
      return;
    } else {
      currentParent = currentParentResult.first();
    }

    String nickname = currentParent.getNickname();
    String phoneString = "Tel: " + currentParent.getPhoneNumber();

    final MaterialAccount account =
        new MaterialAccount(this.getResources(), nickname, phoneString, R.drawable.avatar,
            R.drawable.bamboo);
    this.addAccount(account);

    ImageLoader imageLoader = ImageLoader.getInstance();
    imageLoader.loadImage(currentParent.getAvatarURL(), new SimpleImageLoadingListener() {
      @Override public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        account.setPhoto(loadedImage);
      }
    });

    // set listener
    this.setAccountListener(this);

    // create sections
    this.addSection(newSection("Home", new HomeFragment()));
  }

  @Override public void onAccountOpening(MaterialAccount account) {

  }

  @Override public void onChangeAccount(MaterialAccount newAccount) {

  }
}
