package com.example.ikids;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class StudentGradeLevelActivity extends AppCompatActivity {

    private ImageView ImageViewKinder, ImageViewGrade1, ImageViewGrade2, ImageViewGrade3;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_student_grade_selection);

        ImageViewKinder = findViewById(R.id.image_kinder);
        ImageViewGrade1 = findViewById(R.id.image_one);
        ImageViewGrade2 = findViewById(R.id.image_two);
        ImageViewGrade3 = findViewById(R.id.image_three);

        ImageViewKinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentGradeLevelActivity.this, MainActivity.class));
            }
        });
        ImageViewGrade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentGradeLevelActivity.this, MainActivity.class));
            }
        });
        ImageViewGrade2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentGradeLevelActivity.this, MainActivity.class));
            }
        });
        ImageViewGrade3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentGradeLevelActivity.this, MainActivity.class));
            }
        });
    }
}
