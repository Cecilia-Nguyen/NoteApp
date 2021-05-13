package com.example.noteapp2.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp2.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    private List<com.example.noteapp2.model.Note> noteList;
    private Context context;
    private OnRowClickListener listener;

    public NoteAdapter(List<com.example.noteapp2.model.Note> noteList, Context context, OnRowClickListener listener) {
        this.noteList = noteList;
        this.context = context;
        this.listener = listener;
    }

    public interface OnRowClickListener {
        void onItemClick (int position);
    }

    @NonNull
    @Override
    public com.example.noteapp2.model.NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.note, parent, false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_note_title.setText(noteList.get(i).getTitle());
        viewHolder.tv_note.setText(noteList.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_note,tv_note_title;
        public OnRowClickListener listener;

        public ViewHolder(@NonNull View itemView, OnRowClickListener listener) {
            super(itemView);
            tv_note = (TextView)itemView.findViewById(R.id.tv_note);
            tv_note_title = (TextView)itemView.findViewById(R.id.tv_note_title);
            this.listener = listener;

            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
