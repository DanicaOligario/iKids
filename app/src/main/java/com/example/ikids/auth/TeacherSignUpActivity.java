package com.example.ikids.auth;

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
import com.example.ikids.UserRoleActivity;
import com.example.ikids.models.Teacher;
import com.example.ikids.utils.Constants;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class TeacherSignUpActivity extends AppCompatActivity {

    private EditText mfname, mlname, memail, mpassword;
    private ImageView sign_up;
    private ImageView back;
    private TextView login;
    private ProgressBar progressBar;
    private TextInputLayout til, til2, til3, til4;
    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseFirestore firestore;
    private Constants constants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_teacher_sign_up);
        firestore = FirebaseFirestore.getInstance();
        mfname = findViewById(R.id.name);
        mlname = findViewById(R.id.last_name);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);

        login = findViewById(R.id.login);
        back = findViewById(R.id.imageViewBack);
        sign_up = findViewById(R.id.button_next);
        progressBar = findViewById(R.id.progressBar);

        til = (TextInputLayout) findViewById(R.id.tfFirstName);
        til2 = (TextInputLayout) findViewById(R.id.tfLastName);
        til3 = (TextInputLayout) findViewById(R.id.tfemail);
        til4 = (TextInputLayout) findViewById(R.id.tfpassword);

        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("teacher");

        mfname.addTextChangedListener(new TextWatcher() {
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

        mlname.addTextChangedListener(new TextWatcher() {
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

        memail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                til3.setError(null);
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
                til4.setError(null);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSignUpActivity.this, UserRoleActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSignUpActivity.this, TeacherLoginActivity.class));
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = mfname.getText().toString();
                String lastname = mlname.getText().toString();
                String email = memail.getText().toString();
                String password = mpassword.getText().toString();

                TextInputLayout til = (TextInputLayout) findViewById(R.id.tfFirstName);
                TextInputLayout til2 = (TextInputLayout) findViewById(R.id.tfLastName);
                TextInputLayout til3 = (TextInputLayout) findViewById(R.id.tfemail);
                TextInputLayout til4 = (TextInputLayout) findViewById(R.id.tfpassword);

                if (!firstname.isEmpty()) {
                    if (!lastname.isEmpty()) {
                        if (!email.isEmpty()) {
                            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                if (!password.isEmpty()) {
                                    if (!(password.length() < 6)) {
                                            progressBar.setVisibility(View.VISIBLE);
                                            createUser();
                                    } else
                                        til4.setError("Password must be at least six (6) characters long!");
                                } else
                                    til4.setError("This field is required!");
                            } else
                                til3.setError("Please enter a valid email address");
                        } else
                            til3.setError("This field is required!");
                    } else
                        til2.setError("This field is required");
                }else
                    til.setError("This field is required");
            }
        });


    }

    private void createUser() {
        String email = memail.getText().toString();
        String password = mpassword.getText().toString();
        progressBar = findViewById(R.id.progressBar);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = task.getResult().getUser();
                        saveNewUser(user.getUid(),user.getEmail());
                        startActivity(new Intent(TeacherSignUpActivity.this, TeacherLoginActivity.class));
                        progressBar.setVisibility(View.GONE);
                        finish();
                    }
                }).addOnFailureListener(e -> Toast.makeText(TeacherSignUpActivity.this, "Please check your internet connection and try again!", Toast.LENGTH_SHORT).show());
    }

//    private void saveNewUser() {
//        String firstname = mfname.getText().toString();
//        String lastname = mlname.getText().toString();
//        String email = memail.getText().toString();
//        String password = mpassword.getText().toString();
//
//        Teacher user = new Teacher(firstname, lastname, email, password);
//
//        databaseReference.child(lastname).setValue(user);
//    }
    private void saveNewUser(String id,String email) {
        String firstname = mfname.getText().toString();
        String lastname = mlname.getText().toString();
        Teacher user = new Teacher(id,firstname, lastname, email);
       firestore.collection(constants.TEACHER_TABLE)
               .document(id)
               .set(user)
               .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this
                                ,"Account created successfully!",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this
                                ,"Failed to create account!",Toast.LENGTH_SHORT).show();
                    }
               });
    }
}



