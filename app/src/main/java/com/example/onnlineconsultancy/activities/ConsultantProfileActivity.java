package com.example.onnlineconsultancy.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.onnlineconsultancy.Models.Consultant;
import com.example.onnlineconsultancy.R;

public class ConsultantProfileActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private TextView nameTextView;
    private Button locationTextView;
    private TextView serviceTextView;
    private     Button contactNumberTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_profile);

        profileImageView = findViewById(R.id.profileImageView);
        nameTextView = findViewById(R.id.nameTextView);
        locationTextView = findViewById(R.id.locationTextView);
        serviceTextView = findViewById(R.id.serviceTextView);
        contactNumberTextView = findViewById(R.id.contactNumberTextView);

        // Get the consultant details from the intent
        Consultant consultant = getIntent().getParcelableExtra("consultant");

        // Set the consultant details
        profileImageView.setImageResource(consultant.getProfileImageResId());
        nameTextView.setText(consultant.getName());
        locationTextView.setText(consultant.getLocation());
        serviceTextView.setText(consultant.getService());
        contactNumberTextView.setText(consultant.getContactNumber());
    }
}
