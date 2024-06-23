package com.example.onnlineconsultancy.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onnlineconsultancy.R;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextPassword;
    private Button loginbtn;
    private TextView register, forgotPassword;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.Edforemaillogin);
        editTextPassword = findViewById(R.id.edpasswardforlogin);
        loginbtn = findViewById(R.id.btn_login);
        register = findViewById(R.id.register);
        forgotPassword = findViewById(R.id.forgot_password);

        // Initialize progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

//                Intent intent = new Intent(loginactivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginactivity.this, RegisterActvity.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginactivity.this, forgotpassward.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(loginactivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show the progress dialog before starting the login operation
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    // Dismiss the progress dialog once the task is complete
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        Toast.makeText(loginactivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        navigateToMainActivity();
                    } else {
                        Toast.makeText(loginactivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(loginactivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
