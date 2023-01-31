package com.example.ikids.models;

import java.util.ArrayList;

public class Lessons {
    String id;
    String teacherID;
    String image;
    String sentence;
    ArrayList<String> choices;
    Boolean isOpen;
    Long createdAt;

    public Lessons() {
    }

    public Lessons(String id, String teacherID, String image, String sentence, ArrayList<String> choices, Boolean isOpen, Long createdAt) {
        this.id = id;
        this.teacherID = teacherID;
        this.image = image;
        this.sentence = sentence;
        this.choices = choices;
        this.isOpen = isOpen;
        this.createdAt = createdAt;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
