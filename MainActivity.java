package com.example.notatnik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private NotesDatabaseHelper databaseHelper;
    private List<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        databaseHelper = new NotesDatabaseHelper(this);

        loadNotes();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("noteId", position);
                startActivity(intent);
            }
        });
    }

    private void loadNotes() {
        notesList = databaseHelper.getAllNotes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listView.setAdapter(adapter);
    }

    public void addNewNote (View view) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        startActivity(intent);
    }
}
