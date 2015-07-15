/**
 * Copyright (c) 2011-2015 Wuhan Cloudiya Tech. Co. Ltd
 * This file is part of Little's Childhood （微童年） Software & APP
 * Created by mm on 7/14/15.
 *
 * Purpose: Home
 */

package com.cloudiya.app.tiny_parents_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cloudiya.app.tiny_parents_android.R;
import com.cloudiya.app.tiny_parents_android.adapter.HomeFragmentPagerAdapter;

public class HomeFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);

    ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
    viewPager.setAdapter(new HomeFragmentPagerAdapter(getFragmentManager(), getActivity()));

    TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
    tabLayout.setupWithViewPager(viewPager);

    return view;
  }
}
