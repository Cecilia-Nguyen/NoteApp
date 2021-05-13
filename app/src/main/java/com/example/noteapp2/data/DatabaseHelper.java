package com.example.noteapp2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.noteapp2.Util.Util;
import com.example.noteapp2.model.Note;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Util.NOTE_TITLE + " TEXT , " + Util.NOTE_CONTENT + " TEXT)";
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_NOTE_TABLE = "DROP TABLE IF EXISTS";
        db.execSQL(DROP_NOTE_TABLE, new String[]{Util.TABLE_NAME});
        onCreate(db);
    }

    public long insertNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE_TITLE, note.getTitle());
        contentValues.put(Util.NOTE_CONTENT, note.getContent());
        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close(); //to prevent memory leaks.
        return newRowId;
    }


    public List<Note> fetchNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> note_list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String note_title = cursor.getString(cursor.getColumnIndex(Util.NOTE_TITLE));
                String note_content = cursor.getString(cursor.getColumnIndex(Util.NOTE_CONTENT));
                note_list.add(new Note(note_title,note_content));
                cursor.moveToNext();
            }
        }
        db.close();
        return note_list;
    }

    public Note fetchNote(String content) {
        SQLiteDatabase db = this.getReadableDatabase();
        Note note;
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.NOTE_ID}, Util.NOTE_CONTENT + "=?",
                new String[] { content}, null, null, null);
        String note_title = cursor.getString(cursor.getColumnIndex(Util.NOTE_TITLE));
        String note_content = cursor.getString(cursor.getColumnIndex(Util.NOTE_CONTENT));
        note = new Note(note_title, note_content);
        db.close();
        return note;
    }

    public void deleteNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.NOTE_TITLE + "=?", new String[]{String.valueOf(note.getTitle())}); //delete note where the id is the one in the parameter
    }

    //updates the name and content and returns the passed in id.
    public boolean updateNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE_CONTENT, note.getContent());
        db.update(Util.TABLE_NAME, contentValues, Util.NOTE_TITLE + "=?", new String[]{String.valueOf(note.getTitle())});
        return true;
    }


}
