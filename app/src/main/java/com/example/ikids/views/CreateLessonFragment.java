package com.example.ikids.views;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ikids.R;
import com.example.ikids.databinding.FragmentCreateLessonBinding;
import com.example.ikids.models.Lessons;
import com.example.ikids.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;


public class CreateLessonFragment extends Fragment {


    private FragmentCreateLessonBinding binding;

    private ActivityResultLauncher<Intent> galleryLauncher = null;
    private Uri imageURI = null;
    private StorageReference storageReference = null;
    private StorageTask storageTask = null;
    private FirebaseFirestore firestore;
    private Constants constants = new Constants();
    private ArrayList<String> choicesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreateLessonBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference(constants.LESSONS_TABLE);
        choicesList = new ArrayList<>();
        binding.buttonAddImage.setOnClickListener(view12 -> {
            Intent galleryIntent = new
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(galleryIntent);
        });
        binding.buttonAddChoice.setOnClickListener(view1 -> {
            if (!binding.inputChoice.getText().toString().isEmpty()) {
                addChoice(binding.inputChoice.getText().toString());
            }
        });
        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Intent data = result.getData();
            try {
                if (data != null && data.getData() != null) {
                    imageURI = data.getData();
                    binding.buttonAddImage.setImageURI(imageURI);

                }
            } catch (Exception e){
                Toast.makeText(binding.getRoot().getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        binding.buttonSave.setOnClickListener(view13 -> {
            String sentence = binding.inputSentence.getText().toString();
            if (!sentence.isEmpty() || choicesList.size() != 0) {
                uploadLesson(imageURI,sentence,choicesList);
            }
        });
    }
    private void addChoice(String name) {
        View view = LayoutInflater.from(binding.getRoot().getContext()).inflate(R.layout.row_choices,binding.layoutChoices,false);
        TextView textName = (TextView) view.findViewById(R.id.textChoiceName);
        ImageButton buttonDeleteChoice = view.findViewById(R.id.buttonDeleteChoice);
        textName.setText(name);
        buttonDeleteChoice.setOnClickListener(view1 -> {
            binding.layoutChoices.removeView(view);
            choicesList.remove(name);
        });
        choicesList.add(name);
        binding.layoutChoices.addView(view);
    }
    //TODO: upload file in firestore and storage
    private void uploadLesson(Uri uri, String sentence, ArrayList<String> choices){
        String id = firestore.collection(constants.LESSONS_TABLE).document().getId();
        String teacherID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Lessons lessons = new Lessons(id,teacherID,"",sentence,choices,false,System.currentTimeMillis());
        if (uri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
            storageTask = fileReference.putFile(uri).addOnSuccessListener(taskSnapshot -> {
                fileReference.getDownloadUrl().addOnSuccessListener(uri1 -> {
                    lessons.setImage(uri1.toString());
                    saveLesson(lessons);
                });
            });
        } else {
            saveLesson(lessons);
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = requireContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void saveLesson(Lessons lessons) {
        firestore.collection(constants.LESSONS_TABLE)
                .document(lessons.getId())
                .set(lessons)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(binding.getRoot().getContext(),"Lesson save successfully",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(binding.getRoot().getContext(),"Error saving lesson",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}