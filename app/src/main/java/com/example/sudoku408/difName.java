package com.example.sudoku408;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class difName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dif_name);
    }

    public void onClick_easy(View view) {
        MyConstants.Difficult = "Hard";

        Intent intent = new Intent(difName.this, Sudoku.class);
        startActivity(intent);
    }

    public void onClick_middle(View view) {
        MyConstants.Difficult = "Normal";

        Intent intent = new Intent(difName.this, Sudoku.class);
        startActivity(intent);
    }

    public void onClick_hard(View view) {
        MyConstants.Difficult = "Easy";

        Intent intent = new Intent(difName.this, Sudoku.class);
        startActivity(intent);
    }

    public void Exit(View view) {
        Intent i = new Intent(difName.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        this.finish();
    }
    public void onExit(View view) {
        Intent i = new Intent(difName.this, difName.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        this.finish();
    }
}