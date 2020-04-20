package com.hanseltritama.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNotesActivity extends AppCompatActivity {

    ArrayList<String> mList;
    Intent intent;
    Bundle args;
    EditText addNotesEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

    }

    @Override
    public void onBackPressed() {

        intent = getIntent();
        args = new Bundle();
        mList = args.getStringArrayList("ARRAYLIST");
        addNotesEditText = findViewById(R.id.addNotesEditText);
        mList.add(addNotesEditText.getText().toString());
        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);

        super.onBackPressed();
    }
}
