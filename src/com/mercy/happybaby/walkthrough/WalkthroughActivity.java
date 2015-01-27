package com.mercy.happybaby.walkthrough;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.mercy.happybaby.R;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class WalkthroughActivity extends FragmentActivity{
    WalkthroughFragmentAdapter mAdapter;

    ViewPager mPager;
    PageIndicator mIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.walkthroughviewpager);

        mAdapter = new WalkthroughFragmentAdapter(this,getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}
