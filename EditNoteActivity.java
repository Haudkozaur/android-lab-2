package com.example.notatnik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    private EditText editText;
    private NotesDatabaseHelper databaseHelper;
    private int noteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editText = findViewById(R.id.editText);
        databaseHelper = new NotesDatabaseHelper(this);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1) {
            editText.setText(databaseHelper.getNoteById(noteId));
        }
    }

    public void saveNote (View view) {
        String noteText = editText.getText().toString();
        if (noteId == -1) {
            databaseHelper.addNote(noteText);
        } else {
            databaseHelper.updateNote(noteId, noteText);
        }
        finish();
    }

    public void deleteNote (View view) {
        if (noteId != -1) {
            databaseHelper.deleteNote(noteId);
        }
        finish();
    }
}
