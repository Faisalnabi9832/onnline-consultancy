package com.example.onnlineconsultancy.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.onnlineconsultancy.Models.Country;
import com.example.onnlineconsultancy.R;
import com.example.onnlineconsultancy.adapter.CountryAdapter;
import com.example.onnlineconsultancy.adapter.imageslider;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dawerlayout); // This should point to the correct layout file

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the navigation drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        TextView navHeaderEmail = headerView.findViewById(R.id.nav_header_email);
        TextView navHeaderName = headerView.findViewById(R.id.nav_header_name);

        if (currentUser != null) {
            navHeaderEmail.setText(currentUser.getEmail());
            // Assuming the user's display name is set in the Firebase User Profile
            navHeaderName.setText(currentUser.getDisplayName());
        } else {
            navHeaderEmail.setText("Guest");
            navHeaderName.setText("Guest");
        }
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set up the ViewPager for image sliding
        ViewPager viewPager = findViewById(R.id.viewPager);
        imageslider adapter = new imageslider(this);
        viewPager.setAdapter(adapter);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentPage = viewPager.getCurrentItem();
                if (currentPage == adapter.getCount() - 1) {
                    currentPage = 0;
                } else {
                    currentPage++;
                }
                viewPager.setCurrentItem(currentPage, true);
                handler.postDelayed(this, 3000); // Slide every 3 seconds
            }
        };
        handler.postDelayed(runnable, 3000); // Initial delay

        // Set up the click listener for the student visa card
        LinearLayout studentVisaCard = findViewById(R.id.studentVisaCard);
        studentVisaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VisaDetailActivity.class);
                intent.putExtra("visaType", "Student Visa");
                startActivity(intent);
            }
        });

        // Add similar click listeners for other visa cards if needed
    }



    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String selectedMenuIdString = (String) item.getTitleCondensed();

        if (selectedMenuIdString.equals("Settings")) {

            startActivity(new Intent(this, settingActivity.class));
            finish();
            return true;

        } else if (selectedMenuIdString.equals("Change Password")) {

            startActivity(new Intent(this, ChangePasswordActivity.class));
            finish();
            return true;
        } else if (selectedMenuIdString.equals("Logout")) {

            mAuth.signOut();
            startActivity(new Intent(this, loginactivity.class));
            Toast.makeText(this,"logout",Toast.LENGTH_SHORT).show();
            finish();


        } else if (selectedMenuIdString.equals("change_language")) {
            setLocale("ur");
            return true;

        }
        return true;
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Save the selected language in shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

        // Restart activity to apply changes
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }

}
