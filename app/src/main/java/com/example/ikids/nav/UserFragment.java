package com.example.ikids.nav;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ikids.R;
import com.example.ikids.databinding.FragmentUserBinding;
import com.example.ikids.models.ClassCodes;
import com.example.ikids.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserFragment extends Fragment {


    private FragmentUserBinding binding;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private Constants constants = new Constants();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        //dito ka magcode mamaya
        binding.buttonGenerateClassCode.setOnClickListener(view1 -> {
            showClassCodeDialog();
        });
    }

    private void createClassCode(ClassCodes classCodes) {
        firestore.collection(constants.CLASS_CODES_TABLE)
                .document(classCodes.getId()).set(classCodes).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(binding.getRoot().getContext(),"New class code created!",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(binding.getRoot().getContext(),"Failed to create class code!",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(binding.getRoot().getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                });
    }
    private void showClassCodeDialog() {
        String code =  generateClassCode();
        String id = firestore.collection(constants.CLASS_CODES_TABLE).document().getId();
        String teacherID = auth.getCurrentUser().getUid();
        new MaterialAlertDialogBuilder(binding.getRoot().getContext())
                .setTitle("New class code")
                .setMessage("Do you want to save this class code ? " + code)
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    ClassCodes classCodes = new ClassCodes(id,teacherID,code);
                    createClassCode(classCodes);
                    dialogInterface.dismiss();

                }).setNegativeButton("No", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .show();
    }
    // function to generate a random string of length n
    public String generateClassCode()
    {
        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}