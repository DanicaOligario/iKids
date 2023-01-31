package com.example.ikids;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Student1KinderLevel2SelectionActivity extends AppCompatActivity {

    private ImageView imageViewLevel, imageViewLevel2, imageViewNext, imageViewPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_level2);

        imageViewLevel = findViewById(R.id.imageViewLevel);
        imageViewLevel2 = findViewById(R.id.imageViewLevel2);
        imageViewNext = findViewById(R.id.imageViewNext);
        imageViewPrev = findViewById(R.id.imageViewPrev);

        imageViewLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student1KinderLevel2SelectionActivity.this, Student1KinderLevel2Intro.class));
            }
        });
        imageViewLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student1KinderLevel2SelectionActivity.this, Student1KinderLevel3Intro.class));
            }
        });
        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student1KinderLevel2SelectionActivity.this, Student1KinderLevel3SelectionActivity.class));
            }
        });
        imageViewPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student1KinderLevel2SelectionActivity.this, Student1KinderLevel1SelectionActivity.class));
            }
        });
    }
}
