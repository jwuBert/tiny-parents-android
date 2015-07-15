/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/15/15.
 *
 * Purpose:
 */

package com.cloudiya.app.tiny_parents_android.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.cloudiya.app.tiny_parents_android.fragment.PageFragment;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
  final int PAGE_COUNT = 3;
  private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
  private Context context;

  public HomeFragmentPagerAdapter(FragmentManager fm, Context context) {
    super(fm);
    this.context = context;
  }

  @Override
  public int getCount() {
    return PAGE_COUNT;
  }

  @Override
  public Fragment getItem(int position) {
    return PageFragment.newInstance(position + 1);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    // Generate title based on item position
    return tabTitles[position];
  }
}
