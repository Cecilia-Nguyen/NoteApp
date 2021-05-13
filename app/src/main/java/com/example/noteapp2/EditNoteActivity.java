package com.example.noteapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noteapp2.data.DatabaseHelper;
import com.example.noteapp2.model.Note;

public class EditNoteActivity extends AppCompatActivity {
    DatabaseHelper db;
    String note_title, note_content,updated_content;
    Note note;
    TextView tv_title;
    EditText update_content;
    Button btn_update, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        db=new com.example.noteapp2.data.DatabaseHelper(this);
        update_content=findViewById(R.id.et_update_note);
        tv_title=findViewById(R.id.tv_title);
        btn_update=findViewById(R.id.btn_update);
        btn_delete=findViewById(R.id.btn_delete);
        note_title = getIntent().getStringExtra("current_note_title");
        note_content=getIntent().getStringExtra("current_note_content");
        note=new Note(note_title,note_content);
        tv_title.setText(note.getTitle());
        update_content.setText(note.getContent());

    }

    public void onClickUpdate(View view){
        updated_content = update_content.getText().toString();
        if (updated_content.isEmpty()){
            Toast.makeText(EditNoteActivity.this, "Please enter your note", Toast.LENGTH_SHORT).show();
        }
        else{
            Note updated_note = new Note(note_title,updated_content);

            boolean done = db.updateNote(updated_note);

            if (done == true) {
                Intent intent=new Intent(EditNoteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }

    }

    public void onClickDelete(View view){
        db.deleteNote(note);
        Intent intent=new Intent(EditNoteActivity.this,MainActivity.class);
        startActivity(intent);
    }
}