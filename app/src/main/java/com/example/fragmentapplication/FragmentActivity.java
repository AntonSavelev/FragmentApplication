package com.example.fragmentapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.fragmentapplication.fragments.AboutFragment;
import com.example.fragmentapplication.fragments.PreferenceFragment;
import com.example.fragmentapplication.fragments.list_fragment.ListFragment;

public class FragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListFragment listFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listFragment = new ListFragment();
        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, listFragment);
        transaction.commit();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            ListFragment listFragment = new ListFragment();
            manager.beginTransaction().replace(R.id.fragment_container, listFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        } else if (id == R.id.nav_manage) {
            PreferenceFragment preferenceFragment = new PreferenceFragment();
            manager.beginTransaction().replace(R.id.fragment_container, preferenceFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        } else if (id == R.id.nav_share) {
            AboutFragment aboutFragment = new AboutFragment();
            manager.beginTransaction().replace(R.id.fragment_container, aboutFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
