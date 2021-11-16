package com.example.eportal.User;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.material.navigation.NavigationView;
import com.example.eportal.R;


public class MapsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final float END_SCALE = 0.7f;
    Timer time = new Timer();

    //Drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ConstraintLayout contentLayout;

    ImageView menuIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        String policeCheck = intent.getStringExtra("police");
        Toast.makeText(MapsActivity.this, policeCheck, Toast.LENGTH_SHORT).show();
        MapsFragment mp = new MapsFragment();

        contentLayout = findViewById(R.id.contentLayout);

        //Menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        menuIcon = findViewById(R.id.menu_icon);

        navigationDrawer();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                    new MapsFragment()).commit();

            navigationView.setCheckedItem(R.id.nav_explore);

        }
//
//        time.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//                if (true) {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//
//                            if (policeCheck.length() > 0) {
////                                Bundle bd = new Bundle();
////                                bd.putString("police" , policeCheck);
////                                MapsFragment mp = new MapsFragment();
////                                mp.setArguments(bd);
//                            }
//                        }
//                    });
//
//                }
//
//
//            }
//        }, 5000);


    }


    //navigation drawer function
    private void navigationDrawer() {
        //navigation drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_explore);

        menuIcon.setOnClickListener(view -> {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }


    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.blue_gray_700));

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //Scale the view based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentLayout.setScaleX(offsetScale);
                contentLayout.setScaleY(offsetScale);

                //Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentLayout.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentLayout.setTranslationX(xTranslation);

            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_explore:

                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new MapsFragment()).commit();
                break;


            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new ProfileFragment()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_rate_us:
                Toast.makeText(this, "Rated", Toast.LENGTH_SHORT).show();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}