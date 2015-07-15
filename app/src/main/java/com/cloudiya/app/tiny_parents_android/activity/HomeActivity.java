/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/13/15.
 *
 * Purpose: Home screen
 */

/**
 * Purpose: Home screen
 */

package com.cloudiya.app.tiny_parents_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.cloudiya.app.tiny_parents_android.R;
import com.cloudiya.app.tiny_parents_android.fragment.HomeFragment;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;

public class HomeActivity extends MaterialNavigationDrawer<Fragment>
    implements MaterialAccountListener {

  @Override public void init(Bundle bundle) {

    String nickname = getString(R.string.default_nickname);
    String phoneString = "Tel: " + getString(R.string.default_phone_number);

    MaterialAccount account =
        new MaterialAccount(this.getResources(), nickname, phoneString, R.drawable.avatar,
            R.drawable.bamboo);
    this.addAccount(account);

    // set listener
    this.setAccountListener(this);

    // create sections
    this.addSection(newSection("Home", new HomeFragment()));
    this.addSection(newSection("Login", new Intent(this, LoginActivity.class)));
  }

  @Override public void onAccountOpening(MaterialAccount account) {

  }

  @Override public void onChangeAccount(MaterialAccount newAccount) {

  }
}
