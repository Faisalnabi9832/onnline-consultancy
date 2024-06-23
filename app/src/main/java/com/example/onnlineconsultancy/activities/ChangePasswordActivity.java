package com.example.onnlineconsultancy.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onnlineconsultancy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etConfirmPassword;
    private Button btReset;
    private FirebaseAuth mAuth;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassward);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btReset = findViewById(R.id.bt_reset);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating password...");
        progressDialog.setCancelable(false);

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            return;
        }

        progressDialog.show();

        // Re-authenticate the user
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.updatePassword(password).addOnCompleteListener(task -> {
                progressDialog.dismiss(); // Dismiss the dialog after the task is complete
                if (task.isSuccessful()) {
                    Toast.makeText(ChangePasswordActivity.this, "Password reset successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangePasswordActivity.this, loginactivity.class);
                    startActivity(intent);
                    finish(); // Finish this activity only after successful password change
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Password reset failed", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            progressDialog.dismiss();
            Toast.makeText(ChangePasswordActivity.this, "User not logged in", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        // When back is pressed, navigate to MainActivity
        Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
