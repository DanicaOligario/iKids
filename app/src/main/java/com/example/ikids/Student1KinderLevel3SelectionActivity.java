package com.example.ikids;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Student1KinderLevel3SelectionActivity extends AppCompatActivity {

    private ImageView imageViewLevel, imageViewLevel3, imageViewNext, imageViewPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_level3);

        imageViewLevel = findViewById(R.id.imageViewLevel);
        imageViewLevel3 = findViewById(R.id.imageViewLevel3);
        imageViewNext = findViewById(R.id.imageViewNext);
        imageViewPrev = findViewById(R.id.imageViewPrev);

        imageViewLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student1KinderLevel3SelectionActivity.this, Student1KinderLevel3Intro.class));
            }
        });
        imageViewLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student1KinderLevel3SelectionActivity.this, Student1KinderLevel3Intro.class));
            }
        });
        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student1KinderLevel3SelectionActivity.this, Student1KinderLevel4SelectionActivity.class));
            }
        });
        imageViewPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student1KinderLevel3SelectionActivity.this, Student1KinderLevel2SelectionActivity.class));
            }
        });
    }
}
