package com.example.luxorguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FrameLayout headerBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeHooks();
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TemplesFragment()).commit();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.temples);
    }

    private void makeHooks() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.tool_bar);
        headerBackground = navigationView.getHeaderView(0).findViewById(R.id.header_background);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.temples:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TemplesFragment()).commit();
                headerBackground.setBackgroundResource(R.drawable.temples_header);
                break;
            case R.id.museums:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MuseumsFragment()).commit();
                headerBackground.setBackgroundResource(R.drawable.museum_luxor);
                break;
            case R.id.valleys:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ValleysFragment()).commit();
                headerBackground.setBackgroundResource(R.drawable.valley_of_the_kings);
                break;
            case R.id.restaurants:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RestaurantsFragment()).commit();
                headerBackground.setBackgroundResource(R.drawable.restaurant_aisha);
                break;
            case R.id.entertainment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EntertainmentsFragment()).commit();
                headerBackground.setBackgroundResource(R.drawable.entertainment_hot_air_ballooning);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}