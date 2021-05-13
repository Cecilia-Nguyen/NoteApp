package com.example.noteapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noteapp2.data.DatabaseHelper;
import com.example.noteapp2.model.Note;

public class CreateNoteActivity extends AppCompatActivity {
    EditText et_note,et_note_title;
    Button btn_save;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note_acitivity);

        et_note=findViewById(R.id.et_note);
        et_note_title=findViewById(R.id.et_note_title);
        btn_save=findViewById(R.id.btn_save);
        db=new com.example.noteapp2.data.DatabaseHelper(this);
    }
    public void onClickSave(View view){
        String title = et_note_title.getText().toString();
        String content = et_note.getText().toString();
        if (title.isEmpty()||content.isEmpty()){
            Toast.makeText(CreateNoteActivity.this, "Please enter your title and/or note", Toast.LENGTH_SHORT).show();
        }
        else{
            long result = db.insertNote(new Note(title,content));
            if (result > 0){
                Intent intent=new Intent(CreateNoteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }


    }
}