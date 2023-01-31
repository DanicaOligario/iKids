package com.example.ikids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ImageView ImageViewPlay, ImageViewLesson, ImageViewOptions, ImageViewQuit;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

        ImageViewPlay = findViewById(R.id.imageViewPlay);
        ImageViewLesson = findViewById(R.id.imageViewLearn);
        ImageViewOptions = findViewById(R.id.imageViewOptions);
        ImageViewQuit = findViewById(R.id.imageViewQuit);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user");

        ImageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Student1KinderLevel1SelectionActivity.class));
            }
        });
        ImageViewLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Student1KinderLevel1SelectionActivity.class));
            }
        });
        ImageViewOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Student1KinderLevel1SelectionActivity.class));
            }
        });
        ImageViewQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Student1KinderLevel1SelectionActivity.class));
            }
        });
    }
}