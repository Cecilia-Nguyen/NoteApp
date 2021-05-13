package com.example.noteapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_create,btn_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_create=findViewById(R.id.btn_create);
        btn_show=findViewById(R.id.btn_show);
    }
    public void onClickCreate(View view){
        Intent intent =new Intent(MainActivity.this, com.example.noteapp2.CreateNoteActivity.class);
        startActivity(intent);
    }

    public void onClickShow(View view){
        Intent intent =new Intent(MainActivity.this, com.example.noteapp2.ShowNoteActivity.class);
        startActivity(intent);
    }
}