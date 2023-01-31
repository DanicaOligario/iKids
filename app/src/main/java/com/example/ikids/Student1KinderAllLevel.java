package com.example.ikids;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ikids.adapters.LessonsAdapter;
import com.example.ikids.databinding.ActivityKinderAllLevelBinding;
import com.example.ikids.models.Lessons;
import com.example.ikids.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Student1KinderAllLevel extends AppCompatActivity {

    private ImageView imageViewLesson;
    private ActivityKinderAllLevelBinding binding;
    private FirebaseFirestore firestore;
    private Constants constants = new Constants();
    private ArrayList<Lessons> lessonsArrayList;
    private LessonsAdapter lessonsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        binding = ActivityKinderAllLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        lessonsArrayList = new ArrayList<>();
//                                imageViewLesson.setOnClickListener(view -> startActivity(new Intent(Student1KinderAllLevel.this, Student1KinderLesson.class)));
        firestore = FirebaseFirestore.getInstance();
        binding.recylerviewLevels.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        imageViewLesson = findViewById(R.id.imageViewLesson);
        lessonsAdapter = new LessonsAdapter(binding.getRoot().getContext(),lessonsArrayList);
        getAllLevels();
    }
    private void getAllLevels() {
        lessonsArrayList.clear();
        firestore.collection(constants.LESSONS_TABLE)
                .orderBy("createdAt", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot snapshot: task.getResult()) {
                            Lessons lesson  = snapshot.toObject(Lessons.class);
                            lessonsArrayList.add(lesson);
                        }
                        binding.recylerviewLevels.setAdapter(lessonsAdapter);
                        lessonsAdapter.notifyDataSetChanged();
                    }
                });
    }
}
