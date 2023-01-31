package com.example.ikids.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ikids.Student1KinderAllLevel;
import com.example.ikids.databinding.ActivityStudentSignUpBinding;
import com.example.ikids.models.ClassCodes;
import com.example.ikids.models.Student;
import com.example.ikids.utils.Constants;
import com.example.ikids.utils.Validation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class StudentSignUpActivity extends AppCompatActivity {
    ActivityStudentSignUpBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    Constants constants = new Constants();
    Validation validation  = new Validation();
    ArrayAdapter<String> classCodeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        classCodeAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                getAllClassCodes()
        );
        binding.inputClassCode.setThreshold(1);
        binding.inputClassCode.setAdapter(classCodeAdapter);
        binding.buttonNext.setOnClickListener(view -> {
            String classCode = binding.inputClassCode.getText().toString();
            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();
            if (!classCode.isEmpty()) {
                if (!validation.validateEmail(binding.tfemail) || !validation.validatePassword(binding.tfpassword)) {
                    return;
                }
                Student student = new Student("",email,"","","",classCode);
                createAccount(student,password);
            } else {
                binding.inputClassCode.setError("This field is required!");
            }
        });

        binding.login.setOnClickListener(view -> {
            startActivity(new Intent(this,StudentLoginActivity.class));
        });
    }
    private ArrayList<String> getAllClassCodes() {
        ArrayList<String> classCodeList = new ArrayList<>();
        firestore.collection(constants.CLASS_CODES_TABLE)
                .get()
                .addOnCompleteListener(task -> {
                     if (task.isSuccessful()) {
                         classCodeList.clear();
                         for (DocumentSnapshot snapshot: task.getResult()) {
                             ClassCodes codes = snapshot.toObject(ClassCodes.class);
                             if (codes != null) {
                                 classCodeList.add(codes.getClassCode());
                             }
                         }
                     }
                });
        return classCodeList;
    }
    public void createAccount(Student student, String password) {
        auth.createUserWithEmailAndPassword(student.getEmail(),password).addOnCompleteListener(task -> {
           if (task.isSuccessful()) {
               FirebaseUser user = task.getResult().getUser();
               if (user != null) {
                   student.setId(user.getUid());
                   saveAccount(student);
               }
           }
        }).addOnFailureListener(e -> {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        });
    }
    private  void saveAccount(Student student) {
        firestore.collection(constants.STUDENT_TABLE)
                .document(student.getId())
                .set(student)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this,"Account Created Successfully!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, Student1KinderAllLevel.class));
                        finish();
                    } else {
                        Toast.makeText(this,"Failed to created account!",Toast.LENGTH_SHORT).show();
                    }
                });
    }


}