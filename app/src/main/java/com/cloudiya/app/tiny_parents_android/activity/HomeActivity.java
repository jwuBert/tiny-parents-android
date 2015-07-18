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
 *
 * Purpose: Home screen
 *
 * Purpose: Home screen
 */

/**
 * Purpose: Home screen
 */

package com.cloudiya.app.tiny_parents_android.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.cloudiya.app.tiny_parents_android.R;
import com.cloudiya.app.tiny_parents_android.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {

  private DrawerLayout dlDrawer;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_home);

    // Set a Toolbar to replace the ActionBar.
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // Find our drawer view
    dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

    NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
    // Setup drawer view
    setupDrawerContent(nvDrawer);

    // Set the menu icon instead of the launcher icon.
    final ActionBar actionBar = getSupportActionBar();
    assert actionBar != null;
    actionBar.setHomeAsUpIndicator(R.drawable.ic_action_menu);
    actionBar.setDisplayHomeAsUpEnabled(true);


    // Insert the fragment by replacing any existing fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.flContent, new HomeFragment()).commit();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // The action bar home/up action should open or close the drawer.
    switch (item.getItemId()) {
      case android.R.id.home:
        dlDrawer.openDrawer(GravityCompat.START);
        return true;
    }return super.onOptionsItemSelected(item);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
  }

  private void setupDrawerContent(NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(MenuItem menuItem) {
            selectDrawerItem(menuItem);
            return true;
          }
        });
  }

  public void selectDrawerItem(MenuItem menuItem) {
    // Create a new fragment and specify the planet to show based on
    // position
    Fragment fragment = null;

    Class fragmentClass;
    switch(menuItem.getItemId()) {
      case R.id.nav_home_fragment:
        fragmentClass = HomeFragment.class;
        break;
      case R.id.nav_switch_child_fragment:
        fragmentClass = HomeFragment.class;
        break;
      case R.id.nav_about_us_fragment:
        fragmentClass = HomeFragment.class;
        break;
      case R.id.nav_setting_fragment:
        fragmentClass = HomeFragment.class;
        break;
      default:
        fragmentClass = HomeFragment.class;
    }

    try {
      fragment = (Fragment) fragmentClass.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Insert the fragment by replacing any existing fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

    // Highlight the selected item, update the title, and close the drawer
    menuItem.setChecked(true);
    setTitle(menuItem.getTitle());
    dlDrawer.closeDrawers();
  }
}