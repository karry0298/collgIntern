package com.collekarry.intern;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

public class WardDetailsActivity extends AppCompatActivity
//        implements TabLayout.OnTabSelectedListener,
//        HeartRateFragment.OnFragmentInteractionListener,
//        MedicationFragment.OnFragmentInteractionListener,
//        AppointmentsFragment.OnFragmentInteractionListener
{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private WardPager adapter;
    private AppBarLayout mAppBarLayout;
    private CoordinatorLayout mCoordinatorLayour;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ward_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        mAppBarLayout = findViewById(R.id.app_bar);
        mCoordinatorLayour = findViewById(R.id.coordinator_layout);
        imageView = findViewById(R.id.profilePhoto);

        mAppBarLayout.post(new Runnable() {
            @Override
            public void run() {
                int heightPx = imageView.getHeight();
                setAppBarOffset(heightPx/2);
            }
        });



        viewPager = findViewById(R.id.pager);

        adapter = new WardPager(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

//        tabLayout.addTab(tabLayout.newTab().setText("Heart rate"));
        tabLayout.getTabAt(0).setIcon(R.drawable.heart_rate_ic);
//        tabLayout.addTab(tabLayout.newTab().setText("Medication"));
        tabLayout.getTabAt(1).setIcon(R.drawable.medicines_ic);
//        tabLayout.addTab(tabLayout.newTab().setText("Appointments"));
        tabLayout.getTabAt(2).setIcon(R.drawable.appointments_ic);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        tabLayout.addOnTabSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setAppBarOffset(int offsetPx){
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.onNestedPreScroll(mCoordinatorLayour, mAppBarLayout, null, 0, offsetPx, new int[]{0, 0}, 0);
    }

//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//        viewPager.setCurrentItem(tab.getPosition());
//    }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }
}
