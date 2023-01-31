package com.example.ikids.models;

public class ClassCodes {
    String id;
    String teacherID;
    String classCode;

    public ClassCodes() {
    }

    public ClassCodes(String id, String teacherID, String classCode) {
        this.id = id;
        this.teacherID = teacherID;
        this.classCode = classCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
