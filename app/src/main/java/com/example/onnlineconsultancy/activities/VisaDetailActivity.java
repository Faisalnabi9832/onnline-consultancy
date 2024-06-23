package com.example.onnlineconsultancy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onnlineconsultancy.Models.Country;
import com.example.onnlineconsultancy.R;
import com.example.onnlineconsultancy.adapter.CountryAdapter;

import java.util.ArrayList;
import java.util.List;

public class VisaDetailActivity extends AppCompatActivity {

    private TextView visaTypeTextView;
    private RecyclerView countryRecyclerView;
    private CountryAdapter countryAdapter;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Visa Details");


        visaTypeTextView = findViewById(R.id.visaTypeTextView);
        countryRecyclerView = findViewById(R.id.countryRecyclerView);

        // Get the visa type from the intent
        String visaType = getIntent().getStringExtra("visaType");
        visaTypeTextView.setText(visaType);

        // Initialize country list (this would ideally come from a database or API)
        loadCountries();

        // Set up RecyclerView
        countryRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        countryAdapter = new CountryAdapter(countryList, new CountryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Country country) {
                // Handle country click
                // For example, start a new activity to show detailed visa information for the selected country
                Intent intent = new Intent(VisaDetailActivity.this, ConsultantListActivity.class);
                intent.putExtra("selectedCountry", country.getName());
                startActivity(intent);
            }
        });
        countryRecyclerView.setAdapter(countryAdapter);
    }

    private void loadCountries() {
        // Load the country list
        countryList = new ArrayList<>();
        countryList.add(new Country("USA", R.drawable.usa));
        countryList.add(new Country("Canada", R.drawable.usa));
        countryList.add(new Country("UK", R.drawable.egypt));
        countryList.add(new Country("Australia", R.drawable.egypt));
        countryList.add(new Country("Germany", R.drawable.egypt));
        // Add more countries as needed
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Call the superclass method which handles the back action
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_lef, R.anim.slide_out_right);
         overridePendingTransition(R.anim.slide_in_lef, R.anim.slide_out_right);
    }
}
