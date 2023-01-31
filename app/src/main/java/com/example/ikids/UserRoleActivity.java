package com.example.ikids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.ikids.auth.StudentLoginActivity;
import com.example.ikids.auth.TeacherLoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class UserRoleActivity extends AppCompatActivity {

    private ImageView student;
    private ImageView teacher;
    private ImageView next;
    private ImageView ImageViewTeacher;
    private ImageView ImageViewStudent;
    boolean isStudent;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_user);

        student = findViewById(R.id.imageViewStudent);
        teacher = findViewById(R.id.imageViewTeacher);
        next = findViewById(R.id.imageViewNext);
        ImageViewTeacher = findViewById(R.id.imageViewTeacher);
        ImageViewStudent = findViewById(R.id.imageViewStudent);


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageViewStudent.setBackgroundResource(R.drawable.checked_rounded_square);
                ImageViewTeacher.setBackgroundResource(R.drawable.rounded_square);
                isStudent = true;
            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageViewTeacher.setBackgroundResource(R.drawable.checked_rounded_square);
                ImageViewStudent.setBackgroundResource(R.drawable.rounded_square);
                isStudent = false;
            }
        });

        mAuth = FirebaseAuth.getInstance();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStudent == true) {
                    startActivity(new Intent(UserRoleActivity.this, StudentLoginActivity.class));
                } else if (mAuth.getCurrentUser() != null) {
                    startActivity(new Intent(UserRoleActivity.this, TeacherDashboardActivity.class));
                } else {
                    startActivity(new Intent(UserRoleActivity.this, TeacherLoginActivity.class));
                }
                finish();
            }

        });
    }
}