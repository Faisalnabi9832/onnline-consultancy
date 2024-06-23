package com.example.onnlineconsultancy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onnlineconsultancy.R;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassward extends AppCompatActivity {

    private Button login, emailsendbtn;
    private EditText editText;
    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassward);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.bt_login);
        emailsendbtn = findViewById(R.id.bt_send);
        editText = findViewById(R.id.id_forget_email);

        // Initialize progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending password reset email...");
        progressDialog.setCancelable(false);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forgotpassward.this, loginactivity.class);
                startActivity(intent);
            }
        });

        emailsendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = editText.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(forgotpassward.this, "Please enter your email to reset password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show the progress dialog before starting the reset password operation
        progressDialog.show();

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    // Dismiss the progress dialog once the task is complete
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        Toast.makeText(forgotpassward.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(forgotpassward.this, loginactivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(forgotpassward.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void finish() {
        super.finish();
    }
}
