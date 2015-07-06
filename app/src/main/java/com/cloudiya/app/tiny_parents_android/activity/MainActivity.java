package com.cloudiya.app.tiny_parents_android.activity;

/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/6/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.cloudiya.app.tiny_parents_android.R;
import com.cloudiya.app.tiny_parents_android.fragment.SettingFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;

/**
 * Purpose: Setting
 */

public class MainActivity extends MaterialNavigationDrawer<Fragment> implements MaterialAccountListener {


    @Override
    public void init(Bundle bundle) {

        MaterialAccount account = new MaterialAccount(this.getResources(),"Chang Li", "Tel: 13971125425", R.drawable.avatar, R.drawable.bamboo);
        this.addAccount(account);

        // set listener
        this.setAccountListener(this);

        // create sections
        this.addSection(newSection("Setting", new SettingFragment()));
    }

    @Override
    public void onAccountOpening(MaterialAccount account) {

    }

    @Override
    public void onChangeAccount(MaterialAccount newAccount) {

    }
}
