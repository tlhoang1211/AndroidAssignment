package com.example.androidassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    FrameLayout mainContent;
    AddFragment addFragment;
    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_nav_view);
        toolbar = findViewById(R.id.drawer_toolbar);

        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_menu_message,
                R.string.close_menu_message);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);

        // on bottom menu click
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(this);

        // add fragment.
        mainContent = findViewById(R.id.main_content);
        addFragment = new AddFragment();
        listFragment = new ListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, addFragment, AddFragment.class.getName())
                .commit();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add:
                Log.d(MainActivity.class.getName(), "Add");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, addFragment, AddFragment.class.getName())
                        .commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu_item_list:
                Log.d(MainActivity.class.getName(), "List");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, listFragment, ListFragment.class.getName())
                        .commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return false;
    }
}