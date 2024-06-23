package com.example.onnlineconsultancy.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.onnlineconsultancy.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActvity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextPassword, editTextConfirmPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_actvity);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.edforemailregisrer);
        editTextPassword = findViewById(R.id.edpasswardforregister);
        editTextConfirmPassword = findViewById(R.id.edconfirmpasswardforregister);

        // Initialize progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);

        MaterialButton registerButton = findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        TextView alreadyAccount = findViewById(R.id.alreadyaccount);
        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (password.equals(confirmPassword)) {
            // Show the progress dialog before starting the registration operation
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        // Dismiss the progress dialog once the task is complete
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActvity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActvity.this, loginactivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(RegisterActvity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToLogin() {
        Intent intent = new Intent(this, loginactivity.class);
        startActivity(intent);
    }
}
