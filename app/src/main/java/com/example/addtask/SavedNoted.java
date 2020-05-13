package com.example.addtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedNoted extends AppCompatActivity {

    @BindView(R.id.recy_note)
    RecyclerView recyclerView;
    private DBHelper dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_noted);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbManager = new DBHelper(this);
        List<Note> noteList = dbManager.getAllNotes();

        if(noteList!=null)
            recyclerView.setAdapter(new NoteAdapter(noteList,this));
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }
}
