package com.example.ikids;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentGenderSelectionActivity extends AppCompatActivity {

    private TextView textViewBoy, textViewGirl;
    private ImageView ImageViewBoy, ImageViewGirl;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_student_gender_selection);

        textViewBoy = findViewById(R.id.textViewBoy);
        textViewGirl = findViewById(R.id.textViewGirl);
        ImageViewBoy = findViewById(R.id.boy);
        ImageViewGirl = findViewById(R.id.girl);


        textViewBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentGenderSelectionActivity.this, StudentGradeLevelActivity.class));
            }
        });
        textViewGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentGenderSelectionActivity.this, StudentGradeLevelActivity.class));
            }
        });
        ImageViewBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentGenderSelectionActivity.this, StudentGradeLevelActivity.class));
            }
        });
        ImageViewGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentGenderSelectionActivity.this, StudentGradeLevelActivity.class));
            }
        });
    }

    private void GetGender(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        Query query = databaseReference.orderByChild("nickname").limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}