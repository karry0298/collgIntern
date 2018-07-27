package com.collekarry.intern;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class WardDetailsActivity extends AppCompatActivity implements AddMedicationFragment.OnEntryComplete
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
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView imageView;
    private wardClass ward;
    private String key;

    private StorageReference mStorageReference,imageRef;
    private DatabaseReference mDatabaseReference;


//    public void setWard(wardClass ward) {
//        this.ward = ward;
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ward_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        tabLayout = findViewById(R.id.tabLayout);
        mAppBarLayout = findViewById(R.id.app_bar);
        mCoordinatorLayour = findViewById(R.id.coordinator_layout);
        mCollapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        viewPager = findViewById(R.id.pager);

        imageView = findViewById(R.id.profilePhoto);

        if(FirebaseAuth.getInstance().getUid() == null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        key = intent.getStringExtra("key");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("wards")
                .child(FirebaseAuth.getInstance().getUid()).child(key);

        mStorageReference = FirebaseStorage.getInstance().getReference();


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ward = dataSnapshot.getValue(wardClass.class);

                imageRef = mStorageReference.child(ward.getUid() + "/displayPictures/" + ward.getKey() + ".jpg");

                Glide.with(getApplicationContext())
                        .using(new FirebaseImageLoader())
                        .load(imageRef)
                        .placeholder(R.drawable.default_dp)
                        .centerCrop()
                        .into(imageView);

                mCollapsingToolbarLayout.setTitle(ward.getName());

                adapter = new WardPager(getSupportFragmentManager(), ward);
                adapter.notifyDataSetChanged();
                viewPager.setAdapter(adapter);

                tabLayout.setupWithViewPager(viewPager);

                tabLayout.getTabAt(0).setIcon(R.drawable.heart_rate_ic);
                tabLayout.getTabAt(1).setIcon(R.drawable.medicines_ic);
                tabLayout.getTabAt(2).setIcon(R.drawable.appointments_ic);
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                startActivity(new Intent(WardDetailsActivity.this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "ward retrieve error", Toast.LENGTH_SHORT);
            }
        });



//        DatabaseReference mRef = mDatabaseReference.child(FirebaseAuth.getInstance().getUid()).child(key);

//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                HashMap value = (HashMap) dataSnapshot.getValue();
//
//                ward = new wardClass(
//                        key,
//                        value.get("name").toString(),
//                        Integer.valueOf(Long.toString((Long) value.get("age"))),
//                        value.get("gender").toString(),
//                        value.get("uid").toString()
//                );
//
//                imageRef = mStorageReference.child(ward.getUid() + "/displayPictures/" + ward.getKey() + ".jpg");
//
//                Glide.with(WardDetailsActivity.this)
//                        .using(new FirebaseImageLoader())
//                        .load(imageRef)
//                        .centerCrop()
//                        .into(imageView);
//
//                mCollapsingToolbarLayout.setTitle(ward.getName());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        mAppBarLayout.post(new Runnable() {
            @Override
            public void run() {
                int heightPx = imageView.getHeight();
                setAppBarOffset(heightPx/2);
            }
        });


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ward_details, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_add_meds)
        {
            AddMedicationFragment.newInstance(ward,"direct_entry").show(getSupportFragmentManager(), "add_med");
        }


        return false;
    }

    @Override
    public void onEntryComplete(Medicine med) {

    }

    @Override
    public void onEntryComplete() {
        adapter.notifyDataSetChanged();
    }
}
