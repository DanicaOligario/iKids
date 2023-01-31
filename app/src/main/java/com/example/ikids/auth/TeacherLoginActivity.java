package com.example.ikids.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ikids.R;
import com.example.ikids.TeacherDashboardActivity;
import com.example.ikids.UserRoleActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherLoginActivity extends AppCompatActivity {

    private EditText memail, mpassword;
    private TextView reset;
    private ImageView login;
    private ImageView back;
    private TextView sign_up;
    private ProgressBar progressBar;
    private TextInputLayout til, til2;
    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_teacher_login);

        memail = findViewById(R.id.emailin);
        mpassword = findViewById(R.id.passwordin);
        login = findViewById(R.id.login);
        sign_up = findViewById(R.id.sign_up);
        reset = findViewById(R.id.resetpassword);
        back = findViewById(R.id.imageViewBack);
        progressBar = findViewById(R.id.progressBar);

        til = (TextInputLayout) findViewById(R.id.textfield);
        til2 = (TextInputLayout) findViewById(R.id.textfield2);

        mAuth = FirebaseAuth.getInstance();

        memail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                til.setError(null);
            }
        });

        mpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                til2.setError(null);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherLoginActivity.this, UserRoleActivity.class));
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherLoginActivity.this, ResetPasswordActivity.class));
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherLoginActivity.this, TeacherSignUpActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                loginUser();
            }
        });
    }
        private void loginUser() {
            String email = memail.getText().toString();
            String password = mpassword.getText().toString();

            TextInputLayout til = (TextInputLayout) findViewById(R.id.textfield);
            TextInputLayout til2 = (TextInputLayout) findViewById(R.id.textfield2);

                    if (!email.isEmpty()) {
                        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            if (!password.isEmpty()) {
                                if (!(password.length() < 8)) {
                                    mAuth.signInWithEmailAndPassword(email, password)
                                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                @Override
                                                public void onSuccess(AuthResult authResult) {
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(TeacherLoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                                    updateUI(authResult.getUser());
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(TeacherLoginActivity.this, "Please check your internet connection and try again!", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                } else
                                    til2.setError("Password must be at least six (8) characters long!");
                                progressBar.setVisibility(View.GONE);
                            } else
                                til2.setError("This field is required!");
                            progressBar.setVisibility(View.GONE);
                        } else
                            til.setError("Please enter a valid email address");
                        progressBar.setVisibility(View.GONE);
                    } else
                        til.setError("This field is required!");
            progressBar.setVisibility(View.GONE);
        }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            startActivity(new Intent(TeacherLoginActivity.this, TeacherDashboardActivity.class));
            finish();
        }

    }
}
