package com.example.noteapp2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp2.data.DatabaseHelper;
import com.example.noteapp2.model.Note;
import com.example.noteapp2.model.NoteAdapter;

import java.util.List;

public class ShowNoteActivity extends AppCompatActivity implements NoteAdapter.OnRowClickListener {
    RecyclerView rv_note;
    NoteAdapter noteAdapter;
    List<Note> notes_list;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        db=new DatabaseHelper(this);
        notes_list = db.fetchNotes();
        rv_note=findViewById(R.id.rv_note);
        noteAdapter = new NoteAdapter(notes_list, ShowNoteActivity.this.getApplicationContext(), this);
        rv_note.setAdapter(noteAdapter);
        RecyclerView.LayoutManager vertical_layout_manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_note.setLayoutManager(vertical_layout_manager);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ShowNoteActivity.this, EditNoteActivity.class);
        intent.putExtra("current_note_title", notes_list.get(position).getTitle());
        intent.putExtra("current_note_content", notes_list.get(position).getContent());
        startActivity(intent);
        finish();
    }
}