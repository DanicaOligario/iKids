package com.example.ikids.utils;

import android.util.Patterns;

import com.google.android.material.textfield.TextInputLayout;

public class Validation {
    public boolean validateEmail(TextInputLayout textInputLayout) {
        String email = textInputLayout.getEditText().getText().toString();
        if (email.isEmpty()){
            textInputLayout.setError("Invalid Email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textInputLayout.setError("Invalid Email");
            return false;
        }
         else {
            textInputLayout.setError("");
            return true;
        }
    }
    public boolean validatePassword(TextInputLayout textInputLayout) {
        String password = textInputLayout.getEditText().getText().toString();
        if (password.isEmpty()){
            textInputLayout.setError("This field is required!");
            return false;
        } else if (password.length() < 8) {
            textInputLayout.setError("Password must be at least six (8) characters long!");
            return false;
        } else {
            textInputLayout.setError("");
            return true;
        }
    }
}
