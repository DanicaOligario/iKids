package com.example.ikids;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ikids.models.Student;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentCreateNameActivity extends AppCompatActivity {

    private EditText mname;
    private ImageView next;
    private ImageView back;
    private TextInputLayout til;
    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_student_create_name);

        mname = findViewById(R.id.name);
        next = findViewById(R.id.button_next);
        back = findViewById(R.id.imageViewBack);

        til = (TextInputLayout) findViewById(R.id.tfName);
        mAuth = FirebaseAuth.getInstance();

        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("student");

        mname.addTextChangedListener(new TextWatcher() {
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentCreateNameActivity.this, UserRoleActivity.class));
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();          }
        });

    }
    private void createUser(){
        String name = mname.getText().toString();

        TextInputLayout til = (TextInputLayout) findViewById(R.id.tfName);

        if (!name.isEmpty()) {
            if (!(name.length() > 8)) {
                if (!(name.length() < 2)) {
                    saveNewUser();
                    startActivity(new Intent(StudentCreateNameActivity.this, StudentGenderSelectionActivity.class));
                    finish();
                }else
                    til.setError("Username is too short");
            }else
                til.setError("Username must be atleast 8 characters!");
        } else
            til.setError("This field is required!");
    }

    private void saveNewUser() {
        String studentname = mname.getText().toString();
        String gender = "";
        String gradelevel = "";

      //  Student user = new Student(studentname, gender, gradelevel);

       // databaseReference.child(studentname).setValue(user);
    }
}
