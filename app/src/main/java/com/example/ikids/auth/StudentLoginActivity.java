package com.example.ikids.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ikids.R;
import com.example.ikids.Student1KinderAllLevel;
import com.example.ikids.TeacherDashboardActivity;
import com.example.ikids.databinding.ActivityStudentLoginBinding;
import com.example.ikids.utils.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentLoginActivity extends AppCompatActivity {
    ActivityStudentLoginBinding binding;
    FirebaseAuth auth;
    private Validation validation = new Validation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        binding.login.setOnClickListener(view -> {
            String email = binding.emailin.getText().toString();
            String password = binding.passwordin.getText().toString();
            if (!validation.validateEmail(binding.textfield) || !validation.validatePassword(binding.textfield2)) {
                return;
            }
            login(email,password);
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            startActivity(new Intent(StudentLoginActivity.this, Student1KinderAllLevel.class));
            finish();
        }
    }
    private void login(String email ,String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = task.getResult().getUser();
                        updateUI(user);
                    }
                });
    }
}