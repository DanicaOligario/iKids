package com.example.ikids.models;

import java.io.Serializable;

public class Student implements Serializable {
    String id, email,studentName, gender, gradeLevel,classCode;

    public Student(String id, String email, String studentName, String gender, String gradeLevel, String classCode) {
        this.id = id;
        this.email = email;
        this.studentName = studentName;
        this.gender = gender;
        this.gradeLevel = gradeLevel;
        this.classCode = classCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
