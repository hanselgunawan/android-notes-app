package com.hanseltritama.notesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNotesActivity extends AppCompatActivity {

    ArrayList<String> mList;
    SharedPreferences sharedPreferences;
    EditText notesEditText;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        notesEditText = findViewById(R.id.addNotesEditText);

        sharedPreferences = this.getSharedPreferences("com.hanseltritama.notesapp", MODE_PRIVATE);

        if(sharedPreferences.contains("notes")) {
            try {
                mList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("notes", ObjectSerializer.serialize(new ArrayList<String>())));
                if(getIntent().getExtras() != null) {
                    intent = getIntent();
                    int position = intent.getExtras().getInt("ARRAY_POSITION");
                    notesEditText.setText(mList.get(position));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mList = new ArrayList<String>();
        }


    }

    @Override
    public void onBackPressed() {

        if(getIntent().getExtras() == null) {
            mList.add(notesEditText.getText().toString());
        } else {
            mList.set(intent.getExtras().getInt("ARRAY_POSITION"), notesEditText.getText().toString());
            getIntent().removeExtra("ARRAY_POSITION");
        }
        try {
            sharedPreferences.edit().putString("notes", ObjectSerializer.serialize(mList)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);

        super.onBackPressed();
    }
}
