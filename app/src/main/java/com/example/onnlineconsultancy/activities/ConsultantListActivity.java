package com.example.onnlineconsultancy.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onnlineconsultancy.Models.Consultant;
import com.example.onnlineconsultancy.R;
import com.example.onnlineconsultancy.adapter.ConsultantAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConsultantListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConsultantAdapter consultantAdapter;
    private List<Consultant> consultantList;
    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Consultant Information");

        recyclerView = findViewById(R.id.recyclerViewConsultants);
        searchView = findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the selected country from the intent
        String selectedCountry = getIntent().getStringExtra("selectedCountry");

        // Load all consultants (this should be replaced with your data source)
        loadConsultants();

        // Filter consultants by the selected country
        List<Consultant> filteredConsultants = new ArrayList<>();
        for (Consultant consultant : consultantList) {
            if (consultant.getCountry().equalsIgnoreCase(selectedCountry)) {
                filteredConsultants.add(consultant);
            }
        }

        // Set the filtered list to the adapter
        consultantAdapter = new ConsultantAdapter(filteredConsultants, this);
        recyclerView.setAdapter(consultantAdapter);

        // Implement search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                consultantAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                consultantAdapter.getFilter().filter(newText);
                return false;
            }
        });
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

    private void loadConsultants() {
        // Add your logic to load the consultant list
        consultantList = new ArrayList<>();
        consultantList.add(new Consultant("John Doe", "USA", "IT Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Jane Smith", "Canada", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Jane Smith", "UK", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Jane Smith", "Australia", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Faisal", "Germany", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Haseeb", "Germany", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Jane", "Germany", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Smith", "Germany", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Shahhassan", "Germany", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Umar", "Germany", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Luqman", "Germany", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Tufail", "Germany", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Farhan", "Germany", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Liaqat", "Germany", "Legal Services", R.drawable.profilepic,"0028782","UK"));
        consultantList.add(new Consultant("Jane Smith", "France", "Legal Services", R.drawable.profilepic,"0028782","UK"));
    }
}
