package com.example.noteapp2.model;

public class Note {
    private int id;
    private String title, content;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
